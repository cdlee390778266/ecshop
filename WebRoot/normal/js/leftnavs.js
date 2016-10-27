$(function() {

	$('.user-navs').delegate(
			'em,.user-navs-title',
			'click',
			function(e) {
				
				// $('.user-navs').find('li>em').text('+');
				if( $(this).has('.sub-navs') ){
				     var sub = $(this).parent().find('.sub-navs'),cell = $(this).parent().find('em');
					if( sub.is(":hidden")){ 						
						$(this).siblings().find('.sub-navs').slideUp();
						sub.slideDown();
						cell.text('-')
					}else{ 
						sub.slideUp();
						cell.text('+')
					}
					
				}
				e.stopPropagation();
					
			});

});
