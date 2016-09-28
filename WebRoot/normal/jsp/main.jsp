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
  <link rel="shortcut icon" href="/normal/images/icon/favicon.ico" />
  <link type="text/css" rel="stylesheet" href="/normal/css/global.css" />
  <link type="text/css" rel="stylesheet" href="/normal/css/common.css" />
  <link type="text/css" rel="stylesheet" href="/normal/css/home.css?v=${sessionScope.buildno}" />
  <link type="text/css" rel="stylesheet" href="/normal/css/member.css?v=${sessionScope.buildno}" />
  <link type="text/css" rel="stylesheet" href="/normal/css/commsel.css?v=${sessionScope.buildno}" />
  
  <link type="text/css" rel="stylesheet" href="/normal/widget/css/ui.dialog.css" />    
  <link type="text/css" rel="stylesheet" href="/normal/css/lrtk.css?v=${sessionScope.buildno}" /> 
  <script type="text/javascript" src="/normal/js/jquery.js"></script>
  <script type="text/javascript" src="/normal/js/cnacexutil.js?v=${sessionScope.buildno}"></script>
  <script type="text/javascript" src="/normal/js/selecttags_index.js?v=${sessionScope.buildno}"></script>
  <script type="text/javascript" src="/normal/js/handlebars.js?v=${sessionScope.buildno}"></script>
  <script type="text/javascript" src="/normal/js/ui.pagination.js?v=${sessionScope.buildno}"></script>
  <script type="text/javascript" src="/normal/js/ui.focus.js?v=${sessionScope.buildno}"></script>
  <script type="text/javascript" src="/normal/js/focus.js?v=${sessionScope.buildno}"></script>    
  <script type="text/javascript" src="/normal/js/jquery.cnacex.banner.js?v=${sessionScope.buildno}"></script> 
  <script type="text/javascript" src="/normal/js/stickup.js?v=${sessionScope.buildno}"></script>
  <script type="text/javascript" src="/normal/widget/js/ui.dialog.js"></script>
  <script type="text/javascript" src="/normal/js/ui.tips.js?v=${sessionScope.buildno}"></script>
  <script type="text/javascript" src="/normal/js/sha.js"></script>
  <script type="text/javascript" src="/normal/js/swiper.min.js"></script>
  <script type="text/javascript" src="/normal/js/handle.home.beta.js?v=${sessionScope.buildno}"></script>
  <script type="text/javascript" src="/normal/js/handle.main.table.js?v=${sessionScope.buildno}"></script>
  
  <!-- <jsp:include page="comm/datatables_index.jsp" flush="true" /> -->

  <style>
    .selcomm .rlist .commhead{
     width: auto !important;
   }
   .selcomm{
    overflow: visible;
    display: inline-block;
  }
  .rlist .input-group{
    display: none !important;
  }
  .rlist .input-group:first-child{
    display: inline-block !important;
  }
</style>

<title>交易大厅</title>

</head>
<body>

	<div class="fixed-wrapper">  
   <!-- topbar -->
   <jsp:include page="comm/topbar.jsp" flush="true" />

   <!-- topbar End -->

   <!-- header -->
   <jsp:include page="comm/header.jsp" flush="true" />
   <!-- header End -->
 </div>


 <!-- banner Start -->
 <div class="swiper-container">
  <div class="swiper-wrapper">
    <div class="swiper-slide" style="background-image:url(/normal/images/banner/ad1.png)"></div>
    <div class="swiper-slide" style="background-image:url(/normal/images/banner/ad2.png)"></div>
  </div>
  <!-- Add Pagination -->
  <div class="swiper-pagination swiper-pagination-white"></div>
  <!-- Add Arrows -->
  <div class="swiper-button-next swiper-button-white"></div>
  <div class="swiper-button-prev swiper-button-white"></div>
</div>
<!-- banner End -->

