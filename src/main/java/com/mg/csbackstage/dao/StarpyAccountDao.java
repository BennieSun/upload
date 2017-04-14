package com.mg.csbackstage.dao;

import com.mg.csbackstage.bean.StarpyAccountBean;
import com.mg.csbackstage.factorys.ManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by BennieSun on 2017/4/12.
 */
@Repository
public class StarpyAccountDao {

    @Autowired
    StarpyAccountBean starpyAccountBean;

    public StarpyAccountBean findAccount(Long userId) {
        return starpyAccountBean.SPRead().fetchOne("select * from t_starpy_account where id=?",userId);
    }

    public StarpyAccountBean findAccountAsFac(Long userId) {
        StarpyAccountBean starpyAccountBean = ManagerFactory.getInstance("StarpyAccountBean",StarpyAccountBean.class);
        return starpyAccountBean.SPRead().fetchOne("select * from t_starpy_account where id=?",userId);
    }


}
