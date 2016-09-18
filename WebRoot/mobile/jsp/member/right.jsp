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
    <link rel="shortcut icon" href="/mobile/images/icon/favicon.ico" />
    <link type="text/css" rel="stylesheet" href="/mobile/css/global.css" />
    <link type="text/css" rel="stylesheet" href="/mobile/css/font.css" />
    <link type="text/css" rel="stylesheet" href="/mobile/css/common.css" />
    <link type="text/css" rel="stylesheet" href="/mobile/css/member.css" />
    <script type="text/javascript" src="/mobile/js/jquery.js"></script>
    <script type="text/javascript" src="/mobile/js/stickup.js"></script>
    <title>账户权限</title>
    
</head>
<body class="drawer drawer-left">

	<div class="fixed-wrapper"> 
	<br><!-- topbar -->
	<jsp:include page="../comm/topbar.jsp" flush="true" />
	
	<!-- topbar End -->
	
	<!-- header -->
	<jsp:include page="../comm/header.jsp" flush="true" />
	<!-- header End -->
	<br></div>
	
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
					<a href="/home.htm">交易大厅</a><span class="fa  fa-angle-right"></span><a href="/member/home.htm">我的账户</a><span class="fa  fa-angle-right"></span>账户权限
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
						<span>我的账户</span>
					</div>
					<div class="user-navs members">
						<ul>
							<li><a href="/member/home.htm">安全设置</a></li>
							<li><a href="/member/info.htm">账户信息</a></li>
							
							<c:choose>
						   		<c:when test="${sessionScope.userinfo.operType=='1'}">  
									<li><a href="/member/pay.htm">支付绑定</a></li>
									<li><a href="/member/manager.htm">操作员设置</a></li>    
							    </c:when>
							    <c:otherwise> 
							   		<li class="current"><a href="/member/right.htm" class="mlnks">账户权限</a></li>
							    </c:otherwise>
							</c:choose>
							<li><a href="logout.htm">安全退出</a></li>
						</ul>
					</div>
				</div>
				
				
				<div class="main-content">
					<div class="bd">
							<div class="page-module permiss">
	
								<div class="row">
									<div class="hd">
										<h3>当前权限</h3>
									</div>
									<div class="bd">
	
										<div class="permiss-info">
											<dl>
												<c:if test="${fn:length(txRights)>0}">	
												<dt>交易权限</dt>
												<dd>																											
														<c:forEach items="${txRights}" var="tr">
															<span>${tr.clsName}</span>													
														</c:forEach>													
												</dd>
												</c:if>
												<c:if test="${fn:length(auRights)>0}" >	
												<dt>审核权限</dt>
												<dd>													
														<c:forEach items="${auRights}" var="ar">
															<span>${ar.clsName}</span>														
														</c:forEach>													
												</dd>
												</c:if>
												<c:if test="${PRight==true}" >	
													<dt>资金管理权限</dt>
												</c:if>
												
												<c:if test="${MRight==true}" >	
													<dt>会员管理权限</dt>
												</c:if>
											</dl>
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
	
	<script type="text/javascript">
		jQuery(function($) {
			$(document).ready(function() {
				$('.fixed-wrapper').stickUp();
			});
		});
	</script>
</body>
</html>