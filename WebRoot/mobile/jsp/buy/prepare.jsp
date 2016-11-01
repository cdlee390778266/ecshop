<%@ page pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn" %> 
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix='fmt' %>  
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="keywords" content="" />
	<meta name="description" content="" />
	<base href=""/>
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="author" content="" />
	<link rel="shortcut icon" href="/mobile/images/icon/favicon.ico" />
	
	<link type="text/css" rel="stylesheet" href="/mobile/css/font.css" />
	<link type="text/css" href="/mobile/css/square/green.css" rel="stylesheet">
	<link type="text/css" href="/mobile/css/validate.css" rel="stylesheet">
	<link type="text/css" rel="stylesheet" href="/mobile/widget/css/ui.dialog.css" />
	<script type="text/javascript" src="/mobile/js/jquery.js"></script>
	<script type="text/javascript" src="/mobile/js/icheck.js"></script>
	<script type="text/javascript" src="/mobile/js/jquery-validate.js"></script>
	<script type="text/javascript" src="/mobile/widget/js/ui.dialog.js"></script>
	<script type="text/javascript" src="/mobile/js/stickup.js"></script>

	<jsp:include page="../comm/mobile.jsp" flush="true" />

	<script type="text/javascript">
		$(function() {

			$(document).ready(function() {
				$('.fixed-wrapper').stickUp();
			});

		//RADIO效果渲染		
		$('input').iCheck({
			checkboxClass: 'icheckbox_square-green',
			radioClass: 'iradio_square-green',
		    increaseArea: '20%' // optional
		});
		
		
		//输入域验证
		$('#buyform').validate({
			onFocus: function() {
				this.parent().addClass('active');
				return false;
			},
			onBlur: function() {
				var $parent = this.parent();
				var _status = parseInt(this.attr('data-status'));
				$parent.removeClass('active');
				if (!_status) {
					$parent.addClass('error');
				}
				return false;
			}
		});
		
		
		function checkVolValid(){
			$('.seledmsg').html("");
			if($("#wFlag").val() == 'W'){
				if($("#vol").val() != $("#wFlag").attr("data-rem")){
					$('.seledmsg').html("购买数量不正确");
					return false;
				}
			}else{
				if(Number($("#vol").val()) > Number($("#wFlag").attr("data-rem"))){
					$('.seledmsg').html("购买数量不得大于可买数量");
					return false;
				}
				if(Number($("#vol").val())!= Number($("#wFlag").attr("data-rem"))){

					var diffvol = Number($("#vol").val()-$("#wFlag").attr("data-base"));
					if(diffvol < 0){
						$('.seledmsg').html("购买数据不得小于起订量");
						return false;
					}

					if((diffvol%Number($("#wFlag").attr("data-ic"))) != 0){
						$('.seledmsg').html("购买数量按递增量递增");
						return false;
					}
				}
			}
			return true;
		}

		function checkVolValid_add(){
			$('.seledmsg').html("");
			if($("#wFlag").val() == 'W'){
				if($("#vol").val() != $("#wFlag").attr("data-rem")){
					layer.msg("购买数量不正确");
					return false;
				}
			}else{
				if(Number($("#vol").val()) > Number($("#wFlag").attr("data-rem"))){
					layer.msg("购买数量不得大于可买数量");
					return false;
				}
				if(Number($("#vol").val())!= Number($("#wFlag").attr("data-rem"))){

					var diffvol = Number($("#vol").val()-$("#wFlag").attr("data-base"));
					if(diffvol < 0){
						layer.msg("购买数据不得小于起订量");
						return false;
					}

					if((diffvol%Number($("#wFlag").attr("data-ic"))) != 0){
						layer.msg("购买数量按递增量递增");
						return false;
					}
				}
			}
			return true;
		}
		
		
		var checkSubmitFlg = false;

		$('#buyform').on('submit', function(event) {

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
			
			if(!checkVolValid_add()){
				event.preventDefault();
				return;
			}
			
			var top = $('input:radio[name="top"]:checked').val();
			
			if(top == undefined || top ==''){

				layer.msg("请选择付款方式")
				event.preventDefault();
				return;
				
			}
			
			checkSubmitFlg = true;
			
		});
		
		function checkFeeValid(){

			var top = $('input:radio[name="top"]:checked').val();
			
			if(top == undefined || top ==''){
				return;
			}
			
			var vol = $('#vol').val();
			
			if( vol== undefined || vol ==''){
				return;
			}
			
			var code =$('#code').val();
			var up = $('#up').val();
			
			
			var pog = Number(Number(up)*Number(vol)).toFixed(2);
			var postParam = "top="+top+"&code="+ code+"&pog="+pog;
			$.ajax({
				type : 'post',
				url : '/buy/findfee.htm',
				data : postParam,
				cache : false,
				dataType : 'json',
				success : function(data) {

					var costlist = data.data;
					var totalAmt = 0.00;
					var htm = '';
					if(costlist.length > 0){
						for(var i = 0; i <costlist.length; i++){
							htm += '<span class="ml10">'+costlist[i].costName+'<span class="fr">'+ Number(costlist[i].costAmt).toFixed(2)+'</span>' +'</span>';

							totalAmt = (Number(totalAmt)+Number(costlist[i].costAmt)).toFixed(2);
						}
					}

					htm += '<span class="ml10 fnt-bnd">总金额'+'<span class="fr">'+ Number(totalAmt).toFixed(2) +'</span>'+'</span>';

					$('.seletop').html(htm);
					$('#all-money').text('￥'+Number(totalAmt).toFixed(2)+'元')

				}
			}
			);
		}
		
		
		// 选择付款方式查询后面计算的金额
		$('input:radio[name="top"]').on('ifChecked', function(event) {

			checkFeeValid();

		});
		
		
		$('.increase').on('click', function(event){

			if($(this).hasClass('disable-increase')) return;
			
			if($('.reduce').hasClass('disable-reduce')){
				$('.reduce').removeClass('disable-reduce');
			}
			var inr = Number($(this).attr("data-role"));
			
			var maxvol = Number($(this).attr("data-max")); 	
			
			var buyval = Number($('#vol').val())+inr;
			
			if(buyval >= maxvol){
				$(this).addClass('disable-increase');
				buyval = maxvol;
				$('.seledmsg').html('');
			}
			$('#vol').val(buyval);
			$('.upperamt').html('试算货款金额:'+'<span class="fr fc999">'+ Number(Number($('#up').val())*Number($('#vol').val())).toFixed(2)+'元' +'</span>');

			checkFeeValid();
		});
		
		
		$('.reduce').on('click', function(event){

			if($(this).hasClass('disable-reduce')) return;
			
			if($('.increase').hasClass('disable-increase')){
				$('.increase').removeClass('disable-increase');
			}

			var inr = Number($(this).attr("data-role"));
			
			var minvol = Number($(this).attr("data-min")); 	
			
			var buyval = Number($('#vol').val())-inr;
			
			if((buyval-minvol)%inr != 0)
			{
				buyval = (Number((buyval-minvol)/inr).toFixed(0)*inr)+minvol;
			}
			if(buyval <=  minvol){
				$(this).addClass('disable-reduce');				
				buyval = minvol;
				$('.seledmsg').html('');
			}
			$('#vol').val(buyval);
			$('.upperamt').html('试算货款金额:'+'<span class="fr fc999">'+ Number(Number($('#up').val())*Number($('#vol').val())).toFixed(2)+'元 ' +'</span>');

			checkFeeValid();
			
		});
		
		
		$('.upperamt').html('试算货款金额:'+ '<span class="fr fc999">' + Number(Number($('#up').val())*Number($('#vol').val())).toFixed(2)+'元 ' + '</span>');
		
		
		$('#vol').on('change keyup', function(event){

			$('.upperamt').html('试算货款金额:'+'<span class="fr fc999">'+ Number(Number($('#up').val())*Number($('#vol').val())).toFixed(2)+'元 ' +'</span>');
			
			checkVolValid();
			
			checkFeeValid();

		});

	});


