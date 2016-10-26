<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="user-mans">
	
	<div class="user-navs members">
		<ul>       
			<li>
			    <em>+</em>
				<a href="javascript:void(0);" class="user-navs-title">我的账户</a>
				<dl class="sub-navs active">
					<dd>
						<a href="/member/home.htm" class="mlnks">安全设置</a>
					</dd>
					<dd>  
						<a href="/member/info.htm">账户信息</a>	
					</dd>

					<c:choose>
						<c:when test="${sessionScope.userinfo.operType=='1'}">  
						<dd><a href="/member/pay.htm">支付绑定</a></dd>
						<dd><a href="/member/manager.htm">操作员设置</a></dd>    
					    </c:when>
					    <c:otherwise> 
					   <dd><a href="/member/right.htm">账户权限</a></dd>
				       </c:otherwise>
		            </c:choose>
                    <dd><a href="/member/logout.htm">安全退出</a></dd>
	            </dl>
            </li>


    <c:forEach items="${sessionScope.userinfo.tradeMenus}" var="menu">
				<c:choose>
					<c:when test="${cUrl==menu.menuURL}">
						<li class="current">
					</c:when>
					<c:when test="${cUrl!=menu.menuURL}">
						<li>
					</c:when>
				</c:choose>
				
				<c:choose>
					<c:when test="${menu.hasSubMenu==false}">
						<a href="${menu.menuURL}" class="mlnks">${menu.menuName}</a>
					</c:when>
					<c:when test="${menu.hasSubMenu==true}">

						<c:set var="isExt" value="false" /> 
						<c:forEach items="${menu.subMenus}" var="subMenu">   
							<c:if test="${cL2Url==subMenu.menuURL}">
								<c:set var="isExt" value="true" />  
							</c:if> 
						</c:forEach>

						<c:choose>
							<c:when test="${isExt==true}">
								<em>-</em>
                                        		</c:when>
                                        		<c:when test="${isExt!=true}">
								<em>+</em>
                                        		</c:when>
                               			 </c:choose>	
						

						<a href="javascript:void(0);" class="user-navs-title">${menu.menuName}</a>
						<dl class="sub-navs active">
							<c:forEach items="${menu.subMenus}" var="subMenu">
								<dd>
								<c:choose>
                                    <c:when test="${cL2Url==subMenu.menuURL}">
                                       				 
									<a href="${subMenu.menuURL}" class="mlnks">${subMenu.menuName}</a>
                                     </c:when>
                                     <c:when test="${cL2Url!=subMenu.menuURL}">
									<a href="${subMenu.menuURL}" >${subMenu.menuName}</a>
                                     </c:when>
                               	 </c:choose>	
								</dd>
							</c:forEach>
						</dl>
					</c:when>
				</c:choose>
		</li>
	</c:forEach>
</ul>
</div>
</div>
