package com.mg.upload.core.utils;

/**
 * Created by BennieSun on 2017/2/16.
 *
 * 充值枚举
 */
public class CsEnumUtils {

    /**
     * 发送者
     */
    public enum SenderType{
        player(1),// 玩家
        cs(2);//客服

        private final int stateNum;

        SenderType(int statenum){
            this.stateNum = statenum;
        }
        public int getStatusNum() {
            return stateNum;
        }
    }

    /**
     * 权限
     */
    public enum Jurisdiction{
        ordinary(0),// 客服普通用户
        superAdmin(1),//超级管理员
        csAdmin(2);//客服管理员

        private final int stateNum;

        Jurisdiction(int statenum){
            this.stateNum = statenum;
        }
        public int getStatusNum() {
            return stateNum;
        }
    }

    /**
     * 问题状态（进行中、结束）
     */
    public enum AskQuestionsFlag {
        processing(0),// 进行中
        end(1);//结束

        private final int stateNum;

        AskQuestionsFlag(int statenum){
            this.stateNum = statenum;
        }
        public int getStatusNum() {
            return stateNum;
        }
    }
}
