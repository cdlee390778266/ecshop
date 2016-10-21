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
	<link type="text/css" rel="stylesheet" href="/mobile/widget/css/ui.dialog.css" />
	<script type="text/javascript" src="/mobile/js/jquery.js"></script>
	<script type="text/javascript" src="/mobile/js/leftnavs.js"></script>
	<script type="text/javascript" src="/mobile/widget/js/ui.datepicker.js"></script>
	<script type="text/javascript" src="/mobile/js/handlebars.js"></script>
	<script type="text/javascript" src="/mobile/js/ui.pagination.js"></script>
	<script type="text/javascript" src="/mobile/js/selecttags.js"></script>
	<script type="text/javascript" src="/mobile/js/stickup.js"></script>
	<script type="text/javascript" src="/mobile/widget/js/ui.dialog.js"></script>

	<jsp:include page="../comm/datatables.jsp" flush="true" />
	<jsp:include page="../comm/mobile.jsp" flush="true" />

	<script type="text/javascript" src="/mobile/js/handle.contract.js?v=${sessionScope.buildno}"></script>

	<title>合同管理</title>

</head>
<body class="drawer drawer-left">

	<div class="fixed-wrapper"> 
		<!-- header -->
		<jsp:include page="../comm/header.jsp" flush="true" />
		<!-- header End -->
	</div>

	<div class="container-fluid up-datatables">

		<div class="row safe-type txtcenter bgfff">
			<div class="col-xs-12"><a href="#"  class="active" >我的合同</a>
			</div>
		</div>

		<div class="condition">
			<div class="row">

				<div class="col-xs-12">
					<div class="input-group">
						<span class="input-group-addon">&nbsp;&nbsp; 商品类型</span>
						<input type="text" class="selcomm_dialog form-control" placeholder="请选择商品" >
						<input type="hidden" name="commcode" id="commcode" />
						<div class="selcomm" data-select></div>
					</div>
				</div>

				<div class="col-xs-12 mart10">
					<div class="input-group">
						<span class="input-group-addon">&nbsp;&nbsp; 订立日期</span>
						<input type="text" name="contTime" id="contTime" maxlength="10" datepicker data-date-format="yyyy-mm-dd"  data-auto-close="true"  class="cinp-date form-control" />
						<span class="input-group-addon">至</span>
						<input type="text" name="econtTime" id="econtTime" maxlength="10" datepicker data-date-format="yyyy-mm-dd"  data-auto-close="true"  class="cinp-date form-control" />
					</div>
				</div>

				<div class="col-xs-12 mart10">
					<div class="input-group">
						<span class="input-group-addon">&nbsp;&nbsp; 买卖方向</span>
						<select name="bsType" id="bsType" class="csel form-control">
							<option value="" selected>全部</option>
							<option value="B" >采购合同</option>
							<option value="S">销售合同</option>
						</select>
					</div>
				</div>

				<div class="col-xs-12 mart10">
					<div class="input-group">
						<span class="input-group-addon">&nbsp;&nbsp; 合同状态</span>
						<select name="status" id="status"  class="csel form-control">
							<option value="">全部</option>
							<option value="0">合同执行中</option>
							<option value="1">合同执行完毕</option>
							<option value="100">合同取消</option>
							<option value="-1">合同违约</option>
						</select>
					</div>
				</div>

				<div class="col-xs-12 mart10 ">
					<div class="input-group">
						<span class="input-group-addon">&nbsp;&nbsp; 合同编号</span>
						<input type="text" class="cinp form-control" name="contNo" id="contNo" />
					</div>
				</div>

				<div class="col-xs-12 mart10">
					<div class="input-group">
						<span class="input-group-addon">交易订单号</span>
						<input type="text" class="cinp form-control" name="strikeNo" id="strikeNo" />
					</div>
				</div>

			</div>

		</div>

		<table id="dataset" class="display nowrap cell-border table table-responsive" cellspacing="0" width="100%">
			<thead>
				<tr>
					<th>品种</th>
					<th>商品</th>
					<th>合同类型</th>								                
					<th>买方编号</th>
					<th>买方名称</th>
					<th>卖方编号</th>
					<th>卖方名称</th>								                
					<th>订立日期</th>
					<th>商品数量</th>
					<th>商品单价</th>
					<th>合同金额(元)</th>
					<th>合同状态</th>       
					<th>合同编号</th>  							                  
					<th>成交编号</th> 							                							                
					<th>操作</th>								                
				</tr>
			</thead>
		</table>
		
	</div>
	
	<script type="text/x-handlebars-template" id="entryTemplate">
		<table class="ui-table table-primary">
			<thead>
				<tr>
					<td>信息</td>
					<td>类型</td>
					<td>交易商品</td>
					<td>签订日期</td>
					<td>合同金额</td>
					<td>单价</td>
					<td>合同数量</td>
					<td>合同状态</td>
					<td width="80">操作</td>
				</tr>
			</thead>	
			<tbody>
				{{#contracts}}
				<tr class="{{setstyle @index}}">
					<td class="ctl">
						<div class="mbox">
							<p>{{contNo}}</p>
							<p>卖方：{{lsMemName}}</p>
							<p>买方：{{dbMemName}}</p>
						</div>
					</td>
					<td>{{contractName}}</td>
					<td>
						<div class="cell">{{commName}}</div>
						<div class="cell">{{strikeNo}}</div>
					</td>
					<td>{{contTime}}</td>
					<td>{{money contAmt}}元</td>
					<td>{{money up}}元/{{uom}}</td>
					<td>{{vol}}</td>
					<td>{{statusDesc}}</td>
					<td>
						<a href="/contract/info/{{contNo}}.htm"  class="lnks" target="_blank">查看</a><br/>
						<a href="/contract/download/{{contNo}}.htm" class="lnks" target="_blank">下载</a><br/>
					</td>
				</tr>
				{{/contracts}}
			</tbody>
		</table>
	</script>

	<!-- footer -->
	<jsp:include page="../comm/footer.jsp" flush="true" />
	<!-- footer End -->
</body>
</html>