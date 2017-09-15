package com.nowui.chuangshi.api.xietong.mobile;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.xietong.model.XietongCourse;
import com.nowui.chuangshi.api.xietong.model.XietongCourseApplyHistory;
import com.nowui.chuangshi.api.xietong.service.XietongCourseApplyHistoryService;
import com.nowui.chuangshi.api.xietong.service.XietongCourseService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;

import java.util.List;

@ControllerKey("/mobile/xietong/course/apply/history")
public class XietongCourseApplyHistoryController extends Controller {

    @ActionKey("/mobile/xietong/course/apply/history/list")
    public void list() {
        String request_user_id = getRequest_user_id();

        List<XietongCourse> courseList = XietongCourseService.instance.userList(request_user_id);

        validateResponse(XietongCourse.IS_APPLY, XietongCourse.COURSE_ID, XietongCourse.COURSE_NAME, XietongCourse.COURSE_TEACHER, XietongCourse.COURSE_TIME, XietongCourse.COURSE_APPLY_LIMIT);

        renderSuccessJson(courseList);
    }

    @ActionKey("/mobile/xietong/course/apply/history/find")
    public void find() {
        validateRequest(XietongCourseApplyHistory.COURSE_APPLY_HISTORY_ID);

        XietongCourseApplyHistory model = getModel(XietongCourseApplyHistory.class);

        XietongCourseApplyHistory courseApplyHistory = XietongCourseApplyHistoryService.instance.find(model.getCourse_apply_history_id());

        validateResponse(XietongCourseApplyHistory.COURSE_APPLY_HISTORY_STATUS, XietongCourseApplyHistory.COURSE_APPLY_HISTORY_RESULT);

        renderSuccessJson(courseApplyHistory);
    }

}
