(function(){
  var x = window.eui || {},
	  d_tips = "提示",
	  e = "eui-dialog",
	  u=[],
	  d_main = 'dialog',
	  d_content = "dialog-content",
	  d_close_s= "dialog-close", 
	  d_close = "j_close_dialog",
	  d_shadow="dialog-shd",
      i='<div id="{ID}" class="'+ d_main +'" style="display:none;">'+		
        '<span class="'+ d_shadow+'"></span>'+
        '<div class="'+ d_content +'" style="z-index: 2001;" >{BN_CLOSE}'+ 
            '{TITLE}'+			
            '<div class="bd">'+
                '{BODY}'+
           '</div>'+				
        '</div>'+
        '<div class="mask" style="z-index: 2000;display: block;" ></div>'+
    '</div>',
	html_close = '<a href="javascript: void(0)" class="' + d_close_s + " " + d_close + '"></a>',
	d_title = '<div class="hd"><h3>{TITLE}</h3></div>',
    bt_delete_box ='<input type="button" class="btn-confim" value="确 定"><input type="button"  class="btn-cancel" value="取 消">',
    bt_tips_box = '<a href="javascript: void(0)" class="btn-tips">确 定</a>',
	q=$(i),
	y = {type:"",content:"",buttons:[],nodeId:"",cls:"",title:d_tips,href:null,param:null,width:0,height:0,maxWidth:960},
	plugin=function(A){
		var B = A || {};
        this.config = h(B, y);
        if(!$('.eui-dialog').length){
            this.init();
            return this;
        };
	},
	h = function(D, C) {
			var A = {}, B;
			for (B in C) {
				if (C.hasOwnProperty(B)) {
					A[B] = D[B] || C[B]
				}
			}
			return A
    };
	plugin.prototype={
	       init:function(){
			  if(!this.config){
				  return;
			  }
			  this.render();
			  this.bind();
			  this.updatePosition();
			  return this;
		   },
           show:function(){
                if($(this.node).is(':hidden')){
                   this.node.show();
                   return this; 
                }
                
           },
		   render: function(C) {
            var A = this.config, D = A.nodeId || e + u.length;
            u.push(D);
            var B = typeof A.content === "object" ? $(A.content).html() : A.content;
            $("body").append(i.replace("{ID}",D).replace("{CLS}", A.cls).replace("{TITLE}", d_title.replace("{TITLE}", A.title)).replace("{BN_CLOSE}",html_close).replace("{BODY}", B || C || ""));
            this.node = $("#" + D);
			this.title = $(".hd", this.node);
            this.body = $(".bd", this.node);
            this.shadow = $("." +d_shadow ,this.node);
			this.set(A);
			return this
            },
            bind:function(){
			   var A=this;
		       $(window).bind({
				 'resize':function(){
					A.updatePosition();	 
			     },
				 'scroll':function(){ 
					 A.updatePosition();
				 }  
			   });
			   A.node.delegate('.'+d_close_s,'click',function(e){
			       A.close();
				   e.preventDefault();
			   })
			 return this
		   },
		   close:function(){
                  this.node.remove();
                  return this;
		   },
		   set:function(F){
               var btns=[];
			  if (!F) {
                 return this;
			   }
               if(F.type=='delete'){
                   btns.push(bt_delete_box);
                   this.body.parent().append('<div class="ft"><span class="bn-flat">'+ btns.join('')+'</span></div>');
               }else if(F.type=='tips'){
                   btns.push(bt_tips_box);
                   this.body.parent().append('<div class="ft"><span class="bn-flat">'+ btns.join('')+'</span></div>');
               }else{ 
               		this.body.parent().append('<div class="ms"></div>');
               }
               this.node.css("box-shadow", "0 0 80px 0 rgba(0,0,0,.4)");
			   if (F.width) {
                   this.node.css("width", F.width + "px");
                   this.config.width = F.width
				   this.shadow.css("width",F.width+"px");
				}
				if (F.height) {
					this.node.css("height", F.height + "px");
					this.config.height = F.height;
				}
				if (F.title) {
					this.setTitle(F.title);
					this.config.title = F.title
				}
				if (F.content) {
					if(F.type=='delete'){
						var info = '<div class="delete-info">'+(typeof F.content === "object" ? $(F.content).html() : F.content)+'</div>';
						this.body.html(info);
						this.config.content = F.content;
					}else{
						var info ='<div class="tips-info">'+(typeof F.content === "object" ? $(F.content).html() : F.content)+'</div>';
                        this.body.html(info);
						this.config.content = F.content;
					}
                    this.footer = $(".ft",this.node);
                    var node=this.node;
                    $(".ft input.btn-cancel, .ft a.btn-tips", this.node).click(function(e) {
                        node.remove();
                        e.preventDefault();
                    })
                    if(F.href){
                       this.config.href = F.href;
					this.config.param = F.param;
                       $(".ft input.btn-confim",this.node).click(function(e) {
                            e.preventDefault();
                            node.remove();    
                            F.href(F.param);
                        }) 
                    }
                    
				}
		   },
		   setTitle:function(A) {
                $("h3", this.title).html(A);
                return this
			},
           updatePosition:function(){
                var w=$(window),
                    l=w.width(),
					h=w.height(),
		            A=this.node.width(),
				    C=this.node.height(),
					B = w.scrollTop();
				this.node.css({
					left:Math.floor( l / 2- A / 2) + 'px',
					top:Math.floor( h / 2 -C / 2)  + 'px'
				})
			  return this
		    }  			
		}
		x.Dialog = function(A) {
			return new plugin(A);
		};
        window.UI = x;
})();
(function(){
      if(typeof UP == "undefined" || !UP) {
           var UP = window.UP || {};
      }
    var x = UP,
    d_close_s='close,.d-cancel',
    plugin=function(A){
        this.init(A);
        return this;
    };
    plugin.prototype={
       init:function(A){
          this.render(A);
          this.bind();
          this.show();
          this.updatePosition();
          return this;
       },
       show:function(){
            this.node.show();
            this.mask.show();
            return this; 
       },
        render: function(A) {
            this.node = $('#'+A);
            this.mask = $('<div class="mask" id="masker"></div>');
            return this
        },
        bind:function(){
           var A=this;
           $(window).bind('resize',function(){
               A.updatePosition();
           });
           A.node.delegate('.'+d_close_s,'click',function(e){
               A.close();
               e.preventDefault();
           })
         return this
       },
       close:function(){
            this.node.hide();
            this.mask.remove();
            return this;
       },updatePosition:function(){
            var w=$(window),
                l=w.width(),
                h = w.height(),
                bh=$(window).height(),
                A=this.node.width(),
                C=this.node.height(),
                B = w.scrollTop();
               if(this.mask.is(":hidden") && this.node.is(":visible")){
                   $('body').append(this.mask);
               }
             this.node.css({
                left:Math.floor( l / 2- A / 2) + 'px',
                top:Math.floor(h / 2 -C / 2)  + 'px'
            })
          return this;
        }};
        x.Dialog = function(A) {
            return new plugin(A);
        };
        
        window.UP = x;     
})(); 