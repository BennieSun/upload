package com.mg.csbackstage.constants;

/**
 * Created by BennieSun on 2017/2/2.
 *
 * 登入返回状态
 */
public class ResponseCodeConst{

    /*****************成功或不正确****************************/
    /**成功**/
    public static final int IS_SUCCESS = 1000;

    /**重复提问**/
    public static final int REPEAT_ASK_QUESTIONS = 1002;

    /**消息太长**/
    public static final int MSG_TO_LONG = 1003;


    /********************异常或错误***************************／

     /**失败**/
    public static final int IS_FAIL = 2000;

     /**参数异常**/
    public static final int PARAMS_EXCEPTION = 2001;

    /**签名错误**/
    public static final int SIGN_ERROR = 2002;

    /**系统异常**/
    public static final int SYSTEM_EXCEPTION = 2003;




    /********************game状态***************************/

    /**games server不存在**/
    public static final int GAMES_SERVER_NOT_EXIST = 3000;

    /**userId不存在**/
    public static final int USER_ID_NOT_EXIST = 3001;

    /**gamecode不存在**/
    public static final int GAME_CODE_NOT_EXIST = 3002;

}
