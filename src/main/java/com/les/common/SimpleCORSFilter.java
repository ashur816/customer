package com.les.common;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;


/**
 * @author Lydia
 * @ClassName: SimpleCORSFilter
 * @Description:
 * @date 2016/8/25
 */

@Component
public class SimpleCORSFilter implements Filter {
	
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) res;
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE, PATCH");
//        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
//        response.setHeader("Access-Control-Allow-Headers", "*");

        HttpServletRequest request = (HttpServletRequest) req;
        String uri = request.getRequestURI();
        if(!uri.contains("/login")) {//排除登陆请求
            String token = request.getParameter("token");
            boolean noLogin = true;
            if (StringUtils.isNotBlank(token)) {
                MapCacheManager cacheManager = MapCacheManager.getInstance();
                Map<String, String> cashMap = cacheManager.getMapCache();
                String userId = cashMap.get(token);
                if (StringUtils.isNotBlank(userId)) {
                    request.setAttribute("userId", userId);
                    noLogin = false;
                }
            }
            if (noLogin) {
                //重定向登陆页面
                response.sendRedirect("http://www.baidu.com");
                return;
            }
        }
        chain.doFilter(req, res);
    }

    public void init(FilterConfig filterConfig) {}

    public void destroy() {}



}
