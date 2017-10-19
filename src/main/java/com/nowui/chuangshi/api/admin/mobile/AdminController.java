package com.nowui.chuangshi.api.admin.mobile;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;

@ControllerKey("/mobile/admin")
public class AdminController extends Controller {

    @ActionKey("/mobile/admin/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/admin/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/admin/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/admin/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/admin/delete")
    public void delete() {

        renderSuccessJson();
    }

}