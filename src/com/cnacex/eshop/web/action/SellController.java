package com.cnacex.eshop.web.action;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.cnacex.comm.util.Config;
import com.cnacex.comm.util.StringUtil;
import com.cnacex.eshop.modul.JSonComm;
import com.cnacex.eshop.msg.CommRspMsg;
import com.cnacex.eshop.msg.body.comm.CommProp;
import com.cnacex.eshop.msg.body.comm.Prop;
import com.cnacex.eshop.msg.body.mall.Listed;
import com.cnacex.eshop.msg.body.mall.ListedDetailReq;
import com.cnacex.eshop.msg.body.mall.MdseElement;
import com.cnacex.eshop.msg.body.member.LoginRsp;
import com.cnacex.eshop.msg.body.member.LoginRsp.MemMarket;
import com.cnacex.eshop.msg.body.trade.sell.ApplyCdListedReq;
import com.cnacex.eshop.msg.body.trade.sell.ApplyCdListedRsp;
import com.cnacex.eshop.msg.body.trade.sell.ApplyCdReq;
import com.cnacex.eshop.msg.body.trade.sell.ApplyReq;
import com.cnacex.eshop.msg.body.trade.sell.ApplyReq.DelistMem;
import com.cnacex.eshop.msg.body.trade.sell.ApplyRsp;
import com.cnacex.eshop.msg.body.trade.sell.AuditReq;
import com.cnacex.eshop.msg.body.trade.sell.AuditRsp;
import com.cnacex.eshop.msg.body.trade.sell.BondPayReq;
import com.cnacex.eshop.msg.body.trade.sell.BondPayRsp;
import com.cnacex.eshop.msg.body.trade.sell.CancelReq;
import com.cnacex.eshop.msg.body.trade.sell.Receipts;
import com.cnacex.eshop.msg.body.trade.sell.SellBillReq;
import com.cnacex.eshop.msg.body.trade.sell.SellBillRsp;
import com.cnacex.eshop.msg.body.trade.sell.SellOrderDetailRsp;
import com.cnacex.eshop.msg.body.trade.sell.StoreReq;
import com.cnacex.eshop.msg.body.trade.sell.WRDetailReq;
import com.cnacex.eshop.msg.xml.mall.ListedDetailRspMsg;
import com.cnacex.eshop.msg.xml.trade.sell.ApplyCdListedRspMsg;
import com.cnacex.eshop.msg.xml.trade.sell.ApplyCdRspMsg;
import com.cnacex.eshop.msg.xml.trade.sell.ApplyRspMsg;
import com.cnacex.eshop.msg.xml.trade.sell.AuditCdRspMsg;
import com.cnacex.eshop.msg.xml.trade.sell.AuditRspMsg;
import com.cnacex.eshop.msg.xml.trade.sell.BondPayRspMsg;
import com.cnacex.eshop.msg.xml.trade.sell.SellBillRspMsg;
import com.cnacex.eshop.msg.xml.trade.sell.SellOrderDetailRspMsg;
import com.cnacex.eshop.msg.xml.trade.sell.StoreRspMsg;
import com.cnacex.eshop.msg.xml.trade.sell.WRDetailRspMsg;
import com.cnacex.eshop.service.ISellService;
import com.cnacex.eshop.service.imp.MallServiceImp;
import com.cnacex.eshop.util.ListedUtil;
import com.cnacex.eshop.util.StatusUtil;


/**
 * @author kereny
 * 
 */

@Controller
@RequestMapping(value="/sell")
public class SellController extends TradeController {

	static Logger logger = LoggerFactory.getLogger(SellController.class);


	@Autowired
	private ISellService sellService;

	private static final String IMAGEPATH = Config.getValue("ImagePath");

