package com.mg.csbackstage.pojo;

/**
 * Created by BennieSun on 2017/4/13.
 */
public class CsChatDetailPojo {

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

    /**
     * 发送者名称
     */
    private String senderName;

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

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }
}
