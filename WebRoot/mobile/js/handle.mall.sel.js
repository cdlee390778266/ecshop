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
				{ "data": "commName"},
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
					var up_summary = [];                
					if(row.summary1 != null){              	
						up_summary = [];
					    up_summary = row.summary1.split(':');	                	
						summarystr += '<li class="col-xs-12 col-sm-6">'+up_summary[0]+"："+up_summary[1]+'</li>';		                	
					}	                
					if(row.summary2 != null){	                	
					    up_summary = [];
					    up_summary = row.summary2.split(':');	                	
						summarystr += '<li class="col-xs-12 col-sm-6">'+up_summary[0]+"："+up_summary[1]+'</li>'
					}	                
					if(row.summary3 != null){	                	
						up_summary = [];
					    up_summary = row.summary3.split(':');	                	
						summarystr += '<li class="col-xs-12 col-sm-6">'+up_summary[0]+"："+up_summary[1]+'</li>'		                	
					}	                
					if(row.summary4 != null){	                	
						up_summary = [];
					    up_summary = row.summary4.split(':');	                	
						summarystr += '<li class="col-xs-12 col-sm-6">'+up_summary[0]+"："+up_summary[1]+'</li>'		                	
					}

					var imgurl = '<img src="/images/loadfail.jpg" width="120px" height="120px" alt="">';
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
					+ '<span class="product-price"><i>￥</i> <strong>'+ Number(row.up).formatMoney()+'元/'+row.uom+'</strong></span>'
					+ '<span class="glyphicon glyphicon-menu-down"></span>'
					+ '</div>'
					+ '<div class="product-data">'
					+ '<ul>'
					+ '<li class="col-xs-12 col-sm-6">仓单编号：' + row.listedNo + '</li>'
					+ '<li class="col-xs-12 col-sm-6">数量： <span class="red">'+ row.rem+'/'+row.qty + '</span> ' + row.uom + '</li>'
					+ summarystr
					+ '<li class="col-xs-12 col-sm-6">交收仓库：' + row.storage + '</li>'
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
				"targets": columnlength,
				"orderable": false
			}
			],	                       	                      
			"dom": '<"custsort"<"sortbar">>rt<"bottom"p>',
			initComplete: function () {

				var $sortul = $('<ul class="row"></ul>').appendTo($('.sortbar'));
				$('.sortbar').addClass('container-fluid');
				$sortul.append('<li data-column="3" data-sort=""  class="active col-xs-4">单价<span>&#8593;<span></li>');
				$sortul.append('<li data-column="4" data-sort="" class="col-xs-4">数量<span><span></li>');
				$sortul.append('<li data-column="5" data-sort="" class="col-xs-4">挂牌有效期<span><span></li>');


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
		searching: false,
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
		{ "data": "commName"},
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
			var up_summary = [];	                
			if(row.summary1 != null){
				up_summary = [];
			    up_summary = row.summary1.split(':');	                	
				summarystr += '<li class="col-xs-12 col-sm-6">'+up_summary[0]+"："+up_summary[1]+'</li>';		                	
			}	                
			if(row.summary2 != null){	                	
				up_summary = [];
			    up_summary = row.summary2.split(':');	                	
				summarystr += '<li class="col-xs-12 col-sm-6">'+up_summary[0]+"："+up_summary[1]+'</li>';	                	
			}	                
			if(row.summary3 != null){	                	
				up_summary = [];
			    up_summary = row.summary3.split(':');	                	
				summarystr += '<li class="col-xs-12 col-sm-6">'+up_summary[0]+"："+up_summary[1]+'</li>';		                	
			}	                
			if(row.summary4 != null){	                	
				up_summary = [];
			    up_summary = row.summary4.split(':');	                	
				summarystr += '<li class="col-xs-12 col-sm-6">'+up_summary[0]+"："+up_summary[1]+'</li>';		                	
			}

			var imgurl = '<img src="/images/loadfail.jpg" width="120px" height="120px" alt="">';
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




	//default load data for parameter..
	
	var dvcode = $('#J_TabBar').find('.selected a').data('role');
	var dvflag = $('#J_TabBar').find('.selected a').data('flag');
	
	initTable(dvcode, dvflag == '3'?false:true);
	
	$('#J_TabBar').on('click', 'li', function() {			
		if(!$(this).hasClass('morecell')){

			$(this).hasClass('selected') ? $(this).siblings('li')
			.removeClass('selected') : $(this).siblings('li')
			.removeClass('selected');
			$(this).addClass('selected');

			$('#code').val($(this).find('a').attr("data-role"));

			var vcode = $(this).find('a').data("role");
			var vflag = $(this).find('a').data("flag");

			initTable(vcode, vflag == '3'?false:true);
		}else{
			$(this).siblings().show();
			$(this).hide();
		}

	});


	//商品展示
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

});
