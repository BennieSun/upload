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
 * Created by BennieSun on 2017/2/17.
 *
 * ip 白名单表
 */
@EntityTable(tableName = "t_ip_white_list", primaryKey = "id")
@Component
public final class IpWhiteListBean extends DataBase implements IDataSource,Serializable
{
    private static final long serialVersionUID = 9027556958268669502L;
    /**
     * 唯一id
     */
    private long id;

    /**
     * IP地址
     */
    private String ipAddress;

    /**
     * 描述
     */
    private String description;

    /**
     * 状态：0:正常状态
     */
    private int flag;

    /**
     * 开启时间（时间戳）
     */
    private int openTime;

    /**
     * 关闭时间（时间戳）
     */
    private int closeTime;

    /**
     * 创建时间（时间戳）
     */
    private int createdTime;

    /**
     * 修改时间（时间戳）
     */
    private int modifiedTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public int getOpenTime() {
        return openTime;
    }

    public void setOpenTime(int openTime) {
        this.openTime = openTime;
    }

    public int getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(int closeTime) {
        this.closeTime = closeTime;
    }

    public int getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(int createdTime) {
        this.createdTime = createdTime;
    }

    public int getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(int modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    /**
     * 派生类中实现此方法（ReadAndWrite）
     **/
    @SuppressWarnings("unchecked")
    @EntityDataSource(dataSourceInitName = "twMaster",isMaster = true)
    public final SqlProcessor<IpWhiteListBean> SP() {
        return SqlProcessor.getInstance(IpWhiteListBean.class);
    }

    /**
     * 派生类中实现此方法（Read）
     **/
    @SuppressWarnings("unchecked")
    @EntityDataSource(dataSourceInitName = "twSlave",isMaster = false)
    public final SqlOnlyReadProcessor<IpWhiteListBean> SPRead() {
        return SqlOnlyReadProcessor.getInstance(IpWhiteListBean.class);
    }

} // end class IpWhiteListBean
