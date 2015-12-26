package com.cnacex.eshop.web.interceptor;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.cnacex.comm.util.StringUtil;
import com.cnacex.eshop.msg.body.member.LoginRsp;
import com.cnacex.eshop.service.imp.MallServiceImp;
import com.cnacex.eshop.util.SysStatusUtil;


public class LoginInterceptor extends HandlerInterceptorAdapter{
	
	
	 private static final String[] IGNORE_URI = {"/index.htm", "/error/","/login.htm","/member/logout.htm"};
	 
	 private static final String LOGIN_URL = "/index.htm";
	 

	
	static Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		super.afterCompletion(request, response, handler, ex);
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		
		String status = MallServiceImp.SYS_STATUS;
		request.getSession().removeAttribute("sysstatus");
		request.getSession().setAttribute("sysstatus", status);	
		
		request.getSession().removeAttribute("sysstatusDesc");
		request.getSession().setAttribute("sysstatusDesc", SysStatusUtil.getSysStatus(status));	

		super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		
		 	boolean flag = false;
	        String url = request.getRequestURL().toString();
	        
	        logger.debug("请求URI {}", url);
	        
	        for (String s : IGNORE_URI) {
	            if (url.contains(s)) {
	                flag = true;
	                break;
	            }
	        }
	        if (!flag) {
	        	
	        	Object obj = request.getSession().getAttribute("userinfo");
	        	if(obj != null){
		        	LoginRsp loginRsp = (LoginRsp)obj;
		        	
		        	if(loginRsp != null && !StringUtil.nullOrBlank(loginRsp.getmID()))
		        	{
		        		flag = true;
		        	}	     
	        	}
	        }
	        
	        if(flag == false){
	        	response.sendRedirect(LOGIN_URL);
	        }
	        return flag;
	}
	
	
	

}
