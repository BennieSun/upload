package com.mg.csbackstage.service;

import com.mg.csbackstage.bean.CsJurisdictionGamesBean;
import com.mg.csbackstage.dao.CsJurisdictionGamesDao;
import com.mg.csbackstage.factorys.ManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by BennieSun on 2017/4/12.
 */
@Service
public class CsJurisdictionGamesService {

    @Autowired
    CsJurisdictionGamesDao csJurisdictionGamesDao;

    public List<CsJurisdictionGamesBean> findJurisdictionGames(Long userId) {
        return csJurisdictionGamesDao.findJurisdictionGames(userId);
    }

    public List<CsJurisdictionGamesBean> findJurisdictionGamesAsFac(Long userId) {
        CsJurisdictionGamesDao facJurisdictionGamesDao = ManagerFactory.getInstance("CsJurisdictionGamesDao",CsJurisdictionGamesDao.class);
        return facJurisdictionGamesDao.findJurisdictionGamesAsFac(userId);
    }
}
