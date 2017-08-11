package com.nowui.chuangshi.api.school.mobile;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.school.service.CourseService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;

@ControllerKey("/mobile/course")
public class CourseController extends Controller {

    private final CourseService courseService = new CourseService();

    @ActionKey("/mobile/course/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/course/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/course/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/course/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/course/delete")
    public void delete() {

        renderSuccessJson();
    }

}