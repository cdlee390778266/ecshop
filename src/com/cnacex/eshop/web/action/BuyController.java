package com.cnacex.eshop.web.action;

import java.util.ArrayList;
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

import com.cnacex.comm.util.StringUtil;
import com.cnacex.eshop.modul.JSonComm;
import com.cnacex.eshop.msg.CommRspMsg;
import com.cnacex.eshop.msg.body.comm.CommProp;
import com.cnacex.eshop.msg.body.comm.Prop;
import com.cnacex.eshop.msg.body.fee.FeeReq;
import com.cnacex.eshop.msg.body.fee.FeeReq.ReqMemList;
import com.cnacex.eshop.msg.body.fee.FeeReq.ReqMemList.ReqFeeList;
import com.cnacex.eshop.msg.body.fee.FeeRsp.RspMemList;
import com.cnacex.eshop.msg.body.fee.FeeRsp.RspMemList.RspFeeList;
import com.cnacex.eshop.msg.body.mall.ListedDetailReq;
import com.cnacex.eshop.msg.body.mall.ListedDetailRsp;
import com.cnacex.eshop.msg.body.mall.MdseElement;
import com.cnacex.eshop.msg.body.member.LoginRsp;
import com.cnacex.eshop.msg.body.trade.buy.ApplyReq;
import com.cnacex.eshop.msg.body.trade.buy.ApplyRsp;
import com.cnacex.eshop.msg.body.trade.buy.AuditReq;
import com.cnacex.eshop.msg.body.trade.buy.AuditRsp;
import com.cnacex.eshop.msg.body.trade.buy.BuyBillReq;
import com.cnacex.eshop.msg.body.trade.buy.BuyOrderDetailRsp;
import com.cnacex.eshop.msg.body.trade.buy.BuyPayReq;
import com.cnacex.eshop.msg.body.trade.buy.BuyPayRsp;
import com.cnacex.eshop.msg.body.trade.buy.CancelReq;
import com.cnacex.eshop.msg.body.trade.buy.Delist;
import com.cnacex.eshop.msg.body.trade.buy.EditReq;
import com.cnacex.eshop.msg.body.trade.buy.WRApplyReq;
import com.cnacex.eshop.msg.body.trade.buy.WRApplyRsp;
import com.cnacex.eshop.msg.body.trade.buy.WRAuditReq;
import com.cnacex.eshop.msg.body.trade.buy.WRAuditRsp;
import com.cnacex.eshop.msg.body.trade.buy.WRBuyOrderDetailReq;
import com.cnacex.eshop.msg.body.trade.buy.WRBuyOrderDetailRsp;
import com.cnacex.eshop.msg.body.trade.buy.WRBuyPayReq;
import com.cnacex.eshop.msg.body.trade.buy.WRBuyPayRsp;
import com.cnacex.eshop.msg.body.trade.buy.WRCancelReq;
import com.cnacex.eshop.msg.body.trade.delivery.OrderDetailReq;
import com.cnacex.eshop.msg.xml.fee.FeeRspMsg;
import com.cnacex.eshop.msg.xml.fund.BalAmtRspMsg;
import com.cnacex.eshop.msg.xml.mall.ListedDetailRspMsg;
import com.cnacex.eshop.msg.xml.trade.buy.ApplyRspMsg;
import com.cnacex.eshop.msg.xml.trade.buy.AuditRspMsg;
import com.cnacex.eshop.msg.xml.trade.buy.BuyBillRspMsg;
import com.cnacex.eshop.msg.xml.trade.buy.BuyOrderDetailRspMsg;
import com.cnacex.eshop.msg.xml.trade.buy.BuyPayRspMsg;
import com.cnacex.eshop.msg.xml.trade.buy.WRApplyRspMsg;
import com.cnacex.eshop.msg.xml.trade.buy.WRAuditRspMsg;
import com.cnacex.eshop.msg.xml.trade.buy.WRBuyOrderDetailRspMsg;
import com.cnacex.eshop.msg.xml.trade.buy.WRBuyPayRspMsg;
import com.cnacex.eshop.msg.xml.trade.delivery.OrderDetailRspMsg;
import com.cnacex.eshop.service.IBuyService;
import com.cnacex.eshop.service.IDeliveryService;
import com.cnacex.eshop.service.IFundService;
import com.cnacex.eshop.service.imp.MallServiceImp;
import com.cnacex.eshop.util.CostUtil;
import com.cnacex.eshop.util.ListedUtil;
import com.cnacex.eshop.util.MoneyUtil;
import com.cnacex.eshop.util.StatusUtil;

/**
 * @author kereny
 *
 */
@Controller
@RequestMapping(value = "/buy")
public class BuyController extends TradeController {

	@Autowired
	private IBuyService buyService;

	@Autowired
	private IDeliveryService deliveryService;

	@Autowired
	private IFundService fundService;

	static Logger logger = LoggerFactory.getLogger(BuyController.class);

	@RequestMapping(value = "/prepare/{listedNo}.htm")
	public String prepare(@PathVariable("listedNo") String listedNo, ModelMap model) {
		LoginRsp loginRsp = (LoginRsp) request.getSession().getAttribute("userinfo");

		// 信息会员不允许摘牌
		if ("888".equalsIgnoreCase(loginRsp.getMemLevel())) {
			model.addAttribute("message", "您为信息会员,不能进行摘牌");
			return "comm/fail";
		}

		String sysstatus = MallServiceImp.SYS_STATUS;
		if (!sysstatus.equalsIgnoreCase("100")) {
			model.addAttribute("message", "市场为非开市状态，无法进行摘牌");
			return "comm/fail";
		}

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
		rspBody.setClassName(mallService.findLocalMdseEntity(rspBody.getClassCode()).getMdseName()); // 设置品类名称
		MdseElement me = mallService.findLocalMdseEntity(rspBody.getCommCode());

		rspBody.setCommName(me.getMdseName()); // 设置商品名称
		rspBody.setUom(me.getUom()); // 设置单位
		rspBody.setMarkName(mallService.findLocalMdseEntity(rspBody.getMarkCode()).getMdseName()); // 设置市场名称
		rspBody.setListedTypeName(ListedUtil.getListedName(rspBody.getListedType())); // 设置挂牌方式名称

		List<Prop> props = rspBody.getProps();
		if (props != null) {
			List<CommProp> commprops = mallService.findCommProperty(rspBody.getCommCode());
			List<Prop> rsprops = new ArrayList<Prop>();
			if (commprops != null && commprops.size() > 0) {
				for (Prop p : props) {
					for (CommProp cp : commprops)
						if (Integer.parseInt(p.getPropIdx()) == Integer.parseInt(cp.getPropIdx())) {
							p.setPropName(cp.getPropName());
							break;
						}
					rsprops.add(p);
				}
				rspBody.setProps(rsprops);
			}
		}

		if (!checkRight(loginRsp.getOperRights(), "T", rspMsg.getBody().getClassCode())) {
			model.addAttribute("message", "您无摘牌权限,请联系会员管理员");
			return "comm/comm";
		}

		if (!checkTxComm(loginRsp.getTxComms(), rspMsg.getBody().getClassCode(), "B")) {
			model.addAttribute("message", "您无摘牌权限,请联系会员管理员");
			return "comm/fail";
		}

		// 查询可用资金返回
		BalAmtRspMsg balRspMsg = fundService.queryMemberAvalAmt(loginRsp.getmID(), null);
		if (balRspMsg.getHead() == null) {
			model.addAttribute("message", balRspMsg.getFault().getRspMsg());
			return "comm/fail";
		}

		if (balRspMsg.getHead().getSuccFlag() != 1) {
			model.addAttribute("message", balRspMsg.getHead().getRspMsg());
			return "comm/fail";
		}

		model.addAttribute("totalAmt", balRspMsg.getBody().getTotalABal());
		model.addAttribute("totalAmtUpper", MoneyUtil.toRMBUpper(balRspMsg.getBody().getTotalABal()));
		model.addAttribute("buyBody", rspBody);
		return "buy/prepare";
	}

