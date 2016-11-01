<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<link rel="stylesheet" href="/mobile/css/drawer.css" />
<script src="/mobile/js/iscroll.js"></script>
<script src="/mobile/js/jquery.drawer.min.js"></script>

<div id="header" class="header">

    <div class="header-left" id="header-left"><a href="javascript:void(0);" class="glyphicon glyphicon-user" ></a></div>

    <div class="logo"><a href="/home.htm" ><img src="/mobile/images/logo.png"  alt="" class="img-responsive"  /></a></div>

    <div class="header-right">

       <a href="javascript:void(0);" class="glyphicon glyphicon-search" id="search"></a>

       <div class="btn-group" role="group">

           <a href="#" id="hamburger-menu"  class="glyphicon glyphicon-menu-hamburger dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"></a>

          <ul class="dropdown-menu dropdown-menu-right" aria-labelledby="hamburger" id="hamburger">
             <li><a href="/home.htm">交易大厅</a></li>
             <li><a ui-sref="{{topData[3]}}">全部商品</a></li>
         </ul>

     </div>

  </div>

</div>

<!-- search Start  -->
<div class="container-fluid top-search">

  	<div class="" id="J_SearchTab">
       <form action="/mall/search.htm" method="post" class="form-group has-feedback" id="top-search" >

           <div class="input-group">
            <input type="text" class="form-control input-lg" id="keyword" name="keyword" placeholder="您可根据商品名、商品类进行搜索" />
            <span class="input-group-addon " id="search-btn"><a href="javascript:void(0);"><span class="glyphicon glyphicon-search"></span></a></span>
          </div>

      </form>
  </div>

</div>
<!-- search End  -->

<!-- 左侧导航条 start  -->
<div class="drawer-main drawer-default">
    <nav class="drawer-nav" role="navigation">
        <div class="slideBar-menu"  >
            <div class="cd-navigation-wrapper">

                <div class="slide-close">
                 <img src="/mobile/images/close.png" alt="" />
               </div>

               <ul class="slide-top">
                    <li>
                    <!-- <span class="glyphicon glyphicon-user slide-uface"></span> -->
                     <img  alt="用户头像" src="${sessionScope.userinfo.operPhoto}" class="up-slide-uface" onerror="this.src='/normal/images/portrait.jpg'"  />
                     </li>
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
                    <c:forEach items="${sessionScope.userinfo.tradeMenus}" var="menu" varStatus="status">
                    	 <c:choose>
                    	 	<c:when test="${fn:contains(menu.menuURL,'/member/home.htm')}">
                    	 		<li><a href="/member/account.htm"><img src="/mobile/images/icon1.png" alt="" />我的账户</a></li>
                    	 	</c:when>
                    	 	<c:otherwise>
                    	 		<li><a href="${menu.menuURL}"><img src="/mobile/images/icon${status.index+1}.png" alt="" />${menu.menuName}</a></li>
                    	 	</c:otherwise>
                    	 </c:choose>	 
					          </c:forEach>
                    <li class="martb10 bornone menu_icon8"><a href="/sell/apply.htm?active=enter&type=0" class="btn btn-sm btn-success bnt-inlineblock">我要挂牌</a></li>
                    <li class="martb10 bornone menu_icon9"><a href="/member/logout.htm" class="btn btn-sm btn-warning bnt-inlineblock">退出</a></li>
                </ul>

            </div>
        </div>
    </nav>
</div>
<!-- 左侧导航条 end  -->

<!-- 页面加载 Start  -->
<div class="spinner-wrapper">
    <div class="spinner">
        <div class="spinner-container container1">
            <div class="circle1"></div>
            <div class="circle2"></div>
            <div class="circle3"></div>
            <div class="circle4"></div>
        </div>
        <div class="spinner-container container2">
            <div class="circle1"></div>
            <div class="circle2"></div>
            <div class="circle3"></div>
            <div class="circle4"></div>
        </div>
        <div class="spinner-container container3">
            <div class="circle1"></div>
            <div class="circle2"></div>
            <div class="circle3"></div>
            <div class="circle4"></div>
        </div>
    </div>
</div>
<!-- 页面加载 end  -->

<script>

 var touchFlag = true;

 $(document).ready(function($) {

     //o打开或关闭导航菜单
     $('.drawer').drawer();

     $('#header-left').click(function(){

        $('body').drawer("open");

    });
     $('.slide-close').click(function(){

           if(!$('body').hasClass('drawer-close')){
            $('.drawer-toggle').click();
        }
  
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
    * 文档加载完成显示页面内容
    */ 
    $(document).ready(function() {
   
      $('.spinner-wrapper').fadeOut();
      $('body').css('overflow','auto');
  });
   
  </script>
