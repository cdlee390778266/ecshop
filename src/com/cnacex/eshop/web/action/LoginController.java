package com.cnacex.eshop.web.action;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cnacex.comm.util.Config;
import com.cnacex.comm.util.StringUtil;
import com.cnacex.eshop.modul.MenuNode;
import com.cnacex.eshop.msg.body.mall.MdseElement;
import com.cnacex.eshop.msg.body.member.LoginReq;
import com.cnacex.eshop.msg.body.member.LoginRsp;
import com.cnacex.eshop.msg.body.member.LoginRsp.TxComm;
import com.cnacex.eshop.msg.xml.member.LoginRspMsg;
import com.cnacex.eshop.service.IMemberService;


/**
 * @author kereny
 *
 */

@Controller
public class LoginController extends TradeController{
	
	static Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	private IMemberService memberService;
	

		/**
	     *  主页面跳转处理
	     *  
		 * @author kereny
		 * @date 2015-6-5 下午4:30:22
		 * @return
		 * String
	     *
		 */
	@RequestMapping(value="/")
	public String defaultpage()
    {  
		
		request.getSession().removeAttribute("userinfo");
		logger.debug("进入主页面");
		
		request.getSession().setAttribute("buildno", Config.getValue("buildNo"));
		
		return "index";
    }
	
	@RequestMapping(value="index.htm")
	public String index()
    {  
		request.getSession().removeAttribute("userinfo");
		
		request.getSession().setAttribute("buildno", Config.getValue("buildNo"));
		
		logger.debug("进入主页面");
		return "index";
    }

