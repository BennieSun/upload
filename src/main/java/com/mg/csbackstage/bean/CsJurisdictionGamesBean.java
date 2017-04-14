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
 * 客服权限游戏
 */
@EntityTable(tableName = "t_cs_jurisdiction_games", primaryKey = "id")
@Component
public final class CsJurisdictionGamesBean extends DataBase implements IDataSource,Serializable
{
    private static final long serialVersionUID = 4526068607232846475L;
    /**
     * 唯一id
     */
    private Long id;

    /**
     * 用户id，对应t_cs_account的id
     */
    private Long userId;

    /**
     * 用户密码
     */
    private String gameName;

    /**
     * 申请人
     */
    private String gameCode;

    /**
     * 用户注册时间（时间戳）
     */
    private Integer createTime;

    /**
     * 修改时间（时间戳）
     */
    private Integer modifiedTime;

    /**
     * 用户状态 0：正常状态，1：关服
     */
    private Integer flag;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public Integer getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Integer createTime) {
        this.createTime = createTime;
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
    public final SqlProcessor<CsJurisdictionGamesBean> SP() {
        return SqlProcessor.getInstance(CsJurisdictionGamesBean.class);
    }

    /**
     * 派生类中实现此方法（Read）
     **/
    @SuppressWarnings("unchecked")
    @EntityDataSource(dataSourceInitName = "twSlave",isMaster = false)
    public final SqlOnlyReadProcessor<CsJurisdictionGamesBean> SPRead() {
        return SqlOnlyReadProcessor.getInstance(CsJurisdictionGamesBean.class);
    }

} // end class CsJurisdictionGamesBean
