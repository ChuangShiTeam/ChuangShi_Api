package com.nowui.chuangshi.api.xietong.admin;


import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.xietong.model.XietongSignupJunior;
import com.nowui.chuangshi.api.xietong.service.XietongSignupJuniorService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;

import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.util.Util;

import java.util.List;


@ControllerKey("/admin/xietong/signup/junior")
public class XietongSignupJuniorController extends Controller {

    @ActionKey("/admin/xietong/signup/junior/list")
    public void list() {
        validateRequest(XietongSignupJunior.STUDENT_NAME, XietongSignupJunior.ID_NO, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        XietongSignupJunior model = getModel(XietongSignupJunior.class);
        String request_app_id = getRequest_app_id();

        Integer resultCount = XietongSignupJuniorService.instance.adminCount(request_app_id, model.getStudent_name(), model.getId_no());
        List<XietongSignupJunior> resultList = XietongSignupJuniorService.instance.adminList(request_app_id, model.getStudent_name(), model.getId_no(), getM(), getN());

        validateResponse(XietongSignupJunior.SIGNUP_ID, XietongSignupJunior.STUDENT_NAME, XietongSignupJunior.STUDENT_SEX, XietongSignupJunior.STUDENT_BIRTHDAY, XietongSignupJunior.JOB, XietongSignupJunior.ID_TYPE, XietongSignupJunior.ID_NO, XietongSignupJunior.PERMANENT_ADDRESS, XietongSignupJunior.LIVE_ADDRESSS, XietongSignupJunior.SYSTEM_VERSION);

        renderSuccessJson(resultCount, resultList);
    }

    @ActionKey("/admin/xietong/signup/junior/find")
    public void find() {
        validateRequest(XietongSignupJunior.SIGNUP_ID);

        XietongSignupJunior model = getModel(XietongSignupJunior.class);

        XietongSignupJunior result = XietongSignupJuniorService.instance.find(model.getSignup_id());

        validateResponse(XietongSignupJunior.STUDENT_NAME, XietongSignupJunior.STUDENT_SEX, XietongSignupJunior.STUDENT_BIRTHDAY, XietongSignupJunior.INTERVIEW_TIME, XietongSignupJunior.JOB, XietongSignupJunior.PRIMARY_SCHOOL_CLASS, XietongSignupJunior.PRIMARY_SCHOOL, XietongSignupJunior.ID_TYPE, XietongSignupJunior.ID_NO, XietongSignupJunior.PERMANENT_ADDRESS, XietongSignupJunior.LIVE_ADDRESSS, XietongSignupJunior.FATHER_NAME, XietongSignupJunior.FATHER_ID_NO, XietongSignupJunior.FATHER_WORK, XietongSignupJunior.FATHER_PHONE, XietongSignupJunior.MOTHER_NAME, XietongSignupJunior.MOTHER_ID_NO, XietongSignupJunior.MOTHER_WORK, XietongSignupJunior.MOTHER_PHONE, XietongSignupJunior.MARK, XietongSignupJunior.REMARK, XietongSignupJunior.SIGNUP_STATUS, XietongSignupJunior.MATH_SCORE, XietongSignupJunior.ENGLISH_SCORE, XietongSignupJunior.CHINESE_SCORE, XietongSignupJunior.SYSTEM_VERSION);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/xietong/signup/junior/save")
    public void save() {
        validateRequest(XietongSignupJunior.STUDENT_NAME, XietongSignupJunior.STUDENT_SEX, XietongSignupJunior.STUDENT_BIRTHDAY, XietongSignupJunior.INTERVIEW_TIME, XietongSignupJunior.JOB, XietongSignupJunior.PRIMARY_SCHOOL_CLASS, XietongSignupJunior.PRIMARY_SCHOOL, XietongSignupJunior.ID_TYPE, XietongSignupJunior.ID_NO, XietongSignupJunior.PERMANENT_ADDRESS, XietongSignupJunior.LIVE_ADDRESSS, XietongSignupJunior.FATHER_NAME, XietongSignupJunior.FATHER_ID_NO, XietongSignupJunior.FATHER_WORK, XietongSignupJunior.FATHER_PHONE, XietongSignupJunior.MOTHER_NAME, XietongSignupJunior.MOTHER_ID_NO, XietongSignupJunior.MOTHER_WORK, XietongSignupJunior.MOTHER_PHONE, XietongSignupJunior.MARK, XietongSignupJunior.REMARK, XietongSignupJunior.SIGNUP_STATUS, XietongSignupJunior.MATH_SCORE, XietongSignupJunior.ENGLISH_SCORE, XietongSignupJunior.CHINESE_SCORE);

        XietongSignupJunior model = getModel(XietongSignupJunior.class);
        model.setSignup_id(Util.getRandomUUID());
        String request_user_id = getRequest_user_id();

        Boolean result = XietongSignupJuniorService.instance.save(model, request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/xietong/signup/junior/update")
    public void update() {
        validateRequest(XietongSignupJunior.SIGNUP_ID, XietongSignupJunior.STUDENT_NAME, XietongSignupJunior.STUDENT_SEX, XietongSignupJunior.STUDENT_BIRTHDAY, XietongSignupJunior.INTERVIEW_TIME, XietongSignupJunior.JOB, XietongSignupJunior.PRIMARY_SCHOOL_CLASS, XietongSignupJunior.PRIMARY_SCHOOL, XietongSignupJunior.ID_TYPE, XietongSignupJunior.ID_NO, XietongSignupJunior.PERMANENT_ADDRESS, XietongSignupJunior.LIVE_ADDRESSS, XietongSignupJunior.FATHER_NAME, XietongSignupJunior.FATHER_ID_NO, XietongSignupJunior.FATHER_WORK, XietongSignupJunior.FATHER_PHONE, XietongSignupJunior.MOTHER_NAME, XietongSignupJunior.MOTHER_ID_NO, XietongSignupJunior.MOTHER_WORK, XietongSignupJunior.MOTHER_PHONE, XietongSignupJunior.MARK, XietongSignupJunior.REMARK, XietongSignupJunior.SIGNUP_STATUS, XietongSignupJunior.MATH_SCORE, XietongSignupJunior.ENGLISH_SCORE, XietongSignupJunior.CHINESE_SCORE, XietongSignupJunior.SYSTEM_VERSION);

        XietongSignupJunior model = getModel(XietongSignupJunior.class);
        String request_user_id = getRequest_user_id();

        Boolean result = XietongSignupJuniorService.instance.update(model, model.getSignup_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey("/admin/xietong/signup/junior/delete")
    public void delete() {
        validateRequest(XietongSignupJunior.SIGNUP_ID, XietongSignupJunior.SYSTEM_VERSION);

        XietongSignupJunior model = getModel(XietongSignupJunior.class);
        String request_user_id = getRequest_user_id();

        Boolean result = XietongSignupJuniorService.instance.delete(model.getSignup_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

}