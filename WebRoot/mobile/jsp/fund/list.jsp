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
	<link type="text/css" rel="stylesheet" href="/mobile/widget/css/ui.datepicker.css" />
	<link type="text/css" rel="stylesheet" href="/mobile/css/square/green.css">
	<link type="text/css" rel="stylesheet" href="/mobile/widget/css/ui.dialog.css" />
	<script type="text/javascript" src="/mobile/js/jquery.js"></script>
	<script type="text/javascript" src="/mobile/js/icheck.js"></script>
	<script type="text/javascript" src="/mobile/js/handlebars.js"></script>
	<script type="text/javascript" src="/mobile/js/stickup.js"></script>
	<script type="text/javascript" src="/mobile/widget/js/ui.dialog.js"></script>
	<script type="text/javascript" src="/mobile/widget/js/ui.datepicker.js"></script>

	<jsp:include page="../comm/datatables.jsp" flush="true" />

	<jsp:include page="../comm/mobile.jsp" flush="true" />

	<script type="text/javascript" src="/mobile/js/handle.fund.js?v=${sessionScope.buildno}"></script>

	<title>账务明细</title>

</head>
<body class="drawer drawer-left">

	<div class="fixed-wrapper">
		<!-- header -->
		<jsp:include page="../comm/header.jsp" flush="true" />
		<!-- header End -->
	</div>

	<div class="container-fluid up-datatables">

		<!-- 二级菜单 -->
		<jsp:include page="../comm/submenu.jsp" flush="true" />
		<!-- 二级菜单 Emd-->

		<div class="condition">
			<div class="row">

				<div class="col-xs-12">
					<div class="input-group">
						<span class="input-group-addon">账务日期</span>
						<input type="text" name="beginDate" id="beginDate" maxlength="10" datepicker data-date-format="yyyy-mm-dd"  data-auto-close="true" class="cinp-date form-control" />
						<span class="input-group-addon">至</span>						
						<input type="text" name="endDate" id="endDate" maxlength="10" datepicker data-date-format="yyyy-mm-dd"  data-auto-close="true" class="cinp-date form-control" />	
					</div>
				</div>

				<div class="col-xs-12 mart10">
					<div class="input-group">
						<span class="input-group-addon">收支类型</span>
						<select class="csel form-control" name="iae" id="iae">
							<option value="" selected>所有资金</option>
							<option value="I" >资金收入</option>
							<option value="E">资金支出</option>
						</select>
					</div>
				</div>

				<div class="col-xs-12 mart10">
					<div class="input-group">
						<span class="input-group-addon">费用代码</span>
						<select class="csel form-control" name="costCode" id="costCode">
							<option value="" selected>所有费用</option>
							<c:forEach items="${costList}" var="cost" >
							<option value="${cost.costCode}">${cost.costName}</option>
						</c:forEach>
					</select>
				</div>
			</div>

			<div class="col-xs-12 mart10">
				<div class="input-group">
					<span class="input-group-addon">账务编号</span>
					<input type="text" class="cinp form-control" id="origAcNo" name="origAcNo"  />
				</div>
			</div>

			<div class="col-xs-12 mart10">
				<div class="input-group">
					<span class="input-group-addon">交易单号</span>
					<input type="text" class="cinp form-control" id="extNo" name="extNo"  />
				</div>
			</div>
			
		</div>
	</div>

	<table id="dataset" class="display nowrap cell-border" cellspacing="0" width="100%">
		<thead>
			<tr>
				<th>交易类型</th>
				<th>资金科目</th>
				<th>账务编号</th>
				<th>发生时间</th>
				<th>变动金额(元)</th>
				<th>总余额(元)</th>
				<th>可用余额(元)</th>
				<th>备注</th>						                
			</tr>
		</thead>
	</table>	

</div>

<!-- footer -->
<jsp:include page="../comm/footer.jsp" flush="true" />
<!-- footer End -->


</body>
</html>