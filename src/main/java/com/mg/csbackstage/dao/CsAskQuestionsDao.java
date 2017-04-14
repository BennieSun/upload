package com.mg.csbackstage.dao;

import com.mg.csbackstage.bean.CsAskQuestionsBean;
import com.mg.csbackstage.factorys.ManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by BennieSun on 2017/4/11.
 *
 * 客服问题列表
 */
@Repository
public class CsAskQuestionsDao {

    @Autowired
    CsAskQuestionsBean askQuestionsBean;

    public int insert(CsAskQuestionsBean askQuestionsBean) {
        return askQuestionsBean.insert();
    }

    public int findAQCountByUserId(String userId) {
        return askQuestionsBean.SPRead().fetchCount(
                "select count(*) from t_cs_ask_questions where userId=? and flag=0",userId);
    }

    public CsAskQuestionsBean findAQByUserId(Long userId, String gameCode, int flag) {
        return askQuestionsBean.SPRead().fetchOne(
                "select * from t_cs_ask_questions where userId=? and gameCode=? and flag=?",userId,gameCode,flag);
    }

    public List<CsAskQuestionsBean> findAllAQByGameCodes(int flag) {
        return askQuestionsBean.SPRead().fetchAll(
                "select * from t_cs_ask_questions where flag=? order by modifiedTime Desc",flag);
    }

    public List<CsAskQuestionsBean> findAllAQByGameCodes(int flag, String... gameCodes) {
        StringBuilder sb = new StringBuilder("select * from t_cs_ask_questions where flag=");
        sb.append(flag);
        sb.append(" and gameCode in(");
        String common = "";
        for(String gameCode : gameCodes){
            sb.append(common);
            sb.append(gameCode);
            common = ",";
        }
        sb.substring(0,sb.lastIndexOf(","));
        sb.append(")");
        sb.append(" order by modifiedTime Desc");
        return askQuestionsBean.SPRead().fetchAll(sb.toString());
    }


    public CsAskQuestionsBean findAQAsFac(Long userId) {
        CsAskQuestionsBean csAskQuestionsBean = ManagerFactory.getInstance(
                "CsAskQuestionsBean",CsAskQuestionsBean.class);
        return csAskQuestionsBean.SPRead().fetchOne(
                "select * from t_cs_ask_questions where userId=? and flag=0",userId);
    }

    public CsAskQuestionsBean findAQById(Long aqId) {
        return askQuestionsBean.SPRead().fetchOne(
                "select * from t_cs_ask_questions where id=?",aqId);
    }
}
