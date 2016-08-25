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
	<link type="text/css" rel="stylesheet" href="/css/style.css?v=${sessionScope.buildno}" />
	<script type="text/javascript" src="/js/jquery.js"></script>
	<script type="text/javascript" src="/js/ui.zoom.js"></script>
	<script type="text/javascript" src="/js/ui.tabs.js"></script>
	<script type="text/javascript" src="/js/stickup.js"></script>
<!-- 	<script type="text/javascript">
		$(function(){
			
			$('#tab1').tabs();

			$(document).ready(function() {
				$('.fixed-wrapper').stickUp();
			});
			
		})
	</script> -->
	<jsp:include page="../comm/mobile.jsp" flush="true" />
	<title>${rspBody.commName} ${rspBody.title}</title>
</head>
<body>

	<div class="fixed-wrapper">

		<!-- header -->
		<jsp:include page="../comm/header.jsp" flush="true" />
		<!-- header End -->
	</div>

	<div class="header">
		<div class="header-left"><a href="javascript:history.back(-1);" class="glyphicon glyphicon-menu-left"></a></div>
		<div class="logo  ">
			商品详情 <span class="fcyellow "></span>
		</div>
	</div>
	
	<!-- wrapper -->
	
			<!-- <div class="crumb-nav">
				<div class="backto">
					<div class="backrt">
						<a href="/home.htm">返回首页<i></i></a>
					</div>
				</div>
				<div class="crumb">
					<a href="/home.htm">交易大厅</a>					
					<c:forEach items="${nodepath}" var="node" >
					<span class="fa  fa-angle-right"></span>					
					<a href="/mall/class/${node.mdseCode}.htm">${node.mdseName}</a>
				</c:forEach>	
				<span class="fa  fa-angle-right"></span><a href="javascript: void(0)">商品信息</a>		    									
			</div>
		</div>
	-->
	<!-- main -->
	<div class="container-fluid main examine examinego order">
		<!-- 摘牌信息 -->
		<div class="row bgwhite  examine-data delist mart15">
			<h2>摘牌信息 <span class="glyphicon glyphicon-menu-down"></span></h2>
			<div class="col-xs-12">
				<span class="fcgreen">挂牌单号： </span>${rspBody.listedNo}
			</div>
			<div class="col-xs-12">
				<span class="fcgreen">挂牌商品： </span>${rspBody.commName}
			</div>
			<div class="col-xs-12 ">
				<span class="fcgreen">单价：   </span><span class="fcyello"><fmt:formatNumber value="${rspBody.up}" type="currency" pattern="￥0.00元" />/${rspBody.uom}</span>
			</div>
			<div class="col-xs-12">
				<span class="fcgreen">最后付款日：</span>合同签下后${fn:substringAfter(rspBody.lastPD,"cycle:")}天
			</div>
			<div class="col-xs-12">
				<span class="fcgreen">最后交收日： </span>全款支付后${fn:substringAfter(rspBody.deliDate,"cycle:")}天
			</div>
			<div class="col-xs-12">
				<span class="fcgreen">挂牌日期：</span>${rspBody.dol}
			</div>
			<div class="col-xs-12">
				<span class="fcgreen">发票监管：</span> <c:choose>												
				<c:when test="${rspBody.invoice=='Y'}">  
				(交易平台监管发票)
			</c:when>
			<c:otherwise> 
			(交易平台不负责监管发票)
		</c:otherwise>
	</c:choose>
</div>
</div>
<!-- 卖家信息 -->
<div class="row bgwhite  examine-data delist mart15">
	<h2>卖家信息 <span class="glyphicon glyphicon-menu-down"></span></h2>
	<div class="col-xs-12">
		<span class="fcgreen">会员编号： </span>${rspBody.mID}
	</div>
	<c:if test="${fn:length(rspBody.linkinfos)>0}">

	<c:if test="${! empty rspBody.linkinfos[0].ename}">
	<div class="col-xs-12"><span class="fcgreen">挂牌单位：</span>${rspBody.linkinfos[0].ename}</div>
</c:if>

<c:if test="${! empty rspBody.linkinfos[0].linkTel}">
<div class="col-xs-12"><span class="fcgreen">联系电话:</span>${rspBody.linkinfos[0].linkTel}</div>
</c:if>

