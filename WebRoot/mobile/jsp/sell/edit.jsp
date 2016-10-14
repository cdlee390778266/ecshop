<%@ page pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn" %> 
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
	<link type="text/css" rel="stylesheet" href="/mobile/widget/css/ui.dialog.css" />
	<link type="text/css" rel="stylesheet" href="/mobile/widget/css/ui.datepicker.css" />
	<link type="text/css" rel="stylesheet" href="/mobile/css/commsel.css" />
	<link type="text/css" rel="stylesheet" href="/mobile/css/square/green.css">
	<link type="text/css" rel="stylesheet" href="/mobile/css/validate.css" >
	<link type="text/css" rel="stylesheet" href="/mobile/css/combo.select.css" >

	<script type="text/javascript">
		var listedType = '${rspBody.listedType}';
	</script>
	<script type="text/javascript" src="/mobile/js/jquery.js"></script>
	<script type="text/javascript" src="/mobile/widget/js/ui.dialog.js"></script>
	<script type="text/javascript" src="/mobile/widget/js/ui.datepicker.js"></script>
	<script type="text/javascript" src="/mobile/js/jquery.combo.select.js"></script>
	<script type="text/javascript" src="/mobile/js/icheck.js"></script>
	<script type="text/javascript" src="/mobile/js/localcity.js"></script>
	<script type="text/javascript" src="/mobile/js/jquery-validate.js"></script>
	<script type="text/javascript" src="/mobile/js/stickup.js"></script>
	<script type="text/javascript" src="/mobile/widget/laydate/laydate.js"></script> 
	<script type="text/javascript" src="/mobile/js/handle.sell.edit.js?v=${sessionScope.buildno}"></script>

	<jsp:include page="../comm/mobile.jsp" flush="true" />

	<title>卖方挂牌</title>   
</head>
<body class="drawer drawer-left">

	<div class="fixed-wrapper">  
		<!-- header -->
		<jsp:include page="../comm/header.jsp" flush="true" />
		<!-- header End -->
	</div>
	
	<div class="container-fluid examine demand">
		<div class="row safe-type txtcenter bgfff up-list">
			<div class="col-xs-12"><a href="javascript:void(0);" class="active">卖方挂牌修改</a></div>
		</div>

		<form action="/sell/edithandle.htm" method="post" enctype="multipart/form-data" id="sellEdit" class="form-horizontal">
			<input type="hidden" name="busDate" id="busDate" value="${busDate}" />

			<div class="form-group borderb  form-group-lg">
				<label class="col-xs-5 control-label ">挂牌单号</label>
				<div class="col-xs-7">
					<span class="form-control txtright lh26 ">
						${rspBody.listedNo}
						<input type="hidden" name="listedNo" value="${rspBody.listedNo}" />
						<c:if test="${rspBody.wrNo != '' }">
						<input type="hidden" name="wrNO" value="${rspBody.wrNo}" />
					</c:if>
				</span>
			</div>
		</div>
		<div class="form-group borderb  form-group-lg">
			<label class="col-xs-5 control-label ">挂牌方式</label>
			<div class="col-xs-7">
				<span class="form-control txtright lh26 ">
					<c:if test="${rspBody.listedType=='M'}">
					<span><input type="radio" name="listedType" value="M" checked />保证金</span>
				</c:if>
				<c:if test="${rspBody.listedType=='W'}">
				<span><input type="radio" name="listedType" value="W" checked />注册仓单</span>
			</c:if>
		</span>
	</div>
</div>
<div class="form-group borderb  form-group-lg">
	<label class="col-xs-5 control-label ">挂牌商品</label>
	<div class="col-xs-7">
		<span class="form-control txtright lh26 ">
			<input type="hidden" name="commCode" id="commCode" value="${rspBody.commCode}" />												
			${rspBody.markName}&gt;${rspBody.className}&gt;${rspBody.commName}
		</span>
	</div>
</div>

