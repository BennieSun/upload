package com.mg.csbackstage.service;

import com.mg.csbackstage.bean.CsAskQuestionsBean;
import com.mg.csbackstage.dao.CsAskQuestionsDao;
import com.mg.csbackstage.factorys.ManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by BennieSun on 2017/4/11.
 *
 * 客服问题列表
 */
@Service
public class CsAskQuestionsService {

    @Autowired
    CsAskQuestionsDao askQuestionsDao;

    public int insert(CsAskQuestionsBean askQuestionsBean) {
        return askQuestionsDao.insert(askQuestionsBean);
    }

    public int findAQCountByUserId(String userId) {
        return askQuestionsDao.findAQCountByUserId(userId);
    }

    public CsAskQuestionsBean findAQByUserId(Long userId, String gameCode, int flag) {
        return askQuestionsDao.findAQByUserId(userId, gameCode, flag);
    }

    public CsAskQuestionsBean findAQById(Long aqId) {
        return askQuestionsDao.findAQById(aqId);
    }

    /**
     * 根据状态&gameCode获取问题列表
     * @param flag 状态（进行中、结束）
     * @param gameCodes
     * @return
     */
    public List<CsAskQuestionsBean> findAllAQByGameCodes(int flag, String... gameCodes) {
        return askQuestionsDao.findAllAQByGameCodes(flag,gameCodes);
    }

    /**
     * 根据状态&gameCode获取问题列表
     * @param flag 状态（进行中、结束）
     * @return
     */
    public List<CsAskQuestionsBean> findAllAQByGameCodes(int flag) {
        return askQuestionsDao.findAllAQByGameCodes(flag);
    }

    public CsAskQuestionsBean findAQAsFac(Long userId) {
        CsAskQuestionsDao csAskQuestionsDao = ManagerFactory.getInstance("CsAskQuestionsDao",CsAskQuestionsDao.class);
        return csAskQuestionsDao.findAQAsFac(userId);
    }
}
