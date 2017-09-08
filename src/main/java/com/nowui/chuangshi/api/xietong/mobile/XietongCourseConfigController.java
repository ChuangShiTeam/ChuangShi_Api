package com.nowui.chuangshi.api.xietong.mobile;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;

@ControllerKey("/mobile/xietong/course/config")
public class XietongCourseConfigController extends Controller {

    @ActionKey("/mobile/xietong/course/config/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/xietong/course/config/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/xietong/course/config/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/xietong/course/config/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/xietong/course/config/delete")
    public void delete() {

        renderSuccessJson();
    }

}