package com.mg.csbackstage.core;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by BennieSun on 2017/1/22.
 *
 * 权限拦截器
 */
public class AuthenticationInterceptor extends CoreHandlerInterceptor {

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        // TODO Auto-generated method stub
        // 权限验证
        // Object controller=((HandlerMethod)handler).getBean();
        // if (controller instanceof Identifiable) {
        // System.out.println("into Identifiable:yes>>>"
        // + ((Identifiable)controller).verificate());
        // return true;
        // } else {
        // System.out.println("into Identifiable:no>>>"
        // + handler.getClass().getName());
        // return true;
        // }
        return true;
    }
}
