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
    <link type="text/css" rel="stylesheet" href="/mobile/css/style.css?v=${sessionScope.buildno}" />
    <link type="text/css" rel="stylesheet" href="/mobile/widget/css/ui.dialog.css" />    
    <link type="text/css" rel="stylesheet" href="/mobile/css/lrtk.css?v=${sessionScope.buildno}" /> 
    <script type="text/javascript" src="/mobile/js/jquery.js"></script>
   
    <script type="text/javascript" src="/mobile/js/selecttags_index.js?v=${sessionScope.buildno}"></script>
    <script type="text/javascript" src="/mobile/js/handlebars.js?v=${sessionScope.buildno}"></script>
    <script type="text/javascript" src="/mobile/js/ui.pagination.js?v=${sessionScope.buildno}"></script>
    <script type="text/javascript" src="/mobile/js/ui.focus.js?v=${sessionScope.buildno}"></script>
    <script type="text/javascript" src="/mobile/js/focus.js?v=${sessionScope.buildno}"></script>    
    <script type="text/javascript" src="/mobile/js/jquery.cnacex.banner.js?v=${sessionScope.buildno}"></script> 
    <script type="text/javascript" src="/mobile/js/stickup.js?v=${sessionScope.buildno}"></script>
    <script type="text/javascript" src="/mobile/widget/js/ui.dialog.js"></script>
    <script type="text/javascript" src="/mobile/js/ui.tips.js?v=${sessionScope.buildno}"></script>
    <script type="text/javascript" src="/mobile/js/sha.js"></script>

    <script src="/mobile/js/swiper.min.js"></script>  
    
    <jsp:include page="comm/datatables.jsp" flush="true" />
    <jsp:include page="comm/mobile.jsp" flush="true" />

<style>
  .commhead,.commbd{
    width: auto !important;
  }
  .rlist .commsection,.crumb-list,.ft{
    display: none;
  }
  .rlist .commsection:first-child{
    display: block;
  }

</style>

    <title>交易大厅</title>
</head>
<body>

	<div class="fixed-wrapper">  

     <!-- header -->
     <jsp:include page="comm/header.jsp" flush="true" />
     <!-- header End -->
 </div>

 <!-- banner Start -->
 <div class="swiper-container" >
  <div class="swiper-wrapper" style="font-size: 0px;">
    <div class="swiper-slide" >
        <img alt="" src="/mobile/images/banner1.png"  style="width: 100%" />
    </div>
    <div class="swiper-slide" >
        <img alt="" src="/mobile/images/banner2.png"  style="width: 100%" />
    </div>

</div>
<div class="swiper-pagination"></div>
</div>
<!-- banner End -->

<!-- select Start -->
<div class="container-fluid select">
    <div class="row borderb select-bar select-index">
        <div class="col-xs-4 stype">
            <h2>交易类型<span class="glyphicon glyphicon-menu-down"></span></h2>

             <ul class="selectUl">
            <li>不限<span class="glyphicon glyphicon-ok"></span></li>
            <li>保证金<span class="glyphicon glyphicon-ok"></span></li>
        </ul> 

        </div>

        <div>                  
             <div class="filtercomm">                                     
              <div class="selcomm_index" data-select>        
              </div>                  
          </div>  

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

</div>                
</div>        

        <div class="col-xs-4 stype">
            <h2>挂牌方式<span class="glyphicon glyphicon-menu-down"></span></h2>

             <ul class="selectUl">
            <li>不限<span class="glyphicon glyphicon-ok"></span></li>
            <li>农产品<span class="glyphicon glyphicon-ok"></span></li>
        </ul>   

        </div>
    </div>
    <div class="row">
        <div class="select-mark"></div>
    </div>
</div>
<!-- select End -->

<!-- product Start -->
<div class="container-fluid">
    <div class="form-group  has-feedback gn-sSearch">
      <input type="text" class="form-control" aria-describedby="inputSuccessStatus" placeholder="关键字搜索"  />
  </div>
  <div class="row" id="product">
  
    <!-- 无商品时显示 -->
    <div class="noProduct mart10 marb60">
        <div class="noProductBox fc999">
            <div class="no-img"><img src="images/no-product.png" alt="" /></div>
            <div class="no-txt mart15">
                <p >Hi,</p>
                <p>您搜索的商品暂时不存在！</p>
                <p>共找到 <span class="fcgreen">0</span> 个商品</p>
            </div>
        </div>
    </div>
