package com.cnacex.eshop.web.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.cnacex.comm.util.StringUtil;
import com.cnacex.eshop.modul.JSonComm;
import com.cnacex.eshop.modul.Node;
import com.cnacex.eshop.msg.body.comm.CommProp;
import com.cnacex.eshop.msg.body.comm.Prop;
import com.cnacex.eshop.msg.body.mall.Listed;
import com.cnacex.eshop.msg.body.mall.ListedDetailReq;
import com.cnacex.eshop.msg.body.mall.ListedDetailRsp;
import com.cnacex.eshop.msg.body.mall.MdseElement;
import com.cnacex.eshop.msg.body.mall.MoreQueryReq;
import com.cnacex.eshop.msg.body.mall.SingleQueryReq;
import com.cnacex.eshop.msg.body.mall.MoreQueryReq.ReqComm;
import com.cnacex.eshop.msg.body.member.LoginRsp;
import com.cnacex.eshop.msg.body.member.LoginRsp.MemMarket;
import com.cnacex.eshop.msg.body.member.LoginRsp.TxComm;
import com.cnacex.eshop.msg.xml.mall.ListedDetailRspMsg;
import com.cnacex.eshop.msg.xml.mall.QueryCommRspMsg;
import com.cnacex.eshop.util.ListedUtil;
import com.cnacex.eshop.util.StatusUtil;

/**
 * @author kereny
 *
 */

@Controller
@RequestMapping(value="/mall")
public class MallController extends TradeController{

	static Logger logger = LoggerFactory.getLogger(MallController.class);
	
		/**
	     *  JSON取商品品种的属性列表
		 * @author kereny
		 * @date 2015-6-13 上午11:35:52
		 * @param request
		 * @param response
		 * @param commcode
		 * @return
		 * List<CommProp>
	     *
		 */
	@RequestMapping(value = "/prop.htm")
	public @ResponseBody
	JSonComm findCommProp(HttpServletRequest request,HttpServletResponse response,
			@RequestParam(value = "code", required = false) String code) {
		
		
		JSonComm js = new JSonComm();

		MdseElement me  = mallService.findLocalMdseEntity(code);
		if(me == null){
			js.setSuccflag(-1);
			js.setMsg("无对映数据");
		}else{
			List<CommProp> props = me.getCommProps();			
			Collections.sort(props, new CommPropSortByView()); 			
			me.setCommProps(props);

			js.setSuccflag(0);
			js.setMsg("success");
			js.setData(me);
		}
		
		
		return js; 
	}
	
	
	@RequestMapping(value = "/findallmarket.htm")
	public @ResponseBody JSonComm findAllMarket() {
		
		JSonComm js = new JSonComm();
		List<MdseElement> marks = mallService.findAllMarkets();
		
		if(marks == null){
			js.setSuccflag(-1);
			js.setMsg("无对映数据");
			return js;
		}
		
		LoginRsp loginRsp = (LoginRsp) request.getSession().getAttribute("userinfo");
		List<MdseElement> rsList = new ArrayList<MdseElement>();
		//过滤处理可交易市场
		List<MemMarket> avMarks =  loginRsp.getMemMarkets();
		
		for(MdseElement me: marks)
			for(MemMarket mm: avMarks)
				if(me.getMdseCode().equalsIgnoreCase(mm.getMarkCode()))
					rsList.add(me);

		if(rsList == null||rsList.size() == 0){
			js.setSuccflag(-1);
			js.setMsg("无相关数据");
		}else{
			js.setSuccflag(0);
			js.setMsg("success");
			js.setData(converMdse2Node(rsList));
		}			
		return js;
	}
	
	
		/**
	     *  取买方商品代码列表，兄弟节点
		 * @author kereny
		 * @date 2015-7-8 下午5:55:05
		 * @param request
		 * @param response
		 * @param code
		 * @return
		 * JSonComm
	     *
		 */
	@RequestMapping(value = "/parbuyclass.htm")
	public @ResponseBody
	JSonComm findParBuyClass(HttpServletRequest request,HttpServletResponse response,
			@RequestParam(value = "code", required = false) String code) {
		
		
		LoginRsp loginRsp = (LoginRsp) request.getSession().getAttribute("userinfo");
		
		MdseElement obj  = mallService.findLocalMdseEntity(code);		
		List<MdseElement>  rsList = new ArrayList<MdseElement>();
		if(StringUtil.nullOrBlank(obj.getPmdseCode())){
			
			List<MdseElement> marks = mallService.findAllMarkets();
			
			List<MemMarket> avMarks =  loginRsp.getMemMarkets();
			
			for(MdseElement me: marks)
				for(MemMarket mm: avMarks)
					if(me.getMdseCode().equalsIgnoreCase(mm.getMarkCode()))
						rsList.add(me);
											
		}else{

			List<MdseElement>  sublist =  mallService.findChildList(obj.getPmdseCode());			
			MdseElement pobj  = mallService.findLocalMdseEntity(obj.getPmdseCode());
			
			if(pobj.getClassFlg() != null && "0".equalsIgnoreCase(pobj.getClassFlg()))
			{
				for(TxComm t :loginRsp.getTxComms())
				{
					if(t.getBsDirect().equalsIgnoreCase("B"))
					{
						for(MdseElement e: sublist)
						{
							if(e.getMdseCode().equalsIgnoreCase(t.getClassCode())){
								rsList.add(e);
								break;
							}
						}
					}
				}
			}else{
				rsList = sublist;
			}
		}
		
		JSonComm js = new JSonComm();
		if(rsList == null||rsList.size() == 0){
			js.setSuccflag(-1);
			js.setMsg("无相关数据");
		}else{
			js.setSuccflag(0);
			js.setMsg("success");
			js.setData(converMdse2Node(rsList));
		}
		
		return js;
		
	}
	
	
		/**
	     *  取买方商品代码列表，子节点
		 * @author kereny
		 * @date 2015-7-8 下午5:56:43
		 * @param request
		 * @param response
		 * @param code
		 * @return
		 * JSonComm
	     *
		 */
	@RequestMapping(value = "/subbuyclass.htm")
	public @ResponseBody
	JSonComm findBuyClass(HttpServletRequest request,HttpServletResponse response,
			@RequestParam(value = "code", required = false) String code) {
		
		
		LoginRsp loginRsp = (LoginRsp) request.getSession().getAttribute("userinfo");
		List<MdseElement>  sublist =  mallService.findChildList(code);
		List<MdseElement>  rsList = new ArrayList<MdseElement>();
		
		MdseElement obj  = mallService.findLocalMdseEntity(code);
		
		if(obj.getClassFlg() != null && "0".equalsIgnoreCase(obj.getClassFlg()))
		{
			for(TxComm t :loginRsp.getTxComms())
			{
				if(t.getBsDirect().equalsIgnoreCase("B"))
				{
					for(MdseElement e: sublist)
					{
						if(e.getMdseCode().equalsIgnoreCase(t.getClassCode())){
							rsList.add(e);
							break;
						}
					}
				}
			}
		}else{
			rsList = sublist;
		}
		
		JSonComm js = new JSonComm();
		if(rsList == null||rsList.size() == 0){
			js.setSuccflag(-1);
			js.setMsg("无相关数据");
		}else{
			js.setSuccflag(0);
			js.setMsg("success");
			js.setData(converMdse2Node(rsList));
		}
		
		return js;
		
	}
	
