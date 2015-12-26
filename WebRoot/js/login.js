
/*
 * jQuery placeholder, fix for IE6,7,8,9
 * @author JENA
 * @since 20131115.1504
 * @website ishere.cn
 */
var JPlaceHolder = {
    //检测
    _check : function(){
        return 'placeholder' in document.createElement('input');
    },
    //初始化
    init : function(){
        if(!this._check()){
            this.fix();
        }
    },
    //修复
    fix : function(){
        jQuery(':input[placeholder]').each(function(index, element) {
            var self = $(this), txt = self.attr('placeholder');
            self.wrap($('<div></div>').css({position:'relative', zoom:'1', border:'none', background:'none', padding:'none', margin:'none'}));
            var pos = self.position(), h = self.outerHeight(true), paddingleft = self.css('padding-left');
            var holder = $('<span></span>').text(txt).css({position:'absolute', left:pos.left, top:pos.top, height:h, lienHeight:h, paddingLeft:paddingleft, color:'#aaa'}).appendTo(self.parent());
            self.focusin(function(e) {
                holder.hide();
            }).focusout(function(e) {
                if(!self.val()){
                    holder.show();
                }
            });
            holder.click(function(e) {
                holder.hide();
                self.focus();
            });
        });
    }
};


/**
 * Cookie plugin
 *
 * Copyright (c) 2006 Klaus Hartl (stilbuero.de)
 * Dual licensed under the MIT and GPL licenses:
 * http://www.opensource.org/licenses/mit-license.php
 * http://www.gnu.org/licenses/gpl.html
 *
 */

/**
 * Create a cookie with the given name and value and other optional parameters.
 *
 * @example $.cookie('the_cookie', 'the_value');
 * @desc Set the value of a cookie.
 * @example $.cookie('the_cookie', 'the_value', {expires: 7, path: '/', domain: 'jquery.com', secure: true});
 * @desc Create a cookie with all available options.
 * @example $.cookie('the_cookie', 'the_value');
 * @desc Create a session cookie.
 * @example $.cookie('the_cookie', null);
 * @desc Delete a cookie by passing null as value.
 *
 * @param String name The name of the cookie.
 * @param String value The value of the cookie.
 * @param Object options An object literal containing key/value pairs to provide optional cookie attributes.
 * @option Number|Date expires Either an integer specifying the expiration date from now on in days or a Date object.
 *                             If a negative value is specified (e.g. a date in the past), the cookie will be deleted.
 *                             If set to null or omitted, the cookie will be a session cookie and will not be retained
 *                             when the the browser exits.
 * @option String path The value of the path atribute of the cookie (default: path of page that created the cookie).
 * @option String domain The value of the domain attribute of the cookie (default: domain of page that created the cookie).
 * @option Boolean secure If true, the secure attribute of the cookie will be set and the cookie transmission will
 *                        require a secure protocol (like HTTPS).
 * @type undefined
 *
 * @name $.cookie
 * @cat Plugins/Cookie
 * @author Klaus Hartl/klaus.hartl@stilbuero.de
 */

/**
 * Get the value of a cookie with the given name.
 *
 * @example $.cookie('the_cookie');
 * @desc Get the value of a cookie.
 *
 * @param String name The name of the cookie.
 * @return The value of the cookie.
 * @type String
 *
 * @name $.cookie
 * @cat Plugins/Cookie
 * @author Klaus Hartl/klaus.hartl@stilbuero.de
 */
jQuery.cookie = function(name, value, options) {
    if (typeof value != 'undefined') { // name and value given, set cookie
        options = options || {};
        if (value === null) {
            value = '';
            options.expires = -1;
        }
        var expires = '';
        if (options.expires && (typeof options.expires == 'number' || options.expires.toUTCString)) {
            var date;
            if (typeof options.expires == 'number') {
                date = new Date();
                date.setTime(date.getTime() + (options.expires * 24 * 60 * 60 * 1000));
            } else {
                date = options.expires;
            }
            expires = '; expires=' + date.toUTCString(); // use expires attribute, max-age is not supported by IE
        }
        var path = options.path ? '; path=' + options.path : '';
        var domain = options.domain ? '; domain=' + options.domain : '';
        var secure = options.secure ? '; secure' : '';
        document.cookie = [name, '=', encodeURIComponent(value), expires, path, domain, secure].join('');
    } else { // only name given, get cookie
        var cookieValue = null;
        if (document.cookie && document.cookie != '') {
            var cookies = document.cookie.split(';');
            for (var i = 0; i < cookies.length; i++) {
                var cookie = jQuery.trim(cookies[i]);
                // Does this cookie string begin with the name we want?
                if (cookie.substring(0, name.length + 1) == (name + '=')) {
                    cookieValue = decodeURIComponent(cookie.substring(name.length + 1));
                    break;
                }
            }
        }
        return cookieValue;
    }
};

