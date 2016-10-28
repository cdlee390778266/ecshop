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
						initData : res.data,// 第一次初始化的时候加载select 数据，参考上面的json格式
						surl : "/mall/subsellclass.htm", // 商品类别选择，添加子选择项 url
						removeUrl : "/mall/parsellclass.htm",// 通过“X"删除时请求的 url
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
		            { "data": "mdsename", "type":"cn-string" },
		            { "data": "memname", "type":"cn-string" },
		            { "data": "registno", "type":"cn-string" },
		            { "data": "storename", "type":"cn-string" },		        
		            { "data": "storeno", "type":"cn-string"},
		            { "data": "qty"},	                    
		            { "data": null,"orderable": false}
		        ];
	
	var surl = '/warehouse/findaudited.htm';
	
	var ajaxfn  = function(d){
		if($("#commcode").val() !='' ){
			d.commcode = $("#commcode").val();
		}
		if($("#econtTime").val() !='' ){
			d.econtTime = $("#econtTime").val();
		}
		if($("#contTime").val() !='' ){
			d.contTime = $("#contTime").val();
		}
		if($("#storeno").val() !='' ){
			d.storeno = $("#storeno").val();
		}
		if($("#auditno").val() !='' ){
			d.auditno = $("#auditno").val();
		}
	};
	
	var defaultOrder = [6, "desc"];
	var columndefs = [                             
	  	            {"render": function ( data, type, row, index ) {
	  	            	
		                var html = '';
		                if(enablePay == 1){
		                	// html =  '<input type="button" value="注册仓单挂牌" class="J_Show cbtn" data-key="' + index.row + '" />';
		                }
		                
		                return html;
		              },
		              "targets": -1
		            }
		         ];
	
	var rowfn  = function(row, data, index){
	}
	
	var btnfn = function(){
		var res =/^\d+$/;
		
		if($("#auditno").val() != '' && $("#auditno").val() != null){
			if(!res.test($("#auditno").val())){
				UI.Dialog({type : 'tips',width : '80%', title : '查询提示',content : '审核编号只能由数字组成！'}).show();						
				event.stopPropagation();
				return false;
			}
		}
		
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
	
	var dataList =dataTablesInit('#dataset', surl, ajaxfn, defaultOrder, btnfn, columns, columndefs, rowfn);

	loadMarket(marketurl);

	 $('#type').change(function(event) {
      location.href =   $('#J_TabBar a').eq($(this).val()).attr('href');
   });
	 
});