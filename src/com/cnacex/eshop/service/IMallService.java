package com.cnacex.eshop.service;

import java.util.List;
import com.cnacex.eshop.msg.body.comm.CommProp;
import com.cnacex.eshop.msg.body.mall.ListedDetailReq;
import com.cnacex.eshop.msg.body.mall.MdseElement;
import com.cnacex.eshop.msg.body.mall.MoreQueryReq;
import com.cnacex.eshop.msg.body.mall.SingleQueryReq;
import com.cnacex.eshop.msg.xml.mall.ListedDetailRspMsg;
import com.cnacex.eshop.msg.xml.mall.QueryCommRspMsg;

/**
 * @author kereny
 * 
 */
public interface IMallService {

		/**
		 * 商品分类信息数据列表
		 * 
		 * @author kereny
		 * @date 2015-6-11 下午1:52:20
		 * @return Object
		 * 
		 */
	public abstract boolean findAllMdseData();

		/**
		 * 取所有市场信息及对映的品类数据
		 * 
		 * @author kereny
		 * @date 2015-6-12 下午2:19:43
		 * @return List<MarketEntity>
		 * 
		 */
	
	//该方法废掉了
	//public abstract List<MarketEntity> findAllMarketEntity();

	/**
	 * 取品类的所有属性值
	 * 
	 * @author kereny
	 * @date 2015-6-13 上午11:28:25
	 * @param classcode
	 * @return List<ClassProp>
	 * 
	 */
	//该方法废掉了
	//public abstract List<ClassProp> findClassProperty(String code);

		/**
		 * 取品种的所有属性值
		 * 
		 * @author kereny
		 * @date 2015-6-13 上午11:29:35
		 * @param commcode
		 * @return List<CommProp>
		 * 
		 */
	public abstract List<CommProp> findCommProperty(String code);



		/**
	     *  单商品请求
		 * @author kereny
		 * @date 2015-7-27 下午4:01:58
		 * @param singleQueryReq
		 * @return
		 * QueryCommRspMsg
	     *
		 */
	public abstract QueryCommRspMsg findSingleListed(SingleQueryReq singleQueryReq);

	
	

		/**
	     *  多商品查询
		 * @author kereny
		 * @date 2015-7-27 下午4:02:18
		 * @param moreQueryReq
		 * @return
		 * QueryCommRspMsg
	     *
		 */
	public abstract QueryCommRspMsg findMoreCommListed(MoreQueryReq moreQueryReq);



		/**
	     *  根据代码返回指定类别实体
		 * @author kereny
		 * @date 2015-6-15 下午12:42:49
		 * @param code
		 * @return
		 * MdseElement
	     *
		 */
	public abstract MdseElement findMdseEntity(String code);
	
	
	/**
     *  根据代码返回指定类别实体(本地内存)
	 * @author kereny
	 * @date 2015-6-15 下午12:42:49
	 * @param code
	 * @return
	 * MdseElement
     *
	 */
	public abstract MdseElement findLocalMdseEntity(String code);
	
	
	/**
     *  根据名称返回指定类别实体(本地内存)
	 * @author kereny
	 * @date 2015-6-15 下午12:42:49
	 * @param code
	 * @return
	 * MdseElement
     *
	 */
	public abstract MdseElement findLocalMdseEntityByName(String keyword);
	
		/**
	     *  生成商品类别树路径
		 * @author kereny
		 * @date 2015-6-15 下午12:44:13
		 * @param code
		 * @return
		 * List<MdseElement>
	     *
		 */
	public abstract List<MdseElement> findNodeTreePath(String code);
	
	
		/**
	     *  取分类的子分类
		 * @author kereny
		 * @date 2015-6-18 下午3:58:56
		 * @param code
		 * @return
		 * List<MdseElement>
	     *
		 */
	public abstract List<MdseElement> findChildList(String code);
	
	
		/**
	     *  取市场列表
		 * @author kereny
		 * @date 2015-6-18 下午3:58:56
		 * @param code
		 * @return
		 * List<MdseElement>
	     *
		 */
	public abstract List<MdseElement> findAllMarkets();

	
	
		/**
	     *  取某一节点下面的叶子节点
		 * @author kereny
		 * @date 2015-6-24 下午4:19:17
		 * @param code
		 * @return
		 * List<MdseElement>
	     *
		 */
	public abstract List<MdseElement> findLeafMdseEntity(String code);
	
	/**
     *  取某一节点下面所有classflg=3的节点
	 */
	public abstract List<MdseElement> findClassFlg3MdseEntity(String code);
	
		/**
	     *  查询挂单详情
		 * @author kereny
		 * @date 2015-6-24 下午3:06:06
		 * @param listedDetailReq
		 * @return
		 * ListedDetailRspMsg
	     *
		 */
	public abstract ListedDetailRspMsg findDetailByID(ListedDetailReq listedDetailReq);
	
	
}
