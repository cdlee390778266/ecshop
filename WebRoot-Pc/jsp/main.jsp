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
    
    <link type="text/css" rel="stylesheet" href="/css/font-awesome.min.css" />
    <link type="text/css" rel="stylesheet" href="/css/font-awesome-ie7.min.css" />

    <link type="text/css" rel="stylesheet" href="/css/global.css" />
    <link type="text/css" rel="stylesheet" href="/css/common.css" />
    <link type="text/css" rel="stylesheet" href="/css/home.css?v=${sessionScope.buildno}" />
    <link type="text/css" rel="stylesheet" href="/css/member.css?v=${sessionScope.buildno}" />
    <link type="text/css" rel="stylesheet" href="/css/commsel.css?v=${sessionScope.buildno}" />
    <link type="text/css" rel="stylesheet" href="/css/selecttags.css?v=${sessionScope.buildno}" >
    <link type="text/css" rel="stylesheet" href="/widget/css/ui.dialog.css" />    
    <link type="text/css" rel="stylesheet" href="/css/lrtk.css?v=${sessionScope.buildno}" /> 
    <script type="text/javascript" src="/js/jquery.js"></script>
    <script type="text/javascript" src="/js/selecttags.js?v=${sessionScope.buildno}"></script>
    <script type="text/javascript" src="/js/handlebars.js?v=${sessionScope.buildno}"></script>
    <script type="text/javascript" src="/js/ui.pagination.js?v=${sessionScope.buildno}"></script>
    <script type="text/javascript" src="/js/ui.focus.js?v=${sessionScope.buildno}"></script>
    <script type="text/javascript" src="/js/focus.js?v=${sessionScope.buildno}"></script>    
    <script type="text/javascript" src="/js/jquery.cnacex.banner.js?v=${sessionScope.buildno}"></script> 
    <script type="text/javascript" src="/js/stickup.js?v=${sessionScope.buildno}"></script>
    <script type="text/javascript" src="/widget/js/ui.dialog.js"></script>
    <script type="text/javascript" src="/js/ui.tips.js?v=${sessionScope.buildno}"></script>
    <script type="text/javascript" src="/js/sha.js"></script>
    <script type="text/javascript" src="/js/swiper.min.js"></script>
    
    <jsp:include page="comm/datatables.jsp" flush="true" />

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


 <!-- Swiper -->
 <div class="swiper-container">
    <div class="swiper-wrapper">
        <div class="swiper-slide" style="background-image:url(/images/banner/ad1.png)"></div>
        <div class="swiper-slide" style="background-image:url(/images/banner/ad2.png)"></div>
    </div>
    <!-- Add Pagination -->
    <div class="swiper-pagination swiper-pagination-white"></div>
    <!-- Add Arrows -->
    <div class="swiper-button-next swiper-button-white"></div>
    <div class="swiper-button-prev swiper-button-white"></div>
</div>
</div>
<!-- wrapper -->

<!-- 筛选 -->
<div class="grid-16-16 screen">
    <div class="screen-title">
        <h1>最新挂牌</h1>
    </div>
    <div class="screen-main">
        <div class="input-group">
           <div class="group-txt">交易类型</div>
           <div class="group-box">
               <span class="val"><span>逗 </span><i></i></span>
               <ul>
                   <li>逗比的世界你不懂</li>
                   <li>逗比的世界你不懂</li>
                   <li>逗比的世界</li>
               </ul>
           </div>
       </div>
       <div class="input-group">
           <div class="group-txt">市场</div>
           <div class="group-box">
               <span class="val"><span>逗 </span><i></i></span>
               <ul>
                   <li>逗比的世界你不懂</li>
                   <li>逗比的世界你不懂</li>
                   <li>逗比的世界</li>
               </ul>
           </div>
       </div>
       <div class="input-group">
           <div class="group-txt">挂牌方式</div>
           <div class="group-box">
               <span class="val"><span>逗 </span><i></i></span>
               <ul>
                   <li>逗比的世界你不懂</li>
                   <li>逗比的世界你不懂</li>
                   <li>逗比的世界</li>
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
<div class="grid-16-16 product mart20">

   <!--  <div class="panel">
        <div class="panel-title">
         <strong class="product-name">
             磷酸二铵
         </strong>
         <span class="product-price marr40">
             单价： <small>￥</small><big>2999.00/Kg</big>
         </span>
         <span>有效期：2016-12-07</span>
     </div>
     <ul class="panel-body mart20">
        <li>
            <span class="liTitle">仓单编号</span> ：123456789
        </li>
        <li>
            <span class="liTitle">交收仓库</span> ：逐鹿战场 群雄逐鹿
        </li>
        <li>
            <span class="liTitle">数量</span> ：<span class="fcred">1/1</span>吨
        </li>
        <li>
            <span class="liTitle">交收仓库</span> ：逐鹿战场 群雄逐鹿
        </li>
        <li>
            <span class="liTitle">品牌</span> ：不在地球
        </li>
        <li>
            <span class="liTitle">产地</span> ：极度幽寒
        </li>
        <li>
            <span class="liTitle">交易类型</span> ：<span class="promise">我说了算</span>
        </li>
    </ul>
    <div class="describe-box">
        <div class="product-describe">
            <p >急需100吨磷酸二铵，品牌包括：白鹇，三环，云峰 <br />其它品牌的就不要来摘牌了，谢谢！</p>
        </div>
    </div>
    <div class="product-bottom">
        <div class="product-bl">
            编号：11111111111
        </div>
        <div class="product-br">
            <span>买家</span> 
            河北省农业生产资料有限公司磷复肥-公司
        </div>
    </div>
