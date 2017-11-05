package com.nowui.chuangshi.api.renault.system;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.common.interceptor.SystemInterceptor;

@Before(SystemInterceptor.class)
@ControllerKey("/system/renault/share/comment/praise")
public class RenaultShareCommentPraiseController extends Controller {

    @ActionKey("/system/renault/share/comment/praise/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/system/renault/share/comment/praise/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/system/renault/share/comment/praise/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/system/renault/share/comment/praise/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/system/renault/share/comment/praise/delete")
    public void delete() {

        renderSuccessJson();
    }

}