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
	<link rel="stylesheet" href="/mobile/widget/css/ui.dialog.css" type="text/css"/>
	<link type="text/css" rel="stylesheet" href="/mobile/widget/css/ui.datepicker.css" />
	<link type="text/css" rel="stylesheet" href="/mobile/css/square/green.css">
	<script type="text/javascript" src="/mobile/js/jquery.js"></script>
	<script type="text/javascript" src="/mobile/js/handlebars.js"></script>
	<script type="text/javascript" src="/mobile/widget/js/ui.dialog.js"></script>
	<script type="text/javascript" src="/mobile/widget/js/ui.datepicker.js"></script>
	<script type="text/javascript" src="/mobile/js/ui.pagination.js"></script>
	<script type="text/javascript" src="/mobile/js/selecttags.js"></script>
	<script type="text/javascript" src="/mobile/js/icheck.js"></script>
	<script type="text/javascript" src="/mobile/js/stickup.js"></script>
	
	<jsp:include page="../comm/datatables.jsp" flush="true" />
	<jsp:include page="../comm/mobile.jsp" flush="true" />
	
	<script type="text/javascript" src="/mobile/js/handle.query.sell.js?v=${sessionScope.buildno}"></script>

	<title>历史交收查询</title>

</head>
<body class="drawer drawer-left">

	<div class="fixed-wrapper"> 
		<!-- header -->
		<jsp:include page="../comm/header.jsp" flush="true" />
		<!-- header End -->
	</div>
	
	<div class="container-fluid up-datatables">

		<div class="row safe-type txtcenter bgfff up-list">
			<div class="col-xs-6"><a href="/query/selllist.htm" class="active">卖出查询</a>
			</div>
			<div class="col-xs-6"><a href="/query/buylist.htm" >买入查询</a>
			</div>
		</div>

		<div class="condition">
			<div class="col-xs-12">
				<div class="input-group">
					<span class="input-group-addon">商品分类</span>
					<input type="text" class="selcomm_dialog form-control" placeholder="请选择商品">
					<input type="hidden" name="commcode" id="commcode" />
					<div class="selcomm" data-select></div>
				</div>
			</div>
			<div class="col-xs-12 mart10">
				<div class="input-group">
					<span class="input-group-addon">成交日期</span>
					<input type="text" name="strikeDate" id="strikeDate" class="cinp-date form-control"  datepicker data-date-format="yyyy-mm-dd" data-auto-close="true" />
					<span class="input-group-addon">至</span><input type="text" name="estrikeDate" id="estrikeDate" class="cinp-date form-control"  datepicker data-date-format="yyyy-mm-dd" data-auto-close="true" />
				</div>
			</div>
		</div>

		<table id="dataset" class="display nowrap cell-border" cellspacing="0" width="100%">
			<thead>
				<tr>
					<th>品种</th>
					<th>商品代码</th>
					<th>商品名称</th>
					<th>买方ID</th>
					<th>买方名称</th>
					<th>品牌</th>
					<th>产地</th>
					<th>数量</th>
					<th>单价</th> 
					<th>交易款(元)</th>           
					<th>交收仓库</th>
					<th>交收完成日期</th>
					<th>挂牌类型</th>
					<th>状态</th>	
					<th>成交编号</th>								                								                
					<th>合同编号</th>								                								                
				</tr>
			</thead>
		</table>

	</div>


	<!-- footer -->
	<jsp:include page="../comm/footer.jsp" flush="true" />
	<!-- footer End -->

	<script type="text/x-handlebars-template" id="entryTemplate">
		<table class="ui-table table-primary">
			<thead>
				<tr>
					<td>交收编号</td>
					<td>卖方员编号 </td>
					<td>交易商品</td>
					<td>单价</td>
					<td>数量</td>
					<td>交易款项（元）</td>
					<td>交收完成日期</td>
					<td>状态</td>
				</tr>
			</thead>	
			<tbody>
				{{#hisStrikes}}
				<tr class="{{setstyle @index}}">
					<td>{{strikeNo}}</td>
					<td>{{sayBSMid smID bmID}}</td>
					<td>{{commName}}</td>
					<td>
						{{money up}}元/{{uom}}
					</td>
					<td>
						{{vol}}
					</td>
					<td>										
						{{money strikeAmt}}
					</td>
					<td>										
						{{strikeDate}}
					</td>
					<td>{{statusDesc}}</td>											 	
				</tr>					
				{{/hisStrikes}}
			</tbody>
		</table>
	</script>
	
</body>
</html>