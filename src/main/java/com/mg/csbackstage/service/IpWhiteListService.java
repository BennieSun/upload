package com.mg.csbackstage.service;

import com.mg.csbackstage.bean.IpWhiteListBean;
import com.mg.csbackstage.dao.IpWhiteListDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by BennieSun on 2017/2/17.
 */
@Service
public class IpWhiteListService {

    @Autowired
    IpWhiteListDao ipWhiteListDao;

    public List<IpWhiteListBean> findAllBean(){
        return ipWhiteListDao.findAllBean();
    }
}
