package com.nowui.chuangshi.api.xietong.mobile;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;

@ControllerKey("/mobile/teacher/category")
public class TeacherCategoryController extends Controller {

    @ActionKey("/mobile/teacher/category/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/teacher/category/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/teacher/category/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/teacher/category/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/teacher/category/delete")
    public void delete() {

        renderSuccessJson();
    }

}