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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cnacex.comm.util.StringUtil;
import com.cnacex.eshop.modul.JSonComm;
import com.cnacex.eshop.msg.CommRspMsg;
import com.cnacex.eshop.msg.body.mall.MdseElement;
import com.cnacex.eshop.msg.body.member.LoginRsp;
import com.cnacex.eshop.msg.body.trade.delivery.AppealBillReq;
import com.cnacex.eshop.msg.body.trade.delivery.AppealBillRsp;
import com.cnacex.eshop.msg.body.trade.delivery.AppealReq;
import com.cnacex.eshop.msg.body.trade.delivery.BillReq;
import com.cnacex.eshop.msg.body.trade.delivery.BillRsp;
import com.cnacex.eshop.msg.body.trade.delivery.ConfirmReq;
import com.cnacex.eshop.msg.body.trade.delivery.ConfirmRsp;
import com.cnacex.eshop.msg.body.trade.delivery.OrderDetailReq;
import com.cnacex.eshop.msg.body.trade.delivery.OrderDetailRsp;
import com.cnacex.eshop.msg.body.trade.delivery.Strike;
import com.cnacex.eshop.msg.body.trade.delivery.WRAppealReq;
import com.cnacex.eshop.msg.xml.trade.delivery.AppealBillRspMsg;
import com.cnacex.eshop.msg.xml.trade.delivery.BillRspMsg;
import com.cnacex.eshop.msg.xml.trade.delivery.ConfirmRspMsg;
import com.cnacex.eshop.msg.xml.trade.delivery.OrderDetailRspMsg;
import com.cnacex.eshop.service.IDeliveryService;
import com.cnacex.eshop.util.ListedUtil;
import com.cnacex.eshop.util.StatusUtil;

/**
 * @author kereny
 *
 */

@Controller
@RequestMapping(value = "/delivery")
public class DeliveryController extends TradeController {

	@Autowired
	private IDeliveryService deliveryService;

	static Logger logger = LoggerFactory.getLogger(DeliveryController.class);

	/**
	 * 确认前端调用接口
	 * 
	 * @author kereny
	 * @date 2015-6-18 上午9:51:47
	 * @param type
	 * @param strikeNo
	 * @return JSonComm
	 *
	 */
	@RequestMapping(value = "/confirm.htm")
	public @ResponseBody JSonComm confirm(
			@RequestParam(value = "type", required = true) String type,
			@RequestParam(value = "strikeNo", required = false) String strikeNo,
			@RequestParam(value = "listedType", required = false) String listedType) {

		logger.debug("交收确认处理 {}{}", type, strikeNo);

		LoginRsp loginRsp = (LoginRsp) request.getSession().getAttribute("userinfo");

		ConfirmReq confirmReq = new ConfirmReq();
		confirmReq.setmID(loginRsp.getmID());
		confirmReq.setOperID(loginRsp.getOperID());
		confirmReq.setStrikeNo(strikeNo);
		ConfirmRspMsg rspMsg = deliveryService.confirmDelivery(confirmReq, type, listedType);

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

		ConfirmRsp rspBody = rspMsg.getBody();

		js.setSuccflag(0);
		js.setMsg(StatusUtil.getBuyStatus(rspBody.getStatus()));

		return js;

	}

	/**
	 * 前端交收卖主界面
	 * 
	 * @author kereny
	 * @date 2015-6-26 下午5:47:17
	 * @param type
	 * @param pageNum
	 * @param pageSize
	 * @return String
	 *
	 */
	@RequestMapping(value = "/selllist.htm")
	public String selllist(@RequestParam(value = "type", required = true, defaultValue = "S") String type,
			@RequestParam(value = "pageNum", required = false, defaultValue = "0") int pageNum,
			@RequestParam(value = "pageSize", required = false, defaultValue = "6") int pageSize, ModelMap model) {

		logger.debug("清单查询处理 {}{}", type);

		LoginRsp loginRsp = (LoginRsp) request.getSession().getAttribute("userinfo");

		getUrlMatch(loginRsp.getTradeMenus(), model);
		return "delivery/selllist";
	}

