// 选择图片
/*
function fileSelected(fileID) {
	var url; 
	if (navigator.userAgent.indexOf("MSIE")>=1) { // IE 
	url = document.getElementById(fileID).value; 
	} else if(navigator.userAgent.indexOf("Firefox")>0) { // Firefox 
	url = window.URL.createObjectURL(document.getElementById(fileID).files.item(0)); 
	} else if(navigator.userAgent.indexOf("Chrome")>0) { // Chrome 
	url = window.URL.createObjectURL(document.getElementById(fileID).files.item(0)); 
	} 

	var imgPre = document.getElementById('imgTitlePre'); 
	imgPre.src = url;
	
}
*/
$(function() {
	
	$(document).ready(function() {
		$('.fixed-wrapper').stickUp();
	});
	

	var url = '/member/manager.htm', delUrl = '/member/deleteoper.htm';
	
	var actUrl =  '/member/activeoper.htm';
	// 删除操作
	function delResult(id) {
		if (id) {
			$.ajax({
				type : 'post',
				url : delUrl,
				data : {
					"operId" : id
				},
				cache : false,
				success : function(data) {
					if (data.succflag != 0)
						$.Tips({
							message : data.msg
						});
					else
						$.Tips({
							message : "操作员" + id + "注销成功"
						});
					setTimeout(function() {
						location.reload();
					}, 3000);
				}
			});
		}
	};
	
	// 启用操作
	function actResult(id) {
		if (id) {
			$.ajax({
				type : 'post',
				url : actUrl,
				data : {
					"operId" : id
				},
				cache : false,
				success : function(data) {
					if (data.succflag != 0)
						$.Tips({
							message : data.msg
						});
					else
						$.Tips({
							message : "操作员" + id + "启用成功"
						});
					setTimeout(function() {
						location.reload();
					}, 3000);
				}
			});
		}
	};
	
	$('#J_operater').delegate('.J_delete', 'click', function(e) {
		var opid = $(this).attr('data-id');
		// 初始化删除提示框
		var dl = UI.Dialog({
			type : 'delete', // 提示框类型，这里是delete 代表删除提示框
			width : 240, // 设置提示框的宽度
			title : '注  销', // 提示框标题的文字信息
			content : '您确定要注销该操作员吗？', // 提示框的内容文字信息
			href : delResult, // 点击确定要执行跳转
			param : opid
		}).show();
		e.stopPropagation();
	});
	
	$('#J_operater').delegate('.J_active', 'click', function(e) {
		var opid = $(this).attr('data-id');
		// 初始化删除提示框
		var dl = UI.Dialog({
			type : 'delete', // 提示框类型，这里是delete 代表删除提示框
			width : 240, // 设置提示框的宽度
			title : '启  用', // 提示框标题的文字信息
			content : '您确定要启用该操作员吗？', // 提示框的内容文字信息
			href : actResult, // 点击确定要执行跳转
			param : opid
		}).show();
		e.stopPropagation();
	});


	// 添加管理员弹出层
	$('#J_add_operator').click(
		function(e) {
			var current = $(this).attr('data-href'), source = Handlebars
			.compile($("#" + current).html());
			$.ajax({
				method : "GET",
				url : "/member/findoper.htm",
				cache : false,
				data : {
					operid : ""
				}
			}).done(function(res) {
				if (res.succflag == 0) {
					var right = res.data;
					$('body').append(source(right));
					$('input').iCheck({
						checkboxClass : 'icheckbox_square-green',
						radioClass : 'iradio_square-green',
							increaseArea : '20%' // optional
						});
					$('form').find('input[type=file]').on('change', function(evt) {
						var files = evt.target.files;

						var url = null;
						if (window.createObjectURL != undefined) {
							url = window.createObjectURL(files[0])
						} else if (window.URL != undefined) {
							url = window.URL.createObjectURL(files[0])
						} else if (window.webkitURL != undefined) {
							url = window.webkitURL.createObjectURL(files[0])
						}
						$(this).siblings().text($(this).siblings().text().replace('选择', '替换'));	
						var preImg = '#' + $(this).attr("id") + 'Pre';
						$(preImg).attr("src", url);
						$(preImg).show();

					});
					dialog = PW.Dialog('dialog_' + current);
					$('.close').on('click',function(){
						$('body').css('overflowY','auto');
					})
					$('body').css('overflow-y','hidden');

				}
			});
e.preventDefault();
});

function operatorManage(url, msg, type, txRights, adRights) {
	var formData = new FormData($("#frmUpload")[0]);
	formData.append("txrights", txRights);
	formData.append("adrights", adRights);

	if (type == 1) {
		formData.append("defaultpwd", b64_sha1($("#apwd").val()));
	} else {
		if ($("#upwd") != undefined && $("#upwd").val() != undefined)
			formData.append("resetpwd", b64_sha1($("#upwd").val()));
	}

	$.ajax({
		method : "POST",
		url : url,
		cache : false,
		data : formData,
		processData : false,
		contentType : false
	}).done(function(res) {
		dialog.close();
		if (res.succflag != 0)
			$.Tips({
				message : res.msg
			});
		else
			$.Tips({
				message : msg
			});
		setTimeout(function() {
			location.reload();
		}, 3000)
	});
};

	// 添加操作员
	$(document)
	.on(
		'click',
		'#J_add_opter',
		function() {

			var valFlag = $('form').validate('submitValidate');
			if (valFlag == true) {

			} else {
				event.preventDefault();
				return;
			}

			if($('#apwd').val() != $('#cpwd').val()){
				layer.msg('默认登录密码与确认密码不一致');
				event.preventDefault();
				return;
			}

			var jsonArr = $('#frmUpload').serializeArray(), authArr = [], vertyArr = [], auth = $('.auth-ck'), verty = $('.verify-ck');
			for ( var i = 0; i < auth.length; i++) {
				if (auth[i].checked) {
					authArr.push(parseInt(auth[i].value));
				}
			}
			for ( var j = 0; j < verty.length; j++) {
				if (verty[j].checked) {
					vertyArr.push(parseInt(verty[j].value));
				}
			}

						/* 不需要验证有无权限
						if (authArr.length == 0 && vertyArr == 0
								&& $("input[name=payright]").val() == 0
								&& $("input[name=mgrright]").val() == 0) {
							$('.seledmsg').html("请选择操作员权限");
							event.preventDefault();
							return;
						}
						*/

						operatorManage("/member/createoper.htm", "添加成功！", 1,
							authArr, vertyArr);
						$('body').css('overflowY','auto');
					});

	// 修改管理员
	$(document)
	.on(
		'click',
		'.J_modify',
		function(e) {
			var id = $(this).attr('data-id');
			if (id) {
				var current = $(this).attr('data-href'), source = Handlebars
				.compile($("#" + current).html());

				Handlebars
				.registerHelper(
					"sex",
					function(value) {

						var htm = '';
						if (value == 1) {
							htm = '<label class="mr20"><input type="radio" checked name="operSex" value="1"/>男</label>'
							+ '<label><input type="radio" name="operSex" value="2"/>女</label>';
						} else {
							htm = '<label class="mr20"><input type="radio" name="operSex" value="1"/>男</label>'
							+ '<label><input type="radio" name="operSex" checked value="2"/>女</label>';
						}

						return new Handlebars.SafeString(
							htm);
					});

				Handlebars
				.registerHelper(
					"rightshow",
					function(type, value) {
						var htm = '';
						if (type == "tx") {
							for ( var i = 0; i < value.length; i++) {
								htm += '<dl class="authoritys"><dt>'
								+ value[i].name
								+ '</dt>';
								for ( var j = 0; j < value[i].txRights.length; j++) {
									if (value[i].txRights[j].has == true)
										htm += '<dd><input type="checkbox"  class="auth-ck" value="'
									+ value[i].txRights[j].code
									+ '" checked />'
									+ value[i].txRights[j].name
									+ '</dd>';
									else
										htm += '<dd><input type="checkbox"  class="auth-ck" value="'
									+ value[i].txRights[j].code
									+ '" />'
									+ value[i].txRights[j].name
									+ '</dd>';
								}
								htm += '</dl>'
							}
						} else {
							for ( var i = 0; i < value.length; i++) {
								htm += '<dl class="authoritys"><dt>'
								+ value[i].name
								+ '</dt>';
								for ( var j = 0; j < value[i].adRights.length; j++) {
									if (value[i].adRights[j].has == true)
										htm += '<dd><input type="checkbox"  class="verify-ck" value="'
									+ value[i].adRights[j].code
									+ '" checked />'
									+ value[i].adRights[j].name
									+ '</dd>';
									else
										htm += '<dd><input type="checkbox"  class="verify-ck" value="'
									+ value[i].adRights[j].code
									+ '" />'
									+ value[i].adRights[j].name
									+ '</dd>';
								}
								htm += '</dl>'
							}
						}
						return new Handlebars.SafeString(
							htm);
					});

$
.ajax({
	method : "GET",
	url : "/member/findoper.htm",
	cache : false,
	data : {
		operid : id
	}
})
.done(
	function(res) {
		if (res.succflag == 0) {
			var right = res.data;
			$('body').append(
				source(right));
			$('input')
			.iCheck(
			{
				checkboxClass : 'icheckbox_square-green',
				radioClass : 'iradio_square-green',
																		increaseArea : '20%' // optional
																	});

			$('#reset')
			.on(
				'ifChecked',
				function(
					event) {
					var htm = '<input type="password" name="upwd" id="upwd" onpaste="return false" oncopy="return false" class="required"  data-tip="请输入重置密码" data-valid="isNonEmpty||between:6-16" data-error="重置密码不能为空||密码长度必须为6-16位"/>';
					$('#J_Reset').append(htm);
					$('#J_Reset').addClass('form_control');

					var chtm = '<input type="password" name="cpwd" id="cpwd" onpaste="return false" oncopy="return false" class="required"  data-tip="请输入确认密码" data-valid="isNonEmpty||between:6-16" data-error="确认密码不能为空||密码长度必须为6-16位"/>';
					$('#J_ConfReset').append(chtm);
					$('#J_ConfReset').addClass('form_control');
				});

			$('#reset')
			.on(
				'ifUnchecked',
				function(
					event) {
					$(
						'#J_Reset')
					.removeClass(
						'form_control');
					$(
						'#J_Reset')
					.empty();
					$(
						'#J_ConfReset')
					.removeClass(
						'form_control');
					$(
						'#J_ConfReset')
					.empty();
				});

			$('form').find('input[type=file]').on('change', function(evt) {
				var files = evt.target.files;

				var url = null;
				if (window.createObjectURL != undefined) {
					url = window.createObjectURL(files[0])
				} else if (window.URL != undefined) {
					url = window.URL.createObjectURL(files[0])
				} else if (window.webkitURL != undefined) {
					url = window.webkitURL.createObjectURL(files[0])
				}
				$(this).siblings().text($(this).siblings().text().replace('选择', '替换'));	
				var preImg = '#' + $(this).attr("id") + 'Pre';
				$(preImg).attr("src", url);
				$(preImg).show();

			});
			dialog = PW
			.Dialog('dialog_'
				+ current);
			$('.close').on('click',function(){
				$('body').css('overflowY','auto');
			})
			$('body').css('overflowY','hidden');	
		}
	});
}
e.preventDefault();
})
$(document)
.on(
	'click',
	'#J_modify_opter',
	function() {

		var valFlag = $('form').validate('submitValidate');
		if (valFlag == true) {

		} else {
			event.preventDefault();
			return;
		}

		if($('#upwd').val() != $('#cpwd').val()){
			layer.msg('重置密码与确认密码不一致');
			event.preventDefault();
			return;
		}

		var jsonArr = $('#frmUpload').serializeArray(), authArr = [], vertyArr = [], auth = $('.auth-ck'), verty = $('.verify-ck');
		for ( var i = 0; i < auth.length; i++) {
			if (auth[i].checked) {
				authArr.push(parseInt(auth[i].value));
			}
		}
		for ( var j = 0; j < verty.length; j++) {
			if (verty[j].checked) {
				vertyArr.push(parseInt(verty[j].value));
			}
		}
						/* 不需要验证有无权限
						if (authArr.length == 0 && vertyArr == 0
								&& $("input[name=payright]").val() == 0
								&& $("input[name=mgrright]").val() == 0) {
							$('.seledmsg').html("请选择操作员权限");
							event.preventDefault();
							return;
						}
						*/

						operatorManage("/member/editoper.htm", "修改成功！", 2,
							authArr, vertyArr);
						$('body').css('overflowY','auto');
					})
});