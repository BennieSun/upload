package com.mg.upload.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mg.upload.constants.ResponseCodeConst;
import com.mg.upload.constants.ResponseMsgConst;
import com.mg.upload.constants.ServerConst;
import com.mg.util.core.GlobalHelper;
import com.mg.util.core.message.ParamLogMessage;
import com.mg.util.core.message.ServerConfig;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

import static io.netty.handler.codec.http.HttpHeaders.Names.ACCESS_CONTROL_ALLOW_ORIGIN;
import static io.netty.handler.codec.http.HttpHeaders.Names.CONTENT_TYPE;

/**
 * Created by BennieSun on 2017/4/8.
 *
 * 上传
 */
@Controller
@RequestMapping(method = { RequestMethod.GET, RequestMethod.POST }, value = { "/*", "/upload/*" })
public class UploadController {

    private static Logger logger = Logger.getLogger(UploadController.class);

    /**
     * 图片上传（支持跨域）
     * @param httpServletRequest
     * @param httpServletResponse
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/upload_img", "/upload_img.web"}, method = {RequestMethod.GET, RequestMethod.POST})
    //@ResponseBody
    public void imgUpload(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception{

        ParamLogMessage plm = GlobalHelper.RequestParameterHelper.loadRequestMessage(httpServletRequest, true, true);
        logger.info(plm.print());

        Map<String,String> initMap = plm.getParamsMap();


        httpServletRequest.setCharacterEncoding("UTF-8");
        httpServletResponse.setCharacterEncoding("UTF-8");

        DiskFileItemFactory fac = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(fac);
        upload.setHeaderEncoding("UTF-8");
        List fileList = null;
        try {
            fileList = upload.parseRequest(httpServletRequest);
        } catch (FileUploadException ex) {
            ex.printStackTrace();
            return;
        }
        if(fileList == null){
            return;
        }
        Iterator<FileItem> itemIsFormField = fileList.iterator();

        String userId = "";
        String gameCode = "";
        String gameLanguage = "";
        String redirect = "";
        while (itemIsFormField.hasNext()) {
            FileItem item = itemIsFormField.next();
            if (item.isFormField()) {
                if ("userId".equals(item.getFieldName())){
                    userId = item.getString();
                }else if("gameCode".equals(item.getFieldName())){
                    gameCode = item.getString();
                }else if("gameLanguage".equals(item.getFieldName())){
                    gameLanguage = item.getString();//提示语言信息
                }else if("redirect".equals(item.getFieldName())){
                    redirect = item.getString();
                }
            }
        }

        /*if (StringUtils.isEmpty(gameCode)){
            if (gameCode.endsWith("ios")){
                gameCode = gameCode.substring(0,gameCode.lastIndexOf("ios"));
            }
        }*/

