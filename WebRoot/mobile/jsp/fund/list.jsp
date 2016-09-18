<%@ page pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn" %> 
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix='fmt' %> 

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="keywords" content="" />
    <meta name="description" content="" />
    <base href=""/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="author" content="" />
    <link rel="shortcut icon" href="/mobile/images/icon/favicon.ico" />
    <link type="text/css" rel="stylesheet" href="/mobile/css/font.css" />
    <link type="text/css" rel="stylesheet" href="/mobile/widget/css/ui.datepicker.css" />
    <link type="text/css" rel="stylesheet" href="/mobile/css/square/green.css">
    <link type="text/css" rel="stylesheet" href="/mobile/widget/css/ui.dialog.css" />
    <script type="text/javascript" src="/mobile/js/jquery.js"></script>
    <script type="text/javascript" src="/mobile/js/leftnavs.js"></script>
    <script type="text/javascript" src="/mobile/js/icheck.js"></script>
    <script type="text/javascript" src="/mobile/js/handlebars.js"></script>
    <script type="text/javascript" src="/mobile/js/stickup.js"></script>
    <script type="text/javascript" src="/mobile/widget/js/ui.dialog.js"></script>
    <script type="text/javascript" src="/mobile/widget/js/ui.datepicker.js"></script>
    
    <jsp:include page="../comm/datatables.jsp" flush="true" />

    <jsp:include page="../comm/mobile.jsp" flush="true" />
    
    <script type="text/javascript" src="/mobile/js/handle.fund.js?v=${sessionScope.buildno}"></script>
    <title>账务明细</title>
    
</head>
<body class="drawer drawer-left">

	<div class="fixed-wrapper">

	<!-- header -->
	<jsp:include page="../comm/header.jsp" flush="true" />
	<!-- header End -->
	
	</div>



	<div class="container-fluid bordert">
		<div class="row safe-type txtcenter bgwhite  borderb">
			<div class="col-xs-6"><a href="/fund/info.htm" >资金账户</a>
			</div>
			<div class="col-xs-6"><a href="/fund/list.htm" class="active" >账务明细</a></div>
		</div>

          <table class="ui-table">
										<tbody>
										<tr>										
											<td class="ctr" width="120px" >账务日期：</td>											
											<td>
												<input type="text" name="beginDate" id="beginDate" maxlength="10" datepicker data-date-format="yyyy-mm-dd"  data-auto-close="true" class="cinp-date ml10" />
												至													
												<input type="text" name="endDate" id="endDate" maxlength="10" datepicker data-date-format="yyyy-mm-dd"  data-auto-close="true" class="cinp-date" />																									
											</td>	
											<td class="ctr">收支类型：</td>
												<td>
													<select class="csel" name="iae" id="iae">
														<option value="" selected>所有资金</option>
														<option value="I" >资金收入</option>
														<option value="E">资金支出</option>
													</select>
												</td>	
												<td class="ctr">费用代码：</td>
												<td>
													<select class="csel" name="costCode" id="costCode">
														<option value="" selected>所有费用</option>
														<c:forEach items="${costList}" var="cost" >
															<option value="${cost.costCode}">${cost.costName}</option>
														</c:forEach>
													</select>
												</td>									
										</tr>
										<tr>
												<td class="ctr">账务编号：</td>
												<td><input type="text" class="cinp" id="origAcNo" name="origAcNo"  />
												</td>
												<td class="ctr">交易单号：</td>
												<td colspan="3">
													<input type="text" class="cinp" id="extNo" name="extNo"  />
												</td>
											</tr>										
										</tbody>
									</table>

                          <table id="dataset" class="display nowrap cell-border" cellspacing="0" width="100%">
								        <thead>
								            <tr>
								            	<th>交易类型</th>
												<th>资金科目</th>
												<th>账务编号</th>
												<th>发生时间</th>
				                    			<th>变动金额(元)</th>
												<th>总余额(元)</th>
												<th>可用余额(元)</th>
				                    			<th>备注</th>						                
								            </tr>
								        </thead>
								    </table>	


	</div>



	<!-- 
	<div class="wrapper">
		<div class="grid-16-16"> -->
			
			<!-- main -->
		<!-- 	<div class="">
			
				
				<div class="main-content">
					<div class="bd">
						
						<div class="page-module data-query">
							<div class="row">
							    <div class="hd">
							   		<h3>账务明细</h3>
							    </div>
							    <div class="bd mt10 warehouse">
								<div class="row">
									<table class="ui-table">
										<tbody>
										<tr>										
											<td class="ctr" width="120px" >账务日期：</td>											
											<td>
												<input type="text" name="beginDate" id="beginDate" maxlength="10" datepicker data-date-format="yyyy-mm-dd"  data-auto-close="true" class="cinp-date ml10" />
												至													
												<input type="text" name="endDate" id="endDate" maxlength="10" datepicker data-date-format="yyyy-mm-dd"  data-auto-close="true" class="cinp-date" />																									
											</td>	
											<td class="ctr">收支类型：</td>
												<td>
													<select class="csel" name="iae" id="iae">
														<option value="" selected>所有资金</option>
														<option value="I" >资金收入</option>
														<option value="E">资金支出</option>
													</select>
												</td>	
												<td class="ctr">费用代码：</td>
												<td>
													<select class="csel" name="costCode" id="costCode">
														<option value="" selected>所有费用</option>
														<c:forEach items="${costList}" var="cost" >
															<option value="${cost.costCode}">${cost.costName}</option>
														</c:forEach>
													</select>
												</td>									
										</tr>
										<tr>
												<td class="ctr">账务编号：</td>
												<td><input type="text" class="cinp" id="origAcNo" name="origAcNo"  />
												</td>
												<td class="ctr">交易单号：</td>
												<td colspan="3">
													<input type="text" class="cinp" id="extNo" name="extNo"  />
												</td>
											</tr>										
										</tbody>
									</table>
								</div>
								<div class="row">								
								    <table id="dataset" class="display nowrap cell-border" cellspacing="0" width="100%">
								        <thead>
								            <tr>
								            	<th>交易类型</th>
												<th>资金科目</th>
												<th>账务编号</th>
												<th>发生时间</th>
				                    			<th>变动金额(元)</th>
												<th>总余额(元)</th>
												<th>可用余额(元)</th>
				                    			<th>备注</th>						                
								            </tr>
								        </thead>
								    </table>																																					
								</div>  								
							</div>
						</div>
					</div>
				</div>
			</div> -->
			<!-- main End -->

	<!-- 	    </div>
	    </div>
	</div> -->
	<!-- wrapper End -->
	<!-- footer -->
	<jsp:include page="../comm/footer.jsp" flush="true" />
	<!-- footer End -->
	
	
</body>
</html>