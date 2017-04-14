package com.mg.csbackstage.pojo;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mg.util.core.GlobalHelper;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by BennieSun on 2017/2/15.
 *
 * 商品列表
 */
public class GamesCommodityItemPojo {

    /**
     * 商品唯一Id
     */
    private Long itemId;

    /**
     * 用户gamecode,对应表t_games的gameCode
     */
    private String gameCode;

    /**
     * 商品品项Id（google：sku）
     */
    private String productId;

    /**
     * 苹果商店商品ID
     */
    private String appleId;

    /**
     * 商品价格
     */
    private BigDecimal price;

    /**
     * 商品价格币种（美元，港币、台币）
     */
    private String priceMoneyType;

    /**
     * 现金基数（用于游戏币发放：游戏币=moneyBaseNum*multiple+moneyBaseNum*multiple*giftMultiple）
     */
    private Integer moneyBaseNum;

    /**
     * 现金基数，币种（台币、美元，港币）
     */
    private String MoneyBaseNumType;

    /**
     * 游戏币发放倍数
     */
    private Integer multiple;

    /**
     * 游戏币赠送倍数
     */
    private Integer giftMultiple;

    /**
     * 月卡基数(网页充值,月卡的起点)
     */
    private Integer monthCardBaseNum;

    /**
     * 是否为月卡 0：普通卡，1：月卡
     */
    private Integer isMonthCard;

    /**
     * 创建时间（时间戳）
     */
    private Integer createdTime;

    /**
     * 修改时间（时间戳）
     */
    private Integer modifiedTime;

    /**
     * 储值来源（google、appStore、INGAME、COSTPOINT、HE0011等）
     */
    private String platFormSource;

    /**
     * 储值来源方式（google、appStore、mycardIngame、mycardBilling、mycardMember、mycardVisa）
     */
    private String platFormSourceMode;

    /**
     * 品项描述
     */
    private String description;

    /**
     * 是否为年卡 0：普通卡，3：年卡
     */
    private Integer isYearCard;

    /**
     * 年卡基数(网页充值,年卡的起点)
     */
    private Integer yearCardBaseNum;

    /**
     * 是否为季卡 0：普通卡，2：季卡
     */
    private Integer isQuarterCard;

    /**
     * 季卡基数(网页充值,季卡的起点)
     */
    private Integer quarterCardBaseNum;

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getGameCode() {
        return gameCode;
    }

    public void setGameCode(String gameCode) {
        this.gameCode = gameCode;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getAppleId() {
        return appleId;
    }

    public void setAppleId(String appleId) {
        this.appleId = appleId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getPriceMoneyType() {
        return priceMoneyType;
    }

    public void setPriceMoneyType(String priceMoneyType) {
        this.priceMoneyType = priceMoneyType;
    }

    public Integer getMoneyBaseNum() {
        return moneyBaseNum;
    }

    public void setMoneyBaseNum(Integer moneyBaseNum) {
        this.moneyBaseNum = moneyBaseNum;
    }

    public String getMoneyBaseNumType() {
        return MoneyBaseNumType;
    }

    public void setMoneyBaseNumType(String moneyBaseNumType) {
        MoneyBaseNumType = moneyBaseNumType;
    }

    public Integer getMultiple() {
        return multiple;
    }

    public void setMultiple(Integer multiple) {
        this.multiple = multiple;
    }

    public Integer getGiftMultiple() {
        return giftMultiple;
    }

    public void setGiftMultiple(Integer giftMultiple) {
        this.giftMultiple = giftMultiple;
    }

    public Integer getMonthCardBaseNum() {
        return monthCardBaseNum;
    }

    public void setMonthCardBaseNum(Integer monthCardBaseNum) {
        this.monthCardBaseNum = monthCardBaseNum;
    }

    public Integer getIsMonthCard() {
        return isMonthCard;
    }

    public void setIsMonthCard(Integer isMonthCard) {
        this.isMonthCard = isMonthCard;
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

    public String getPlatFormSource() {
        return platFormSource;
    }

    public void setPlatFormSource(String platFormSource) {
        this.platFormSource = platFormSource;
    }

    public String getPlatFormSourceMode() {
        return platFormSourceMode;
    }

    public void setPlatFormSourceMode(String platFormSourceMode) {
        this.platFormSourceMode = platFormSourceMode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getIsYearCard() {
        return isYearCard;
    }

    public void setIsYearCard(Integer isYearCard) {
        this.isYearCard = isYearCard;
    }

    public Integer getYearCardBaseNum() {
        return yearCardBaseNum;
    }

    public void setYearCardBaseNum(Integer yearCardBaseNum) {
        this.yearCardBaseNum = yearCardBaseNum;
    }

    public Integer getIsQuarterCard() {
        return isQuarterCard;
    }

    public void setIsQuarterCard(Integer isQuarterCard) {
        this.isQuarterCard = isQuarterCard;
    }

    public Integer getQuarterCardBaseNum() {
        return quarterCardBaseNum;
    }

    public void setQuarterCardBaseNum(Integer quarterCardBaseNum) {
        this.quarterCardBaseNum = quarterCardBaseNum;
    }

    public GamesCommodityItemPojo getCommodityItem(JSONObject jsonObject){
        GamesCommodityItemPojo commodityItemPojo = null;
        try {
            commodityItemPojo = GlobalHelper.ToEntityHelper.getObjectFromJson(jsonObject,this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return commodityItemPojo;
    }

    public List<GamesCommodityItemPojo> getCommodityItemList(String jsonObject){
        return JSONArray.parseArray(jsonObject,GamesCommodityItemPojo.class);
    }

} // end class GamesCommodityItemBean
