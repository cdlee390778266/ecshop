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

	<script type="text/javascript" src="/normal/js/handle.sell.js?v=${sessionScope.buildno}"></script>
	<title>销售清单</title>

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
	
	<!-- wrapper -->
	<div class="wrapper service-full mt30">
		<div class="grid-16-16">
			<div class="crumb-nav">
				<div class="crumb">
					<a href="/home.htm">交易大厅</a><span class="fa  fa-angle-right"></span><a href="/sell/list.htm">会员中心</a><span class="fa  fa-angle-right"></span><a href="javascript: void(0)">销售清单</a>
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
									<h3>我的销售订单</h3>
								</div>


								<div class="bd mt10 warehouse">
									<table class="ui-table">
										<tbody>
											<tr>
												<td width="80px" class="ctr">商品类型：</td>
												<td>
													<input type="hidden" name="commcode" id="commcode" />
													<div class="selcomm" data-select></div>
													<div class="select-box" id="product-dialog">
														<div class="select-txt" id="select-txt">请选择</div>
													</div>						
												</td>
												<td class="ctr">
													订单状态
												</td>
												<td>
													<select class="csel" id="type" >
														<option value="#type0">待处理订单</option>	
														<option value="#type1">待审核订单</option>
														<option value="#type2">待付保证金</option>
														<option value="#type3">审核未通过</option>
														<option value="#type4">已撤消订单</option>
														<option value="#type5">已完成订单</option>
													</select>

													<ul id="J_TabBar" class="pa-tabbar pdeta" style="display:none;">
													<li class="item tab-cell selected"><a data-role='' id="type0">待处理订单</a></li>
														<li class="item tab-cell"><a data-role='0' id="type1">待审核订单</a></li>
														<li class="item tab-cell"><a data-role='1' id="type2">待付保证金</a></li>
														<li class="item tab-cell"><a data-role='-1' id="type3">审核未通过</a></li>
														<li class="item tab-cell"><a data-role='-2' id="type4">已撤消订单</a></li>
														<li class="item tab-cell"><a href="/mall/list.htm" id="type5">已完成订单</a></li>

													</ul>
												</td>
												<td width="110"></td>
												<td width="227"></td>
											</tr>									
										</tbody>
									</table>
								</div>	

							</div>


							<div class="row clearfix">
							<!-- 
								<div class="sub-search">
										<button class="cbtn" id="J_ssearch">搜&nbsp;&nbsp;索</button>																				
								</div>
							-->	
						</div>

						<input type="hidden" value="${sessionScope.userinfo.mID}" id="mid" />
						<input type="hidden" value="${sessionScope.userinfo.operID}" id="pid" />

						
						<div class="sub-wrap">
							
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
										<th>挂牌单号</th>
										<th>挂牌数量</th>
										<th>单价</th>            							                
										<th>挂牌日期</th>
										<th>挂牌有效期</th>
										<th>挂牌类型</th>
										<th>状态</th>	
										<th>商品简述</th>								                							                
										<th>操作</th>								                
									</tr>
								</thead>
							</table>


						</div> 
					</div>
				</div>
			</div>
		</div>
		<!-- main  end -->
	</div>
</div>
<!-- wrapper End -->
<script type="text/x-handlebars-template" id="entryTemplate">
	<table class="ui-table table-primary">
		<thead>
			<tr>
				<td colspan="2">交易商品信息</td>
				<td>挂牌日期</td>
				<td>挂牌有效期</td>
				<td>交易状态</td>
				<td>交易操作</td>
			</tr>
		</thead>
		<tbody>
			{{#listeds}}
			<tr class="{{setstyle @index}}">
				<td><div class="pic"><img src="{{titlePic}}" width="80px" height="80px" alt="" onError="this.src='/images/loadfail.jpg'"></div></td>
				<td><div class="ordercommity">单号:{{listedNo}}</div><div class="ordercommity">商品:{{commName}}</div><div class="ordertitle">标题:{{title}}</div></td>
				<td>{{dol}}</td>
				<td>{{doe}}</td>
				<td><span class="cor-red">{{statusDesc}}</span></td>
				<td>
					{{#if effRec}}
					{{#if status}}
					<a href="/sell/handle/P/{{listedNo}}.htm" class="cor-yellow">支付</a>	

					{{else}}
					<a href="/sell/handle/A/{{listedNo}}.htm" class="cor-yellow">审核</a>

					<a href="/sell/edit/{{listedNo}}.htm" class="cor-yellow ml20">修改</a>
					{{/if}}
					{{/if}}
				</td>												 	
			</tr>	
			{{/listeds}}
		</tbody>
	</table>
</script>

<!-- footer -->
<jsp:include page="../comm/footer.jsp" flush="true" />
<!-- footer End -->


</body>
</html>