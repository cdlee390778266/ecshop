;(function(){
      if(typeof PW == "undefined" || !PW) {
           var PW = window.PW || {};
      }
    var x = PW,
    d_close_s='dclose,.d-cancel,.btn-close',
    plugin=function(A){
        this.init(A);
        return this;
    };
    plugin.prototype={
       init:function(A){
          this.render(A);
          this.bind();
          this.shower();
          this.updatePosition();
          return this;
       },
       shower:function(){
            this.node.show();
            this.mask.show();
            return this; 
       },
        render: function(A) {
            this.node = $('#'+A);
            this.mask = $("#masker");
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
            this.node.remove();
            this.mask.hide();
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
                   this.mask.show();
               }
               this.node.css({
                  left:Math.floor( l / 2- A / 2) + 'px',
                  top:Math.floor(h / 2 - C / 2) + 'px'
               })

             
          return this;
        }};
        x.Dialog = function(A) {
            return new plugin(A);
        };
        
        window.PW = x;     
})(); 