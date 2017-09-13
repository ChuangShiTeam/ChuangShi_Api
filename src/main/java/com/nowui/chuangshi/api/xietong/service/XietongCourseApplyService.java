package com.nowui.chuangshi.api.xietong.service;

import java.util.Iterator;
import java.util.List;

import com.nowui.chuangshi.api.xietong.dao.XietongCourseApplyDao;
import com.nowui.chuangshi.api.xietong.model.XietongClazz;
import com.nowui.chuangshi.api.xietong.model.XietongCourse;
import com.nowui.chuangshi.api.xietong.model.XietongCourseApply;
import com.nowui.chuangshi.api.xietong.model.XietongCourseApplyHistory;
import com.nowui.chuangshi.api.xietong.model.XietongStudent;
import com.nowui.chuangshi.common.service.Service;
import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.util.CacheUtil;
import com.nowui.chuangshi.util.Util;

public class XietongCourseApplyService extends Service {

    public static final XietongCourseApplyService instance = new XietongCourseApplyService();
    private final String XIETONG_COURSE_APPLY_ITEM_CACHE = "xietong_course_apply_item_cache";
    private final String XIETONG_COURSE_APPLY_USER_LIST_CACHE = "xietong_course_apply_user_list_cache";
    private final XietongCourseApplyDao xietongCourseApplyDao = new XietongCourseApplyDao();

    public Integer adminCount(String app_id, String course_id, String user_id) {
        Cnd cnd = new Cnd();
        cnd.where(XietongCourseApply.SYSTEM_STATUS, true);
        cnd.and(XietongCourseApply.APP_ID, app_id);
        cnd.andAllowEmpty(XietongCourseApply.COURSE_ID, course_id);
        cnd.andAllowEmpty(XietongCourseApply.USER_ID, user_id);

        Integer count = xietongCourseApplyDao.count(cnd);
        return count;
    }

    public List<XietongCourseApply> adminList(String app_id, String course_id, String user_id, Integer m, Integer n) {
        Cnd cnd = new Cnd();
        cnd.where(XietongCourseApply.SYSTEM_STATUS, true);
        cnd.and(XietongCourseApply.APP_ID, app_id);
        cnd.andAllowEmpty(XietongCourseApply.COURSE_ID, course_id);
        cnd.andAllowEmpty(XietongCourseApply.USER_ID, user_id);
        cnd.paginate(m, n);

        List<XietongCourseApply> xietong_course_applyList = xietongCourseApplyDao.primaryKeyList(cnd);
        for (XietongCourseApply xietong_course_apply : xietong_course_applyList) {
            xietong_course_apply.put(find(xietong_course_apply.getCourse_apply_id()));
        }
        return xietong_course_applyList;
    }
    
   public List<XietongCourseApply> userList(String user_id) {
       List<XietongCourseApply> xietong_course_apply_list = CacheUtil.get(XIETONG_COURSE_APPLY_USER_LIST_CACHE, user_id);
       
       if (xietong_course_apply_list == null) {
           Cnd cnd = new Cnd();
           cnd.select(XietongCourse.TABLE_XIETONG_COURSE + "." + XietongCourse.COURSE_ID);
           cnd.select(XietongCourse.TABLE_XIETONG_COURSE + "." + XietongCourse.COURSE_NAME);
           cnd.select(XietongCourse.TABLE_XIETONG_COURSE + "." + XietongCourse.COURSE_TEACHER);
           cnd.select(XietongCourse.TABLE_XIETONG_COURSE + "." + XietongCourse.COURSE_TIME);
           cnd.select(XietongCourse.TABLE_XIETONG_COURSE + "." + XietongCourse.COURSE_APPLY_LIMIT);
           cnd.select(XietongCourseApply.TABLE_XIETONG_COURSE_APPLY + "." + XietongCourseApply.USER_ID);
           cnd.leftJoin(XietongCourse.TABLE_XIETONG_COURSE, XietongCourse.COURSE_ID, XietongCourseApply.TABLE_XIETONG_COURSE_APPLY, XietongCourseApply.COURSE_ID);
           cnd.where(XietongCourseApply.TABLE_XIETONG_COURSE_APPLY + "." + XietongCourseApply.SYSTEM_STATUS, true);
           cnd.and(XietongCourseApply.TABLE_XIETONG_COURSE_APPLY + "." + XietongCourseApply.USER_ID, user_id);
           cnd.asc(XietongCourse.TABLE_XIETONG_COURSE + "." + XietongCourse.SYSTEM_CREATE_TIME);
           cnd.desc(XietongCourseApply.TABLE_XIETONG_COURSE_APPLY + "." + XietongCourseApply.SYSTEM_CREATE_TIME);
           
           xietong_course_apply_list = xietongCourseApplyDao.list(cnd);
           CacheUtil.put(XIETONG_COURSE_APPLY_USER_LIST_CACHE, user_id, xietong_course_apply_list);
       }
       
       return xietong_course_apply_list;
   }
   
