package com.cnacex.eshop.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
import com.cnacex.eshop.msg.body.comm.OperRight;
import com.cnacex.eshop.msg.body.mall.MdseElement;
import com.cnacex.eshop.msg.body.member.LoginRsp;
import com.cnacex.eshop.msg.body.trade.sell.ApplyCdReq;
import com.cnacex.eshop.msg.body.trade.sell.Receipts;
import com.cnacex.eshop.msg.body.warehouse.BindingMemberReq;
import com.cnacex.eshop.msg.body.warehouse.CancelReceipts;
import com.cnacex.eshop.msg.body.warehouse.CancelWarehouseReq;
import com.cnacex.eshop.msg.body.warehouse.IssueWarehouseListReq;
import com.cnacex.eshop.msg.body.warehouse.ROutWarehouseReq;
import com.cnacex.eshop.msg.body.warehouse.RegWarehouseCancelReq;
import com.cnacex.eshop.msg.body.warehouse.RegWarehouseListReq;
import com.cnacex.eshop.msg.body.warehouse.TrigBindingMemberReq;
import com.cnacex.eshop.msg.body.warehouse.WarehouseReceipts;
import com.cnacex.eshop.msg.xml.trade.sell.ApplyCdRspMsg;
import com.cnacex.eshop.msg.xml.warehouse.BindingMemberRspMsg;
import com.cnacex.eshop.msg.xml.warehouse.CancelWarehouseRspMsg;
import com.cnacex.eshop.msg.xml.warehouse.IssueWarehouseListRspMsg;
import com.cnacex.eshop.msg.xml.warehouse.ROutWarehouseRspMsg;
import com.cnacex.eshop.msg.xml.warehouse.RegWarehouseCancelRspMsg;
import com.cnacex.eshop.msg.xml.warehouse.RegWarehouseListRspMsg;
import com.cnacex.eshop.msg.xml.warehouse.TrigBindingMemberRspMsg;
import com.cnacex.eshop.service.ISellService;
import com.cnacex.eshop.service.IWarehouseService;

/**
 * 仓单管理
 * 
 * @author文闻
 * 
 */
@Controller
@RequestMapping(value = "/warehouse")
public class WarehouseController extends TradeController {

	static Logger logger = LoggerFactory.getLogger(WarehouseController.class);

	@Autowired
	private IWarehouseService warehouseService;
	
	@Autowired
	private ISellService sellService;

	/**
	 * 转跳到签发仓单的查询页面
	 * 
	 * @author 文闻
	 * @date 2016-4-5
	 * @param request
	 * @param model
	 * @return String
	 *
	 */
	@RequestMapping(value = "/list.htm")
	public String list(HttpServletRequest request, ModelMap model) {

		LoginRsp loginRsp = (LoginRsp) request.getSession().getAttribute("userinfo");

		// 判断登陆用户是否有T权限
		model.addAttribute("enablePay", 0);
		List<OperRight> or = loginRsp.getOperRights();
		for (OperRight operRight : or) {
			if(operRight.getRightType().equalsIgnoreCase("T")){
				model.addAttribute("enablePay", 1);
			}
		}

		logger.debug("转跳签发仓单注册页面 ");
		return "warehouse/list";
	}
	