<!-- 筛选 -->
<div class="grid-16-16 screen">
  <div class="screen-title">
    <h1>最新挂牌</h1>
  </div>
  <div class="screen-main">
    <div class="input-group">
     <div class="group-txt">交收类型</div>
     <div class="group-box">
       <span class="val"><span>不限 </span><i></i></span>
       <ul>
         <li>保证金</li>
         <li>仓单</li>
       </ul>
     </div>
   </div>
   



<div class="input-group">
 <div class="group-txt">挂牌方式</div>
 <div class="group-box">
   <span class="val"><span>不限 </span><i></i></span>
   <ul>
     <li>买方挂牌</li>
     <li>卖方挂牌</li>
   </ul>
 </div>
</div>
<div class="screen-search">
 <label for=""></label>
 <input type="text" placeholder="在结果中搜索关键字" />
</div>
</div>

</div>
<!-- 筛选 -->


<div class="wrapper service-full mt30">
  <div class="grid-16-16">
   <!-- main -->


   <div class="main">


   </div>

   <!-- main End -->

   <div class="main-content">
     <div class="bd">

      <div class="page-module product-set">
       <div class="row">
         <div class="product">

          <input type="hidden" value="${sessionScope.userinfo.mID}" id="currMID" />
          <div class="row" id="datalist">

    </div>  				
  </div>
</div>
</div>
</div>
</div>			
</div>	
<!--  content End -->			
</div>


<!-- wrapper End -->


<script type="text/x-handlebars-template" id="entryTemplate">
	<table class="ui-table table-primary">
		<tbody>
      {{#listeds}}
      <tr>
        <td rowspan="3" width="200px">		
         <div class="pic"><a target="_blank" href="/mall/item/{{listedNo}}.htm" >{{imgshow titlePic}}</a></div>
         <div class="title">{{title}}</div>
       </td>
       <td class="ctl-2" width="650px"><span style="padding-left:15px">{{commName}}</span> </td>
       <td class="ctl-3"><span class="cell">有效期:{{doe}}</span></td> 
     </tr>
     <tr>
       <td rowspan="2">
        <table>
         <tr>
          <td class="ctl" width="25%"><span>单价:{{money up}}/{{uom}}</span></td>
          <td class="ctl" width="25%"><span>现量:{{rem}}/{{qty}}</span></td>
          <td><span class="cell">交收仓库:{{storage}}</span></td>
          <td><span class="cell">交收类型:{{listedTypeName}}</span>
          </tr>
          <tr>
            <td colspan="4">
             {{HandleSummary summary1 summary2 summary3 summary4 }}
           </td>
         </tr>
       </table>
     </td>
     <td class="ctl">
      <div style="text-align:left;">卖家:{{memName}}<br/>编号:{{mid}}</div>
    </tr>
    <tr>
      <td>
       {{delistbtn listedNo mid}}
     </td>
   </tr>
   {{/listeds}}							
 </tbody>
</table>
</script>
<!-- footer -->
<jsp:include page="comm/footer.jsp" flush="true" />
<!-- footer End -->


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
<!-- footer End -->
<!-- Initialize Swiper -->
<script>
  var swiper = new Swiper('.swiper-container', {
    pagination: '.swiper-pagination',
    paginationClickable: true,
    nextButton: '.swiper-button-next',
    prevButton: '.swiper-button-prev',
    spaceBetween: 30,
    effect: 'fade'
  });
  
  $(document).ready(function(){
	  var tab = $("#datalist").tabulation("/mall/findtradelist.htm");
	  console.log(tab);
  });
  
  $(function(){
    $('body').on('click','.group-box',function(event){
     event.stopPropagation();
     $('.group-box ul').slideUp(10);
     $(this).find('ul').slideDown(10);
   })
    $(document).click(function(){
      $('.group-box ul').slideUp(10);
    })
    $('body').on('click','.group-box ul li',function(event){
      event.stopPropagation();
      $(this).parents('.group-box').find('.val span').html($(this).html());
      $(document).click();
    })

    $('body').on('click','.panel',function(){
      location.href = $(this).find('a').eq(0).attr('href');
    })
})

</script>
</body>
</html>
