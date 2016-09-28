$(function() {
	
	$(document).ready(function() {
		$('.fixed-wrapper').stickUp();
	});
	
	var difftime =  6*24*60*60*1000;
	$('#endDate').val(new Date().format("yyyy-MM-dd"));
	$('#beginDate').val(new Date((new Date()).valueOf()-difftime).format("yyyy-MM-dd"));

	$('#beginDate, #endDate').on("blur", function(event){   
		$('.datepicker-container').hide();
	});
	
	var columns = [
		            { "data": "trTypeName", "type":"cn-string"  },
		            { "data": "accTitleName", "type":"cn-string"  },
		            { "data": "acNo", "type":"string" },
		            { "data": "occTime" },
		            { "data": "chgABV" },		        
		            { "data": "aftBal"},
		            { "data": "aftAB"},
		            { "data": "remark", "type":"cn-string"}
		        ];
	
	var surl = '/fund/findplatlist.htm';
	
	var ajaxfn  = function(d){
		d.qryType = $('input[name="qryType"]:checked').val(),
		d.beginDate = $("#beginDate").val(),
		d.endDate = $("#endDate").val(),
		d.origAcNo = $("#origAcNo").val(),
		d.iae = $("#iae").val(),
		d.costCode = $("#costCode").val(),
		d.extNo = $("#extNo").val();
	};
	
	var defaultOrder = [ 2, "asc" ];
	
	var rowfn  = function(row, data, index){
		
		$('td:eq(4)', row).html(Number(data.chgABV).formatMoney());
   	 	$('td:eq(5)', row).html(Number(data.aftBal).formatMoney());
   	 	$('td:eq(6)', row).html(Number(data.aftAB).formatMoney());
	}
	
	
	var btnfn = function(){
		
		
		if($('#beginDate').val()==''&&$('#endDate').val() == ''){
		    UI.Dialog({type : 'tips',width : '80%', title : '查询提示',content : '请必须输入账务开始日期或结束日期'}).show();						
			event.stopPropagation();
			return false;
		}
		
		//为空是填入默认值
		if($('#beginDate').val()==''){
			$('#beginDate').val($('#endDate').val());
		}
		if($('#endDate').val()==''){
			$('#endDate').val($('#beginDate').val());
		}
		
		if($('#beginDate').val() > $('#endDate').val()){
		    UI.Dialog({type : 'tips',width : '80%', title : '查询提示',content : '账务开始日期大于结束日期'}).show();						
			event.stopPropagation();
			return false;
		}
		
		return true;
	}
	
	dataTablesInit('#dataset', surl, ajaxfn, defaultOrder, btnfn, columns, null, rowfn);
	
});