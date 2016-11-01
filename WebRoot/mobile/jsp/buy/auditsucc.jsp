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

  <title> 买方摘牌</title>
  <script type="text/javascript">

   $(function() {

    $(document).ready(function() {
      $('.fixed-wrapper').stickUp();
    });

  });

</script>

</head>
<body class="drawer drawer-left">

	<div class="fixed-wrapper"> 
   <!-- header -->
   <jsp:include page="../comm/header.jsp" flush="true" />
   <!-- header End -->
 </div>

 <div class="main examine">

   <div class="header bornone">
     <div class="header-left"><a href="javascript:history.back(-1);" class="glyphicon glyphicon-menu-left"></a></div>
     <div class="logo ">
       处理信息
     </div>
   </div>

   <div class="container-fluid padb120">

     <div class="row">
       <div class="col-xs-12 lh40 bgwhite mart15 padtb15 txtcenter bordertb ">
         摘牌单号：<span class="fcgreen ">${rspBody.delistNo}</span> <span class="fcyellow ">(${rspBody.statusDesc})</span>
       </div>
     </div>
     
     <c:if test="${enablePay=='1'}">  
     <div class="examine-go txtcenter examine-btn">
      <a href="/buy/handle/P/${rspBody.delistNo}.htm"  class="btn-normal btn-buy">继续支付</a>
    </div>
    </c:if>

  </div>

</div>

</body>
</html>