function checkBrowser(){
	var _broswer = {};
    var sUserAgent = navigator.userAgent;
    var isOpera = sUserAgent.indexOf("Opera") > -1;
    if (isOpera) {
        //首先检测Opera是否进行了伪装
        if (navigator.appName == 'Opera') {
            //如果没有进行伪装，则直接后去版本号
            _broswer.version = parseFloat(navigator.appVersion);
        } else {
            var reOperaVersion = new RegExp("Opera (\\d+.\\d+)");
            //使用正则表达式的test方法测试并将版本号保存在RegExp.$1中
            reOperaVersion.test(sUserAgent);
            _broswer.version = parseFloat(RegExp['$1']);
        }
        _broswer.opera = true;
    }
    var isChrome = sUserAgent.indexOf("Chrome") > -1;
    if (isChrome) {
        var reChorme = new RegExp("Chrome/(\\d+\\.\\d+(?:\\.\\d+\\.\\d+))?");
        reChorme.test(sUserAgent);
        _broswer.version = parseFloat(RegExp['$1']);
        _broswer.chrome = true;
    }
    //排除Chrome信息，因为在Chrome的user-agent字符串中会出现Konqueror/Safari的关键字
    var isKHTML = (sUserAgent.indexOf("KHTML") > -1
            || sUserAgent.indexOf("Konqueror") > -1 || sUserAgent
            .indexOf("AppleWebKit") > -1)
            && !isChrome;
    if (isKHTML) {//判断是否基于KHTML，如果时的话在继续判断属于何种KHTML浏览器
        var isSafari = sUserAgent.indexOf("AppleWebKit") > -1;
        var isKonq = sUserAgent.indexOf("Konqueror") > -1;
        if (isSafari) {
            var reAppleWebKit = new RegExp("Version/(\\d+(?:\\.\\d*)?)");
            reAppleWebKit.test(sUserAgent);
            var fAppleWebKitVersion = parseFloat(RegExp["$1"]);
            _broswer.version = parseFloat(RegExp['$1']);
            _broswer.safari = true;
        } else if (isKonq) {
            var reKong = new RegExp(
                    "Konqueror/(\\d+(?:\\.\\d+(?\\.\\d)?)?)");
            reKong.test(sUserAgent);
            _broswer.version = parseFloat(RegExp['$1']);
            _broswer.konqueror = true;
        }
    }
    // !isOpera 避免是由Opera伪装成的IE  
    var isIE = sUserAgent.indexOf("compatible") > -1
            && sUserAgent.indexOf("MSIE") > -1 && !isOpera;
    if (isIE) {
        var reIE = new RegExp("MSIE (\\d+\\.\\d+);");
        reIE.test(sUserAgent);
        _broswer.version = parseFloat(RegExp['$1']);
        _broswer.msie = true;
    }
    // 排除Chrome 及 Konqueror/Safari 的伪装
    var isMoz = sUserAgent.indexOf("Gecko") > -1 && !isChrome && !isKHTML;
    if (isMoz) {
        var reMoz = new RegExp("rv:(\\d+\\.\\d+(?:\\.\\d+)?)");
        reMoz.test(sUserAgent);
        _broswer.version = parseFloat(RegExp['$1']);
        _broswer.mozilla = true;
    }
    return _broswer;
}

