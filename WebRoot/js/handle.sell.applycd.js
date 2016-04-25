$(function() {
	
	$(document).ready(function() {
		$('.fixed-wrapper').stickUp();
		$('#dataset').dataTable();
	});
	
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

	// 输入域验证
	$('#sellApply').validate({
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
	
	laydate.skin('molv');
	
	laydate({
	    elem: '#doe', //目标元素。
	    event: 'focus', //响应事件。
	    min: $('#doe').data('min'),
	    choose: function(dates){
	    	$('#doe').blur();
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
	
	
	$('#lastPD').on("focus", function(event){ 
		$(this).css('border','1px #89C975 solid');
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
		$(this).css('border','1px #89C975 solid');
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
	        //$msg.text("请选择交收仓");
	        return false;
		}else{
			 $msg.text("");
		}
	});
	
	
	var checkSubmitFlg = false; 
	
	// 提示验证
	$('#sellApply').on('submit', function(event) {
		
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
		if ($("#wrno").val() == null || $("#wrno").val() == "") {
			//$('.seledmsg').html("未选择商品品种");
			$('#codev').html("请选择注册仓单！");
			event.preventDefault();
			return;
		}else{
			$('#codev').html("");
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
			
			$("select[name=propsel]").each(function() {		
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
		var wsFlag = $('.J_WholeFlag input[name="wholeFlag"]:checked').val();

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
		}else{
			if( $("#moq").val() == undefined || $("#moq").val() == "")
			{
				$("#moq").val(1);
				$("#moq").parent().addClass('success');
			}
			
			if( $("#incrNum").val() == undefined || $("#incrNum").val() == "")
			{
				$("#incrNum").val(1);
				$("#incrNum").parent().addClass('success');
			}
		}
	
	});
	

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

	function propChange(p){		
		var val = $(p).attr("data-key");		
	}
	
	var selmarkcode = '';
	
	$("#commCode").val("");
	
	function load(key, haveleaf, selflag, prevkey) { // 通过回调函数传递的code 去查询数据，渲染模板	
		
		$('.seledmsg').html("");
		$("#commCode").val("");
		$("#sCCode").val("");
		if(selflag == false){
			if(String(key).length ==2){
				selmarkcode = '';
			}			
		}else{	
			if(String(key).length ==2){
				selmarkcode = key;
			}
			
			if (!haveleaf) {
				var scv = selmarkcode;
				$('.seledmsg').html("");
				$("#commCode").val(key);
				$("#sCCode").val(scv);						
			} 
		}
	}
	


	function finderr(msg) {
		$('.seledmsg').html("无相关可挂牌的商品列表，请确认用户权限");
	}

	var marketurl = '/mall/findallmarket.htm';

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
						removeUrl : "/mall/parsellclass.htm",// 通过“X"删除时请求的 url
						callback : load,
					// 回调函数，每次触发select change 事件和删除项，会返回一个code
						defaultparams : {
							txtype : "T"
						},
						exceptcallback : finderr
					});
				} else {
					$('.seledmsg').html("无相关有效市场，请确认用户权限");
				}
			}
		});
	}
	
	loadMarket(marketurl);

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
	
	//文闻2016年3月23日修改
	function checkmemdelist(){
		var wrno = $('#wrno').val();
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
			if(wrno == ''){
				$('#memdelistlink').text('请先选择注册仓单');
				$('#memdelistlink').addClass('memdelist-unknowmark');
			}else{
				$('#memdelistlink').text('选择会员列表');
				$('#memdelistlink').addClass('memdelist-select');
			}
		}
	}
	
	$('#memdelistlink').on('click', function(event){
		var mc = $("#markcode").val();
		if(mc != ''){
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
		var mc =$("#markcode").val()
		$('#divisID').val(fullname);
		$('#divisID').attr("date-key", key);
		$('#divisID').attr("date-idx", index);
		$('#divisID').attr("date-full", fullname);
		
		var formParam = "divlevel="+index+"&divcode="+key+"&markcode="+mc;
		
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
	
	var btnfn = function(){
		if($('#sCCode').val()==''||$('#commCode').val() == ''){
		    UI.Dialog({type : 'tips',width : 320, title : '查询提示',content : '按商品查询时,必须选择具体的商品'}).show();						
			event.stopPropagation();
			return false;
		}
		return true;
	}
	
	var ajaxfn  = function(d){	
        d.code = $('#commCode').val();

	};
	
	var columns =  [
		            { "data": "storeno", "type":"cn-string" },
		            { "data": "storename", "type":"cn-string" },
		            { "data": "registno", "type":"cn-string" },
		            { "data": "memname"},		        
		            { "data": "mdsename", "type":"cn-string"},
		            { "data": "qty"}
		        ];
	
	var surl = '/sell/findCdList.htm';
	var defaultOrder = [0, "desc"];
	var dataList = dataTablesInit1('#J_cdList #dataset', surl, ajaxfn, defaultOrder, btnfn, columns, null, "");
	$('#selectCd').on('click', function(event){
		
		UP.Dialog('J_cdList');
		dataList.columns.adjust();
	});

	//选择注册仓单取消
	$('#cancelbtn1').on('click', function(event){
		//获取已经获取的数据
		UP.Dialog('J_cdList').close();
		$('.mask').hide();
		$('.mark').remove();
	});
	
	//选择注册仓单确定
	$('#confirmbtn1').on('click', function(event){
		//获取已经获取的数据
		var d =dataList.rows('.selected').data();
		if(d.length<1){
			$("#wrno").val("");
			$("#markcode").val("");
			$("#storage").val("");
			$("#commcode").val("");
			$("qty").val("");
		}else{
			$("#wrno").val(d[0].registno);
			$("#markcode").val(d[0].markcode);
			$("#storage").val(d[0].storename);
			$("#commcode").val(d[0].commcode);
			$("#qty").val(d[0].qty);
		}
		
		UP.Dialog('J_cdList').close();
		$('.mask').hide();
		$('.mark').remove();
		
	});
	
});
