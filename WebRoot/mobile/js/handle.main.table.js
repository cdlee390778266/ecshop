;
(function($) {

	$.fn.tabulation = function(url) {

		if (!url)
			return;
		$.fn.tabulation.url = url;
		$.fn.tabulation.defaults = {
			start : 0,
			length : 10,
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
                      
                        if (data.data.length > 0) {

                        	recordsTotal = data.recordsTotal;
                        	recordsFiltered = data.recordsFiltered;
                        	totalPage = Math.ceil(recordsTotal / pageSize);

                        	for (var j = 0; j < data.data.length; j++) {
                        		var row = data.data[j];

                        		var html = '';
                        		var addStr = [];

                        		var summarystr = '';
                        		if (row.summary1 != null) {
                        			addStr = [];
                        			addStr = row.summary1.split(':');
                        			summarystr += '<li ><span class="liTitle">' + addStr[0] + '：</span>' + addStr[1] + '</li>';
                        		}
                        		if (row.summary2 != null) {
                        			addStr = [];
                        			addStr = row.summary2.split(':');
                        			summarystr += '<li  ><span class="liTitle">' + addStr[0] + '：</span>' + addStr[1] + '</li>';
                        		}
                        		if (row.summary3 != null) {
                        			addStr = [];
                        			addStr = row.summary3.split(':');
                        			summarystr += '<li  ><span class="liTitle">' + addStr[0] + '：</span>' + addStr[1] + '</li>';
                        		}
                        		if (row.summary4 != null) {
                        			addStr = [];
                        			addStr = row.summary4.split(':');
                        			summarystr += '<li  ><span class="liTitle">' + addStr[0] + '：</span>' + addStr[1] + '</li>';
                        		}

                        		var imgurl = '<img src="/normal/images/loadfail.jpg" width="120px" height="120px" alt="">';
                        		if (row.titlePic != null) {
                        			imgurl = '<img src="' + row.titlePic + '" width="120px" height="120px" alt="">';
                        		}

                        		var buyhtml = '';
                        		if ($('#currMID').val() != row.mid) {
                        			buyhtml = '<a target="_blank" href="/mall/item/' + row.listedNo + '.htm" class="btn-normal btn-buy">摘牌</a>';
                        		}

                        
                                html = '<div class="product" >'
                                + '<div class="product-bar">'
                                + '<span class="product-name">' + row.commName + '</span>'
                                + '<span class="product-price"><i>￥</i> <strong>'+ Number(row.up).formatMoney()+'元/'+row.uom+'</strong></span>'
                                + '<span class="glyphicon glyphicon-menu-down"></span>'
                                + '</div>'
                                + '<div class="product-data">'
                                + '<ul>'
                                + '<li>仓单编号：' + row.listedNo + '</li>'
                                + '<li>数量： <span class="red">'+ row.rem+'/'+row.qty + '</span> ' + row.uom + '</li>'
                                + summarystr
                                + '<li>交收仓库：' + row.storage + '</li>'
                                + '<li>仓库地址：</li>'
                                + '<li>交收类型： <span class="red radius">'+row.listedTypeName+'</span></li>'
                                + '</ul>'
                                + '<div class="seller row">'
                                + '<div class="col-xs-6 col-sm-6 seller-id">编号：'+ row.mid +'</div>'
                                + '<div class="col-xs-6 col-sm-6 seller-date">有效期：' + row.doe + '</div>'
                                + '<div class="col-xs-12 col-sm-6 seller-name"><span>卖家</span>' + row.memName + '</div>'
                                + '<div class="col-xs-12 col-sm-6 ">'
                                + '<a href="/mall/item/'+row.listedNo+'.htm">'+imgurl+'</a>'
                                + '</div>'
                                + '</div>'
                                + '</div>'
                                + '</div>'
                                parent.append(html);
                            }

                        }else{
                         $('.loadMore').addClass('disabled');
                         $('.loadMore').html('已加载所有商品');
                         $('.loader').hide();
                     }

                      
                 }

					/**
					 * 加载更多
					 */
                    $('.loadMore').click( function() {
                        if($(this).hasClass('disabled')) 
                            return ;
                        $('.loader').show();
                        var page = $(this).data('page');
                        $(this).data('page',page+1);
                        var options = {
                            start : initData.length * page,
                            length : 4
                        };
                        var initOpts = $.extend({}, initData, options);
                        init(initOpts, page);
                    });
                    init(initData, 1);
                });

}
})(jQuery);

