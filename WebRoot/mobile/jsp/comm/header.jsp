<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<link rel="stylesheet" href="/mobile/css/drawer.css" />
<script src="/mobile/js/iscroll.js"></script>
<script src="/mobile/js/jquery.drawer.min.js"></script>


<div class="header" id="header">
  <div class="header-left" id="header-left"><a href="javascript:void(0);" class="glyphicon glyphicon-user" ></a></div>
  <div class="logo"><a href="/home.htm" ><img src="/mobile/images/logo.png"  alt="" class="img-responsive"  /></a></div>
  <div class="header-right">
     <a href="javascript:void(0);" class="glyphicon glyphicon-search" id="search"></a>
     <div class="btn-group" role="group">
        <a id="btnGroupVerticalDrop1"  class="glyphicon glyphicon-menu-hamburger dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"></a>
        <ul class="dropdown-menu dropdown-menu-right" aria-labelledby="btnGroupVerticalDrop1">
           <li><a href="/home.htm">交易大厅</a></li>
           <li><a ui-sref="{{topData[3]}}">全部商品</a></li>
       </ul>
   </div>
</div>
</div>

<div class="container-fluid top-search">
	<div class="" id="J_SearchTab">
		<div class="search-hd clearfix hide" >
			<ul class="subcat switchable-navs">
				<li class="J_SearchTab search-tab selected" data-action="/home.htm">全部商品<i></i></li>		
							<!--					  
							<li class="J_SearchTab search-tab" data-action="shop_search.htm">大厅<i></i></li>
							<li class="J_SearchTab search-tab" data-action="shop_search.htm">专场<i></i></li>	
						-->						
					</ul>
				</div>  

				<form action="/mall/search.htm" method="post" class="form-group has-feedback" id="top-search" >
					<div class="input-group">
						<input type="text" class="form-control input-lg" id="keyword" name="keyword" placeholder="您可根据商品名、商品类进行搜索" />
						<span class="input-group-addon " id="search-btn"><a href="javascript:void(0);"><span class="glyphicon glyphicon-search"></span></a></span>
					</div>
				</form>

					<!--  
					<div class="search-ft">
						<div class="search-hots">
							<div class="search-hots-sline">
								<dl>
									<dt><span>热门搜索：</span></dt>
									<dd>
										<a href="javascript: void(0)" class="h">化肥</a>
										<a href="javascript: void(0)" class="h">农药</a>
										<a href="javascript: void(0)" class="h">农用机械</a>
										<a href="javascript: void(0)" class="h">水果</a>
										<a href="javascript: void(0)" class="h">中药材</a>
										<a href="javascript: void(0)" class="h">农作物</a>
										<a href="javascript: void(0)" class="more more-with-border">更多&gt;&gt;</a>
									</dd>
								</dl>
								
							</div>
						</div>
					</div>
				-->
			</div>
		</div>

		<!-- 左侧导航条 start  -->