</div>

<!-- 加载更多 -->
<!-- <div class="row rel">
   <div class="loadMore" ng-click="load();">加载更多</div>

   <div class="loader"><div class="loader-icon"> <div></div><div></div></div>加载中</div>

</div> -->

</div>
<!-- product End -->

<!-- wrapper -->
<div class="wrapper service-full marb60">
  <div class="grid-16-16">
   <!-- main -->

   <!-- main End -->

   <div class="main-content">
     <div class="bd">

      <div class="page-module product-set">
       <div >
           <div class="bd mt10">
           			

<input type="hidden" value="${sessionScope.userinfo.mID}" id="currMID" />


   <table id="dataset" class="cell-border hover tabble-index table" cellspacing="0" width="100%" style="padding: 0px">
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
</table>								  				
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

<script type="text/javascript" src="/mobile/js/handle.home.js?v=${sessionScope.buildno}"></script>
<!-- footer End -->


<script>
    //轮播
    var mySwiper = new Swiper ('.swiper-container', {
       direction: 'horizontal',
       loop: true,
       pagination : '.swiper-pagination',
       nextButton: '.swiper-button-next',
       prevButton: '.swiper-button-prev',
       paginationClickable: true,
       centeredSlides: true,
       autoplay: 5000,
       autoplayDisableOnInteraction: false
   });

    //select

    $('.select-mark').click(function(){
      $('.select .selectUl').slideUp(100);
      $('.stype').find('h2 span').css({
        'transform' : 'rotate(0deg)',
        'color' : '#666'
    });
      $('.stype').parents('.select').find('.select-mark').hide();
      removeEvent(document.getElementsByTagName('body')[0],"touchmove",prevent);
      $('body').css('overflow','auto');
  });
    $('body').on('click','.stype',function(event){
       event.stopPropagation();
       $('.stype').parents('.select').find('.select-mark').show();
       $('.stype').find('h2 span').css({
          'transform' : 'rotate(0deg)',
          'color' : '#666'
      });
       $(this).find('h2 span').css({
          'transform' : 'rotate(180deg)',
          'color' : '#e5c04e'
      });
       $('.select .selectUl').hide();
       $(this).find('.selectUl').slideDown(100);
       addEvent(document.getElementsByTagName('body')[0],"touchmove",prevent)  
       $('body').css('overflow','hidden');
   })
    $('.select').on('click','li',function(event){
       event.stopPropagation();
       $(this).parent().find('li').css('color','#666');
       $(this).parent().find('li').find('span').css('display','none');
       $(this).css('color','#e5c04e');
       $(this).find('span').css('display','block');
       $('.select-mark').click();
   })

    //product
    $('.product-bar').off('click');
    $('body').on('click','.product-bar',function(event){
        event.stopPropagation();
        var $self = $(this).find('.glyphicon-menu-down');
        $(this).find('.glyphicon-menu-down').hasClass('slideOn') ? $(this).find('.glyphicon-menu-down').css({'transform':'rotate(0)','color':'#333'}) : $(this).find('.glyphicon-menu-down').css({'transform':'rotate(180deg)','color':'#6db23d'});
        $(this).find('.glyphicon-menu-down').parents('.product').find('.product-data').toggle(100,function(){
          $self.hasClass('slideOn') ? $self.removeClass('slideOn') : $self.addClass('slideOn')
      });
    })

    $('body').on('click','.product-data',function(event){
      if($(this).find('a').eq(0).attr('href'))
        location.href = $(this).find('a').eq(0).attr('href');
    })

    //加载更多
    var loadMore = true;
    function load(){
      $('.loader').show();
      if(loadMore){
        loadMore = false;
          $.ajax({
            url : '',
            success : function(response){
              $scope.loadFlag = true;
              $('.loader').hide();
          }
      })
      }
  }
  $('.loadMore').click(load);

</script>


</body>
</html>
