package com.nowui.chuangshi.api.xietong.mobile;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;

@ControllerKey("/mobile/xietong/teacher/category")
public class XietongTeacherCategoryController extends Controller {

    @ActionKey("/mobile/xietong/teacher/category/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/xietong/teacher/category/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/xietong/teacher/category/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/xietong/teacher/category/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/xietong/teacher/category/delete")
    public void delete() {

        renderSuccessJson();
    }

}