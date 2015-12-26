(function($) {
	$.fn.extend({
		"alterBgColor" : function(options) {
			var prevselitem = null;
			// 设置默认值
			options = $.extend({
				style : "4",
				odd : "odd", /* 偶数行样式 */
				even : "even", /* 奇数行样式 */
				over : "over", /* 鼠标掠过 */
				selected : "selected" /* 选中行样式 */
			}, options);
			// 交替背景
			$("tbody>tr:odd", this).addClass(options.odd);
			$("tbody>tr:even", this).addClass(options.even);
			// 鼠标移动背景
			$("tbody>tr", this).hover(function() {
				$(this).addClass(options.over);
			}, function() {
				$(this).removeClass(options.over);
			});
			// 初始背景 (判断第一列中有被选中的话就高亮)
			$("tbody>tr td:first-child:has(:checked)", this).parents('tr')
					.addClass(options.selected);

			// 样式1
			if (options.style == "1") {
				$("tbody>tr", this).click(function() {
					$(this).toggleClass(options.selected);
				});
			}

			// 样式2
			if (options.style == "2") {
				$('tbody>tr', this).click(
						function() {
							// 判断当前是否选中
							var hasSelected = $(this)
									.hasClass(options.selected);
							// 如果选中，则移出selected类，否则就加上selected类
							$(this)[hasSelected ? "removeClass" : "addClass"](
									options.selected)
							// 查找内部的checkbox,设置对应的属性。
							.find(':checkbox:first').attr('checked',
									!hasSelected);
						});
			}

			// 样式3
			if (options.style == "3") {
				// 如果单选框默认情况下是选择的，则高色.
				$('tbody>tr', this).click(
						function() {
							$(this).siblings().removeClass(options.selected); // 除掉同胞的样式。
							$(this).addClass(options.selected).find(
									':radio:first').attr('checked', true);
						});
			}

			// 样式4
			if (options.style == "4") {
				// 第一列 为多选
				$('tbody>tr td:first-child', this).click(
						function() {
							var p = $(this).parents("tr");
							var hasSelected = p.hasClass(options.selected);
							p[hasSelected ? "removeClass" : "addClass"](
									options.selected).find(':checkbox:first')
									.attr('checked', !hasSelected);
						});
				// 其他列 为单选
				$('tbody>tr td:not(:first-child)', this).click(
						function() {
							var p = $(this).parents("tr");
							p.addClass(options.selected).siblings()
									.removeClass(options.selected).find(
											':checkbox:first').attr('checked',
											false);
							p.find(':checkbox:first').attr('checked', true);
						});
			}

			// 表头中的checkbox （全选 反选）
			$("thead>tr th:first-child :checkbox:first", this).click(
					function() {
						// 判断当前是否选中
						var hasSelected = $(this).attr("checked");
						// 如果选中，则移出selected类，否则就加上selected类
						var tab = $(this).parents("table");
						tab.find("tbody>tr")[!hasSelected ? "removeClass"
								: "addClass"](options.selected);
						tab.find('tbody>tr td:first-child :checkbox').attr(
								"checked", hasSelected ? true : false);

					});
			return this; // 返回this，使方法可链。
		}
	});
})(jQuery);