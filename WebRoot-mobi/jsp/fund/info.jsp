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
    <link rel="shortcut icon" href="/images/icon/favicon.ico" />
    <link type="text/css" rel="stylesheet" href="/css/global.css" />
    <link type="text/css" rel="stylesheet" href="/css/font.css" />
    <link type="text/css" rel="stylesheet" href="/css/common.css" />
    <link type="text/css" rel="stylesheet" href="/css/member.css" />
    <script type="text/javascript" src="/js/jquery.js"></script>
    <script type="text/javascript" src="/js/leftnavs.js"></script>
    <script type="text/javascript" src="/js/ui.tabs.js"></script>
    <script type="text/javascript" src="/js/stickup.js"></script>
	<script type="text/javascript">
			$(function(){
			
				$(document).ready(function() {
					$('.fixed-wrapper').stickUp();
				});
				
				$('#tab1').tabs();
			
			});
	</script>
    <title>我的账户</title>
    
</head>
<body>

	<div class="fixed-wrapper">
	<!-- topbar -->
	<jsp:include page="../comm/topbar.jsp" flush="true" />
	
	<!-- topbar End -->
	
	<!-- header -->
	<jsp:include page="../comm/header.jsp" flush="true" />
	<!-- header End -->
	</div>
	
	<div class="wrapper">
		<div class="grid-16-16">
			<div class="crumb-nav">
				<div class="backto">
					<div class="backrt">
						<a href="/home.htm">返回首页<i></i></a>
					</div>
				</div>
				<div class="crumb">
					<a href="/home.htm">交易大厅</a><span class="fa  fa-angle-right"></span><a href="/fund/info.htm">会员中心</a><span class="fa  fa-angle-right"></span><a href="javascript: void(0)">资金账户</a>
				</div>
			</div>
			<!-- main -->
			<div class="page">
				<!-- left -->
				<jsp:include page="../comm/left.jsp" flush="true" />
				<!-- left End -->
				
				<div class="main-content">
					<div class="bd">
						
						<div class="page-module capital">
							<div class="row">
							    <div class="hd">
							   		<h3>资金账户</h3>
							    </div>
								<div class="bd mt10" id="tab1" style="border: solid 1px #e3e1f9;">
								
									<div style="background: #ebf6fa;height: 40px;padding: 10px 0 0px 30px;font-size: 14px;color: green;">
										系统总余额:<fmt:formatNumber value="${rspBody.totalBal}" type="currency" pattern="￥#,##0.00#元" /> 
										
										系统总可用余额:<fmt:formatNumber value="${rspBody.totalABal}" type="currency" pattern="￥#,##0.00#元" />
										
									
									</div>
	
								<div class="tabbar-wrap">
								  <div class="mod-tabbar">
										<ul id="J_TabBar"  style="border-top: solid 1px #e3e1f9;" class="pa-tabbar pdeta">
											<c:forEach items="${rspBody.details}" var="info" varStatus="status">
												<c:if test="${status.count == 1}">
													<li class="item tab-cell selected"><a href="javascript: void(0)">${info.acctTypeName}</a></li>
												</c:if>										
												<c:if test="${status.count != 1}">
													<li class="item tab-cell"><a href="javascript: void(0)">${info.acctTypeName}</a></li>
												</c:if>
											</c:forEach>
											</ul>
									</div>
								</div>

								<div class="sub-wrap">
								
									<c:forEach items="${rspBody.details}" var="info" varStatus="status">
										<div class="tab-content" <c:if test="${status.count != 1}"> style="display:none;" </c:if>>
											<table class="ui-table">
												<c:if test="${info.acctType=='00'}">
													<tr class="section-row">
														<td>
															<p style="font-size:18px">余额:<fmt:formatNumber value="${info.totalBal}" type="currency" pattern="￥#,##0.00#元" /></p>
														</td>
													</tr>
												</c:if>
												<tr>
													<td>
														<p class="ml20"  style="font-size:16px">可用余额:<fmt:formatNumber value="${info.avalBal}" type="currency" pattern="￥#,##0.00#元" /></p>
													</td>
												</tr>
												<tr class="section-row">
													<td><p class="ml20"  style="font-size:16px">冻结余额:<fmt:formatNumber value="${info.frzAmt}" type="currency" pattern="￥#,##0.00#元" /></p>
													</td>
												</tr>
												
												<c:if test="${info.acctType=='00'}">
												<tr>
													<td>
														<p class="ml40"  style="font-size:14px">冻结交易保证金:<fmt:formatNumber value="${info.frzMargin}" type="currency" pattern="￥#,##0.00#元" /></p>
													</td>
												</tr>
												<tr class="section-row">
													<td>
														<p class="ml40"  style="font-size:14px">冻结发票保证金:<fmt:formatNumber value="${info.frzInvMargin}" type="currency" pattern="￥#,##0.00#元" /></p>
													</td>
												</tr>
												<tr >
													<td>
														<p class="ml40"  style="font-size:14px">冻结货款:<fmt:formatNumber value="${info.frzPOG}" type="currency" pattern="￥#,##0.00#元" /></p>
													</td>
												</tr>
												<tr class="section-row">
													<td>
														<p class="ml40"  style="font-size:14px">冻结手续费:<fmt:formatNumber value="${info.frzPond}" type="currency" pattern="￥#,##0.00#元" /></p>
													</td>
												</tr>
												</c:if>
											</table>
										</div>
									</c:forEach>	
								</div>						
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- main End -->

		    </div>
	    </div>
	</div>
	<!-- wrapper End -->
	
	<!-- footer -->
	<jsp:include page="../comm/footer.jsp" flush="true" />
	<!-- footer End -->
</body>
</html>