	/**
	 * 签发仓单查询
	 * @author 文闻
	 * @data 2016-3-30
	 * @param code
	 * @param status
	 * @param pageNum
	 * @param pageSize
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/findList.htm")
	public @ResponseBody JSonComm  findList(
			@RequestParam(value = "commcode", required = false) String commcode,
			@ModelAttribute IssueWarehouseListReq apply,
			@RequestParam(value = "contTime", required = false) String contTime,
			@RequestParam(value = "econtTime", required = false) String econtTime,
			ModelMap model,HttpServletRequest request) {
		LoginRsp loginRsp = (LoginRsp)request.getSession().getAttribute("userinfo");
		//获取用户账号
		String mid = loginRsp.getmID();
		apply.setMid(mid);
		apply.setStatus(2l);
		apply.setEdate(econtTime);
		apply.setSdate(contTime);
		IssueWarehouseListRspMsg rspMsg = warehouseService.findIssueWarehouseList(apply);
		JSonComm js = new JSonComm();
		if(rspMsg.getHead() == null){
			
			js.setSuccflag(-1);
			js.setMsg(rspMsg.getFault().getRspMsg());
			js.setData("");
			return js;
		}
		
		if(rspMsg.getHead().getSuccFlag() != 1){			
			js.setSuccflag(-2);
			js.setMsg(rspMsg.getHead().getRspMsg());
			js.setData("");
			return js;
		}
		if(rspMsg.getBody().getRseceipts() != null && !"".equals(rspMsg.getBody().getRseceipts())){
			List<WarehouseReceipts> r =rspMsg.getBody().getRseceipts();
			for (WarehouseReceipts receipts : r) {
				MdseElement mdseElement = mallService.findLocalMdseEntity(receipts.getCommcode());
				receipts.setUnit(mdseElement.getUom());
				receipts.setMemname(rspMsg.getBody().getMemname());
				receipts.setProvid(rspMsg.getBody().getProvid());
			}
			js.setData(r);
		}else{
			js.setData("");
		}	
		
		return js;
	}
	
	/**
	 * 签发仓单注册
	 * @author 文闻
	 * @data 2016-4-6
	 * @param storeno
	 * @param receiptno
	 * @param qty
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/register.htm")
	public @ResponseBody JSonComm  register(
			@ModelAttribute RegWarehouseListReq apply,
			ModelMap model,HttpServletRequest request) {

		LoginRsp loginRsp = (LoginRsp)request.getSession().getAttribute("userinfo");
		//获取用户账号
		String mid = loginRsp.getmID();
		apply.setMid(mid);	
		RegWarehouseListRspMsg rspMsg = warehouseService.register(apply);
		JSonComm js = new JSonComm();
		if(rspMsg.getHead() == null){
			
			js.setSuccflag(-1);
			js.setMsg(rspMsg.getFault().getRspMsg());
			js.setData("");
			return js;
		}
		
		if(rspMsg.getHead().getSuccFlag() != 1){			
			js.setSuccflag(-2);
			js.setMsg(rspMsg.getHead().getRspMsg());
			js.setData("");
			return js;
		}
		if(rspMsg.getBody().getRegistno()!= null && !"".equals(rspMsg.getBody().getRegistno())){			
			js.setData(rspMsg.getBody().getRegistno());
		}else{
			js.setData("");
		}	
		
		return js;
	}
	
	/**
	 * 转跳到签发仓单撤销的查询页面
	 * 
	 * @author 文闻
	 * @date 2016-4-5
	 * @param request
	 * @param model
	 * @return String
	 *
	 */
	@RequestMapping(value = "/cancel.htm")
	public String cancel(HttpServletRequest request, ModelMap model) {

		LoginRsp loginRsp = (LoginRsp) request.getSession().getAttribute("userinfo");

		// 判断登陆用户是否有T权限
		model.addAttribute("enablePay", 0);
		List<OperRight> or = loginRsp.getOperRights();
		for (OperRight operRight : or) {
			if(operRight.getRightType().equalsIgnoreCase("T")){
				model.addAttribute("enablePay", 1);
			}
		}

		logger.debug("转跳签发仓单注册撤销页面 ");
		return "warehouse/cancel";
	}
	
