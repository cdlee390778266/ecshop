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
	<link type="text/css" rel="stylesheet" href="/css/style.css?v=${sessionScope.buildno}" />
	<script type="text/javascript" src="/mobile/js/jquery.js"></script>
	<script type="text/javascript" src="/mobile/js/ui.zoom.js"></script>
	<script type="text/javascript" src="/mobile/js/ui.tabs.js"></script>
	<script type="text/javascript" src="/mobile/js/stickup.js"></script>
	<script type="text/javascript">
		$(function(){
			
			$('#tab1').tabs();

			$(document).ready(function() {
				$('.fixed-wrapper').stickUp();
			});

			$('.delist h2').click(function(){
				if($(this).hasClass('active')){
                  $(this).removeClass('active');
                  $(this).parents('.delist').find('.detail-data').slideUp();
				}else{
                  $(this).addClass('active');
                   $(this).parents('.delist').find('.detail-data').slideDown();
				}
			})
			
		})
	</script>
	<jsp:include page="../comm/mobile.jsp" flush="true" />
	<title>${rspBody.commName} ${rspBody.title}</title>
</head>
<body class="drawer drawer-left">

	<div class="fixed-wrapper">

		<!-- header -->
		<jsp:include page="../comm/header.jsp" flush="true" />
		<!-- header End -->
	</div>

	<div class="header">
		<div class="header-left"><a href="javascript:history.back(-1);"><img src="/mobile/images/back.png" alt="" /></a></div>
		<div class="logo  ">
			商品详情 <span class="fcyellow "></span>
		</div>
	</div>
	
	
	<!-- main -->
	<div class="container-fluid main examine examinego order minh100">
		<!-- 摘牌信息 -->
		<div class="row bgwhite  examine-data delist mart15 lh40 fc999">
			<h2>摘牌信息 <span class="glyphicon glyphicon-menu-down"></span></h2>
			<div class="detail-data">
				<div class="col-xs-12 strong">
					<span class="fcgreen ">挂牌单号： </span>${rspBody.listedNo}
				</div>
				<div class="col-xs-12 strong">
					<span class="fcgreen ">挂牌商品： </span>${rspBody.commName}
				</div>
				<div class="col-xs-12 strong ">
					<span class="fcgreen ">单价：   </span><span class="fcyello"><fmt:formatNumber value="${rspBody.up}" type="currency" pattern="￥0.00元" />/${rspBody.uom}</span>
				</div>
				<div class="col-xs-12 strong">
					<span class="fcgreen ">最后付款日：</span>合同签下后${fn:substringAfter(rspBody.lastPD,"cycle:")}天
				</div>
				<div class="col-xs-12 strong">
					<span class="fcgreen ">最后交收日： </span>全款支付后${fn:substringAfter(rspBody.deliDate,"cycle:")}天
				</div>
				<div class="col-xs-12 strong">
					<span class="fcgreen ">挂牌日期：</span>${rspBody.dol}
				</div>
				<div class="col-xs-12 strong">
					<span class="fcgreen ">发票监管：</span> <c:choose>												
					<c:when test="${rspBody.invoice=='Y'}">  
					(交易平台监管发票)
				</c:when>
				<c:otherwise> 
				(交易平台不负责监管发票)
			</c:otherwise>
		</c:choose>
	</div>
</div>
</div>
<!-- 卖家信息 -->
<div class="row bgwhite  examine-data delist mart15 lh40 fc999">
	<h2>卖家信息 <span class="glyphicon glyphicon-menu-down"></span></h2>
	<div class="detail-data">
		<div class="col-xs-12 strong">
			<span class="fcgreen ">会员编号： </span>${rspBody.mID}
		</div>
		<c:if test="${fn:length(rspBody.linkinfos)>0}">

		<c:if test="${! empty rspBody.linkinfos[0].ename}">
		<div class="col-xs-12 strong"><span class="fcgreen ">挂牌单位：</span>${rspBody.linkinfos[0].ename}</div>
	</c:if>

	<c:if test="${! empty rspBody.linkinfos[0].linkTel}">
	<div class="col-xs-12 strong"><span class="fcgreen ">联系电话:</span>${rspBody.linkinfos[0].linkTel}</div>
</c:if>

<c:if test="${! empty rspBody.linkinfos[0].legPer}">
<div class="col-xs-12 strong"><span class="fcgreen ">会员法人：</span>${rspBody.linkinfos[0].legPer}</div>
</c:if>

<c:if test="${! empty rspBody.linkinfos[0].fax}">
<div class="col-xs-12 strong"><span class="fcgreen ">传真号码：</span>${rspBody.linkinfos[0].fax}</div>
</c:if>

<c:if test="${! empty rspBody.linkinfos[0].email}">
<div class="col-xs-12 strong"><span class="fcgreen ">电子邮箱：</span>${rspBody.linkinfos[0].email}</div>
</c:if>

