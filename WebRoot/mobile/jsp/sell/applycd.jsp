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
	<link rel="shortcut icon" href="/mobile/images/icon/favicon.ico" />
	<link type="text/css" rel="stylesheet" href="/mobile/css/font.css" />
	<link type="text/css" rel="stylesheet" href="/mobile/widget/css/ui.dialog.css" />
	<link type="text/css" rel="stylesheet" href="/mobile/widget/css/ui.datepicker.css" />
	<link type="text/css" rel="stylesheet" href="/mobile/css/commsel.css" />
	<link type="text/css" rel="stylesheet" href="/mobile/css/square/green.css">
	<link type="text/css" rel="stylesheet" href="/mobile/css/validate.css">
	<link type="text/css" rel="stylesheet" href="/mobile/css/combo.select.css">
	<link type="text/css" rel="stylesheet" href="/mobile/widget/css/ui.dialog.css" />
	<style>
		.select_ta {margin-left: 100px}
	</style>
	<script type="text/javascript" src="/mobile/js/jquery.js"></script>
	<script type="text/javascript" src="/mobile/widget/js/ui.dialog.js"></script>
	<script type="text/javascript" src="/mobile/widget/js/ui.datepicker.js"></script>
	<script type="text/javascript" src="/mobile/js/jquery.combo.select.js"></script>
	<script type="text/javascript" src="/mobile/js/icheck.js"></script>
	<script type="text/javascript" src="/mobile/js/jquery-validate.js"></script>
	<script type="text/javascript" src="/mobile/js/selecttags-cd.js"></script>
	<script type="text/javascript" src="/mobile/js/localcity.js"></script>
	<script type="text/javascript" src="/mobile/js/stickup.js"></script>
	<script type="text/javascript" src="/mobile/widget/laydate/laydate.js"></script>
	<script type="text/javascript" src="/mobile/widget/js/ui.dialog.js"></script>
	<jsp:include page="../comm/datatables.jsp" flush="true" />
	<jsp:include page="../comm/mobile.jsp" flush="true" />
	<script type="text/javascript" src="/mobile/js/handle.sell.applycd.js?v=${sessionScope.buildno}"></script>

	<title>卖方挂牌</title>