</div>

<div class="panel">
    <div class="panel-title">
     <strong class="product-name">
         磷酸二铵
     </strong>
     <span class="product-price marr40">
         单价： <small>￥</small><big>2999.00/Kg</big>
     </span>
     <span>有效期：2016-12-07</span>
 </div>
 <ul class="panel-body mart20">
    <li>
        <span class="liTitle">仓单编号</span> ：123456789
    </li>
    <li>
        <span class="liTitle">交收仓库</span> ：逐鹿战场 群雄逐鹿
    </li>
    <li>
        <span class="liTitle">数量</span> ：<span class="fcred">1/1</span>吨
    </li>
    <li>
        <span class="liTitle">交收仓库</span> ：逐鹿战场 群雄逐鹿
    </li>
    <li>
        <span class="liTitle">品牌</span> ：不在地球
    </li>
    <li>
        <span class="liTitle">产地</span> ：极度幽寒
    </li>
    <li>
        <span class="liTitle">交易类型</span> ：<span class="promise">我说了算</span>
    </li>
</ul>
<div class="describe-box">

</div>
<div class="product-bottom">
    <div class="product-bl">
        编号：11111111111
    </div>
    <div class="product-br">
        <span>买家</span> 
        河北省农业生产资料有限公司磷复肥-公司
    </div>
</div>
</div>
 -->







</div>
<!-- 产品主体部分 -->


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
            <div class="row">									 

             <div class="filtercomm">																			
              <div class="selcomm" data-select>        
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

<input type="hidden" value="${sessionScope.userinfo.mID}" id="currMID" />
<div class="row">

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
</table>								  
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

<script type="text/javascript" src="/js/handle.home.js?v=${sessionScope.buildno}"></script>
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
    $(function(){
        $('.group-box').click(function(event){
         event.stopPropagation();
         $('.group-box ul').slideUp(10);
         $(this).find('ul').slideDown(10);
     })
        $(document).click(function(){
            $('.group-box ul').slideUp(10);
        })
        $('.group-box ul li').click(function(event){
            event.stopPropagation();
            $(this).parents('.group-box').find('.val span').html($(this).html());
            $(document).click();
        })

        var str = '';
        $.ajax({
            url : '/mall/findunlimitlist.htm',
            success : function(res){

               //console.log(res);
               $.each(res.data.listeds, function(index, val) {
                 str +=  '<div class="panel">'
                 str +=  '<div class="panel-title">'
                 str +=  '<strong class="product-name">' + val.commName
                 str +=  '</strong>'
                 str +=  '<span class="product-price marr40">'
                 str +=  ' 单价： <small>￥</small><big>' + val.up + '/Kg</big>'
                 str +=  '</span>'
                 str +=  '<span>有效期：' + val.dol + '</span>'
                 str +=  '</div>'
                 str +=  '<ul class="panel-body mart20">'
                 str +=  '<li>'
                 str +=  '<span class="liTitle">仓单编号</span> ：' + val.listedNo
                 str +=  '</li>'
                 str +=  '<li>'
                 str +=  '<span class="liTitle">品牌</span> ：' + val.summary1
                 str +=  '</li>'
                 str +=  '<li>'
                 str +=  '<span class="liTitle">数量</span> ：<span class="fcred">' + val.rem + '' + val.qty + '</span>吨'
                 str +=  '</li>'
                 str +=  '<li>'
                 str +=  '<span class="liTitle">产地</span> ：' + val.summary2
                 str +=  '</li>'
                 str +=  '<li>'
                 str +=  '<span class="liTitle">仓库名称</span> ：<span title="' + val.storage  +'">' + val.storage +'</span>'
                 str +=  '</li>'
                 str +=  '<li>'
                 str +=  '<span class="liTitle">仓库地址</span> ：'+ '待开发...'
                 str +=  '</li>'
                 str +=  '<li>'
                 str +=  '<span class="liTitle">交易类型</span> ：<span class="promise">' + val.listedTypeName + '</span>'
                 str +=  '</li>'
                 str +=  '</ul>'
                 str +=  '<div class="describe-box">'
                 str +=  '<div class="product-describe"><p >' + '待开发...' + '</p>'
                 str +=  '</div>'
                 str +=  '</div>'
                 str +=  '<div class="product-bottom">'
                 str +=  '<div class="product-bl">'
                 str +=  '编号：' + val.mid
                 str +=  '</div>'
                 str +=  '<div class="product-br">'
                 str +=  '<span>卖家</span> ' + val.memName
                 str +=  '</div>'
                 str +=  '</div>'
                 str +=  '</div>'
                 console.log(val)
             });
$('.product').append(str)
}
})




})




</script>
</body>
</html>
