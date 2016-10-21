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
  <link type="text/css" rel="stylesheet" href="/mobile/widget/css/ui.dialog.css" />    
  <link type="text/css" rel="stylesheet" href="/mobile/css/lrtk.css?v=${sessionScope.buildno}" /> 
  <script type="text/javascript" src="/mobile/js/jquery.js"></script>
  <script type="text/javascript" src="/mobile/js/cnacexutil.js?v=${sessionScope.buildno}"></script>
  <script type="text/javascript" src="/mobile/js/handlebars.js?v=${sessionScope.buildno}"></script>
  <script type="text/javascript" src="/mobile/js/ui.pagination.js?v=${sessionScope.buildno}"></script>
  <script type="text/javascript" src="/mobile/js/ui.focus.js?v=${sessionScope.buildno}"></script>
  <script type="text/javascript" src="/mobile/js/focus.js?v=${sessionScope.buildno}"></script>    
  <script type="text/javascript" src="/mobile/js/jquery.cnacex.banner.js?v=${sessionScope.buildno}"></script> 
  <script type="text/javascript" src="/mobile/js/stickup.js?v=${sessionScope.buildno}"></script>
  <script type="text/javascript" src="/mobile/widget/js/ui.dialog.js"></script>
  <script type="text/javascript" src="/mobile/js/ui.tips.js?v=${sessionScope.buildno}"></script>
  <script type="text/javascript" src="/mobile/js/sha.js"></script>

  <script src="/mobile/js/swipe.min.js"></script>  

  <jsp:include page="comm/mobile.jsp" flush="true" />

  <script src="/mobile/js/handle.main.table.js"></script>  

  <title>交易大厅</title>

</head>
<body class="drawer drawer-left">

	<div class="fixed-wrapper">  
   <!-- header -->
   <jsp:include page="comm/header.jsp" flush="true" />
   <!-- header End -->
 </div>

 <!-- banner Start -->
 <div id='mySwipe' style='margin: 0 auto' class='swipe'>
  <div class='swipe-wrap'>
    <div>
      <a href="javascript:void(0);"><img src="/mobile/images/banner1.png"  width="100%" /></a> 
    </div>
    <div>
      <a href="javascript:void(0);"><img src="/mobile/images/banner2.png" width="100%" /></a>
    </div>
  </div>
  <div class="swipe-tabs">
    <span class="active"></span>
    <span></span>
  </div>
</div>
<!-- banner End -->

<!-- select Start -->
<div class="container-fluid select">

  <div class="row borderb select-bar select-index">

    <div class="col-xs-6 stype">
      <h2>交易类型<span class="glyphicon glyphicon-menu-down"></span></h2>
      <ul class="selectUl">
        <li>不限<span class="glyphicon glyphicon-ok"></span></li>
        <li>保证金<span class="glyphicon glyphicon-ok"></span></li>
      </ul> 
    </div>

    <div class="col-xs-6 stype">
      <h2>市场<span class="glyphicon glyphicon-menu-down"></span></h2>
      <ul class="selectUl">
        <li>不限<span class="glyphicon glyphicon-ok"></span></li>
        <li>生产资料<span class="glyphicon glyphicon-ok"></span></li>
      </ul> 
    </div>

  </div>

  <div class="row">
    <div class="select-mark"></div>
  </div>

</div>
<!-- select End -->

<!-- product Start -->
<div class="gn-sSearch hide">
  <input type="text" placeholder="关键字搜索"  />
</div>

<div class="container-fluid mart15" >

  <div class="row" id="product">
    <input type="hidden" value="${sessionScope.userinfo.mID}" id="currMID" />
  </div>

  <!-- 加载更多 -->
  <div class="row rel">
   <div class="loadMore" data-page='1' id="loadMore">加载更多</div>
   <div class="loader" style="display: block;"><span></span><span></span><span></span><span></span>
     <div>拼命加载中...</div> 
   </div>
 </div>
 <!-- 加载更多 end-->

</div>
<!-- product End -->

<!-- <div class="container-fluid mart15">
  <div class="row" id="product"> -->

    <!-- 无商品时显示 -->
   <!--  <div class="noProduct mart10 marb60">
      <div class="noProductBox fc999">
        <div class="no-img"><img src="images/no-product.png" alt="" /></div>
        <div class="no-txt mart15">
          <p >Hi,</p>
          <p>您搜索的商品暂时不存在！</p>
          <p>共找到 <span class="fcgreen">0</span> 个商品</p>
        </div>
      </div>
    </div>
  </div> -->
  <!-- </div> -->


  <!-- 用于判断用户是否修改过密码 -->
  <input type="hidden" id="isAuth" <c:if test="${!empty pwdstatus}"> value="${pwdstatus}" </c:if> <c:if test="${empty pwdstatus}"> value="1" </c:if> />

  <!-- 第一次提醒用户修改密码 -->
  <div class="updialog d-add-role w350" id="J_SetPwd">
    <div class="hd">
     <h3>修改默认密码</h3>
   </div>
   <div class="bd">
     <div class="d-content">
      <div class="duserinfo">
       <div class="bd mt10">
        <div class="uitem">
         <table class="ui-table">
          <tr>
           <td colspan="2" style="text-align:center;color:#c0171e">友情提示：第一次登陆必须修改默认密码。</td>
         </tr>
         <tr>
           <td width="120px" class="tr" height="45px" >默认密码：</td>
           <td><input type="password" id="oldpassword" class="inp w160 modifyPwd" maxlength="16" name="oldpassword" onpaste='return false' oncopy='return false' tabindex="1" /> </td>
         </tr>
         <tr>
           <td class="tr" height="45px" >用户新密码：</td>
           <td><input type="password" id="newpassword" class="inp w160 modifyPwd" maxlength="16" name="newpassword" onpaste='return false' oncopy='return false' tabindex="2" /></td>
         </tr>
         <tr>
           <td class="tr" height="45px" >用户确认密码：</td>
           <td><input type="password" id="confpassword" class="inp w160 modifyPwd" maxlength="16" name="confpassword" onpaste='return false' oncopy='return false' tabindex="3" /></td>
         </tr>
         <tr>
           <td></td>
           <td>
            <div class="pt10"><button class="cbtn cpublish" data-value="" tabindex="4" id="confirmbtn">确认修改</button></div>        
          </td>
        </tr>
      </table>
    </div>
    <div class="rsmsg" style="text-align:center;color:#c0171e">
    </div>
  </div>
</div>
</div>
</div>
</div>

<script type="text/javascript" src="/mobile/js/handle.home.js?v=${sessionScope.buildno}"></script>


</body>
</html>
