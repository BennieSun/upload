package com.mg.csbackstage.controller;

import com.alibaba.fastjson.JSONObject;
import com.mg.csbackstage.bean.CsAskQuestionsBean;
import com.mg.csbackstage.bean.CsChatBean;
import com.mg.csbackstage.constants.ResponseCodeConst;
import com.mg.csbackstage.constants.ResponseMsgConst;
import com.mg.csbackstage.core.commons.CommonUtils;
import com.mg.csbackstage.core.utils.CsEnumUtils;
import com.mg.csbackstage.manager.CsPlayerManager;
import com.mg.csbackstage.manager.RequestApplicationManager;
import com.mg.csbackstage.pojo.CsChatDetailPojo;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by BennieSun on 2017/4/13.
 */
@Controller
@RequestMapping(method = { RequestMethod.GET, RequestMethod.POST }, value = { "/*", "/csChat/*" })
public class CsChatController {

    private static Logger logger = Logger.getLogger(CsChatController.class);

    @Autowired
    CsPlayerManager playerManager;

    @Autowired
    RequestApplicationManager requestApplicationManager;

    @RequestMapping(value = {"/csChat_message", "/csChat_message.web"}, method = {RequestMethod.GET, RequestMethod.POST})
    //@ResponseBody
    public ModelAndView message(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception{
        ParamLogMessage plm = GlobalHelper.RequestParameterHelper.loadRequestMessage(httpServletRequest, true, true);
        logger.info(plm.print());
        //JSONObject jsonObject = new JSONObject();
        ModelAndView mav = new ModelAndView("comm/chatMessage");

        Map<String,String> initMap = plm.getParamsMap();

        String gameLanguage = initMap.get("gameLanguage");//提示语言信息
        String aqId = initMap.get("aqId");//问题唯一id
        String userId = initMap.get("userId");//玩家userId
        String gameCode = initMap.get("gameCode");//游戏标识

        String senderType = initMap.get("senderType");//玩家or客服

        String langName = ServerConfig.getInstance().getLang();
        if (StringUtils.isNotEmpty(gameLanguage)) {
            langName = gameLanguage;
        }

        if (StringUtils.isEmpty(userId) || StringUtils.isEmpty(aqId) || StringUtils.isEmpty(gameCode)){
            logger.info("params is exception：userId="+userId+",aqId="+aqId+",gameCode:"+gameCode);
            //jsonObject.put("code", ResponseCodeConst.PARAMS_EXCEPTION);
            //jsonObject.put("message", ResponseMsgConst.getInstance(langName).getResponseMsg(ResponseCodeConst.PARAMS_EXCEPTION));
            //return jsonObject.toJSONString();
            return null;
        }

        CsAskQuestionsBean csAskQuestionsBean = playerManager.getQuestions(Long.valueOf(userId),gameCode);

        if (null == csAskQuestionsBean){
            logger.info("csAskQuestionsBean is null:userId="+userId);
            return null;
        }

        if (Long.valueOf(aqId) != csAskQuestionsBean.getId()){
            logger.info("userId:"+userId+" does not exist this aqId, aqId="+aqId);
            //jsonObject.put("code", ResponseCodeConst.PARAMS_EXCEPTION);
            //jsonObject.put("message", ResponseMsgConst.getInstance(langName).getResponseMsg(ResponseCodeConst.PARAMS_EXCEPTION));
            //return jsonObject.toJSONString();
            return null;
        }

        List<CsChatDetailPojo> CsChatDetailPojoList = new ArrayList<CsChatDetailPojo>();
        List<CsChatBean> csChatBeanList = playerManager.getChatList(aqId);
        if (null == csChatBeanList){
            logger.info("csChatBeanList is null:aqId"+aqId);
            return null;
        }else{
            for (CsChatBean chatBean:csChatBeanList) {
                CsChatDetailPojo csChatDetailPojo = new CsChatDetailPojo();
                Map<String,Object> tempMap = GlobalHelper.ToEntityHelper.transEntity2Map(chatBean);
                GlobalHelper.ToEntityHelper.copyMap2Object(tempMap, csChatDetailPojo);

                if (chatBean.getSenderType()== CsEnumUtils.SenderType.player.getStatusNum()){
                    csChatDetailPojo.setSenderName(csAskQuestionsBean.getRoleName());
                }else if(chatBean.getSenderType()== CsEnumUtils.SenderType.cs.getStatusNum()){
                    csChatDetailPojo.setSenderName(playerManager.getAccount(Long.valueOf(userId)).getForeignName());
                }
                CsChatDetailPojoList.add(csChatDetailPojo);
            }

        }

        //用户free marker样式调整
        if (StringUtils.isEmpty(senderType) || CsEnumUtils.SenderType.player.toString().equals(senderType)){
            senderType = CsEnumUtils.SenderType.player.toString();
        }else if(CsEnumUtils.SenderType.cs.toString().equals(senderType)){
            senderType = CsEnumUtils.SenderType.cs.toString();
        }

        mav.addObject("senderType",senderType);
        mav.addObject("csChatDetailList", CsChatDetailPojoList);
        return mav;
    }

    @RequestMapping(value = {"/csChat_end", "/csChat_end.web"}, method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String chatEnd(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception{
        ParamLogMessage plm = GlobalHelper.RequestParameterHelper.loadRequestMessage(httpServletRequest, true, true);
        logger.info(plm.print());
        JSONObject jsonObject = new JSONObject();

        String ip = CommonUtils.getIpAddr(httpServletRequest);

        Map<String,String> initMap = plm.getParamsMap();
        String gameLanguage = initMap.get("gameLanguage");//提示语言信息
        String aqId = initMap.get("aqId");//问题唯一id
        String userId = initMap.get("userId");//玩家userId
        String gameCode = initMap.get("gameCode");//游戏标识

        String langName = ServerConfig.getInstance().getLang();
        if (StringUtils.isNotEmpty(gameLanguage)) {
            langName = gameLanguage;
        }

        if (StringUtils.isEmpty(userId) || StringUtils.isEmpty(aqId) || StringUtils.isEmpty(gameCode)){
            logger.info("params is exception：userId="+userId+",aqId="+aqId+",gameCode:"+gameCode);
            jsonObject.put("code", ResponseCodeConst.PARAMS_EXCEPTION);
            jsonObject.put("message", ResponseMsgConst.getInstance(langName).getResponseMsg(ResponseCodeConst.PARAMS_EXCEPTION));
            return jsonObject.toJSONString();
        }

        CsAskQuestionsBean csAskQuestionsBean = playerManager.getQuestions(Long.valueOf(aqId));
        if (null == csAskQuestionsBean || csAskQuestionsBean.getUserId().longValue() != Long.valueOf(userId).longValue()
                || !gameCode.equals(csAskQuestionsBean.getGameCode())){
            logger.info("aqId："+aqId+",does not exist params userId="+userId
                    +",Check："+(csAskQuestionsBean.getUserId().longValue() != Long.valueOf(userId).longValue())
                    +",gameCode:"+gameCode+"，Check："+!gameCode.equals(csAskQuestionsBean.getGameCode()));
            jsonObject.put("code", ResponseCodeConst.DATA_CHECK_EXCEPTION);
            jsonObject.put("message", ResponseMsgConst.getInstance(langName).getResponseMsg(ResponseCodeConst.DATA_CHECK_EXCEPTION));
            return jsonObject.toJSONString();
        }

        int returnCount = playerManager.updateChatEnd(Long.valueOf(aqId), ip, GlobalHelper.currentTimestamp());
        if (returnCount > 0){
            jsonObject.put("code", ResponseCodeConst.IS_SUCCESS);
            jsonObject.put("message", ResponseMsgConst.getInstance(langName).getResponseMsg(ResponseCodeConst.IS_SUCCESS));
            return jsonObject.toJSONString();
        }

        jsonObject.put("code", ResponseCodeConst.SYSTEM_EXCEPTION);
        jsonObject.put("message", ResponseMsgConst.getInstance(langName).getResponseMsg(ResponseCodeConst.SYSTEM_EXCEPTION));
        return jsonObject.toJSONString();
    }

}
