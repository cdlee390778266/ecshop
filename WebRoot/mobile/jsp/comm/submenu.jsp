<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<div class="row safe-type txtcenter bgfff up-list">
<c:forEach items="${sessionScope.userinfo.tradeMenus}" var="menu">
	<c:if test="${cUrl==menu.menuURL}">
		<c:choose>
			<c:when test="${menu.hasSubMenu==false}">
				<div class="col-xs-12">
					<a href="javascript:void(0);" class="active">${menu.menuName}</a>
				</div>
			</c:when>
			<c:when test="${menu.hasSubMenu==true}">
				<c:choose>
					<c:when test="${fn:length(menu.subMenus)==1}">
						<c:set var="size">12</c:set>
					</c:when>
					<c:when test="${fn:length(menu.subMenus)==2}">
						<c:set var="size">6</c:set>
					</c:when>
					<c:when test="${fn:length(menu.subMenus)==3}">
						<c:set var="size">4</c:set>
					</c:when>
					<c:when test="${fn:length(menu.subMenus)==4}">
						<c:set var="size">3</c:set>
					</c:when>
				</c:choose>
				
				<c:forEach items="${menu.subMenus}" var="subMenu">
					<c:choose>
						<c:when test="${cL2Url==subMenu.menuURL}">
							<div class="col-xs-${size}">
								<a href="javascript:void(0);" class="active">${subMenu.menuName}</a>
							</div>
						</c:when>
						<c:when test="${cL2Url!=subMenu.menuURL}">
							<div class="col-xs-${size}">
								<a href="${subMenu.menuURL}">${subMenu.menuName}</a>
							</div>
						</c:when>
					</c:choose>
				</c:forEach>
			</c:when>
		</c:choose>
	</c:if>
</c:forEach>
</div>

