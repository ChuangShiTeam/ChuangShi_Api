package com.nowui.chuangshi.api.xietong.admin;

import java.util.List;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.file.service.FileService;
import com.nowui.chuangshi.api.xietong.model.XietongTeacherRecruitment;
import com.nowui.chuangshi.api.xietong.service.XietongTeacherRecruitmentService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.util.Util;


@ControllerKey("/admin/xietong/teacher/recruitment")
public class XietongTeacherRecruitmentController extends Controller {

    @ActionKey("/admin/xietong/teacher/recruitment/list")
    public void list() {
        validateRequest(XietongTeacherRecruitment.TEACHER_RECRUITMENT_NAME, XietongTeacherRecruitment.TEACHER_RECRUITMENT_MOBILE, XietongTeacherRecruitment.TEACHER_RECRUITMENT_FACULTY, XietongTeacherRecruitment.TEACHER_RECRUITMENT_SUBJECT, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        XietongTeacherRecruitment model = getModel(XietongTeacherRecruitment.class);
        String request_app_id = getRequest_app_id();

        Integer resultCount = XietongTeacherRecruitmentService.instance.adminCount(request_app_id, model.getTeacher_recruitment_name(), model.getTeacher_recruitment_mobile(), model.getTeacher_recruitment_faculty(), model.getTeacher_recruitment_subject());
        List<XietongTeacherRecruitment> resultList = XietongTeacherRecruitmentService.instance.adminList(request_app_id, model.getTeacher_recruitment_name(), model.getTeacher_recruitment_mobile(), model.getTeacher_recruitment_faculty(), model.getTeacher_recruitment_subject(), getM(), getN());

        validateResponse(XietongTeacherRecruitment.TEACHER_RECRUITMENT_ID, XietongTeacherRecruitment.TEACHER_RECRUITMENT_NAME, XietongTeacherRecruitment.TEACHER_RECRUITMENT_SEX, XietongTeacherRecruitment.TEACHER_RECRUITMENT_MOBILE, XietongTeacherRecruitment.TEACHER_RECRUITMENT_FACULTY, XietongTeacherRecruitment.TEACHER_RECRUITMENT_SUBJECT, XietongTeacherRecruitment.SYSTEM_VERSION);

        renderSuccessJson(resultCount, resultList);
    }

    @ActionKey("/admin/xietong/teacher/recruitment/find")
    public void find() {
        validateRequest(XietongTeacherRecruitment.TEACHER_RECRUITMENT_ID);

        XietongTeacherRecruitment model = getModel(XietongTeacherRecruitment.class);

        XietongTeacherRecruitment result = XietongTeacherRecruitmentService.instance.find(model.getTeacher_recruitment_id());

        result.put(XietongTeacherRecruitment.TEACHER_RECRUITMENT_FILE_FILE, FileService.instance.getFile(result.getTeacher_recruitment_file()));

        validateResponse(XietongTeacherRecruitment.TEACHER_RECRUITMENT_NAME, XietongTeacherRecruitment.TEACHER_RECRUITMENT_SEX, XietongTeacherRecruitment.TEACHER_RECRUITMENT_BIRTHDAY, XietongTeacherRecruitment.TEACHER_RECRUITMENT_MOBILE, XietongTeacherRecruitment.TEACHER_RECRUITMENT_EMAIL, XietongTeacherRecruitment.TEACHER_RECRUITMENT_FACULTY, XietongTeacherRecruitment.TEACHER_RECRUITMENT_SUBJECT, XietongTeacherRecruitment.TEACHER_RECRUITMENT_IS_FRESH_GRADUATE, XietongTeacherRecruitment.TEACHER_RECRUITMENT_WORK_YEAR, XietongTeacherRecruitment.TEACHER_RECRUITMENT_OLD_UNIT, XietongTeacherRecruitment.TEACHER_RECRUITMENT_POLITICS_STATUS, XietongTeacherRecruitment.TEACHER_RECRUITMENT_JOB_TITLE, XietongTeacherRecruitment.TEACHER_RECRUITMENT_EDUCATION, XietongTeacherRecruitment.TEACHER_RECRUITMENT_MAJOR, XietongTeacherRecruitment.TEACHER_RECRUITMENT_GRAD_SCHOOL, XietongTeacherRecruitment.TEACHER_RECRUITMENT_EDUCATION_EXPERIENCE, XietongTeacherRecruitment.TEACHER_RECRUITMENT_WORK_EXPERIENCE, XietongTeacherRecruitment.TEACHER_RECRUITMENT_REPRESENTATIVE_HONOR, XietongTeacherRecruitment.TEACHER_RECRUITMENT_NOW_ADDRESS, XietongTeacherRecruitment.TEACHER_RECRUITMENT_FILE, XietongTeacherRecruitment.TEACHER_RECRUITMENT_FILE_FILE, XietongTeacherRecruitment.SYSTEM_VERSION);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/xietong/teacher/recruitment/save")
    public void save() {
        validateRequest(XietongTeacherRecruitment.TEACHER_RECRUITMENT_NAME, XietongTeacherRecruitment.TEACHER_RECRUITMENT_SEX, XietongTeacherRecruitment.TEACHER_RECRUITMENT_BIRTHDAY, XietongTeacherRecruitment.TEACHER_RECRUITMENT_MOBILE, XietongTeacherRecruitment.TEACHER_RECRUITMENT_EMAIL, XietongTeacherRecruitment.TEACHER_RECRUITMENT_FACULTY, XietongTeacherRecruitment.TEACHER_RECRUITMENT_SUBJECT, XietongTeacherRecruitment.TEACHER_RECRUITMENT_IS_FRESH_GRADUATE, XietongTeacherRecruitment.TEACHER_RECRUITMENT_WORK_YEAR, XietongTeacherRecruitment.TEACHER_RECRUITMENT_OLD_UNIT, XietongTeacherRecruitment.TEACHER_RECRUITMENT_POLITICS_STATUS, XietongTeacherRecruitment.TEACHER_RECRUITMENT_JOB_TITLE, XietongTeacherRecruitment.TEACHER_RECRUITMENT_EDUCATION, XietongTeacherRecruitment.TEACHER_RECRUITMENT_MAJOR, XietongTeacherRecruitment.TEACHER_RECRUITMENT_GRAD_SCHOOL, XietongTeacherRecruitment.TEACHER_RECRUITMENT_EDUCATION_EXPERIENCE, XietongTeacherRecruitment.TEACHER_RECRUITMENT_WORK_EXPERIENCE, XietongTeacherRecruitment.TEACHER_RECRUITMENT_REPRESENTATIVE_HONOR, XietongTeacherRecruitment.TEACHER_RECRUITMENT_NOW_ADDRESS, XietongTeacherRecruitment.TEACHER_RECRUITMENT_FILE);

        XietongTeacherRecruitment model = getModel(XietongTeacherRecruitment.class);
        model.setTeacher_recruitment_id(Util.getRandomUUID());
        String request_user_id = getRequest_user_id();

        Boolean result = XietongTeacherRecruitmentService.instance.save(model, request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/xietong/teacher/recruitment/update")
    public void update() {
        validateRequest(XietongTeacherRecruitment.TEACHER_RECRUITMENT_ID, XietongTeacherRecruitment.TEACHER_RECRUITMENT_NAME, XietongTeacherRecruitment.TEACHER_RECRUITMENT_SEX, XietongTeacherRecruitment.TEACHER_RECRUITMENT_BIRTHDAY, XietongTeacherRecruitment.TEACHER_RECRUITMENT_MOBILE, XietongTeacherRecruitment.TEACHER_RECRUITMENT_EMAIL, XietongTeacherRecruitment.TEACHER_RECRUITMENT_FACULTY, XietongTeacherRecruitment.TEACHER_RECRUITMENT_SUBJECT, XietongTeacherRecruitment.TEACHER_RECRUITMENT_IS_FRESH_GRADUATE, XietongTeacherRecruitment.TEACHER_RECRUITMENT_WORK_YEAR, XietongTeacherRecruitment.TEACHER_RECRUITMENT_OLD_UNIT, XietongTeacherRecruitment.TEACHER_RECRUITMENT_POLITICS_STATUS, XietongTeacherRecruitment.TEACHER_RECRUITMENT_JOB_TITLE, XietongTeacherRecruitment.TEACHER_RECRUITMENT_EDUCATION, XietongTeacherRecruitment.TEACHER_RECRUITMENT_MAJOR, XietongTeacherRecruitment.TEACHER_RECRUITMENT_GRAD_SCHOOL, XietongTeacherRecruitment.TEACHER_RECRUITMENT_EDUCATION_EXPERIENCE, XietongTeacherRecruitment.TEACHER_RECRUITMENT_WORK_EXPERIENCE, XietongTeacherRecruitment.TEACHER_RECRUITMENT_REPRESENTATIVE_HONOR, XietongTeacherRecruitment.TEACHER_RECRUITMENT_NOW_ADDRESS, XietongTeacherRecruitment.TEACHER_RECRUITMENT_FILE, XietongTeacherRecruitment.SYSTEM_VERSION);

        XietongTeacherRecruitment model = getModel(XietongTeacherRecruitment.class);
        String request_user_id = getRequest_user_id();

        Boolean result = XietongTeacherRecruitmentService.instance.update(model, model.getTeacher_recruitment_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey("/admin/xietong/teacher/recruitment/delete")
    public void delete() {
        validateRequest(XietongTeacherRecruitment.TEACHER_RECRUITMENT_ID, XietongTeacherRecruitment.SYSTEM_VERSION);

        XietongTeacherRecruitment model = getModel(XietongTeacherRecruitment.class);
        String request_user_id = getRequest_user_id();

        Boolean result = XietongTeacherRecruitmentService.instance.delete(model.getTeacher_recruitment_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }
    
    @ActionKey("/admin/xietong/teacher/recruitment/all/export")
    public void allExport() {
        render(XietongTeacherRecruitmentService.instance.allExport());
    }

}