<c:if test="${! empty rspBody.linkinfos[0].legPer}">
<div class="col-xs-12"><span class="fcgreen">会员法人：</span>${rspBody.linkinfos[0].legPer}</div>
</c:if>

<c:if test="${! empty rspBody.linkinfos[0].fax}">
<div class="col-xs-12"><span class="fcgreen">传真号码：</span>${rspBody.linkinfos[0].fax}</div>
</c:if>

<c:if test="${! empty rspBody.linkinfos[0].email}">
<div class="col-xs-12"><span class="fcgreen">电子邮箱：</span>${rspBody.linkinfos[0].email}</div>
</c:if>

<c:if test="${! empty rspBody.linkinfos[0].regAddr}">
<div class="col-xs-12"><span class="fcgreen">注册地：</span>${rspBody.linkinfos[0].regAddr}</div>
</c:if>

<c:if test="${! empty rspBody.linkinfos[0].regAddr}">
<div class="col-xs-12"><span class="fcgreen">会员网址：</span>${rspBody.linkinfos[0].url}</div>
</c:if>											

</c:if>
</div>

<!-- 商品信息 -->
<div class="row bgwhite  examine-data delist mart15">
	<h2>商品信息 <span class="glyphicon glyphicon-menu-down"></span></h2>
	<c:forEach items="${rspBody.props}" var="prop" >
	<div class="col-xs-12"><span class="fcgreen">${prop.propName}：</span>${prop.propVal}</div>
</c:forEach>

<div class="col-xs-12">

	<span class="fcgreen">挂牌单号： </span>${rspBody.listedNo}
</div>
</div>

<!-- 商品描述 -->
<div class="row bgwhite  examine-data delist mart15">
	<h2>商品描述 <span class="glyphicon glyphicon-menu-down"></span></h2>
	<div class="col-xs-12">
		${rspBody.detail}
	</div>
</div>

<!-- 商品图描述 -->
<div class="row bgwhite  delist mart15">
	<h2>商品图描述 <span class="glyphicon glyphicon-menu-down"></span></h2>
	<div class="col-xs-6 col-sm-4 col-md-3">
		<c:if test="${fn:length(rspBody.ctxPic1)>0}"> 
		<img src="${rspBody.ctxPic1}" alt="" onError="this.src='/images/loadfail.jpg'" class="img-thumbnail" >
	</c:if>
</div>

<div class="col-xs-6 col-sm-4 col-md-3">
	<c:if test="${fn:length(rspBody.ctxPic2)>0}"> 
	<img src="${rspBody.ctxPic2}" alt="" onError="this.src='/images/loadfail.jpg'" class="img-thumbnail" >
</c:if>
</div>

<div class="col-xs-6 col-sm-4 col-md-3">
	<c:if test="${fn:length(rspBody.ctxPic3)>0}"> 
	<img src="${rspBody.ctxPic3}" alt="" onError="this.src='/images/loadfail.jpg'" class="img-thumbnail" >
</c:if>
</div>
</div>


<c:if test="${rspBody.delist=='A' && rspBody.mID == sessionScope.userinfo.mID}">
<!-- 指定卖牌方 -->
<div class="row bgwhite  examine-data delist mart15">
	<h2>指定卖牌方 <span class="glyphicon glyphicon-menu-down"></span></h2>
	<c:if test="${fn:length(rspBody.delistMems)>0}">
	<c:forEach items="${rspBody.delistMems}" var="dm" >
	<div class="col-xs-12">
		${rspBody.detail}
	</div>
</c:forEach>
</c:if>
</div>
</c:if>


<!-- 立即购买 -->
<div class="product row marb160">
	<div class="product-bar col-xs-12">
		<span class="product-name ">${rspBody.commName}</span>
	</div>
	<div class="col-xs-12 col-sm-6">
		<span class="fc333 ">${rspBody.title}</span>
	</div>
	<div class="col-xs-12 col-sm-6 ">
		单价： <strong><span class="fcyellow "><fmt:formatNumber value="${rspBody.up}" type="currency" pattern="￥0.00元" />/${rspBody.uom} </span> </strong>
	</div>
	<div class="col-xs-12 col-sm-6 ">
		数量(剩余量/总数量)： <strong><span class="fcyellow ">${rspBody.rem}/</span><span class="fcgreen ">${rspBody.qty}${rspBody.uom}</span> <span class="fc333 ">吨</span></strong>
	</div>
	<div class="col-xs-6  col-xs-offset-6 martb10 txtright">
		<a href="/buy/prepare/${rspBody.listedNo}.htm" class="yellow-btn1 ">立即购买</a>
	</div>
