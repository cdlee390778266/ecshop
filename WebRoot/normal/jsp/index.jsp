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
    <link type="text/css" rel="stylesheet" href="/normal/css/global.css?v=${sessionScope.buildno}" />
    <link type="text/css" rel="stylesheet" href="/normal/css/font.css?v=${sessionScope.buildno}" />
    <link type="text/css" rel="stylesheet" href="/normal/css/common.css?v=${sessionScope.buildno}" />
    <link type="text/css" rel="stylesheet" href="/normal/css/my.css?v=${sessionScope.buildno}" />
    <link rel="stylesheet" href="/normal/widget/css/ui.dialog.css?v=${sessionScope.buildno}" type="text/css"/>
    <link rel="shortcut icon" href="/normal/images/icon/favicon.ico" />
    <script type="text/javascript" src="/normal/js/jquery.js?v=${sessionScope.buildno}"></script>
    <script type="text/javascript" src="/normal/widget/js/ui.dialog.js?v=${sessionScope.buildno}"></script>
    <script type="text/javascript" src="/normal/js/sha.js?v=${sessionScope.buildno}"></script>
	<script type="text/javascript" src="/normal/js/login.js?v=${sessionScope.buildno}"></script>
	
    <title>交易中心登录</title>
</head>
<body style="background:#f5f5f5;">

	<!-- header -->
	<div class="header mt50 borderb5 padt120">
		<div class="grid-16-10 clearfix txtcenter">

			<div class="header-bd ">
				<div class="logo fl"><img src="/normal/images/logo.png" alt=""></div>
				<h1 class=" top-text fl">交易中心登录</h1>
			</div>
		</div>
	</div>
	<!-- header End -->
	<!-- wrapper -->
	<div class="wrapper">
		<div class="login-wrap">
			<div class="grid-16-10">
				<!-- main -->
				<div class="page">
					<div class="login">
						<div class="login-main txtcenter">
							
						<div id="J_LoginBox" class="mt20 login-box module-static iblock">
							
							<div class="bd">
								<div class="pan-box">
									<!--login box begin-->
								    <div id="J_Static" class="static">
									    <form id="J_StaticForm" action="login.htm" method="post">
									    
									    	<div id="J_Message" class='login-msg msg <c:if test="${!empty message}"> show</c:if>' >
												<p class="error">${message}</p>
											</div>
											
											
									        <div class="field">
									        	<label for="Ca_username">交易商账号</label>
									            <input type="text" name="mid" id="mid" class="login-text J_UserName" value="" maxlength="12" tabindex="1" placeholder="请输入交易商账号"/>            	
									        </div>
									        <div class="field">
									        	<label for="Ca_operator">操作员账号</label>
									            <input type="text" name="operid" id="operid" class="login-text J_Operator" value="" maxlength="4" tabindex="2" placeholder="请输入操作员账号"/>            	
									        </div>
									        <div class="field">
												<label id="password-label" for="Pa_password">操作员密码</label>
												
												<span id="J_StandardPwd">
													<input type="password" name="operpwd" id="operpwd" class="login-text" 
													 maxlength="16" tabindex="3" placeholder="请输入操作员密码">
												</span>  
												<strong id="J_CapsLockTip" class="warning-tip" style="display:none;">Caps Lock键正处于启用状态，<br>启用它可能导致密码输入错误。</strong>
											</div>
								              <div class="field field-checkcode rel" id="l_f_code">
												<label id="password-label" for="Pa_password">登录验证码</label>
									              <input id="mac" type="text" class="login-text checkcode J_CheckCode" maxlength="4" name="mac" tabindex="4" placeholder="请输入验证码">
									              
									              <img id="J_StandardCode_m" src="/AuthImg" width="68" height="33" data-src="" class="check-code-img">
								             </div>
								             
								             <div class="field txtleft remmber">
								             <div class="remmber-checkbox fl">
												<input type="checkbox" id="remmberflag" name="remmberflag">
												 <label for="remmberflag"></label> 
								             </div>
								           <span class="fl remmber-txt">记住用户</span> 
											 </div>
											
									        <div class="submit mart40">
									            <button type="submit" class="btn-normal J_Submit" tabindex="5" id="J_SubmitStatic">登录</button>
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
				</div>
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