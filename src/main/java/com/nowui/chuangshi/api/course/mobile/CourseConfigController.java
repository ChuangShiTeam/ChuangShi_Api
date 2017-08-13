package com.nowui.chuangshi.api.course.mobile;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.course.service.CourseConfigService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;

@ControllerKey("/mobile/course/config")
public class CourseConfigController extends Controller {

    private final CourseConfigService courseConfigService = new CourseConfigService();

    @ActionKey("/mobile/course/config/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/course/config/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/course/config/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/course/config/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/course/config/delete")
    public void delete() {

        renderSuccessJson();
    }

}