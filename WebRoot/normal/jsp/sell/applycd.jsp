<%@ page pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
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
<link type="text/css" rel="stylesheet" href="/normal/normal/css/global.css" />
<link type="text/css" rel="stylesheet" href="/normal/css/font.css" />
<link type="text/css" rel="stylesheet" href="/normal/css/common.css" />
<link type="text/css" rel="stylesheet" href="/normal/css/member.css" />
<link type="text/css" rel="stylesheet" href="/normal/widget/css/ui.dialog.css" />
<link type="text/css" rel="stylesheet" href="/normal/widget/css/ui.datepicker.css" />
<link type="text/css" rel="stylesheet" href="/normal/css/commsel.css" />
<link type="text/css" rel="stylesheet" href="/normal/css/square/green.css">
<link type="text/css" rel="stylesheet" href="/normal/css/validate.css">
<link type="text/css" rel="stylesheet" href="/normal/css/selecttags.css">
<link type="text/css" rel="stylesheet" href="/normal/css/localcity.css">
<link type="text/css" rel="stylesheet" href="/normal/css/combo.select.css">
<link type="text/css" rel="stylesheet" href="/normal/widget/css/ui.dialog.css" />
<style>
	.select_ta {margin-left: 100px}
</style>
<script type="text/javascript" src="/normal/js/jquery.js"></script>
<script type="text/javascript" src="/normal/widget/js/ui.dialog.js"></script>
<script type="text/javascript" src="/normal/widget/js/ui.datepicker.js"></script>
<script type="text/javascript" src="/normal/js/jquery.combo.select.js"></script>
<script type="text/javascript" src="/normal/js/icheck.js"></script>
<script type="text/javascript" src="/normal/js/jquery-validate.js"></script>
<script type="text/javascript" src="/normal/js/selecttags.js"></script>
<script type="text/javascript" src="/normal/js/localcity.js"></script>
<script type="text/javascript" src="/normal/js/stickup.js"></script>
<script type="text/javascript" src="/normal/widget/laydate/laydate.js"></script>
    <script type="text/javascript" src="/normal/widget/js/ui.dialog.js"></script>
