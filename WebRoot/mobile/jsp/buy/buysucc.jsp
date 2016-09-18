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
    <script type="text/javascript" src="/mobile/js/jquery.js"></script>
    <script type="text/javascript" src="/mobile/js/stickup.js"></script>

    <jsp:include page="../comm/mobile.jsp" flush="true" />

    <title> 买方摘牌</title>
    <script type="text/javascript">
    

	$(function() {

		$(document).ready(function() {
				$('.fixed-wrapper').stickUp();
		});
    });
    </script>
    
</head>
<body class="drawer drawer-left">

	<div class="fixed-wrapper"> 
	<!-- header -->
	<jsp:include page="../comm/header.jsp" flush="true" />
	<!-- header End -->
	</div>
	
<div class="container-fluid">
       <div class="row">
           <div class="col-xs-12 lh100 bgwhite mart15  txtcenter ng-binding">
               摘牌单号：<span class="fcgreen ng-binding">KLAF1253621314373</span>
           </div>
       </div>
      <div class="row">
           <div class="col-xs-12 lh50   txtcenter ng-binding">
               本次摘牌费用说明
           </div>
       </div>
       <!-- 本次摘牌费用说明  -->
       <!-- uiView: zpDescribe --><div ui-view="zpDescribe" class="ng-scope"><!-- ngRepeat: data in orderData --><div class="row bgwhite  examine-data ng-scope" ng-repeat="data in orderData">
 <div class="col-xs-4 borderdb  txtcenter  ng-binding">费用名称</div>
 <div class="col-xs-8 borderdb  ng-binding">买方交易手续费</div>
 <div class="col-xs-4 borderdb txtcenter ng-binding">交易类型</div>
 <div class="col-xs-8 borderdb ng-binding">冻结</div>
 <div class="col-xs-4 borderdb txtcenter ng-binding">费用金额</div>
 <div class="col-xs-8 borderdb fcyellow ng-binding">￥12536</div>
 <div class="col-xs-4 borderdb txtcenter ng-binding">当前状态</div>
 <div class="col-xs-8 borderdb ng-binding">未扣除</div>
</div><!-- end ngRepeat: data in orderData --><div class="row bgwhite  examine-data ng-scope" ng-repeat="data in orderData">
 <div class="col-xs-4 borderdb  txtcenter  ng-binding">费用名称</div>
 <div class="col-xs-8 borderdb  ng-binding">买方履约保证金</div>
 <div class="col-xs-4 borderdb txtcenter ng-binding">交易类型</div>
 <div class="col-xs-8 borderdb ng-binding">冻结</div>
 <div class="col-xs-4 borderdb txtcenter ng-binding">费用金额</div>
 <div class="col-xs-8 borderdb fcyellow ng-binding">￥12536</div>
 <div class="col-xs-4 borderdb txtcenter ng-binding">当前状态</div>
 <div class="col-xs-8 borderdb ng-binding">未扣除</div>
</div><!-- end ngRepeat: data in orderData --></div>

       <div class="row examine-go txtcenter">
           <div class="col-xs-12">
               <a ui-sref="examinego" class="ng-binding" href="#/examinego">继续审核</a>
           </div>
       </div>
   </div>





	<!-- wrapper -->
	<div class="wrapper service-full mt30">
		<div class="grid-16-16">
			
			
			<!-- main -->
			<div class="">

				<div class="main-content">
					<div class="bd">
					
						<div class="page-module bsmenus">
						
							<div class="row" style="overflow:hidden;zoom:1;height:90px;margin-bottom:10px">
								<ul class="step-list">
									<li class="active">
										<div class="linebox">
											<label for="">
												<p>订单生成</p>
												<i class="icon-step step-active">1</i>
												<span class="round">请在当日完成订单审核</span>
											</label>
										</div>
									</li>
									<li class="mid">
										<div class="linebox">
											<label for="">
												<p>审核确认</p>
												<i class="icon-step">2</i>
												<span class="round">请在当日完成订单定金支付</span>
											</label>
										</div>
									</li>
									<li>
										<div class="linebox">
											<label for="">
												<p>资金支付</p>
												<i class="icon-step">3</i>
												<span class="round">全款支付后请到交收管理中准备接收货物</span>
											</label>
										</div>
									</li>
									<li class="nobor">
										<div class="linebox noline">
											<label for="">
												<p>交收确认</p>
												<i class="icon-step"><i class="fa fa-angle-arraw">4</i></i>
												<span class="round">根据交收日期进行交收确认</span>
											</label>
										</div>
									</li>
								</ul>
							</div>
								
							<div class="sline"></div>
																			
							<div class="row">
								<div class="hd">
									<h3>处理信息</h3>
								</div>	
								<div class="bind-bd">
									<div class="title">摘牌单号：${rspBody.delistNo}</div>
									<div class="title">摘牌状态：${rspBody.statusDesc}</div>
									<div class="box">
									
											<table class="ui-table table-primary">
														<caption>本次摘牌费用说明</caption>
														<thead>
																<tr>
																	<td>费用名称</td>
																	<td>交易类型</td>
																	<td>费用金额</td>
																	<td>当前状态</td>
																</tr>
															</thead>
															<tbody>
																<c:forEach items="${rspBody.costPays}" var="cost" >
																<tr>
																	<td>${cost.costName}</td>
																	<td>${cost.trTypeName}</td>
																	<td><fmt:formatNumber value="${cost.costAmt}" type="currency" pattern="￥0.00元" /></td>
																	<td>${cost.flagDesc}</td>
																</tr>
																</c:forEach>
														</tbody>
											</table>
										
										<c:if test="${enableAudit=='1'}">
											<div class="pa-action clearfix mt10 ml60">
												<div class="pa-btn-buy fn-fl">
														<a href="/buy/handle/A/${rspBody.delistNo}.htm"  class="btn-normal btn-buy">继续审核</a>
												</div>
											</div>
										</c:if>										
									</div>
								</div>
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
</body>
</html>