	private static final String RESOURCEIP = Config.getValue("ResoureIP");
	
	
	/**
	 * 注册仓单挂牌
	 * @author 文闻
	 * @data 2016-3-30
	 * @param apply
	 * @param unitPrice
	 * @param titfile
	 * @param ctxfile
	 * @param memdelists
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/applyListed.htm")
	public String sellApplyListed(
			@ModelAttribute ApplyCdListedReq apply,
			@RequestParam(value = "unitPrice", required = false) String unitPrice,
			@RequestParam(value = "titfile", required = false) MultipartFile titfile,
			@RequestParam(value = "ctxfile", required = false) MultipartFile[] ctxfile,
			@RequestParam(value = "memdelists", required = false) String memdelists,
			HttpServletRequest request, ModelMap model) {

		LoginRsp loginRsp = (LoginRsp)request.getSession().getAttribute("userinfo");
		apply.setMid(loginRsp.getmID());
		apply.setTxoperid(loginRsp.getOperID());
		apply.setUnitprice(Double.valueOf(unitPrice));
		logger.debug("进入挂牌处理 参数 commCode{}", apply.getCommcode());
		
		//根据仓单编号查询仓单详情
		WRDetailReq wreq = new WRDetailReq();
		wreq.setReceiptNo(apply.getWrno());
		WRDetailRspMsg wrRsp = sellService.getWRDetail(wreq);
		List<Prop> p = wrRsp.getBody().getProps();
		apply.setProps(p);
		MdseElement mdseElement = mallService.findLocalMdseEntity(apply.getCommcode());		
		if(mdseElement != null){
			apply.setMarkcode(mdseElement.getMarkCode());
			apply.setClasscode(mdseElement.getClassCode());
		}else{
			model.addAttribute("message", "商品无定位市场和分类,请确认");
			return "comm/fail";
		}
		
		logger.debug("进入挂牌处理 参数 {}", apply.toString());
		//验证权限处理
		if(!checkRight(loginRsp.getOperRights(), "T", apply.getClasscode()))
		{
			model.addAttribute("message", "您无此权限,请联系会员管理员");
			return "comm/fail";
		}
		
		if(!checkMarket(loginRsp.getMemMarkets(), apply.getMarkcode()))
		{
			model.addAttribute("message", "您无此权限,请联系会员管理员");
			return "comm/fail";
		}
		
		if(!checkTxComm(loginRsp.getTxComms(), apply.getClasscode(), "S"))
		{
			model.addAttribute("message", "您无此权限,请联系会员管理员");
			return "comm/fail";
		}
		
		if(apply.getDelist().equals("A")){
			String delists[] = memdelists.split(";");
			if(StringUtil.nullOrBlank(memdelists) || delists == null || delists.length == 0){
				model.addAttribute("message", "指定摘牌方式未选择指定摘牌会员");
				return "comm/fail";

			}

			List<com.cnacex.eshop.msg.body.trade.sell.DelistMem> mems = 
					new ArrayList<com.cnacex.eshop.msg.body.trade.sell.DelistMem>();
			
			for(int k = 0; k < delists.length; k++){
				com.cnacex.eshop.msg.body.trade.sell.DelistMem dm = 
						new com.cnacex.eshop.msg.body.trade.sell.DelistMem();
				
				dm.setDelistmid(delists[k]);
				mems.add(dm);
			}
			apply.setDelistMem(mems);
		}
		
		String path = getFilePath(loginRsp.getmID(), apply.getCommcode());

		String titlePic = titleResourceConvert(path, titfile);

		apply.setTitlepic(titlePic);
		
		List<String> ctxList = ctxResourceConvert(path, ctxfile);

		for (int i = 0; i < ctxList.size() && i < 3; i++) {
			if (i == 0)
				apply.setCtxpic1(ctxList.get(i));
			if (i == 1)
				apply.setCtxpic2(ctxList.get(i));

			if (i == 2)
				apply.setCtxpic3(ctxList.get(i));
		}
			
		apply.setMoq(0l);
		apply.setIncrnum(0l);
		ApplyCdListedRspMsg rspMsg = sellService.applyCdListedReq(apply);
		
		if (rspMsg.getHead() == null) {
			model.addAttribute("message", rspMsg.getFault().getRspMsg());
			return "comm/fail";
		}

		if (rspMsg.getHead().getSuccFlag() != 1) {
			model.addAttribute("message", rspMsg.getHead().getRspMsg());
			return "comm/fail";
		}
		//获取挂牌编号
		ApplyCdListedRsp rsp = rspMsg.getBody();
		rsp.setStatusDesc(StatusUtil.getSellStatus(rsp.getStatus().intValue()));
		model.addAttribute("listed", rsp);
		//判断权限
		if(checkRight(loginRsp.getOperRights(), "A", apply.getClasscode()))
		{
			model.addAttribute("enableAudit", 1);
		}else{
			model.addAttribute("enableAudit", 0);
		}
		model.addAttribute("listedType", apply.getListedtype());
		return "sell/sellsucc";
	}

	/**
	 * 注册仓单查询
	 * @author 文闻
	 * @data 2016-3-30
	 * @param code
	 * @param status
	 * @param pageNum
	 * @param pageSize
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/findCdList.htm")
	public @ResponseBody JSonComm  findCdList(
			@RequestParam(value = "code", required = false) String code,
			ModelMap model,HttpServletRequest request) {
		LoginRsp loginRsp = (LoginRsp)request.getSession().getAttribute("userinfo");
		//获取用户账号
		String mid = loginRsp.getmID();
		ApplyCdReq req = new ApplyCdReq();
		req.setMid(mid);
		req.setStatus(0l);
		if(!"".equals(code)){
			req.setCommcode(code);
		}
		ApplyCdRspMsg rspMsg = sellService.findApplyCdReq(req);		
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
	 * 卖方挂牌
	 * 
	 * @author kereny
	 * @date 2015-6-10 上午10:12:39
	 * @param file
	 * @param request
	 * @param model
	 * @return String
	 * 
	 */
	@RequestMapping(value = "/apply.htm")
	public String sellApply(

			@RequestParam(value = "active", required = true) String active,
			@RequestParam(value = "type", required = false) String type,
			@ModelAttribute ApplyReq apply,
			@RequestParam(value = "propdata", required = false) String[] propdata,
			@RequestParam(value = "titfile", required = false) MultipartFile titfile,
			@RequestParam(value = "ctxfile", required = false) MultipartFile[] ctxfile,
			@RequestParam(value = "memdelists", required = false) String memdelists,
			HttpServletRequest request, ModelMap model) {

		LoginRsp loginRsp = (LoginRsp)request.getSession().getAttribute("userinfo");
		 
		logger.debug("进入挂牌处理 {}", active);
		
		String sysstatus = MallServiceImp.SYS_STATUS;
		if(!sysstatus.equalsIgnoreCase("100")){
			model.addAttribute("message", "市场为非开市状态，无法进行挂牌");
			return "comm/fail";
		}
		
		if("888".equalsIgnoreCase(loginRsp.getMemLevel()))
		{
			model.addAttribute("message", "您为信息会员,不能进行挂牌");
			return "comm/fail";
		}

		// 进入挂牌页
		if ("enter".equalsIgnoreCase(active)) {
			
			List<MdseElement> marks = mallService.findAllMarkets();
			
			List<MdseElement> rs = new ArrayList<MdseElement>();
			
			for(MemMarket mark:loginRsp.getMemMarkets())
			{
				for(MdseElement me: marks)
				{
					if(me.getMdseCode().equalsIgnoreCase(mark.getMarkCode()))
					{
						rs.add(me);
						break;
					}
				}
			}
			String busDate = MallServiceImp.SYS_BUSDATE;
			model.addAttribute("busDate", busDate);
			model.addAttribute("active", "sell");
			model.addAttribute("marks", rs);
			if("1".equals(type)){
				return "sell/applycd";
			}else{
				return "sell/apply";
			}
		}

		apply.setmID(loginRsp.getmID());
		apply.setTxOperID(loginRsp.getOperID());
		
		
		logger.debug("进入挂牌处理 参数 commCode{}", apply.getCommCode());
		

		MdseElement mdseElement = mallService.findLocalMdseEntity(apply.getCommCode());		
		if(mdseElement != null){
			apply.setMarkCode(mdseElement.getMarkCode());
			apply.setClassCode(mdseElement.getClassCode());
		}else{
			model.addAttribute("message", "商品无定位市场和分类,请确认");
			return "comm/fail";
		}
		logger.debug("进入挂牌处理 参数 {}", apply.toString());
		
		if(!checkRight(loginRsp.getOperRights(), "T", apply.getClassCode()))
		{
			model.addAttribute("message", "您无此权限,请联系会员管理员");
			return "comm/fail";
		}
		
		if(!checkMarket(loginRsp.getMemMarkets(), apply.getMarkCode()))
		{
			model.addAttribute("message", "您无此权限,请联系会员管理员");
			return "comm/fail";
		}
		
		if(!checkTxComm(loginRsp.getTxComms(), apply.getClassCode(), "S"))
		{
			model.addAttribute("message", "您无此权限,请联系会员管理员");
			return "comm/fail";
		}
		
		
		if(apply.getDelist() =='A'){
			String delists[] = memdelists.split(";");
			if(StringUtil.nullOrBlank(memdelists) || delists == null || delists.length == 0){
				model.addAttribute("message", "指定摘牌方式未选择指定摘牌会员");
				return "comm/fail";

			}

			List<DelistMem> mems = new ArrayList<DelistMem>();
			
			for(int k = 0; k < delists.length; k++){
				DelistMem dm = new DelistMem();
				
				dm.setDelistMID(delists[k]);
				mems.add(dm);
			}
			apply.setDelistMems(mems);
		}

		String path = getFilePath(loginRsp.getmID(), apply.getCommCode());

		String titlePic = titleResourceConvert(path, titfile);

		apply.setTitlePic(titlePic);

		List<String> ctxList = ctxResourceConvert(path, ctxfile);

		for (int i = 0; i < ctxList.size() && i < 3; i++) {
			if (i == 0)
				apply.setCtxPic1(ctxList.get(i));
			if (i == 1)
				apply.setCtxPic2(ctxList.get(i));

			if (i == 2)
				apply.setCtxPic3(ctxList.get(i));
		}
		
		List<CommProp> cps = mallService.findCommProperty(apply.getCommCode());

		//解析属性数据
		List<Prop> props = new ArrayList<Prop>();
		for (int i = 0; propdata!= null && i < propdata.length; i++)
		{
			
			String sp[] = propdata[i].split("~");
			Prop op = new Prop();
			
			if(sp.length < 2) continue;
			if(StringUtil.nullOrBlank(sp[1])) continue;
			
			op.setPropIdx(sp[0]);
			op.setPropVal(sp[1]);
			
			props.add(op);
			
			StringBuffer strbuf = new StringBuffer("");
			
			for(CommProp cp:cps)
				if( !StringUtil.nullOrBlank(cp.getSumIdx()) && op.getPropIdx().equalsIgnoreCase(cp.getPropIdx()))
				{
					strbuf.append(cp.getPropName());
					strbuf.append(":");
					strbuf.append(op.getPropVal());
					int sumIdx  = Integer.parseInt(cp.getSumIdx());
					switch(sumIdx){
					case 1:
						apply.setSummary1(strbuf.toString());
						break;
					case 2:
						apply.setSummary2(strbuf.toString());
						break;
					case 3:
						apply.setSummary3(strbuf.toString());
						break;
					case 4:
						apply.setSummary4(strbuf.toString());
						break;
					default:
						break;					
					}
					break;
				}
		}
		apply.setProps(props);	

		ApplyRspMsg rspMsg = sellService.applySell(apply);

		if (rspMsg.getHead() == null) {
			model.addAttribute("message", rspMsg.getFault().getRspMsg());
			return "comm/fail";
		}

		if (rspMsg.getHead().getSuccFlag() != 1) {
			model.addAttribute("message", rspMsg.getHead().getRspMsg());
			return "comm/fail";
		}
		ApplyRsp rspBody = rspMsg.getBody();
		rspBody.setCostPays(supplyCostName(rspBody.getCostPays()));
		model.addAttribute("listed", rspBody);
		
		
		if(checkRight(loginRsp.getOperRights(), "A", apply.getClassCode()))
		{
			model.addAttribute("enableAudit", 1);
		}else{
			model.addAttribute("enableAudit", 0);
		}
		model.addAttribute("listedType", apply.getListedType());
		// 进入业务处理页
		return "sell/sellsucc";
	}
	
	
	/**
	 * 卖方挂牌
	 * 
	 * @author kereny
	 * @date 2015-6-10 上午10:12:39
	 * @param file
	 * @param request
	 * @param model
	 * @return String
	 * 
	 */
	@RequestMapping(value = "/edithandle.htm")
	public String editApply(
			@ModelAttribute ApplyReq apply,
			@RequestParam(value = "propdata", required = false) String[] propdata,
			@RequestParam(value = "titfile", required = false) MultipartFile titfile,
			@RequestParam(value = "ctxfile", required = false) MultipartFile[] ctxfile,
			@RequestParam(value = "ctxchg", required = false) String[] ctxchg,
			@RequestParam(value = "memdelists", required = false) String memdelists,
			HttpServletRequest request, ModelMap model) {

		LoginRsp loginRsp = (LoginRsp)request.getSession().getAttribute("userinfo");
		 
		SellOrderDetailRspMsg rspMsg = sellService.findSellDetailByID(loginRsp.getmID(),
				loginRsp.getOperID(), apply.getListedNo());
		

		if (rspMsg.getHead() == null) {
			model.addAttribute("message", rspMsg.getFault().getRspMsg());
			return "comm/fail";
		}

		if (rspMsg.getHead().getSuccFlag() != 1) {
			model.addAttribute("message", rspMsg.getHead().getRspMsg());
			return "comm/fail";
		}
		
		SellOrderDetailRsp rspDetailBody = rspMsg.getBody();
		
		//有效状态
		if(rspDetailBody.getEffRec() != 1){
			model.addAttribute("message", "挂牌单无效,无法提交修改,请查证后重试");
			return "comm/fail";
		}
		
		//待审核和审核未通过、已撤消可以修改
		if(!(rspDetailBody.getStatus() == 0 || rspDetailBody.getStatus() == - 1 || rspDetailBody.getStatus() == -2)){
			model.addAttribute("message", "挂牌单无法提交修改,该单号状态"+StatusUtil.getSellStatus(rspDetailBody.getStatus())+",请查证后重试");
			return "comm/fail";
		}

		apply.setmID(loginRsp.getmID());
		apply.setTxOperID(loginRsp.getOperID());
		MdseElement mdseElement = mallService.findLocalMdseEntity(apply.getCommCode());		
		if(mdseElement != null){
			apply.setMarkCode(mdseElement.getMarkCode());
			apply.setClassCode(mdseElement.getClassCode());
		}else{
			model.addAttribute("message", "商品无定位市场和分类,请确认");
			return "comm/fail";
		}
		logger.debug("进入挂牌处理 参数 {}", apply.toString());
		
		if(!checkRight(loginRsp.getOperRights(), "T", apply.getClassCode()))
		{
			model.addAttribute("message", "您无此权限,请联系会员管理员");
			return "comm/fail";
		}
		
		if(!checkMarket(loginRsp.getMemMarkets(), apply.getMarkCode()))
		{
			model.addAttribute("message", "您无此权限,请联系会员管理员");
			return "comm/fail";
		}
		
		if(!checkTxComm(loginRsp.getTxComms(), apply.getClassCode(), "S"))
		{
			model.addAttribute("message", "您无此权限,请联系会员管理员");
			return "comm/fail";
		}
		
		if(apply.getDelist() =='A'){
			String delists[] = memdelists.split(";");
			if(StringUtil.nullOrBlank(memdelists) || delists == null || delists.length == 0){
				model.addAttribute("message", "指定摘牌方式未选择指定摘牌会员");
				return "comm/fail";

			}

			List<DelistMem> mems = new ArrayList<DelistMem>();
			
			for(int k = 0; k < delists.length; k++){
				DelistMem dm = new DelistMem();
				
				dm.setDelistMID(delists[k]);
				mems.add(dm);
			}
			apply.setDelistMems(mems);
		}

		String path = getFilePath(loginRsp.getmID(), apply.getCommCode());

		String titlePic = titleResourceConvert(path, titfile);
		
		if(StringUtil.nullOrBlank(titlePic))
			apply.setTitlePic(rspDetailBody.getTitlePic());
		else
			apply.setTitlePic(titlePic);
		
		List<String> ctxList = ctxResourceConvert(path, ctxfile);
		
		boolean ctx1 = false, ctx2 = false, ctx3 = false;
		if(ctxList.size() > 0){
			int k = 0;
			for (int i = 0; i < ctxList.size() && i < 3; i++) {
				
				for(; k < ctxchg.length; k++){
					if(!StringUtil.nullOrBlank(ctxchg[k])){
						break;
					}
				}
				if(k < ctxchg.length){
					if("ctxPic0".equalsIgnoreCase(ctxchg[k]))
					{
						apply.setCtxPic1(ctxList.get(i));
						ctx1 = true;
					}else if("ctxPic1".equalsIgnoreCase(ctxchg[k])){
						apply.setCtxPic2(ctxList.get(i));
						ctx2 = true;
					}else if("ctxPic2".equalsIgnoreCase(ctxchg[k])){
						apply.setCtxPic3(ctxList.get(i));
						ctx3 = true;
					}
					k++;
				}
			}
		}
		
		if(ctx1 == false)
			apply.setCtxPic1(rspDetailBody.getCtxPic1());
		
		if(ctx2 == false)
			apply.setCtxPic2(rspDetailBody.getCtxPic2());
		
		if(ctx3 == false)
			apply.setCtxPic3(rspDetailBody.getCtxPic3());

		List<CommProp> cps = mallService.findCommProperty(apply.getCommCode());

		//解析属性数据
		List<Prop> props = new ArrayList<Prop>();
		for (int i = 0; propdata!= null && i < propdata.length; i++)
		{
			
			String sp[] = propdata[i].split("~");
			Prop op = new Prop();
			
			if(sp.length < 2) continue;
			if(StringUtil.nullOrBlank(sp[1])) continue;
			
			op.setPropIdx(sp[0]);
			op.setPropVal(sp[1]);
			
			props.add(op);
			StringBuffer strbuf = new StringBuffer("");
			
			for(CommProp cp:cps)
				if( !StringUtil.nullOrBlank(cp.getSumIdx()) && op.getPropIdx().equalsIgnoreCase(cp.getPropIdx()))
				{
					strbuf.append(cp.getPropName());
					strbuf.append(":");
					strbuf.append(op.getPropVal());
					int sumIdx  = Integer.parseInt(cp.getSumIdx());
					switch(sumIdx){
					case 1:
						apply.setSummary1(strbuf.toString());
						break;
					case 2:
						apply.setSummary2(strbuf.toString());
						break;
					case 3:
						apply.setSummary3(strbuf.toString());
						break;
					case 4:
						apply.setSummary4(strbuf.toString());
						break;
					default:
						break;					
					}
					break;
				}
		}
		apply.setProps(props);	
		if(String.valueOf(apply.getListedType()).equals("M")){
			ApplyRspMsg editRspMsg = sellService.editSell(apply);
			ApplyRsp rspBody = editRspMsg.getBody();
			rspBody.setCostPays(supplyCostName(rspBody.getCostPays()));
			model.addAttribute("listed", rspBody);
			if (editRspMsg.getHead() == null) {
				model.addAttribute("message", editRspMsg.getFault().getRspMsg());
				return "comm/fail";
			}

			if (editRspMsg.getHead().getSuccFlag() != 1) {
				model.addAttribute("message", editRspMsg.getHead().getRspMsg());
				return "comm/fail";
			}
		}else{
			ApplyCdListedRspMsg editRspMsg= sellService.editCdSell(apply);
			ApplyCdListedRsp rspBody = editRspMsg.getBody();
			model.addAttribute("listed", rspBody);
			if (editRspMsg.getHead() == null) {
				model.addAttribute("message", editRspMsg.getFault().getRspMsg());
				return "comm/fail";
			}

			if (editRspMsg.getHead().getSuccFlag() != 1) {
				model.addAttribute("message", editRspMsg.getHead().getRspMsg());
				return "comm/fail";
			}
		}
		
		
		
		

		
		if(checkRight(loginRsp.getOperRights(), "A", apply.getClassCode()))
		{
			model.addAttribute("enableAudit", 1);
		}else{
			model.addAttribute("enableAudit", 0);
		}
		model.addAttribute("listedType", apply.getListedType());
		// 进入业务处理页
		return "sell/sellsucc";
	}