<jsp:include page="../comm/datatables.jsp" flush="true" />
<script type="text/javascript" src="/normal/js/handle.sell.applycd.js?v=${sessionScope.buildno}"></script>


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
					<a href="/home.htm">交易大厅</a><span class="fa  fa-angle-right"></span><a
						href="/sell/apply.htm?active=enter&type=0">买卖菜单</a><span
						class="fa  fa-angle-right"></span><a
						href="/sell/apply.htm?active=enter&type=0">卖方挂牌</a>
				</div>
			</div>

			<!-- main -->
			<div class="page">
				<div class="user-mans">
					<div class="portrait-big">
						<c:if test="${empty sessionScope.userinfo.operPhoto}">
							<img width="200" height="161" alt="" src="/images/portrait.jpg" />
						</c:if>
						<c:if test="${!empty sessionScope.userinfo.operPhoto}">
							<img width="200" height="161" alt=""
								src="${sessionScope.userinfo.operPhoto}" />
						</c:if>
					</div>

					<div class="ucenter">
						<span>买卖菜单</span>
					</div>
					<div class="user-navs members">
						<ul>
							<li class="current"><a href="/sell/apply.htm?active=enter&type=0"
								class="mlnks">卖方挂牌</a></li>
						</ul>
					</div>
				</div>

				<div class="main-content">
					<div class="bd">
						<form action="/sell/applyListed.htm" method="post"
							enctype="multipart/form-data" id="sellApply">
							<input type="hidden" name="active" value="${active}" /> <input
								type="hidden" name="busDate" id="busDate" value="${busDate}" />

							<div class="page-module warehouse">
								<div class="row">
									<div class="hd">
										<h3 class="cor-green">卖方挂牌</h3>
									</div>
									<div style="margin: 20px 20px 20px 0px;">
										<a href="/sell/apply.htm?active=enter&type=0" style="text-decoration: none;border:1px solid #ECECEC;padding: 10px;">保证金</a>
										<a href="/sell/apply.htm?active=enter&type=1" style="text-decoration: none;border: 1px solid #049019;color: #FFFFFF;background-color: #89C975;padding: 10px;margin-left: 10px;">注册仓单</a>		
									</div>
									<div class="bd">
										<table class="ui-table">
											<tbody>
												<input type="hidden" name="listedtype" value="W" />
												<input type="hidden" name="markcode" id="markcode" />
												<input type="hidden" name="commcode" id="commcode" />
												<tr>
													<td colspan="4" class="ctr">注册仓单编码：
														<input type="text" readonly="readonly" name="wrno" id="wrno"
															style="border: 1px solid #ECECEC;width: 180px;"/>
															<a href="#" id="selectCd" style="text-decoration: none;border: 1px solid;color: #FFFFFF;
																background-color: #89C975;padding: 5px;margin: 10px;" >选择注册仓单</a>	<span id="codev"
																	class="valid_message" style="color: red"></span>
													</td>
									
												</tr>
												<tr>
													<td class="ctr">单价<span class="forceinput">(必填)</span>：
													</td>
													<td width="300px">


														<div class="form_control">
															<input type="text" name="unitPrice" id="unitPrice"
																class="required" data-tip="请输入商品单价"
																data-valid="isNonEmpty||isNoNZeroMoney"
																data-error="单价必填||金额格式:1.00" /> <span
																class="priceunit_message"></span> <span
																class="valid_message"></span>

														</div>
													</td>

													<td class="ctr" width="125px">一口价：</td>
													<td><span class="ml20 mr20"><input type="radio"
															name="DfpFlag" value="F" checked disabled="disabled" />是</span>

														<input type="hidden" name="fpflag" value="F" /> <!--  
											<span><input type="radio" name="fpFlag" value="V" />否</span>
											--></td>
												</tr>

												<tr>

													<td class="ctr">总量<span class="forceinput">(必填)</span>：
													</td>
													<td>
														<div class="form_control">
															<input type="text" name="qty" id="qty" class="required" readonly="readonly"
																data-tip="请输入商品总量" data-valid="isNonEmpty||plusInt"
																data-error="总量必填||总量必须为整数" /> <span
																class="unit_message"></span>
														</div>
													</td>

													<td class="ctr">是否整单：</td>
													<td>
														<div class="J_WholeFlag">
															<span class="ml20 mr20"><input type="radio"
																name="wholeFlag" id="s_flag" value="W" checked disabled="disabled"/>是</span>
																<input type="hidden" name="wholeflag" value="W" />
														</div>

													</td>
												</tr>
												<tr>
													<td class="ctr">挂牌有效期<span class="forceinput">(必填)</span>：
													</td>
													<td>
														<div class="form_control">
															<input type="text" name="doe" id="doe"
																data-min="${busDate}" class="required"
																data-tip="请选择挂牌有效期"
																data-valid="isNonEmpty||isDate||after:${busDate}"
																data-error="挂牌有效期必填||有效期格式不正确||有效期小于业务日期${busDate}" />

														</div>
													</td>
													<td class="ctr">卖场：</td>
													<td><span class="ml20"> <input type="checkbox"
															name="Dmart" value="O" checked disabled="disabled">公开大厅
													</span> <input type="hidden" name="mart" value="O" /> <!-- 后面再做
											<span><input type="checkbox" name="mart" value="E" >专有专场</span>
											 --></td>

												</tr>
												<!-- <tr>
													<td class="ctr">最后付款日<span class="forceinput">(必填)</span>：
													</td>
													<td>合同签定后 <input type="text" name="lastPD" id="lastPD"
														maxlength="4"
														style="width: 40px; height: 24px; padding: 5px 5px; border: 1px solid #ECECEC;"
														autocomplete="off" />天 <span class="valid_message"></span>
													</td>
													<td class="ctr">最后交收日<span class="forceinput">(必填)</span>：
													</td>
													<td>全款支付后 <input type="text" name="deliDate"
														id="deliDate" maxlength="4"
														style="width: 40px; height: 24px; padding: 5px 5px; border: 1px solid #ECECEC;"
														autocomplete="off" />天 <span class="valid_message"></span>
													</td>
												</tr> -->
												<tr>
													<td class="ctr">交收仓库<span class="forceinput">(必选)</span>：
													</td>
													<td>
									
														<input type="text" name="storage" id="storage"
																	readonly="readonly" width="230px" style="border: 1px solid #ECECEC;margin-left: 10px;"/>
													</td>
													<td class="ctr">平台监管发票：</td>
													<td><span class="ml20 mr20"><input type="radio"
															name="invoice" value="Y" checked />需要</span> <span><input
															type="radio" name="invoice" value="N" />不需要</span></td>
												</tr>

												<tr>
													<td class="ctr">是否指定摘牌方：</td>
													<td colspan="3"><span class="ml20 mr20"><input
															type="radio" name="delist" value="O" checked />不指定</span> <span><input
															type="radio" name="delist" value="A" />指定</span> <span
														id="memdelistlink"></span> <span id="memdelistmsg"></span>
														<input type="hidden" name="memdelists" id="memdelists" />
													</td>
												</tr>


												<tr>
													<td class="ctr">商品标题：</td>
													<td>
														<div class="form_control">
															<input type="text" name="title" class="required"
																data-tip="请输入描述标题" data-valid="maxGBLength:128"
																data-error="描述标题长度不超过128" />
														</div>
													</td>
													<td class="ctr">商品描述：</td>
													<td><textarea name="detail" id="detail" cols="45"
															rows="6"></textarea>
														<div class="detailmsg" style="color: #f00;"></div></td>
												</tr>
												<tr>
													<td class="ctr">商品描述图：</td>
													<td colspan="3">
														<table>
															<tr>
																<td>
																	<div class="clearfix">
																		<img id="titfilePre" width="120px" height="120px"
																			style="display: none; float: left" /> <span
																			class="btn-upload_unselected fileinput ml10">
																			<span>选择标题图片</span> <input type="file" name="titfile"
																			id="titfile"
																			accept="image/jpg,image/jpeg,image/png,image/bmp,image/gif" />
																		</span>
																	</div>
																</td>
																<td>
																	<div class="clearfix">
																		<img id="ctxPic0Pre" width="120px" height="120px"
																			style="display: none; float: left" /> <span
																			class="btn-upload_unselected fileinput ml10">
																			<span>选择商品描述首图</span> <input type="file"
																			name="ctxfile" id="ctxPic0"
																			accept="image/jpg,image/jpeg,image/png,image/bmp,image/gif" />
																		</span>
																	</div>
																</td>

																<td>
																	<div class="clearfix">
																		<img id="ctxPic1Pre" width="120px" height="120px"
																			style="display: none; float: left" /> <span
																			class="btn-upload_unselected fileinput ml10">
																			<span>选择商品描述次图</span> <input type="file"
																			name="ctxfile" id="ctxPic1"
																			accept="image/jpg,image/jpeg,image/png,image/bmp,image/gif" />
																		</span>
																	</div>

																</td>
																<td>
																	<div class="clearfix">
																		<img id="ctxPic2Pre" width="120px" height="120px"
																			style="display: none; float: left" /> <span
																			class="btn-upload_unselected fileinput ml10">
																			<span>选择商品描述尾图</span> <input type="file"
																			name="ctxfile" id="ctxPic2"
																			accept="image/jpg,image/jpeg,image/png,image/bmp,image/gif" />
																		</span>
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
												<button class="btn-normal btn-sell"
													style="margin-left: 200px;">我要挂牌</button>
											</div>
											<div></div>
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
							<input type="text" placeholder="请选择行政区域" name="divisID"
								data-key="0086" data-idx="0" data-full="中国" id="divisID"
								class="inp-search" />
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
											<button class="btn-normal btn-sell">&lt;&lt;全部</button>
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

							</select>
						</div>
					</div>
					<div style="float: right">

						<button class="cbtn" id="confirmbtn">确&nbsp;&nbsp;认</button>
						<button class="cbtn" id="cancelbtn">取&nbsp;&nbsp;消</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	
		<!-- 选择注册仓单弹出层 -->
	<div class="updialog w850" id="J_cdList" style="height: 500px;overflow: scroll;">
		<div class="hd">
			<span class="close ic"></span>
			<h3>注册仓单选择</h3>
		</div>
		<div class="bd">
			<div style="margin-top: 5px;">
				<table class="ui-table">
					<tbody>
						<input type="hidden" name="listedType" value="M" /> 
						<tr>
							<td class="ctr">商品分类：</td>
							<td colspan="3">
								<div class="selcomm" data-select></div> <!--  comm需要级联查询得到 -->
								<input type="hidden" name="commCode" id="commCode"
								value="" />
								<input type="hidden" name="sCCode" id="sCCode" value="" />
								<div class="seledmsg" style="color: #f00; float: right"></div>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div style="width: 99%;margin: 5px;min-height: 100px;">
				<table id="dataset" class="display nowrap cell-border" cellspacing="0" width="100%" >
				        <thead>
				            <tr>
				            	<th>仓库编号</th>
				            	<th>仓库名称</th>
				                <th>仓单编号</th>
				                <th>交易商名称</th>
				                <th>商品种类名称</th>
				                <th>货物数量</th>
				            </tr>
				        </thead>
				  </table>
			</div>
			
			<div>
				<div style="float: right">

					<button class="cbtn" id="confirmbtn1">确&nbsp;&nbsp;认</button>
					<button class="cbtn" id="cancelbtn1">取&nbsp;&nbsp;消</button>
				</div>
			</div>
			
		</div>
	</div>
</body>
</html>