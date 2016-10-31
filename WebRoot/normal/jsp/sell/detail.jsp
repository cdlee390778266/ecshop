<%@ page pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix='fmt'%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="" />
<meta name="description" content="" />
<base href="" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="author" content="" />
<link rel="shortcut icon" href="/normal/images/icon/favicon.ico" />
<link type="text/css" rel="stylesheet" href="/normal/css/global.css" />
<link type="text/css" rel="stylesheet" href="/normal/css/font.css" />
<link type="text/css" rel="stylesheet" href="/normal/css/common.css" />
<link type="text/css" rel="stylesheet" href="/normal/css/member.css" />
<link type="text/css" rel="stylesheet" href="/normal/css/validate.css" />
<link type="text/css" rel="stylesheet"
	href="/normal/css/square/green.css">
<link type="text/css" rel="stylesheet"
	href="/normal/widget/css/ui.dialog.css" />
<script type="text/javascript" src="/normal/js/jquery.js"></script>
<script type="text/javascript" src="/normal/js/sha.js"></script>
<script type="text/javascript" src="/normal/js/icheck.js"></script>
<script type="text/javascript" src="/normal/js/jquery-validate.js"></script>
<script type="text/javascript" src="/normal/widget/js/ui.dialog.js"></script>
<script type="text/javascript" src="/normal/js/stickup.js"></script>
<title>挂牌处理</title>


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
				<div class="crumb">
					<a href="/home.htm">交易大厅</a><span class="fa  fa-angle-right"></span><a
						href="/mall/list.htm">我的挂牌</a><span class="fa  fa-angle-right"></span><a
						href="javascript: void(0)">卖方挂牌</a>
				</div>
			</div>

			<!-- main -->
			<div class="page">

				<div class="main-content">
					<div class="bd">
						<div class="page-module warehouse">
							<div class="row">
								<div class="bd">


									<form action="/sell/${rspBody.listedType}/${link}.htm"
										method="post" id="sellform">

										<input type="hidden" name="listedNo"
											value="${rspBody.listedNo}" /> <input type="hidden"
											name="opertype" value="${operType}" />


										<table class="ui-table">
											<tbody>

												<tr>
													<td class="ctr" width="130">挂牌编号：</td>
													<td>${rspBody.listedNo}</td>
												</tr>

												<tr>
													<td class="ctr" width="130">挂牌商品：</td>
													<td>${rspBody.className}&gt;${rspBody.commName}<span
														class="fcyellow">(${rspBody.title}) </span></td>
												</tr>

												<c:if test="${fn:length(rspBody.titlePic)>0}">
													<tr>
														<td class="ctr">描述图片：</td>
														<td><img id="imgTitlePre" src="${rspBody.titlePic}"
															width="120px" height="120px" style="display: block;" /></td>
													</tr>
												</c:if>

												<c:if test="${fn:length(rspBody.props)>0}">
													<c:forEach items="${rspBody.props}" var="prop">
														<tr>
															<td class="ctr">${prop.propName}：</td>
															<td>${prop.propVal}</td>
														</tr>
													</c:forEach>
												</c:if>

												<tr>
													<td class="ctr">挂牌方式：</td>
													<td><c:choose>
															<c:when test="${rspBody.listedType=='M'}">
																<span class="mr10">保证金</span>
															</c:when>
															<c:otherwise>
																<span class="mr10">仓单</span>
															</c:otherwise>
														</c:choose></td>
												</tr>
												<tr>
													<td class="ctr">单价：</td>
													<td><span class="price g_price g_price-highlight">
															<span> <fmt:formatNumber value="${rspBody.up}"
																	type="currency" pattern="￥0.00元" />/${rspBody.uom}
														</span>
													</span> <span class="ml20 fcyellow"> <c:choose>
																<c:when test="${rspBody.fpFlg=='F'}">  
													(商品不可议价)
											    </c:when>
																<c:otherwise> 
											   		(商品可议价)
											    </c:otherwise>
															</c:choose>
													</span></td>
												</tr>

												<tr>

													<td class="ctr">总量：</td>
													<td>${rspBody.qty}${rspBody.uom}<span
														class="ml20 fcyellow"> <c:choose>
																<c:when test="${rspBody.wholeFlg!='W'}">  
														(商品可拆单交易&nbsp;&nbsp;&nbsp;&nbsp;起订数量：${rspBody.moq}${rspBody.uom}&nbsp;&nbsp;&nbsp;&nbsp; 递增数量：${rspBody.ic}${rspBody.uom})
												    </c:when>
																<c:otherwise> 
												   		(商品不可拆单交易)
												    </c:otherwise>
															</c:choose>
													</span>
													</td>

												</tr>

												<tr>
													<td class="ctr">挂牌有效期：</td>
													<td>${rspBody.doe}</td>
												</tr>
												<c:if test="${rspBody.listedType=='M'}">
													<tr>
														<td class="ctr">最后付款日：</td>
														<td>合同签下后${fn:substringAfter(rspBody.lastPD,"cycle:")}天</td>
													</tr>

													<tr>
														<td class="ctr">最后交收日：</td>
														<td>全款支付后${fn:substringAfter(rspBody.deliDate,"cycle:")}天</td>
													</tr>
												</c:if>


												<tr>
													<td class="ctr">交收仓库：</td>
													<td>${rspBody.storage}</td>
												</tr>
												<tr>
													<td class="ctr">卖场：</td>
													<td><c:if test="${rspBody.mart=='O'}">       
                                                      		公开大厅  
			                           			</c:if> <c:if test="${rspBody.mart=='E'}">       
                                                      		专有专场  
			                           			</c:if> <c:if test="${rspBody.mart=='B'}">       
                                                      		专有专场 / 公开大厅
			                           			</c:if> &nbsp;&nbsp;&nbsp;&nbsp; <c:choose>
															<c:when test="${rspBody.invoice=='Y'}">
																<span class="fcyellow">(本商品交易平台监管发票)</span>
															</c:when>
															<c:otherwise>
																<span class="fcyellow">(本商品交易平台不负责监管发票)</span>
															</c:otherwise>
														</c:choose></td>
												</tr>
												<tr>
													<td class="ctr">指定摘牌方：</td>
													<td><c:choose>
															<c:when test="${rspBody.delist=='A'}">  
										   					共选择${fn:length(rspBody.delistMems)}家会员做的指定摘牌方  (会员列表)<br />
																<div
																	style="width: 700px; height: 120px; overflow: auto; border: 1px solid #89C975;">
																	<ul>
																		<c:forEach items="${rspBody.delistMems}" var="dm">
																			<li
																				style="float: left; width: 330px; text-align: left; overflow: hidden; margin: 0 5px; padding: 2px 0; text-overflow: ellipsis;">${dm.delistMemName}</li>
																		</c:forEach>
																	</ul>
																</div>
															</c:when>
															<c:otherwise>
										   					不指定  
										   				</c:otherwise>
														</c:choose></td>
												</tr>

												<tr>
													<td class="ctr">商品描述：</td>
													<td>${rspBody.detail}</td>
												</tr>

												<tr>
													<td class="ctr">描述图:</td>
													<td>
														<div class="clearfix ml20">
															<c:if test="${fn:length(rspBody.ctxPic1)>0}">
																<img id="imgCtxPic1Pre" src="${rspBody.ctxPic1}"
																	style="display: block; float: left" class="ml10"
																	width="200px" height="200px" />
															</c:if>
															<c:if test="${fn:length(rspBody.ctxPic2)>0}">
																<img id="imgCtxPic1Pre" src="${rspBody.ctxPic2}"
																	style="display: block; float: left" class="ml10"
																	width="200px" height="200px" />
															</c:if>
															<c:if test="${fn:length(rspBody.ctxPic3)>0}">
																<img id="imgCtxPic1Pre" src="${rspBody.ctxPic3}"
																	style="display: block; float: left" class="ml10"
																	width="200px" height="200px" />
															</c:if>
														</div>
													</td>
												</tr>
												<c:if test="${rspBody.listedType=='M'}">
													<tr>
														<td class="ctr">费用明细：</td>
														<td>
															<div class="box">
																<table class="ui-table table-primary">
																	<caption>本次挂牌费用说明</caption>
																	<thead>
																		<tr>
																			<td>费用名称</td>
																			<td>交易类型</td>
																			<td>费用金额</td>
																			<td>当前状态</td>
																		</tr>
																	</thead>
																	<tbody>
																		<c:forEach items="${rspBody.costPays}" var="cost">
																			<tr>
																				<td>${cost.costName}</td>
																				<td>${cost.trTypeName}</td>
																				<td><fmt:formatNumber value="${cost.costAmt}"
																						type="currency" pattern="￥0.00元" /></td>
																				<td>${cost.flagDesc}</td>
																			</tr>
																		</c:forEach>
																	</tbody>
																</table>
															</div>
														</td>
													</tr>
												</c:if>

												<c:if test="${operType=='A'}">
													<tr>
														<td class="ctr">审核结论：</td>
														<td><input type="radio" name="auditRet" id="auditRet"
															value="1" checked />审核通过 <input type="radio"
															name="auditRet" id="auditRet" value="0" />不审核通过</td>
													</tr>

													<tr>
														<td class="ctr">审核意见：</td>
														<td>
															<div class="form_control">
																<input name="comment" id="comment" type="text"
																	class="required" data-tip="请输入审核意见"
																	data-valid="maxGBLength:512" data-error="审核意见长度错误" />
															</div>
														</td>
													</tr>

													<tr>
														<td class="ctr"></td>
														<td class="txtcenter">
															<div class="pa-btn-sell ">
																<button class="btn-normal btn-sell mart10 marb10">提交审核</button>
															</div>
														</td>
													</tr>
												</c:if>

												<c:if test="${rspBody.listedType=='M'}">
													<c:if test="${operType=='P'}">
														<tr>
															<td class="ctr">资金密码：</td>
															<td>
																<div class="form_control">
																	<input type="password" name="fundPwd" id="fundPwd"
																		class="required" data-tip="请输入资金密码"
																		data-valid="isNonEmpty||between:6-16"
																		data-error="资金密码必填||密码长度错误" />
																</div>
															</td>
														</tr>

														<tr>
															<td class="ctr"></td>
															<td class="txtcenter">
																<div class="pa-btn-sell iblock">
																	<button class="btn-normal btn-sell mart10 marb10">支付资金</button>
																</div>
															</td>
														</tr>
													</c:if>
												</c:if>
											</tbody>
										</table>
									</form>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- wrapper End -->

	<!-- footer -->
	<jsp:include page="../comm/footer.jsp" flush="true" />
	<!-- footer End -->

	<script type="text/javascript">
		$(function() {

			$(document).ready(function() {
				$('.fixed-wrapper').stickUp();
			});

			//RADIO效果渲染		
			$('input').iCheck({
				checkboxClass : 'icheckbox_square-green',
				radioClass : 'iradio_square-green',
				increaseArea : '20%' // optional
			});

			//输入域验证
			$('#sellform').validate({
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

			var checkSubmitFlg = false;

			$('#sellform').on(
					'submit',
					function(event) {

						if (checkSubmitFlg == true) {
							UI.Dialog({
								type : 'tips',
								width : 320,
								title : '提交提示',
								content : "正在处理,不能重复提交！"
							}).show();
							return false;
						}

						var valFlag = $(this).validate('submitValidate');

						if (valFlag == true) {
							if ($("#fundPwd").length > 0) {
								$("#fundPwd")
										.val(b64_sha1($("#fundPwd").val()));
							}

							var auditflag = $("input[name='auditRet']:checked")
									.val();
							if (auditflag != undefined && auditflag == '0') {
								if ($('#comment').val() == ''
										|| $('#comment').val() == null) {
									var $parent = $('#comment').parent();
									$parent.removeClass('success');
									$parent.addClass('error');
									var $msg = $parent.find('.valid_message');
									$msg.text("请输入审核意见");
									event.preventDefault();
									return;
								}
							}
						} else {
							event.preventDefault();
							return;
						}

						checkSubmitFlg = true;
					});
		});
	</script>
</body>
</html>