package com.cnacex.eshop.service;


import com.cnacex.eshop.msg.CommRspMsg;
import com.cnacex.eshop.msg.body.member.DivisMemberReq;
import com.cnacex.eshop.msg.body.member.LoginReq;
import com.cnacex.eshop.msg.body.member.InfoModifyReq;
import com.cnacex.eshop.msg.body.member.InfoQueryReq;
import com.cnacex.eshop.msg.body.member.InvQueryReq;
import com.cnacex.eshop.msg.body.member.OperManageReq;
import com.cnacex.eshop.msg.body.member.PwdChangeReq;
import com.cnacex.eshop.msg.body.member.RightsQueryReq;
import com.cnacex.eshop.msg.xml.member.DivisMemberRspMsg;
import com.cnacex.eshop.msg.xml.member.InfoQueryRspMsg;
import com.cnacex.eshop.msg.xml.member.InvQueryRspMsg;
import com.cnacex.eshop.msg.xml.member.LoginRspMsg;
import com.cnacex.eshop.msg.xml.member.OperManageRspMsg;
import com.cnacex.eshop.msg.xml.member.RightsQueryRspMsg;


/**
 * @author kereny
 *
 */
public interface IMemberService {
	
		/**
	     *  会员登录验证
		 * @author kereny
		 * @date 2015-6-4 下午12:51:15
		 * @param member
		 * @return
		 * Member
	     *
		 */
	
	public abstract LoginRspMsg login(LoginReq loginReq);
	
	
	
	
	
	/**
     *  操作员资料查询
	 * @author sunc
	 * @date 2015-6-13
	 * @param 
	 * @return
	 */
	public abstract InfoQueryRspMsg queryOperInfo(
				InfoQueryReq infoQueryReq);
	
	
	
	
	
	
	/**
     *  操作员资料变更
	 * @author sunc
	 * @date 2015-6-13
	 * @param 
	 * @return
	 */
	public abstract CommRspMsg modifyOperInfo(InfoModifyReq infoModifyReq);
	
	
	
	
	
	
	/**
     *  操作员权限查询
	 * @author sunc
	 * @date 2015-6-13
	 * @param 
	 * @return
	 */
	public abstract  RightsQueryRspMsg queryOperRights(RightsQueryReq rightsQueryReq);
	
	
	
	
	
	
	/**
     *  操作员管理处理
	 * @author sunc
	 * @date 2015-6-13
	 * @param 
	 * @return
	 */
	public abstract OperManageRspMsg handleOperManage(OperManageReq operManageReq);
	
	
	
	
	
	
	/**
     *  操作员密码修改
	 * @author sunc
	 * @date 2015-6-13
	 * @param 
	 * @return
	 */
	public abstract CommRspMsg changeOperPwd(PwdChangeReq pwdChangeReq);
	
	
	
	
	
	
	
		/**
	     *  操作员清单查询
		 * @author sunc
		 * @date 2015-6-13
		 * @param 
		 * @return
		 */
	public abstract InvQueryRspMsg queryOperInv(InvQueryReq invQueryReq);
	
	
		/**
	     *  查询区域下面会员列表
		 * @author kereny
		 * @date 2015-8-25 上午10:55:02
		 * @param divisMemberReq
		 * @return
		 * DivisMemberRspMsg
	     *
		 */
	public abstract DivisMemberRspMsg findMemberList(DivisMemberReq divisMemberReq);

}
