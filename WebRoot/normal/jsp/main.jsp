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
       </ul>
     </div>
   </div>
   
   <!-- #TO_DEL#
   <div class="selcomm" data-select>        
   </div> -->
   
   <!-- #TO_DEL#
   <div class="custfilter">                  
    <div class="filtersection">
     <div class="filterhd">品牌:</div>
     <div class="filterbd">
       <ul class="J_Brand">
        <li  class="active"  data-key="" >不限</li>
      </ul>
      <div class="more-btn">更多&#8870;</div>
    </div>

  </div>

  <div class="filtersection">
   <div class="filterhd">产地:</div>
   <div class="filterbd">
     <ul class="J_Origin">
      <li class="active"  data-key="" >不限</li>
    </ul>                     
    <div class="more-btn">更多&#8870;</div>
  </div>
</div>


<div class="filtersection">
 <div class="filterhd">价格:</div>
 <div class="filterbd">
   <input type="hidden" id="price-max" name="price-max" />
   <input type="hidden" id="price-min" name="price-min" />
   <ul class="J_Price">
    <li  class="active"  data-key="" >不限</li>                       
    <li data-max="1000"  >1000元以下</li>
    <li data-max="2000" data-min="1000" >1000-2000元</li>
    <li data-max="3000" data-min="2000" >2000-3000元</li>
    <li data-max="5000" data-min="5000" >3000-5000元</li>
    <li data-min="5000" >5000元以上</li>
  </ul>
  <div class="filtinterval">
    <input type="text" class="input-text" id="p-min" name="p-min" /><em>-</em>
    <input type="text" class="input-text" id="p-max" name="p-max" />
    <a id="priceBtn" class="filtbtn">查询</a>
  </div>
</div>
</div>

<div class="filtersection">
 <div class="filterhd">数量:</div>
 <div class="filterbd">
   <input type="hidden" id="volume-max" name="volume-max" />
   <input type="hidden" id="volume-min" name="volume-min" />
   <ul class="J_Volume">
    <li class="active"  data-key="" >不限</li>                        
    <li data-max="50" >0-50</li>
    <li data-max="100" data-min="50" >50-100</li>
    <li data-max="200" data-min="100" >100-200</li>
    <li data-max="500" data-min="200" >200-500</li>
    <li data-min="500" >500以上</li>
  </ul>
  <div class="filtinterval">
    <input type="text"  class="input-text" id="v-min" name="v-min"  onkeyup="this.value=this.value.replace(/\D/g,'')"  /><em>-</em>
    <input type="text"  class="input-text" id="v-max" name="v-max"  onkeyup="this.value=this.value.replace(/\D/g,'')"  />
    <a id="volumeBtn" class="filtbtn">查询</a>
  </div>
</div>
</div>
</div> -->


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



