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
	<link rel="shortcut icon" href="/mobile/images/icon/favicon.ico" />
	<link type="text/css" rel="stylesheet" href="/mobile/css/font-awesome.min.css" />
	<link type="text/css" rel="stylesheet" href="/mobile/css/font-awesome-ie7.min.css" />
	<script type="text/javascript" src="/mobile/js/jquery.js"></script>
	<script type="text/javascript" src="/mobile/js/handlebars.js"></script>
	<script type="text/javascript" src="/mobile/js/ui.pagination.js"></script>
	<script type="text/javascript" src="/mobile/js/stickup.js"></script>

	<jsp:include page="../comm/datatables.jsp" flush="true" />
	<jsp:include page="../comm/mobile.jsp" flush="true" />

	<script type="text/javascript" src="/mobile/js/handle.mall.sel.js?v=${sessionScope.buildno}"></script>

	<title>交易大厅</title>

</head>
<body class="drawer drawer-left">

	<div class="fixed-wrapper">  
		<!-- header -->
		<jsp:include page="../comm/header.jsp" flush="true" />
		<!-- header End -->
	</div>
	
	<div class="wrapper service-full up-search sel-search">

		<div class="grid-16-16">
			<!-- main：当前节点的路径-->
			<div class="c-select mart10">
				<div class="rlist">
					<div class="commsection">
						<c:forEach items="${nodetree}" var="nodes" varStatus="status">
						<c:if test="${fn:length(nodes)>= 1}">
						<c:set var="classFlag">${nodes[0].classFlg}</c:set>
						<div class="up-comm">
							<div class="commhead  ">
								<c:choose>
								<c:when test="${'0'==classFlag }">市场</c:when>
								<c:when test="${'1'==classFlag }">分类</c:when>
								<c:when test="${'2'==classFlag }">类别</c:when>
								<c:otherwise>商品</c:otherwise>
							</c:choose>
						</div>
						<div class="commbd">
							<ul>
								<c:forEach items="${nodes}" var="node">
								<li><c:set var="index">${status.index}</c:set> <a
									<c:if test="${index < fn:length(nodepath) && node.mdseCode == nodepath[index].mdseCode }">class="active"</c:if>
									href="/mall/class/${node.mdseCode}.htm">${node.mdseName}</a>
								</li>
							</c:forEach>
						</ul>
					</div>
				</div>
			</c:if>
		</c:forEach>
	</div>
</div>
</div>
<!-- main End -->

<div class="main-content up-main-content">
	

	<div class="tabbar-wrap">
		<div class="mod-tabbar" style="display: none;">
			<div class="header-exg tab-sty">
				<ul id="J_TabBar" class="pa-tabbar pdeta">

					<c:if test="${fn:length(subcls) == 1}">
					<li class="item tab-cell selected"><a
						data-role='${subcls[0].mdseCode}'
						data-flag='${subcls[0].classFlg}'>${subcls[0].mdseName}</a></li>
					</c:if>

					<c:if test="${fn:length(subcls) > 1}">
					<li class="item tab-cell selected"><a
						data-role='${curcls.mdseCode}'
						data-flag='${curcls.classFlg}'>全部</a></li>
						<c:forEach items="${subcls}" var="cls" varStatus="status">
						<c:if test="${status.count < 8}">
						<li class="item tab-cell"><a
							data-role='${cls.mdseCode}'
							data-flag='${cls.classFlg}'>${cls.mdseName}</a></li>
						</c:if>
						<c:if test="${status.count >= 8}">
						<li class="item tab-cell" style="display: none"><a
							data-role='${cls.mdseCode}'
							data-flag='${cls.classFlg}'>${cls.mdseName}</a></li>
						</c:if>
					</c:forEach>
					<c:if test="${fn:length(subcls) >= 8}">
					<li class="item tab-cell morecell"><a
						href="javascript:void(0);">更多分类...</a></li>
					</c:if>
				</c:if>
			</ul>
		</div>
	</div>
</div>