		/**
	     *  挂牌审核前端接口
		 * @author kereny 文闻 2016年4月1日修改
		 * @date 2015-6-16 下午3:11:26
		 * @param auditReq
		 * @param request
		 * @param model
		 * @return
		 * AuditRsp
	     *
		 */
	@RequestMapping(value = "/{type}/audit.htm")
	public String sellAudit(@ModelAttribute AuditReq auditReq,
			@PathVariable("type") String type,
			HttpServletRequest request, ModelMap model) {

		LoginRsp loginRsp = (LoginRsp)request.getSession().getAttribute("userinfo");
		auditReq.setmID(loginRsp.getmID());
		auditReq.setAuOperID(loginRsp.getOperID());

		logger.debug("进入挂牌审核处理 参数 {}", auditReq.toString());

		SellOrderDetailRspMsg rspMsg = sellService.findSellDetailByID(loginRsp.getmID(),
				loginRsp.getOperID(), auditReq.getListedNo());
		if (rspMsg.getHead() == null) {
			model.addAttribute("message", rspMsg.getFault().getRspMsg());
			return "comm/fail";
		}

		if (rspMsg.getHead().getSuccFlag() != 1) {
			model.addAttribute("message", rspMsg.getHead().getRspMsg());
			return "comm/fail";
		}
		
		//验证权限处理
		if(!checkRight(loginRsp.getOperRights(), "A", rspMsg.getBody().getClassCode()))
		{
			model.addAttribute("message", "您无此操作权限,请联系会员管理员");
			return "comm/fail";
		}
		
		
		if(!checkMarket(loginRsp.getMemMarkets(), rspMsg.getBody().getMarkCode()))
		{
			model.addAttribute("message", "您无此操作权限,请联系会员管理员");
			return "comm/fail";
		}
		
		if(!checkTxComm(loginRsp.getTxComms(), rspMsg.getBody().getClassCode(), "S"))
		{
			model.addAttribute("message", "您无此操作权限,请联系会员管理员");
			return "comm/fail";
		}
		
		logger.debug("全不权限校验 ok");
		
		if(type.equals("M")){
			//提交保证金审核 
			AuditRspMsg audRspMsg = sellService.auditSell(auditReq);
			
			if (audRspMsg.getHead() == null) {
				
				model.addAttribute("message", audRspMsg.getFault().getRspMsg());
				return "comm/fail";
			}

			if (audRspMsg.getHead().getSuccFlag() != 1) {
				model.addAttribute("message", audRspMsg.getHead().getRspMsg());
				return "comm/fail";
			}
			
			AuditRsp rspBody =  audRspMsg.getBody();
			rspBody.setStatusDesc(StatusUtil.getSellStatus(rspBody.getStatus()));
			
			
			model.addAttribute("rspBody", rspBody);
			
			if(checkRight(loginRsp.getOperRights(), "P", null) && rspBody.getStatus() == 1)
			{
				model.addAttribute("enablePay", 1);
			}else{
				model.addAttribute("enablePay", 0);
			}
		}else{
			//提交注册仓单审核
			AuditCdRspMsg audCdRspMsp = sellService.auditCdSell(auditReq);
			if (audCdRspMsp.getHead() == null) {
				
				model.addAttribute("message", audCdRspMsp.getFault().getRspMsg());
				return "comm/fail";
			}

			if (audCdRspMsp.getHead().getSuccFlag() != 1) {
				model.addAttribute("message", audCdRspMsp.getHead().getRspMsg());
				return "comm/fail";
			}
			
			AuditRsp rspBody =  audCdRspMsp.getBody();
			rspBody.setStatusDesc(StatusUtil.getSellStatus(rspBody.getStatus()));
			
			model.addAttribute("rspBody", rspBody);
			
			model.addAttribute("enablePay", 0);
		}
		
		
		
		return "sell/auditsucc";

	}
	
	
		/**
	     *  保证金支付前端请求接口
		 * @author kereny
		 * @date 2015-6-16 下午3:10:56
		 * @param payReq
		 * @param request
		 * @param model
		 * @return
		 * BondPayRsp
	     *
		 */
	@RequestMapping(value = "/{type}/pay.htm")
	public String sellPayBond(@ModelAttribute BondPayReq payReq,
			@PathVariable("type") String type,
			HttpServletRequest request, ModelMap model) {

		// 商品分类
		LoginRsp loginRsp = (LoginRsp)request.getSession().getAttribute("userinfo");

		payReq.setmID(loginRsp.getmID());
		payReq.setPyOperID(loginRsp.getOperID());
		
		logger.debug("进入挂牌保证金处理 参数 {}", payReq.toString());

		SellOrderDetailRspMsg rspMsg = sellService.findSellDetailByID(loginRsp.getmID(),
				loginRsp.getOperID(), payReq.getListedNo());
		if (rspMsg.getHead() == null) {
			model.addAttribute("message",rspMsg.getFault().getRspMsg());
			return "comm/fail";
		}

		if (rspMsg.getHead().getSuccFlag() != 1) {
			model.addAttribute("message", rspMsg.getHead().getRspMsg());
			return "comm/fail";
		}
		
		//验证权限处理
		if(!checkRight(loginRsp.getOperRights(), "P", rspMsg.getBody().getClassCode()))
		{
			model.addAttribute("message", "您无此操作权限,请联系会员管理员");
			return "comm/fail";
		}
		
		if(!checkMarket(loginRsp.getMemMarkets(), rspMsg.getBody().getMarkCode()))
		{
			model.addAttribute("message", "您无此操作权限,请联系会员管理员");
			return "comm/fail";
		}
		
		if(!checkTxComm(loginRsp.getTxComms(), rspMsg.getBody().getClassCode(), "S"))
		{
			model.addAttribute("message", "您无此操作权限,请联系会员管理员");
			return "comm/fail";
		}
		
		
		//提交交易
		BondPayRspMsg payRspMsg = sellService.payBondSell(payReq);
		
		if (payRspMsg.getHead() == null) {

			model.addAttribute("message",payRspMsg.getFault().getRspMsg());
			return "comm/fail";
		}

		if (payRspMsg.getHead().getSuccFlag() != 1) {
			model.addAttribute("message", payRspMsg.getHead().getRspMsg());
			return "comm/fail";
		}
		
		BondPayRsp rspBody = payRspMsg.getBody();
		rspBody.setCostPays(supplyCostName(rspBody.getCostPays()));
		
		rspBody.setStatusDesc(StatusUtil.getSellStatus(rspBody.getStatus()));
		
		model.addAttribute("rspBody", rspBody);
		
		return "sell/paysucc";

	}
	
