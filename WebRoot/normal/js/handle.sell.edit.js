$(function() {
	
	$(document).ready(function() {
		$('.fixed-wrapper').stickUp();
	});
	
	function setElementReadOnly() {
		if(listedType == 'W') {   //仓单设置以下元素只读
			$("input[name='listedType']").attr("disabled", "disabled");   //设置挂牌方式只读
			$("input[name='qty']").attr("readonly", "readonly");   //设置总量只读
			$("input[name='wholeFlag']").attr("disabled", "disabled");   //设置是否整单只读
			$("#storage").attr("disabled", "disabled");   //设置交收仓库只读
		}
	}
	
	function rmElementReadOnly() {
		$("input[name='listedType']").removeAttr("disabled");   //设置挂牌方式可写
		$("input[name='qty']").removeAttr("readonly");   //设置总量可写
		$("input[name='wholeFlag']").removeAttr("disabled");   //设置是否整单可写
		$("#storage").removeAttr("disabled");   //设置交收仓库可写
	}
	
	setElementReadOnly();
	
	// 是否整单处理
	$('.J_WholeFlag input').on('ifChecked', function(event) {
		if (this.id == 's_flag') {
			$("#moq").val(1);
			$("#incrNum").val(1);
			$("#moq").removeAttr("readonly");
			$("#incrNum").removeAttr("readonly");
		} else {
			$("#moq").val($("#qty").val());
			$("#incrNum").val($("#qty").val());
			$("#moq").attr({
				readonly : 'true'
			});
			$("#incrNum").attr({
				readonly : 'true'
			});
		}
	});
	
	laydate.skin('molv');
	
	laydate({
	    elem: '#doe', //目标元素。
	    event: 'focus', //响应事件。
	    min: $('#doe').data('min'),
	    choose: function(dates){
	    	$('#doe').blur();
	    }
	});

	// 输入域验证
	$('#sellEdit').validate({
		onFocus : function() {
			this.parent().addClass('active');
			return false;
		},
		onBlur : function() {
			var $parent = this.parent();
			var _status = parseInt(this.attr('data-status'));
			$parent.removeClass('active');
			if (!_status) {
				$parent.addClass('error');
			}
			return false;
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
	
	function checkLastPD(){
		
		$this = $('#lastPD');
		
		$this.css("border","1px #ECECEC solid");
		$this.css("background-color","#FFFFFF");
		var $context = $this.parent();
        var $msg = $context.find('.valid_message');
        $msg.css("background","");   
		if($this.val()==''){
			$this.css("border","1px solid #F8C944");
			$msg.css("color","red");	
			$msg.css("font-size","14px");
	        $msg.text("最后付款日必填");
	        $this.focus();
	        return false;
		}
		if(!/^[0-9]*$/.test($this.val())){
			$this.css("border","1px solid #F8C944");
			$msg.css("color","red");
			$msg.css("font-size","14px");
	        $msg.text("最后付款日为整数");
	        $this.focus();
	        return false;
		}
		$msg.html("&nbsp;&nbsp;&nbsp;&nbsp;");
		$msg.css("background","url(/images/icon/sucess.png) no-repeat");
		$msg.css("left", "auto");
		return true;
	}
	
	function checkDeliDate(){
		
		$this = $('#deliDate');
		
		$this.css("border","1px #ECECEC solid");
		$this.css("background-color","#FFFFFF");
		var $context = $this.parent();
        var $msg = $context.find('.valid_message');
        $msg.css("background","");   
		if($this.val()==''){
			$this.css("border","1px solid #F8C944");
			$msg.css("color","red");
			$msg.css("font-size","14px");
	        $msg.text("最后交收日必填");
	        $this.focus();
	        return false;
		}
		if(!/^[0-9]*$/.test($this.val())){
			$this.css("border","1px solid #F8C944");
			$msg.css("color","red");
			$msg.css("font-size","14px");
	        $msg.text("最后交收日为整数");
	        $this.focus();
	        return false;
		}
		$msg.html("&nbsp;&nbsp;&nbsp;&nbsp;");
		$msg.css("background","url(/images/icon/sucess.png) no-repeat");
		$msg.css("left", "auto");
		return true;
	}
	
	$('#lastPD').on("focus", function(event){ 
		$(this).css('border','1px #4c9430 solid');
		var $context = $(this).parent();
	    var $msg = $context.find('.valid_message');
	    if($msg.text() == ''){
		    $msg.css("color","green");
		    $msg.css("background","");   
		    $msg.text("请输入最后付款日");
	    }
	});

	$('#lastPD').on("blur", function(event){   
		checkLastPD();
	});
	
	
	$('#doe').on("blur", function(event){   
		if(!$('.datepicker-container').is(':hidden'))
		{
			$('.datepicker-container').hide();
		}
	});
	
	$('#deliDate').on("focus", function(event){ 
		$(this).css('border','1px #4c9430 solid');
		var $context = $(this).parent();
	    var $msg = $context.find('.valid_message');
	    if($msg.text() == ''){
		    $msg.css("color","green");
		    $msg.css("background","");   
		    $msg.text("请输入最后交收日");
	    }
	});

	$('#deliDate ').on("blur", function(event){   
		checkDeliDate();
	});
	
	$('#storage').on('change', function(event){
		var $context = $(this).parent();
        var $msg = $context.find('.valid_message');
        $msg.css("background","");   
		if($(this).val()==''){
			$msg.css("color","red");	
			$msg.css("font-size","14px");
	        $msg.text("请选择交收仓");
	        return false;
		}else{
			 $msg.text("");
		}
	});
	
	
	$('.ui-table select[name=propsel]').change(function(){ 
		var key = $(this).attr("data-key");									
		var val = $(this).val();	
		
		var $msg = $(this).parent().find('.valid_message');		
        $msg.text('');
        
		$("select[data-type=03]").each(function(){ 
			var relakey = $(this).attr('data-relakey');
			var items = $(this).attr('data-items');										
			var relaname = $(this).attr('data-relaname');										
			if(key == relakey){											
				$(this).empty();											
															
				if(val != null &&  val !=""){												
					var begkey = "{"+val+":";
					var begoffset = items.indexOf(begkey);
					if(begoffset >= 0){
						var seles = items.substring(begoffset+begkey.length);
						var endoffset = seles.indexOf("}");											
						if(endoffset >= 0){												
							var items = seles.substring(0, endoffset).split(';');															
							if(items.length == 1){
								$(this).empty();											
								$(this).append('<option value="'+items[0]+'">'+items[0]+'</option>');															
							}else{
								$(this).append('<option value="">请选择</option>');
								for(var k = 0; k < items.length; k++){
									$(this).append('<option value="'+items[k]+'">'+items[k]+'</option>');
								}
							}
						}												
					}else{
						$(this).append('<option value="" selected>商品无此属性项</option>');
					}
				}else{
					$(this).append('<option value="">请先选择'+relaname+'</option>');	
				} 
			}
		}); 
	}); 

	
	$('#detail').text($('.proddetail').text());
	
	if(listedType == 'M'){
		$('#storage').comboSelect();
	}

	function checkUnitPrice(){
		
		$that = $('#unitPrice');
	    var vlimit = $that.attr('data-limit');
	    
	    if(vlimit == undefined || vlimit == ''){
	    	return true;
	    }else{
	    	var vstart = vlimit.indexOf(")");
			var vactive = "";
			if(vstart != -1){
				vactive = vlimit.substring(1, vstart);
	    	
				var $relaprop = $("select[name=propsel][data-key="+vactive+"]");
			
				if($relaprop == undefined){
					var $context = $that.parent();
			        var $msg = $context.find('.valid_message');
				    $msg.text("商品约束关系配置有误");
				    
				    if($context.hasClass('success')){
				    	$context.removeClass('success');
				    }
				    
				    $context.addClass('error');
					
					return false;
				}else{
					
					
					var pval =  $relaprop.val();
					
					if(pval == null || pval == ""){
						var $context = $that.parent();
				        var $msg = $context.find('.valid_message');
					    $msg.text("商品约束关系配置有误");
					    
					    if($context.hasClass('success')){
					    	$context.removeClass('success');
					    }
					    
					    $context.addClass('error');
						
						return false;
						
					}else{
					
						var pvstart = vlimit.indexOf(pval);
						
						if(pvstart == -1)
							return true;
						else{
							var rvalue = vlimit.substring(pvstart+pval.length+1);
							
							var pvend = rvalue.indexOf('}');
							
							if(pvend == -1){
								
								var $context = $that.parent();
						        var $msg = $context.find('.valid_message');
							    $msg.text("商品约束关系配置有误");
							    
							    if($context.hasClass('success')){
							    	$context.removeClass('success');
							    }
							    
							    $context.addClass('error');
								
								return false;
							}else{
								var vm = rvalue.substring(0, pvend);
								
								var mpara = vm.split('~');
								
								var priceval = $that.val();
								
								
								if(parseFloat(priceval)< parseFloat(mpara[0]) || parseFloat(priceval) > parseFloat(mpara[1])){
									
									var $context = $that.parent();
							        var $msg = $context.find('.valid_message');
								    $msg.text("单价："+  parseFloat(mpara[0])+"到"+ parseFloat(mpara[1])+"之间");
								    
								    if($context.hasClass('success')){
								    	$context.removeClass('success');
								    }
								    
								    $context.addClass('error');
								    
								    $that.focus();
									
									return false;
									
								}else{
															
									return true;
								}
							}
						}
					}
				}
			}else{
				
				var $context = $that.parent();
		        var $msg = $context.find('.valid_message');
			    $msg.text("数据格式有误");
			    
			    if($context.hasClass('success')){
			    	$context.removeClass('success');
			    }
			    
			    $context.addClass('error');
				return false;
				
			}
	    }
	}
	
	var checkSubmitFlg = false;
	
	// 提示验证
	$('#sellEdit').on('submit', function(event) {
		
		if(checkSubmitFlg == true)
		{
			UI.Dialog({
				type : 'tips',
				width : 320,
				title : '提交提示',
				content : "正在处理,不能重复提交！"
			}).show();
			return false;
		}
		
		
		if ($("#commCode").val() == null || $("#commCode").val() == "") {
			$('.seledmsg').html("未选择商品品种");
			event.preventDefault();
			return;
		}
		
		var valFlag = $(this).validate('submitValidate');
		if (valFlag == true) {
			
			
			var selFlag = false;
			$("select[name=propsel]").each(function() {	
				
				var tval = $(this).find("option:selected").text();
				
				if($(this).attr('data-empty') == '1' &&  '商品无此属性项' != tval && $(this).val() == '')
				{
					var $msg = $(this).parent().find('.valid_message');
					$msg.css("color","red");
					$msg.css("font-size","14px");
			        $msg.text('请选择'+$(this).attr('data-propname'));
			        $(this).focus();
			        selFlag = true;
			        return;			   
				}
			});
			
			if(selFlag == true){
				event.preventDefault();
				return;
			}
			
			if(checkUnitPrice() == false){
				event.preventDefault();
				return;
			}
			if(listedType == 'M'){
				if(checkLastPD() == false){
					event.preventDefault();
					return;
				}

				if(checkDeliDate() == false){
					event.preventDefault();
					return;
				}
			}
			
			
			var $storage = $('#storage');
			var $context = $storage.parent().parent();
	        var $msg = $context.find('.valid_message');
	        $msg.css("background","");   
			if($storage.val()==''){
				$msg.css("color","red");	
				$msg.css("font-size","14px");
		        //$msg.text("请选择交收仓");
				 $storage.focus();
		        return false;
			}
			
			var checkdelist = $('input:radio[name="delist"]:checked').val();
			if(checkdelist =='A'&&$('#memdelists').val() ==''){
				$('#memdelistmsg').text("未选择指定摘牌方");	
				$('#memdelistmsg').addClass('memdelist-error');
				event.preventDefault();
				return;
			}
			
	    	var rlen =  UTFStrLength($('#detail').val())
	    	if(rlen >= 1500){
	    		$('.detailmsg').html("商品描述信息过长,不能超过1500字符");
				event.preventDefault();
				return;
	    	}
	    	
			$("select[name=propsel]").each(function(i) {		
				$($(this).siblings()[0]).val($(this).val());
			});
			
			
			$("input[name=propdata]").each(function(i) {
				if($(this).val() != ''){
					$(this).val($(this).attr('data-key') + '~' + $(this).val()+$(this).attr('data-unit'));
				}
			});
			
			var lPDval = $('#lastPD').val();
			$('#lastPD').val('cycle:'+lPDval);	
			var lDelival = $('#deliDate').val();
			$('#deliDate').val('cycle:'+lDelival);
			
		} else {
			event.preventDefault();
			return;
		}
		
		rmElementReadOnly();
		checkSubmitFlg = true;
	});

	// RADIO效果渲染
	$('input').iCheck({
		checkboxClass : 'icheckbox_square-green',
		radioClass : 'iradio_square-green',
		increaseArea : '20%' // optional
	});

	// 总量改变事件。
	$('#qty').on('change', function(event) {
		var wsFlag = $('.J_WholeFlag input[name="DwholeFlag"]:checked').val();

		$("#moq").parent().removeClass('error');
		$("#incrNum").parent().removeClass('error');
		if (wsFlag == 'W') {
			$("#moq").val($(this).val());
			$("#incrNum").val($(this).val());
			
			if($(this).val() == ''){
				$("#moq").parent().removeClass('success');
				$("#incrNum").parent().removeClass('success');
				
			}else{
				$("#moq").parent().addClass('success');
				$("#incrNum").parent().addClass('success');
			}
			$("#moq").parent().find('.valid_message').text('');
			$("#incrNum").parent().find('.valid_message').text('');
		}
		
		
	});

	$('form').find('input[type=file]').on('change', function(evt) {
		var files = evt.target.files;

		var url = null;
		if (window.createObjectURL != undefined) {
			url = window.createObjectURL(files[0])
		} else if (window.URL != undefined) {
			url = window.URL.createObjectURL(files[0])
		} else if (window.webkitURL != undefined) {
			url = window.webkitURL.createObjectURL(files[0])
		}
		
		var chgID = '#' + $(this).attr("id") + 'Chg';
		$(chgID).val($(this).attr("id"));
		var preImg = '#' + $(this).attr("id") + 'Pre';
		$(preImg).attr("src", url);
		$(preImg).show();
		$(this).siblings().text($(this).siblings().text().replace('选择', '替换'));	
		$(this).parent().removeClass('btn-upload_unselected');
		$(this).parent().addClass('btn-upload-selected');
	});
	
	
	var localsel = $('.localcity').localCity({
		
		provurl : "/divis/findprov.htm", 
		cityurl : "/divis/findcity.htm",
		disturl : "/divis/finddist.htm",
		callback : localcallback
	});
	
	
	$('input:radio[name="delist"]').on('ifChecked', function(event) {
		checkmemdelist();
	});
	
	function checkmemdelist(){
		
		if($('#memdelistlink').hasClass('memdelist-unknowmark'))
			$('#memdelistlink').removeClass('memdelist-unknowmark');
		
		if($('#memdelistlink').hasClass('memdelist-select'))
			$('#memdelistlink').removeClass('memdelist-select');
		
		$('#memdelistmsg').text('');
		$('.memselect .unselect select option').remove();
		$('.memselect .selected select option').remove();
		$('#memdelists').val('');
		var checkdelist = $('input:radio[name="delist"]:checked').val();
		if(checkdelist == 'O'){
			$('#memdelistlink').text('');							
		}else{
			if($('#markcode').val() == ''){
				$('#memdelistlink').text('请先选择市场');
				$('#memdelistlink').addClass('memdelist-unknowmark');
			}else{
				$('#memdelistlink').text('选择会员列表');
				$('#memdelistlink').addClass('memdelist-select');
			}
		}
	}
	
	$('#memdelistlink').on('click', function(event){
		if($('#markcode').val() != ''){
			localcallback($('#divisID').attr("data-idx"), $('#divisID').attr("data-key"), '', '', $('#divisID').attr("data-full"));
			UP.Dialog('J_MemList');
		}	
	});
	
	
	$('#divisID').on('focus', function(event){
		if(!$('.localcity').hasClass('cityactive'))$('.localcity').addClass('cityactive');
	})
	
	
	function IsContain(arr,value)
	{
		if(arr == undefined || arr.length == 0) return false;
		
		for(var i=0;i<arr.length;i++)
		{
			if(arr[i]==value)
				return true;
		}
		return false;
	}
	
	function localcallback(index, key, keyname, fullkey, fullname){
		
		$('#divisID').val(fullname);
		$('#divisID').attr("date-key", key);
		$('#divisID').attr("date-idx", index);
		$('#divisID').attr("date-full", fullname);
		
		var formParam = "divlevel="+index+"&divcode="+key+"&markcode="+$('#markcode').val();
		$('.memselect .unselect select option').remove();
		$.ajax({
			type : 'post',
			url : '/divis/findmember.htm',
			data : formParam,
			cache : false,
			dataType : 'json',
			success : function(data) {
				if(data.succflag == 0){									
					var selectedvals = new Array(); //定义数组
				    $("#selectedlist option").each(function(){ 
				        var val = $(this).val(); 
				        if(val != undefined && val != "") 
				        	selectedvals.push(val); 
				    });
					
					var nodes = data.data.memList;		
					for(var n = 0; n < nodes.length; n++){
						if(IsContain(selectedvals, nodes[n].mID))
							$('.memselect .unselect select').append('<option value="'+nodes[n].mID+'" disabled>'+nodes[n].memName+'</option>');
						else
							$('.memselect .unselect select').append('<option value="'+nodes[n].mID+'">'+nodes[n].memName+'</option>');
					}										
				}else{
					$('.memselect .unselect select').append('<option disabled>无会员列表</option>');
				}
			}
		});
	}
	
	$('.selbtn').delegate('.pa-btn-sell', 'click', function(e) {
		
		if($(this).hasClass('btn-single-select')){
			var selvals = $("#selectlist").val();
			var selectedobj =$("#selectedlist"); 			
			$("#selectlist option").each(function(){ 
				var val = $(this).val();
				if(IsContain(selvals, val)){	
					selectedobj.append('<option value="'+val+'">'+ $(this).text()+'</option>');  
					$(this).attr("disabled", "disabled");
				}
			 });
		}else if($(this).hasClass('btn-all-select')){
			var selectedobj =$("#selectedlist"); 
			$("#selectlist option").each(function(){ 
		    	var val = $(this).val();
		    	if($(this).attr('disabled') == undefined){
		    		selectedobj.append('<option value="'+val+'">'+ $(this).text()+'</option>');  
					$(this).attr("disabled", "disabled");
		    	}
		    }); 
		}else if($(this).hasClass('btn-single-unselect')){
			var selvals = $("#selectedlist").val();
			var selectobj =$("#selectlist"); 			
			$("#selectedlist option").each(function(){ 
				var val = $(this).val();
				if(IsContain(selvals, val)){	
					$(this).remove();
				}
			 });
			
			$("#selectlist option").each(function(){ 
				var val = $(this).val();
				if(IsContain(selvals, val)){	
					$(this).removeAttr("disabled");
				}
			 });	
		}else if($(this).hasClass('btn-all-unselect')){
			var selectobj =$("#selectlist"); 		
			var selectarray = new Array();
			
			$("#selectedlist option").each(function(){ 
				selectarray.push($(this).val());
				$(this).remove();
		    }); 
			
			$("#selectlist option").each(function(){ 
				var val = $(this).val();
				if(IsContain(selectarray, val)){	
					$(this).removeAttr("disabled");
				}
		    }); 
		}
	});
	
	
	$('#J_MemList').delegate('.cbtn', 'click', function(e) {	
		if($(this).attr('id') == 'confirmbtn'){
			var selectarray = new Array();			
			$("#selectedlist option").each(function(){ 
				selectarray.push($(this).val());
		    }); 
			if($('#memdelistmsg').hasClass('memdelist-error'))
				$('#memdelistmsg').removeClass('memdelist-error');
			
			if(selectarray.length > 0)
				$('#memdelistmsg').text("共选择"+selectarray.length+"家会员做的指定摘牌方");
			else
				$('#memdelistmsg').text("未选择指定摘牌方");			
			$('#memdelists').val(selectarray.join(";"));

		}else if($(this).attr('id') == 'cancelbtn'){
			
		}
		UP.Dialog('J_MemList').close();
		$('.mask').hide();
		$('.mark').remove();
	});

});
