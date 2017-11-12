package com.nowui.chuangshi.api.xietong.desktop;


import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.xietong.model.XietongTeacherRecruitment;
import com.nowui.chuangshi.api.xietong.service.XietongTeacherRecruitmentService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.util.Util;

@ControllerKey("/desktop/xietong/teacher/recruitment")
public class XietongTeacherRecruitmentController extends Controller {

    @ActionKey("/desktop/xietong/teacher/recruitment/save")
    public void save() {
        validateRequest(XietongTeacherRecruitment.TEACHER_RECRUITMENT_NAME, XietongTeacherRecruitment.TEACHER_RECRUITMENT_SEX, XietongTeacherRecruitment.TEACHER_RECRUITMENT_BIRTHDAY, XietongTeacherRecruitment.TEACHER_RECRUITMENT_MOBILE, XietongTeacherRecruitment.TEACHER_RECRUITMENT_EMAIL, XietongTeacherRecruitment.TEACHER_RECRUITMENT_FACULTY, XietongTeacherRecruitment.TEACHER_RECRUITMENT_SUBJECT, XietongTeacherRecruitment.TEACHER_RECRUITMENT_IS_FRESH_GRADUATE, XietongTeacherRecruitment.TEACHER_RECRUITMENT_WORK_YEAR, XietongTeacherRecruitment.TEACHER_RECRUITMENT_OLD_UNIT, XietongTeacherRecruitment.TEACHER_RECRUITMENT_POLITICS_STATUS, XietongTeacherRecruitment.TEACHER_RECRUITMENT_JOB_TITLE, XietongTeacherRecruitment.TEACHER_RECRUITMENT_EDUCATION, XietongTeacherRecruitment.TEACHER_RECRUITMENT_MAJOR, XietongTeacherRecruitment.TEACHER_RECRUITMENT_GRAD_SCHOOL, XietongTeacherRecruitment.TEACHER_RECRUITMENT_EDUCATION_EXPERIENCE, XietongTeacherRecruitment.TEACHER_RECRUITMENT_WORK_EXPERIENCE, XietongTeacherRecruitment.TEACHER_RECRUITMENT_REPRESENTATIVE_HONOR, XietongTeacherRecruitment.TEACHER_RECRUITMENT_NOW_ADDRESS, XietongTeacherRecruitment.TEACHER_RECRUITMENT_FILE);

        XietongTeacherRecruitment model = getModel(XietongTeacherRecruitment.class);
        model.setTeacher_recruitment_id(Util.getRandomUUID());
        String request_user_id = getRequest_user_id();

        Boolean result = XietongTeacherRecruitmentService.instance.save(model, request_user_id);

        renderSuccessJson(result);
    }

}