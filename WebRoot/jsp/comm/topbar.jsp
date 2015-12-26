<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

	<div class="top-bar">
		<div class="grid-16-16 tp-bd">

			<div class="login-af clearfix">
				<ul class="fn-fr top-nav">
					<li class="uitem">
					
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
					<li class="uitem"><a href="/member/home.htm" class="n">我的账户</a><i>|</i></li>
					

					<li class="uitem dropdown">
						<a href="javascript:;" class="dcell n">会员中心<span class="fa fa-angle-down"></span></a><i>|</i>
						<div class="dbox dmember">
							<dl>

								<c:forEach items="${sessionScope.userinfo.tradeMenus}" var="menu" >
									<dd><a href="${menu.menuURL}">${menu.menuName}</a></dd>
								
								</c:forEach>
								
							</dl>
						</div>
					</li>
					<c:if test="${sessionScope.userinfo.sellEnable == true}">
						<li class="uitem dropdown">
							<a href="javascript:;" class="user-act n">买卖菜单<span class="fa fa-angle-down"></span></a><i>|</i>
							<div class="dbox">
								<dl>
									<dd><a href="/sell/apply.htm?active=enter">卖方挂牌</a></dd>
								</dl>
							</div>
						</li>
					</c:if>
					<li class="uitem"><a href="/member/logout.htm" class="n">用户退出</a></li>

				</ul>
				<a href="/home.htm" class="lnk-home"><span class="fa fa-home"></span>交易大厅首页</a>
				
				<span style="font-size:14px; margin-left:30px; ">状态：</span>
				
				<c:choose>
					<c:when test="${sessionScope.sysstatus == '100'}">						
						<span style="font-size:14px; font-weight: bold; color:#89C975 ">${sessionScope.sysstatusDesc}</span>
					</c:when>
					<c:otherwise>
						<c:choose>
							<c:when test="${sessionScope.sysstatus == '50'}">						
								<span style="font-size:14px; font-weight: bold; color:#FFCC33 ">${sessionScope.sysstatusDesc}</span>
							</c:when>
							<c:otherwise>
								<span style="font-size:14px; font-weight: bold; color:#fe0000">${sessionScope.sysstatusDesc}</span>
							
							</c:otherwise>
						</c:choose>
					</c:otherwise>
				</c:choose>
	
			</div>

		</div>
	</div>