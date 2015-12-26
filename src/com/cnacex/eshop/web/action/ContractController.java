package com.cnacex.eshop.web.action;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.cnacex.comm.util.Config;
import com.cnacex.comm.util.StringUtil;
import com.cnacex.eshop.modul.JSonComm;
import com.cnacex.eshop.msg.body.contract.QueryListReq;
import com.cnacex.eshop.msg.body.contract.QueryListRsp;
import com.cnacex.eshop.msg.body.contract.QuerySingleRsp.SignList;
import com.cnacex.eshop.msg.body.contract.QuerySingleReq;
import com.cnacex.eshop.msg.body.contract.QuerySingleRsp;
import com.cnacex.eshop.msg.body.mall.MdseElement;
import com.cnacex.eshop.msg.body.member.LoginRsp;
import com.cnacex.eshop.msg.body.trade.delivery.Strike;
import com.cnacex.eshop.msg.xml.contract.QueryListRspMsg;
import com.cnacex.eshop.msg.xml.contract.QuerySingleRspMsg;
import com.cnacex.eshop.service.IContractService;
import com.cnacex.eshop.util.MoneyUtil;
import com.cnacex.eshop.util.StatusUtil;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.BaseFont;

/**
 * @author kereny
 * 
 */

@Controller
@RequestMapping(value = "/contract")
public class ContractController extends TradeController {

	@Autowired
	private IContractService contractService;

	static Logger logger = LoggerFactory.getLogger(ContractController.class);

	/**
	 * 合同查看
	 * 
	 * @author kereny
	 * @date 2015-6-30 上午9:51:47
	 * @param contno
	 * @param response
	 * @return
	 * @throws IOException
	 * 
	 * 
	 */
	@RequestMapping(value = "/info/{contno}.htm")
	public void detail(@PathVariable("contno") String contno,
			HttpServletResponse response) throws IOException {

		LoginRsp loginRsp = (LoginRsp) request.getSession().getAttribute(
				"userinfo");
		QuerySingleReq querySingleReq = new QuerySingleReq();
		querySingleReq.setmID(loginRsp.getmID());
		querySingleReq.setOperID(loginRsp.getOperID());
		querySingleReq.setContNo(contno);

		QuerySingleRspMsg rspMsg = contractService
				.querySingleContract(querySingleReq);
		if (rspMsg.getHead() == null) {
			createHTMLAlert(rspMsg.getFault().getRspMsg(), response);
			return;
		}

		if (rspMsg.getHead().getSuccFlag() != 1) {
			createHTMLAlert(rspMsg.getHead().getRspMsg(), response);
			return;
		}

		QuerySingleRsp rspBody = rspMsg.getBody();
		rspBody.setStatusDesc(StatusUtil.getContractStatus(rspBody.getStatus()));
		rspBody.setContAmtUpper(MoneyUtil.toRMBUpper(rspBody.getContAmt()));

		String htmlContext = null;
		if (StringUtil.nullOrBlank(rspBody.getModURL())){
			rspBody.setModURL("/home/weblogic/fmt/contract.html");
			htmlContext = FileUtils.readFileToString(
					new File(rspBody.getModURL()), "UTF-8");
		}else{
			htmlContext = getDocumentAt(rspBody.getModURL());
		}

		response.setContentType("text/html; charset=utf-8");

		htmlContext = replaceTagByFmt(rspBody, htmlContext);

		PrintWriter out = response.getWriter();

		out.print(htmlContext.toString());
		out.flush();
		out.close();
	}

	/**
	 * 合同下载
	 * 
	 * @author kereny
	 * @date 2015-6-30 上午9:51:47
	 * @param contno
	 * @param response
	 * @return String
	 * @throws DocumentException
	 * 
	 */