		/**
	     *  取商品代码列表，兄弟节点
		 * @author kereny
		 * @date 2015-7-8 下午5:55:05
		 * @param request
		 * @param response
		 * @param code
		 * @return
		 * JSonComm
	     *
		 */
	@RequestMapping(value = "/parclass.htm")
	public @ResponseBody
	JSonComm findParAllClass(HttpServletRequest request,HttpServletResponse response,
			@RequestParam(value = "code", required = false) String code) {
		
		
		LoginRsp loginRsp = (LoginRsp) request.getSession().getAttribute("userinfo");
		
		MdseElement obj  = mallService.findLocalMdseEntity(code);		
		List<MdseElement>  rsList = new ArrayList<MdseElement>();
		if(StringUtil.nullOrBlank(obj.getPmdseCode())){
			
			List<MdseElement> marks = mallService.findAllMarkets();
			
			List<MemMarket> avMarks =  loginRsp.getMemMarkets();
			
			for(MdseElement me: marks)
				for(MemMarket mm: avMarks)
					if(me.getMdseCode().equalsIgnoreCase(mm.getMarkCode()))
						rsList.add(me);
											
		}else{
	
			List<MdseElement>  sublist =  mallService.findChildList(obj.getPmdseCode());			
			MdseElement pobj  = mallService.findLocalMdseEntity(obj.getPmdseCode());
			
			if(pobj.getClassFlg() != null && "0".equalsIgnoreCase(pobj.getClassFlg()))
			{
				for(TxComm t :loginRsp.getTxComms())
				{
					for(MdseElement e: sublist)
					{
						if(e.getMdseCode().equalsIgnoreCase(t.getClassCode())){
							boolean bflag = false;
							for(MdseElement sm : rsList){
								if(sm.getMdseCode().equalsIgnoreCase(e.getMdseCode())){
									bflag = true;
									break;
								}
							}
							
							if(!bflag){
								rsList.add(e);
								break;
							}
						}
					}
				}
				
			}else{
				rsList = sublist;
			}
		}
		
		JSonComm js = new JSonComm();
		if(rsList == null||rsList.size() == 0){
			js.setSuccflag(-1);
			js.setMsg("无相关数据");
		}else{
			js.setSuccflag(0);
			js.setMsg("success");
			js.setData(converMdse2Node(rsList));
		}
		
		return js;
		
	}
	
	
		/**
	     *  取买方商品代码列表，子节点
		 * @author kereny
		 * @date 2015-7-8 下午5:56:43
		 * @param request
		 * @param response
		 * @param code
		 * @return
		 * JSonComm
	     *
		 */
	@RequestMapping(value = "/subclass.htm")
	public @ResponseBody
	JSonComm findAllClass(HttpServletRequest request,HttpServletResponse response,
			@RequestParam(value = "code", required = false) String code) {
		
		
		LoginRsp loginRsp = (LoginRsp) request.getSession().getAttribute("userinfo");
		List<MdseElement>  sublist =  mallService.findChildList(code);
		List<MdseElement>  rsList = new ArrayList<MdseElement>();
		
		MdseElement obj  = mallService.findLocalMdseEntity(code);
		
		if(obj.getClassFlg() != null && "0".equalsIgnoreCase(obj.getClassFlg()))
		{
			for(TxComm t :loginRsp.getTxComms())
			{
				for(MdseElement e: sublist)
				{
					if(e.getMdseCode().equalsIgnoreCase(t.getClassCode())){
						boolean bflag = false;
						for(MdseElement sm : rsList){
							if(sm.getMdseCode().equalsIgnoreCase(e.getMdseCode())){
								bflag = true;
								break;
							}
						}
						
						if(!bflag){
							rsList.add(e);
							break;
						}
					}
				}
			}
		}else{
			rsList = sublist;
		}
		
		JSonComm js = new JSonComm();
		if(rsList == null||rsList.size() == 0){
			js.setSuccflag(-1);
			js.setMsg("无相关数据");
		}else{
			js.setSuccflag(0);
			js.setMsg("success");
			js.setData(converMdse2Node(rsList));
		}
		
		return js;
		
	}	
	
