package com.mg.csbackstage.service;

import com.mg.csbackstage.bean.StarpyAccountBean;
import com.mg.csbackstage.dao.StarpyAccountDao;
import com.mg.csbackstage.factorys.ManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by BennieSun on 2017/4/12.
 */
@Service
public class StarpyAccountservice {

    @Autowired
    StarpyAccountDao starpyAccountDao;

    public StarpyAccountBean findAccount(Long userId) {
        return starpyAccountDao.findAccount(userId);
    }

    public StarpyAccountBean findAccountAsFac(Long userId) {
        StarpyAccountDao starpyAccountDao = ManagerFactory.getInstance("StarpyAccountDao",StarpyAccountDao.class);
        return starpyAccountDao.findAccountAsFac(userId);
    }
}
