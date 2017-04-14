package com.mg.csbackstage.interfaces;

import com.mg.csbackstage.pojo.UsersPojo;
import io.netty.channel.Channel;

/**
 * Created by BennieSun on 2017/4/5.
 */
public interface ISession {

    public UsersPojo usersPojo();

    public Channel channel();
}
