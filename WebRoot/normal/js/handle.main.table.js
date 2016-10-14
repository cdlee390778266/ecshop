;
(function($) {
	$.fn.tabulation = function(url) {
		if (!url)
			return;
		$.fn.tabulation.url = url;
		$.fn.tabulation.defaults = {
			start : 0,
			length : 4,
			draw : null
		};

		var totalPage = 0; // 分页总数

		return this.each(function() {
			var that = $(this);
			var initData = $.fn.tabulation.defaults, initUrl = $.fn.tabulation.url;
			function init(initParam, pageNum) {
				$.ajax({
					type : "get",
					url : initUrl,
					data : initParam,
					cache : false,
					dataType : "json",
					success : function(data) {
						that.empty();
						construct(that, data, initParam, pageNum);
					}
				});
			}



					/**
					 * parent 父容器 data 查询返回的数据 reqData 请求数据 pageNum 页码
					 */
					 function construct(parent, data, reqData, pageNum) {
						var pageSize = initData.length; // 每页大小
						var recordsTotal = 0; // 总记录数
						var recordsFiltered = 0; // 过滤后总记录数
						var breakpage = 7; //最多显示页码个数
                        var breakspace = 2; //两边显示页码个数
                        var space =  (breakpage-1)/2;


                        if (data) {
                        	recordsTotal = data.recordsTotal;
                        	recordsFiltered = data.recordsFiltered;
                        	totalPage = Math.ceil(recordsTotal / pageSize);

							// 生成分页栏
							var pageField = $(
								'<div class="up-page">'
								+ '<div class="up-pagel">'
								+ '<div class="prev"><a href="javascript:void(0);" class="disabled"> < 上一页</a></div>'
								+ '<div class="up-page-main"></div>'
								+ '<div class="next"><a href="javascript:void(0);">下一页 > </a></div>'
								+ '</div>'
								+ '<div class="up-pager">'
								+ '<span>共 ' + totalPage + ' 页</span>'
								+ '<span>到第 <input type="text" value="1" /> 页</span>'
								+ '<a href="javascript:void(0);">确定</a>'
								+ '</div>'
								+ '</div>');
							
                     /**
					 * 分页
					 */
					 if(totalPage <= (breakpage + breakspace*2)){
					 	for (var i = 1; i <= totalPage; i++) {
					 		if (pageNum == i) {
					 			pageField.find('.up-page-main').append('<a href="javascript:void(0);" class="active" data-page="' + i + '">' + i + '</a>');
					 		} else {
					 			pageField.find('.up-page-main').append('<a href="javascript:void(0);" data-page="' + i + '">' + i + '</a>');
					 		}
					 	}
					 }else{

					 	if(pageNum <= breakspace+space+1){
					 		for (var i = 1; i <= breakpage; i++) {
					 			if (pageNum == i) {
					 				pageField.find('.up-page-main').append('<a href="javascript:void(0);" class="active" data-page="' + i + '">' + i + '</a>');
					 			} else {
					 				pageField.find('.up-page-main').append('<a href="javascript:void(0);" data-page="' + i + '">' + i + '</a>');
					 			}
					 		}
					 		pageField.find('.up-page-main').append('<span>...</span>');
					 		pageField.find('.up-page-main').append('<a href="javascript:void(0);" data-page="' + (totalPage-1) + '">' + (totalPage-1) + '</a>');
					 		pageField.find('.up-page-main').append('<a href="javascript:void(0);" data-page="' + totalPage + '">' + totalPage + '</a>');
					 	}else if(pageNum > totalPage - space - breakspace -1){
					 		pageField.find('.up-page-main').append('<a href="javascript:void(0);" data-page="1">1</a>');
					 		pageField.find('.up-page-main').append('<a href="javascript:void(0);" data-page="2">2</a>');
					 		pageField.find('.up-page-main').append('<span>...</span>');
					 		for (var i = totalPage - breakpage + 1; i <= totalPage; i++) {
					 			if (pageNum == i) {
					 				pageField.find('.up-page-main').append('<a href="javascript:void(0);" class="active" data-page="' + i + '">' + i + '</a>');
					 			} else {
					 				pageField.find('.up-page-main').append('<a href="javascript:void(0);" data-page="' + i + '">' + i + '</a>');
					 			}
					 		}
					 	}else{
					 		pageField.find('.up-page-main').append('<a href="javascript:void(0);" data-page="1">1</a>');
					 		pageField.find('.up-page-main').append('<a href="javascript:void(0);" data-page="2">2</a>');
					 		pageField.find('.up-page-main').append('<span>...</span>');
					 		for (var i = pageNum - space; i <= pageNum + space; i++) {
					 			if (pageNum == i) {
					 				pageField.find('.up-page-main').append('<a href="javascript:void(0);" class="active" data-page="' + i + '">' + i + '</a>');
					 			} else {
					 				pageField.find('.up-page-main').append('<a href="javascript:void(0);" data-page="' + i + '">' + i + '</a>');
					 			}
					 		}
					 		pageField.find('.up-page-main').append('<span>...</span>');
					 		pageField.find('.up-page-main').append('<a href="javascript:void(0);" data-page="' + (totalPage-1) + '">' + (totalPage-1) + '</a>');
					 		pageField.find('.up-page-main').append('<a href="javascript:void(0);" data-page="' + totalPage + '">' + totalPage + '</a>');
					 	}
					 }

							//当前为第一页时，将上一页按钮置为禁用状态
							if (pageNum == 1) {
								pageField.find(".prev a").addClass("disabled");
							} else {
								pageField.find(".prev a").removeClass("disabled");
							}
							
							//当前页为最后一页时，将下一页按钮置为禁用状态
							if (pageNum == totalPage) {
								pageField.find(".next a").addClass("disabled");
							} else {
								pageField.find(".next a").removeClass("disabled");
							}
							
							// pageField.find('.up-page-main').append('<span>...</span>');

							for (var j = 0; j < data.data.length; j++) {
								var row = data.data[j];

								var html = '';
								var addStr = [];

								var summarystr = '';
								if (row.summary1 != null) {
									addStr = [];
									addStr = row.summary1.split(':');
									summarystr += '<li><span class="liTitle">' + addStr[0] + '：</span>' + addStr[1] + '</li>';
								}
								if (row.summary2 != null) {
									addStr = [];
									addStr = row.summary2.split(':');
									summarystr += '<li><span class="liTitle">' + addStr[0] + '：</span>' + addStr[1] + '</li>';
								}
								if (row.summary3 != null) {
									addStr = [];
									addStr = row.summary3.split(':');
									summarystr += '<li><span class="liTitle">' + addStr[0] + '：</span>' + addStr[1] + '</li>';
								}
								if (row.summary4 != null) {
									addStr = [];
									addStr = row.summary4.split(':');
									summarystr += '<li><span class="liTitle">' + addStr[0] + '：</span>' + addStr[1] + '</li>';
								}

								var imgurl = '<img src="/normal/images/loadfail.jpg" width="120px" height="120px" alt="">';
								if (row.titlePic != null) {
									imgurl = '<img src="' + row.titlePic + '" width="120px" height="120px" alt="">';
								}

								var buyhtml = '';
								if ($('#currMID').val() != row.mid) {
									buyhtml = '<a target="_blank" href="/mall/item/' + row.listedNo + '.htm" class="btn-normal btn-buy">摘牌</a>';
								}

								html = '<div class="panel">'
								+ '<div class="panel-title">'
								+ '<strong class="product-name">' + row.commName + '</strong>'
								+ '<span class="product-price marr40">' + ' 单价： <small>￥</small>' 
								+ '<big>' + Number(row.up).formatMoney() + '元/' + row.uom + '</big>' + '</span>'
								+ '<span>有效期：' + row.dol + '</span>'
								+ '</div>'
								+ '<ul class="panel-body mart20">'
								+ '<li>'
								+ '<span class="liTitle">仓单编号</span> ：' + row.listedNo
								+ '</li>'
								+ '<li>'
								+ '<span class="liTitle">数量</span> ：<span class="fcred">' + row.rem + '/' + row.qty + row.uom
								+ '</span>吨'
								+ '</li>' + summarystr
								+ '<li>'
								+ '<span class="liTitle">交收仓库</span> ：<span title="' + row.storage + '">' + row.storage + '</span>'
								+ '</li>'
								+ '<li>'
								+ '<span class="liTitle">交收类型</span> ：<span class="promise">' + row.listedTypeName
								+ '</span>'
								+ '</li>'
								+ '</ul>'
								+ '<div class="describe-box">'
								+ '<div class="product-describe"><p >'
								+ '无商品描述'
								+ '</p>'
								+ '</div>'
								+ '</div>'
								+ '<div class="product-bottom">'
								+ '<div class="product-bl">' 
								+ '编号：' + row.mid
								+ '</div>'
								+ '<div class="product-br">'
								+ '<span>卖家</span> ' + row.memName
								+ '</div>'
								+ '</div>'
								+ '<a  href="/mall/item/' + row.listedNo + '.htm"></a>'
								+ '</div>';
								parent.append(html);
							}

							parent.append(pageField);
						}
					}

					/**
					 * 点击页码
					 */
					 $(this).on('click', '.up-page-main a', function() {
					 	var page = parseInt($(this).data("page"));
					 	var options = {
					 		start : initData.length * (page - 1),
					 		length : 4
					 	};
					 	var initOpts = $.extend({}, initData, options);
					 	init(initOpts, page);
					 });

					/**
					 * 输入页码后点击确定
					 */
					 $(this).on('click', '.up-pager a', function() {
					 	var page = parseInt($('.up-pager input').val());
					 	if(totalPage < page){
					 		layer.msg('输入页面太大!');
					 		return 
					 	}
					 	var options = {
					 		start : initData.length * (page - 1),
					 		length : 4
					 	};
					 	var initOpts = $.extend({}, initData, options);
					 	init(initOpts, page);
					 });

					/**
					 * 点击下一页
					 */
					 $(this).on('click', '.next a', function() {
					 	if ($(this).hasClass('disabled'))
					 		return;
					 	var page = parseInt($('.up-page-main a.active').data('page'));
					 	var options = {
					 		start : initData.length * page,
					 		length : 4
					 	};
					 	var initOpts = $.extend({}, initData, options);
					 	init(initOpts, page+1);
					 });

					/**
					 * 点击上一页
					 */
					 $(this).on('click', '.prev a', function() {
					 	if ($(this).hasClass('disabled'))
					 		return;
					 	var activePage = parseInt($('.up-page-main a.active').data('page'));
					 	var prevPage = activePage - 1;

					 	var options = {
					 		start : initData.length * (prevPage - 1),
					 		length : 4
					 	};
					 	var initOpts = $.extend({}, initData, options);
					 	init(initOpts, prevPage);
					 });

					 init(initData, 1);
					});


}
})(jQuery);