<div >
	<c:set var="pricelimit" value="" />
	<c:if test="${fn:length(rspBody.props) > 0}">
	<div class="mtr row bgfff" id="step2">
		<c:forEach items="${rspBody.props}" var="prop" varStatus="status">

		<c:choose>
		<c:when test="${prop.consType != '81'}">

		<div class="col-xs-6 col-sm-3"  >														
			<c:if test="${prop.forceInput =='1'}">
			${prop.propName}<span class="fcyellow">(必填)</span>：
		</c:if>
		<c:if test="${prop.forceInput !='1'}">
		${prop.propName}：
	</c:if>
</div>
<div class="col-xs-6 col-sm-3">														
	<c:if test="${prop.consType == '00'}">
	<c:set var="valid" value="${''}" />
	<c:set var="validmsg" value="${''}" />
	<c:set var="extmsg" value="${''}" />
	<c:set var="tipsmsg" value="${'请输入'}${prop.propName}" />
	<c:if test="${prop.forceInput =='1'}">
	<c:set var="valid" value="${'isNonEmpty||'}" />
	<c:set var="validmsg" value="${prop.propName}${'必填||'}" />
</c:if>	

<c:set var="fmt" value="${prop.valFmt}" />
<c:set var="vunit" value="${''}" />
<c:set var="vfmt" value="${''}" />																
<c:set var="pointidx" value="${'0'}" />
<c:set var="fmtlength" value="${'0'}" />

<c:if test="${!empty fmt && fn:length(fmt) > 0}">
<c:if test="${fn:indexOf(fmt, '||') >= 0}">
<c:set var="vunit" value="${fn:substringAfter(fmt, '||')}" />
<c:set var="fmt" value="${fn:substringBefore(fmt, '||')}" />																																														
</c:if>
<c:set var="vfmt" value="${fn:substringAfter(fmt, 'fm')}" />																	
<c:set var="pointidx" value="${fn:indexOf(vfmt, '.')}" />
<c:set var="fmtlength" value="${fn:length(vfmt)}" />
<c:set var="tipsmsg" value="${'格式'}${vfmt}" />
</c:if>

<c:set var="pval" value="${prop.propVal}" />
<c:if test="${!empty vunit && fn:length(vunit)>0}">
<c:set var="pval" value="${fn:substringBefore(prop.propVal, vunit)}" />
</c:if>

<c:if test="${prop.valType =='CUR'}">
<c:set var="valid" value="${valid}${'isMoney||'}" />
<c:set var="validmsg" value="${validmsg}${'输入格式货币类型||'}" />																	
</c:if>

<c:if test="${prop.valType =='DAT'}">
<c:set var="valid" value="${valid}${'isDate||'}" />
<c:set var="validmsg" value="${validmsg}${'输入格式日期类型||'}" />																			
<c:set var="extmsg" value="${'datepicker data-date-format=yyyy-mm-dd data-auto-close=true'}" />																																			
</c:if>																
<c:if test="${prop.valType =='TIM'}">
<c:set var="valid" value="${valid}${'isTime||'}" />
<c:set var="validmsg" value="${validmsg}${'输入格式时间类型||'}" />																	
</c:if>	 															
<c:if test="${prop.valType =='NUM'}">																
<c:if test="${fmtlength > 0}">
<c:if test="${pointidx < 0}">
<c:set var="valid" value="${valid}${'isRegexpInt:'}${fmtlength}${'||'}" />
<c:set var="validmsg" value="${validmsg}${'输入格式不正确||'}" />	
</c:if>
<c:if test="${pointidx >= 0}">																			
<c:set var="valid" value="${valid}${'isRegexpNum:'}${fmtlength}${'-'}${fmtlength-pointidx-1}${'||'}" />
<c:set var="validmsg" value="${validmsg}${'输入格式不正确||'}" />	
</c:if>
<c:set var="valid" value="${valid}${'onlyNum||'}" />
<c:set var="validmsg" value="${validmsg}${'输入格式只能为数字||'}" />																																						
</c:if>																		
<c:if test="${fmtlength > 0}">
<c:set var="valid" value="${valid}${'betweenval:0-'}${vfmt}${'||'}" />
<c:set var="validmsg" value="${validmsg}${'输入超过限值||'}" />	
</c:if>																																			
</c:if>
<c:if test="${prop.valType =='PER'}">																
<c:if test="${fmtlength > 0}">
<c:if test="${pointidx < 0}">
<c:set var="valid" value="${valid}${'isRegexpInt:'}${fmtlength}${'||'}" />
<c:set var="validmsg" value="${validmsg}${'输入格式不正确||'}" />	
</c:if>
<c:if test="${pointidx >= 0}">																			
<c:set var="valid" value="${valid}${'isRegexpNum:'}${fmtlength}${'-'}${fmtlength-pointidx-1}${'||'}" />
<c:set var="validmsg" value="${validmsg}${'输入格式不正确||'}" />	
</c:if>
<c:set var="valid" value="${valid}${'onlyNum||'}" />
<c:set var="validmsg" value="${validmsg}${'输入格式只能为数字||'}" />																																						
</c:if>																		
<c:if test="${fmtlength > 0}">
<c:set var="valid" value="${valid}${'betweenval:0-100||'}" />
<c:set var="validmsg" value="${validmsg}${'输入超过限值||'}" />	
</c:if>																																			
</c:if>
<c:set var="valid" value="${valid}${'maxGBLength:128'}" />
<c:set var="validmsg" value="${validmsg}${'长度不能超过128'}" />