   public List<XietongCourseApply> courseIdAndCourseTimeAndStudentNumberOrderByList() {
       Cnd cnd = new Cnd();
       cnd.select(XietongCourseApply.TABLE_XIETONG_COURSE_APPLY + ".*");
       cnd.select(XietongStudent.TABLE_XIETONG_STUDENT + "." + XietongStudent.STUDENT_NAME);
       cnd.select(XietongStudent.TABLE_XIETONG_STUDENT + "." + XietongStudent.STUDENT_NUMBER);
       cnd.select(XietongStudent.TABLE_XIETONG_STUDENT + "." + XietongStudent.STUDENT_SEX);
       cnd.select(XietongClazz.TABLE_XIETONG_CLAZZ + "." + XietongClazz.CLAZZ_NAME);
       cnd.select(XietongCourse.TABLE_XIETONG_COURSE + "." + XietongCourse.COURSE_NAME);
       cnd.select(XietongCourse.TABLE_XIETONG_COURSE + "." + XietongCourse.COURSE_TIME);
       cnd.select(XietongCourse.TABLE_XIETONG_COURSE + "." + XietongCourse.COURSE_TEACHER);
       cnd.select(XietongCourse.TABLE_XIETONG_COURSE + "." + XietongCourse.COURSE_ADDRESS);
       cnd.leftJoin(XietongStudent.TABLE_XIETONG_STUDENT, XietongStudent.USER_ID, XietongCourseApply.TABLE_XIETONG_COURSE_APPLY, XietongCourseApply.USER_ID);
       cnd.leftJoin(XietongCourse.TABLE_XIETONG_COURSE, XietongCourse.COURSE_ID, XietongCourseApply.TABLE_XIETONG_COURSE_APPLY, XietongCourseApply.COURSE_ID);
       cnd.leftJoin(XietongClazz.TABLE_XIETONG_CLAZZ, XietongClazz.CLAZZ_ID, XietongStudent.TABLE_XIETONG_STUDENT, XietongStudent.CLAZZ_ID);       
       cnd.where(XietongCourseApply.TABLE_XIETONG_COURSE_APPLY + "." + XietongCourseApply.SYSTEM_STATUS, true);
       cnd.asc(XietongCourse.TABLE_XIETONG_COURSE + "." + XietongCourse.COURSE_ID);
       cnd.asc(XietongCourse.TABLE_XIETONG_COURSE + "." + XietongCourse.COURSE_TIME);
       cnd.asc(XietongStudent.TABLE_XIETONG_STUDENT + "." + XietongStudent.STUDENT_NUMBER);
       
       return xietongCourseApplyDao.list(cnd);
   }
   
