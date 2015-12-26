
/**
 * 
 * 格式化金额
 * @param places
 * @param symbol
 * @param thousand
 * @param decimal
 * @returns
 */

Number.prototype.formatMoney = function(places, symbol, thousand, decimal) {
	places = !isNaN(places = Math.abs(places)) ? places : 2;
	symbol = symbol !== undefined ? symbol : "";  //¥
	thousand = thousand || ",";
	decimal = decimal || ".";
	var number = this, negative = number < 0 ? "-" : "", i = parseInt(
			number = Math.abs(+number || 0).toFixed(places), 10)
			+ "", j = (j = i.length) > 3 ? j % 3 : 0;
	return symbol
			+ negative
			+ (j ? i.substr(0, j) + thousand : "")
			+ i.substr(j).replace(/(\d{3})(?=\d)/g, "$1" + thousand)
			+ (places ? decimal + Math.abs(number - i).toFixed(places).slice(2)
					: "");
};

/**
 * 
 * 格式化输出日期
 * @param fmt
 * @returns
 */

Date.prototype.format = function(fmt)   
{  
  var o = {   
    "M+" : this.getMonth()+1,                 //月份   
    "d+" : this.getDate(),                    //日   
    "h+" : this.getHours(),                   //小时   
    "m+" : this.getMinutes(),                 //分   
    "s+" : this.getSeconds(),                 //秒   
    "q+" : Math.floor((this.getMonth()+3)/3), //季度   
    "S"  : this.getMilliseconds()             //毫秒   
  };   
  if(/(y+)/.test(fmt))   
    fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));   
  for(var k in o)   
    if(new RegExp("("+ k +")").test(fmt))   
  fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));   
  return fmt;   
}  


dataTablesInit = function(table, url,  ajaxfncallback, defaultOrder, btnfncallback,  columns, columndefs, rowfncallback){
	
	var columnselector =new Array()
	
	var columnlength =  $('#dataset thead').find('th').length;
	
	if(columndefs != null) columnlength--;
	
	for(var i = 0; i < columnlength; i++){
		columnselector.push(i);
	}
	
	var hiddencolumn = [];
	
	//设置FLASH路径
	$.fn.dataTable.Buttons.swfPath = '/images/swf/flashExport.swf';
	
	$.fn.dataTable.ext.type.order['cn-string-asc']  = function(s1,s2) {  
	    return s1.localeCompare(s2);  
	};  
	
	$.fn.dataTable.ext.type.order['cn-string-desc']  = function(s1,s2) {  
	    return s2.localeCompare(s1);  
	}; 
	
	//定义页面的DataTable对象
	var $dataset = $(table).DataTable( {
	        "ajax": {
	        	"url": url,
	            "data":  function ( d ) {	           
	            	if( typeof ajaxfncallback === 'function' )
	            		ajaxfncallback(d);            	
	            }
	        },
	        "order": defaultOrder,
	        "scrollX": true,
	        "scrollY": '330px',
	        "processing" : true,
	        "language": {
	        	"processing": "",
	            "lengthMenu": "每页显示 _MENU_ 条",
	            "zeroRecords": "无相关数据记录",
	            "info": "显示从_START_ 到 _END_ 记录,共 _TOTAL_ 条记录", 
	            "infoEmpty": "",
	            "infoFiltered": "(从 _MAX_ 总记录查询)",
	            "search": "结果检索：",
	            'paginate': {  
	                'first':      '第一页',  
	                'last':       '最后一页',  
	                'next':       '下一页',  
	                'previous':   '上一页'  
	            },
	            "decimal": ".",
	            "thousands": ","
	        },
	        "lengthMenu": [[10, 20, -1], [10, 20, "全部"]],
	        "colVis": {
	            "buttonText": "改变显示列"
	        },
	        buttons: [ 
	          {
				text: '条件查询',
				action: function ( e, dt, node, config ) {					
					var flag = true;					
					if(typeof btnfncallback === 'function'){
						flag = btnfncallback();
					}
					if(flag == true){
						resetColume(dt);					
					    dt.ajax.reload(function(data){			
					    	reloadColumn(dt);
					    });
					}
				 }
	           },
	           {
	             extend: 'excelFlash',
	             text: '导出Excel',
	             orientation: 'landscape',
	             pageSize: 'LEGAL',
	             exportOptions: {
	                columns: [':visible']
	             }
	           }
	        ],	        
	        "columns": columns,  
	        "columnDefs": columndefs,
	        "dom": 'CfB<"clear">rt<"bottom"ilp><"clear">',
	        initComplete: function () {
	            this.api().columns(columnselector).every( function () {
	                var column = this;                
	                var title = column.header().innerHTML;	                
	                $(column.header()).html('');	                
	                var select = $('<select><option value="">'+title+'</option></select>').appendTo(column.header()).on( 'change', function () {
	                        var val = $.fn.dataTable.util.escapeRegex(
	                            $(this).val()
	                        );	 
	                        column
	                            .search( val ? '^'+val+'$' : '', true, false )
	                            .draw();
	                    } );
	                column.data().unique().sort().each( function ( d, j ) {
	                	if(d != undefined && d != null && d != '')
	                		select.append( '<option value="'+d+'">'+d+'</option>' )
	                } );
	            } );	            
	            this.api().columns.adjust();
	        },
	        createdRow: function ( row, data, index ) {	        	       
	        	if(typeof rowfncallback === 'function'){
	        		rowfncallback(row, data, index);
	        	}
	        }
	} );
	
	
	//查询复位回调
	function resetColume(datatable){
		
		hiddencolumn = [];
		
		datatable.columns(columnselector).every( function () {
	  	       var column = this;    
	  	       
		  	   if(column.visible() == false){
		  		  hiddencolumn.push(column.index());
			  	  column.visible(true);
			   }
	  	       var select =  $(column.header()).find('select');
	  	       $(select).val('');			  	       
	  	       column.search('', true, false ).draw();
	  	 } );
	}
	
	//重新载入数据标题选择重新生成回调
	function reloadColumn(datatable){
		datatable.columns(columnselector).every( function () {
  	       var column = this;                
  	       var select =  $(column.header()).find('select');
  	       $(select).find("option[value!='']").remove(); 
  	       column.data().unique().sort().each( function ( d, j ) {
  	                	if(d != undefined && d != null && d != '')
  	                		select.append( '<option value="'+d+'">'+d+'</option>' )
  	                } );
  	    } );
		
		datatable.columns(hiddencolumn).every( function () {
	  	    var column = this;    		  
	  	    var colindex = column.index();
		  	 if(column.visible() == true){ 
		  		 column.visible(false); 
		  		$('.ColVis_collection').find('input[type=checkbox]').eq(colindex).attr('checked',false);
		  	 }	  	  
	  	});
		datatable.columns.adjust();
	}
	
	//选择加粗显示
	$(table + ' tbody').on( 'click', 'tr', function () {
        if ( $(this).hasClass('selected') ) {
            $(this).removeClass('selected');
        }
        else {
        	$dataset.$('tr.selected').removeClass('selected');
            $(this).addClass('selected');
        }
    } );
	
	
	return $dataset;
	
}