$(function() {
	
	$(document).ready(function() {
		$('.fixed-wrapper').stickUp();
	});

	var isAuth = parseInt($('#isAuth ').val(), 10);
    //isAuth 为0的时候表示需要修改密码，为1的时候不需要
    if (!isAuth) {
        // 弹出修改密码层
        Dialog = UP.Dialog('J_SetPwd');

        var modifyPwd = '/member/setpwd.htm';

        function noTips() {

        	$("#oldpassword").val(b64_sha1($("#oldpassword").val()));
        	$("#newpassword").val(b64_sha1($("#newpassword").val()));
        	$("#confpassword").val(b64_sha1($("#confpassword").val()));
            var formParam = $(".modifyPwd").serialize();// 序列化表格内容为字符串
            $.ajax({
            	type : 'post',
            	url : modifyPwd,
            	data : formParam,
            	cache : false,
            	dataType : 'json',
            	success : function(data) {
            		if (data.succflag == 0) {

            			Dialog.mask.hide();
            			Dialog.node.hide();
            			$(".modifyPwd").val('');
            			layer.msg('密码修改成功');

            		} else {
            			$(".modifyPwd").val('');
            			layer.msg(data.msg);
            		}
            	}
            });
        }

        $('#confirmbtn').click(function(e) {
        	var str = $.trim($("#oldpassword").val());
        	if (str.length == '') {
        		layer.msg("默认密码为空");
        		return false;
        	}

        	if (str.length < 6 || str.length > 16) {
        		layer.msg("默认密码长度不正确");
        		return false;
        	}

        	str = $.trim($("#newpassword").val());
        	if (str.length == '') {
        		layer.msg("新密码为空");
        		return false;
        	}

        	if (str.length < 6 || str.length > 16) {
        		layer.msg("新密码长度不正确");
        		return false;
        	}

        	str = $.trim($("#confpassword").val());
        	if (str.length == '') {
        		layer.msg("确认密码为空");
        		return false;
        	}

        	if (str.length < 6 || str.length > 16) {
        		layer.msg("确认密码长度不正确");
        		return false;
        	}

        	if ($("#newpassword").val() != $("#confpassword").val()) {
        		layer.msg("新密码与确认密码不一致");
        		return false;
        	}
        	noTips();
        })
    }

//轮播
var elem = document.getElementById('mySwipe');
window.mySwipe = Swipe(elem, {
    startSlide: 0,
    auto: 3000,
    continuous: true,
    disableScroll: true,
    stopPropagation: false,
    callback: function (index, element) {},
    transitionEnd: function (index, element) {
        $('.swipe-tabs span').removeClass('active');
        $('.swipe-tabs span').eq(index%2).addClass('active');
    }
});

//交易与市场下拉
$('.select-mark').click(function(){
    
    $('.select-mark-white').hide();
	$('.select .selectUl').slideUp(100);
    $('.stype').removeClass('active');
	$('.stype').parents('.select').find('.select-mark').hide();
	$('body').css('overflow','auto');

});

$('.select-mark,.select-mark-white,.select').on('touchmove',function(e){
   e.stopPropagation();
   e.preventDefault();
})

$('body').on('click','.stype',function(event){

	event.stopPropagation();
    $('.select-mark-white').show();
	$('.stype').parents('.select').find('.select-mark').show();
    if($(this).hasClass('active')){
        $('.select-mark').click();
    }else{
        $('.stype').removeClass('active');
        $(this).addClass('active');
        $('.select .selectUl').hide();
        $(this).find('.selectUl').slideDown(100);
        $('body').css('overflow','hidden');
    }
})

$('.select').on('click','li',function(event){

	event.stopPropagation();
	$(this).parent().find('li').css('color','#666');
	$(this).parent().find('li').find('span').css('display','none');
	$(this).css('color','#e5c04e');
	$(this).find('span').css('display','block');
	$('.select-mark').click();

})


//挂牌商品展示
$('.product-bar').off('click');
$('body').on('click','.product-bar',function(event){

	event.stopPropagation();
	var $self = $(this).find('.glyphicon-menu-down');
	if($(this).find('.glyphicon-menu-down').hasClass('slideOn')){
		$(this).find('.glyphicon-menu-down').css({'transform':'rotate(0)','color':'#333'});
		$(this).parents('.product').removeClass('active');
	}else{
		$(this).find('.glyphicon-menu-down').css({'transform':'rotate(180deg)','color':'#6db23d'});
		$(this).parents('.product').addClass('active');
	}

	$(this).find('.glyphicon-menu-down').parents('.product').find('.product-data').toggle(100,function(){
		$self.hasClass('slideOn') ? $self.removeClass('slideOn') : $self.addClass('slideOn')
	});

})

$('body').on('click','.product-data',function(event){

	if($(this).find('a').eq(0).attr('href'))
		location.href = $(this).find('a').eq(0).attr('href');

})

var tab = $("#product").tabulation("/mall/findtradelist.htm");

//加载更多
$(window).scroll(function(){

	if ((document.documentElement.scrollHeight) <= (document.documentElement.scrollTop | document.body.scrollTop) + document.documentElement.clientHeight + 10){
		$('.loadMore').click();
	}
    
});


});
