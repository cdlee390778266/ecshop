package com.cnacex.eshop.web.action;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cnacex.comm.util.DivisionUtil;
import com.cnacex.eshop.modul.JSonComm;
import com.cnacex.eshop.modul.NodeDiv;
import com.cnacex.eshop.msg.body.member.DivisMemberReq;
import com.cnacex.eshop.msg.body.member.DivisMemberRsp.MemList;
import com.cnacex.eshop.msg.body.member.LoginRsp;
import com.cnacex.eshop.msg.xml.member.DivisMemberRspMsg;
import com.cnacex.eshop.service.IMemberService;



/**
 * @author kereny
 *
 */

@Controller
@RequestMapping(value="/divis")
public class DivisController{
	
	static Logger logger = LoggerFactory.getLogger(DivisController.class);
	
	@Autowired
	IMemberService memberService;
	
	@Autowired
	private  HttpServletRequest request;

	@RequestMapping(value="/findprov.htm")
	public @ResponseBody JSonComm findProv(
			@RequestParam(value = "key", required = true, defaultValue = "0086") String key)
    {  

		JSonComm  jsdata = new JSonComm();
		
		List<NodeDiv> provs =  DivisionUtil.findProvince(key); 
		
		
		if(provs == null || provs.size() == 0){
			jsdata.setSuccflag(-2);
			jsdata.setMsg("无返回数据列表");
			return jsdata;
		}else{
			jsdata.setSuccflag(0);
			jsdata.setData(provs);
			return jsdata;
		}
    }
	
	
	@RequestMapping(value="/findcity.htm")
	public @ResponseBody JSonComm findCity(
			@RequestParam(value = "key", required = true, defaultValue = "0") String key)
    {  

		JSonComm  jsdata = new JSonComm();
		
		List<NodeDiv> provs =  DivisionUtil.findCity(key); 
		
		
		if(provs == null || provs.size() == 0){
			jsdata.setSuccflag(-2);
			jsdata.setMsg("无返回数据列表");
			return jsdata;
		}else{
			jsdata.setSuccflag(0);
			jsdata.setData(provs);
			return jsdata;
		}
    }
	
	
	@RequestMapping(value="/finddist.htm")
	public @ResponseBody JSonComm findCounty(
			@RequestParam(value = "key", required = true, defaultValue = "0") String key)
    {  

		JSonComm  jsdata = new JSonComm();
		
		List<NodeDiv> provs =  DivisionUtil.findCounty(key); 
		
		
		if(provs == null || provs.size() == 0){
			jsdata.setSuccflag(-2);
			jsdata.setMsg("无返回数据列表");
			return jsdata;
		}else{
			jsdata.setSuccflag(0);
			jsdata.setData(provs);
			return jsdata;
		}
    }
	
	
	@RequestMapping(value="/findmember.htm")
	public @ResponseBody JSonComm findMembers(
			@RequestParam(value = "divlevel", required = true, defaultValue = "0") String divlevel,
			@RequestParam(value = "divcode", required = true, defaultValue = "0") String divCode,
			@RequestParam(value = "markcode", required = true, defaultValue = "0") String markCode,
			@RequestParam(value = "pagenum", required = true, defaultValue = "0") int pageNum,
			@RequestParam(value = "pagesize", required = true, defaultValue = "0") int pageSize	
			)
    {  

		JSonComm  jsdata = new JSonComm();
		
		DivisMemberReq divisMemberReq = new DivisMemberReq();
		
		LoginRsp loginRsp =(LoginRsp) request.getSession().getAttribute("userinfo");
		
		divisMemberReq.setDivLevel(divlevel);
		divisMemberReq.setDivCode(divCode);
		divisMemberReq.setMarkCode(markCode);
		//不分页,查所有
		divisMemberReq.setReqStart(Integer.toString(pageSize*pageNum+1));
		if(pageSize > 0){
			divisMemberReq.setReqNum(Integer.toString(pageSize));	
		}
		
		DivisMemberRspMsg rspMsg = memberService.findMemberList(divisMemberReq);
		
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
		
		List<MemList> mems = rspMsg.getBody().getMemList();
		if(mems != null && mems.size() > 0){
			for(MemList m:mems)
				if(m.getmID().equalsIgnoreCase(loginRsp.getmID())){
					mems.remove(m);
					break;
				}
		}
		
		rspMsg.getBody().setMemList(mems);
		
		if(rspMsg.getBody().getMemList() == null ||rspMsg.getBody().getMemList().size() == 0){
			
			jsdata.setSuccflag(-2);
			jsdata.setMsg("无数据列表");
			return jsdata;
		}
		
		jsdata.setSuccflag(0);
		jsdata.setData(rspMsg.getBody());
		return jsdata;
		
    }
}
