package com.nowui.chuangshi.api.school.mobile;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.school.service.StudentService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;

@ControllerKey("/mobile/student")
public class StudentController extends Controller {

    private final StudentService studentService = new StudentService();

    @ActionKey("/mobile/student/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/student/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/student/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/student/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/student/delete")
    public void delete() {

        renderSuccessJson();
    }

}