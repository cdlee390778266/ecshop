<%@ page pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="keywords" content="" />
	<meta name="description" content="" />
	<base href=""/>
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	
	<meta name="author" content="" />
	<link rel="stylesheet" href="/mobile/widget/css/ui.dialog.css?v=${sessionScope.buildno}" type="text/css"/>
	<link rel="shortcut icon" href="/mobile/images/icon/favicon.ico" />
	<script type="text/javascript" src="/mobile/js/jquery.js?v=${sessionScope.buildno}"></script>
	<script type="text/javascript" src="/mobile/widget/js/ui.dialog.js?v=${sessionScope.buildno}"></script>
	<script type="text/javascript" src="/mobile/js/sha.js?v=${sessionScope.buildno}"></script>
	<script type="text/javascript" src="/mobile/js/login.js?v=${sessionScope.buildno}"></script>
	 <jsp:include page="comm/mobile.jsp" flush="true" />

	<title>会员登录</title>
</head>
<body>

	<!-- header -->
	<div class="header">
		<div class="logo"><a href="" ><img src="/mobile/images/logo.png"  alt="" class="img-responsive"  /></a></div>
	</div>
	<!-- header End -->
	<!-- wrapper -->
	<div class="container-fluid ">
		<div class="row rel">
			<!-- main -->
			<div >
				<img src="/mobile/images/login.png" alt="" class="imgW100" />
			</div>
			<div class="login col-xs-12">
				<div class="login-main">

					<div id="J_LoginBox" class="mt20 login-box module-static">

						<div class="bd ">
							<div class="pan-box">
								<!--login box begin-->
								<div id="J_Static" class="static">
									<form id="J_StaticForm" action="login.htm" method="post">
										<h1 class="padtb15">欢迎登录</h1>

										<div id="J_Message" class='form-group login-error <c:if test="${!empty message}"> show</c:if>' >
											<p class="error">${message}</p>
										</div>


										<div class=" form-group">
											<!-- <label for="Ca_username">交易商账号</label> -->
											<input type="text" name="mid" id="mid" class="form-control input-lg J_UserName" value="" maxlength="12" tabindex="1" placeholder="请输入交易商账号"/>            	
										</div>
										<div class=" form-group">
											<!-- <label for="Ca_operator">操作员账号</label> -->
											<input type="text" name="operid" id="operid" class="form-control input-lg J_Operator" value="" maxlength="4" tabindex="2" placeholder="请输入操作员账号"/>            	
										</div>
										<div class=" form-group">
													<!-- <label id="password-label" for="Pa_password">操作员密码</label>
												-->
												<span id="J_StandardPwd">
													<input type="password" name="operpwd" id="operpwd" class="form-control input-lg" 
													maxlength="16" tabindex="3" placeholder="请输入操作员密码">
												</span>  
												<strong id="J_CapsLockTip" class="warning-tip" style="display:none;">Caps Lock键正处于启用状态，<br>启用它可能导致密码输入错误。</strong>
											</div>
											<div class=" form-group rel" id="l_f_code">
												<!-- <label id="password-label" for="Pa_password">登录验证码</label> -->
												<input id="mac" type="text" class="form-control input-lg verCode J_CheckCode" maxlength="4" name="mac" tabindex="4" placeholder="请输入验证码">

												<img id="J_StandardCode_m" src="/AuthImg" width="68" height="33" data-src="" class="code">

												<!-- 	<a href="javascript: void(0)" class="change-code" id="J_StandardCode">换一张</a> -->
											</div>

											<div class="checkbox">
												<label for="">
													<input type="checkbox" id="remmberflag" name="remmberflag"> 记住用户
												</label>
											</div>

											<div class="form-group">
												<button type="submit" class="form-control input-lg btn btn-success J_Submit" tabindex="5" id="J_SubmitStatic">登　录</button>
											</div>
										</form>
									    <!--  
									    <div class="ft">
											<a href="javascript: void(0)" class="cor-red"><span>会员申请流程</span></a>
										</div>
									-->
								</div>
								<!--login box end-->
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-xs-12 lh40 bgwhite about txtcenter">
			<a href="/about/aboutus.html" class="fcgreen">关于我们>></a>
			</div>
		</div>
	</div>

	<!-- wrapper End -->
	<!-- footer -->
	<jsp:include page="comm/footer.jsp" flush="true" />
	<!-- footer End -->
	<div class="updialog  w350" id="J_BroswerMsg">
		<div class="hd">
			<span class="close dclose ic"></span>
			<h3>浏览器检测提示</h3>
		</div>
		<div class="bd">
			<div class="d-content">
				<div class="duserinfo">
					<div class="bd mt10">
						<div class="uitem" style="font-size: 14px; color:green">
							您当前使用的浏览器是：<div id="broswerinfo"></div>程序版本过老，可能存在显示或操作性问题。<br/>

							我们强烈建议您升级浏览器版本。
							<br/>官方下载地址：<div id="broswerdown"></div>

						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>