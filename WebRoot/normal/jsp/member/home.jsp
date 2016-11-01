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
	<link rel="shortcut icon" href="/normal/images/icon/favicon.ico" />
	<link type="text/css" rel="stylesheet" href="/normal/css/global.css" />
	<link type="text/css" rel="stylesheet" href="/normal/css/font.css" />
	<link type="text/css" rel="stylesheet" href="/normal/css/common.css" />
	<link type="text/css" rel="stylesheet" href="/normal/css/member.css" />
	<link rel="stylesheet" href="/normal/widget/css/ui.dialog.css" type="text/css"/>
	<script type="text/javascript" src="/normal/js/jquery.js"></script>
	<script type="text/javascript" src="/normal/js/handlebars.js"></script>
	<script type="text/javascript" src="/normal/widget/js/ui.dialog.js"></script>
	<script type="text/javascript" src="/normal/js/ui.tips.js"></script>
	<script type="text/javascript" src="/normal/js/sha.js"></script>
	<script type="text/javascript" src="/normal/js/stickup.js"></script>
	<script type="text/javascript" src="/normal/js/leftnavs.js"></script>

	<title>安全设置</title>

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
					<a href="/home.htm">交易大厅</a><span class="fa  fa-angle-right"></span><a href="/member/home.htm">我的账户</a><span class="fa  fa-angle-right"></span>安全设置
				</div>
			</div>
			
			<!-- main -->
			<div class="page">
				<jsp:include page="../comm/left.jsp" flush="true" />
				
				
				<div class="main-content">
					<div class="bd">
						<div class="page-module pwdman">
							<div class="row">
								<div class="hd gp-h3">
									<h3 >密码管理</h3>
								</div>
								<div class="pwdman-setting">

									<div class="pwman-title">
										<span class="loginPwd" data-type="1" data-href="J_SetPwd">登录密码</span>
										<span class="zj" data-type="2" data-href="J_SetPwd">资金密码</span>
									</div>
									<div class="pwman-body">

										<div class="pwman-box" id="J_SetPwd">
											<div class="pwman-row">
												<div class="pwman-coll">	
												</div>
												<div class="pwman-colr red">
													互联网用户账户存在盗号风险，建议定期修改账户密码!
												</div>
											</div>

											<div class="pwman-row">
												<div class="pwman-coll">
													<span id="prompt_old">用户</span>密码	
												</div>
												<div class="pwman-colr">
													<input type="password" id="oldpassword" class="inp w160 modifyPwd" maxlength="16" name="oldpassword" tabindex="1" onpaste="return false" oncopy="return false"/ >
												</div>
											</div>
											<div class="pwman-row">
												<div class="pwman-coll">
													<span id="prompt_new">用户</span>新密码
												</div>
												<div class="pwman-colr ">
													<input type="password" id="newpassword" class="inp w160 modifyPwd" maxlength="16"  name="newpassword" tabindex="2" onpaste="return false" oncopy="return false" />
												</div>
											</div>
											<div class="pwman-row">
												<div class="pwman-coll">
													<span id="prompt_conf">用户</span>确认密码	
												</div>
												<div class="pwman-colr">
													<input type="password" id="confpassword" class="inp w160 modifyPwd" maxlength="16"  name="confpassword" tabindex="3" onpaste="return false" oncopy="return false" />
												</div>
											</div>
											<div class="pwman-row errmsg">
												<div class="pwman-coll">	
												</div>
												<div class="pwman-colr red">
													<span class="rsmsg"></span>
												</div>
											</div>
											<div class="pt10 pwman-row">
												<div class="pwman-coll"></div>
												<div class="pwman-colr">
												<button class="pwdman-btn" data-value="1" id="confirmbtn" tabindex="3" >确&nbsp;&nbsp;认</button>
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
		$(function(){

        	var setpwd='/member/setpwd.htm'; //登录密码
          	var fundsetpwd='/member/fundsetpwd.htm';//资金密码

          	function noTips(type){    
          		$("#oldpassword").val(b64_sha1($.trim($("#oldpassword").val())));
          		$("#newpassword").val(b64_sha1($.trim($("#newpassword").val())));
				var formParam = $(".modifyPwd").serialize();//序列化表格内容为字符串  
				var url = type==1 ? setpwd : fundsetpwd ;
				$.ajax({    
					type:'post',        
					url:url,    
					data:formParam,    
					cache:false,    
					dataType:'json',    
					success:function(data){

						$(".modifyPwd").val('');  
						if(data.succflag==0){					        	
							// Dialog.mask.hide();
							// Dialog.node.hide();
							$.Tips({'message':'密码修改成功','type':'info'});	
						}else{
							$('.errmsg').show();
							$('.rsmsg').html(data.msg);
						}
					}    
				});    
			}  

			$('.pwman-title').on('click','span',function(e){
				$('.pwman-title span').removeClass('active');
				$(this).addClass('active');	
				$(".modifyPwd").val('');
				$('.rsmsg').html('');
				var current = $(this).attr('data-href'),type=$(this).attr('data-type');
				$('#confirmbtn').attr('data-value',type);
				
				if(type == 1){
					$('#prompt_old').text('用户');
					$('#prompt_new').text('用户');
					$('#prompt_conf').text('用户');
				}else{
					$('#prompt_old').text('资金');
					$('#prompt_new').text('资金');
					$('#prompt_conf').text('资金');
				}
				// Dialog = UP.Dialog(current);	
				e.preventDefault();
			})

			/**  初始化 **/
			$('.pwman-title span').removeClass('active');
			$('.loginPwd').addClass('active');	
			$('.loginPwd').click();


			$('#cancelbtn').click(function(e){
				$(".modifyPwd").val('');
				Dialog.mask.hide();
				Dialog.node.hide();
			})


			$('#confirmbtn').click(function(e){
				if($("#oldpassword").val().trim() == ''){
					$('.errmsg').show();
					$('.rsmsg').html('旧密码不能为空！');
					return false;
				}
				var str = $("#oldpassword").val().trim();
				
				if(str.length < 6 ||  str.length > 16 ){
					$('.errmsg').show();
					$('.rsmsg').html('旧密码长度不正确！');
					return false;
				}
				
				
				if($("#newpassword").val().trim() == ''){
					$('.errmsg').show();
					$('.rsmsg').html('新密码不能为空!');
					return false;
				}
				
				var str = $("#newpassword").val().trim();
				
				if(str.length < 6 ||  str.length > 16 ){
					$('.errmsg').show();
					$('.rsmsg').html('新密码长度不正确！');
					return false;
				}
				
				if($("#confpassword").val().trim() == ''){
					$('.errmsg').show();
					$('.rsmsg').html('确认密码不能为空!');
					return false;
				}
				
				var str = $("#confpassword").val().trim();
				
				if(str.length < 6 ||  str.length > 16 ){
					$('.errmsg').show();
					$('.rsmsg').html('确认密码长度不正确！');
					return false;
				}
				
				if($("#newpassword").val() != $("#confpassword").val())
				{
					$('.errmsg').show();
					$('.rsmsg').html('确认密码不一致！');
					return false;
				}
				$('.errmsg').hide();
				var type = $(this).attr('data-value');
				noTips(type);
				
			})

})


</script>
</body>
</html>