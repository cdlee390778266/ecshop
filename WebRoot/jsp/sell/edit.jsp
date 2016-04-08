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
    <link rel="shortcut icon" href="/images/icon/favicon.ico" />
    <link type="text/css" rel="stylesheet" href="/css/global.css" />
    <link type="text/css" rel="stylesheet" href="/css/font.css" />
    <link type="text/css" rel="stylesheet" href="/css/common.css" />
    <link type="text/css" rel="stylesheet" href="/css/member.css" />
    <link type="text/css" rel="stylesheet" href="/widget/css/ui.dialog.css" />
    <link type="text/css" rel="stylesheet" href="/widget/css/ui.datepicker.css" />
    <link type="text/css" rel="stylesheet" href="/css/commsel.css" />
    <link type="text/css" rel="stylesheet" href="/css/square/green.css">
    <link type="text/css" rel="stylesheet" href="/css/validate.css" >
    <link type="text/css" rel="stylesheet" href="/css/localcity.css" >
    <link type="text/css" rel="stylesheet" href="/css/combo.select.css" >
    
    <script type="text/javascript">
    	var listedType = '${rspBody.listedType}';
    </script>
    <script type="text/javascript" src="/js/jquery.js"></script>
    <script type="text/javascript" src="/widget/js/ui.dialog.js"></script>
    <script type="text/javascript" src="/widget/js/ui.datepicker.js"></script>
    <script type="text/javascript" src="/js/jquery.combo.select.js"></script>
	<script type="text/javascript" src="/js/icheck.js"></script>
	<script type="text/javascript" src="/js/localcity.js"></script>
    <script type="text/javascript" src="/js/jquery-validate.js"></script>
    <script type="text/javascript" src="/js/stickup.js"></script>
    <script type="text/javascript" src="/widget/laydate/laydate.js"></script> 
    <script type="text/javascript" src="/js/handle.sell.edit.js?v=${sessionScope.buildno}"></script>
    <title>卖方挂牌</title>   
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
	<div class="wrapper service-full mt30">
		<div class="grid-16-16">
			<div class="crumb-nav">
				<div class="backto">
					<div class="backrt">
						<a href="/home.htm">返回首页<i></i></a>
					</div>
				</div>
				<div class="crumb">
					<a href="/home.htm">交易大厅</a><span class="fa  fa-angle-right"></span><a href="/sell/apply.htm?active=enter">卖方挂牌</a>
				</div>
			</div>
			
			<!-- main -->
			<div class="page">
				<div class="user-mans">
					<div class="portrait-big">
						<c:if test="${empty sessionScope.userinfo.operPhoto}">
							<img  width="200" height="161" alt="" src="/images/portrait.jpg" />
						</c:if>
						<c:if test="${!empty sessionScope.userinfo.operPhoto}">
							<img  width="200" height="161" alt="" src="${sessionScope.userinfo.operPhoto}"/>
						</c:if>
					</div>
					
					<div class="ucenter">
						<span>买卖菜单</span>
					</div>
					<div class="user-navs members">
						<ul>
							<li class="current"><a href="/sell/apply.htm?active=enter" class="mlnks">卖方挂牌</a></li>							
						</ul>
					</div>
				</div>
				
				<div class="main-content">
					<div class="bd">
						<form action="/sell/edithandle.htm" method="post" enctype="multipart/form-data" id="sellEdit">

						<input type="hidden" name="busDate" id="busDate" value="${busDate}" />
						
						<div class="page-module warehouse">
							<div class="row">
								<div class="hd">
									<h3 class="cor-green">卖方挂牌修改</h3>
								</div>
								<div class="bd">
									<table class="ui-table">
									<tbody>
										<tr>
										
										<td class="ctr" width="125px">挂牌单号：</td>
											<td colspan="3">																																				
												 ${rspBody.listedNo}
												 <input type="hidden" name="listedNo" value="${rspBody.listedNo}" />
											</td>
										</tr>
										<tr>
											<td class="ctr">挂牌方式：</td>
											<td colspan="3">
												<c:if test="${rspBody.listedType=='M'}">
													<span class="ml20"><input type="radio" name="DlistedType" value="M" checked disabled="disabled" />保证金</span>	
												  	<input type="hidden" name="listedType" value="M" />		
												</c:if>
												<c:if test="${rspBody.listedType=='W'}">
													<span class="ml20"><input type="radio" name="DlistedType" value="W" checked disabled="disabled" />注册仓单</span>	
												  	<input type="hidden" name="listedType" value="W" />		
												</c:if>																																				 
											</td>
										</tr>
										
											
										
										<tr>
											<td class="ctr">挂牌商品：</td>
											<td colspan="3">													
												<input type="hidden" name="commCode" id="commCode" value="${rspBody.commCode}" />												
													${rspBody.markName}&gt;${rspBody.className}&gt;${rspBody.commName}												
											</td>
										</tr>										
										<c:set var="pricelimit" value="" />
										<c:if test="${fn:length(rspBody.props) > 0}">
											<tr>
												<c:forEach items="${rspBody.props}" var="prop" varStatus="status">
												
													<c:choose>
													<c:when test="${prop.consType != '81'}">
														
														<td class="ctr" width="125px" >														
															<c:if test="${prop.forceInput =='1'}">
																${prop.propName}<span class="forceinput">(必填)</span>：
															</c:if>
															<c:if test="${prop.forceInput !='1'}">
																${prop.propName}：
															</c:if>
														</td>
														<td>														
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

																<div class="form_control">
																<input type="text" name="propdata" id="propdata" class="required" ${extmsg} value="${pval}" data-empty="${prop.forceInput}" id="propdata" data-unit="${vunit}"  data-key="${prop.propIdx}" 
																data-tip="${tipsmsg}" autocomplete="off" data-valid="${valid}" data-error="${validmsg}" />																	
																	<c:if test="${!empty vunit && fn:length(vunit)>0}">
																		<span class="unit_message">${vunit}</span>																
																	</c:if>																																
																</div>																
															</c:if>
															
															<c:if test="${prop.consType == '01'}">
																<input type="hidden" name="propdata" value="" data-key="${prop.propIdx}" data-unit="" />
																<c:set var="enumvals" value="${fn:split(prop.consVal, ';')}" />
																<select name="propsel" id="propsel" class="csel" data-key="${prop.propIdx}"  data-empty="${prop.forceInput}" data-propname="${prop.propName}">
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
																<select name="propsel" id="propsel" class="csel" data-key="${prop.propIdx}"  data-empty="${prop.forceInput}" data-propname="${prop.propName}">
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
																<select name="propsel" id="propsel" class="csel" data-type="${prop.consType}" data-key="${prop.propIdx}" data-relaname="${keyname}" data-relakey="${relakey}"  data-items="${items}"  data-empty="${prop.forceInput}" data-propname="${prop.propName}" >
																		
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
																																									
														</td>
														<c:if test="${status.index % 2 == 1 }">
															</tr>
															<tr>
														
														</c:if>	
														</c:when>
														
														<c:otherwise>
														
															<c:set var="pricelimit" value="${prop.consVal}" />
														
														
														</c:otherwise>	
														
													</c:choose>															
												</c:forEach>	
												
												<c:if test="${fn:length(rspBody.props) %2 == 1}">	
													<td></td>
													<td></td>	
												</c:if>									
											</tr>										
										</c:if>

										
										<tr>
											<td class="ctr">单价<span class="forceinput">(必填)</span>：</td>
											<td width="285px">
											<div class="form_control">
												<input type="text" name="unitPrice" class="required" id="unitPrice" value="${rspBody.up}" data-limit="${pricelimit}" data-tip="请输入商品单价" data-valid="isNonEmpty||isNoNZeroMoney" data-error="单价必填||金额格式:1.00"/>
												<span class="priceunit_message">${rspBody.uom}/元</span>
											</div>
											</td>
											
											<td class="ctr" width="115px">一口价：</td>
											<td>
											
												<span class="ml20 mr20"><input type="radio" name="DfpFlag" value="F" checked disabled="disabled" />是</span>
												
												<input type="hidden" name="fpFlag" value="F" />
											</td>
										</tr>
										
										<tr>
										
											<td class="ctr">总量<span class="forceinput">(必填)</span>：</td>
											<td>											
												<div class="form_control">
													<input type="text" name="qty" id="qty" value="${rspBody.qty}"  class="required"  data-tip="请输入商品总量" data-valid="isNonEmpty||plusInt" data-error="总量必填||总量必须为整数" />
													<span class="unit_message">${rspBody.uom}</span>
												</div>	
											</td>
											
											<td class="ctr">是否整单：</td>
											<td>
											<div class="J_WholeFlag">
											<c:choose>
												<c:when test="${rspBody.wholeFlg == 'W'}">
													<span class="ml20 mr20"><input type="radio" name="wholeFlag" id="w_flag" value="W" checked  />是</span>
													<span><input type="radio" name="wholeFlag" id="s_flag" value="S" />否</span>
												</c:when>												
												<c:otherwise>
													<span class="ml20 mr20"><input type="radio" name="wholeFlag" id="w_flag" value="W"   />是</span>
													<span><input type="radio" name="wholeFlag" id="s_flag" value="S" checked />否</span>
												</c:otherwise>
											</c:choose>
											</div>
											
											</td>
										</tr>
										<c:if test="${rspBody.listedType=='M'}">
                                        <tr>
                                        	
											<td class="ctr">起订数量<span class="forceinput">(必填)</span>：</td>
											<td width="285px">
												<div class="form_control">
													<input type="text" name="moq" id="moq" class="required"  value="${rspBody.moq}" <c:if test="${rspBody.wholeFlg == 'W'}"> readonly="true" </c:if> data-tip="请输入商品起订数量" data-valid="isNonEmpty||plusInt" data-error="起订量必填||起订量必须为整数" />
													<span class="unit_message">${rspBody.uom}</span>
												</div>	
											</td>								
											<td class="ctr">递增数量<span class="forceinput">(必填)</span>：</td>
											<td>
												<div class="form_control">
													<input type="text" name="incrNum" id="incrNum" class="required" value="${rspBody.ic}"  <c:if test="${rspBody.wholeFlg == 'W'}"> readonly="true" </c:if> data-tip="请输入商品递增量" data-valid="isNonEmpty||plusInt" data-error="递增量必填||递增量必须为整数"  />
													<span class="unit_message">${rspBody.uom}</span>
												</div>
											</td>
									
										</tr>
										</c:if>
                                        <tr>
											<td class="ctr">挂牌有效期<span class="forceinput">(必填)</span>：</td>
											<td>
												<div class="form_control">
												
													<!-- 
													<input type="text" name="doe" id="doe" value="${rspBody.doe}"  datepicker data-date-format="yyyy-mm-dd" data-auto-close="true" class="required"  data-tip="请选择挂牌有效期" data-valid="isNonEmpty||isDate||after:${busDate}" data-error="挂牌有效期必填||有效期格式不正确||有效期小于业务日期${busDate}"  />
													 -->
													 <input type="text" name="doe" id="doe" value="${rspBody.doe}"  data-min="${busDate}" 
													  class="required"  data-tip="请选择挂牌有效期" data-valid="isNonEmpty||isDate||after:${busDate}" data-error="挂牌有效期必填||有效期格式不正确||有效期小于业务日期${busDate}"  />
													
												
												</div>	
											</td>
											<td class="ctr">卖场：</td>
											<td>
											
											<span class="ml20">
											<input type="checkbox" name="Dmart" value="O" checked disabled="disabled" >公开大厅</span>
											<input type="hidden" name="mart" value="O" />
											<!-- 后面再做
											<span><input type="checkbox" name="mart" value="E" >专有专场</span>
											 -->											
											</td>
											
										</tr>
										<c:if test="${rspBody.listedType=='M'}">
										<tr>
									 		<td class="ctr">最后付款日<span class="forceinput">(必填)</span>：</td>
											<td>合同签定后 <input type="text" name="lastPD" id="lastPD" maxlength="4" value="${fn:substringAfter(rspBody.lastPD,'cycle:')}" style="width:40px; height:24px; padding: 5px 5px; border: 1px solid #ECECEC;"  autocomplete="off" />天
											<span class="valid_message"></span>
											</td>
											<td class="ctr">最后交收日<span class="forceinput">(必填)</span>：</td>
											<td>
												全款支付后 <input type="text" name="deliDate" id="deliDate" maxlength="4" value="${fn:substringAfter(rspBody.deliDate,'cycle:')}" style="width:40px; height:24px; padding: 5px 5px; border: 1px solid #ECECEC;" autocomplete="off" />天
												<span class="valid_message"></span>
											</td>
										</tr>
										</c:if>		
									 	                                                             
			                           <tr>
											<td class="ctr">交收仓库<span class="forceinput">(必填)</span>：</td>
											<td>
												<select name="storage" id="storage" style="width:230px;margin-left:10px">
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
											</td>
											<td class="ctr">平台监管发票：</td>
											<td>
											<c:choose>
												<c:when test="${rspBody.invoice == 'Y'}">
													<span class="ml20 mr20"><input type="radio" name="invoice" value="Y" checked />需要</span>
													<span><input type="radio" name="invoice" value="N" />不需要</span>
												</c:when>
												<c:otherwise>
													<span class="ml20 mr20"><input type="radio" name="invoice" value="Y"  />需要</span>
													<span><input type="radio" name="invoice" value="N" checked />不需要</span>
												</c:otherwise>
											</c:choose>											
											</td>
										</tr>  
										
										
										<tr>
											<td class="ctr">是否指定摘牌方：</td>
											<td colspan="3">
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
													<span id="memdelistlink" class="memdelist-select">选择会员列表</span>
													<span id="memdelistmsg">共选择${fn:length(rspBody.delistMems)}家会员做的指定摘牌方</span>													
													<c:forEach items="${rspBody.delistMems}" var="dm" >	
														<c:set var="delistIDs" value="${delistIDs}${dm.delistMID}${';'}" />	
														<c:set var="delistNames" value="${delistNames}${dm.delistMemName}${';'}" />
													</c:forEach>
													
													
												</c:otherwise>
											</c:choose>
												
											<input type="hidden" name="markcode" id="markcode" value="${rspBody.markCode}" />
											
															
											<input type="hidden" name="memdelists" id="memdelists" value="${delistIDs}" data-key="${delistNames}" />
											</td>
										</tr> 
                                        
                                        <tr>
                                            <td class="ctr">商品标题：</td>
                                            <td>
                                            <div class="form_control">
                                            	<input type="text" name="title" class="required" value="${rspBody.title}"  data-tip="请输入描述标题" data-valid="maxGBLength:128" data-error="长度不超过128"  />
                                            </div>
                                            </td>  
                                            
                                             <td  class="ctr">商品描述：</td>
                                            <td >   
                                            	<div class="proddetail" style="display:none">${rspBody.detail}</div>                                        
                                            	<textarea name="detail" id="detail" cols="45" rows="6"></textarea>
                                            	<div class="detailmsg" style="color: #f00;"></div>
                                             </td>                                                                   
                                        </tr>
                                          <tr>
                                          <td class="ctr"></td>
                                            <td colspan="3">	
                                            <table>
                                            <tr>
                                            	<td width="25%">											 
													<div class="clearfix">
														<c:if test="${fn:length(rspBody.titlePic)> 0 }">
															<img id="titfilePre" width="120px" height="120px" src="${rspBody.titlePic}" style="display:;float:left"  />
															<span class="btn-upload_unselected fileinput ml10">
	                                                      	<span>替换标题图片</span>
	                                                       	<input type="file" name="titfile" id="titfile" accept="image/jpg,image/jpeg,image/png,image/bmp,image/gif" />
	                                                     	</span>
														</c:if>
	                                                   <c:if test="${fn:length(rspBody.titlePic)== 0 }">
															<img id="titfilePre" width="120px" height="120px"  style="display:none; float:left"  />
															<span class="btn-upload_unselected fileinput ml10">
	                                                      	<span>选择标题图片</span>
	                                                       	<input type="file" name="titfile" id="titfile" accept="image/jpg,image/jpeg,image/png,image/bmp,image/gif" />
	                                                     	</span>
													  </c:if>	                                                     
	                                                </div>
                                                </td>
                                                
                                                <td width="25%">
		                                            <div class="clearfix">
		                                            		<input type="hidden" name="ctxchg" id="ctxPic0Chg" />                                                                                        
		                                            		<c:if test="${fn:length(rspBody.ctxPic1)> 0 }">
																<img id="ctxPic0Pre" width="120px" height="120px" src="${rspBody.ctxPic1}" style="display:;float:left"  />
																<span class="btn-upload_unselected fileinput ml10">
		                                                     <span>替换商品描述首图</span>
		                                                     <input type="file"  name="ctxfile" id="ctxPic0" accept="image/jpg,image/jpeg,image/png,image/bmp,image/gif" />
		                                               		 </span>
															</c:if>
		                                                   <c:if test="${fn:length(rspBody.ctxPic1)== 0 }">
																<img id="ctxPic0Pre" width="120px" height="120px"  style="display:none; float:left"  />
																<span class="btn-upload_unselected fileinput ml10">
		                                                     <span>选择商品描述首图</span>
		                                                     <input type="file"  name="ctxfile" id="ctxPic0" accept="image/jpg,image/jpeg,image/png,image/bmp,image/gif" />
		                                               		 </span>
														  </c:if>
		                                            </div>
		                                        </td>
		                                        <td width="25%">
			                                         <div class="clearfix">
			                                                   <input type="hidden" name="ctxchg" id="ctxPic1Chg" /> 
			                                                   <c:if test="${fn:length(rspBody.ctxPic2)> 0 }">
																	<img id="ctxPic1Pre" width="120px" height="120px" src="${rspBody.ctxPic2}" style="display:;float:left"  />
																	<span class="btn-upload_unselected fileinput ml10">
			                                                     <span>替换商品描述次图</span>
			                                                     <input type="file"  name="ctxfile" id="ctxPic1" accept="image/jpg,image/jpeg,image/png,image/bmp,image/gif" />
			                                               		 </span>
																</c:if>
			                                                   <c:if test="${fn:length(rspBody.ctxPic2)== 0 }">
																	<img id="ctxPic1Pre" width="120px" height="120px"  style="display:none; float:left"  />
																	<span class="btn-upload_unselected fileinput ml10">
			                                                     <span>选择商品描述次图</span>
			                                                     <input type="file"  name="ctxfile" id="ctxPic1" accept="image/jpg,image/jpeg,image/png,image/bmp,image/gif" />
			                                               		 </span>
															  </c:if>
			                                             </div>
			                                       </td>
			                                       <td>
			                                            <div class="clearfix">
			                                            	  <input type="hidden" name="ctxchg" id="ctxPic2Chg" /> 
			                                                  <c:if test="${fn:length(rspBody.ctxPic3)> 0 }">
																	<img id="ctxPic2Pre" width="120px" height="120px" src="${rspBody.ctxPic3}" style="display:;float:left"  />
																	<span class="btn-upload_unselected fileinput ml10">
			                                                     <span>替换商品描述尾图</span>
			                                                     <input type="file"  name="ctxfile" id="ctxPic2" accept="image/jpg,image/jpeg,image/png,image/bmp,image/gif" />
			                                               		 </span>
																</c:if>
			                                                   <c:if test="${fn:length(rspBody.ctxPic3)== 0 }">
																	<img id="ctxPic2Pre" width="120px" height="120px"  style="display:none; float:left"  />
																	<span class="btn-upload_unselected fileinput ml10">
			                                                     <span>选择商品描述尾图</span>
			                                                     <input type="file"  name="ctxfile" id="ctxPic2" accept="image/jpg,image/jpeg,image/png,image/bmp,image/gif" />
			                                               		 </span>
															  </c:if>
			                                              </div>
                                            		</td>
                                                </tr>
                                                </table>
                                            </td>
                                        </tr>                                                 
									</tbody>
								</table>

								<div class="pa-action clearfix mt10">
									<div class="pa-btn-sell fn-fl">									
										<button class="btn-normal btn-sell" style="margin-left: 200px;">修改挂牌</button>												
									</div>
								<div>
								</div>
							</div>
						</div>
						</div>
						</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- wrapper End -->		

	<!-- footer -->
	<jsp:include page="../comm/footer.jsp" flush="true" />
	<!-- footer End -->
	
	<!-- 弹出层 -->	
	<div class="updialog w850" id="J_MemList">
		<div class="hd">
			<span class="close ic"></span>
			<h3>指定摘牌会员选择</h3>
		</div>
		<div class="bd">
			<div class="d-content">
				<div>
					<div class="memselect">
							
						<div class="unselect">
								<h3>会员列表</h3>
								<select name="seletlist" size="20" multiple id="selectlist">
			
								</select>				
						</div>
						
						<div class="manage mr10 ml10">				
								<input type="text" placeholder="请选择行政区域" name="divisID" data-key="0086"  data-idx="0" data-full="中国" id="divisID" class="inp-search"/>		
								<div class="localcity"></div>																
								<div class="selbtn">
									<ul>
										<li>
											<div class="pa-btn-sell btn-single-select">								
												<button class="btn-normal btn-sell">单个&gt;&nbsp;&nbsp;</button>												
											</div>
										</li>									
										<li>
											<div class="pa-btn-sell btn-all-select">								
												<button class="btn-normal btn-sell">全部&gt;&gt;</button>												
											</div>
										</li>									
									</ul>
									<ul>
										<li>
									
											<div class="pa-btn-sell btn-all-unselect">									
												<button class="btn-normal btn-sell" >&lt;&lt;全部</button>												
											</div>
										</li>
										
										<li>
										
											<div class="pa-btn-sell btn-single-unselect">									
												<button class="btn-normal btn-sell">&nbsp;&nbsp;&lt;单个</button>												
											</div>
										</li>
									</ul>
								</div>										
						</div>
							
						<div class="selected">
								<h3>已选择会员列表</h3>
								<select name="seletedlist" size="20" multiple id="selectedlist">		
									<c:if test="${rspBody.delist =='A'}">
										<c:forEach items="${rspBody.delistMems}" var="dm" >	
											<option value="${dm.delistMID}">${dm.delistMemName}</option>
										</c:forEach>					
									</c:if>
								</select>						
						</div>
					</div>
					<div style="float:right">
						
						<button class="cbtn" id="confirmbtn">确&nbsp;&nbsp;认</button>	
						<button class="cbtn" id="cancelbtn">取&nbsp;&nbsp;消</button>						
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>