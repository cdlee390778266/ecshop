package com.cnacex.eshop.util;

import com.cnacex.eshop.msg.AbstractReqMsg;
import com.cnacex.eshop.msg.Head;


public class MsgBuilder {
	

		/**
	     *  创建请求报文处理
		 * @author kereny
		 * @date 2015-6-10 下午2:33:06
		 * @param reqMsgCls
		 * @param reqBody
		 * @return
		 * M
	     *
		 */
	public static <M extends AbstractReqMsg<B>, B> M buildReqMsg(Class<M> reqMsgCls, B reqBody) {
		try {
			M msg = reqMsgCls.newInstance();
			buildHead(msg);
			msg.setBody(reqBody);
			return msg;
		} catch (Exception e) {
			throw new RuntimeException(String.format("构建 %s 实例 失败!", reqMsgCls), e);
		}
	}
	
	

		/**
	     *  创建请求头
		 * @author kereny
		 * @date 2015-6-10 下午2:33:52
		 * @param msg
		 * void
	     *
		 */
	private static void buildHead(AbstractReqMsg<?> msg) {
		Head head = new Head();
		head.setTxSN(TxSNManager.BuildTxSN());
		msg.setHead(head);
	}



}
