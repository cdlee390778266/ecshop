<%@ page pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="" />
<meta name="description" content="" />
<base href="" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="author" content="" />
<link rel="shortcut icon" href="/mobile/images/icon/favicon.ico" />
<link type="text/css" rel="stylesheet" href="/mobile/css/font.css" />
<link rel="stylesheet" href="/mobile/widget/css/ui.dialog.css"
	type="text/css" />
<script type="text/javascript" src="/mobile/js/jquery.js"></script>
<script type="text/javascript" src="/mobile/js/handlebars.js"></script>
<script type="text/javascript" src="/mobile/js/stickup.js"></script>
<jsp:include page="../comm/mobile.jsp" flush="true" />

<title>安全设置</title>

</head>
<body class="drawer drawer-left">
	<div class="fixed-wrapper">
		<!-- header -->
		<jsp:include page="../comm/header.jsp" flush="true" />
		<!-- header End -->
	</div>

	<div class="main safe examine addoperation " ng-controller="account">
		<div class="header">
			<div class="header-left">
				<a href="javascript:history.back(-1);"> <img
					src="/mobile/images/back.png" alt="">
				</a>
			</div>
			<div class="logo ">我的账户</div>
		</div>

		<div class="container-fluid marb15">
			<div class="row">
				<div class="col-xs-12 file bgwhite bordertb padtb15">
					<c:if test="${empty sessionScope.userinfo.operPhoto}">
						<img alt="用户头像" src="/mobile/images/portrait.jpg"
							class="img-rounded" />
					</c:if>
					<c:if test="${!empty sessionScope.userinfo.operPhoto}">
						<img alt="用户头像" src="${sessionScope.userinfo.operPhoto}"
							class="img-rounded"
							onerror="this.src='/normal/images/portrait.jpg'" />
					</c:if>

					<div class="account-mes fc666">
						<p>${sessionScope.userinfo.operName}</p>
					</div>
				</div>

				<div class="input-group form-group-lg ">
					<span class="input-group-addon "> <span
						class="glyphicon glyphicon-ok-sign"></span> 安全设置
					</span> <a href="/member/home.htm" class="form-control txtright "><span
						class=" glyphicon glyphicon-menu-right account-a"></span></a>
				</div>

				<div class="input-group form-group-lg ">
					<span class="input-group-addon "> <span
						class="glyphicon glyphicon-th-list"></span> 账户信息
					</span> <a href="/member/info.htm" class="form-control txtright "><span
						class=" glyphicon glyphicon-menu-right account-a"></span></a>
				</div>
				
				<c:choose>
					<c:when test="${sessionScope.userinfo.operType=='1'}">
						<div class="input-group form-group-lg ">
							<span class="input-group-addon "> <span
								class="glyphicon glyphicon-link"></span> 支付绑定
							</span> <a href="/member/pay.htm" class="form-control txtright "><span
								class=" glyphicon glyphicon-menu-right account-a"></span></a>
						</div>

						<div class="input-group form-group-lg ">
							<span class="input-group-addon "> <span
								class="glyphicon glyphicon-cog"></span> 操作员设置
							</span> <a href="/member/manager.htm" class="form-control txtright "><span
								class=" glyphicon glyphicon-menu-right account-a"></span></a>
						</div>
					</c:when>
					<c:otherwise>
						<div class="input-group form-group-lg ">
							<span class="input-group-addon "> <span
								class="glyphicon glyphicon-cog"></span> 账户权限
							</span> <a href="/member/right.htm" class="form-control txtright "><span
								class=" glyphicon glyphicon-menu-right account-a"></span></a>
						</div>
					</c:otherwise>
				</c:choose>

				<div class="input-group form-group-lg ">
					<span class="input-group-addon "> <span
						class="glyphicon glyphicon-log-out"></span> 安全退出
					</span> <a href="logout.htm" class="form-control txtright "><span
						class=" glyphicon glyphicon-menu-right account-a"></span></a>
				</div>
			</div>
		</div>
	</div>

	<script type="text/javascript">
		jQuery(function($) {

			$(document).ready(function() {
				$('.fixed-wrapper').stickUp();
			});
			$('.form-group-lg').click(function() {
				location.href = $(this).find('a').attr('href');
			})

		});
	</script>

</body>
</html>