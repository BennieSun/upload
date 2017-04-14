package com.mg.csbackstage.pojo;

import com.alibaba.fastjson.JSONObject;
import com.mg.util.core.GlobalHelper;

/**
 * Created by BennieSun on 2017/1/20.
 *
 * User
 */

public class UsersPojo {

    /**
     * 用户唯一id
     */
    private Long userId;

    /**
     * 用户名
     */
    private String name;

    /**
     * 对外名称，玩家角色名称，客服人员为t_starpy_account表中的foreignName
     */
    private String foreignName;

    /**
     * 用户密码
     */
    private String pwd;

    /**
     * 免注册用户名
     */
    private String freeRegisterName;

    /**
     * 免注册用户密码
     */
    private String freeRegisterPwd;

    /**
     * 免注册用户密码Md5
     */
    private String freeRegisterPwdMd5;

    /**
     * 第三方平台ID
     */
    private String thirdPlatId;

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
     * 用户注册时间（时间戳）
     */
    private Integer registerTime;

    /**
     * 用户注册IP
     */
    private String registerIp;

    /**
     * 用户绑定时间（时间戳）
     */
    private Integer bindTime;

    /**
     * 用户绑定时IP
     */
    private String bindIp;

    /**
     * 用户修改时的时间（时间戳）
     */
    private Integer modifiedTime;

    /**
     * 用户修改时IP
     */
    private String modifiedIp;

    /**
     * 用户状态 0代表正常状态，1代表黑名单状态
     */
    private Integer flag;

    /**
     * 用户注册来源平台 fb、google、twitter
     */
    private String registPlatform;

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
     * 广告来源平台
     */
    private String adsPlatForm;

    /**
     * 广告所属广告商
     */
    private String advertiser;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getForeignName() {
        return foreignName;
    }

    public void setForeignName(String foreignName) {
        this.foreignName = foreignName;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getFreeRegisterName() {
        return freeRegisterName;
    }

    public void setFreeRegisterName(String freeRegisterName) {
        this.freeRegisterName = freeRegisterName;
    }

    public String getFreeRegisterPwd() {
        return freeRegisterPwd;
    }

    public void setFreeRegisterPwd(String freeRegisterPwd) {
        this.freeRegisterPwd = freeRegisterPwd;
    }

    public String getFreeRegisterPwdMd5() {
        return freeRegisterPwdMd5;
    }

    public void setFreeRegisterPwdMd5(String freeRegisterPwdMd5) {
        this.freeRegisterPwdMd5 = freeRegisterPwdMd5;
    }

    public String getThirdPlatId() {
        return thirdPlatId;
    }

    public void setThirdPlatId(String thirdPlatId) {
        this.thirdPlatId = thirdPlatId;
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

    public Integer getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Integer registerTime) {
        this.registerTime = registerTime;
    }

    public String getRegisterIp() {
        return registerIp;
    }

    public void setRegisterIp(String registerIp) {
        this.registerIp = registerIp;
    }

    public Integer getBindTime() {
        return bindTime;
    }

    public void setBindTime(Integer bindTime) {
        this.bindTime = bindTime;
    }

    public String getBindIp() {
        return bindIp;
    }

    public void setBindIp(String bindIp) {
        this.bindIp = bindIp;
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

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public String getRegistPlatform() {
        return registPlatform;
    }

    public void setRegistPlatform(String registPlatform) {
        this.registPlatform = registPlatform;
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

    public String getAdsPlatForm() {
        return adsPlatForm;
    }

    public void setAdsPlatForm(String adsPlatForm) {
        this.adsPlatForm = adsPlatForm;
    }

    public String getAdvertiser() {
        return advertiser;
    }

    public void setAdvertiser(String advertiser) {
        this.advertiser = advertiser;
    }

    public UsersPojo getUsers(JSONObject jsonObject){
        UsersPojo usersPojo = null;
        try {
            usersPojo = GlobalHelper.ToEntityHelper.getObjectFromJson(jsonObject,this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return usersPojo;
    }
}
