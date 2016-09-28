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
	<link type="text/css" rel="stylesheet" href="/mobile/css/validate.css" >
	<link href="/mobile/css/square/green.css" rel="stylesheet">
	<script type="text/javascript" src="/mobile/js/jquery.js"></script>
	<script type="text/javascript" src="/mobile/js/icheck.js"></script>
	<script type="text/javascript" src="/mobile/js/jquery-validate.js"></script>
	<script type="text/javascript" src="/mobile/js/stickup.js"></script>
	<script type="text/javascript" src="/mobile/widget/layer/layer.js"></script>

	<jsp:include page="../comm/mobile.jsp" flush="true" />

	<title>账户信息</title>

</head>
<body class="drawer drawer-left">

	<div class="fixed-wrapper">  
		<!-- header -->
		<jsp:include page="../comm/header.jsp" flush="true" />
		<!-- header End -->
	</div>
	
	<div class="header">
		<div class="header-left"><a href="javascript:history.back(-1);"><img src="/mobile/images/back.png" alt=""></a></div>
		<div class="logo ">账户信息</div>
	</div>

	<!-- wrapper -->
	<div class="service-full main safe examine up-info">
		<div class="container-fluid">

			<!-- main -->
			<form id="memberform" action="/member/info.htm" method="post" enctype="multipart/form-data" class="form-horizontal">
				<input type="hidden" name="active" value="save" />
				<div class="row  bgfff">
				<div class="col-xs-12 file bordertb">
						<img id="operPhotoPre" src="${user.operPhoto}" width="120px" height="120px" onError="this.src='/normal/images/portrait.jpg'" class="img-rounded" />
						<div class="account-mes fc999">
							<span class="btn-upload fileinput ml10 txtright">
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
				<span class="input-group-addon ">会员ID</span>
				<div class="form-control lh26 txtright fc999">${user.mID}</div>
			</div>
			<div class="input-group form-group-lg">
				<span class="input-group-addon ">会员名称</span>
				<div class="form-control lh26 txtright fc999">${user.memName}</div>
			</div>
			<div class="input-group form-group-lg bgfff">
				<span class="input-group-addon ">名称</span>
				<div class="form_control up-info-name">
					<input type="text" value="${user.operName}" name="operName" id="operName" class="required txtright"  data-tip="" data-valid="isNonEmpty||maxGBLength:128" data-error="名称不能为空||名称长度不对" />
				</div>
			</div>
			<div class="input-group form-group-lg">
				<span class="input-group-addon ">操作员性别</span>
				<div class="form-control txtright">
					<input type="radio" name="sex" class="marl5 fn-vm" value="1" <c:if test="${user.operSex=='1'}">checked="checked"</c:if> />男
					<input type="radio" name="sex" class="marl5 fn-vm"  value="2" <c:if test="${user.operSex=='2'}">checked="checked"</c:if> />女
				</div>
			</div>
		</div>
		<div class="row padtb40">
			<div class="col-xs-12">
				<input type="submit" value="保存" class="up-btn-success" />
			</div>
		</div>
	</form>					
</div>
</div>	

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
	
		
		$('#memberform').on('submit', function(event){
			var valFlag = $(this).validate('submitValidate');
			if(valFlag == false){
				event.preventDefault();
				return false;
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