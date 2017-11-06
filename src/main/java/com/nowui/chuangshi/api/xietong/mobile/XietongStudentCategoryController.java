package com.nowui.chuangshi.api.xietong.mobile;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;

@ControllerKey("/mobile/xietong/student/category")
public class XietongStudentCategoryController extends Controller {

    @ActionKey("/mobile/xietong/student/category/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/xietong/student/category/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/xietong/student/category/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/xietong/student/category/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/xietong/student/category/delete")
    public void delete() {

        renderSuccessJson();
    }

}