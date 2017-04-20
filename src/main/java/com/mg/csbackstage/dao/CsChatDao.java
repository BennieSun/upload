package com.mg.csbackstage.dao;

import com.mg.csbackstage.bean.CsChatBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by BennieSun on 2017/4/12.
 *
 * 客服聊天
 */
@Repository
public class CsChatDao {

    @Autowired
    CsChatBean chatBean;

    //CsChatBean csChatBean = ManagerFactory.getInstance("CsChatBean",CsChatBean.class);

    public int insert(CsChatBean csChatBean) {
        return csChatBean.insert();
    }

    public CsChatBean findLastChat(Long askQuestionsId) {
        return chatBean.SPRead().fetchOne(
                "select * from t_cs_chat where id=(select max(id) from t_cs_chat where aqid=?)",askQuestionsId);
    }

    public CsChatBean findFirstChat(Long askQuestionsId) {
        return chatBean.SPRead().fetchOne(
                "select * from t_cs_chat where id=(select min(id) from t_cs_chat where aqid=?)",askQuestionsId);
    }

    public List<CsChatBean> findChatList(String aqId) {
        return chatBean.SPRead().fetchAll("select * from t_cs_chat where aqid=? order by createdTime asc", aqId);
    }
}
