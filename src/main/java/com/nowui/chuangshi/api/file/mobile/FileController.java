package com.nowui.chuangshi.api.file.mobile;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.core.ActionKey;
import com.jfinal.upload.UploadFile;
import com.nowui.chuangshi.api.file.model.File;
import com.nowui.chuangshi.api.file.service.FileService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.constant.Constant;

@ControllerKey("/mobile/file")
public class FileController extends Controller {

    @ActionKey("/mobile/file/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/file/find")
    public void find() {
        validateRequest(File.FILE_ID);
        
        File model = getModel(File.class);
        
        File file = FileService.instance.find(model.getFile_id());
        
        validateResponse(File.FILE_NAME, File.FILE_ORIGINAL_PATH, File.FILE_PATH);
        
        renderSuccessJson(file);
    }

    @ActionKey("/mobile/file/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/file/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/file/delete")
    public void delete() {

        renderSuccessJson();
    }
    
    @ActionKey("/mobile/file/upload")
    public void upload() {
        String request_app_id = getRequest_app_id();
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        List<UploadFile> uploadFileList = getFiles(request_user_id, 1024 * 1024 * 300);

        List<Map<String, Object>> resultList = FileService.instance.upload(uploadFileList, request_app_id, request_user_id);

        validateResponse(File.FILE_ID, File.FILE_NAME, File.FILE_PATH);
        
        renderSuccessJson(resultList);
    }
    
    @ActionKey("/mobile/file/image/base64/upload")
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