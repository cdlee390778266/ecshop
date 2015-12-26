package com.cnacex.eshop.web.interceptor;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

public class ExceptionHandler implements HandlerExceptionResolver {

	@Override
	public ModelAndView resolveException(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception ex) {
		
		
		Map<String, Object> model = new HashMap<String, Object>();
		
	    if (ex instanceof MaxUploadSizeExceededException){ 
	    	
	    	model.put("message", "商品描述图不得大于 "+getFileKB(((MaxUploadSizeExceededException)ex).getMaxUploadSize()));

         }else{  
        	 model.put("message", "处理异常: "+ex.getMessage());
        }  
	    return new ModelAndView("comm/fail", model);
	}
	
	private String getFileKB(long byteFile){  
        if(byteFile==0)  
           return "0KB";  
        long kb=1024;  
        return ""+byteFile/kb+"KB";  
    }  

}
