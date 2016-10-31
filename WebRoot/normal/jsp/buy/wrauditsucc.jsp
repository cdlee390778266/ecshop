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
    <script type="text/javascript" src="/normal/js/jquery.js"></script>
    <script type="text/javascript" src="/normal/js/stickup.js"></script>
    <title> 买方摘牌</title>
    <script type="text/javascript">
    

	$(function() {

		$(document).ready(function() {
				$('.fixed-wrapper').stickUp();
		});
    });
    </script>
    
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
		<div class="grid-16-16 ">
			<div class="crumb-nav">
				<div class="crumb">
					<a href="/home.htm">交易大厅</a><span class="fa  fa-angle-right"></span><a href="/buy/list.htm">会员中心</a><span class="fa  fa-angle-right"></span><a href="javascript: void(0)">买方摘牌</a>
				</div>
			</div>
			
			<!-- main -->
			<div class="page">

				<div class="main-content bornone">
					<div class="bd">
					
						<div class="page-module bsmenus">
						
						 <div class="curmbs mart20">
								<ul>
									<li><a href="javascript:void(0);" >订单生成<br><span class="fs12">请在当日完成订单审核</span></a></li>
									<li><a href="javascript:void(0);" class="active">审核确认<br><span class="fs12">请在当日完成订单定金支付</span></a></li>
									<li><a href="javascript:void(0);">资金支付<br><span class="fs12">全款支付后请到交收管理中准备接收货物</span></a></li>
									<li><a href="javascript:void(0);">交收确认<br><span class="fs12">根据交收日期进行交收确认</span></a></li>
								</ul>
							</div>

							<div class="row examine-result">
								<div class="hd">
									<h3>处理信息</h3>
								</div>	
								<div class="bind-bd">
									<div class="title">摘牌单号：<span class="fcgreen">${rspBody.delistNo}</span></div>
									<div class="title">摘牌状态：<span class="fcgreen">${rspBody.statusDesc}</span></div>
									<div class="box">

										<c:if test="${enablePay=='1'}">
											<div class="pa-action clearfix mt10 ml60">
												<div class="pa-btn-buy fn-fl">
														<a href="/buy/handle/wr/P/${rspBody.delistNo}.htm"  class="btn-normal btn-buy">继续支付</a>
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