$(function() {
	$(document).ready(function() {
		$('.fixed-wrapper').stickUp();
	});

	var isAuth = parseInt($('#isAuth ').val(), 10);
	//isAuth 为0的时候表示需要修改密码，为1的时候不需要
	if (!isAuth) {
		// 弹出修改密码层
		Dialog = UP.Dialog('J_SetPwd');

		var modifyPwd = '/member/setpwd.htm';

		function noTips() {

			$("#oldpassword").val(b64_sha1($("#oldpassword").val()));
			$("#newpassword").val(b64_sha1($("#newpassword").val()));
			$("#confpassword").val(b64_sha1($("#confpassword").val()));
			var formParam = $(".modifyPwd").serialize();// 序列化表格内容为字符串
			$.ajax({
				type : 'post',
				url : modifyPwd,
				data : formParam,
				cache : false,
				dataType : 'json',
				success : function(data) {
					if (data.succflag == 0) {

						Dialog.mask.hide();
						Dialog.node.hide();
						$(".modifyPwd").val('');
						$.Tips({
							'message' : '密码修改成功',
							'type' : 'info'
						});

					} else {
						$(".modifyPwd").val('');
						$('.rsmsg').html(data.msg);
					}
				}
			});
		}

		$('#confirmbtn').click(function(e) {
			var str = $.trim($("#oldpassword").val());
			if (str.length == '') {
				$('.rsmsg').html("默认密码为空");
				return false;
			}

			if (str.length < 6 || str.length > 16) {
				$('.rsmsg').html("默认密码长度不正确");
				return false;
			}

			str = $.trim($("#newpassword").val());
			if (str.length == '') {
				$('.rsmsg').html("新密码为空");
				return false;
			}

			if (str.length < 6 || str.length > 16) {
				$('.rsmsg').html("新密码长度不正确");
				return false;
			}

			str = $.trim($("#confpassword").val());
			if (str.length == '') {
				$('.rsmsg').html("确认密码为空");
				return false;
			}

			if (str.length < 6 || str.length > 16) {
				$('.rsmsg').html("确认密码长度不正确");
				return false;
			}

			if ($("#newpassword").val() != $("#confpassword").val()) {
				$('.rsmsg').html("新密码与确认密码不一致");
				return false;
			}
			noTips();
		})
	}
});