$(document).ready(function() {
	
	broswer = checkBrowser();
	var broswerName = '';
	var broswerUrl = ''
	if(broswer.opera == true){
		if(broswer.version < 10.5){
			broswerName = "Opera浏览器  Version"+broswer.version;
			broswerUrl = "http://www.opera.com/";
		}
	}else if(broswer.chrome == true){
		if(broswer.version < 3.0){
			broswerName = "Chrome 浏览器  Version"+broswer.version;
			broswerUrl = "http://www.google.cn/chrome/browser/";
		}
	}else if(broswer.konqueror == true){
		if(broswer.version < 4.4){
			broswerName = "Konqueror 浏览器  Version"+broswer.version;
			broswerUrl = "https://konqueror.org/download/";
		}
	}else if(broswer.msie == true){
		if(broswer.version < 9){
			broswerName = "IE 浏览器  Version"+broswer.version;
			broswerUrl = "http://windows.microsoft.com/zh-cn/internet-explorer/download-ie";
		}
	}else if(broswer.mozilla == true){
		if(broswer.version < 3.5){
			broswerName = "Mozilla Firefox 浏览器  Version"+broswer.version;
			broswerUrl = "http://www.firefox.com.cn/download/";
		}
	}
	if(broswerName!=''&&broswerUrl!=''){
		$('#broswerinfo').html(broswerName);
		$('#broswerdown').html('<a href="'+broswerUrl+'" >'+broswerUrl+'</a>');
		Dialog = UP.Dialog('J_BroswerMsg');
	}
		
	
	var rmbflag = $.cookie("rmbflag");
	if(rmbflag == null){
		rmbflag = "false";
	}
	if (rmbflag == "true") {
		$("#remmberflag").attr("checked", true);
		$("#mid").val($.cookie("mid"));
		$("#operid").val($.cookie("operid"));
	}else{
		JPlaceHolder.init(); 
	}
	
	//换验证码
	$("#J_StandardCode").on('click', function(event) {
		$("#J_StandardCode_m").attr("src", "/AuthImg?rand="+Math.random());
	});
	
	$("#J_StandardCode_m").on('click', function(event) {
		$("#J_StandardCode_m").attr("src", "/AuthImg?rand="+Math.random());
	});
	
	$("#mac").on('focus', function(event) {
		$("#J_StandardCode_m").attr("src", "/AuthImg?rand="+Math.random());
	});
	
	
	
	$('form').on('submit', function(event){
		
		if($("#mid").val() == null ||$("#mid").val() == ""){
			$('#J_Message p').html("请输入会员ID号");
			$('#J_Message').addClass('show');
			event.preventDefault();
			return;
		}
		
		if(!/^[0-9]*$/.test($("#mid").val())){
			$('#J_Message p').html("会员ID格式不正确");
			$('#J_Message').addClass('show');
			event.preventDefault();
			return;
		}
		
		if($("#mid").val().length != 12){
			$('#J_Message p').html("会员ID长度不正确");
			$('#J_Message').addClass('show');
			event.preventDefault();
			return;
		}
		
		if($("#operid").val() == null ||$("#operid").val() == ""){
			$('#J_Message p').html("请输入会员操作员ID号");
			$('#J_Message').addClass('show');
			event.preventDefault();
			return;
		}
		
		if(!/^[0-9]*$/.test($("#operid").val())){
			$('#J_Message p').html("会员操作员ID格式不正确");
			$('#J_Message').addClass('show');
			event.preventDefault();
			return;
		}
		
		if($("#operid").val().length != 4){
			$('#J_Message p').html("会员操作员ID长度不正确");
			$('#J_Message').addClass('show');
			event.preventDefault();
			return;
		}
		
		if($("#operpwd").val() == null ||$("#operpwd").val() == ""){
			$('#J_Message p').html("请输入会员操作员密码");
			$('#J_Message').addClass('show');
			event.preventDefault();
			return;
		}
		
		if($("#operpwd").val().length < 6){
			$('#J_Message p').html("会员操作员密码长度不正确");
			$('#J_Message').addClass('show');
			event.preventDefault();
			return;
		}
		
		if($("#mac").val() == null ||$("#mac").val() == ""){
			$('#J_Message p').html("请输入验证码");
			$('#J_Message').addClass('show');
			event.preventDefault();
			return;
		}
		
		if($("#mac").val().length != 4){
			$('#J_Message p').html("验证码长度不正确");
			$('#J_Message').addClass('show');
			event.preventDefault();
			return;
		}
		
		var flag = $("#remmberflag").prop("checked")

		if(flag == true){
			$.cookie("rmbflag", "true", {expires : 7}); // 存储一个带7天期限的 cookie
			$.cookie("mid", $("#mid").val(), {expires : 7}); // 存储一个带7天期限的 cookie
			$.cookie("operid", $("#operid").val(), {expires : 7}); // 存储一个带7天期限的 cookie
		}else{
			$.cookie("rmbflag", "false", {expires : -1}); // 删除 cookie
			$.cookie("mid", '', {expires : -1});
			$.cookie("operid", '', {expires : -1});
		}
		$("#operpwd").val(b64_sha1($("#operpwd").val()));

	});
});

