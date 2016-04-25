package com.cnacex.eshop.service.imp;



import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cnacex.comm.util.StringUtil;
import com.cnacex.eshop.dao.BaseDAO;
import com.cnacex.eshop.msg.AbstractReqMsg;
import com.cnacex.eshop.msg.body.comm.CommProp;
import com.cnacex.eshop.msg.body.mall.ListedDetailReq;
import com.cnacex.eshop.msg.body.mall.MdseElement;
import com.cnacex.eshop.msg.body.mall.MdseStaticReq;
import com.cnacex.eshop.msg.body.mall.MdseStaticRsp;
import com.cnacex.eshop.msg.body.mall.MoreQueryReq;
import com.cnacex.eshop.msg.body.mall.SingleQueryReq;
import com.cnacex.eshop.msg.xml.mall.ListedDetailReqMsg;
import com.cnacex.eshop.msg.xml.mall.ListedDetailRspMsg;
import com.cnacex.eshop.msg.xml.mall.MdseStaticReqMsg;
import com.cnacex.eshop.msg.xml.mall.MdseStaticRspMsg;
import com.cnacex.eshop.msg.xml.mall.MoreQueryReqMsg;
import com.cnacex.eshop.msg.xml.mall.QueryCommRspMsg;
import com.cnacex.eshop.msg.xml.mall.SingleQueryReqMsg;
import com.cnacex.eshop.service.IMallService;
import com.cnacex.eshop.util.MsgBuilder;

@Service("mallService")
public class MallServiceImp implements IMallService {

	static Logger logger = LoggerFactory.getLogger(MallServiceImp.class);

	public static String MAC = "00000";
	
	private static boolean IS_MAC_EXPIRED = true;
	
	private static String MAC_SYNC_FLAG = "MAC_SYNC_FLAG";

	List<MdseElement> mdseList= null;
	
	public static String SYS_STATUS = "0";
	
	public static String SYS_BUSDATE = "";
	
	
	public static Hashtable<String, MdseElement> mdseTable = new Hashtable<String, MdseElement>();
	
	
	public static Hashtable<String, MdseElement> mdseNameTable = new Hashtable<String, MdseElement>();

	@Autowired
	private BaseDAO baseDAO;

	@Override
	public boolean findAllMdseData() {

		logger.debug("当前MAC {}", MAC);

		MdseStaticReq staticReq = new MdseStaticReq();
		staticReq.setDac(MAC);

		AbstractReqMsg<?> reqMsg = MsgBuilder.buildReqMsg(
				MdseStaticReqMsg.class, staticReq);

		MdseStaticRspMsg rspMsg = baseDAO.handle(reqMsg, MdseStaticRspMsg.class);

		if (rspMsg.getHead() != null) {
			if (rspMsg.getHead().getSuccFlag() == 1) {
				if (rspMsg.getBody() != null) {
					if (rspMsg.getBody().getIsUpd() == '0') {
						logger.debug("校验码一样,无需要更新静态业务数据");
					} else {	
						IS_MAC_EXPIRED = true;
						if (IS_MAC_EXPIRED){
							synchronized (MAC_SYNC_FLAG) {
								if (IS_MAC_EXPIRED){
									refershMemData(rspMsg.getBody());
								}
							}
						}
					}
				} else {
					logger.error("处理错误,返回业务报文无报文体,与协议不符");
					return false;
				}
				
				synchronized (SYS_STATUS){
					SYS_STATUS = rspMsg.getHead().getSysStatus();
					SYS_BUSDATE = rspMsg.getHead().getBusiDate();
				}
			} else {
				logger.error("处理错误:{}", rspMsg.getHead().getRspMsg());
				return false;
			}
		} else {
			if (rspMsg.getFault() != null)
				logger.error("处理错误:{}", rspMsg.getFault().getRspMsg());
			else
				logger.error("处理错误,返回无业务报文头及异常报文头");

			return false;
		}

		return true;
	}
	
	
	private void refershMemData(MdseStaticRsp rspBody){
		mdseList = new ArrayList<MdseElement>();
		List<MdseElement> lists = rspBody.getMdseLists();
		for(MdseElement me : lists)
		{
			if(hasChild(lists, me)){
				me.setHaveLeaf(true);
			}else{
				me.setHaveLeaf(false);
			}
			mdseTable.put(me.getMdseCode(), me);
			
			mdseNameTable.put(me.getMdseName(), me);
			
			mdseList.add(me);
		}
		
		MAC = rspBody.getDac();
		IS_MAC_EXPIRED = false;
	}

