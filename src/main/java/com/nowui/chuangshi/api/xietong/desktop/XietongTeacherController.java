package com.nowui.chuangshi.api.xietong.desktop;

import java.util.List;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.xietong.model.XietongTeacher;
import com.nowui.chuangshi.api.xietong.service.XietongTeacherService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.common.interceptor.AdminInterceptor;
import com.nowui.chuangshi.constant.Constant;

@Before(AdminInterceptor.class)
@ControllerKey("/desktop/xietong/teacher")
public class XietongTeacherController extends Controller {

    @ActionKey("/desktop/xietong/teacher/list")
    public void list() {
        validateRequest(XietongTeacher.ORGANIZATION_ID, XietongTeacher.TEACHER_NAME, XietongTeacher.TEACHER_NUMBER, XietongTeacher.TEACHER_CATEGORY, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        String request_app_id = getRequest_app_id();

        Integer resultCount = XietongTeacherService.instance.desktopCount(request_app_id);
        List<XietongTeacher> resultList = XietongTeacherService.instance.desktopList(request_app_id, getM(), getN());

        validateResponse(XietongTeacher.TEACHER_ID, XietongTeacher.TEACHER_NAME, XietongTeacher.TEACHER_DESCRIPTION);

        renderSuccessJson(resultCount, resultList);
    }

}