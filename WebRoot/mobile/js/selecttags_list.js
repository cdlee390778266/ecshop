;(function($){
	$.fn.SelectTags = function(options,fn){
		$.fn.SelectTags.defaults = {  
			catText:'',
          initData:null, 
          surl:'',
          removeUrl:'',
          callback:function(){return false;},
          defaultparams:'',
          exceptcallback:function(){return false;}
      };
      var opts = $.extend({}, $.fn.SelectTags.defaults, options);
        /*var shtm = '<div class="hd">'+
            '<label for="">'+opts.catText+'</label>'+
            '</div>'+ */
            var shtm = '<div class="bd">'+
            '<div class="crumb-list">'+
            '</div>'+
            '</div>'+
            '<div class="ft">'+
            '</div>'+
            '<div class="rlist">'+
            '</div>'+
            '<div class="code-box">'+
            '<input type="hidden" value="" id="code"/>'+
            '</div>';

            var tag = '<a class="pro icon-tag J_tag"  data-key="{key}" data-leaf="{leaf}" data-level="{level}" data-action="remove" href="javascript: void(0)">'+
            '{value}'+
            '<span class="icon-btn-x"></span>'+
            '</a>';

            var arrow =	'<a class="icon-btn-vbarrow"></a>';

            $('body').append('<div class="select_mask"></div>');

            $('.select_mask').on('click',function(){
               $('.select_mask').css('display','none').animate({opacity: 0});
               $('.selcomm').css('bottom','-50%');
               $('.rlist .commsection').css('display','none');
               $('.rlist .commsection:first-child').css('display','block');
               $('body').css('overflow','auto');
           })

            return this.each(function(){ 
              var that = $(this), selData = opts.initData;
              that.append(shtm);
              function init(sel){
                if(!sel){
                  return;
              }else{
               var idata = sel;

               var level = 1;
               if(idata.length > 0){
                 level = idata[0].level;               
             }

             var head = '';
             if(level == 0){
                 head = "市场";
             }else if(level == 1){
                 head = "分类";
             }else if(level == 2){
              head = "类别";
          }else if(level == 3){
              head = "商品";
          }

          if(level == 0){
              var ulh = '<div class="commsection"><div class="commhead">'+head+'<span class="ch-close"><img src="/mobile/images/select_close.png" /></span></div><div class="commbd"><ul>';
          }else{
              var ulh = '<div class="commsection"><div class="commhead"><span class="sel-back"><img src="/mobile/images/sel-back.png" /></span>'+head+'<span class="ch-close"><img src="/mobile/images/select_close.png" /></span></div><div class="commbd"><ul>';
          }

          for(var i=0;i<idata.length;i++){
              ulh += '<li data-level="'+idata[i].level+'" data-key="'+idata[i].code+'" data-leaf="'+idata[i].haveLeaf+'">'+idata[i].name+'</li>';
              
          }
          ulh+='</ul></div><div>'
          that.find('.rlist').append(ulh);
      }
  }
  function fixTags(key,value, leaf, level){ 
   if(key && value ){           		
      if(level != 0){
         that.find('.crumb-list').append(arrow);
     }            		
     that.find('.crumb-list').append(tag.replace("{key}",key).replace("{value}",value).replace("{leaf}",leaf).replace("{level}",level));           		
 }
}
init(selData);

            /*
            $(this).on('change','.J_Tselect',function(){
                var id= $(this).val(),haveleaf= $(this).find("option:selected").data('leaf'),level= $(this).find("option:selected").data('level'),txt = $(this).find("option:selected").text();
                if(!id) return;
                
                if(!!haveleaf){ 
                	 fixTags(id,txt, haveleaf, level);	
                	 render(id,opts.surl,opts.defaultparams);
                }else{ 
                	fixTags(id,txt, haveleaf, level);
                	$('.crumb-list').find('a.icon-btn-vbarrow:last').remove();
                	$(this).remove();
                }
                pager(id, level=='3'?false:true, true);
            })
*/


$(this).on('click','.commbd li',function(){
  var id= $(this).data('key');           
  var haveleaf= $(this).data("leaf");
  var level = $(this).data("level");               
  var txt =  $(this).text(); 

  var contexts = $(this).parent();
  var context = contexts.find('.active');

  var pid = $(context).data('key');
  var phaveleaf = $(context).data('leaf');

  var plevel = $(context).data("level");               
  var ptxt =  $(context).text(); 

  var navlables =  $('.crumb-list a');
  var idx = -1;
  for(var i = 0; i < navlables.length; i++){
     var navkey = $(navlables[i]).data('key');
     if(navkey != undefined && navkey == pid){
        idx = i;
    }
}
if(idx >= 0){
   var nav = navlables.eq(idx);
   $($(nav).nextAll()).remove();

   var prevnode = $(nav).prev();
   if(prevnode != undefined)
      $(prevnode).remove();               	
  $(nav).remove();
}

var grandnode = $(this).parents(".commsection");         
var grandnext = $(grandnode).nextAll();              
$(grandnext).remove();     
$(this).siblings().removeClass('active');                  
$(this).addClass('active');

var nothingsel = false;
if(!id) {      
  var grandprev = $(grandnode).prev();                  
  var selli = $(grandprev).find('.active');                   
  if(selli != undefined){                  
     id= $(selli).data('key');           
     haveleaf= $(selli).data("leaf");
     level = $(selli).data("level");               
     txt =  $(selli).text(); 
     nothingsel = true;
 }
}

if(!!haveleaf){                	
   if(nothingsel == false){
     fixTags(id,txt, haveleaf, level);	
     render(id,opts.surl,opts.defaultparams);
 }
}else{ 
   fixTags(id,txt, haveleaf, level);
}
var selflag = true;
var prevkey = pid;
if(nothingsel == true && !id){
   id = pid;
   level = 0;
   selflag = false;
   prevkey = '';
}           
pager(id, level=='3'?false:true, selflag, prevkey);


if(level=='3'){
  $('.selcomm_dialog').html($(this).html());
  $('.select_mask').click();
}else{
   $('.selcomm_dialog').val('请选择挂牌商品');
}

grandnode.hide();
grandnext.show();

})

$(this).on('click','.ch-close',function(){
   $('.selcomm_dialog').val('请选择挂牌商品');
   $('.select_mask').click();
})

$(this).on('click','.sel-back',function(){
   $(this).parents('.commsection').hide();
   $(this).parents('.commsection').prev().show();
})


function pager(param, haveleaf, selflag, prevkey){ 
   if(typeof opts.callback != 'undefined'){ 
      var propagation = opts.callback(param, haveleaf, selflag, prevkey);	
      return propagation;
  }	
}
$(this).on('click','.J_tag',function(e){ 
   var key = $(this).data('key');
   var leaf = $(this).data('leaf');
   var level = $(this).data('level');


   var activeset = $('.rlist .active');
   var activeitem =  null;
   for(var i = 0; i < activeset.length; i++){
      var actkey = $(activeset[i]).data('key');
      if(actkey != undefined && actkey == key){
         activeitem = $(activeset[i]);
         break;
     }
 }

 if(activeitem != null){
  grandnode = $(activeitem).parents(".commsection");         
  var grandnext = $(grandnode).nextAll();              
  $(grandnext).remove();                         
  $(grandnode).remove();
}

if(key){ 
  $(this).nextAll().remove();
  var prevkey = '';

  var prevAllobj = $(this).prevAll();
  if( typeof prevAllobj != undefined&& prevAllobj.length >= 2){                   	
     prevkey = $(prevAllobj[1]).data('key');
 } 

 var prevnode = $(this).prev();
 if(prevnode != undefined)
  $(prevnode).remove(); 

$(this).remove();
pager(key, level=='3'?false:true, false, prevkey);
render(key,opts.removeUrl, opts.defaultparams)
}
e.preventDefault();
})

function render(code,surl, params){
  var postParam = "code="+code;

  if(typeof params != 'undefined' && params != null){ 
     for(var k in params) {              		
        postParam += ("&"+k+"="+params[k]);
    }
}
$.ajax({    
  type:'GET',        
  url:surl,    
  data:postParam,    
  cache:false,    
  dataType:'json',    
  success:function(res){	
     if(res.succflag==0){
        init(res.data);
    }else{    
        $('.J_Tselect').hide();
        if(typeof opts.exceptcallback != 'undefined'){ 
           var propagation = opts.exceptcallback(res.msg);	
           return propagation;
       }                    	   
   }                               			    
}
});                
} 

}) 
}


})(jQuery);

$(function(){
  $('.selcomm_dialog').click(function(){
    $('.select_mask').css('display','block').animate({opacity: 0.5});
    $('.selcomm').animate({bottom: 0});
    $('body').css('overflowY','hidden');
})
  $('.selcomm_dialog').focus(function(event) {
     $(this).blur();
 });
})