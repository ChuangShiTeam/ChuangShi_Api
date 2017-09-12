package com.nowui.chuangshi.api.exception.mobile;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;

@ControllerKey("/mobile/exception")
public class ExceptionController extends Controller {

    @ActionKey("/mobile/exception/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/exception/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/exception/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/exception/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/exception/delete")
    public void delete() {

        renderSuccessJson();
    }

}