		/**
	     *  实现我的挂牌销售清单里面的返回内容
		 * @author kereny
		 * @date 2015-6-16 下午3:42:42
		 * @param request
		 * @param status
		 * @param start
		 * @param num
		 * @param model
		 * @return
		 * String
	     *
		 */
	@RequestMapping(value = "/list.htm")
	public String list(HttpServletRequest request, 
			@RequestParam(value = "status", required = false) String status,  
			@RequestParam(value = "pageNum", required = false, defaultValue = "0") int pageNum, 
			@RequestParam(value = "pageSize", required = false, defaultValue = "0" ) int pageSize,
			ModelMap model) {
		
		LoginRsp loginRsp = (LoginRsp)request.getSession().getAttribute("userinfo");
		
		getUrlMatch(loginRsp.getTradeMenus(), model);
		
		return "sell/list";

	}
	
	

		/**
	     *  JSON查询返回列表清单
		 * @author kereny
		 * @date 2015-7-9 下午5:52:25
		 * @param code
		 * @param status
		 * @param listedType
		 * @param dol
		 * @param doe
		 * @param pageNum
		 * @param pageSize
		 * @param model
		 * @return
		 * JSonComm
	     *
		 */
	@RequestMapping(value = "/findlist.htm")
	public @ResponseBody JSonComm  findlist(
			@RequestParam(value = "code", required = false) String code,  
			@RequestParam(value = "status", required = false) String status,  
			@RequestParam(value = "pageNum", required = false, defaultValue = "0") int pageNum, 
			@RequestParam(value = "pageSize", required = false, defaultValue = "20" ) int pageSize,
			
			
			ModelMap model) {
		
		LoginRsp loginRsp = (LoginRsp)request.getSession().getAttribute("userinfo");
		
		SellBillReq  sellReq = new SellBillReq();
		sellReq.setmID(loginRsp.getmID());
		sellReq.setOperID(loginRsp.getOperID());
		
		if(!StringUtil.nullOrBlank(status))
			sellReq.setStatus(status);
		
		sellReq.setCommCode(code);
		
		if(pageNum < 0) pageNum = 0;
		if(pageSize == 0) pageSize = PAGESIZE;
		
		//不分页,查所有
		if(pageSize > 0){
			sellReq.setReqNum(Integer.toString(pageSize));
			sellReq.setReqStart(Integer.toString(pageSize*pageNum+1));
		}
	
		logger.debug("进入挂牌清单查询 参数 {}", sellReq.toString());
		
		SellBillRspMsg rspMsg = sellService.findSellList(sellReq);
		
		JSonComm  jsdata = new JSonComm();
		
		if(rspMsg.getHead() == null){
			
			jsdata.setSuccflag(-1);
			jsdata.setMsg(rspMsg.getFault().getRspMsg());
			return jsdata;
		}
		
		if(rspMsg.getHead().getSuccFlag() != 1){
			
			jsdata.setSuccflag(-2);
			jsdata.setMsg(rspMsg.getHead().getRspMsg());
			return jsdata;
		}
		
		SellBillRsp  rsp = rspMsg.getBody();
		
		List<Listed> listeds = rsp.getListeds();
		
		if(listeds == null || listeds.size() == 0){
			jsdata.setSuccflag(-2);
			jsdata.setMsg("无数据列表");
			return jsdata;
		}

		List<Listed> rslisteds = new ArrayList<Listed>();
		for(Listed l: listeds)
		{
			MdseElement me = mallService.findLocalMdseEntity(l.getCommCode());
			if(me != null)
				l.setCommName(mallService.findLocalMdseEntity(l.getCommCode()).getMdseName());
			l.setStatusDesc(StatusUtil.getSellStatus(l.getStatus()));
			l.setEffRecDesc(ListedUtil.getEffRec(l.getEffRec()));
			l.setListedTypeName(ListedUtil.getListedName(l.getListedType()));
			rslisteds.add(l);
		}
		rspMsg.getBody().setListeds(rslisteds);
		
		jsdata.setSuccflag(0);
		jsdata.setMsg(rspMsg.getHead().getRspMsg());
		jsdata.setData(rspMsg.getBody());
		
		return jsdata;
	
	}
	
	
	@RequestMapping(value = "/findsells.htm")
	public @ResponseBody HashMap<String, Object>  findSells(
			@RequestParam(value = "code", required = false) String code,  
			@RequestParam(value = "status", required = false) String status,  
			ModelMap model) {
		
		
		HashMap<String, Object> maps = new HashMap<String, Object>();
		
		LoginRsp loginRsp = (LoginRsp)request.getSession().getAttribute("userinfo");
		
		SellBillReq  sellReq = new SellBillReq();
		sellReq.setmID(loginRsp.getmID());
		sellReq.setOperID(loginRsp.getOperID());
		
		if(!StringUtil.nullOrBlank(status))
			sellReq.setStatus(status);
		
		sellReq.setCommCode(code);
	
		logger.debug("挂牌清单查询 {}", sellReq.toString());
		
		SellBillRspMsg rspMsg = sellService.findSellList(sellReq);
		
		List<Listed> rslisteds = new ArrayList<Listed>();
		
		if(rspMsg.getHead() == null){
			
			maps.put("data", rslisteds);
			return maps;
		}
		
		if(rspMsg.getHead().getSuccFlag() != 1){
			
			maps.put("data", rslisteds);
			return maps;
		}
		
		SellBillRsp  rsp = rspMsg.getBody();
		
		List<Listed> listeds = rsp.getListeds();
		
		if(listeds == null || listeds.size() == 0){
			maps.put("data", rslisteds);
			return maps;
		}

		for(Listed l: listeds)
		{
			MdseElement me = mallService.findLocalMdseEntity(l.getCommCode());
			if(me != null)
			{
				l.setCommName(me.getMdseName());
				l.setUom(me.getUom());
				l.setClsCode(me.getPmdseCode());
				
				MdseElement clsme = mallService.findLocalMdseEntity(me.getPmdseCode());
				if(clsme != null){
					l.setClsName(clsme.getMdseName());
				}
			}
			
			if(!StringUtil.nullOrBlank(l.getSummary1()))
				l.setSummary1(l.getSummary1().substring(l.getSummary1().indexOf(":")+1));
			
			if(!StringUtil.nullOrBlank(l.getSummary2()))
				l.setSummary2(l.getSummary2().substring(l.getSummary2().indexOf(":")+1));
			
			if(!StringUtil.nullOrBlank(l.getSummary3()))
				l.setSummary3(l.getSummary3().substring(l.getSummary3().indexOf(":")+1));
			
			if(!StringUtil.nullOrBlank(l.getSummary4()))
				l.setSummary4(l.getSummary4().substring(l.getSummary4().indexOf(":")+1));
			
			String statusDesc=StatusUtil.getSellStatus(l.getStatus());//审核状态
			if(l.getStatus()==-1&&l.getAuditComment()!=null){//审核未通过
				statusDesc+=",审核意见："+l.getAuditComment();
			}
			l.setStatusDesc(statusDesc);
			l.setListedTypeName(ListedUtil.getListedName(l.getListedType()));
			l.setEffRecDesc(ListedUtil.getEffRec(l.getEffRec()));
			rslisteds.add(l);
		}
		
		maps.put("data", rslisteds);
		return maps;
	
	}
	
	
	
