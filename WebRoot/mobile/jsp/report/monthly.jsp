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

	<jsp:include page="../comm/mobile.jsp" flush="true" />

	<script type="text/javascript" src="/mobile/js/tableselect.js"></script>
	<script type="text/javascript">

		$(function() {

			$(document).ready(function() {
				$('.fixed-wrapper').stickUp();
			});

			$('#check-month').empty();
			for(var i = 1; i < 13; i++)
				$('<option value="'+i+'">'+i+'</option>').appendTo('#check-month');
			
			$('#check-year').empty();
			for(var i = 0; i < 20; i++)
				$('<option value="'+(2015+i)+'">'+(2015+i)+'</option>').appendTo('#check-year');
			
			
			var currenttime = new Date();
			var curYear = currenttime.getFullYear();
			var curMonth = currenttime.getMonth()+1;

			curMonth = curMonth -1;
			if(curMonth == 0){
				curMonth = 12;
				curYear--;
			}
			
			$('#check-month').val(curMonth);
			$('#check-year').val(curYear);

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

				var selmonth = $('#check-month').val();
				if(selmonth.length == 1)
					rptdate = $('#check-year').val()+'-0'+selmonth;
				else
					rptdate = $('#check-year').val()+'-'+selmonth;

				var parms ={'rptdate':rptdate};
				$.ajax({
					type : 'post',
					url : '/report/reportmonthly.htm',
					data : parms,
					cache : false,
					dataType : 'json',
					success : function(data) {
						if(data.succflag != 0){
							$('.searchmsg').html(data.msg);
						}else{
							var context = data.data;

							$('#headrender').html('<div class="headtitle">中国农业交易平台</div>');

							unitRender(context.monthly, 'monthly');

								$(".rpt-model").alterBgColor({ style: "4" }); //可以链式操作  
							}
							$('.report-box').show();
						}

					});

			});

			$('#J_Export').on('click', function(event){

				var rptdate = '';
				$('.searchmsg').html('');

				var selmonth = $('#check-month').val();
				if(selmonth.length == 1)
					rptdate = $('#check-year').val()+'-0'+selmonth;
				else
					rptdate = $('#check-year').val()+'-'+selmonth;


				window.location.href = "/report/exportmonthly.htm?rptdate="+rptdate;

			});
		});

	</script>

	<title>交易商月报表</title>

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
						<span class="input-group-addon">报表周期</span>
						<select name="check-year" id="check-year" class="csel form-control"></select><span class="input-group-addon">年</span>
						<select name="check-month" id="check-month" class="csel form-control"></select><span class="input-group-addon">月</span>
						<span class="input-group-addon" id="J_Search"><span class="report-search">搜索</span></span>
					</div>
				</div>
			</div>
		</div>
		
		<div class="report-box mart10" style="display:none;">
			<div class="row">
				<div id="monthlyrender" class="rpt-content lh34">
				</div>
			</div>
		</div>

	</div>

	<div class="report-export">
		<button  id="J_Export"><img src="/mobile/images/export.png" alt="">导出结果</button>
	</div>


	<script type="text/x-handlebars-template" id="monthlyTemplate">
		<div class="col-xs-12">
			<select name="" id="" class="">
				<option value="交易商月报表" selected="">交易商月报表</option>
			</select>
		</div>
		<div class="col-xs-6 padl20">交易商号</div>
		<div class="col-xs-6 txtright fc999 padr20">{{mid}}</div>
		<div class="col-xs-6 padl20">交易商名称</div>	
		<div class="col-xs-6 txtright fc999 padr20">{{memname}}</div>
		<div class="col-xs-6 padl20">报表周期</div>
		<div class="col-xs-6 txtright fc999 padr20">{{reportdate}}</div>

		<div class="col-xs-12 padb60">
			<table class="rpt-model table table-responsive">
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





</body>
</html>