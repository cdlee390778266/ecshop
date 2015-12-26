$(function() {
	$(document).ready(function() {
		$('.fixed-wrapper').stickUp();
	});
	
	// RADIO效果渲染
	$('input').iCheck({
		checkboxClass : 'icheckbox_square-green',
		radioClass : 'iradio_square-green',
		increaseArea : '20%' // optional
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

	// 确认回调函数
	function noTips(strikeNo) {
		var formParam = "strikeNo=" + strikeNo + "&type=S";

		$.ajax({
			type : 'post',
			url : '/delivery/confirm.htm',
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
	$('#dataset').on('click', '.J_SendProd',  function(e) {

		var strikeNo = $(this).data("key");
		// 初始化确认提示框

		var dl = UI.Dialog({
			type : 'delete', // 提示框类型，这里是delete 代表删除提示框
			width : 480, // 设置提示框的宽度
			title : '交易确认', // 提示框标题的文字信息
			content : '您确定单号' + strikeNo + '的商品要发货吗？', // 提示框的内容文字信息
			href : noTips,
			param: strikeNo
		}).show();
		e.stopPropagation();
	});

	// 投诉弹出
	$('#dataset').on( 'click', '.J_AppealLink',function(e) {
		var current = $(this).data('href');

		$('.reasonmsg').html('');
		$('#appealDesc').val('');
		
		$("#AppealStkNo").html($(this).data('role'));
		$("#AppealStkDoe").html($(this).data('doe'));
		
		if($(this).attr('data-status')==100){
			$('#delayMsg').html("买方未按期付款");
		}else if($(this).attr('data-status')==300){
			$('#delayMsg').html("买方逾期未确认货物");
		}else if($(this).attr('data-status')==400){
			$('#delayMsg').html("买方逾期未确认发票");
		}

		UP.Dialog(current);
		e.preventDefault();
	});
	
	
	// 弹出投诉显示层
	$('#dataset').on( 'click', '.J_AppealShowLink', function(e) {
		var appealId = $(this).data("key");
		var current = $(this).data('href');
		var formParam = "compno=" + appealId;
		$.ajax({
				type : 'post',
				url : '/delivery/findappeal.htm',
				data : formParam,
				cache : false,
				dataType : 'json',
				success : function(data) {
					if(data.succflag == '0'){
						
						acceptmsg = data.data;
						$('#'+current+' .compstrikeno').html(acceptmsg.strikeNo);
						$('#'+current+' .compmid').html(acceptmsg.compMID);						
						$('#'+current+' .comptrdstatus').html(acceptmsg.trdStatusDesc);						
						$('#'+current+' .comptime').html(acceptmsg.compTime);						
						$('#'+current+' .comprlt').html(acceptmsg.compRlt);						
						$('#'+current+' .compaccepter').html(acceptmsg.accepter);						
						$('#'+current+' .compacceptime').html(acceptmsg.accepTime);
						$('#'+current+' .compcomment').html(acceptmsg.comment);	
						
						if(acceptmsg.acceptRet == '0')
							$('#'+current+" .compacceptret").html('拒绝受理该投诉');
						else if(acceptmsg.acceptRet == '1')
							$('#'+current+" .compacceptret").html('已受理投诉');
						else
							$('#'+current+" .compacceptret").html('');
						UP.Dialog(current);
						e.preventDefault();
					}else{
						var rsdl = UI.Dialog({
							type : 'tips',
							width : 320,
							title : '交易结果',
							content : data.msg,
						}).show();
					}
				}	
		});
	});

	$('input:radio[name="appealType"]').on('ifChecked', function(event) {		
		if(this.id=='delayCheck'){
			$("#appealDescTitel").hide();
			$("#appealDesc").hide();
		}else{
			$("#appealDescTitel").show();
			$("#appealDesc").show();
		}
	});
	
	
	function UTFStrLength(value){
		var rlen= 0;
    	var len = value.length;
    	var charCode = -1;
    	for(var i = 0; i < len; i++){
    	　　charCode = value.charCodeAt(i);
    	　　if (charCode >= 0 && charCode <= 128) {
    	　　		rlen += 1;
    	　　}else{
    			rlen += 3;
    	　　}
    	}
    	return rlen;	
	}

	// 投诉事件处理
	$('.updialog').delegate(
			'.cpublish',
			'click',
			function(e) {

				var strikeNo = $("#AppealStkNo").html();
				var status = $('input:radio[name="appealType"]:checked').val();
				var reason = '';
				if(status=='1'){
					reason = $("#appealDesc").val();
					
					var rlen = UTFStrLength(reason);
					
			    	if(rlen >= 512){
			    		$('.reasonmsg').html("投诉原因说明不能超过512个字符");
						event.preventDefault();
						return;
			    	}
			    	if(rlen == 0){
			    		$('.reasonmsg').html("其他原因时必须填入投诉说明");
						event.preventDefault();
						return;
			    	}
				}						 
				var formParam = "strikeNo=" + strikeNo + "&status=" + status
						+ "&reason=" + reason;
				UP.Dialog('J_Appeal').close();
				$.ajax({
					type : 'post',
					url : '/delivery/appeal.htm',
					data : formParam,
					cache : false,
					dataType : 'json',
					success : function(data) {
						var rsdl = UI.Dialog({
							type : 'tips',
							width : 320,
							title : '交易结果',
							content : data.msg
						}).show();
							
						$('.mask').hide();
						$('.mark').remove();
						// 需要刷新本页数据
						extResetColume($dataset);					
						$dataset.ajax.reload(function(data){			
							extReloadColumn($dataset);

					     });			
					}
				});
			});


	
	var marketurl = "/mall/findallmarket.htm";
	
	loadMarket(marketurl);
	
	
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
	
	
	var columns =  [
	                { "data": "clsName", "type":"cn-string" },
	                { "data": "commCode" },
	                { "data": "commName", "type":"cn-string" },
	                { "data": "summary1", "type":"cn-string" },
	                { "data": "summary2", "type":"cn-string" },	
	                { "data": "bmID" },
	                { "data": "bmemName", "type":"cn-string" },  
	                { "data": "dod" }, 
	                { "data": "lastpd" },
	                { "data": "delidate" },
	                { "data": "statusDesc", "type":"cn-string" },      
	                { "data": "vol"},            
	                { "data": "up"},
	                { "data": "contAmt"},     	          
	                { "data": "listedTypeName", "type":"cn-string" },	
	                { "data": "storage", "type":"cn-string" },
	                { "data": "title", "type":"cn-string" },               
	                { "data": "strikeNo" },
	                { "data": "contNo" },              
	                { "data": null,"orderable": false }
	            ];
	
	var surl = '/delivery/finddelist.htm';
	
	var ajaxfn  = function(d){	
		d.type = 'S';
        d.strikeNo = $('#strikeNo').val();
        d.lastPD = $('#lastPD').val();
        d.status = $('#status').val();
        d.commcode = $('#commcode').val();
	};
	
	var defaultOrder = [9, "asc"];
	
	var columndefs = [
	                  {"render": function ( data, type, row ) {
	                  	var htm = '';

	      				if (row.status > 0) {
	      					if (row.status == 200 && row.enableT == 1) {					
	      						htm = '<input type="button" value="确认发货" class="J_SendProd cbtn" data-key="'+row.strikeNo+ '" />';
	      					} else {						
	      						if(row.status <= 300 && row.enableT == 1){							
	      							htm = '<input type="button" value="交易投诉" data-href="J_Appeal"  class="J_AppealLink cbtn" data-role="'+row.strikeNo+ '" data-doe="'+row.doe+ '"  data-status="'+row.status+ '" />';			
	      						}
	      					}
	      				}
	      				
	      				if (row.lastCompNo != undefined && row.lastCompNo != null && row.lastCompNo != "" && row.lastCompNo != 0 ) {
	      					htm += '<input type="button" value="投诉查看"  data-href="J_AppealShow" class="J_AppealShowLink cbtn" data-key="'+row.lastCompNo+ '" />';			
	      				}
	      				
	      				return htm;
	      				
	                    },
	                    "targets": -1
	                  }
	               ];
	
	var rowfn  = function(row, data, index){
		var val = data['delidate'].split('-');
        
        if(val.length >= 3){
			var currentdate = new Date();									
			var paydate = new Date();
			
			paydate.setFullYear(parseInt(val[0], 10));									
			paydate.setMonth(parseInt(val[1], 10)-1);
			paydate.setDate(parseInt(val[2], 10));
												
			var interval = paydate-currentdate								
			var html ='';
			if(interval <  4*24*60*60*1000){									
				$('td:eq(9)', row).addClass('highlight');										
			}
		}
        $('td:eq(11)', row).html(data.vol+data.uom);
        $('td:eq(12)', row).html(Number(data.up).formatMoney());
        $('td:eq(13)', row).html(Number(data.contAmt).formatMoney());
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

});
