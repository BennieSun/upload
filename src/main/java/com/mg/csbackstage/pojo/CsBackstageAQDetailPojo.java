package com.mg.csbackstage.pojo;

/**
 * Created by BennieSun on 2017/4/13.
 */
public class CsBackstageAQDetailPojo {
    /**
     * 唯一id
     */
    private Long id;

    /**
     * 游戏标识
     */
    private String gameCode;

    /**
     * 游戏原始名称
     */
    private String gameOriginName;

    /**
     * 游戏名称
     */
    private String gameName;

    /**
     * 游戏伺服器标识
     */
    private String serverCode;

    /**
     * 游戏伺服器名称
     */
    private String serverName;

    /**
     * 用户注册来源平台 fb、google、twitter、unique、starpy
     */
    private String registPlatform;

    /**
     * 游戏包名称
     */
    private String packageName;

    /**
     * 用户唯一id，关联t_users表的userId
     */
    private Long userId;

    /**
     * 唯一标识Id（iOS&android同用）
     */
    private String uniqueId;

    /**
     * 用户mac地址
     */
    private String mac;

    /**
     * 用户imei地址
     */
    private String imei;

    /**
     * 用户android地址
     */
    private String androidId;

    /**
     * 设备广告ID
     */
    private String adid;

    /**
     * IOS:idfa
     */
    private String idfa;

    /**
     * IOS:uuid
     */
    private String uuid;

    /**
     * 角色名
     */
    private String roleName;

    /**
     * 角色等级
     */
    private String roleLevel;

    /**
     * 角色Id
     */
    private String roleId;

    /**
     * 用户操作系统 android、ios、winphine
     */
    private String operatingSystem;

    /**
     * 手机系统版本
     */
    private String systemVersion;

    /**
     * 设备类型
     */
    private String deviceType;

    /**
     * 用户eamil
     */
    private String email;

    /**
     * 用户手机号码
     */
    private String telephone;

    /**
     * 创建时间（时间戳）
     */
    private Integer createdTime;

    /**
     * 创建ip
     */
    private String createdIp;

    /**
     * 修改时间（时间戳）
     */
    private Integer modifiedTime;

    /**
     * 修改时ip
     */
    private String modifiedIp;

    /**
     * 结束时间（时间戳）
     */
    private Integer endTime;

    /**
     * 结束时ip
     */
    private String endIp;

    /**
     * 狀態：0进行中，1為已完成
     */
    private Integer flag;

    /**
     * 聊天信息
     */
    private String message;

    /**
     * 发送者名称
     */
    private String senderId;

    /**
     * 聊天图片地址
     */
    private String imgUrl;

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

    public String getGameCode() {
        return gameCode;
    }

    public void setGameCode(String gameCode) {
        this.gameCode = gameCode;
    }

    public String getGameOriginName() {
        return gameOriginName;
    }

    public void setGameOriginName(String gameOriginName) {
        this.gameOriginName = gameOriginName;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getServerCode() {
        return serverCode;
    }

    public void setServerCode(String serverCode) {
        this.serverCode = serverCode;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public String getRegistPlatform() {
        return registPlatform;
    }

    public void setRegistPlatform(String registPlatform) {
        this.registPlatform = registPlatform;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getAndroidId() {
        return androidId;
    }

    public void setAndroidId(String androidId) {
        this.androidId = androidId;
    }

    public String getAdid() {
        return adid;
    }

    public void setAdid(String adid) {
        this.adid = adid;
    }

    public String getIdfa() {
        return idfa;
    }

    public void setIdfa(String idfa) {
        this.idfa = idfa;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleLevel() {
        return roleLevel;
    }

    public void setRoleLevel(String roleLevel) {
        this.roleLevel = roleLevel;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getOperatingSystem() {
        return operatingSystem;
    }

    public void setOperatingSystem(String operatingSystem) {
        this.operatingSystem = operatingSystem;
    }

    public String getSystemVersion() {
        return systemVersion;
    }

    public void setSystemVersion(String systemVersion) {
        this.systemVersion = systemVersion;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
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

    public Integer getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Integer modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public String getModifiedIp() {
        return modifiedIp;
    }

    public void setModifiedIp(String modifiedIp) {
        this.modifiedIp = modifiedIp;
    }

    public Integer getEndTime() {
        return endTime;
    }

    public void setEndTime(Integer endTime) {
        this.endTime = endTime;
    }

    public String getEndIp() {
        return endIp;
    }

    public void setEndIp(String endIp) {
        this.endIp = endIp;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Integer getSenderType() {
        return senderType;
    }

    public void setSenderType(Integer senderType) {
        this.senderType = senderType;
    }
}