		/**
	     *  取卖方商品代码列表，兄弟节点
		 * @author kereny
		 * @date 2015-7-8 下午5:57:09
		 * @param request
		 * @param response
		 * @param code
		 * @return
		 * JSonComm
	     *
		 */
	@RequestMapping(value = "/parsellclass.htm")
	public @ResponseBody
	JSonComm findParSellClass(HttpServletRequest request,HttpServletResponse response,
			@RequestParam(value = "code", required = false) String code,
			@RequestParam(value = "txtype", required = false) String txtype) {
		
		
		LoginRsp loginRsp = (LoginRsp) request.getSession().getAttribute("userinfo");
		
		MdseElement obj  = mallService.findLocalMdseEntity(code);		
		List<MdseElement>  rsList = new ArrayList<MdseElement>();
		if(StringUtil.nullOrBlank(obj.getPmdseCode())){
			
			List<MdseElement> marks = mallService.findAllMarkets();
			
			List<MemMarket> avMarks =  loginRsp.getMemMarkets();
			
			for(MdseElement me: marks)
				for(MemMarket mm: avMarks)
					if(me.getMdseCode().equalsIgnoreCase(mm.getMarkCode()))
						rsList.add(me);
											
		}else{

			List<MdseElement>  sublist =  mallService.findChildList(obj.getPmdseCode());			
			MdseElement pobj  = mallService.findLocalMdseEntity(obj.getPmdseCode());
			
			if(pobj.getClassFlg() != null && "0".equalsIgnoreCase(pobj.getClassFlg()))
			{
				for(TxComm t :loginRsp.getTxComms())
				{
					if(t.getBsDirect().equalsIgnoreCase("S"))
					{
						for(MdseElement e: sublist)
						{
							if(e.getMdseCode().equalsIgnoreCase(t.getClassCode())){
								if(StringUtil.nullOrBlank(txtype)){
									rsList.add(e);
									break;
								}else if(checkRight(loginRsp.getOperRights(), txtype, t.getClassCode())){
									rsList.add(e);
									break;
								}
							}
						}
					}
				}
			}else{
				rsList = sublist;
			}
		}
		
		JSonComm js = new JSonComm();
		if(rsList == null||rsList.size() == 0){
			js.setSuccflag(-1);
			js.setMsg("无相关数据");
		}else{
			js.setSuccflag(0);
			js.setMsg("success");
			if(!StringUtil.nullOrBlank(txtype)&& "T".equalsIgnoreCase(txtype))
				js.setData(converMdse2ActiveNode(rsList));
			else
				js.setData(converMdse2Node(rsList));
		}
		
		return js;
		
	}
	
	
		/**
	     *  取卖方商品代码列表，子节点
		 * @author kereny
		 * @date 2015-7-8 下午5:57:41
		 * @param request
		 * @param response
		 * @param code
		 * @return
		 * JSonComm
	     *
		 */
	@RequestMapping(value = "/subsellclass.htm")
	public @ResponseBody
	JSonComm findSellClass(HttpServletRequest request,HttpServletResponse response,
			@RequestParam(value = "code", required = false) String code,
			@RequestParam(value = "txtype", required = false) String txtype) {
		
		
		LoginRsp loginRsp = (LoginRsp) request.getSession().getAttribute("userinfo");
		List<MdseElement>  sublist =  mallService.findChildList(code);
		List<MdseElement>  rsList = new ArrayList<MdseElement>();
		
		MdseElement obj  = mallService.findLocalMdseEntity(code);
		
		if(obj.getClassFlg() != null && "0".equalsIgnoreCase(obj.getClassFlg()))
		{
			for(TxComm t :loginRsp.getTxComms())
			{
				if(t.getBsDirect().equalsIgnoreCase("S"))
				{
					for(MdseElement e: sublist)
					{
						if(e.getMdseCode().equalsIgnoreCase(t.getClassCode())){
							
							if(StringUtil.nullOrBlank(txtype)){
								rsList.add(e);
								break;
							}else if(checkRight(loginRsp.getOperRights(), txtype, t.getClassCode())){
								rsList.add(e);
								break;
							}
						}
					}
				}
			}
		}else{
			
			rsList = sublist;
		}
		
		JSonComm js = new JSonComm();
		if(rsList == null||rsList.size() == 0){
			js.setSuccflag(-1);
			js.setMsg("无相关数据");
		}else{
			js.setSuccflag(0);
			js.setMsg("success");
			if(!StringUtil.nullOrBlank(txtype)&& "T".equalsIgnoreCase(txtype))
				js.setData(converMdse2ActiveNode(rsList));
			else
				js.setData(converMdse2Node(rsList));
		}
		
		return js;
	}
	
	
		/**
	     *  商品分类节点转NODE数据结构
		 * @author kereny
		 * @date 2015-7-8 下午5:58:52
		 * @param mes
		 * @return
		 * List<Node>
	     *
		 */
	private List<Node> converMdse2Node(List<MdseElement> mes){
		
		if(mes == null || mes.size() == 0) return null;

		List<Node> nodes = new ArrayList<Node>();
		Node node = null;
		for(MdseElement e: mes){
			node = new Node();
			node.setCode(e.getMdseCode());
			node.setName(e.getMdseName());
			node.setHaveLeaf(e.isHaveLeaf());
			node.setLevel(e.getClassFlg());
			
			nodes.add(node);
		}
			
		return nodes;
		
	}
	