	@RequestMapping(value = "/download/{contno}.htm")
	public void donwload(@PathVariable("contno") String contno,
			HttpServletResponse response) throws IOException, DocumentException {

		LoginRsp loginRsp = (LoginRsp) request.getSession().getAttribute(
				"userinfo");
		QuerySingleReq querySingleReq = new QuerySingleReq();
		querySingleReq.setmID(loginRsp.getmID());
		querySingleReq.setOperID(loginRsp.getOperID());
		querySingleReq.setContNo(contno);

		QuerySingleRspMsg rspMsg = contractService
				.querySingleContract(querySingleReq);
		if (rspMsg.getHead() == null) {

			createHTMLAlert(rspMsg.getFault().getRspMsg(), response);
			return;
		}

		if (rspMsg.getHead().getSuccFlag() != 1) {
			createHTMLAlert(rspMsg.getHead().getRspMsg(), response);
			return;
		}

		QuerySingleRsp rspBody = rspMsg.getBody();
		rspBody.setStatusDesc(StatusUtil.getBuyStatus(rspBody.getStatus()));
		rspBody.setContAmtUpper(MoneyUtil.toRMBUpper(rspBody.getContAmt()));

		String htmlContext = null;
		if (StringUtil.nullOrBlank(rspBody.getModURL())){
			rspBody.setModURL("/home/weblogic/fmt/contract.html");
			htmlContext = FileUtils.readFileToString(
					new File(rspBody.getModURL()), "UTF-8");
		}else{
			htmlContext = getDocumentAt(rspBody.getModURL());
		}
			
		String contextPath = request.getSession().getServletContext()
				.getRealPath("");

		htmlContext = replaceTagByFmt(rspBody, htmlContext);

		String path = Config.getValue("DataPath");
		String fileName = contno + ".pdf";

		String pdf = path + fileName;

		OutputStream os = new FileOutputStream(pdf);
		org.xhtmlrenderer.pdf.ITextRenderer renderer = new ITextRenderer();

		renderer.setDocumentFromString(htmlContext.toString());
		ITextFontResolver fontResolver = renderer.getFontResolver();
		fontResolver.addFont(contextPath + "/fonts/simsun.ttc",
				BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);// 宋体字
		fontResolver.addFont(contextPath + "/fonts/arial.ttf",
				BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);// 宋体字
		renderer.layout();
		renderer.createPDF(os);
		os.close();

		OutputStream rsponseos = response.getOutputStream();
		try {
			response.reset();
			response.setHeader("Content-Disposition", "attachment; filename="
					+ fileName);
			response.setContentType("application/octet-stream; charset=utf-8");
			rsponseos.write(FileUtils.readFileToByteArray(new File(pdf)));
			rsponseos.flush();
		} finally {
			if (rsponseos != null) {
				rsponseos.close();
			}
		}
	}

	/**
	 * 全部列表
	 * 
	 * @author kereny
	 * @date 2015-6-30 下午5:47:17
	 * @param pageNum
	 * @param pageSize
	 * @param model
	 * @return String
	 * 
	 */
	@RequestMapping(value = "/list.htm")
	public String list(
			@RequestParam(value = "pageNum", required = false, defaultValue = "0") int pageNum,
			@RequestParam(value = "pageSize", required = false, defaultValue = "20") int pageSize,
			ModelMap model) {

		LoginRsp loginRsp = (LoginRsp) request.getSession().getAttribute(
				"userinfo");

		getUrlMatch(loginRsp.getTradeMenus(), model);
		return "contract/list";
	}

