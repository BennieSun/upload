package com.mg.csbackstage.manager;

import com.mg.csbackstage.bean.CsAskQuestionsBean;
import com.mg.csbackstage.bean.CsChatBean;
import com.mg.csbackstage.bean.CsJurisdictionGamesBean;
import com.mg.csbackstage.bean.StarpyAccountBean;
import com.mg.csbackstage.controller.CsPlayerController;
import com.mg.csbackstage.core.utils.CsEnumUtils;
import com.mg.csbackstage.service.CsAskQuestionsService;
import com.mg.csbackstage.service.CsChatService;
import com.mg.csbackstage.service.CsJurisdictionGamesService;
import com.mg.csbackstage.service.StarpyAccountservice;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by BennieSun on 2017/4/12.
 *
 * 后台
 */
@Component
public class CsBackstageManager {

    @Autowired
    CsAskQuestionsService csAskQuestionsService;

    @Autowired
    StarpyAccountservice starpyAccountservice;

    @Autowired
    CsJurisdictionGamesService csJurisdictionGamesService;

    @Autowired
    CsChatService csChatService;

    private static Logger logger = Logger.getLogger(CsPlayerController.class);

    public String getGameCodes(Long userId) {
        StarpyAccountBean starpyAccountBean = starpyAccountservice.findAccount(userId);
        if (CsEnumUtils.Jurisdiction.csAdmin.getStatusNum() == starpyAccountBean.getJurisdiction()){//管理员
            return CsEnumUtils.Jurisdiction.csAdmin.toString();
        }

        if (CsEnumUtils.Jurisdiction.ordinary.getStatusNum() == starpyAccountBean.getJurisdiction()){//普通客服
            List<CsJurisdictionGamesBean> csJurisdictionGamesList = csJurisdictionGamesService.findJurisdictionGames(userId);
            StringBuilder gameCodes = new StringBuilder("");
            for (CsJurisdictionGamesBean jurisdictionGame:csJurisdictionGamesList) {
                gameCodes.append(jurisdictionGame.getGameCode());
                gameCodes.append(",");
            }
            gameCodes.substring(0,gameCodes.lastIndexOf(","));
            return gameCodes.toString();
        }
        logger.info("userId:"+userId+",Jurisdiction:"+starpyAccountBean.getJurisdiction()+",applicant:"+starpyAccountBean.getApplicant());
        return null;
    }

    /**
     * 进行中的问题
     * @param gameCode
     * @return
     */
    public List<CsAskQuestionsBean> getAllAQAsProcessing(String... gameCode) {
        if (null == gameCode || gameCode.length<=0){
            return csAskQuestionsService.findAllAQByGameCodes(CsEnumUtils.AskQuestionsFlag.processing.getStatusNum());
        }else {
            return csAskQuestionsService.findAllAQByGameCodes(CsEnumUtils.AskQuestionsFlag.processing.getStatusNum(),
                    gameCode);
        }
    }

    /**
     * 已结束的问题
     * @param gameCode
     * @return
     */
    public List<CsAskQuestionsBean> getAllAQAsEnd(String... gameCode) {
        if (null == gameCode || gameCode.length<=0){
            return csAskQuestionsService.findAllAQByGameCodes(CsEnumUtils.AskQuestionsFlag.end.getStatusNum());
        }else {
            return csAskQuestionsService.findAllAQByGameCodes(CsEnumUtils.AskQuestionsFlag.end.getStatusNum(),
                    gameCode);
        }
    }

    /**
     * 获取提问数据，根据问题唯一id
     * @param aqId
     * @return
     */
    public CsAskQuestionsBean getAQById(Long aqId){
        return csAskQuestionsService.findAQById(aqId);
    }


    public CsChatBean getLastChat(Long askQuestionsId) {
        return csChatService.findLastChat(askQuestionsId);
    }

    public CsChatBean getFirstChat(Long askQuestionsId) {
        return csChatService.findFirstChat(askQuestionsId);
    }

    public StarpyAccountBean getStartpyAccount(Long userId) {
        return starpyAccountservice.findAccount(userId);
    }
}
