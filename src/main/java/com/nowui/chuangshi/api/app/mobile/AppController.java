package com.nowui.chuangshi.api.app.mobile;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;

@ControllerKey("/mobile/app")
public class AppController extends Controller {

    @ActionKey("/mobile/app/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/app/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/app/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/app/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/app/delete")
    public void delete() {

        renderSuccessJson();
    }

}