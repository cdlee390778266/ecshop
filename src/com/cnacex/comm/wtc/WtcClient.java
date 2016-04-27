package com.cnacex.comm.wtc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cnacex.comm.util.StringUtil;

import weblogic.wtc.gwt.TuxedoConnection;
import weblogic.wtc.jatmi.Ferror;
import weblogic.wtc.jatmi.Reply;
import weblogic.wtc.jatmi.TPException;
import weblogic.wtc.jatmi.TPReplyException;
import weblogic.wtc.jatmi.TypedFML32;

/**
 * @author kereny
 * 
 */
public class WtcClient {

	static Logger logger = LoggerFactory.getLogger(WtcClient.class);

	FldMessage sndFldMsg;

	FldMessage rcvFldMsg;

	private String service;

	TypedFML32 sndFmlBuffer = null;

	TypedFML32 rcvFmlBuffer = null;

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public FldMessage getSndFldMsg() {
		return sndFldMsg;
	}

	public void setSndFldMsg(FldMessage sndFldMsg) {
		this.sndFldMsg = sndFldMsg;
	}

	public FldMessage getRcvFldMsg() {
		return rcvFldMsg;
	}

	public void setRcvFldMsg(FldMessage rcvFldMsg) {
		this.rcvFldMsg = rcvFldMsg;
	}

	/**
	 * 调用WTC服务处理
	 * 
	 * @author kereny
	 * @date 2015-6-4 下午7:23:11
	 * @return
	 * @throws TPException
	 * @throws TPReplyException
	 * @throws Ferror
	 *             boolean
	 * 
	 */
	public boolean tpCallFML32() throws TPException, TPReplyException, Ferror {

		TuxedoConnection tuxConn = null;
		Reply tuxReply;

		if (fml32SetValue() == false) {
			return false;
		}

		logger.debug("调用服务名:  {}", this.service);
		//logger.debug("发送请求报文:  {}", c.FML32toXML(sndFmlBuffer));

		tuxConn = WtcConnect.getConnection();
			
		tuxReply = tuxConn.tpcall(this.service, sndFmlBuffer, 0);
		logger.debug("调用完毕");
			
		rcvFmlBuffer = (TypedFML32) tuxReply.getReplyBuffer();
			
		tuxConn.tpterm();

		if (rcvFmlBuffer == null) {
			logger.error("响应数据报文为空");

			return false;
		}

		if (fml32GetValue() == false) {
			return false;
		}

		return true;
	}

	/**
	 * FML32 压域操作,将对象完成到FML32BUFFER的处理
	 * 
	 * @author kereny
	 * @date 2015-6-6 上午10:37:30
	 * @return
	 * @throws Ferror
	 *             boolean
	 * 
	 */
	private boolean fml32SetValue() throws Ferror {

		sndFmlBuffer = new TypedFML32(new CnacexFld());

		if (sndFldMsg.getVer() == 0) {
			logger.error("通信报文错误 , 未取得协议版本");
			return false;
		}
		sndFmlBuffer.Fchg(sndFmlBuffer.Fldid("PHF_VER"), 0, sndFldMsg.getVer());

		if (StringUtil.nullOrBlank(sndFldMsg.getReqsys())) {
			logger.error("通信报文错误 , 未取得请求方系统号");
			return false;
		}
		sndFmlBuffer.Fchg(sndFmlBuffer.Fldid("PHF_REQSYS"), 0,
				sndFldMsg.getReqsys());

		if (!StringUtil.nullOrBlank(sndFldMsg.getReqnode())) {
			sndFmlBuffer.Fchg(sndFmlBuffer.Fldid("PHF_REQNODE"), 0,
					sndFldMsg.getReqnode());
		} else {
			logger.debug("通信报文未设置请求节点号");
		}

		if (StringUtil.nullOrBlank(sndFldMsg.getTxcode())) {
			logger.error("通信报文错误 , 未取得请求交易码");
			return false;
		}
		sndFmlBuffer.Fchg(sndFmlBuffer.Fldid("PHF_TXCODE"), 0,
				sndFldMsg.getTxcode());

		if (sndFldMsg.getReqno() != 0) {
			sndFmlBuffer.Fchg(sndFmlBuffer.Fldid("PHF_REQNO"), 0,
					sndFldMsg.getReqno());
		} else {
			logger.debug("通信报文未设置请求号");
		}

		if (!StringUtil.nullOrBlank(sndFldMsg.getEncode())) {
			sndFmlBuffer.Fchg(sndFmlBuffer.Fldid("PHF_ENCODE"), 0,
					sndFldMsg.getEncode());
		} else {
			logger.debug("通信报文未设置报文编码方式");
		}

		if (!StringUtil.nullOrBlank(sndFldMsg.getDynauth())) {
			sndFmlBuffer.Fchg(sndFmlBuffer.Fldid("PHF_DYNAUTH"), 0,
					sndFldMsg.getDynauth());
		} else {
			logger.debug("通信报文未设置报文动态认证码");
		}

		sndFmlBuffer.Fchg(sndFmlBuffer.Fldid("PHF_MAC"), 0, sndFldMsg.getMac());

		if (StringUtil.nullOrBlank(sndFldMsg.getData())) {
			logger.error("通信报文错误 , 未取得请求交易报文");
			return false;
		}
		sndFmlBuffer.Fchg(sndFmlBuffer.Fldid("PBF_DATA"), 0, sndFldMsg
				.getData().getBytes());

		return true;
	}

