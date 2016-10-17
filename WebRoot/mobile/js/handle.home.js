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
                        $.Tips({
                            'message' : '密码修改成功',
                            'type' : 'info'
                        });

                    } else {
                        $(".modifyPwd").val('');
                        $('.rsmsg').html(data.msg);
                    }
                }
            });
        }

        $('#confirmbtn').click(function(e) {
            var str = $.trim($("#oldpassword").val());
            if (str.length == '') {
                $('.rsmsg').html("默认密码为空");
                return false;
            }

            if (str.length < 6 || str.length > 16) {
                $('.rsmsg').html("默认密码长度不正确");
                return false;
            }

            str = $.trim($("#newpassword").val());
            if (str.length == '') {
                $('.rsmsg').html("新密码为空");
                return false;
            }

            if (str.length < 6 || str.length > 16) {
                $('.rsmsg').html("新密码长度不正确");
                return false;
            }

            str = $.trim($("#confpassword").val());
            if (str.length == '') {
                $('.rsmsg').html("确认密码为空");
                return false;
            }

            if (str.length < 6 || str.length > 16) {
                $('.rsmsg').html("确认密码长度不正确");
                return false;
            }

            if ($("#newpassword").val() != $("#confpassword").val()) {
                $('.rsmsg').html("新密码与确认密码不一致");
                return false;
            }
            noTips();
        })
    }

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

   // 新增代码
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

 var myScroll,
	pullDownEl, pullDownOffset,
	pullUpEl, pullUpOffset,
	generatedCount = 0;


/**
 * 滚动翻页 （自定义实现此方法）
 * myScroll.refresh();		// 数据加载完成后，调用界面更新方法
 */
function pullUpAction () {
	alert()
}

/**
 * 初始化iScroll控件
 */
// function loaded() {

// 	pullUpEl = document.getElementById('loadMore');	
// 	pullUpOffset = pullUpEl.offsetHeight;
	
// 	myScroll = new IScroll('#product', {
// 		scrollbarClass: 'myScrollbar', /* 重要样式 */
// 		useTransition: false, /* 此属性不知用意，本人从true改为false */
// 		topOffset: pullDownOffset,
// 		onRefresh: function () {
// 			if (pullUpEl.className.match('loading')) {
// 				pullUpEl.className = '';
// 				pullUpEl.querySelector('.pullUpLabel').innerHTML = '上拉加载更多...';
// 			}
// 		},
// 		onScrollMove: function () {
// 			 if (this.y < (this.maxScrollY - 5) && !pullUpEl.className.match('flip')) {
// 				pullUpEl.className = 'flip';
// 				pullUpEl.querySelector('.pullUpLabel').innerHTML = '松手开始更新...';
// 				this.maxScrollY = this.maxScrollY;
// 			} else if (this.y > (this.maxScrollY + 5) && pullUpEl.className.match('flip')) {
// 				pullUpEl.className = '';
// 				pullUpEl.querySelector('.pullUpLabel').innerHTML = '上拉加载更多...';
// 				this.maxScrollY = pullUpOffset;
// 			}
// 		},
// 		onScrollEnd: function () {
// 			alert()
// 			if (pullUpEl.className.match('flip')) {
// 				pullUpEl.className = 'loading';
// 				pullUpEl.querySelector('.pullUpLabel').innerHTML = '加载中...';				
// 				pullUpAction();	// Execute custom function (ajax call?)
// 			}
// 		}
// 	});
	
// 	setTimeout(function () { document.getElementById('product').style.left = '0'; }, 800);
// }

//初始化绑定iScroll控件 
// loaded();

});
