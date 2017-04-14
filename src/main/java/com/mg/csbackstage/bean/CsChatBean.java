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
 * 客服聊天
 */
@EntityTable(tableName = "t_cs_chat", primaryKey = "id")
@Component
public final class CsChatBean extends DataBase implements IDataSource,Serializable
{
    private static final long serialVersionUID = -3835169416407484438L;
    /**
     * 唯一id
     */
    private Long id;

    /**
     * 问题id，对应表t_ask_questions的id
     */
    private Long aqId;

    /**
     * 聊天信息
     */
    private String message;

    /**
     * 发送者id
     */
    private Long senderId;

    /**
     * 聊天图片地址
     */
    private String imgUrl;

    /**
     * 创建时间（时间戳）
     */
    private Integer createdTime;

    /**
     * 创建ip
     */
    private String createdIp;

    /**
     * 1:玩家，2:客服人员 默认为0
     */
    private Integer senderType;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAqId() {
        return aqId;
    }

    public void setAqId(Long aqId) {
        this.aqId = aqId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getSenderId() {
        return senderId;
    }

    public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Integer getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Integer createdTime) {
        this.createdTime = createdTime;
    }

    public String getCreatedIp() {
        return createdIp;
    }

    public void setCreatedIp(String createdIp) {
        this.createdIp = createdIp;
    }

    public Integer getSenderType() {
        return senderType;
    }

    public void setSenderType(Integer senderType) {
        this.senderType = senderType;
    }

    /**
     * 派生类中实现此方法（ReadAndWrite）
     **/
    @SuppressWarnings("unchecked")
    @EntityDataSource(dataSourceInitName = "twMaster",isMaster = true)
    public final SqlProcessor<CsChatBean> SP() {
        return SqlProcessor.getInstance(CsChatBean.class);
    }

    /**
     * 派生类中实现此方法（Read）
     **/
    @SuppressWarnings("unchecked")
    @EntityDataSource(dataSourceInitName = "twSlave",isMaster = false)
    public final SqlOnlyReadProcessor<CsChatBean> SPRead() {
        return SqlOnlyReadProcessor.getInstance(CsChatBean.class);
    }

} // end class CsChatBean
