<%@ page pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix='fmt'%>

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
	<link type="text/css" rel="stylesheet" href="/mobile/widget/css/ui.datepicker.css" />
	<link type="text/css" rel="stylesheet" href="/mobile/css/selecttags.css">
	<link type="text/css" rel="stylesheet" href="/mobile/widget/css/ui.dialog.css" />
	<link type="text/css" rel="stylesheet" href="/mobile/css/my.css" />
	<script type="text/javascript" src="/mobile/js/jquery.js"></script>
	<script type="text/javascript" src="/mobile/js/sha.js"></script>
	<script type="text/javascript" src="/mobile/widget/js/ui.datepicker.js"></script>
	<script type="text/javascript" src="/mobile/js/handlebars.js"></script>
	<script type="text/javascript" src="/mobile/js/ui.pagination.js"></script>
	<script type="text/javascript" src="/mobile/js/selecttags.js"></script>
	<script type="text/javascript" src="/mobile/js/stickup.js"></script>
	<script type="text/javascript" src="/mobile/widget/js/ui.dialog.js"></script>

	<jsp:include page="../comm/datatables.jsp" flush="true" />
	<jsp:include page="../comm/mobile.jsp" flush="true" />

	<script type="text/javascript" src="/mobile/js/handle.membinding.js?v=${sessionScope.buildno}"></script>

	<title>仓单管理</title>

</head>
<body class="drawer drawer-left">

	<div class="fixed-wrapper">
		<!-- header -->
		<jsp:include page="../comm/header.jsp" flush="true" />
		<!-- header End -->
	</div>

	<div class="container-fluid examine safe">

		<div class="row safe-type txtcenter bgfff  borderb pwdman-setting">
			<div class="col-xs-4"><a href="/warehouse/list.htm">签发仓单注册</a>
			</div>
			<div class="col-xs-4"><a href="/warehouse/rolloutlist.htm" >签发仓单转出</a>
			</div>
			<div class="col-xs-4"><a href="javascript:void(0);" class="active">会员绑定</a>
			</div>
		</div>

		<form id="J_StaticForm" action="trigmembinding.htm" method="post">

			<div class="row cd-binding">
				<div class="col-xs-12 col-sm-6">
					仓库会员账号：${rspBody.provId}
				</div>
				<div class="col-xs-12 col-sm-6">
					绑定状态：
					<c:if test="${rspBody.provId != null && rspBody.provId != ''}">
					<span class="fcgreen">
						已绑定
					</span>
				</c:if>
				<c:if test="${rspBody.provId == null || rspBody.provId == ''}">
				<span class="fcyellow">
					未绑定
				</span>
			</c:if>
		</div>
	</div>

	<div class="row">

		<div class="input-group form-group-lg bgfff">
			<span class="input-group-addon ">仓库会员账号</span>
			<input class="form-control" type="text" name="provId" id="provId" maxlength="12" <c:if test="${rspBody.provId != null && rspBody.provId != ''}"> value="<c:out value="${rspBody.provId}" />"</c:if> placeholder="请输入仓库会员账号">
		</div>

		<div class="input-group  form-group-lg bgfff">
			<span class="input-group-addon ">仓库会员密码</span>
			<input type="password" name="passwd" id="passwd" maxlength="28" placeholder="请输入仓库会员密码" class="form-control">
		</div>

	</div>

	<div class="col-xs-12 mart10 mart10 marb60">

		<c:if test="${rspBody.provId != null && rspBody.provId != ''}">
		<input type="hidden" name="flag" id="flag" value="1">
	</c:if>

	<c:if test="${rspBody.provId != null && rspBody.provId != ''}">
	<input type="hidden" name="flag" id="flag" value="0">
</c:if>

<c:if test="${rspBody.provId != null && rspBody.provId != ''}">
<button type="submit" class="btn btn-warning btn-block " tabindex="5" id="J_SubmitStatic">取消绑定</button>
</c:if>

<c:if test="${rspBody.provId == null || rspBody.provId == ''}">
<button type="submit" class="btn btn-warning btn-block " tabindex="5" id="J_SubmitStatic">绑定会员</button>
</c:if>

</div>

</form>

</div>

<!-- footer -->
<jsp:include page="../comm/footer.jsp" flush="true" />
<!-- footer End -->

</body>
</html>