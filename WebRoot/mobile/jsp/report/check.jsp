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
	<link rel="shortcut icon" href="/mobile/images/icon/favicon.ico" />
	<link type="text/css" rel="stylesheet" href="/mobile/css/font.css" />
	<link type="text/css" rel="stylesheet" href="/mobile/widget/css/ui.datepicker.css" />
	<script type="text/javascript" src="/mobile/js/jquery.js"></script>
	<script type="text/javascript" src="/mobile/js/handlebars.js"></script>
	<script type="text/javascript" src="/mobile/js/stickup.js"></script>
	<script type="text/javascript" src="/mobile/widget/js/ui.datepicker.js"></script>

	<script type="text/javascript" src="/mobile/js/tableselect.js"></script>

	<jsp:include page="../comm/mobile.jsp" flush="true" />

	<script type="text/javascript">
		$(function() {

			$(document).ready(function() {
				$('.fixed-wrapper').stickUp();
			});


			Number.prototype.formatMoney = function(places, symbol, thousand, decimal) {
				places = !isNaN(places = Math.abs(places)) ? places : 2;
				symbol = symbol !== undefined ? symbol : "";
				thousand = thousand || ",";
				decimal = decimal || ".";
				var number = this, negative = number < 0 ? "-" : "", i = parseInt(
					number = Math.abs(+number || 0).toFixed(places), 10)
				+ "", j = (j = i.length) > 3 ? j % 3 : 0;
				return symbol
				+ negative
				+ (j ? i.substr(0, j) + thousand : "")
				+ i.substr(j).replace(/(\d{3})(?=\d)/g, "$1" + thousand)
				+ (places ? decimal
					+ Math.abs(number - i).toFixed(places).slice(2) : "");
			};


			function unitRender(data, val){
				var source = Handlebars.compile($("#"+val+"Template").html()); 

				Handlebars.registerHelper("money", function(value) {

					if(value != -1.00)
						return value.formatMoney();
					else
						return '';
				});

				Handlebars.registerHelper("style", function(value, bold) {			
					var htm = '';
					if(bold == true){
						if(value == undefined || value == null){
							htm = '<strong> </strong>'
						}else{
							htm = '<strong>'+value+'</strong>'
						}
					}else{
						if(value == undefined || value == null){
							htm ='';
						}else{
							htm = value;
						}
					}				   
					return new Handlebars.SafeString(htm);
				});


				var htmlText = source(data);
				$('#'+val+'render').html(htmlText);
			}


			$('#J_Search').on('click', function(event){


				var rptdate = '';
				$('.searchmsg').html('');
				if($('#rptdate').val() == ''){
					$('.searchmsg').html('没有输入日期时查询最近结算日情况');
					rptdate = '9999-99-99';
				}else{
					rptdate = $('#rptdate').val();
				}

				var parms ={'rptdate':rptdate};
				$.ajax({
					type : 'post',
					url : '/report/reportcheck.htm',
					data : parms,
					cache : false,
					dataType : 'json',
					success : function(data) {
						if(data.succflag != 0){
							$('.searchmsg').html(data.msg);
						}else{
							var context = data.data;

							$('.rpt-content').hide();
							$('.rpt-content').eq(0).show();
							$("#report-type").get(0).selectedIndex=0;

							$('#headrender').html('<div class="headtitle">中国农业交易平台</div><div class="title">'+data.mid+'交易商对账单</div>');

							unitRender(context.daily, 'daily');

							unitRender(context.fund, 'fund');

							unitRender(context.tick, 'tick');

							unitRender(context.notice, 'notice');

							unitRender(context.attend, 'attend');

							var curdate = new Date();
							var stampdate = curdate.getFullYear()+"年"+(curdate.getMonth()+1)+"月"+curdate.getDate()+"日"+curdate.getHours()+"点"+curdate.getMinutes()+"分"


							$('#tailrender').html('<div class="signin col-xs-12 ">签收<div class="sunderline"></div></div><div class="seal col-xs-12 ">签章 <div class="sunderline"></div></div><div class="datestamp col-xs-12 ">'+stampdate+'</div>');


								$(".rpt-model").alterBgColor({ style: "4" }); //可以链式操作  
							}
						}
					});

});

$('#J_Export').on('click', function(event){

	var rptdate = '';
	$('.searchmsg').html('');
	if($('#rptdate').val() == ''){
		$('.searchmsg').html('没有输入日期时查询最近结算日情况');
		rptdate = '9999-99-99';
	}else{
		rptdate = $('#rptdate').val();
	}


	window.location.href = "/report/exportcheck.htm?rptdate="+rptdate;

});

$('#report-type').change(function(){
	$('.rpt-content').hide();
	$('.rpt-content').eq($(this).val()).show();
})

$('#rptdate').focus(function(event) {
	$(this).blur();
});

});


</script>

<title>交易商对账单</title>

