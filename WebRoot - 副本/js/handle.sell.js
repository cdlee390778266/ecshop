$(function() {

	$(document).ready(function() {
		$('.fixed-wrapper').stickUp();
	});
	
	var columns =  [
		            { "data": "clsName", "type":"cn-string" },
		            { "data": "commCode" },
		            { "data": "commName", "type":"cn-string" },
		            { "data": "summary1", "type":"cn-string" },
		            { "data": "summary2", "type":"cn-string" },	
		            { "data": "listedNo" },
		            { "data": "qty"},
		            { "data": "up"},
		            { "data": "dol" },
		            { "data": "doe" },		            	          
		            { "data": "listedTypeName", "type":"cn-string" },	
		            { "data": "statusDesc", "type":"cn-string" },
		            { "data": "title", "type":"cn-string" },
		            { "data": null,"orderable": false }
		        ];
	
	var surl = '/sell/findsells.htm';
	
	var ajaxfn  = function(d){	
		var status = $('#J_TabBar .selected').find('a').data("role");
		d.status = status;
        d.code = $('#commcode').val();
	};
	
	var defaultOrder = [9, "desc"];
	
	var columndefs = [
	  	            {"render": function ( data, type, row ) {	                    	   
		                var html = '';    
		                
		                var currentpid = $('#pid').val();
		                if(row.effRec == 1){
		                	if(row.status == 1){	//待支付状态时0000操作员可以撤消                		
		                		html += '<input type="button" value="支付" class="J_GotoPay cbtn"  data-key="'+row.listedNo+ '" />';	                		
		                		if(currentpid === '0000'){
		                			html += '<input type="button" value="撤消" class="J_Cancel cbtn"  data-key="'+row.listedNo+ '" />';
		                		}
		                	}else if(row.status == -1 || row.status == -2){//审核未通过,撤消后的单子也可以修改
		                		
		                		html += '<input type="button" value="修改" class="J_Modify cbtn"  data-key="'+row.listedNo+ '" />';
		                		
		                		if(row.status == -2){
		                			html += '<input type="button" value="删除" class="J_Del cbtn"  data-key="'+row.listedNo+ '" />';
		                		}
		                		
		                	}else{
		                		
		                		html += '<input type="button" value="审核" class="J_Audit cbtn"  data-key="'+row.listedNo+ '" />';
		                		
		                		html += '<input type="button" value="修改" class="J_Modify cbtn"  data-key="'+row.listedNo+ '" />';
		                		
		                		html += '<input type="button" value="撤消" class="J_Cancel cbtn"  data-key="'+row.listedNo+ '" />';
		                	}
		                }
		                return html;
		              },
		              "targets": -1
		            }
		         ];
	
	var rowfn  = function(row, data, index){
		$('td:eq(6)', row).html(data.qty+data.uom);	        	 
   	    $('td:eq(7)', row).html(Number(data.up).formatMoney());
	}
	
	var btnfn = function(){
		if($('#code').val()!=''&&$('#commcode').val() == ''){
		    UI.Dialog({type : 'tips',width : 320, title : '查询提示',content : '按商品查询时,必须选择具体的商品'}).show();						
			event.stopPropagation();
			return false;
		}
		return true;
	}
	
	
	$dataset = dataTablesInit('#dataset', surl, ajaxfn, defaultOrder, btnfn, columns, columndefs, rowfn);
	
	//选项卡选择事件
	$('#J_TabBar').on('click', 'li', function() {
        $(this).hasClass('selected') ? $(this).siblings('li')
						.removeClass('selected') : $(this).siblings('li')
						.removeClass('selected');
        $(this).addClass('selected');
        extResetColume($dataset);
        $dataset.ajax.reload(function(data){
        	extReloadColumn($dataset);
        });  
    });
	
	var hiddencolumn = [];
	//查询复位回调
	function extResetColume(datatable){
		hiddencolumn = [];
		datatable.columns().every( function () {
	  	    var column = this;  
	  	    
		  	 if(column.visible() == false){ 
		  		 hiddencolumn.push(column.index());
		  		 column.visible(true);
		  	 }
	  	  
	  	    var select =  $(column.header()).find('select');
	  	    $(select).val('');			  	       
	  	    column.search('', true, false ).draw();
	  	});
	}
	
	//重新载入数据标题选择重新生成回调
	function extReloadColumn(datatable){
		
		datatable.columns(hiddencolumn).every( function () {
	  	    var column = this;    		  
	  	    var colindex = column.index();
		  	 if(column.visible() == true){ 
		  		 column.visible(false); 
		  		$('.ColVis_collection').find('input[type=checkbox]').eq(colindex).attr('checked',false);
		  	 }	  	  
	  	});
		
		datatable.columns().every( function () {
  	        var column = this;                
  	        var select =  $(column.header()).find('select');
  	        $(select).find("option[value!='']").remove(); 
  	        column.data().unique().sort().each( function ( d, j ) {
  	            if(d != undefined && d != null && d != '')
  	                select.append( '<option value="'+d+'">'+d+'</option>' )
  	            });
  	        });
		datatable.columns.adjust();
	}
	
	
	//支付响应
	$('#dataset').on('click', '.J_GotoPay', function(e) {
		var key = $(this).attr('data-key');
		
		window.location.href = ("/sell/handle/P/"+key+".htm");  
	});
	
	//审核响应
	$('#dataset').on('click', '.J_Audit', function(e) {
		var key = $(this).attr('data-key');
		
		window.location.href = ("/sell/handle/A/"+key+".htm");  
	});
	
	//修改响应
	$('#dataset').on('click', '.J_Modify', function(e) {
		var key = $(this).attr('data-key');
		
		window.location.href = ("/sell/edit/"+key+".htm");  
	});
	

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
						surl : "/mall/subsellclass.htm", // 商品类别选择，添加子选择项 url
						removeUrl : "/mall/parsellclass.htm",// 通过“X"删除时请求的
						callback : obtainkey
					});
				}else{
					$('[data-select]').html(res.msg);
				}
			}
		});
	}

	function obtainkey(key, haveleaf, selflag, prevkey)  { // 通过回调函数传递的code 去查询数据，渲染模板		
		if(selflag == false)
		{
			$('#code').val(prevkey);
			$('#commcode').val('');
		}else{
			if(haveleaf){
				$('#commcode').val('');
				
			}else{
				$('#commcode').val(key);
			}
			$('#code').val(key);
		}
	}

	var marketurl = "/mall/findallmarket.htm";
	
	loadMarket(marketurl);
	
	
	
	// 确认回调函数
	function gotoCancel(orderno) {
		var formParam = "listedNo=" + orderno;

		$.ajax({
			type : 'post',
			url : '/sell/applycancel.htm',
			data : formParam,
			cache : false,
			dataType : 'json',
			success : function(data) {
				var rsdl = UI.Dialog({
					type : 'tips',
					width : 320,
					title : '交易结果',
					content : data.msg,
				}).show();
				extResetColume($dataset);					
				$dataset.ajax.reload(function(data){			
					extReloadColumn($dataset);

			     });
			}
		});
	}

	// 确认弹出层
	$('#dataset').on('click', '.J_Cancel',  function(e) {

		var sorderno = $(this).data("key");
		// 初始化确认提示框

		var dl = UI.Dialog({
			type : 'delete', // 提示框类型，这里是delete 代表删除提示框
			width : 480, // 设置提示框的宽度
			title : '交易确认', // 提示框标题的文字信息
			content : '您确定要撤消' + sorderno + '的订单吗？', // 提示框的内容文字信息
			href : gotoCancel,
			param: sorderno
		}).show();
		e.stopPropagation();
	});
	
	// 确认回调函数
	function gotoDel(orderno) {
		var formParam = "listedNo=" + orderno;

		$.ajax({
			type : 'post',
			url : '/sell/applydel.htm',
			data : formParam,
			cache : false,
			dataType : 'json',
			success : function(data) {
				var rsdl = UI.Dialog({
					type : 'tips',
					width : 320,
					title : '交易结果',
					content : data.msg,
				}).show();
				extResetColume($dataset);					
				$dataset.ajax.reload(function(data){			
					extReloadColumn($dataset);

			     });
			}
		});
	}

	// 确认弹出层
	$('#dataset').on('click', '.J_Del',  function(e) {

		var sorderno = $(this).data("key");
		// 初始化确认提示框

		var dl = UI.Dialog({
			type : 'delete', // 提示框类型，这里是delete 代表删除提示框
			width : 480, // 设置提示框的宽度
			title : '交易确认', // 提示框标题的文字信息
			content : '您确定要删除' + sorderno + '的订单吗？', // 提示框的内容文字信息
			href : gotoDel,
			param: sorderno
		}).show();
		e.stopPropagation();
	});

});