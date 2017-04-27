package com.mg.upload.core;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpServlet;

/**
 * Created by BennieSun on 2017/2/9.
 */
public class InitPoolManager extends HttpServlet {

    private static final Logger log = Logger.getLogger(InitPoolManager.class);
    private static final long serialVersionUID = -1123651676714378267L;


    /**
     * 初始化
     */
    public void init() {
        //RedisPoolManager.getInstance().init();
        //JdbcPoolManager.getInstance().init();
    }

    /**
     * 销毁
     */
    public void destroy() {
        //RedisPoolManager.getInstance().destroy();
    }
}
