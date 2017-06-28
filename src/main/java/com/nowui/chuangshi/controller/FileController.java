package com.nowui.chuangshi.controller;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.constant.Url;
import com.nowui.chuangshi.model.File;
import com.nowui.chuangshi.service.FileService;
import com.nowui.chuangshi.util.Util;

import java.util.List;

public class FileController extends Controller {

    private final FileService fileService = new FileService();

    @ActionKey(Url.FILE_LIST)
    public void list() {
        validateRequest_app_id();
        validate(Constant.PAGE_SIZE, Constant.FIRST_CREATE_TIME, Constant.LAST_CREATE_TIME);

        String request_app_id = getRequest_app_id();
        String request_http_id = getRequest_http_id();
        String request_user_id = getRequest_user_id();
        JSONObject jsonObject = getParameterJSONObject();

        List<File> resultList = fileService.listByApp_idAndSystem_create_timeAndLimit(request_app_id, jsonObject.getDate(Constant.LAST_CREATE_TIME), 0, getN(), request_app_id, request_http_id, request_user_id);

        for (File result : resultList) {
            result.keep(File.FILE_ID, File.SYSTEM_VERSION);
        }

        renderSuccessJson(resultList);
    }

    @ActionKey(Url.FILE_FIND)
    public void find() {
        validateRequest_app_id();
        validate(File.FILE_ID);

        File model = getModel(File.class);
        String request_app_id = getRequest_app_id();
        String request_http_id = getRequest_http_id();
        String request_user_id = getRequest_user_id();

        File file = fileService.findByFile_id(model.getFile_id(), request_app_id, request_http_id, request_user_id);

        authenticateRequest_app_idAndRequest_user_id();
        authenticateApp_id(file.getApp_id());
        authenticateSystem_create_user_id(file.getSystem_create_user_id());

        file.keep(File.FILE_ID, File.SYSTEM_VERSION);

        renderSuccessJson(file);
    }

