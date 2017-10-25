package com.nowui.chuangshi.api.menu.mobile;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;

@ControllerKey("/mobile/menu")
public class MenuController extends Controller {

    @ActionKey("/mobile/menu/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/menu/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/menu/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/menu/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/menu/delete")
    public void delete() {

        renderSuccessJson();
    }

}