package com.mg.csbackstage.manager;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mg.csbackstage.bean.CsAskQuestionsBean;
import com.mg.csbackstage.bean.CsChatBean;
import com.mg.csbackstage.bean.CsJurisdictionGamesBean;
import com.mg.csbackstage.bean.StarpyAccountBean;
import com.mg.csbackstage.constants.ResponseCodeConst;
import com.mg.csbackstage.constants.ResponseMsgConst;
import com.mg.csbackstage.core.utils.CsEnumUtils;
import com.mg.csbackstage.factorys.ManagerFactory;
import com.mg.csbackstage.interfaces.ISession;
import com.mg.csbackstage.pojo.UsersPojo;
import com.mg.csbackstage.service.CsAskQuestionsService;
import com.mg.csbackstage.service.CsChatService;
import com.mg.csbackstage.service.CsJurisdictionGamesService;
import com.mg.csbackstage.service.StarpyAccountservice;
import com.mg.csbackstage.sessions.UserSession;
import com.mg.util.core.GlobalHelper;
import com.mg.util.core.message.ServerConfig;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by BennieSun on 2017/4/5.
 */
public class WebSocketManager {

    private static final Logger logger = Logger.getLogger(WebSocketManager.class);

    public static void setInitSession(String message, ChannelHandlerContext ctx) {
        JSONObject params = JSON.parseObject(message);
        String userId = params.getString("userId");
        String senderType = params.getString("senderType");

        UsersPojo usersPojo = new UsersPojo();
        usersPojo.setUserId(Long.valueOf(userId));
        if (StringUtils.isEmpty(senderType) || senderType.equals(CsEnumUtils.SenderType.player.toString())){//玩家
            String roleName = params.getString("roleName");
            usersPojo.setForeignName(roleName);

        }else if(senderType.equals(CsEnumUtils.SenderType.cs.toString())){//客服人员
            StarpyAccountservice facStarpyAccountservice = ManagerFactory.getInstance("StarpyAccountservice",StarpyAccountservice.class);
            usersPojo.setForeignName(facStarpyAccountservice.findAccountAsFac(Long.valueOf(userId)).getForeignName());
        }

        if (CsEnumUtils.SenderType.cs.toString().equals(senderType)){//客服后台
            CsBackstageUserSessionManager.setInitSession(usersPojo, ctx.channel());
        }

        if (CsEnumUtils.SenderType.player.toString().equals(senderType)) {//玩家
            CsPlayerUserSessionManager.setInitSession(usersPojo, ctx.channel());
        }
    }

    public static void removeSession(ChannelHandlerContext ctx) {
        Long userId = CsPlayerUserSessionManager.getUserIdByChannelId(ctx.channel().id());
        CsBackstageUserSessionManager.removeSession(userId,ctx.channel().id());
        CsPlayerUserSessionManager.removeSession(userId,ctx.channel().id());
    }

