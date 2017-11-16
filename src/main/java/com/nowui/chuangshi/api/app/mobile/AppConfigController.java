package com.nowui.chuangshi.api.app.mobile;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;

@ControllerKey("/mobile/app/config")
public class AppConfigController extends Controller {

    @ActionKey("/mobile/app/config/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/app/config/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/app/config/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/app/config/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/app/config/delete")
    public void delete() {

        renderSuccessJson();
    }

}