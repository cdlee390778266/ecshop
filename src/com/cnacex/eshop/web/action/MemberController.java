package com.cnacex.eshop.web.action;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.cnacex.comm.ftp.UPFileClient;
import com.cnacex.comm.util.Config;
import com.cnacex.comm.util.StringUtil;
import com.cnacex.eshop.modul.ClassRight;
import com.cnacex.eshop.modul.JSonComm;
import com.cnacex.eshop.modul.MarkRight;
import com.cnacex.eshop.msg.CommRspMsg;
import com.cnacex.eshop.msg.body.comm.OperRight;
import com.cnacex.eshop.msg.body.fund.BindAccQueryRsp.PayDetail;
import com.cnacex.eshop.msg.body.fund.FundPwdSetReq;
import com.cnacex.eshop.msg.body.mall.MdseElement;
import com.cnacex.eshop.msg.body.member.InfoModifyReq;
import com.cnacex.eshop.msg.body.member.InfoQueryReq;
import com.cnacex.eshop.msg.body.member.InfoQueryRsp;
import com.cnacex.eshop.msg.body.member.InvQueryReq;
import com.cnacex.eshop.msg.body.member.LoginRsp;
import com.cnacex.eshop.msg.body.member.LoginRsp.MemMarket;
import com.cnacex.eshop.msg.body.member.LoginRsp.TxComm;
import com.cnacex.eshop.msg.body.member.OperManageReq;
import com.cnacex.eshop.msg.body.member.PwdChangeReq;
import com.cnacex.eshop.msg.body.member.RightsQueryReq;
import com.cnacex.eshop.msg.xml.fund.BindAccQueryRspMsg;
import com.cnacex.eshop.msg.xml.member.InfoQueryRspMsg;
import com.cnacex.eshop.msg.xml.member.InvQueryRspMsg;
import com.cnacex.eshop.msg.xml.member.OperManageRspMsg;
import com.cnacex.eshop.msg.xml.member.RightsQueryRspMsg;
import com.cnacex.eshop.service.IFundService;
import com.cnacex.eshop.service.IMallService;
import com.cnacex.eshop.service.IMemberService;
import com.cnacex.eshop.util.ActTypeUtil;

/**
 * @author sunc
 *
 */

@Controller
@RequestMapping(value="/member")
public class MemberController {
	
	static Logger logger = LoggerFactory.getLogger(MemberController.class);
	private static final String IMAGEPATH = Config.getValue("ImagePath");
	private static final String RESOURCEIP = Config.getValue("ResoureIP");
	
	@Autowired
	private IMemberService memberService;
	
	@Autowired
	private  HttpServletRequest request;
	
	@Autowired
	protected UPFileClient ftpClient;

	
	@Autowired
	private IMallService mallService;
	
	
	@Autowired
	private IFundService fundService;

	 private static final String LOGIN_URL = "/index.htm";
	

