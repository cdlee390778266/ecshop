package com.cnacex.eshop.web.action;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cnacex.comm.util.StringUtil;
import com.cnacex.eshop.modul.JSonComm;
import com.cnacex.eshop.msg.body.mall.MdseElement;
import com.cnacex.eshop.msg.body.member.LoginRsp;
import com.cnacex.eshop.msg.body.query.HisQueryReq;
import com.cnacex.eshop.msg.body.query.HisQueryRsp;
import com.cnacex.eshop.msg.body.trade.delivery.Strike;
import com.cnacex.eshop.msg.xml.query.HisQueryRspMsg;
import com.cnacex.eshop.service.IQueryService;
import com.cnacex.eshop.util.ListedUtil;
import com.cnacex.eshop.util.StatusUtil;



/**
 * @author kereny
 *
 */

@Controller
@RequestMapping(value="/query")
public class QueryController extends TradeController{
	

	@Autowired
	private IQueryService queryService;
	
	
	static Logger logger = LoggerFactory.getLogger(QueryController.class);
	
		/**
	     *  成交查询卖列表页面
		 * @author kereny
		 * @date 2015-7-25 下午12:15:27
		 * @param model
		 * @return
		 * String
	     *
		 */
	@RequestMapping(value = "/selllist.htm")
	public String selllist(ModelMap model){
		
		LoginRsp loginRsp = (LoginRsp) request.getSession().getAttribute("userinfo");

		getUrlMatch(loginRsp.getTradeMenus(), model);
		return "query/sell";
	}
	
	
		