		/**
	     *  请求仓库列表
		 * @author kereny
		 * @date 2015-9-16 下午8:44:22
		 * @param code
		 * @param model
		 * @return
		 * JSonComm
	     *
		 */
	@RequestMapping(value = "/findstore.htm")
	public @ResponseBody JSonComm  findstore(
			@RequestParam(value = "code", required = false) String code,	
			ModelMap model) {
		
		LoginRsp loginRsp = (LoginRsp)request.getSession().getAttribute("userinfo");
		
		StoreReq  storeReq = new StoreReq();
		
		storeReq.setReqStart("1");
		storeReq.setStoreType("03");
		storeReq.setCommCode(code);
		storeReq.setStatus("1");
		storeReq.setStoreMID(loginRsp.getmID());
	
		StoreRspMsg rspMsg = sellService.findStoreList(storeReq);
		
		JSonComm  jsdata = new JSonComm();
		
		if(rspMsg.getHead() == null){
			
			jsdata.setSuccflag(-1);
			jsdata.setMsg(rspMsg.getFault().getRspMsg());
			return jsdata;
		}
		
		if(rspMsg.getHead().getSuccFlag() != 1){
			
			jsdata.setSuccflag(-2);
			jsdata.setMsg(rspMsg.getHead().getRspMsg());
			return jsdata;
		}
		
		
		jsdata.setSuccflag(0);
		jsdata.setMsg(rspMsg.getHead().getRspMsg());
		jsdata.setData(rspMsg.getBody());
		
		return jsdata;
	
	}


