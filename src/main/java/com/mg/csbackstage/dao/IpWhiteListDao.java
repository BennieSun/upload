package com.mg.csbackstage.dao;

import com.mg.csbackstage.bean.IpWhiteListBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by BennieSun on 2017/2/9.
 */
@Repository
public class IpWhiteListDao {

    @Autowired
    IpWhiteListBean ipWhiteListBean;

    public List<IpWhiteListBean> findAllBean(){
        return ipWhiteListBean.SPRead().fetchAll("select * from t_ip_white_list where flag = 0");
    }
}