<input type="text" name="propdata" id="propdata" class="required form-control" ${extmsg} value="${pval}" data-empty="${prop.forceInput}" id="propdata" data-unit="${vunit}"  data-key="${prop.propIdx}" 
data-tip="${tipsmsg}" autocomplete="off" data-valid="${valid}" data-error="${validmsg}" />																	
<c:if test="${!empty vunit && fn:length(vunit)>0}">
<span class="unit_message">${vunit}</span>																
</c:if>																																											
</c:if>

<c:if test="${prop.consType == '01'}">
<input type="hidden" name="propdata" value="" data-key="${prop.propIdx}" data-unit="" />
<c:set var="enumvals" value="${fn:split(prop.consVal, ';')}" />
<select name="propsel" id="propsel" class="csel form-control" data-key="${prop.propIdx}"  data-empty="${prop.forceInput}" data-propname="${prop.propName}">
	<c:forEach items="${enumvals}" var="enum">																
	<c:if test="${enum == prop.propVal}">
	<option value="${enum}" selected>${enum}</option>
</c:if>																	
<c:if test="${enum != prop.propVal}">
<option value="${enum}">${enum}</option>
</c:if>																	
</c:forEach>																
</select>
<span class="valid_message"></span>																
</c:if>	

<c:if test="${prop.consType == '02'}">
<input type="hidden" name="propdata" value="" data-key="${prop.propIdx}" data-unit="" />
<select name="propsel" id="propsel" class="csel form-control" data-key="${prop.propIdx}"  data-empty="${prop.forceInput}" data-propname="${prop.propName}">
	<c:set var="val1" value="${fn:replace(prop.consVal,']','')}" />																
	<c:set var="val2" value="${fn:replace(val1,'[','')}" />																
	<c:set var="vals" value="${fn:split(val2, ',')}" />

	<c:forEach var="x" begin="${vals[0]}" end="${vals[1]}" step="1">  
	<c:if test="${x == prop.propVal}">
	<option value="${x}" selected>${x}</option>
</c:if>																	
<c:if test="${x != prop.propVal}">
<option value="${x}">${x}</option>
</c:if>																		  
</c:forEach> 																
</select>
<span class="valid_message"></span>																
</c:if>	

<c:if test="${prop.consType == '03'}">

<c:set var="keyoffset" value="${fn:indexOf(prop.consVal, ')')}" />															
<c:set var="relakey" value="${fn:substring(prop.consVal, 1, keyoffset)}" />															
<c:set var="items" value="${fn:substringAfter(prop.consVal, ')')}" />																		
<c:set var="keyname" value="" />	
<c:set var="keyval" value="" />		

<c:forEach items="${rspBody.props}" var="sprop">
<c:if test="${relakey==sprop.propIdx}">
<c:set var="keyname" value="${sprop.propName}" />	
<c:set var="keyval" value="${sprop.propVal}" />																																							
</c:if>																																
</c:forEach>																
<c:set var="prefix" value="${keyval}:" />																
<c:set var="nprefix" value="${fn:substringAfter(items, prefix)}" />																	
<c:set var="seles" value="${fn:substringBefore(nprefix, '}')}" />																												
<c:set var="enumvals" value="${fn:split(seles, ';')}" />

