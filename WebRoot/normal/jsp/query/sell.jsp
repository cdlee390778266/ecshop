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
    <link rel="stylesheet" href="/normal/widget/css/ui.dialog.css" type="text/css"/>
    <link type="text/css" rel="stylesheet" href="/normal/widget/css/ui.datepicker.css" />
    <link type="text/css" rel="stylesheet" href="/normal/css/selecttags.css" >
    <link type="text/css" rel="stylesheet" href="/normal/css/square/green.css">
    <script type="text/javascript" src="/normal/js/jquery.js"></script>
    <script type="text/javascript" src="/normal/js/handlebars.js"></script>
	<script type="text/javascript" src="/normal/widget/js/ui.dialog.js"></script>
	<script type="text/javascript" src="/normal/widget/js/ui.datepicker.js"></script>
	<script type="text/javascript" src="/normal/js/leftnavs.js"></script>
	<script type="text/javascript" src="/normal/js/ui.pagination.js"></script>
	<script type="text/javascript" src="/normal/js/selecttags.js"></script>
	<script type="text/javascript" src="/normal/js/icheck.js"></script>
	<script type="text/javascript" src="/normal/js/stickup.js"></script>
	
	<jsp:include page="../comm/datatables.jsp" flush="true" />
	
	<script type="text/javascript" src="/normal/js/handle.query.sell.js?v=${sessionScope.buildno}"></script>
    <title>历史交收查询</title>
    
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
					<a href="/home.htm">交易大厅</a><span class="fa  fa-angle-right"></span><a href="/query/selllist.htm">会员中心</a><span class="fa  fa-angle-right"></span>卖出成交
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
							   		<h3>历史交收卖出查询</h3>
							    </div>
							    <div class="bd mt10 warehouse">
								    <table class="ui-table">
										<tbody>
											<tr>
												<td width="80px" class="ctr">商品类型:</td>
												<td >
												<input type="hidden" name="commcode" id="commcode" />
												<div class="selcomm" data-select></div>
												<div class="select-box" id="product-dialog">
														<div class="select-txt" id="select-txt">请选择</div>
													</div>
												</td>
												<td class="ctr">成交日期:</td>
												<td>
													<input type="text" name="strikeDate" id="strikeDate" class="cinp-date ml10"  datepicker data-date-format="yyyy-mm-dd" data-auto-close="true" />
												  至<input type="text" name="estrikeDate" id="estrikeDate" class="cinp-date"  datepicker data-date-format="yyyy-mm-dd" data-auto-close="true" />
								
												</td>
                                                <td width="110"></td>
												<td width="227"></td>
											</tr>			
										</tbody>
									</table>
							    </div>
								
							</div>

							<div class="row">	
							
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
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- wrapper End -->		

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