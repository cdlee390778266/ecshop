<%@ page pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn" %> 
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
    <title>挂牌审核</title>
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
				<div class="user-mans">
					<div class="portrait-big">
						<c:if test="${empty sessionScope.userinfo.operPhoto}">
							<img  width="200" height="161" alt="" src="/images/portrait.jpg" />
						</c:if>
						<c:if test="${!empty sessionScope.userinfo.operPhoto}">
							<img  width="200" height="161" alt="" src="${sessionScope.userinfo.operPhoto}"/>
						</c:if>
					</div>
					<div class="ucenter">
						<span>买卖菜单</span>
					</div>
					<div class="user-navs members">
						<ul>
							<li class="current"><a href="/sell/list.htm">销售订单</a></li>							
						</ul>
					</div>
				</div>
				
				<div class="main-content">
					<div class="bd">
					
						<div class="page-module">
						
						<div class="row">
							<div class="hd">
								<h3 class="cor-green">挂牌状态</h3>
							</div>
							<div class="bd">
								<div class="bind-item">
									<div class="title ml20">挂牌单号：${rspBody.listedNO}</div>
							
									<div class="title ml20">处理状态：${rspBody.statusDesc}</div>
									
									<c:if test="${enablePay=='1'}">
											<div class="pa-action clearfix mt10 ml60">
												<div class="pa-btn-sell fn-fl">
														<a href="/sell/handle/P/${rspBody.listedNO}.htm" class="btn-normal btn-sell">继续支付</a>														
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
	</div>
	<!-- wrapper End -->		

	<!-- footer -->
	<jsp:include page="../comm/footer.jsp" flush="true" />
	<!-- footer End -->
</body>
</html>