<input type="hidden" name="propdata" value="" data-key="${prop.propIdx}" data-unit="" />
<select name="propsel" id="propsel" class="csel form-control" data-type="${prop.consType}" data-key="${prop.propIdx}" data-relaname="${keyname}" data-relakey="${relakey}"  data-items="${items}"  data-empty="${prop.forceInput}" data-propname="${prop.propName}" >

	<c:choose>
	<c:when test="${empty seles || fn:length(seles) == 0}">																	
	<option value="" selected>商品无此属性项</option>																	
</c:when>														
<c:otherwise>													
<c:forEach items="${enumvals}" var="enum">															
<c:if test="${enum == prop.propVal}">
<option value="${enum}" selected>${enum}</option>
</c:if>																	
<c:if test="${enum != prop.propVal}">
<option value="${enum}">${enum}</option>
</c:if>		

</c:forEach>																																															
</c:otherwise>
</c:choose>																																
</select>
<span class="valid_message"></span>																
</c:if>	

</div>
<c:if test="${status.index % 2 == 1 }">


</c:if>	
</c:when>

<c:otherwise>

<c:set var="pricelimit" value="${prop.consVal}" />


</c:otherwise>	

</c:choose>															
</c:forEach>	
</div>

<c:if test="${fn:length(rspBody.props) %2 == 1}">	
<div></div>
<div></div>	
</c:if>									

</c:if>
</div>	

<div id="step3">
	<div class="form-group buy-group">
		<div class="input-group rel">
			<div class="input-group-addon">单价<span class="fcyellow">(必填)</span></div>
			<input type="text" name="unitPrice" class="required form-control" id="unitPrice" value="${rspBody.up}" data-limit="${pricelimit}" data-tip="请输入商品单价" data-valid="isNonEmpty||isNoNZeroMoney" data-error="单价必填||金额格式:1.00"/>
			<span class="priceunit_message">元/${rspBody.uom}</span>
		</div>
	</div>

	<div class="form-group buy-group">
		<div class="input-group rel">
			<div class="input-group-addon">一口价：</div>
			<div class="txtright">
				<input type="radio" name="DfpFlag" value="F" checked disabled="disabled" />是
				<input type="hidden" name="fpFlag" value="F" />
			</div>
		</div>
	</div>

	<div class="form-group buy-group">
		<div class="input-group rel">
			<div class="input-group-addon">总量<span class="fcyellow">(必填)</span></div>								<input type="text" name="qty" id="qty" value="${rspBody.qty}" class="required form-control"  data-tip="请输入商品总量" data-valid="isNonEmpty||plusInt" data-error="总量必填||总量必须为整数" />
			<span class="unit_message">${rspBody.uom}</span>
		</div>
	</div>

	<div class="form-group buy-group">
		<div class="input-group rel">
			<div class="input-group-addon">是否整单：</div>
			<div class="J_WholeFlag txtright">
				<c:choose>
				<c:when test="${rspBody.wholeFlg == 'W'}">
				<span class="ml20 mr20"><input type="radio" name="wholeFlag" id="w_flag" value="W" checked />是</span>
				<span><input type="radio" name="wholeFlag" id="s_flag" value="S" disabled="disabled" />否</span>
			</c:when>												
			<c:otherwise>
			<span class="ml20 mr20"><input type="radio" name="wholeFlag" id="w_flag" value="W" />是</span>
			<span><input type="radio" name="wholeFlag" id="s_flag" value="S" checked />否</span>
		</c:otherwise>
	</c:choose>
</div>
</div>
</div>


<div class="form-group buy-group">
	<div class="input-group rel">
		<div class="input-group-addon">起订数量<span class="fcyellow">(必填)</span></div>
		<div class="txtright">
			<input type="text" name="moq" id="moq" class="required form-control"  value="${rspBody.moq}" <c:if test="${rspBody.wholeFlg == 'W'}"> readonly="true" 
		</c:if> 
		data-tip="请输入商品起订数量" data-valid="isNonEmpty||plusInt" data-error="起订量必填||起订量必须为整数" />
		<span class="unit_message">${rspBody.uom}</span>	
	</div>	
