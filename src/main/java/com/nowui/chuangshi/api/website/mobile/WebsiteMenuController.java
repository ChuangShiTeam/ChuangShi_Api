package com.nowui.chuangshi.api.website.mobile;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;

@ControllerKey("/mobile/website/menu")
public class WebsiteMenuController extends Controller {

    @ActionKey("/mobile/website/menu/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/website/menu/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/website/menu/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/website/menu/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/website/menu/delete")
    public void delete() {

        renderSuccessJson();
    }

}