		/**
	     *  商品分类节点转NODE数据结构
		 * @author kereny
		 * @date 2015-7-8 下午5:58:52
		 * @param mes
		 * @return
		 * List<Node>
	     *
		 */
	private List<Node> converMdse2ActiveNode(List<MdseElement> mes){
		
		if(mes == null || mes.size() == 0) return null;
	
		List<Node> nodes = new ArrayList<Node>();
		Node node = null;
		for(MdseElement e: mes){
			node = new Node();
			node.setCode(e.getMdseCode());
			node.setName(e.getMdseName());
			node.setHaveLeaf(e.isHaveLeaf());
			node.setLevel(e.getClassFlg());
			
			if("3".equalsIgnoreCase(e.getClassFlg()))
			{
				if(e.getActive() == 1){
					nodes.add(node);
				}
			}else{
				nodes.add(node);
			}	
		}
			
		return nodes;
		
	}
	
		/**
	     *  进入我的挂牌主界面
		 * @author kereny
		 * @date 2015-6-23 下午1:06:11
		 * @param model
		 * @return
		 * String
	     *
		 */
	@RequestMapping(value = "/list.htm")
	public String list(ModelMap model) {
		
		LoginRsp loginRsp = (LoginRsp) request.getSession().getAttribute("userinfo");
		
		getUrlMatch(loginRsp.getTradeMenus(), model);
		return "mall/list";
	}
	
	
	
	@RequestMapping(value = "/findlimitlist.htm")
	public  @ResponseBody JSonComm findList(
			@RequestParam(value = "code", required = false) String code,  
			@RequestParam(value = "doe", required = false) String doe,
			@RequestParam(value = "dol", required = false) String dol,
			@RequestParam(value = "pageNum", required = false, defaultValue = "0") int pageNum, 
			@RequestParam(value = "pageSize", required = false, defaultValue = "20" ) int pageSize,
			@RequestParam(value = "status", required = false, defaultValue = "") String status, 
			Model model) {
		
			LoginRsp loginRsp = (LoginRsp) request.getSession().getAttribute("userinfo");
		
			if(pageNum < 0) pageNum = 0;
			if(pageSize == 0) pageSize = PAGESIZE;
			
			String start = Integer.toString(pageNum*pageSize+1);

			MdseElement obj  = null;
			if(!StringUtil.nullOrBlank(code))
				obj  = mallService.findLocalMdseEntity(code);
			QueryCommRspMsg rspMsg = null;

			if(obj != null && !"1".equalsIgnoreCase(obj.getClassFlg()) && !"3".equalsIgnoreCase(obj.getClassFlg()))
			{
				List<MdseElement> mes = mallService.findLeafMdseEntity(code);
				
				MoreQueryReq moreQueryReq = new MoreQueryReq();
				List<ReqComm> reqcomms = new ArrayList<ReqComm>();
				ReqComm reqComm = null;
				if(mes != null){
					for (int i = 0; i < mes.size(); i++) {
						reqComm = new ReqComm();
						reqComm.setReqCode(mes.get(i).getMdseCode());
						reqComm.setReqType("3");
						reqcomms.add(reqComm);
					}
				}
				moreQueryReq.setReqComms(reqcomms);
				moreQueryReq.setReqStart(start);
				moreQueryReq.setReqStatus(status);
				moreQueryReq.setReqNum(pageSize);
				moreQueryReq.setmID(loginRsp.getmID());
				moreQueryReq.setOperID(loginRsp.getOperID());
				moreQueryReq.setListedMID(loginRsp.getmID());
				moreQueryReq.setDoe(doe);
				moreQueryReq.setDol(dol);
				
				rspMsg = mallService.findMoreCommListed(moreQueryReq);
			
			}else{
				
				SingleQueryReq singleQueryReq = new SingleQueryReq();
				singleQueryReq.setReqCode(code);

				singleQueryReq.setReqStart(start);
				singleQueryReq.setReqStatus(status);
				singleQueryReq.setReqNum(pageSize);
				singleQueryReq.setmID(loginRsp.getmID());
				singleQueryReq.setOperID(loginRsp.getOperID());
				singleQueryReq.setListedMID(loginRsp.getmID());
				
				singleQueryReq.setDoe(doe);
				singleQueryReq.setDol(dol);
				
				if(obj != null){
					singleQueryReq.setReqType( obj.getClassFlg());
				}
				rspMsg = mallService.findSingleListed(singleQueryReq);
			}
			
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
			
			
			if(rspMsg.getBody().getListeds() == null ||rspMsg.getBody().getListeds().size() == 0){
				
				jsdata.setSuccflag(-2);
				jsdata.setMsg("无数据列表");
				return jsdata;
			}
			
			List<Listed> listeds = rspMsg.getBody().getListeds();
			
			List<Listed> rslisteds = new ArrayList<Listed>();
			for(Listed l: listeds)
			{
				MdseElement me = mallService.findLocalMdseEntity(l.getCommCode());
				if(me != null)
				{
					l.setCommName(me.getMdseName());
					l.setUom(me.getUom());
				}
				l.setListedTypeName(ListedUtil.getListedName(l.getListedType()));
				l.setStatusDesc(StatusUtil.getSellStatus(l.getStatus()));
				rslisteds.add(l);
			}
			rspMsg.getBody().setListeds(rslisteds);
			
			int nTotalNum = rspMsg.getBody().getTotalNum();
			
			if(nTotalNum%pageSize == 0){
				rspMsg.getBody().setTotalPage(nTotalNum/pageSize);
			}else{
				rspMsg.getBody().setTotalPage(nTotalNum/pageSize+1);
			}
			
			jsdata.setSuccflag(0);
			jsdata.setMsg(rspMsg.getHead().getRspMsg());
			jsdata.setData(rspMsg.getBody());
			
			return jsdata;
	}
	
	
	
