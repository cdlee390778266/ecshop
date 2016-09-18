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
  <link rel="shortcut icon" href="/mobile/images/icon/favicon.ico" />
  <link type="text/css" rel="stylesheet" href="/mobile/css/font.css" />
  <link  type="text/css"rel="stylesheet" href="/mobile/widget/css/ui.dialog.css"/>
  <link type="text/css" rel="stylesheet" href="/mobile/css/square/green.css">
  <link type="text/css" rel="stylesheet" href="/mobile/css/validate.css" >
  <script type="text/javascript" src="/mobile/js/jquery.js"></script>
  <script type="text/javascript" src="/mobile/js/handlebars.js"></script>
  <script type="text/javascript" src="/mobile/js/ui.tpl.js"></script>
  <script type="text/javascript" src="/mobile/widget/js/ui.dialog.js"></script>
  <script type="text/javascript" src="/mobile/js/ui.tips.js"></script>
  <script type="text/javascript" src="/mobile/js/sha.js"></script>
  <script type="text/javascript" src="/mobile/js/icheck.js"></script>
  <script type="text/javascript" src="/mobile/js/jquery-validate.js"></script>
  <script type="text/javascript" src="/mobile/js/stickup.js"></script>
  <script type="text/javascript" src="/mobile/js/handle.m.manger.js?v=${sessionScope.buildno}"></script>

  <jsp:include page="../comm/mobile.jsp" flush="true" />

  <title>操作员设置</title>
  <style>
    .typelist {margin-top: -4px;}
    .authbox{position:relative;}
    .authoritys{display:none;padding:10px;background-color:#fff;border:solid 1px #ccc;width:150px;position:absolute;top:37px;right:0; text-align: left;border-radius: 4px;}
    .authoritys input.auth-ck,.authoritys input.verify-ck{vertical-align: middle;}
    .authoritys dt{display:block;margin-bottom: 10px;}
    .authbox:hover .authoritys{display:block;}
    .authoritys dd{margin-left: 10px;margin-bottom: 10px;}
    .authoritys dd>div{float:right;position:relative;top:-4px;}
    .authbox .btn{position: relative;z-index: 2;}
  </style>
</head>
<body class="drawer drawer-left">

  <div class="fixed-wrapper"> 

   <!-- header -->
   <jsp:include page="../comm/header.jsp" flush="true" />
   <!-- header End -->
 </div>

 <!-- wrapper -->

 <div class="main safe examine " >
  <div class="header">
   <div class="header-left"><a href="javascript:history.back(-1);" class="glyphicon glyphicon-menu-left"></a></div>
   <div class="logo ">
     操作员设置
   </div>
 </div>
 <div class="container-fluid" id="J_operater">
  <div class="row marb60">

    <c:forEach items="${operlist}" var="oper" varStatus="obj">
    <div class="cox-xs-12 bgwhite operBox " >
      <div class="media">
        <div class="media-left media-middle padlr15 ">
          <a href="#">
            <img class="media-object" src="/mobile/images/operation.png" alt="...">
          </a>
        </div>
        <div class="media-body ">
          <h4 class="media-heading ">${oper.operName} 
            <span class="fc999 ">
              <c:if test="${oper.status == 100}">
              已注销
            </c:if>
            <c:if test="${oper.status != 100}">
            活动
          </c:if> </span>
        </h4>
        ${oper.operID}
      </div>
      <div class="media-body">
       <c:if test="${oper.status != 100}">
       <a href="javascript: void(0)" class="btn btn-success J_modify" data-href="modify_template" data-id="${oper.operID}">修改</a>
     </c:if>
     <c:if test="${oper.status != 100}">
     <c:if test="${oper.operID !=  sessionScope.userinfo.operID}">
     <a href="javascript: void(0)" class="btn btn-success J_delete" data-id="${oper.operID}">注销</a>
   </c:if>
 </c:if>
 <c:if test="${oper.status == 100}">
 <c:if test="${oper.operID !=  sessionScope.userinfo.operID}">
 <a href="javascript: void(0)" class="btn btn-success J_active" data-id="${oper.operID}">启用</a>
</c:if>
</c:if>

</div>
</div>
</div>
</c:forEach>

<div class="col-xs-12 mart15">
  <a href="javascript: void(0)" id="J_add_operator" data-href="add_template" class="btn btn-success btn-block"><span>+</span>新增操作员</a>
</div>
</div>
</div>

</div>


<!-- wrapper End -->

<!-- footer -->
<jsp:include page="../comm/footer.jsp" flush="true" />
<!-- footer End -->

<div class="mask" id="masker"></div>
<!-- 增加 -->
<script id="add_template" type="text/x-handlebars-template"> 
  <div class="updialog d-add-role w700" id="dialog_add_template">
    <div class="hd">
      <span class="close dclose ic"></span>
      <h3>新增操作员</h3>
    </div>
    <div class="main safe examine addoperation">
      <div class="container-fluid">
        <div class="duserinfo">
          <div class="bd mt10 warehouse">
            <div class="uitem">
              <form id="frmUpload" enctype="multipart/form-data" class="form-horizontal up-frmUpload">

                <div class="row">
                  <div class="col-xs-12 file bgwhite bordertb padtb15">

                   <img id="operPhotoPre" src="" width="120px" height="120px"  onerror="this.src='/mobile/images/portrait.jpg'" class="img-rounded">
                   <span class="btn-upload fileinput inputTxt fc999">
                    <span>仅支持JPEG、GIF、PNG、BMP格式，文件小于4M</span>
                    <input type="file" name="operPhoto" id="operPhoto" accept="image/jpg,image/jpeg,image/png,image/bmp,image/gif" class="form-control txtright" />
                  </span>
                </div>

                <div class="input-group    form-group-lg">
                  <span class="input-group-addon ">账户名称</span>
                  <input  type="text" name="operName"  class="required form-control txtright"  data-tip="请输入操作员名称" data-valid="isNonEmpty||maxGBLength:128" data-error="名称不能为空||名称过长"/>
                </div>

                <div class="input-group    form-group-lg">
                  <span class="input-group-addon ">性别</span>
                  <div class="form-control txtright">
                   <label class="mr20"><input type="radio" checked name="operSex" value="1"/>男</label>
                   <label><input type="radio" name="operSex" value="2"/>女</label>
                 </div>
               </div>
               <div class="input-group form-group-lg">
                <span class="input-group-addon ">支付权限</span>
                <div class="form-control txtright">
                 <label class="mr20"><input type="radio" checked name="payright" value="0"/>无</label>
                 <label><input type="radio" name="payright" value="1"/>有</label>
               </div>
             </div>

             <div class="input-group form-group-lg">
              <span class="input-group-addon ">管理权限</span>
              <div class="form-control txtright">
                <label class="mr20"><input type="radio" checked name="mgrright" value="0"/>无</label>
                <label><input type="radio" name="mgrright" value="1"/>有</label>
              </div>
            </div>

            <div class="input-group form-group-lg" style="z-index:8">
              <span class="input-group-addon ">类别</span>
              <div class="form-control txtright">
               <ul class="typelist">
                <li>
                  <div class="authbox">
                    <span class="btn btn-default">交易<i class="fa fa-angle-down glyphicon glyphicon-menu-down"></i></span>
                    {{#each right}}
                    <dl class="authoritys">
                      <dt>{{name}}</dt>
                      {{#each txRights}}   
                      <dd><input type="checkbox"  class="auth-ck" value="{{code}}" data-has="{{has}}"/>{{name}}</dd>
                      {{/each}}   
                    </dl>
                    {{/each}}
                  </div>
                </li>
                <li><div class="authbox">
                  <span class="btn btn-default">审核<i class="fa fa-angle-down glyphicon glyphicon-menu-down"></i></span>
                  {{#each right}}
                  <dl class="authoritys">
                    <dt>{{name}}</dt>
                    {{#each adRights}}   
                    <dd><input type="checkbox"  class="verify-ck" value="{{code}}" data-has="{{has}}"/>{{name}}</dd>
                    {{/each}}   
                  </dl>
                  {{/each}}
                </div></li>
              </ul>
            </div>
          </div>

          <div class="input-group form-group-lg">
            <span class="input-group-addon ">默认登录密码：</span>
            <input  type="password" name="apwd" id="apwd" onpaste="return false" oncopy="return false" class="required form-control txtright"  data-tip="请输入登录密码" data-valid="isNonEmpty||between:6-16" data-error="默认密码不能为空||密码长度为6-16位"/>
          </div>
          <div class="input-group form-group-lg">
            <span class="input-group-addon ">确认登录密码：</span>
            <input  type="password" name="cpwd" id="cpwd"  onpaste="return false" oncopy="return false" class="required form-control txtright"  data-tip="请输入确认密码" data-valid="isNonEmpty||between:6-16" data-error="确认密码不能为空||密码长度为6-16位"/>
          </div>

          <div class="col-xs-12 martb40">
           <button class="cbtn cpublish btn btn-success btn-block btn-lg" id="J_add_opter">添加</button>
         </div>

       </div>
     </form>
     <div class="seledmsg" style="text-align:center;color:#c0171e"></div>  
   </div>
 </div>

</div>
</div>
</div>
</div>
</script>


<script id="modify_template" type="text/x-handlebars-template">
  <div class="updialog d-add-role w700" id="dialog_modify_template">
    <div class="hd">
      <span class="close dclose ic"></span>
      <h3>修改操作员</h3>
    </div>
    <div class="main safe examine addoperation">
      <div class="container-fluid">
        <div class="duserinfo">
          <div class="bd mt10 warehouse">
            <div class="uitem">
              <form id="frmUpload" enctype="multipart/form-data">
                <input type="hidden" value="{{info.operID}}" name="operId" class="inp w180" />


                <div class="row">

                  <div class="col-xs-12 file bgwhite bordertb padtb15">
                    <img id="operPhotoPre" src="{{info.operPhoto}}" width="120px" height="120px" style="display: block;float:left" onerror="this.src='/mobile/images/portrait.jpg'">
                    <span class="btn-upload fileinput inputTxt fc999">
                      <span>仅支持JPEG、GIF、PNG、BMP格式，文件小于4M</span>
                      <input type="file" name="operPhoto" id="operPhoto" accept="image/jpg,image/jpeg,image/png,image/bmp,image/gif" />
                    </span>
                  </div>

                  <div class="input-group    form-group-lg">
                    <span class="input-group-addon ">账户名称</span>
                    <input  type="text" name="operName"  class="required form-control txtright"  value="{{info.operName}}" data-tip="请输入操作员名称" data-valid="isNonEmpty||maxGBLength:128" data-error="名称不能为空||名称长度过长"/>
                  </div>

                  <div class="input-group    form-group-lg">
                    <span class="input-group-addon ">性别</span>
                    <div class="form-control txtright">
                     {{sex info.operSex}}
                   </div>
                 </div>

                 <div class="input-group form-group-lg">
                  <span class="input-group-addon ">支付权限</span>
                  <div class="form-control txtright">
                    {{#if hasPayRight}}
                    <label class="mr20"><input type="radio"  name="payright" value="0"/>无</label>
                    <label><input type="radio" name="payright" checked value="1"/>有</label>
                    {{else}}
                    <label class="mr20"><input type="radio" name="payright"  checked value="0"/>无</label>
                    <label><input type="radio" name="payright" value="1"/>有</label>
                    {{/if}}
                  </div>
                </div>

                <div class="input-group form-group-lg">
                  <span class="input-group-addon ">管理权限</span>
                  <div class="form-control txtright">
                    {{#if hasMgrRight}}
                    <label class="mr20"><input type="radio"  name="mgrright" value="0"/>无</label>
                    <label><input type="radio" name="mgrright" checked value="1"/>有</label>
                    {{else}}
                    <label class="mr20"><input type="radio" checked name="mgrright" value="0"/>无</label>
                    <label><input type="radio" name="mgrright"  value="1"/>有</label>
                    {{/if}}
                  </div>
                </div>

                <div class="input-group form-group-lg" style="z-index:8">
                  <span class="input-group-addon ">类别</span>
                  <div class="form-control txtright">
                   <ul class="typelist">
                    <li>
                      <div  class="authbox">
                        <span class="btn btn-default">交易<i class="fa fa-angle-down glyphicon glyphicon-menu-down"></i></span>
                        {{rightshow "tx" right}}
                      </div>
                    </li>
                    <li><div class="authbox">
                     <span>审核<i class="fa fa-angle-down"></i></span>
                     {{rightshow "ad" right}}
                   </div>
                 </li>
               </ul>
             </div>
           </div>

           <div class="input-group form-group-lg">
            <span class="input-group-addon "><input type="checkbox" id="reset" name="reset">重置密码</span> 
            <div id="J_Reset"></div>
            <div id="J_ConfReset"></div>
          </div>

           <div class="col-xs-12 martb40">
           <button class="cbtn cpublish btn btn-success btn-block btn-lg" id="J_modify_opter">修改</button>
         </div>

        </div>
      </div> 
</form>
<div class="seledmsg" style="text-align:center;color: #f00;"></div>
<div class="pt20" style="margin-bottom:8px; float:right;"><button class="cbtn cpublish" id="J_modify_opter">修&nbsp;&nbsp;改</button></div>  
</div>
</div>

</div>
</div>
</div>
</div>
</script>
</body>
</html>