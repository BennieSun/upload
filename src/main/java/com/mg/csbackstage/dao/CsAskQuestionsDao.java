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

    public int findAQCountByUserId(String userId, String[] gameCodes, int flag) {
        //return askQuestionsBean.SPRead().fetchCount("select count(*) from t_cs_ask_questions where userId=? and gameCode=? and flag=?",userId, gameCode, flag);
        StringBuilder sb = new StringBuilder("select count(*) from t_cs_ask_questions where userId=");
        sb.append(userId);
        sb.append(" and gameCode in('");
        String common = "";
        for(String gameCode : gameCodes){
            sb.append(common);
            sb.append(gameCode);
            common = "','";
        }
        sb.append("')");
        sb.append(" and flag=");
        sb.append(flag);
        return askQuestionsBean.SPRead().fetchCount(sb.toString());
    }

    public CsAskQuestionsBean findAQByUserId(Long userId, String[] gameCodes, int flag) throws Exception{
        //return askQuestionsBean.SPRead().fetchOne("select * from t_cs_ask_questions where userId=? and gameCode=? and flag=?",userId,gameCode,flag);
        StringBuilder sb = new StringBuilder("select * from t_cs_ask_questions where userId=");
        sb.append(userId);
        sb.append(" and gameCode in('");
        String common = "";
        for(String gameCode : gameCodes){
            sb.append(common);
            sb.append(gameCode);
            common = "','";
        }
        sb.append("')");
        sb.append(" and flag=");
        sb.append(flag);
        return askQuestionsBean.SPRead().fetchOne(sb.toString());
    }

    public List<CsAskQuestionsBean> findAllAQByGameCodes(int flag) {
        return askQuestionsBean.SPRead().fetchAll(
                "select * from t_cs_ask_questions where flag=? order by modifiedTime Desc",flag);
    }

    public List<CsAskQuestionsBean> findAllAQByGameCodes(int flag, String... gameCodes) {
        StringBuilder sb = new StringBuilder("select * from t_cs_ask_questions where flag=");
        sb.append(flag);
        sb.append(" and gameCode in('");
        String common = "";
        for(String gameCode : gameCodes){
            sb.append(common);
            sb.append(gameCode);
            common = "','";
        }
        //sb.substring(0,sb.lastIndexOf(","));
        sb.append("')");
        sb.append(" order by modifiedTime Desc");
        return askQuestionsBean.SPRead().fetchAll(sb.toString());
    }

    public CsAskQuestionsBean findAQById(Long aqId) {
        return askQuestionsBean.SPRead().fetchOne(
                "select * from t_cs_ask_questions where id=?",aqId);
    }

    public CsAskQuestionsBean findAQById(Long aqId, int flag) {
        return askQuestionsBean.SPRead().fetchOne(
                "select * from t_cs_ask_questions where id=? and flag=?",aqId, flag);
    }

    public int updateAQById(Long aqId, String[] keys, Object[] values) {
        StringBuilder sb = new StringBuilder("update t_cs_ask_questions set ");
        String common = "";
        for(int i=0;i<keys.length;i++){
            sb.append(common);
            sb.append(keys[i]);
            sb.append("='");
            sb.append(values[i]);
            common = "',";
        }
        sb.append("' where id = ?");
        return askQuestionsBean.SP().executeUpdate(sb.toString(), aqId);
    }

    public CsAskQuestionsBean findAQByUserIdAsFac(Long userId, int flag) {
        CsAskQuestionsBean csAskQuestionsBean = ManagerFactory.getInstance(
                "CsAskQuestionsBean",CsAskQuestionsBean.class);
        return csAskQuestionsBean.SPRead().fetchOne(
                "select * from t_cs_ask_questions where userId=? and flag=?",userId, flag);
    }

    public CsAskQuestionsBean findAQByAqIdAsFac(Long aqId, int flag) {
        CsAskQuestionsBean csAskQuestionsBean = ManagerFactory.getInstance(
                "CsAskQuestionsBean",CsAskQuestionsBean.class);
        return csAskQuestionsBean.SPRead().fetchOne(
                "select * from t_cs_ask_questions where id=? and flag=?",aqId, flag);
    }

    public int updateAQByIdAsFac(Long aqId, String[] keys, Object[] values) {
        CsAskQuestionsBean csAskQuestionsBean = ManagerFactory.getInstance(
                "CsAskQuestionsBean",CsAskQuestionsBean.class);
        StringBuilder sb = new StringBuilder("update t_cs_ask_questions set ");
        String common = "";
        for(int i=0;i<keys.length;i++){
            sb.append(common);
            sb.append(keys[i]);
            sb.append("='");
            sb.append(values[i]);
            common = "',";
        }
        sb.append("' where id = ?");
        return csAskQuestionsBean.SP().executeUpdate(sb.toString(), aqId);
    }
}