<!-- 产品主体部分 -->
<div class="product grid-16-16"></div>

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
         <div class="bd mt10">


          <input type="hidden" value="${sessionScope.userinfo.mID}" id="currMID" />
          <div class="row" id="datalist">


           <!-- #TO_DEL#
           <table id="dataset" class="cell-border hover" cellspacing="0" width="100%" style="padding: 0px">
            <thead>
              <tr>
               <th></th>
               <th></th>
               <th></th>
               <th></th>
               <th></th>
               <th></th>
               <th></th>
             </tr>
           </thead>
         </table> -->
         <div class="panel">
          	<div class="panel-title">
          		<strong class="product-name">尿素</strong>
          		<span class="product-price marr40"> 单价： <small>￥</small><big>1,200.00元/吨</big></span>
          		<span>有效期：2016-09-06</span>
          	</div>
          	<ul class="panel-body mart20">
          		<li><span class="liTitle">仓单编号</span> ：LS201609061441000799</li>
          		<li><span class="liTitle">数量</span> ：<span class="fcred">100/100吨</span>吨</li>
          		<li><span class="liTitle">品牌：</span>兰花</li>
          		<li><span class="liTitle">产地：</span>山  西</li>
          		<li><span class="liTitle">交收仓库</span> ：<span title="涿鹿县惠农农资有限公司库">涿鹿县惠农农资有限公司库</span></li>
          		<li><span class="liTitle">仓库地址</span> ：待开发...</li>
          		<li><span class="liTitle">交收类型</span> ：<span class="promise">保证金</span></li>
          	</ul>
          	<div class="describe-box">
          		<div class="product-describe"><p>待开发...</p></div>
          	</div>
          	<div class="product-bottom">
          		<div class="product-bl">编号：000000000023</div>
          		<div class="product-br"><span>卖家</span> 测试会员1</div>
          	</div>
          	<a href="/mall/item/LS201609061441000799.htm"></a>
          </div>
          <div class="panel">
          	<div class="panel-title">
          		<strong class="product-name">尿素</strong>
          		<span class="product-price marr40"> 单价： <small>￥</small><big>1,200.00元/吨</big></span>
          		<span>有效期：2016-09-06</span>
          	</div>
          	<ul class="panel-body mart20">
          		<li><span class="liTitle">仓单编号</span> ：LS201609061441000799</li>
          		<li><span class="liTitle">数量</span> ：<span class="fcred">100/100吨</span>吨</li>
          		<li><span class="liTitle">品牌：</span>兰花</li>
          		<li><span class="liTitle">产地：</span>山  西</li>
          		<li><span class="liTitle">交收仓库</span> ：<span title="涿鹿县惠农农资有限公司库">涿鹿县惠农农资有限公司库</span></li>
          		<li><span class="liTitle">仓库地址</span> ：待开发...</li>
          		<li><span class="liTitle">交收类型</span> ：<span class="promise">保证金</span></li>
          	</ul>
          	<div class="describe-box">
          		<div class="product-describe"><p>待开发...</p></div>
          	</div>
          	<div class="product-bottom">
          		<div class="product-bl">编号：000000000023</div>
          		<div class="product-br"><span>卖家</span> 测试会员1</div>
          	</div>
          	<a href="/mall/item/LS201609061441000799.htm"></a>
          </div>
          <div class="panel">
          	<div class="panel-title">
          		<strong class="product-name">尿素</strong>
          		<span class="product-price marr40"> 单价： <small>￥</small><big>1,200.00元/吨</big></span>
          		<span>有效期：2016-09-06</span>
          	</div>
          	<ul class="panel-body mart20">
          		<li><span class="liTitle">仓单编号</span> ：LS201609061441000799</li>
          		<li><span class="liTitle">数量</span> ：<span class="fcred">100/100吨</span>吨</li>
          		<li><span class="liTitle">品牌：</span>兰花</li>
          		<li><span class="liTitle">产地：</span>山  西</li>
          		<li><span class="liTitle">交收仓库</span> ：<span title="涿鹿县惠农农资有限公司库">涿鹿县惠农农资有限公司库</span></li>
          		<li><span class="liTitle">仓库地址</span> ：待开发...</li>
          		<li><span class="liTitle">交收类型</span> ：<span class="promise">保证金</span></li>
          	</ul>
          	<div class="describe-box">
          		<div class="product-describe"><p>待开发...</p></div>
          	</div>
          	<div class="product-bottom">
          		<div class="product-bl">编号：000000000023</div>
          		<div class="product-br"><span>卖家</span> 测试会员1</div>
          	</div>
          	<a href="/mall/item/LS201609061441000799.htm"></a>
          </div>
          <div class="panel">
          	<div class="panel-title">
          		<strong class="product-name">尿素</strong>
          		<span class="product-price marr40"> 单价： <small>￥</small><big>1,200.00元/吨</big></span>
          		<span>有效期：2016-09-06</span>
          	</div>
          	<ul class="panel-body mart20">
          		<li><span class="liTitle">仓单编号</span> ：LS201609061441000799</li>
          		<li><span class="liTitle">数量</span> ：<span class="fcred">100/100吨</span>吨</li>
          		<li><span class="liTitle">品牌：</span>兰花</li>
          		<li><span class="liTitle">产地：</span>山  西</li>
          		<li><span class="liTitle">交收仓库</span> ：<span title="涿鹿县惠农农资有限公司库">涿鹿县惠农农资有限公司库</span></li>
          		<li><span class="liTitle">仓库地址</span> ：待开发...</li>
          		<li><span class="liTitle">交收类型</span> ：<span class="promise">保证金</span></li>
          	</ul>
          	<div class="describe-box">
          		<div class="product-describe"><p>待开发...</p></div>
          	</div>
          	<div class="product-bottom">
          		<div class="product-bl">编号：000000000023</div>
          		<div class="product-br"><span>卖家</span> 测试会员1</div>
          	</div>
          	<a href="/mall/item/LS201609061441000799.htm"></a>
          </div>

         <div class="up-page">
          <div class="up-pagel">
            <div class="prev">
              <a href="" class="disabled"> < 第一页</a>
            </div>
            <div class="up-page-main">
              <a href="" >1</a>
              <a href="" >2</a>
              <a href="" >3</a>
              <a href="" >4</a>
              <span>...</span>
            </div>
            <div class="next">
             <a href="">下一页 > </a>
           </div>
         </div>
         <div class="up-pager">
          <span>共 15 页</span>
          <span>到第 <input type="text" value="1" /> 页</span>
          <a href="javascript:void(0);">确定</a>
        </div>
      </div>

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
