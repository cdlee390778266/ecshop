package com.cnacex.eshop.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;

import weblogic.wtc.jatmi.Ferror;
import weblogic.wtc.jatmi.TPException;
import weblogic.wtc.jatmi.TPReplyException;

import com.cnacex.comm.util.Config;
import com.cnacex.comm.util.StringUtil;
import com.cnacex.comm.util.XmlUtil;
import com.cnacex.comm.wtc.FldMessage;
import com.cnacex.comm.wtc.WtcClient;
import com.cnacex.eshop.msg.AbstractReqMsg;
import com.cnacex.eshop.msg.AbstractRspMsg;
import com.cnacex.eshop.msg.CommRspMsg;
import com.cnacex.eshop.msg.Fault;
import com.cnacex.eshop.msg.Head;
import com.cnacex.eshop.util.FMLConfManager;
import com.cnacex.eshop.util.TxConfig;

import org.springframework.stereotype.Repository;

/**
 * @author Administrator
 *
 */

@Repository("baseDAO") 
public class BaseDAO {
	
	static Logger logger = LoggerFactory.getLogger(BaseDAO.class);
	
		/**
	     *  根据输入数据返回交易数据的结果数据
		 * @author kereny
		 * @date 2015-6-8 下午1:30:11
		 * @param reqMsgCls
		 * @param respMsgCls
		 * @return
		 * T
	     *
		 */
	public <B, T extends AbstractRspMsg<?>> T handle(AbstractReqMsg<B> reqMsgCls,
			Class<T> respMsgCls) {
		
		Fault fault = new Fault();
		WtcClient wtcclient = new WtcClient();
		FldMessage fldMsg = new FldMessage();
		fldMsg.setVer(FMLConfManager.PHF_VER);
		fldMsg.setReqno(FMLConfManager.buildREQNO());
		fldMsg.setReqnode(FMLConfManager.PHF_REQNODE);
		fldMsg.setReqsys(FMLConfManager.PHF_REQSYS);
		fldMsg.setTxcode(reqMsgCls.getTxCode());

		wtcclient.setService(TxConfig.getSvcName(reqMsgCls.getTxCode()));
		StringBuffer strbuf = new StringBuffer("");
		strbuf.append(XmlUtil.toXml(reqMsgCls));
		fldMsg.setMac(FMLConfManager.getCRC32(strbuf.toString()));
		fldMsg.setData(strbuf.toString());
		wtcclient.setSndFldMsg(fldMsg);
		
		StopWatch timer = new StopWatch(reqMsgCls.getTxCode()+"-"+fldMsg.getReqno());
		timer.start();
		try {
			wtcclient.tpCallFML32();
		} catch (TPReplyException e) {
			fault.setRspCode("9999");
			fault.setRspMsg("通讯错误,错误号:【"+e.gettperrno()+"】 错误信息:【"+ e.getMessage()+"】");
			e.printStackTrace();
		} catch (TPException e) {
			fault.setRspCode("9999");
			fault.setRspMsg("通讯错误,错误号:【"+e.gettperrno()+"】 错误信息:【"+ e.getMessage()+"】");
			e.printStackTrace();
		} catch (Ferror e) {
			fault.setRspCode("9999");
			fault.setRspMsg("通讯错误,错误号:【"+e.getFerror()+"】 错误信息:【"+ e.getMessage()+"】");
			e.printStackTrace();
		} catch (Exception e){
			fault.setRspCode("9999");
			fault.setRspMsg("系统错误, 错误信息:【"+ e.getMessage()+"】");
			e.printStackTrace();
		}
		finally{
			timer.stop();
			logger.info(timer.toString());
		}
		FldMessage fldRcvMsg = wtcclient.getRcvFldMsg();
		
		if(fldRcvMsg != null)
		{
			int crcVal = FMLConfManager.getCRC32(fldRcvMsg.getData()) ;
			if(crcVal != fldRcvMsg.getMac())
			{
				logger.error("系统CRC校验失败, 接收数据:{} CRC:{},计算CRC:{}", fldRcvMsg.getData(), fldRcvMsg.getMac(), crcVal);
			}
		}
		
		if(fldRcvMsg == null || fldRcvMsg.getData() == null)
		{
			logger.debug(fldRcvMsg.toString());
			T msg = null;
			if(StringUtil.nullOrBlank(fault.getRspCode()))
			{
				fault.setRspCode("99998");
				fault.setRspMsg("系统错误,未知异常,请联系系统管理员");
			}
			try {
				msg = respMsgCls.newInstance();
				msg.setFault(fault);
			} catch (Exception e) {
				throw new RuntimeException(String.format("构建 %s 实例 失败!", reqMsgCls), e);
			}
			logger.error("接收数据错误  请求号{}", fldMsg.getReqno());
			msg.getFault().setReqNo(fldMsg.getReqno());
			return msg;
		}
		
		T t= XmlUtil.fromXml(fldRcvMsg.getData(), respMsgCls);
		
		if(t.getHead() != null){
			t.getHead().setReqno(fldMsg.getReqno());
			
			if(t.getHead().getSuccFlag() != 1){
				logger.info("交易处理业务错误  请求号:{} 请求信息:{} 返回错误信息:{}", fldMsg.getReqno(), strbuf.toString(),  t.getHead().getTxRspMsg());
			}
		}
		
		if(t.getFault() != null){
			t.getFault().setReqNo(fldMsg.getReqno());
			
			if(t.getFault().getFaultstring()!=null && t.getFault().getFaultstring().size() > 0){
				logger.info("交易处理系统错误  请求号:{} 请求信息:{} 返回错误信息:{}", fldMsg.getReqno(), strbuf.toString(), t.getFault().getRspMsg());
			}
		}
			
		return t;
	}

}