		/**
	     *  审核支付预处理
		 * @author kereny
		 * @date 2015-7-2 下午12:29:44
		 * @param request
		 * @param operType
		 * @param listedNo
		 * @param model
		 * @return
		 * String
	     *
		 */
	@RequestMapping(value = "/handle/{operType}/{listedNo}.htm")
	public String sellDetail(HttpServletRequest request,
			@PathVariable("operType") String operType, 
			@PathVariable("listedNo") String listedNo, 
			ModelMap model) {

		LoginRsp loginRsp = (LoginRsp)request.getSession().getAttribute("userinfo");
	
		logger.debug("进入挂牌明细单查询 参数 {} {}", listedNo, operType);
		
		SellOrderDetailRspMsg rspMsg = sellService.findSellDetailByID(loginRsp.getmID(),loginRsp.getOperID(), listedNo);
		
		if (rspMsg.getHead() == null) {
			model.addAttribute("message", rspMsg.getFault().getRspMsg());
			return "comm/fail";
		}
		
		if (rspMsg.getHead().getSuccFlag() != 1) {
			model.addAttribute("message", rspMsg.getHead().getRspMsg());
			return "comm/fail";
		}
		
		if(!StringUtil.nullOrBlank(operType)){
			if(!checkRight(loginRsp.getOperRights(), operType, rspMsg.getBody().getClassCode())){
				model.addAttribute("message", "您无操作该商品的权限请与会员管理员联系");
				return "comm/fail";
				
			}
		}
	
		SellOrderDetailRsp rspBody = rspMsg.getBody();
		
		rspBody.setMarkName(mallService.findLocalMdseEntity(rspBody.getMarkCode()).getMdseName());
		rspBody.setClassName(mallService.findLocalMdseEntity(rspBody.getClassCode()).getMdseName());
		
		
		MdseElement me = mallService.findLocalMdseEntity(rspBody.getCommCode());
		rspBody.setCommName(me.getMdseName());
		rspBody.setUom(me.getUom());
		rspBody.setStatusDesc(StatusUtil.getSellStatus(rspBody.getStatus()));
		
		rspBody.setEffRecDesc(rspBody.getEffRec()=='0'?"不生效":"生效");
		

		rspBody.setCostPays(supplyCostName(rspBody.getCostPays()));
		
		rspBody.setProps(supplyIdxName(rspBody.getCommCode(), rspBody.getProps()));
		
		rspBody.setListedTypeName(ListedUtil.getListedName(rspBody.getListedType()));
			
		model.addAttribute("operType", operType);
		
		if("A".equalsIgnoreCase(operType)){
			model.addAttribute("link", "audit");
		}else if("P".equalsIgnoreCase(operType)){
			model.addAttribute("link", "pay");
		}else{
			logger.warn("未知类型{}", operType);
			model.addAttribute("link", "nothing");
		}

		model.addAttribute("rspBody", rspBody);
		return "sell/detail";
	}
	
	
	
		/**
	     *  未审核挂牌商品修改
		 * @author kereny
		 * @date 2015-7-2 下午12:29:44
		 * @param request
		 * @param operType
		 * @param listedNo
		 * @param model
		 * @return
		 * String
	     *
		 */
		@RequestMapping(value = "/listedcancel.htm")
		public@ResponseBody JSonComm listedcancel(
				@RequestParam(value = "listedNo", required = true) String listedNo) {
		
			LoginRsp loginRsp = (LoginRsp)request.getSession().getAttribute("userinfo");
		
			logger.debug("下架 参数 {}", listedNo);
			
			ListedDetailReq listedDetailReq = new ListedDetailReq();
			
			listedDetailReq.setListedNo(listedNo);
			listedDetailReq.setmID(loginRsp.getmID());
			listedDetailReq.setOperID(loginRsp.getOperID());
			
			
			ListedDetailRspMsg rspMsg = mallService.findDetailByID(listedDetailReq);
			
			JSonComm js = new JSonComm();	
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
			
			if((rspMsg.getBody().getStatus() != 100 && rspMsg.getBody().getStatus() != 997) || !"1".equalsIgnoreCase(rspMsg.getBody().getEffRec())){
				
				js.setSuccflag(-1);
				js.setMsg("商品状态"+StatusUtil.getSellStatus(rspMsg.getBody().getStatus())+"，不允许下架");
				return js;
			}
			
			CancelReq cancelReq = new CancelReq();
			cancelReq.setmID(loginRsp.getmID());
			cancelReq.setTxOperID(loginRsp.getOperID());
			cancelReq.setListedNo(listedNo);
			ApplyRspMsg cancelRspMsg = new ApplyRspMsg();
			if(rspMsg.getBody().getListedType().equals("M")){
				
				cancelRspMsg = sellService.cancelSell(cancelReq);
			}else{
				cancelRspMsg = sellService.cancelCdSell(cancelReq);

			}
			
			if (cancelRspMsg.getHead() == null) {
				js.setSuccflag(-1);
				js.setMsg(cancelRspMsg.getFault().getRspMsg());
				return js;
			}
		
			if (cancelRspMsg.getHead().getSuccFlag() != 1) {
				js.setSuccflag(-1);
				js.setMsg(cancelRspMsg.getHead().getRspMsg());
				return js;
			}
			
			ApplyRsp rspBody = cancelRspMsg.getBody();
			
			rspBody.setStatusDesc(StatusUtil.getSellStatus(rspBody.getStatus()));
			rspBody.setListedType(rspMsg.getBody().getListedType());
			if(rspMsg.getBody().getListedType().equals("M")){
				
				rspBody.setCostPays(supplyCostName(rspBody.getCostPays()));
			}
			js.setSuccflag(0);
			js.setData(rspBody);
			
			
			return js;
		}
		
		
		
