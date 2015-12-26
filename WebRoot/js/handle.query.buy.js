$(function() {
	$(document).ready(function() {
		$('.fixed-wrapper').stickUp();
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
						surl : "/mall/subbuyclass.htm", // 商品类别选择，添加子选择项 url
						removeUrl : "/mall/parbuyclass.htm",// 通过“X"删除时请求的
																// url
						callback : obtainkey
					});
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
	
	
	var difftime =  6*24*60*60*1000;	//七天
	$('#estrikeDate').val(new Date().format("yyyy-MM-dd"));
	$('#strikeDate').val(new Date((new Date()).valueOf()-difftime).format("yyyy-MM-dd"));
	
	$('#strikeDate， #estrikeDate').on("blur", function(event){   
		$('.datepicker-container').hide();
	});
	
	var marketurl = "/mall/findallmarket.htm";
	
	loadMarket(marketurl);
	
	var columns = [
		            { "data": "clsName", "type":"cn-string" },
		            { "data": "commCode" },	         
		            { "data": "commName", "type":"cn-string" },
		            { "data": "smID" },	         
		            { "data": "smemName", "type":"cn-string" },
		            { "data": "summary1", "type":"cn-string" },
		            { "data": "summary2", "type":"cn-string" },		        
		            { "data": "vol"},
		            { "data": "up"},
		            { "data": "contAmt"},
		            { "data": "storage", "type":"cn-string" },
		            { "data": "strikeDate" },		            	          
		            { "data": "listedTypeName", "type":"cn-string" },	
		            { "data": "statusDesc", "type":"cn-string" },
		            { "data": "strikeNo" },	            
		            { "data": "contNo" }
		        ];
	
	var surl = '/query/finddevliery.htm';
	
	var ajaxfn  = function(d){
		d.type = "B";
        d.code = $('#commcode').val();
        d.estrikeDate = $('#estrikeDate').val();
        d.strikeDate = $('#strikeDate').val();
	};
	
	var defaultOrder = [11, "asc"];
	
	var rowfn  = function(row, data, index){
		$('td:eq(7)', row).html(data.vol+data.uom);
		$('td:eq(8)', row).html(Number(data.up).formatMoney());
    	$('td:eq(9)', row).html(Number(data.contAmt).formatMoney());
	}
	
	var btnfn = function(){
		if($('#code').val()!=''&&$('#commcode').val() == ''){
		    UI.Dialog({type : 'tips',width : 320, title : '查询提示',content : '按商品查询时,必须选择具体的商品'}).show();						
			event.stopPropagation();
			return false;
		}
		
		if($('#strikeDate').val()==''&&$('#estrikeDate').val() == ''){
		    UI.Dialog({type : 'tips',width : 320, title : '查询提示',content : '请必须输入成交开始日期或结束日期'}).show();						
			event.stopPropagation();
			return false;
		}
		
		//为空是填入默认值
		if($('#strikeDate').val()==''){
			$('#strikeDate').val($('#estrikeDate').val());
		}
		if($('#estrikeDate').val()==''){
			$('#estrikeDate').val($('#strikeDate').val());
		}
		
		if($('#strikeDate').val() > $('#estrikeDate').val()){
		    UI.Dialog({type : 'tips',width : 320, title : '查询提示',content : '成交开始日期大于结束日期'}).show();						
			event.stopPropagation();
			return false;
		}
		
		return true;
	}
	
	dataTablesInit('#dataset', surl, ajaxfn, defaultOrder, btnfn, columns, null, rowfn);
	
});
