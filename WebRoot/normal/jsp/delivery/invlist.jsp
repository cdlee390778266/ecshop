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
     <link rel="shortcut icon" href="/normal/images/icon/favicon.ico" />
    <link type="text/css" rel="stylesheet" href="/normal/css/global.css" />
    <link type="text/css" rel="stylesheet" href="/normal/css/font.css" />
    <link type="text/css" rel="stylesheet" href="/normal/css/common.css" />
    <link type="text/css" rel="stylesheet" href="/normal/css/member.css" />
    <link type="text/css" rel="stylesheet" href="/normal/widget/css/ui.dialog.css" />
    <link type="text/css" rel="stylesheet" href="/normal/widget/css/ui.datepicker.css" />
    <link type="text/css" rel="stylesheet" href="/normal/css/square/green.css">
    <link type="text/css" rel="stylesheet" href="/normal/css/selecttags.css" >
    <script type="text/javascript" src="/normal/js/jquery.js"></script>
    <script type="text/javascript" src="/normal/js/handlebars.js"></script>
	<script type="text/javascript" src="/normal/widget/js/ui.dialog.js"></script>
	<script type="text/javascript" src="/normal/js/leftnavs.js"></script>
	<script type="text/javascript" src="/normal/widget/js/ui.datepicker.js"></script>
	<script type="text/javascript" src="/normal/js/ui.pagination.js"></script>
	<script type="text/javascript" src="/normal/js/selecttags.js"></script>
	<script type="text/javascript" src="/normal/js/icheck.js"></script>
	<script type="text/javascript" src="/normal/js/stickup.js"></script>
	
	<jsp:include page="../comm/datatables.jsp" flush="true" />
	
	<script type="text/javascript" src="/normal/js/handle.delivery.inv.js?v=${sessionScope.buildno}"></script>
    <title>交收管理</title>
    
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
					<a href="/home.htm">交易大厅</a><span class="fa  fa-angle-right"></span><a href="/delivery/buylist.htm">会员中心</a><span class="fa  fa-angle-right"></span>发票确认
				</div>
			</div>
			
			<!-- main -->
			<div class="page">
				<!-- left -->
				<jsp:include page="../comm/left.jsp" flush="true" />
				<!-- left End -->
				
				
				<div class="main-content">
					<div class="bd">
						
						<div class="page-module data-query">
							<div class="row">
							    <div class="hd">
							   		<h3>交收采购订单</h3>
							    </div>
							    <div class="bd mt10 warehouse">
								    <table class="ui-table">
										<tbody>
											<tr>

												<td width="80" class="ctr">商品分类：</td>
												<td>
													<input type="hidden" name="commcode" id="commcode" />
													<div class="selcomm" data-select></div>
													<div class="select-box" id="product-dialog">
														<div class="select-txt" id="select-txt">请选择</div>
													</div>											
												</td>
                                                <td class="ctr">交收状态：</td>
												<td>
													<select name="status" id="status" class="csel">
														<option value="400">买方待确认发票</option>
													</select>
												</td>
												<td class="ctr">交收单号：</td>
												<td><input type="text"  name="strikeNo" id="strikeNo"  class="cinp"/></td>
											</tr>											
											<tr>												
												<td class="ctr">最后付款日：</td>
												<td><input type="text"  name="lastPD" id="lastPD" datepicker data-date-format="yyyy-mm-dd"  data-auto-close="true"  class="cinp-date ml10"/>
												至
												<input type="text"  name="elastPD" id="elastPD" datepicker data-date-format="yyyy-mm-dd"  data-auto-close="true"  class="cinp-date"/>
												
												</td>
											</tr>											
										</tbody>
									</table>
							    </div>								
							</div>

								 
								 <table id="dataset" class="display nowrap cell-border" cellspacing="0" width="100%">
								        <thead>
								            <tr>
								            	<th>品种</th>
								            	<th>商品代码</th>
								            	<th>商品名称</th>
								            	<th>品牌</th>	
								                <th>产地</th> 
								                <th>卖方编号</th>								                	
								                <th>卖方名称</th> 
								                <th>成交日期</th>								                
								                <th>最后付款日</th>
								                <th>最后交收日</th>
								                <th>状态</th>								                								                								                
								                <th>商品数量</th>
								                <th>商品单价</th>
								                <th>成交金额(元)</th>   								                
								                <th>交收类型</th>
								                <th>交收仓库</th>								                								                
								                <th>标题简述</th>								                								                	
								                <th>订单号</th>	
								                <th>合同编号</th> 
								                									                					                
								                <th>操作</th>								                
								            </tr>
								        </thead>
								  </table>
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
	
	<!-- 投诉弹出层 -->	
	<div class="updialog d-add-role w350" id="J_Appeal">
		<div class="hd">
			<span class="close ic"></span>
			<h3>交易投诉</h3>
		</div>
		<div class="bd">
			<div class="d-content">
				<div class="bd mt10">
					<div class="uitem">
							<table class="ui-table">
								<tr>
									<td width="100px" class="tr">交易单号：</td>
									<td><div id="AppealStkNo"></div></td>
								</tr>
								<tr>
									<td class="tr">挂牌有效期：</td>
									<td><div id="AppealStkDoe"></div></td>
								</tr>
								<tr>
									<td class="tr">投诉原因：</td>
									<td>
									<input type="radio" name="appealType" id="delayCheck" value="0" checked /><span id="delayMsg"></span>
									<input type="radio" name="appealType" id="otherCheck" value="1" />其他原因
									<textarea name="appealDesc" id="appealDesc" cols="40" rows="5" style="display:none; margin-top: 10px"></textarea>
									<input type="hidden" name="listedType" id="listedType">
									</td>
								</tr>								
								<tr>
									<td></td>
									<td>
										<div class="pt10"><button class="cbtn cpublish">提交投诉</button></div>         
									</td>
								</tr>
							</table>
							<div class="reasonmsg" style="color: #f00;"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	
	<div class="updialog d-add-role w350" id="J_AppealShow">
		<div class="hd">
			<span class="close ic"></span>
			<h3>投诉查看</h3>
		</div>
		<div class="bd">
			<div class="d-content">
				<div class="bd mt10">
					<div class="uitem">
							<table class="ui-table">
								<tr>
									<td width="100px" class="tr">交易单号：</td>
									<td><div class="compstrikeno"></div></td>
								</tr>
								<tr>
									<td class="tr">投诉会员：</td>
									<td><div class="compmid"></div></td>
								</tr>
								<tr>
									<td class="tr">投诉交易状态：</td>
									<td><div class="comptrdstatus"></div></td>
								</tr>
								<tr>
									<td class="tr">投诉时间：</td>
									<td><div class="comptime"></div></td>
								</tr>
								<tr>
									<td class="tr">投诉原因：</td>
									<td><div class="comprlt"></div></td>
								</tr>
								
								<tr>
									<td class="tr">受理人：</td>
									<td><div class="compaccepter"></div></td>
								</tr>
								<tr>
									<td class="tr">受理时间：</td>
									<td><div class="compacceptime"></div></td>
								</tr>
								<tr>
									<td class="tr">受理意见：</td>
									<td><div class="compcomment"></div></td>
								</tr>
								<tr>
									<td class="tr">受理结果：</td>
									<td><div class="compacceptret"></div></td>
								</tr>							
							</table>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<script type="text/x-handlebars-template" id="entryTemplate">
		<table class="ui-table table-primary">
			<thead>
				<tr>
					<td colspan="2">交易商品</td>
					<td>合同</td>
					<td>单价（元）</td>
					<td>数量</td>
					<td>交易款项（元）</td>
	                <td>交收状态</td>
	                <td>交易操作</td>
					</tr>
			</thead>	
            <tbody>
                {{#strikes}}
					<tr class="{{setstyle @index}}">
						<td colspan="2">成交日期:{{dod}}</td>
						<td colspan="2">订单号:{{strikeNo}}</td>
						<td colspan="2">
							{{sayBSMid smID bmID}}
						</td>
						<td colspan="2" style="text-align:left">交收类型:{{listedTypeName}}  {{handlepay lastpd}} </td>
					</tr>
					<tr class="{{setstyle @index}}">
						<td>
							<div class="pic"><img src="{{titlePic}}" width="53" height="53" alt="" onError="this.src='/normal/images/loadfail.jpg'"></div>
						</td>
						<td>
							<div class="delivtitle">{{title}}</div>
							<div class="delivcommity">{{commName}}</div>
						</td>
						<td>										
							<a href="/contract/info/{{contno}}.htm" class="lnks" target="_blank">{{contno}}</a>
						</td>
						<td>{{money up}}</td>
						<td>{{vol}}</td>
						<td>{{money contAmt}}</td>
						<td><span class="cor-red">{{statusDesc}}</span></td>
						<td>
							{{#if effRec}}
								{{handlelink status strikeNo doe enableT enableP lastCompNo}}
							{{else}}
								订单无效
							{{/if}}
						</td>									 	
					</tr>					
                 {{/strikes}}
           </tbody>
       </table>
	</script>
	
</body>
</html>