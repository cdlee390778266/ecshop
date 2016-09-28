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
    <link type="text/css" rel="stylesheet" href="/mobile/css/font.css" />
    <script type="text/javascript" src="/mobile/js/jquery.js"></script>
    <script type="text/javascript" src="/mobile/js/stickup.js"></script>

    <jsp:include page="../comm/mobile.jsp" flush="true" />

    <title>支付绑定</title>
    
</head>
<body class="drawer drawer-left">

	<div class="fixed-wrapper"> 
	
	<!-- header -->
	<jsp:include page="../comm/header.jsp" flush="true" />
	<!-- header End -->
	
	</div>
	
	<!-- wrapper -->
	
			<div class="main safe examine" >
    <div class="header borhb">
     <div class="header-left"><a href="javascript:history.back(-1);"><img src="/mobile/images/back.png" alt=""></a></div>
     <div class="logo">
       支付绑定
     </div>
   </div>
   <div class="container-fluid bgfff minh400">
    <div class="row marb60">

    <c:forEach items="${accList}" var="detail">	
    <div class="cox-xs-12" >
        <div class="media operBox">
          <div class="media-left media-middle padl5 ">
            <a href="#">
            <img class="media-object" src="/mobile/images/bind.png" alt="...">
            </a>
          </div>
          <div class="media-body">
            <p class="mart5">${detail.pbATypeName}</p>
          <p class="marb5">${detail.pbAcct}</p>
          </div>
           <div class="media-body lh50">
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