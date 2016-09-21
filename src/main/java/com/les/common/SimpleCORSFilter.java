package com.les.common;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger logger = LoggerFactory.getLogger(SimpleCORSFilter.class);

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) res;
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE, PATCH");
//        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
//        response.setHeader("Access-Control-Allow-Headers", "*");

        HttpServletRequest request = (HttpServletRequest) req;
        String uri = request.getRequestURI();
        if (!uri.contains("/login")) {//排除登陆请求
            String token = request.getParameter("token");
            boolean noLogin = true;
            if (StringUtils.isNotBlank(token)) {
                MapCacheManager cacheManager = MapCacheManager.getInstance();
                Map<String, String> cashMap = cacheManager.getMapCache(token);
                String userId = cashMap.get("userId");
                if (StringUtils.isNotBlank(userId)) {
                    request.setAttribute("loginUserId", userId);
                    noLogin = false;
                }
            }
            if (noLogin) {
                //重定向登陆页面
                response.sendRedirect("http://localhost:63342/customer/web/WEB-INF/project3/login.html");
                logger.error("未登录");
                return ;
            }
        }
        chain.doFilter(req, res);
    }

    public void init(FilterConfig filterConfig) {
    }

    public void destroy() {
    }


}
