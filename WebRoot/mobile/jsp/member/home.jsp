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
	<link rel="stylesheet" href="/mobile/widget/css/ui.dialog.css" type="text/css"/>
	<script type="text/javascript" src="/mobile/js/jquery.js"></script>
	<script type="text/javascript" src="/mobile/js/handlebars.js"></script>
	<script type="text/javascript" src="/mobile/js/sha.js"></script>
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

	<div class="main safe examine addoperation">
		<div class="container-fluid marb15">
			<div class="row safe-type txtcenter bgwhite  borderb pwdman-setting">
				<div class="col-xs-6"><a href="javascript:void(0);" class="active" data-type="1" data-href="J_SetPwd">登录密码</a>
				</div>
				<div class="col-xs-6"><a href="javascript:void(0);" data-type="2" data-href="J_SetPwd">资金密码</a></div>
			</div>
			<div id="J_SetPwd" >
				<div class="row">
					<div class="input-group form-group-lg">
						<span class="input-group-addon "><span id="prompt_old">用户</span>旧密码</span>
						<input type="password" id="oldpassword" class="inp modifyPwd form-control" maxlength="16" name="oldpassword" tabindex="1" onpaste="return false" oncopy="return false" placeholder="6-16位数字密码" required="" />
					</div>
					<div class="input-group  form-group-lg">
						<span class="input-group-addon "><span id="prompt_new">用户</span>新密码</span>
						<input type="password" id="newpassword" class="inp modifyPwd form-control" maxlength="16"  name="newpassword" tabindex="2" onpaste="return false" oncopy="return false"  placeholder="6-16位数字密码" required="" />
					</div>
					<div class="input-group  form-group-lg">
						<span class="input-group-addon "><span id="prompt_conf">用户</span>确认密码</span>
						<input type="password" id="confpassword" class="inp modifyPwd form-control" maxlength="16"  name="confpassword"  onpaste="return false" oncopy="return false" placeholder="6-16位数字密码" required="" />
					</div>
				</div>
				<div class="col-xs-12 martb40">
					<button class="btn btn-success btn-block btn-lg " data-value="1" id="confirmbtn"  >确认</button>
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
							layer.msg('密码修改成功');
							setTimeout(function(){
                             location.href = '/member/account.htm';
							}, 3000)
						}else{
							layer.msg(data.msg);
						}
					}    
				});    
			}  

			$('.pwdman-setting').on('click','a',function(e){		
				$(".modifyPwd").val('');
				$('.rsmsg').html('');
				$('.pwdman-setting a').removeClass('active');
				$(this).addClass('active');
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
				e.preventDefault();
			})

			$('#confirmbtn').click(function(e){
				if($("#oldpassword").val().trim() == ''){
					layer.msg('旧密码不能为空！');
					return false;
				}
				var str = $("#oldpassword").val().trim();
				
				if(str.length < 6 ||  str.length > 16 ){
					layer.msg('旧密码长度不正确！');
					return false;
				}
				
				
				if($("#newpassword").val().trim() == ''){
					layer.msg('新密码不能为空!');
					return false;
				}
				
				var str = $("#newpassword").val().trim();
				
				if(str.length < 6 ||  str.length > 16 ){
					layer.msg('新密码长度不正确！');
					return false;
				}
				
				if($("#confpassword").val().trim() == ''){
					layer.msg('确认密码不能为空!');
					return false;
				}
				
				var str = $("#confpassword").val().trim();
				
				if(str.length < 6 ||  str.length > 16 ){
					layer.msg('确认密码长度不正确！');
					return false;
				}
				
				if($("#newpassword").val() != $("#confpassword").val())
				{
					layer.msg('确认密码不一致！');
					return false;
				}
				var type = $(this).attr('data-value');
				noTips(type);
				
			})

		})

	</script>
</body>
</html>