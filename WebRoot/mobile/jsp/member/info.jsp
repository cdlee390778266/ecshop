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
	<link type="text/css" rel="stylesheet" href="/normal/css/font.css" />
	<link type="text/css" rel="stylesheet" href="/normal/css/validate.css" >
	<link href="/normal/css/square/green.css" rel="stylesheet">
	<script type="text/javascript" src="/normal/js/jquery.js"></script>
	<script type="text/javascript" src="/normal/js/icheck.js"></script>
	<script type="text/javascript" src="/normal/js/jquery-validate.js"></script>
	<script type="text/javascript" src="/normal/js/stickup.js"></script>
	<script type="text/javascript" src="/normal/widget/layer/layer.js"></script>

	<jsp:include page="../comm/mobile.jsp" flush="true" />

	<title>账户信息</title>

</head>
<body>

	<div class="fixed-wrapper">  

		<!-- header -->
		<jsp:include page="../comm/header.jsp" flush="true" />
		<!-- header End -->
	</div>
	
	<div class="header">
		<div class="header-left"><a href="javascript:history.back(-1);" class="glyphicon glyphicon-menu-left"></a></div>
		<div class="logo "></div>
	</div>

	<!-- wrapper -->
	<div class="wrapper service-full main safe examine">
		<div class="container-fluid">

			<!-- main -->
			<div class="">
				
				
				
				
				<div class="main-content">
					<div class="bd">
						
						<div class="page-module warehouse">
							<div class="">
								
								<div class="bd">
									<form id="memberform" action="/member/info.htm" method="post" enctype="multipart/form-data" class="form-horizontal">
										<input type="hidden" name="active" value="save" />

										<div class="row">
											<div class="col-xs-12 file bgwhite bordertb padtb15">
												<img id="operPhotoPre" src="${user.operPhoto}" width="120px" height="120px" onError="this.src='/normal/images/portrait.jpg'" class="img-rounded" />
												<div class="account-mes fc666">
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
									</div>

									<div class="input-group    form-group-lg">
										<span class="input-group-addon ">会员ID：</span>
										<div class="form-control lh26">${user.mID}</div>
									</div>
									<div class="input-group form-group-lg">
										<span class="input-group-addon ">会员名称：</span>
										<div class="form-control lh26">${user.memName}</div>
									</div>
									<div class="input-group form-group-lg bgfff">
										<span class="input-group-addon ">名称：</span>
										<div class="form_control up-info-name">
											<input type="text" value="${user.operName}" name="operName" id="operName" class="required"  data-tip="请输入名称" data-valid="isNonEmpty||maxGBLength:128" data-error="名称不能为空||名称长度不对" />
										</div>
									</div>
									<div class="input-group form-group-lg">
										<span class="input-group-addon ">操作员性别：</span>
										<div class="form-control">
											<input type="radio" name="sex" class="mr5 fn-vm" value="1" <c:if test="${user.operSex=='1'}">checked="checked"</c:if> />男
											<input type="radio" name="sex" class="mr5 fn-vm"  value="2" <c:if test="${user.operSex=='2'}">checked="checked"</c:if> />女
										</div>
									</div>
										
								</div>

								<div class="row padtb40">
									<div class="col-xs-12">
										<button class="btn btn-success btn-block " >保存</button>
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

		if('${message}'){
			layer.msg('${message}');
		}

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