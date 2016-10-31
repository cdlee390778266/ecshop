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
	<script type="text/javascript" src="/normal/js/jquery.js"></script>
	<script type="text/javascript" src="/normal/js/ui.zoom.js"></script>
	<script type="text/javascript" src="/normal/js/ui.tabs.js"></script>
	<script type="text/javascript" src="/normal/js/stickup.js"></script>
	<script type="text/javascript">
		$(function(){
			
			$('#tab1').tabs();

			$(document).ready(function() {
				$('.fixed-wrapper').stickUp();
			});
			
		})
	</script>
	<title>${rspBody.commName} ${rspBody.title}</title>
</head>
<body>

	<div class="fixed-wrapper stuckMenu">
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
					<a href="/home.htm">交易大厅</a>					
					<c:forEach items="${nodepath}" var="node" >
					<span class="fa  fa-angle-right"></span>					
					<a href="/mall/class/${node.mdseCode}.htm">${node.mdseName}</a>
				</c:forEach>	
				<span class="fa  fa-angle-right"></span><a href="javascript: void(0)" class="fc999">商品信息</a>		    									
			</div>
		</div>

		<!-- main -->

		<div class="page sell-delist" style="background-color:#fff">				
			<div class="main-content">
				<div class="bd">

					<div class="page-module">

						<div class="commodity-detail" id="commodity">
							<div class="detail-bd">
								<div class="sale-info mart20">
									<div class="bd">
										<h4 class="seller-txt">卖家信息</h4>
										<div class="info">
											<ul class="mt25">
												<c:if test="${! empty rspBody.linkinfos[0].ename}">
												<li>挂牌单位：${rspBody.linkinfos[0].ename}</li>
											</c:if>
											<li>会员编号：${rspBody.mID}</li>
											<c:if test="${! empty rspBody.linkinfos[0].regAddr}">
											<li>注册地：${rspBody.linkinfos[0].regAddr}</li>
										</c:if>
										<c:if test="${! empty rspBody.linkinfos[0].regAddr}">
										<li>会员网址：<a href="${rspBody.linkinfos[0].url}">${rspBody.linkinfos[0].url}</a></li>
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
				</ul>
			</div>
		</div>
	</div>
	<div class="item-info clearfix">

		<div class="item-info-r">
			<div class="property">
				<div class="pa-wrap">
					<div class="pa-wrap-x">
						<div class="pa-title">
							<h3><strong class="product-name">${rspBody.commName} </strong><span>${rspBody.title}</span>
								<c:if test="${enableBuy=='true'}">  							
								<div class="pa-action fr">
									<div class="pa-btn-buy fn-fl">
										<a href="/buy/prepare/${rspBody.listedNo}.htm"  class="btn-normal btn-buy">立即购买</a>														 
									</div>
								</div>
							</c:if>
						</h3>
					</div>
					<div class="product-bar">
						单价：<span class="price marr30"><small>￥</small><fmt:formatNumber value="${rspBody.up}" type="currency" pattern="0.00元" />/${rspBody.uom}</span>
						数量（剩余数量/总数量）：<span class="fnt-bnd"> <span class="fs18"><span class="fcyellow">${rspBody.rem}</span>/${rspBody.qty}</span> ${rspBody.uom}</span>
					</div>

					<div class="pa-skin clearfix product-data" id="choose">
						<table class="ui-table">
							<tr >
								<th class="product-dataTitle" colspan="4">挂牌信息</th>
							</tr>
							<tr>
								<td width="120px" >挂牌有效期：</td>
								<td>${rspBody.doe} </td>
								<td width="120px" >挂牌单号：</td>
								<td>${rspBody.listedNo}</td>
							</tr>
							<tr>
								<td >交货仓库：</td>
								<td>${rspBody.storage}</td>
								<td >交收类型：</td>
								<td>${rspBody.listedTypeName}</td>
							</tr>
							<tr>
								<td >最后付款日：</td>
								<td>合同签下后${fn:substringAfter(rspBody.lastPD,"cycle:")}天</td>
								<td >最后交收日：</td>
								<td>全款支付后${fn:substringAfter(rspBody.deliDate,"cycle:")}天</td>
							</tr>

							<tr>
								<td >挂牌日期：</td>
								<td>${rspBody.dol}</td>
								<td >发票监管：</td>
								<td><c:choose>												
									<c:when test="${rspBody.invoice=='Y'}">  
									交易平台监管发票
								</c:when>
								<c:otherwise> 
								交易平台不负责监管发票
							</c:otherwise>
						</c:choose>
					</td>
				</tr>														


			</table>

		</div>

	</div>

</div>
</div>
<div class="col-sub mt10" id="tab1">

	<!-- mod:tabbar -->
	<div class="tabbar-wrap">
		<div class="mod-tabbar up-tabbar">
			<ul class="pa-tabbar pdeta" id="J_TabBar">
				<li class="item tab-cell selected">
					<a  href="javascript:void(0);" hidefocus="true">商品信息</a>				
				</li>
				<li class="item tab-cell">
					<a  href="javascript:void(0);" hidefocus="true">商品描述</a>
				</li>
				<li class="item tab-cell">
					<a  href="javascript:void(0);" hidefocus="true">商品图片描述</a>
				</li>
				<c:if test="${rspBody.delist=='A' && rspBody.mID == sessionScope.userinfo.mID}">
				<li class="item tab-cell">
					<a  href="javascript:void(0);" hidefocus="true">指定摘牌方</a>
				</li>
			</c:if>

		</ul>
	</div>
</div>
<!-- end of mod:tabbar -->

<div class="sub-wrap">
	<div class="tab-content">
		<div class="attributes" id="attributes">
			<ul class="attributes-list clearfix">
				<c:if test="${fn:length(rspBody.props)>0}">
				<c:forEach items="${rspBody.props}" var="prop" >
				<li>${prop.propName}：${prop.propVal}</li>
			</c:forEach>
		</c:if>
	</ul>
</div>
</div>
<div class="tab-content" style="display:none;">
	<p style="color:#7E7E7E">${rspBody.detail}</p>
</div>
<div class="tab-content" style="display:none;">
	<p style="margin-top:20px;text-align:center;">

		<c:if test="${fn:length(rspBody.ctxPic1)>0}"> 
		<img src="${rspBody.ctxPic1}" alt="" onError="this.src='/normal/images/loadfail.jpg'" >
	</c:if>

	<c:if test="${fn:length(rspBody.ctxPic2)>0}"> 
	<img src="${rspBody.ctxPic2}" alt="" onError="this.src='/normal//images/loadfail.jpg'" >
</c:if>

<c:if test="${fn:length(rspBody.ctxPic3)>0}"> 
<img src="${rspBody.ctxPic3}" alt="" onError="this.src='/normal//images/loadfail.jpg'" >
</c:if>

</p>
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

<!-- end of mod-reviews -->	
</div>

</div>
</div>


</div>


</div>



</div>
</div>
</div>


</div>
<!-- main End -->

</div>
</div>
<!-- wrapper End -->		

<!-- footer -->
<jsp:include page="../comm/footer.jsp" flush="true" />
<!-- footer End -->

</body>
</html>