</script>

<title>用户摘牌</title>

</head>
<body class="drawer drawer-left">

	<div class="fixed-wrapper"> 
		<!-- header -->
		<jsp:include page="../comm/header.jsp" flush="true" />
		<!-- header End -->
	</div>

	<div class="main examine examinego order ">

		<div class="header">
			<div class="header-left"><a href="javascript:history.back(-1);"><img src="/mobile/images/back.png" alt=""></a></div>
			<div class="logo  ">
				订单生成 <span class="fcyellow ">[请在当日完成订单审核]</span>
			</div>
		</div>

		<div class="container-fluid bgfff padb60">

			<!-- 本次摘牌费用说明  -->
			<div class="row bgwhite  examine-data delist mart15 lh40 fc999">

				<h2 class="active">摘牌信息</h2>

				<div class="detail-data" style="display: block;">

					<div class="col-xs-12 strong">
						<span class="fcgreen">摘牌商品： </span>${buyBody.commName} <span style="color: #F40">${buyBody.title}</span>
					</div>

					<div class="col-xs-12 strong">
						<span class="fcgreen ">交货仓库：</span>${buyBody.storage}
					</div>

					<div class="col-xs-12 strong">
						<span class="fcgreen ">有效期至：</span>${buyBody.doe}
					</div>

					<div class="col-xs-12 strong">
						<span class="fcgreen ">挂牌方式：</span>${buyBody.listedTypeName}
					</div>

					<div class="col-xs-12 strong">
						<span class="fcgreen ">交收日期：</span>
						<c:choose>
						<c:when test="${fn:startsWith(buyBody.deliDate,'cycle:')}"> 
						全款支付后${fn:substringAfter(buyBody.deliDate,"cycle:")}天
					</c:when>
					<c:otherwise> 
					${buyBody.deliDate}
				</c:otherwise>
			</c:choose>	
		</div>

		<div class="col-xs-12 strong">
			<span class="fcgreen ">最后付款日：</span>
			<c:choose>
			<c:when test="${fn:startsWith(buyBody.lastPD,'cycle:')}"> 
			合同签下后${fn:substringAfter(buyBody.lastPD,"cycle:")}天
		</c:when>
		<c:otherwise> 
		${buyBody.lastPD}
	</c:otherwise>
