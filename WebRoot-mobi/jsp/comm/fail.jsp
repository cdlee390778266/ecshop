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
    <link type="text/css" rel="stylesheet" href="/css/global.css" />
    <link type="text/css" rel="stylesheet" href="/css/font.css" />
    <link type="text/css" rel="stylesheet" href="/css/common.css" />
    <link type="text/css" rel="stylesheet" href="/css/member.css" />
    <script type="text/javascript" src="/js/jquery.js"></script>

    <jsp:include page="../comm/mobile.jsp" flush="true" />
    <title>处理提示</title>
    
</head>
<body>

	
	<!-- header -->
	<jsp:include page="../comm/header.jsp" flush="true" />
	<!-- header End -->
	
	<!-- wrapper -->
	<div class="container-fluid">
     <div class="row">
            <div class="col-xs-12 fail">
                <h3 style="color: #f47912;">${message}</h3>
            </div>
        </div>   
    </div>
		
			
			
				
			
		
	
	<!-- wrapper End -->		

	<!-- footer -->
	<jsp:include page="../comm/footer.jsp" flush="true" />
	<!-- footer End -->
</body>
</html>