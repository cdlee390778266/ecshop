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

	<script type="text/javascript">
		var enablePay = ${enablePay};
	</script>

	<script type="text/javascript" src="/mobile/js/jquery.js"></script>
	<script type="text/javascript" src="/mobile/widget/js/ui.datepicker.js"></script>
	<script type="text/javascript" src="/mobile/js/handlebars.js"></script>
	<script type="text/javascript" src="/mobile/js/ui.pagination.js"></script>
	<script type="text/javascript" src="/mobile/js/selecttags.js"></script>
	<script type="text/javascript" src="/mobile/js/stickup.js"></script>
	<script type="text/javascript" src="/mobile/widget/js/ui.dialog.js"></script>

	<jsp:include page="../comm/datatables.jsp" flush="true" />
	<jsp:include page="../comm/mobile.jsp" flush="true" />

	<script type="text/javascript" src="/mobile/js/handle.warehouse.cancel.js?v=${sessionScope.buildno}"></script>

	<title>仓单管理</title>

</head>
<body class="drawer drawer-left up_cdList">

	<div class="fixed-wrapper"> 
		<!-- header -->
		<jsp:include page="../comm/header.jsp" flush="true" />
		<!-- header End -->
	</div>
	
	<div class="container-fluid  up-datatables">

		<!-- 二级菜单 -->
		<jsp:include page="../comm/submenu.jsp" flush="true" />
		<!-- 二级菜单 Emd-->

		<div class="condition">
			<div class="row">

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
						<span class="input-group-addon">入库时间</span>
						<input type="text" name="contTime" id="contTime" maxlength="10" datepicker data-date-format="yyyy-mm-dd"  data-auto-close="true"  class="cinp-date form-control" />
						<span class="input-group-addon">至</span>
						<input type="text" name="econtTime" id="econtTime" maxlength="10" datepicker data-date-format="yyyy-mm-dd"  data-auto-close="true"  class="cinp-date form-control" />
					</div>
				</div>

				<div class="col-xs-12 mart10">
					<div class="input-group">
						<span class="input-group-addon">仓库编号</span>
						<input type="text" class="cinp form-control" name="storeno" maxlength="12" id="storeno" />
					</div>
				</div>

				<div class="col-xs-12 mart10">
					<div class="input-group">
						<span class="input-group-addon">审核编号</span>
						<input type="text" class="cinp form-control" name="auditno" maxlength="14" id="auditno" />
					</div>
				</div>

				<div class="col-xs-12 mart10">
					<div class="input-group">
						<span class="input-group-addon">订单状态</span>
						<select class="form-control" id="type">
							<option value="0" >可注册仓单</option>
							<option value="1" selected>待审核仓单</option>
							<option value="2">已审核仓单</option>
						</select>
						<ul id="J_TabBar" class="pa-tabbar pdeta hide">
							<li class="item tab-cell selected"><a href="/warehouse/list.htm">可注册仓单</a></li>
							<li class="item tab-cell"><a href="#">待审核仓单</a></li>
							<li class="item tab-cell"><a href="/warehouse/list.htm?category=audited">已审核仓单</a></li>
						</ul>
					</div>
				</div>

			</div>
		</div>

		<table id="dataset" class="display nowrap cell-border" cellspacing="0" width="100%">
			<thead>
				<tr>
					<th>审核编号</th>
					<th>商品种类名称</th>
					<th>会员名称</th>
					<th>注册仓单编号</th>
					<th>仓库名称</th>
					<th>仓库编号</th>
					<th>货物数量</th>
					<th>操作</th>
				</tr>
			</thead>
		</table>

	</div>

	<!-- wrapper -->
	
	<div class="updialog w850" id="J_cdList" >
		<div class="hd">
			<span class="close ic"></span>
			<h3>注册仓单撤销</h3>
		</div>
		<div class="bd">
			<div style="margin-top: 5px;line-height:24px">
				<table class="ui-table">
					<tbody>
						<tr>
							<td class="ctr txtright">审核编号：</td>											
							<td><span id="auditnotmp" ></span></td>
						</tr> 
						<tr>
							<td class="ctr txtright">仓单编号：</td>											
							<td><span id="registnotmp" ></span></td>
						</tr> 
						<tr>
							<td class="ctr txtright">商品种类名称：</td>											
							<td><span id="mdsenametmp" ></span></td>
						</tr>
						<tr>
							<td class="ctr txtright" width="130">仓库编号： </td>
							<td><span id="storenotmp" ></span></td>
						</tr>
						<tr>
							<td class="ctr txtright">仓库名称： </td>
							<td><span id="storenametmp" ></span></td>
						</tr>
						<tr>
							<td class="ctr txtright">货物数量：</td>											
							<td><span id="qtytmp" ></span><span style="margin-left: 2px;">吨</span></td>
						</tr>   
					</tbody>
				</table>
			</div>
			
			<div>
				<div class="txtcenter mart15 marb15">

					<button class="cbtn" id="confirmbtn1">提&nbsp;&nbsp;交</button>
					<button class="cbtn" id="cancelbtn1">取&nbsp;&nbsp;消</button>
				</div>
			</div>
		</div>
	</div>
	
	<!-- footer -->
	<jsp:include page="../comm/footer.jsp" flush="true" />
	<!-- footer End -->
	
</body>
</html>