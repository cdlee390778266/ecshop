
(function($) {  
  $.Tips = function(options) {  
    var opts = $.extend({}, $.Tips.defaults, options);  
      var o = $.meta ? $.extend({}, opts) : opts;  
      var $tip = $('#tip');
      if ($tip.length == 0) {
          $tip = $('<span id="tip" style="font-weight:bold;position:absolute;top:50%;left: 50%;z-index:9999"></span>');
          $('body').append($tip);
      }
      $tip.stop(true).attr('class', 'alert alert-' + o.type).text(o.message).css('margin-left', -$tip.outerWidth() / 2).fadeIn(500).delay(2000).fadeOut(1500);
 
  };  
  $.Tips.defaults = {  
    type: 'info',  
    message: ''  
  };  

})(jQuery);   
