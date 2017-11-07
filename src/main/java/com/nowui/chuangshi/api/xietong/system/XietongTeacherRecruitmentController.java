package com.nowui.chuangshi.api.xietong.system;


import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;



@ControllerKey("/system/xietong/teacher/recruitment")
public class XietongTeacherRecruitmentController extends Controller {

    @ActionKey("/system/xietong/teacher/recruitment/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/system/xietong/teacher/recruitment/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/system/xietong/teacher/recruitment/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/system/xietong/teacher/recruitment/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/system/xietong/teacher/recruitment/delete")
    public void delete() {

        renderSuccessJson();
    }

}