    /**
     * paramsMap.put("params", params); => json
     * paramsMap.put("sendMessageTime", GlobalHelper.currentTimestamp()+""); => int
     * @param paramsMap
     * @param ctx
     */
    public static void channelData(Map<String, String> paramsMap, ChannelHandlerContext ctx) {

        JSONObject responeJson = new JSONObject();

        JSONObject params = JSON.parseObject(paramsMap.get("params"));
        int createTime = Integer.parseInt(paramsMap.get("sendMessageTime"));

        //玩家
        String aqId = params.getString("aqUniqueId");
        String message = params.getString("message");
        String gameCode = params.getString("gameCode");
        String gameLanguage = params.getString("gameLanguage");

        //后台
        String senderType = params.getString("senderType");
        String playerUserId = params.getString("playerUserId");

        String langName = ServerConfig.getInstance().getLang();
        if (StringUtils.isNotEmpty(gameLanguage)) {
            langName = gameLanguage;
        }

        if (StringUtils.isEmpty(aqId) || Long.valueOf(aqId) <=0 || StringUtils.isEmpty(message)){
            logger.info("params is null,aqId:"+aqId+",message:"+message);
            return;
        }

        /**区分信息来源**/
        UsersPojo usersPojo = null;
        int senderTypeNum = 0;
        if (StringUtils.isEmpty(senderType) || CsEnumUtils.SenderType.player.toString().equals(senderType)) {//玩家
            usersPojo = new UsersPojo();
            usersPojo.setUserId(CsPlayerUserSessionManager.getUserIdByChannelId(ctx.channel().id()));
            senderTypeNum = CsEnumUtils.SenderType.player.getStatusNum();
        }else if (CsEnumUtils.SenderType.cs.toString().equals(senderType)) {//客服后台
            if (StringUtils.isEmpty(playerUserId)){
                logger.info("params is exception,userId"+playerUserId);
                responeJson.put("code", ResponseCodeConst.PARAMS_EXCEPTION);
                responeJson.put("message", ResponseMsgConst.getInstance(langName).getResponseMsg(ResponseCodeConst.PARAMS_EXCEPTION));
                ctx.channel().writeAndFlush(new TextWebSocketFrame(responeJson.toJSONString()));
                return;
            }
            usersPojo = new UsersPojo();
            usersPojo.setUserId(CsBackstageUserSessionManager.getUserIdByChannelId(ctx.channel().id()));
            senderTypeNum = CsEnumUtils.SenderType.cs.getStatusNum();
        }

        //验证字符长度
        if (GlobalHelper.StringHelper.length(message.replace("</br>"," "))>300){
            logger.info("message is to long");
            responeJson.put("code", ResponseCodeConst.MSG_TO_LONG);
            responeJson.put("message", ResponseMsgConst.getInstance(langName).getResponseMsg(ResponseCodeConst.MSG_TO_LONG));
            ctx.channel().writeAndFlush(new TextWebSocketFrame(responeJson.toJSONString()));
            return;
        }

        CsChatService facChatService = ManagerFactory.getInstance("CsChatService",CsChatService.class);
        CsAskQuestionsService facAskQuestionsService = ManagerFactory.getInstance("CsAskQuestionsService",CsAskQuestionsService.class);
        StarpyAccountservice facStarpyAccountservice = ManagerFactory.getInstance("StarpyAccountservice",StarpyAccountservice.class);
        CsJurisdictionGamesService facJurisdictionGamesService = ManagerFactory.getInstance("CsJurisdictionGamesService",CsJurisdictionGamesService.class);

        if (null == usersPojo || null == usersPojo.getUserId()){
            logger.info("channelId="+ctx.channel().id()+":session's userId is not exist");
            return;
        }

        CsAskQuestionsBean csAskQuestionsBean = facAskQuestionsService.findAQAsFac(usersPojo.getUserId());
        if (null == csAskQuestionsBean || csAskQuestionsBean.getId()!=Long.valueOf(aqId)){
            logger.info("aqUniqueId is not this userId,userId="+usersPojo.getUserId());
            return;
        }

        CsChatBean csChatBean = new CsChatBean();
        csChatBean.setAqId(Long.valueOf(aqId));
        csChatBean.setCreatedIp(ctx.channel().remoteAddress()+"");
        csChatBean.setCreatedTime(createTime);
        csChatBean.setMessage(message);
        csChatBean.setSenderId(usersPojo.getUserId());
        csChatBean.setSenderType(senderTypeNum);
        int returnId = facChatService.insertAsFac(csChatBean);

        if (returnId>0){
            //通知信息接收人
            if (CsEnumUtils.SenderType.player.getStatusNum() == senderTypeNum) {//发送者为玩家 -> 管理员
                //客服在线
                ConcurrentHashMap<Long, ISession> sessionMap = CsBackstageUserSessionManager.getSessionMap();
                if (null != sessionMap) {
                    Set<Map.Entry<Long, ISession>> iter = sessionMap.entrySet();
                    for (Map.Entry<Long, ISession> entry : iter) {
                        JSONObject responeJsonTemp = new JSONObject();
                        Long tempPlayerUserId = entry.getKey();
                        //获取权限
                        StarpyAccountBean starpyAccountBean = facStarpyAccountservice.findAccountAsFac(tempPlayerUserId);
                        //客服管理员
                        if (starpyAccountBean.getJurisdiction() == CsEnumUtils.Jurisdiction.csAdmin.getStatusNum()){
                            responeJsonTemp.put("foreignName", CsPlayerUserSessionManager.getSessionByChannelId(ctx.channel().id()).usersPojo().getForeignName());
                            responeJsonTemp.put("message", message);
                            entry.getValue().channel().writeAndFlush(new TextWebSocketFrame(responeJsonTemp.toJSONString()));
                        }else if (starpyAccountBean.getJurisdiction() == CsEnumUtils.Jurisdiction.ordinary.getStatusNum()){
                            List<CsJurisdictionGamesBean> jurisdictionList = facJurisdictionGamesService.findJurisdictionGamesAsFac(tempPlayerUserId);
                            for (CsJurisdictionGamesBean bean:jurisdictionList){
                                if (bean.getGameCode().equals(gameCode)){
                                    responeJsonTemp.put("foreignName", CsPlayerUserSessionManager.getSessionByChannelId(ctx.channel().id()).usersPojo().getForeignName());
                                    responeJsonTemp.put("message", message);
                                    entry.getValue().channel().writeAndFlush(new TextWebSocketFrame(responeJsonTemp.toJSONString()));
                                }
                            }
                        }else{
                            logger.info("无即时通信权限，只有客服相关权限才有,管理员账号name:"+starpyAccountBean.getName());
                            return;
                        }
                    }
                }
            }else if (CsEnumUtils.SenderType.cs.getStatusNum() == senderTypeNum) {//发送者为客服后台 -> 发送到玩家
                UserSession userSession = (UserSession) CsPlayerUserSessionManager.getSessionByUserId(Long.valueOf(playerUserId));
                if (null != userSession && null != userSession.channel()){
                    JSONObject responeJsonTemp = new JSONObject();
                    responeJsonTemp.put("foreignName", CsBackstageUserSessionManager.getSessionByChannelId(ctx.channel().id()).usersPojo().getForeignName());
                    responeJsonTemp.put("message", message);
                    userSession.channel().writeAndFlush(new TextWebSocketFrame(responeJsonTemp.toJSONString()));
                }
            }

            //通知发送人成功
            responeJson.put("code", ResponseCodeConst.IS_SUCCESS);
            if (CsEnumUtils.SenderType.player.getStatusNum() == senderTypeNum){
                responeJson.put("foreignName", CsPlayerUserSessionManager.getSessionByChannelId(ctx.channel().id()).usersPojo().getForeignName());
            }else if (CsEnumUtils.SenderType.cs.getStatusNum() == senderTypeNum){
                responeJson.put("foreignName", CsBackstageUserSessionManager.getSessionByChannelId(ctx.channel().id()).usersPojo().getForeignName());
            }
            responeJson.put("message", ResponseMsgConst.getInstance(langName).getResponseMsg(ResponseCodeConst.IS_SUCCESS));
            ctx.channel().writeAndFlush(new TextWebSocketFrame(responeJson.toJSONString()));
            return;
        }

        responeJson.put("code", ResponseCodeConst.SYSTEM_EXCEPTION);
        responeJson.put("message", ResponseMsgConst.getInstance(langName).getResponseMsg(ResponseCodeConst.SYSTEM_EXCEPTION));
        ctx.channel().writeAndFlush(new TextWebSocketFrame(responeJson.toJSONString()));
    }