</div>
<c:if test="${enableBuy=='true'}"> 
</c:if>
</div>


<!-- <div class="" style="background-color:#fff">				
	<div class="main-content">
		<div class="bd">

			<div class="page-module">

				<div class="commodity-detail" id="commodity">
					<div class="detail-bd">
						<div class="sale-info">
							<div class="bd">
								<div class="info">
									<ul class="mt25">
										<li>会员编号：${rspBody.mID}</li>
										<li>挂牌单位：${rspBody.memName}</li>
									</ul>
								</div>
							</div>
						</div>
						<div class="item-info clearfix">

							<div class="item-info-l">
								<div class="pa-gallery">

									<div class="pa-booth pa-pic">
										<c:if test="${fn:length(rspBody.titlePic)>0}"> 
										<a href="${rspBody.titlePic}" id="zoom1" rel="nofollow" class="MagicZoom MagicThumb" target="_blank">     
											<img id="J_ImgBooth" src="${rspBody.titlePic}" width="200px" height="200px"  onError="this.src='/images/loadfail.jpg'" >
										</a>
									</c:if>
									<c:if test="${fn:length(rspBody.titlePic)<= 0}">   
									<img  src="/images/loadfail.jpg" width="200px" height="200px" >
								</c:if>																				 
							</div>
						</div>
					</div>

					<div class="item-info-r">
						<div class="property">
							<div class="pa-wrap">
								<div class="pa-wrap-x">
									<div class="pa-title">
										<h3>${rspBody.commName} <span>${rspBody.title}</span></h3>
									</div>
									<div class="pa-skin clearfix" id="choose">
										<table class="ui-table">
											<tr>
												<td width="90px" class="ctr"><span class="cor-red">单价：</span></td>
												<td><span class="cor-red fnt-bnd"><fmt:formatNumber value="${rspBody.up}" type="currency" pattern="￥0.00元" />/${rspBody.uom}</span></td>
												<td width="90px" class="ctr">挂牌有效期：</td>
												<td><span class="fnt-bnd">${rspBody.doe}</span> </td>
											</tr>
											<tr>
												<td class="ctr">交货仓库：</td>
												<td>${rspBody.storage}</td>
												<td class="ctr">交收类型：</td>
												<td>${rspBody.listedTypeName}</td>
											</tr>
											<tr>
												<td class="ctr">最后付款日：</td>
												<td>合同签下后${fn:substringAfter(rspBody.lastPD,"cycle:")}天</td>
												<td class="ctr">最后交收日：</td>
												<td>全款支付后${fn:substringAfter(rspBody.deliDate,"cycle:")}天</td>
											</tr>

											<tr>
												<td class="ctr">挂牌日期：</td>
												<td>${rspBody.dol}</td>
												<td class="ctr">发票监管：</td>
												<td><c:choose>												
													<c:when test="${rspBody.invoice=='Y'}">  
													(交易平台监管发票)
												</c:when>
												<c:otherwise> 
												(交易平台不负责监管发票)
											</c:otherwise>
										</c:choose>
									</td>
								</tr>														

								<tr>
									<td class="ctr" colspan="4">数量（剩余数量/总数量）：<span class="fnt-bnd">${rspBody.rem}/${rspBody.qty}${rspBody.uom}</span>


									</td>
								</tr>
							</table>

						</div>
						<c:if test="${enableBuy=='true'}">  							
						<div class="pa-action clearfix mt10">
							<div class="pa-btn-buy fn-fl">
								<a href="/buy/prepare/${rspBody.listedNo}.htm"  class="btn-normal btn-buy">立即购买</a>														 
							</div>
						</div>
					</c:if>
				</div>

			</div>
		</div>

	</div>
</div>


</div>