</head>
<body class="drawer drawer-left">

	<div class="fixed-wrapper">
		<!-- header -->
		<jsp:include page="../comm/header.jsp" flush="true" />
		<!-- header End -->
	</div>

	<!-- wrapper -->

	<div class="container-fluid examine demand">

		<div class="row safe-type txtcenter bgfff up-list">
			<!-- <div class="col-xs-6"><a href=""  >买方挂牌</a></div> -->
			<div class="col-xs-12"><a href="javascript:void(0);"  class="active">卖方挂牌</a></div>
		</div>

		<form action="/sell/applyListed.htm" method="post" enctype="multipart/form-data" id="sellApply" class="form-horizontal">

			<input type="hidden" name="active" value="${active}" /> 
			<input type="hidden" name="busDate" id="busDate" value="${busDate}" />

			<div class="gp-box">

				<div class="gp-step1" id="step1">

					<div class="row">
						<div class="col-xs-12 demand-bar borderb ">
							选择挂牌类型及仓单编码
						</div>
					</div>

					<div class="form-group  borderb  form-group-lg">
						<label class="col-xs-5 control-label ">挂牌类型</label>
						<div class="col-xs-7">
							<select name="" id="cd-type" class="form-control">
								<option value="/sell/apply.htm?active=enter&type=0">保证金</option>
								<option value="/sell/apply.htm?active=enter&type=1" selected="">仓单</option>
							</select>
							<span class="form-control txtright lh26 hide" id="delist-type">
								<input type="radio" name="DlistedType" value="M" checked disabled="disabled" class="hide" class="hide" />
								<input type="hidden" name="listedType" value="M" />保证金
							</span>
						</div>
					</div>

					<div class="form-group  borderb  form-group-lg" id="selectCd">
						<label class="col-xs-5 control-label ">选择注册仓单编码</label>
						<div class="col-xs-7">
							<input type="text" readonly="readonly" name="wrno" id="wrno" class="form-control cd-txt" placeholder="选择注册仓单" />	
							<span id="codev" class="valid_message" ></span>
						</div>
					</div>

					<div class="row martb40">
						<div class="col-xs-12 txtcenter marb60">
							<a class="btn btn-warning  btn-next" href="javascript:void(0);" >下一步</a>
						</div>
					</div>	

				</div>

				<div id="step3" class="gp-step3" >

					<div class="row">
						<div class="col-xs-12 demand-bar borderb ">
							请选择商品属性
						</div>
					</div>

					<div class="form-group buy-group">
						<div class="input-group rel">
							<span class="input-group-addon">单价(<span class="fcyellow ">必填</span>)</span>
							<input type="text" name="unitPrice" id="unitPrice" placeholder="请输入商品单价" class="required form-control" data-tip="请输入商品单价"
							data-valid="isNonEmpty||isNoNZeroMoney" data-error="单价必填||金额格式:1.00" /> 
							<span class="priceunit_message"></span> 
							<span class="valid_message"></span>
						</div>
					</div>

					<div class="form-group buy-group">
						<div class="input-group rel">
							<span class="input-group-addon">一口价(<span class="fcyellow ">必填</span>)</span>
							<div class="txtright">
								<input type="radio" name="DfpFlag" value="F" checked disabled="disabled" />是
							</div>
							<input type="hidden" name="fpflag" value="F" /> 
					<!--  
											<span><input type="radio" name="fpFlag" value="V" />否</span>
										-->
									</div>
								</div>

								<div class="form-group buy-group">
									<div class="input-group rel">
										<span class="input-group-addon">总量(<span class="fcyellow ">必填</span>)</span>
										<input type="text" name="qty" id="qty" class="required form-control" readonly="readonly"
										data-tip="请输入商品总量" data-valid="isNonEmpty||plusInt"
										data-error="总量必填||总量必须为整数" /> <span
										class="unit_message"></span>
									</div>
								</div>

								<div class="form-group buy-group J_WholeFlag">
									<div class="input-group rel">
										<span class="input-group-addon">是否整单</span>
										<div class="txtright">
											<input type="radio"
											name="wholeFlag" id="s_flag" value="W" checked disabled="disabled"/>是
										</div>
										<input type="hidden" name="wholeflag" value="W" />
									</div>
								</div>

								<div class="form-group buy-group">
									<div class="input-group rel">
										<span class="input-group-addon">挂牌有效期(<span class="fcyellow ">必填</span>)</span>
										<input type="text" name="doe" id="doe" placeholder="请选择挂牌有效期"
										data-min="${busDate}" class="required form-control"
										data-tip="请选择挂牌有效期"
										data-valid="isNonEmpty||isDate||after:${busDate}"
										data-error="挂牌有效期必填||有效期格式不正确||有效期小于业务日期${busDate}" />
									</div>
								</div>

								<div class="form-group buy-group">
									<div class="input-group rel">
										<span class="input-group-addon">卖场(<span class="fcyellow ">必填</span>)</span>
										<div class="txtright"> 
											<input type="checkbox"
											name="Dmart" value="O" checked disabled="disabled">公开大厅

										</div> 
										<input type="hidden" name="mart" value="O" />
									</div>
								</div>

								<div class="form-group buy-group">
									<div class="input-group rel">
										<span class="input-group-addon">交收仓库(<span class="fcyellow ">必填</span>)</span>
										<input type="text" name="storage" id="storage" readonly="readonly" class="form-control" />
									</div>
								</div>

								<div class="form-group buy-group">
									<div class="input-group rel">
										<span class="input-group-addon">平台监管发票(<span class="fcyellow ">必填</span>)</span>
										<div class="txtright">
											<span ><input type="radio" name="invoice" value="Y" checked />需要</span>
											<span><input type="radio" name="invoice" value="N" />不需要</span>
										</div>
									</div>
								</div>

								<div class="form-group buy-group">
									<div class="input-group rel">
										<span class="input-group-addon">是否指定摘牌方(<span class="fcyellow ">必填</span>)</span>
										<div class="txtright">
											<span><input type="radio" name="delist" value="O" checked />不指定</span> 
											<span><input type="radio" name="delist" value="A" />指定</span> 
											<input type="hidden" name="memdelists" id="memdelists" />
										</div>
									</div>
								</div>

								<div class="form-group buy-group" id="memdelistlink" style="display:none;border-bottom:none;">
									<div class="input-group rel">
										<span class="input-group-addon"></span>
										<div class="txtright">
											<span id="memdelistmsg"></span>
											<span class=" glyphicon glyphicon-menu-right account-a"></span>
										</div>
									</div>
								</div>

								<div class="row martb40">
									<div class="col-xs-6 txtcenter marb60">
										<a class="btn btn-default  btn-prev"  href="javascript:void(0);">上一步</a>
									</div>
									<div class="col-xs-6 txtcenter marb60">
										<a class="btn btn-warning  btn-next" href="javascript:void(0);" >下一步</a>
									</div>
								</div>

							</div>

							<div id="step4" class="gp-step4">

								<input type="hidden" name="listedtype" value="W" />
								<input type="hidden" name="markcode" id="markcode" />
								<input type="hidden" name="commcode" id="commcode" />

								<div class="row">

									<div class="col-xs-12 demand-bar ">
										商品描述
									</div>

									<div class="form-group  step-title">
										<div class="col-xs-12 rel">
											<input type="text" name="title" class="required form-control"
											data-tip="请输入描述标题" data-valid="maxGBLength:128"
											data-error="描述标题长度不超过128" />
										</div>
									</div>

									<textarea name="detail" id="detail" cols="45" rows="6" class="form-control"></textarea>

									<div class="detailmsg" style="color: #f00;"></div>

									<div class="clearfix col-xs-12" style="margin-top:2px">
										<img id="titfilePre" width="120px" height="120px"
										style="display: none; float: left" /> 
										<div class="btn-upload_unselected fileinput">
											<span>选择标题图片</span> 
											<input type="file" name="titfile" id="titfile" accept="image/jpg,image/jpeg,image/png,image/bmp,image/gif" />
										</div>
									</div>

									<div class="clearfix col-xs-12">
										<img id="ctxPic0Pre" width="120px" height="120px"
										style="display: none; float: left" /> 
										<div class="btn-upload_unselected fileinput ">
											<span>选择商品描述首图</span> 
											<input type="file" name="ctxfile" id="ctxPic0" accept="image/jpg,image/jpeg,image/png,image/bmp,image/gif" />
										</div>
									</div>

									<div class="clearfix col-xs-12">
										<img id="ctxPic1Pre" width="120px" height="120px"
										style="display: none; float: left" /> 
										<div class="btn-upload_unselected fileinput ml10">
											<span>选择商品描述次图</span> 
											<input type="file" name="ctxfile" id="ctxPic1" accept="image/jpg,image/jpeg,image/png,image/bmp,image/gif" />
										</div>
									</div>

									<div class="clearfix col-xs-12">
										<img id="ctxPic2Pre" width="120px" height="120px"
										style="display: none; float: left" /> 
										<div class="btn-upload_unselected fileinput ml10">
											<span>选择商品描述尾图</span> 
											<input type="file" name="ctxfile" id="ctxPic2" accept="image/jpg,image/jpeg,image/png,image/bmp,image/gif" />
										</div>
									</div>

								</div>

								<div class="col-xs-6 martb10 marb60">
									<a class="btn btn-default  btn-prev" href="javascript:void(0);" >上一步</a>
								</div>  

								<div class="col-xs-6 martb10 marb60">
									<button class="btn btn-warning btn-sell">我要挂牌</button>
								</div>
								
							</div>
						</div>

					</form>
				</div>
				<!-- wrapper End -->

				<!-- 弹出层 -->
				<div class="updialog" id="J_MemList">
					<div class="hd">
						<span class="close ic"></span>
						<h3>指定摘牌会员选择</h3>
					</div>
					<div class="bd">
						<div>
							<div class="memselect">

								<div class="manage">
									<input type="text" placeholder="请选择行政区域" name="divisID"
									data-key="0086" data-idx="0" data-full="中国" id="divisID"
									class="inp-search"  />
									<div class="localcity"></div>
									<div class="unselect">
										<select name="seletlist" size="20" multiple id="selectlist" style="display:none;" >
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
							<div style="float: right;display:none;">
								<button class="cbtn" id="confirmbtn">确&nbsp;&nbsp;认</button>
								<button class="cbtn" id="cancelbtn">取&nbsp;&nbsp;消</button>
							</div>
						</div>
					</div>
				</div>

				<!-- 选择注册仓单弹出层 -->
				<div class="select_mask"></div>
				<div class="up-cdList" id="J_cdList" style="height: 500px;overflow: scroll;">
					<div class="hd select-hd">
						注册仓单选择
						<span><img src="/mobile/images/select_close.png" alt="" /></span>
					</div>
					<div class="select-body" id="select-body">
					<div>
						<input type="hidden" name="listedType" value="M" /> 
						<div class="cd-select" data-select></div> <!--  comm需要级联查询得到 -->
						<input type="hidden" name="commCode" id="commCode"
						value="" />
						<input type="hidden" name="sCCode" id="sCCode" value="" />
						<div class="seledmsg" style="color: #f00; float: right"></div>

						<div class="bd">

							<div style="width: 99%;min-height: 100px;">
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
								<div style="">
									<button  id="confirmbtn1">确&nbsp;&nbsp;认</button>
									<button class="cbtn hide" id="cancelbtn1">取&nbsp;&nbsp;消</button>
								</div>
							</div>

						</div>
					</div>
					</div>
				</div>
			</body>
			</html>