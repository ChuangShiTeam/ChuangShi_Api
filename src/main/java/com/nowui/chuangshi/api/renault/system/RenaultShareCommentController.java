package com.nowui.chuangshi.api.renault.system;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.common.interceptor.SystemInterceptor;

@Before(SystemInterceptor.class)
@ControllerKey("/system/renault/share/comment")
public class RenaultShareCommentController extends Controller {

    @ActionKey("/system/renault/share/comment/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/system/renault/share/comment/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/system/renault/share/comment/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/system/renault/share/comment/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/system/renault/share/comment/delete")
    public void delete() {

        renderSuccessJson();
    }

}