	/**
	 * 前端交收买主界面
	 * 
	 * @author kereny
	 * @date 2015-6-26 下午5:47:17
	 * @param type
	 * @param pageNum
	 * @param pageSize
	 * @return String
	 *
	 */
	@RequestMapping(value = "/buylist.htm")
	public String buylist(@RequestParam(value = "type", required = true, defaultValue = "B") String type,
			@RequestParam(value = "pageNum", required = false, defaultValue = "0") int pageNum,
			@RequestParam(value = "pageSize", required = false, defaultValue = "6") int pageSize, ModelMap model) {

		logger.debug("清单查询处理 {}{}", type);

		LoginRsp loginRsp = (LoginRsp) request.getSession().getAttribute("userinfo");

		getUrlMatch(loginRsp.getTradeMenus(), model);
		return "delivery/buylist";
	}

	/**
	 * JSon查询数据列表
	 * 
	 * @author kereny
	 * @date 2015-7-3 下午12:58:19
	 * @param type
	 * @param pageNum
	 * @param pageSize
	 * @param model
	 * @return JSonComm
	 *
	 */
	@RequestMapping(value = "/findlist.htm")
	public @ResponseBody JSonComm findList(@RequestParam(value = "type", required = true) String type,
			@RequestParam(value = "pageNum", required = false, defaultValue = "0") int pageNum,
			@RequestParam(value = "pageSize", required = false, defaultValue = "20") int pageSize,
			@RequestParam(value = "code", required = false) String code,
			@RequestParam(value = "status", required = false) String status,
			@RequestParam(value = "strikeNo", required = false) String strikeNo,
			@RequestParam(value = "lastPD", required = false) String lastPD, ModelMap model) {

		LoginRsp loginRsp = (LoginRsp) request.getSession().getAttribute("userinfo");

		BillReq billReq = new BillReq();
		if (pageSize > 0) {
			billReq.setReqNum(Integer.toString(pageSize));
			billReq.setReqStart(Integer.toString(pageSize * pageNum + 1));
		}
		billReq.setmID(loginRsp.getmID());
		billReq.setOperID(loginRsp.getOperID());

		billReq.setStatus(status);

		if (!StringUtil.nullOrBlank(code))
			billReq.setCommCode(code);

		if (!StringUtil.nullOrBlank(strikeNo))
			billReq.setStrikeNo(strikeNo);

		if ("B".equalsIgnoreCase(type) && !StringUtil.nullOrBlank(lastPD)) {
			billReq.setLastPD(lastPD);
		}

		BillRspMsg rspMsg = deliveryService.deliveryBillList(billReq, type);
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
		BillRsp rspBody = rspMsg.getBody();
		if (rspBody == null || rspBody.getTotalNum() == 0) {
			js.setSuccflag(-1);
			js.setMsg("无交收清单数据");
			return js;
		}

		List<Strike> strikes = rspBody.getStrikes();
		if (strikes != null && strikes.size() > 0) {
			List<Strike> rsstrikes = new ArrayList<Strike>();
			for (Strike s : strikes) {
				MdseElement me = mallService.findLocalMdseEntity(s.getCommCode());
				if (me != null) {
					s.setCommName(me.getMdseName());

					if (checkRight(loginRsp.getOperRights(), "T", me.getClassCode())
							&& checkTxComm(loginRsp.getTxComms(), me.getClassCode(), type)) {
						s.setEnableT(1);
					}

					if (checkRight(loginRsp.getOperRights(), "P", null)
							&& checkTxComm(loginRsp.getTxComms(), me.getClassCode(), type)) {
						s.setEnableP(1);
					}
				}
				s.setStatusDesc(StatusUtil.getBuyStatus(s.getStatus()));
				s.setEffRecDesc(ListedUtil.getEffRec(s.getEffRec()));
				s.setListedTypeName(ListedUtil.getListedName(s.getListedType()));

				double amt = s.getUp() * s.getVol();
				BigDecimal bigAmt = new BigDecimal(amt);
				s.setContAmt(bigAmt.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());

				rsstrikes.add(s);

			}
			rspBody.setStrikes(rsstrikes);

			if (pageSize > 0) {
				int nTotalNum = rspMsg.getBody().getTotalNum();

				if (nTotalNum % pageSize == 0) {
					rspMsg.getBody().setTotalPage(nTotalNum / pageSize);
				} else {
					rspMsg.getBody().setTotalPage(nTotalNum / pageSize + 1);
				}
			}
		}
		js.setSuccflag(0);
		js.setMsg("success");
		js.setData(rspBody);
		return js;
	}

