<%@ page pageEncoding="UTF-8"%>
<%@ page import="org.springframework.mobile.device.Device" %>
<%
	Device device = (Device)request.getAttribute("currentDevice");
	if (device != null && device.isMobile()) {
		response.sendRedirect("/mobile/error/405.jsp");
	} else {
		response.sendRedirect("/normal/error/405.jsp");
	}
%>