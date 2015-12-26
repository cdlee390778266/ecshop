package com.cnacex.eshop.web.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.CellRangeAddress;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bea.common.security.xacml.IOException;
import com.cnacex.comm.util.StringUtil;
import com.cnacex.eshop.modul.JSonComm;
import com.cnacex.eshop.msg.body.member.LoginRsp;

import com.cnacex.eshop.msg.body.report.Daily;
import com.cnacex.eshop.msg.body.report.DailyRsp;
import com.cnacex.eshop.msg.body.report.FTList;
import com.cnacex.eshop.msg.body.report.FVList;
import com.cnacex.eshop.msg.body.report.Fund;
import com.cnacex.eshop.msg.body.report.ReportCommReq;
import com.cnacex.eshop.msg.body.report.SubAcctList;
import com.cnacex.eshop.msg.body.report.Tick;
import com.cnacex.eshop.msg.body.report.TickRsp;

import com.cnacex.eshop.msg.xml.report.AttendRspMsg;
import com.cnacex.eshop.msg.xml.report.DailyRspMsg;
import com.cnacex.eshop.msg.xml.report.FundRspMsg;
import com.cnacex.eshop.msg.xml.report.TickRspMsg;

import com.cnacex.eshop.service.IReportService;

import com.cnacex.eshop.util.StatusUtil;
import com.cnacex.eshop.util.SubActUtil;

/**
 * @author kereny
 * 
 */

@Controller
@RequestMapping(value = "/report")
public class ReportController extends TradeController {

	private static double INVALID_AMT = -1.00;

	static Logger logger = LoggerFactory.getLogger(ReportController.class);

	@Autowired
	private IReportService reportService;

		/**
		 * 对账单入口
		 * 
		 * @author kereny
		 * @date 2015-9-17 上午9:33:26
		 * @param request
		 * @param response
		 * @return
		 * @throws IOException
		 *             String
		 * 
		 */
	@RequestMapping(value = "/check.htm")
	public String daily(HttpServletRequest request, HttpServletResponse response, ModelMap model)
			throws IOException {
		LoginRsp loginRsp = (LoginRsp)request.getSession().getAttribute("userinfo");
		getUrlMatch(loginRsp.getTradeMenus(), model);

		return "report/check";
	}
	
	

		/**
	     *  月报表入口
		 * @author kereny
		 * @date 2015-10-8 下午5:40:28
		 * @param request
		 * @param response
		 * @param model
		 * @return
		 * @throws IOException
		 * String
	     *
		 */
	@RequestMapping(value = "/monthly.htm")
	public String monthly(HttpServletRequest request, HttpServletResponse response, ModelMap model)
			throws IOException {
		LoginRsp loginRsp = (LoginRsp)request.getSession().getAttribute("userinfo");
		getUrlMatch(loginRsp.getTradeMenus(), model);
	
		return "report/monthly";
	}
		

	private HashMap<String, Object> handleDaily(String mid, String rptdate, String mname){
		
		ReportCommReq reportCommReq = new ReportCommReq();

		reportCommReq.setmID(mid);

		reportCommReq.setRptDate(rptdate);

		DailyRspMsg rspMsg = reportService.findDaily(reportCommReq);
		
		boolean succflag = true;
		String msg = "success";
		if (rspMsg.getHead() == null) {
			
			succflag = false;
			msg = rspMsg.getFault().getRspMsg();
		}
		if (succflag == true && rspMsg.getHead().getSuccFlag() != 1) {
			succflag = false;
			msg = rspMsg.getHead().getRspMsg();
		}

		HashMap<String, Object> maps = new HashMap<String, Object>();
		maps.put("title", "交易商资金日报表");
		maps.put("succflag", succflag);
		maps.put("mid", mid);
		maps.put("memname", mname);
		maps.put("msg", msg);
		if(succflag){
			maps.put("reportdate",  rspMsg.getBody().getDate());
			List<Daily> list = Covert2Daily(rspMsg.getBody());
			maps.put("list", list);
		}
		return maps;
	}
	
	
	private HashMap<String, Object> handleMonthly(String mid, String rptdate, String mname){
			
			ReportCommReq reportCommReq = new ReportCommReq();
	
			reportCommReq.setmID(mid);
	
			reportCommReq.setRptDate(rptdate);
	
			DailyRspMsg rspMsg = reportService.findDaily(reportCommReq);
			
			boolean succflag = true;
			String msg = "success";
			if (rspMsg.getHead() == null) {
				
				succflag = false;
				msg = rspMsg.getFault().getRspMsg();
			}
			if (succflag == true && rspMsg.getHead().getSuccFlag() != 1) {
				succflag = false;
				msg = rspMsg.getHead().getRspMsg();
			}
	
			HashMap<String, Object> maps = new HashMap<String, Object>();
			maps.put("title", "交易商月报表");
			maps.put("succflag", succflag);
			maps.put("mid", mid);
			maps.put("memname", mname);
			maps.put("msg", msg);
			if(succflag){
				maps.put("reportdate",  rspMsg.getBody().getDate());
				List<Daily> list = Covert2Daily(rspMsg.getBody());
				maps.put("list", list);
			}
			return maps;
		}
	
	
	private HashMap<String, Object> handleFund(String mid, String rptdate, String mname){
		
		ReportCommReq reportCommReq = new ReportCommReq();

		reportCommReq.setmID(mid);

		reportCommReq.setRptDate(rptdate);

		FundRspMsg rspMsg = reportService.findDetail(reportCommReq);
		
		boolean succflag = true;
		String msg = "success";
		if (rspMsg.getHead() == null) {
			
			succflag = false;
			msg = rspMsg.getFault().getRspMsg();
		}
		if (succflag == true && rspMsg.getHead().getSuccFlag() != 1) {
			succflag = false;
			msg = rspMsg.getHead().getRspMsg();
		}
		
		HashMap<String, Object> maps = new HashMap<String, Object>();
		maps.put("title", "出入金明细表");
		maps.put("succflag", succflag);
		maps.put("mid", mid);
		maps.put("memname", mname);
		maps.put("msg", msg);
		if(succflag){
			List<Fund> funds  = Covert2Fund(rspMsg.getBody().getList());
			maps.put("reportdate", rspMsg.getBody().getDate());
			maps.put("list", funds);

		}
		return maps;
	}
	
	private HashMap<String, Object> handleAttend(String mid, String rptdate, String mname){
		
		ReportCommReq reportCommReq = new ReportCommReq();

		reportCommReq.setmID(mid);

		reportCommReq.setRptDate(rptdate);

		AttendRspMsg rspMsg = reportService.findAttend(reportCommReq);
		
		boolean succflag = true;
		String msg = "success";
		if (rspMsg.getHead() == null) {
			
			succflag = false;
			msg = rspMsg.getFault().getRspMsg();
		}
		if (succflag == true && rspMsg.getHead().getSuccFlag() != 1) {
			succflag = false;
			msg = rspMsg.getHead().getRspMsg();
		}
		
		HashMap<String, Object> maps = new HashMap<String, Object>();
		maps.put("title", "交收办理明细表");
		maps.put("succflag", succflag);
		maps.put("mid", mid);
		maps.put("memname", mname);
		maps.put("msg", msg);
		if(succflag){
			List<Fund> funds  = Covert2Fund(rspMsg.getBody().getList());
			maps.put("reportdate", rspMsg.getBody().getDate());
			maps.put("list", funds);

		}
		return maps;
	}
	