</head>
<body class="drawer drawer-left">

	<div class="fixed-wrapper">
		<!-- header -->
		<jsp:include page="../comm/header.jsp" flush="true" />
		<!-- header End -->
	</div>

	<div class="container-fluid up-datatables report">

		<!-- 二级菜单 -->
		<jsp:include page="../comm/submenu.jsp" flush="true" />
		<!-- 二级菜单 Emd-->

		<div class="condition">
			<div class="row">
				<div class="col-xs-12">
					<div class="input-group">
						<span class="input-group-addon">对账单日期</span>
						<input type="text" name="rptdate" id="rptdate"
						maxlength="10" datepicker data-date-format="yyyy-mm-dd"
						data-auto-close="true" class="cinp form-control" />
						<span class="input-group-addon" id="J_Search"><span class="report-search">搜索</span></span>
					</div>
				</div>
				<div class="col-xs-12 searchmsg"></div>
			</div>
		</div>

		<div class="report-box mart10">

			<div class="row">
				<div class="col-xs-12">
					<select name="" id="report-type" class="">
						<option value="0" selected="">交易商资金日报表</option>
						<option value="1">出入金明细表</option>
						<option value="2">成交明细表</option>
						<option value="3">交收通知单</option>
						<option value="4">交收办理明细表</option>
					</select>
				</div>
			</div>

			<div id="dailyrender" class="rpt-content lh34"></div>

			<div id="fundrender" class="rpt-content lh34"></div>

			<div id="tickrender" class="rpt-content lh34"></div>

			<div id="noticerender" class="rpt-content lh34"></div>

			<div id="attendrender" class="rpt-content lh34"></div>

			<div id="tailrender" class="tailrender lh34 txtright"></div>

		</div>

	</div>

	<div class="report-export">
		<button id="J_Export"><img src="/mobile/images/export.png" alt="" />导出结果</button>
	</div>

	<script type="text/x-handlebars-template" id="dailyTemplate">
		<div class="row">
			<div class="col-xs-6 padl20">交易商号</div>
			<div class="col-xs-6 txtright fc999 padr20">{{mid}}</div>
			<div class="col-xs-6 padl20">交易商名称</div>	
			<div class="col-xs-6 txtright fc999 padr20">{{memname}}</div>
			<div class="col-xs-6 padl20">报表日期</div>
			<div class="col-xs-6 txtright fc999 padr20">{{reportdate}}</div>
		</div>
		<div class="padb20 table-responsive">
			<table class="rpt-model   table txtcenter">
				<thead>
					<tr>
						<th>序号</th>
						<th>摘要</th>
						<th>增加</th>
						<th>减少</th>
						<th>余额</th>
					</tr>
				</thead>
				<tbody>
					{{#if succflag}}
					{{#each list}}
					<tr>
						<td>{{seqno}}</td>
						<td>{{style remark bold}}</td>
						<td>{{money incAmt}}</td>
						<td>{{money decrAmt}}</td>
						<td>{{money amt}}</td>
					</tr>
					{{/each}}
					{{#if list}}
					{{else}}
					<td colspan="5" class="report-nodata">无数据</td>
					{{/if}}
					{{/if}}											
				</tbody>
			</table>
		</div>

	</script>
	
	
	<script type="text/x-handlebars-template" id="fundTemplate">
		<div class="row">
			<div class="col-xs-6 padl20">交易商号</div>
			<div class="col-xs-6 txtright fc999 padr20">{{mid}}</div>
			<div class="col-xs-6 padl20">交易商名称</div>	
			<div class="col-xs-6 txtright fc999 padr20">{{memname}}</div>
			<div class="col-xs-6 padl20">报表日期</div>
			<div class="col-xs-6 txtright fc999 padr20">2{{reportdate}}</div>
		</div>
		<div class="padb20 table-responsive">
			<table class="rpt-model   table">
				<thead>
					<tr>
						<th>日期</th>
						<th>流水号</th>
						<th>变动类型</th>
						<th>资金类型</th>
						<th>入金</th>
						<th>出金</th>
					</tr>
				</thead>
				<tbody>
					{{#if succflag}}
					{{#each list}}
					<tr>
						<td>{{date}}</td>
						<td>{{acNo}}</td>
						<td>{{iaeDesc}}</td>
						<td>{{accTitleName}}</td>
						<td>{{money inAmt}}</td>
						<td>{{money outAmt}}</td>
					</tr>
					{{/each}}
					{{#if list}}
					{{else}}
					<td colspan="6" class="report-nodata">无数据</td>
					{{/if}}
					{{/if}}											
				</tbody>
			</table>
		</div>
	</script>
	
	
	<script type="text/x-handlebars-template" id="tickTemplate">
		<div class="row">
			<div class="col-xs-6 padl20">交易商号</div>
			<div class="col-xs-6 txtright fc999 padr20">{{mid}}</div>
			<div class="col-xs-6 padl20">交易商名称</div>	
			<div class="col-xs-6 txtright fc999 padr20">{{memname}}</div>
			<div class="col-xs-6 padl20">报表日期</div>
			<div class="col-xs-6 txtright fc999 padr20">2{{reportdate}}</div>
		</div>
		<div class="padb20 table-responsive">
			<table class="rpt-model   table">
				<thead>
					<tr>
						<th>成交编号</th>
						<th>成交时间</th>
						<th>合同编号</th>
						<th>商品名称</th>
						<th>买/卖</th>
						<th>数量</th>
						<th>价格</th>
						<th>合同总值</th>
						<th>交易手续费</th>
						<th>交易保证金</th>
						<th>品牌</th>
						<th>区域</th>
						<th>交货仓库</th>
					</tr>
				</thead>
				<tbody>
					{{#if succflag}}
					{{#each list}}
					<tr>
						<td>{{strikeNo}}</td>
						<td>{{dod}}</td>
						<td>{{contNo}}</td>
						<td>{{commName}}</td>
						<td>{{bsDesc}}</td>
						<td>{{vol}}</td>
						<td>{{money up}}</td>
						<td>{{money contAmt}}</td>
						<td>{{money trdCharge}}</td>
						<td>{{money trdMargin}}</td>
						<td>{{commBrand}}</td>
						<td>{{divName}}</td>
						<td>{{store}}</td>
					</tr>
					{{/each}}
					{{#if list}}
					{{else}}
					<td colspan="13" class="report-nodata">无数据</td>
					{{/if}}
					{{/if}}											
				</tbody>
			</table>
		</div>
	</script>
	
	
	<script type="text/x-handlebars-template" id="noticeTemplate">
		<div class="row">
			<div class="col-xs-6 padl20">交易商号</div>
			<div class="col-xs-6 txtright fc999 padr20">{{mid}}</div>
			<div class="col-xs-6 padl20">交易商名称</div>	
			<div class="col-xs-6 txtright fc999 padr20">{{memname}}</div>
			<div class="col-xs-6 padl20">报表日期</div>
			<div class="col-xs-6 txtright fc999 padr20">2{{reportdate}}</div>
		</div>
		<div class="padb20 table-responsive">
			<table class="rpt-model   table">
				<thead>
					<tr>
						<th>成交编号</th>
						<th>合同编号</th>
						<th>商品名称</th>
						<th>买/卖</th>
						<th>价格</th>
						<th>成交数量</th>
						<th>确认数量</th>
						<th>买入货款</th>
						<th>卖出货款</th>
						<th>发票监管</th>
						<th>已开票金额</th>
						<th>开票保证金</th>
						<th>交收状态</th>
						<th>仓单类型</th>
						<th>品牌</th>
						<th>区域</th>
						<th>交货仓库</th>
					</tr>
				</thead>
				<tbody>
					{{#if succflag}}
					
					{{#each list}}
					<tr>
						<td>{{strikeNo}}</td>
						<td>{{contNo}}</td>
						<td>{{commName}}</td>
						<td>{{bsDesc}}</td>
						<td>{{money up}}</td>
						<td>{{vol}}</td>
						<td>{{sureVol}}</td>
						<td>{{money inPog}}</td>
						<td>{{money outPog}}</td>
						<td>{{invoice}}</td>
						<td>{{money invAmt}}</td>
						<td>{{money invMargin}}</td>
						<td>{{statusDesc}}</td>
						<td>{{listedType}}</td>
						<td>{{commBrand}}</td>
						<td>{{divName}}</td>
						<td>{{store}}</td>
					</tr>
					{{/each}}
					{{#if list}}
					{{else}}
					<td colspan="17" class="report-nodata">无数据</td>
					{{/if}}
					{{/if}}

				</tbody>
			</table>
		</div>
	</script>
	
	<script type="text/x-handlebars-template" id="attendTemplate">
		<div class="row">
			<div class="col-xs-6 padl20">交易商号</div>
			<div class="col-xs-6 txtright fc999 padr20">{{mid}}</div>
			<div class="col-xs-6 padl20">交易商名称</div>	
			<div class="col-xs-6 txtright fc999 padr20">{{memname}}</div>
			<div class="col-xs-6 padl20">报表日期</div>
			<div class="col-xs-6 txtright fc999 padr20">2{{reportdate}}</div>
		</div>
		<div class="padb20 table-responsive">
			<table class="rpt-model   table">
				<thead>
					<tr>
						<th>成交日期</th>
						<th>成交编号</th>
						<th>买/卖</th>
						<th>操作日期</th>
						<th>交收状态</th>
						<th>资金账户</th>
						<th>出入类型</th>
						<th>资金类型</th>
						<th>入金</th>
						<th>出金</th>
					</tr>
				</thead>
				<tbody>
					{{#if succflag}}
					{{#each list}}
					<tr>
						<td>{{dod}}</td>
						<td>{{strikeNo}}</td>
						<td>{{bsDesc}}</td>
						<td>{{operDate}}</td>
						<td>{{statusDesc}}</td>
						<td>{{fundAcctName}}</td>
						<td>{{iaeDesc}}</td>
						<td>{{accTitleName}}</td>
						<td>{{money inAmt}}</td>
						<td>{{money outAmt}}</td>
					</tr>
					{{/each}}
					{{#if list}}
					{{else}}
					<td colspan="10" class="report-nodata">无数据</td>
					{{/if}}
					
					{{/if}}											
				</tbody>
			</table>
		</div>
	</script>

</body>
</html>