package com.nowui.chuangshi.api.xietong.service;

import com.nowui.chuangshi.api.xietong.dao.XietongCourseDao;
import com.nowui.chuangshi.api.xietong.model.XietongCourse;
import com.nowui.chuangshi.common.service.Service;
import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.util.CacheUtil;

import java.util.List;

public class XietongCourseService extends Service {

    public static final XietongCourseService instance = new XietongCourseService();
    private final String XIETONG_COURSE_ITEM_CACHE = "xietong_course_item_cache";
    private final XietongCourseDao xietongCourseDao = new XietongCourseDao();

    public Integer adminCount(String app_id, String course_teacher, String course_name) {
        Cnd cnd = new Cnd();        
        cnd.where(XietongCourse.SYSTEM_STATUS, true);
        cnd.and(XietongCourse.APP_ID, app_id);
        cnd.andAllowEmpty(XietongCourse.COURSE_TEACHER, course_teacher);
        cnd.andAllowEmpty(XietongCourse.COURSE_NAME, course_name);

        Integer count = xietongCourseDao.count(cnd);
        return count;
    }

    public List<XietongCourse> adminList(String app_id, String course_teacher, String course_name, Integer m, Integer n) {
        Cnd cnd = new Cnd();        
        cnd.where(XietongCourse.SYSTEM_STATUS, true);
        cnd.and(XietongCourse.APP_ID, app_id);
        cnd.andAllowEmpty(XietongCourse.COURSE_TEACHER, course_teacher);
        cnd.andAllowEmpty(XietongCourse.COURSE_NAME, course_name);
        cnd.paginate(m, n);

        List<XietongCourse> xietong_courseList = xietongCourseDao.primaryKeyList(cnd);
        for (XietongCourse xietong_course : xietong_courseList) {
            xietong_course.put(find(xietong_course.getCourse_id()));
        }
        return xietong_courseList;
    }

    public XietongCourse find(String course_id) {
        XietongCourse xietong_course = CacheUtil.get(XIETONG_COURSE_ITEM_CACHE, course_id);

        if (xietong_course == null) {
            xietong_course = xietongCourseDao.find(course_id);

            CacheUtil.put(XIETONG_COURSE_ITEM_CACHE, course_id, xietong_course);
        }

        return xietong_course;
    }

    public Boolean save(XietongCourse xietong_course, String system_create_user_id) {
        Boolean success = xietongCourseDao.save(xietong_course, system_create_user_id);
        return success;
    }

    public Boolean update(XietongCourse xietong_course, String course_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();        
        cnd.where(XietongCourse.SYSTEM_STATUS, true);
        cnd.and(XietongCourse.COURSE_ID, course_id);
        cnd.and(XietongCourse.SYSTEM_VERSION, system_version);

        Boolean success = xietongCourseDao.update(xietong_course, system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(XIETONG_COURSE_ITEM_CACHE, course_id);
        }

        return success;
    }

    public Boolean delete(String course_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();        
        cnd.where(XietongCourse.SYSTEM_STATUS, true);
        cnd.and(XietongCourse.COURSE_ID, course_id);
        cnd.and(XietongCourse.SYSTEM_VERSION, system_version);

        Boolean success = xietongCourseDao.delete(system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(XIETONG_COURSE_ITEM_CACHE, course_id);
        }

        return success;
    }

}