package com.cnacex.eshop.service;

import com.cnacex.eshop.msg.body.report.ReportCommReq;

import com.cnacex.eshop.msg.xml.report.AttendRspMsg;
import com.cnacex.eshop.msg.xml.report.DailyRspMsg;
import com.cnacex.eshop.msg.xml.report.FundRspMsg;
import com.cnacex.eshop.msg.xml.report.TickRspMsg;

public interface IReportService {
	
	
		/**
	     *  交易商日报表
		 * @author kereny
		 * @date 2015-9-17 下午4:33:00
		 * @param reportCommReq
		 * @return
		 * DailyRspMsg
	     *
		 */
	public abstract DailyRspMsg findDaily(ReportCommReq reportCommReq);
	
	
		/**
	     *  出入金报表
		 * @author kereny
		 * @date 2015-9-17 下午4:33:41
		 * @param reportCommReq
		 * @return
		 * FundRspMsg
	     *
		 */
	public abstract FundRspMsg findDetail(ReportCommReq reportCommReq);
	
	
		/**
	     *  成交明细表
		 * @author kereny
		 * @date 2015-9-17 下午6:56:27
		 * @param reportCommReq
		 * @return
		 * TickRspMsg
	     *
		 */
	public abstract TickRspMsg findTick(ReportCommReq reportCommReq);
	
	
		/**
	     *  交收通知单
		 * @author kereny
		 * @date 2015-9-18 上午9:43:23
		 * @param reportCommReq
		 * @return
		 * TickRspMsg
	     *
		 */
	public abstract TickRspMsg findInform(ReportCommReq reportCommReq);
	
		/**
	     *  办理明细表
		 * @author kereny
		 * @date 2015-9-18 上午9:43:39
		 * @param reportCommReq
		 * @return
		 * TickRspMsg
	     *
		 */
	public abstract AttendRspMsg findAttend(ReportCommReq reportCommReq);
	
}
