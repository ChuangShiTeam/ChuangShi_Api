package com.nowui.chuangshi.api.xietong.service;

import java.util.List;

import com.nowui.chuangshi.api.file.model.File;
import com.nowui.chuangshi.api.user.model.User;
import com.nowui.chuangshi.api.user.service.UserService;
import com.nowui.chuangshi.api.xietong.dao.XietongTeacherDao;
import com.nowui.chuangshi.api.xietong.model.XietongOrganization;
import com.nowui.chuangshi.api.xietong.model.XietongTeacher;
import com.nowui.chuangshi.api.xietong.model.XietongTeacherCategory;
import com.nowui.chuangshi.common.service.Service;
import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.type.UserType;
import com.nowui.chuangshi.util.CacheUtil;
import com.nowui.chuangshi.util.Util;
import com.nowui.chuangshi.util.ValidateUtil;

public class XietongTeacherService extends Service {

    public static final XietongTeacherService instance = new XietongTeacherService();
    private final String XIETONG_TEACHER_ITEM_CACHE = "xietong_teacher_item_cache";
    private final XietongTeacherDao xietongTeacherDao = new XietongTeacherDao();

    public Integer adminCount(String app_id, String organization_id, String teacher_name, String teacher_number, String teacher_category_id) {
        Cnd cnd = new Cnd();
        cnd.where(XietongTeacher.SYSTEM_STATUS, true);
        cnd.and(XietongTeacher.APP_ID, app_id);
        cnd.andAllowEmpty(XietongTeacher.ORGANIZATION_ID, organization_id);
        cnd.andAllowEmpty(XietongTeacher.TEACHER_NAME, teacher_name);
        cnd.andAllowEmpty(XietongTeacher.TEACHER_NUMBER, teacher_number);
        cnd.andAllowEmpty(XietongTeacher.TEACHER_CATEGORY_ID, teacher_category_id);

        Integer count = xietongTeacherDao.count(cnd);
        return count;
    }
    
    public Integer desktopCount(String app_id, String teacher_category_id) {
        Cnd cnd = new Cnd();
        cnd.where(XietongTeacher.SYSTEM_STATUS, true);
        cnd.and(XietongTeacher.APP_ID, app_id);
        cnd.andAllowEmpty(XietongTeacher.TEACHER_CATEGORY_ID, teacher_category_id);
        
        Integer count = xietongTeacherDao.count(cnd);
        return count;
    }

    public List<XietongTeacher> adminList(String app_id, String organization_id, String teacher_name, String teacher_number, String teacher_category_id, Integer m, Integer n) {
        Cnd cnd = new Cnd();
        cnd.select(XietongOrganization.TABLE_XIETONG_ORGANIZATION + "." + XietongOrganization.ORGANIZATION_NAME);
        cnd.select(XietongTeacherCategory.TABLE_XIETONG_TEACHER_CATEGORY + "." + XietongTeacherCategory.TEACHER_CATEGORY_NAME);
        cnd.leftJoin(XietongOrganization.TABLE_XIETONG_ORGANIZATION, XietongOrganization.ORGANIZATION_ID, XietongTeacher.TABLE_XIETONG_TEACHER, XietongTeacher.ORGANIZATION_ID);
        cnd.leftJoin(XietongTeacherCategory.TABLE_XIETONG_TEACHER_CATEGORY, XietongTeacherCategory.TEACHER_CATEGORY_ID, XietongTeacher.TABLE_XIETONG_TEACHER, XietongTeacher.TEACHER_CATEGORY_ID);
        cnd.where(XietongTeacher.TABLE_XIETONG_TEACHER + "." + XietongTeacher.SYSTEM_STATUS, true);
        cnd.and(XietongTeacher.TABLE_XIETONG_TEACHER + "." + XietongTeacher.APP_ID, app_id);
        cnd.andAllowEmpty(XietongTeacher.TABLE_XIETONG_TEACHER + "." + XietongTeacher.ORGANIZATION_ID, organization_id);
        cnd.andAllowEmpty(XietongTeacher.TABLE_XIETONG_TEACHER + "." + XietongTeacher.TEACHER_NAME, teacher_name);
        cnd.andAllowEmpty(XietongTeacher.TABLE_XIETONG_TEACHER + "." + XietongTeacher.TEACHER_NUMBER, teacher_number);
        cnd.andAllowEmpty(XietongTeacher.TABLE_XIETONG_TEACHER + "." + XietongTeacher.TEACHER_CATEGORY_ID, teacher_category_id);
        cnd.paginate(m, n);

        List<XietongTeacher> xietong_teacherList = xietongTeacherDao.primaryKeyList(cnd);
        for (XietongTeacher xietong_teacher : xietong_teacherList) {
            xietong_teacher.put(find(xietong_teacher.getTeacher_id()));
        }
        return xietong_teacherList;
    }
    
    public List<XietongTeacher> desktopList(String app_id, String teacher_category_id, Integer m, Integer n) {
        Cnd cnd = new Cnd();
        cnd.where(XietongTeacher.SYSTEM_STATUS, true);
        cnd.and(XietongTeacher.APP_ID, app_id);
        cnd.and(XietongTeacher.TEACHER_CATEGORY_ID, teacher_category_id);
        cnd.asc(XietongTeacher.TEACHER_SORT);
        cnd.desc(XietongTeacher.SYSTEM_CREATE_TIME);
        cnd.paginate(m, n);

        List<XietongTeacher> xietong_teacherList = xietongTeacherDao.primaryKeyList(cnd);
        for (XietongTeacher xietong_teacher : xietong_teacherList) {
            xietong_teacher.put(find(xietong_teacher.getTeacher_id()));
        }
        return xietong_teacherList;
    }
    
