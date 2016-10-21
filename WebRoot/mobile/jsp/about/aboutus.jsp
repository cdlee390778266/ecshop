<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn" %> 

<!DOCTYPE html>
<html >
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
    <link type="text/css" rel="stylesheet" href="/mobile/widget/bootstrap/css/bootstrap.css?v=${sessionScope.buildno}" />
    <link type="text/css" rel="stylesheet" href="/mobile/css/style.css?v=${sessionScope.buildno}" />
    <script type="text/javascript" src="/mobile/js/jquery.js"></script>
    <script type="text/javascript" src="/mobile/widget/bootstrap/js/bootstrap.js?v=${sessionScope.buildno}"></script>
    <script type="text/javascript" src="/mobile/js/stickup.js?v=${sessionScope.buildno}"></script>

    <script type="text/javascript">

        $(function() {

            $(document).ready(function() {
                $('.fixed-wrapper').stickUp();
            });
        });

    </script>

    <title>关于我们</title>
</head>
<body>

    <div class="fixed-wrapper"> 
        <!-- header Start -->
        <div id="header">
         <div class="header-left"><a href="javascript:history.back(-1);" class="glyphicon glyphicon-menu-left bgfff"></a></div>
         <div class="logo"><a ui-sref="home" href="javascript:void(0);"><img src="/mobile/images/logo.png" alt="" class="img-responsive"></a></div>
     </div>
     <!-- header End-->
 </div>

 <!-- wrapper Start -->
 <div class="aboutpage ">

   <div class="aboutBox">
       <h1 >联系我们</h1>
       <p>电话：0311-86031282</p>
       <p>地址：广州市海珠区新港西路82号广州交易所集团A栋</p>
   </div>

   <div class="aboutBox">
       <h1 >友情链接</h1>
       <p><span class="glyphicon glyphicon-plus"></span><a href="http://www.cmegroup.com" >芝加哥交易所</a></p>
       <p><span class="glyphicon glyphicon-plus"></span><a href="http://cnacex.com/content/home/index.htm" >中国农业交易平台</a></p>
   </div>

</div>
<!-- wrapper End -->

<!-- footer -->
<div class="container-fluid clearfix">

   <div id="footer" class="row footer">
      <div class="col-xs-12">
        <p>copyright©2014-2015 冀ICP备09082601</p>
        <p>北京市国农泰丰农业咨询有限公司版权所有</p>
        <p>技术支持 :上海乾隆高科技有限公司</p>
    </div>
</div>

</div>
<!-- footer End -->   

</body>
</html>