	/**
	 * 待审核注册仓单查询
	 * @author 文闻
	 * @data 2016-4-6
	 * @param code
	 * @param status
	 * @param pageNum
	 * @param pageSize
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/findcancel.htm")
	public @ResponseBody JSonComm  findcancel(
			@ModelAttribute RegWarehouseCancelReq apply,
			@RequestParam(value = "contTime", required = false) String contTime,
			@RequestParam(value = "econtTime", required = false) String econtTime,
			ModelMap model,HttpServletRequest request) {
		LoginRsp loginRsp = (LoginRsp)request.getSession().getAttribute("userinfo");
		//获取用户账号
		String mid = loginRsp.getmID();
		apply.setMid(mid);
		apply.setEdate(econtTime);
		apply.setSdate(contTime);
		RegWarehouseCancelRspMsg rspMsg = warehouseService.findCancelList(apply);
		JSonComm js = new JSonComm();
		if(rspMsg.getHead() == null){
			
			js.setSuccflag(-1);
			js.setMsg(rspMsg.getFault().getRspMsg());
			js.setData("");
			return js;
		}
		
		if(rspMsg.getHead().getSuccFlag() != 1){			
			js.setSuccflag(-2);
			js.setMsg(rspMsg.getHead().getRspMsg());
			js.setData("");
			return js;
		}
		if(rspMsg.getBody().getRseceipts() != null && !"".equals(rspMsg.getBody().getRseceipts())){
			List<CancelReceipts> r =rspMsg.getBody().getRseceipts();
			for (CancelReceipts receipts : r) {
				receipts.setMemname(rspMsg.getBody().getMemname());
			}
			js.setData(r);
		}else{
			js.setData("");
		}	
		
		return js;
	}
	
	
	/**
	 * 注册仓单撤销
	 * @author 文闻
	 * @data 2016-4-7
	 * @param storeno
	 * @param receiptno
	 * @param qty
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/cancelWarehouse.htm")
	public @ResponseBody JSonComm  cancelWarehouse(
			@ModelAttribute CancelWarehouseReq apply,
			ModelMap model,HttpServletRequest request) {
	
		CancelWarehouseRspMsg rspMsg = warehouseService.cancelWarehouse(apply);
		JSonComm js = new JSonComm();
		if(rspMsg.getHead() == null){
			
			js.setSuccflag(-1);
			js.setMsg(rspMsg.getFault().getRspMsg());
			js.setData("");
			return js;
		}
		
		if(rspMsg.getHead().getSuccFlag() != 1){			
			js.setSuccflag(-2);
			js.setMsg(rspMsg.getHead().getRspMsg());
			js.setData("");
			return js;
		}	
		
		return js;
	}
	
	/**
	 * 转跳到注册仓单转出页面
	 * 
	 * @author 文闻
	 * @date 2016-4-7
	 * @param request
	 * @param model
	 * @return String
	 *
	 */
	@RequestMapping(value = "/rolloutlist.htm")
	public String rolloutlist(HttpServletRequest request, ModelMap model) {

		LoginRsp loginRsp = (LoginRsp) request.getSession().getAttribute("userinfo");

		// 判断登陆用户是否有T权限
		model.addAttribute("enablePay", 0);
		List<OperRight> or = loginRsp.getOperRights();
		for (OperRight operRight : or) {
			if(operRight.getRightType().equalsIgnoreCase("T")){
				model.addAttribute("enablePay", 1);
			}
		}

		logger.debug("转跳签发仓单注册页面 ");
		return "warehouse/rolloutlist";
	}
	
