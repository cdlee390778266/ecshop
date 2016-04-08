;(function($){
	$.fn.tabs = function(options,fn){
		$.fn.tabs.defaults = {  
			className:'selected'
		};
	var opts = $.extend({}, $.fn.tabs.defaults, options);
	return this.each(function(){ 
			var that =$(this),
				tabCell = that.find('.tab-cell'),tabContent = that.find('.tab-content');
				tabCell.eq(0).addClass('selected');
				tabContent.eq(0).addClass('active');
			var tab = function(target){
				tabCell.each(function(){
					$(this).bind('click',function(){
						var index = tabCell.index($(this));
						tabCell.each(function(i){
							if(i == index){
								!!tabCell.eq(i).length ?  tabCell.eq(i).addClass(opts.className) : tabCell.eq(i).removeClass(opts.className);	
								tabContent.removeClass('active');
								tabContent.eq(i).addClass('active');
								if(fn!=undefined && (fn instanceof Function)){
									fn();
								}
							}else{
								!!tabCell.eq(i).find('a').length ? tabCell.eq(i).removeClass(opts.className) : tabCell.eq(i).addClass(opts.className);
								
							}	
						})
					})
				})
			}
			tab(tabCell);
		})
	}  
})(jQuery);