		/**
	     *  会员登录处理
		 * @author kereny
		 * @date 2015-6-4 下午1:17:18
		 * @param username
		 * @param password
		 * @param mac
		 * @param model
		 * @return
		 * String
	     *
		 */
	@RequestMapping(value="login.htm",method=RequestMethod.POST)
	public String login(
			@RequestParam(value = "mid") String  mid,
            @RequestParam(value = "operid") String  operid,
            @RequestParam(value = "operpwd") String  operpwd,
            @RequestParam(value = "mac") String mac, Model model)
     {  
		
		String hostIp = request.getRemoteAddr();
		logger.info("用户登录, IP:{} 会员ID:{} 操作员ID:{} 密码{} 验证码:{}", hostIp, mid, operid, operpwd, mac);
		
		if(StringUtil.nullOrBlank(mid)){
			model.addAttribute("message", "会员ID未输入");
			return "index";
		}
		
		model.addAttribute("mid", mid);
		
		if(StringUtil.nullOrBlank(operid)){
			model.addAttribute("message", "会员操作员ID未输入");
			return "index";
		}
		
		
		model.addAttribute("operid", operid);
		if(StringUtil.nullOrBlank(operpwd)){
			model.addAttribute("message", "会员操作员密码未输入");
			return "index";
		}
		
		if(StringUtil.nullOrBlank(mac)){
			model.addAttribute("message", "验证码未输入");
			return "index";
		}
		
		
		Object obj  = request.getSession().getAttribute("authCode");
		if(obj == null)
		{
			model.addAttribute("message",  "验证码失效,请重新登录");
			return "index";
		}

		if(!obj.toString().equalsIgnoreCase(mac)){
			model.addAttribute("message",  "验证码错误");
			return "index";
		}
		
		LoginReq login = new LoginReq();
		
		login.setmID(mid);
		login.setOperID(operid);
		login.setOperPwd(operpwd);
		
		LoginRspMsg loginRsp = memberService.login(login);
		if(loginRsp == null){
			model.addAttribute("message",  "登录异常,请重试");
			return "index";
		}
		
		if(loginRsp.getHead() == null)
		{
			if(loginRsp.getFault() != null)
			{
				model.addAttribute("message",  loginRsp.getFault().getRspMsg());
				return "index";
			}
			model.addAttribute("message",  "返回报文错误,无法解析到返回业务数据头");
			return "index";
		}
		
		if(loginRsp.getHead().getSuccFlag() != 1)
		{
			model.addAttribute("message",  loginRsp.getHead().getRspMsg());
			return "index";
		}
		
		if(loginRsp.getBody().getTxComms()== null || loginRsp.getBody().getTxComms().size() == 0)
		{
			model.addAttribute("message",  "无可交易商品数据");
			return "index";
		}
		
		for(TxComm t :loginRsp.getBody().getTxComms())
			if(t.getBsDirect().equalsIgnoreCase("B")){
				loginRsp.getBody().setBuyEnable(true);
			}	
			else if(t.getBsDirect().equalsIgnoreCase("S")){
				loginRsp.getBody().setSellEnable(true);
			}
				
				
		List<MenuNode> tradeMenus =  createMemberMenu(loginRsp.getBody());
		
		loginRsp.getBody().setTradeMenus(tradeMenus);
	
		request.getSession().setAttribute("userinfo", loginRsp.getBody());
		
		model.addAttribute("pwdstatus", loginRsp.getBody().getPwdStatus());
		
		
		List<MdseElement> marks = mallService.findAllMarkets();
		
		if(marks == null){
			model.addAttribute("message",  "无相关可交易的市场");
			return "index";
		}	
		return "main";
    }
	
	
		/**
	     *  交易大厅主页
		 * @author kereny
		 * @date 2015-6-25 上午10:01:50
		 * @return
		 * String
	     *
		 */
	@RequestMapping(value="home.htm")
	public String home(Model model)
    {  
		LoginRsp loginRsp = (LoginRsp) request.getSession().getAttribute("userinfo");
		
		if(loginRsp == null){
			return "index";
		}
		
		return "main";
    }
	
	
	private List<MenuNode> createMemberMenu(LoginRsp loginRsp){
		
		List<MenuNode> tradeMenu = new ArrayList<MenuNode>();	
		
		if(loginRsp.isSellEnable()){
			
			MenuNode menu = new MenuNode();
			

			menu.setHasSubMenu(false);
			menu.setMenuName("我的挂牌");
			menu.setMenuURL("/mall/list.htm");
			
			tradeMenu.add(menu);
		}
		
		if(loginRsp.isSellEnable()&&loginRsp.isBuyEnable()){
			MenuNode menu = new MenuNode();
			
			menu.setHasSubMenu(true);
			menu.setMenuName("我的订单");
			menu.setMenuURL("/sell/list.htm");
			
			List<MenuNode> subMenus = new ArrayList<MenuNode>();
			MenuNode submenu = new MenuNode();
			
			submenu.setHasSubMenu(false);
			submenu.setMenuName("销售申请");
			submenu.setMenuURL("/sell/list.htm");
			subMenus.add(submenu);
			submenu = new MenuNode();
			
			submenu.setHasSubMenu(false);
			submenu.setMenuName("购买申请");
			submenu.setMenuURL("/buy/list.htm");
			subMenus.add(submenu);
			menu.setSubMenus(subMenus);
			
			tradeMenu.add(menu);
		}else if(loginRsp.isSellEnable()){
			MenuNode menu = new MenuNode();
			

			menu.setHasSubMenu(false);
			menu.setMenuName("我的订单");
			menu.setMenuURL("/sell/list.htm");
			tradeMenu.add(menu);
		}else if(loginRsp.isBuyEnable()){
			MenuNode menu = new MenuNode();
			

			menu.setHasSubMenu(false);
			menu.setMenuName("我的订单");
			menu.setMenuURL("/buy/list.htm");
			tradeMenu.add(menu);
		}
		//我的仓单 先包含签发仓单的注册，以及签发仓单的转出
		if(loginRsp.isSellEnable()&&loginRsp.isBuyEnable()){
			MenuNode menu = new MenuNode();
			
			menu.setHasSubMenu(true);
			menu.setMenuName("我的仓单");
			menu.setMenuURL("/warehouse/list.htm");
			
			List<MenuNode> subMenus = new ArrayList<MenuNode>();
			
			MenuNode submenu = new MenuNode();
			submenu.setHasSubMenu(false);
			submenu.setMenuName("签发仓单注册");
			submenu.setMenuURL("/warehouse/list.htm");
			subMenus.add(submenu);
			
			submenu = new MenuNode();
			submenu.setHasSubMenu(false);
			submenu.setMenuName("签发仓单转出");
			submenu.setMenuURL("/warehouse/rolloutlist.htm");
			subMenus.add(submenu);
			
			submenu = new MenuNode();
			submenu.setHasSubMenu(false);
			submenu.setMenuName("会员绑定");
			submenu.setMenuURL("/warehouse/membinding.htm");
			subMenus.add(submenu);
			
			menu.setSubMenus(subMenus);
			
			tradeMenu.add(menu);
		}else if(loginRsp.isSellEnable()){
			MenuNode menu = new MenuNode();
			menu.setHasSubMenu(true);
			menu.setMenuName("我的仓单");
			menu.setMenuURL("/warehouse/list.htm");
			
			List<MenuNode> subMenus = new ArrayList<MenuNode>();
			
			MenuNode submenu = new MenuNode();
			submenu.setHasSubMenu(false);
			submenu.setMenuName("签发仓单注册");
			submenu.setMenuURL("/warehouse/list.htm");
			subMenus.add(submenu);
			
			submenu = new MenuNode();
			submenu.setHasSubMenu(false);
			submenu.setMenuName("签发仓单转出");
			submenu.setMenuURL("/warehouse/rolloutlist.htm");
			subMenus.add(submenu);
			
			submenu = new MenuNode();
			submenu.setHasSubMenu(false);
			submenu.setMenuName("会员绑定");
			submenu.setMenuURL("/warehouse/membinding.htm");
			subMenus.add(submenu);
			
			menu.setSubMenus(subMenus);
			
			tradeMenu.add(menu);
		}else if(loginRsp.isBuyEnable()){
			MenuNode menu = new MenuNode();
			menu.setHasSubMenu(true);
			menu.setMenuName("我的仓单");
			menu.setMenuURL("/warehouse/list.htm");
			
			List<MenuNode> subMenus = new ArrayList<MenuNode>();
			
			MenuNode submenu = new MenuNode();
			submenu.setHasSubMenu(false);
			submenu.setMenuName("签发仓单转出");
			submenu.setMenuURL("/warehouse/rolloutlist.htm");
			subMenus.add(submenu);
			
			submenu = new MenuNode();
			submenu.setHasSubMenu(false);
			submenu.setMenuName("会员绑定");
			submenu.setMenuURL("/warehouse/membinding.htm");
			subMenus.add(submenu);
			
			menu.setSubMenus(subMenus);
			
			tradeMenu.add(menu);
		}
		
		if(loginRsp.isSellEnable()||loginRsp.isBuyEnable()){
			
			MenuNode menu = new MenuNode();
			

			menu.setHasSubMenu(false);
			menu.setMenuName("我的合同");
			menu.setMenuURL("/contract/list.htm");
			
			tradeMenu.add(menu);
		}
		
		if(loginRsp.isSellEnable()&&loginRsp.isBuyEnable()){
			MenuNode menu = new MenuNode();

			menu.setHasSubMenu(true);
			menu.setMenuName("交收管理");
			menu.setMenuURL("/delivery/selllist.htm");
			
			List<MenuNode> subMenus = new ArrayList<MenuNode>();
			MenuNode submenu = new MenuNode();
			
			submenu.setHasSubMenu(false);
			submenu.setMenuName("销售交收");
			submenu.setMenuURL("/delivery/selllist.htm");
			subMenus.add(submenu);
			
			submenu = new MenuNode();
			submenu.setHasSubMenu(false);
			submenu.setMenuName("采购交收");
			submenu.setMenuURL("/delivery/buylist.htm");
			subMenus.add(submenu);
			menu.setSubMenus(subMenus);
			
			submenu = new MenuNode();
			submenu.setHasSubMenu(false);
			submenu.setMenuName("发票确认");
			submenu.setMenuURL("/delivery/invlist.htm");
			subMenus.add(submenu);
			menu.setSubMenus(subMenus);
			
			tradeMenu.add(menu);
		}else if(loginRsp.isSellEnable()){
			MenuNode menu = new MenuNode();
			

			menu.setHasSubMenu(false);
			menu.setMenuName("交收管理");
			menu.setMenuURL("/delivery/selllist.htm");
			tradeMenu.add(menu);
		}else if(loginRsp.isBuyEnable()){
			MenuNode menu = new MenuNode();
			

			menu.setHasSubMenu(false);
			menu.setMenuName("交收管理");
			menu.setMenuURL("/delivery/buylist.htm");
			tradeMenu.add(menu);
		}
		
		if(checkRight(loginRsp.getOperRights(),"P", null)){
			
			MenuNode menu = new MenuNode();

			menu.setHasSubMenu(true);
			menu.setMenuName("我的资金");
			menu.setMenuURL("/fund/info.htm");
			
			List<MenuNode> subMenus = new ArrayList<MenuNode>();
			MenuNode submenu = new MenuNode();
			
			submenu.setHasSubMenu(false);
			submenu.setMenuName("资金账户");
			submenu.setMenuURL("/fund/info.htm");
			subMenus.add(submenu);
			submenu = new MenuNode();
			
			submenu.setHasSubMenu(false);
			submenu.setMenuName("账务明细");
			submenu.setMenuURL("/fund/list.htm");
			subMenus.add(submenu);
			menu.setSubMenus(subMenus);
			
			tradeMenu.add(menu);
		}
		
		
		if(loginRsp.isSellEnable()&&loginRsp.isBuyEnable()){
			MenuNode menu = new MenuNode();
			

			menu.setHasSubMenu(true);
			menu.setMenuName("历史交收");
			menu.setMenuURL("/query/selllist.htm");
			
			List<MenuNode> subMenus = new ArrayList<MenuNode>();
			MenuNode submenu = new MenuNode();
			
			submenu.setHasSubMenu(false);
			submenu.setMenuName("卖出查询");
			submenu.setMenuURL("/query/selllist.htm");
			subMenus.add(submenu);
			submenu = new MenuNode();
			
			submenu.setHasSubMenu(false);
			submenu.setMenuName("买入查询");
			submenu.setMenuURL("/query/buylist.htm");
			subMenus.add(submenu);
			menu.setSubMenus(subMenus);
			
			tradeMenu.add(menu);
		}else if(loginRsp.isSellEnable()){
			MenuNode menu = new MenuNode();
			
			menu.setHasSubMenu(false);
			menu.setMenuName("历史交收");
			menu.setMenuURL("/query/selllist.htm");
			tradeMenu.add(menu);
		}else if(loginRsp.isBuyEnable()){
			MenuNode menu = new MenuNode();
			
			menu.setHasSubMenu(false);
			menu.setMenuName("历史交收");
			menu.setMenuURL("/query/buylist.htm");
			tradeMenu.add(menu);
		}
		
		MenuNode menu = new MenuNode();

		menu.setHasSubMenu(true);
		menu.setMenuName("我的报表");
		menu.setMenuURL("/report/check.htm");
		
		
		List<MenuNode> subMenus = new ArrayList<MenuNode>();
		MenuNode submenu = new MenuNode();
		
		submenu.setHasSubMenu(false);
		submenu.setMenuName("日常对账单");
		submenu.setMenuURL("/report/check.htm");
		subMenus.add(submenu);
		submenu = new MenuNode();
		
		submenu.setHasSubMenu(false);
		submenu.setMenuName("账户月报表");
		submenu.setMenuURL("/report/monthly.htm");
		subMenus.add(submenu);
		menu.setSubMenus(subMenus);
		
		tradeMenu.add(menu);

		return tradeMenu;
	}
}
