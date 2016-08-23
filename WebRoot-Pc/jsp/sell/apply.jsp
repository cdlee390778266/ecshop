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
  <link type="text/css" rel="stylesheet" href="/css/selecttags.css" >
  <link type="text/css" rel="stylesheet" href="/css/localcity.css" >
  <link type="text/css" rel="stylesheet" href="/css/combo.select.css" >
  <script type="text/javascript" src="/js/jquery.js"></script>
  <script type="text/javascript" src="/widget/js/ui.dialog.js"></script>
  <script type="text/javascript" src="/widget/js/ui.datepicker.js"></script>
  <script type="text/javascript" src="/js/jquery.combo.select.js"></script> 
  <script type="text/javascript" src="/js/icheck.js"></script>
  <script type="text/javascript" src="/js/jquery-validate.js"></script>
  <script type="text/javascript" src="/js/selecttags.js"></script>
  <script type="text/javascript" src="/js/localcity.js"></script>
  <script type="text/javascript" src="/js/stickup.js"></script>  
  <script type="text/javascript" src="/widget/laydate/laydate.js"></script> 
  <script type="text/javascript" src="/js/handle.sell.apply.js?v=${sessionScope.buildno}"></script>

  <script type="text/javascript" src="/widget/layer/layer.js"></script>

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
    <div class="crumb">
     <a href="/home.htm">交易大厅</a><span class="fa  fa-angle-right"></span><a href="/sell/apply.htm?active=enter">买卖菜单</a><span class="fa  fa-angle-right"></span><a href="/sell/apply.htm?active=enter">卖方挂牌</a>
   </div>
 </div>
 <div class="curmbs mart20 gp">
  <ul>
    <li><a href="javascript:void(0);" id="step1-notice"  ><img src="/images/step-success.png" alt="" style="display:inline-block;" />1 选择挂牌方式及具体商品</a></li>
    <li><a href="javascript:void(0);" id="step2-notice"><img src="/images/step-success.png" alt="" />2 填写商品属性</a></li>
    <li><a href="javascript:void(0);" id="step3-notice" ><img src="/images/step-success.png" alt="" />3 设置交易方式</a></li>
    <li><a href="javascript:void(0);" class="active" id="step4-notice"><img src="/images/step-success.png" alt="" />4 商品描述及确认挂牌</a></li>
  </ul>
</div>

