package com.mg.csbackstage.dao;

import com.mg.csbackstage.bean.CsJurisdictionGamesBean;
import com.mg.csbackstage.factorys.ManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by BennieSun on 2017/4/12.
 */
@Repository
public class CsJurisdictionGamesDao {

    @Autowired
    CsJurisdictionGamesBean csJurisdictionGamesBean;

    public List<CsJurisdictionGamesBean> findJurisdictionGames(Long userId) {
        return csJurisdictionGamesBean.SPRead().fetchAll("select * from t_cs_jurisdiction_games where userId=?",userId);
    }

    public List<CsJurisdictionGamesBean> findJurisdictionGamesAsFac(Long userId) {
        CsJurisdictionGamesBean facJurisdictionGamesBean = ManagerFactory.getInstance("CsJurisdictionGamesBean",CsJurisdictionGamesBean.class);
        return facJurisdictionGamesBean.SPRead().fetchAll("select * from t_cs_jurisdiction_games where userId=?",userId);
    }
}
