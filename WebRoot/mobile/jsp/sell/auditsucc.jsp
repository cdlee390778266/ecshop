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
	<script type="text/javascript" src="/mobile/js/jquery.js"></script>
	<script type="text/javascript" src="/mobile/js/stickup.js"></script>

	<jsp:include page="../comm/mobile.jsp" flush="true" />

	<title>挂牌审核</title>
	<script type="text/javascript">


		$(function() {

			$(document).ready(function() {
				$('.fixed-wrapper').stickUp();
			});
		});
	</script>

</head>
<body class="drawer drawer-left">

	<div class="fixed-wrapper"> 
		<!-- header -->
		<jsp:include page="../comm/header.jsp" flush="true" />
		<!-- header End -->
	</div>
	
	<div class="container-fluid lh34 audit">
		<div class="row">
			<div class="col-xs-12 fc777">
				<p>挂牌状态</p>
			</div>
			<div class="col-xs-12 marb30">
				<p>挂牌单号：<span class="fcgreen">${rspBody.listedNO}</span></p>
				<p>处理状态：<span class="fcgreen">${rspBody.statusDesc}</span></p>
			</div>

			<c:if test="${enablePay=='1'}">
			<div class="pa-action clearfix mt10 ml60">
				<div class="pa-btn-sell padtb40">
					<p>
						<a href="/sell/handle/P/${rspBody.listedNO}.htm" class="btn-normal btn-sell btn btn-warning">继续支付</a>				
					</p>			
				</div>
			</div>
		</c:if>

	</div>
</div>




<!-- footer -->
<jsp:include page="../comm/footer.jsp" flush="true" />
<!-- footer End -->
</body>
</html>