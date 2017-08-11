package com.nowui.chuangshi.api.school.admin;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.school.model.Student;
import com.nowui.chuangshi.api.school.service.StudentService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.common.interceptor.AdminInterceptor;
import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.util.Util;

import java.util.List;

@Before(AdminInterceptor.class)
@ControllerKey("/admin/student")
public class StudentController extends Controller {

    @ActionKey("/admin/student/list")
    public void list() {
        validateRequest(Student.STUDENT_NAME, Student.STUDENT_NUMBER, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        Student model = getModel(Student.class);
        Cnd cnd = Cnd.where(Student.APP_ID, model.getApp_id()).andAllowEmpty(Student.STUDENT_NAME, model.getStudent_name()).andAllowEmpty(Student.STUDENT_NUMBER, model.getStudent_number());

        Integer resultCount = StudentService.me.count(cnd);
        List<Student> resultList = StudentService.me.list(cnd.paginate(getM(), getN()));

        validateResponse(Student.STUDENT_ID, Student.STUDENT_NAME, Student.STUDENT_NUMBER, Student.STUDENT_SEX, Student.SYSTEM_VERSION);

        renderSuccessJson(resultCount, resultList);
    }

    @ActionKey("/admin/student/find")
    public void find() {
        validateRequest(Student.STUDENT_ID);

        Student model = getModel(Student.class);

        Student result = StudentService.me.findById(model.getStudent_id());

        validateResponse(Student.USER_ID, Student.CLAZZ_ID, Student.STUDENT_NAME, Student.STUDENT_NUMBER, Student.STUDENT_SEX, Student.SYSTEM_VERSION);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/student/save")
    public void save() {
        validateRequest(Student.USER_ID, Student.CLAZZ_ID, Student.STUDENT_NAME, Student.STUDENT_NUMBER, Student.STUDENT_SEX);

        Student model = getModel(Student.class);
        model.setStudent_id(Util.getRandomUUID());

        Boolean result = StudentService.me.save(model);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/student/update")
    public void update() {
        validateRequest(Student.STUDENT_ID, Student.USER_ID, Student.CLAZZ_ID, Student.STUDENT_NAME, Student.STUDENT_NUMBER, Student.STUDENT_SEX, Student.SYSTEM_VERSION);

        Student model = getModel(Student.class);

        Boolean result = StudentService.me.update(model, Cnd.where(model.STUDENT_ID, model.getStudent_id()).and(Student.SYSTEM_VERSION, model.getSystem_version()));

        renderSuccessJson(result);
    }

    @ActionKey("/admin/student/delete")
    public void delete() {
        validateRequest(Student.STUDENT_ID, Student.SYSTEM_VERSION);

        Student model = getModel(Student.class);

        Boolean result = StudentService.me.delete(model, Cnd.where(model.STUDENT_ID, model.getStudent_id()).and(Student.SYSTEM_VERSION, model.getSystem_version()));

        renderSuccessJson(result);
    }

}