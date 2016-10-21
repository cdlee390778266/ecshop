<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link type="text/css" rel="stylesheet" href="/normal/css/font-awesome.min.css" />
<link type="text/css" rel="stylesheet" href="/normal/css/font-awesome-ie7.min.css" />
<script type="text/javascript" src="/normal/widget/layer/layer.js"></script>

<div class="top-bar">
	<div class="grid-16-16 tp-bd">

		<div class="login-af clearfix">
			<ul class="fn-fr top-nav">
			<!-- 		<li class="uitem">
					
					<c:set var="level" value="${'0'}" />
					<c:set var="memdesc" value="${''}" />
					
					<c:if test="${sessionScope.userinfo.memLevel == '000'}">
						<c:set var="level" value="${'5'}" />
						<c:set var="memdesc" value="${'核心会员'}" />	
					</c:if>	
					<c:if test="${sessionScope.userinfo.memLevel == '001'}">
						<c:set var="level" value="${'4'}" />
						<c:set var="memdesc" value="${'1级会员'}" />
					</c:if>	
					<c:if test="${sessionScope.userinfo.memLevel == '002'}">
						<c:set var="level" value="${'3'}" />
						<c:set var="memdesc" value="${'2级会员'}" />
					</c:if>	
					<c:if test="${sessionScope.userinfo.memLevel == '100'}">
						<c:set var="level" value="${'1'}" />
						<c:set var="memdesc" value="${'普通会员'}" />
					</c:if>	
					<c:if test="${sessionScope.userinfo.memLevel == '888'}">
						<c:set var="level" value="${'0'}" />
						<c:set var="memdesc" value="${'信息会员'}" />
					</c:if>	
					
									
					<c:forEach var="x" begin="1" end="5" step="1">  
						<c:if test="${level >= x}">
							<img src="/images/star-on-big.png" width="16px"  height="16px">
						</c:if>																	
						<c:if test="${level < x}">
							<img src="/images/star-off-big.png" width="16px"  height="16px">
						</c:if>																		  
					</c:forEach> 	
					<span style="color:#f47912;font-size:12px">${memdesc}</span>&nbsp;&nbsp;&nbsp;&nbsp;<i>|</i>
					${sessionScope.userinfo.memName} ${sessionScope.userinfo.operName}(${sessionScope.userinfo.operID})
					
					</li>
				-->


				<li class="uitem dropdown">
					<a href="javascript:;" class="dcell n" style="border-bottom:1px solid transparent"><img src="/normal/images/user.png" alt="" class="dropdown-face" />会员中心<span class="fa fa-angle-down"></span></a>
					<div class="dbox dmember padt20">
						<div class="user-face fl">
							<img src="${user.operPhoto}"  alt="" class="mart10" onError="this.src='/normal/images/face.png'" width="43" height="43" style="border-radius : 50%" />
						</div>
						<div class="dlData fl">
							<div class="dlTitle">
								<h2>${sessionScope.userinfo.memName}</h2>
								<p>
									<c:set var="level" value="${'0'}" />
									<c:set var="memdesc" value="${''}" />
									
									<c:if test="${sessionScope.userinfo.memLevel == '000'}">
									<c:set var="level" value="${'5'}" />
									<c:set var="memdesc" value="${'核心会员'}" />	
								</c:if>	
								<c:if test="${sessionScope.userinfo.memLevel == '001'}">
								<c:set var="level" value="${'4'}" />
								<c:set var="memdesc" value="${'1级会员'}" />
							</c:if>	
							<c:if test="${sessionScope.userinfo.memLevel == '002'}">
							<c:set var="level" value="${'3'}" />
							<c:set var="memdesc" value="${'2级会员'}" />
						</c:if>	
						<c:if test="${sessionScope.userinfo.memLevel == '100'}">
						<c:set var="level" value="${'1'}" />
						<c:set var="memdesc" value="${'普通会员'}" />
					</c:if>	
					<c:if test="${sessionScope.userinfo.memLevel == '888'}">
					<c:set var="level" value="${'0'}" />
					<c:set var="memdesc" value="${'信息会员'}" />
				</c:if>	
				
				
				<c:forEach var="x" begin="1" end="5" step="1">  
				<c:if test="${level >= x}">
				<img src="/normal/images/star-on-big.png" width="16px"  height="16px">
			</c:if>																	
			<c:if test="${level < x}">
			<img src="/normal/images/star-off-big.png" width="16px"  height="16px">
		</c:if>																		  
	</c:forEach> 

	<span style="color:#6db23d;font-size:12px" class="marl20">${memdesc}</span>


</p>
</div>
<dl class="mart20">
	<c:forEach items="${sessionScope.userinfo.tradeMenus}" var="menu" >
	<dd><a href="${menu.menuURL}">${menu.menuName}</a></dd>
</c:forEach>
<dd><a href="/member/home.htm" >我的账户</a></dd>	
</dl>
</div>
</div>

</li>

<li class="uitem gn-loginout"><a href="/member/logout.htm" >退出</a></li>

</ul>





<a href="/home.htm" class="lnk-home"><span class="fa fa-home"></span>交易大厅首页</a>

<span style="font-size:14px; margin-left:30px; ">状态：</span>

<c:choose>
<c:when test="${sessionScope.sysstatus == '100'}">						
<span style="font-size:14px;  color:#fff " class="triangle">${sessionScope.sysstatusDesc}</span>
</c:when>
<c:otherwise>
<c:choose>
<c:when test="${sessionScope.sysstatus == '50'}">						
<span style="font-size:14px;  color:#fff " class="triangle" >${sessionScope.sysstatusDesc}</span>
</c:when>
<c:otherwise>
<span style="font-size:14px;  color:#fff" class="triangle" >${sessionScope.sysstatusDesc}</span>

</c:otherwise>
</c:choose>
</c:otherwise>
</c:choose>

</div>

</div>
</div>