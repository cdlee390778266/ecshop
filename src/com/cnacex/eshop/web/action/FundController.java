package com.cnacex.eshop.web.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cnacex.eshop.modul.JSonComm;
import com.cnacex.eshop.msg.body.fund.BusBalAmtRsp.BusDetail;
import com.cnacex.eshop.msg.body.fund.QueryAccSReq;
import com.cnacex.eshop.msg.body.fund.QueryAccSRsp.Acct;
import com.cnacex.eshop.msg.body.fund.QueryAccSRsp.Acct.OccDetail;
import com.cnacex.eshop.msg.body.mall.Listed;
import com.cnacex.eshop.msg.body.mall.MdseElement;
import com.cnacex.eshop.msg.body.member.LoginRsp;
import com.cnacex.eshop.msg.body.member.LoginRsp.MemMarket;
import com.cnacex.eshop.msg.body.member.LoginRsp.TxComm;
import com.cnacex.eshop.msg.body.trade.sell.ApplyReq;
import com.cnacex.eshop.msg.xml.fund.BalAmtRspMsg;
import com.cnacex.eshop.msg.xml.fund.BusBalAmtRspMsg;
import com.cnacex.eshop.msg.xml.fund.QueryAccSRspMsg;
import com.cnacex.eshop.msg.xml.mall.QueryCommRspMsg;
import com.cnacex.eshop.service.IFundService;
import com.cnacex.eshop.util.AcctTrUtil;
import com.cnacex.eshop.util.ActTypeUtil;
import com.cnacex.eshop.util.CostUtil;
import com.cnacex.eshop.util.IAEUtil;
import com.cnacex.eshop.util.ListedUtil;


/**
 * @author kereny
 *
 */

@Controller
@RequestMapping(value="/fund")
public class FundController extends TradeController{
	
	static Logger logger = LoggerFactory.getLogger(FundController.class);
	
	@Autowired
	private IFundService fundService;
	

		/**
	     *  资金余额查询
		 * @author kereny
		 * @date 2015-7-30 上午9:53:43
		 * @param model
		 * @return
		 * String
	     *
		 */
	@RequestMapping(value="/info.htm")
	public String info(ModelMap model)
    {  

		//查询可用资金返回
		LoginRsp loginRsp = (LoginRsp) request.getSession().getAttribute("userinfo");
		
		BusBalAmtRspMsg balRspMsg = fundService.queryBusAvalAmt(loginRsp.getmID());
		
		if (balRspMsg.getHead() == null) {
			model.addAttribute("message",  balRspMsg.getFault().getRspMsg());
			return "comm/fail";
		}

		if (balRspMsg.getHead().getSuccFlag() != 1) {
			model.addAttribute("message",  balRspMsg.getHead().getRspMsg());
			return "comm/fail";
		}
					
		List<BusDetail> details = balRspMsg.getBody().getDetails();
		if(details != null && details.size() > 0){
			List<BusDetail> rsdetails = new ArrayList<BusDetail>();
			for(BusDetail d:details){
				d.setAcctTypeName(ActTypeUtil.getValue(d.getAcctType()));
				rsdetails.add(d);
			}
			
			balRspMsg.getBody().setDetails(rsdetails);
			
		}
		
		model.addAttribute("rspBody", balRspMsg.getBody());
		getUrlMatch(loginRsp.getTradeMenus(), model);
		return "fund/info";
    }
	
	
		/**
	     *  账务明细查询
		 * @author kereny
		 * @date 2015-7-30 上午9:54:07
		 * @param model
		 * @return
		 * String
	     *
		 */
	@RequestMapping(value="/list.htm")
	public String list(ModelMap model)
    {  

		LoginRsp loginRsp = (LoginRsp) request.getSession().getAttribute("userinfo");
		
		model.addAttribute("costList", CostUtil.getCostList());
		
		getUrlMatch(loginRsp.getTradeMenus(), model);
		return "fund/list";
    }
	
	
		/**
	     *  条件查询账务明细
		 * @author kereny
		 * @date 2015-7-30 上午9:54:27
		 * @param queryAccSReq
		 * @param pageNum
		 * @param pageSize
		 * @param model
		 * @return
		 * JSonComm
	     *
		 */
	@RequestMapping(value="/findlist.htm")
	public @ResponseBody JSonComm findlist(
			@ModelAttribute QueryAccSReq queryAccSReq,
			@RequestParam(value = "pageNum", required = false, defaultValue = "0") int pageNum, 
			@RequestParam(value = "pageSize", required = false, defaultValue = "20" ) int pageSize,
			ModelMap model)
    {  

		LoginRsp loginRsp = (LoginRsp) request.getSession().getAttribute("userinfo");
		

		queryAccSReq.setmID(loginRsp.getmID());
		
		logger.debug("query {}", queryAccSReq.toString());
		
		QueryAccSRspMsg rspMsg = fundService.queryAccList(queryAccSReq);	
		JSonComm  jsdata = new JSonComm();
		if (rspMsg.getHead() == null) {
			jsdata.setSuccflag(-1);
			jsdata.setMsg(rspMsg.getFault().getRspMsg());
			return jsdata;
		}

		if (rspMsg.getHead().getSuccFlag() != 1) {
			jsdata.setSuccflag(-2);
			jsdata.setMsg(rspMsg.getHead().getRspMsg());
			return jsdata;
		}
		
		if(rspMsg.getBody().getAccts() == null || rspMsg.getBody().getAccts().size() == 0){
			jsdata.setSuccflag(-2);
			jsdata.setMsg("无返回数据列表");
			return jsdata;
		}
		
		List<Acct> accts = rspMsg.getBody().getAccts();
		List<Acct> rsaccts = new ArrayList<Acct>();
		boolean bfindDetail = false;
		for(Acct acct :accts){
			
			 List<OccDetail> details = acct.getOccDetails();
			 if(details != null && details.size() > 0){
				 bfindDetail = true;
				 List<OccDetail> rsdetails =new  ArrayList<OccDetail>();
				 
				 for(OccDetail occ: details){
					 occ.setIaeName(IAEUtil.getValue(occ.getIae()));
					 occ.setFundName(CostUtil.getValue(occ.getFundCode()));	
					 occ.setTrTypeName(AcctTrUtil.getValue(occ.getTrType()));
					 
					 rsdetails.add(occ);									
					 
				 }
				 acct.setOccDetails(rsdetails);
				 acct.setQryAcctTypeName(ActTypeUtil.getValue(acct.getQryAcctType()));
			 }
			 rsaccts.add(acct);
		}
		
		rspMsg.getBody().setAccts(rsaccts);
		
		if(bfindDetail == false){
			jsdata.setSuccflag(-2);
			jsdata.setMsg("无返回数据列表");
			return jsdata;
		}
		
		jsdata.setData(rspMsg.getBody());
		jsdata.setSuccflag(0);
		jsdata.setMsg("success");
		 
		return jsdata;
    }
	

