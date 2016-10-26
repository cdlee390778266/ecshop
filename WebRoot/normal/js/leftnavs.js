$(function() {

	$('.user-navs').delegate(
			'em,.user-navs-title',
			'click',
			function(e) {
				
				// $('.user-navs').find('li>em').text('+');
				if( $(this).has('.sub-navs') ){
				     var sub = $(this).parent().find('.sub-navs'),cell = $(this).parent().find('em');
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