   public List<XietongCourseApply> clazzNameAndStudentIdAndCourseTimeOrderByList() {
       Cnd cnd = new Cnd();
       cnd.select(XietongCourseApply.TABLE_XIETONG_COURSE_APPLY + ".*");
       cnd.select(XietongStudent.TABLE_XIETONG_STUDENT + "." + XietongStudent.STUDENT_NAME);
       cnd.select(XietongStudent.TABLE_XIETONG_STUDENT + "." + XietongStudent.STUDENT_NUMBER);
       cnd.select(XietongStudent.TABLE_XIETONG_STUDENT + "." + XietongStudent.STUDENT_SEX);
       cnd.select(XietongClazz.TABLE_XIETONG_CLAZZ + "." + XietongClazz.CLAZZ_NAME);
       cnd.select(XietongCourse.TABLE_XIETONG_COURSE + "." + XietongCourse.COURSE_NAME);
       cnd.select(XietongCourse.TABLE_XIETONG_COURSE + "." + XietongCourse.COURSE_TIME);
       cnd.select(XietongCourse.TABLE_XIETONG_COURSE + "." + XietongCourse.COURSE_TEACHER);
       cnd.select(XietongCourse.TABLE_XIETONG_COURSE + "." + XietongCourse.COURSE_ADDRESS);
       cnd.leftJoin(XietongStudent.TABLE_XIETONG_STUDENT, XietongStudent.USER_ID, XietongCourseApply.TABLE_XIETONG_COURSE_APPLY, XietongCourseApply.USER_ID);
       cnd.leftJoin(XietongClazz.TABLE_XIETONG_CLAZZ, XietongClazz.CLAZZ_ID, XietongStudent.TABLE_XIETONG_STUDENT, XietongStudent.CLAZZ_ID);
       cnd.leftJoin(XietongCourse.TABLE_XIETONG_COURSE, XietongCourse.COURSE_ID, XietongCourseApply.TABLE_XIETONG_COURSE_APPLY, XietongCourseApply.COURSE_ID);
       cnd.where(XietongCourseApply.TABLE_XIETONG_COURSE_APPLY + "." + XietongCourseApply.SYSTEM_STATUS, true);
       cnd.asc(XietongClazz.TABLE_XIETONG_CLAZZ + "." + XietongClazz.CLAZZ_NAME);
       cnd.desc(XietongStudent.TABLE_XIETONG_STUDENT + "." + XietongStudent.STUDENT_ID);
       cnd.asc(XietongCourse.TABLE_XIETONG_COURSE + "." + XietongCourse.COURSE_TIME);
       
       return xietongCourseApplyDao.list(cnd);
   }
   
   public Integer courseCount(String course_id) {
       Cnd cnd = new Cnd();
       cnd.where(XietongCourseApply.SYSTEM_STATUS, true);
       cnd.and(XietongCourseApply.COURSE_ID, course_id);
       
       Integer count = xietongCourseApplyDao.count(cnd);
       return count;
   }
   
   public Integer userCount(String user_id) {
       Cnd cnd = new Cnd();
       cnd.where(XietongCourseApply.SYSTEM_STATUS, true);
       cnd.and(XietongCourseApply.USER_ID, user_id);
       
       Integer count = xietongCourseApplyDao.count(cnd);
       return count;
   }

   public Integer courseAndUserCount(String course_id, String user_id) {
       Cnd cnd = new Cnd();
       cnd.where(XietongCourseApply.SYSTEM_STATUS, true);
       cnd.and(XietongCourseApply.COURSE_ID, course_id);
       cnd.and(XietongCourseApply.USER_ID, user_id);
       
       Integer count = xietongCourseApplyDao.count(cnd);
       return count;
   }
   
    public XietongCourseApply find(String course_apply_id) {
        XietongCourseApply xietong_course_apply = CacheUtil.get(XIETONG_COURSE_APPLY_ITEM_CACHE, course_apply_id);

        if (xietong_course_apply == null) {
            xietong_course_apply = xietongCourseApplyDao.find(course_apply_id);

            CacheUtil.put(XIETONG_COURSE_APPLY_ITEM_CACHE, course_apply_id, xietong_course_apply);
        }

        return xietong_course_apply;
    }

    public Boolean save(XietongCourseApply xietong_course_apply, String system_create_user_id) {
        Boolean success = xietongCourseApplyDao.save(xietong_course_apply, system_create_user_id);
        return success;
    }
    
