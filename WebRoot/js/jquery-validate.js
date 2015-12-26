(function(factory) {
    if (typeof define === 'function' && define.amd) {
        // AMD
        define(['jquery'], factory);
    } else if (typeof exports === 'object') {
        // CommonJS
        factory(require('jquery'));
    } else {
        // Browser globals
        factory(jQuery, window, document);
    }
}(function($, window, document, undefined) {


    /*************************策略对象*****************************/

    var RULES = {
        isNonEmpty: function(value, errorMsg) {
            //不能为空
            if (!value.length) {
                return errorMsg;
            }
        },
        minLength: function(value, length, errorMsg) {
            //大于
            if (value.length < length) {
                return errorMsg;
            }
        },
        maxLength: function(value, length, errorMsg) {
            //小于
            if (value.length > length) {
                return errorMsg;
            }
        },
        maxGBLength: function(value, length, errorMsg) {
            //小于        	
        	/*var cArr = value.match(/[^\x00-\xff]/ig);
        	var rlen =  value.length + (cArr === null ? 0 : cArr.length);
        	*/
        	var rlen= 0;
        	var len = value.length;
        	var charCode = -1;
        	for(var i = 0; i < len; i++){
        	　　charCode = value.charCodeAt(i);
        	　　if (charCode >= 0 && charCode <= 128) {
        	　　		rlen += 1;
        	　　}else{
        			rlen += 3;
        	　　}
        	}

            if (rlen > length) {
                return errorMsg;
            }
        },       
        isMobile: function(value, errorMsg) {
            //是否为手机号码
            if (!/(^1[3|5|8][0-9]{9}$)/.test(value)) {
                return errorMsg;
            }
        },
        isEmail: function(value, errorMsg) {
            //是否为邮箱
            if (!/(^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$)/.test(value)) {
                return errorMsg;
            }
        },
        between: function(value, range, errorMsg) {
            //大于小于
            var min = parseInt(range.split('-')[0]);
            var max = parseInt(range.split('-')[1]);
            if (value.length < min || value.length > max) {
                return errorMsg;
            }
        },
        betweenval: function(value, range, errorMsg) {
            //大于小于
            var min = parseFloat(range.split('-')[0]);
            var max = parseFloat(range.split('-')[1]);
            if (parseFloat(value) < min || parseFloat(value) > max) {
                return errorMsg;
            }
        },
        onlyEn: function(value, errorMsg) {
            //纯英文
            if (!/^[A-Za-z]+$/.test(value)) {

            }
        },
        onlyZh: function(value, errorMsg) {
            //纯中文
            if (!/^[\u4e00-\u9fa5]+$/.test(value)) {
                return errorMsg;
            }
        },
        onlyNum: function(value, errorMsg) {
        	
        	if(value == undefined || value =="") return;
            //数字包含小数
            if (!/^[0-9]+([.][0-9]+){0,1}$/.test(value)) {
                return errorMsg;
            }
        },
        plusInt: function(value, errorMsg) {
        	//正整数
            if (!/^[1-9]\d*$/.test(value)) {
                return errorMsg;
            }
        },
        onlyInt: function(value, errorMsg) {
            //整数
            if (!/^[0-9]*$/.test(value)) {
                return errorMsg;
            }
        },
        
        isMoney: function(value, errorMsg){
        	//金额
        	if (!/^[0-9]*(\.[0-9]{0,2})?$/.test(value)) {
                return errorMsg;
            }
        },
        
        isNoNZeroMoney: function(value, errorMsg){
        	//金额
        	if (!/^(0(?:[.](?:[1-9]\d?|0[1-9]))|[1-9]\d*(?:[.]\d{1,2}|$))$/.test(value)) {
                return errorMsg;
            }
        },
        isDate: function(value, errorMsg){
        	if(!/^(\d{4})\-(\d{2})\-(\d{2})$/.test(value)){
        		return errorMsg;
        	}	
        },
        
        after: function(value, range, errorMsg){
        	if(Date.parse(value) < Date.parse(range)){
        		
        		return errorMsg;
        	}
        },
        
        before: function(value, range, errorMsg){
        	if(Date.parse(value) > Date.parse(range)){
        		
        		return errorMsg;
        	}
        },
        
        
        isTime: function(value, errorMsg){
        	if(!/^(\d{2}):(\d{2}):(\d{2})$/.test(value)){
        		return errorMsg;
        	}
        },
        
        isDateTime: function(value, errorMsg){
        	if(!/^(\d{4})\-(\d{2})\-(\d{2}) (\d{2}):(\d{2}):(\d{2})$/.test(value)){
        		return errorMsg;
        	}	
        },
        isChecked: function(value, errorMsg, el) {
            var i = 0;
            var $collection = $(el).find('input:checked');
            if(!$collection.length){
                return errorMsg;
            }
        },
        
        isRegexpNum:function(value, length, errorMsg){
        	 var len = parseFloat(length.split('-')[0]);
             var dec = parseFloat(length.split('-')[1]);   
             
             var int = len-dec-1;                
             eval("var re = /^\\d{0," + int + "}\\.\?\\d{0,"+dec+"}$/g;"); 
             
             if(!value.match(re)){
            	 return errorMsg;
             }        
        },
        
        isRegexpInt:function(value, length, errorMsg){   
        	
        	eval("var re = /^\\d{0," + length + "}$/g;"); 
        	
            if(!value.match(re)){
              return errorMsg;
            }
       }
    };

    /*************************Validator类*****************************/

    var setting = {
        type: null,
        onBlur: null,
        onFocus: null,
        onChange: null,
        successTip: true
    };

    var Validator = function() {
        this.cache = [];
    };

    Validator.prototype.add = function(dom, rules) {
        var self = this;
        for (var i = 0, rule; rule = rules[i++];) {
            (function(rule) {
                var strategyAry = rule.strategy.split(':');
                var errorMsg = rule.errorMsg
                self.cache.push(function() {
                    var strategy = strategyAry.shift(); // 前删匹配方式并赋值
                    strategyAry.unshift(dom.value); // 前插value值
                    strategyAry.push(errorMsg); // 后插出错提示
                    strategyAry.push(dom); // 后插dom
                    if (!RULES[strategy]) {
                        $.error('没有' + strategy + '规则，请检查命名或自行定义');
                    }
                    return {
                        errorMsg: RULES[strategy].apply(dom, strategyAry),
                        el: dom
                    };
                });
            }(rule));
        }
    };

    Validator.prototype.start = function() {
        var result;
        for (var i = 0, validatorFunc; validatorFunc = this.cache[i++];) {
            var result = validatorFunc();
            if (setting.successTip) {
                new Validator().showMsg($(result.el), '', 1);
            }
            if (result.errorMsg) {
                return result;
            }

        };
        return true;
    };

    Validator.prototype.showMsg = function(target, msg, status, callback) {
        //status
        // 0 : tip
        // 1 : success
        // 2 : error
        var _current = status ? (status > 1 ? 'error' : 'success') : 'tip';
        var $context = target.parent();
        var $msg = $context.find('.valid_message');
        var _other = target.attr('data-type') || '';
        $msg.remove();
        $context.removeClass('success tip error').addClass(_current+' '+_other).append('<span class="valid_message">' + msg + '</span>');
    };

    var plugin = {
        init: function(options) {
            var $form = this;
            var $body = $('body');
            var $required = $form.find('.required');
            setting = $.extend(setting, options);

            if (setting.type) {
                $.extend(RULES, setting.type);
            }

            var validator = new Validator();

            $body.on({
                focus: function(event) {
                    var $this = $(this);
                    var _tipMsg = $this.attr('data-tip') || '';
                    var _status = $this.attr('data-status');
                    if (_status === undefined ||!parseInt(_status)) {
                        validator.showMsg($this, _tipMsg);
                    }
                    setting.onFocus ? setting.onFocus.call($this, arguments) : '';
                },
                blur: function(event) {
                    var $this = $(this);
                    var dataValid = $this.attr('data-valid');
                    var validLen = dataValid.split('||');
                    var errCollection = $this.attr('data-error');
                    var errMsgAry = errCollection.split("||");
                    var strategyAry, strategy, errMsg;

                    for (var i = 0; i < validLen.length; i++) {
                        strategyAry = validLen[i].split(':');
                        strategy = strategyAry.shift();
                        strategyAry.unshift(this.value);
                        strategyAry.push(errMsgAry[i]);
                        strategyAry.push(this);
                        errMsg = RULES[strategy].apply(this, strategyAry);
                        if (errMsg) {
                            $this.attr('data-status', 0);
                            validator.showMsg($this, errMsg, 2);
                            break;
                        }
                    };

                    if (!errMsg) {
                        $this.attr('data-status', 1);
                        setting.successTip ? validator.showMsg($this, '', 1) : $this.parent().find('.valid_message').remove();
                    }

                    setting.onBlur ? setting.onBlur.call($this, arguments) : '';
                },
                change: function(event) {
                    setting.onChange ? setting.onChange.call($this, arguments) : '';
                }
            }, '.required');


        },
        submitValidate: function(options) {
            var $form = options || this;
            var $body = $('body');
            var $required = $form.find('.required');
            var validator = new Validator();
            var $target;

            $.each($required, function(index, el) {
                var $el = $(el);
                var dataValid = $el.attr('data-valid');
                var validLen = dataValid.split('||');
                var errCollection = $el.attr('data-error');
                var errMsgAry = errCollection.split("||");
                var ruleAry = [];

                for (var i = 0; i < validLen.length; i++) {
                    ruleAry.push({
                        strategy: validLen[i],
                        errorMsg: errMsgAry[i]
                    });
                };

                validator.add(el, ruleAry);

            });

            var result = validator.start();

            if (result.errorMsg) {
                $target = $(result.el);
                $target.attr('data-status', 0)[0].focus();
                validator.showMsg($target, result.errorMsg, 2);
                return false;
            }


            return true;
        }
    };

    $.fn.validate = function() {
        var method = arguments[0];
        if (plugin[method]) {
            method = plugin[method];
            arguments = Array.prototype.slice.call(arguments, 1);
        } else if (typeof(method) == 'object' || !method) {
            method = plugin.init;
        } else {
            $.error('Method ' + method + ' does not exist on jQuery.validate Plugin');
            return this;
        }
        return method.apply(this, arguments);
    }

}))
