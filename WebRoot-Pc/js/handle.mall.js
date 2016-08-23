$(function() {

	//滚动置顶
	$(document).ready(function() {
		$('.fixed-wrapper').stickUp();

	});
						
	var btime =  6*24*60*60*1000;
	$('#edol').val(new Date().format("yyyy-MM-dd"));
	$('#dol').val(new Date((new Date()).valueOf()-btime).format("yyyy-MM-dd"));
	
	var columns = [
		            { "data": "clsName", "type":"cn-string"},
		            { "data": "commCode"},
		            { "data": "commName", "type":"cn-string"},
		            { "data": "summary1", "type":"cn-string"},
		            { "data": "summary2", "type":"cn-string" },		        
		            { "data": "qty"},
		            { "data": "rem"},
		            { "data": "up"},
		            { "data": "storage", "type":"cn-string"},
		            { "data": "dol"},
		            { "data": "doe"},		            	          
		            { "data": "listedTypeName", "type":"cn-string"},	
		            { "data": "statusDesc", "type":"cn-string"},
		            { "data": "title", "type":"cn-string"},	            
		            { "data": "listedNo"},
		            { "data": null,"orderable": false}
		        ];
	
	var surl = '/mall/findsell.htm';
	
	var ajaxfn  = function(d){
		d.status = $('#status').val();
        d.code = $('#commcode').val();
        d.doe = $('#doe').val();
        d.dol = $('#dol').val();        
        d.edol = $('#edol').val();
	};
	
	var defaultOrder = [10, "desc"];
	
	var columndefs = [
	                  {"render": function ( data, type, row ) {	                    	   
	                      var html = '';                   	   
	                      html =  '<input type="button"  value="详情"  class="J_Detail cbtn" data-key="'+row.listedNo+ '" />';                   	   
	                      if(row.effRec == 1 && (row.status == 100 || row.status == 997)){	                    		   
	                         html += '<input type="button" value="下架" class="J_Cancel cbtn" data-key="'+row.listedNo+ '" />';
	                      }
	                      return html;
	                   },
	                   "targets": -1
	                 }
	                ];
	
	var rowfn  = function(row, data, index){
	    $('td:eq(5)', row).html(data.qty+data.uom);
	    $('td:eq(6)', row).html(data.rem+data.uom);
        $('td:eq(7)', row).html(Number(data.up).formatMoney());
	}
	
	var btnfn = function(){
		if($('#code').val()!=''&&$('#commcode').val() == ''){
		    UI.Dialog({type : 'tips',width : 320, title : '查询提示',content : '按商品查询时,必须选择具体的商品'}).show();						
			event.stopPropagation();
			return false;
		}
		
		if($('#dol').val()==''&&$('#edol').val() == ''){
		    UI.Dialog({type : 'tips',width : 320, title : '查询提示',content : '请必须输入挂牌开始日期或结束日期'}).show();						
			event.stopPropagation();
			return false;
		}
		
		//为空是填入默认值
		if($('#dol').val()==''){
			$('#dol').val($('#edol').val());
		}
		if($('#edol').val()==''){
			$('#edol').val($('#dol').val());
		}
		
		if($('#dol').val() > $('#edol').val()){
		    UI.Dialog({type : 'tips',width : 320, title : '查询提示',content : '挂牌开始日期大于结束日期'}).show();						
			event.stopPropagation();
			return false;
		}
		
		return true;
	}
	
	$dataset = dataTablesInit('#dataset', surl, ajaxfn, defaultOrder, btnfn, columns, columndefs, rowfn);
	
	
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
	
	
	//显示详情
	$('#dataset').on('click', '.J_Detail', function(e) {
		var key = $(this).attr('data-key');
		
		window.open("/mall/item/"+key+".htm");  

	});
	
	//下架响应
	$('#dataset').on('click', '.J_Cancel', function(e) {
		var key = $(this).attr('data-key');

		// 初始化确认提示框
		var dl = UI.Dialog({
			type : 'delete', // 提示框类型，这里是delete 代表删除提示框
			width : 480, // 设置提示框的宽度
			title : '下架确认', // 提示框标题的文字信息
			content : '您确定要下架挂牌号为' + key + '商品交易单吗？', // 提示框的内容文字信息
			href : todoCancel,
			param : key
		}).show();
		e.stopPropagation(); 

	});
	
	//下架处理
	function todoCancel(listedNo) {
		var formParam = "listedNo=" + listedNo;

		$.ajax({
			type : 'post',
			url : '/sell/listedcancel.htm',
			data : formParam,
			cache : false,
			dataType : 'json',
			success : function(data) {
				console.log(data.data);
				if(data.succflag == 0){
					var source='';
					if(data.data.listedType == 'M'){
						
						source = Handlebars.compile($("#backTemplate").html()); 
					}else{
						source = Handlebars.compile($("#backTemplatecd").html());
					}
					Handlebars.registerHelper("money", function(value) {
						return value.formatMoney();
					});
					var htmlText = source(data.data);
					
					var rsdl = UI.Dialog({
						type : 'tips',
						width : 450,
						title : '交易结果',
						content : htmlText,
						}).show();
				}else{
					var rsdl = UI.Dialog({
					type : 'tips',
					width : 320,
					title : '交易结果',
					content : data.msg,
					}).show();
				}
				// 需要刷新本页数据
				extResetColume($dataset);					
				$dataset.ajax.reload(function(data){			
					extReloadColumn($dataset);

			     });
			}
		});
	}

	// 查询市场信息
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
						removeUrl : "/mall/parsellclass.htm",// 通过“X"删除时请求的																// url
						callback : obtainkey
					});
				}else{
					$('[data-select]').html(res.msg);
				}
			}
		});
	}

	//参数回调
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
	
	$('#doe, #dol, #edol').on("blur", function(event){   
		$('.datepicker-container').hide();
	});
		
	var marketurl = "/mall/findallmarket.htm";
	
	loadMarket(marketurl);
});