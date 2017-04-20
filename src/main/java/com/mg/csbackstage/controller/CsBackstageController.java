package com.mg.csbackstage.controller;

import com.alibaba.fastjson.JSONObject;
import com.mg.csbackstage.bean.CsAskQuestionsBean;
import com.mg.csbackstage.bean.CsChatBean;
import com.mg.csbackstage.constants.ResponseCodeConst;
import com.mg.csbackstage.constants.ResponseMsgConst;
import com.mg.csbackstage.core.commons.CommonUtils;
import com.mg.csbackstage.core.utils.CsEnumUtils;
import com.mg.csbackstage.manager.CsBackstageManager;
import com.mg.csbackstage.manager.CsPlayerManager;
import com.mg.csbackstage.pojo.CsBackstageAQChatDetailPojo;
import com.mg.csbackstage.pojo.CsBackstageAQDetailPojo;
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
 * Created by BennieSun on 2017/4/12.
 *
 * 后台
 */
@Controller
@RequestMapping(method = { RequestMethod.GET, RequestMethod.POST }, value = { "/*", "/csBackstage/*" })
public class CsBackstageController {

    private static Logger logger = Logger.getLogger(CsPlayerController.class);

    @Autowired
    CsBackstageManager csBackstageManager;

    @Autowired
    CsPlayerManager csPlayerManager;


