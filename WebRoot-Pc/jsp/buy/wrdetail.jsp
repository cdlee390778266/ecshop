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
    <link rel="shortcut icon" href="/images/icon/favicon.ico" />
    <link type="text/css" rel="stylesheet" href="/css/global.css" />
    <link type="text/css" rel="stylesheet" href="/css/font.css" />
    <link type="text/css" rel="stylesheet" href="/css/common.css" />
    <link type="text/css" rel="stylesheet" href="/css/member.css" />
    <link type="text/css" href="/css/square/green.css" rel="stylesheet">
    <link type="text/css" href="/css/validate.css" rel="stylesheet">
    <link type="text/css" rel="stylesheet" href="/widget/css/ui.dialog.css" />
    <script type="text/javascript" src="/js/jquery.js"></script>
    <script type="text/javascript" src="/js/sha.js"></script>
	<script type="text/javascript" src="/js/icheck.js"></script>
    <script type="text/javascript" src="/js/jquery-validate.js"></script>
    <script type="text/javascript" src="/widget/js/ui.dialog.js"></script>
    <script type="text/javascript" src="/js/stickup.js"></script>
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
		$('#buyform').validate({
		    onFocus: function() {
		      this.parent().addClass('active');
		      return false;
		    },
		    onBlur: function() {
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
	    				$msg.text("请输入审核意见");	
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
	<div class="wrapper">
		<div class="grid-16-16 prelat">
			<div class="crumb-nav">
				<div class="backto">
					<div class="backrt">
						<a href="/home.htm">返回首页<i></i></a>
					</div>
				</div>
				<div class="crumb">
					<a href="/home.htm">交易大厅</a><span class="fa  fa-angle-right"></span><a href="/buy/list.htm">会员中心</a><span class="fa  fa-angle-right"></span><a href="/buy/list.htm">购买订单</a>
				</div>
			</div>
			<!-- main -->
			<div class="page" style="background-color:#fff">
				
				<div class="main-content">
					<div class="bd">
						
						<div class="page-module bsmenus">
							<div class="row" style="overflow:hidden;zoom:1;height:90px;margin-bottom:10px">
								<ul class="step-list">
									
									<li>
										<div class="linebox">
											<label for="">
												<p>订单生成</p>
												<i class="icon-step step-active">1</i>
												<span class="round">请在当日完成订单审核</span>
											</label>
										</div>
									</li>
									
								<c:choose>
						   			<c:when test="${operType=='A'}"> 
						   				<li class="active"> 
									</c:when>
							    	<c:otherwise> 
							   			<li class="mid">
							    	</c:otherwise>
								</c:choose>
										<div class="linebox">
											<label for="">
												<p>审核确认</p>
												<i class="icon-step">2</i>
												<span class="round">请在当日完成订单定金支付</span>
											</label>
										</div>
									</li>
								<c:choose>
						   			<c:when test="${operType=='P'}"> 
						   				<li class="active"> 
									</c:when>
							    	<c:otherwise> 
									<li>
									</c:otherwise>
								</c:choose>
										<div class="linebox">
											<label for="">
												<p>资金支付</p>
												<i class="icon-step">3</i>
												<span class="round">全款支付后请到交收管理中准备接收货物</span>
											</label>
										</div>
									</li>
									<li class="nobor">
										<div class="linebox noline">
											<label for="">
												<p>交收确认</p>
												<i class="icon-step"><i class="fa fa-angle-arraw">4</i></i>
												<span class="round">根据交收日期进行交收确认</span>
											</label>
										</div>
									</li>
								</ul>
							</div>
							
							<div class="sline"></div>
							
							<div class="row bsrow">
								<div class="hd">
									<h3>摘牌信息</h3>
								</div>
								<div class="bd">
									<table class="ui-table bstable">
										<tbody>
										
											<tr>
												<td width="120px"><label for="">摘牌单号：</label></td>
												<td colspan="5"> ${rspBody.deListNo}</td>	
											</tr>
											<tr>
												<td width="120px"><label for="">摘牌商品：</label></td>
												<td> ${rspBody.commName} <span style="color: #F40">${rspBody.title}</span> </td>												
												<c:forEach items="${rspBody.props}" var="prop" >												
													<c:if test="${prop.propName =='品牌' || prop.propName =='产地' }">
														<td><label for="">${prop.propName}：</label></td>
														<td>${prop.propVal}</td>
													
													</c:if>
												</c:forEach>
																	
											</tr>
											<tr>												
												<td><label for="">单&nbsp;&nbsp;&nbsp;&nbsp;价：</label></td>
												<td><fmt:formatNumber value="${rspBody.up}" type="currency" pattern="￥0.00元" />/${rspBody.uom}</td>
												<td><label for="">挂牌有效期：</label></td>
												<td>${rspBody.doe}</td>
												<td><label for="">交收日期：</label></td>
												<td>
													<c:choose>
											   			<c:when test="${fn:startsWith(rspBody.deliDate,'cycle:')}"> 
											   				全款支付后${fn:substringAfter(rspBody.deliDate,"cycle:")}天
														</c:when>
												    	<c:otherwise> 
															${rspBody.deliDate}
														</c:otherwise>
													</c:choose>
												
												</td>
											</tr>	
											
											<tr>
												<td><label for="">摘牌日期：</label></td>
												<td>${rspBody.dod}</td>
												<td><label for="">购买数量：</label></td>
												<td>${rspBody.vol}</td>
												<td><label for="">最后付款日：</label></td>
												<td>
												
													<c:choose>
											   			<c:when test="${fn:startsWith(rspBody.lastPD,'cycle:')}"> 
											   				合同签下后${fn:substringAfter(rspBody.lastPD,"cycle:")}天
														</c:when>
												    	<c:otherwise> 
															${rspBody.lastPD}
														</c:otherwise>
													</c:choose>
											</td>
											</tr>
											
											<tr>
												<td><label for="">付款方式：</label></td>
												<td>
													<c:if test="${rspBody.top =='S'}">
													仅付定金
													
													</c:if>
													<c:if test="${rspBody.top =='F'}">
													全款支付
													
													</c:if>
												
												</td>	
												<td><label for="">交货仓库：</label></td>
												<td>${rspBody.storage}</td>	
												
												<td><label for="">发票监管：</label></td>
												<td><c:choose>												
																<c:when test="${rspBody.invoice=='Y'}">  
																	交易平台监管发票
															    </c:when>
															    <c:otherwise> 
															   		交易平台不负责监管发票
															    </c:otherwise>
															</c:choose></td>																																	
											</tr>
											<tr>
												<td><label for="">费用明细：</label></td>
												<td colspan="5">
													<div class="box">
													<table class="ui-table table-primary">
														<caption>本次摘牌费用说明</caption>
														<thead>
																<tr>
																	<td>费用名称</td>
																	<td>交易类型</td>
																	<td>费用金额</td>
																	<td>当前状态</td>
																</tr>
															</thead>
															<tbody>
																<c:forEach items="${rspBody.costPays}" var="cost" >
																<tr>
																	<td>${cost.costName}</td>
																	<td>${cost.trTypeName}</td>
																	<td><fmt:formatNumber value="${cost.costAmt}" type="currency" pattern="￥0.00元" /></td>
																	<td>${cost.flagDesc}</td>
																</tr>
																</c:forEach>
														</tbody>
													</table>
													
													
													</div>
												
												</td>																																		
											</tr>
										</tbody>									                                               
									</table>				                                                                                                         
								</div>
							</div>
							
							<c:if test="${operType=='A'||operType=='P'}"> 
							
							<div class="row bsrow">
								<form name="buyform" id="buyform" method="post" action="/buy/wr/${link}.htm" >
								<input type="hidden" name="delistNo" value="${rspBody.deListNo}" />
								<input type="hidden" name="auditNo" id="auditNo" value="${rspBody.auditNo}">
								<div class="hd">
									<h3>填写并确认信息</h3>
								</div>	
								<div class="bd">
									<table class="ui-table bstable">
								 		<c:if test="${operType=='A'}">
	                                        <tr>
	                                            <td width="120px"><label for="">审核结论：</label></td>
	                                            <td><input type="radio" name="auditRet" id="auditRet" value="1" checked />审核通过   <input type="radio" name="auditRet" id="auditRet" value="0" />不审核通过 </td>                          
	                                        </tr>
	                                        
	                                         <tr>
	                                            <td><label for="">审核意见：</label></td>
	                                            <td>
	                                            	<div class="form_control">
	                                            		<input name="comment" id="comment" type="text" class="required"  data-tip="请输入审核意见" data-valid="maxGBLength:512" data-error="审核意见长度错误" />
	                                            	</div>
	                                            </td>
	                                        </tr>
	                                        
	                                        <tr>
											<td colspan="2">
											<div class="pa-btn-buy fn-fl ml60">
												<button class="btn-normal btn-buy">提交审核</button>
											</div>
											</td>
											</tr>
                                     	 </c:if>
                                     	 
                                     	 
                                     	 <c:if test="${operType=='P'}">
	                                         <tr>
	                                            <td width="120px"><label for="">资金密码：</label></td>
	                                            <td>
	                                            
	                                            <div class="form_control">
	                                            	<input type="password" name="fundPwd" id="fundPwd" class="required"  data-tip="请输入资金密码" data-valid="isNonEmpty||between:6-16" data-error="资金密码必填||密码长度错误" />
	                                            </div>
	                                            </td>
	                                        </tr>
	                                        
	                                        <tr>
											<td colspan="2">
												<div class="pa-btn-buy fn-fl ml60">
													<button class="btn-normal btn-buy">支付资金</button>
												</div>
												</td>
											</tr>
                                     	 </c:if>
										
									</table>
								</div>
								</form>
							</div>
							</c:if>
						</div>
					</div>
				</div>
			</div>
			<!-- main End -->

		</div>
	</div>
	<!-- wrapper End -->		

	<!-- footer -->
	<jsp:include page="../comm/footer.jsp" flush="true" />
	<!-- footer End -->
</body>
</html>