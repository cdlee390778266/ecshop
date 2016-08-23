<%@ page language="java" pageEncoding="UTF-8"%>
<div class="header" id="header">
		<div class="header-left" id="header-left"><a href="javascript:void(0);" class="glyphicon glyphicon-user" ></a></div>
		<div class="logo"><a ui-sref="home" ><img src="/images/logo.png"  alt="" class="img-responsive"  /></a></div>
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

		<!-- 左侧导航条  -->
		<div class="slideBar-menu"  >
			<div class="cd-navigation-wrapper">
				<div class="slide-close">
					<a href="" class="glyphicon glyphicon-remove"></a>
				</div>
				<ul class="slide-top">
					<li><span class="glyphicon glyphicon-user slide-uface"></span></li>
					<li >
						<span class="glyphicon glyphicon-star star-org"></span>
						<span class="glyphicon glyphicon-star star-org"></span>
						<span class="glyphicon glyphicon-star star-org"></span>
						<span class="glyphicon glyphicon-star-empty star "></span>
						<span class="glyphicon glyphicon-star-empty star"></span>
					</li>
					<li class="star-font">三星级会员</li>
				</ul>
				<ul class="slide-middle">
					<li><a href="">我的账户</a></li>
					<li><a href="">我的订单</a></li>
					<li><a href="">我的合同</a></li>
					<li><a href="">交收管理</a></li>
					<li><a href="">我的仓单</a></li>
					<li><a href="">交收历史</a></li>
					<li><a href="">我的资金</a></li>
					<li ><a  ui-sref="{{button[3]}}" class="btn btn-sm btn-success bnt-inlineblock">我要挂牌</a></li>
					<li ><a ui-sref="{{button[1]}}" class="btn btn-sm btn-warning bnt-inlineblock">退出</a></li>
				</ul>
			</div>

		</div>
		<div class="mark" style="display:none"></div>
		<!-- 左侧导航条  -->

		<script>
			var touchFlag = true;
			$(document).ready(function($) {
     //o打开或关闭导航菜单
     $('#header-left').on('click', function(event) {
     	event.preventDefault();    
     	$('body').addClass('navigation-is-open').css('overflow','hidden');
     	$('.mark').show(); 
      //禁用滚动
      if(touchFlag){
      	addEvent(document.body,"touchmove",prevent)  
      	touchFlag =  false;
      }
  });
     $('.mark,.slide-close').click(function(event){
     	event.preventDefault();
     	$('.mark').hide();
     	$('body').removeClass('navigation-is-open').css('overflow','auto');
      //解除禁用滚动
      if(!touchFlag){
      	removeEvent(document.body,"touchmove",prevent)
      	touchFlag = true;
      }
  })

       //search
       $('#search').click(function(){
       	$('.top-search').stop().slideToggle();
       })
       $('#search-btn').click(function(){
       	$('#top-search').submit();
       })
       
     // alert(document.getElementsByClassName('slide-middle')[0].offsetHeight)
 //slidebar 滚动天到底时解除
//  $('.slideBar-menu').scroll(function(){
//   　　var scrollTop = $(this).scrollTop();
//   　　var scrollHeight = $('.slideBar-menu').height();
//   　　var windowHeight = $(window).height();
// console.log(scrollTop,scrollHeight,windowHeight)
//   　　if(scrollTop + windowHeight >= 700){
//     　　　　addEvent(document.getElementsByClassName('slideBar-menu')[0],"touchmove",prevent)  
//   　　}else{
//    removeEvent(document.getElementsByClassName('slideBar-menu')[0],"touchmove",prevent)
//  }
// });

// var slide = document.getElementsByClassName('slideBar-menu')[0];

// var mc = new Hammer(slide);
// //mc.get('pan').set({ direction: Hammer.DIRECTION_ALL });
// // listen to events...
// mc.on("panup", function(ev) {
//     　var scrollTop = $('.slideBar-menu').scrollTop();
//   　　var scrollHeight = $('.slideBar-menu').height();
//   　　var windowHeight = $(window).height();
// console.log(scrollTop,scrollHeight,windowHeight)

//   　　if(scrollTop + windowHeight >= 500){

//     　　　　addEvent(document.getElementsByClassName('slideBar-menu')[0],"touchmove",prevent)  
//   　　}
// });
// mc.on("pandown", function(ev) {
//    removeEvent(document.getElementsByClassName('slideBar-menu')[0],"touchmove",prevent)
// })


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