	/**
	 * JSon查询数据列表
	 * 
	 * @author kereny
	 * @date 2015-7-3 下午12:58:19
	 * @param type
	 * @param pageNum
	 * @param pageSize
	 * @param model
	 * @return JSonComm
	 *
	 */
	@RequestMapping(value = "/finddelist.htm")
	public @ResponseBody HashMap<String, Object> findDeList(@RequestParam(value = "type", required = true) String type,
			@RequestParam(value = "code", required = false) String code,
			@RequestParam(value = "status", required = false) String status,
			@RequestParam(value = "strikeNo", required = false) String strikeNo,
			@RequestParam(value = "lastPD", required = false) String lastPD,
			@RequestParam(value = "elastPD", required = false) String elastPD,

			ModelMap model) {

		LoginRsp loginRsp = (LoginRsp) request.getSession().getAttribute("userinfo");

		BillReq billReq = new BillReq();
		billReq.setmID(loginRsp.getmID());
		billReq.setOperID(loginRsp.getOperID());

		billReq.setStatus(status);

		if (!StringUtil.nullOrBlank(code))
			billReq.setCommCode(code);

		if (!StringUtil.nullOrBlank(strikeNo))
			billReq.setStrikeNo(strikeNo);

		if ("B".equalsIgnoreCase(type)) {
			billReq.setLastPD(lastPD);
			billReq.setElastPD(elastPD);

		}

		BillRspMsg rspMsg = deliveryService.deliveryBillList(billReq, type);

		List<Strike> rsstrikes = new ArrayList<Strike>();

		HashMap<String, Object> maps = new HashMap<String, Object>();

		if (rspMsg.getHead() == null) {
			maps.put("data", rsstrikes);
			return maps;
		}

		if (rspMsg.getHead().getSuccFlag() != 1) {
			maps.put("data", rsstrikes);
			return maps;
		}
		BillRsp rspBody = rspMsg.getBody();
		if (rspBody == null || rspBody.getTotalNum() == 0) {
			maps.put("data", rsstrikes);
			return maps;
		}

		List<Strike> strikes = rspBody.getStrikes();
		if (strikes != null && strikes.size() > 0) {
			for (Strike s : strikes) {
				MdseElement me = mallService.findLocalMdseEntity(s.getCommCode());
				if (me != null) {
					s.setCommName(me.getMdseName());

					if (checkRight(loginRsp.getOperRights(), "T", me.getClassCode())
							&& checkTxComm(loginRsp.getTxComms(), me.getClassCode(), type)) {
						s.setEnableT(1);
					}

					if (checkRight(loginRsp.getOperRights(), "P", null)
							&& checkTxComm(loginRsp.getTxComms(), me.getClassCode(), type)) {
						s.setEnableP(1);
					}

					s.setUom(me.getUom());

					MdseElement clsme = mallService.findLocalMdseEntity(me.getPmdseCode());

					s.setClsCode(me.getPmdseCode());
					if (clsme != null) {
						s.setClsName(clsme.getMdseName());
					}
				}

				if (!StringUtil.nullOrBlank(s.getDelidate())) {
					int flag = s.getDelidate().indexOf(':');
					if (flag != -1) {
						s.setDelidate("全款支付后" + s.getDelidate().substring(flag + 1) + "天");
					}
				}

				if (!StringUtil.nullOrBlank(s.getSummary1()))
					s.setSummary1(s.getSummary1().substring(s.getSummary1().indexOf(":") + 1));

				if (!StringUtil.nullOrBlank(s.getSummary2()))
					s.setSummary2(s.getSummary2().substring(s.getSummary2().indexOf(":") + 1));

				if (!StringUtil.nullOrBlank(s.getSummary3()))
					s.setSummary3(s.getSummary3().substring(s.getSummary3().indexOf(":") + 1));

				if (!StringUtil.nullOrBlank(s.getSummary4()))
					s.setSummary4(s.getSummary4().substring(s.getSummary4().indexOf(":") + 1));

				s.setStatusDesc(StatusUtil.getBuyStatus(s.getStatus()));
				s.setEffRecDesc(ListedUtil.getEffRec(s.getEffRec()));
				s.setListedTypeName(ListedUtil.getListedName(s.getListedType()));

				double amt = s.getUp() * s.getVol();
				BigDecimal bigAmt = new BigDecimal(amt);
				s.setContAmt(bigAmt.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());

				rsstrikes.add(s);

			}
		}
		maps.put("data", rsstrikes);
		return maps;
	}

