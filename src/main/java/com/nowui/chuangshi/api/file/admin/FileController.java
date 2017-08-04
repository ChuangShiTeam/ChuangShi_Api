package com.nowui.chuangshi.api.file.admin;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.file.model.File;
import com.nowui.chuangshi.api.file.service.FileService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.common.interceptor.AdminInterceptor;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.util.Util;

import java.util.List;

@Before(AdminInterceptor.class)
@ControllerKey("/admin/file")
public class FileController extends Controller {

    private final FileService fileService = FileService.me;

    @ActionKey("/admin/file/list")
    public void list() {
        validateRequest(File.FILE_TYPE, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        File model = getModel(File.class);
        model.where(File.APP_ID).andEmpty(File.FILE_TYPE);

        Integer resultCount = fileService.count(model);
        List<File> resultList = fileService.list(model.paginate());

        validateResponse(File.FILE_ID, File.FILE_TYPE, File.FILE_NAME, File.FILE_SUFFIX, File.FILE_SIZE, File.SYSTEM_VERSION);

        renderSuccessModeListlJson(resultCount, resultList);
    }

    @ActionKey("/admin/file/find")
    public void find() {
        validateRequest(File.FILE_ID);

        File model = getModel(File.class);
        model.where(File.FILE_ID);

        File result = fileService.find(model);

        validateResponse(File.FILE_TYPE, File.FILE_NAME, File.FILE_SUFFIX, File.FILE_SIZE, File.FILE_PATH, File.FILE_THUMBNAIL_PATH, File.FILE_ORIGINAL_PATH, File.FILE_IMAGE, File.FILE_IS_EXTERNAL, File.SYSTEM_VERSION);

        renderSuccessModelJson(result);
    }

    @ActionKey("/admin/file/save")
    public void save() {
        validateRequest(File.FILE_TYPE, File.FILE_NAME, File.FILE_SUFFIX, File.FILE_SIZE, File.FILE_PATH, File.FILE_THUMBNAIL_PATH, File.FILE_ORIGINAL_PATH, File.FILE_IMAGE, File.FILE_IS_EXTERNAL);

        File model = getModel(File.class);
        model.setFile_id(Util.getRandomUUID());

        Boolean result = fileService.save(model);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/file/update")
    public void update() {
        validateRequest(File.FILE_ID, File.FILE_TYPE, File.FILE_NAME, File.FILE_SUFFIX, File.FILE_SIZE, File.FILE_PATH, File.FILE_THUMBNAIL_PATH, File.FILE_ORIGINAL_PATH, File.FILE_IMAGE, File.FILE_IS_EXTERNAL, File.SYSTEM_VERSION);

        File model = getModel(File.class);
        model.where(model.FILE_ID).and(File.SYSTEM_VERSION);

        Boolean result = fileService.update(model);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/file/delete")
    public void delete() {
        validateRequest(File.FILE_ID, File.SYSTEM_VERSION);

        File model = getModel(File.class);
        model.where(model.FILE_ID).and(File.SYSTEM_VERSION);

        Boolean result = fileService.delete(model);

        renderSuccessJson(result);
    }

}