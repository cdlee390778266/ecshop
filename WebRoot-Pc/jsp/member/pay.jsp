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
    <title>支付绑定</title>
    
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
					<a href="/home.htm">交易大厅</a><span class="fa  fa-angle-right"></span><a href="/member/home.htm">我的账户</a><span class="fa  fa-angle-right"></span>支付绑定
				</div>
			</div>
			
			<!-- main -->
			<div class="page">
				<jsp:include page="../comm/left.jsp" flush="true" />
				
				
				<div class="main-content">
		
					<div class="bd">
						<div class="page-module" style="background-color:#fff">
							<div class="row">
								<div class="hd gp-h3">
										<h3>绑定列表</h3>
								</div>
							
							
								<div class="bd">
									<div class="bind-item">
									<div class="box">
										<table class="ui-table table-primary">
											<thead>
												<tr>
													<td class="bold">账户</td>
													<td class="bold">账户类型</td>
													<td class="bold">绑定状态</td>
												</tr>
											</thead>
											<tbody>
												<c:forEach items="${accList}" var="detail">							
													<tr>
														<td>${detail.pbAcct}</td>
														<td>${detail.pbATypeName}</td>
														<td><span class="m-bind">${detail.statusDesc}</span><span class="m-unbind">${detail.statusDesc}</span></td>
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