<div class="sub-wrap mart10">
	<input type="hidden" value="${sessionScope.userinfo.mID}" id="currMID" />

	<div class="custfilter up-custfilter">
		<div class="filtersection">
			<div class="filterhd">品牌:</div>
			<div class="filterbd">
				<ul class="J_Brand">
					<li class="active" data-key="">不限</li>
				</ul>
			</div>

		</div>

		<div class="filtersection">
			<div class="filterhd">产地:</div>
			<div class="filterbd">
				<ul class="J_Origin">
					<li class="active" data-key="">不限</li>
				</ul>
			</div>
		</div>


		<div class="filtersection">
			<div class="filterhd">价格:</div>
			<div class="filterbd">
				<input type="hidden" id="price-max" name="price-max" /> <input
				type="hidden" id="price-min" name="price-min" />
				<ul class="J_Price">
					<li class="active" data-key="">不限</li>
					<li data-max="1000">1000元以下</li>
					<li data-max="2000" data-min="1000">1000-2000元</li>
					<li data-max="3000" data-min="2000">2000-3000元</li>
					<li data-max="5000" data-min="5000">3000-5000元</li>
					<li data-min="5000">5000元以上</li>
				</ul>
			</div>
		</div>

		<div class="filtersection">
			<div class="filterhd">数量:</div>
			<div class="filterbd">
				<input type="hidden" id="volume-max" name="volume-max" />
				<input type="hidden" id="volume-min" name="volume-min" />
				<ul class="J_Volume">
					<li class="active" data-key="">不限</li>
					<li data-max="50">0-50</li>
					<li data-max="100" data-min="50">50-100</li>
					<li data-max="200" data-min="100">100-200</li>
					<li data-max="500" data-min="200">200-500</li>
					<li data-min="500">500以上</li>
				</ul>
			</div>
		</div>

	</div>

	<div class="page-module product-set up-product ">
		<table id="dataset" class="cell-border hover table" cellspacing="0"
		width="100%" style="padding: 0px">
		<thead>
			<tr>
				<th></th>
				<th></th>
				<th></th>
				<th></th>
				<th></th>
				<th></th>
				<th></th>
			</tr>
		</thead>
	</table>
</div>

</div>

</div>

<!--  content End -->
</div>
</div>

<!-- wrapper End -->

<script type="text/x-handlebars-template" id="entryTemplate">
	<table class="ui-table table-primary">
		<tbody>
			{{#listeds}}
			<tr>
				<td rowspan="3" width="200px">		
					<div class="pic"><a target="_blank" href="/mall/item/{{listedNo}}.htm" >{{imgshow titlePic}}</a></div>
					<div class="title">{{title}}</div>
				</td>
				<td class="ctl-2" width="650px"><span style="padding-left:15px">{{commName}}</span> </td>
				<td class="ctl-3"><span class="cell">有效期:{{doe}}</span></td> 
			</tr>
			<tr>
				<td rowspan="2">
					<table>
						<tr>
							<td class="ctl" width="25%"><span>单价:{{money up}}/{{uom}}</span></td>
							<td class="ctl" width="25%"><span>现量:{{rem}}/{{qty}}</span></td>
							<td><span class="cell">交收仓库:{{storage}}</span></td>
							<td><span class="cell">交收类型:{{listedTypeName}}</span>
							</tr>
							<tr>
								<td colspan="4">
									{{HandleSummary summary1 summary2 summary3 summary4 }}
								</td>
							</tr>
						</table>
					</td>
					<td class="ctl">
						<div style="text-align:left;">卖家:{{memName}}<br/>编号:{{mid}}</div>
					</tr>
					<tr>
						<td>
							{{delistbtn listedNo mid}}
						</td>
					</tr>
					{{/listeds}}							
				</tbody>
			</table>
		</script>
		<!-- footer -->
		<jsp:include page="../comm/footer.jsp" flush="true" />
		<!-- footer End -->


		<!-- footer End -->
	</body>
	</html>