	/**
	 * 交易投诉
	 * 
	 * @author kereny
	 * @date 2015-7-3 下午12:58:40
	 * @param strikeNo
	 * @param TrdStatus
	 * @param reason
	 * @param model
	 * @return JSonComm
	 *
	 */
	@RequestMapping(value = "/appeal.htm")
	public @ResponseBody JSonComm appeal(@RequestParam(value = "strikeNo", required = true) String strikeNo,
			@RequestParam(value = "status", required = false) String trdStatus,
			@RequestParam(value = "reason", required = false) String reason, ModelMap model) {

		LoginRsp loginRsp = (LoginRsp) request.getSession().getAttribute("userinfo");

		OrderDetailReq orderDetailReq = new OrderDetailReq();

		orderDetailReq.setmID(loginRsp.getmID());
		orderDetailReq.setOperID(loginRsp.getOperID());
		orderDetailReq.setStrikeNo(strikeNo);

		logger.debug("查询摘牌明细单 {}", orderDetailReq.toString());

		OrderDetailRspMsg rspMsg = deliveryService.findDetaiByID(orderDetailReq);

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

		OrderDetailRsp rspBody = rspMsg.getBody();

		// 卖方投诉
		int status = 0;
		if (rspBody.getLsmID().equalsIgnoreCase(loginRsp.getmID())) {
			if (rspBody.getStatus() == 200) {

				js.setSuccflag(-1);
				js.setMsg("订单状态" + StatusUtil.getBuyStatus(rspBody.getStatus()) + "，无法进行投诉，请重新查询确认状态");
				return js;
			}
			status = -1000;
		} else { // 买方投诉
			if (rspBody.getStatus() == 100) {
				js.setSuccflag(-1);
				js.setMsg("订单状态" + StatusUtil.getBuyStatus(rspBody.getStatus()) + "，无法进行投诉，请重新查询确认状态");
				return js;
			}
		}
		
		CommRspMsg commRspMsg = null;
		
		if("M".equalsIgnoreCase(rspBody.getListedType())) {   //保证金
			AppealReq appealReq = new AppealReq();
			appealReq.setmID(loginRsp.getmID());
			appealReq.setOperID(loginRsp.getOperID());
			appealReq.setStrikeNo(strikeNo);

			status = status + ((-1) * rspBody.getStatus() - 100 - Integer.parseInt(trdStatus));
			appealReq.setTrdStatus(Integer.toString(status));

			if (!"0".equalsIgnoreCase(trdStatus)) {
				appealReq.setReason(reason);
			}

			commRspMsg = deliveryService.deliveryAppeal(appealReq);
			
		} else if("W".equalsIgnoreCase(rspBody.getListedType())) {   //仓单
			WRAppealReq appealReq = new WRAppealReq();
			appealReq.setmID(loginRsp.getmID());
			appealReq.setOperID(loginRsp.getOperID());
			appealReq.setStrikeNo(strikeNo);
			
			status = status + ((-1) * rspBody.getStatus() - 100 - Integer.parseInt(trdStatus));
			appealReq.setTrdStatus(Integer.toString(status));

			if (!"0".equalsIgnoreCase(trdStatus)) {
				appealReq.setReason(reason);
			}

			commRspMsg = deliveryService.wrDeliveryAppeal(appealReq);
		}
		
		if (commRspMsg.getHead() == null) {
			js.setSuccflag(-1);
			js.setMsg(commRspMsg.getFault().getRspMsg());
			return js;
		}

		if (commRspMsg.getHead().getSuccFlag() != 1) {
			js.setSuccflag(-1);
			js.setMsg(commRspMsg.getHead().getRspMsg());
			return js;
		}

		js.setSuccflag(0);
		js.setMsg("投诉提交成功,请等待受理");
		return js;

	}