	/**
	 * FML32 解析操作,将FML32BUFFER的数据转换到对象
	 * 
	 * @author kereny
	 * @date 2015-6-6 上午10:38:14
	 * @return boolean
	 * @throws Ferror
	 * 
	 */

	private boolean fml32GetValue() throws Ferror {

		Object obj = null;

		rcvFldMsg = new FldMessage();

		TypedFML32 fml32 = new TypedFML32(new CnacexFld());

		if(fldExist(rcvFmlBuffer, fml32.Fldid("PHF_VER")))
		{
			obj = rcvFmlBuffer.Fget(fml32.Fldid("PHF_VER"), 0);
			if(obj != null)
				rcvFldMsg.setVer(Integer.parseInt(obj.toString().trim()));
			else
			{
				logger.error("通信报文错误 , 响应报文中协议版本号为空 FML32ID {}",
						fml32.Fldid("PHF_VER"));
				return false;
			}
		} else {
			logger.error("通信报文错误 , 响应报文中协议版本号 不存在 FML32ID {}",
					fml32.Fldid("PHF_VER"));
			return false;
		}
		

		if(fldExist(rcvFmlBuffer, fml32.Fldid("PHF_RSPSYS"))){
			obj = rcvFmlBuffer.Fget(fml32.Fldid("PHF_RSPSYS"), 0);
			if (obj != null) {
				rcvFldMsg.setRspsys(obj.toString());
			} else {
				logger.error("通信报文错误 , 响应报文应答方系统号为空 FML32ID {}",
						fml32.Fldid("PHF_RSPSYS"));
				return false;
			}
		} else {
			logger.error("通信报文错误 , 响应报文应答方系统号不存在  FML32ID {}",
					fml32.Fldid("PHF_RSPSYS"));
			return false;
		}
		
		
		if(fldExist(rcvFmlBuffer, fml32.Fldid("PHF_RSPNODE"))){
			obj = rcvFmlBuffer.Fget(fml32.Fldid("PHF_RSPNODE"), 0);
			if (obj == null) {
				logger.debug("响应报文中应答结点号为空FML32ID {}", fml32.Fldid("PHF_RSPNODE"));
			} else {
				rcvFldMsg.setRspnode(obj.toString());
			}
		}else {
			logger.error("通信报文错误 , 响应报文应答方系统号不存在  FML32ID {}",
					fml32.Fldid("PHF_RSPNODE"));
		}
		

		if(fldExist(rcvFmlBuffer, fml32.Fldid("PHF_TXCODE"))){
			obj = rcvFmlBuffer.Fget(fml32.Fldid("PHF_TXCODE"), 0);
			if (obj != null) {
				rcvFldMsg.setTxcode(obj.toString().trim());
			} else {
				logger.error("通信报文错误 , 响应报文交易代码为空FML32ID {}",
						fml32.Fldid("PHF_TXCODE"));
				return false;
			}
		}else{
			logger.error("通信报文错误 , 响应报文交易代码不存在 FML32ID {}",
					fml32.Fldid("PHF_TXCODE"));
			return false;
		}
		
		
		if(fldExist(rcvFmlBuffer, fml32.Fldid("PHF_RSPNO"))){
			obj = rcvFmlBuffer.Fget(fml32.Fldid("PHF_RSPNO"), 0);
			if (obj == null) {
				logger.debug("响应报文中应答编号为空 FML32ID {}", fml32.Fldid("PHF_RSPNO"));
			} else {
				rcvFldMsg.setRspno(Integer.parseInt(obj.toString().trim()));
			}
		}else{
			logger.debug("响应报文中应答编号不存在 FML32ID {}", fml32.Fldid("PHF_RSPNO"));
		}

		if(fldExist(rcvFmlBuffer, fml32.Fldid("PHF_REQNO"))){
			obj = rcvFmlBuffer.Fget(fml32.Fldid("PHF_REQNO"), 0);
			if (obj == null) {
				logger.debug("响应报文中原请求编号为空FML32ID {}", fml32.Fldid("PHF_REQNO"));
			} else {
				rcvFldMsg.setReqno(Integer.parseInt(obj.toString().trim()));
			}
		}else{
			logger.debug("响应报文中原请求编号不存在 FML32ID {}", fml32.Fldid("PHF_REQNO"));
		}

		if(fldExist(rcvFmlBuffer, fml32.Fldid("PHF_ENCODE"))){
			obj = rcvFmlBuffer.Fget(fml32.Fldid("PHF_ENCODE"), 0);
			if (obj == null) {
				logger.debug("响应报文中报文编码方式为空 FML32ID {}", fml32.Fldid("PHF_ENCODE"));
			} else {
				rcvFldMsg.setEncode(obj.toString());
			}
		}else{
			logger.debug("响应报文中报文编码方式不存在 FML32ID {}", fml32.Fldid("PHF_ENCODE"));
		}

		if(fldExist(rcvFmlBuffer, fml32.Fldid("PHF_DYNAUTH"))){
			obj = rcvFmlBuffer.Fget(fml32.Fldid("PHF_DYNAUTH"), 0);
			if (obj == null) {
				logger.debug("响应报文中报文动态认证码为空 FML32ID {}", fml32.Fldid("PHF_DYNAUTH"));
			} else {
				rcvFldMsg.setDynauth(obj.toString());
			}
		}else{
			logger.debug("响应报文中报文动态认证码不存在 FML32ID {}", fml32.Fldid("PHF_DYNAUTH"));
		}

		if(fldExist(rcvFmlBuffer, fml32.Fldid("PHF_RSPSTAT"))){
			obj = rcvFmlBuffer.Fget(fml32.Fldid("PHF_RSPSTAT"), 0);
			if (obj != null) {
				rcvFldMsg.setRspstat(obj.toString());
			} else {
				logger.error("通信报文错误 , 响应报文应答码为空FML32ID {}",
						fml32.Fldid("PHF_RSPSTAT"));
				return false;
			}
		}else{
			logger.error("通信报文错误 , 响应报文应答码不存在 FML32ID {}",
					fml32.Fldid("PHF_RSPSTAT"));
			return false;
		}

		
		if(fldExist(rcvFmlBuffer, fml32.Fldid("PHF_MAC"))){
			obj = rcvFmlBuffer.Fget(fml32.Fldid("PHF_MAC"), 0);
			if (obj != null) {
				rcvFldMsg.setMac(Integer.parseInt(obj.toString()));
			} else {
				logger.error("通信报文错误 , 响应报文校验码为空 FML32ID {}",
						fml32.Fldid("PHF_MAC"));
				return false;
			}
		}else{
			logger.error("通信报文错误 , 响应报文校验码不存在  FML32ID {}",
					fml32.Fldid("PHF_MAC"));
			return false;
		}
		

		if(fldExist(rcvFmlBuffer, fml32.Fldid("PBF_DATA"))){
			obj = rcvFmlBuffer.Fget(fml32.Fldid("PBF_DATA"), 0);
			if (obj != null) {
				rcvFldMsg.setData(new String((byte[]) obj));
			} else {
				logger.error("通信报文错误 , 响应报文无返回数据 FML32ID {}",
						fml32.Fldid("PBF_DATA"));
				return false;
			}
		}else{
			logger.error("通信报文错误 , 响应报文无返回域  FML32ID {}",
					fml32.Fldid("PBF_DATA"));
			return false;
		}

		return false;

	}

		/**
	     *  验证FML32域是否存在
		 * @author kereny
		 * @date 2015-6-6 下午1:47:06
		 * @param fml32
		 * @param filId
		 * @return
		 * boolean
	     *
		 */
	private boolean fldExist(TypedFML32 fml32, int filId) {

		try {
			fml32.Fget(filId, 0);
		} catch (Ferror e) {
			if (e.getFerror() == Ferror.FNOTPRES) {
				return false;
			}
			return true;
		}
		return true;
	}
}
