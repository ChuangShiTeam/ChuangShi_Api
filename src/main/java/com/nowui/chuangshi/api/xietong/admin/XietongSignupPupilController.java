package com.nowui.chuangshi.api.xietong.admin;


import java.util.List;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.xietong.model.XietongSignupJunior;
import com.nowui.chuangshi.api.xietong.model.XietongSignupPupil;
import com.nowui.chuangshi.api.xietong.service.XietongSignupPupilService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.util.Util;


@ControllerKey("/admin/xietong/signup/pupil")
public class XietongSignupPupilController extends Controller {

    @ActionKey("/admin/xietong/signup/pupil/list")
    public void list() {
        validateRequest(XietongSignupPupil.STUDENT_NAME, XietongSignupJunior.STUDENT_CATEGORY, XietongSignupPupil.ID_NO, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        XietongSignupPupil model = getModel(XietongSignupPupil.class);
        String request_app_id = getRequest_app_id();

        Integer resultCount = XietongSignupPupilService.instance.adminCount(request_app_id, model.getStudent_name(), model.getStudent_category(), model.getId_no());
        List<XietongSignupPupil> resultList = XietongSignupPupilService.instance.adminList(request_app_id, model.getStudent_name(), model.getStudent_category(), model.getId_no(), getM(), getN());

        validateResponse(XietongSignupPupil.SIGNUP_ID, XietongSignupPupil.STUDENT_NAME, XietongSignupJunior.STUDENT_CATEGORY, XietongSignupPupil.STUDENT_SEX, XietongSignupPupil.STUDENT_BIRTHDAY, XietongSignupPupil.KINDERGARTEN, XietongSignupPupil.ID_NO, XietongSignupPupil.SIGNUP_STATUS, XietongSignupPupil.SYSTEM_VERSION);

        renderSuccessJson(resultCount, resultList);
    }

    @ActionKey("/admin/xietong/signup/pupil/find")
    public void find() {
        validateRequest(XietongSignupPupil.SIGNUP_ID);

        XietongSignupPupil model = getModel(XietongSignupPupil.class);

        XietongSignupPupil result = XietongSignupPupilService.instance.find(model.getSignup_id());

        validateResponse(XietongSignupPupil.STUDENT_NAME, XietongSignupJunior.STUDENT_CATEGORY, XietongSignupPupil.STUDENT_SEX, XietongSignupPupil.STUDENT_BIRTHDAY, XietongSignupPupil.INTERVIEW_TIME, XietongSignupPupil.KINDERGARTEN, XietongSignupPupil.ID_TYPE, XietongSignupPupil.ID_NO, XietongSignupPupil.PERMANENT_ADDRESS, XietongSignupPupil.LIVE_ADDRESSS, XietongSignupPupil.FATHER_NAME, XietongSignupPupil.FATHER_ID_NO, XietongSignupPupil.FATHER_WORK, XietongSignupPupil.FATHER_PHONE, XietongSignupPupil.MOTHER_NAME, XietongSignupPupil.MOTHER_ID_NO, XietongSignupPupil.MOTHER_WORK, XietongSignupPupil.MOTHER_PHONE, XietongSignupPupil.REMARK, XietongSignupPupil.MARK, XietongSignupPupil.SIGNUP_STATUS, XietongSignupPupil.SYSTEM_VERSION);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/xietong/signup/pupil/save")
    public void save() {
        validateRequest(XietongSignupPupil.STUDENT_NAME, XietongSignupJunior.STUDENT_CATEGORY, XietongSignupPupil.STUDENT_SEX, XietongSignupPupil.STUDENT_BIRTHDAY, XietongSignupPupil.INTERVIEW_TIME, XietongSignupPupil.KINDERGARTEN, XietongSignupPupil.ID_TYPE, XietongSignupPupil.ID_NO, XietongSignupPupil.PERMANENT_ADDRESS, XietongSignupPupil.LIVE_ADDRESSS, XietongSignupPupil.FATHER_NAME, XietongSignupPupil.FATHER_ID_NO, XietongSignupPupil.FATHER_WORK, XietongSignupPupil.FATHER_PHONE, XietongSignupPupil.MOTHER_NAME, XietongSignupPupil.MOTHER_ID_NO, XietongSignupPupil.MOTHER_WORK, XietongSignupPupil.MOTHER_PHONE, XietongSignupPupil.REMARK, XietongSignupPupil.MARK, XietongSignupPupil.SIGNUP_STATUS);

        XietongSignupPupil model = getModel(XietongSignupPupil.class);
        model.setSignup_id(Util.getRandomUUID());
        String request_user_id = getRequest_user_id();

        Boolean result = XietongSignupPupilService.instance.save(model, request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/xietong/signup/pupil/update")
    public void update() {
        validateRequest(XietongSignupPupil.SIGNUP_ID, XietongSignupPupil.STUDENT_NAME, XietongSignupJunior.STUDENT_CATEGORY, XietongSignupPupil.STUDENT_SEX, XietongSignupPupil.STUDENT_BIRTHDAY, XietongSignupPupil.INTERVIEW_TIME, XietongSignupPupil.KINDERGARTEN, XietongSignupPupil.ID_TYPE, XietongSignupPupil.ID_NO, XietongSignupPupil.PERMANENT_ADDRESS, XietongSignupPupil.LIVE_ADDRESSS, XietongSignupPupil.FATHER_NAME, XietongSignupPupil.FATHER_ID_NO, XietongSignupPupil.FATHER_WORK, XietongSignupPupil.FATHER_PHONE, XietongSignupPupil.MOTHER_NAME, XietongSignupPupil.MOTHER_ID_NO, XietongSignupPupil.MOTHER_WORK, XietongSignupPupil.MOTHER_PHONE, XietongSignupPupil.REMARK, XietongSignupPupil.MARK, XietongSignupPupil.SIGNUP_STATUS, XietongSignupPupil.SYSTEM_VERSION);

        XietongSignupPupil model = getModel(XietongSignupPupil.class);
        String request_user_id = getRequest_user_id();

        Boolean result = XietongSignupPupilService.instance.update(model, model.getSignup_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey("/admin/xietong/signup/pupil/delete")
    public void delete() {
        validateRequest(XietongSignupPupil.SIGNUP_ID, XietongSignupPupil.SYSTEM_VERSION);

        XietongSignupPupil model = getModel(XietongSignupPupil.class);
        String request_user_id = getRequest_user_id();

        Boolean result = XietongSignupPupilService.instance.delete(model.getSignup_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }
    
    @ActionKey("/admin/xietong/signup/pupil/all/export")
    public void allExport() {
        render(XietongSignupPupilService.instance.allExport());
    }

}