package com.nowui.chuangshi.api.xietong.desktop;

import java.util.List;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.file.model.File;
import com.nowui.chuangshi.api.xietong.model.XietongTeacher;
import com.nowui.chuangshi.api.xietong.service.XietongTeacherService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.common.interceptor.AdminInterceptor;

@Before(AdminInterceptor.class)
@ControllerKey("/desktop/xietong/teacher")
public class XietongTeacherController extends Controller {

    @ActionKey("/desktop/xietong/teacher/list")
    public void list() {
        String request_app_id = getRequest_app_id();

        List<XietongTeacher> resultList = XietongTeacherService.instance.appList(request_app_id);

        validateResponse(XietongTeacher.TEACHER_ID, XietongTeacher.TEACHER_CATEGORY_ID, File.FILE_PATH, XietongTeacher.TEACHER_NAME, XietongTeacher.TEACHER_TITLE, XietongTeacher.TEACHER_DESCRIPTION);

        renderSuccessJson(resultList);
    }

    @ActionKey("/desktop/xietong/teacher/find")
    public void find() {
        validateRequest(XietongTeacher.TEACHER_ID);

        XietongTeacher model = getModel(XietongTeacher.class);

        XietongTeacher result = XietongTeacherService.instance.find(model.getTeacher_id());

        validateResponse(XietongTeacher.TEACHER_ID, XietongTeacher.TEACHER_NAME, XietongTeacher.TEACHER_CATEGORY_ID, File.FILE_ORIGINAL_PATH, XietongTeacher.TEACHER_TITLE, XietongTeacher.TEACHER_DESCRIPTION);

        renderSuccessJson(result);
    }

}