	@RequestMapping(value = "/findsell.htm")
	public  @ResponseBody HashMap<String, Object>  findSell(
			@RequestParam(value = "code", required = false) String code,  
			@RequestParam(value = "doe", required = false) String doe,
			@RequestParam(value = "dol", required = false) String dol,
			
			@RequestParam(value = "edoe", required = false) String edoe,
			@RequestParam(value = "edol", required = false) String edol,
			
			@RequestParam(value = "status", required = false, defaultValue = "") String status, 
			Model model) {
		
			LoginRsp loginRsp = (LoginRsp) request.getSession().getAttribute("userinfo");
			
			
			HashMap<String, Object> maps = new HashMap<String, Object>();

			SingleQueryReq singleQueryReq = new SingleQueryReq();
			singleQueryReq.setmID(loginRsp.getmID());
			singleQueryReq.setOperID(loginRsp.getOperID());
			singleQueryReq.setListedMID(loginRsp.getmID());
			
			singleQueryReq.setDoe(doe);
			singleQueryReq.setDol(dol);
			singleQueryReq.setEdoe(edoe);
			singleQueryReq.setEdol(edol);
			singleQueryReq.setReqStatus(status);
			
			if(!StringUtil.nullOrBlank(code)){
				singleQueryReq.setReqCode(code);
				singleQueryReq.setReqType("3");
			}
			
			QueryCommRspMsg rspMsg  = mallService.findSingleListed(singleQueryReq);
			List<Listed> rslisteds = new ArrayList<Listed>();
			
			if(rspMsg.getHead() == null){							
				maps.put("data", rslisteds);
				return maps;
			}
			
			if(rspMsg.getHead().getSuccFlag() != 1){
				
				maps.put("data", rslisteds);
				return maps;
			}
			
			
			if(rspMsg.getBody().getListeds() == null ||rspMsg.getBody().getListeds().size() == 0){				
				maps.put("data", rslisteds);
				return maps;
			}

			List<Listed> listeds = rspMsg.getBody().getListeds();
			
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

				l.setListedTypeName(ListedUtil.getListedName(l.getListedType()));
				l.setStatusDesc(StatusUtil.getSellStatus(l.getStatus()));
				rslisteds.add(l);
			}
			
