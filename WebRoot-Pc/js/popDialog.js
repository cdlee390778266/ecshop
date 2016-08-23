/******        商品类型弹窗          ******/
$(function(){

$('.selcomm').hide();
	$('#product-dialog').on('click', function(){
		$('.selcomm').show();
		layer.open({
			type: 1,
			title : '商品选择',
			area: '600px',
        shadeClose: true, //点击遮罩关闭
        content: $('.selcomm'),
        closeBtn : 1,
        scrollbar : false,
        move: false,
        shift :1
    });

	});

	
})