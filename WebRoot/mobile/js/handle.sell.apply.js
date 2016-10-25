$(function() {
	
	$(document).ready(function() {
		$('.fixed-wrapper').stickUp();
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
		layer.msg("最后付款日必填");
		$this.focus();
		return false;
	}
	if(!/^[0-9]*$/.test($this.val())){
		$this.css("border","1px solid #F8C944");
		$msg.css("color","red");
		$msg.css("font-size","14px");
		layer.msg("最后付款日为整数");
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
		layer.msg("最后交收日必填");
		$this.focus();
		return false;
	}
	if(!/^[0-9]*$/.test($this.val())){
		$this.css("border","1px solid #F8C944");
		$msg.css("color","red");
		$msg.css("font-size","14px");
		layer.msg("最后交收日为整数");
		$this.focus();
		return false;
	}
	$msg.html("&nbsp;&nbsp;&nbsp;&nbsp;");
	$msg.css("background","url(/images/icon/sucess.png) no-repeat");
	$msg.css("left", "auto");
	return true;
}

	
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
				width : '80%',
				title : '提交提示',
				show : 'scale',
				content : "正在处理,不能重复提交！"
			}).show();
			return false;
		}
		
		if ($("#commCode").val() == null || $("#commCode").val() == "") {
			layer.msg('未选择挂牌商品',{time : 2000});

			event.preventDefault();
			return;
		}

		if(step2){
			step(2);
			step2 = false;
			return false;
		}
		
		//var valFlag = $(this).validate('submitValidate');
		var valFlag = true
		if (valFlag == true) {
			
			var selFlag = false;
			var empty = true;
			$("select[name=propsel]").each(function() {	
				
				var tval = $(this).find("option:selected").text();
				
				if($(this).attr('data-empty') == '1' &&  '商品无此属性项' != tval && $(this).val() == '' && empty == true)
				{
					var $msg = $(this).parent().find('.valid_message');
					$msg.css("color","#e25f59");
					$msg.css("font-size","14px");
					layer.msg('请选择'+$(this).attr('data-propname'));
					$(this).focus();
					selFlag = true;
					empty = false;
					
					return;
				}
			});

			if(selFlag == true){
				event.preventDefault();
				return;
			}

			if(step3){
				step(3);
				step3 = false;
				return false;
			}

			
			if(checkUnitPrice() == false){
				event.preventDefault();
				return ;
				
			}

			if(checkData('#qty') == false){
				event.preventDefault();
				return ;	
			}
			if(checkData('#moq') == false){
				event.preventDefault();
				return ;	
			}
			if(checkData('#incrNum') == false){
				event.preventDefault();
				return ;	
			}

			if($('#doe').val() == ''){
				layer.msg('挂牌有效期必填');
				$('#doe').focus();
				return false;
			}

			if(checkLastPD() == false){
				event.preventDefault();
				return;
			}

			if(checkDeliDate() == false){
				event.preventDefault();
				return;
			}
			
			var $storage = $('#storage');
			var $context = $storage.parent().parent();
			var $msg = $context.find('.valid_message');
			$msg.css("background","");   
			if($storage.val()==''){
				layer.msg("请选择交收仓");
				$storage.focus();
				event.preventDefault();
				return;
			}

			var checkdelist = $('input:radio[name="delist"]:checked').val();
			if(checkdelist =='A'&&$('#memdelists').val() ==''){	
				layer.msg("未选择指定摘牌方");
				event.preventDefault();
				return;
			}


			if(step4){
				step(4);
				step4 = false;
				return false;
			}


			var rlen =  UTFStrLength($('#detail').val())
			if(rlen >= 1500){
				layer.msg("商品描述信息过长,不能超过1500字符")
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
					layer.msg("商品约束关系配置有误");
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
						layer.msg("商品约束关系配置有误");
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
								layer.msg("商品约束关系配置有误");

								if($context.hasClass('success')){
									$context.removeClass('success');
								}

								$context.addClass('error');
								
								return false;
							}else{
								var vm = rvalue.substring(0, pvend);
								
								var mpara = vm.split('~');
								
								var priceval = $that.val();
								
								if(isNaN(parseFloat(priceval)) || parseFloat(priceval)< parseFloat(mpara[0]) || parseFloat(priceval) > parseFloat(mpara[1])){
									
									var $context = $that.parent();
									var $msg = $context.find('.valid_message');
									layer.msg("单价："+  parseFloat(mpara[0])+"到"+ parseFloat(mpara[1])+"之间");
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
				layer.msg("数据格式有误");
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
		$('.unit_message').text('');
		$('.priceunit_message').text('');
		
		$('#storage').empty();
		$('#storage').append('<option value="" selected>请选择</option>');
		if(selflag == false)
		{
			if(String(key).length ==2){
				selmarkcode = '';
				checkmemdelist();
			}
			
			$('#J_AjaxProp .mtbody .mtr').remove();
			$('#J_AjaxProp').hide();	
			
		}else{	
			if(String(key).length ==2){
				selmarkcode = key;
				checkmemdelist();
			}
			
			if (!haveleaf) {
				$('.seledmsg').html("");
				$("#commCode").val(key);
				var postParam = "code=" + key;
				$.ajax({
					type : 'post',
					url : '/mall/prop.htm',
					data : postParam,
					cache : false,
					dataType : 'json',
					success : function(data) {
						$('#J_AjaxProp .mtbody .mtr').remove();
						if (data.succflag == 0) {
							$('.unit_message').text(data.data.uom);
							$('.priceunit_message').text('元/'+data.data.uom);
							var node = data.data.commProps;
							var html = "";
							for ( var i = 0; i < node.length; i++) {

								if(node[i].consType == "81"){									
									$('#unitPrice').attr('data-limit', node[i].consVal);																							
								}

								if(node[i].valFmt == "nodisplay"){
									continue;
								}


								if (i % 2 == 0) {
									if (html != null && html != "") {
										html = html + '</div>';
										$('#J_AjaxProp .mtbody').append(html);
									}
									html = '<div class="mtr">';
								}
								var forceMsg = '';
								if(node[i].forceInput == '1')
									forceMsg ='(<span class="fcyellow">必填</span>)'

								html = html
								+ '<div class="col-xs-6 col-sm-3">'
								+ node[i].propName+forceMsg
								+ '：</div>';


									//类型不一样显示不一样									
									if(node[i].consType == "00"){
										
										//确认验证
										var valid = '';
										var validmsg = '';
										var tipsMsg = '';
										var unitmsg = '';

										if(node[i].forceInput == '1'){
											valid += 'isNonEmpty||';
											validmsg += (node[i].propName+'必填||');
										}
										
										var fmt = node[i].valFmt;										
										var vunit = '';
										var vfmt = '';
										var extmsg = '';

										if(fmt != undefined && fmt != ''){											
											var unitoffset = fmt.indexOf("||");	
											if(unitoffset > 0){
												vfmt = fmt.substring(2, unitoffset);										
												vunit = fmt.substring(unitoffset+2);
											}else{
												vfmt = fmt.substring(2);
											}
										}
										
										var pointidx = 0;
										var fmtlength = 0;
										if(vfmt!=''){
											tipsMsg='格式:'+vfmt;
											pointidx = vfmt.indexOf(".");
											fmtlength = vfmt.length;
										}
										
										if(vunit != undefined && vunit != ''){
											unitmsg = '<span class="unit_message">'+vunit+'</span>';									
										}
										
										var vtype = node[i].valType;	

										if(vtype =='NUM'){												
											if(fmtlength > 0){
												if(pointidx < 0){
													valid += 'isRegexpInt:'+fmtlength+'||';
													validmsg = '输入格式不正确||';
												}else{
													valid += 'isRegexpNum:'+fmtlength+'-'+(fmtlength-pointidx-1)+'||';
													validmsg = '输入格式不正确||';
												}
												valid += 'betweenval:0-'+vfmt+'||';
												validmsg  += '输入超过限值||';												
											}else{
												valid += 'onlyNum||';
												validmsg = '输入格式只能为数字||';
											}
										}else if(vtype =='CUR'){
											valid += 'isMoney||';
											validmsg = '输入格式货币类型||';
										}else if(vtype =='DAT'){
											valid += 'isDate||';
											validmsg = '输入格式日期类型||';
											extmsg = 'datepicker data-date-format="yyyy-mm-dd" data-auto-close="true"';
										}else if(vtype =='TIM'){
											valid += 'isTime||';
											validmsg = '输入格式时间类型||';
										}else if(vtype =='PER'){
											if(fmtlength > 0){
												if(pointidx < 0){
													valid += 'isRegexpInt:'+fmtlength+'||';
													validmsg = '输入格式不正确||';
												}else{
													valid += 'isRegexpNum:'+fmtlength+'-'+(fmtlength-pointidx-1)+'||';
													validmsg = '输入格式不正确||';
												}
											}else{
												valid += 'onlyNum||';
												validmsg = '输入格式只能为数字||';
											}
											valid += 'betweenval:0-100||';
											validmsg  += '输入超过限值||';
										}									
										if(tipsMsg ==''){
											tipsMsg='请输入'+node[i].propName;
										}
										
										valid += 'maxGBLength:128';
										validmsg  += ('长度不能超过128');
										
										html = html 
										+ '<div class="col-xs-6 col-sm-3"><div class="form_control"><input type="text" class="required" '+extmsg+' data-tip="'
										+ tipsMsg
										+ '" data-valid="'+valid+'" data-error="'+validmsg
										+ '" name="propdata" value="" autocomplete="off" data-key="'
										+ node[i].propIdx
										+ '" data-unit="'
										+ vunit
										+ '"/>'+unitmsg+'</div></div>';

									}else if(node[i].consType == "01"){										
										var consEnum = node[i].consVal.split(';');																				
										html = html + '<div class="col-xs-6 col-sm-3"><input type="hidden" name="propdata" value="" data-unit="" data-key="'+ node[i].propIdx+ '" />'+
										'<select name="propsel" id="propsel" class="csel form-control" data-key="'+ node[i].propIdx+ '" data-empty="'+node[i].forceInput+ '" data-propname="'+node[i].propName+'" ><option value="">请选择</option>';
										for(var k = 0; k < consEnum.length; k++)
										{
											html = html + '<option value="'+consEnum[k]+'">'+consEnum[k]+'</option>';
										}
										html = html +'</select><span class="valid_message"></span></div>';										
									}else if(node[i].consType == "02"){
										
										
									}else if(node[i].consType == "03"){											
										var relakeyoffset =  node[i].consVal.indexOf(")");										
										var relakey = node[i].consVal.substring(1, relakeyoffset);
										var items = node[i].consVal.substring(relakeyoffset+1);																					
										var keys = relakey.split('&');										
										var selectmsg = '';										
										for(var n = 0; n < keys.length; n++){
											for(var m = 0; m < node.length; m++){
												if(node[m].propIdx == keys[n]){
													selectmsg += node[m].propName;
													break;
												}
											}
										}
										html = html + '<div class="col-xs-6 col-sm-3"><input type="hidden" name="propdata" value="" data-unit="" data-key="'+ node[i].propIdx+ '" />';										
										html = html + '<select name="propsel" id="propsel" class="csel form-control" data-relakey="'+ relakey+ '" data-relaname="'+selectmsg+'" data-items="'+items+'" data-type="'+ node[i].consType+ '" data-key="'+ node[i].propIdx+ '" data-empty="'+node[i].forceInput+'" data-propname="'+node[i].propName+'" >';		
										html = html + '<option value="">请先选择'+selectmsg+'</option>';
										html = html +'</select><span class="valid_message"></span></div>';											
									}									
								}
								if (html != null && html != "") {
									html = html + '</div>';
									$('#J_AjaxProp .mtbody').append(html);
								}
								$('#J_AjaxProp').show();
								
								$("#J_AjaxProp input[data-date-format=yyyy-mm-dd]").each(function(){ 
									$(this).datepicker();
								});
								

								$('#J_AjaxProp select[name=propsel]').change(function(){ 
									
									var $msg = $(this).parent().find('.valid_message');		
									$msg.text('');

									var key = $(this).attr("data-key");									
									var val = $(this).val();								
									$("select[data-type=03]").each(function(){ 
										var relakey = $(this).attr('data-relakey');
										var items = $(this).attr('data-items');										
										var relaname = $(this).attr('data-relaname');
										var $submsg = $(this).parent().find('.valid_message');		
										$submsg.text('');
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

} else {
	layer.msg("商品无相关属性")														
}
}
});

$.ajax({
	type : 'post',
	url : '/sell/findstore.htm',
	data : {
		"code" : key
	},
	cache : false,
	success : function(data) {
		if (data.succflag == 0){

			var datalist = data.data.list;

			if(datalist != undefined && datalist != null && datalist.length > 0){

				if(datalist.length == 1){
					$('#storage').empty();
				}

				for(var l = 0; l < datalist.length; l++ ){

					$('#storage').append('<option value="'+datalist[l]['storeName']+'">'+datalist[l]['storeName']+'</option>');
				}
			}

		}
		$('#storage').comboSelect();
	}
});
} else {				
	$('#J_AjaxProp .mtbody .mtr').remove();
	$('#J_AjaxProp').hide();
}
}
}



function finderr(msg) {
	layer.msg("无相关可挂牌的商品列表，请确认用户权限")
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
				layer.msg("无相关有效市场，请确认用户权限")
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
	callback : function(){
		localcallback();
		emptyFlag = false;
	}
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
	$('#memdelistmsg').hide();
	$('.memselect .unselect select option').remove();
	$('.memselect .selected select option').remove();
	$('#memdelists').val('');
	var checkdelist = $('input:radio[name="delist"]:checked').val();
	if(checkdelist == 'O'){
		$('#memdelistlink .input-group-addon').text('');
		$('#memdelistlink').hide();				
	}else{
		if(selmarkcode == ''){
			$('#memdelistlink .input-group-addon').html('<span>请先选择市场</span>');
			$('#memdelistlink').addClass('memdelist-unknowmark');
			$('#memdelistlink').show();
		}else{
			$('#memdelistlink .input-group-addon').html('<span>选择会员列表</span>');
			$('#memdelistlink').addClass('memdelist-select');
			$('#memdelistlink').show();
		}
	}
}

$('#memdelistlink').on('click', function(event){
	if(selmarkcode != ''){
		localcallback($('#divisID').attr("data-idx"), $('#divisID').attr("data-key"), '', '', $('#divisID').attr("data-full"));
		UP.Dialog('J_MemList');
		$('body').css('overflowY','hidden');
		emptyFlag = true;
	}	

});

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

	var formParam = "divlevel="+index+"&divcode="+key+"&markcode="+selmarkcode;
	$('#selc-ul').html('');
	$.ajax({
		type : 'post',
		url : '/divis/findmember.htm',
		data : formParam,
		cache : false,
		dataType : 'json',
		success : function(data) {
			if(data.succflag == 0){									
				
					var nodes = data.data.memList;		
					for(var n = 0; n < nodes.length; n++){
						if(IsContain(selectarray, nodes[n].mID))
						{
							$('#selc-ul').append('<li class="active" data='+nodes[n].mID+'>'+nodes[n].memName+'</li>');
						}else{
							$('#selc-ul').append('<li data="' + nodes[n].mID + '">'+nodes[n].memName+'</li>');
						}
					}										
				}else{
					$('#selc-ul').append('<li class="disabled">无会员列表</li>');
				}
			}
		});
}


//会员列表  单选
$('#selc-ul').on('click','li',function(){
	if($(this).hasClass('active')){
		$(this).removeClass('active');
	}else{
		$(this).addClass('active');
	}	
})

//会员列表  全选
$('#select-all').click(function(){
    $('#selc-ul li').addClass('active');
})

//会员列表  取消全选
$('#select-none').click(function(){
    $('#selc-ul li').removeClass('active');
})

$('.btn-close').click(function(){
	$('#confirmbtn').click();
})
$('#confirmbtn').click(function(){
	$('body').css('overflowY','auto');
})



$('#confirmbtn').click(function(){

        if(emptyFlag){
        	selectarray = [];
        }
        selectarray = unique(selectarray);
        $('#selc-ul li').each(function(){
        	if($(this).hasClass('active') && !$(this).hasClass('disabled')){
        		selectarray.push($(this).attr('data'));
        	}
        })
		if(selectarray.length > 0){
			$('#memdelistmsg').text("共选择"+selectarray.length+"家会员做的指定摘牌方");
			$('#memdelistmsg').show();
		}
		else{
			$('#memdelistmsg ').text("未选择指定摘牌方");
			$('#memdelistmsg').show();			
		}
		$('#memdelists').val(selectarray.join(";"));

	UP.Dialog('J_MemList').close();
	$('.mask').hide();
	$('.mark').remove();
});

var cityHeight = $(window).height() - 120;
$('#selc-ul').css('height',cityHeight);

var step2 = false;
var step3 = false;
var step4 = false;
var selectarray = [];
var emptyFlag = true;

    //初始化
    $('#step1,#step2,#step3,#step4').hide();
    $('#step1').show();

    function step(num){
    	if(num ==1){
    		$('#step1,#step2,#step3,#step4').hide();
    		$('#step1').show();
    	}else if(num == 2){
    		$('#step1,#step2,#step3,#step4').hide();
    		$('#step2').show();
    	}else if(num == 3){
    		$('#step1,#step2,#step3,#step4').hide();
    		$('#step3').show();
    	}else if(num == 4){
    		$('#step1,#step2,#step3,#step4').hide();
    		$('#step4').show();
    	}
    }

    $('#step1 .btn-next').click(function(event){
    	step2 = true;
    	$('#sellApply').submit();

    })
    $('#step2 .btn-next').click(function(event){
    	step3 = true;
    	$('#sellApply').submit();	
    })

    $('#step3 .btn-next').click(function(event){
    	step4 = true;
    	$('#sellApply').submit();
    })

    $('#step2 .btn-prev').click(function(){
    	step(1);
    })
    $('#step3 .btn-prev').click(function(){
    	step(2);
    })
    $('#step4 .btn-prev').click(function(){
    	step(3);
    })

    $('.close').click(function(){
    	// $('.con').removeClass('cityactive');
    	$('.cancelbtn').click();
    })

    function checkData(id){
    	$qty = $(id);
    	qtyVal = $qty.val();
    	error =$qty.attr('data-error').split('||');
    	console.log(typeof(qtyVal),qtyVal)
    	if(qtyVal == ''){
    		layer.msg(error[0]);
    		$qty.focus();
    		return false;
    	}else if(!(/^[0-9]+$/.test(qtyVal))){
    		layer.msg(error[1]);
    		$qty.focus();
    		return false;
    	}else{
    		return true;
    	}
    }
    
    //挂牌类型页面跳转
    $('#cd-type').change(function(event) {
    	location.href = $(this).val();
    });

		//移除数组中相同的项
		function unique(arr){
		// 遍历arr，把元素分别放入tmp数组(不存在才放)
		var tmp = new Array();
		for(var i in arr){
		//该元素在tmp内部不存在才允许追加
		if(tmp.indexOf(arr[i])==-1){
			tmp.push(arr[i]);
		}
	}
	return tmp;
}

});
