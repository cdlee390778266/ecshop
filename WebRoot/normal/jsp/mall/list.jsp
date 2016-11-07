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
	<link rel="shortcut icon" href="/normal/images/icon/favicon.ico" />
	<link type="text/css" rel="stylesheet" href="/normal/css/global.css" />
	<link type="text/css" rel="stylesheet" href="/normal/css/font.css" />
	<link type="text/css" rel="stylesheet" href="/normal/css/common.css" />
	<link type="text/css" rel="stylesheet" href="/normal/css/member.css" />
	<link type="text/css" rel="stylesheet" href="/normal/widget/css/ui.datepicker.css" />
	<link type="text/css" rel="stylesheet" href="/normal/css/selecttags.css" >
	<link  type="text/css"rel="stylesheet" href="/normal/widget/css/ui.dialog.css" />
	<script type="text/javascript" src="/normal/js/jquery.js"></script>
	<script type="text/javascript" src="/normal/js/leftnavs.js"></script>
	<script type="text/javascript" src="/normal/widget/js/ui.datepicker.js"></script>
	<script type="text/javascript" src="/normal/js/handlebars.js"></script>
	<script type="text/javascript" src="/normal/js/ui.pagination.js"></script>
	<script type="text/javascript" src="/normal/js/selecttags.js"></script>
	<script type="text/javascript" src="/normal/js/stickup.js"></script>
	<script type="text/javascript" src="/normal/widget/js/ui.dialog.js"></script>

	<jsp:include page="../comm/datatables.jsp" flush="true" />

	<script type="text/javascript" src="/normal/js/handle.mall.js?v=${sessionScope.buildno}"></script>


	<title>我的挂牌</title>

</head>
<body>

	<div class="fixed-wrapper">
		<!-- topbar -->
		<jsp:include page="../comm/topbar.jsp" flush="true" />

		<!-- topbar End -->

		<!-- header -->
		<jsp:include page="../comm/header.jsp" flush="true" />
		<!-- header End -->

	</div>
	
	<div class="wrapper">
		<div class="grid-16-16">
			<div class="crumb-nav">
				<div class="crumb">
					<a href="/home.htm">交易大厅</a><span class="fa  fa-angle-right"></span><a href="/mall/list.htm">会员中心</a><span class="fa  fa-angle-right"></span><a href="javascript: void(0)">我的挂牌</a>
				</div>
			</div>
			<!-- main -->
			<div class="page">
				<!-- left -->
				<jsp:include page="../comm/left.jsp" flush="true" />
				<!-- left End -->
				
				<div class="main-content">
					<div class="bd">
						
						<div class="page-module data-query">
							<div class="row">
								<div class="hd gp-h3">
									<h3>销售清单</h3>
								</div>
								<div class="bd mt10 warehouse"> 
									<table class="ui-table">
										<tbody>
											<tr>
												<td width="80" class="ctr">商品类型：</td>
												<td >
													<input type="hidden" name="commcode" id="commcode" />
													<div class="selcomm" data-select></div>			<div class="select-box" id="product-dialog">
													<div class="select-txt" id="select-txt">请选择</div>
												</div>						
											</td>
											<td class="ctr" width="80">挂牌日期：</td>
											<td><input type="text" name="dol" id="dol" datepicker data-date-format="yyyy-mm-dd" data-auto-close="true"  class="cinp-date ml10" />至

												<input type="text" name="edol" id="edol" datepicker data-date-format="yyyy-mm-dd" data-auto-close="true"  class="cinp-date" />
											</td>

											<td class="ctr">挂牌有效截止日：</td>
											<td><input type="text" name="doe" id="doe" datepicker data-date-format="yyyy-mm-dd" data-auto-close="true"  class="cinp-date ml10" /></td>
											</tr>
											<tr>
												<td class="ctr">状态：</td>
												<td>												
													<select class="csel" name="status" id="status">
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
												</td>
											</tr>											
										</tbody>
									</table>
								</div>
							</div>

							<!--  
							<div class="row clearfix">
								<div class="sub-search">
										<button class="cbtn" id="J_ssearch">搜&nbsp;&nbsp;索</button>																				
								</div>									
							</div>
						-->	
						<div class="row">



								 <!-- 
								<div id="render"></div>

								
								 <div id="Pagination" class="pagination"></div>
								  
									
								
								<div class="noData">
								       <h3>暂无数据</h3>
								</div>	
								
							-->
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

					</div>
				</div>
			</div>
		</div>
		<!-- main End -->
	</div>
</div>

<!-- wrapper End -->
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
	<div class="box">	
		<table class="ui-table table-primary">
			<caption>本次下架费用说明</caption>
			<thead>
				<tr>
					<td>费用名称</td>
					<td>交易类型</td>
					<td>费用金额</td>
					<td>当前状态</td>
				</tr>
			</thead>
			<tbody>
				{{#costPays}}
				<tr>
					<td>{{costName}}</td>
					<td>{{trTypeName}}</td>
					<td>{{money costAmt}}</td>
					<td>{{flagDesc}}</td>
				</tr>
				{{/costPays}}
			</tbody>
		</table>										
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