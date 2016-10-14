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
	<link rel="shortcut icon" href="/mobile/images/icon/favicon.ico" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="author" content="" />
	<link type="text/css" rel="stylesheet" href="/mobile/css/font.css" />
	<script type="text/javascript" src="/mobile/js/jquery.js"></script>
	<script type="text/javascript" src="/mobile/js/stickup.js"></script>

	<jsp:include page="../comm/mobile.jsp" flush="true" />

	<title>卖方挂牌</title>
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
		<!-- header  Start -->
		<jsp:include page="../comm/header.jsp" flush="true" />
		<!-- header End -->
	</div>

	<div class="container-fluid up-applay examine">
		<c:if test="${listedType=='M'}">
		<div class="row">
			<div class="col-xs-12 mart15  txtcenter padtb40 lh26">
				摘牌单号：<span class="fcgreen ">${listed.listedNo}</span><br>
			</div>
		</div>

		<!-- 本次摘牌费用说明 Start -->
		<div class="lh40 padb120">
			<div class="col-xs-12 lh50   txtcenter ">
				本次摘牌费用说明
			</div>
			<c:forEach items="${listed.costPays}" var="cost" >
			<div class="row">
				<div class="col-xs-4 borderdb  txtcenter  ">费用名称</div>
				<div class="col-xs-8 borderdb fc999">${cost.costName}</div>
				<div class="col-xs-4 borderdb txtcenter ">交易类型</div>
				<div class="col-xs-8 borderdb fc999">${cost.trTypeName}</div>
				<div class="col-xs-4 borderdb txtcenter ">费用金额</div>
				<div class="col-xs-8 borderdb fcyellow">
					<fmt:formatNumber value="${cost.costAmt}" type="currency" pattern="￥0.00元" />  
				</div>
				<div class="col-xs-4 borderdb txtcenter ">当前状态</div>
				<div class="col-xs-8 borderdb fc999">${cost.flagDesc}</div>
			</div>
		</c:forEach>
	</div>
	<!-- 本次摘牌费用说明 End -->

	<c:if test="${enableAudit=='1'}">
	<div class="pa-action ">
		<div class="pa-btn-sell txtcenter examine-btn examine-go">
			<a href="/sell/handle/A/${listed.listedNo}.htm" >继续审核</a>
		</div>
	</div>
</c:if>

</c:if>

<c:if test="${listedType=='W'}">
<div class="row padb120">
	<div class="col-xs-12 mart15  txtcenter padtb40 lh26">
		摘牌单号：<span class="fcgreen ">${listed.listedno}</span><br>
		摘牌状态：<span class="fcyellow">${listed.statusDesc}</span>
	</div>
	<div class="box">
		<c:if test="${enableAudit=='1'}">
		<div class="pa-action clearfix mt10 ml60">
			<div class="pa-btn-sell txtcenter examine-go examine-btn">
				<a href="/sell/handle/A/${listed.listedno}.htm" >继续审核</a>
			</div>
		</div>
	</c:if>
</div>
</div>
</c:if>

</div>

</body>
</html>