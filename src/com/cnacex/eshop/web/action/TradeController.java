package com.cnacex.eshop.web.action;



import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;

import com.cnacex.comm.ftp.UPFileClient;
import com.cnacex.comm.util.Config;
import com.cnacex.eshop.modul.MenuNode;
import com.cnacex.eshop.msg.body.comm.CommProp;
import com.cnacex.eshop.msg.body.comm.CostPay;
import com.cnacex.eshop.msg.body.comm.OperRight;
import com.cnacex.eshop.msg.body.comm.Prop;
import com.cnacex.eshop.msg.body.mall.ListedDetailRsp.DelistMem;
import com.cnacex.eshop.msg.body.member.LoginRsp.MemMarket;
import com.cnacex.eshop.msg.body.member.LoginRsp.TxComm;
import com.cnacex.eshop.service.IFeeService;
import com.cnacex.eshop.service.IMallService;
import com.cnacex.eshop.util.AcctTrUtil;
import com.cnacex.eshop.util.CostUtil;

/**
 * @author kereny
 *
 */

public class TradeController {
	

	static Logger logger = LoggerFactory.getLogger(TradeController.class);
	
	protected static final int PAGESIZE = Config.getIntValue("defaultPageSize", 20);
	
	@Autowired
	protected UPFileClient ftpClient;

	@Autowired
	protected HttpServletRequest request;
	
	@Autowired
	protected IMallService mallService;
	
	
	@Autowired
	protected IFeeService feeService;
	
	
	/**
	 * 补充对映的费用名称信息,默认返回的无名称
	 * 
	 * @author kereny
	 * @date 2015-6-16 下午1:04:24
	 * @param costPays
	 * @return List<CostPay>
	 * 
	 */
	protected List<CostPay> supplyCostName(List<CostPay> costPays) {
		
		if(costPays == null) return null;

		List<CostPay> costs = new ArrayList<CostPay>();

		for (CostPay cost : costPays) {

			cost.setFlagDesc(CostUtil.getFlagValue(Integer.toString(cost.getFlag())));
			
			cost.setTrTypeName(AcctTrUtil.getValue(cost.getTrType()));

			cost.setCostName(CostUtil.getValue(cost.getCostCode()));
			costs.add(cost);
		}
		return costs;

	}

	/**
	 * 检查用户权限
	 * 
	 * @author kereny
	 * @date 2015-6-16 下午12:27:51
	 * @param rights
	 * @param right
	 * @return boolean
	 * 
	 */

	protected boolean checkRight(List<OperRight> rights, String right, String code) {

		if (rights == null)
			return false;

		for (OperRight r : rights)
		{
			if(right.equalsIgnoreCase("P")&& r.getRightType().equalsIgnoreCase(right))
				return true;	
			else if (r.getRightType().equalsIgnoreCase(right)&& code.equalsIgnoreCase(r.getClassCode()))
				return true;
		}
		return false;

	}

	/**
	 * 检查市场权限
	 * 
	 * @author kereny
	 * @date 2015-6-16 下午12:27:51
	 * @param rights
	 * @param right
	 * @return boolean
	 * 
	 */

	protected boolean checkMarket(List<MemMarket> markets,  String code) {

		if (markets == null)
			return false;

		for (MemMarket m : markets)
			if (m.getMarkCode().equalsIgnoreCase(code))
				return true;
		return false;

	}
	
	
	/**
	 * 检查品种权限
	 * 
	 * @author kereny
	 * @date 2015-6-16 下午12:27:51
	 * @param rights
	 * @param right
	 * @return boolean
	 * 
	 */

