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
    <link rel="shortcut icon" href="/normal/images/icon/favicon.ico" />
    <link type="text/css" rel="stylesheet" href="/normal/css/global.css" />
    <link type="text/css" rel="stylesheet" href="/normal/css/font.css" />
    <link type="text/css" rel="stylesheet" href="/normal/css/common.css" />
    <link type="text/css" rel="stylesheet" href="/normal/css/member.css" />
	<link type="text/css" rel="stylesheet" href="/normal/widget/css/ui.datepicker.css" />
	<link type="text/css" rel="stylesheet" href="/normal/css/selecttags.css" >
	<link type="text/css" rel="stylesheet" href="/normal/widget/css/ui.dialog.css" />
	<script type="text/javascript">
		var enablePay = ${enablePay};
	</script>
    <script type="text/javascript" src="/normal/js/jquery.js"></script>
    <script type="text/javascript" src="/normal/js/leftnavs.js"></script>
	<script type="text/javascript" src="/normal/widget/js/ui.datepicker.js"></script>
	<script type="text/javascript" src="/normal/js/handlebars.js"></script>
    <script type="text/javascript" src="/normal/js/ui.pagination.js"></script>
    <script type="text/javascript" src="/normal/js/selecttags.js"></script>
    <script type="text/javascript" src="/normal/js/stickup.js"></script>
    <script type="text/javascript" src="/normal/widget/js/ui.dialog.js"></script>
    
    <jsp:include page="../comm/datatables.jsp" flush="true" />
    
    <script type="text/javascript" src="/normal/js/handle.warehouse.audited.js?v=${sessionScope.buildno}"></script>
    <title>仓单管理</title>
    
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
					<a href="/home.htm">交易大厅</a><span class="fa  fa-angle-right"></span><a href="/warehouse/list.htm">我的仓单</a><span class="fa  fa-angle-right"></span><a href="javascript: void(0)">签发仓单注册</a>
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
							   		<h3>注册仓单撤销</h3>
							    </div>
							    <div class="bd mt10 warehouse">
									<table class="ui-table">
									<tbody>
										<tr>
											<td width="80" class="ctr">商品类型：</td>
												<td>
												<input type="hidden" name="commcode" id="commcode" />
												<div class="selcomm" data-select></div>
												<div class="select-box" id="product-dialog">
														<div class="select-txt" id="select-txt">请选择</div>
													</div>	
											</td>
											<td class="ctr">入库时间：</td>
											<td>
												<input type="text" name="contTime" id="contTime" maxlength="10" datepicker data-date-format="yyyy-mm-dd"  data-auto-close="true"  class="cinp-date ml10" />
													至
												<input type="text" name="econtTime" id="econtTime" maxlength="10" datepicker data-date-format="yyyy-mm-dd"  data-auto-close="true"  class="cinp-date" />
											</td>
											<td class="ctr">仓库编号：</td>
											<td><input type="text" class="cinp" name="storeno" maxlength="12" id="storeno" /></td>
										</tr>
										<tr>
											<td class="ctr">仓单状态：</td>
											<td colspan="5">
											<select class="csel" id="type">
														<option value="/warehouse/list.htm" >可注册仓单</option>	
														<option value="/warehouse/list.htm?category=cancel"  >待审核仓单</option>
														<option value="#" selected="" >已审核仓单</option>
													</select>
											</td>
										</tr>																	
									</tbody>
								</table>
							    </div>
							</div>
							<div class="row">
								<table id="dataset" class="display nowrap cell-border" cellspacing="0" width="100%">
								        <thead>
								            <tr>
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
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<!-- footer -->
	<jsp:include page="../comm/footer.jsp" flush="true" />
	<!-- footer End -->
</body>
</html>