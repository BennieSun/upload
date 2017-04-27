package com.mg.upload.constants;

import com.mg.upload.core.commons.CommonUtils;

/**
 * Created by BennieSun on 2017/2/5.
 *
 * 服务器，常量
 */
public class ServerConst {

    public static final String LOCAL_PLAT_FORM = "starpy";

    public static final String LOCAL_PAY_FROM = "starpy";

    public static final boolean CLOSE_SIGIN = CommonUtils.getCloseSigin();
    public static final boolean OPEN_IP_WHITE = CommonUtils.getOpenIpWhite();
    public static final String GAME_VERIFICATION_KEY = CommonUtils.getGameVerificationKey();
    public static final String GAME_URL = CommonUtils.getGameUrl();
    public static final String LOGIN_VERIFICATION_KEY = CommonUtils.getLoginVerificationKey();
    public static final String LOGIN_URL = CommonUtils.getLoginUrl();

    /**图片访问链接**/
    public static final String IMG_DOWN_URL = CommonUtils.getImgDownUrl();

    /**最大图片上传大小**/
    public static final long IMG_UPLOAD_SIZE = 6144000;


    //public static final String pay_Prefix = "localhost:8082/csbackstage/";
    public static final String pay_Prefix = "http://cs.starb168.com/";
    public static final String mycardSuccessURL = pay_Prefix+"success.html";
    public static final String mycardFailureURL = pay_Prefix+"failure.html";
}