<c:if test="${! empty rspBody.linkinfos[0].regAddr}">
<div class="col-xs-12 strong"><span class="fcgreen ">注册地：</span>${rspBody.linkinfos[0].regAddr}</div>
</c:if>

<c:if test="${! empty rspBody.linkinfos[0].regAddr}">
<div class="col-xs-12 strong"><span class="fcgreen ">会员网址：</span>${rspBody.linkinfos[0].url}</div>
</c:if>											

</c:if>
</div>
</div>

<!-- 商品信息 -->
<div class="row bgwhite  examine-data delist mart15 lh40 fc999">
	<h2>商品信息 <span class="glyphicon glyphicon-menu-down"></span></h2>
	<div class="detail-data">
	<c:forEach items="${rspBody.props}" var="prop" >
	<div class="col-xs-12 strong"><span class="fcgreen ">${prop.propName}：</span>${prop.propVal}</div>
</c:forEach>
</div>
</div>

<!-- 商品描述 -->
<div class="row bgwhite  examine-data delist mart15 lh40 fc999">
	<h2>商品描述 <span class="glyphicon glyphicon-menu-down"></span></h2>
	<div class="detail-data">
	<c:if test="${fn:length(rspBody.detail)>0}">
	<div class="col-xs-12">
		${rspBody.detail}1
	</div>
	</c:if>

<c:if test="${fn:length(rspBody.detail)<=0}">
<div class="col-xs-12">
	<span class="marl10">无描述信息！</span>
</div>
</c:if>

	</div>
</div>

<!-- 商品图描述 -->
<div class="row bgwhite  delist mart15 marb200">
	<h2>商品图描述<span class="glyphicon glyphicon-menu-down"></span></h2>
	<div class="detail-data padb10 ofhidden">

	<c:if test="${fn:length(rspBody.ctxPic1)>0}"> 
	<div class="col-xs-12">
		<img src="${rspBody.ctxPic1}" alt="" onError="this.src='/mobile/images/loadfail.jpg'" class="img-responsive mart10" >
</div>
</c:if>


	<c:if test="${fn:length(rspBody.ctxPic2)>0}"> 
	<div class="col-xs-12">
	<img src="${rspBody.ctxPic2}" alt="" onError="this.src='/mobile/images/loadfail.jpg'" class="img-responsive mart10" >
	</div>
</c:if>

	<c:if test="${fn:length(rspBody.ctxPic3)>0}"> 
	<div class="col-xs-12">
	<img src="${rspBody.ctxPic3}" alt="" onError="this.src='/mobile/images/loadfail.jpg'" class="img-responsive mart10" >
	</div>
</c:if>

<c:if test="${fn:length(rspBody.ctxPic1)<=0 && fn:length(rspBody.ctxPic2)<=0 && fn:length(rspBody.ctxPic3)<=0}"> 
	<div class="col-xs-12">
	<span class="marl10">暂无描述</span>
	</div>
</c:if>

</div>
</div>


<c:if test="${rspBody.delist=='A' && rspBody.mID == sessionScope.userinfo.mID}">
<!-- 指定卖牌方 -->
<div class="row bgwhite  examine-data delist mart15">
	<h2>指定卖牌方 <span class="glyphicon glyphicon-menu-down"></span></h2>
	<div class="detail-data">
	<c:if test="${fn:length(rspBody.delistMems)>0}">
	<c:forEach items="${rspBody.delistMems}" var="dm" >
	<div class="col-xs-12">
		${rspBody.detail}
	</div>
</c:forEach>
</c:if>
</div>
</div>
</c:if>
</div>

<!-- 立即购买 -->

<div class="delist-product container-fluid">
<div class="product row">
	<div class="product-bar col-xs-12 lh19">
		<span class="product-name ">${rspBody.commName}</span>
	</div>
	<div class="col-xs-12">
		<span class="fc333 ">${rspBody.title}</span>
	</div>
	<div class="col-xs-12 col-sm-6 ">
		单价： <strong><span class="fcyellow "><fmt:formatNumber value="${rspBody.up}" type="currency" pattern="￥0.00元" />/${rspBody.uom} </span> </strong>
	</div>
	<div class="col-xs-12 col-sm-6 ">
		数量(剩余量/总数量)： <strong><span class="fcyellow ">${rspBody.rem}/</span><span class="fcgreen strong ">${rspBody.qty}${rspBody.uom}</span> <span class="fc333 ">吨</span></strong>
	</div>
	<c:if test="${enableBuy=='true'}">
	<div class="col-xs-12  martb10 txtright">
		<a href="/buy/prepare/${rspBody.listedNo}.htm" class="yellow-btn1 block">立即购买</a>
	</div>
	</c:if>

	<c:if test="${enableBuy=='false'}">
	<div class="col-xs-12  martb10 txtright padb20">
		<a href="javascript:layer.msg('您无购买权限！');" class="yellow-btn1 block btn-disbabled">立即购买</a>
	</div>
	</c:if>
</div>
</div>

</body>
</html>