</div>
</div>

<div class="form-group buy-group">
	<div class="input-group rel">
		<div class="input-group-addon">递增数量<span class="fcyellow">(必填)</span></div>
		<div class="txtright">
			<input type="text" name="incrNum" id="incrNum" class="required form-control" value="${rspBody.ic}"  <c:if test="${rspBody.wholeFlg == 'W'}"> readonly="true" </c:if> data-tip="请输入商品递增量" data-valid="isNonEmpty||plusInt" data-error="递增量必填||递增量必须为整数"  />
			<span class="unit_message">${rspBody.uom}</span>
		</div>
	</div>
</div>

<div class="form-group buy-group">
	<div class="input-group rel">
		<div class="input-group-addon">挂牌有效期<span class="fcyellow">(必填)</span></div>
		<div class="txtright">
			<input type="text" name="doe" id="doe" value="${rspBody.doe}"  data-min="${busDate}" 
			class="required form-control"  data-tip="请选择挂牌有效期" data-valid="isNonEmpty||isDate||after:${busDate}" data-error="挂牌有效期必填||有效期格式不正确||有效期小于业务日期${busDate}"  />
		</div>
	</div>
</div>

<div class="form-group buy-group">
	<div class="input-group rel">
		<div class="input-group-addon">卖场</div>
		<div class="txtright">
			<input type="checkbox" name="Dmart" value="O" checked disabled="disabled" >公开大厅
			<input type="hidden" name="mart" value="O" />
											<!-- 后面再做
											<span><input type="checkbox" name="mart" value="E" >专有专场</span>
										-->											
									</div>
								</div>
							</div>

							<c:if test="${rspBody.listedType=='M'}">
							
							<div class="form-group buy-group">
								<div class="input-group rel">
									<div class="input-group-addon">最后付款日<span class="fcyellow">(必填)</span></div>
									<div class="txtright">合同签定后 <input type="text" name="lastPD" id="lastPD" maxlength="4" value="${fn:substringAfter(rspBody.lastPD,'cycle:')}" style="width:40px; height:24px; padding: 5px 5px; border: 1px solid #ECECEC;"  autocomplete="off" />天
										<span class="valid_message"></span>
									</div>
								</div>
							</div>

							<div class="form-group buy-group">
								<div class="input-group rel">
									<div class="input-group-addon">最后交收日<span class="fcyellow">(必填)</span></div>
									<div class="txtright">
										全款支付后 <input type="text" name="deliDate" id="deliDate" maxlength="4" value="${fn:substringAfter(rspBody.deliDate,'cycle:')}" style="width:40px; height:24px; padding: 5px 5px; border: 1px solid #ECECEC;" autocomplete="off" />天
										<span class="valid_message"></span>
									</div>
								</div>
							</div>
						</c:if>	

						<div class="form-group buy-group">
							<div class="input-group rel">
								<div class="input-group-addon">交收仓库<span class="fcyellow">(必填)</span></div>
								<select name="storage" id="storage" >
									<c:forEach items="${storeList}" var="store" >
									<c:choose>
									<c:when test="${store.storeName == rspBody.storage}">
									<option value="${store.storeName}" selected>${store.storeName}</option>
								</c:when>
								<c:otherwise>
								<option value="${store.storeName}">${store.storeName}</option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</select>
				<span class="valid_message"></span>
			</div>
		</div>

		<div class="form-group buy-group">
			<div class="input-group rel">
				<div class="input-group-addon">平台监管发票：</div>
				<div class="txtright">
					<c:choose>
					<c:when test="${rspBody.invoice == 'Y'}">
					<span class="ml20 mr20"><input type="radio" name="invoice" value="Y" checked />需要</span>
					<span><input type="radio" name="invoice" value="N" />不需要</span>
				</c:when>
				<c:otherwise>
				<span class="ml20 mr20"><input type="radio" name="invoice" value="Y" />需要</span>
				<span><input type="radio" name="invoice" value="N" checked />不需要</span>
			</c:otherwise>
		</c:choose>											
	</div>
</div>
</div>

