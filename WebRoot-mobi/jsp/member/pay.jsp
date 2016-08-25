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
    <link rel="shortcut icon" href="/images/icon/favicon.ico" />
    <link type="text/css" rel="stylesheet" href="/css/font.css" />
    <script type="text/javascript" src="/js/jquery.js"></script>
    <script type="text/javascript" src="/js/stickup.js"></script>

    <jsp:include page="../comm/mobile.jsp" flush="true" />

    <title>支付绑定</title>
    
</head>
<body>

	<div class="fixed-wrapper"> 
	
	<!-- header -->
	<jsp:include page="../comm/header.jsp" flush="true" />
	<!-- header End -->
	
	</div>
	
	<!-- wrapper -->
	
			<div class="main safe examine" >
    <div id="header">
     <div class="header-left"><a href="javascript:history.back(-1);" class="glyphicon glyphicon-menu-left"></a></div>
     <div class="logo">
       支付绑定
     </div>
   </div>
   <div class="container-fluid">
    <div class="row marb60">

    <c:forEach items="${accList}" var="detail">	
    <div class="cox-xs-12 bgwhite operBox " >
        <div class="media">
          <div class="media-left media-middle padlr15 ">
            <a href="#">
            <img class="media-object" src="/images/bind.png" alt="...">
            </a>
          </div>
          <div class="media-body">
            <h4 class="media-heading ">${detail.pbATypeName}</h4>
          ${detail.pbAcct}
          </div>
           <div class="media-body">
           <span class="glyphicon glyphicon-link"></span>
            <span class="fc999">${detail.statusDesc}</span>
          </div>
        </div>
      </div>
      </c:forEach>

    </div>
  </div>


</div>

	<!-- wrapper End -->		

	<!-- footer -->
	<jsp:include page="../comm/footer.jsp" flush="true" />
	<!-- footer End -->
	<script type="text/javascript">
	jQuery(function($) {
			$(document).ready(function() {
				$('.fixed-wrapper').stickUp();
			});
	});
	</script>
</body>
</html>