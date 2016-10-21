$(function() {
	
	var step3 = false;
	var step4 = false;
	var selectarray = [];
	var emptyFlag = true;

//改版部分新增代码   ---start

    //初始化
    $('#wrno').val('');
    $('#step1-notice,#step2-notice,#step3-notice,#step4-notice').removeClass('active');
    $('#step1-notice').addClass('active');
    $('.curmbs').find('img').hide();
    $('.gp-step1,.gp-step2,.gp-step3,.gp-step4').hide();
    $('.gp-step1').show();
    $('.step-promise').html($('.trade-promise .val span').html());

    function step(num){
    	if(num ==1){
    		$('#step1-notice,#step2-notice,#step3-notice,#step4-notice').removeClass('active');
    		$('#step1-notice').addClass('active');
    		$('.curmbs').find('img').hide();
    		$('.gp-step1,.gp-step2,.gp-step3,.gp-step4').hide();
    		$('.gp-step1').show();
    	}else if(num == 2){
    		$('#step1-notice,#step2-notice,#step3-notice,#step4-notice').removeClass('active');
    		$('#step2-notice').addClass('active');
    		$('.curmbs').find('img').hide();
    		$('#step1-notice img').show();
    		$('.gp-step1,.gp-step2,.gp-step3,.gp-step4').hide();
    		$('.gp-step2').show();
    	}else if(num == 3){
    		$('#step1-notice,#step2-notice,#step3-notice,#step4-notice').removeClass('active');
    		$('#step3-notice').addClass('active');
    		$('.curmbs').find('img').hide();
    		$('#step1-notice img,#step2-notice img').show();
    		$('.gp-step1,.gp-step2,.gp-step3,.gp-step4').hide();
    		$('.gp-step3').show();
    	}else if(num == 4){
    		$('#step1-notice,#step2-notice,#step3-notice,#step4-notice').removeClass('active');
    		$('#step4-notice').addClass('active');
    		$('.curmbs').find('img').hide();
    		$('#step1-notice img,#step2-notice img,#step3-notice img').show();
    		$('.gp-step1,.gp-step2,.gp-step3,.gp-step4').hide();
    		$('.gp-step4').show();
    	}
    }

    $('.gp-step1 .btn-next').click(function(event){
    	step3 = true;
    	$('#sellApply').submit();

    })
    
    $('.gp-step3 .btn-next').click(function(event){
    	step4 = true;
    	$('#sellApply').submit();
    })

    $('.toStep1').click(function(){
    	step(1);
    })

    $('.toStep3').click(function(){
    	step(3);
    })

    $('.close').click(function(){
    	$('.cancelbtn').click();
    	$('body').css('overflowY','auto');
    })

    $('#tradeType li').click(function(){
    	location.href = $(this).data('href');
    })

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

//改版部分新增代码   ---end

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
			layer.msg("请选择注册仓单！");
			event.preventDefault();
			return;
		}else{
			$('#codev').html("");
		}

		if(step3){
			step(3);
			step3 = false;
			return false;
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
				layer.msg("未选择指定摘牌方");	
				$('#memdelistmsg').addClass('memdelist-error');
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
				layer.msg("商品描述信息过长,不能超过1500字符");
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
								
								
								if(parseFloat(priceval)< parseFloat(mpara[0]) || parseFloat(priceval) > parseFloat(mpara[1])){
									
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
		layer.msg("无相关可挂牌的商品列表，请确认用户权限");
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
					layer.msg("无相关有效市场，请确认用户权限");
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
	
	//文闻2016年3月23日修改
	function checkmemdelist(){
		var wrno = $('#wrno').val();
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
			$('#memdelistlink').text('');							
		}else{
			if(wrno == ''){
				$('#memdelistlink .input-group-addon').html('<span>请先选择注册仓单</span>');
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
		var mc = $("#markcode").val();
		if(mc != ''){
			localcallback($('#divisID').attr("data-idx"), $('#divisID').attr("data-key"), '', '', $('#divisID').attr("data-full"));
			UP.Dialog('J_MemList');
			$('body').css('overflowY','hidden');
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

		// UP.Dialog('J_cdList');
		$('.select_mask').css({
			display:'block',
			opacity:0.5
		})
		$('#J_cdList').css('display','block').animate({
			bottom : '0'
		})
		dataList.columns.adjust();
	});

	//选择注册仓单取消
	$('#cancelbtn1').on('click', function(event){
		//获取已经获取的数据
		// UP.Dialog('J_cdList').close();
		$('.select_mask').css({
			display:'none',
			opacity:0.5
		})
		$('#J_cdList').css({
			'display' : 'none',
			'bottom' : '-70vh'
		})
		$('.mask').hide();
		$('.mark').remove();

	});

    //注册仓单弹窗关闭
    $('.select-hd span,.select_mask').click(function(){
    	$('#cancelbtn1').click();
    })

	//选择注册仓单确定
	$('#confirmbtn1').on('click', function(event){
		//获取已经获取的数据
		var d =dataList.rows('.selected').data();
		if(d.length<1){
			$("#wrno").val("");   //注册仓单编码
			$("#markcode").val("");   //市场代码
			$("#storage").val("");   //交收仓库
			$("#commcode").val("");   //商品代码
			$("qty").val("");   //货物数量
		}else{
			$("#wrno").val(d[0].registno);   //注册仓单编码
			$("#markcode").val(d[0].markcode);   //市场代码
			$("#storage").val(d[0].storename);   //交收仓库
			$("#commcode").val(d[0].commcode);   //商品代码
			$("#qty").val(d[0].qty);   //货物数量
		}
		
		// UP.Dialog('J_cdList').close();
		
		$('.mask').hide();
		$('.mark').remove();
		$('#cancelbtn1').click();
		$('.cd-id').html($('#wrno').val());
	});
	
});
