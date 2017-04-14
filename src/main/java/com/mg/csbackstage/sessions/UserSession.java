package com.mg.csbackstage.sessions;

import com.mg.csbackstage.interfaces.ISession;
import com.mg.csbackstage.pojo.UsersPojo;
import io.netty.channel.Channel;

/**
 * Created by BennieSun on 2017/4/6.
 */
public class UserSession implements ISession {

    UsersPojo usersPojo;

    Channel channel;

    public UserSession(UsersPojo user, Channel channel) {
        this.channel = channel;
        this.usersPojo = user;
    }


    @Override
    public UsersPojo usersPojo() {
        return this.usersPojo;
    }

    @Override
    public Channel channel() {
        return this.channel;
    }
}