<div class="form-group buy-group">
	<div class="input-group rel">
		<div class="input-group-addon">是否指定摘牌方：</div>
		<div class="txtright">
			<c:set var="delistIDs" value="${''}" />										
			<c:set var="delistNames" value="${''}" />
			<c:choose>
			<c:when test="${rspBody.delist =='O'}">
			<span class="ml20 mr20"><input type="radio" name="delist" value="O" checked />不指定</span>
			<span><input type="radio" name="delist" value="A" />指定</span>
			<span id="memdelistlink"></span>
			<span id="memdelistmsg"></span>	
		</c:when>
		<c:otherwise>
		<span class="ml20 mr20"><input type="radio" name="delist" value="O" />不指定</span>
		<span><input type="radio" name="delist" value="A" checked />指定</span>
		<c:forEach items="${rspBody.delistMems}" var="dm" >	
		<c:set var="delistIDs" value="${delistIDs}${dm.delistMID}${';'}" />	
		<c:set var="delistNames" value="${delistNames}${dm.delistMemName}${';'}" />
	</c:forEach>
</c:otherwise>
</c:choose>
<input type="hidden" name="markcode" id="markcode" value="${rspBody.markCode}" />
<input type="hidden" name="memdelists" id="memdelists" value="${delistIDs}" data-key="${delistNames}" />
</div>
</div> 
</div>

<div class="form-group buy-group memdelist-select" id="memdelistlink" >
	<div class="input-group rel">
		<span class="input-group-addon"><span>选择会员列表</span></span>
		<div class="txtright">
			<span id="memdelistmsg">共选择${fn:length(rspBody.delistMems)}家会员做的指定摘牌方</span>
			<span class=" glyphicon glyphicon-menu-right account-a"></span>
		</div>
	</div>
</div>
</div>

<div class="form-group buy-group bgfff" style="margin-bottom:0;">
	<div class="input-group rel">
		<div class="input-group-addon"><span >商品标题</span></div>
		<div class="txtright">
			<input type="text" name="title" class="required form-control txtright" value="${rspBody.title}"  data-tip="请输入描述标题" data-valid="maxGBLength:128" data-error="长度不超过128"  />
		</div>                                                           
	</div>
</div>

<div class="row">
	<div  id="step4">
		<div class="col-xs-12 bgfff">
			<div  class="lh46 fc999 ">商品描述</div>
		</div>
		<div class="col-xs-12 rel bgfff">
			<div class="proddetail" style="display:none">${rspBody.detail}</div>
			<textarea name="detail" id="detail" cols="45" rows="6" class="form-control marb10"></textarea>
			<div class="detailmsg" style="color: #f00;"></div>
		</div>

		<div class="clearfix col-xs-12" style="margin-top:5px">
			<c:if test="${fn:length(rspBody.titlePic)> 0 }">
			<img id="titfilePre" width="120px" height="120px" src="${rspBody.titlePic}" style="display:;float:left"  />
			<div class="btn-upload_unselected fileinput">
				<span>替换标题图片</span>
				<input type="file" name="titfile" id="titfile" accept="image/jpg,image/jpeg,image/png,image/bmp,image/gif" />
			</div>
		</c:if>
		<c:if test="${fn:length(rspBody.titlePic)== 0 }">
		<img id="titfilePre" width="120px" height="120px"  style="display:none; float:left"  />
		<div class="btn-upload_unselected fileinput">
			<span>选择标题图片</span>
			<input type="file" name="titfile" id="titfile" accept="image/jpg,image/jpeg,image/png,image/bmp,image/gif" />
		</div>
	</c:if>	                                                     
</div>
<div class="clearfix col-xs-12">
	<input type="hidden" name="ctxchg" id="ctxPic0Chg" />
	<c:if test="${fn:length(rspBody.ctxPic1)> 0 }">
	<img id="ctxPic0Pre" width="120px" height="120px" src="${rspBody.ctxPic1}" style="display:;float:left"  />
	<div class="btn-upload_unselected fileinput">
		<span>替换商品描述首图</span>
		<input type="file"  name="ctxfile" id="ctxPic0" accept="image/jpg,image/jpeg,image/png,image/bmp,image/gif" />
	</div>
