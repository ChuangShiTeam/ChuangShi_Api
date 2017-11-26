package com.nowui.chuangshi.api.xietong.desktop;

import java.util.List;

import org.apache.commons.lang3.Validate;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.file.model.File;
import com.nowui.chuangshi.api.xietong.model.XietongTeacher;
import com.nowui.chuangshi.api.xietong.service.XietongTeacherService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.constant.Constant;



@ControllerKey("/desktop/xietong/teacher")
public class XietongTeacherController extends Controller {

    @ActionKey("/desktop/xietong/teacher/list")
    public void list() {
        validateRequest(XietongTeacher.TEACHER_CATEGORY_ID, Constant.PAGE_INDEX, Constant.PAGE_SIZE);
        
        XietongTeacher xietongTeacher = getModel(XietongTeacher.class);
        String request_app_id = getRequest_app_id();

        Integer resultCount = XietongTeacherService.instance.desktopCount(request_app_id, xietongTeacher.getTeacher_category_id());
        List<XietongTeacher> resultList = XietongTeacherService.instance.desktopList(request_app_id, xietongTeacher.getTeacher_category_id(), getM(), getN());

        validateResponse(XietongTeacher.TEACHER_ID, XietongTeacher.TEACHER_CATEGORY_ID, File.FILE_PATH, XietongTeacher.TEACHER_NAME, XietongTeacher.TEACHER_TITLE, XietongTeacher.TEACHER_DESCRIPTION);

        renderSuccessJson(resultCount, resultList);
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