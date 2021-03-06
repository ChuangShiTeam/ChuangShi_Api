package com.nowui.chuangshi.api.file.admin;

import com.alibaba.fastjson.JSONObject;

import com.jfinal.core.ActionKey;
import com.jfinal.upload.UploadFile;
import com.nowui.chuangshi.api.file.model.File;
import com.nowui.chuangshi.api.file.service.FileService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;

import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.type.FileType;

import java.util.List;
import java.util.Map;


@ControllerKey("/admin/file")
public class FileController extends Controller {


    @ActionKey("/admin/file/image/list")
    public void list() {
        validateRequest(Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        String request_app_id = getRequest_app_id();

        Integer resultCount = FileService.instance.fileTypeCount(request_app_id, FileType.IMAGE.getKey(), false);
        List<File> resultList = FileService.instance.fileTypeList(request_app_id, FileType.IMAGE.getKey(), false, getM(), getN());

        validateResponse(File.FILE_ID, File.FILE_PATH, File.SYSTEM_VERSION);

        renderSuccessJson(resultCount, resultList);
    }

    @ActionKey("/admin/file/find")
    public void find() {


        renderSuccessJson();
    }

    @ActionKey("/admin/file/save")
    public void save() {


        renderSuccessJson();
    }

    @ActionKey("/admin/file/update")
    public void update() {


        renderSuccessJson();
    }

    @ActionKey("/admin/file/delete")
    public void delete() {


        renderSuccessJson();
    }

    @ActionKey("/admin/file/upload")
    public void upload() {
        String request_app_id = getRequest_app_id();
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        List<UploadFile> uploadFileList = getFiles(request_user_id, 1024 * 1024 * 300);

        List<Map<String, Object>> resultList = FileService.instance.upload(uploadFileList, request_app_id, request_user_id);

        validateResponse(File.FILE_ID, File.FILE_NAME, File.FILE_PATH);
        
        renderSuccessJson(resultList);
    }

    @ActionKey("/admin/file/image/base64/upload")
    public void imageBase64Upload() {
        String request_app_id = getRequest_app_id();
        String request_user_id = getRequest_user_id();
        JSONObject jsonObject = getParameterJSONObject();

        authenticateRequest_app_idAndRequest_user_id();

        String dataString = jsonObject.getString(Constant.DATA);

        Map<String, Object> result = FileService.instance.uploadBase64(dataString, request_app_id, request_user_id);

        validateResponse(File.FILE_ID, File.FILE_NAME, File.FILE_PATH);
        renderSuccessJson(result);
    }


}