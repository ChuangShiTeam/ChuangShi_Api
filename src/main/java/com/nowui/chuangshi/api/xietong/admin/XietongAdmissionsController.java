package com.nowui.chuangshi.api.xietong.admin;

import java.util.List;


import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.user.model.User;
import com.nowui.chuangshi.api.xietong.model.XietongAdmissions;
import com.nowui.chuangshi.api.xietong.service.XietongAdmissionsService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;

import com.nowui.chuangshi.constant.Constant;


@ControllerKey("/admin/xietong/admissions")
public class XietongAdmissionsController extends Controller {

    @ActionKey("/admin/xietong/admissions/list")
    public void list() {
        validateRequest(XietongAdmissions.ADMISSIONS_NO, XietongAdmissions.ADMISSIONS_NAME, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        XietongAdmissions model = getModel(XietongAdmissions.class);
        String request_app_id = getRequest_app_id();

        Integer resultCount = XietongAdmissionsService.instance.adminCount(request_app_id, model.getAdmissions_no(), model.getAdmissions_name());
        List<XietongAdmissions> resultList = XietongAdmissionsService.instance.adminList(request_app_id, model.getAdmissions_no(), model.getAdmissions_name(), getM(), getN());

        validateResponse(XietongAdmissions.ADMISSIONS_ID, XietongAdmissions.ADMISSIONS_NO, XietongAdmissions.ADMISSIONS_NAME, XietongAdmissions.ADMISSIONS_CERTIFICATE_TYPE, XietongAdmissions.ADMISSIONS_CERTIFICATE_NUMBER, XietongAdmissions.ADMISSIONS_SEX, XietongAdmissions.SYSTEM_VERSION);

        renderSuccessJson(resultCount, resultList);
    }

    @ActionKey("/admin/xietong/admissions/find")
    public void find() {
        validateRequest(XietongAdmissions.ADMISSIONS_ID);

        XietongAdmissions model = getModel(XietongAdmissions.class);

        XietongAdmissions result = XietongAdmissionsService.instance.find(model.getAdmissions_id());

        validateResponse(XietongAdmissions.USER_ID, XietongAdmissions.ADMISSIONS_NO, XietongAdmissions.ADMISSIONS_NAME, XietongAdmissions.ADMISSIONS_CERTIFICATE_TYPE, XietongAdmissions.ADMISSIONS_CERTIFICATE_NUMBER, XietongAdmissions.ADMISSIONS_SEX, XietongAdmissions.ADMISSIONS_BIRTHDAY, XietongAdmissions.ADMISSIONS_IS_APPLY_LIVE_SCHOOL, XietongAdmissions.ADMISSIONS_OLD_SCHOOL, XietongAdmissions.ADMISSIONS_REGISTRATION_ADDRESS, XietongAdmissions.ADMISSIONS_HOME_ADDRESS, XietongAdmissions.ADMISSIONS_HOME_FIRST_NAME, XietongAdmissions.ADMISSIONS_HOME_FIRST_UNIT, XietongAdmissions.ADMISSIONS_HOME_FIRST_TEL, XietongAdmissions.ADMISSIONS_HOME_SECOND_NAME, XietongAdmissions.ADMISSIONS_HOME_SECOND_UNIT, XietongAdmissions.ADMISSIONS_HOME_SECOND_TEL, XietongAdmissions.ADMISSIONS_NOTES, XietongAdmissions.SYSTEM_VERSION);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/xietong/admissions/save")
    public void save() {
        validateRequest(XietongAdmissions.ADMISSIONS_NAME, XietongAdmissions.ADMISSIONS_CERTIFICATE_TYPE, XietongAdmissions.ADMISSIONS_CERTIFICATE_NUMBER, XietongAdmissions.ADMISSIONS_SEX, XietongAdmissions.ADMISSIONS_BIRTHDAY, XietongAdmissions.ADMISSIONS_IS_APPLY_LIVE_SCHOOL, XietongAdmissions.ADMISSIONS_OLD_SCHOOL, XietongAdmissions.ADMISSIONS_REGISTRATION_ADDRESS, XietongAdmissions.ADMISSIONS_HOME_ADDRESS, XietongAdmissions.ADMISSIONS_HOME_FIRST_NAME, XietongAdmissions.ADMISSIONS_HOME_FIRST_UNIT, XietongAdmissions.ADMISSIONS_HOME_FIRST_TEL, XietongAdmissions.ADMISSIONS_HOME_SECOND_NAME, XietongAdmissions.ADMISSIONS_HOME_SECOND_UNIT, XietongAdmissions.ADMISSIONS_HOME_SECOND_TEL, XietongAdmissions.ADMISSIONS_NOTES);

        XietongAdmissions model = getModel(XietongAdmissions.class);
        String request_user_id = getRequest_user_id();

        Boolean result = XietongAdmissionsService.instance.save(model, request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/xietong/admissions/update")
    public void update() {
        validateRequest(XietongAdmissions.USER_ID, XietongAdmissions.ADMISSIONS_ID, XietongAdmissions.ADMISSIONS_NO, XietongAdmissions.ADMISSIONS_NAME, XietongAdmissions.ADMISSIONS_CERTIFICATE_TYPE, XietongAdmissions.ADMISSIONS_CERTIFICATE_NUMBER, XietongAdmissions.ADMISSIONS_SEX, XietongAdmissions.ADMISSIONS_BIRTHDAY, XietongAdmissions.ADMISSIONS_IS_APPLY_LIVE_SCHOOL, XietongAdmissions.ADMISSIONS_OLD_SCHOOL, XietongAdmissions.ADMISSIONS_REGISTRATION_ADDRESS, XietongAdmissions.ADMISSIONS_HOME_ADDRESS, XietongAdmissions.ADMISSIONS_HOME_FIRST_NAME, XietongAdmissions.ADMISSIONS_HOME_FIRST_UNIT, XietongAdmissions.ADMISSIONS_HOME_FIRST_TEL, XietongAdmissions.ADMISSIONS_HOME_SECOND_NAME, XietongAdmissions.ADMISSIONS_HOME_SECOND_UNIT, XietongAdmissions.ADMISSIONS_HOME_SECOND_TEL, XietongAdmissions.ADMISSIONS_NOTES, XietongAdmissions.SYSTEM_VERSION);

        XietongAdmissions model = getModel(XietongAdmissions.class);
        User userModel = getModel(User.class);
        String request_user_id = getRequest_user_id();

        Boolean result = XietongAdmissionsService.instance.update(model, userModel, request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey("/admin/xietong/admissions/delete")
    public void delete() {
        validateRequest(XietongAdmissions.ADMISSIONS_ID, XietongAdmissions.SYSTEM_VERSION);

        XietongAdmissions model = getModel(XietongAdmissions.class);
        String request_user_id = getRequest_user_id();

        Boolean result = XietongAdmissionsService.instance.delete(model.getAdmissions_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

}