	/*
	@Override
	public List<MarketEntity> findAllMarketEntity() {

		List<MarketEntity> marks = new ArrayList<MarketEntity>();

		MarketEntity market = null;
		if (findAllMdseData() == true) {

			List<MdseElement> mdseroots = getRootNodes(mdseElements);

			for (MdseElement ele : mdseroots) {
				market = new MarketEntity();

				market.setMdseCode(ele.getMdseCode());
				market.setMdseName(ele.getMdseName());

				List<MdseElement> mdsenexts = getNextNodes(mdseElements,
						ele.getMdseCode());

				List<NodeEntity> nodes = new ArrayList<NodeEntity>();
				NodeEntity node = null;
				for (MdseElement nele : mdsenexts) {
					node = new NodeEntity();
					node.setMdseCode(nele.getMdseCode());
					node.setMdseName(nele.getMdseName());
					node.setLeafnodes(getLeafNodes(mdseElements,
							nele.getMdseCode()));
					nodes.add(node);
				}

				market.setNextnodes(nodes);
				marks.add(market);
			}
		}
		return marks;
	}
	*/

	/**
	 * 取市场结点
	 * 
	 * @author kereny
	 * @date 2015-6-12 下午4:23:18
	 * @param list
	 * @return List<MdseElement>
	 * 
	 */
	private List<MdseElement> getRootNodes(List<MdseElement> list) {

		List<MdseElement> rootnodes = null;
		if (list == null)
			return rootnodes;
		rootnodes = new ArrayList<MdseElement>();
		for (Iterator<MdseElement> iterator = list.iterator(); iterator
				.hasNext();) {
			MdseElement node = (MdseElement) iterator.next();

			if (StringUtil.nullOrBlank(node.getPmdseCode())) {
				rootnodes.add(node);
			}
		}
		return rootnodes;
	}

	/**
	 * 取指定父节点的下级结点
	 * 
	 * @author kereny
	 * @date 2015-6-12 下午4:25:39
	 * @param list
	 * @param pmdseCode
	 * @return List<MdseElement>
	 * 
	 */
	private List<MdseElement> getNextNodes(List<MdseElement> list,
			String pmdseCode) {

		List<MdseElement> nextnodes = null;
		if (list == null || StringUtil.nullOrBlank(pmdseCode))
			return nextnodes;
		nextnodes = new ArrayList<MdseElement>();
		for (Iterator<MdseElement> iterator = list.iterator(); iterator
				.hasNext();) {
			MdseElement node = (MdseElement) iterator.next();

			if (!StringUtil.nullOrBlank(node.getPmdseCode())
					&& node.getPmdseCode().equalsIgnoreCase(pmdseCode)) {
				
				nextnodes.add(node);
			}
		}
		return nextnodes;
	}

	/**
	 * 得到分类下面的所有叶子节点
	 * 
	 * @author kereny
	 * @date 2015-6-12 下午4:49:09
	 * @param list
	 * @param mdseCode
	 * @return List<MdseElement>
	 * 
	 */
	private List<MdseElement> getLeafNodes(List<MdseElement> list,
			String mdseCode) {

		List<MdseElement> leafnodes = null;
		if (list == null || StringUtil.nullOrBlank(mdseCode))
			return leafnodes;

		leafnodes = new ArrayList<MdseElement>();

		for (Iterator<MdseElement> iterator = list.iterator(); iterator
				.hasNext();) {
			MdseElement node = (MdseElement) iterator.next();
			// 一、根据传入的某个父节点ID,遍历该父节点的所有子节点

			if (!StringUtil.nullOrBlank(node.getPmdseCode())
					&& mdseCode.equalsIgnoreCase(node.getMdseCode())) {
				leafnodes = recursionFn(list, node, leafnodes);
			}
		}
		return leafnodes;
	}

	/**
	 * 递归到子节点
	 * 
	 * @author kereny
	 * @date 2015-6-12 下午4:49:48
	 * @param list
	 * @param node
	 * @param leaflist
	 * @return List<MdseElement>
	 * 
	 */
	private List<MdseElement> recursionFn(List<MdseElement> list,
			MdseElement node, List<MdseElement> leaflist) {
		List<MdseElement> childList = getChildList(list, node);// 得到子节点列表
		if (hasChild(list, node)) {
			Iterator<MdseElement> it = childList.iterator();
			while (it.hasNext()) {
				MdseElement n = (MdseElement) it.next();
				recursionFn(list, n, leaflist);
			}
		} else {
			leaflist.add(node);

		}
		return leaflist;
	}

	/**
	 * 得到子节点列表
	 * 
	 * @author kereny
	 * @date 2015-6-12 下午4:50:26
	 * @param list
	 * @param node
	 * @return List<MdseElement>
	 * 
	 */
	private List<MdseElement> getChildList(List<MdseElement> list,
			MdseElement node) {
		List<MdseElement> nodeList = new ArrayList<MdseElement>();
		Iterator<MdseElement> it = list.iterator();
		while (it.hasNext()) {
			MdseElement n = (MdseElement) it.next();
			if (n.getPmdseCode().equalsIgnoreCase(node.getMdseCode())) {
				nodeList.add(n);
			}
		}
		return nodeList;
	}

