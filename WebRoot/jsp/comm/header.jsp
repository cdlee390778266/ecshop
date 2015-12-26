<%@ page language="java" pageEncoding="UTF-8"%>
	<div class="header mt20">

		<div class="grid-16-16 clearfix">

			<div class="header-bd">
				<div class="search-wrap clearfix fn-fr" id="J_SearchTab">
					
					 
					<div class="search-hd clearfix">
						<ul class="subcat switchable-navs">
							<li class="J_SearchTab search-tab selected" data-action="/home.htm">全部商品<i></i></li>		
							<!--					  
							<li class="J_SearchTab search-tab" data-action="shop_search.htm">大厅<i></i></li>
							<li class="J_SearchTab search-tab" data-action="shop_search.htm">专场<i></i></li>	
							-->						
						</ul>
					</div>  
					
					
					<form action="/mall/search.htm" method="post" >
						<div class="space"></div>
						 
						<div class="search-bd">
							<input type="text" class="inp-search" id="keyword" name="keyword" placeholder="您可根据商品名、商品类进行搜索" style="height:20px"/>
							<button type="submit" class="icon-search"><i></i></button>
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
				<div class="logo"><img src="/images/logo.png" alt=""></div>
			</div>
		</div>
	</div>