	private HashMap<String, Object> handleTick(String mid, String rptdate, int type, String mname){
		
		ReportCommReq reportCommReq = new ReportCommReq();

		reportCommReq.setmID(mid);

		reportCommReq.setRptDate(rptdate);
		
		
		TickRspMsg rspMsg = null;
		String title = "";
		
		if(type == 0){
			rspMsg = reportService.findTick(reportCommReq);
			title = "成交明细表";
		}
		else if(type == 1)
		{
			
			rspMsg = reportService.findInform(reportCommReq);
			title = "交收通知单";
		}
		
		
		boolean succflag = true;
		String msg = "success";
		if (rspMsg.getHead() == null) {
			
			succflag = false;
			msg = rspMsg.getFault().getRspMsg();
		}
		if (succflag == true && rspMsg.getHead().getSuccFlag() != 1) {
			succflag = false;
			msg = rspMsg.getHead().getRspMsg();
		}
		
		HashMap<String, Object> maps = new HashMap<String, Object>();
		maps.put("title", title);
		maps.put("succflag", succflag);
		maps.put("mid", mid);
		maps.put("memname", mname);
		maps.put("msg", msg);
		if(succflag){
			
			TickRsp rspBody = rspMsg.getBody();
			
			List<Tick> ticks =  Covert2Tick(rspBody.getList());

			maps.put("reportdate",  rspMsg.getBody().getDate());

			maps.put("list", ticks);

		}
		return maps;
	}

		/**
		 * 对账单数据提取
		 * 
		 * @author kereny
		 * @date 2015-9-17 上午9:33:53
		 * @param request
		 * @param rptdate
		 * @param model
		 * @return JSonComm
		 * 
		 */
	@RequestMapping(value = "/reportcheck.htm")
	public @ResponseBody
	JSonComm findDail(HttpServletRequest request,
			@RequestParam(value = "rptdate", required = false) String rptdate,
			ModelMap model) {

		LoginRsp loginRsp = (LoginRsp) request.getSession().getAttribute(
				"userinfo");

		HashMap<String, Object> submaps = new HashMap<String, Object>();
		
		submaps.put("daily", handleDaily(loginRsp.getmID(), rptdate, loginRsp.getMemName()));

		submaps.put("fund", handleFund(loginRsp.getmID(), rptdate, loginRsp.getMemName()));
		
		submaps.put("tick", handleTick(loginRsp.getmID(), rptdate, 0, loginRsp.getMemName()));
		
		submaps.put("notice", handleTick(loginRsp.getmID(), rptdate, 1, loginRsp.getMemName()));
		
		submaps.put("attend", handleAttend(loginRsp.getmID(), rptdate, loginRsp.getMemName()));
		
		JSonComm jsdata = new JSonComm();

		jsdata.setSuccflag(0);
		jsdata.setMsg("success");
		jsdata.setData(submaps);
		jsdata.setMid(loginRsp.getmID());

		return jsdata;
	}
	
	
	
		/**
	     *  请求月报表数据
		 * @author kereny
		 * @date 2015-10-8 下午5:40:58
		 * @param request
		 * @param rptdate
		 * @param model
		 * @return
		 * JSonComm
	     *
		 */
	@RequestMapping(value = "/reportmonthly.htm")
	public @ResponseBody
	JSonComm findMonthly(HttpServletRequest request,
			@RequestParam(value = "rptdate", required = false) String rptdate,
			ModelMap model) {

		LoginRsp loginRsp = (LoginRsp) request.getSession().getAttribute(
				"userinfo");

		HashMap<String, Object> submaps = new HashMap<String, Object>();
		
		submaps.put("monthly", handleMonthly(loginRsp.getmID(), rptdate, loginRsp.getMemName()));

		JSonComm jsdata = new JSonComm();

		jsdata.setSuccflag(0);
		jsdata.setMsg("success");
		jsdata.setData(submaps);
		jsdata.setMid(loginRsp.getmID());

		return jsdata;
	}
	
	
	
