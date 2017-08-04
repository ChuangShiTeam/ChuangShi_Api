package com.nowui.chuangshi.api.file.mobile;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.file.service.FileService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;

@ControllerKey("/mobile/file")
public class FileController extends Controller {

    private final FileService fileService = new FileService();

    @ActionKey("/mobile/file/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/file/find")
    public void find() {

        renderSuccessJson();
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

}