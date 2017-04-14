package com.mg.csbackstage.service;

import com.mg.csbackstage.bean.CsChatBean;
import com.mg.csbackstage.dao.CsChatDao;
import com.mg.csbackstage.factorys.ManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by BennieSun on 2017/4/12.
 *
 * 客服聊天
 */
@Service
public class CsChatService {

    @Autowired
    CsChatDao chatDao;


    public int insertAsFac(CsChatBean csChatBean) {
        CsChatDao csChatDao = ManagerFactory.getInstance("CsChatDao",CsChatDao.class);
        return csChatDao.insert(csChatBean);
    }

    public CsChatBean findLastChat(Long askQuestionsId) {
        return chatDao.findLastChat(askQuestionsId);
    }

    public List<CsChatBean> findChatList(String aqId) {
        return chatDao.findChatList(aqId);
    }
}
