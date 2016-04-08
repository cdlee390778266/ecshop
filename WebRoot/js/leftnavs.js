$(function() {

	$('.user-navs').delegate(
			'li',
			'click',
			function(e) {
				
				$('.user-navs').find('li>em').text('+');
				if( $(this).has('.sub-navs') ){
				     var sub = $(this).find('.sub-navs'),cell = $(this).find('em');
					if( sub.is(":hidden")){ 						
						$(this).siblings().find('.sub-navs').hide();
						sub.show();
						cell.text('-')
					}else{ 
						sub.hide();
						cell.text('+')
					}
					
				}
				e.stopPropagation();
					
			});

});
