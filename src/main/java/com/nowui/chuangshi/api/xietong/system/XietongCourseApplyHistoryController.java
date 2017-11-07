package com.nowui.chuangshi.api.xietong.system;


import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;



@ControllerKey("/system/xietong/course/apply/history")
public class XietongCourseApplyHistoryController extends Controller {

    @ActionKey("/system/xietong/course/apply/history/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/system/xietong/course/apply/history/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/system/xietong/course/apply/history/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/system/xietong/course/apply/history/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/system/xietong/course/apply/history/delete")
    public void delete() {

        renderSuccessJson();
    }

}