	/**
	 * JS请求全部列表
	 * 
	 * @author kereny
	 * @date 2015-6-30 下午5:47:17
	 * @param pageNum
	 * @param pageSize
	 * @param model
	 * @return JSonComm
	 * 
	 */
	@RequestMapping(value = "/findlist.htm")
	public @ResponseBody
	JSonComm findlist(
			@RequestParam(value = "pageNum", required = false, defaultValue = "0") int pageNum,
			@RequestParam(value = "pageSize", required = false, defaultValue = "20") int pageSize,
			@RequestParam(value = "status", required = false) String status,
			@RequestParam(value = "code", required = false) String code,
			@RequestParam(value = "bsType", required = false) String bsType,
			@RequestParam(value = "contNo", required = false) String contNo,
			@RequestParam(value = "strikeNo", required = false) String strikeNo,
			@RequestParam(value = "qryType", required = false) String qryType,
			@RequestParam(value = "contTime", required = false) String contTime,
			ModelMap model) {

		LoginRsp loginRsp = (LoginRsp) request.getSession().getAttribute(
				"userinfo");

		QueryListReq queryListReq = new QueryListReq();
		if (pageSize > 0) {
			queryListReq.setReqNum(Integer.toString(pageSize));
			queryListReq.setReqStart(Integer.toString(pageSize * pageNum + 1));
		}

		queryListReq.setmID(loginRsp.getmID());
		queryListReq.setOperID(loginRsp.getOperID());
		queryListReq.setCommCode(code);
		queryListReq.setBsType(bsType);
		queryListReq.setContNo(contNo);
		queryListReq.setStrikeNo(strikeNo);

		queryListReq.setStatus(status);

		if (!StringUtil.nullOrBlank(qryType) && "H".equalsIgnoreCase(qryType)) {
			queryListReq.setContTime(contTime);
		}

		JSonComm js = new JSonComm();

		QueryListRspMsg rspMsg = contractService
				.queryContractList(queryListReq);
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

		QueryListRsp rspBody = rspMsg.getBody();
		if (rspBody == null || rspBody.getContracts() == null) {
			js.setSuccflag(-1);
			js.setMsg("无合同列表");
			return js;
		}

		List<Strike> conts = rspBody.getContracts();
		List<Strike> rsconts = new ArrayList<Strike>();
		if (conts.size() > 0) {
			for (Strike c : conts) {
				c.setStatusDesc(StatusUtil.getContractStatus(c.getStatus()));

				if (loginRsp.getmID().equalsIgnoreCase(c.getSmID())) {
					c.setContractName("销售合同");
				} else {
					c.setContractName("采购合同");
				}

				rsconts.add(c);
			}
			rspBody.setContracts(rsconts);
		}
		if (pageSize > 0) {
			int nTotalNum = rspMsg.getBody().getTotalNum();

			if (nTotalNum % pageSize == 0) {
				rspMsg.getBody().setTotalPage(nTotalNum / pageSize);
			} else {
				rspMsg.getBody().setTotalPage(nTotalNum / pageSize + 1);
			}
		}

		js.setSuccflag(0);
		js.setMsg("success");
		js.setData(rspBody);
		return js;
	}
	
	
	/**
	 * JS请求全部列表
	 * 
	 * @author kereny
	 * @date 2015-6-30 下午5:47:17
	 * @param pageNum
	 * @param pageSize
	 * @param model
	 * @return JSonComm
	 * 
	 */
	@RequestMapping(value = "/findcontract.htm")
	public @ResponseBody HashMap<String, Object> findcontract(
			@RequestParam(value = "status", required = false) String status,
			@RequestParam(value = "code", required = false) String code,
			@RequestParam(value = "bsType", required = false) String bsType,
			@RequestParam(value = "contNo", required = false) String contNo,
			@RequestParam(value = "strikeNo", required = false) String strikeNo,
			@RequestParam(value = "contTime", required = false) String contTime,
			@RequestParam(value = "econtTime", required = false) String econtTime,
			ModelMap model) {

		LoginRsp loginRsp = (LoginRsp) request.getSession().getAttribute(
				"userinfo");

		QueryListReq queryListReq = new QueryListReq();
		queryListReq.setmID(loginRsp.getmID());
		queryListReq.setOperID(loginRsp.getOperID());
		queryListReq.setCommCode(code);
		queryListReq.setBsType(bsType);
		queryListReq.setContNo(contNo);
		queryListReq.setStrikeNo(strikeNo);

		queryListReq.setStatus(status);
		queryListReq.setContTime(contTime);
		queryListReq.setEcontTime(econtTime);

		HashMap<String, Object> maps = new HashMap<String, Object>();
		
		QueryListRspMsg rspMsg = contractService
				.queryContractList(queryListReq);
		
		List<Strike> rsconts = new ArrayList<Strike>();
		if (rspMsg.getHead() == null) {
			maps.put("data", rsconts);
			return maps;
		}

		if (rspMsg.getHead().getSuccFlag() != 1) {
			maps.put("data", rsconts);
			return maps;
		}

		QueryListRsp rspBody = rspMsg.getBody();
		if (rspBody == null || rspBody.getContracts() == null) {
			maps.put("data", rsconts);
			return maps;
		}

		List<Strike> conts = rspBody.getContracts();
		
		if (conts.size() > 0) {
			for (Strike c : conts) {
				c.setStatusDesc(StatusUtil.getContractStatus(c.getStatus()));
				if (loginRsp.getmID().equalsIgnoreCase(c.getSmID())) {
					c.setContractName("销售合同");
				} else {
					c.setContractName("采购合同");
				}
				MdseElement me = mallService.findLocalMdseEntity(c.getCommCode());
				if(me != null)
				{
					c.setCommName(me.getMdseName());
					c.setClsCode(me.getPmdseCode());
					
					MdseElement clsme = mallService.findLocalMdseEntity(me.getPmdseCode());
					if(clsme != null){
						c.setClsName(clsme.getMdseName());
					}
				}
				rsconts.add(c);
			}
		}
		
		maps.put("data", rsconts);
		return maps;
		
	}

