package com.nowui.chuangshi.api.file.desktop;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.core.ActionKey;
import com.jfinal.upload.UploadFile;
import com.nowui.chuangshi.api.file.model.File;
import com.nowui.chuangshi.api.file.service.FileService;
import com.nowui.chuangshi.api.user.model.User;
import com.nowui.chuangshi.api.user.service.UserService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.constant.Constant;

@ControllerKey("/desktop/file")
public class FileController extends Controller {

    @ActionKey("/desktop/file/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/desktop/file/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/desktop/file/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/desktop/file/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/desktop/file/delete")
    public void delete() {

        renderSuccessJson();
    }
    
    /**
     * 应用下开放的公共上传接口不需要登录即可上传
     */
    @ActionKey("/desktop/file/app/upload")
    public void appUpload() {
        String request_app_id = getRequest_app_id();
        //查询应用下的系统管理员用户，上传的文件归于系统管理员下
        User user = UserService.instance.appAndAdminFind(request_app_id);
        
        if (user == null) {
            throw new RuntimeException("上传失败");
        }

        List<UploadFile> uploadFileList = getFiles(request_app_id, 1024 * 1024 * 300);

        List<Map<String, Object>> resultList = FileService.instance.upload(uploadFileList, request_app_id, user.getUser_id());

        validateResponse(File.FILE_ID, File.FILE_NAME, File.FILE_PATH);
        
        renderSuccessJson(resultList);
    }
    
    /**
     * 用户文件上传接口
     */
    @ActionKey("/desktop/file/upload")
    public void upload() {
        String request_app_id = getRequest_app_id();
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        List<UploadFile> uploadFileList = getFiles(request_user_id, 1024 * 1024 * 300);

        List<Map<String, Object>> resultList = FileService.instance.upload(uploadFileList, request_app_id, request_user_id);

        validateResponse(File.FILE_ID, File.FILE_NAME, File.FILE_PATH);
        
        renderSuccessJson(resultList);
    }
    
    /**
     * 用户图片base64上传
     */
    @ActionKey("/desktop/file/image/base64/upload")
    public void imageBase64Upload() {
        String request_app_id = getRequest_app_id();
        String request_user_id = getRequest_user_id();
        JSONObject jsonObject = getParameterJSONObject();

        String dataString = jsonObject.getString(Constant.DATA);

        Map<String, Object> result = FileService.instance.uploadBase64(dataString, request_app_id, request_user_id);

        validateResponse(File.FILE_ID, File.FILE_NAME, File.FILE_PATH);
        renderSuccessJson(result);
    }


}