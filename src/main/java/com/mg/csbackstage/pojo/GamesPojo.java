package com.mg.csbackstage.pojo;

import com.alibaba.fastjson.JSONObject;
import com.mg.util.core.GlobalHelper;

/**
 * Created by BennieSun on 2017/1/20.
 *
 * games
 */

public class GamesPojo {

//    private String code;
//
//    private String message;

    /**
     * 唯一id
     */
    private Long gameId;

    /**
     * 游戏原始名称
     */
    private String gameOriginName;

    /**
     * 游戏名称
     */
    private String gameName;

    /**
     * 游戏标识
     */
    private String gameCode;

    /**
     * 客户端key
     */
    private String appKey;

    /**
     * facebook应用Id
     */
    private String fbAppId;

    /**
     * Google key
     */
    private String googleKey;

    /**
     * 蘋果應用的unique id
     */
    private String appleId;

    /**
     * 游戏包名称
     */
    private String packageName;

    /**
     * 登入开关 0为关，1为开
     */
    private String loginSwitch;

    /**
     * 充值开关 0为关，1为开
     */
    private String paySwitch;

    /**
     * 登入key
     */
    private String loginKey;

    /**
     * 充值key
     */
    private String payKey;

    /**
     * 平台标识：（iOS、android、winphone）
     */
    private String platform;

    /**
     * 创建时间（时间戳）
     */
    private Integer createdTime;

    /**
     * 修改时间（时间戳）
     */
    private Integer modifiedTime;

    /**
     *  狀態：0為開，1為關 默認為0
     */
    private Integer flag;

//    public String getCode() {
//        return code;
//    }
//
//    public void setCode(String code) {
//        this.code = code;
//    }
//
//    public String getMessage() {
//        return message;
//    }
//
//    public void setMessage(String message) {
//        this.message = message;
//    }

    public Long getGameId() {
        return gameId;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
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

    public String getGameCode() {
        return gameCode;
    }

    public void setGameCode(String gameCode) {
        this.gameCode = gameCode;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getFbAppId() {
        return fbAppId;
    }

    public void setFbAppId(String fbAppId) {
        this.fbAppId = fbAppId;
    }

    public String getGoogleKey() {
        return googleKey;
    }

    public void setGoogleKey(String googleKey) {
        this.googleKey = googleKey;
    }

    public String getAppleId() {
        return appleId;
    }

    public void setAppleId(String appleId) {
        this.appleId = appleId;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getLoginSwitch() {
        return loginSwitch;
    }

    public void setLoginSwitch(String loginSwitch) {
        this.loginSwitch = loginSwitch;
    }

    public String getPaySwitch() {
        return paySwitch;
    }

    public void setPaySwitch(String paySwitch) {
        this.paySwitch = paySwitch;
    }

    public String getLoginKey() {
        return loginKey;
    }

    public void setLoginKey(String loginKey) {
        this.loginKey = loginKey;
    }

    public String getPayKey() {
        return payKey;
    }

    public void setPayKey(String payKey) {
        this.payKey = payKey;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public Integer getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Integer createdTime) {
        this.createdTime = createdTime;
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

    public GamesPojo getGames(JSONObject jsonObject){
        GamesPojo gamesPojo = null;
        try {
            gamesPojo = GlobalHelper.ToEntityHelper.getObjectFromJson(jsonObject,this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return gamesPojo;
    }
}
