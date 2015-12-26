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
    <link type="text/css" rel="stylesheet" href="/css/validate.css" >
    <link href="/css/square/green.css" rel="stylesheet">
    <script type="text/javascript" src="/js/jquery.js"></script>
 	<script type="text/javascript" src="/js/icheck.js"></script>
 	<script type="text/javascript" src="/js/jquery-validate.js"></script>
 	<script type="text/javascript" src="/js/stickup.js"></script>
    <title>账户信息</title>
    
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
					<a href="/home.htm">交易大厅</a><span class="fa  fa-angle-right"></span><a href="/member/home.htm">我的账户</a><span class="fa  fa-angle-right"></span>账户信息
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
							<li><a href="/member/home.htm">安全设置</a></li>
							<li class="current"><a href="/member/info.htm" class="mlnks">账户信息</a></li>							
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
						
						<div class="page-module warehouse">
							<div class="row">
								<div class="hd">
									<h3 class="cor-green">会员基本信息</h3>
								</div>
								<div class="bd">
								<form id="memberform" action="/member/info.htm" method="post" enctype="multipart/form-data">
									<input type="hidden" name="active" value="save" />
									<table class="ui-table">
									<tbody>
									
										<tr>
										<td class="ctr" width="120px">会员ID：</td>
											<td><span class="ml20">${user.mID}</span></td>
										</tr>
										<tr>
											<td class="ctr">会员名称：</td>
											<td><span class="ml20">${user.memName}</span></td>
										</tr>
										
										<tr>
											<td class="ctr">操作员ID：</td>
											<td><span class="ml20">${user.operID}</span></td>
										</tr>
										
										<tr>
											<td class="ctr">名称：</td>
											<td>
												<div class="form_control">
													<input type="text" value="${user.operName}" name="operName" id="operName" class="required"  data-tip="请输入名称" data-valid="isNonEmpty||maxGBLength:128" data-error="名称不能为空||名称长度不对" />
												</div>
											</td>
										</tr>

										<tr>
											<td class="ctr">操作员性别：</td>								
											 
											<td>
											 <span class="mr20 ml20"><input type="radio" name="sex" class="mr5 fn-vm" value="1" <c:if test="${user.operSex=='1'}">checked="checked"</c:if> />男</span>
                                             <span><input type="radio" name="sex" class="mr5 fn-vm"  value="2" <c:if test="${user.operSex=='2'}">checked="checked"</c:if> />女</span>
										</tr>
										<tr>
											<td class="ctr">我的头像：</td>
											<td>
  												<div class="clearfix ml20">
                                                   <img id="operPhotoPre" src="${user.operPhoto}" width="120px" height="120px" style="display: block;float:left" onError="this.src='/images/portrait.jpg'" />
                                                     <span class="btn-upload fileinput ml10">
                                                     <c:if test="${fn:length(user.operPhoto)>0 }" >
                                                      <span>替换头像</span>
                                                      </c:if>
                                                       <c:if test="${fn:length(user.operPhoto)==0 || empty user.operPhoto}" >
                                                      <span>选择头像</span>
                                                      </c:if>
                                                       <input type="file" name="operPhoto" id="operPhoto" accept="image/jpg,image/jpeg,image/png,image/bmp,image/gif"/>
                                                     </span>
                                                </div>
											</td>
										</tr>
										
										<tr>								
											<td colspan="2"><button class="cbtn" style="float:right">保&nbsp;&nbsp;存</button></td>
										</tr>
									</tbody>
								</table>
								</form>
								<div style="color: #f00;">${message}</div>
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

	$(function() {
	
		$(document).ready(function() {
			$('.fixed-wrapper').stickUp();
		});
	
		$('input').iCheck({
		    checkboxClass: 'icheckbox_square-green',
		    radioClass: 'iradio_square-green',
		    increaseArea: '20%' // optional
		});
		
		// 输入域验证
		$('#memberform').validate({
			onFocus : function() {
				this.parent().addClass('active');
				return false;
			},
			onBlur : function() {
				var $parent = this.parent();
				var _status = parseInt(this.attr('data-status'));
				$parent.removeClass('active');
				if (!_status) {
					$parent.addClass('error');
				}
				return false;
			}
		});
		
		$('#memberform').on('submit', function(event){
		
			var valFlag = $(this).validate('submitValidate');
			if(varFlag == false){
				event.preventDefault();
				return;
			}
		});


		/*
		$('#fileToUpload').on('change', function(){
			var file = $(this)[0].files[0];
	    	upload_file = file;
	   		pos = 0;
		    upload_data = null ;
		    upload_finished = false;
		    upload_file_hash = '';
		    if ( file ){
		        upload_file_size = file.size;
		        var fileSize = 0;
		        if (file.size > 1024*1024){
		            fileSize = (Math.round(file.size * 100 / (1024 * 1024)) / 100).toString() + 'MB';
		        }else{
		            fileSize = (Math.round(file.size * 100 / 1024) / 100).toString() + 'KB';
		        }
		        //preview image
		        if ( file.type.split('/')[0] == 'image' ){
		            var rd = new FileReader();
		            rd.addEventListener('load',function(evt){
		                var im = evt.target.result;
		                $('#imgTitlePre').attr('src',im);
		
		            },false);
		            rd.readAsDataURL(file);
		        }
		    }
		});
		*/
		$('form').find('input[type=file]').on('change', function(evt) {
			var files = evt.target.files;
	
			var url = null;
			if (window.createObjectURL != undefined) {
				url = window.createObjectURL(files[0])
			} else if (window.URL != undefined) {
				url = window.URL.createObjectURL(files[0])
			} else if (window.webkitURL != undefined) {
				url = window.webkitURL.createObjectURL(files[0])
			}
	
			var preImg = '#' + $(this).attr("id") + 'Pre';
			$(preImg).attr("src", url);
			$(preImg).show();
			$(this).parent().removeClass('btn-upload_unselected');
			$(this).parent().addClass('btn-upload');
	
		});
	 
	});
	</script>
</body>
</html>