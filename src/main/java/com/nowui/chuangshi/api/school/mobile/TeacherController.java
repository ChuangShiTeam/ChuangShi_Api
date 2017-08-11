package com.nowui.chuangshi.api.school.mobile;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.school.service.TeacherService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;

@ControllerKey("/mobile/teacher")
public class TeacherController extends Controller {

    private final TeacherService teacherService = new TeacherService();

    @ActionKey("/mobile/teacher/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/teacher/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/teacher/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/teacher/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/teacher/delete")
    public void delete() {

        renderSuccessJson();
    }

}