	/**
	 * 待审核注册仓单查询
	 * @author 文闻
	 * @data 2016-4-6
	 * @param code
	 * @param status
	 * @param pageNum
	 * @param pageSize
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/findCdList.htm")
	public @ResponseBody JSonComm  findCdList(
			@ModelAttribute ApplyCdReq apply,
			@RequestParam(value = "contTime", required = false) String contTime,
			@RequestParam(value = "econtTime", required = false) String econtTime,
			ModelMap model,HttpServletRequest request) {
		LoginRsp loginRsp = (LoginRsp)request.getSession().getAttribute("userinfo");
		//获取用户账号
		String mid = loginRsp.getmID();
		apply.setMid(mid);
		apply.setStatus(0l);
		apply.setEdate(econtTime);
		apply.setSdate(contTime);
		ApplyCdRspMsg rspMsg = sellService.findApplyCdReq(apply);
		JSonComm js = new JSonComm();
		if(rspMsg.getHead() == null){
			
			js.setSuccflag(-1);
			js.setMsg(rspMsg.getFault().getRspMsg());
			js.setData("");
			return js;
		}
		
		if(rspMsg.getHead().getSuccFlag() != 1){			
			js.setSuccflag(-2);
			js.setMsg(rspMsg.getHead().getRspMsg());
			js.setData("");
			return js;
		}
		if(rspMsg.getBody().getrseceipts() != null && !"".equals(rspMsg.getBody().getrseceipts())){
			List<Receipts> r =rspMsg.getBody().getrseceipts();
			for (Receipts receipts : r) {
				receipts.setMemname(rspMsg.getBody().getMemname());
				receipts.setProvid(rspMsg.getBody().getProvid());
			}
			js.setData(r);
		}else{
			js.setData("");
		}	
		
		return js;
	}
	
	/**
	 * 注册仓单转出
	 * @author 文闻
	 * @data 2016-4-7
	 * @param storeno
	 * @param receiptno
	 * @param qty
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/rollout.htm")
	public @ResponseBody JSonComm  rollout(
			@ModelAttribute ROutWarehouseReq apply,
			ModelMap model,HttpServletRequest request) {
	
		ROutWarehouseRspMsg rspMsg = warehouseService.rollout(apply);
		JSonComm js = new JSonComm();
		if(rspMsg.getHead() == null){
			
			js.setSuccflag(-1);
			js.setMsg(rspMsg.getFault().getRspMsg());
			js.setData("");
			return js;
		}
		
		if(rspMsg.getHead().getSuccFlag() != 1){
			js.setSuccflag(-2);
			js.setMsg(rspMsg.getHead().getRspMsg());
			js.setData("");
			return js;
		}	
		
		return js;
	}

	/**
	 * 转跳到会员绑定页面
	 * 
	 * @param request
	 * @param model
	 * @return String
	 *
	 */
	@RequestMapping(value = "/membinding.htm")
	public String memBinding(HttpServletRequest request, ModelMap model) {
		LoginRsp loginRsp = (LoginRsp) request.getSession().getAttribute("userinfo");
		
		BindingMemberReq applyReq = new BindingMemberReq();
		applyReq.setMid(loginRsp.getmID());
		BindingMemberRspMsg rspMsg = warehouseService.findRelations(applyReq);
		if (rspMsg.getHead() == null) {
			model.addAttribute("message", rspMsg.getFault().getRspMsg());
			return "comm/fail";
		}
	
		if (rspMsg.getHead().getSuccFlag() != 1) {
			model.addAttribute("message", rspMsg.getHead().getRspMsg());
			return "comm/fail";
		}

		model.addAttribute("rspBody", rspMsg.getBody());
		logger.debug("转跳会员绑定页面 ");
		getUrlMatch(loginRsp.getTradeMenus(), model);
		return "warehouse/membinding";
	}
	
	/**
	 * 转跳到会员绑定页面
	 * 
	 * @param request
	 * @param model
	 * @return String
	 *
	 */
	@RequestMapping(value = "/trigmembinding.htm")
	public String trigMemBinding(
			@ModelAttribute TrigBindingMemberReq trigBindingMemberReq, 
			HttpServletRequest request, 
			ModelMap model) {
		
		LoginRsp loginRsp = (LoginRsp) request.getSession().getAttribute("userinfo");
		
		trigBindingMemberReq.setMid(loginRsp.getmID());
		TrigBindingMemberRspMsg rspMsg = warehouseService.trigBindingMember(trigBindingMemberReq);
		if (rspMsg.getHead() == null) {
			model.addAttribute("message", rspMsg.getFault().getRspMsg());
			return "comm/fail";
		}
	
		if (rspMsg.getHead().getSuccFlag() != 1) {
			model.addAttribute("message", rspMsg.getHead().getRspMsg());
			return "comm/fail";
		}
		
		BindingMemberReq applyReq = new BindingMemberReq();
		applyReq.setMid(loginRsp.getmID());
		BindingMemberRspMsg bindingMemberRspMsg = warehouseService.findRelations(applyReq);
		if (bindingMemberRspMsg.getHead() == null) {
			model.addAttribute("message", bindingMemberRspMsg.getFault().getRspMsg());
			return "comm/fail";
		}
	
		if (bindingMemberRspMsg.getHead().getSuccFlag() != 1) {
			model.addAttribute("message", bindingMemberRspMsg.getHead().getRspMsg());
			return "comm/fail";
		}
		
		model.addAttribute("rspBody", bindingMemberRspMsg.getBody());
		logger.debug("转跳会员绑定页面 ");
		getUrlMatch(loginRsp.getTradeMenus(), model);
		return "warehouse/membinding";
	}
}
