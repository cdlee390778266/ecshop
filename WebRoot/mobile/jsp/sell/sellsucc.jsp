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
    <link rel="shortcut icon" href="/mobile/images/icon/favicon.ico" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="author" content="" />
    <link type="text/css" rel="stylesheet" href="/mobile/css/font.css" />
    <script type="text/javascript" src="/mobile/js/jquery.js"></script>
    <script type="text/javascript" src="/mobile/js/stickup.js"></script>

     <jsp:include page="../comm/mobile.jsp" flush="true" />

    <title>卖方挂牌</title>
    <script type="text/javascript">
    

	$(function() {

		$(document).ready(function() {
				$('.fixed-wrapper').stickUp();
		});
    });
    </script>
</head>
<body class="drawer drawer-left">

	<div class="fixed-wrapper"> 
	<!-- header -->
	<jsp:include page="../comm/header.jsp" flush="true" />
	<!-- header End -->
	</div>

	<div class="container-fluid">
		
<c:if test="${listedType=='M'}">

<div class="row">
			<div class="col-xs-12 mart30 fc777">
				挂牌处理
			</div>
			<div class="col-xs-12 martb10">
				挂牌单号：<span class="fcgreen">${listed.listedNo}</span>
			</div>
		</div>

								
										<table class="table table-bordered table-responsive table-hover">
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
														<td>
														<span class="fcgreen">
														<fmt:formatNumber value="${cost.costAmt}" type="currency" pattern="￥0.00" /> </span> 元
														</td>
														<td>${cost.flagDesc}</td>
													</tr>
													</c:forEach>
											</tbody>
										</table>
										
										<c:if test="${enableAudit=='1'}">
										
										<div class="pa-action ">
											<div class="pa-btn-sell txtcenter padtb40">
												<a href="/sell/handle/A/${listed.listedNo}.htm" class="btn-normal btn-sell btn btn-warning">继续审核</a>
											</div>
										</div>
											
											
										</c:if>
										
									
									</c:if>


										<c:if test="${listedType=='W'}">
								<div class="row">
									<div class="col-xs-12">挂牌单号：<span class="fcgreen">${listed.listedno}</span></div>
									<div class="col-xs-12">挂牌状态：<span class="fcgreen">${listed.statusDesc}</span></div>
									<div class="box">
										<c:if test="${enableAudit=='1'}">
										
										<div class="pa-action clearfix mt10 ml60">
											<div class="pa-btn-sell txtcenter padtb40">
												<a href="/sell/handle/A/${listed.listedno}.htm" class="btn-normal btn-sell">继续审核</a>
											</div>
										</div>
											
											
										</c:if>
										
										</div>
									</div>

									</c:if>




	</div>
	
	<!-- wrapper -->
	<!-- <div class="wrapper service-full mt30">
		<div class="grid-16-16">
		
			
			
			<div class="">
				
				
				<div class="main-content">
					<div class="bd">
					
						<div class="page-module">
							
						<div class="row">
							<div class="hd">
								<h3 class="cor-green">挂牌处理</h3>
							</div>
							<div class="bd">
								<c:if test="${listedType=='M'}">
								<div class="bind-item">
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
										
										<div class="pa-action clearfix mt10 ml60">
											<div class="pa-btn-sell fn-fl">
												<a href="/sell/handle/A/${listed.listedNo}.htm" class="btn-normal btn-sell">继续审核</a>
											</div>
										</div>
											
											
										</c:if>
										
										</div>
									</div>
									</c:if>
									<c:if test="${listedType=='W'}">
								<div class="bind-item">
									<div class="title ml20">挂牌单号：${listed.listedno}</div>
									<div class="title ml20">挂牌状态：${listed.statusDesc}</div>
									<div class="box">
										<c:if test="${enableAudit=='1'}">
										
										<div class="pa-action clearfix mt10 ml60">
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
	</div> -->
	<!-- wrapper End -->		

	<!-- footer -->
	<jsp:include page="../comm/footer.jsp" flush="true" />
	<!-- footer End -->
</body>

</html>