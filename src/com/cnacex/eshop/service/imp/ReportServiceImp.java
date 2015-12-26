package com.cnacex.eshop.service.imp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cnacex.eshop.dao.BaseDAO;
import com.cnacex.eshop.msg.AbstractReqMsg;
import com.cnacex.eshop.msg.body.report.ReportCommReq;
import com.cnacex.eshop.msg.xml.report.AttendReqMsg;
import com.cnacex.eshop.msg.xml.report.AttendRspMsg;
import com.cnacex.eshop.msg.xml.report.DailyReqMsg;
import com.cnacex.eshop.msg.xml.report.DailyRspMsg;
import com.cnacex.eshop.msg.xml.report.FundReqMsg;
import com.cnacex.eshop.msg.xml.report.FundRspMsg;
import com.cnacex.eshop.msg.xml.report.InformReqMsg;
import com.cnacex.eshop.msg.xml.report.TickReqMsg;
import com.cnacex.eshop.msg.xml.report.TickRspMsg;
import com.cnacex.eshop.service.IReportService;
import com.cnacex.eshop.util.MsgBuilder;

@Service("reportService")
public class ReportServiceImp implements IReportService {
	
	
	static Logger logger = LoggerFactory.getLogger(ReportServiceImp.class);
	@Autowired
	private BaseDAO baseDAO;

	@Override
	public DailyRspMsg findDaily(ReportCommReq reportCommReq) {
		AbstractReqMsg<?> reqMsg = MsgBuilder.buildReqMsg(
				DailyReqMsg.class, reportCommReq);
		
		DailyRspMsg rspMsg = baseDAO.handle(reqMsg, DailyRspMsg.class);
		return rspMsg;
	}
	
	

	@Override
	public FundRspMsg findDetail(ReportCommReq reportCommReq) {
		AbstractReqMsg<?> reqMsg = MsgBuilder.buildReqMsg(
				FundReqMsg.class, reportCommReq);
		
		FundRspMsg rspMsg = baseDAO.handle(reqMsg, FundRspMsg.class);
		return rspMsg;
	}



	@Override
	public TickRspMsg findTick(ReportCommReq reportCommReq) {
		AbstractReqMsg<?> reqMsg = MsgBuilder.buildReqMsg(
				TickReqMsg.class, reportCommReq);
		
		TickRspMsg rspMsg = baseDAO.handle(reqMsg, TickRspMsg.class);
		return rspMsg;
	}



	@Override
	public TickRspMsg findInform(ReportCommReq reportCommReq) {
		AbstractReqMsg<?> reqMsg = MsgBuilder.buildReqMsg(
				InformReqMsg.class, reportCommReq);
		
		TickRspMsg rspMsg = baseDAO.handle(reqMsg, TickRspMsg.class);
		return rspMsg;
	}



	@Override
	public AttendRspMsg findAttend(ReportCommReq reportCommReq) {
		AbstractReqMsg<?> reqMsg = MsgBuilder.buildReqMsg(
				AttendReqMsg.class, reportCommReq);
		
		AttendRspMsg rspMsg = baseDAO.handle(reqMsg, AttendRspMsg.class);
		return rspMsg;
	}

}
