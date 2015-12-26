package com.cnacex.eshop.service.imp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cnacex.eshop.dao.BaseDAO;
import com.cnacex.eshop.msg.AbstractReqMsg;
import com.cnacex.eshop.msg.CommRspMsg;
import com.cnacex.eshop.msg.body.member.DivisMemberReq;
import com.cnacex.eshop.msg.body.member.LoginReq;
import com.cnacex.eshop.msg.body.member.InfoModifyReq;
import com.cnacex.eshop.msg.body.member.InfoQueryReq;
import com.cnacex.eshop.msg.body.member.InvQueryReq;
import com.cnacex.eshop.msg.body.member.OperManageReq;
import com.cnacex.eshop.msg.body.member.PwdChangeReq;
import com.cnacex.eshop.msg.body.member.RightsQueryReq;
import com.cnacex.eshop.msg.xml.member.DivisMemberReqMsg;
import com.cnacex.eshop.msg.xml.member.DivisMemberRspMsg;
import com.cnacex.eshop.msg.xml.member.InfoQueryRspMsg;
import com.cnacex.eshop.msg.xml.member.InvQueryReqMsg;
import com.cnacex.eshop.msg.xml.member.InvQueryRspMsg;
import com.cnacex.eshop.msg.xml.member.LoginReqMsg;
import com.cnacex.eshop.msg.xml.member.LoginRspMsg;
import com.cnacex.eshop.msg.xml.member.InfoModifyReqMsg;
import com.cnacex.eshop.msg.xml.member.InfoQueryReqMsg;
import com.cnacex.eshop.msg.xml.member.OperManageReqMsg;
import com.cnacex.eshop.msg.xml.member.OperManageRspMsg;
import com.cnacex.eshop.msg.xml.member.PwdChangeReqMsg;
import com.cnacex.eshop.msg.xml.member.RightsQueryReqMsg;
import com.cnacex.eshop.msg.xml.member.RightsQueryRspMsg;
import com.cnacex.eshop.service.IMemberService;
import com.cnacex.eshop.util.MsgBuilder;

/**
 * @author kereny
 *
 */
@Service("memberService")
public class MemberServiceImp  implements IMemberService {

	static Logger logger = LoggerFactory.getLogger(MemberServiceImp.class);
	

	@Autowired
	private BaseDAO baseDAO;
	
	
	@Override
	public  LoginRspMsg login(LoginReq loginReq){
		
		AbstractReqMsg<?> reqMsg = MsgBuilder.buildReqMsg(LoginReqMsg.class, loginReq); 
		LoginRspMsg rspMsg = baseDAO.handle(reqMsg,  LoginRspMsg.class);
		return rspMsg;
	}


	/**
     *  操作员资料查询
	 * @author sunc
	 * @date 2015-6-13 
	 * @param InfoQueryReq
	 * @return OperInfoQueryRspMsg
	 */
	@Override
	public InfoQueryRspMsg queryOperInfo(InfoQueryReq infoQueryReq) {
		AbstractReqMsg<?> reqMsg = MsgBuilder.buildReqMsg(InfoQueryReqMsg.class, infoQueryReq);
		InfoQueryRspMsg rspMsg = baseDAO.handle(reqMsg,  InfoQueryRspMsg.class);
		return rspMsg;
	}

	/**
     *  操作员资料变更
	 * @author sunc
	 * @date 2015-6-15 
	 * @param InfoModifyReq
	 * @return CommRspMsg
	 */

	@Override
	public CommRspMsg modifyOperInfo(InfoModifyReq infoModifyReq) {
		AbstractReqMsg<?> reqMsg = MsgBuilder.buildReqMsg(InfoModifyReqMsg.class, infoModifyReq);
		CommRspMsg rspMsg = baseDAO.handle(reqMsg,  CommRspMsg.class);
		return rspMsg;
	}

	
	/**
     *  操作员权限查询
	 * @author sunc
	 * @date 2015-6-15 
	 * @param IRightsQueryReq
	 * @return RightsQueryRspMsg
	 */
	@Override
	public RightsQueryRspMsg queryOperRights(RightsQueryReq rightQueryReq) {
		AbstractReqMsg<?> reqMsg = MsgBuilder.buildReqMsg(RightsQueryReqMsg.class, rightQueryReq);
		RightsQueryRspMsg rspMsg = baseDAO.handle(reqMsg,  RightsQueryRspMsg.class);
		return rspMsg;
	}



	@Override
	public OperManageRspMsg handleOperManage(OperManageReq operManageReq){
		AbstractReqMsg<?> reqMsg = MsgBuilder.buildReqMsg(OperManageReqMsg.class, operManageReq);
		OperManageRspMsg rspMsg = baseDAO.handle(reqMsg,  OperManageRspMsg.class);
		return rspMsg;
	}


	@Override
	public CommRspMsg changeOperPwd(PwdChangeReq pwdChangeReq) {
		AbstractReqMsg<?> reqMsg = MsgBuilder.buildReqMsg(PwdChangeReqMsg.class, pwdChangeReq);
		CommRspMsg rspMsg = baseDAO.handle(reqMsg,  CommRspMsg.class);
		return rspMsg;
	}




	@Override
	public InvQueryRspMsg queryOperInv(InvQueryReq invQueryReq) {
		AbstractReqMsg<?> reqMsg = MsgBuilder.buildReqMsg(InvQueryReqMsg.class, invQueryReq);
		InvQueryRspMsg rspMsg = baseDAO.handle(reqMsg,  InvQueryRspMsg.class);
		return rspMsg;
	}


	@Override
	public DivisMemberRspMsg findMemberList(DivisMemberReq divisMemberReq) {
		AbstractReqMsg<?> reqMsg = MsgBuilder.buildReqMsg(DivisMemberReqMsg.class, divisMemberReq);
		DivisMemberRspMsg rspMsg = baseDAO.handle(reqMsg,  DivisMemberRspMsg.class);
		return rspMsg;
	}
}
