package com.nowui.chuangshi.api.school.mobile;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.school.service.CourseApplyService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;

@ControllerKey("/mobile/course/apply")
public class CourseApplyController extends Controller {

    private final CourseApplyService courseApplyService = new CourseApplyService();

    @ActionKey("/mobile/course/apply/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/course/apply/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/course/apply/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/course/apply/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/course/apply/delete")
    public void delete() {

        renderSuccessJson();
    }

}