<div class="drawer-main drawer-default">
  <nav class="drawer-nav" role="navigation">
    <div class="slideBar-menu"  >
            <div class="cd-navigation-wrapper">
                <div class="slide-close">
                   <img src="/mobile/images/close.png" alt="" />
                </div>
                <ul class="slide-top">
                    <li><span class="glyphicon glyphicon-user slide-uface"></span></li>
                    <li >
                    <c:set var="level" value="${'0'}" />
                    <c:set var="memdesc" value="${''}" />
                    <c:if test="${sessionScope.userinfo.memLevel == '000'}">
                        <c:set var="level" value="${'5'}" />
                        <c:set var="memdesc" value="${'核心会员'}" />   
                    </c:if> 
                    <c:if test="${sessionScope.userinfo.memLevel == '001'}">
                        <c:set var="level" value="${'4'}" />
                        <c:set var="memdesc" value="${'1星级会员'}" />
                    </c:if> 
                    <c:if test="${sessionScope.userinfo.memLevel == '002'}">
                        <c:set var="level" value="${'3'}" />
                        <c:set var="memdesc" value="${'2星级会员'}" />
                    </c:if> 
                    <c:if test="${sessionScope.userinfo.memLevel == '100'}">
                        <c:set var="level" value="${'1'}" />
                        <c:set var="memdesc" value="${'普通会员'}" />
                    </c:if> 
                    <c:if test="${sessionScope.userinfo.memLevel == '888'}">
                        <c:set var="level" value="${'0'}" />
                        <c:set var="memdesc" value="${'信息会员'}" />
                    </c:if> 

                    <c:forEach var="x" begin="1" end="5" step="1">  
                        <c:if test="${level >= x}">
                           <span class="glyphicon glyphicon-star star-org"></span>
                        </c:if>                                                                 
                        <c:if test="${level < x}">
                           <span class="glyphicon glyphicon-star-empty star "></span>
                        </c:if>                                                                       
                    </c:forEach>    
                    </li>
                    <li class="star-font marb10">${memdesc} <br />${sessionScope.userinfo.memName}</li>
                </ul>
                <ul class="slide-middle">
                    <li><a href="/member/home.htm"><img src="/mobile/images/icon1.png" alt="" />我的账户</a></li>
                    <li><a href="/sell/list.htm" ><img src="/mobile/images/icon2.png" alt="" />我的订单</a></li>
                    <li><a href="/contract/list.htm"><img src="/mobile/images/icon3.png" alt="" />我的合同</a></li>
                    <li><a href="/delivery/selllist.htm" ><img src="/mobile/images/icon4.png" alt="" />交收管理</a></li>
                    <li><a href="/warehouse/list.htm" ><img src="/mobile/images/icon5.png" alt="" />我的仓单</a></li>
                    <li><a href="/query/selllist.htm" ><img src="/mobile/images/icon6.png" alt="" />交收历史</a></li>
                    <li><a href="/fund/info.htm"><img src="/mobile/images/icon7.png" alt="" />我的资金</a></li>
                    <li class="martb10 bornone menu_icon8"><a href="/sell/apply.htm?active=enter&type=0" class="btn btn-sm btn-success bnt-inlineblock">我要挂牌</a></li>
                    <li class="martb10 bornone menu_icon9"><a href="/member/logout.htm" class="btn btn-sm btn-warning bnt-inlineblock">退出</a></li>
                </ul>
            </div>
        </div>
  </nav>
</div>
<!-- 左侧导航条 end  -->



		<script>
			var touchFlag = true;
			$(document).ready(function($) {
     //o打开或关闭导航菜单
  $('.drawer').drawer();
    $('#header-left').click(function(){
      $('body').drawer("open");
    });
    $('.slide-close').click(function(){
        $('.drawer-toggle').click();
    })

       //search
       $('#search').click(function(){
       	$('.top-search').stop().slideToggle();
       })
       $('#search-btn').click(function(){
       	$('#top-search').submit();
       })
});
/** 
* @description 事件绑定，兼容各浏览器 
* @param target 事件触发对象 
* @param type 事件 
* @param func 事件处理函数 
*/ 
function addEvent(target, type, func) { 
    if (target.addEventListener) //非ie 和ie9 
    	target.addEventListener(type, func, false); 
    else if (target.attachEvent) //ie6到ie8 
    	target.attachEvent("on" + type, func); 
    else target["on" + type] = func; //ie5 
}; 

/** 
* @description 事件移除，兼容各浏览器 
* @param target 事件触发对象 
* @param type 事件 
* @param func 事件处理函数 
*/ 
function removeEvent(target, type, func){ 
	if (target.removeEventListener) {
		if(func != 'undefined'){
			target.removeEventListener(type, func, false);
		} else{
			target.removeEventListener(type);
		}
	}
	else if (target.detachEvent) 
		target.detachEvent("on" + type, func); 
	else target["on" + type] = null; 
};
//阻止默认事件
function prevent(e){
	e.preventDefault();
	e.stopPropagation();
}

</script>
