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
	<link type="text/css" rel="stylesheet" href="/widget/css/ui.datepicker.css" />
	<link type="text/css" rel="stylesheet" href="/css/selecttags.css" >
	<link type="text/css" rel="stylesheet" href="/widget/css/ui.dialog.css" />
    <script type="text/javascript" src="/js/jquery.js"></script>
    <script type="text/javascript" src="/js/sha.js"></script>
    <script type="text/javascript" src="/js/leftnavs.js"></script>
	<script type="text/javascript" src="/widget/js/ui.datepicker.js"></script>
	<script type="text/javascript" src="/js/handlebars.js"></script>
    <script type="text/javascript" src="/js/ui.pagination.js"></script>
    <script type="text/javascript" src="/js/selecttags.js"></script>
    <script type="text/javascript" src="/js/stickup.js"></script>
    <script type="text/javascript" src="/widget/js/ui.dialog.js"></script>
    
    <jsp:include page="../comm/datatables.jsp" flush="true" />
    
    <script type="text/javascript" src="/js/handle.membinding.js?v=${sessionScope.buildno}"></script>
    <title>仓单管理</title>
    
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
								<div class="bd">
									<div class="pan-box">
										<div id="J_Static" class="static">
									    	<form id="J_StaticForm" action="trigmembinding.htm" method="post">
												<div class="field">
													<label for="provId">仓库会员账号</label>
													<input type="text" name="provId" id="provId" maxlength="12" <c:if test="${rspBody.provId != null && rspBody.provId != ''}"> value="<c:out value="${rspBody.provId}" />"</c:if> placeholder="请输入仓库会员账号">            	
												</div>
												<div class="field">
													<label id="password-label" for="passwd">仓库会员密码</label>
													<span id="J_StandardPwd">
														<input type="password" name="passwd" id="passwd" maxlength="28" placeholder="请输入仓库会员密码">
													</span>  
													<strong id="J_CapsLockTip" class="warning-tip" style="display:none;">Caps Lock键正处于启用状态，<br>启用它可能导致密码输入错误。</strong>
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
											</form>
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
	
	<!-- footer -->
	<jsp:include page="../comm/footer.jsp" flush="true" />
	<!-- footer End -->
</body>
</html>