    public List<XietongTeacher> appList(String app_id) {
        Cnd cnd = new Cnd();
        cnd.where(XietongTeacher.SYSTEM_STATUS, true);
        cnd.and(XietongTeacher.APP_ID, app_id);
        cnd.asc(XietongTeacher.TEACHER_SORT);
        cnd.desc(XietongTeacher.SYSTEM_CREATE_TIME);

        List<XietongTeacher> xietong_teacherList = xietongTeacherDao.primaryKeyList(cnd);
        for (XietongTeacher xietong_teacher : xietong_teacherList) {
            xietong_teacher.put(find(xietong_teacher.getTeacher_id()));
        }
        return xietong_teacherList;
    }

    public List<XietongTeacher> organizationList(String organization_id, Integer m, Integer n) {
        Cnd cnd = new Cnd();
        cnd.where(XietongTeacher.SYSTEM_STATUS, true);
        cnd.and(XietongTeacher.ORGANIZATION_ID, organization_id);
        cnd.asc(XietongTeacher.TEACHER_SORT);
        cnd.desc(XietongTeacher.SYSTEM_CREATE_TIME);
        cnd.paginate(m, n);

        List<XietongTeacher> xietong_teacherList = xietongTeacherDao.primaryKeyList(cnd);
        for (XietongTeacher xietong_teacher : xietong_teacherList) {
            xietong_teacher.put(find(xietong_teacher.getTeacher_id()));
        }
        return xietong_teacherList;
    }

    public List<XietongTeacher> categoryList(String teacher_category_id) {
        Cnd cnd = new Cnd();
        cnd.where(XietongTeacher.SYSTEM_STATUS, true);
        cnd.and(XietongTeacher.TEACHER_CATEGORY_ID, teacher_category_id);
        cnd.asc(XietongTeacher.TEACHER_SORT);
        cnd.desc(XietongTeacher.SYSTEM_CREATE_TIME);

        List<XietongTeacher> xietong_teacherList = xietongTeacherDao.primaryKeyList(cnd);
        for (XietongTeacher xietong_teacher : xietong_teacherList) {
            xietong_teacher.put(find(xietong_teacher.getTeacher_id()));
        }
        return xietong_teacherList;
    }

    public XietongTeacher find(String teacher_id) {
        XietongTeacher xietong_teacher = CacheUtil.get(XIETONG_TEACHER_ITEM_CACHE, teacher_id);

        if (xietong_teacher == null) {
            Cnd cnd = new Cnd();
            cnd.select(File.TABLE_FILE + "." + File.FILE_ID);
            cnd.select(File.TABLE_FILE + "." + File.FILE_PATH);
            cnd.select(File.TABLE_FILE + "." + File.FILE_ORIGINAL_PATH);
            cnd.leftJoin(File.TABLE_FILE, File.FILE_ID, XietongTeacher.TABLE_XIETONG_TEACHER, XietongTeacher.TEACHER_IMAGE);
            cnd.where(XietongTeacher.TABLE_XIETONG_TEACHER + "." + XietongTeacher.SYSTEM_STATUS, true);
            cnd.and(XietongTeacher.TABLE_XIETONG_TEACHER + "." + XietongTeacher.TEACHER_ID, teacher_id);

            xietong_teacher = xietongTeacherDao.find(cnd);
            CacheUtil.put(XIETONG_TEACHER_ITEM_CACHE, teacher_id, xietong_teacher);
        }

        return xietong_teacher;
    }

    public Boolean save(XietongTeacher xietong_teacher, String system_create_user_id) {
        Boolean success = xietongTeacherDao.save(xietong_teacher, system_create_user_id);
        return success;
    }
    
    public Boolean save(XietongTeacher xietong_teacher, User user, String request_user_id) {
        String user_id = Util.getRandomUUID();
        xietong_teacher.setUser_id(user_id);
        xietong_teacher.setTeacher_id(Util.getRandomUUID());
        Boolean result = save(xietong_teacher, request_user_id);

        if (result) {
            UserService.instance.userAccountSave(user_id, xietong_teacher.getApp_id(), xietong_teacher.getTeacher_id(), UserType.TEACHER.getKey(), xietong_teacher.getTeacher_name(), xietong_teacher.getTeacher_number(), user.getUser_password(), request_user_id);
        }

        return result;
    }

    public Boolean update(XietongTeacher xietong_teacher, String teacher_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(XietongTeacher.SYSTEM_STATUS, true);
        cnd.and(XietongTeacher.TEACHER_ID, teacher_id);
        cnd.and(XietongTeacher.SYSTEM_VERSION, system_version);

        Boolean success = xietongTeacherDao.update(xietong_teacher, system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(XIETONG_TEACHER_ITEM_CACHE, teacher_id);
        }

        return success;
    }
    
    public Boolean update(XietongTeacher xietong_teacher, User user, String request_user_id, Integer system_version) {
        boolean success = this.update(xietong_teacher, xietong_teacher.getTeacher_id(), request_user_id, system_version);

        if (success) {
            UserService.instance.userAccountAndNameUpdate(user.getUser_id(), xietong_teacher.getTeacher_number(), xietong_teacher.getTeacher_name(), request_user_id);
            if (!ValidateUtil.isNullOrEmpty(user.getUser_password())) {
                UserService.instance.userPasswordUpdate(user.getUser_id(), user.getUser_password(), request_user_id);
            }
        }

        return success;
    }

    public Boolean delete(String teacher_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(XietongTeacher.SYSTEM_STATUS, true);
        cnd.and(XietongTeacher.TEACHER_ID, teacher_id);
        cnd.and(XietongTeacher.SYSTEM_VERSION, system_version);

        Boolean success = xietongTeacherDao.delete(system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(XIETONG_TEACHER_ITEM_CACHE, teacher_id);
        }

        return success;
    }

}