package com.nowui.chuangshi.api.school.mobile;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.school.service.CourseApplyHistoryService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;

@ControllerKey("/mobile/course/apply/history")
public class CourseApplyHistoryController extends Controller {

    private final CourseApplyHistoryService courseApplyHistoryService = new CourseApplyHistoryService();

    @ActionKey("/mobile/course/apply/history/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/course/apply/history/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/course/apply/history/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/course/apply/history/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/course/apply/history/delete")
    public void delete() {

        renderSuccessJson();
    }

}