		/**
	     *  操作员个人信息修改及设置, 性别  名称  头像
		 * @author kereny
		 * @date 2015-6-22 下午3:16:06
		 * @param active
		 * @param operSex
		 * @param operName
		 * @param photofile
		 * @param model
		 * @return
		 * String
	     *
		 */
	@RequestMapping(value="/info.htm")
	public String handleInfo(
			@RequestParam(value = "active", required = false) String active,
			@RequestParam(value = "sex", required = false) String operSex,	
			@RequestParam(value = "operName", required = false) String operName,
			@RequestParam(value = "operPhoto", required = false) MultipartFile photofile,
			Model model){  
		
		logger.debug("查询操作员资料...");
		LoginRsp loginRsp =(LoginRsp) request.getSession().getAttribute("userinfo");
		InfoQueryReq req=new InfoQueryReq();
		req.setmID(loginRsp.getmID());
		req.setOperID(loginRsp.getOperID());
		
		InfoQueryRsp rspBody = new InfoQueryRsp();
		rspBody.setMemName(loginRsp.getMemName());
		rspBody.setmID(loginRsp.getmID());
		rspBody.setOperName(loginRsp.getOperName());
		rspBody.setOperSex(loginRsp.getOperSex());
		rspBody.setOperPhoto(loginRsp.getOperPhoto());
		rspBody.setOperID(loginRsp.getOperID());
		
		if(active!=null&&active.equalsIgnoreCase("save"))
		{
			
			InfoModifyReq infoModifyReq = new InfoModifyReq();
			infoModifyReq.setmID(loginRsp.getmID());
			if(StringUtil.nullOrBlank(operSex))
				infoModifyReq.setOperSex(loginRsp.getOperSex());
			else
				infoModifyReq.setOperSex(operSex.charAt(0));
			infoModifyReq.setOperName(operName);
			
			infoModifyReq.setOperID(loginRsp.getOperID());
			//对头像图片的处理
			String path=getFilePath(loginRsp.getmID());
			String operPhotoPath = photoResourceConvert(path, photofile);
			
			if(StringUtil.nullOrBlank(operPhotoPath))
				infoModifyReq.setOperPhoto(loginRsp.getOperPhoto());
			else
				infoModifyReq.setOperPhoto(operPhotoPath);
			
			CommRspMsg rspMsg=memberService.modifyOperInfo(infoModifyReq);
			if(rspMsg.getHead() != null){
				if(rspMsg.getHead().getSuccFlag()!= 1){
					if(!StringUtil.nullOrBlank(rspMsg.getHead().getRspCode())){
						model.addAttribute("message", rspMsg.getFault().getRspMsg());
					}
					else{
						model.addAttribute("message", rspMsg.getHead().getRspMsg());
					}
				}else{
					loginRsp.setOperName(infoModifyReq.getOperName());
					loginRsp.setOperSex(infoModifyReq.getOperSex());
					loginRsp.setOperPhoto(infoModifyReq.getOperPhoto());					
					request.getSession().removeAttribute("userinfo");					 
					request.getSession().setAttribute("userinfo", loginRsp);					 
					rspBody.setOperName(infoModifyReq.getOperName());					 
					rspBody.setOperSex(infoModifyReq.getOperSex());					 
					rspBody.setOperPhoto(infoModifyReq.getOperPhoto());			
					model.addAttribute("message", "修改成功");
				}
			}else{
				model.addAttribute("message", rspMsg.getFault().getRspMsg());
			}
		}
		model.addAttribute("user", rspBody);
		
		return "member/info";
		
    }
	

		/**
	     *  进入安全设置页面
		 * @author kereny
		 * @date 2015-6-22 上午11:02:59
		 * @param model
		 * @return
		 * String
	     *
		 */
	@RequestMapping(value="/home.htm")
	public String home(Model model){  
		return "member/home";
    }
	
