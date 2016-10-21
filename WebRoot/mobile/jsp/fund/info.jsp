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
	<script type="text/javascript" src="/mobile/js/leftnavs.js"></script>
	<script type="text/javascript" src="/mobile/js/ui.tabs.js"></script>
	<script type="text/javascript" src="/mobile/js/stickup.js"></script>

	<jsp:include page="../comm/mobile.jsp" flush="true" />

	<script type="text/javascript">
		$(function(){
			
			$(document).ready(function() {
				$('.fixed-wrapper').stickUp();
			});

			$('#tab1').tabs();

		});
	</script>

	<title>我的账户</title>

</head>
<body class="drawer drawer-left">

	<div class="fixed-wrapper">
		<!-- header -->
		<jsp:include page="../comm/header.jsp" flush="true" />
		<!-- header End -->
	</div>


	<div class="container-fluid up-datatables">

		<div class="row safe-type txtcenter bgfff up-list">
			<div class="col-xs-6"><a href="/fund/info.htm" class="active">资金账户</a>
			</div>
			<div class="col-xs-6"><a href="/fund/list.htm"  >账务明细</a></div>
		</div>

	</div>

	<div class="container-fluid marb15">
		<div class="row lh26">

			<div class="col-xs-12 mart10 ">
				系统总余额： 
				<span class="fcgreen ">
					<fmt:formatNumber value="${rspBody.totalBal}" type="currency" pattern="￥#,##0.00#" />
				</span> 元
			</div>

			<div class="col-xs-12 marb10 ">
				系统总可用余额： 
				<span class="fcgreen ">
					<fmt:formatNumber value="${rspBody.totalABal}" type="currency" pattern="￥#,##0.00#" />
				</span> 元
			</div>

			<div class="found-bar col-xs-12 txtcenter">

				<c:forEach items="${rspBody.details}" var="info" varStatus="status">
				<c:if test="${status.count == 1}">
				<a href="javascript: void(0)" class="active">${info.acctTypeName}</a>
			</c:if>										
			<c:if test="${status.count != 1}">
			<a href="javascript: void(0)">${info.acctTypeName}</a>
		</c:if>
	</c:forEach>

</div>

<c:forEach items="${rspBody.details}" var="info" varStatus="status">

<div class="found-box" <c:if test="${status.count != 1}"> style="display:none;" </c:if> >

	<div class="col-xs-12 mart15 ">
		<c:if test="${info.acctType=='00'}">
		余额： <span class="fcyellow "><fmt:formatNumber value="${info.totalBal}" type="currency" pattern="￥#,##0.00#元" /></span>
	</c:if>
</div>

<div class="col-xs-12">
	<table class="table table-bordered">
		<tbody>
			<tr class="fcgreen">
				<td class="">可用余额</td>
				<td class="">冻结余额</td>
				<c:if test="${info.acctType=='00'}">
				<td class="">冻结交易保证金</td>
			</c:if>
		</tr>
		<tr>
			<td>
				<fmt:formatNumber value="${info.avalBal}" type="currency" pattern="￥#,##0.00#元" />
			</td>
			<td >
				<fmt:formatNumber value="${info.frzAmt}" type="currency" pattern="￥#,##0.00#元" />
			</td>
			<c:if test="${info.acctType=='00'}">
			<td >
				<fmt:formatNumber value="${info.frzMargin}" type="currency" pattern="￥#,##0.00#元" />
			</td>
		</c:if>
	</tr>
	<c:if test="${info.acctType=='00'}">
	<tr  class="fcgreen">
		<td>冻结发票保证金</td>
		<td>冻结货款</td>
		<td>冻结手续费</td>
	</tr>
	<tr >
		<td>
			<fmt:formatNumber value="${info.frzInvMargin}" type="currency" pattern="￥#,##0.00#元" />
		</td>
		<td>
			<fmt:formatNumber value="${info.frzPOG}" type="currency" pattern="￥#,##0.00#元" />
		</td>
		<td>
			<fmt:formatNumber value="${info.frzPond}" type="currency" pattern="￥#,##0.00#元" />
		</td>
	</tr>
</c:if>
</tbody>
</table>

</div>

</div>

</c:forEach>

</div>
</div>

<script>
	$(function(){
		$('.found-bar a').click(function(){
			$('.found-bar a').removeClass('active');
			$('.found-box').hide();
			$('.found-bar a').eq($(this).index()).addClass('active');
			$('.found-box').eq($(this).index()).show();
		})

	})	
</script>
</body>
</html>