</c:choose>
</div>

<div class="col-xs-12 strong">
	<span class="fcgreen ">发票监管：</span>
	<c:choose>												
	<c:when test="${buyBody.invoice=='Y'}">  
	(交易平台监管发票)
</c:when>
<c:otherwise> 
(交易平台不负责监管发票)
</c:otherwise>
</c:choose>
</div>

</div>

</div>

<form name="buyform" id="buyform" method="post" action="/buy/apply.htm" class="marb60 mart10 order-buyform" >
	<input type="hidden" name="listedNo" value="${buyBody.listedNo}" />
	<input type="hidden" name="listedType" value="${buyBody.listedType}" />
	<input type="hidden" name="active" value="buy" />
	<input type="hidden" name="code" id="code" value="${buyBody.commCode}" />
	<input type="hidden" name="wFlag" id="wFlag" value="${buyBody.wholeFlg}" data-base="${buyBody.moq}" data-rem="${buyBody.rem}" data-ic="${buyBody.ic}" />

	<div class="productData row">
		<h2 class="col-xs-12 marb15 ">填写并确认信息</h2>

		<div id="J_Stock" class="padlr20">
			<div class="col-xs-7 padlr5">

				<c:if test="${buyBody.wholeFlg=='W'}">
				<div class="input-group input-group-lg">
					<span class="input-group-addon padlr5 ">购买数量</span>
					<input id="vol" name="vol"  type="text" class="p-text form-control" value="${buyBody.rem}" readOnly maxlength="8" title="整单交易不允许修改">
		       </div>
           <!-- <span class="cnumber fn-fl ml40 cor-red">整单交易不允许修改</span>  -->
			</c:if>

			<c:if test="${buyBody.wholeFlg=='S'}">

			<c:if test="${buyBody.rem <= buyBody.moq}">
			<div class="input-group input-group-lg">
					<span class="input-group-addon padlr5 ">购买数量</span>
					<input id="vol" name="vol"  type="text" class="p-text form-control" value="${buyBody.rem}" readOnly maxlength="8" title="剩余商品一次性交易"> 
		       </div>
		</c:if>

		<c:if test="${buyBody.rem > buyBody.moq}">
		<div class="input-group input-group-lg">
			<span class="input-group-addon padlr5 ">购买数量</span>
			<input class="form-control txtcenter" id="vol" name="vol"  type="text" value="${buyBody.moq}" title="请输入购买量">
			<span class="input-group-addon add rel">
				<a href="javascript:;" class="glyphicon glyphicon-triangle-top increase"  hidefocus="" data-role="${buyBody.ic}" data-max="${buyBody.rem}"></a>
				<a href="javascript:;" class="glyphicon glyphicon-triangle-bottom reduce disable-reduce"  hidefocus="" data-role="${buyBody.ic}" data-min="${buyBody.moq}"></a>
			</span>
		</div>
	</c:if>

</c:if>

</div>

<div class="col-xs-5 rel h46 ">
    
    <c:choose>
	   <c:when test="${buyBody.listedType != 'W'}">   
		   <label class="radio-inline  radio-top ">
				<input type="radio" name="top" id="top" value="S" name="cpay" > 付定金
			</label>
			<label class="radio-inline radio-bottom ">
				<input type="radio" name="top" id="top" value="F" name="cpay" >付全款
			</label>
	   </c:when>
	   <c:otherwise>
		    <label class="radio-inline radio-middle ">
				<input type="radio" name="top" id="top" value="F" name="cpay" >付全款
			</label>
	   </c:otherwise>
  </c:choose>

</div>

<div class="seletop fcyellow"></div>

</div>

<div class="col-xs-12 fc333 mart15">
	单价
	<span class="fcyellow fnt-bnd fr fs18">
		<fmt:formatNumber value="${buyBody.up}" type="currency" pattern="￥0.00元" />/${buyBody.uom}<input type="hidden" name="up" id="up" value="${buyBody.up}" />
	</span>
</div>

<div class="col-xs-12 fc333 ">
	递增数量<span class="fr fc999">${buyBody.ic}${buyBody.uom}</span>
</div>

<div class="col-xs-12 order-borb fc333">
	可买数量<span class="fr fc999">${buyBody.rem}${buyBody.uom}</span>
</div>

<div class="col-xs-12 upperamt fc333"></div>
<div class="col-xs-12 seledmsg bornone"></div>

<div class="col-xs-12 ">
	<span class="fc333">可用资金</span>
	<div class="valid-money">
		<span><fmt:formatNumber value="${totalAmt}" type="currency" pattern="￥0.00" /> </span>元 <br />
		大写(${totalAmtUpper}) 
	</div>
</div>

<div class="order-submit">

	<div>
		总金额<br />
		<strong class="fcyellow" id="all-money">￥0.00元</strong>
	</div>
	<div>
		<button class="yellow-btn">提交订单</button>
	</div>
	
</div>

</div>
</div>
</form>
</div>
</div>

</body>
</html>