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

<div class="container-fluid  up-datatables">
		<div class="row safe-type txtcenter bgfff up-list">
			<div class="col-xs-4"><a href="/warehouse/list.htm" >签发仓单注册</a>
			</div>
			<div class="col-xs-4"><a href="/warehouse/rolloutlist.htm" class="active">签发仓单转出</a>
			</div>
			<div class="col-xs-4"><a href="javascript:void(0);">会员绑定</a>
			</div>
		</div>
		<div class="condition">
			<div class="row">
				<div class="col-xs-12">
					<div class="input-group">
						<span class="input-group-addon">商品分类</span>
						<input type="text" class="selcomm_dialog form-control" placeholder="请选择商品">
						<input type="hidden" name="commcode" id="commcode" />
						<div class="selcomm" data-select></div>
					</div>
				</div>
				<div class="col-xs-12 mart10">
					<div class="input-group">
						<span class="input-group-addon">入库时间</span>
						<input type="text" name="contTime" id="contTime" maxlength="10" datepicker data-date-format="yyyy-mm-dd"  data-auto-close="true"  class="cinp-date form-control" />
						<span class="input-group-addon">至</span>
						<input type="text" name="econtTime" id="econtTime" maxlength="10" datepicker data-date-format="yyyy-mm-dd"  data-auto-close="true"  class="cinp-date form-control" />
					</div>
				</div>
				<div class="col-xs-12 mart10">
					<div class="input-group">
						<span class="input-group-addon">仓库编号</span>
						<input type="text" class="cinp form-control" name="storeno" maxlength="12" id="storeno" />
					</div>
				</div>
			</div>
		</div>
		<table id="dataset" class="display nowrap cell-border" cellspacing="0" width="100%">
			<thead>
				<tr>
					<th>商品种类名称</th>
					<th>交易商名称</th>
					<th>仓单编号</th>
					<th>仓库名称</th>								                
					<th>仓库编号</th>
					<th>货物数量</th>
					<th>操作</th>								                
				</tr>
			</thead>
		</table>
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
					<a href="/home.htm">交易大厅</a><span class="fa  fa-angle-right"></span><a href="/warehouse/list.htm">我的仓单</a><span class="fa  fa-angle-right"></span><a href="javascript: void(0)">会员绑定</a>
				</div>
			</div>

			<!-- main -->
			<div class="page">

				<!-- left -->
				<jsp:include page="../comm/left.jsp" flush="true" />
				<!-- left End -->

				<div class="main-content">
					<div class="bd">

						<div class="page-module data-query">
							<div class="row">
								<div class="hd">
									<h3>会员绑定</h3>
								</div>
							</div>
							<div class="row">
								<form id="J_StaticForm" action="trigmembinding.htm" method="post">
									<div class="gn-cd-bangding">
										<span>绑定状态</span>
										<span class="gn-cd-yibang">
											<c:if test="${rspBody.provId != null && rspBody.provId != ''}">
												已绑定
											</c:if>
											<c:if test="${rspBody.provId == null || rspBody.provId == ''}">
												未绑定
											</c:if>
										</span>
										<label>仓库会员账号</label><span class="gn-cd-zh">${rspBody.provId}</span>
									</div>
									<div class="gn-cd-bangding-input">
										<div class="field">
											<label for="provId">仓库会员账号</label> <input type="text" name="provId" id="provId" maxlength="12"
												<c:if test="${rspBody.provId != null && rspBody.provId != ''}"> value="<c:out value="${rspBody.provId}" />"</c:if> placeholder="请输入仓库会员账号">
										</div>
										<div class="field">
											<label id="password-label" for="passwd">仓库会员密码</label> <span id="J_StandardPwd"> <input type="password" name="passwd" id="passwd" maxlength="28" placeholder="请输入仓库会员密码">
											</span> <strong id="J_CapsLockTip" class="warning-tip" style="display: none;">Caps Lock键正处于启用状态，<br>启用它可能导致密码输入错误。
											</strong>
										</div>
										<c:if test="${rspBody.provId != null && rspBody.provId != ''}">
											<input type="hidden" name="flag" id="flag" value="1">
										</c:if>
										<c:if test="${rspBody.provId != null && rspBody.provId != ''}">
											<input type="hidden" name="flag" id="flag" value="0">
										</c:if>
										<div class="submit">
											<c:if test="${rspBody.provId != null && rspBody.provId != ''}">
												<button type="submit" class="btn-normal J_Submit" tabindex="5" id="J_SubmitStatic">取消绑定</button>
											</c:if>
											<c:if test="${rspBody.provId == null || rspBody.provId == ''}">
												<button type="submit" class="btn-normal J_Submit" tabindex="5" id="J_SubmitStatic">绑定会员</button>
											</c:if>
										</div>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- footer -->
	<jsp:include page="../comm/footer.jsp" flush="true" />
	<!-- footer End -->
</body>
</html>