</c:if>
<c:if test="${fn:length(rspBody.ctxPic1)== 0 }">
<img id="ctxPic0Pre" width="120px" height="120px"  style="display:none; float:left"  />
<div class="btn-upload_unselected fileinput">
	<span>选择商品描述首图</span>
	<input type="file"  name="ctxfile" id="ctxPic0" accept="image/jpg,image/jpeg,image/png,image/bmp,image/gif" />
</div>
</c:if>
</div>
<div class="clearfix col-xs-12">
	<input type="hidden" name="ctxchg" id="ctxPic1Chg" /> 
	<c:if test="${fn:length(rspBody.ctxPic2)> 0 }">
	<img id="ctxPic1Pre" width="120px" height="120px" src="${rspBody.ctxPic2}" style="display:;float:left"  />
	<div class="btn-upload_unselected fileinput">
		<span>替换商品描述次图</span>
		<input type="file"  name="ctxfile" id="ctxPic1" accept="image/jpg,image/jpeg,image/png,image/bmp,image/gif" />
	</div>
</c:if>
<c:if test="${fn:length(rspBody.ctxPic2)== 0 }">
<img id="ctxPic1Pre" width="120px" height="120px"  style="display:none; float:left"  />
<div class="btn-upload_unselected fileinput">
	<span>选择商品描述次图</span>
	<input type="file"  name="ctxfile" id="ctxPic1" accept="image/jpg,image/jpeg,image/png,image/bmp,image/gif" />
</div>
</c:if>
</div>
<div class="clearfix col-xs-12">
	<input type="hidden" name="ctxchg" id="ctxPic2Chg" /> 
	<c:if test="${fn:length(rspBody.ctxPic3)> 0 }">
	<img id="ctxPic2Pre" width="120px" height="120px" src="${rspBody.ctxPic3}" style="display:;float:left"  />
	<div class="btn-upload_unselected fileinput">
		<span>替换商品描述尾图</span>
		<input type="file"  name="ctxfile" id="ctxPic2" accept="image/jpg,image/jpeg,image/png,image/bmp,image/gif" />
	</div>
</c:if>
<c:if test="${fn:length(rspBody.ctxPic3)== 0 }">
<img id="ctxPic2Pre" width="120px" height="120px"  style="display:none; float:left"  />
<div class="btn-upload_unselected fileinput">
	<span>选择商品描述尾图</span>
	<input type="file"  name="ctxfile" id="ctxPic2" accept="image/jpg,image/jpeg,image/png,image/bmp,image/gif" />
</div>
</c:if>
</div>
<div class="col-xs-12 mart15 marb60">
	<button class="btn btn-warning btn-sell btn-block">我要挂牌</button>
</div>
</div>	
</div>	

</form>

</div>

<!-- footer -->
<jsp:include page="../comm/footer.jsp" flush="true" />
<!-- footer End -->

<!-- 弹出层 -->	
<div class="updialog w850" id="J_MemList">
	<div class="hd">
		<span class="close ic"></span>
		<h3>指定摘牌会员选择</h3>
	</div>
	<div class="memselect">
		<div class="manage">				
			<input type="text" placeholder="请选择行政区域" name="divisID" data-key="0086"  data-idx="0" data-full="中国" id="divisID" class="inp-search"/>		
			<div class="localcity"></div>
			<div class="unselect">
				<select name="seletlist" size="20" multiple id="selectlist" style="display:none;">
				</select>				
			</div>
			<ul id="selc-ul"></ul>
			<div class="selbtn">
				<div class="row">
					<div class="col-xs-6 up-sell-btn">
						<div class="pa-btn-sell btn-all-select ">
							<button class="btn-normal btn-sell" id="select-all">全选</button>/
						</div>
						<div class="pa-btn-sell btn-all-unselect ">
							<button class="btn-normal btn-sell" id="select-none">取消全选</button>
						</div>
					</div>
					<div class="col-xs-6 up-sell-btn">
						<span class="fcgreen btn-close" id="select-confirm">确定</span>
					</div>
				</div>
			</div>										
		</div>
</div>
<div style="float:right;display:none;">
	<button class="cbtn" id="confirmbtn">确&nbsp;&nbsp;认</button>	
	<button class="cbtn" id="cancelbtn">取&nbsp;&nbsp;消</button>						
</div>


</div>
</body>
</html>