    //任务
    public static class RequestTask implements Runnable {

        private static final Logger logger = Logger.getLogger(RequestTask.class);

        public String acceptUserId;

        public Map<String, Channel> onlineUserChannelMap;

        public String message = GlobalHelper.timestampFormat(GlobalHelper.currentTimestamp()) + ":" + "taskOnline";

        public RequestTask(Map<String, Channel> onlineUserChannelMap) {
            this.onlineUserChannelMap = onlineUserChannelMap;
        }

        public RequestTask(String acceptUserId, Map<String, Channel> onlineUserChannelMap) {
            this.acceptUserId = acceptUserId;
            this.onlineUserChannelMap = onlineUserChannelMap;
        }

        public void run() {
            try {

                while (true) {

                    for (Channel channel : onlineUserChannelMap.values()) {

                        //Channel channel = onlineUserChannelMap.get(acceptUserId);

                        if (channel != null && channel.isOpen()) {
                            logger.info("["
                                    + channel.remoteAddress() + "]:" + GlobalHelper.timestampFormat(GlobalHelper.currentTimestamp()) + ":" + "taskOnline" + "\n");
                            channel.writeAndFlush(new TextWebSocketFrame("["
                                    + channel.remoteAddress() + "]:" + GlobalHelper.timestampFormat(GlobalHelper.currentTimestamp()) + ":" + "taskOnline" + "\n"));
                        }

                        Thread.sleep(10000);
                    }
                }
            } catch (Exception e) {
                logger.info(e.getMessage());
            }

        }
    }
}
