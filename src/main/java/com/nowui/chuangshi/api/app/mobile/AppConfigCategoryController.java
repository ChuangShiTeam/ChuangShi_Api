package com.nowui.chuangshi.api.app.mobile;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;

@ControllerKey("/mobile/app/config/category")
public class AppConfigCategoryController extends Controller {

    @ActionKey("/mobile/app/config/category/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/app/config/category/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/app/config/category/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/app/config/category/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/app/config/category/delete")
    public void delete() {

        renderSuccessJson();
    }

}