	/**
	 * 摘牌修改
	 * 
	 * @author kereny
	 * @date 2015-8-8 下午5:12:35
	 * @param delistNo
	 * @param model
	 * @return String
	 *
	 */
	@RequestMapping(value = "/edit/{delistNo}.htm")
	public String editBuy(@PathVariable("delistNo") String delistNo, ModelMap model) {
		LoginRsp loginRsp = (LoginRsp) request.getSession().getAttribute("userinfo");

		BuyOrderDetailRspMsg rspMsg = buyService.findBuyDetailByID(loginRsp.getmID(), loginRsp.getOperID(), delistNo);
		if (rspMsg.getHead() == null) {

			model.addAttribute("message", rspMsg.getFault().getRspMsg());
			return "comm/fail";
		}

		if (rspMsg.getHead().getSuccFlag() != 1) {
			model.addAttribute("message", rspMsg.getHead().getRspMsg());
			return "comm/fail";
		}

		if (!checkRight(loginRsp.getOperRights(), "T", rspMsg.getBody().getClassCode())) {
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

		BuyOrderDetailRsp buyRspBody = rspMsg.getBody();

		if (buyRspBody.getEffRec() != '1') {
			model.addAttribute("message", "摘牌订单无效,无法修改对映的摘牌订单");
			return "comm/fail";
		}

		if (!(buyRspBody.getStatus() == 0 || buyRspBody.getStatus() == -1 || buyRspBody.getStatus() == -2
				|| buyRspBody.getStatus() == -4)) {
			model.addAttribute("message", "所摘商品状态为" + StatusUtil.getBuyStatus(buyRspBody.getStatus()) + ",无法修改对映的摘牌订单");
			return "comm/fail";
		}

		ListedDetailReq listedDetailReq = new ListedDetailReq();

		listedDetailReq.setListedNo(rspMsg.getBody().getListedNo());
		listedDetailReq.setmID(loginRsp.getmID());

		listedDetailReq.setOperID(loginRsp.getOperID());

		ListedDetailRspMsg listedRspMsg = mallService.findDetailByID(listedDetailReq);

		if (listedRspMsg.getHead() == null) {

			model.addAttribute("message", listedRspMsg.getFault().getRspMsg());
			return "comm/fail";
		}

		if (listedRspMsg.getHead().getSuccFlag() != 1) {
			model.addAttribute("message", listedRspMsg.getHead().getRspMsg());
			return "comm/fail";
		}

		ListedDetailRsp rspBody = listedRspMsg.getBody();

		if (!"1".equalsIgnoreCase(rspBody.getEffRec())) {
			model.addAttribute("message", "挂牌订单无效,请确认所摘商品是否正常");
			return "comm/fail";
		}

		if (rspBody.getStatus() != 100) {
			model.addAttribute("message", "挂牌商品状态为" + StatusUtil.getSellStatus(rspBody.getStatus()) + ",无法修改对映的摘牌订单");
			return "comm/fail";
		}

		rspBody.setClassName(mallService.findLocalMdseEntity(rspBody.getClassCode()).getMdseName());

		MdseElement me = mallService.findLocalMdseEntity(rspBody.getCommCode());

		if (me != null) {
			rspBody.setCommName(me.getMdseName());

			rspBody.setUom(me.getUom());
		}

		rspBody.setMarkName(mallService.findLocalMdseEntity(rspBody.getMarkCode()).getMdseName());

		rspBody.setListedTypeName(ListedUtil.getListedName(rspBody.getListedType()));

		List<Prop> props = rspBody.getProps();

		if (props != null) {
			List<CommProp> commprops = mallService.findCommProperty(rspBody.getCommCode());
			List<Prop> rsprops = new ArrayList<Prop>();

			if (commprops != null && commprops.size() > 0) {
				for (Prop p : props) {
					for (CommProp cp : commprops)
						if (Integer.parseInt(p.getPropIdx()) == Integer.parseInt(cp.getPropIdx())) {
							p.setPropName(cp.getPropName());
							break;
						}
					rsprops.add(p);
				}
				rspBody.setProps(rsprops);
			}
		}

		// 查询可用资金返回
		if (!checkRight(loginRsp.getOperRights(), "T", rspMsg.getBody().getClassCode())) {
			model.addAttribute("message", "您无摘牌权限,请联系会员管理员");
			return "comm/fail";
		}

		if (!checkRight(loginRsp.getOperRights(), "T", rspMsg.getBody().getClassCode())) {
			model.addAttribute("message", "您无摘牌权限,请联系会员管理员");
			return "comm/comm";
		}

		if (!checkTxComm(loginRsp.getTxComms(), rspMsg.getBody().getClassCode(), "B")) {
			model.addAttribute("message", "您无摘牌权限,请联系会员管理员");
			return "comm/fail";
		}

		// 查询可用资金返回

		BalAmtRspMsg balRspMsg = fundService.queryMemberAvalAmt(loginRsp.getmID(), null);
		if (balRspMsg.getHead() == null) {
			model.addAttribute("message", balRspMsg.getFault().getRspMsg());
			return "comm/fail";
		}

		if (balRspMsg.getHead().getSuccFlag() != 1) {
			model.addAttribute("message", balRspMsg.getHead().getRspMsg());
			return "comm/fail";
		}

		model.addAttribute("totalAmt", balRspMsg.getBody().getTotalABal());
		model.addAttribute("totalAmtUpper", MoneyUtil.toRMBUpper(balRspMsg.getBody().getTotalABal()));

		model.addAttribute("buyvol", rspMsg.getBody().getVol());
		model.addAttribute("buytop", rspMsg.getBody().getTop());
		model.addAttribute("buydelistNo", rspMsg.getBody().getDeListNo());

		model.addAttribute("buyBody", rspBody);
		return "buy/edit";
	}

	/**
	 * 修改摘牌
	 * 
	 * @author kereny
	 * @date 2015-6-17 上午9:34:32
	 * @param active
	 * @param listedNo
	 * @param request
	 * @param model
	 * @return String
	 *
	 */
	@RequestMapping(value = "/edithandle.htm")
	public String buyEdit(
			@RequestParam(value = "listedNo", required = false) String listedNo,
			@RequestParam(value = "delistNo", required = false) String delistNo,
			@RequestParam(value = "vol", required = false) String vol,
			@RequestParam(value = "top", required = false) String top,
			@RequestParam(value = "remark", required = false) String remark,
			HttpServletRequest request, ModelMap model) {

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

		// 判断源挂牌单
		if (!"1".equalsIgnoreCase(rspBody.getEffRec())) {
			model.addAttribute("message", "挂牌订单无效,请确认所摘商品是否正常");
			return "comm/fail";
		}

		if (rspBody.getStatus() != 100) {
			model.addAttribute("message", "挂牌商品状态" + StatusUtil.getSellStatus(rspBody.getStatus()) + ",无法修改对映的摘牌订单");
			return "comm/fail";
		}

		if (!checkRight(loginRsp.getOperRights(), "T", rspMsg.getBody().getClassCode())) {
			model.addAttribute("message", "您无摘牌权限,请联系会员管理员");
			return "comm/fail";
		}

		if (!checkRight(loginRsp.getOperRights(), "T", rspMsg.getBody().getClassCode())) {
			model.addAttribute("message", "您无摘牌权限,请联系会员管理员");
			return "comm/fail";
		}

		if (!checkTxComm(loginRsp.getTxComms(), rspMsg.getBody().getClassCode(), "B")) {
			model.addAttribute("message", "您无摘牌权限,请联系会员管理员");
			return "comm/fail";
		}

		BuyOrderDetailRspMsg buyRspMsg = buyService.findBuyDetailByID(loginRsp.getmID(), loginRsp.getOperID(),
				delistNo);
		if (buyRspMsg.getHead() == null) {

			model.addAttribute("message", buyRspMsg.getFault().getRspMsg());
			return "comm/fail";
		}

		if (buyRspMsg.getHead().getSuccFlag() != 1) {
			model.addAttribute("message", buyRspMsg.getHead().getRspMsg());
			return "comm/fail";
		}

		buyRspMsg.getBody().setStatusDesc(StatusUtil.getBuyStatus(buyRspMsg.getBody().getStatus()));

		BuyOrderDetailRsp buyRspBody = buyRspMsg.getBody();

		// 判断源摘牌单
		if (buyRspBody.getEffRec() != '1') {
			model.addAttribute("message", "摘牌订单无效,无法修改对映的摘牌订单");
			return "comm/fail";
		}

		if (!(buyRspBody.getStatus() == 0 || buyRspBody.getStatus() == -1 || buyRspBody.getStatus() == -2
				|| buyRspBody.getStatus() == -4)) {
			model.addAttribute("message", "所摘商品状态" + StatusUtil.getBuyStatus(buyRspBody.getStatus()) + ",无法修改对映的摘牌订单");
			return "comm/fail";
		}

		EditReq editReq = new EditReq();
		editReq.setListedNo(listedNo);
		editReq.setmID(loginRsp.getmID());
		editReq.setTxOperID(loginRsp.getOperID());
		editReq.setVol(Integer.parseInt(vol));
		editReq.setTop(top);
		editReq.setRemark(remark);
		editReq.setDelistNo(delistNo);

		ApplyRspMsg applyRspMsg = buyService.editBuy(editReq);

		if (applyRspMsg.getHead() == null) {
			model.addAttribute("message", applyRspMsg.getFault().getRspMsg());
			return "comm/fail";
		}

		if (applyRspMsg.getHead().getSuccFlag() != 1) {
			model.addAttribute("message", applyRspMsg.getHead().getRspMsg());
			return "comm/fail";
		}
		ApplyRsp arspBody = applyRspMsg.getBody();
		arspBody.setCostPays(supplyCostName(arspBody.getCostPays()));

		arspBody.setStatusDesc(StatusUtil.getBuyStatus(arspBody.getStatus()));

		logger.debug("处理状态 {}", arspBody.getStatusDesc());

		model.addAttribute("rspBody", arspBody);

		if (checkRight(loginRsp.getOperRights(), "A", rspBody.getClassCode())) {
			model.addAttribute("enableAudit", 1);
		} else {
			model.addAttribute("enableAudit", 0);
		}

		// 进入业务处理页
		return "buy/buysucc";
	}

	/**
	 * 买方摘牌
	 * 
	 * @author kereny
	 * @date 2015-6-17 上午9:34:32
	 * 
	 * @param active
	 * @param listedNo
	 *            所摘挂牌编号
	 * @param vol
	 *            购买数量
	 * @param top
	 *            付款方式
	 * @param request
	 * @param model
	 * 
	 * @return String
	 *
	 */
	@RequestMapping(value = "/apply.htm")
	public String buyApply(
			@RequestParam(value = "active", required = false) String active,
			@RequestParam(value = "listedNo", required = false) String listedNo,
			@RequestParam(value = "vol", required = false) String vol,
			@RequestParam(value = "top", required = false) String top,
			@RequestParam(value = "remark", required = false) String remark,
			HttpServletRequest request, ModelMap model) {

		logger.debug("进入摘牌处理 {}", active);
		LoginRsp loginRsp = (LoginRsp) request.getSession().getAttribute("userinfo");
		String input = "";

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

		// 判断源挂牌单
		if (!"1".equalsIgnoreCase(rspBody.getEffRec())) {
			model.addAttribute("message", "挂牌订单无效,请确认所摘商品是否正常");
			return "comm/fail";
		}

		if (rspBody.getStatus() != 100) {
			model.addAttribute("message", "挂牌商品状态" + StatusUtil.getSellStatus(rspBody.getStatus()) + ",无法生成摘牌订单");
			return "comm/fail";
		}

		rspBody.setClassName(mallService.findLocalMdseEntity(rspBody.getClassCode()).getMdseName());
		MdseElement me = mallService.findLocalMdseEntity(rspBody.getCommCode());
		rspBody.setCommName(me.getMdseName());
		rspBody.setUom(me.getUom());
		rspBody.setMarkName(mallService.findLocalMdseEntity(rspBody.getMarkCode()).getMdseName());
		rspBody.setListedTypeName(ListedUtil.getListedName(rspBody.getListedType()));
		List<Prop> props = rspBody.getProps();
		if (props != null) {
			List<CommProp> commprops = mallService.findCommProperty(rspBody.getCommCode());
			if (commprops != null && commprops.size() > 0) {
				List<Prop> rsprops = new ArrayList<Prop>();
				for (Prop p : props) {
					for (CommProp cp : commprops)
						if (Integer.parseInt(p.getPropIdx()) == Integer.parseInt(cp.getPropIdx())) {
							p.setPropName(cp.getPropName());
							break;
						}
					rsprops.add(p);
				}
				rspBody.setProps(rsprops);
			}
		}

		// 查询可用资金返回
		if (!checkRight(loginRsp.getOperRights(), "T", rspMsg.getBody().getClassCode())) {
			model.addAttribute("message", "您无摘牌权限,请联系会员管理员");
			return "comm/fail";
		}

		if (!checkRight(loginRsp.getOperRights(), "T", rspMsg.getBody().getClassCode())) {
			model.addAttribute("message", "您无摘牌权限,请联系会员管理员");
			return "comm/fail";
		}

		if (!checkTxComm(loginRsp.getTxComms(), rspMsg.getBody().getClassCode(), "B")) {
			model.addAttribute("message", "您无摘牌权限,请联系会员管理员");
			return "comm/fail";
		}

		// 保证金
		if ("M".equalsIgnoreCase(rspBody.getListedType())) {
			ApplyReq apply = new ApplyReq();
			apply.setListedNO(listedNo);
			apply.setmID(loginRsp.getmID());
			apply.setTxOperID(loginRsp.getOperID());
			apply.setVol(Integer.parseInt(vol));
			apply.setTop(top);
			apply.setRemark(remark);
			ApplyRspMsg applyRspMsg = buyService.applyBuy(apply); // 摘牌申请

			if (applyRspMsg.getHead() == null) {
				model.addAttribute("message", applyRspMsg.getFault().getRspMsg());
				return "comm/fail";
			}

			if (applyRspMsg.getHead().getSuccFlag() != 1) {
				model.addAttribute("message", applyRspMsg.getHead().getRspMsg());
				return "comm/fail";
			}
			ApplyRsp arspBody = applyRspMsg.getBody();
			arspBody.setCostPays(supplyCostName(arspBody.getCostPays()));
			arspBody.setStatusDesc(StatusUtil.getBuyStatus(arspBody.getStatus()));
			logger.debug("处理状态 {}", arspBody.getStatusDesc());
			model.addAttribute("rspBody", arspBody);
			input = "buy/buysucc";

		} else if ("W".equalsIgnoreCase(rspBody.getListedType())) { // 仓单
			WRApplyReq wrApply = new WRApplyReq();
			wrApply.setListedNo(listedNo);
			wrApply.setMid(loginRsp.getmID());
			wrApply.setRemark(remark);
			wrApply.setTop(top);
			WRApplyRspMsg wrApplyRspMsg = buyService.applyWRBuy(wrApply); // 仓单摘牌申请
			WRApplyRsp wrApplyRspBody = wrApplyRspMsg.getBody();
			wrApplyRspBody.setCostPays(supplyCostName(wrApplyRspBody.getCostPays()));

			if (wrApplyRspMsg.getHead() == null) {
				model.addAttribute("message", wrApplyRspMsg.getFault().getRspMsg());
				return "comm/fail";
			}

			if (wrApplyRspMsg.getHead().getSuccFlag() != 1) {
				model.addAttribute("message", wrApplyRspMsg.getHead().getRspMsg());
				return "comm/fail";
			}
			
			model.addAttribute("rspBody", wrApplyRspBody);
			input = "buy/wrbuysucc";
		}

		if (checkRight(loginRsp.getOperRights(), "A", rspBody.getClassCode())) {
			model.addAttribute("enableAudit", 1);
		} else {
			model.addAttribute("enableAudit", 0);
		}

		// 进入业务处理页
		return input;
	}

	/**
	 * 摘牌审核处理
	 * 
	 * @author kereny
	 * @date 2015-6-17 上午10:38:07
	 * @param auditReq
	 * @param request
	 * @param model
	 * @return String
	 *
	 */
	@RequestMapping(value = "/audit.htm")
	public String buyAudit(
			@ModelAttribute AuditReq auditReq,
			@RequestParam(value = "opertype", required = false) String operType, 
			HttpServletRequest request,
			ModelMap model) {

		logger.debug("进入摘牌审核处理 {}", auditReq.toString());

		LoginRsp loginRsp = (LoginRsp) request.getSession().getAttribute("userinfo");

		BuyOrderDetailRspMsg rspMsg = buyService.findBuyDetailByID(loginRsp.getmID(), loginRsp.getOperID(),
				auditReq.getDelistNo());
		if (rspMsg.getHead() == null) {

			model.addAttribute("message", rspMsg.getFault().getRspMsg());
			return "comm/fail";
		}

		if (rspMsg.getHead().getSuccFlag() != 1) {
			model.addAttribute("message", rspMsg.getHead().getRspMsg());
			return "comm/fail";
		}

		if (!checkRight(loginRsp.getOperRights(), "A", rspMsg.getBody().getClassCode())) {
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

		auditReq.setmID(loginRsp.getmID());
		auditReq.setAuOperID(loginRsp.getOperID());
		// 提交交易
		AuditRspMsg audRspMsg = buyService.auditBuy(auditReq);

		if (audRspMsg.getHead() == null) {
			model.addAttribute("message", audRspMsg.getFault().getRspMsg());
			return "comm/fail";
		}

		if (audRspMsg.getHead().getSuccFlag() != 1) {
			model.addAttribute("message", audRspMsg.getHead().getRspMsg());
			return "comm/fail";
		}

		AuditRsp rspBody = audRspMsg.getBody();

		rspBody.setStatusDesc(StatusUtil.getBuyStatus(rspBody.getStatus()));

		if (checkRight(loginRsp.getOperRights(), "P", null) && rspBody.getStatus() == 1) {
			model.addAttribute("enablePay", 1);
		} else {
			model.addAttribute("enablePay", 0);
		}

		model.addAttribute("rspBody", rspBody);

		return "buy/auditsucc";

	}

	/**
	 * 摘牌审核处理
	 * 
	 * @author kereny
	 * @date 2015-6-17 上午10:38:07
	 * @param auditReq
	 * @param request
	 * @param model
	 * @return String
	 *
	 */
	@RequestMapping(value = "/wr/audit.htm")
	public String wrBuyAudit(
			@ModelAttribute WRAuditReq wrAuditReq,
			@RequestParam(value = "opertype", required = false) String operType, 
			HttpServletRequest request,
			ModelMap model) {

		logger.debug("进入摘牌审核处理 {}", wrAuditReq.toString());

		LoginRsp loginRsp = (LoginRsp) request.getSession().getAttribute("userinfo");

		WRBuyOrderDetailReq wrBuyOrderDetailReq = new WRBuyOrderDetailReq();
		wrBuyOrderDetailReq.setDelistNo(wrAuditReq.getDelistNo());
		wrBuyOrderDetailReq.setMid(loginRsp.getmID());

		WRBuyOrderDetailRspMsg rspMsg = buyService.findWRBuyOrderDetail(wrBuyOrderDetailReq);
		if (rspMsg.getHead() == null) {
			model.addAttribute("message", rspMsg.getFault().getRspMsg());
			return "comm/fail";
		}

		if (rspMsg.getHead().getSuccFlag() != 1) {
			model.addAttribute("message", rspMsg.getHead().getRspMsg());
			return "comm/fail";
		}

		if (!checkRight(loginRsp.getOperRights(), "A", rspMsg.getBody().getClassCode())) {
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

		wrAuditReq.setMid(loginRsp.getmID());
		wrAuditReq.setAuoperId(loginRsp.getOperID());
		// 提交交易
		WRAuditRspMsg audRspMsg = buyService.auditWRBuy(wrAuditReq);

		if (audRspMsg.getHead() == null) {
			model.addAttribute("message", audRspMsg.getFault().getRspMsg());
			return "comm/fail";
		}

		if (audRspMsg.getHead().getSuccFlag() != 1) {
			model.addAttribute("message", audRspMsg.getHead().getRspMsg());
			return "comm/fail";
		}

		WRAuditRsp rspBody = audRspMsg.getBody();
		rspBody.setStatusDesc(StatusUtil.getBuyStatus(rspBody.getStatus()));
		if (checkRight(loginRsp.getOperRights(), "P", null) && rspBody.getStatus() == 1) {
			model.addAttribute("enablePay", 1);
		} else {
			model.addAttribute("enablePay", 0);
		}

		model.addAttribute("rspBody", rspBody);

		return "buy/wrauditsucc";

	}

	/**
	 * 未上架撤消申请
	 * 
	 * @author kereny
	 * @date 2015-12-10 下午3:23:16
	 * @param delistNo
	 * @return JSonComm
	 *
	 */
	@RequestMapping(value = "/applycancel.htm")
	public @ResponseBody JSonComm applyCancel(@RequestParam(value = "delistNo", required = true) String delistNo) {

		LoginRsp loginRsp = (LoginRsp) request.getSession().getAttribute("userinfo");

		BuyOrderDetailRspMsg rspMsg = buyService.findBuyDetailByID(loginRsp.getmID(), loginRsp.getOperID(), delistNo);

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

		if (rspMsg.getBody().getEffRec() != '1') {
			js.setSuccflag(-1);
			js.setMsg("订单为无效订单,请确认");
			return js;
		}

		if (loginRsp.getOperID().equalsIgnoreCase("0000")) {
			if ((rspMsg.getBody().getStatus() != 0 && rspMsg.getBody().getStatus() != 1)) {
				js.setSuccflag(-1);
				js.setMsg("订单状态为" + StatusUtil.getSellStatus(rspMsg.getBody().getStatus()) + ",无法撤消");
				return js;
			}
		} else {
			if (!rspMsg.getBody().getTxOper().equalsIgnoreCase(loginRsp.getOperID())) {
				js.setSuccflag(-1);
				js.setMsg("订单无法撤消,请确认是否为该单交易员");
				return js;
			}
			if ((rspMsg.getBody().getStatus() != 0)) {
				js.setSuccflag(-1);
				js.setMsg("订单状态为" + StatusUtil.getSellStatus(rspMsg.getBody().getStatus()) + ",无法撤消");
				return js;
			}
		}

		CancelReq cancelReq = new CancelReq();
		cancelReq.setmID(loginRsp.getmID());
		cancelReq.setOperID(loginRsp.getOperID());
		cancelReq.setDelistNo(delistNo);

		CommRspMsg rsRspMsg = buyService.applyCancel(cancelReq);
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
	 * 仓单摘牌撤销
	 * 
	 * @param delistNo
	 * @return JSonComm
	 *
	 */
	@RequestMapping(value = "/wr/applycancel.htm")
	public @ResponseBody JSonComm wrApplyCancel(@RequestParam(value = "delistNo", required = true) String delistNo) {

		LoginRsp loginRsp = (LoginRsp) request.getSession().getAttribute("userinfo");

		WRBuyOrderDetailReq detaillReq = new WRBuyOrderDetailReq();
		detaillReq.setDelistNo(delistNo);
		detaillReq.setMid(loginRsp.getmID());
		WRBuyOrderDetailRspMsg rspMsg = buyService.findWRBuyOrderDetail(detaillReq);

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

		if (rspMsg.getBody().getEffRec() != '1') {
			js.setSuccflag(-1);
			js.setMsg("订单为无效订单,请确认");
			return js;
		}

		if (loginRsp.getOperID().equalsIgnoreCase("0000")) {
			if ((rspMsg.getBody().getStatus() != 0 && rspMsg.getBody().getStatus() != 1)) {
				js.setSuccflag(-1);
				js.setMsg("订单状态为" + StatusUtil.getSellStatus(rspMsg.getBody().getStatus()) + ",无法撤消");
				return js;
			}
		} else {
			if ((rspMsg.getBody().getStatus() != 0)) {
				js.setSuccflag(-1);
				js.setMsg("订单状态为" + StatusUtil.getSellStatus(rspMsg.getBody().getStatus()) + ",无法撤消");
				return js;
			}
		}
		
		WRCancelReq cancelReq = new WRCancelReq();
		cancelReq.setDsNO(delistNo);
		cancelReq.setMid(loginRsp.getmID());

		CommRspMsg rsRspMsg = buyService.wrCancel(cancelReq);
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
	 * 删除申请
	 * 
	 * @author kereny
	 * @date 2015-12-10 下午3:23:16
	 * @param delistNo
	 * @return JSonComm
	 *
	 */
	@RequestMapping(value = "/applydel.htm")
	public @ResponseBody JSonComm applyDel(@RequestParam(value = "delistNo", required = true) String delistNo) {

		LoginRsp loginRsp = (LoginRsp) request.getSession().getAttribute("userinfo");

		BuyOrderDetailRspMsg rspMsg = buyService.findBuyDetailByID(loginRsp.getmID(), loginRsp.getOperID(), delistNo);

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

		if (rspMsg.getBody().getEffRec() != '1') {
			js.setSuccflag(-1);
			js.setMsg("订单为无效订单,请确认");
			return js;
		}

		if (loginRsp.getOperID().equalsIgnoreCase("0000")) {
			if ((rspMsg.getBody().getStatus() != -2 && rspMsg.getBody().getStatus() != -4)) {
				js.setSuccflag(-1);
				js.setMsg("订单状态为" + StatusUtil.getSellStatus(rspMsg.getBody().getStatus()) + ",无法撤消");
				return js;
			}
		} else {
			if (!rspMsg.getBody().getTxOper().equalsIgnoreCase(loginRsp.getOperID())) {
				js.setSuccflag(-1);
				js.setMsg("订单无法撤消,请确认是否为该单交易员");
				return js;
			}
			if (rspMsg.getBody().getStatus() != -2 && rspMsg.getBody().getStatus() != -4) {
				js.setSuccflag(-1);
				js.setMsg("订单状态为" + StatusUtil.getSellStatus(rspMsg.getBody().getStatus()) + ",无法撤消");
				return js;
			}
		}

		CancelReq cancelReq = new CancelReq();
		cancelReq.setmID(loginRsp.getmID());
		cancelReq.setOperID(loginRsp.getOperID());
		cancelReq.setDelistNo(delistNo);
		cancelReq.setFlag("1");

		CommRspMsg rsRspMsg = buyService.applyCancel(cancelReq);
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
	 * 支付处理
	 * 
	 * @author kereny
	 * @date 2015-6-17 下午2:58:49
	 * @param delistNo
	 * @param fundPwd
	 * @param request
	 * @param model
	 * @return BuyPayRsp
	 *
	 */
	@RequestMapping(value = "/pay.htm")
	public String buyPay(
			@RequestParam(value = "delistNo", required = true) String delistNo,
			@RequestParam(value = "fundPwd", required = true) String fundPwd, 
			HttpServletRequest request,
			ModelMap model) {

		logger.debug("进入摘牌支付处理 {} {}", delistNo, fundPwd);

		// 商品分类
		LoginRsp loginRsp = (LoginRsp) request.getSession().getAttribute("userinfo");

		if ("DB".equalsIgnoreCase(delistNo.substring(0, 2))) {
			BuyOrderDetailRspMsg rspMsg = buyService.findBuyDetailByID(loginRsp.getmID(), loginRsp.getOperID(),
					delistNo);

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
		} else {

			OrderDetailReq orderDetailReq = new OrderDetailReq();

			orderDetailReq.setmID(loginRsp.getmID());
			orderDetailReq.setOperID(loginRsp.getOperID());
			orderDetailReq.setStrikeNo(delistNo);

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
		}

		BuyPayReq buyPayReq = new BuyPayReq();

		buyPayReq.setmID(loginRsp.getmID());
		buyPayReq.setPyOperID(loginRsp.getOperID());
		buyPayReq.setDsNo(delistNo);
		buyPayReq.setFundPwd(fundPwd);
		// 提交交易
		BuyPayRspMsg payRspMsg = buyService.payBuy(buyPayReq);

		if (payRspMsg.getHead() == null) {
			model.addAttribute("message", payRspMsg.getFault().getRspMsg());
			return "comm/fail";
		}

		if (payRspMsg.getHead().getSuccFlag() != 1) {
			model.addAttribute("message", payRspMsg.getHead().getRspMsg());
			return "comm/fail";
		}

		BuyPayRsp rspBody = payRspMsg.getBody();
		rspBody.setCostPays(supplyCostName(rspBody.getCostPays()));

		rspBody.setStatusDesc(StatusUtil.getBuyStatus(rspBody.getStatus()));

		model.addAttribute("rspBody", rspBody);

		return "buy/paysucc";
	}

	/**
	 * 支付处理
	 * 
	 * @author kereny
	 * @date 2015-6-17 下午2:58:49
	 * @param delistNo
	 * @param fundPwd
	 * @param request
	 * @param model
	 * @return BuyPayRsp
	 *
	 */
	@RequestMapping(value = "/wr/pay.htm")
	public String wrBuyPay(
			@RequestParam(value = "delistNo", required = true) String delistNo,
			@RequestParam(value = "fundPwd", required = true) String fundPwd, 
			HttpServletRequest request,
			ModelMap model) {

		logger.debug("进入摘牌支付处理 {} {}", delistNo, fundPwd);

		// 商品分类
		LoginRsp loginRsp = (LoginRsp) request.getSession().getAttribute("userinfo");

		WRBuyOrderDetailReq wrDetailReq = new WRBuyOrderDetailReq();
		wrDetailReq.setDelistNo(delistNo);
		wrDetailReq.setMid(loginRsp.getmID());
		WRBuyOrderDetailRspMsg rspMsg = buyService.findWRBuyOrderDetail(wrDetailReq);

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

		WRBuyPayReq buyPayReq = new WRBuyPayReq();
		buyPayReq.setDsNo(delistNo);
		buyPayReq.setMid(loginRsp.getmID());
		buyPayReq.setFundPwd(fundPwd);

		// 提交交易
		WRBuyPayRspMsg payRspMsg = buyService.wrPayBuy(buyPayReq);

		if (payRspMsg.getHead() == null) {
			model.addAttribute("message", payRspMsg.getFault().getRspMsg());
			return "comm/fail";
		}

		if (payRspMsg.getHead().getSuccFlag() != 1) {
			model.addAttribute("message", payRspMsg.getHead().getRspMsg());
			return "comm/fail";
		}

		WRBuyPayRsp rspBody = payRspMsg.getBody();
		rspBody.setCostPays(supplyCostName(rspBody.getCostPays()));

		rspBody.setStatusDesc(StatusUtil.getBuyStatus(rspBody.getStatus()));

		model.addAttribute("rspBody", rspBody);

		return "buy/wrpaysucc";
	}

	/**
	 * 对摘牌未完成交易的列表
	 * 
	 * @author kereny
	 * @date 2015-6-17 下午2:59:15
	 * @param request
	 * @param status
	 * @param start
	 * @param num
	 * @param model
	 * @return String
	 *
	 */
	@RequestMapping(value = "/list.htm")
	public String list(
			HttpServletRequest request, 
			@RequestParam(value = "status", required = false) String status,
			@RequestParam(value = "pageNum", required = false, defaultValue = "0") int pageNum,
			@RequestParam(value = "pageSize", required = false, defaultValue = "0") int pageSize, 
			ModelMap model) {

		LoginRsp loginRsp = (LoginRsp) request.getSession().getAttribute("userinfo");

		getUrlMatch(loginRsp.getTradeMenus(), model);
		return "buy/list";

	}

	/**
	 * 对摘牌未完成交易的列表(可能暂时不用)
	 * 
	 * @author kereny
	 * @date 2015-6-17 下午2:59:15
	 * @param request
	 * @param status
	 * @param start
	 * @param num
	 * @param model
	 * @return JSComm
	 *
	 */
	@RequestMapping(value = "/findlist.htm")
	public @ResponseBody JSonComm findlist(
			HttpServletRequest request,
			@RequestParam(value = "code", required = false) String code,
			@RequestParam(value = "status", required = false) String status,
			@RequestParam(value = "pageNum", required = false, defaultValue = "0") int pageNum,
			@RequestParam(value = "pageSize", required = false, defaultValue = "20") int pageSize, 
			ModelMap model) {

		LoginRsp loginRsp = (LoginRsp) request.getSession().getAttribute("userinfo");

		BuyBillReq buyBillReq = new BuyBillReq();
		if (!StringUtil.nullOrBlank(status))
			buyBillReq.setStatus(status);

		// 不分页,查所有
		if (pageSize > 0) {
			buyBillReq.setReqNum(Integer.toString(pageSize));
			buyBillReq.setReqStart(Integer.toString(pageSize * pageNum + 1));
		}

		buyBillReq.setmID(loginRsp.getmID());
		buyBillReq.setOperID(loginRsp.getOperID());
		buyBillReq.setCommCode(code);

		logger.debug("JSON查询摘牌清单 {}", buyBillReq.toString());

		BuyBillRspMsg rspMsg = buyService.findBuyList(buyBillReq);

		JSonComm jsdata = new JSonComm();
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

		if (rspMsg.getBody().getDelists() == null || rspMsg.getBody().getDelists().size() == 0) {

			jsdata.setSuccflag(-2);
			jsdata.setMsg("无数据列表");
			return jsdata;
		}

		List<Delist> delists = rspMsg.getBody().getDelists();
		List<Delist> rsdelists = new ArrayList<Delist>();
		if (delists != null && delists.size() > 0) {
			for (Delist d : delists) {
				d.setCommName(mallService.findLocalMdseEntity(d.getCommCode()).getMdseName());
				d.setStatusDesc(StatusUtil.getBuyStatus(d.getStatus()));
				d.setEffRecDesc(ListedUtil.getEffRec(d.getEffRec()));
				d.setListedTypeName(ListedUtil.getListedName(d.getListedType()));
				rsdelists.add(d);
			}
			rspMsg.getBody().setDelists(rsdelists);
		}

		jsdata.setSuccflag(0);
		jsdata.setMsg(rspMsg.getHead().getRspMsg());
		jsdata.setData(rspMsg.getBody());

		return jsdata;
	}

	@RequestMapping(value = "/findbuys.htm")
	public @ResponseBody HashMap<String, Object> findbuys(
			HttpServletRequest request,
			@RequestParam(value = "code", required = false) String code,
			@RequestParam(value = "status", required = false) String status, 
			ModelMap model) {

		LoginRsp loginRsp = (LoginRsp) request.getSession().getAttribute("userinfo");

		BuyBillReq buyBillReq = new BuyBillReq();
		if (!StringUtil.nullOrBlank(status))
			buyBillReq.setStatus(status);

		HashMap<String, Object> maps = new HashMap<String, Object>();

		buyBillReq.setmID(loginRsp.getmID());
		buyBillReq.setOperID(loginRsp.getOperID());
		buyBillReq.setCommCode(code);

		logger.debug("摘牌清单查询 {}", buyBillReq.toString());

		BuyBillRspMsg rspMsg = buyService.findBuyList(buyBillReq);

		List<Delist> rsdelists = new ArrayList<Delist>();
		if (rspMsg.getHead() == null) {
			maps.put("data", rsdelists);
			return maps;

		}

		if (rspMsg.getHead().getSuccFlag() != 1) {
			maps.put("data", rsdelists);
			return maps;

		}

		if (rspMsg.getBody().getDelists() == null || rspMsg.getBody().getDelists().size() == 0) {

			maps.put("data", rsdelists);
			return maps;
		}

		List<Delist> delists = rspMsg.getBody().getDelists();

		for (Delist d : delists) {

			MdseElement me = mallService.findLocalMdseEntity(d.getCommCode());
			if (me != null) {
				d.setCommName(me.getMdseName());
				d.setUom(me.getUom());
				d.setClsCode(me.getPmdseCode());

				MdseElement clsme = mallService.findLocalMdseEntity(me.getPmdseCode());
				if (clsme != null) {
					d.setClsName(clsme.getMdseName());
				}
			}

			if (!StringUtil.nullOrBlank(d.getSummary1()))
				d.setSummary1(d.getSummary1().substring(d.getSummary1().indexOf(":") + 1));

			if (!StringUtil.nullOrBlank(d.getSummary2()))
				d.setSummary2(d.getSummary2().substring(d.getSummary2().indexOf(":") + 1));

			if (!StringUtil.nullOrBlank(d.getSummary3()))
				d.setSummary3(d.getSummary3().substring(d.getSummary3().indexOf(":") + 1));

			if (!StringUtil.nullOrBlank(d.getSummary4()))
				d.setSummary4(d.getSummary4().substring(d.getSummary4().indexOf(":") + 1));

			// 审核状态
			String statusDesc = StatusUtil.getBuyStatus(d.getStatus());
			if (d.getStatus() == -1 && d.getAuditComment() != null) {
				statusDesc += ",审核意见：" + d.getAuditComment();
			}

			d.setStatusDesc(statusDesc);
			d.setEffRecDesc(ListedUtil.getEffRec(d.getEffRec()));
			d.setListedTypeName(ListedUtil.getListedName(d.getListedType()));
			rsdelists.add(d);
		}
		maps.put("data", rsdelists);
		return maps;
	}

	/**
	 * 审核与支付查看详单
	 * 
	 * @param request
	 * @param delistNo
	 * @param operType
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/handle/{operType}/{delistNo}.htm")
	public String detail(
			HttpServletRequest request, 
			@PathVariable("delistNo") String delistNo,
			@PathVariable("operType") String operType, 
			ModelMap model) {

		LoginRsp loginRsp = (LoginRsp) request.getSession().getAttribute("userinfo");

		logger.debug("查询摘牌明细单 {}", delistNo, loginRsp.getmID(), loginRsp.getOperID());

		BuyOrderDetailRspMsg rspMsg = buyService.findBuyDetailByID(loginRsp.getmID(), loginRsp.getOperID(), delistNo);

		if (rspMsg.getHead() == null) {
			model.addAttribute("message", rspMsg.getFault().getRspMsg());
			return "comm/fail";
		}

		if (rspMsg.getHead().getSuccFlag() != 1) {
			model.addAttribute("message", rspMsg.getHead().getRspMsg());
			return "comm/fail";
		}

		if (!checkRight(loginRsp.getOperRights(), operType, rspMsg.getBody().getClassCode())) {
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

		BuyOrderDetailRsp rspBody = rspMsg.getBody();

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

		model.addAttribute("rspBody", rspBody);

		model.addAttribute("operType", operType);

		if ("A".equalsIgnoreCase(operType)) {
			model.addAttribute("link", "audit");
		} else if ("P".equalsIgnoreCase(operType)) {
			model.addAttribute("link", "pay");
		} else {
			logger.warn("未知类型{}", operType);
			model.addAttribute("link", "nothing");
		}

		return "buy/detail";
	}

	/**
	 * 仓单审核与支付查看详单
	 * 
	 * @param request
	 * @param delistNo
	 * @param operType
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/handle/wr/{operType}/{delistNo}.htm")
	public String wrDetail(
			HttpServletRequest request, 
			@PathVariable("delistNo") String delistNo,
			@PathVariable("operType") String operType, 
			ModelMap model) {

		LoginRsp loginRsp = (LoginRsp) request.getSession().getAttribute("userinfo");

		logger.debug("查询摘牌明细单 {}", delistNo, loginRsp.getmID(), loginRsp.getOperID());

		WRBuyOrderDetailReq wrApplyReq = new WRBuyOrderDetailReq();
		wrApplyReq.setDelistNo(delistNo);
		wrApplyReq.setMid(loginRsp.getmID());
		WRBuyOrderDetailRspMsg rspMsg = buyService.findWRBuyOrderDetail(wrApplyReq);

		if (rspMsg.getHead() == null) {
			model.addAttribute("message", rspMsg.getFault().getRspMsg());
			return "comm/fail";
		}

		if (rspMsg.getHead().getSuccFlag() != 1) {
			model.addAttribute("message", rspMsg.getHead().getRspMsg());
			return "comm/fail";
		}

		if (!checkRight(loginRsp.getOperRights(), operType, rspMsg.getBody().getClassCode())) {
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

		WRBuyOrderDetailRsp rspBody = rspMsg.getBody();

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

		model.addAttribute("rspBody", rspBody);

		model.addAttribute("operType", operType);

		if ("A".equalsIgnoreCase(operType)) {
			model.addAttribute("link", "audit");
		} else if ("P".equalsIgnoreCase(operType)) {
			model.addAttribute("link", "pay");
		} else {
			logger.warn("未知类型{}", operType);
			model.addAttribute("link", "nothing");
		}

		return "buy/wrdetail";
	}

	/**
	 * 对摘牌未完成交易的列表(可能暂时不用)
	 * 
	 * @author kereny
	 * @date 2015-6-17 下午2:59:15
	 * @param request
	 * @param status
	 * @param start
	 * @param num
	 * @param model
	 * @return JSComm
	 *
	 */
	@RequestMapping(value = "/findfee.htm")
	public @ResponseBody JSonComm findlist(
			HttpServletRequest request,
			@RequestParam(value = "code", required = false) String code,
			@RequestParam(value = "pog", required = false) String pog,
			@RequestParam(value = "top", required = false) String top) {

		LoginRsp loginRsp = (LoginRsp) request.getSession().getAttribute("userinfo");

		MdseElement m = mallService.findLocalMdseEntity(code);

		FeeReq bondReq = new FeeReq();
		List<ReqMemList> bondlists = new ArrayList<ReqMemList>();
		ReqMemList bond = new ReqMemList();
		bond.setmID(loginRsp.getmID());
		bond.setMemLevel(loginRsp.getMemLevel());
		bond.setMarkCode(m.getMarkCode());
		bond.setPog(pog);

		List<ReqFeeList> bondfees = new ArrayList<ReqFeeList>();
		ReqFeeList fee = new ReqFeeList();
		fee.setCostCode("8201");
		bondfees.add(fee);

		if (top.equalsIgnoreCase("F")) {
			fee = new ReqFeeList();
			fee.setCostCode("8203");
			bondfees.add(fee);
		}
		bond.setFeelists(bondfees);
		bondlists.add(bond);
		bondReq.setMemLists(bondlists);

		FeeReq chargeReq = new FeeReq();
		List<ReqMemList> chargelists = new ArrayList<ReqMemList>();

		ReqMemList charge = new ReqMemList();
		charge.setmID(loginRsp.getmID());
		charge.setMemLevel(loginRsp.getMemLevel());
		charge.setMarkCode(m.getMarkCode());
		charge.setPog(pog);

		List<ReqFeeList> chargefees = new ArrayList<ReqFeeList>();
		fee = new ReqFeeList();
		fee.setCostCode("8102");
		chargefees.add(fee);

		charge.setFeelists(chargefees);
		chargelists.add(charge);
		chargeReq.setMemLists(chargelists);

		FeeRspMsg bondRspMsg = feeService.findFeeList(bondReq, "1");

		FeeRspMsg chargeRspMsg = feeService.findFeeList(chargeReq, "2");

		JSonComm jsdata = new JSonComm();
		if (bondRspMsg.getHead() == null || chargeRspMsg.getHead() == null) {
			jsdata.setSuccflag(-1);
			jsdata.setMsg(bondRspMsg.getFault().getRspMsg() + chargeRspMsg.getFault().getRspMsg());
			return jsdata;

		}

		if (bondRspMsg.getHead().getSuccFlag() != 1 || chargeRspMsg.getHead().getSuccFlag() != 1) {
			jsdata.setSuccflag(-2);
			jsdata.setMsg(bondRspMsg.getHead().getRspMsg() + bondRspMsg.getHead().getRspMsg());
			return jsdata;

		}

		List<RspMemList> bms = bondRspMsg.getBody().getMemLists();

		List<RspMemList> cms = chargeRspMsg.getBody().getMemLists();

		List<RspFeeList> bfs = null;

		List<RspFeeList> cfs = null;

		if (bms != null && bms.size() > 0) {

			for (RspMemList r : bms) {
				if (r.getmID().equalsIgnoreCase(loginRsp.getmID()) && r.getFeelists() != null
						&& r.getFeelists().size() > 0) {
					bfs = r.getFeelists();
				}
			}
		}

		if (cms != null && cms.size() > 0) {

			for (RspMemList r : cms) {
				if (r.getmID().equalsIgnoreCase(loginRsp.getmID()) && r.getFeelists() != null
						&& r.getFeelists().size() > 0) {
					cfs = r.getFeelists();
				}
			}
		}
		bfs.addAll(cfs);

		for (RspFeeList rf : bfs) {
			rf.setCostName(CostUtil.getValue(rf.getCostCode()));
		}

		jsdata.setSuccflag(0);
		jsdata.setMsg("success");
		jsdata.setData(bfs);

		return jsdata;
	}

}
