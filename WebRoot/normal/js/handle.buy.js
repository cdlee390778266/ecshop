$(function() {
	$(document).ready(function() {
		$('.fixed-wrapper').stickUp();
	});
	
	var columns =  [
		            { "data": "clsName","type":"cn-string" },
		            { "data": "commCode" },
		            { "data": "commName", "type":"cn-string" },
		            { "data": "summary1", "type":"cn-string" },
		            { "data": "summary2", "type":"cn-string" },	
		            { "data": "delistNo" },
		            { "data": "vol"},
		            { "data": "up"},
		            { "data": "dod" },
		            { "data": "doe" },
		            { "data": "listedTypeName", "type":"cn-string" },	
		            { "data": "statusDesc", "type":"cn-string" },
		            { "data": null,"orderable": false }
		        ];
	
	var surl = '/buy/findbuys.htm';
	
	var ajaxfn  = function(d){	
		var status = $('#J_TabBar .selected').find('a').data("role");
		d.status = status;
        d.code = $('#commcode').val();
	};
	
	var defaultOrder = [8, "desc"];
	
	var columndefs = [
	  	            {"render": function ( data, type, row ) {	                    	   
		                var html = '';       
		                
		                var currentpid = $('#pid').val();
		                
		                if(row.effRec == 1){
		                	if(row.status == 1){//待支付
		                		html += '<input type="button" value="支付" class="J_GotoPay cbtn" data-key="' + row.delistNo + '" data-type="' + row.listedType + '" />';
		                		
		                		if(currentpid === '0000'){
		                			html += '<input type="button" value="撤消" class="J_Cancel cbtn"  data-key="' + row.delistNo + '" data-type="' + row.listedType + '" />';
		                		}		                	
		                	}else if(row.status == -1 || row.status == -2 || row.status == -4){//审核未通过
		                		//仓单无修改无删除
		                		if(row.listedType == 'M'){
		                			html += '<input type="button" value="修改" class="J_Modify cbtn" data-key="' + row.delistNo + '" data-type="' + row.listedType + '" />';
			                		
			                		if(row.status == -2 || row.status == -4){
			                			html += '<input type="button" value="删除" class="J_Del cbtn"  data-key="' + row.delistNo + '" data-type="' + row.listedType + '" />';
			                		}
		                		}
		                	}else if(row.status != -3){//待审核
		                		html += '<input type="button" value="审核" class="J_Audit cbtn" data-key="' + row.delistNo + '" data-type="' + row.listedType + '" />';
		                		
		                		//仓单无修改
		                		if(row.listedType == 'M'){
		                			html += '<input type="button" value="修改" class="J_Modify cbtn" data-key="' + row.delistNo + '" data-type="' + row.listedType + '" />';
		                		}
		                		
		                		html += '<input type="button" value="撤消" class="J_Cancel cbtn"  data-key="' + row.delistNo + '" data-type="' + row.listedType + '" />';
		                	}
		                }
		                return html;
		              },
		              "targets": -1
		            }
		         ];
	
	var rowfn  = function(row, data, index){
		$('td:eq(6)', row).html(data.vol+data.uom);
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
	$('#type').change(function(event) {
		if($(this).val() != '#type5'){
			$($(this).val()).click();
		}else{
			location.href = '/delivery/buylist.htm';
		}
	});
	
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
		datatable.columns().every( function () {
  	        var column = this;                
  	        var select =  $(column.header()).find('select');
  	        $(select).find("option[value!='']").remove(); 
  	        column.data().unique().sort().each( function ( d, j ) {
  	            if(d != undefined && d != null && d != '')
  	                select.append( '<option value="'+d+'">'+d+'</option>' )
  	            });
  	        });		
		
		datatable.columns(hiddencolumn).every( function () {
	  	    var column = this;    		  
	  	    var colindex = column.index();
		  	 if(column.visible() == true){ 
		  		 column.visible(false); 
		  		$('.ColVis_collection').find('input[type=checkbox]').eq(colindex).attr('checked',false);
		  	 }	  	  
	  	});
		
		datatable.columns.adjust();
	}
	
	
	//支付响应
	$('#dataset').on('click', '.J_GotoPay', function(e) {
		var key = $(this).attr('data-key');
		var type = $(this).attr('data-type');
		
		if(type == "M"){   //保证金
			window.location.href = ("/buy/handle/P/" + key + ".htm");
		}else if(type == "W"){   //仓单
			window.location.href = ("/buy/handle/wr/P/" + key + ".htm");
		}
	});
	
	//审核响应
	$('#dataset').on('click', '.J_Audit', function(e) {
		var key = $(this).attr('data-key');
		var type = $(this).attr('data-type');
		
		if(type == "M"){   //保证金
			window.location.href = ("/buy/handle/A/" + key + ".htm");
		}else if(type == "W"){   //仓单
			window.location.href = ("/buy/handle/wr/A/" + key + ".htm");
		}
	});
	
	//修改响应
	$('#dataset').on('click', '.J_Modify', function(e) {
		var key = $(this).attr('data-key');
		window.location.href = ("/buy/edit/" + key + ".htm");  
	});
	
	var marketurl = "/mall/findallmarket.htm";


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
	
	loadMarket(marketurl);
	
	
	// 确认回调函数
	function gotoCancel(order) {
		var formParam = "delistNo=" + order.orderno;
		var url = "";
		
		if(order.type == "M"){
			url = "/buy/applycancel.htm";
		}else if(order.type == "W"){
			url = "/buy/wr/applycancel.htm";
		}

		$.ajax({
			type : 'post',
			url : url,
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
		var borderno = $(this).data("key");
		var type = $(this).data("type");
		// 初始化确认提示框

		var dl = UI.Dialog({
			type : 'delete', // 提示框类型，这里是delete 代表删除提示框
			width : 480, // 设置提示框的宽度
			title : '交易确认', // 提示框标题的文字信息
			content : '您确定要撤消' + borderno + '的订单吗？', // 提示框的内容文字信息
			href : gotoCancel,
			param: {"orderno": borderno, "type": type}
		}).show();
		e.stopPropagation();
	});
	
	
	// 确认回调函数
	function gotoDel(orderno) {
		var formParam = "delistNo=" + orderno;

		$.ajax({
			type : 'post',
			url : '/buy/applydel.htm',
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

		var borderno = $(this).data("key");
		// 初始化确认提示框

		var dl = UI.Dialog({
			type : 'delete', // 提示框类型，这里是delete 代表删除提示框
			width : 480, // 设置提示框的宽度
			title : '交易确认', // 提示框标题的文字信息
			content : '您确定要删除' + borderno + '的订单吗？', // 提示框的内容文字信息
			href : gotoDel,
			param: borderno
		}).show();
		e.stopPropagation();
	});
});