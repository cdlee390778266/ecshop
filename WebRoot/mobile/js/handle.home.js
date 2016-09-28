$(function() {
	
	$(document).ready(function() {
		$('.fixed-wrapper').stickUp();
	});

	//标题选择列ID
	var columnselector =new Array();
	var columnlength =  $('#dataset thead').find('th').length-1;
	
	for(var i = 0; i < columnlength; i++){
		columnselector.push(i);
	}
	
	var $dataset;
	
	//定义页面的DataTable对象
	function initTable(querypara, haveleaf){
		if($dataset != null){
			$dataset.destroy();			
			$('#dataset').empty();
		}
		
		$('.custfilter').hide();
		
		if(haveleaf == false){
			$dataset = $('#dataset').DataTable( {
				"ajax": {
					"url": "/mall/findtradelist.htm",
					"data": function ( d ) {
						d.querypara = querypara;
					}
				},	
				ordering:true,
				"processing" : true,
				"language": {
					"processing": "",
					"lengthMenu": "每页显示 _MENU_ 条",
					"zeroRecords": "无相关数据记录",
					"info": "显示从_START_ 到 _END_ 记录,共 _TOTAL_ 条记录", 
					"infoEmpty": "",
					"infoFiltered": "(从 _MAX_ 总记录查询)",
					"search": "关键字检索：",
					'paginate': {  
						'first':      '第一页',  
						'last':       '最后一页',  
						'next':       '下一页',  
						'previous':   '上一页'  
					},
					"decimal": ".",
					"thousands": ","
				},
				"order": [3, "asc"],
				"columns": [
				{ "data": "commName", "type":"cn-string"},
				{ "data": "summary1" },
				{ "data": "summary2" },	
				{ "data": "up"},
				{ "data": "qty"},	    	            
				{ "data": "doe" },
				{ "data": "" }
				],			       		        
				"columnDefs": [	                       
				{
					"targets": columnselector,
					"visible": false,
				},
				{"render": function ( data, type, row ) {	                    	   
					var html = '';   		                

					var summarystr = '';	                
					if(row.summary1 != null){	                	
						summarystr += '<span>'+row.summary1+'</span>';		                	
					}	                
					if(row.summary2 != null){	                	
						summarystr += '<span>'+row.summary2+'</span>';		                	
					}	                
					if(row.summary3 != null){	                	
						summarystr += '<span>'+row.summary3+'</span>';		                	
					}	                
					if(row.summary4 != null){	                	
						summarystr += '<span>'+row.summary4+'</span>';		                	
					}

					var imgurl = '<img src="/normal/images/loadfail.jpg" width="120px" height="120px" alt="">';
					if(row.titlePic != null){
						imgurl = '<img src="'+row.titlePic+'" width="120px" height="120px" alt="">';
					}


					var buyhtml = '';
					if($('#currMID').val() !=row.mid){
						buyhtml = '<a target="_blank" href="/mall/item/'+row.listedNo+'.htm" class="btn-normal btn-buy">摘牌</a>';
					}

					html = '<div class="product" >'
					+ '<div class="product-bar">'
					+ '<span class="product-name">' + row.commName + '</span>'
					+ '<span class="product-price"><i>￥</i><strong>'+ Number(row.up).formatMoney()+'元/'+row.uom+'</strong></span>'
					+ '<span class="glyphicon glyphicon-menu-down"></span>'
					+ '</div>'
					+ '<div class="product-data">'
					+ '<ul>'
					+ '<li class="col-xs-12 col-sm-6">仓单编号：' + row.listedNo + '</li>'
					+ '<li class="col-xs-12 col-sm-6">数量： <span class="red">'+ row.rem+'/'+row.qty + '</span> ' + row.uom + '</li>'
					+ summarystr
					+ '<li class="col-xs-12 col-sm-6">交收仓库：' + row.storage + '</li>'
					+ '<li class="col-xs-12 col-sm-6">仓库地址：</li>'
					+ '<li class="col-xs-12 col-sm-6">交收类型： <span class="red radius">'+row.listedTypeName+'</span></li>'
					+ '</ul>'
					+ '<div class="product-describe">呆呆...</div>'
					+ '<div class="seller row">'
					+ '<div class="col-xs-12 col-sm-6 seller-id">编号：'+ row.mid +'</div>'
					+ '<div class="col-xs-12 col-sm-6 seller-date">有效期：' + row.doe + '</div>'
					+ '<div class="col-xs-12 col-sm-6 seller-name"><span>卖家</span>' + row.memName + '</div>'
					+ '<div class="col-xs-12 col-sm-6 ">'
					+ '<a href="/mall/item/'+row.listedNo+'.htm">'+imgurl+'</a>'
					+ '</div>'
					+ '</div>'
					+ '</div>'
					+ '</div>'
					
					return html;
				},
				"targets": columnlength,
				"orderable": false
			}
			],	                       	                      
			"dom": '<"custsort"<"sortbar">f>rt<"bottom"ip>',
			initComplete: function () {

				var $sortul = $('<ul></ul>').appendTo($('.sortbar'));
				$sortul.append('<li data-column="3" data-sort=""  class="active">按单价排序<span>&#8593;<span></li>');
				$sortul.append('<li data-column="4" data-sort="">按数量排序<span><span></li>');
				$sortul.append('<li data-column="5" data-sort="">按挂牌有效期排序<span><span></li>');


				$('.J_Brand').empty();

				$('.J_Brand').append('<li data-key=""  class="active">不限</li>');

				$('.J_Origin').empty()

				$('.J_Origin').append('<li data-key=""  class="active">不限</li>');


				this.api().columns([1]).every( function () {
					var column = this;                	 
					column.data().unique().sort().each( function ( d, j ) {
						if(d != undefined && d != null && d != ''){

							var vflag = d.indexOf(":");
							if(vflag >= 0){	                			
								var vbrand = d.substring(vflag+1);	  		                			
								$('.J_Brand').append('<li data-key="'+vbrand+'">'+vbrand+'</li>');
							}
						}	                		
					} );
				} );


				this.api().columns([2]).every( function () {
					var column = this;                	 
					column.data().unique().sort().each( function ( d, j ) {
						if(d != undefined && d != null && d != ''){

							var vflag = d.indexOf(":");
							if(vflag >= 0){	                			
								var vorigin = d.substring(vflag+1);	                			
								$('.J_Origin').append('<li data-key="'+vorigin+'">'+vorigin+'</li>');
							}
						}	                		
					} );
				} );

				$('.custfilter').show();
			}
		} );

$('.sortbar').on('click', 'li', function(){	
	$column = $(this).data('column'); 
	$sort = $(this).attr('data-sort'); 

	if($sort == ''){
		$sort = 'asc';
	}
	$dataset.order( [Number($column),$sort ] ).draw();

	$(this).siblings().removeClass('active');  
	$(this).siblings().attr('data-sort', '');
	$(this).siblings().find('span').html('');


	if($sort =='asc'){
		$(this).attr('data-sort','desc');
		$(this).find('span').html('&#8593;');
	}else{
		$(this).attr('data-sort','asc');
		$(this).find('span').html('&#8595;');
	}

	$(this).addClass('active');

});

}else{
	$dataset = $('#dataset').DataTable( {
		"ajax": {
			"url": "/mall/findtradelist.htm",
			"data": function ( d ) {	                
				d.querypara = querypara;
			}
		},	
		serverSide: true,
		ordering:false,
		searching: true,
		"processing" : true,
		"language": {
			"processing": "",
			"lengthMenu": "每页显示 _MENU_ 条",
			"zeroRecords": "无相关数据记录",
			"info": "显示从_START_ 到 _END_ 记录,共 _TOTAL_ 条记录", 
			"infoEmpty": "",
			"infoFiltered": "(从 _MAX_ 总记录查询)",
			"search": "关键字检索：",
			'paginate': {  
				'first':      '第一页',  
				'last':       '最后一页',  
				'next':       '下一页',  
				'previous':   '上一页'  
			},
			"decimal": ".",
			"thousands": ","
		},			        
		"columns": [
		{ "data": "commName", "type":"cn-string"},
		{ "data": "summary1" },
		{ "data": "summary2" },	
		{ "data": "up"},
		{ "data": "qty"},	    	            
		{ "data": "doe" },
		{ "data": "" }
		],			       		        
		"columnDefs": [	                       
		{
			"targets": columnselector,
			"visible": false
		},
		{"render": function ( data, type, row ) {	                    	   
			var html = '';   

			var summarystr = '';	                
			if(row.summary1 != null){	                	
				summarystr += '<li class="col-xs-12 col-sm-6><span>'+row.summary1+'</span></li>';		                	
			}	                
			if(row.summary2 != null){	                	
				summarystr += '<li class="col-xs-12 col-sm-6><span>'+row.summary2+'</span></li>';		                	
			}	                
			if(row.summary3 != null){	                	
				summarystr += '<li class="col-xs-12 col-sm-6><span>'+row.summary3+'</span></li>';		                	
			}	                
			if(row.summary4 != null){	                	
				summarystr += '<li class="col-xs-12 col-sm-6><span>'+row.summary4+'</span></li>';		                	
			}

			var imgurl = '<img src="/images/loadfail.jpg" width="120px" height="120px" alt="">';
			if(row.titlePic != null){
				imgurl = '<img src="'+row.titlePic+'" width="120px" height="120px" alt="">';
			}

			var buyhtml = '';
			if($('#currMID').val() !=row.mid){
				buyhtml = '<a  href="/mall/item/'+row.listedNo+'.htm" class="btn-normal btn-buy">摘牌</a>';
			}

			html = '<div class="product" >'
			+ '<div class="product-bar">'
			+ '<span class="product-name">' + row.commName + '</span>'
			+ '<span class="product-price"><i>￥</i> <strong>'+ Number(row.up).formatMoney()+'元/'+row.uom+'</strong></span>'
			+ '<span class="glyphicon glyphicon-menu-down"></span>'
			+ '</div>'
			+ '<div class="product-data">'
			+ '<ul>'
			+ '<li class="col-xs-12 col-sm-6">仓单编号：' + row.listedNo + '</li>'
			+ '<li class="col-xs-12 col-sm-6">数量： <span class="red">'+ row.rem+'/'+row.qty + '</span> ' + row.uom + '</li>'
			+ summarystr
			+ '<li class="col-xs-12 col-sm-6">交收仓库：' + row.storage + '</li>'
			+ '<li class="col-xs-12 col-sm-6">仓库地址：</li>'
			+ '<li class="col-xs-12 col-sm-6">交收类型： <span class="red radius">'+row.listedTypeName+'</span></li>'
			+ '</ul>'
			+ '<div class="seller row">'
			+ '<div class="col-xs-6 col-sm-6 seller-id">编号：'+ row.mid +'</div>'
			+ '<div class="col-xs-6 col-sm-6 seller-date">有效期：' + row.doe + '</div>'
			+ '<div class="col-xs-12 col-sm-6 seller-name"><span>卖家</span>' + row.memName + '</div>'
			+ '<div class="col-xs-12 col-sm-6 ">'
			+ '<a href="/mall/item/'+row.listedNo+'.htm">'+imgurl+'</a>'
			+ '</div>'
			+ '</div>'
			+ '</div>'
			+ '</div>'

			return html;
		},
		"targets": columnlength
	}
	],	                       	                      
	"dom": 'rt<"bottom"p>',
	initComplete: function () {

	}
} );								
}
}


$('.J_Brand').on('click', 'li', function(){	
	var key = $(this).data('key'); 
	$dataset.column( 1 ).search(key).draw();

	$(this).siblings().removeClass('active');  
	$(this).addClass('active');

});


$('.J_Origin').on('click', 'li', function(){	
	var key = $(this).data('key'); 
	$dataset.column( 2 ).search(key).draw();
	$(this).siblings().removeClass('active');  
	$(this).addClass('active');

});

$.fn.dataTable.ext.search.push(
	function( settings, data, dataIndex ) {
		var min = parseFloat( $('#price-min').val());
		var max = parseFloat( $('#price-max').val());
		        var price = parseFloat( data[3] ) || 0; // use data for the age column

		        if ( ( isNaN( min ) && isNaN( max ) ) ||
		        	( isNaN( min ) && price <= max ) ||
		        	( min <= price   && isNaN( max ) ) ||
		        	( min <= price   && price <= max ) )
		        {
		        	return true;
		        }
		        return false;
		    },
		    function( settings, data, dataIndex ) {
		    	var min = parseInt( $('#volume-min').val(), 10 );
		    	var max = parseInt( $('#volume-max').val(), 10 );
		        var vol = parseFloat( data[4] ) || 0; // use data for the age column

		        if ( ( isNaN( min ) && isNaN( max ) ) ||
		        	( isNaN( min ) && vol <= max ) ||
		        	( min <= vol   && isNaN( max ) ) ||
		        	( min <= vol   && vol <= max ) )
		        {
		        	return true;
		        }
		        return false;
		    }
		    );


$('.J_Price').on('click', 'li', function(){	
	var max = $(this).data('max');
	var min = $(this).data('min');

	if(max == undefined ||max == null){
		max == Number.MAX_VALUE;
	}

	if(min == undefined || min == null){
		min = 0;
	}
	$('#price-max').val(max);
	$('#price-min').val(min);
	$dataset.draw();
	$(this).siblings().removeClass('active');  
	$(this).addClass('active');

	if($(this).parent().siblings('.filtinterval').hasClass('intervalactive')){
		$(this).parent().siblings('.filtinterval').removeClass('intervalactive');
		$('#p-max').val('');
		$('#p-min').val('');

	}
});

$('.J_Volume').on('click', 'li', function(){	
	var max = $(this).data('max');
	var min = $(this).data('min');

	if(max == undefined ||max == null){
		max == Number.MAX_VALUE;
	}

	if(min == undefined || min == null){
		min = 0;
	}
	$('#volume-max').val(max);
	$('#volume-min').val(min);
	$dataset.draw();
	$(this).siblings().removeClass('active');  
	$(this).addClass('active');

	if($(this).parent().siblings('.filtinterval').hasClass('intervalactive')){
		$(this).parent().siblings('.filtinterval').removeClass('intervalactive');
		$('#v-max').val('');
		$('#v-min').val('');
	}
});


$('.filtinterval').on('click', '#priceBtn', function(){

	var max = $('#p-max').val();
	var min = $('#p-min').val();

	if(isNaN(max) || isNaN(min)){
		return false;
	}

	$('#price-max').val(max);
	$('#price-min').val(min);
	$dataset.draw();
	$(this).parent().addClass('intervalactive');
	$(this).parent().siblings("ul").find('li').removeClass('active');
});


$('.filtinterval').on('click', '#volumeBtn', function(){

	var max = $('#v-max').val();
	var min = $('#v-min').val();

	if(isNaN(max) || isNaN(min)){
		return false;
	}

	$('#volume-max').val(max);
	$('#volume-min').val(min);
	$dataset.draw();
	$(this).parent().addClass('intervalactive');
	$(this).parent().siblings("ul").find('li').removeClass('active');

});

var url = '/mall/findunlimitlist.htm';

var marketurl = '/mall/findallmarket.htm';

var loadMarket = function(url) {
	$.ajax({
		type : 'GET',
		url : url,
		cache : false,
		dataType : 'json',
		success : function(res) {
			if (res.succflag == 0) {
				$('[data-select]').SelectTags({
						initData : res.data,// 第一次初始化的时候加载select 数据，参考上面的json格式
						surl : "/mall/subbuyclass.htm", // 商品类别选择，添加子选择项 url
						removeUrl : "/mall/parbuyclass.htm",// 通过“X"删除时请求的 url
						callback : load
					// 回调函数，每次触发select change 事件和删除项，会返回一个code
				});
			} else {

			}
		}
	});
}

	function load(key, haveleaf, selflag, prevkey)  { // 通过回调函数传递的code 去查询数据，渲染模板
		
		if(selflag == false)
		{
			selcode = prevkey;
		}else{
			selcode = key;
		}
		
		initTable(selcode, haveleaf);

	}
	
	loadMarket(marketurl);
	
	initTable(null,true);

	var isAuth = parseInt($('#isAuth ').val(), 10);
	// isAuth 为0的时候表示需要修改密码，为1的时候不需要
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
			
			if(str.length < 6 || str.length > 16 ){
				$('.rsmsg').html("默认密码长度不正确");
				return false;
			}
			
			str = $.trim($("#newpassword").val());
			if (str.length == '') {

				$('.rsmsg').html("新密码为空");
				return false;
			}
			
			if(str.length < 6 || str.length > 16 ){
				$('.rsmsg').html("新密码长度不正确");
				return false;
			}
			
			str = $.trim($("#confpassword").val());
			if (str.length == '') {

				$('.rsmsg').html("确认密码为空");
				return false;
			}
			
			if(str.length < 6 || str.length > 16 ){
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

    //加载更多
    // var loadMore = true;
    // function load(){
    //   $('.loader').show();
    //   if(loadMore){
    //     loadMore = false;
    //     $.ajax({
    //       url : '',
    //       success : function(response){
    //         $scope.loadFlag = true;
    //         $('.loader').hide();
    //       }
    //     })
    //   }
    // }
    // $('.loadMore').click(load);

});
