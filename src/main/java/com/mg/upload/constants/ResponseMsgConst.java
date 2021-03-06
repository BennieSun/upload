package com.mg.upload.constants;

import com.mg.util.core.message.LangManager;
import com.mg.util.core.message.ServerConfig;
import org.apache.commons.collections4.map.LRUMap;

/**
 * Created by BennieSun on 2017/2/5.
 *
 * 登入返回提示消息
 */
public class ResponseMsgConst {

    private static String langNameLocal = ServerConfig.getInstance().getLang();

    /**所有语言包数据**/
    private static LRUMap<String, ResponseMsgConst> responseMsgLRUMap = new LRUMap<String, ResponseMsgConst>(100);

    private ResponseMsgConst(String langName){
        langNameLocal = langName;
    }

    public static ResponseMsgConst getInstance(String langName){
        ResponseMsgConst responseMsgConst = responseMsgLRUMap.get(langName);
        if (null == responseMsgConst) {
            responseMsgConst = new ResponseMsgConst(langName);
            responseMsgLRUMap.put(langName,responseMsgConst);
        }
        langNameLocal = langName;
        return responseMsgConst;
    }

    public String getResponseMsg(int responseCode){
        String responseMsg;
        switch (responseCode){
            case ResponseCodeConst.IS_SUCCESS:/**成功**/
                responseMsg = LangManager.getString(langNameLocal,"responseMsg","isSuccessMsg");
                break;
            case ResponseCodeConst.IS_CHAT_SUCCESS:/**聊天发送成功**/
                responseMsg = LangManager.getString(langNameLocal,"responseMsg","isChatSuccessMsg");
                break;
            case ResponseCodeConst.REPEAT_ASK_QUESTIONS:/**重复提问**/
                responseMsg = LangManager.getString(langNameLocal,"responseMsg","repeatAskQuestionsMsg");
                break;
            case ResponseCodeConst.MSG_TO_LONG:/**消息太长**/
                responseMsg = LangManager.getString(langNameLocal,"responseMsg","msgToLongMsg");
                break;
            case ResponseCodeConst.MSG_IS_END:/**问题已结束**/
                responseMsg = LangManager.getString(langNameLocal,"responseMsg","msgIsEndMsg");
                break;
            case ResponseCodeConst.MSG_SEND_PERMISSION:/**没有发送权限**/
                responseMsg = LangManager.getString(langNameLocal,"responseMsg","msgSendPermissionMsg");
                break;
            case ResponseCodeConst.FILE_SIZE_TOO_LARGE:/**文件过大,超过1M**/
                responseMsg = LangManager.getString(langNameLocal,"responseMsg","fileSizeTooLargeMsg");
                break;
            case ResponseCodeConst.FILE_FORMAT_NOT_MATCH:/**文件格式不符合**/
                responseMsg = LangManager.getString(langNameLocal,"responseMsg","fileFormatNotMatchMsg");
                break;



            case ResponseCodeConst.IS_FAIL:/**错误**/
                responseMsg = LangManager.getString(langNameLocal,"responseMsg","isFailMsg");
                break;
            case ResponseCodeConst.PARAMS_EXCEPTION:/**参数异常**/
                responseMsg = LangManager.getString(langNameLocal,"responseMsg","paramsExceptionMsg");
                break;
            case ResponseCodeConst.SIGN_ERROR:/**签名错误**/
                responseMsg = LangManager.getString(langNameLocal,"responseMsg","signErrorMsg");
                break;
            case ResponseCodeConst.DATA_CHECK_EXCEPTION:/**核对异常**/
                responseMsg = LangManager.getString(langNameLocal,"responseMsg","dataCheckExceptionMsg");
                break;
            case ResponseCodeConst.SYSTEM_EXCEPTION:/**系统错误**/
                responseMsg = LangManager.getString(langNameLocal,"responseMsg","systemExceptionMsg");
                break;
            default:
                throw new RuntimeException("responseCode does not exist!");
        }

        return responseMsg;
    }

}
