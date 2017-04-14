package com.mg.csbackstage.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;

/**
 * Created by BennieSun on 2017/2/17.
 *
 *
 */
public class DecodeRequestUtil {

    private static Logger logger = Logger.getLogger(DecodeRequestUtil.class);

    /**
     * 发送游戏币返回结果处理
     *
     * @param objectJson
     * @return true（发送游戏币成功），false（发送游戏币失败）
     */
    public static final boolean sendStoneResult(String objectJson) {
        try {
            JSONObject json = JSONObject.parseObject(objectJson);
            logger.info("exchange工程返回值，数据为:" + objectJson);
            if ("1000".equalsIgnoreCase(json.getString("code"))) {
                return true;
            }
        } catch (Exception e) {
            logger.info("exchange工程返回值数据RuntimeException:"+objectJson);
            logger.info(e.getMessage());
        }
        return false;
    }

    /**
     * game工程
     *
     * @param objectJson
     * @return
     */
    public static final JSONObject gamesResult(String objectJson) {
        try {
            JSONObject json = JSONObject.parseObject(objectJson);
            logger.info("game工程返回值,数据为:"+objectJson);
            if ("1000".equalsIgnoreCase(json.getString("code"))) {
                if (json.containsKey("games")) {
                    return json.getJSONObject("games");
                }

                if (json.containsKey("gamesServer")) {
                    return json.getJSONObject("gamesServer");
                }

                if (json.containsKey("gamesCommodityItem")) {
                    return json.getJSONObject("gamesCommodityItem");
                }

                if (json.containsKey("users")) {
                    return json.getJSONObject("users");
                }
            }
        } catch (Exception e) {
            logger.info("game工程返回值数据RuntimeException:"+objectJson);
            logger.info(e.getMessage());
        }
        return null;
    }

    /**
     * login工程
     *
     * @param objectJson
     * @return
     */
    public static final JSONObject loginResult(String objectJson) {
        try {
            JSONObject json = JSONObject.parseObject(objectJson);
            logger.info("login工程返回值,数据为:"+objectJson);
            if ("1010".equalsIgnoreCase(json.getString("code"))) {
                if (json.containsKey("users")) {
                    return json.getJSONObject("users");
                }
            }
        } catch (Exception e) {
            logger.info("login工程返回值数据RuntimeException:"+objectJson);
            logger.info(e.getMessage());
        }
        return null;
    }
}
