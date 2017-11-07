package com.nowui.chuangshi.api.xietong.system;


import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;



@ControllerKey("/system/xietong/course/apply")
public class XietongCourseApplyController extends Controller {

    @ActionKey("/system/xietong/course/apply/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/system/xietong/course/apply/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/system/xietong/course/apply/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/system/xietong/course/apply/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/system/xietong/course/apply/delete")
    public void delete() {

        renderSuccessJson();
    }

}