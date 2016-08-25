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
    <link rel="shortcut icon" href="/normal/images/icon/favicon.ico" />
    <link type="text/css" rel="stylesheet" href="/normal/css/global.css" />
    <link type="text/css" rel="stylesheet" href="/normal/css/font.css" />
    <link type="text/css" rel="stylesheet" href="/normal/css/common.css" />
    <link type="text/css" rel="stylesheet" href="/normal/css/member.css" />
    <link  type="text/css"rel="stylesheet" href="/normal/widget/css/ui.dialog.css"/>
    <link type="text/css" rel="stylesheet" href="/normal/css/square/green.css">
    <link type="text/css" rel="stylesheet" href="/normal/css/validate.css" >
    <script type="text/javascript" src="/normal/js/jquery.js"></script>
    <script type="text/javascript" src="/normal/js/handlebars.js"></script>
    <script type="text/javascript" src="/normal/js/ui.tpl.js"></script>
    <script type="text/javascript" src="/normal/widget/js/ui.dialog.js"></script>
    <script type="text/javascript" src="/normal/js/ui.tips.js"></script>
    <script type="text/javascript" src="/normal/js/sha.js"></script>
    <script type="text/javascript" src="/normal/js/icheck.js"></script>
    <script type="text/javascript" src="/normal/js/jquery-validate.js"></script>
    <script type="text/javascript" src="/normal/js/stickup.js"></script>
    <script type="text/javascript" src="/normal/js/handle.m.manger.js?v=${sessionScope.buildno}"></script>
    
    <title>操作员设置</title>
    <style>
        .authbox{position:relative;}
        .authoritys{display:none;padding:5px;background-color:#fff;border:solid 1px #ccc;width:150px;position:absolute;top:15px;left:0;}
        .authoritys input.auth-ck,.authoritys input.verify-ck{vertical-align: middle;}
        .authoritys dt{display:block;}
        .authbox:hover .authoritys{display:block;}
        .authoritys dd{width:50%;float:left;}
    </style>
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
  <div class="grid-16-16">
   <div class="crumb-nav">
    <div class="crumb">
     <a href="/home.htm">交易大厅</a><span class="fa  fa-angle-right"></span><a href="/member/home.htm">我的账户</a><span class="fa  fa-angle-right"></span>操作员设置
 </div>
</div>
<!-- main -->
<div class="page">
   <jsp:include page="../comm/left.jsp" flush="true" />

   <div class="main-content">
     <div class="bd">
      <div class="page-module">
       <div class="row">
           <div class="hd gp-h3">
            <h3>操作员列表 <a href="javascript: void(0)" id="J_add_operator" data-href="add_template"><span>+</span>新增操作员</a></h3>
        </div>
        <div id="J_operater" class="padt20">

            <c:forEach items="${operlist}" var="oper" varStatus="obj">
            <div class="oper-box">
             <p>编号：${oper.operID}</p>
             <p>姓名：${oper.operName}</p>
             <p>状态：
                 <c:if test="${oper.status == 100}">
                 已注销
             </c:if>
             <c:if test="${oper.status != 100}">
             <span class="red">活动</span>
         </c:if>     
     </p>
     <p class="txtright">
         <c:if test="${oper.status != 100}">
         <a href="javascript: void(0)" class="oper-modify J_modify" data-href="modify_template" data-id="${oper.operID}">修改</a>
     </c:if>
     <c:if test="${oper.status != 100}">
     <c:if test="${oper.operID !=  sessionScope.userinfo.operID}">
     <a href="javascript: void(0)" class="oper-delete J_delete" data-id="${oper.operID}">注销</a>
 </c:if>
</c:if>
<c:if test="${oper.status == 100}">
<c:if test="${oper.operID !=  sessionScope.userinfo.operID}">
<a href="javascript: void(0)" class="oper-active J_active" data-id="${oper.operID}">启用</a>
</c:if>
</c:if>

</p>
</div>
</c:forEach>



<!-- <div class="operlist-item">
 <div class="box">
  <table class="ui-table table-primary">
   <thead>
    <tr>
     <td>编号</td>
     <td>姓名</td>
     <td>状态</td>
     <td colspan="2">操作</td>
 </tr>
</thead>
<tbody>								
    <c:forEach items="${operlist}" var="oper" varStatus="obj">
    <c:if test="${obj.count%2=='0'}">
    <tr class="theader">
    </c:if>

    <c:if test="${obj.count%2!='0'}">
    <tr>
    </c:if>
    <td class="pl15">${oper.operID}</td>
    <td>${oper.operName}</td>

    <td>
      <c:if test="${oper.status == 100}">
      已注销
  </c:if>
  <c:if test="${oper.status != 100}">
  活动
</c:if>														
</td>
<td>
  <c:if test="${oper.status != 100}">
  <a href="javascript: void(0)" class="cor-yellow J_modify" data-href="modify_template" data-id="${oper.operID}">修改</a>
</c:if>

</td>
<td>
  <c:if test="${oper.status != 100}">
  <c:if test="${oper.operID !=  sessionScope.userinfo.operID}">
  <a href="javascript: void(0)" class="cor-yellow J_delete" data-id="${oper.operID}">注销</a>
</c:if>
</c:if>
<c:if test="${oper.status == 100}">
<c:if test="${oper.operID !=  sessionScope.userinfo.operID}">
<a href="javascript: void(0)" class="cor-yellow J_active" data-id="${oper.operID}">启用</a>
</c:if>
</c:if>
</td>
</tr>
</c:forEach>
</tbody>
</table>
</div>
<div class="fn-fr mr30" style="margin-top: 20px;">

</div>
</div> -->
</div>


</div>
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

<div class="mask" id="masker"></div>
<!-- 增加 -->
<script id="add_template" type="text/x-handlebars-template">
    <div class="updialog d-add-role w700" id="dialog_add_template">
        <div class="hd">
            <span class="close dclose ic"></span>
            <h3>新增操作员</h3>
        </div>
        <div class="bd">
            <div class="d-content">
                <div class="duserinfo">
                    <div class="bd mt10 warehouse">
                        <div class="uitem">
                            <form id="frmUpload" enctype="multipart/form-data">
                                <table class="ui-table">
                                    <tr>
                                        <td class="ctr" width="120px">操作员名称：</td>
                                        <td>
                                           <div class="form_control">
                                            <input  type="text" name="operName"  class="required"  data-tip="请输入操作员名称" data-valid="isNonEmpty||maxGBLength:128" data-error="名称不能为空||名称过长"/>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="ctr">性别：</td>
                                    <td>
                                        <label class="mr20"><input type="radio" checked name="operSex" value="1"/>男</label>
                                        <label><input type="radio" name="operSex" value="2"/>女</label>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="ctr">用户头像：</td>
                                    <td>
                                       <div class="clearfix">
                                        <img id="operPhotoPre" src="" width="120px" height="120px" style="display: block;float:left" onerror="this.src='/images/portrait.jpg'">
                                        <span class="btn-upload fileinput ml10">
                                            <span>选择头像</span>
                                            <input type="file" name="operPhoto" id="operPhoto" accept="image/jpg,image/jpeg,image/png,image/bmp,image/gif" />
                                        </span>
                                        <span class="img-support">
                                         <p>仅支持JPEG、GIF、PNG、BMP格式，文件小于4M</p>
                                     </span>
                                 </div>

                             </td>
                         </tr>
                         <tr>
                            <td class="ctr">支付权限：</td>
                            <td>
                                <label class="mr20"><input type="radio" checked name="payright" value="0"/>无</label>
                                <label><input type="radio" name="payright" value="1"/>有</label>
                            </td>
                        </tr>
                        {{#if supportMgr}}
                        <tr>
                            <td class="ctr">管理权限：</td>
                            <td>
                                <label class="mr20"><input type="radio" checked name="mgrright" value="0"/>无</label>
                                <label><input type="radio" name="mgrright" value="1"/>有</label>
                            </td>
                        </tr>
                        {{/if}}
                        <tr>
                            <td class="ctr">默认登录密码：</td>
                            <td>
                               <div class="form_control">
                                <input  type="password" name="apwd" id="apwd" onpaste="return false" oncopy="return false" class="required"  data-tip="请输入登录密码" data-valid="isNonEmpty||between:6-16" data-error="默认密码不能为空||密码长度为6-16位"/>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td class="ctr">确认登录密码：</td>
                        <td>
                           <div class="form_control">
                            <input  type="password" name="cpwd" id="cpwd"  onpaste="return false" oncopy="return false" class="required"  data-tip="请输入确认密码" data-valid="isNonEmpty||between:6-16" data-error="确认密码不能为空||密码长度为6-16位"/>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td class="ctr" valign="top">
                      类别：
                  </td>
                  <td>
                    <ul class="typelist">
                        <li>
                            <div class="authbox">
                                <span>交易<i class="fa fa-angle-down"></i></span>
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
                            <span>审核<i class="fa fa-angle-down"></i></span>
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
                </td>
            </tr>
        </table>
    </form>
    <div class="seledmsg" style="text-align:center;color:#c0171e"></div>
    <div class="pt20" style="margin-bottom:8px; float:right;"><button class="cbtn cpublish" id="J_add_opter">添&nbsp;&nbsp;加</button></div>  
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
        <div class="bd">
            <div class="d-content">
                <div class="duserinfo">
                    <div class="bd mt10 warehouse">
                        <div class="uitem">
                            <form id="frmUpload" enctype="multipart/form-data">
                                <input type="hidden" value="{{info.operID}}" name="operId" class="inp w180" />
                                <table class="ui-table">
                                    <tr>
                                        <td class="ctr" width="120px">账户名称：</td>
                                        <td>
                                           <div class="form_control">
                                            <input  type="text" name="operName"  class="required"  value="{{info.operName}}" data-tip="请输入操作员名称" data-valid="isNonEmpty||maxGBLength:128" data-error="名称不能为空||名称长度过长"/>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="ctr">性别：</td>
                                    <td>
                                       {{sex info.operSex}}                                            
                                   </td>
                               </tr>
                               <tr>
                                <td class="ctr">用户头像：</td>
                                <td>
                                   <div class="clearfix">
                                    <img id="operPhotoPre" src="{{info.operPhoto}}" width="120px" height="120px" style="display: block;float:left" onerror="this.src='/normal/images/portrait.jpg'">
                                    <span class="btn-upload fileinput ml10">
                                     {{#if info.operPhoto}}
                                     <span>替换头像</span>
                                     {{else}}
                                     <span>选择头像</span>
                                     {{/if}}
                                     <input type="file" name="operPhoto" id="operPhoto" accept="image/jpg,image/jpeg,image/png,image/bmp,image/gif" />
                                 </span>
                                 <span class="img-support">
                                     <p>仅支持JPEG、GIF、PNG、BMP格式，文件小于4M</p>
                                 </span>
                             </div>

                         </td>
                     </tr>
                     <tr>
                        <td class="ctr">支付权限：</td>
                        <td>
                           {{#if hasPayRight}}
                           <label class="mr20"><input type="radio"  name="payright" value="0"/>无</label>
                           <label><input type="radio" name="payright" checked value="1"/>有</label>
                           {{else}}
                           <label class="mr20"><input type="radio" name="payright"  checked value="0"/>无</label>
                           <label><input type="radio" name="payright" value="1"/>有</label>
                           {{/if}}
                       </td>
                   </tr>
                   {{#if supportMgr}}
                   <tr>
                    <td class="ctr">管理权限：</td>
                    <td>
                       {{#if hasMgrRight}}
                       <label class="mr20"><input type="radio"  name="mgrright" value="0"/>无</label>
                       <label><input type="radio" name="mgrright" checked value="1"/>有</label>
                       {{else}}
                       <label class="mr20"><input type="radio" checked name="mgrright" value="0"/>无</label>
                       <label><input type="radio" name="mgrright"  value="1"/>有</label>
                       {{/if}}
                   </td>
               </tr>
               {{/if}}
               <tr>
                <td class="ctr "><input type="checkbox" id="reset" name="reset">重置密码</td>										
                <td class="reset-pwd">											
                   <div id="J_Reset">

                   </div>
                   <div id="J_ConfReset">

                   </div>
               </td>
           </tr>
           <tr>
            <td class="ctr" valign="top">
             类别：
         </td>
         <td>
            <ul class="typelist">
                <li>
                    <div  class="authbox">
                        <span>交易<i class="fa fa-angle-down"></i></span>
                        {{rightshow "tx" right}}
                    </div>

                </li>
                <li><div class="authbox">
                 <span>审核<i class="fa fa-angle-down"></i></span>
                 {{rightshow "ad" right}}
             </div>
         </li>
     </ul>
 </td>
</tr>
</table>
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