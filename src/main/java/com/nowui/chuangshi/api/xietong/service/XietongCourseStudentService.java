package com.nowui.chuangshi.api.xietong.service;

import java.util.List;

import com.nowui.chuangshi.api.xietong.dao.XietongCourseStudentDao;
import com.nowui.chuangshi.api.xietong.model.XietongClazz;
import com.nowui.chuangshi.api.xietong.model.XietongCourseStudent;
import com.nowui.chuangshi.api.xietong.model.XietongStudent;
import com.nowui.chuangshi.common.service.Service;
import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.type.CourseStudentType;
import com.nowui.chuangshi.util.CacheUtil;
import com.nowui.chuangshi.util.Util;

public class XietongCourseStudentService extends Service {

    public static final XietongCourseStudentService instance = new XietongCourseStudentService();
    private final String XIETONG_COURSE_STUDENT_ITEM_CACHE = "xietong_course_student_item_cache";
    private final String XIETONG_COURSE_STUDENT_BLACK_LIST_CACHE = "xietong_course_student_black_list_cache";
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
    
    public Integer count(String app_id, String course_id, String student_id) {
        Cnd cnd = new Cnd();        
        cnd.where(XietongCourseStudent.SYSTEM_STATUS, true);
        cnd.and(XietongCourseStudent.APP_ID, app_id);
        cnd.andAllowEmpty(XietongCourseStudent.COURSE_ID, course_id);
        cnd.andAllowEmpty(XietongCourseStudent.STUDENT_ID, student_id);

        Integer count = xietongCourseStudentDao.count(cnd);
        return count;
    }
    
    public List<XietongCourseStudent> list(String course_id, String course_student_type) {
        if (course_student_type.equals(CourseStudentType.BLACK.getKey())) {
            List<XietongCourseStudent> courseStudentList = CacheUtil.get(XIETONG_COURSE_STUDENT_BLACK_LIST_CACHE, course_id);

            if (courseStudentList == null) {
                courseStudentList = WhiteOrBlackList(course_id, course_student_type);
                
                CacheUtil.put(XIETONG_COURSE_STUDENT_BLACK_LIST_CACHE, course_id, courseStudentList);
            }

            return courseStudentList;
        } else {
            return WhiteOrBlackList(course_id, course_student_type);
        }
    }
    
    public List<XietongCourseStudent> WhiteOrBlackList(String course_id, String course_student_type) {
        Cnd cnd = new Cnd();
        cnd.select(XietongCourseStudent.TABLE_XIETONG_COURSE_STUDENT + "." + XietongCourseStudent.COURSE_STUDENT_ID);
        cnd.select(XietongCourseStudent.TABLE_XIETONG_COURSE_STUDENT + "." + XietongCourseStudent.STUDENT_ID);
        cnd.select(XietongClazz.TABLE_XIETONG_CLAZZ + "." + XietongClazz.CLAZZ_NAME);
        cnd.select(XietongStudent.TABLE_XIETONG_STUDENT + "." + XietongStudent.STUDENT_NAME);
        cnd.select(XietongStudent.TABLE_XIETONG_STUDENT + "." + XietongStudent.STUDENT_NUMBER);
        cnd.leftJoin(XietongStudent.TABLE_XIETONG_STUDENT, XietongStudent.STUDENT_ID, XietongCourseStudent.TABLE_XIETONG_COURSE_STUDENT, XietongCourseStudent.STUDENT_ID);
        cnd.leftJoin(XietongClazz.TABLE_XIETONG_CLAZZ, XietongClazz.CLAZZ_ID, XietongStudent.TABLE_XIETONG_STUDENT, XietongStudent.CLAZZ_ID);
        cnd.where(XietongCourseStudent.SYSTEM_STATUS, true);
        cnd.and(XietongCourseStudent.COURSE_ID, course_id);
        cnd.and(XietongCourseStudent.COURSE_STUDENT_TYPE, course_student_type);
        cnd.desc(XietongCourseStudent.SYSTEM_CREATE_TIME);
        return xietongCourseStudentDao.list(cnd);
    }
    
    public XietongCourseStudent find(String course_student_id) {
        XietongCourseStudent xietong_course_student = CacheUtil.get(XIETONG_COURSE_STUDENT_ITEM_CACHE, course_student_id);

        if (xietong_course_student == null) {
            xietong_course_student = xietongCourseStudentDao.find(course_student_id);

            CacheUtil.put(XIETONG_COURSE_STUDENT_ITEM_CACHE, course_student_id, xietong_course_student);
        }

        return xietong_course_student;
    }

    public Boolean save(XietongCourseStudent xietong_course_student, String system_create_user_id, String app_id) {
        int count = count(app_id, xietong_course_student.getCourse_id(), xietong_course_student.getStudent_id());

        if (count == 0) {
            xietong_course_student.setCourse_student_id(Util.getRandomUUID());
            return xietongCourseStudentDao.save(xietong_course_student, system_create_user_id);
        }
        
        return false;
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