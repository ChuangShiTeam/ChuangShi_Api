package com.nowui.chuangshi.api.xietong.service;

import java.util.List;

import com.nowui.chuangshi.api.user.model.User;
import com.nowui.chuangshi.api.xietong.dao.XietongStudentDao;
import com.nowui.chuangshi.api.xietong.model.XietongStudent;
import com.nowui.chuangshi.common.service.Service;
import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.service.UserService;
import com.nowui.chuangshi.type.UserType;
import com.nowui.chuangshi.util.CacheUtil;
import com.nowui.chuangshi.util.Util;

public class XietongStudentService extends Service {

    public static final XietongStudentService instance = new XietongStudentService();
    private final String XIETONG_STUDENT_ITEM_CACHE = "xietong_student_item_cache";
    private final XietongStudentDao xietongStudentDao = new XietongStudentDao();
    private static final UserService userService = new UserService();

    public Integer adminCount(String app_id, String student_name, String clazz_id) {
        Cnd cnd = new Cnd();        
        cnd.where(XietongStudent.SYSTEM_STATUS, true);
        cnd.and(XietongStudent.APP_ID, app_id);
        cnd.andAllowEmpty(XietongStudent.STUDENT_NAME, student_name);
        cnd.andAllowEmpty(XietongStudent.CLAZZ_ID, clazz_id);

        Integer count = xietongStudentDao.count(cnd);
        return count;
    }

    public List<XietongStudent> adminList(String app_id, String student_name, String clazz_id, Integer m, Integer n) {
        Cnd cnd = new Cnd();        
        cnd.where(XietongStudent.SYSTEM_STATUS, true);
        cnd.and(XietongStudent.APP_ID, app_id);
        cnd.andAllowEmpty(XietongStudent.STUDENT_NAME, student_name);
        cnd.andAllowEmpty(XietongStudent.CLAZZ_ID, clazz_id);
        cnd.paginate(m, n);

        List<XietongStudent> xietong_studentList = xietongStudentDao.primaryKeyList(cnd);
        for (XietongStudent xietong_student : xietong_studentList) {
            xietong_student.put(find(xietong_student.getStudent_id()));
        }
        return xietong_studentList;
    }
    
    public List<XietongStudent> mobileList(String app_id, String student_name, Integer m, Integer n) {
        Cnd cnd = new Cnd();        
        cnd.where(XietongStudent.SYSTEM_STATUS, true);
        cnd.and(XietongStudent.APP_ID, app_id);
        cnd.andAllowEmpty(XietongStudent.STUDENT_NAME, student_name);
        cnd.paginate(m, n);

        List<XietongStudent> xietong_studentList = xietongStudentDao.primaryKeyList(cnd);
        for (XietongStudent xietong_student : xietong_studentList) {
            xietong_student.put(find(xietong_student.getStudent_id()));
        }
        return xietong_studentList;
    }

    public XietongStudent find(String student_id) {
        XietongStudent xietong_student = CacheUtil.get(XIETONG_STUDENT_ITEM_CACHE, student_id);

        if (xietong_student == null) {
            xietong_student = xietongStudentDao.find(student_id);

            CacheUtil.put(XIETONG_STUDENT_ITEM_CACHE, student_id, xietong_student);
        }

        return xietong_student;
    }

    public Boolean save(XietongStudent xietong_student, String system_create_user_id) {
        Boolean success = xietongStudentDao.save(xietong_student, system_create_user_id);
        return success;
    }
    
    public Boolean save(XietongStudent xietong_student, User user, String request_user_id) {
        xietong_student.setStudent_id(Util.getRandomUUID());
        xietong_student.setSystem_create_user_id(request_user_id);
        xietong_student.setSystem_update_user_id(request_user_id);
        Boolean success = this.save(xietong_student, request_user_id);
        if (success) {
            String user_id = Util.getRandomUUID();
            userService.saveByUser_idAndApp_idAndObject_idAndUser_typeAndUser_nameAndUser_accountAndUser_password(user_id, xietong_student.getApp_id(), xietong_student.getStudent_id(), UserType.STUDENT.getKey(), xietong_student.getStudent_name(), xietong_student.getStudent_number(), user.getUser_password(), request_user_id);
            //this.updateByStudent_idAndUser_id(xietong_student.getStudent_id(), request_user_id, request_user_id, 1);
        }
        return success;
    }

    public Boolean update(XietongStudent xietong_student, String student_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();        
        cnd.where(XietongStudent.SYSTEM_STATUS, true);
        cnd.and(XietongStudent.STUDENT_ID, student_id);
        cnd.and(XietongStudent.SYSTEM_VERSION, system_version);

        Boolean success = xietongStudentDao.update(xietong_student, system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(XIETONG_STUDENT_ITEM_CACHE, student_id);
        }

        return success;
    }
    
    public Boolean update(XietongStudent xietong_student, User user, String request_user_id, Integer system_version) {
        boolean success = this.update(xietong_student, xietong_student.getStudent_id(), request_user_id, system_version);
        
        if (success) {
            userService.updateByUser_nameAndUser_accountAndUser_password(user.getUser_id(), xietong_student.getStudent_name(), xietong_student.getStudent_number(), user.getUser_password(), request_user_id);
        }
        
        return success;
    }
    
    public Boolean updateByPassword(String user_id, String user_password, String request_user_id) {
        return userService.updateByUser_password(user_id, user_password, request_user_id);
    }
    
    /*public Boolean updateByStudent_idAndUser_id(String student_id, String user_id, String system_update_user_id, Integer system_version) {
        Boolean success = xietongStudentDao.updateByStudent_idAndUser_id(student_id, user_id, system_update_user_id, system_version);
        
        if (success) {
            CacheUtil.remove(XIETONG_STUDENT_ITEM_CACHE, student_id);
        }

        return success;
    }*/

    public Boolean delete(String student_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();        
        cnd.where(XietongStudent.SYSTEM_STATUS, true);
        cnd.and(XietongStudent.STUDENT_ID, student_id);
        cnd.and(XietongStudent.SYSTEM_VERSION, system_version);

        Boolean success = xietongStudentDao.delete(system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(XIETONG_STUDENT_ITEM_CACHE, student_id);
        }

        return success;
    }
    
    public Boolean deleteAll(String system_update_user_id) {
        
        Boolean result = xietongStudentDao.deleteAll(system_update_user_id);

        if(result) {
            userService.deleteByUser_type(UserType.STUDENT.getKey(), system_update_user_id);
        }

        return result;
    }

}