	@RequestMapping(value="/findplatlist.htm")
	public @ResponseBody HashMap<String, Object> findPlatList(
			@ModelAttribute QueryAccSReq queryAccSReq,
			ModelMap model)
	{  
	
		LoginRsp loginRsp = (LoginRsp) request.getSession().getAttribute("userinfo");
		queryAccSReq.setmID(loginRsp.getmID());
		
		logger.debug("query {}", queryAccSReq.toString());
		
		HashMap<String, Object> maps = new HashMap<String, Object>();
		
		QueryAccSRspMsg rspMsg = fundService.queryAccList(queryAccSReq);	
		List<OccDetail> rsdetails =new  ArrayList<OccDetail>();
		if (rspMsg.getHead() == null) {
			maps.put("data", rsdetails);
			return maps;
		}
	
		if (rspMsg.getHead().getSuccFlag() != 1) {
			maps.put("data", rsdetails);
			return maps;
		}
		
		if(rspMsg.getBody().getAccts() == null || rspMsg.getBody().getAccts().size() == 0){
			maps.put("data", rsdetails);
			return maps;
		}
		
		List<Acct> accts = rspMsg.getBody().getAccts();

		for(Acct acct :accts){
			 List<OccDetail> details = acct.getOccDetails();
			 if(details != null && details.size() > 0 && "00".equalsIgnoreCase(acct.getQryAcctType())){
				 for(OccDetail occ: details){
					 occ.setIaeName(IAEUtil.getValue(occ.getIae()));
					 occ.setFundName(CostUtil.getValue(occ.getFundCode()));	
					 occ.setTrTypeName(AcctTrUtil.getValue(occ.getTrType()));
					 
					 if("E".equalsIgnoreCase(occ.getIae()) && occ.getChgABV() > 0){					 
						 	occ.setChgABV(0-occ.getChgABV());
					 }
					 rsdetails.add(occ);
				 }

			 }
		}
		
		maps.put("data", rsdetails);
		return maps;
	}

		
}
