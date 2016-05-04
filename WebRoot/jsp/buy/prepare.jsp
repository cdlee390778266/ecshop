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
    <link rel="shortcut icon" href="/images/icon/favicon.ico" />
    <link type="text/css" rel="stylesheet" href="/css/global.css" />
    <link type="text/css" rel="stylesheet" href="/css/font.css" />
    <link type="text/css" rel="stylesheet" href="/css/common.css" />
    <link type="text/css" rel="stylesheet" href="/css/member.css" />
    <link type="text/css" href="/css/square/green.css" rel="stylesheet">
    <link type="text/css" href="/css/validate.css" rel="stylesheet">
    <link type="text/css" rel="stylesheet" href="/widget/css/ui.dialog.css" />
    <script type="text/javascript" src="/js/jquery.js"></script>
	<script type="text/javascript" src="/js/icheck.js"></script>
    <script type="text/javascript" src="/js/jquery-validate.js"></script>
    <script type="text/javascript" src="/widget/js/ui.dialog.js"></script>
    <script type="text/javascript" src="/js/stickup.js"></script>

    <script type="text/javascript">
   $(function() {

		$(document).ready(function() {
				$('.fixed-wrapper').stickUp();
				checkFeeValid();
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
			
			if(!checkVolValid()){
				event.preventDefault();
				return;
			}
			
			var top = $('input:radio[name="top"]:checked').val();
			
			if(top == undefined || top ==''){
			
				$('.seletop').html("请选择付款方式");
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
			
			if( vol == undefined || vol == ''){
				return;
			}
			
			var code =$('#code').val();
			var up = $('#up').val();
			
			
			var pog = Number(Number(up) * Number(vol)).toFixed(2);
			var postParam = "top=" + top + "&code=" + code + "&pog=" + pog;
			$.ajax({
						type : 'post',
						url : '/buy/findfee.htm',
						data : postParam,
						cache : false,
						dataType : 'json',
						success : function(data) {
							var listedType = $("#listedType").val();
							var costlist = data.data;
							var totalAmt = 0.00;
							var htm = '';
							if(costlist.length > 0){
								for(var i = 0; i <costlist.length; i++){
									htm += '<span class="ml10">' + costlist[i].costName + ':' + Number(costlist[i].costAmt).toFixed(2) + '</span>';
									
									totalAmt = (Number(totalAmt) + Number(costlist[i].costAmt)).toFixed(2);
								}
							}
							
							htm += '<span class="ml10 fnt-bnd">总金额:' + Number(totalAmt).toFixed(2) + '</span>';
							
							if(listedType == 'M'){
								$('.seletop').html(htm);
							}
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
			$('.upperamt').text('试算货款金额:'+Number(Number($('#up').val())*Number($('#vol').val())).toFixed(2)+'元   ');
			
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
			$('.upperamt').text('试算货款金额:'+Number(Number($('#up').val())*Number($('#vol').val())).toFixed(2)+'元  ');
			
			checkFeeValid();
			
		});
		
		
		$('.upperamt').text('试算货款金额:'+Number(Number($('#up').val())*Number($('#vol').val())).toFixed(2)+'元 ');
		
		
		$('#vol').on('change keyup', function(event){
		
			$('.upperamt').text('试算货款金额:'+Number(Number($('#up').val())*Number($('#vol').val())).toFixed(2)+'元 ');
			
			
			checkVolValid();
			
			checkFeeValid();
		
		});

    });

    
    
    </script>
    <title>用户摘牌</title>
</head>
<body>

	<div class="fixed-wrapper"> 
	<!-- topbar -->
	<jsp:include page="../comm/topbar.jsp" flush="true" />
	
	<!-- topbar End -->
	
	<!-- header -->
	<jsp:include page="../comm/header.jsp" flush="true" />
	<!-- header End -->
	
	</div>
	
	<!-- wrapper -->
	<div class="wrapper">
		<div class="grid-16-16 prelat">
			<div class="crumb-nav">
				<div class="backto">
					<div class="backrt">
						<a href="/home.htm">返回首页<i></i></a>
					</div>
				</div>
				<div class="crumb">
					<a href="/home.htm">交易大厅</a><span class="fa  fa-angle-right"></span><a href="/buy/list.htm">会员中心</a><span class="fa  fa-angle-right"></span><a href="/buy/list.htm">购买订单</a>
				</div>
			</div>
			<!-- main -->
			<div class="page">
				
				<div class="main-content">
					<div class="bd">
						
						<div class="page-module bsmenus">
							<div class="row" style="overflow:hidden;zoom:1;height:90px;margin-bottom:10px">
								<ul class="step-list">
									<li class="active">
										<div class="linebox">
											<label for="">
												<p>订单生成</p>
												<i class="icon-step step-active">1</i>
												<span class="round">请在当日完成订单审核</span>
											</label>
										</div>
									</li>
									<li class="mid">
										<div class="linebox">
											<label for="">
												<p>审核确认</p>
												<i class="icon-step">2</i>
												<span class="round">请在当日完成订单定金支付</span>
											</label>
										</div>
									</li>
									<li>
										<div class="linebox">
											<label for="">
												<p>资金支付</p>
												<i class="icon-step">3</i>
												<span class="round">全款支付后请到交收管理中准备接收货物</span>
											</label>
										</div>
									</li>
									<li class="nobor">
										<div class="linebox noline">
											<label for="">
												<p>交收确认</p>
												<i class="icon-step"><i class="fa fa-angle-arraw">4</i></i>
												<span class="round">根据交收日期进行交收确认</span>
											</label>
										</div>
									</li>
								</ul>
							</div>
							<div class="row bsrow">
								<div class="hd">
									<h3>摘牌信息</h3>
								</div>
								<div class="bd">
									<table class="ui-table bstable">
										<tbody>
											<tr>
												<td width="120px"><label for="">摘牌商品：</label></td>
												<td colspan="5">${buyBody.commName} <span style="color: #F40">${buyBody.title}</span></td>
																								
											</tr>	
											<tr>
												<td><label for="">交货仓库：</label></td>
												<td>${buyBody.storage}</td>
												<td width="120px"><label for="">有效期至：</label></td>
												<td>${buyBody.doe}</td>
												<td width="120px"><label for="">挂牌方式：</label></td>
												<td>${buyBody.listedTypeName}</td>
											</tr>
											
											<tr>
											<td><label for="">交收日期：</label></td>
												<td>
													<c:choose>
											   			<c:when test="${fn:startsWith(buyBody.deliDate,'cycle:')}"> 
											   				全款支付后${fn:substringAfter(buyBody.deliDate,"cycle:")}天
														</c:when>
												    	<c:otherwise> 
															${buyBody.deliDate}
														</c:otherwise>
													</c:choose>												
												</td>
												<td><label for="">最后付款日：</label></td>
												<td>											
													<c:choose>
											   			<c:when test="${fn:startsWith(buyBody.lastPD,'cycle:')}"> 
											   				合同签下后${fn:substringAfter(buyBody.lastPD,"cycle:")}天
														</c:when>
												    	<c:otherwise> 
															${buyBody.lastPD}
														</c:otherwise>
													</c:choose>
												</td>
												<td>
													<label for="">发票监管：</label>
												</td>
												<td>
													<c:choose>												
														<c:when test="${buyBody.invoice=='Y'}">  
															交易平台监管发票
														</c:when>
														<c:otherwise> 
															   		交易平台不负责监管发票
														</c:otherwise>
													</c:choose>
												</td>
											</tr>											
										</tbody>										                                               
									</table>
									                                                                                                         
								</div>
							</div>
							<div class="row bsrow">
								<form name="buyform" id="buyform" method="post" action="/buy/apply.htm" >
								<input type="hidden" name="listedNo" value="${buyBody.listedNo}" />
								<input type="hidden" name="listedType" value="${buyBody.listedType}" />
								<input type="hidden" name="active" value="buy" />
								<input type="hidden" name="code" id="code" value="${buyBody.commCode}" />
								<input type="hidden" name="wFlag" id="wFlag" value="${buyBody.wholeFlg}" data-base="${buyBody.moq}" data-rem="${buyBody.rem}" data-ic="${buyBody.ic}" />
								<div class="hd">
									<h3>填写并确认信息</h3>
								</div>	
								<div class="bd">
									<table class="ui-table bstable">
										<tr>
											<td width="120px"><label for="">单价：</label></td>
											<td>
												<div class="cont clearfix">
													<span class="cor-red fnt-bnd"> <fmt:formatNumber value="${buyBody.up}" type="currency" pattern="￥0.00元" />/${buyBody.uom}</span>
													<input type="hidden" name="up" id="up" value="${buyBody.up}" />
												</div>
											</td>
										</tr>
										<tr>
											<td><label for="">购买数量：</label></td>
											<td>
												<div class="cont clearfix">
													<span class="pa-stock clearfix fn-fl" id="J_Stock">
													
													<c:if test="${buyBody.wholeFlg=='W'}">
														<input id="vol" name="vol"  type="text" class="p-text" value="${buyBody.rem}" readOnly maxlength="8" title="整单交易不允许修改"><span class="cnumber fn-fl ml40 cor-red">整单交易不允许修改</span> 
													</c:if>
													<c:if test="${buyBody.wholeFlg=='S'}">
														<c:if test="${buyBody.rem <= buyBody.moq}">
															<input id="vol" name="vol"  type="text" class="p-text" value="${buyBody.rem}" readOnly maxlength="8" title="剩余商品一次性交易"> 
														</c:if>
														<c:if test="${buyBody.rem > buyBody.moq}">
															<a href="javascript:;" hidefocus="" data-role="${buyBody.ic}" data-max="${buyBody.rem}" class="increase">+</a>
															<input id="vol" name="vol"  type="text" value="${buyBody.moq}" class="p-text" title="请输入购买量">
															<a href="javascript:;" hidefocus="" data-role="${buyBody.ic}" data-min="${buyBody.moq}" class="reduce disable-reduce">-</a>
															<span class="cnumber fn-fl ml20 cor-red">递增数量：${buyBody.ic}${buyBody.uom}</span>
														</c:if>
													</c:if>
													</span>
													<span class="cnumber fn-fl ml20">可买数量：${buyBody.rem}${buyBody.uom}</span>
													<span class="upperamt" style="line-height: 30px; margin-left: 10px; font-size:14px"></span>
													<span class="seledmsg" style="color: #f00; line-height: 30px; margin-left: 10px;"></span>
													
													
												</div>
											</td>
										</tr>
										
										<tr>
											<td><label for="">可用资金：</label></td>
											<td>
												<div class="cont clearfix">
													 <fmt:formatNumber value="${totalAmt}" type="currency" pattern="￥0.00元" />  大写(${totalAmtUpper}) 
												</div>
											</td>
										</tr>
										<tr>
											<td><label for="">付款方式：</label></td>
											<td>
												
													<c:if test="${buyBody.listedType != 'W'}">
														<input type="radio" name="top" id="top" value="S" name="cpay"/><span class="ml5 mr20">仅付定金</span>
													</c:if>
													<input type="radio" name="top" id="top" value="F" name="cpay" readonly="readonly" checked="checked" /><span class="ml5 mr20">付全款</span>
												
												
													<span class="seletop" style="color: #f00; line-height: 30px; margin-left: 10px;"></span>
											</td>
										</tr>
													
										<tr>
											<td colspan="2">
												<div class="cont ml60">
													<div class="pa-btn-buy fn-fl">
												  		<button class="btn-normal btn-buy">提交订单</button> 
												  	</div>                	
												</div>
											</td>
										</tr>
									</table>
								</div>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- main End -->

		</div>
	</div>
	<!-- wrapper End -->		

	<!-- footer -->
	<jsp:include page="../comm/footer.jsp" flush="true" />
	<!-- footer End -->
</body>
</html>