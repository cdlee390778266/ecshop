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
	<link type="text/css" rel="stylesheet" href="/mobile/widget/css/ui.datepicker.css" />
	<link  type="text/css"rel="stylesheet" href="/mobile/widget/css/ui.dialog.css" />
	<script type="text/javascript" src="/mobile/js/jquery.js"></script>
	<script type="text/javascript" src="/mobile/widget/js/ui.datepicker.js"></script>
	<script type="text/javascript" src="/mobile/js/handlebars.js"></script>
	<script type="text/javascript" src="/mobile/js/ui.pagination.js"></script>
	<script type="text/javascript" src="/mobile/js/selecttags.js"></script>
	<script type="text/javascript" src="/mobile/js/stickup.js"></script>
	<script type="text/javascript" src="/mobile/widget/js/ui.dialog.js"></script>

	<jsp:include page="../comm/datatables.jsp" flush="true" />

	<jsp:include page="../comm/mobile.jsp" flush="true" />

	<script type="text/javascript" src="/mobile/js/handle.mall.js?v=${sessionScope.buildno}"></script>

	<title>我的挂牌</title>

</head>
<body class="drawer drawer-left backTemplate-dialog">

	<div class="fixed-wrapper">
		<!-- header -->
		<jsp:include page="../comm/header.jsp" flush="true" />
		<!-- header End -->
	</div>

	<div class="container-fluid bordert up-datatables">

		<div class="row safe-type txtcenter bgfff up-list">
			<div class="col-xs-12"><a href="javascript:void(0);" class="active">销售清单</a></div>
		</div>

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

				<div class="col-xs-12">

					<div class="input-group mart5 up-group">
						<span class="input-group-addon">挂牌日期：</span>
						<input type="text" name="dol" id="dol" datepicker data-date-format="yyyy-mm-dd" data-auto-close="true"  class="cinp-date form-control"  />
						<span class="input-group-addon">至</span>
						<input type="text" name="edol" id="edol" datepicker data-date-format="yyyy-mm-dd" data-auto-close="true"  class="cinp-date form-control" />
					</div>

					<div class="input-group mart5 up-group">
						<span class="input-group-addon">挂牌有效截止日：</span>
						<input type="text" name="doe" id="doe" datepicker data-date-format="yyyy-mm-dd" data-auto-close="true"  class="cinp-date form-control" />
					</div>

					<div class="input-group mart5 up-group">
						<span class="input-group-addon">状态：</span>
						<select class="csel form-control" name="status" id="status">
							<option value="">全状态挂牌</option>	
							<option value="0">待审核商品</option>
							<option value="1">已审核商品</option>
							<option value="-1">审核未通过商品</option>
							<option value="-2">已撤消商品</option>
							<option value="100" selected>正常交易商品</option>
							<option value="999">下架商品</option>
							<option value="998">售罄商品</option>		
							<option value="997">过期商品</option>
						</select>	
					</div>
					
				</div>

			</div>
		</div>

		<table id="dataset" class="display nowrap cell-border" cellspacing="0" width="100%">
			<thead>
				<tr>
					<th>品种</th>
					<th>商品代码</th>
					<th>商品名称</th>
					<th>品牌</th>
					<th>产地</th>
					<th>挂牌数量</th>
					<th>剩余数量</th>
					<th>单价</th>            
					<th>交收仓库</th>
					<th>挂牌日期</th>
					<th>挂牌有效期</th>
					<th>挂牌类型</th>
					<th>状态</th>	
					<th>商品简述</th>								                
					<th>挂牌单号</th>								                
					<th>操作</th>								                
				</tr>
			</thead>
		</table>		
	</div>


	<script type="text/x-handlebars-template" id="entryTemplate">
		<table class="ui-table table-primary">
			<tbody>
				{{#listeds}}

				<tr  class="{{setstyle @index}}">
					<td rowspan="2">		
						<div class="pic">{{imgshow titlePic}}</div>
						<div class="title" style="width:120px">{{title}}</div>
					</td>

					<td class="ctl"  width="320px"><span style="padding-left:15px">单号：{{listedNo}}</span></td>
					<td width="150px">有效期： {{doe}}</td>
					<td colspan="2" class="ctl"><span class="fnt-bnd">状态：{{ParseStatus statusDesc effrec}}</span></td>										
				</tr>
				<tr  class="{{setstyle @index}}">
					<td colspan="2">
						<span class="product_name">商品名:{{commName}}</span>

						<div class="summar"><ul class="attributes-list clearfix">
							<li>单价:{{money up}}元/{{uom}}</li>
							<li>剩余量/总量:{{rem}}/{{qty}}</li>	
							<li>交收仓库:{{storage}}</li>
							<li>交收类型:{{listedTypeName}}</li>
							{{HandleSummary summary1 summary2 summary3 summary4 }}
						</ul></div>	
					</td>
					<td>
						<a target="_blank" href="/mall/item/{{listedNo}}.htm" class="cor-blue" style="margin: 20px">查看</a>
						{{delist status effrec listedNo}}
					</td>
				</tr>
				{{/listeds}}
			</tbody>
		</table>
	</script>

	<script type="text/x-handlebars-template" id="backTemplate">
		<div class="title ml20">单号：{{listedNo}}</div>							
		<div class="title ml20">处理状态：{{statusDesc}}</div>
		<div class="container-fluid">
			<div class="lh40 mart5 marb60">
				<div class="row">
					<div class="col-xs-12 padlr25 up-title">本次摘牌费用说明</div>
				</div>
				{{#costPays}}
				<div class="row bgfff up-cost">
					<div class="col-xs-4 borderdb  txtleft">费用名称</div>
					<div class="col-xs-8 borderdb fc999">{{costName}}</div>
					<div class="col-xs-4 borderdb txtleft ">交易类型</div>
					<div class="col-xs-8 borderdb fc999">{{trTypeName}}</div>
					<div class="col-xs-4 borderdb txtleft ">费用金额</div>
					<div class="col-xs-8 borderdb fcyellow">
						{{money costAmt}}
					</div>
					<div class="col-xs-4 borderdb txtleft ">当前状态</div>
					<div class="col-xs-8 borderdb fc999">{{flagDesc}}</div>
				</div>
				{{/costPays}}
			</div>	
		</div>
	</script>

	<script type="text/x-handlebars-template" id="backTemplatecd">
		<div class="title ml20">单号：{{listedNo}}</div>							
		<div class="title ml20">处理状态：{{statusDesc}}</div>
	</script>

	<!-- footer -->
	<jsp:include page="../comm/footer.jsp" flush="true" />
	<!-- footer End -->
</body>
</html>