package com.nowui.chuangshi.api.xietong.mobile;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;

@ControllerKey("/mobile/xietong/course/apply")
public class XietongCourseApplyController extends Controller {

    @ActionKey("/mobile/xietong/course/apply/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/xietong/course/apply/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/xietong/course/apply/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/xietong/course/apply/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/xietong/course/apply/delete")
    public void delete() {

        renderSuccessJson();
    }

}