package com.mg.csbackstage.bean;

import com.mg.db.jdbc.core.SqlOnlyReadProcessor;
import com.mg.db.jdbc.core.SqlProcessor;
import com.mg.db.jdbc.core.abstracts.DataBase;
import com.mg.db.jdbc.core.annotations.EntityDataSource;
import com.mg.db.jdbc.core.annotations.EntityTable;
import com.mg.db.jdbc.core.interfaces.IDataSource;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * Created by BennieSun on 2017/4/11.
 *
 * 管理账号
 */
@EntityTable(tableName = "t_starpy_account", primaryKey = "id")
@Component
public final class StarpyAccountBean extends DataBase implements IDataSource,Serializable
{
    private static final long serialVersionUID = 9008334155305680658L;
    /**
     * 唯一id
     */
    private Long id;

    /**
     * 用户名
     */
    private String name;

    /**
     * 用户密码
     */
    private String pwd;

    /**
     * 申请人
     */
    private String applicant;

    /**
     * 对外名称
     */
    private String foreignName;

    /**
     * 权限 0：普通，1:超级管理员，2:客服管理员
     */
    private Integer jurisdiction;

    /**
     * 用户注册时间（时间戳）
     */
    private Integer registerTime;

    /**
     * 修改时间（时间戳）
     */
    private Integer modifiedTime;

    /**
     * 用户状态 0：正常状态，1：黑名单状态
     */
    private Integer flag;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getApplicant() {
        return applicant;
    }

    public void setApplicant(String applicant) {
        this.applicant = applicant;
    }

    public String getForeignName() {
        return foreignName;
    }

    public void setForeignName(String foreignName) {
        this.foreignName = foreignName;
    }

    public Integer getJurisdiction() {
        return jurisdiction;
    }

    public void setJurisdiction(Integer jurisdiction) {
        this.jurisdiction = jurisdiction;
    }

    public Integer getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Integer registerTime) {
        this.registerTime = registerTime;
    }

    public Integer getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Integer modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    /**
     * 派生类中实现此方法（ReadAndWrite）
     **/
    @SuppressWarnings("unchecked")
    @EntityDataSource(dataSourceInitName = "twMaster",isMaster = true)
    public final SqlProcessor<StarpyAccountBean> SP() {
        return SqlProcessor.getInstance(StarpyAccountBean.class);
    }

    /**
     * 派生类中实现此方法（Read）
     **/
    @SuppressWarnings("unchecked")
    @EntityDataSource(dataSourceInitName = "twSlave",isMaster = false)
    public final SqlOnlyReadProcessor<StarpyAccountBean> SPRead() {
        return SqlOnlyReadProcessor.getInstance(StarpyAccountBean.class);
    }

} // end class CsAccountBean