	/**
	 * 前端交收买主界面
	 * 
	 * @author kereny
	 * @date 2015-6-26 下午5:47:17
	 * @param type
	 * @param pageNum
	 * @param pageSize
	 * @return String
	 *
	 */
	@RequestMapping(value = "/P/{strikeNo}.htm")
	public String deliverPay(@PathVariable("strikeNo") String strikeNo, ModelMap model) {

		LoginRsp loginRsp = (LoginRsp) request.getSession().getAttribute("userinfo");

		OrderDetailReq orderDetailReq = new OrderDetailReq();

		orderDetailReq.setmID(loginRsp.getmID());
		orderDetailReq.setOperID(loginRsp.getOperID());
		orderDetailReq.setStrikeNo(strikeNo);

		logger.debug("查询摘牌明细单 {}", orderDetailReq.toString());

		OrderDetailRspMsg rspMsg = deliveryService.findDetaiByID(orderDetailReq);

		if (rspMsg.getHead() == null) {
			model.addAttribute("message", rspMsg.getFault().getRspMsg());
			return "comm/fail";
		}

		if (rspMsg.getHead().getSuccFlag() != 1) {
			model.addAttribute("message", rspMsg.getHead().getRspMsg());
			return "comm/fail";
		}

		if (!checkRight(loginRsp.getOperRights(), "P", rspMsg.getBody().getClassCode())) {
			model.addAttribute("message", "您无此操作权限,请联系会员管理员");
			return "comm/fail";
		}

		if (!checkMarket(loginRsp.getMemMarkets(), rspMsg.getBody().getMarkCode())) {
			model.addAttribute("message", "您无此操作权限,请联系会员管理员");
			return "comm/fail";
		}

		if (!checkTxComm(loginRsp.getTxComms(), rspMsg.getBody().getClassCode(), "B")) {
			model.addAttribute("message", "您无此操作权限,请联系会员管理员");
			return "comm/fail";
		}

		OrderDetailRsp rspBody = rspMsg.getBody();
		rspBody.setMarkName(mallService.findLocalMdseEntity(rspBody.getMarkCode()).getMdseName());
		rspBody.setClassName(mallService.findLocalMdseEntity(rspBody.getClassCode()).getMdseName());

		MdseElement me = mallService.findLocalMdseEntity(rspBody.getCommCode());
		rspBody.setCommName(me.getMdseName());
		rspBody.setUom(me.getUom());
		rspBody.setStatusDesc(StatusUtil.getBuyStatus(rspBody.getStatus()));

		rspBody.setEffRecDesc(rspBody.getEffRec() == '0' ? "不生效" : "生效");

		rspBody.setCostPays(supplyCostName(rspBody.getCostPays()));

		rspBody.setProps(supplyIdxName(rspBody.getCommCode(), rspBody.getProps()));

		rspBody.setListedTypeName(ListedUtil.getListedName(rspBody.getListedType()));

		rspBody.setDeListNo(rspBody.getStrikeNo());

		model.addAttribute("rspBody", rspBody);

		model.addAttribute("operType", "P");

		model.addAttribute("link", "pay");

		return "buy/detail";
	}

	@RequestMapping(value = "/findappeal.htm")
	public @ResponseBody JSonComm findAppealContext(@RequestParam(value = "compno", required = true) String compno) {

		AppealBillReq appealBillReq = new AppealBillReq();

		appealBillReq.setCompNo(compno);

		AppealBillRspMsg rspMsg = deliveryService.findAppealContent(appealBillReq);

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

		AppealBillRsp rspBody = rspMsg.getBody();
		rspBody.setTrdStatusDesc(StatusUtil.getBuyStatus(rspBody.getTrdStatus()));

		js.setSuccflag(0);
		js.setData(rspBody);

		return js;

	}

	/**
	 * 发票确认
	 * @param type
	 * @param pageNum
	 * @param pageSize
	 * @return String
	 *
	 */
	@RequestMapping(value = "/invlist.htm")
	public String invlist(
			@RequestParam(value = "type", required = true, defaultValue = "B") String type,
			@RequestParam(value = "pageNum", required = false, defaultValue = "0") int pageNum,
			@RequestParam(value = "pageSize", required = false, defaultValue = "6") int pageSize, 
			ModelMap model) {

		logger.debug("清单查询处理 {}{}", type);

		LoginRsp loginRsp = (LoginRsp) request.getSession().getAttribute("userinfo");

		getUrlMatch(loginRsp.getTradeMenus(), model);
		return "delivery/invlist";
	}
}
