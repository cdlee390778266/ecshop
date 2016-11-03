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
   
    <link type="text/css" rel="stylesheet" href="/mobile/css/font.css" />
    <script type="text/javascript" src="/mobile/js/jquery.js"></script>
    <script type="text/javascript" src="/mobile/js/stickup.js"></script>

    <jsp:include page="../comm/mobile.jsp" flush="true" />

    <title>账户权限</title>
    
</head>
<body class="drawer drawer-left bgfff">

	<div class="fixed-wrapper"> 
	<!-- header -->
	<jsp:include page="../comm/header.jsp" flush="true" />
	<!-- header End -->
	</div>
	
	<div class="main safe examine addoperation ">
		<div class="header">
			<div class="header-left">
				<a href="javascript:history.back(-1);"> <img src="/mobile/images/back.png" alt="">
				</a>
			</div>
			<div class="logo ">账户权限</div>
		</div>
		<div class="container-fluid bgfff juris">
			<div class="row">
				<div class="col-xs-12">
					
						<c:if test="${fn:length(txRights)>0}">
						<dl>	
						<dt>交易权限</dt>
						<dd>																											
							<c:forEach items="${txRights}" var="tr">
								<span>${tr.clsName}</span>													
							</c:forEach>													
						</dd>
						</dl>
						</c:if>
						
						<c:if test="${fn:length(auRights)>0}" >
						<dl>	
						<dt>审核权限</dt>
						<dd>													
							<c:forEach items="${auRights}" var="ar">
								<span>${ar.clsName}</span>														
							</c:forEach>													
						</dd>
						</dl>
						</c:if>
						<c:if test="${PRight==true}" >
						    <dl>	
							<dt>资金管理权限</dt>
							</dl>
						</c:if>
						
						<c:if test="${MRight==true}" >
						    <dl>	
							<dt>会员管理权限</dt>
							</dl>
						</c:if>
				</div>
			</div>
		</div>
	</div>		

	<script type="text/javascript">
		jQuery(function($) {
			$(document).ready(function() {
				$('.fixed-wrapper').stickUp();
			});
		});
	</script>
</body>
</html>