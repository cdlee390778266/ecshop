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
		            "search": "",
		            "searchPlaceholder" : '在结果中检索',
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
		                var sumStr = [];	                
		                if(row.summary1 != null){
		                    sumStr = row.summary1.split(':');       	
		                    summarystr += '<span><strong>'+sumStr[0]+ ':</strong>' + sumStr[1] +'</span>';		                	 }	                
		                if(row.summary2 != null){	                	
		                	 sumStr = row.summary2.split(':');       	
		                    summarystr += '<span><strong>'+sumStr[0]+ ':</strong>' + sumStr[1] +'</span>';	                	}	                
		                if(row.summary3 != null){	                	
		                	 sumStr = row.summary3.split(':');       	
		                    summarystr += '<span><strong>'+sumStr[0]+ ':</strong>' + sumStr[1] +'</span>';	                	 }	                
		                if(row.summary4 != null){	                	
		                	 sumStr = row.summary4.split(':');       	
		                    summarystr += '<span><strong>'+sumStr[0]+ ':</strong>' + sumStr[1] +'</span>';	                	}
		                
		                var imgurl = '<img src="/normal/images/loadfail.jpg" width="120px" height="120px" alt="">';
		                if(row.titlePic != null){
		                	imgurl = '<img src="'+row.titlePic+'" width="120px" height="120px" alt="">';
		                }
		                
		                var buyhtml = '';
		                if($('#currMID').val() !=row.mid){
		                	buyhtml = '<a target="_blank" href="/mall/item/'+row.listedNo+'.htm" >摘牌</a>';
		                }else{
		                	buyhtml = '<a  href="javascript:void(0);" class="disabled" >摘牌</a>';
		                }
		                
		                html = '<div class="sell-commodity">'
		                	 +'	<div class="c-caption">'
		                	 +'		<div class="cap-img"><a target="_blank" href="/mall/item/'+row.listedNo+'.htm">'+imgurl+'</a></div>'
		                	 +'	</div>'	                  
		                	 +'	<div class="c-summary">'               
		                	 +'		<div class="sum-commname"><span class="sum-name">'+row.commName+'</span><span><strong>'+ Number(row.up).formatMoney()+'</strong>元/'+row.uom +'</span><span ><i>数量:</i>'+row.rem+'/'+row.qty+row.uom+'</span></div>'
		                	 +'		<div class="sum-detail">'
		                	 +'		<div>'	 
		                	 +'			<span><strong>交收仓库:</strong><span class="ellipsis" title="' + row.storage +'">'+row.storage+'</span></span>'	
		                	 +'			<span><strong>交收类型:</strong>'+row.listedTypeName+'</span>'	
		                	 +'<span><strong>卖家:</strong><span class="ellipsis" title="' + row.memName + '">'+row.memName+'</span>'
		                	 +'		</div>'
		                	 +'		<div>'
		                	 +'<span><strong>会员编号:</strong><span class="ellipsis" title="'+ row.mid +'">'+row.mid+'</span></span>'
		                	 +			summarystr
		                	 +'		</div>'
		                	  
		                	 +'		</div>'

		                	 +' </div>'
				             +' <div class="c-supply">'
				             +'		<div class="sup-doe">有效期:'+row.doe+'</div>'	
				             +'		<div class="sup-sellinfo">'+buyhtml+'</div>'	
				             +' </div>'
				             +'</div>'                 
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
			                 var sumStr = [];	                
		                if(row.summary1 != null){
		                    sumStr = row.summary1.split(':');       	
		                    summarystr += '<span><strong>'+sumStr[0]+ ':</strong>' + sumStr[1] +'</span>';		                	 }	                
		                if(row.summary2 != null){	                	
		                	 sumStr = row.summary2.split(':');       	
		                    summarystr += '<span><strong>'+sumStr[0]+ ':</strong>' + sumStr[1] +'</span>';	                	}	                
		                if(row.summary3 != null){	                	
		                	 sumStr = row.summary3.split(':');       	
		                    summarystr += '<span><strong>'+sumStr[0]+ ':</strong>' + sumStr[1] +'</span>';	                	 }	                
		                if(row.summary4 != null){	                	
		                	 sumStr = row.summary4.split(':');       	
		                    summarystr += '<span><strong>'+sumStr[0]+ ':</strong>' + sumStr[1] +'</span>';	                	}
			                
			                var imgurl = '<img src="/normal/images/loadfail.jpg" width="120px" height="120px" alt="">';
			                if(row.titlePic != null){
			                	imgurl = '<img src="'+row.titlePic+'" width="120px" height="120px" alt="">';
			                }
			                
			                var buyhtml = '';
			                 if($('#currMID').val() !=row.mid){
		                	    buyhtml = '<a target="_blank" href="/mall/item/'+row.listedNo+'.htm" >摘牌</a>';
		                       }else{
		                	buyhtml = '<a  href="javascript:void(0);" class="disabled" >摘牌</a>';
		                    }
			                
			                html = '<div class="sell-commodity">'
		                	 +'	<div class="c-caption">'
		                	 +'		<div class="cap-img"><a target="_blank" href="/mall/item/'+row.listedNo+'.htm">'+imgurl+'</a></div>'
		                	 +'	</div>'	                  
		                	 +'	<div class="c-summary">'               
		                	 +'		<div class="sum-commname"><span class="sum-name">'+row.commName+'</span><span><strong>'+ Number(row.up).formatMoney()+'</strong>元/'+row.uom +'</span><span ><i>数量:</i>'+row.rem+'/'+row.qty+row.uom+'</span></div>'
		                	 +'		<div class="sum-detail">'
		                	 +'		<div>'	 
		                	 +'			<span><strong>交收仓库:</strong><span class="ellipsis" title="'+ row.storage +'">'+row.storage+'</span></span>'	
		                	 +'			<span><strong>交收类型:</strong>'+row.listedTypeName+'</span>'	
		                	 +'<span><strong>卖家:</strong><span class="ellipsis" title="'+ row.memName +'">'+row.memName+'</span></span>'
		                	 +'		</div>'
		                	 +'		<div>'
		                	 +'<span><strong>编号:</strong>'+row.mid+'</span>'
		                	 +			summarystr
		                	 +'		</div>'
		                	  
		                	 +'		</div>'

		                	 +' </div>'
				             +' <div class="c-supply">'
				             +'		<div class="sup-doe">有效期:'+row.doe+'</div>'	
				             +'		<div class="sup-sellinfo">'+buyhtml+'</div>'	
				             +' </div>'
				             +'</div>'              
			                return html;
			              },
			              "targets": columnlength
			            }
			         ],	                       	                      
			        "dom": 'rt<"bottom"ip>',
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

});