    public boolean save(String course_id, XietongCourse course, String request_user_id, String request_app_id) {
        XietongCourseApply xietong_course_apply = new XietongCourseApply();
        xietong_course_apply.setApp_id(request_app_id);
        xietong_course_apply.setCourse_id(course_id);
        xietong_course_apply.setUser_id(request_user_id);
        String course_apply_id = Util.getRandomUUID();
        xietong_course_apply.setCourse_apply_id(course_apply_id);
        boolean result = save(xietong_course_apply, request_user_id);

        if (result) {
            List<XietongCourseApply> courseApplyList = userList(request_user_id);

            XietongCourseApply course_apply = new XietongCourseApply();
            course_apply.put(XietongCourse.COURSE_ID, course.getCourse_id());
            course_apply.put(XietongCourse.COURSE_NAME, course.getCourse_name());
            course_apply.put(XietongCourse.COURSE_TEACHER, course.getCourse_teacher());
            course_apply.put(XietongCourse.COURSE_TIME, course.getCourse_time());
            course_apply.put(XietongCourse.COURSE_APPLY_LIMIT, course.getCourse_apply_limit());
            course_apply.put(XietongCourse.COURSE_ID, course.getCourse_id());

            course_apply.setCourse_apply_id(course_apply_id);
            course_apply.setUser_id(request_user_id);
            courseApplyList.add(course_apply);
            
            CacheUtil.put(XIETONG_COURSE_APPLY_USER_LIST_CACHE, request_user_id, courseApplyList);
            
            XietongCourseApplyHistory xietong_course_apply_history = new XietongCourseApplyHistory();
            xietong_course_apply_history.setApp_id(request_app_id);
            xietong_course_apply_history.setCourse_apply_history_id(Util.getRandomUUID());
            xietong_course_apply_history.setCourse_id(course_id);
            xietong_course_apply_history.setUser_id(request_user_id);
            xietong_course_apply_history.setCourse_apply_history_status(true);
            XietongCourseApplyHistoryService.instance.save(xietong_course_apply_history, request_user_id);
        }

        return result;
    }

    public Boolean update(XietongCourseApply xietong_course_apply, String course_apply_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(XietongCourseApply.SYSTEM_STATUS, true);
        cnd.and(XietongCourseApply.COURSE_APPLY_ID, course_apply_id);
        cnd.and(XietongCourseApply.SYSTEM_VERSION, system_version);

        Boolean success = xietongCourseApplyDao.update(xietong_course_apply, system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(XIETONG_COURSE_APPLY_ITEM_CACHE, course_apply_id);
        }

        return success;
    }

    public Boolean delete(String course_apply_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(XietongCourseApply.SYSTEM_STATUS, true);
        cnd.and(XietongCourseApply.COURSE_APPLY_ID, course_apply_id);
        cnd.and(XietongCourseApply.SYSTEM_VERSION, system_version);

        Boolean success = xietongCourseApplyDao.delete(system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(XIETONG_COURSE_APPLY_ITEM_CACHE, course_apply_id);
        }

        return success;
    }
    
    public Integer oneDayCount(String user_id, Integer course_time) {
        return xietongCourseApplyDao.oneDayCount(user_id, course_time);
    }
    
    public boolean courseAndUserDelete(String course_id, String user_id, String request_user_id, String request_app_id) {
        
        boolean result = xietongCourseApplyDao.courseAndUserDelete(course_id, user_id, request_user_id);

        if (result) {
            List<XietongCourseApply> courseApplyList = userList(user_id);

            Iterator<XietongCourseApply> iterator = courseApplyList.iterator();
            while (iterator.hasNext()) {
                XietongCourseApply courseApply = iterator.next();
                if (courseApply.getCourse_id().equals(course_id) && courseApply.getUser_id().equals(user_id)) {
                    CacheUtil.remove(XIETONG_COURSE_APPLY_ITEM_CACHE, courseApply.getCourse_apply_id());
                    iterator.remove();
                }
            }

            CacheUtil.put(XIETONG_COURSE_APPLY_USER_LIST_CACHE, user_id, courseApplyList);
            
            XietongCourseApplyHistory xietong_course_apply_history = new XietongCourseApplyHistory();
            xietong_course_apply_history.setApp_id(request_app_id);
            xietong_course_apply_history.setCourse_apply_history_id(Util.getRandomUUID());
            xietong_course_apply_history.setCourse_id(course_id);
            xietong_course_apply_history.setUser_id(user_id);
            xietong_course_apply_history.setCourse_apply_history_status(false);
            XietongCourseApplyHistoryService.instance.save(xietong_course_apply_history, request_user_id);
        }

        return result;
    }
    
    public Boolean allDelete(String system_update_user_id) {
        Boolean result = xietongCourseApplyDao.allDelete(system_update_user_id);
        
        if (result) {
            CacheUtil.removeAll(XIETONG_COURSE_APPLY_ITEM_CACHE);
            CacheUtil.removeAll(XIETONG_COURSE_APPLY_USER_LIST_CACHE);
        }
        return result;
    }

}