	/**
	 * 判断是否有子节点
	 * 
	 * @author kereny
	 * @date 2015-6-12 下午4:50:40
	 * @param list
	 * @param node
	 * @return boolean
	 * 
	 */
	private boolean hasChild(List<MdseElement> list, MdseElement node) {
		return getChildList(list, node).size() > 0 ? true : false;
	}

	/*
	@Override
	public List<ClassProp> findClassProperty(String code) {

		if (findAllMdseData() == true) {

			for (MdseElement ele : mdseElements) {
				if (ele.getMdseCode().equalsIgnoreCase(code)) {
					return ele.getClassProps();
				}
			}
		}
		return null;
	}
	*/

	@Override
	public List<CommProp> findCommProperty(String code) {
		//if (findAllMdseData() == true) {
			MdseElement me = mdseTable.get(code);
			if(me != null)
				return me.getCommProps();
		//}
		return null;
	}

	
	@Override
	public MdseElement findMdseEntity(String code) {
		if (findAllMdseData() == true) {

			return mdseTable.get(code);
		}
		return null;
	}

	@Override
	public QueryCommRspMsg findSingleListed(SingleQueryReq singleQueryReq) {

		AbstractReqMsg<?> reqMsg = MsgBuilder.buildReqMsg(
				SingleQueryReqMsg.class, singleQueryReq);

		QueryCommRspMsg rspMsg = baseDAO.handle(reqMsg, QueryCommRspMsg.class);
		
		return rspMsg;
	}

	@Override
	public QueryCommRspMsg findMoreCommListed(MoreQueryReq moreQueryReq) {


		AbstractReqMsg<?> reqMsg = MsgBuilder.buildReqMsg(
				MoreQueryReqMsg.class, moreQueryReq);

		QueryCommRspMsg rspMsg = baseDAO.handle(reqMsg, QueryCommRspMsg.class);

		return rspMsg;
	}

	
	@Override
	public List<MdseElement> findNodeTreePath(String code) {

		if (StringUtil.nullOrBlank(code))
			return null;

		return nodeTreePath(mdseList, code);

	}

	
		/**
	     *  某分类得到上级分类的链
		 * @author kereny
		 * @date 2015-6-15 下午1:44:23
		 * @param list
		 * @param code
		 * @return
		 * List<MdseElement>
	     *
		 */
	public List<MdseElement> nodeTreePath(List<MdseElement> list, String code) {

		if (list == null || StringUtil.nullOrBlank(code))
			return null;

		List<MdseElement> rs = new ArrayList<MdseElement>();

		for (Iterator<MdseElement> iterator = list.iterator(); iterator
				.hasNext();) {
			MdseElement node = (MdseElement) iterator.next();

			if (code.equalsIgnoreCase(node.getMdseCode())) {
				rs = getParentFn(list, node, rs);

				rs.add(node);
				return rs;

			}
		}
		return rs;
	}

		/**
	     *  递归得到上级分类的处理
		 * @author kereny
		 * @date 2015-6-15 下午1:43:53
		 * @param list
		 * @param node
		 * @param rslist
		 * @return
		 * List<MdseElement>
	     *
		 */
	public List<MdseElement> getParentFn(List<MdseElement> list,
			MdseElement node, List<MdseElement> rslist) {

		if (StringUtil.nullOrBlank(node.getPmdseCode())) {
			return rslist;
		}
		Iterator<MdseElement> it = list.iterator();
		while (it.hasNext()) {
			MdseElement n = (MdseElement) it.next();

			if (node.getPmdseCode().equalsIgnoreCase(n.getMdseCode())) {
				rslist = getParentFn(list, n, rslist);
				rslist.add(n);
				return rslist;
			}
		}
		return rslist;

	}

		@Override
		public List<MdseElement> findChildList(String code) {
			List<MdseElement> childNodes = null;
			if (mdseList == null || StringUtil.nullOrBlank(code))
				return null;
			
			childNodes = getNextNodes(mdseList, code);
			
			return childNodes;
		}

		
		
		@Override
		public List<MdseElement> findAllMarkets() {
			
			
			if (findAllMdseData() == true) {
				return getRootNodes(mdseList);
			}
			return null;
		}


		@Override
		public MdseElement findLocalMdseEntity(String code) {
			return mdseTable.get(code);
		}


		@Override
		public ListedDetailRspMsg findDetailByID(ListedDetailReq listedDetailReq) {
			AbstractReqMsg<?> reqMsg = MsgBuilder.buildReqMsg(ListedDetailReqMsg.class, listedDetailReq);
			ListedDetailRspMsg rspMsg = baseDAO.handle(reqMsg, ListedDetailRspMsg.class);
			return rspMsg;
		}


		@Override
		public List<MdseElement> findLeafMdseEntity(String code) {

			return getLeafNodes(mdseList, code);
			
		}


		@Override
		public MdseElement findLocalMdseEntityByName(String keyword) {
			return mdseNameTable.get(keyword);
		}
		
		

}
