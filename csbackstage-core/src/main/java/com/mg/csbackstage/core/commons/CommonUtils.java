package com.mg.csbackstage.core.commons;

import com.mg.redis.core.RedisUtil;
import com.mg.util.core.GlobalHelper;
import com.mg.util.core.message.ServerConfig;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by BennieSun on 2017/2/10.
 */
public class CommonUtils {

    public static boolean getOpenDebug() {
        String openDebug = ServerConfig.getInstance().getConfig("openDebug");
        return (null!=openDebug && openDebug.equals("true")) ? true : false;
    }

    public static boolean getCloseSigin() {
        String closeSign = ServerConfig.getInstance().getConfig("closeSign");
        return (null!=closeSign && closeSign.equals("true")) ? true : false;
    }

    public static boolean getOpenIpWhite() {
        String openIpWhite = ServerConfig.getInstance().getConfig("openIpWhite");
        return (null!=openIpWhite && openIpWhite.equals("true")) ? true : false;
    }

    public static String getGameVerificationKey() {
        return ServerConfig.getInstance().getConfig("gameVerificationKey");
    }

    public static String getGameUrl() {
        return ServerConfig.getInstance().getConfig("gameUrl");
    }

    public static String getLoginVerificationKey() {
        return ServerConfig.getInstance().getConfig("loginVerificationKey");
    }

    public static String getLoginUrl() {
        return ServerConfig.getInstance().getConfig("loginUrl");
    }

    /**
     * 获取accessToken
     * @param gameCode
     * @return
     */
    public static String getAccessToken(String gameCode,String userId,long timestamp){
        return GlobalHelper.MD5Helper.getMD5String(userId+gameCode+timestamp).toLowerCase();
    }

    /**
     * 获取IP地址
     * @param request
     * @return
     */
    public static String getIpAddr(HttpServletRequest request) {
        return GlobalHelper.IpUtil.getIpAddrByRequest(request);
    }

    /**
     * key值在某个second时间内允许出现num次
     *
     * @param key
     * @param second
     * @param num
     * @return
     */
    public final static boolean checkRechargeRequest(String key, int second, int num) {
        int incr = RedisUtil.incr(key).intValue();
        int ttl = RedisUtil.ttl(key).intValue();
        RedisUtil.expire(key, incr > 1 ? (ttl > 0 ? ttl : second) : second);
        return incr > num;
    }

    public static String generateOrderId(String gameCode, String userId) {
        return gameCode.toUpperCase() + SequenceUtil.loadRandomString(20 - gameCode.length());
    }
}
