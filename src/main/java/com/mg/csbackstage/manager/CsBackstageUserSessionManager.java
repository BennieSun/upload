package com.mg.csbackstage.manager;

import com.mg.csbackstage.interfaces.ISession;
import com.mg.csbackstage.pojo.UsersPojo;
import com.mg.csbackstage.sessions.UserSession;
import io.netty.channel.Channel;
import io.netty.channel.ChannelId;
import org.apache.log4j.Logger;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by BennieSun on 2017/4/5.
 *
 * 后台管理员userSession
 */
public class CsBackstageUserSessionManager {

    private static final Logger logger = Logger.getLogger(CsBackstageUserSessionManager.class);

    private static ConcurrentHashMap<ChannelId, Long> channelUserMap = new ConcurrentHashMap();
    private static ConcurrentHashMap<Long, Channel> userChannelMap = new ConcurrentHashMap();
    private static ConcurrentHashMap<Long, ISession> sessionMap = new ConcurrentHashMap();

    public static void setInitSession(UsersPojo user, Channel channel) {
        UserSession session = new UserSession(user, channel);
        sessionMap.put(user.getUserId(), session);
        updateUserAndChannel(user.getUserId(), channel);
    }

    public static void removeSession(Long userId, ChannelId channelId) {
        if (null != userId && sessionMap.containsKey(userId)) {
            sessionMap.remove(userId);
        }
        if (null != channelId && channelUserMap.containsKey(channelId)) {
            channelUserMap.remove(channelId);
        }
        if (null != userId && userChannelMap.containsKey(userId)) {
            userChannelMap.remove(userId);
        }
    }

    public static void updateUserAndChannel(Long userId, Channel channel) {
        if(userId != 0) {
            channelUserMap.put(channel.id(), userId);
            userChannelMap.put(userId, channel);
        }
    }

    public static Long getUserIdByChannelId(ChannelId channelId) {
        return channelUserMap.get(channelId);
    }

    public static Channel getChannelByUserId(Long userId){
        return userChannelMap.get(userId);
    }

    public static ISession getSessionByUserId(Long userId) {
        return (ISession)sessionMap.get(userId);
    }

    public static ISession getSessionByChannelId(ChannelId channelId) {
        Long userId = channelUserMap.get(channelId);
        return userId == null?null:(ISession)sessionMap.get(userId);
    }

    public static ConcurrentHashMap<Long, ISession> getSessionMap(){
        return sessionMap;
    }

    public static Integer getOnlineNum() {
        return sessionMap.size();
    }



}