			/**
		     *  删除处理 文闻（2016年4月8日修改 添加仓单删除功能）
			 * @author kereny
			 * @date 2015-12-10 上午10:21:59
			 * @param listedNo
			 * @return
			 * JSonComm
		     *
			 */
		@RequestMapping(value = "/applydel.htm")
		public@ResponseBody JSonComm applydel(
				@RequestParam(value = "listedNo", required = true) String listedNo) {
		
			LoginRsp loginRsp = (LoginRsp)request.getSession().getAttribute("userinfo");
		
			logger.debug("撤取删除 参数 {}", listedNo);
	
			SellOrderDetailRspMsg rspMsg = sellService.findSellDetailByID(loginRsp.getmID(),loginRsp.getOperID(), listedNo);
			
			JSonComm js = new JSonComm();	
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
			
			if(rspMsg.getBody().getEffRec() != 1){
				js.setSuccflag(-1);
				js.setMsg("订单为无效订单,请确认");
				return js;
			}
			
			if(loginRsp.getOperID().equalsIgnoreCase("0000")){
				if(rspMsg.getBody().getStatus() != -2){
					js.setSuccflag(-1);
					js.setMsg("订单状态为"+StatusUtil.getSellStatus(rspMsg.getBody().getStatus())+",无法撤消");
					return js;
				}
			}else{
				if(!rspMsg.getBody().getTxOper().equalsIgnoreCase(loginRsp.getOperID())){				
					js.setSuccflag(-1);
					js.setMsg("订单无法撤消,请确认是否为该单交易员");
					return js;
				}
				if((rspMsg.getBody().getStatus() != -2 )){
					js.setSuccflag(-1);
					js.setMsg("订单状态为"+StatusUtil.getSellStatus(rspMsg.getBody().getStatus())+",无法撤消");
					return js;
				}
			}
	
			CancelReq cancelReq = new CancelReq();
			cancelReq.setmID(loginRsp.getmID());
			cancelReq.setTxOperID(loginRsp.getOperID());
			cancelReq.setListedNo(listedNo);
			cancelReq.setFlag("1");

			CommRspMsg rsRspMsg = new CommRspMsg();
			if(rspMsg.getBody().getListedType().equals("M")){
				rsRspMsg = sellService.applyCancel(cancelReq);
			}else{
				rsRspMsg = sellService.applyCancelCd(cancelReq);
			}
			if (rsRspMsg.getHead() == null) {
				js.setSuccflag(-1);
				js.setMsg(rsRspMsg.getFault().getRspMsg());
				return js;
			}
		
			if (rsRspMsg.getHead().getSuccFlag() != 1) {
				js.setSuccflag(-1);
				js.setMsg(rsRspMsg.getHead().getRspMsg());
				return js;
			}
			
			js.setSuccflag(0);
			js.setMsg("订单已删除");
			
			return js;
		}

		

			/**
		     *  未上架撤消处理
			 * @author kereny 文闻（2016年4月8日修改 添加仓单撤销功能）
			 * @date 2015-12-10 上午10:21:59
			 * @param listedNo
			 * @return
			 * JSonComm
		     *
			 */
		@RequestMapping(value = "/applycancel.htm")
		public@ResponseBody JSonComm applyCancel(
				@RequestParam(value = "listedNo", required = true) String listedNo) {
		
			LoginRsp loginRsp = (LoginRsp)request.getSession().getAttribute("userinfo");
		
			logger.debug("未上架撤消 参数 {}", listedNo);

			SellOrderDetailRspMsg rspMsg = sellService.findSellDetailByID(loginRsp.getmID(),loginRsp.getOperID(), listedNo);
			
			JSonComm js = new JSonComm();	
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
			
			if(rspMsg.getBody().getEffRec() != 1){
				js.setSuccflag(-1);
				js.setMsg("订单为无效订单,请确认");
				return js;
			}
			
			if(loginRsp.getOperID().equalsIgnoreCase("0000")){
				if((rspMsg.getBody().getStatus() != 0 && rspMsg.getBody().getStatus() != 1)){
					js.setSuccflag(-1);
					js.setMsg("订单状态为"+StatusUtil.getSellStatus(rspMsg.getBody().getStatus())+",无法撤消");
					return js;
				}
			}else{
				if(!rspMsg.getBody().getTxOper().equalsIgnoreCase(loginRsp.getOperID())){				
					js.setSuccflag(-1);
					js.setMsg("订单无法撤消,请确认是否为该单交易员");
					return js;
				}
				if((rspMsg.getBody().getStatus() != 0 )){
					js.setSuccflag(-1);
					js.setMsg("订单状态为"+StatusUtil.getSellStatus(rspMsg.getBody().getStatus())+",无法撤消");
					return js;
				}
			}

			CancelReq cancelReq = new CancelReq();
			cancelReq.setmID(loginRsp.getmID());
			cancelReq.setTxOperID(loginRsp.getOperID());
			cancelReq.setListedNo(listedNo);
			CommRspMsg rsRspMsg = new CommRspMsg();
			if(rspMsg.getBody().getListedType().equals("M")){
				rsRspMsg = sellService.applyCancel(cancelReq);
			}else{
				rsRspMsg = sellService.applyCancelCd(cancelReq);
			}
			
			if (rsRspMsg.getHead() == null) {
				js.setSuccflag(-1);
				js.setMsg(rsRspMsg.getFault().getRspMsg());
				return js;
			}
		
			if (rsRspMsg.getHead().getSuccFlag() != 1) {
				js.setSuccflag(-1);
				js.setMsg(rsRspMsg.getHead().getRspMsg());
				return js;
			}
			
			js.setSuccflag(0);
			js.setMsg("订单已撤消");
			
			return js;
		}
	
	
		
