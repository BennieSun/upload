package com.mg.csbackstage.core;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * Created by BennieSun on 2017/1/22.
 */
public class CoreHandlerInterceptor extends HandlerInterceptorAdapter implements HandlerInterceptor {
    protected String[] allowUrls;

    public CoreHandlerInterceptor() {
    }

    public void setAllowUrls(String[] allowUrls) {
        this.allowUrls = allowUrls;
    }
}
