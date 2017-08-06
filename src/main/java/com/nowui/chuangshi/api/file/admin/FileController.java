package com.nowui.chuangshi.api.file.admin;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.common.interceptor.AdminInterceptor;

@Before(AdminInterceptor.class)
@ControllerKey("/admin/file")
public class FileController extends Controller {


    @ActionKey("/admin/file/list")
    public void list() {


        renderSuccessJson();
    }

    @ActionKey("/admin/file/find")
    public void find() {


        renderSuccessJson();
    }

    @ActionKey("/admin/file/save")
    public void save() {


        renderSuccessJson();
    }

    @ActionKey("/admin/file/update")
    public void update() {


        renderSuccessJson();
    }

    @ActionKey("/admin/file/delete")
    public void delete() {


        renderSuccessJson();
    }

}