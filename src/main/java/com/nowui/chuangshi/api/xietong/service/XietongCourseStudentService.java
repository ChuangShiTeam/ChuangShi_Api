package com.nowui.chuangshi.api.xietong.service;

import com.nowui.chuangshi.api.xietong.dao.XietongCourseStudentDao;
import com.nowui.chuangshi.api.xietong.model.XietongCourseStudent;
import com.nowui.chuangshi.common.service.Service;
import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.util.CacheUtil;

import java.util.List;

public class XietongCourseStudentService extends Service {

    public static final XietongCourseStudentService instance = new XietongCourseStudentService();
    private final String XIETONG_COURSE_STUDENT_ITEM_CACHE = "xietong_course_student_item_cache";
    private final XietongCourseStudentDao xietongCourseStudentDao = new XietongCourseStudentDao();

    public Integer adminCount(String app_id, String course_id, String student_id) {
        Cnd cnd = new Cnd();        
        cnd.where(XietongCourseStudent.SYSTEM_STATUS, true);
        cnd.and(XietongCourseStudent.APP_ID, app_id);
        cnd.andAllowEmpty(XietongCourseStudent.COURSE_ID, course_id);
        cnd.andAllowEmpty(XietongCourseStudent.STUDENT_ID, student_id);

        Integer count = xietongCourseStudentDao.count(cnd);
        return count;
    }

    public List<XietongCourseStudent> adminList(String app_id, String course_id, String student_id, Integer m, Integer n) {
        Cnd cnd = new Cnd();        
        cnd.where(XietongCourseStudent.SYSTEM_STATUS, true);
        cnd.and(XietongCourseStudent.APP_ID, app_id);
        cnd.andAllowEmpty(XietongCourseStudent.COURSE_ID, course_id);
        cnd.andAllowEmpty(XietongCourseStudent.STUDENT_ID, student_id);
        cnd.paginate(m, n);

        List<XietongCourseStudent> xietong_course_studentList = xietongCourseStudentDao.primaryKeyList(cnd);
        for (XietongCourseStudent xietong_course_student : xietong_course_studentList) {
            xietong_course_student.put(find(xietong_course_student.getCourse_student_id()));
        }
        return xietong_course_studentList;
    }

    public XietongCourseStudent find(String course_student_id) {
        XietongCourseStudent xietong_course_student = CacheUtil.get(XIETONG_COURSE_STUDENT_ITEM_CACHE, course_student_id);

        if (xietong_course_student == null) {
            xietong_course_student = xietongCourseStudentDao.find(course_student_id);

            CacheUtil.put(XIETONG_COURSE_STUDENT_ITEM_CACHE, course_student_id, xietong_course_student);
        }

        return xietong_course_student;
    }

    public Boolean save(XietongCourseStudent xietong_course_student, String system_create_user_id) {
        Boolean success = xietongCourseStudentDao.save(xietong_course_student, system_create_user_id);
        return success;
    }

    public Boolean update(XietongCourseStudent xietong_course_student, String course_student_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();        
        cnd.where(XietongCourseStudent.SYSTEM_STATUS, true);
        cnd.and(XietongCourseStudent.COURSE_STUDENT_ID, course_student_id);
        cnd.and(XietongCourseStudent.SYSTEM_VERSION, system_version);

        Boolean success = xietongCourseStudentDao.update(xietong_course_student, system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(XIETONG_COURSE_STUDENT_ITEM_CACHE, course_student_id);
        }

        return success;
    }

    public Boolean delete(String course_student_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();       
        cnd.where(XietongCourseStudent.SYSTEM_STATUS, true);
        cnd.and(XietongCourseStudent.COURSE_STUDENT_ID, course_student_id);
        cnd.and(XietongCourseStudent.SYSTEM_VERSION, system_version);

        Boolean success = xietongCourseStudentDao.delete(system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(XIETONG_COURSE_STUDENT_ITEM_CACHE, course_student_id);
        }

        return success;
    }

}