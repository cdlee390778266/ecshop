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
    <link rel="shortcut icon" href="/normal/images/icon/favicon.ico" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="author" content="" />
    <link type="text/css" rel="stylesheet" href="/normal/css/global.css" />
    <link type="text/css" rel="stylesheet" href="/normal/css/font.css" />
    <link type="text/css" rel="stylesheet" href="/normal/css/common.css" />
    <link type="text/css" rel="stylesheet" href="/normal/css/member.css" />
    <script type="text/javascript" src="/normal/js/jquery.js"></script>
    <script type="text/javascript" src="/normal/js/stickup.js"></script>
    <title>卖方挂牌</title>
    <script type="text/javascript">
    

	$(function() {

		$(document).ready(function() {
				$('.fixed-wrapper').stickUp();
		});
    });
    </script>
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
	
	<!-- wrapper -->
	<div class="wrapper service-full mt30">
		<div class="grid-16-16">
			<div class="crumb-nav">
				<div class="crumb">
					<a href="/home.htm">交易大厅</a><span class="fa  fa-angle-right"></span><a href="/sell/list.htm">会员中心</a><span class="fa  fa-angle-right"></span><a href="/sell/list.htm">销售订单</a>
				</div>
			</div>
			
			<!-- main -->
			<div class="page">
		
				<div class="main-content">
					<div class="bd">
					
						<div class="page-module">
							
						<div class="row">
							<div class="hd">
								<h3 class="cor-green">挂牌处理</h3>
							</div>
							<div class="bd">
								<c:if test="${listedType=='M'}">
								<div class="bind-item up-bind-item">
									<div class="title ml20">挂牌单号：${listed.listedNo}</div>
									
									<div class="box">
										<table class="ui-table table-primary">
											<caption>本次挂牌费用说明</caption>
											<thead>
													<tr>
														<td>费用名称</td>
														<td>交易类型</td>
														<td>费用金额</td>
														<td>当前状态</td>
													</tr>
												</thead>
												<tbody>
													<c:forEach items="${listed.costPays}" var="cost" >
													<tr>
														<td>${cost.costName}</td>
														<td>${cost.trTypeName}</td>
														<td><fmt:formatNumber value="${cost.costAmt}" type="currency" pattern="￥0.00元" /></td>
														<td>${cost.flagDesc}</td>
													</tr>
													</c:forEach>
											</tbody>
										</table>
										
										<c:if test="${enableAudit=='1'}">
										
										<div class=" clearfix mart40 txtcenter">
											<div class="pa-btn-sell ">
												<a href="/sell/handle/A/${listed.listedNo}.htm" class="btn-normal btn-sell">继续审核</a>
											</div>
										</div>
											
											
										</c:if>
										
										</div>
									</div>
									</c:if>
									<c:if test="${listedType=='W'}">
									<div class="bind-item  up-bind-item">
										<div class="title ml20">挂牌单号：${listed.listedno}</div>
										<!-- <div class="title ml20">挂牌状态：${listed.statusDesc}</div> -->
										<div class="box">
										<c:if test="${enableAudit=='1'}">
										<div class="pa-action clearfix mt10 ">
											<div class="pa-btn-sell fn-fl">
												<a href="/sell/handle/A/${listed.listedno}.htm" class="btn-normal btn-sell">继续审核</a>
											</div>
										</div>
										</c:if>
										</div>
									</div>
									</c:if>
								</div>
							</div>
						</div>	
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- wrapper End -->		

	<!-- footer -->
	<jsp:include page="../comm/footer.jsp" flush="true" />
	<!-- footer End -->
</body>
</html>