	/**
	 * 进入账户页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/account.htm")
	public String account(Model model){  
		return "member/account";
    }

	@RequestMapping(value="/setpwd.htm")
	public @ResponseBody JSonComm passwordSet(
			@RequestParam(value = "oldpassword", required = false) String oldPwd,	
			@RequestParam(value = "newpassword", required = false) String newPwd,
			Model model){
				
		logger.debug("操作员登录密码修改...");
		LoginRsp user=(LoginRsp) request.getSession().getAttribute("userinfo");
		
		//封装请求报文
		PwdChangeReq pwdChangeReq=new PwdChangeReq();
		pwdChangeReq.setmID(user.getmID());
		pwdChangeReq.setOperID(user.getOperID());
		
		//对密码进行简单的验证
		
		JSonComm js = new JSonComm();
		
		
		if(!StringUtil.nullOrBlank(oldPwd)){
			pwdChangeReq.setOldPwd(oldPwd);
		}else{
			js.setSuccflag(-1);
			js.setMsg("旧密码为空");
			return js;
		}
		
		if(!StringUtil.nullOrBlank(newPwd)){
			pwdChangeReq.setNewPwd(newPwd);
		}else{
			js.setSuccflag(-1);
			js.setMsg("新密码为空");
			return js;
		}
		
		//调用service，获取应答信息
		CommRspMsg rspMsg=memberService.changeOperPwd(pwdChangeReq);
		
		if(rspMsg.getHead().getSuccFlag() == 1){
			
			js.setSuccflag(0);
			js.setMsg("修改成功");
			return js;
		}
		
		if(StringUtil.nullOrBlank(rspMsg.getHead().getRspCode())){
			js.setSuccflag(-1);
			js.setMsg(rspMsg.getFault().getRspMsg());
		}else{
			js.setSuccflag(-1);
			js.setMsg(rspMsg.getHead().getRspMsg());
		}
		return js;
	}
	
	
	@RequestMapping(value="/fundsetpwd.htm")
	public @ResponseBody JSonComm fundPasswordSet(
			@RequestParam(value = "oldpassword", required = false) String oldPwd,	
			@RequestParam(value = "newpassword", required = false) String newPwd,
			Model model){
				
		logger.debug("操作员资金密码修改...");
		LoginRsp user=(LoginRsp) request.getSession().getAttribute("userinfo");
		
		//封装请求报文
		FundPwdSetReq fundPwdSetReq=new FundPwdSetReq();
		fundPwdSetReq.setmID(user.getmID());
		fundPwdSetReq.setOperID(user.getOperID());
		
		//对密码进行简单的验证
		
		JSonComm js = new JSonComm();
		
		
		if(!StringUtil.nullOrBlank(oldPwd)){
			fundPwdSetReq.setOldFundPwd(oldPwd);
		}else{
			js.setSuccflag(-1);
			js.setMsg("旧密码为空");
			return js;
		}
		
		if(!StringUtil.nullOrBlank(newPwd)){
			fundPwdSetReq.setNewFundPwd(newPwd);
		}else{
			js.setSuccflag(-1);
			js.setMsg("新密码为空");
			return js;
		}
		
		//调用service，获取应答信息
		CommRspMsg rspMsg=fundService.fundPasswordSet(fundPwdSetReq);
		
		if(rspMsg.getHead().getSuccFlag() == 1){
			
			js.setSuccflag(0);
			js.setMsg("修改成功");
			return js;
		}
		
		if(StringUtil.nullOrBlank(rspMsg.getHead().getRspCode())){
			js.setSuccflag(-1);
			js.setMsg(rspMsg.getFault().getRspMsg());
		}else{
			js.setSuccflag(-1);
			js.setMsg(rspMsg.getHead().getRspMsg());
		}
		return js;
	}
	
	
		/**
	     *  安全退出
		 * @author kereny
		 * @date 2015-6-22 下午1:09:10
		 * @param model
		 * @param response
		 * @return
		 * String
	     *
		 */
	@RequestMapping(value="/logout.htm")
	public String LogOut(Model model, HttpServletResponse response){  
		
		request.getSession().removeAttribute("userinfo");
		
		try {
			response.sendRedirect(LOGIN_URL);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "../index";
    }
	
	
	

	@RequestMapping(value="/right.htm")
	public String  queryRight(Model model){
		
		logger.debug("操作员权限查询...");
		
		LoginRsp user=(LoginRsp) request.getSession().getAttribute("userinfo");
		RightsQueryReq req=new RightsQueryReq();
		req.setmID(user.getmID());
		req.setOperID(user.getOperID());
		
		//调用service，获取应答信息
		RightsQueryRspMsg rspMsg=memberService.queryOperRights(req);
		
		
		if(rspMsg.getHead() != null){
			if(rspMsg.getHead() != null){
				if(rspMsg.getHead().getSuccFlag()!= 1){
					if(!StringUtil.nullOrBlank(rspMsg.getHead().getRspCode())){
						model.addAttribute("message", rspMsg.getFault().getRspMsg());
					}
					else{
						model.addAttribute("message", rspMsg.getHead().getRspMsg());
					}
				}else{
					List<OperRight> rights = rspMsg.getBody().getOperRights();
					
					List<OperRight> trights = new ArrayList<OperRight>();
					
					List<OperRight> arights = new ArrayList<OperRight>();
					
					boolean havePright = false;
					boolean haveMright = false;
					
					if(rights != null){
					
						for(OperRight r:rights){
							if(r.getRightType().charAt(0) =='P'){
								havePright = true;
							}else if(r.getRightType().charAt(0) =='M'){
								haveMright = true;
							}else{	
								MdseElement me = mallService.findLocalMdseEntity(r.getClassCode());
								r.setClsName(me.getMdseName());
								
								if(r.getRightType().charAt(0) =='T')
									trights.add(r);
								else if(r.getRightType().charAt(0) =='A')
									arights.add(r);
							}					
						}
					
						model.addAttribute("txRights", trights);
						
						model.addAttribute("auRights", arights);
						
						model.addAttribute("PRight", havePright);
						
						model.addAttribute("MRight", haveMright);
					}
					
				}
			}
		}else{
			model.addAttribute("message", rspMsg.getFault().getRspMsg());
		}
		return "member/right";
	}
	
	
	
		/**
	     *  账户绑定信息查看
		 * @author kereny
		 * @date 2015-6-22 下午5:04:51
		 * @param model
		 * @return
		 * String
	     *
		 */
	@RequestMapping(value="/pay.htm")
	public String payInfo(Model model){  
		
		
		LoginRsp loginRsp =(LoginRsp) request.getSession().getAttribute("userinfo");
		
		BindAccQueryRspMsg rspMsg = fundService.queryBindAccInfo(loginRsp.getmID());
		
		if(rspMsg.getHead()!= null && rspMsg.getHead().getSuccFlag() == 1 
				&& rspMsg.getBody().getPayDetails() != null ){
			
			logger.debug("Acc List Size {}", rspMsg.getBody().getPayDetails().size());
			
			List<PayDetail> details = rspMsg.getBody().getPayDetails();
			if(details != null && details.size() > 0){
				List<PayDetail> rsdetails = new ArrayList<PayDetail>();
				for(PayDetail p : details)
				{
					
					p.setPbATypeName(ActTypeUtil.getValue(p.getPbAType()));
					
					p.setStatusDesc(p.getStatus().equalsIgnoreCase("1")?"已绑定":"未绑定");
					
					rsdetails.add(p);
				}
						
				model.addAttribute("accList", rsdetails);
			}
			
			
			
		}
		return "member/pay";
    }
	
	
	
	
		/**
	     *  操作员管理主界面
		 * @author kereny
		 * @date 2015-6-22 下午5:05:17
		 * @param active
		 * @param model
		 * @return
		 * String
	     *
		 */
	@RequestMapping(value="/manager.htm")
	public String manager(
			@RequestParam(value = "active", required = false) String active,			
			Model model){  
		
		LoginRsp user = (LoginRsp)request.getSession().getAttribute("userinfo");
		
		InvQueryReq invQueryReq=new InvQueryReq();
		invQueryReq.setmID(user.getmID());
		invQueryReq.setAdminID(user.getOperID());
		
		InvQueryRspMsg rspMsg=memberService.queryOperInv(invQueryReq);
		
		if(rspMsg.getHead() != null){
			
			if(rspMsg.getHead().getSuccFlag() != 1){
				if(StringUtil.nullOrBlank(rspMsg.getHead().getRspCode()))
				{
					model.addAttribute("message", rspMsg.getFault().getRspMsg());
				}else{
					model.addAttribute("message", rspMsg.getHead().getRspMsg());
				}
			}else{
				model.addAttribute("operlist", rspMsg.getBody().getOperators());
			}
		}else{
			model.addAttribute("message", rspMsg.getFault().getRspMsg());
		}
		return "member/manager";
    }
	
	
		/**
	     *  查询权限
		 * @author kereny
		 * @date 2015-6-22 下午5:05:17
		 * @param operid
		 * @param model
		 * @return
		 * JSonComm
	     *
		 */
	
	@RequestMapping(value="/findoper.htm")
	public @ResponseBody JSonComm findOper(
			@RequestParam(value = "operid", required = false) String operid,	
			Model model){
		
		
		LoginRsp loginRsp = (LoginRsp)request.getSession().getAttribute("userinfo");
		
		HashMap<String , Object> map = new HashMap<String , Object>();  
		
		List<OperRight> operRights = null;
		InfoQueryRsp operInfo = null;
		
		if(!StringUtil.nullOrBlank(operid)){
			RightsQueryReq req=new RightsQueryReq();
			req.setmID(loginRsp.getmID());
			req.setOperID(operid);
			
			//调用service，获取应答信息
			RightsQueryRspMsg rightRspMsg=memberService.queryOperRights(req);
			
			if(rightRspMsg.getHead().getSuccFlag() == 1){
				operRights = rightRspMsg.getBody().getOperRights();
			}
			
			InfoQueryReq infoQueryReq = new InfoQueryReq();
			
			infoQueryReq.setmID(loginRsp.getmID());
			infoQueryReq.setOperID(operid);
			
			InfoQueryRspMsg infoRspMsg = memberService.queryOperInfo(infoQueryReq);
			
			if(infoRspMsg.getHead().getSuccFlag() == 1){
				operInfo = infoRspMsg.getBody();
				map.put("info", operInfo);
			}
		}
		
		List<TxComm> txCls = loginRsp.getTxComms();
		
		List<MdseElement> marks = mallService.findAllMarkets();
		List<MdseElement> amks = new ArrayList<MdseElement>();
		//过滤处理可交易市场
		List<MemMarket> avMarks = loginRsp.getMemMarkets();
			for(MdseElement me: marks)
				for(MemMarket mm: avMarks)
					if(me.getMdseCode().equalsIgnoreCase(mm.getMarkCode()))
						amks.add(me);
		
		List<MarkRight> markRights = new ArrayList<MarkRight>();
						
		boolean bHasPayRight = false;
		boolean bhasMgrRight = false;
		for(MdseElement m:amks){
			List<MdseElement> markclass = mallService.findChildList(m.getMdseCode());
			
			
			MarkRight mark = new MarkRight();
			
			mark.setCode(m.getMdseCode());
			mark.setName(m.getMdseName());
			
			List<ClassRight> txrights = new ArrayList<ClassRight>();
			List<ClassRight> adrights = new ArrayList<ClassRight>();
			
			//取这个市场下面的所有分类
			List<TxComm> txMrkCls = new ArrayList<TxComm>();
			for(TxComm t: txCls){
				for(MdseElement sb: markclass){
					if(sb.getMdseCode().equalsIgnoreCase(t.getClassCode())){
						txMrkCls.add(t);
						break;
					}
				}
			}
			
			//过滤一次买卖重复的
			List<TxComm> txUseCls = new ArrayList<TxComm>();
			for(TxComm tx:txMrkCls){
				boolean bFlag = false;
				for(TxComm tux: txUseCls)
					if(tx.getClassCode().equalsIgnoreCase(tux.getClassCode())){
						bFlag = true;
						break;
					}
				if(bFlag == false){
					txUseCls.add(tx);
				}
			}
			
			logger.debug("txUseCls {}", txUseCls.size());
			
			
			for(TxComm tx: txUseCls){
				ClassRight txright = new ClassRight();
				ClassRight adright = new ClassRight();
					
				MdseElement me  = mallService.findLocalMdseEntity(tx.getClassCode());
				
				txright.setCode(tx.getClassCode());	
				txright.setName(me.getMdseName());
				
				adright.setCode(tx.getClassCode());	
				adright.setName(me.getMdseName());

				if(operRights != null){
					for(OperRight or:operRights){
						
						logger.debug("OperRight = {} txClass={}", or.toString(), tx.getClassCode());
						if(or.getRightType().equalsIgnoreCase("P"))
							bHasPayRight = true;
						else if(or.getRightType().equalsIgnoreCase("M"))
							bhasMgrRight = true;
						else if(or.getClassCode().equalsIgnoreCase(tx.getClassCode())){
							
							if(or.getRightType().equalsIgnoreCase("T"))
								txright.setHas(true);
							else if(or.getRightType().equalsIgnoreCase("A"))
								adright.setHas(true);
						}
					}
				}
				txrights.add(txright);
				adrights.add(adright);	
			}
			
			mark.setAdRights(adrights);
			mark.setTxRights(txrights);
			
			markRights.add(mark);
		}
		
		map.put("right", markRights);
		map.put("supportMgr", (loginRsp.getMgrID()=='2'||loginRsp.getMgrID()=='1')?true:false);
		
		map.put("hasPayRight", bHasPayRight);	
		map.put("hasMgrRight", bhasMgrRight);	
		
		JSonComm js = new JSonComm();
	
		js.setSuccflag(0);
		js.setMsg("success");
		js.setData(map);
		
		return js;
    }
	

	
		/**
	     *  创建操作员
		 * @author kereny
		 * @date 2015-7-1 下午6:15:24
		 * @param operName
		 * @param operSex
		 * @param photofile
		 * @param txrights
		 * @param adrights
		 * @param payright
		 * @param mgrright
		 * @param defaultpwd
		 * @param model
		 * @return
		 * JSonComm
	     *
		 */
	@RequestMapping(value="/createoper.htm")
	public @ResponseBody JSonComm createOper(				
				@RequestParam(value = "operName", required = true) String operName,
				@RequestParam(value = "operSex", required = true) String operSex,
				@RequestParam(value = "operPhoto", required = false) MultipartFile photofile,
				
				@RequestParam(value = "txrights", required = false) String[] txrights,
				@RequestParam(value = "adrights", required = false) String[] adrights,
				
				@RequestParam(value = "payright", required = false) String payright,
				@RequestParam(value = "mgrright", required = false) String mgrright,
				@RequestParam(value = "defaultpwd", required = true) String defaultpwd,
			Model model){
		
		LoginRsp loginRsp = (LoginRsp)request.getSession().getAttribute("userinfo");
		OperManageReq operManageReq = new OperManageReq();
		
		operManageReq.setSetTpye('I');
		operManageReq.setmID(loginRsp.getmID());
		operManageReq.setAdminID(loginRsp.getOperID());
		operManageReq.setOperName(operName);
		
		operManageReq.setOperSex(operSex.charAt(0));
		String path = getFilePath(loginRsp.getmID());
		String titlePic = photoResourceConvert(path, photofile);
		if(!StringUtil.nullOrBlank(titlePic))
			operManageReq.setOperPhoto(titlePic);
		else
			operManageReq.setOperPhoto("");
		
		List<OperRight> operRights = new ArrayList<OperRight>();
		OperRight right = null;
		if(txrights != null && txrights.length > 0){
			for(int i = 0; i< txrights.length; i++){
				if(!StringUtil.nullOrBlank(txrights[i])){
					right = new OperRight(); 
					right.setRightType("T");
					right.setClassCode(txrights[i]);
					operRights.add(right);
				}
			}
		}
		
		if(adrights != null && adrights.length > 0){
			for(int i = 0; i< adrights.length; i++){
				if(!StringUtil.nullOrBlank(adrights[i])){
					right = new OperRight(); 
					right.setRightType("A");
					right.setClassCode(adrights[i]);
					operRights.add(right);
				}
			}
		}
		if("1".equalsIgnoreCase(payright)){
			right = new OperRight(); 
			right.setRightType("P");
			operRights.add(right);
		}
		
		if("1".equalsIgnoreCase(mgrright)){
			right = new OperRight(); 
			right.setRightType("M");
			operRights.add(right);
		}
		operManageReq.setOperRights(operRights);
		operManageReq.setResetPwd(defaultpwd);
		
		OperManageRspMsg rspMsg = memberService.handleOperManage(operManageReq);
		
		JSonComm js = new JSonComm();
		if(rspMsg.getHead() == null){
			js.setSuccflag(-1);
			js.setMsg(rspMsg.getFault().getRspMsg());
			return js;
		}
		
		if(rspMsg.getHead().getSuccFlag() != 1){
			js.setSuccflag(-1);
			js.setMsg(rspMsg.getHead().getRspMsg());
			return js;
		}
		
		js.setSuccflag(0);
		js.setMsg("sucess");
		js.setData(rspMsg.getBody().getOperID());
		
		return js;
	}
	
	
		/**
	     *  修改操作员
		 * @author kereny
		 * @date 2015-7-1 下午6:15:47
		 * @param operId
		 * @param operName
		 * @param operSex
		 * @param photofile
		 * @param txrights
		 * @param adrights
		 * @param payright
		 * @param mgrright
		 * @param resetpwd
		 * @param model
		 * @return
		 * JSonComm
	     *
		 */
	@RequestMapping(value="/editoper.htm")
	public @ResponseBody JSonComm editOper(		
				@RequestParam(value = "operId", required = true) String operId,
				@RequestParam(value = "operName", required = true) String operName,
				@RequestParam(value = "operSex", required = true) String operSex,
				@RequestParam(value = "operPhoto", required = false) MultipartFile photofile,
				
				@RequestParam(value = "txrights", required = false) String[] txrights,
				@RequestParam(value = "adrights", required = false) String[] adrights,
				
				@RequestParam(value = "payright", required = false) String payright,
				@RequestParam(value = "mgrright", required = false) String mgrright,
				@RequestParam(value = "resetpwd", required = false) String resetpwd,
			Model model){
		
		LoginRsp loginRsp = (LoginRsp)request.getSession().getAttribute("userinfo");
		OperManageReq operManageReq = new OperManageReq();
		
		operManageReq.setSetTpye('U');
		operManageReq.setmID(loginRsp.getmID());
		operManageReq.setAdminID(loginRsp.getOperID());
		operManageReq.setOperID(operId);
		operManageReq.setOperName(operName);
		
		operManageReq.setOperSex(operSex.charAt(0));
		String path = getFilePath(loginRsp.getmID());
		String titlePic = photoResourceConvert(path, photofile);
		if(!StringUtil.nullOrBlank(titlePic))
			operManageReq.setOperPhoto(titlePic);
		else{
			InfoQueryReq infoQueryReq = new InfoQueryReq();
			
			infoQueryReq.setmID(loginRsp.getmID());
			infoQueryReq.setOperID(operId);
			
			InfoQueryRspMsg infoRspMsg = memberService.queryOperInfo(infoQueryReq);
			
			if(infoRspMsg.getHead() != null && infoRspMsg.getHead().getSuccFlag() == 1){
				operManageReq.setOperPhoto(infoRspMsg.getBody().getOperPhoto());
			}
		}
			
		List<OperRight> operRights = new ArrayList<OperRight>();
		OperRight right = null;
		if(txrights != null && txrights.length > 0){
			for(int i = 0; i< txrights.length; i++){
				if(!StringUtil.nullOrBlank(txrights[i])){
					right = new OperRight(); 
					right.setRightType("T");
					right.setClassCode(txrights[i]);
					operRights.add(right);
				}
			}
		}
		
		if(adrights != null && adrights.length > 0){
			for(int i = 0; i< adrights.length; i++){
				if(!StringUtil.nullOrBlank(adrights[i])){
					right = new OperRight(); 
					right.setRightType("A");
					right.setClassCode(adrights[i]);
					operRights.add(right);
				}
			}
		}
		if("1".equalsIgnoreCase(payright)){
			right = new OperRight(); 
			right.setRightType("P");
			operRights.add(right);
		}
		
		if("1".equalsIgnoreCase(mgrright)){
			right = new OperRight(); 
			right.setRightType("M");
			operRights.add(right);
		}
		operManageReq.setOperRights(operRights);
		if(!StringUtil.nullOrBlank(resetpwd))
			operManageReq.setResetPwd(resetpwd);
		
		OperManageRspMsg rspMsg = memberService.handleOperManage(operManageReq);
		
		JSonComm js = new JSonComm();
		if(rspMsg.getHead() == null){
			js.setSuccflag(-1);
			js.setMsg(rspMsg.getFault().getRspMsg());
			return js;
		}
		
		if(rspMsg.getHead().getSuccFlag() != 1){
			js.setSuccflag(-1);
			js.setMsg(rspMsg.getHead().getRspMsg());
			return js;
		}
		
		js.setSuccflag(0);
		js.setMsg("sucess");

		return js;
	}
	
	
	
		/**
	     *  删除操作员
		 * @author kereny
		 * @date 2015-7-1 下午6:16:20
		 * @param operId
		 * @param model
		 * @return
		 * JSonComm
	     *
		 */
	@RequestMapping(value="/deleteoper.htm")
	public @ResponseBody JSonComm deleteOper(		
				@RequestParam(value = "operId", required = true) String operId,
			Model model){
		
		LoginRsp loginRsp = (LoginRsp)request.getSession().getAttribute("userinfo");
		OperManageReq operManageReq = new OperManageReq();
		
		operManageReq.setSetTpye('D');
		operManageReq.setmID(loginRsp.getmID());
		operManageReq.setAdminID(loginRsp.getOperID());
		operManageReq.setOperID(operId);
		
		OperManageRspMsg rspMsg = memberService.handleOperManage(operManageReq);
		
		JSonComm js = new JSonComm();
		if(rspMsg.getHead() == null){
			js.setSuccflag(-1);
			js.setMsg(rspMsg.getFault().getRspMsg());
			return js;
		}
		
		if(rspMsg.getHead().getSuccFlag() != 1){
			js.setSuccflag(-1);
			js.setMsg(rspMsg.getHead().getRspMsg());
			return js;
		}
		
		js.setSuccflag(0);
		js.setMsg("sucess");

		return js;
	}
		
	
		/**
	     *  删除操作员
		 * @author kereny
		 * @date 2015-7-1 下午6:16:20
		 * @param operId
		 * @param model
		 * @return
		 * JSonComm
	     *
		 */
	@RequestMapping(value="/activeoper.htm")
	public @ResponseBody JSonComm activeOper(		
				@RequestParam(value = "operId", required = true) String operId,
			Model model){
		
		LoginRsp loginRsp = (LoginRsp)request.getSession().getAttribute("userinfo");
		OperManageReq operManageReq = new OperManageReq();
		
		operManageReq.setSetTpye('R');
		operManageReq.setmID(loginRsp.getmID());
		operManageReq.setAdminID(loginRsp.getOperID());
		operManageReq.setOperID(operId);
		
		OperManageRspMsg rspMsg = memberService.handleOperManage(operManageReq);
		
		JSonComm js = new JSonComm();
		if(rspMsg.getHead() == null){
			js.setSuccflag(-1);
			js.setMsg(rspMsg.getFault().getRspMsg());
			return js;
		}
		
		if(rspMsg.getHead().getSuccFlag() != 1){
			js.setSuccflag(-1);
			js.setMsg(rspMsg.getHead().getRspMsg());
			return js;
		}
		
		js.setSuccflag(0);
		js.setMsg("sucess");
	
		return js;
	}
	
	
	/**
	 * 生成图目录路径
	 */
	private String getFilePath(String mid) {
		StringBuffer path = new StringBuffer("");
		path.append("member/");
		path.append(mid);
		path.append("/");
		return path.toString();
	}

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
	 */
	private String getExtName(String fileName) {
		String[] token = fileName.split("[.]");
		return token[token.length - 1];
	}

	/**
	 * 头像图片处理和上传
	 */
	private String photoResourceConvert(String path, MultipartFile operPhoto) {

		if(StringUtil.nullOrBlank(operPhoto.getOriginalFilename()))
			return null;
		
		String fileName = getFileName("oper",
				getExtName(operPhoto.getOriginalFilename()));

		StringBuffer localPath = new StringBuffer("");
		localPath.append(IMAGEPATH);
		localPath.append(path);

		File targetFile = new File(localPath.toString(), fileName);
		if (!targetFile.exists()) {
			targetFile.mkdirs();
		}
		try {
			operPhoto.transferTo(targetFile);
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

}