    @ActionKey(Url.FILE_SAVE)
    public void save() {
        validateRequest_app_id();
        validate(File.FILE_TYPE, File.FILE_NAME, File.FILE_SUFFIX, File.FILE_SIZE, File.FILE_PATH, File.FILE_THUMBNAIL_PATH, File.FILE_ORIGINAL_PATH, File.FILE_IMAGE);

        authenticateRequest_app_idAndRequest_user_id();

        File model = getModel(File.class);
        String file_id = Util.getRandomUUID();
        String request_app_id = getRequest_app_id();
        String request_http_id = getRequest_http_id();
        String request_user_id = getRequest_user_id();

        Boolean result = fileService.save(file_id, request_app_id, model.getFile_type(), model.getFile_name(), model.getFile_suffix(), model.getFile_size(), model.getFile_path(), model.getFile_thumbnail_path(), model.getFile_original_path(), model.getFile_image(), request_user_id, request_app_id, request_http_id, request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey(Url.FILE_UPDATE)
    public void update() {
        validateRequest_app_id();
        validate(File.FILE_ID, File.FILE_TYPE, File.FILE_NAME, File.FILE_SUFFIX, File.FILE_SIZE, File.FILE_PATH, File.FILE_THUMBNAIL_PATH, File.FILE_ORIGINAL_PATH, File.FILE_IMAGE, File.SYSTEM_VERSION);

        File model = getModel(File.class);
        String request_app_id = getRequest_app_id();
        String request_http_id = getRequest_http_id();
        String request_user_id = getRequest_user_id();

        File file = fileService.findByFile_id(model.getFile_id(), request_app_id, request_http_id, request_user_id);
        authenticateRequest_app_idAndRequest_user_id();
        authenticateApp_id(file.getApp_id());
        authenticateSystem_create_user_id(file.getSystem_create_user_id());

        Boolean result = fileService.updateValidateSystem_version(model.getFile_id(), model.getFile_type(), model.getFile_name(), model.getFile_suffix(), model.getFile_size(), model.getFile_path(), model.getFile_thumbnail_path(), model.getFile_original_path(), model.getFile_image(), request_user_id, model.getSystem_version(), request_app_id, request_http_id, request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey(Url.FILE_DELETE)
    public void delete() {
        validateRequest_app_id();
        validate(File.FILE_ID, File.SYSTEM_VERSION);

        File model = getModel(File.class);
        String request_app_id = getRequest_app_id();
        String request_http_id = getRequest_http_id();
        String request_user_id = getRequest_user_id();

        File file = fileService.findByFile_id(model.getFile_id(), request_app_id, request_http_id, request_user_id);
        authenticateApp_id(file.getApp_id());
        authenticateSystem_create_user_id(file.getSystem_create_user_id());

        Boolean result = fileService.deleteByFile_idAndSystem_update_user_idValidateSystem_version(model.getFile_id(), request_user_id, model.getSystem_version(), request_app_id, request_http_id, request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey(Url.FILE_ADMIN_LIST)
    public void adminList() {
        validateRequest_app_id();
        validate(Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        String request_app_id = getRequest_app_id();
        String request_http_id = getRequest_http_id();
        String request_user_id = getRequest_user_id();

        Integer total = fileService.countByApp_id(request_app_id, request_app_id, request_http_id, request_user_id);
        List<File> resultList = fileService.listByApp_idAndLimit(request_app_id, getM(), getN(), request_app_id, request_http_id, request_user_id);

        for (File result : resultList) {
            result.keep(File.FILE_ID, File.SYSTEM_VERSION);
        }

        renderSuccessJson(total, resultList);
    }

    @ActionKey(Url.FILE_ADMIN_FIND)
    public void adminFind() {
        validateRequest_app_id();
        validate(File.FILE_ID);

        File model = getModel(File.class);
        String request_app_id = getRequest_app_id();
        String request_http_id = getRequest_http_id();
        String request_user_id = getRequest_user_id();

        File file = fileService.findByFile_id(model.getFile_id(), request_app_id, request_http_id, request_user_id);

        authenticateRequest_app_idAndRequest_user_id();
        authenticateApp_id(file.getApp_id());

        file.keep(File.FILE_ID, File.SYSTEM_VERSION);

        renderSuccessJson(file);
    }

    @ActionKey(Url.FILE_ADMIN_SAVE)
    public void adminSave() {
        save();
    }

    @ActionKey(Url.FILE_ADMIN_UPDATE)
    public void adminUpdate() {
        validateRequest_app_id();
        validate(File.FILE_ID, File.FILE_TYPE, File.FILE_NAME, File.FILE_SUFFIX, File.FILE_SIZE, File.FILE_PATH, File.FILE_THUMBNAIL_PATH, File.FILE_ORIGINAL_PATH, File.FILE_IMAGE, File.SYSTEM_VERSION);

        File model = getModel(File.class);
        String request_app_id = getRequest_app_id();
        String request_http_id = getRequest_http_id();
        String request_user_id = getRequest_user_id();

        File file = fileService.findByFile_id(model.getFile_id(), request_app_id, request_http_id, request_user_id);
        authenticateRequest_app_idAndRequest_user_id();
        authenticateApp_id(file.getApp_id());

        Boolean result = fileService.updateValidateSystem_version(model.getFile_id(), model.getFile_type(), model.getFile_name(), model.getFile_suffix(), model.getFile_size(), model.getFile_path(), model.getFile_thumbnail_path(), model.getFile_original_path(), model.getFile_image(), request_user_id, model.getSystem_version(), request_app_id, request_http_id, request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey(Url.FILE_ADMIN_DELETE)
    public void adminDelete() {
        validateRequest_app_id();
        validate(File.FILE_ID, File.SYSTEM_VERSION);

        File model = getModel(File.class);
        String request_app_id = getRequest_app_id();
        String request_http_id = getRequest_http_id();
        String request_user_id = getRequest_user_id();

        File file = fileService.findByFile_id(model.getFile_id(), request_app_id, request_http_id, request_user_id);
        authenticateApp_id(file.getApp_id());

        Boolean result = fileService.deleteByFile_idAndSystem_update_user_idValidateSystem_version(model.getFile_id(), request_user_id, model.getSystem_version(), request_app_id, request_http_id, request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey(Url.FILE_SYSTEM_LIST)
    public void systemList() {
        validateRequest_app_id();
        validate(File.APP_ID, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        File model = getModel(File.class);
        String request_app_id = getRequest_app_id();
        String request_http_id = getRequest_http_id();
        String request_user_id = getRequest_user_id();

        Integer total = fileService.countByOrApp_id(model.getApp_id(), request_app_id, request_http_id, request_user_id);
        List<File> resultList = fileService.listByOrApp_idAndLimit(model.getApp_id(), getM(), getN(), request_app_id, request_http_id, request_user_id);

        for (File result : resultList) {
            result.keep(File.FILE_ID, File.SYSTEM_VERSION);
        }

        renderSuccessJson(total, resultList);
    }

    @ActionKey(Url.FILE_SYSTEM_FIND)
    public void systemFind() {
        validateRequest_app_id();
        validate(File.FILE_ID);

        File model = getModel(File.class);
        String request_app_id = getRequest_app_id();
        String request_http_id = getRequest_http_id();
        String request_user_id = getRequest_user_id();

        File file = fileService.findByFile_id(model.getFile_id(), request_app_id, request_http_id, request_user_id);

        file.keep(File.FILE_ID, File.SYSTEM_VERSION);

        renderSuccessJson(file);
    }

    @ActionKey(Url.FILE_SYSTEM_SAVE)
    public void systemSave() {
        validateRequest_app_id();
        validate(File.APP_ID, File.FILE_TYPE, File.FILE_NAME, File.FILE_SUFFIX, File.FILE_SIZE, File.FILE_PATH, File.FILE_THUMBNAIL_PATH, File.FILE_ORIGINAL_PATH, File.FILE_IMAGE);

        authenticateRequest_app_idAndRequest_user_id();

        File model = getModel(File.class);
        String file_id = Util.getRandomUUID();
        String request_app_id = getRequest_app_id();
        String request_http_id = getRequest_http_id();
        String request_user_id = getRequest_user_id();

        Boolean result = fileService.save(file_id, model.getApp_id(), model.getFile_type(), model.getFile_name(), model.getFile_suffix(), model.getFile_size(), model.getFile_path(), model.getFile_thumbnail_path(), model.getFile_original_path(), model.getFile_image(), request_user_id, request_app_id, request_http_id, request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey(Url.FILE_SYSTEM_UPDATE)
    public void systemUpdate() {
        validateRequest_app_id();
        validate(File.FILE_ID, File.FILE_TYPE, File.FILE_NAME, File.FILE_SUFFIX, File.FILE_SIZE, File.FILE_PATH, File.FILE_THUMBNAIL_PATH, File.FILE_ORIGINAL_PATH, File.FILE_IMAGE, File.SYSTEM_VERSION);

        File model = getModel(File.class);
        String request_app_id = getRequest_app_id();
        String request_http_id = getRequest_http_id();
        String request_user_id = getRequest_user_id();

        Boolean result = fileService.updateValidateSystem_version(model.getFile_id(), model.getFile_type(), model.getFile_name(), model.getFile_suffix(), model.getFile_size(), model.getFile_path(), model.getFile_thumbnail_path(), model.getFile_original_path(), model.getFile_image(), request_user_id, model.getSystem_version(), request_app_id, request_http_id, request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey(Url.FILE_SYSTEM_DELETE)
    public void systemDelete() {
        validateRequest_app_id();
        validate(File.FILE_ID, File.SYSTEM_VERSION);

        File model = getModel(File.class);
        String request_app_id = getRequest_app_id();
        String request_http_id = getRequest_http_id();
        String request_user_id = getRequest_user_id();

        Boolean result = fileService.deleteByFile_idAndSystem_update_user_idValidateSystem_version(model.getFile_id(), request_user_id, model.getSystem_version(), request_app_id, request_http_id, request_user_id);

        renderSuccessJson(result);
    }

}