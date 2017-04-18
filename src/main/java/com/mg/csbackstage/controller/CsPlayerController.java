package com.mg.csbackstage.controller;

import com.alibaba.fastjson.JSONObject;
import com.mg.csbackstage.bean.CsAskQuestionsBean;
import com.mg.csbackstage.constants.ResponseCodeConst;
import com.mg.csbackstage.constants.ResponseMsgConst;
import com.mg.csbackstage.core.commons.CommonUtils;
import com.mg.csbackstage.manager.CsPlayerManager;
import com.mg.csbackstage.manager.RequestApplicationManager;
import com.mg.csbackstage.pojo.GamesPojo;
import com.mg.csbackstage.pojo.GamesServerPojo;
import com.mg.csbackstage.pojo.UsersPojo;
import com.mg.util.core.GlobalHelper;
import com.mg.util.core.message.ParamLogMessage;
import com.mg.util.core.message.ServerConfig;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by BennieSun on 2017/4/8.
 *
 * 玩家
 */
@Controller
@RequestMapping(method = { RequestMethod.GET, RequestMethod.POST }, value = { "/*", "/csPlayer/*" })
public class CsPlayerController {

    private static Logger logger = Logger.getLogger(CsPlayerController.class);

    @Autowired
    CsPlayerManager playerManager;

    @Autowired
    RequestApplicationManager requestApplicationManager;