        JSONObject jsonObject = new JSONObject();
        String langName = ServerConfig.getInstance().getLang();
        if (StringUtils.isNotEmpty(gameLanguage)) {
            langName = gameLanguage;
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        String dirDate = sdf.format(new Date());
        String dir = dirDate+"/"+ gameCode;//按日期生成存放目录
        //String savePath = getClass().getResource("/").getPath()+"/"+dir;
        String savePath =  httpServletRequest.getSession().getServletContext().getRealPath("/WEB-INF/upload/"+dir) ;//"WEB-INF/"+dir;
        File fileRoot = new File(savePath);
        if (!fileRoot.exists()) {
            fileRoot.mkdirs();
        }
        fileRoot = null;

        String name = "";
        Long size = null;
        String extName = "";
        JSONArray jsonArray = new JSONArray();
        Iterator<FileItem> itemIsNotFormField = fileList.iterator();
        while (itemIsNotFormField.hasNext()) {
            FileItem item = itemIsNotFormField.next();
            if (!item.isFormField()) {
                name = item.getName();
                size = item.getSize();
                if (name == null || name.trim().equals("") || null == size) {
                    JSONObject jsonObjectException = new JSONObject();
                    jsonObjectException.put("name",name);
                    jsonObjectException.put("code", ResponseCodeConst.PARAMS_EXCEPTION);
                    jsonObjectException.put("message", ResponseMsgConst.getInstance(langName).getResponseMsg(ResponseCodeConst.PARAMS_EXCEPTION));
                    jsonArray.add(jsonObjectException);
                    continue;
                }

                if (size.longValue() > ServerConst.IMG_UPLOAD_SIZE){
                    JSONObject jsonObjectSize = new JSONObject();
                    jsonObjectSize.put("name",name);
                    jsonObjectSize.put("code", ResponseCodeConst.FILE_SIZE_TOO_LARGE);
                    jsonObjectSize.put("message", ResponseMsgConst.getInstance(langName).getResponseMsg(ResponseCodeConst.FILE_SIZE_TOO_LARGE)+(size.longValue()/1000)+"KB");
                    jsonArray.add(jsonObjectSize);
                    continue;
                }

                if (!name.matches(".+(.JPEG|.jpeg|.JPG|.jpg|.GIF|.gif|.BMP|.bmp|.PNG|.png)$")){
                    JSONObject jsonObjectMatch = new JSONObject();
                    jsonObjectMatch.put("name",name);
                    jsonObjectMatch.put("code", ResponseCodeConst.FILE_FORMAT_NOT_MATCH);
                    jsonObjectMatch.put("message", ResponseMsgConst.getInstance(langName).getResponseMsg(ResponseCodeConst.FILE_FORMAT_NOT_MATCH));
                    jsonArray.add(jsonObjectMatch);
                    continue ;
                }else{
                    logger.info("匹配 OK"+name);
                }

                //文件后缀名 如：.jpg
                if (name.lastIndexOf(".") >= 0) {
                    extName = name.substring(name.lastIndexOf("."));
                }
                //\s去除任何空空白符如：  空格符、制表符和进纸符等
                /*String fileName = name.replaceAll("\\s", "");

                fileName = fileName.substring(0, fileName.lastIndexOf(".") - 1);*/

                String fileName = userId+"_"+String.valueOf(UUID.randomUUID()).replace("-","");

                // 仅仅是为了判断当前命名的文件是否已存在
                File f = new File(savePath + "/" + fileName + extName);

                while (f.exists()) {//如果文件存在，在文件名后加上随机数做区分
					/* 日期+100以内的随机数 */
                    int rand = (int) Math.round(Math.random() * 1000);
                    sdf = new SimpleDateFormat("HHmmss");

                    fileName += "_" + sdf.format(new Date()) + rand;
                    f = new File(savePath + "/" + fileName + extName);
                }
                f = null;

                File saveFile = new File(savePath + "/" + fileName
                        + extName);
                try {
                    item.write(saveFile);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                logger.info(dir + "/" + fileName + extName);//返回相对根路径：http://xx.xx.com/upload/songs/2010/06/05/ring.mp3

				/*
				 * 注：插件需要服务器端返回JSON格式的字符串，且必须以下面的格式来返回，一个字段都不能少
				 *     如果上传失败，那么则须用特定格式返回信息：
				 *      "name": "picture1.jpg",
    			 *		"size": 902604,
    			 *		"error": "Filetype not allowed"
    			 *	   另外，files必须为一个JSON数组，哪怕上传的是一个文件
				 */

                JSONObject json = new JSONObject();
                json.put("code",ResponseCodeConst.IS_SUCCESS);
                json.put("message", ResponseMsgConst.getInstance(langName).getResponseMsg(ResponseCodeConst.IS_SUCCESS));
                json.put("name", fileName);
                json.put("size", size);
                json.put("url", ServerConst.IMG_DOWN_URL+"/" + dir + "/" + saveFile.getName());
                jsonArray.add(json);
            }
        }

        JSONObject js = new JSONObject();
        js.put("files", jsonArray);



        httpServletResponse.setContentType("text/plain;charset=UTF-8");
        httpServletResponse.setHeader(CONTENT_TYPE, "text/html; charset=UTF-8");
        httpServletResponse.setHeader(ACCESS_CONTROL_ALLOW_ORIGIN, "*");
        httpServletResponse.setHeader("Location", redirect + "?" + js.toString());
        httpServletResponse.sendRedirect(redirect + "?" + js.toString());
    }

    /**
     * 图片上传
     * @param httpServletRequest
     * @param httpServletResponse
     * @return
     * @throws Exception
     */
//    @RequestMapping(value = {"/player_imgUpload", "/player_imgUpload.web"}, method = {RequestMethod.GET, RequestMethod.POST})
//    @ResponseBody
//    public String imgUpload(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception{
//        ParamLogMessage plm = GlobalHelper.RequestParameterHelper.loadRequestMessage(httpServletRequest, true, true);
//        logger.info(plm.print());
//
//        Map<String,String> initMap = plm.getParamsMap();
//
//
//        httpServletRequest.setCharacterEncoding("UTF-8");
//        httpServletResponse.setCharacterEncoding("UTF-8");
//
//        DiskFileItemFactory fac = new DiskFileItemFactory();
//        ServletFileUpload upload = new ServletFileUpload(fac);
//        upload.setHeaderEncoding("UTF-8");
//        List fileList = null;
//        try {
//            fileList = upload.parseRequest(httpServletRequest);
//        } catch (FileUploadException ex) {
//            ex.printStackTrace();
//            return null;
//        }
//        if(fileList == null){
//            return null;
//        }
//        Iterator<FileItem> itemIsFormField = fileList.iterator();
//
//        String userId = "";
//        String gameCode = "";
//        String gameLanguage = "";
//        while (itemIsFormField.hasNext()) {
//            FileItem item = itemIsFormField.next();
//            if (item.isFormField()) {
//                if ("userId".equals(item.getFieldName())){
//                    userId = item.getString();
//                }else if("gameCode".equals(item.getFieldName())){
//                    gameCode = item.getString();
//                }else if("gameLanguage".equals(item.getFieldName())){
//                    gameLanguage = item.getString();//提示语言信息
//                }
//            }
//        }
//
//        /*if (StringUtils.isEmpty(gameCode)){
//            if (gameCode.endsWith("ios")){
//                gameCode = gameCode.substring(0,gameCode.lastIndexOf("ios"));
//            }
//        }*/
//
//        JSONObject jsonObject = new JSONObject();
//        String langName = ServerConfig.getInstance().getLang();
//        if (StringUtils.isNotEmpty(gameLanguage)) {
//            langName = gameLanguage;
//        }
//
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//
//        String dirDate = sdf.format(new Date());
//        String dir = "upload/" + dirDate+"/"+ gameCode;//按日期生成存放目录
//        //String savePath = getClass().getResource("/").getPath()+"/"+dir;
//        String savePath = "WEB-INF/"+dir;
//        File fileRoot = new File(savePath);
//        if (!fileRoot.exists()) {
//            fileRoot.mkdirs();
//        }
//        fileRoot = null;
//
//        String name = "";
//        Long size = null;
//        String extName = "";
//        JSONArray jsonArray = new JSONArray();
//        Iterator<FileItem> itemIsNotFormField = fileList.iterator();
//        while (itemIsNotFormField.hasNext()) {
//            FileItem item = itemIsNotFormField.next();
//            if (!item.isFormField()) {
//                name = item.getName();
//                size = item.getSize();
//                if (name == null || name.trim().equals("")) {
//                    JSONObject jsonObjectException = new JSONObject();
//                    jsonObjectException.put("name",name);
//                    jsonObjectException.put("code", ResponseCodeConst.PARAMS_EXCEPTION);
//                    jsonObjectException.put("message", ResponseMsgConst.getInstance(langName).getResponseMsg(ResponseCodeConst.PARAMS_EXCEPTION));
//                    jsonArray.add(jsonObjectException);
//                    continue;
//                }
//
//                if (null == size || size.longValue() > 1024000){
//                    JSONObject jsonObjectSize = new JSONObject();
//                    jsonObjectSize.put("name",name);
//                    jsonObjectSize.put("code", ResponseCodeConst.FILE_SIZE_TOO_LARGE);
//                    jsonObjectSize.put("message", ResponseMsgConst.getInstance(langName).getResponseMsg(ResponseCodeConst.FILE_SIZE_TOO_LARGE));
//                    jsonArray.add(jsonObjectSize);
//                    continue;
//                }
//
//                if (!name.matches(".+(.JPEG|.jpeg|.JPG|.jpg|.GIF|.gif|.BMP|.bmp|.PNG|.png)$")){
//                    JSONObject jsonObjectMatch = new JSONObject();
//                    jsonObjectMatch.put("name",name);
//                    jsonObjectMatch.put("code", ResponseCodeConst.FILE_FORMAT_NOT_MATCH);
//                    jsonObjectMatch.put("message", ResponseMsgConst.getInstance(langName).getResponseMsg(ResponseCodeConst.FILE_FORMAT_NOT_MATCH));
//                    return jsonObjectMatch.toJSONString();
//                }else{
//                    logger.info("匹配 OK"+name);
//                }
//
//                //文件后缀名 如：.jpg
//                if (name.lastIndexOf(".") >= 0) {
//                    extName = name.substring(name.lastIndexOf("."));
//                }
//                //\s去除任何空空白符如：  空格符、制表符和进纸符等
//                /*String fileName = name.replaceAll("\\s", "");
//
//                fileName = fileName.substring(0, fileName.lastIndexOf(".") - 1);*/
//
//                String fileName = userId+"_"+String.valueOf(UUID.randomUUID()).replace("-","");
//
//                // 仅仅是为了判断当前命名的文件是否已存在
//                File f = new File(savePath + "/" + fileName + extName);
//
//                while (f.exists()) {//如果文件存在，在文件名后加上随机数做区分
//					/* 日期+100以内的随机数 */
//                    int rand = (int) Math.round(Math.random() * 1000);
//                    sdf = new SimpleDateFormat("HHmmss");
//
//                    fileName += "_" + sdf.format(new Date()) + rand;
//                    f = new File(savePath + "/" + fileName + extName);
//                }
//                f = null;
//
//                File saveFile = new File(savePath + "/" + fileName
//                        + extName);
//                try {
//                    item.write(saveFile);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//                logger.info(dir + "/" + fileName + extName);//返回相对根路径：http://xx.xx.com/upload/songs/2010/06/05/ring.mp3
//
//				/*
//				 * 注：插件需要服务器端返回JSON格式的字符串，且必须以下面的格式来返回，一个字段都不能少
//				 *     如果上传失败，那么则须用特定格式返回信息：
//				 *      "name": "picture1.jpg",
//    					"size": 902604,
//    					"error": "Filetype not allowed"
//    				   另外，files必须为一个JSON数组，哪怕上传的是一个文件
//				 */
//
//                JSONObject json = new JSONObject();
//                json.put("code",ResponseCodeConst.IS_SUCCESS);
//                json.put("message", ResponseMsgConst.getInstance(langName).getResponseMsg(ResponseCodeConst.IS_SUCCESS));
//                json.put("name", fileName);
//                json.put("size", size);
//                json.put("url", "http://localhost:8086/upload/" + dirDate + "/" + saveFile.getName());
//                /*json.put("thumbnailUrl", "http://localhost:8086/upload/" + dirDate + "/" + "thumbnail/" + saveFile.getName());
//                json.put("deleteUrl", "http://localhost:8086/upload/" + dirDate + "/" + saveFile.getName());
//                json.put("deleteType", "DELETE");*/
//                jsonArray.add(json);
//            }
//        }
//
//        JSONObject js = new JSONObject();
//        js.put("files", jsonArray);
//
//        return js.toString();
//    }
}
