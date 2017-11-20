package com.nowui.chuangshi.api.user.mobile;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;

@ControllerKey("/mobile/user/role")
public class UserRoleController extends Controller {

    @ActionKey("/mobile/user/role/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/user/role/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/user/role/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/user/role/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/user/role/delete")
    public void delete() {

        renderSuccessJson();
    }

}