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
	<script type="text/javascript" src="/mobile/js/sha.js"></script>
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

			var valFlag = $(this).validate('submitValidate'); 

			if(valFlag == true){
				if($("#fundPwd").length > 0)
				{
					$("#fundPwd").val(b64_sha1($("#fundPwd").val()));
				}	

				var auditflag  = $("input[name='auditRet']:checked").val();
				if(auditflag != undefined && auditflag == '0'){
					if($('#comment').val() == '' || $('#comment').val() == null){
						var $parent = $('#comment').parent();		
						$parent.removeClass('success');	    			
						$parent.addClass('error');	    			 		    	
						var $msg = $parent.find('.valid_message');
						layer.msg('请输入审核意见');	
						event.preventDefault();
						return;	    			
					}
				}

			}else{
				event.preventDefault();
				return;
			}
			checkSubmitFlg = true;
		});
	});

</script>

<title>摘牌处理</title>

</head>
<body class="drawer drawer-left">

	<div class="fixed-wrapper"> 
		<!-- header -->
		<jsp:include page="../comm/header.jsp" flush="true" />
		<!-- header End -->
	</div>

	<div class="header">
		<div class="header-left"><a href="javascript:history.back(-1);"><img src="/mobile/images/back.png" alt=""></a></div>
		<div class="logo  ">
			<div class="logoTxt">
				资金支付 <br /><span class="fcyellow ">[全款支付后请到交收管理中准备接收货物]</span>
			</div>
		</div>
	</div>

	<div class="container-fluid examine up-detail">
		<div class="row bgwhite  examine-data delist mart5 lh40 fc999">
			<h2 class="active">摘牌信息</h2>
			<div class="detail-data">

				<div class="col-xs-12 strong">
					<span class="fcgreen">摘牌单号： </span>${rspBody.deListNo}
				</div>

				<div class="col-xs-12 strong">
					<span class="fcgreen">摘牌商品： </span>${rspBody.commName}
				</div>

				<c:forEach items="${rspBody.props}" var="prop" >												
				<c:if test="${prop.propName =='品牌' || prop.propName =='产地' }">
				<div class="col-xs-12 strong">
					<span class="fcgreen">${prop.propName}：</span>${prop.propVal}
				</div>
			</c:if>
		</c:forEach>

		<div class="col-xs-12 strong">
			<span class="fcgreen ">单价：</span><fmt:formatNumber value="${rspBody.up}" type="currency" pattern="￥0.00元" />/${rspBody.uom}
		</div>

		<div class="col-xs-12 strong">
			<span class="fcgreen ">挂牌有效期：</span>${rspBody.doe}
		</div>

		<div class="col-xs-12 strong">
			<span class="fcgreen ">交收日期：</span>
			<c:choose>
			<c:when test="${fn:startsWith(rspBody.deliDate,'cycle:')}"> 
			全款支付后${fn:substringAfter(rspBody.deliDate,"cycle:")}天
		</c:when>
		<c:otherwise> 
		${rspBody.deliDate}
	</c:otherwise>
</c:choose>
</div>

<div class="col-xs-12 strong">
	<span class="fcgreen ">摘牌日期：</span>${rspBody.dod}
</div>

<div class="col-xs-12 strong">
	<span class="fcgreen ">购买数量：</span>${rspBody.vol}
</div>

<div class="col-xs-12 strong">
	<span class="fcgreen ">最后付款日：</span>
	<c:choose>
	<c:when test="${fn:startsWith(rspBody.lastPD,'cycle:')}"> 
	合同签下后${fn:substringAfter(rspBody.lastPD,"cycle:")}天
</c:when>
<c:otherwise> 
${rspBody.lastPD}
</c:otherwise>
</c:choose>
</div>

<div class="col-xs-12 strong">
	<span class="fcgreen ">付款方式：</span>
	<c:if test="${rspBody.top =='S'}">仅付定金</c:if>
	<c:if test="${rspBody.top =='F'}">全款支付</c:if>
</div>

<div class="col-xs-12 strong">
	<span class="fcgreen ">交收仓库：</span>${rspBody.storage}
</div>

<div class="col-xs-12 strong">
	<span class="fcgreen ">发票监管：</span>
	<c:choose>												
	<c:when test="${rspBody.invoice=='Y'}">  
	交易平台监管发票
</c:when>
<c:otherwise> 
交易平台不负责监管发票
</c:otherwise>
</c:choose>
</div>

</div>
</div>

<div class="lh40 mart5">

	<div class="row">
		<div class="col-xs-12 padlr25 up-title">本次摘牌费用说明</div>
	</div>

	<c:forEach items="${rspBody.costPays}" var="cost" >
	<div class="row bgfff up-cost">
		<div class="col-xs-4 borderdb  txtleft">费用名称</div>
		<div class="col-xs-8 borderdb fc999">${cost.costName}</div>
		<div class="col-xs-4 borderdb txtleft ">交易类型</div>
		<div class="col-xs-8 borderdb fc999">${cost.trTypeName}</div>
		<div class="col-xs-4 borderdb txtleft ">费用金额</div>
		<div class="col-xs-8 borderdb fcyellow">
			<fmt:formatNumber value="${cost.costAmt}" type="currency" pattern="￥0.00元" />
		</div>
		<div class="col-xs-4 borderdb txtleft ">当前状态</div>
		<div class="col-xs-8 borderdb fc999">${cost.flagDesc}</div>
	</div>
</c:forEach>

</div>

<div class="row mart5">
	<form name="buyform" id="buyform" method="post" action="/buy/${link}.htm" class="bgfff padb120 ofhidden">
		<input type="hidden" name="delistNo" value="${rspBody.deListNo}" />
		<c:if test="${operType=='A'}"> 

		<div class="col-xs-12 up-title">
			填写并确认信息
		</div>

		<div class="form-bgwhite">

			<div class="form-group ">
				<div class="radio">
					<label for="radio1">审核通过</label>
					<input type="radio" name="auditRet" id="auditRet" value="1" checked />
				</div>
				<div class="radio">
					<label for="radio2">不审核通过</label>
					<input type="radio" name="auditRet" id="auditRet" value="0" />
				</div>
			</div>

			<div>
				<h3 class="marb10 fc999">审核意见</h3>
				<div class="rel">
					<textarea name="comment" id="comment" type="text" class="required form-control"  data-tip="请输入审核意见" data-valid="maxGBLength:512" data-error="审核意见长度错误" rows="3"></textarea>
				</div>
			</div>

		</div>

		<div class="examine-go txtcenter examine-btn">
			<button class="btn-normal btn-buy">提交审核</button>
		</div>

	</c:if>

	<c:if test="${operType=='P'}">

	<div class="col-xs-12 up-title">
		资金密码
	</div>  

	<div class="col-xs-12 mart10 rel up-funpwd">
		<input type="password" name="fundPwd" id="fundPwd" class="required form-control input-lg"  data-tip="请输入资金密码" data-valid="isNonEmpty||between:6-16" data-error="资金密码必填||密码长度错误" />
	</div>  

	<div class="examine-go txtcenter examine-btn">
		<button class="btn-normal btn-buy">支付资金</button>
	</div>                          	                                        

</c:if>  
</form>

</div>

</div>

</body>
</html>