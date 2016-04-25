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
    <script type="text/javascript" src="/js/stickup.js"></script>
    <title> 买方摘牌</title>
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
				<div class="backto">
					<div class="backrt">
						<a href="/home.htm">返回首页<i></i></a>
					</div>
				</div>
				<div class="crumb">
					<a href="/home.htm">交易大厅</a><span class="fa  fa-angle-right"></span><a href="/buy/list.htm">会员中心</a><span class="fa  fa-angle-right"></span><a href="/buy/list.htm">购买订单</a>
				</div>
			</div>
			
			<!-- main -->
			<div class="page">

				<div class="main-content">
					<div class="bd">
					
						<div class="page-module bsmenus">
						
							<div class="row" style="overflow:hidden;zoom:1;height:90px;margin-bottom:10px">
								<ul class="step-list">
									<li>
										<div class="linebox">
											<label for="">
												<p>订单生成</p>
												<i class="icon-step step-active">1</i>
												<span class="round">请在当日完成订单审核</span>
											</label>
										</div>
									</li>
									<li>
										<div class="linebox">
											<label for="">
												<p>审核确认</p>
												<i class="icon-step">2</i>
												<span class="round">请在当日完成订单定金支付</span>
											</label>
										</div>
									</li>
									<li  class="active">
										<div class="linebox">
											<label for="">
												<p>资金支付</p>
												<i class="icon-step">3</i>
												<span class="round">全款支付后请到交收管理中准备接收货物</span>
											</label>
										</div>
									</li>
									<li class="nobor">
										<div class="linebox noline">
											<label for="">
												<p>交收确认</p>
												<i class="icon-step"><i class="fa fa-angle-arraw">4</i></i>
												<span class="round">根据交收日期进行交收确认</span>
											</label>
										</div>
									</li>
								</ul>
							</div>
							
							<div class="sline"></div>
																				
							<div class="row bsrow">
								<div class="hd">
									<h3>处理信息</h3>
								</div>	
								<div class="bind-bd">
									<div class="title">摘牌单号：${rspBody.dsNo}</div>
									<div class="title">摘牌状态：${rspBody.statusDesc}</div>
									<div class="box">
										<table class="ui-table table-primary">
											<caption>本次摘牌费用说明</caption>
											<thead>
													<tr>
														<td>费用名称</td>
														<td>交易类型</td>
														<td>费用金额</td>
														<td>当前状态</td>
													</tr>
												</thead>
												<tbody>
													<c:forEach items="${rspBody.costPays}" var="cost" >
													<tr>
														<td>${cost.costName}</td>
														<td>${cost.trTypeName}</td>
														<td><fmt:formatNumber value="${cost.costAmt}" type="currency" pattern="￥0.00元" /></td>
														<td>${cost.flagDesc}</td>
													</tr>
													</c:forEach>
											</tbody>
										</table>																							
									</div>
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