	protected boolean checkTxComm(List<TxComm> comms,  String code, String bsType) {

		if (comms == null)
			return false;

		for (TxComm t : comms)
			if (t.getClassCode().equalsIgnoreCase(code) && bsType.equalsIgnoreCase(t.getBsDirect()))
				return true;
		return false;

	}
	
	
	/**
	 * 
	 * 按VIEWIDX排序
	 * 
	 * @author kereny
	 *
	 */
	class PropSortByView implements Comparator<Prop> {
		public int compare(Prop p1, Prop p2) {
			if (Integer.parseInt(p1.getViewIdx()) > Integer.parseInt(p2.getViewIdx()))
				return 1;
			else if (Integer.parseInt(p1.getViewIdx()) == Integer.parseInt(p2.getViewIdx())) {
				return 0;
			}
			return -1;
		}
	}
	
	
	class CommPropSortByView implements Comparator<CommProp> {
		public int compare(CommProp p1, CommProp p2) {
			if (Integer.parseInt(p1.getViewIdx()) > Integer.parseInt(p2.getViewIdx()))
				return 1;
			else if (Integer.parseInt(p1.getViewIdx()) == Integer.parseInt(p2.getViewIdx())) {
				return 0;
			}
			return -1;
		}
	}

	
	protected boolean checkIsDelistMem(List<DelistMem> delists, String mID){
		
		if(delists == null || delists.size() == 0) return false;
		
		for(DelistMem dm:delists){
			if(mID.equalsIgnoreCase(dm.getDelistMID().trim())) return true;
		}
		return false;
	}
	
		/**
	     *  商品属性补全有值名称
		 * @author kereny
		 * @date 2015-8-21 下午1:10:08
		 * @param code
		 * @param props
		 * @return
		 * List<Prop>
	     *
		 */
	protected List<Prop> supplyIdxName(String code,  List<Prop> props) {
		
		if(props == null) return null;
		
		List<CommProp> commProps = mallService.findCommProperty(code);
		
		if(commProps == null || commProps.size() == 0) return props;
		List<Prop> rsProps = new ArrayList<Prop>();
		for(Prop p:props)
		{
			for(CommProp commp: commProps)
			{
				if(p.getPropIdx().equalsIgnoreCase(commp.getPropIdx()))
				{
					p.setPropName(commp.getPropName());
					p.setConsType(commp.getConsType());
					p.setConsVal(commp.getConsVal());
					p.setViewIdx(commp.getViewIdx());
					rsProps.add(p);
					break;
				}
			}
		}
		Collections.sort(rsProps, new PropSortByView());
		return rsProps;
	}
	

	
		/**
	     *  商品属性补全属性
		 * @author kereny
		 * @date 2015-8-21 下午1:10:43
		 * @param code
		 * @param props
		 * @return
		 * List<Prop>
	     *
		 */
	protected List<Prop> supplyCommIdxVal(String code,  List<Prop> props) {
		
		if(props == null) return null;
		
		List<CommProp> commProps = mallService.findCommProperty(code);
		
		if(commProps == null || commProps.size() == 0) return props;
		List<Prop> rsProps = new ArrayList<Prop>();
		for(CommProp commp: commProps)
		{
			Prop prop = new Prop();
			
			prop.setPropName(commp.getPropName());
			prop.setConsType(commp.getConsType());
			prop.setConsVal(commp.getConsVal());
			prop.setForceInput(commp.getForceInput());
			prop.setValType(commp.getValType());
			prop.setValFmt(commp.getValFmt());
			prop.setPropIdx(commp.getPropIdx());
			prop.setViewIdx(commp.getViewIdx());
			
			if(props != null){
				for(Prop p:props)
				{
					if(p.getPropIdx().equalsIgnoreCase(commp.getPropIdx()))
					{
						prop.setPropVal(p.getPropVal());
						break;
					}
				}
			}
			rsProps.add(prop);
		}
		Collections.sort(rsProps,new PropSortByView()); 
		
		return rsProps;
	}
	
	
	protected void getUrlMatch(List<MenuNode> menuNodes, ModelMap model){
		
		if(menuNodes == null || menuNodes.size() == 0) return;
		
		String  url = request.getRequestURL().toString();

    	for(MenuNode m : menuNodes){
    		if(m.isHasSubMenu()){
    			boolean bMatch = false;
    			for(MenuNode sbm : m.getSubMenus()){
    				if(url.indexOf(sbm.getMenuURL())>0){
	        			bMatch = true;
	        			model.addAttribute("cL2Url", sbm.getMenuURL());  
	        			break;
	        		}
    			}
    			if(bMatch){
    				model.addAttribute("cUrl", m.getMenuURL());  
    				break;
    			}
    		}else if(url.indexOf(m.getMenuURL())>0){
    			model.addAttribute("cUrl", m.getMenuURL()); 
    			break;
    		}
    	}
	}
	


}
