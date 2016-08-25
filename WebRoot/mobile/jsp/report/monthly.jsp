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
<link type="text/css" rel="stylesheet" href="/mobile/css/global.css" />
<link type="text/css" rel="stylesheet" href="/mobile/css/font.css" />
<link type="text/css" rel="stylesheet" href="/mobile/css/common.css" />
<link type="text/css" rel="stylesheet" href="/mobile/css/member.css" />
<link type="text/css" rel="stylesheet" href="/mobile/widget/css/ui.datepicker.css" />
<script type="text/javascript" src="/mobile/js/jquery.js"></script>
<script type="text/javascript" src="/mobile/js/leftnavs.js"></script>
<script type="text/javascript" src="/mobile/js/handlebars.js"></script>
<script type="text/javascript" src="/mobile/js/stickup.js"></script>
<script type="text/javascript" src="/mobile/widget/js/ui.datepicker.js"></script>

<script type="text/javascript" src="/mobile/js/tableselect.js"></script>
    <script type="text/javascript">
   $(function() {
   
		$(document).ready(function() {
				$('.fixed-wrapper').stickUp();
		});
		
		$('#check-month').empty();
		for(var i = 1; i < 13; i++)
			$('<option value="'+i+'">'+i+'月</option>').appendTo('#check-month');
			
		$('#check-year').empty();
		for(var i = 0; i < 20; i++)
			$('<option value="'+(2015+i)+'">'+(2015+i)+'年</option>').appendTo('#check-year');
			
			
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
<body>

	<div class="fixed-wrapper">
		<!-- topbar -->
		<jsp:include page="../comm/topbar.jsp" flush="true" />

		<!-- topbar End -->

		<!-- header -->
		<jsp:include page="../comm/header.jsp" flush="true" />
		<!-- header End -->

	</div>

	<div class="wrapper">
		<div class="grid-16-16">
			<div class="crumb-nav">
				<div class="backto">
					<div class="backrt">
						<a href="/home.htm">返回首页<i></i> </a>
					</div>
				</div>
				<div class="crumb">
					<a href="/home.htm">交易大厅</a><span class="fa  fa-angle-right"></span><a
					href="/report/monthly.htm">会员中心</a><span class="fa  fa-angle-right"></span><a
						href="javascript: void(0)">我的报表</a>
				</div>
			</div>
			<!-- main -->
			<div class="page">
				<!-- left -->
				<jsp:include page="../comm/left.jsp" flush="true" />
				<!-- left End -->

				<div class="main-content">
					<div class="bd">

						<div class="page-module data-rpt">
							<div class="row">
								<div class="hd">
									<h3>交易商月报表</h3>
								</div>
								<div class="row clearfix mt10">

									<div class="searchinput">
										报表周期：  <select name="check-year" id="check-year" class="csel">									
 													</select>
 													<select name="check-month" id="check-month" class="csel">							
 													</select>

									</div>
									
									<div class="searchmsg"></div>

									<div class="export">
										<button class="cbtn ml5" id="J_Export">导出结果</button>
									</div>

									<div class="search">
										<button class="cbtn ml5" id="J_Search">搜&nbsp;&nbsp;索</button>
									</div>
								</div>

								<div id="headrender" class="rpt-content"></div>

								<div id="monthlyrender" class="rpt-content"></div>
								
							</div>
						</div>
					</div>
				</div>
				<!-- main End -->

			</div>
		</div>
	</div>
	<!-- wrapper End -->

	<script type="text/x-handlebars-template" id="monthlyTemplate">

		<div class="rptname">{{title}}</div>
		<table class="rpt-model">
			<caption>
				<div class="caption"><div class="cpt-id">交易商号:{{mid}} </div><div class="cpt-name">交易商名称：{{memname}}</div> <div class="cpt-date">报表周期：{{reportdate}}</div></div>
			</caption>
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
				{{/if}}											
			</tbody>
		</table>

	</script>
	

	<!-- footer -->
	<jsp:include page="../comm/footer.jsp" flush="true" />
	<!-- footer End -->


</body>
</html>