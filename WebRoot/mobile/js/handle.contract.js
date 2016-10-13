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
						surl : "/mall/subclass.htm", // 商品类别选择，添加子选择项 url
						removeUrl : "/mall/parclass.htm",// 通过“X"删除时请求的 url
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
	
	var btime =  6*24*60*60*1000;
	$('#econtTime').val(new Date().format("yyyy-MM-dd"));
	$('#contTime').val(new Date((new Date()).valueOf()-btime).format("yyyy-MM-dd"));
	
	$('#contTime, #econtTime').on("blur", function(event){   
		$('.datepicker-container').hide();
	});

	var marketurl = "/mall/findallmarket.htm";
	
	var columns =  [
		            { "data": "clsName", "type":"cn-string" },
		            { "data": "commName", "type":"cn-string" },
		            { "data": "contractName", "type":"cn-string" },
		            { "data": "bmID" },		        
		            { "data": "bmemName", "type":"cn-string"},
		            { "data": "smID"},
		            { "data": "smemName", "type":"cn-string"},	            
		            { "data": "contTime" },
		            { "data": "vol" },		            	          
		            { "data": "up" },	
		            { "data": "contAmt"},
		            { "data": "statusDesc", "type":"cn-string" },
		            { "data": "contNo" },
		            { "data": "strikeNo" },            
		            { "data": null,"orderable": false}
		        ];
	
	var surl = '/contract/findcontract.htm';
	
	var ajaxfn  = function(d){	
        d.code = $("#commcode").val(),
		d.econtTime = $("#econtTime").val(),
		d.contTime = $("#contTime").val(),
		d.strikeNo = $("#strikeNo").val(),
		d.contNo = $("#contNo").val(),
		d.status = $("#status").val(),
		d.bsType = $("#bsType").val();
	};
	
	var defaultOrder = [7, "desc"];
	
	var columndefs = [                             
	  	            {"render": function ( data, type, row ) {	                    	   
		                var html = '';                   	   
		                html =  '<input type="button" value="查看" class="J_Show cbtn" data-key="'+row.contNo+ '" />'                 	   	                	                    		   
		                	+'<input type="button" value="下载" class="J_Download cbtn"  data-key="'+row.contNo+ '" />';
		                
		                return html;
		              },
		              "targets": -1
		            }
		         ];
	
	var rowfn  = function(row, data, index){
		$('td:eq(8)', row).html(data.vol+data.uom);
   	 	$('td:eq(9)', row).html(Number(data.up).formatMoney());
   	 	$('td:eq(10)', row).html(Number(data.contAmt).formatMoney());
	}
	
	var btnfn = function(){
		if($('#code').val()!=''&&$('#commcode').val() == ''){
		    UI.Dialog({type : 'tips',width : '80%', title : '查询提示',content : '按商品查询时,必须选择具体的商品'}).show();						
			event.stopPropagation();
			return false;
		}
		
		
		if($('#contTime').val()==''&&$('#econtTime').val() == ''){
		    UI.Dialog({type : 'tips',width : '80%', title : '查询提示',content : '请必须输入订立开始日期或结束日期'}).show();						
			event.stopPropagation();
			return false;
		}
		
		//为空是填入默认值
		if($('#contTime').val()==''){
			$('#contTime').val($('#econtTime').val());
		}
		if($('#econtTime').val()==''){
			$('#econtTime').val($('#contTime').val());
		}
		
		if($('#contTime').val() > $('#econtTime').val()){
		    UI.Dialog({type : 'tips',width : '80%', title : '查询提示',content : '订立开始日期大于结束日期'}).show();						
			event.stopPropagation();
			return false;
		}
		
		
		
		return true;
	}
	
	dataTablesInit('#dataset', surl, ajaxfn, defaultOrder, btnfn, columns, columndefs, rowfn);
	
	
	$('#dataset').on('click', '.J_Show', function(e) {
		var key = $(this).attr('data-key');

		location.href = "/contract/info/"+key+".htm";

	});
	
	$('#dataset').on('click', '.J_Download', function(e) {
		var key = $(this).attr('data-key');

		location.href =  "/contract/download/"+key+".htm";

	});

	loadMarket(marketurl);
	

});