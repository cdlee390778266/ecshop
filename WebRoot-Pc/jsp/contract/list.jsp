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
	<link rel="shortcut icon" href="/images/icon/favicon.ico" />
	<link type="text/css" rel="stylesheet" href="/css/global.css" />
	<link type="text/css" rel="stylesheet" href="/css/font.css" />
	<link type="text/css" rel="stylesheet" href="/css/common.css" />
	<link type="text/css" rel="stylesheet" href="/css/member.css" />
	<link type="text/css" rel="stylesheet" href="/widget/css/ui.datepicker.css" />
	<link type="text/css" rel="stylesheet" href="/css/selecttags.css" >
	<link type="text/css" rel="stylesheet" href="/widget/css/ui.dialog.css" />
	<script type="text/javascript" src="/js/jquery.js"></script>
	<script type="text/javascript" src="/js/leftnavs.js"></script>
	<script type="text/javascript" src="/widget/js/ui.datepicker.js"></script>
	<script type="text/javascript" src="/js/handlebars.js"></script>
	<script type="text/javascript" src="/js/ui.pagination.js"></script>
	<script type="text/javascript" src="/js/selecttags.js"></script>
	<script type="text/javascript" src="/js/stickup.js"></script>
	<script type="text/javascript" src="/widget/js/ui.dialog.js"></script>

	<jsp:include page="../comm/datatables.jsp" flush="true" />

	<script type="text/javascript" src="/js/handle.contract.js?v=${sessionScope.buildno}"></script>
	<title>合同管理</title>

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
					<a href="/home.htm">交易大厅</a><span class="fa  fa-angle-right"></span><a href="/contract/list.htm">会员中心</a><span class="fa  fa-angle-right"></span><a href="javascript: void(0)">我的合同</a>
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
									<h3>我的合同</h3>
								</div>
								<div class="bd mt10 warehouse">


									<table class="ui-table">
										<tbody>
											<tr>
												<td width="80" class="ctr">合同商品：</td>
												<td>
													<input type="hidden" name="commcode" id="commcode" />
													<div class="selcomm" data-select></div>
													<div class="select-box" id="product-dialog">
														<div class="select-txt" id="select-txt">请选择</div>
													</div>					
												</td>

												<td class="ctr">订立日期：</td>
												<td>

													<input type="text" name="contTime" id="contTime" maxlength="10" datepicker data-date-format="yyyy-mm-dd"  data-auto-close="true"  class="cinp-date ml10" />
													至
													<input type="text" name="econtTime" id="econtTime" maxlength="10" datepicker data-date-format="yyyy-mm-dd"  data-auto-close="true"  class="cinp-date" />

												</td>
												<td class="ctr">买卖方向：</td>
												<td>
													<select name="bsType" id="bsType" class="csel">
														<option value="" selected>全部</option>
														<option value="B" >采购合同</option>
														<option value="S">销售合同</option>
													</select>
												</td>		
											</tr>										
											<tr>
												<td class="ctr">合同状态：</td>
												<td>
													<select name="status" id="status"  class="csel">
														<option value="">全部</option>
														<option value="0">合同执行中</option>
														<option value="1">合同执行完毕</option>
														<option value="100">合同取消</option>
														<option value="-1">合同违约</option>
													</select>
												</td>
												<td class="ctr">合同编号：</td>
												<td><input type="text" class="cinp" name="contNo" id="contNo" /></td>
												<td class="ctr">交易订单号：</td>
												<td ><input type="text" class="cinp" name="strikeNo" id="strikeNo" /></td>
											</tr>			
										</tbody>
									</table>
									
									
									
								</div>							
							</div>

							<!-- 
							<div class="row clearfix">
								<div class="sub-search">
									<button class="cbtn ml5" id="J_ssearch">搜&nbsp;&nbsp;索</button>
								</div>
							</div>
						-->
						<div class="row">

							<table id="dataset" class="display nowrap cell-border" cellspacing="0" width="100%">
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

					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- wrapper End -->		
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