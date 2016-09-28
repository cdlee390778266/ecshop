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
    <link rel="shortcut icon" href="/mobile/images/icon/favicon.ico" />
    <link type="text/css" rel="stylesheet" href="/mobile/css/font.css" />
    <script type="text/javascript" src="/mobile/js/jquery.js"></script>
    <script type="text/javascript" src="/mobile/js/stickup.js"></script>

    <jsp:include page="../comm/mobile.jsp" flush="true" />

    <title> 买方摘牌</title>
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
	<div class="header">
		<div class="header-left"><a href="javascript:history.back(-1);"><img src="/mobile/images/back.png" alt=""></a></div>
		<div class="logo">
       交收确认 <span class="fcyellow">根据交收日期进行交收确认</span>
     </div>
	</div>
<div class="main examine examinego">
	<div class="container-fluid">
		<div class="row">
     <div class="col-xs-12 bgwhite  mart5 success">
       <p class="txtcenter"><img src="/mobile/images/success.png" alt="" width="70" /></p>
       <p class="">摘牌单号 <span class="fcgreen">${rspBody.dsNO}</span></p>
       <p class="fcyellow">${rspBody.statusDesc}</p>
     </div>
   </div>
   <div class="lh40 mart5 marb60">
	<div class="row">
		<div class="col-xs-12 padlr25 up-title">本次摘牌费用说明</div>
	</div>
	
	<c:forEach items="${rspBody.costPays}" var="cost" >
	<div class="row bgfff up-cost">
		<div class="col-xs-4 borderdb  txtleft">费用名称</div>
		<div class="col-xs-8 borderdb fc999">${cost.costName}</div>
		<div class="col-xs-4 borderdb txtleft ">交易类型</div>
		<div class="col-xs-8 borderdb fc999">${cost.trTypeName}</div>
		<div class="col-xs-4 borderdb txtleft ">费用金额</div>
		<div class="col-xs-8 borderdb fcyellow">
			<fmt:formatNumber value="${cost.costAmt}" type="currency" pattern="￥0.00元" />
		</div>
		<div class="col-xs-4 borderdb txtleft ">当前状态</div>
		<div class="col-xs-8 borderdb fc999">${cost.flagDesc}</div>
	</div>
</c:forEach>
	

</div>
	</div>
</div>


	<!-- footer -->
	<jsp:include page="../comm/footer.jsp" flush="true" />
	<!-- footer End -->
</body>
</html>