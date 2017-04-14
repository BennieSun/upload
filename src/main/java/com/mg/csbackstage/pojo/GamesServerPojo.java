package com.mg.csbackstage.pojo;


import com.alibaba.fastjson.JSONObject;
import com.mg.util.core.GlobalHelper;

/**
 * Created by BennieSun on 2017/2/15.
 *
 * 服务器列表
 */
public class GamesServerPojo {

//    private String code;
//
//    private String message;

    /**
     * 唯一id
     */
    private Long gameServerId;

    /**
     * 用户gamecode,对应表t_games的gameCode
     */
    private String gameCode;

    /**
     * 游戏伺服器名称
     */
    private String serverName;

    /**
     * 游戏伺服器标识
     */
    private String serverCode;

    /**
     * 开服时间
     */
    private Integer openServerTime;

    /**
     * 创建时间（时间戳）
     */
    private Integer createdTime;

    /**
     * 修改时间（时间戳）
     */
    private Integer modifiedTime;

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

    public Long getGameServerId() {
        return gameServerId;
    }

    public void setGameServerId(Long gameServerId) {
        this.gameServerId = gameServerId;
    }

    public String getGameCode() {
        return gameCode;
    }

    public void setGameCode(String gameCode) {
        this.gameCode = gameCode;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public String getServerCode() {
        return serverCode;
    }

    public void setServerCode(String serverCode) {
        this.serverCode = serverCode;
    }

    public Integer getOpenServerTime() {
        return openServerTime;
    }

    public void setOpenServerTime(Integer openServerTime) {
        this.openServerTime = openServerTime;
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

    public GamesServerPojo getGames(JSONObject jsonObject){
        GamesServerPojo gamesServerPojo = null;
        try {
            gamesServerPojo = GlobalHelper.ToEntityHelper.getObjectFromJson(jsonObject,this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return gamesServerPojo;
    }

} // end class GamesServerBean