		/**
	     *  日报表导出
		 * @author kereny
		 * @date 2015-9-17 下午4:08:03
		 * @param request
		 * @param response
		 * @param model
		 * @param rptdate
		 * @return
		 * @throws IOException
		 * @throws java.io.IOException
		 * String
	     *
		 */
	@RequestMapping(value = "/exportcheck.htm")
	public String exportDaily(HttpServletRequest request,
			HttpServletResponse response,
			ModelMap model,
			@RequestParam(value = "rptdate", required = false) String rptdate)
			throws IOException, java.io.IOException {
	
		
		
		LoginRsp loginRsp = (LoginRsp) request.getSession().getAttribute(
				"userinfo");
	
		HashMap<String, Object> dailymaps =handleDaily(loginRsp.getmID(), rptdate, loginRsp.getMemName());
		
		HashMap<String, Object> fundmaps = handleFund(loginRsp.getmID(), rptdate, loginRsp.getMemName());
		
		
		HashMap<String, Object> tickmaps = handleTick(loginRsp.getmID(), rptdate, 0, loginRsp.getMemName());
		
		HashMap<String, Object> noticemaps = handleTick(loginRsp.getmID(), rptdate, 1, loginRsp.getMemName());
		
		HashMap<String, Object> attendmaps = handleAttend(loginRsp.getmID(), rptdate, loginRsp.getMemName());
		
		
		Object obj = dailymaps.get("reportdate");
		String extdate = "";
		if(obj != null){
			extdate = obj.toString();
		}
	
		// 创建Excel的工作书册 Workbook,对应到一个excel文档
		HSSFWorkbook wb = new HSSFWorkbook();
		
		wb.writeProtectWorkbook("cnacex", "owner");
	
		HSSFSheet sheet = wb.createSheet(loginRsp.getMemName());
		
		int rownum = 0;
		// 总标题
		String maintitle = "中国农业交易平台";
		HSSFRow row = sheet.createRow(rownum++);
		row.setHeightInPoints((short) 24);
		HSSFCell cell = row.createCell(0);
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 16));
		
		CellStyle headcs = this.getHeadCellStyle(wb);
		cell.setCellStyle(headcs);
		cell.setCellValue(maintitle);
		
		StringBuffer exttitle  = new StringBuffer("");
		exttitle.append(loginRsp.getmID()).append("号交易商对账单(").append(extdate).append(")");
		row = sheet.createRow(rownum);
		row.setHeightInPoints((short) 24);
		cell = row.createCell(0);
		sheet.addMergedRegion(new CellRangeAddress(rownum, rownum, 0, 16));
		rownum++;
		
		cell.setCellStyle(headcs);
		cell.setCellValue(exttitle.toString());
		
		rownum = dailyAddSheet(dailymaps, sheet, rownum);
		
		rownum =  fundAddSheet(fundmaps, sheet, rownum);
		
		rownum =  tickAddSheet(tickmaps, sheet, rownum);
		
		rownum =  noticeAddSheet(noticemaps, sheet, rownum);
		
		rownum =  attendAddSheet(attendmaps, sheet, rownum);
		
		row = sheet.createRow(rownum++);
		row.setHeightInPoints(16);
		cell = row.createCell(5);
		
		row = sheet.createRow(rownum);
		row.setHeightInPoints(16);
		cell = row.createCell(0);
		sheet.addMergedRegion(new CellRangeAddress(rownum, rownum, 0, 16));
		rownum++;
		
		CellStyle titlecs = this.getTitleCellStyle(wb);
		cell.setCellStyle(titlecs);
		cell.setCellValue("签章:____________________________  签名：__________________");
		
		
		Date date=new Date();
		DateFormat format=new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
		String time=format.format(date);
		row = sheet.createRow(rownum);
		row.setHeightInPoints(16);
		cell = row.createCell(0);
		sheet.addMergedRegion(new CellRangeAddress(rownum, rownum, 0, 16));
		rownum++;
		cell.setCellStyle(titlecs);
		cell.setCellValue(time);
		
		for(int i = 0; i < 16; i++)
			sheet.autoSizeColumn(i);
		
		
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		try {
			wb.write(os);
		} catch (java.io.IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		byte[] content = os.toByteArray();
		InputStream is = new ByteArrayInputStream(content);
		ServletOutputStream out = null;
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
	
		// 设置response参数，可以打开下载页面
		response.reset();
		response.setContentType("application/vnd.ms-excel;charset=utf-8");
		
		String rptname = "对账单_"+loginRsp.getMemName()+"_"+extdate+".xls";
        String userAgent = request.getHeader("User-Agent");  

        if (userAgent.contains("MSIE")||userAgent.contains("Trident")) {
        	rptname = java.net.URLEncoder.encode(rptname, "UTF-8");
        } else {
        	rptname = new String(rptname.getBytes("UTF-8"),"ISO-8859-1");
        }
        
		response.setHeader("Content-Disposition", "attachment;filename=" + rptname );
		out = response.getOutputStream();
	
		bis = new BufferedInputStream(is);
		bos = new BufferedOutputStream(out);
	
		byte[] buff = new byte[2048];
		int bytesRead;
	
		// Simple read/write loop.
		while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
			bos.write(buff, 0, bytesRead);
		}
	
		if (bis != null)
			bis.close();
		if (bos != null)
			bos.close();
	
		return null;
	
	}
	
	
		/**
	     *  导出月报表
		 * @author kereny
		 * @date 2015-10-8 下午5:39:50
		 * @param request
		 * @param response
		 * @param model
		 * @param rptdate
		 * @return
		 * @throws IOException
		 * @throws java.io.IOException
		 * String
	     *
		 */
	@RequestMapping(value = "/exportmonthly.htm")
	public String exportMonthly(HttpServletRequest request,
			HttpServletResponse response,
			ModelMap model,
			@RequestParam(value = "rptdate", required = false) String rptdate)
			throws IOException, java.io.IOException {
	
		
		
		LoginRsp loginRsp = (LoginRsp) request.getSession().getAttribute(
				"userinfo");
	
		HashMap<String, Object> monthlymaps =handleMonthly(loginRsp.getmID(), rptdate, loginRsp.getMemName());
		

		Object obj = monthlymaps.get("reportdate");
		String extdate = "";
		if(obj != null){
			extdate = obj.toString();
		}
	
		// 创建Excel的工作书册 Workbook,对应到一个excel文档
		HSSFWorkbook wb = new HSSFWorkbook();
		
		wb.writeProtectWorkbook("cnacex", "owner");
	
		HSSFSheet sheet = wb.createSheet(loginRsp.getMemName());
		
		int rownum = 0;
		// 总标题
		String maintitle = "中国农业交易平台";
		HSSFRow row = sheet.createRow(rownum++);
		row.setHeightInPoints((short) 24);
		HSSFCell cell = row.createCell(0);
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 4));
		
		CellStyle headcs = this.getHeadCellStyle(wb);
		cell.setCellStyle(headcs);
		cell.setCellValue(maintitle);
		

		rownum = dailyAddSheet(monthlymaps, sheet, rownum);
		
		for(int i = 0; i < 5; i++)
			sheet.autoSizeColumn(i);
		
		
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		try {
			wb.write(os);
		} catch (java.io.IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		byte[] content = os.toByteArray();
		InputStream is = new ByteArrayInputStream(content);
		ServletOutputStream out = null;
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
	
		// 设置response参数，可以打开下载页面
		response.reset();
		response.setContentType("application/vnd.ms-excel;charset=utf-8");
		
		
        String userAgent = request.getHeader("User-Agent");  
        String rptname = "交易商月报表_"+loginRsp.getMemName()+"_"+extdate+".xls";
        if (userAgent.contains("MSIE")||userAgent.contains("Trident")) {
        	rptname = java.net.URLEncoder.encode(rptname, "UTF-8");
        } else {
        	rptname = new String(rptname.getBytes("UTF-8"),"ISO-8859-1");
        }
        
		response.setHeader("Content-Disposition", "attachment;filename="+rptname);
		out = response.getOutputStream();
	
		bis = new BufferedInputStream(is);
		bos = new BufferedOutputStream(out);
	
		byte[] buff = new byte[2048];
		int bytesRead;
	
		// Simple read/write loop.
		while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
			bos.write(buff, 0, bytesRead);
		}
	
		if (bis != null)
			bis.close();
		if (bos != null)
			bos.close();
	
		return null;
	
	}
		
	
	
	private int dailyAddSheet(HashMap<String, Object> data, HSSFSheet sheet, int rownum){
		CellStyle titlecs = this.getTitleCellStyle(sheet.getWorkbook());
		CellStyle captioncs = this.getCaptionCellStyle(sheet.getWorkbook());
		CellStyle numcs = this.getNumCellStyle(sheet.getWorkbook());
		CellStyle textcs = this.getTextCellStyle(sheet.getWorkbook());
		CellStyle textboldcs = this.getBoldTextCellStyle(sheet.getWorkbook());
		CellStyle menoycs = this.getMenoyCellStyle(sheet.getWorkbook());
		
		String title = data.get("title").toString();
		String mid = data.get("mid").toString();
		
		String mname = data.get("memname").toString();
		String reportdate = "";
		List<Daily> list = null;
		
		boolean succflag =((Boolean) data.get("succflag")).booleanValue();
		
		if(succflag == true){
			Object obj =  data.get("reportdate");
			if(obj != null)
				reportdate =obj.toString();
			list = (ArrayList<Daily>)data.get("list");
		}
		
		HSSFRow row = sheet.createRow(rownum);
		
		row.setHeightInPoints((short) 18);
		 
		HSSFCell cell = row.createCell(0);
		sheet.addMergedRegion(new CellRangeAddress(rownum, rownum, 0, 4));
		cell.setCellStyle(titlecs);
		cell.setCellValue(title);
		rownum++;

		row = sheet.createRow(rownum++);
		row.setHeightInPoints((short) 16);

		cell = row.createCell(0);
		cell.setCellStyle(captioncs);
		cell.setCellValue("交易商号:");

		cell = row.createCell(1);
		cell.setCellStyle(captioncs);
		cell.setCellValue(mid + "        交易商名称:");

		cell = row.createCell(2);
		cell.setCellStyle(captioncs);
		cell.setCellValue(mname);

		cell = row.createCell(3);
		cell.setCellStyle(captioncs);
		cell.setCellValue("");

		cell = row.createCell(4);
		cell.setCellStyle(captioncs);
		cell.setCellValue("日期:" + reportdate);
		
		row = sheet.createRow(rownum++);
		row.setHeightInPoints((short) 16);
		cell = row.createCell(0);
		cell.setCellStyle(textcs);
		cell.setCellValue("序号");

		cell = row.createCell(1);
		cell.setCellStyle(textcs);
		cell.setCellValue("摘要");

		cell = row.createCell(2);
		cell.setCellStyle(textcs);
		cell.setCellValue("增加");

		cell = row.createCell(3);
		cell.setCellStyle(textcs);
		cell.setCellValue("减少");

		cell = row.createCell(4);
		cell.setCellStyle(textcs);
		cell.setCellValue("余额");


		if(list != null && list.size() > 0){
			for (Daily d : list) {
				// 标题head
				row = sheet.createRow(rownum++);
				row.setHeightInPoints((short) 16);
				cell = row.createCell(0);
				cell.setCellStyle(numcs);
				cell.setCellValue(d.getSeqno());
	
				cell = row.createCell(1);
				if(d.isBold())
					cell.setCellStyle(textboldcs);
				else
					cell.setCellStyle(textcs);
				cell.setCellValue(d.getRemark());
	
				cell = row.createCell(2);
				if (d.getIncAmt() == INVALID_AMT) {
					cell.setCellStyle(textcs);
					cell.setCellValue("");
				} else {
					cell.setCellStyle(menoycs);
					cell.setCellValue(d.getIncAmt());
				}
	
				cell = row.createCell(3);
				if (d.getDecrAmt() == INVALID_AMT) {
					cell.setCellStyle(textcs);
					cell.setCellValue("");
				} else {
					cell.setCellStyle(menoycs);
					cell.setCellValue(d.getDecrAmt());
				}
	
				cell = row.createCell(4);
				if (d.getAmt() == INVALID_AMT) {
					cell.setCellStyle(textcs);
					cell.setCellValue("");
				} else {
					cell.setCellStyle(menoycs);
					cell.setCellValue(d.getAmt());
				}
			}
		}
		
		row = sheet.createRow(rownum++);
		row.setHeightInPoints(16);
		cell = row.createCell(0);
		
		return rownum;
	}

	
	private int fundAddSheet(HashMap<String, Object> data, HSSFSheet sheet, int rownum){
		CellStyle titlecs = this.getTitleCellStyle(sheet.getWorkbook());
		CellStyle captioncs = this.getCaptionCellStyle(sheet.getWorkbook());
		CellStyle textcs = this.getTextCellStyle(sheet.getWorkbook());
		CellStyle menoycs = this.getMenoyCellStyle(sheet.getWorkbook());
		
		String title = data.get("title").toString();
		String mid = data.get("mid").toString();
		
		String mname = data.get("memname").toString();
		String reportdate = "";
		List<Fund> list = null;
		
		boolean succflag =((Boolean) data.get("succflag")).booleanValue();
		
		if(succflag == true){
			list = (ArrayList<Fund>)data.get("list");
			Object obj =  data.get("reportdate");
			if(obj != null)
				reportdate =obj.toString();
		}
		
		HSSFRow row = sheet.createRow(rownum);
		row.setHeightInPoints((short) 18);
		HSSFCell cell = row.createCell(0);
		sheet.addMergedRegion(new CellRangeAddress(rownum, rownum, 0, 5));
		cell.setCellStyle(titlecs);
		cell.setCellValue(title);
		rownum++;
		
		// caption
		row = sheet.createRow(rownum++);
		row.setHeightInPoints((short) 16);

		// 创建一个Excel的单元格
		cell = row.createCell(0);
		cell.setCellStyle(captioncs);
		cell.setCellValue("交易商号");

		cell = row.createCell(1);
		cell.setCellStyle(captioncs);
		cell.setCellValue(mid);

		cell = row.createCell(2);
		cell.setCellStyle(captioncs);
		cell.setCellValue("交易商名称");

		cell = row.createCell(3);
		cell.setCellStyle(captioncs);
		cell.setCellValue(mname);

		cell = row.createCell(4);
		cell.setCellStyle(captioncs);
		cell.setCellValue("报表日期:"+reportdate);

		// 标题head
		row = sheet.createRow(rownum++);
		row.setHeightInPoints((short) 16);
		cell = row.createCell(0);
		cell.setCellStyle(textcs);
		cell.setCellValue("日期");

		cell = row.createCell(1);
		cell.setCellStyle(textcs);
		cell.setCellValue("流水号");

		cell = row.createCell(2);
		cell.setCellStyle(textcs);
		cell.setCellValue("变动类型");

		cell = row.createCell(3);
		cell.setCellStyle(textcs);
		cell.setCellValue("资金类型");

		cell = row.createCell(4);
		cell.setCellStyle(textcs);
		cell.setCellValue("入金");
		
		cell = row.createCell(5);
		cell.setCellStyle(textcs);
		cell.setCellValue("出金");

		if(list != null && list.size() > 0){
			for (Fund f : list) {
				// 标题head
				row = sheet.createRow(rownum++);
				row.setHeightInPoints((short) 16);
				cell = row.createCell(0);
				cell.setCellStyle(textcs);
				cell.setCellValue(f.getDate());
	
				cell = row.createCell(1);
				cell.setCellStyle(textcs);
				cell.setCellValue(f.getAcNo());
	
				cell = row.createCell(2);
				cell.setCellStyle(textcs);
				cell.setCellValue(f.getIaeDesc());

	
				cell = row.createCell(3);
				cell.setCellStyle(textcs);
				cell.setCellValue(f.getAccTitleName());

	
				cell = row.createCell(4);
				cell.setCellStyle(menoycs);
				cell.setCellValue(f.getInAmt());
				
				cell = row.createCell(5);
				cell.setCellStyle(menoycs);
				cell.setCellValue(f.getOutAmt());
			}
		}
		
		row = sheet.createRow(rownum++);
		row.setHeightInPoints(16);
		cell = row.createCell(0);
		
		return rownum;
	}
	
	
	private int tickAddSheet(HashMap<String, Object> data, HSSFSheet sheet, int rownum){
		CellStyle titlecs = this.getTitleCellStyle(sheet.getWorkbook());
		CellStyle captioncs = this.getCaptionCellStyle(sheet.getWorkbook());
		CellStyle textcs = this.getTextCellStyle(sheet.getWorkbook());
		CellStyle menoycs = this.getMenoyCellStyle(sheet.getWorkbook());
		CellStyle numcs = this.getNumCellStyle(sheet.getWorkbook());
		
		String title = data.get("title").toString();
		String mid = data.get("mid").toString();
		
		String mname = data.get("memname").toString();
		List<Tick> list = null;
		String reportdate = "";
		
		boolean succflag =((Boolean) data.get("succflag")).booleanValue();
		
		if(succflag == true){
			Object obj =  data.get("reportdate");
			if(obj != null)
				reportdate =obj.toString();
			
			list = (ArrayList<Tick>)data.get("list");
		}
		
		HSSFRow row = sheet.createRow(rownum);
		row.setHeightInPoints((short) 18);
		HSSFCell cell = row.createCell(0);
		sheet.addMergedRegion(new CellRangeAddress(rownum, rownum, 0, 5));
		cell.setCellStyle(titlecs);
		cell.setCellValue(title);
		rownum++;
		
		// caption
		row = sheet.createRow(rownum++);
		row.setHeightInPoints((short) 16);

		// 创建一个Excel的单元格
		cell = row.createCell(0);
		cell.setCellStyle(captioncs);
		cell.setCellValue("交易商号");

		cell = row.createCell(1);
		cell.setCellStyle(captioncs);
		cell.setCellValue(mid);

		cell = row.createCell(2);
		cell.setCellStyle(captioncs);
		cell.setCellValue("交易商名称");

		cell = row.createCell(3);
		cell.setCellStyle(captioncs);
		cell.setCellValue(mname);

		cell = row.createCell(4);
		cell.setCellStyle(captioncs);
		cell.setCellValue("");
		
		cell = row.createCell(5);
		cell.setCellStyle(captioncs);
		cell.setCellValue("");
		
		cell = row.createCell(6);
		cell.setCellStyle(captioncs);
		cell.setCellValue("");
		
		cell = row.createCell(5);
		cell.setCellStyle(captioncs);
		cell.setCellValue("日期");
		
		cell = row.createCell(6);
		cell.setCellStyle(captioncs);
		cell.setCellValue(reportdate);

		// 标题head
		row = sheet.createRow(rownum++);
		row.setHeightInPoints((short) 16);
		cell = row.createCell(0);
		cell.setCellStyle(textcs);
		cell.setCellValue("成交编号");

		cell = row.createCell(1);
		cell.setCellStyle(textcs);
		cell.setCellValue("成交时间");

		cell = row.createCell(2);
		cell.setCellStyle(textcs);
		cell.setCellValue("合同编号");

		cell = row.createCell(3);
		cell.setCellStyle(textcs);
		cell.setCellValue("商品名称");

		cell = row.createCell(4);
		cell.setCellStyle(textcs);
		cell.setCellValue("买/卖");
		
		cell = row.createCell(5);
		cell.setCellStyle(textcs);
		cell.setCellValue("数量");
		
		cell = row.createCell(6);
		cell.setCellStyle(textcs);
		cell.setCellValue("价格");

		cell = row.createCell(7);
		cell.setCellStyle(textcs);
		cell.setCellValue("合同总值");

		cell = row.createCell(8);
		cell.setCellStyle(textcs);
		cell.setCellValue("交易手续费");

		cell = row.createCell(9);
		cell.setCellStyle(textcs);
		cell.setCellValue("交易保证金");

		cell = row.createCell(10);
		cell.setCellStyle(textcs);
		cell.setCellValue("品牌");
		
		cell = row.createCell(11);
		cell.setCellStyle(textcs);
		cell.setCellValue("区域");
		
		cell = row.createCell(12);
		cell.setCellStyle(textcs);
		cell.setCellValue("交货仓库");

		if(list != null && list.size() > 0){
			for (Tick t : list) {
				// 标题head
				row = sheet.createRow(rownum++);
				row.setHeightInPoints((short) 16);
				cell = row.createCell(0);
				cell.setCellStyle(textcs);
				cell.setCellValue(t.getStrikeNo());
				
				cell = row.createCell(1);
				cell.setCellStyle(textcs);
				cell.setCellValue(t.getDod());
				
				cell = row.createCell(2);
				cell.setCellStyle(textcs);
				cell.setCellValue(t.getContNo());

	
				cell = row.createCell(3);
				cell.setCellStyle(textcs);
				cell.setCellValue(t.getCommName());
				
				cell = row.createCell(4);
				cell.setCellStyle(textcs);
				cell.setCellValue(t.getBsDesc());

	
				cell = row.createCell(5);
				cell.setCellStyle(numcs);
				cell.setCellValue(t.getVol());
				
				cell = row.createCell(6);
				if (t.getUp() == INVALID_AMT) {
					cell.setCellStyle(textcs);
					cell.setCellValue("");
				} else {
					cell.setCellStyle(menoycs);
					cell.setCellValue(t.getUp());
				}
				
				
				cell = row.createCell(7);
				cell.setCellStyle(menoycs);
				cell.setCellValue(t.getContAmt());
				
				cell = row.createCell(8);
				cell.setCellStyle(menoycs);
				cell.setCellValue(t.getTrdCharge());

	
				cell = row.createCell(9);
				cell.setCellStyle(menoycs);
				cell.setCellValue(t.getTrdMargin());
				
				cell = row.createCell(10);
				cell.setCellStyle(textcs);
				cell.setCellValue(t.getCommBrand());

	
				cell = row.createCell(11);
				cell.setCellStyle(textcs);
				cell.setCellValue(t.getDivName());
				
				cell = row.createCell(12);
				cell.setCellStyle(menoycs);
				cell.setCellValue(t.getStore());
			}
		}
		
		row = sheet.createRow(rownum++);
		row.setHeightInPoints(16);
		cell = row.createCell(0);
		
		return rownum;
	}

	
	private int noticeAddSheet(HashMap<String, Object> data, HSSFSheet sheet, int rownum){
		CellStyle titlecs = this.getTitleCellStyle(sheet.getWorkbook());
		CellStyle captioncs = this.getCaptionCellStyle(sheet.getWorkbook());
		CellStyle textcs = this.getTextCellStyle(sheet.getWorkbook());
		CellStyle menoycs = this.getMenoyCellStyle(sheet.getWorkbook());
		CellStyle numcs = this.getNumCellStyle(sheet.getWorkbook());
		
		String title = data.get("title").toString();
		String mid = data.get("mid").toString();
		
		String mname = data.get("memname").toString();
		List<Tick> list = null;
		String reportdate = "";
		
		boolean succflag =((Boolean) data.get("succflag")).booleanValue();
		
		if(succflag == true){
			Object obj =  data.get("reportdate");
			if(obj != null)
				reportdate =obj.toString();
			list = (ArrayList<Tick>)data.get("list");
		}
		
		HSSFRow row = sheet.createRow(rownum);
		row.setHeightInPoints((short) 18);
		HSSFCell cell = row.createCell(0);
		sheet.addMergedRegion(new CellRangeAddress(rownum, rownum, 0, 5));
		cell.setCellStyle(titlecs);
		cell.setCellValue(title);
		rownum++;
		
		// caption
		row = sheet.createRow(rownum++);
		row.setHeightInPoints((short) 16);

		// 创建一个Excel的单元格
		cell = row.createCell(0);
		cell.setCellStyle(captioncs);
		cell.setCellValue("交易商号");

		cell = row.createCell(1);
		cell.setCellStyle(captioncs);
		cell.setCellValue(mid);

		cell = row.createCell(2);
		cell.setCellStyle(captioncs);
		cell.setCellValue("交易商名称");

		cell = row.createCell(3);
		cell.setCellStyle(captioncs);
		cell.setCellValue(mname);

		cell = row.createCell(4);
		cell.setCellStyle(captioncs);
		cell.setCellValue("");
		
		cell = row.createCell(5);
		cell.setCellStyle(captioncs);
		cell.setCellValue("");
		
		cell = row.createCell(6);
		cell.setCellStyle(captioncs);
		cell.setCellValue("");
		
		cell = row.createCell(5);
		cell.setCellStyle(captioncs);
		cell.setCellValue("日期");
		
		cell = row.createCell(6);
		cell.setCellStyle(captioncs);
		cell.setCellValue(reportdate);

		// 标题head
		row = sheet.createRow(rownum++);
		row.setHeightInPoints((short) 16);
		cell = row.createCell(0);
		cell.setCellStyle(textcs);
		cell.setCellValue("成交编号");

		cell = row.createCell(1);
		cell.setCellStyle(textcs);
		cell.setCellValue("合同编号");

		cell = row.createCell(2);
		cell.setCellStyle(textcs);
		cell.setCellValue("商品名称");

		cell = row.createCell(3);
		cell.setCellStyle(textcs);
		cell.setCellValue("买/卖");

		cell = row.createCell(4);
		cell.setCellStyle(textcs);
		cell.setCellValue("价格");
		
		cell = row.createCell(5);
		cell.setCellStyle(textcs);
		cell.setCellValue("成交数量");
		
		cell = row.createCell(6);
		cell.setCellStyle(textcs);
		cell.setCellValue("确认数量");

		cell = row.createCell(7);
		cell.setCellStyle(textcs);
		cell.setCellValue("买入货款");

		cell = row.createCell(8);
		cell.setCellStyle(textcs);
		cell.setCellValue("卖出货款");

		cell = row.createCell(9);
		cell.setCellStyle(textcs);
		cell.setCellValue("发票监管");

		cell = row.createCell(10);
		cell.setCellStyle(textcs);
		cell.setCellValue("已开票金额");
		
		cell = row.createCell(11);
		cell.setCellStyle(textcs);
		cell.setCellValue("开票保证金");
		
		cell = row.createCell(12);
		cell.setCellStyle(textcs);
		cell.setCellValue("交收状态");
		
		cell = row.createCell(13);
		cell.setCellStyle(textcs);
		cell.setCellValue("仓单类型");

		cell = row.createCell(14);
		cell.setCellStyle(textcs);
		cell.setCellValue("品牌");
		
		cell = row.createCell(15);
		cell.setCellStyle(textcs);
		cell.setCellValue("区域");
		
		cell = row.createCell(16);
		cell.setCellStyle(textcs);
		cell.setCellValue("交货仓库");

		if(list != null && list.size() > 0){
			for (Tick t : list) {
				// 标题head
				row = sheet.createRow(rownum++);
				row.setHeightInPoints((short) 16);
				cell = row.createCell(0);
				cell.setCellStyle(textcs);
				cell.setCellValue(t.getStrikeNo());
	
		
				cell = row.createCell(1);
				cell.setCellStyle(textcs);
				cell.setCellValue(t.getContNo());
				
				cell = row.createCell(2);
				cell.setCellStyle(textcs);
				cell.setCellValue(t.getCommName());

	
				cell = row.createCell(3);
				cell.setCellStyle(textcs);
				cell.setCellValue(t.getBsDesc());
				
				cell = row.createCell(4);
				if (t.getUp() == INVALID_AMT) {
					cell.setCellStyle(textcs);
					cell.setCellValue("");
				} else {
					cell.setCellStyle(menoycs);
					cell.setCellValue(t.getUp());
				}

	
				cell = row.createCell(5);
				cell.setCellStyle(numcs);
				cell.setCellValue(t.getVol());
				
				cell = row.createCell(6);
				cell.setCellStyle(numcs);
				cell.setCellValue(t.getSureVol());
				
				
				cell = row.createCell(7);
				cell.setCellStyle(menoycs);
				cell.setCellValue(t.getInPog());
				
				cell = row.createCell(8);
				cell.setCellStyle(menoycs);
				cell.setCellValue(t.getOutPog());

	
				cell = row.createCell(9);
				cell.setCellStyle(textcs);
				cell.setCellValue(t.getInvoice());
				
				cell = row.createCell(10);
				cell.setCellStyle(menoycs);
				cell.setCellValue(t.getInvAmt());

	
				cell = row.createCell(11);
				cell.setCellStyle(menoycs);
				cell.setCellValue(t.getInvMargin());
				
				cell = row.createCell(12);
				cell.setCellStyle(textcs);
				cell.setCellValue(t.getStatusDesc());
				
				cell = row.createCell(13);
				cell.setCellStyle(textcs);
				cell.setCellValue(t.getListedType());
				
				cell = row.createCell(14);
				cell.setCellStyle(textcs);
				cell.setCellValue(t.getCommBrand());
				
				cell = row.createCell(15);
				cell.setCellStyle(textcs);
				cell.setCellValue(t.getDivName());
				
				cell = row.createCell(16);
				cell.setCellStyle(textcs);
				cell.setCellValue(t.getStore());
			}
		}
		
		row = sheet.createRow(rownum++);
		row.setHeightInPoints(16);
		cell = row.createCell(0);
		
		return rownum;
	}
	
	
	private int attendAddSheet(HashMap<String, Object> data, HSSFSheet sheet, int rownum){
		CellStyle titlecs = this.getTitleCellStyle(sheet.getWorkbook());
		CellStyle captioncs = this.getCaptionCellStyle(sheet.getWorkbook());
		CellStyle textcs = this.getTextCellStyle(sheet.getWorkbook());
		CellStyle menoycs = this.getMenoyCellStyle(sheet.getWorkbook());
		CellStyle numcs = this.getNumCellStyle(sheet.getWorkbook());
		
		String title = data.get("title").toString();
		String mid = data.get("mid").toString();
		
		String mname = data.get("memname").toString();
		List<Fund> list = null;
		String reportdate = "";
		
		boolean succflag =((Boolean) data.get("succflag")).booleanValue();
		
		if(succflag == true){
			Object obj =  data.get("reportdate");
			if(obj != null)
				reportdate =obj.toString();
			list = (ArrayList<Fund>)data.get("list");
		}
		
		HSSFRow row = sheet.createRow(rownum);
		row.setHeightInPoints((short) 18);
		HSSFCell cell = row.createCell(0);
		sheet.addMergedRegion(new CellRangeAddress(rownum, rownum, 0, 5));
		cell.setCellStyle(titlecs);
		cell.setCellValue(title);
		rownum++;
		
		// caption
		row = sheet.createRow(rownum++);
		row.setHeightInPoints((short) 16);

		// 创建一个Excel的单元格
		cell = row.createCell(0);
		cell.setCellStyle(captioncs);
		cell.setCellValue("交易商号");

		cell = row.createCell(1);
		cell.setCellStyle(captioncs);
		cell.setCellValue(mid);

		cell = row.createCell(2);
		cell.setCellStyle(captioncs);
		cell.setCellValue("交易商名称");

		cell = row.createCell(3);
		cell.setCellStyle(captioncs);
		cell.setCellValue(mname);

		cell = row.createCell(4);
		cell.setCellStyle(captioncs);
		cell.setCellValue("");
		
		cell = row.createCell(5);
		cell.setCellStyle(captioncs);
		cell.setCellValue("");
		
		cell = row.createCell(6);
		cell.setCellStyle(captioncs);
		cell.setCellValue("");
		
		cell = row.createCell(5);
		cell.setCellStyle(captioncs);
		cell.setCellValue("日期");
		
		cell = row.createCell(6);
		cell.setCellStyle(captioncs);
		cell.setCellValue(reportdate);

		// 标题head
		row = sheet.createRow(rownum++);
		row.setHeightInPoints((short) 16);
		cell = row.createCell(0);
		cell.setCellStyle(textcs);
		cell.setCellValue("成交日期");

		cell = row.createCell(1);
		cell.setCellStyle(textcs);
		cell.setCellValue("成交编号");

		cell = row.createCell(2);
		cell.setCellStyle(textcs);
		cell.setCellValue("买/卖");

		cell = row.createCell(3);
		cell.setCellStyle(textcs);
		cell.setCellValue("操作日期");

		cell = row.createCell(4);
		cell.setCellStyle(textcs);
		cell.setCellValue("交收状态");
		
		cell = row.createCell(5);
		cell.setCellStyle(textcs);
		cell.setCellValue("资金账户");
		
		cell = row.createCell(6);
		cell.setCellStyle(textcs);
		cell.setCellValue("出入类型");

		cell = row.createCell(7);
		cell.setCellStyle(textcs);
		cell.setCellValue("资金类型");

		cell = row.createCell(8);
		cell.setCellStyle(textcs);
		cell.setCellValue("入金");

		cell = row.createCell(9);
		cell.setCellStyle(textcs);
		cell.setCellValue("出金");


		if(list != null && list.size() > 0){
			for (Fund f : list) {
				// 标题head
				row = sheet.createRow(rownum++);
				row.setHeightInPoints((short) 16);
				cell = row.createCell(0);
				cell.setCellStyle(textcs);
				cell.setCellValue( f.getDod());
	
				
				cell = row.createCell(1);
				cell.setCellStyle(textcs);
				cell.setCellValue(f.getStrikeNo());
				
				cell = row.createCell(2);
				cell.setCellStyle(textcs);
				cell.setCellValue(f.getBsDesc());

	
				cell = row.createCell(3);
				cell.setCellStyle(textcs);
				cell.setCellValue(f.getOperDate());
				
				cell = row.createCell(4);
				cell.setCellStyle(textcs);
				cell.setCellValue(f.getStatusDesc());

	
				cell = row.createCell(5);
				cell.setCellStyle(textcs);
				cell.setCellValue(f.getFundAcctName());
				
				cell = row.createCell(6);
				cell.setCellStyle(textcs);
				cell.setCellValue(f.getIaeDesc());
				
				
				cell = row.createCell(7);
				cell.setCellStyle(menoycs);
				cell.setCellValue(f.getAccTitleName());
				
				cell = row.createCell(8);
				cell.setCellStyle(menoycs);
				cell.setCellValue(f.getInAmt());

	
				cell = row.createCell(9);
				cell.setCellStyle(menoycs);
				cell.setCellValue(f.getOutAmt());

			}
		}
		
		row = sheet.createRow(rownum++);
		row.setHeightInPoints(16);
		cell = row.createCell(0);
		
		return rownum;
	}


	
	private  CellStyle getHeadCellStyle(HSSFWorkbook wb){
		Font f = wb.createFont();
		f.setColor(IndexedColors.BLACK.getIndex());
		f.setFontHeightInPoints((short) 20);
		f.setBoldweight(Font.BOLDWEIGHT_BOLD);

		DataFormat df = wb.createDataFormat();
		
		CellStyle titlecs = wb.createCellStyle();
		titlecs.setFont(f);
		titlecs.setBorderLeft(CellStyle.BORDER_NONE);
		titlecs.setBorderRight(CellStyle.BORDER_NONE);
		titlecs.setBorderTop(CellStyle.BORDER_NONE);
		titlecs.setBorderBottom(CellStyle.BORDER_NONE);
		titlecs.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		titlecs.setVerticalAlignment(HSSFCellStyle.ALIGN_LEFT);
		titlecs.setDataFormat(df.getFormat("text"));
		return titlecs;
	}
	
	private  CellStyle getTitleCellStyle(HSSFWorkbook wb){
		Font ftitle = wb.createFont();
		ftitle.setFontHeightInPoints((short) 12);
		ftitle.setColor(IndexedColors.BLACK.getIndex());
		ftitle.setBoldweight(Font.BOLDWEIGHT_BOLD);
		DataFormat df = wb.createDataFormat();
		
		CellStyle titlecs = wb.createCellStyle();
		titlecs.setFont(ftitle);
		titlecs.setBorderLeft(CellStyle.BORDER_NONE);
		titlecs.setBorderRight(CellStyle.BORDER_NONE);
		titlecs.setBorderTop(CellStyle.BORDER_NONE);
		titlecs.setBorderBottom(CellStyle.BORDER_NONE);
		titlecs.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		titlecs.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		titlecs.setDataFormat(df.getFormat("text"));
		return titlecs;
	}
	
	private  CellStyle getCaptionCellStyle(HSSFWorkbook wb){
		Font fnormal = wb.createFont();
		fnormal.setFontHeightInPoints((short) 10);
		fnormal.setColor(IndexedColors.BLACK.getIndex());
		fnormal.setBoldweight(Font.BOLDWEIGHT_NORMAL);

		CellStyle captioncs = wb.createCellStyle();
		captioncs.setFont(fnormal);
		captioncs.setBorderLeft(CellStyle.BORDER_NONE);
		captioncs.setBorderRight(CellStyle.BORDER_NONE);
		captioncs.setBorderTop(CellStyle.BORDER_NONE);
		captioncs.setBorderBottom(CellStyle.BORDER_NONE);
		captioncs.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		captioncs.setVerticalAlignment(HSSFCellStyle.ALIGN_LEFT);
		return captioncs;

	}
	
	
	
	private  CellStyle getNumCellStyle(HSSFWorkbook wb){
		Font fnormal = wb.createFont();
		fnormal.setFontHeightInPoints((short) 10);
		fnormal.setColor(IndexedColors.BLACK.getIndex());
		fnormal.setBoldweight(Font.BOLDWEIGHT_NORMAL);
		
		DataFormat df = wb.createDataFormat();
		
		CellStyle numcs = wb.createCellStyle();
		numcs.setFont(fnormal);
		numcs.setBorderLeft(CellStyle.BORDER_THIN);
		numcs.setBorderRight(CellStyle.BORDER_THIN);
		numcs.setBorderTop(CellStyle.BORDER_THIN);
		numcs.setBorderBottom(CellStyle.BORDER_THIN);
		numcs.setVerticalAlignment(HSSFCellStyle.ALIGN_LEFT);
		numcs.setDataFormat(df.getFormat("0"));
		
		return numcs;

	}
	
	private  CellStyle getTextCellStyle(HSSFWorkbook wb){
		Font fnormal = wb.createFont();
		fnormal.setFontHeightInPoints((short) 10);
		fnormal.setColor(IndexedColors.BLACK.getIndex());
		fnormal.setBoldweight(Font.BOLDWEIGHT_NORMAL);
		
		DataFormat df = wb.createDataFormat();
		
		CellStyle textcs = wb.createCellStyle();
		textcs.setFont(fnormal);
		textcs.setBorderLeft(CellStyle.BORDER_THIN);
		textcs.setBorderRight(CellStyle.BORDER_THIN);
		textcs.setBorderTop(CellStyle.BORDER_THIN);
		textcs.setBorderBottom(CellStyle.BORDER_THIN);
		textcs.setVerticalAlignment(HSSFCellStyle.ALIGN_LEFT);
		textcs.setDataFormat(df.getFormat("text"));
		return textcs;

	}
	
	private  CellStyle getBoldTextCellStyle(HSSFWorkbook wb){
		Font fnormal = wb.createFont();
		fnormal.setFontHeightInPoints((short) 10);
		fnormal.setColor(IndexedColors.BLACK.getIndex());
		fnormal.setBoldweight(Font.BOLDWEIGHT_BOLD);
		
		DataFormat df = wb.createDataFormat();
		
		CellStyle textcs = wb.createCellStyle();
		textcs.setFont(fnormal);
		textcs.setBorderLeft(CellStyle.BORDER_THIN);
		textcs.setBorderRight(CellStyle.BORDER_THIN);
		textcs.setBorderTop(CellStyle.BORDER_THIN);
		textcs.setBorderBottom(CellStyle.BORDER_THIN);
		textcs.setVerticalAlignment(HSSFCellStyle.ALIGN_LEFT);
		textcs.setDataFormat(df.getFormat("text"));
		return textcs;

	}
	
	private  CellStyle getMenoyCellStyle(HSSFWorkbook wb){
		Font fnormal = wb.createFont();
		fnormal.setFontHeightInPoints((short) 10);
		fnormal.setColor(IndexedColors.BLACK.getIndex());
		fnormal.setBoldweight(Font.BOLDWEIGHT_NORMAL);
		
		DataFormat df = wb.createDataFormat();
		
		CellStyle menoycs = wb.createCellStyle();
		menoycs.setFont(fnormal);
		menoycs.setBorderLeft(CellStyle.BORDER_THIN);
		menoycs.setBorderRight(CellStyle.BORDER_THIN);
		menoycs.setBorderTop(CellStyle.BORDER_THIN);
		menoycs.setBorderBottom(CellStyle.BORDER_THIN);
		menoycs.setVerticalAlignment(HSSFCellStyle.ALIGN_LEFT);
		menoycs.setDataFormat(df.getFormat("#,##0.00"));
		return menoycs;
	}
	
	
	private List<Fund> Covert2Fund(List<Fund> list) {

		List<Fund> funds  = new ArrayList<Fund>();

		if(list == null) return null;
		double inTotalAmt = 0.00;
		double outTotalAmt = 0.00;
		
		for (Fund f: list) {
			
			if("I".equalsIgnoreCase(f.getIae())){
				f.setInAmt(f.getOccAmt());
				inTotalAmt = addX(inTotalAmt, f.getOccAmt());
				f.setIaeDesc("入金");
			}else{
				f.setOutAmt(f.getOccAmt());
				outTotalAmt = addX(outTotalAmt, f.getOccAmt());
				f.setIaeDesc("出金");
			}
			
			if(!StringUtil.nullOrBlank(f.getStatus()))
				f.setStatusDesc(StatusUtil.getBuyStatus(Integer.parseInt(f.getStatus())));
			else
				logger.error("错误数据 {}", f.toString());
			
			if("B".equalsIgnoreCase(f.getBsDirect())){
				f.setBsDesc("买入");
			}else{
				f.setBsDesc("卖出");
			}
			funds.add(f);
		}
		
		Fund tf = new Fund();
		tf.setDate("合计");
		tf.setDod("合计");
		tf.setInAmt(inTotalAmt);
		tf.setOutAmt(outTotalAmt);
		funds.add(tf);
		return funds;
	}
	
	private double addX(double v1, double v2){
	   BigDecimal b1 = new BigDecimal(Double.toString(v1));  
	   BigDecimal b2 = new BigDecimal(Double.toString(v2));  
	   return b1.add(b2).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue(); 
	}
	
	private List<Tick> Covert2Tick(List<Tick> tick) {

		List<Tick> ticks  = new ArrayList<Tick>();

		if(tick == null) return null;
		
		double totalContAmt = 0.00;
		double totaltrdCharge = 0.00;
		double totaltrdMargin = 0.00;
		long	totalVol = 0;
		long   totalSureVol = 0;
		double totalInPog = 0.00;
		double totalOutPog = 0.00;
		double totalInvAmt = 0.00;
		double totalInvMargin = 0.00;
		
		for (Tick t: tick) {
			
			if("B".equalsIgnoreCase(t.getBsDirect())){
				t.setBsDesc("买入");
				t.setInPog(t.getPog());
			}else{
				t.setBsDesc("卖出");
				t.setOutPog(t.getPog());
			}
			
			if(!StringUtil.nullOrBlank(t.getInvoice())){
				t.setInvoice("Y".equalsIgnoreCase(t.getInvoice())?"监管发票":"不监管发票");
			}else{
				t.setInvoice("未知");
			}
			
			if(!StringUtil.nullOrBlank(t.getListedType())){
				t.setListedType("M".equalsIgnoreCase(t.getListedType())?"保证金":"仓单");
			}else{
				t.setListedType("未知");
			}
			
			if(!StringUtil.nullOrBlank(t.getStatus()))
				t.setStatusDesc(StatusUtil.getBuyStatus(Integer.parseInt(t.getStatus())));
			else
				logger.error("错误数据 {}", t.toString());
			
			
			totalContAmt = addX(t.getContAmt(), totalContAmt);
			totaltrdCharge = addX(t.getTrdCharge(), totaltrdCharge);
			totaltrdMargin = addX(t.getTrdMargin(), totaltrdMargin);
			totalVol = totalVol + t.getVol();
			totalSureVol = totalSureVol + t.getSureVol();
			totalSureVol = totalSureVol + t.getSureVol();
			totalOutPog = addX(t.getOutPog(), totalOutPog);
			totalInPog = addX(t.getInPog(), totalInPog);
			totalInvAmt = addX(t.getInvAmt(), totalInvAmt);
			totalInvMargin = addX(t.getInvMargin(), totalInvMargin);
			
			
			ticks.add(t);
		}
		
		Tick totaltick = new Tick();
		totaltick.setStrikeNo("合计");
		totaltick.setContAmt(totalContAmt);
		totaltick.setTrdCharge(totaltrdCharge);
		totaltick.setTrdMargin(totaltrdMargin);
		totaltick.setVol(totalVol);
		totaltick.setSureVol(totalSureVol);
		totaltick.setInPog(totalInPog);
		totaltick.setOutPog(totalOutPog);
		totaltick.setInvAmt(totalInvAmt);
		totaltick.setInvMargin(totalInvMargin);
		totaltick.setUp(INVALID_AMT);

		ticks.add(totaltick);
		return ticks;
	}
	


	private List<Daily> Covert2Daily(DailyRsp rspbody) {

		List<Daily> dailys = new ArrayList<Daily>();
		List<SubAcctList> sublist = rspbody.getSubAcctList();
		int seqno = 1;
		
		if(sublist == null) return null;

		Daily daily = null;
		for (SubAcctList sa : sublist) {

			daily = new Daily();
			daily.setSeqno(seqno++);
			daily.setRemark(SubActUtil.getValue("rb" + sa.getSubType()));
			daily.setIncAmt(INVALID_AMT);
			daily.setDecrAmt(INVALID_AMT);
			daily.setAmt(sa.getOpenBal());
			daily.setBold(true);
			dailys.add(daily);
			List<FTList> ftlist = sa.getFtlist();
			List<FVList> fvlist = sa.getFvlist();

			for (FTList ft : ftlist) {
				if(fvlist != null){
					boolean setflag = false;
					for (FVList fv : fvlist) {
						if (Integer.parseInt(ft.getAccTitle()) == Integer
								.parseInt(fv.getAccTitle())) {
							daily = new Daily();
							daily.setSeqno(seqno++);
							daily.setRemark(ft.getAccTitleName());
	
							if ("I".equalsIgnoreCase(fv.getIae())) {
								daily.setIncAmt(fv.getOccAmt());
								daily.setDecrAmt(INVALID_AMT);
								daily.setAmt(INVALID_AMT);
							} else {
								daily.setDecrAmt(fv.getOccAmt());
								daily.setIncAmt(INVALID_AMT);
								daily.setAmt(INVALID_AMT);
							}
							dailys.add(daily);
							setflag = true;
							break;
						}
					}
					if(setflag == false){
						daily = new Daily();
						daily.setSeqno(seqno++);
						daily.setRemark(ft.getAccTitleName());
						daily.setIncAmt(INVALID_AMT);
						daily.setDecrAmt(INVALID_AMT);
						daily.setAmt(INVALID_AMT);
						dailys.add(daily);
					}
				}else{
					daily = new Daily();
					daily.setSeqno(seqno++);
					daily.setRemark(ft.getAccTitleName());
					daily.setIncAmt(INVALID_AMT);
					daily.setDecrAmt(INVALID_AMT);
					daily.setAmt(INVALID_AMT);
					dailys.add(daily);
				}
			}

			daily = new Daily();
			daily.setSeqno(seqno++);
			daily.setRemark(SubActUtil.getValue("re" + sa.getSubType()));
			daily.setAmt(sa.getCloseBal());
			daily.setIncAmt(INVALID_AMT);
			daily.setDecrAmt(INVALID_AMT);
			daily.setBold(true);

			dailys.add(daily);
		}
		
		daily = new Daily();
		daily.setSeqno(seqno++);
		daily.setRemark(SubActUtil.getValue("deduc"));
		daily.setIncAmt(INVALID_AMT);
		daily.setDecrAmt(INVALID_AMT);
		daily.setAmt(rspbody.getOffsetAmt());
		daily.setBold(true);
		dailys.add(daily);
		
		
		daily = new Daily();
		daily.setSeqno(seqno++);
		daily.setRemark(SubActUtil.getValue("avail"));
		daily.setIncAmt(INVALID_AMT);
		daily.setDecrAmt(INVALID_AMT);
		daily.setAmt(rspbody.getCloseABV());
		daily.setBold(true);
		dailys.add(daily);
		
		daily = new Daily();
		daily.setSeqno(seqno++);
		daily.setRemark(SubActUtil.getValue("desir"));
		daily.setIncAmt(INVALID_AMT);
		daily.setDecrAmt(INVALID_AMT);
		daily.setAmt(rspbody.getCloseFAV());
		daily.setBold(true);
		dailys.add(daily);

		return dailys;
	}

}