		/**
	     *  成交查询买列表页面
		 * @author kereny
		 * @date 2015-7-25 下午12:16:10
		 * @param model
		 * @return
		 * String
	     *
		 */
	@RequestMapping(value = "/buylist.htm")
	public String buylist(ModelMap model){
		

		LoginRsp loginRsp = (LoginRsp) request.getSession().getAttribute("userinfo");
		getUrlMatch(loginRsp.getTradeMenus(), model);
		return "query/buy";
	}

	
	
	
		/**
	     *  JSON成交查询
		 * @author kereny
		 * @date 2015-7-25 下午12:17:41
		 * @param type
		 * @param pageNum
		 * @param pageSize
		 * @param code
		 * @param qryType
		 * @param beginDate
		 * @param endData
		 * @param model
		 * @return
		 * JSonComm
	     *
		 */
	@RequestMapping(value = "/findlist.htm")
	public @ResponseBody
	JSonComm findList(
			@RequestParam(value = "type", required = true) String type,
			@RequestParam(value = "pageNum", required = false, defaultValue = "0") int pageNum, 
			@RequestParam(value = "pageSize", required = false, defaultValue = "20" ) int pageSize,
			@RequestParam(value = "code", required = false) String code,
			@RequestParam(value = "qryType", required = false) String qryType,
			@RequestParam(value = "strikeDate", required = false) String strikeDate,	
			ModelMap model){
		

		LoginRsp loginRsp = (LoginRsp) request.getSession().getAttribute("userinfo");

		JSonComm js = new JSonComm();

		HisQueryReq hisQueryReq = new HisQueryReq();
		hisQueryReq.setmID(loginRsp.getmID());
		hisQueryReq.setOperID(loginRsp.getOperID());
		if(pageSize > 0){
			hisQueryReq.setReqNum(Integer.toString(pageSize));
			hisQueryReq.setReqStart(Integer.toString(pageSize*pageNum+1));
		}
		
		if(!StringUtil.nullOrBlank(code))
			hisQueryReq.setCommCode(code);
		
		if("H".equalsIgnoreCase(qryType)){
			hisQueryReq.setStrikeDate(strikeDate);
		}
				
		HisQueryRspMsg rspMsg = queryService.queryHisList(hisQueryReq, type);
			
		if (rspMsg.getHead() == null) {
			js.setSuccflag(-1);
			js.setMsg(rspMsg.getFault().getRspMsg());
			return js;
		}
		
		if (rspMsg.getHead().getSuccFlag() != 1) {
			js.setSuccflag(-1);
			js.setMsg(rspMsg.getHead().getRspMsg());
			return js;
		}
		
		HisQueryRsp rspBody = rspMsg.getBody();
		if(rspBody == null || rspBody.getTotalNum() == 0){
			js.setSuccflag(-1);
			js.setMsg("无成交数据");
			return js;
		}
		
		List<Strike> hisStrikes = rspBody.getHisStrikes();
		
		if( hisStrikes != null && hisStrikes.size() > 0)
		{
			List<Strike> rshisStrikes = new ArrayList<Strike>();
			for(Strike s: hisStrikes)
			{
				MdseElement me = mallService.findLocalMdseEntity(s.getCommCode());
				if(me != null){
					s.setCommName(me.getMdseName());
					s.setUom(me.getUom());
				}

				s.setListedTypeName(ListedUtil.getListedName(s.getListedType()));
				s.setStatusDesc(StatusUtil.getBuyStatus(s.getStatus()));
				
				double   amt   =   s.getUp()*s.getVol();
				BigDecimal   bigAmt   =   new   BigDecimal(amt);  
				s.setContAmt(bigAmt.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue());  
				
				rshisStrikes.add(s);
				
			}
			rspBody.setHisStrikes(rshisStrikes);
		}
		js.setSuccflag(0);
		js.setMsg("success");
		js.setData(rspBody);
		return js;
	}
	
	
		/**
	     *  JSON成交查询
		 * @author kereny
		 * @date 2015-7-25 下午12:17:41
		 * @param type
		 * @param pageNum
		 * @param pageSize
		 * @param code
		 * @param qryType
		 * @param beginDate
		 * @param endData
		 * @param model
		 * @return
		 * JSonComm
	     *
		 */
	@RequestMapping(value = "/finddevliery.htm")
	public @ResponseBody
	HashMap<String, Object>  findDevliery(
			@RequestParam(value = "type", required = true) String type,
			@RequestParam(value = "code", required = false) String code,
			@RequestParam(value = "estrikeDate", required = false) String estrikeDate,	
			@RequestParam(value = "strikeDate", required = false) String strikeDate,	
			ModelMap model){
		
	
		LoginRsp loginRsp = (LoginRsp) request.getSession().getAttribute("userinfo");
	
		HashMap<String, Object> maps = new HashMap<String, Object>();
	
		HisQueryReq hisQueryReq = new HisQueryReq();
		hisQueryReq.setmID(loginRsp.getmID());
		hisQueryReq.setOperID(loginRsp.getOperID());

		
		if(!StringUtil.nullOrBlank(code))
			hisQueryReq.setCommCode(code);
		
		
		hisQueryReq.setStrikeDate(strikeDate);
		hisQueryReq.setEstrikeDate(estrikeDate);
		
				
		HisQueryRspMsg rspMsg = queryService.queryHisList(hisQueryReq, type);
		List<Strike> rshisStrikes = new ArrayList<Strike>();	
		if (rspMsg.getHead() == null) {
			maps.put("data", rshisStrikes);
			return maps;
		}
		
		if (rspMsg.getHead().getSuccFlag() != 1) {
			maps.put("data", rshisStrikes);
			return maps;
		}
		
		HisQueryRsp rspBody = rspMsg.getBody();
		if(rspBody == null || rspBody.getTotalNum() == 0){
			maps.put("data", rshisStrikes);
			return maps;
		}
		
		List<Strike> hisStrikes = rspBody.getHisStrikes();
		
		if( hisStrikes != null && hisStrikes.size() > 0)
		{
			
			for(Strike s: hisStrikes)
			{
				MdseElement me = mallService.findLocalMdseEntity(s.getCommCode());
				if(me != null)
				{
					s.setCommName(me.getMdseName());
					s.setUom(me.getUom());
					s.setClsCode(me.getPmdseCode());
					
					MdseElement clsme = mallService.findLocalMdseEntity(me.getPmdseCode());
					if(clsme != null){
						s.setClsName(clsme.getMdseName());
					}
				}
				
				if(!StringUtil.nullOrBlank(s.getSummary1()))
					s.setSummary1(s.getSummary1().substring(s.getSummary1().indexOf(":")+1));
				
				if(!StringUtil.nullOrBlank(s.getSummary2()))
					s.setSummary2(s.getSummary2().substring(s.getSummary2().indexOf(":")+1));
				
				if(!StringUtil.nullOrBlank(s.getSummary3()))
					s.setSummary3(s.getSummary3().substring(s.getSummary3().indexOf(":")+1));
				
				if(!StringUtil.nullOrBlank(s.getSummary4()))
					s.setSummary4(s.getSummary4().substring(s.getSummary4().indexOf(":")+1));
	
				s.setListedTypeName(ListedUtil.getListedName(s.getListedType()));
				s.setStatusDesc(StatusUtil.getBuyStatus(s.getStatus()));
				
				double   amt   =   s.getUp()*s.getVol();
				BigDecimal   bigAmt   =   new   BigDecimal(amt);  
				s.setContAmt(bigAmt.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue());  
				
				rshisStrikes.add(s);
				
			}
			rspBody.setHisStrikes(rshisStrikes);
		}
		maps.put("data", rshisStrikes);
		return maps;
	}
		
}
