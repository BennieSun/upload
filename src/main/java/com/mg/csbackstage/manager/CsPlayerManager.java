package com.mg.csbackstage.manager;

import com.mg.csbackstage.bean.CsAskQuestionsBean;
import com.mg.csbackstage.bean.CsChatBean;
import com.mg.csbackstage.bean.StarpyAccountBean;
import com.mg.csbackstage.core.utils.CsEnumUtils;
import com.mg.csbackstage.service.CsAskQuestionsService;
import com.mg.csbackstage.service.CsChatService;
import com.mg.csbackstage.service.StarpyAccountservice;
import com.mg.util.core.GlobalHelper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Created by BennieSun on 2017/4/11.
 *
 * 客服问题列表（玩家）
 */
@Component
public class CsPlayerManager {

    private static Logger logger = Logger.getLogger(CsPlayerManager.class);

    @Autowired
    CsAskQuestionsService askQuestionsService;

    @Autowired
    CsChatService csChatService;

    @Autowired
    StarpyAccountservice starpyAccountservice;

    public int insert(Map<String, String> initMap) {

        String ip = initMap.get("ip");
        String gameCode = initMap.get("gameCode");//游戏标识
        String packageName = initMap.get("packageName");//游戏包名称
        String serverCode = initMap.get("serverCode");//游戏服标识
        String userId = initMap.get("userId");//用户唯一id，关联t_users表的userId
        String uniqueId = initMap.get("uniqueId");//唯一标识Id（iOS&android同用）
        String mac = initMap.get("mac");//用户mac地址
        String imei = initMap.get("imei");//用户imei地址
        String androidId = initMap.get("androidId");//用户android地址
        String adid = initMap.get("adid");//设备广告ID
        String idfa = initMap.get("idfa");//IOS:idfa
        String uuid = initMap.get("uuid");//IOS:uuid
        String roleName = initMap.get("roleName");//角色名
        String roleLevel = initMap.get("roleLevel");//角色等级
        String roleId = initMap.get("roleId");//角色Id
        String operatingSystem = initMap.get("operatingSystem");//'用户操作系统 android、ios、winphine'
        String systemVersion = initMap.get("systemVersion");//'手机系统版本'
        String deviceType = initMap.get("deviceType");//'设备类型'
        String message = initMap.get("message");//'消息'
        String tel = initMap.get("tel");//'电话'
        String imgUrl = initMap.get("imgUrl");//'图片地址'

        String gameOriginName = initMap.get("gameOriginName");//游戏原始名称
        String gameName = initMap.get("gameName");//游戏名称
        String serverName =  initMap.get("serverName");//伺服器服名称
        String registPlatform = initMap.get("registPlatform");//玩家注册来源

        int now = GlobalHelper.currentTimestamp();

        CsAskQuestionsBean askQuestionsBean = new CsAskQuestionsBean();
        askQuestionsBean.setUserId(Long.valueOf(userId));
        askQuestionsBean.setGameCode(gameCode);
        askQuestionsBean.setCreatedIp(ip);
        askQuestionsBean.setPackageName(packageName);
        askQuestionsBean.setServerCode(serverCode);
        askQuestionsBean.setUniqueId(uniqueId);
        askQuestionsBean.setMac(mac);
        askQuestionsBean.setImei(imei);
        askQuestionsBean.setAndroidId(androidId);
        askQuestionsBean.setIdfa(idfa);
        askQuestionsBean.setUuid(uuid);
        askQuestionsBean.setRoleName(roleName);
        askQuestionsBean.setRoleLevel(roleLevel);
        askQuestionsBean.setRoleId(roleId);
        askQuestionsBean.setOperatingSystem(operatingSystem);
        askQuestionsBean.setSystemVersion(systemVersion);
        askQuestionsBean.setDeviceType(deviceType);
        askQuestionsBean.setAdid(adid);
        askQuestionsBean.setCreatedTime(now);
        askQuestionsBean.setCreatedIp(ip);
        askQuestionsBean.setModifiedTime(now);
        askQuestionsBean.setModifiedIp(ip);
        askQuestionsBean.setTelephone(tel);
        askQuestionsBean.setGameOriginName(gameOriginName);
        askQuestionsBean.setGameName(gameName);
        askQuestionsBean.setServerName(serverName);
        askQuestionsBean.setRegistPlatform(registPlatform);

        int returnId = askQuestionsService.insert(askQuestionsBean);
        if (returnId > 0){
            CsChatBean csChatBean = new CsChatBean();
            csChatBean.setAqId(Long.valueOf(returnId));
            csChatBean.setCreatedIp(ip);
            csChatBean.setCreatedTime(now);
            csChatBean.setMessage(message);
            csChatBean.setImgUrl(imgUrl);
            csChatBean.setSenderId(Long.valueOf(userId));
            csChatBean.setSenderType(CsEnumUtils.SenderType.player.getStatusNum());
            csChatService.insertAsFac(csChatBean);
        }

        return returnId;
    }

    /**
     * 存在未结束的问题
     * @param userId
     * @return
     */
    public boolean isExistQuestions(String userId) {
        int count = askQuestionsService.findAQCountByUserId(userId);
        if (count >= 1){
            return true;
        }

        return false;
    }

    /**
     * 未完成的问题
     * @param userId
     * @return
     */
    public CsAskQuestionsBean getQuestions(Long userId, String gameCode){
        return askQuestionsService.findAQByUserId(userId, gameCode, CsEnumUtils.AskQuestionsFlay.processing.getStatusNum());
    }

    /**
     * 获取聊天所有信息
     * @param aqId
     * @return
     */
    public List<CsChatBean> getChatList(String aqId) {
        return csChatService.findChatList(aqId);
    }

    public StarpyAccountBean getAccount(Long userId) {
        return starpyAccountservice.findAccount(userId);
    }
}