<!-- main -->
<div class="page mart20">


  <div class="main-content">
   <div class="bd">
    <form action="/sell/apply.htm" method="post" enctype="multipart/form-data" id="sellApply">
      <input type="hidden" name="active" value="${active}" />

      <input type="hidden" name="busDate" id="busDate" value="${busDate}" />

      <div class="gp-box">
       <div class="gp-step1 mart40">
         <div class="gp-step1-main txtcenter">
           <div class="select-box">
             <div class="select-title">
               我是：
             </div>
             <div class="input-group">
               <div class="group-box sellerTxt">
                 <span class="val"><span>卖家</span><i></i></span>
                 <ul style="display: none;">
                   <li>卖家</li>
                   <li>买家</li>
                 </ul>
               </div>
             </div>
           </div>
           <div class="select-box">
             <div class="select-title">
               交易方式：
             </div>
             <div class="input-group ">
               <div class="group-box trade-promise">
                 <span class="val"><span >保证金</span><i></i></span>
                 <ul style="display: none;">
                   <li>保证金</li>
                 </ul>
               </div>
             </div>
           </div>
           <div class="select-box" id="product-dialog">
             <div class="select-title">
               商品选择：
             </div>
             <div class="select-txt" id="select-txt">请选择</div>
           </div>


         </div>

         <div class="gp-step-btn txtcenter">
           <span class="btn-next" >下一步</span>
         </div>
         <div class="selcomm" data-select style="display:none;"> </div>
         <!--  comm需要级联查询得到 -->
         <input type="hidden" name="commCode" id="commCode" value="" />
         <div class="seledmsg" style="color: #f00;float:right"></div>
       </div>

       <div class="gp-step2 mart40">
         <div class="step-bar">
           您选择了：
           <span  class="seller">未选择</span>
           <span  class="step-promise">未选择</span>
           <span  class="product">未选择</span>
           <a class="modify toStep1 " href="javascript:void(0);" >修改</a>
         </div>
         <div class="step-body">
           <h2>请填写商品信息</h2>
           <table id="J_AjaxProp" style="display:none">
             <tbody>
              <tr>                                                  
               <td>&nbsp;</td>
             </tr>
           </tbody>                                               
         </table>
         <div class="gp-step-btn txtcenter">
           <span class="btn-next" >下一步</span>
         </div>
       </div>




     </div>


     <div class="gp-step3 mart40">
       <div class="step-bar">
         您选择了：
         <span  class="seller">未选择</span>
         <span  class="step-promise">未选择</span>
         <span  class="product">未选择</span>
         <a class="modify toStep1" href="javascript:void(0);" >修改</a>
       </div>
       <div class="step-bar">
         您已选择了商品属性：
         <a class="modify toStep2" href="javascript:void(0);" >修改</a>
       </div>
       <div class="step-body">
         <table class="ui-table">
           <tbody>
            <tr>
             <td class="ctr">单价<span class="forceinput">(必填)</span>：</td>
             <td width="300px">


               <div class="form_control">
                <input type="text" name="unitPrice" id="unitPrice" class="required"  data-tip="请输入商品单价" data-valid="isNonEmpty||isNoNZeroMoney" data-error="单价必填||金额格式:1.00"/>
                <span class="priceunit_message"></span>

                <span class="valid_message"></span>

              </div>


                                            <!--  
                                            <div style="width: 119px;position: relative; margin-left: 9px; ">
                                                <input type="text" name="unitPrice" id="unitPrice"  style="width:119px;height:24px; padding: 5px 5px; border: 1px solid #ECECEC;"  />
                                                <span class="priceunit_message" style="position: absolute;left: 70px; top: 5px; font-size: 14px; width: 60px;"></span>                                              
                                                <span class="valid_message" style="position: absolute;left: 153px;top: 5px; font-size: 14px;width: 125px;"></span>
                                                
                                            </div>
                                          -->

                                        </td>

                                        <td class="ctr" width="125px">一口价：</td>
                                        <td>
                                         <span class="ml20 mr20"><input type="radio" name="DfpFlag" value="F" checked disabled="disabled" />是</span>

                                         <input type="hidden" name="fpFlag" value="F" />
                                            <!--  
                                            <span><input type="radio" name="fpFlag" value="V" />否</span>
                                          -->
                                        </td>
                                      </tr>

                                      <tr>

                                       <td class="ctr">总量<span class="forceinput">(必填)</span>：</td>
                                       <td>                                         
                                        <div class="form_control">
                                         <input type="text" name="qty" id="qty" class="required"  data-tip="请输入商品总量" data-valid="isNonEmpty||plusInt" data-error="总量必填||总量必须为整数" />
                                         <span class="unit_message"></span>
                                       </div> 
                                     </td>

                                     <td class="ctr">是否整单：</td>
                                     <td>
                                       <div class="J_WholeFlag">
                                        <span class="ml20 mr20"><input type="radio" name="wholeFlag" id="w_flag" value="W"  />是</span>
                                        <span><input type="radio" name="wholeFlag" id="s_flag" value="S" checked />否</span>
                                      </div>

                                    </td>
                                  </tr>
                                  <tr>

                                   <td class="ctr">起订数量<span class="forceinput">(必填)</span>：</td>
                                   <td>
                                    <div class="form_control">
                                     <input type="text" name="moq" id="moq" class="required"  data-tip="请输入商品起订数量" data-valid="isNonEmpty||plusInt" data-error="起订量必填||起订量必须为整数" />
                                     <span class="unit_message"></span>
                                   </div>   
                                 </td>                                
                                 <td class="ctr">递增数量<span class="forceinput">(必填)</span>：</td>
                                 <td>
                                  <div class="form_control">
                                   <input type="text" name="incrNum" id="incrNum" class="required"  data-tip="请输入商品递增量" data-valid="isNonEmpty||plusInt" data-error="递增量必填||递增量必须为整数"  />
                                   <span class="unit_message"></span>
                                 </div>
                               </td>
                             </tr>
                             <tr>
                               <td class="ctr">挂牌有效期<span class="forceinput">(必填)</span>：</td>
                               <td>
                                <div class="form_control">
                                                    <!-- 
                                                    <input type="text" name="doe" id="doe" datepicker data-date-format="yyyy-mm-dd" data-auto-close="true" class="required"  data-tip="请选择挂牌有效期" data-valid="isNonEmpty||isDate||after:${busDate}" data-error="挂牌有效期必填||有效期格式不正确||有效期小于业务日期${busDate}"  />
                                                  -->
                                                  <input type="text" name="doe" id="doe" data-min="${busDate}" 

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
                                      <tr>
                                        <td class="ctr">最后付款日<span class="forceinput">(必填)</span>：</td>
                                        <td>合同签定后 <input type="text" name="lastPD" id="lastPD" maxlength="4" style="width:40px; height:24px; padding: 5px 5px; border: 1px solid #ECECEC;"  autocomplete="off"  />天
                                         <span class="valid_message"></span>
                                       </td>
                                       <td class="ctr">最后交收日<span class="forceinput">(必填)</span>：</td>
                                       <td>
                                        全款支付后 <input type="text" name="deliDate" id="deliDate" maxlength="4" style="width:40px; height:24px; padding: 5px 5px; border: 1px solid #ECECEC;" autocomplete="off"  />天
                                        <span class="valid_message"></span>
                                      </td>
                                    </tr>


                                    <tr>
                                     <td class="ctr">交收仓库<span class="forceinput">(必选)</span>：</td>
                                     <td>
                                      <select style="width:230px;margin-left:10px" name="storage" id="storage">
                                        <option value="" selected>请选择</option>

                                      </select>
                                      <span class="valid_message"></span>
                                    </td>
                                    <td class="ctr">平台监管发票：</td>
                                    <td>
                                     <span class="ml20 mr20"><input type="radio" name="invoice" value="Y" checked />需要</span>
                                     <span><input type="radio" name="invoice" value="N" />不需要</span>

                                   </td>
                                 </tr> 

                                 <tr>
                                   <td class="ctr">是否指定摘牌方：</td>
                                   <td colspan="3">
                                     <span class="ml20 mr20"><input type="radio" name="delist" value="O" checked />不指定</span>
                                     <span><input type="radio" name="delist" value="A" />指定</span>
                                     <span id="memdelistlink"></span> 
                                     <span id="memdelistmsg"></span>                  
                                     <input type="hidden" name="memdelists" id="memdelists" />
                                   </td>
                                 </tr> 

                               </tbody>
                             </table>
                           </div>
                           <div class="gp-step-btn txtcenter">
                             <span class="btn-next" >下一步</span>
                           </div>
                         </div>

                         <div class="gp-step4 mart40">
                           <div class="step-bar">
                             您选择了：
                             <span  class="seller">未选择</span>
                             <span  class="step-promise">未选择</span>
                             <span  class="product">未选择</span>
                             <a class="modify toStep1" href="javascript:void(0);" >修改</a>
                           </div>
                           <div class="step-bar">
                             您已选择了商品属性：
                             <a class="modify toStep2" href="javascript:void(0);">修改</a>
                           </div>
                           <div class="step-bar">
                             您已设置交易方式:
                             <a class="modify toStep3" href="javascript:void(0);" >修改</a>
                           </div>

                           <div class="step-body">

                             <table class="ui-table">
                               <tbody>


                                 <tr>
                                  <td class="ctr">商品标题：</td>
                                  <td>
                                    <div class="form_control">
                                     <input type="text" name="title" class="required"  data-tip="请输入描述标题" data-valid="maxGBLength:128" data-error="描述标题长度不超过128"  />
                                   </div>
                                 </td> 
                                 <td  class="ctr">商品描述：</td>
                                 <td>                                           
                                   <textarea name="detail" id="detail" cols="45" rows="6"></textarea>
                                   <div class="detailmsg" style="color: #f00;"></div>
                                 </td>                          
                               </tr>                                                                                       
                               <tr>
                                 <td  class="ctr">商品描述图：</td>
                                 <td colspan="3">
                                  <table>
                                    <tr>
                                     <td>
                                      <div class="clearfix">
                                       <img id="titfilePre" width="120px" height="120px" style="display: none;float:left"  />
                                       <span class="btn-upload_unselected fileinput ml10">
                                        <span>选择标题图片</span>
                                        <input type="file" name="titfile" id="titfile" accept="image/jpg,image/jpeg,image/png,image/bmp,image/gif" />
                                      </span>
                                    </div>
                                  </td>
                                  <td>
                                    <div class="clearfix">
                                     <img id="ctxPic0Pre" width="120px" height="120px" style="display: none;float:left"  />
                                     <span class="btn-upload_unselected fileinput ml10">
                                       <span>选择商品描述首图</span>
                                       <input type="file"  name="ctxfile" id="ctxPic0" accept="image/jpg,image/jpeg,image/png,image/bmp,image/gif" />
                                     </span>
                                   </div>
                                 </td>

                                 <td>
                                   <div class="clearfix">
                                     <img id="ctxPic1Pre" width="120px" height="120px" style="display: none;float:left"  />     
                                     <span class="btn-upload_unselected fileinput ml10">
                                       <span>选择商品描述次图</span>
                                       <input type="file" name="ctxfile" id="ctxPic1" accept="image/jpg,image/jpeg,image/png,image/bmp,image/gif" />
                                     </span>
                                   </div>

                                 </td>
                                 <td>
                                  <div class="clearfix">
                                    <img id="ctxPic2Pre" width="120px" height="120px" style="display: none;float:left"  />                                                    
                                    <span class="btn-upload_unselected fileinput ml10">
                                     <span>选择商品描述尾图</span>
                                     <input type="file" name="ctxfile" id="ctxPic2" accept="image/jpg,image/jpeg,image/png,image/bmp,image/gif" />
                                   </span>
                                 </div>

                               </td>
                             </tr>
                           </table>
                         </td>
                       </tr>
                                         <!--  
                                            <tr>
                                            <td class="ctr"></td>
                                            <td colspan="3">
                                            <div class="clearfix">
                                                   <img id="ctxPic0Pre" width="120px" height="120px" style="display: none;float:left"  />
                                                    <span class="btn-upload_unselected fileinput ml10">
                                                     <span>选择商品描述首图</span>
                                                     <input type="file"  name="ctxfile" id="ctxPic0" accept="image/jpg,image/jpeg,image/png,image/bmp,image/gif" />
                                                </span>
                                            </div>
                                            </td>
                                         </tr>
                                         <tr>
                                            <td class="ctr"></td>
                                            <td colspan="3">
                                            <div class="clearfix">
                                                   <img id="ctxPic1Pre" width="120px" height="120px" style="display: none;float:left"  />     
                                                <span class="btn-upload_unselected fileinput ml10">
                                                     <span>选择商品描述次图</span>
                                                     <input type="file" name="ctxfile" id="ctxPic1" accept="image/jpg,image/jpeg,image/png,image/bmp,image/gif" />
                                                </span>
                                             </div>
                                            </td>
                                        </tr>                                        
                                        <tr>
                                            <td class="ctr"></td>
                                            <td colspan="3">
                                            <div class="clearfix">
                                                  <img id="ctxPic2Pre" width="120px" height="120px" style="display: none;float:left"  />                                                    
                                                  <span class="btn-upload_unselected fileinput ml10">
                                                     <span>选择商品描述尾图</span>
                                                     <input type="file" name="ctxfile" id="ctxPic2" accept="image/jpg,image/jpeg,image/png,image/bmp,image/gif" />
                                                </span>
                                                </div>
                                            </td>
                                        </tr>                                                                                                       
                                      -->
                                    </tbody>
                                  </table>
                                  <div class="pa-btn-sell fn-fl">                                    
                                    <button class="btn-normal btn-sell" style="margin-left: 200px;">我要挂牌</button>                                             
                                  </div>
                                </div>
                              </div>
                            </div>



                            <div class="page-module warehouse">
                             <div class="row">
                              <div class="bd">




                                <div class="pa-action clearfix mt10">

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