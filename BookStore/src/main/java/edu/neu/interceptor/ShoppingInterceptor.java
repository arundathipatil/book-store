package edu.neu.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.apache.commons.io.IOUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;

public class ShoppingInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        try{
            Enumeration<String> params = request.getParameterNames();
            while(params.hasMoreElements()){
                String param = (String) params.nextElement();
                String value = request.getParameter(param);
                if(check(value)){
                    request.setAttribute("unsafe_input", "true");
                    break;
                }
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
        return true;
    }

//    @Override
//    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//        System.out.println("POST HANDLE");
//    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("AFTER COMPLETION");
    }

    private boolean check(String value) {
        if (value != null) {
            return (value.matches("<script>(.*?)</script>") || value.matches("\"<script(.*?)>\""));
        }
        return false;
    }
}
