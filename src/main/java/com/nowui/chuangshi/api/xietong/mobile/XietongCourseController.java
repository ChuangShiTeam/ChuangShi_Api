package com.nowui.chuangshi.api.xietong.mobile;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;

@ControllerKey("/mobile/xietong/course")
public class XietongCourseController extends Controller {

    @ActionKey("/mobile/xietong/course/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/xietong/course/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/xietong/course/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/xietong/course/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/xietong/course/delete")
    public void delete() {

        renderSuccessJson();
    }

}