    /**
     * 玩家访问入口
     * @param httpServletRequest
     * @param httpServletResponse
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/player_entrance", "/cs_entrance.app"}, method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView entrance(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        ParamLogMessage plm = GlobalHelper.RequestParameterHelper.loadRequestMessage(httpServletRequest, true, true);
        logger.info(plm.print());
        Map<String,String> initMap = plm.getParamsMap();

        String ip = CommonUtils.getIpAddr(httpServletRequest);
        //initMap.put("ip", ip);
        String gameCode = initMap.get("gameCode");//游戏标识
        String packageName = initMap.get("packageName");//游戏包名称
        String serverCode = initMap.get("serverCode");//游戏服务器标识
        String userId = initMap.get("userId");//用户唯一id，关联t_users表的userId
        String uniqueId = initMap.get("uniqueId");//唯一标识Id（iOS&android同用）
        String mac = initMap.get("mac");//用户mac地址
        String imei = initMap.get("imei");//用户imei地址
        String androidId = initMap.get("androidId");//用户android地址
        String adid = initMap.get("adid");//设备广告ID
        String idfa = initMap.get("idfa");//IOS:idfa
        String uuid = initMap.get("uuid");//IOS:uuid
        String roleName = initMap.get("roleName");//角色名
        String roleLevel = initMap.get("roleLevel");//角色等级
        String roleId = initMap.get("roleId");//角色Id
        String operatingSystem = initMap.get("operatingSystem");//'用户操作系统 android、ios、winphine'
        String systemVersion = initMap.get("systemVersion");//'手机系统版本'
        String deviceType = initMap.get("deviceType");//'设备类型'
        String gameLanguage = initMap.get("gameLanguage");//提示语言信息
        String accessToken = initMap.get("accessToken");
        String loginTimestamp = initMap.get("loginTimestamp");

        String langName = ServerConfig.getInstance().getLang();
        if (StringUtils.isNotEmpty(gameLanguage)) {
            langName = gameLanguage;
        }

        JSONObject jsonObject = new JSONObject();
        if (StringUtils.isEmpty(gameCode) || StringUtils.isEmpty(packageName) || StringUtils.isEmpty(serverCode)
                || StringUtils.isEmpty(userId) || StringUtils.isEmpty(accessToken)
                || StringUtils.isEmpty(loginTimestamp)){
            logger.info("params is exception：gameCode="+gameCode+",packageName="+packageName+",userId="+userId
            +",accessToken="+accessToken+",loginTimestamp="+loginTimestamp);
            jsonObject.put("code", ResponseCodeConst.PARAMS_EXCEPTION);
            jsonObject.put("message", ResponseMsgConst.getInstance(langName).getResponseMsg(ResponseCodeConst.PARAMS_EXCEPTION));
            return null;
        }

        CsAskQuestionsBean csAskQuestionsBean = playerManager.getQuestions(Long.valueOf(userId), gameCode);
        String aqId = null;
        if (null != csAskQuestionsBean){
            aqId = csAskQuestionsBean.getId()+"";
        }

        ModelAndView mav = new ModelAndView("app/csMain");
        //mav.addObject("paramsMap", paramsMap);
        mav.addObject("ip",ip);
        mav.addObject("gameCode",gameCode);
        mav.addObject("packageName",packageName);
        mav.addObject("serverCode",serverCode);
        mav.addObject("userId",userId);
        mav.addObject("uniqueId",uniqueId);
        mav.addObject("mac",mac);
        mav.addObject("imei",imei);
        mav.addObject("androidId",androidId);
        mav.addObject("adid",adid);
        mav.addObject("idfa",idfa);
        mav.addObject("uuid",uuid);
        mav.addObject("roleName",roleName);
        mav.addObject("roleLevel",roleLevel);
        mav.addObject("roleId",roleId);
        mav.addObject("operatingSystem",operatingSystem);
        mav.addObject("systemVersion",systemVersion);
        mav.addObject("deviceType",deviceType);
        mav.addObject("gameLanguage",langName);
        mav.addObject("accessToken",accessToken);
        mav.addObject("loginTimestamp",loginTimestamp);
        mav.addObject("aqId",aqId);
        return mav;
    }

    /**
     * 提问
     * @param httpServletRequest
     * @param httpServletResponse
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/player_askQuestions", "/cs_askQuestions.web"}, method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String askQuestions(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        ParamLogMessage plm = GlobalHelper.RequestParameterHelper.loadRequestMessage(httpServletRequest, true, true);
        logger.info(plm.print());
        JSONObject jsonObject = new JSONObject();

        Map<String,String> initMap = plm.getParamsMap();
        String ip = initMap.get("ip");
        String gameCode = initMap.get("gameCode");//游戏标识
        String packageName = initMap.get("packageName");//游戏包名称
        String serverCode = initMap.get("serverCode");//游戏服务器标识
        String userId = initMap.get("userId");//用户唯一id，关联t_users表的userId
        String uniqueId = initMap.get("uniqueId");//唯一标识Id（iOS&android同用）
        String mac = initMap.get("mac");//用户mac地址
        String imei = initMap.get("imei");//用户imei地址
        String androidId = initMap.get("androidId");//用户android地址
        String adid = initMap.get("adid");//设备广告ID
        String idfa = initMap.get("idfa");//IOS:idfa
        String uuid = initMap.get("uuid");//IOS:uuid
        String roleName = initMap.get("roleName");//角色名
        String roleLevel = initMap.get("roleLevel");//角色等级
        String roleId = initMap.get("roleId");//角色Id
        String operatingSystem = initMap.get("operatingSystem");//'用户操作系统 android、ios、winphine'
        String systemVersion = initMap.get("systemVersion");//'手机系统版本'
        String deviceType = initMap.get("deviceType");//'设备类型'
        String gameLanguage = initMap.get("gameLanguage");//提示语言信息
        String accessToken = initMap.get("accessToken");
        String loginTimestamp = initMap.get("loginTimestamp");

        String message = initMap.get("message");//'消息'
        String tel = initMap.get("tel");//'电话'
        String imgUrl = initMap.get("imgUrl");//'图片地址'

        String langName = ServerConfig.getInstance().getLang();
        if (StringUtils.isNotEmpty(gameLanguage)) {
            langName = gameLanguage;
        }

        if (StringUtils.isEmpty(gameCode) || StringUtils.isEmpty(packageName) || StringUtils.isEmpty(serverCode)
                || StringUtils.isEmpty(userId) || StringUtils.isEmpty(accessToken)
                || StringUtils.isEmpty(loginTimestamp)){
            logger.info("params is exception：gameCode="+gameCode+",packageName="+packageName+",userId="+userId
                    +",accessToken="+accessToken+",loginTimestamp="+loginTimestamp);
            jsonObject.put("code", ResponseCodeConst.PARAMS_EXCEPTION);
            jsonObject.put("message", ResponseMsgConst.getInstance(langName).getResponseMsg(ResponseCodeConst.PARAMS_EXCEPTION));
            return jsonObject.toJSONString();
        }

        //验证字符长度
        if (GlobalHelper.StringHelper.length(message.replace("</br>"," "))>300){
            logger.info("message is to long");
            jsonObject.put("code", ResponseCodeConst.MSG_TO_LONG);
            jsonObject.put("message", ResponseMsgConst.getInstance(langName).getResponseMsg(ResponseCodeConst.MSG_TO_LONG));
            return jsonObject.toJSONString();
        }

        boolean isExistQuestions = playerManager.isExistQuestions(userId);
        if (isExistQuestions){
            jsonObject.put("code", ResponseCodeConst.REPEAT_ASK_QUESTIONS);
            jsonObject.put("message", ResponseMsgConst.getInstance(langName).getResponseMsg(ResponseCodeConst.REPEAT_ASK_QUESTIONS));
            return jsonObject.toJSONString();
        }

        Map<String,String> gamesMap = new HashMap<String, String>();
        gamesMap.put("gameCode",gameCode);
        if (StringUtils.isEmpty(packageName)){
            packageName = "web";
        }
        gamesMap.put("packageName",packageName);
        GamesPojo gamesPojo = requestApplicationManager.getGames(gamesMap);
        if (null == gamesPojo){
            logger.info("gamesPojo is null,gameCode:"+gameCode+",packageName:"+packageName);
            jsonObject.put("code", ResponseCodeConst.PARAMS_EXCEPTION);
            jsonObject.put("message", ResponseMsgConst.getInstance(langName).getResponseMsg(ResponseCodeConst.PARAMS_EXCEPTION));
            return jsonObject.toJSONString();
        }

        Map<String,String> gamesServerMap = new HashMap<String, String>();
        gamesServerMap.put("serverCode",serverCode);
        gamesServerMap.put("gameCode",gameCode);
        GamesServerPojo gamesServerBean = requestApplicationManager.getGamesServer(gamesServerMap);
        if (null == gamesServerBean){
            logger.info("gamesServerBean is null,gameCode:"+gameCode+",serverCode:"+serverCode);
            jsonObject.put("code", ResponseCodeConst.PARAMS_EXCEPTION);
            jsonObject.put("message", ResponseMsgConst.getInstance(langName).getResponseMsg(ResponseCodeConst.PARAMS_EXCEPTION));
            return jsonObject.toJSONString();
        }

        Map<String,String> userMap = new HashMap<String, String>();
        userMap.put("userId",userId);
        UsersPojo usersPojo = requestApplicationManager.getUsers(userMap);
        if (null == usersPojo) {
            logger.info("usersPojo is null,userId:"+userId);
            jsonObject.put("code", ResponseCodeConst.PARAMS_EXCEPTION);
            jsonObject.put("message", ResponseMsgConst.getInstance(langName).getResponseMsg(ResponseCodeConst.PARAMS_EXCEPTION));
            return jsonObject.toJSONString();
        }

        initMap.put("gameOriginName",gamesPojo.getGameOriginName());
        initMap.put("gameName",gamesPojo.getGameName());
        initMap.put("serverName",gamesServerBean.getServerName());
        initMap.put("registPlatform",usersPojo.getRegistPlatform());

        int returnCount = playerManager.insert(initMap);

        if (returnCount>0){
            jsonObject.put("aqUniqueId",returnCount);
            jsonObject.put("code", ResponseCodeConst.IS_SUCCESS);
            jsonObject.put("message", ResponseMsgConst.getInstance(langName).getResponseMsg(ResponseCodeConst.IS_SUCCESS));
            return jsonObject.toJSONString();
        }

        jsonObject.put("code", ResponseCodeConst.SYSTEM_EXCEPTION);
        jsonObject.put("message", ResponseMsgConst.getInstance(langName).getResponseMsg(ResponseCodeConst.SYSTEM_EXCEPTION));
        return jsonObject.toJSONString();
    }


}
