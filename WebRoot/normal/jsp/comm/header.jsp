<%@ page language="java" pageEncoding="UTF-8"%>
	<div class="header mt20">

		<div class="grid-16-16 clearfix">

			<div class="header-bd">
			<div class="fr delisting" >
					<a href="/sell/apply.htm?active=enter&type=0" >卖方挂牌</a>
				</div>

				<div class="search-wrap clearfix fn-fr" id="J_SearchTab">
					
					 
					<div class="search-hd clearfix">
						<ul class="subcat switchable-navs">
							<li class="J_SearchTab search-tab selected" data-action="/home.htm">全部商品</li>		
							<!--					  
							<li class="J_SearchTab search-tab" data-action="shop_search.htm">大厅<i></i></li>
							<li class="J_SearchTab search-tab" data-action="shop_search.htm">专场<i></i></li>	
							-->						
						</ul>
					</div>  
					
					
					<form action="/mall/search.htm" method="post" >
						<div class="search-bd">
							<input type="text" class="inp-search" id="keyword" name="keyword" placeholder="您可根据商品名、商品类进行搜索" style="height:20px" value="${keyword}"/>
							<button type="submit" class="icon-search">搜索</button>
						</div>
					</form>

					<!--  
					<div class="search-ft">
						<div class="search-hots">
							<div class="search-hots-sline">
								<dl>
									<dt><span>热门搜索：</span></dt>
									<dd>
										<a href="javascript: void(0)" class="h">化肥</a>
										<a href="javascript: void(0)" class="h">农药</a>
										<a href="javascript: void(0)" class="h">农用机械</a>
										<a href="javascript: void(0)" class="h">水果</a>
										<a href="javascript: void(0)" class="h">中药材</a>
										<a href="javascript: void(0)" class="h">农作物</a>
										<a href="javascript: void(0)" class="more more-with-border">更多&gt;&gt;</a>
									</dd>
								</dl>
								
							</div>
						</div>
					</div>
					-->
				</div>
				
				<div class="logo"><a href="/home.htm"><img src="/normal/images/logo.png" alt=""></a></div>
			</div>
		</div>
	</div>

	 <!-- nav -->
   <div class="nav">
       <div class=" grid-16-16 rel">
         <dl class="nav-dl">
             <dt>全部商品分类<span class="fa fa-angle-down"></span></dt>
             <dd id="nav-buy-tree">
             </dd>
         </dl>
         <ul class="nav-ul">
             <li><a href="">交易大厅</a></li>
             <li><a href="">农资挂牌</a></li>
             <li><a href="">农副挂牌</a></li>
             <li><a href="">会员专场</a></li>
             <li><a href="">挂牌行情</a></li>
         </ul>
     </div>
 </div>
 <!-- nav -->
 <script type="text/javascript">
 
 	function fillData(data,level,id){
 		if(data==null ||data.length<=0){
 			return ;
 		}
 		var subdata = null;//遍历的子体
 		var name = null;//分类名称
 		var code = null;//分类代码
 		var html = null;
 		
 		var leafFlag = false;
 		var leafId = id+"-leaf"
 		
 		
 		for(var i=0;i<data.length;i++){
 			subdata =  data[i];
 			name    =  subdata.name;
 			code    =  subdata.code;
 			subid   =  id+"-"+subdata.code;
 			if(level==1){
 				html = "<h2>"+
		         		 "<span><i></i>"+name+"<span></span></span>"+
         		  		 "<div class='dl-left' id='"+subid+"'></div>"+
         		       "</h2>";
 			}else if(level==2){
 				if(subdata.level==2){
 					html ="<dl>"+
                            "<dt>"+name+"<i></i></dt>"+
                            "<dd id='"+subid+"'></dd>"+
                          "</dl>";
 				}else{//当2级节点为叶子节点
 					if(!leafFlag){
 						leafFlag = true;
 						html ="<dl>"+
 	                    		"<dt><i></i></dt>"+
 	                   			"<dd id='"+leafId+"'>"+
 	                   				"<a href=''>"+name+"</a>"+
 	                   			"</dd>"+
 	                   		"</dl>";
 					}else{
 						html="<a href='/mall/class/"+code+".htm'>"+name+"</a>";
 						$("#"+leafId).append(leafHtml);
 						return;
 					}
 				}
 			}else{
 				html ="<a href='/mall/class/"+code+".htm'>"+name+"</a>";
 			}
            $("#"+id).append(html);
            if(subdata.childNodes!=null){
                fillData(subdata.childNodes,level+1,subid)
            }
 		}
 	}
 	
 	$(document).ready(function(){
 		var  marketurl = '/mall/findbuytree.htm';
 		$.ajax({
 				type : 'GET',
 				url : marketurl,
 				cache : false,
 				dataType : 'json',
 				success : function(res) {
 					if (res.succflag == 0) {
 						var classcodes = res.data;
 						if(classcodes!=null){
 							fillData(classcodes,1,"nav-buy-tree");
 						}
 					}
 				}
 		});	
 	})
 </script>