<div class="col-sub mt10" id="tab1"> -->

	<!-- mod:tabbar -->
	<!-- <div class="tabbar-wrap">
		<div class="mod-tabbar">
			<ul class="pa-tabbar pdeta" id="J_TabBar">
				<li class="item tab-cell selected">
					<a  href="javascript:void(0);" hidefocus="true">商品信息</a>				
				</li>
				<li class="item tab-cell">
					<a  href="javascript:void(0);" hidefocus="true">联系卖家</a>
				</li>
				<c:if test="${rspBody.delist=='A' && rspBody.mID == sessionScope.userinfo.mID}">
				<li class="item tab-cell">
					<a  href="javascript:void(0);" hidefocus="true">指定摘牌方</a>
				</li>
			</c:if>

		</ul>
	</div>
</div> -->
<!-- end of mod:tabbar -->

<!-- <div class="sub-wrap">

	<div class="tab-content">
		<div class="attributes" id="attributes">
			<ul class="attributes-list clearfix">

				<li>挂牌单号：${rspBody.listedNo}</li>
				<c:if test="${fn:length(rspBody.props)>0}">																							


				<c:forEach items="${rspBody.props}" var="prop" >
				<li>${prop.propName}：${prop.propVal}</li>
			</c:forEach>


		</c:if>
	</ul>
</div>
</div>
<div class="tab-content" style="display:none;">

	<c:if test="${fn:length(rspBody.linkinfos)>0}">

	<div class="attributes" id="attributes">
		<ul class="attributes-list clearfix">

			<c:if test="${! empty rspBody.linkinfos[0].ename}">
			<li>挂牌单位：${rspBody.linkinfos[0].ename}</li>
		</c:if>

		<c:if test="${! empty rspBody.linkinfos[0].linkTel}">
		<li>联系电话:${rspBody.linkinfos[0].linkTel}</li>
	</c:if>

	<c:if test="${! empty rspBody.linkinfos[0].legPer}">
	<li>会员法人：${rspBody.linkinfos[0].legPer}</li>
</c:if>

<c:if test="${! empty rspBody.linkinfos[0].fax}">
<li>传真号码：${rspBody.linkinfos[0].fax}</li>
</c:if>

<c:if test="${! empty rspBody.linkinfos[0].email}">
<li>电子邮箱：${rspBody.linkinfos[0].email}</li>
</c:if>

<c:if test="${! empty rspBody.linkinfos[0].regAddr}">
<li>注册地：${rspBody.linkinfos[0].regAddr}</li>
</c:if>

<c:if test="${! empty rspBody.linkinfos[0].regAddr}">
<li>会员网址：${rspBody.linkinfos[0].url}</li>
</c:if>											

<li>
</ul>
</div>

</c:if>
</div>
<c:if test="${rspBody.delist=='A' && rspBody.mID == sessionScope.userinfo.mID}">
<div class="tab-content" style="display:none;">
	<div class="attributes" id="attributes">
		<c:if test="${fn:length(rspBody.delistMems)>0}">																											
		<ul class="attributes-list clearfix">
			<c:forEach items="${rspBody.delistMems}" var="dm" >
			<li>${dm.delistMemName}</li>
		</c:forEach>
	</ul>									
</c:if>
</div>
</div>
</c:if>

</div>

<div class="prodet">
	<h4>商品描述</h4>
	<p style="color:#7E7E7E">${rspBody.detail}</p>
</div>
<div id="description" class="ke-post J_DetailSection"> 
	<h4>商品图片描述</h4>
	<p style="margin-top:20px;text-align:center;">

		<c:if test="${fn:length(rspBody.ctxPic1)>0}"> 
		<img src="${rspBody.ctxPic1}" alt="" onError="this.src='/images/loadfail.jpg'" >
	</c:if>

	<c:if test="${fn:length(rspBody.ctxPic2)>0}"> 
	<img src="${rspBody.ctxPic2}" alt="" onError="this.src='/images/loadfail.jpg'" >
</c:if>

<c:if test="${fn:length(rspBody.ctxPic3)>0}"> 
<img src="${rspBody.ctxPic3}" alt="" onError="this.src='/images/loadfail.jpg'" >
</c:if>

</p>
</div>
-->
<!-- end of mod-reviews -->	
<!-- </div>
</div>



</div>
</div>
</div>


</div> -->
<!-- main End -->


<!-- wrapper End -->		

<!-- footer -->
<jsp:include page="../comm/footer.jsp" flush="true" />
<!-- footer End -->

</body>
</html>