	/**
	 * 
	 * 生成查看或下载异常提示
	 * 
	 * @author kereny
	 * @date 2015-6-30 下午5:47:17
	 * @param message
	 * @param response
	 * @throws IOException
	 */
	private void createHTMLAlert(String message, HttpServletResponse response)
			throws IOException {

		PrintWriter out = response.getWriter();
		response.setContentType("text/html; charset=utf-8");

		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD>");
		out.println("	<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />");
		out.println("	<TITLE>处理提示</TITLE>");
		out.println("	</HEAD>");
		out.println("  <BODY>");
		out.print(message);
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();

	}

	/**
	 * 
	 * 合同模块变量替换
	 * 
	 * @author kereny
	 * @date 2015-6-30 下午5:47:17
	 * 
	 * @param contract
	 * @param htmlContext
	 * @return String
	 */
	private String replaceTagByFmt(QuerySingleRsp contract, String htmlContext) {

		htmlContext = htmlContext.replaceAll("##卖方ID##", contract.getSmID());
		
		htmlContext = htmlContext.replaceAll("##卖方名称##", contract.getSmemName());

		htmlContext = htmlContext.replaceAll("##买方ID##", contract.getBmID());
		
		htmlContext = htmlContext.replaceAll("##买方名称##", contract.getBmemName());

		htmlContext = htmlContext.replaceAll("##合同编号##", contract.getContNo());

		htmlContext = htmlContext.replaceAll("##电子签名##", contract.getDac());

		htmlContext = htmlContext
				.replaceAll("##商品名称##", contract.getCommName());

		htmlContext = htmlContext.replaceAll("##数量##", contract.getVol()
				+ contract.getUom());

		NumberFormat currencyFmt = NumberFormat.getCurrencyInstance();

		String unitPrice = String.format("%s元/%s",
				currencyFmt.format(contract.getUp()), contract.getUom());
		htmlContext = htmlContext.replaceAll("##单价##", unitPrice);

		htmlContext = htmlContext.replaceAll("##金额##",
				currencyFmt.format(contract.getContAmt()) + "元");

		htmlContext = htmlContext.replaceAll("##金额大写##",
				contract.getContAmtUpper());

		htmlContext = htmlContext.replaceAll("##备注##", contract.getRemark());

		htmlContext = htmlContext
				.replaceAll("##签订时间##", contract.getContTime());
		
		
		
		 List<SignList> bssigns = contract.getSignLists();
		 
		 if(bssigns == null || bssigns.size() != 2){
				htmlContext = htmlContext.replaceAll("##卖方单位##", "");
				
				htmlContext = htmlContext.replaceAll("##卖方单位地址##", "");
				
				htmlContext = htmlContext.replaceAll("##卖方法人代表##", "");
				
				htmlContext = htmlContext.replaceAll("##卖方委托代理人##", "");
				
				htmlContext = htmlContext.replaceAll("##卖方电话##", "");
				
				htmlContext = htmlContext.replaceAll("##卖方开户##", "");
				
				htmlContext = htmlContext.replaceAll("##卖方账号##", "");
				
				
				htmlContext = htmlContext.replaceAll("##买方单位##", "");
				
				htmlContext = htmlContext.replaceAll("##买方单位地址##", "");
				
				htmlContext = htmlContext.replaceAll("##买方法人代表##", "");
				
				htmlContext = htmlContext.replaceAll("##买方委托代理人##", "");
				
				htmlContext = htmlContext.replaceAll("##买方电话##", "");
				
				htmlContext = htmlContext.replaceAll("##买方开户##", "");
				
				htmlContext = htmlContext.replaceAll("##买方账号##", "");
			 
		 }else{
			 
			 for(SignList s: bssigns){
				 if("S".equalsIgnoreCase(s.getSignDir())){
					 
					 if(StringUtil.nullOrBlank(s.getUnitName()))
						 htmlContext = htmlContext.replaceAll("##卖方单位##", ""); 
					 else						 
						 htmlContext = htmlContext.replaceAll("##卖方单位##", s.getUnitName());
					 
					 if(StringUtil.nullOrBlank(s.getUnitAddr()))
						 htmlContext = htmlContext.replaceAll("##卖方单位地址##", ""); 
					 else						 
						 htmlContext = htmlContext.replaceAll("##卖方单位地址##", s.getUnitAddr());
						
					 if(StringUtil.nullOrBlank(s.getLegPer()))
						 htmlContext = htmlContext.replaceAll("##卖方法人代表##", ""); 
					 else						 
						 htmlContext = htmlContext.replaceAll("##卖方法人代表##", s.getLegPer());
						
					 if(StringUtil.nullOrBlank(s.getEnProxy()))
						 htmlContext = htmlContext.replaceAll("##卖方委托代理人##", ""); 
					 else						 
						 htmlContext = htmlContext.replaceAll("##卖方委托代理人##", s.getEnProxy());
					 
					 if(StringUtil.nullOrBlank(s.getTel()))
						 htmlContext = htmlContext.replaceAll("##卖方电话##", ""); 
					 else						 
						 htmlContext = htmlContext.replaceAll("##卖方电话##", s.getTel());
						
					 if(StringUtil.nullOrBlank(s.getBank()))
						 htmlContext = htmlContext.replaceAll("##卖方开户行##", ""); 
					 else						 
						 htmlContext = htmlContext.replaceAll("##卖方开户行##", s.getBank());
						
						
					 if(StringUtil.nullOrBlank(s.getAccount()))
						 htmlContext = htmlContext.replaceAll("##卖方账号##", ""); 
					 else						 
						 htmlContext = htmlContext.replaceAll("##卖方账号##", s.getAccount());
										
				 }else{
					 
					 if(StringUtil.nullOrBlank(s.getUnitName()))
						 htmlContext = htmlContext.replaceAll("##买方单位##", ""); 
					 else						 
						 htmlContext = htmlContext.replaceAll("##买方单位##", s.getUnitName());
					 
					 if(StringUtil.nullOrBlank(s.getUnitAddr()))
						 htmlContext = htmlContext.replaceAll("##买方单位地址##", ""); 
					 else						 
						 htmlContext = htmlContext.replaceAll("##买方单位地址##", s.getUnitAddr());
						
					 if(StringUtil.nullOrBlank(s.getLegPer()))
						 htmlContext = htmlContext.replaceAll("##买方法人代表##", ""); 
					 else						 
						 htmlContext = htmlContext.replaceAll("##买方法人代表##", s.getLegPer());
						
					 if(StringUtil.nullOrBlank(s.getEnProxy()))
						 htmlContext = htmlContext.replaceAll("##买方委托代理人##", ""); 
					 else						 
						 htmlContext = htmlContext.replaceAll("##买方委托代理人##", s.getEnProxy());
					 
					 if(StringUtil.nullOrBlank(s.getTel()))
						 htmlContext = htmlContext.replaceAll("##买方电话##", ""); 
					 else						 
						 htmlContext = htmlContext.replaceAll("##买方电话##", s.getTel());
						
					 if(StringUtil.nullOrBlank(s.getBank()))
						 htmlContext = htmlContext.replaceAll("##买方开户行##", ""); 
					 else						 
						 htmlContext = htmlContext.replaceAll("##买方开户行##", s.getBank());
						
						
					 if(StringUtil.nullOrBlank(s.getAccount()))
						 htmlContext = htmlContext.replaceAll("##买方账号##", ""); 
					 else						 
						 htmlContext = htmlContext.replaceAll("##买方账号##", s.getAccount());
					 
				 }		 
			 }
		 }

		return htmlContext;
	}

	public String getDocumentAt(String urlString) { 
			StringBuffer document = new StringBuffer(); 
			try { 

				URL url = new URL(urlString); 

				URLConnection conn = url.openConnection(); 

				BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream())); 

				String line = null; 

				while ( (line = reader.readLine()) != null) { 

					document.append(line + " ");
				} 

				reader.close();
			}catch (MalformedURLException e) { 
				logger.error("Unable to connect to URL: " + urlString); 
			}catch (IOException e) {
				logger.error("IOException when connecting to URL: " + urlString);
			}
			return document.toString();
		}
}