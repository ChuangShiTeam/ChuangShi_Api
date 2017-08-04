package com.nowui.chuangshi.api.file.system;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.file.service.FileService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.common.interceptor.SystemInterceptor;

@Before(SystemInterceptor.class)
@ControllerKey("/system/file")
public class FileController extends Controller {

    private final FileService fileService = new FileService();

    @ActionKey("/system/file/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/system/file/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/system/file/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/system/file/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/system/file/delete")
    public void delete() {

        renderSuccessJson();
    }

}