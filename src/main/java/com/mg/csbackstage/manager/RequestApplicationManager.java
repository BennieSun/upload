package com.mg.csbackstage.manager;

import com.alibaba.fastjson.JSONObject;
import com.mg.csbackstage.constants.ServerConst;
import com.mg.csbackstage.pojo.GamesPojo;
import com.mg.csbackstage.pojo.GamesServerPojo;
import com.mg.csbackstage.pojo.UsersPojo;
import com.mg.csbackstage.service.IpWhiteListService;
import com.mg.csbackstage.utils.DecodeRequestUtil;
import com.mg.util.core.GlobalHelper;
import com.mg.util.core.message.ServerConfig;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by BennieSun on 2017/2/20.
 *
 * 请求其他工程
 */
@Component
public class RequestApplicationManager {

    @Autowired
    IpWhiteListService ipWhiteListService;

    private static Logger logger = Logger.getLogger(RequestApplicationManager.class);

    /**
     * games获取
     *
     * @return
     */
    //@RequestMapping(value = {"/games", "/games.web"}, method = {RequestMethod.GET, RequestMethod.POST})
    //@ResponseBody
    //public String credit(@RequestBody PayPojo payPojo) throws Exception{
    public static GamesPojo getGames(Map<String,String> paramsMap) throws Exception {
        //ParamLogMessage plm = GlobalHelper.RequestParameterHelper.loadRequestMessage(httpServletRequest, true, true);
        //logger.info(plm.print());
        //JSONObject paramsJson = plm.getParamsJsonObj();
        String gameCode = paramsMap.get("gameCode");
        String packageName = paramsMap.get("packageName");
        GamesPojo gamesPojo = new GamesPojo();

        if (StringUtils.isEmpty(gameCode) || StringUtils.isEmpty(packageName)){
            logger.info("gameCode or packageName is null,gameCode="+gameCode+",packageName="+packageName);
            return null;
        }

        String langName = ServerConfig.getInstance().getLang();
        if (StringUtils.isEmpty(langName)){
            logger.info("langName is null");
            return null;
        }

        String gameUrl = ServerConst.GAME_URL;
        if (StringUtils.isEmpty(gameUrl)){
            logger.info("gameUrl is null");
            return null;
        }

        String gameVerificationKey = ServerConst.GAME_VERIFICATION_KEY;
        if (null == gameVerificationKey){
            logger.info("gameVerificationKey is null");
            return null;
        }
        long  timestamp = GlobalHelper.currentMillisecond();

        String signature = GlobalHelper.MD5Helper.getMD5String(gameVerificationKey+gameCode+timestamp);

        StringBuffer params = new StringBuffer();
        params.append(gameUrl);
        params.append("/games?");
        params.append("gameCode="+gameCode);
        params.append("&");
        params.append("packageName="+packageName);
        params.append("&");
        params.append("timestamp="+timestamp);
        params.append("&");
        params.append("gameLanguage="+langName);
        params.append("&");
        params.append("signature="+signature);

        JSONObject jsonObject = DecodeRequestUtil.gamesResult(GlobalHelper.HttpUtil.getUrlBodyAsString(params.toString(),"get",null));
        if (null == jsonObject){
            return null;
        }
        return gamesPojo.getGames(jsonObject);
    }

    /**
     * getGameServer获取
     *
     * @return
     */
    //@RequestMapping(value = {"/gameServer", "/getGameServer.web"}, method = {RequestMethod.GET, RequestMethod.POST})
    //@ResponseBody
    //public String credit(@RequestBody PayPojo payPojo) throws Exception{
    public GamesServerPojo getGamesServer(Map<String,String> paramsMap) throws Exception {
        //ParamLogMessage plm = GlobalHelper.RequestParameterHelper.loadRequestMessage(httpServletRequest, true, true);
        //logger.info(plm.print());
        //JSONObject paramsJson = plm.getParamsJsonObj();

        String gameCode = paramsMap.get("gameCode");
        String serverCode = paramsMap.get("serverCode");

        //JSONObject jsonObject = new JSONObject();
        GamesServerPojo gamesServerPojo = new GamesServerPojo();

        String langName = ServerConfig.getInstance().getLang();
        if (StringUtils.isEmpty(langName)){
            logger.info("langName is null");
            return null;
        }

        String gameUrl = ServerConst.GAME_URL;
        if (StringUtils.isEmpty(gameUrl)){
            logger.info("gameUrl is null");
            return null;
        }

        String gameVerificationKey = ServerConst.GAME_VERIFICATION_KEY;
        if (StringUtils.isEmpty(gameVerificationKey)){
            logger.info("gameVerificationKey is null");
            return null;
        }
        long  timestamp = GlobalHelper.currentMillisecond();

        String signature = GlobalHelper.MD5Helper.getMD5String(gameVerificationKey+gameCode+timestamp);

        StringBuffer params = new StringBuffer();
        params.append(gameUrl);
        params.append("/gamesServer?");
        params.append("gameCode="+gameCode);
        params.append("&");
        params.append("serverCode="+serverCode);
        params.append("&");
        params.append("timestamp="+timestamp);
        params.append("&");
        params.append("gameLanguage="+langName);
        params.append("&");
        params.append("signature="+signature);

        JSONObject jsonObject = DecodeRequestUtil.gamesResult(GlobalHelper.HttpUtil.getUrlBodyAsString(params.toString(),"get",null));
        if (null == jsonObject){
            return null;
        }
        return gamesServerPojo.getGames(jsonObject);
    }

    /**
     * getGameServer获取
     *
     * @return
     */
    //@RequestMapping(value = {"/users", "/getGameServer.web"}, method = {RequestMethod.GET, RequestMethod.POST})
    //@ResponseBody
    //public String credit(@RequestBody PayPojo payPojo) throws Exception{
    public UsersPojo getUsers(Map<String,String> paramsMap) throws Exception {
        //ParamLogMessage plm = GlobalHelper.RequestParameterHelper.loadRequestMessage(httpServletRequest, true, true);
        //logger.info(plm.print());
        //JSONObject paramsJson = plm.getParamsJsonObj();

        String userId = paramsMap.get("userId");

        //JSONObject jsonObject = new JSONObject();
        UsersPojo usersPojo = new UsersPojo();

        String langName = ServerConfig.getInstance().getLang();
        if (StringUtils.isEmpty(langName)){
            logger.info("langName is null");
            return null;
        }

        String loginUrl = ServerConst.LOGIN_URL;
        if (StringUtils.isEmpty(loginUrl)){
            logger.info("loginUrl is null");
            return null;
        }

        String loginVerificationKey = ServerConst.LOGIN_VERIFICATION_KEY;
        if (StringUtils.isEmpty(loginVerificationKey)){
            logger.info("loginVerificationKey is null");
            return null;
        }
        long  timestamp = GlobalHelper.currentMillisecond();

        String signature = GlobalHelper.MD5Helper.getMD5String(loginVerificationKey+userId+timestamp);

        StringBuffer params = new StringBuffer();
        params.append(loginUrl);
        params.append("/users?");
        params.append("userId="+userId);
        params.append("&");
        params.append("timestamp="+timestamp);
        params.append("&");
        params.append("gameLanguage="+langName);
        params.append("&");
        params.append("signature="+signature);

        JSONObject jsonObject = DecodeRequestUtil.loginResult(GlobalHelper.HttpUtil.getUrlBodyAsString(params.toString(),"get",null));
        if (null == jsonObject){
            return null;
        }
        return usersPojo.getUsers(jsonObject);
    }


}
