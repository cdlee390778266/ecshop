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
    <link rel="stylesheet" href="/widget/css/ui.dialog.css" type="text/css"/>
    <script type="text/javascript" src="/js/jquery.js"></script>
	<script type="text/javascript" src="/js/handlebars.js"></script>
	<script type="text/javascript" src="/widget/js/ui.dialog.js"></script>
	<script type="text/javascript" src="/js/ui.tips.js"></script>
	<script type="text/javascript" src="/js/sha.js"></script>
	<script type="text/javascript" src="/js/stickup.js"></script>
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
				<div class="backto">
					<div class="backrt">
						<a href="/home.htm">返回首页<i></i></a>
					</div>
				</div>
				<div class="crumb">
					<a href="/home.htm">交易大厅</a><span class="fa  fa-angle-right"></span><a href="/member/home.htm">我的账户</a><span class="fa  fa-angle-right"></span>安全设置
				</div>
			</div>
			
			<!-- main -->
			<div class="page">
				<div class="user-mans">
					<div class="portrait-big">
						<c:if test="${empty sessionScope.userinfo.operPhoto}">
							<img  width="200" height="161" alt="" src="/images/portrait.jpg" />
						</c:if>
						<c:if test="${!empty sessionScope.userinfo.operPhoto}">
							<img  width="200" height="161" alt="" src="${sessionScope.userinfo.operPhoto}"/>
						</c:if>
					</div>
					<div class="ucenter">
						<span>我的账户</span>
					</div>
					<div class="user-navs members">
						<ul>
							<li class="current"><a href="/member/home.htm" class="mlnks">安全设置</a></li>
							<li><a href="/member/info.htm">账户信息</a></li>
							
							<c:choose>
						   		<c:when test="${sessionScope.userinfo.operType=='1'}">  
									<li><a href="/member/pay.htm">支付绑定</a></li>
									<li><a href="/member/manager.htm">操作员设置</a></li>    
							    </c:when>
							    <c:otherwise> 
							   		<li><a href="/member/right.htm">账户权限</a></li>
							    </c:otherwise>
							</c:choose>
							<li><a href="logout.htm">安全退出</a></li>
						</ul>
					</div>
				</div>
				
				
				<div class="main-content">
					<div class="bd">
						<div class="page-module pwdman">
							<div class="row">
								<div class="hd">
									<h3>密码管理</h3>
								</div>
								<div class="pwdman-setting">
									<div class="bd">
										<div class="available">
												<span class="item-operate fn-fr">
												<a href="javascript: void(0)" class="cor-blue J_setpwd" data-type="1" data-href="J_SetPwd">修改密码</a>
												</span>
											    <span class="item-image mr15"><img src="/images/icon/Rhozt32lZho89z.png" alt=""></span>
											    <span class="item-word">登录密码</span>
											    <!--
											    <span class="item-safe mr20">安全程度</span>
											      
											    <span class="item-strength mr25"><em style="width:45%"></em></span>
											    -->
											    <span class="item-content">互联网用户账户存在盗号风险，建议定期修改账户密码</span>
										</div>
										
										<c:if test="${sessionScope.userinfo.operType=='1'}">
											<div class="available mt20">
													<span class="item-operate fn-fr">
													<a href="javascript: void(0)" class="cor-blue J_setpwd" data-type="2" data-href="J_SetPwd">修改密码</a>
													</span>
												    <span class="item-image mr15"><img src="/images/icon/Rhozt32lZho89z.png" alt=""></span>
												    <span class="item-word">资金密码</span>
												    <!--
												    <span class="item-safe mr20">安全程度</span>
												      
												    <span class="item-strength mr25"><em style="width:45%"></em></span>
												    -->
												    <span class="item-content">互联网用户账户存在盗号风险，建议定期修改账户密码</span>
											</div>
										</c:if>
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
	
	<!-- 登录弹出层 -->
	
	<div class="updialog d-add-role w350" id="J_SetPwd">
		<div class="hd">
			<span class="close ic"></span>
			<h3>修改密码</h3>
		</div>
		<div class="bd">
			<div class="d-content">
				<div class="duserinfo">
					<div class="bd mt10">
						<div class="uitem">
							<table class="ui-table">
							
								<tr>
									<td width="120px" class="tr" height="45px" ><span id="prompt_old">用户</span>密码：</td>
									<td><input type="password" id="oldpassword" class="inp w160 modifyPwd" maxlength="16" name="oldpassword" tabindex="1" onpaste="return false" oncopy="return false"/> </td>
								</tr>
								<tr>
									<td class="tr" height="45px" ><span id="prompt_new">用户</span>新密码：</td>
									<td><input type="password" id="newpassword" class="inp w160 modifyPwd" maxlength="16"  name="newpassword" tabindex="2" onpaste="return false" oncopy="return false" /></td>
								</tr>
								<tr>
									<td class="tr" height="45px" ><span id="prompt_conf">用户</span>确认密码：</td>
									<td><input type="password" id="confpassword" class="inp w160 modifyPwd" maxlength="16"  name="confpassword" tabindex="3" onpaste="return false" oncopy="return false" /></td>
								</tr>


								<tr>
									<td></td>
									<td>
										<div class="pt10">
											<button class="cbtn" data-value="" id="confirmbtn" tabindex="3" >确&nbsp;&nbsp;认</button>
											<button class="cbtn" id="cancelbtn" tabindex="4" >取&nbsp;&nbsp;消</button>
																	
										</div>        
									</td>
								</tr>
							</table>
						</div>
						<div class="rsmsg" style="text-align:center;color:#c0171e"></div>
					</div>
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
				            	Dialog.mask.hide();
					        	Dialog.node.hide();
					        	$.Tips({'message':'密码修改成功','type':'info'});	
						 	}else{
						 		$('.rsmsg').html(data.msg);
    						}
				      	}    
				});    
			}  
	  		
			$('.pwdman-setting').on('click','.J_setpwd',function(e){		
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
		        	Dialog = UP.Dialog(current);	
				e.preventDefault();
			})
	  		

	  		$('#cancelbtn').click(function(e){
				$(".modifyPwd").val('');
	  			Dialog.mask.hide();
				Dialog.node.hide();
	  		})
	  		
	  		
			$('#confirmbtn').click(function(e){
				if($("#oldpassword").val().trim() == ''){
			         $('.rsmsg').html('旧密码不能为空！');
				     return false;
				}
				var str = $("#oldpassword").val().trim();
				
				if(str.length < 6 ||  str.length > 16 ){
			         $('.rsmsg').html('旧密码长度不正确！');
				     return false;
				}
				
				
				if($("#newpassword").val().trim() == ''){
			         $('.rsmsg').html('新密码不能为空!');
				     return false;
				}
				
				var str = $("#newpassword").val().trim();
				
				if(str.length < 6 ||  str.length > 16 ){
			         $('.rsmsg').html('新密码长度不正确！');
				     return false;
				}
				
				if($("#confpassword").val().trim() == ''){
			         $('.rsmsg').html('确认密码不能为空!');
				     return false;
				}
				
				var str = $("#confpassword").val().trim();
				
				if(str.length < 6 ||  str.length > 16 ){
			         $('.rsmsg').html('确认密码长度不正确！');
				     return false;
				}
				
				if($("#newpassword").val() != $("#confpassword").val())
				{
					$('.rsmsg').html('确认密码不一致！');
					return false;
				}
				var type = $(this).attr('data-value');
				noTips(type);
				
	  		})
	  		
        })
       
        
    </script>
</body>
</html>