			maps.put("data", rslisteds);
			return maps;
	}
	
	
	
	
	@RequestMapping(value = "/findunlimitlist.htm")
	public  @ResponseBody JSonComm findUnlimitList(
			@RequestParam(value = "code", required = false) String code,  
			@RequestParam(value = "pageNum", required = false, defaultValue = "0") int pageNum, 
			@RequestParam(value = "pageSize", required = false, defaultValue = "20" ) int pageSize,
			@RequestParam(value = "status", required = false, defaultValue = "100") String status, Model model) {
		
			LoginRsp loginRsp = (LoginRsp) request.getSession().getAttribute("userinfo");
			
			
			if(pageNum < 0) pageNum = 0;
			if(pageSize == 0) pageSize = PAGESIZE;
			
			String start = Integer.toString(pageNum*pageSize+1);
		
			MdseElement obj  = null;
			if(!StringUtil.nullOrBlank(code))
				obj  = mallService.findLocalMdseEntity(code);
			QueryCommRspMsg rspMsg = null;
			if(obj != null && !"1".equalsIgnoreCase(obj.getClassFlg()) && !"3".equalsIgnoreCase(obj.getClassFlg()))
			{
				List<MdseElement> mes = mallService.findLeafMdseEntity(code);
				
				MoreQueryReq moreQueryReq = new MoreQueryReq();
				List<ReqComm> reqcomms = new ArrayList<ReqComm>();
				ReqComm reqComm = null;
				if(mes != null){
					for (int i = 0; i < mes.size(); i++) {
						reqComm = new ReqComm();
						reqComm.setReqCode(mes.get(i).getMdseCode());
						reqComm.setReqType("3");
						reqcomms.add(reqComm);
					}
				}
				moreQueryReq.setReqComms(reqcomms);
				moreQueryReq.setReqStart(start);
				moreQueryReq.setReqStatus(status);
				moreQueryReq.setReqNum(pageSize);
				moreQueryReq.setmID(loginRsp.getmID());
				moreQueryReq.setOperID(loginRsp.getOperID());
				moreQueryReq.setReqEffRec("1");
				
				rspMsg = mallService.findMoreCommListed(moreQueryReq);
			
			}else{
				
				SingleQueryReq singleQueryReq = new SingleQueryReq();
				singleQueryReq.setReqCode(code);

				singleQueryReq.setReqStart(start);
				singleQueryReq.setReqStatus(status);
				singleQueryReq.setReqNum(pageSize);
				singleQueryReq.setmID(loginRsp.getmID());
				singleQueryReq.setOperID(loginRsp.getOperID());
				
				singleQueryReq.setReqEffRec("1");
				
				if(obj != null){
					singleQueryReq.setReqType( obj.getClassFlg());
				}
				rspMsg = mallService.findSingleListed(singleQueryReq);
			}
			
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
			
			if(rspMsg.getBody().getListeds() == null ||rspMsg.getBody().getListeds().size() == 0){
				
				jsdata.setSuccflag(-2);
				jsdata.setMsg("无数据列表");
				return jsdata;
			}
			
			List<Listed> listeds = rspMsg.getBody().getListeds();
			
			List<Listed> rslisteds = new ArrayList<Listed>();
			for(Listed l: listeds)
			{
				MdseElement me = mallService.findLocalMdseEntity(l.getCommCode());
				if(me != null)
				{
					l.setCommName(me.getMdseName());
					l.setUom(me.getUom());
				}
				l.setListedTypeName(ListedUtil.getListedName(l.getListedType()));
				rslisteds.add(l);
			}
			rspMsg.getBody().setListeds(rslisteds);
			

			int nTotalNum = rspMsg.getBody().getTotalNum();
			
			if(nTotalNum%pageSize== 0){
				rspMsg.getBody().setTotalPage(nTotalNum/pageSize);
			}else{
				rspMsg.getBody().setTotalPage(nTotalNum/pageSize+1);
			}
			
			jsdata.setSuccflag(0);
			jsdata.setMsg(rspMsg.getHead().getRspMsg());
			jsdata.setData(rspMsg.getBody());
			
			return jsdata;
	}
	
	
	
	@RequestMapping(value = "/findtradelist.htm")
	public  @ResponseBody HashMap<String, Object> findTradeList(
			@RequestParam(value = "querypara", required = false) String querypara,  		
			@RequestParam(value = "start", required = false) String start, 
			@RequestParam(value = "length", required = false) String length,			
			@RequestParam(value = "draw", required = false) String draw,
			@RequestParam(value = "status", required = false, defaultValue = "100") String status, Model model) {
		
			LoginRsp loginRsp = (LoginRsp) request.getSession().getAttribute("userinfo");
			
			MdseElement obj  = null;
			
			if(!StringUtil.nullOrBlank(querypara))
			{
				if(StringUtil.isNumber(querypara)){
					obj  = mallService.findLocalMdseEntity(querypara);
				}else{
					obj  = mallService.findLocalMdseEntityByName(querypara);
				}
			}
				
			QueryCommRspMsg rspMsg = null;
			if(obj != null && !"1".equalsIgnoreCase(obj.getClassFlg()) && !"3".equalsIgnoreCase(obj.getClassFlg()))
			{
				List<MdseElement> mes = mallService.findLeafMdseEntity(obj.getMdseCode());
				
				MoreQueryReq moreQueryReq = new MoreQueryReq();
				List<ReqComm> reqcomms = new ArrayList<ReqComm>();
				ReqComm reqComm = null;
				if(mes != null){
					for (int i = 0; i < mes.size(); i++) {
						reqComm = new ReqComm();
						reqComm.setReqCode(mes.get(i).getMdseCode());
						reqComm.setReqType("3");
						reqcomms.add(reqComm);
					}
				}
				moreQueryReq.setReqComms(reqcomms);
				moreQueryReq.setReqStatus(status);
				moreQueryReq.setmID(loginRsp.getmID());
				moreQueryReq.setOperID(loginRsp.getOperID());
				moreQueryReq.setReqEffRec("1");
				
				
				if(!StringUtil.nullOrBlank(start) && !StringUtil.nullOrBlank(length)){				
					moreQueryReq.setReqStart(String.format("%d", (Integer.parseInt(start)+1)));
					moreQueryReq.setReqNum(Integer.parseInt(length));
				}			
				rspMsg = mallService.findMoreCommListed(moreQueryReq);
			
			}else{
				
				SingleQueryReq singleQueryReq = new SingleQueryReq();
				
				if(obj != null)
					singleQueryReq.setReqCode(obj.getMdseCode());

				singleQueryReq.setReqStatus(status);
				singleQueryReq.setmID(loginRsp.getmID());
				singleQueryReq.setOperID(loginRsp.getOperID());
				
				singleQueryReq.setReqEffRec("1");
				
				if(!StringUtil.nullOrBlank(start) && !StringUtil.nullOrBlank(length)){				
					singleQueryReq.setReqStart(String.format("%d", (Integer.parseInt(start)+1)));
					singleQueryReq.setReqNum(Integer.parseInt(length));
				}	
				
				if(obj != null){
					singleQueryReq.setReqType( obj.getClassFlg());
				}
				rspMsg = mallService.findSingleListed(singleQueryReq);
			}
			
			HashMap<String, Object> maps = new HashMap<String, Object>();
			
			List<Listed> rslisteds = new ArrayList<Listed>();
			if(rspMsg.getHead() == null){
				
				maps.put("data", rslisteds);
				maps.put("recordsTotal", 0);				
				maps.put("recordsFiltered", 0);			
				maps.put("draw", draw);
				return maps;
			}
			
			if(rspMsg.getHead().getSuccFlag() != 1){
				
				maps.put("data", rslisteds);
				maps.put("recordsTotal", 0);				
				maps.put("recordsFiltered", 0);			
				maps.put("draw", draw);
				return maps;
			}
			
			if(rspMsg.getBody().getListeds() == null ||rspMsg.getBody().getListeds().size() == 0){
				
				maps.put("data", rslisteds);
				maps.put("recordsTotal", 0);				
				maps.put("recordsFiltered", 0);			
				maps.put("draw", draw);
				return maps;
			}
			
			List<Listed> listeds = rspMsg.getBody().getListeds();
			
			
			for(Listed l: listeds)
			{
				MdseElement me = mallService.findLocalMdseEntity(l.getCommCode());
				if(me != null)
				{
					l.setCommName(me.getMdseName());
					l.setUom(me.getUom());
				}
				l.setListedTypeName(ListedUtil.getListedName(l.getListedType()));
				rslisteds.add(l);
			}
			
			maps.put("data", rslisteds);
			if(!StringUtil.nullOrBlank(start) && !StringUtil.nullOrBlank(length)){	
				maps.put("recordsTotal", rspMsg.getBody().getTotalNum());
				
				maps.put("recordsFiltered", rspMsg.getBody().getTotalNum());
			
				maps.put("draw", draw);
			}
			
			return maps;
			
	}
	
	
	

	/**
     *  查看明细
	 * @author kereny
	 * @date 2015-6-23 下午1:06:11
	 * @param model
	 * @return
	 * String
     *
	 */
	@RequestMapping(value = "/item/{listedNo}.htm")
	public String detail(
			@PathVariable("listedNo") String listedNo, Model model) {
	
		LoginRsp loginRsp = (LoginRsp) request.getSession().getAttribute("userinfo");
		
		ListedDetailReq listedDetailReq = new ListedDetailReq();
		
		listedDetailReq.setListedNo(listedNo);
		listedDetailReq.setmID(loginRsp.getmID());
		
		listedDetailReq.setOperID(loginRsp.getOperID());
	
		ListedDetailRspMsg rspMsg = mallService.findDetailByID(listedDetailReq);
		
		
		if (rspMsg.getHead() == null) {
			model.addAttribute("message", rspMsg.getFault().getRspMsg());
			return "comm/fail";
		}
		
		if (rspMsg.getHead().getSuccFlag() != 1) {
			model.addAttribute("message", rspMsg.getHead().getRspMsg());
			return "comm/fail";
		}
		
		ListedDetailRsp rspBody = rspMsg.getBody();
		
		rspBody.setClassName(mallService.findLocalMdseEntity(rspBody.getClassCode()).getMdseName());
		
		MdseElement me = mallService.findLocalMdseEntity(rspBody.getCommCode());
		if(me != null)
		{
			rspBody.setCommName(me.getMdseName());
			
			rspBody.setUom(me.getUom());
			
			if(me.getActive() == 0){
				model.addAttribute("message", "该商品已禁止交易,相关详细信息已无法浏览,请联系相关商家");
				return "comm/fail";
			}
		}
		
		if("A".equalsIgnoreCase(rspBody.getDelist()) && !rspBody.getmID().equalsIgnoreCase(loginRsp.getmID())){
			if(!checkIsDelistMem(rspBody.getDelistMems(), loginRsp.getmID())){
				model.addAttribute("message", "该商品无法浏览,指定会员可查看");
				return "comm/fail";
			}
		}
		
		rspBody.setMarkName(mallService.findLocalMdseEntity(rspBody.getMarkCode()).getMdseName());
		List<MdseElement> nodes = mallService.findNodeTreePath(rspBody.getCommCode());

		rspBody.setProps(supplyIdxName(rspBody.getCommCode(), rspBody.getProps()));		
		rspBody.setListedTypeName(ListedUtil.getListedName(rspBody.getListedType()));
		
		if(rspMsg.getBody().getmID().equalsIgnoreCase(loginRsp.getmID()) ||
				!checkRight(loginRsp.getOperRights(), "T", rspMsg.getBody().getClassCode()) ||
				!checkMarket(loginRsp.getMemMarkets(), rspMsg.getBody().getMarkCode()) ||
				!checkTxComm(loginRsp.getTxComms(), rspMsg.getBody().getClassCode(), "B") ||
				!"1".equalsIgnoreCase(rspBody.getEffRec())||
				"888".equalsIgnoreCase(loginRsp.getMemLevel()))
		{
			model.addAttribute("enableBuy", "false");
		}else {
			model.addAttribute("enableBuy", "true");
		}
		model.addAttribute("active", "prepare");
		
		model.addAttribute("nodepath", nodes);
		
		model.addAttribute("rspBody", rspBody);
		
		return "mall/detail";
	}
	
	
	/**
     *  列表
	 * @author kereny
	 * @date 2015-6-23 下午1:06:11
	 * @param model
	 * @return
	 * String
     *
	 */
	@RequestMapping(value = "/class/{code}.htm")
	public String findclass(
			@PathVariable("code") String code, Model model) {
	
		LoginRsp loginRsp = (LoginRsp) request.getSession().getAttribute("userinfo");
		
		MdseElement obj = mallService.findLocalMdseEntity(code);
		
		if(obj == null){
			model.addAttribute("message", "无法找到对映的商品,请确认分类是否存在");
			return "comm/fail";
		}
		
		List<MdseElement> mdses = mallService.findChildList(code);
		
		List<MdseElement> rsList = new ArrayList<MdseElement>();
		
		if(obj.getClassFlg() != null && "0".equalsIgnoreCase(obj.getClassFlg()))
		{
			for(TxComm t :loginRsp.getTxComms())
			{
				if(t.getBsDirect().equalsIgnoreCase("B"))
				{
					for(MdseElement e: mdses)
					{
						if(e.getMdseCode().equalsIgnoreCase(t.getClassCode())){
							
								rsList.add(e);
								break;

						}
					}
				}
			}
		}else{
			rsList = mdses;
		}
		
		if(rsList == null || rsList.size() == 0 && "3".equalsIgnoreCase(obj.getClassFlg()))
		{
			rsList.add(obj);
		}
		List<MdseElement> nodes = mallService.findNodeTreePath(code);
		
		model.addAttribute("nodepath", nodes);
		
		model.addAttribute("curcls", obj);
		
		model.addAttribute("subcls", rsList);	
		
		return "mall/sellist";
	}
	
	
	/**
     *  列表
	 * @author kereny
	 * @date 2015-6-23 下午1:06:11
	 * @param model
	 * @return
	 * String
     *
	 */
	@RequestMapping(value = "/search.htm")
	public String search(
			@RequestParam(value = "keyword", required = false) String keyword, Model model) {
	
		LoginRsp loginRsp = (LoginRsp) request.getSession().getAttribute("userinfo");
		
		
		if(StringUtil.nullOrBlank(keyword)){
			model.addAttribute("message", "搜索商品时未输入搜索关键字");
			return "comm/fail";
		}
		
		
		MdseElement obj = mallService.findLocalMdseEntityByName(keyword.trim());
		
		if(obj == null){
			model.addAttribute("message", "无法找到对映的商品,请确认商品名称是否正确");
			return "comm/fail";
		}
		
		List<MdseElement> mdses = mallService.findChildList(obj.getMdseCode());
		
		List<MdseElement> rsList = new ArrayList<MdseElement>();
		
		if(obj.getClassFlg() != null && "0".equalsIgnoreCase(obj.getClassFlg()))
		{
			for(TxComm t :loginRsp.getTxComms())
			{
				if(t.getBsDirect().equalsIgnoreCase("B"))
				{
					for(MdseElement e: mdses)
					{
						if(e.getMdseCode().equalsIgnoreCase(t.getClassCode())){
							
								rsList.add(e);
								break;

						}
					}
				}
			}
		}else{
			rsList = mdses;
		}
		
		if(rsList == null || rsList.size() == 0 && "3".equalsIgnoreCase(obj.getClassFlg()))
		{
			rsList.add(obj);
		}
		List<MdseElement> nodes = mallService.findNodeTreePath(obj.getMdseCode());
		
		
		model.addAttribute("keywork", keyword);
		
		model.addAttribute("nodepath", nodes);
		
		model.addAttribute("curcls", obj);
		
		model.addAttribute("subcls", rsList);	
		
		return "mall/sellist";
	}

	
}