		/**
	     *  未审核挂牌商品修改
		 * @author kereny
		 * @date 2015-7-2 下午12:29:44
		 * @param request
		 * @param operType
		 * @param listedNo
		 * @param model
		 * @return
		 * String
	     *
		 */
	@RequestMapping(value = "/edit/{listedNo}.htm")
	public String listedEdit(HttpServletRequest request,
			@PathVariable("listedNo") String listedNo, 
			ModelMap model) {
	
		LoginRsp loginRsp = (LoginRsp)request.getSession().getAttribute("userinfo");
	
		logger.debug("挂牌单修改 参数 {}", listedNo);
		
		
		SellOrderDetailRspMsg rspMsg = sellService.findSellDetailByID(loginRsp.getmID(),loginRsp.getOperID(), listedNo);
		
		if (rspMsg.getHead() == null) {
			model.addAttribute("message", rspMsg.getFault().getRspMsg());
			return "comm/fail";
		}
		
		if (rspMsg.getHead().getSuccFlag() != 1) {
			model.addAttribute("message", rspMsg.getHead().getRspMsg());
			return "comm/fail";
		}
		

		if(!checkRight(loginRsp.getOperRights(), "T", rspMsg.getBody().getClassCode())){
			model.addAttribute("message", "您无操作该商品的权限请与会员管理员联系");
			return "comm/fail";	
		}

		
		SellOrderDetailRsp rspBody = rspMsg.getBody();
		
		//待审核和审核未通过,及已撤消的可以修改
		int status=rspBody.getStatus();
		if(status != 0 && status != - 1&& status != -2){
			model.addAttribute("message", "商品已经通过审核,挂牌单无法修改");
			return "comm/fail";
		}
		
		
		StoreReq  storeReq = new StoreReq();
		
		storeReq.setReqStart("1");
		storeReq.setStoreType("03");
		storeReq.setCommCode(rspBody.getCommCode());
		storeReq.setStatus("1");
		storeReq.setStoreMID(loginRsp.getmID());
	
		StoreRspMsg storeRspMsg = sellService.findStoreList(storeReq);
		
		if(storeRspMsg.getHead() == null){
			
			model.addAttribute("message", storeRspMsg.getFault().getRspMsg());
			return "comm/fail";
		}
		
		if (storeRspMsg.getHead().getSuccFlag() != 1) {
			model.addAttribute("message", storeRspMsg.getHead().getRspMsg());
			return "comm/fail";
		}
		
		if (storeRspMsg.getBody() == null || storeRspMsg.getBody().getList() == null || storeRspMsg.getBody().getList().size() == 0) {
			model.addAttribute("message", "该商品没有相关的交收仓");
			return "comm/fail";
		}
		
		rspBody.setMarkName(mallService.findLocalMdseEntity(rspBody.getMarkCode()).getMdseName());
		rspBody.setClassName(mallService.findLocalMdseEntity(rspBody.getClassCode()).getMdseName());
		
		MdseElement me = mallService.findLocalMdseEntity(rspBody.getCommCode());
		rspBody.setCommName(me.getMdseName());
		rspBody.setUom(me.getUom());
		rspBody.setStatusDesc(StatusUtil.getSellStatus(rspBody.getStatus()));
		
		rspBody.setEffRecDesc(ListedUtil.getEffRec(rspBody.getEffRec()));

		rspBody.setProps(supplyCommIdxVal(rspBody.getCommCode(), rspBody.getProps()));
		
		rspBody.setListedTypeName(ListedUtil.getListedName(rspBody.getListedType()));
			
		model.addAttribute("rspBody", rspBody);
		
		model.addAttribute("storeList", storeRspMsg.getBody().getList());
		
		String busDate = MallServiceImp.SYS_BUSDATE;
		model.addAttribute("busDate", busDate);
		
		return "sell/edit";
	}

	
	/**
	 * 生成图目录路径
	 * 
	 * @author kereny
	 * @date 2015-6-16 下午12:29:05
	 * @param mid
	 * @param commcode
	 * @return String
	 * 
	 */
	private String getFilePath(String mid, String commcode) {
        java.text.DateFormat format2 = new java.text.SimpleDateFormat("yyyyMMdd");
        String s = format2.format(new Date());
        
		StringBuffer path = new StringBuffer("");
		path.append(s);
		path.append("/");		
		path.append(mid);
		path.append("/");
		path.append(commcode);
		path.append("/");
		return path.toString();
	}

	/**
	 * 生成图片文件名
	 * 
	 * @author kereny
	 * @date 2015-6-16 下午12:29:45
	 * @param type
	 * @param extName
	 * @return String
	 * 
	 */
	private String getFileName(String type, String extName) {
		StringBuffer path = new StringBuffer(type);
		path.append(String.format("%d", System.currentTimeMillis()));
		path.append(String.format("%03d", (int) (Math.random() * 1000)));
		path.append(".");
		path.append(extName);
		return path.toString();
	}

	/**
	 * 取文件名的扩展名
	 * 
	 * @author kereny
	 * @date 2015-6-16 下午12:30:11
	 * @param fileName
	 * @return String
	 * 
	 */
	private String getExtName(String fileName) {
		String[] token = fileName.split("[.]");
		return token[token.length - 1];
	}

	/**
	 * 挂牌标题图片处理并上传
	 * 
	 * @author kereny
	 * @date 2015-6-16 下午12:30:44
	 * @param path
	 * @param titfile
	 * @return String
	 * 
	 */
	private String titleResourceConvert(String path, MultipartFile titfile) {

		if(StringUtil.nullOrBlank(titfile.getOriginalFilename()))
			return null;
		
		String fileName = getFileName("tit",
				getExtName(titfile.getOriginalFilename()));

		StringBuffer localPath = new StringBuffer("");
		localPath.append(IMAGEPATH);
		localPath.append(path);

		File targetFile = new File(localPath.toString(), fileName);
		if (!targetFile.exists()) {
			targetFile.mkdirs();
		}
		try {
			titfile.transferTo(targetFile);
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			ftpClient.connect();
			ftpClient.changeDirector(path);
			ftpClient.upFile(targetFile);
			ftpClient.closeConnect();
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		StringBuffer remotePath = new StringBuffer("");
		remotePath.append("http://");
		remotePath.append(RESOURCEIP);
		remotePath.append("/");
		remotePath.append(path);
		remotePath.append(fileName);

		logger.debug("目标文件  {} 源文件 {}", targetFile.getAbsolutePath(),
				remotePath.toString());

		return remotePath.toString();
	}

	/**
	 * 描述图片组上传
	 * 
	 * @author kereny
	 * @date 2015-6-16 下午12:31:25
	 * @param path
	 * @param ctxfile
	 * @return List<String>
	 * 
	 */
	private List<String> ctxResourceConvert(String path,
			MultipartFile ctxfile[]) {

		List<String> remotePathList = new ArrayList<String>();
		
		if(ctxfile == null || ctxfile.length == 0)
			return remotePathList;

		StringBuffer localPath = new StringBuffer("");
		localPath.append(IMAGEPATH);
		localPath.append(path);

		try {
			ftpClient.connect();
			ftpClient.changeDirector(path);
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		for (int i = 0; i < ctxfile.length; i++) {
			if (StringUtil.nullOrBlank(ctxfile[i].getOriginalFilename()))
				continue;
			String fileName = getFileName("pic",
					getExtName(ctxfile[i].getOriginalFilename()));

			File targetFile = new File(localPath.toString(), fileName);
			if (!targetFile.exists()) {
				targetFile.mkdirs();
			}
			try {
				ctxfile[i].transferTo(targetFile);
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				ftpClient.upFile(targetFile);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			StringBuffer remotePath = new StringBuffer("");
			remotePath.append("http://");
			remotePath.append(RESOURCEIP);
			remotePath.append("/");
			remotePath.append(path);
			remotePath.append(fileName);

			remotePathList.add(remotePath.toString());
		}
		try {
			ftpClient.closeConnect();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return remotePathList;
	}

}