    @RequestMapping(value = {"/csBackstage_login", "/csBackstage_login.web"}, method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String login(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {

        return "";
    }

    @RequestMapping(value = {"/csBackstage_entrance", "/csBackstage_entrance.web"}, method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView entrance(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        ParamLogMessage plm = GlobalHelper.RequestParameterHelper.loadRequestMessage(httpServletRequest, true, true);
        logger.info(plm.print());
        Map<String,String> initMap = plm.getParamsMap();

        String ip = CommonUtils.getIpAddr(httpServletRequest);

        String accountId = initMap.get("accountId");//系统账号id
        String gameLanguage = initMap.get("gameLanguage");//提示语言信息

        String langName = ServerConfig.getInstance().getLang();
        if (StringUtils.isNotEmpty(gameLanguage)) {
            langName = gameLanguage;
        }

        JSONObject jsonObject = new JSONObject();
        if (StringUtils.isEmpty(accountId)){
            logger.info("params is exception：accountId="+accountId);
            jsonObject.put("code", ResponseCodeConst.PARAMS_EXCEPTION);
            jsonObject.put("message", ResponseMsgConst.getInstance(langName).getResponseMsg(ResponseCodeConst.PARAMS_EXCEPTION));
            return null;
        }

        String gameCodeOrCsAdmin = csBackstageManager.getGameCodes(Long.valueOf(accountId));

        if (null == gameCodeOrCsAdmin){
            logger.info("gameCode is null：userId="+accountId);
            return null;
        }

        List<CsAskQuestionsBean> csAskQuestionsList = null;
        if (CsEnumUtils.Jurisdiction.csAdmin.toString().equals(gameCodeOrCsAdmin)){
            csAskQuestionsList = csBackstageManager.getAllAQAsProcessing();
        }else{
            String[] gameCodes = gameCodeOrCsAdmin.split(",");
            if (gameCodes.length > 0) {
                csAskQuestionsList = csBackstageManager.getAllAQAsProcessing(gameCodes);
            }else{
                logger.info("gameCode is null and Jurisdiction is not csAdmin：accountId="+accountId);
                return null;
            }
        }

        //数据拼装
        List<CsBackstageAQDetailPojo> csAQDetailPojoList = new ArrayList<CsBackstageAQDetailPojo>();
        if (null != csAskQuestionsList){
            for (CsAskQuestionsBean csAskQuestionsBean:csAskQuestionsList) {
                CsBackstageAQDetailPojo csAskQuestionsDetailPojo = new CsBackstageAQDetailPojo();
                //对象复制
                Map<String, Object> tempMap = GlobalHelper.ToEntityHelper.transEntity2Map(csAskQuestionsBean);
                GlobalHelper.ToEntityHelper.copyMap2Object(tempMap, csAskQuestionsDetailPojo);
                //对象赋值
                CsChatBean csChatBean = csBackstageManager.getLastChat(csAskQuestionsBean.getId());
                if (null != csChatBean){
                    csAskQuestionsDetailPojo.setImgUrl(csChatBean.getImgUrl());
                    csAskQuestionsDetailPojo.setMessage(csChatBean.getMessage());
                    if (csChatBean.getSenderType() == CsEnumUtils.SenderType.player.getStatusNum()){
                        csAskQuestionsDetailPojo.setSenderId(csAskQuestionsBean.getRoleName());
                    }else if(csChatBean.getSenderType() == CsEnumUtils.SenderType.cs.getStatusNum()){
                        csAskQuestionsDetailPojo.setSenderId(
                                csBackstageManager.getStartpyAccount(csChatBean.getSenderId()).getApplicant());
                    }else{
                        csAskQuestionsDetailPojo.setSenderId("未识别");
                    }
                }
                csAQDetailPojoList.add(csAskQuestionsDetailPojo);
            }
        }

        ModelAndView mav = new ModelAndView("web/csBackstageMain");
        mav.addObject("csAskQuestionsList",csAQDetailPojoList);
        mav.addObject("accountId",accountId);
        mav.addObject("gameLanguage",langName);
        return mav;
    }

    @RequestMapping(value = {"/csBackstage_endDetail", "/csBackstage_endDetail.web"}, method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView endDetail(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        ParamLogMessage plm = GlobalHelper.RequestParameterHelper.loadRequestMessage(httpServletRequest, true, true);
        logger.info(plm.print());
        Map<String,String> initMap = plm.getParamsMap();

        String ip = CommonUtils.getIpAddr(httpServletRequest);

        String accountId = initMap.get("accountId");//系统账号id
        String gameLanguage = initMap.get("gameLanguage");//提示语言信息

        String langName = ServerConfig.getInstance().getLang();
        if (StringUtils.isNotEmpty(gameLanguage)) {
            langName = gameLanguage;
        }

        JSONObject jsonObject = new JSONObject();
        if (StringUtils.isEmpty(accountId)){
            logger.info("params is exception：accountId="+accountId);
            jsonObject.put("code", ResponseCodeConst.PARAMS_EXCEPTION);
            jsonObject.put("message", ResponseMsgConst.getInstance(langName).getResponseMsg(ResponseCodeConst.PARAMS_EXCEPTION));
            return null;
        }

        String gameCodeOrCsAdmin = csBackstageManager.getGameCodes(Long.valueOf(accountId));

        if (null == gameCodeOrCsAdmin){
            logger.info("gameCode is null：userId="+accountId);
            return null;
        }

        List<CsAskQuestionsBean> csAskQuestionsList = null;
        if (CsEnumUtils.Jurisdiction.csAdmin.toString().equals(gameCodeOrCsAdmin)){
            csAskQuestionsList = csBackstageManager.getAllAQAsEnd();
        }else{
            String[] gameCodes = gameCodeOrCsAdmin.split(",");
            if (gameCodes.length > 0) {
                csAskQuestionsList = csBackstageManager.getAllAQAsProcessing(gameCodes);
            }else{
                logger.info("gameCode is null and Jurisdiction is not csAdmin：accountId="+accountId);
                return null;
            }
        }

        //数据拼装
        List<CsBackstageAQDetailPojo> csAQDetailPojoList = new ArrayList<CsBackstageAQDetailPojo>();
        if (null != csAskQuestionsList){
            for (CsAskQuestionsBean csAskQuestionsBean:csAskQuestionsList) {
                CsBackstageAQDetailPojo csAskQuestionsDetailPojo = new CsBackstageAQDetailPojo();
                //对象复制
                Map<String, Object> tempMap = GlobalHelper.ToEntityHelper.transEntity2Map(csAskQuestionsBean);
                GlobalHelper.ToEntityHelper.copyMap2Object(tempMap, csAskQuestionsDetailPojo);
                //对象赋值
                CsChatBean csChatBean = csBackstageManager.getLastChat(csAskQuestionsBean.getId());
                if (null != csChatBean){
                    csAskQuestionsDetailPojo.setImgUrl(csChatBean.getImgUrl());
                    csAskQuestionsDetailPojo.setMessage(csChatBean.getMessage());
                    if (csChatBean.getSenderType() == CsEnumUtils.SenderType.player.getStatusNum()){
                        csAskQuestionsDetailPojo.setSenderId(csAskQuestionsBean.getRoleName());
                    }else if(csChatBean.getSenderType() == CsEnumUtils.SenderType.cs.getStatusNum()){
                        csAskQuestionsDetailPojo.setSenderId(
                                csBackstageManager.getStartpyAccount(csChatBean.getSenderId()).getApplicant());
                    }else{
                        csAskQuestionsDetailPojo.setSenderId("未识别");
                    }
                }
                csAQDetailPojoList.add(csAskQuestionsDetailPojo);
            }
        }

        ModelAndView mav = new ModelAndView("web/csBackstageEndDetailMain");
        mav.addObject("csAskQuestionsList",csAQDetailPojoList);
        mav.addObject("accountId",accountId);
        mav.addObject("gameLanguage",langName);
        return mav;
    }

    @RequestMapping(value = {"/csBackstage_detailChat", "/csBackstage_detailChat.web"}, method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView detailChat(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        ParamLogMessage plm = GlobalHelper.RequestParameterHelper.loadRequestMessage(httpServletRequest, true, true);
        logger.info(plm.print());
        Map<String,String> initMap = plm.getParamsMap();

        String ip = CommonUtils.getIpAddr(httpServletRequest);

        String starpyAccountId = initMap.get("userId");//玩家Id
        String aqId = initMap.get("aqId");//问题Id
        String gameLanguage = initMap.get("gameLanguage");//提示语言信息

        String langName = ServerConfig.getInstance().getLang();
        if (StringUtils.isNotEmpty(gameLanguage)) {
            langName = gameLanguage;
        }

        if (StringUtils.isEmpty(aqId) || Long.valueOf(aqId)<=0){
            logger.info("params is null,aqId:"+aqId+",");
            return null;
        }

        CsBackstageAQChatDetailPojo csBsAQChatDetailPojo = new CsBackstageAQChatDetailPojo();

        CsAskQuestionsBean csBSAQChatDetail = csBackstageManager.getAQById(Long.valueOf(aqId));
        Map<String, Object> tempMap = GlobalHelper.ToEntityHelper.transEntity2Map(csBSAQChatDetail);
        GlobalHelper.ToEntityHelper.copyMap2Object(tempMap,csBsAQChatDetailPojo);


        ModelAndView mav = new ModelAndView("web/csBackstageDetailChat");
        mav.addObject("csBsAQChatDetail",GlobalHelper.ToEntityHelper.transEntity2Map(csBsAQChatDetailPojo));
        return mav;
    }

}
