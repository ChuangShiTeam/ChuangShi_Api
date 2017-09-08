package com.nowui.chuangshi.api.xietong.service;

import com.nowui.chuangshi.api.xietong.dao.XietongTeacherDao;
import com.nowui.chuangshi.api.xietong.model.XietongTeacher;
import com.nowui.chuangshi.common.service.Service;
import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.util.CacheUtil;

import java.util.List;

public class XietongTeacherService extends Service {

    public static final XietongTeacherService instance = new XietongTeacherService();
    private final String XIETONG_TEACHER_ITEM_CACHE = "xietong_teacher_item_cache";
    private final XietongTeacherDao xietongTeacherDao = new XietongTeacherDao();

    public Integer adminCount(String app_id, String teacher_name) {
        Cnd cnd = new Cnd();        
        cnd.where(XietongTeacher.SYSTEM_STATUS, true);
        cnd.and(XietongTeacher.APP_ID, app_id);
        cnd.andAllowEmpty(XietongTeacher.TEACHER_NAME, teacher_name);

        Integer count = xietongTeacherDao.count(cnd);
        return count;
    }

    public List<XietongTeacher> adminList(String app_id, String teacher_name, Integer m, Integer n) {
        Cnd cnd = new Cnd();        
        cnd.where(XietongTeacher.SYSTEM_STATUS, true);
        cnd.and(XietongTeacher.APP_ID, app_id);
        cnd.andAllowEmpty(XietongTeacher.TEACHER_NAME, teacher_name);
        cnd.paginate(m, n);

        List<XietongTeacher> xietong_teacherList = xietongTeacherDao.primaryKeyList(cnd);
        for (XietongTeacher xietong_teacher : xietong_teacherList) {
            xietong_teacher.put(find(xietong_teacher.getTeacher_id()));
        }
        return xietong_teacherList;
    }

    public XietongTeacher find(String teacher_id) {
        XietongTeacher xietong_teacher = CacheUtil.get(XIETONG_TEACHER_ITEM_CACHE, teacher_id);

        if (xietong_teacher == null) {
            xietong_teacher = xietongTeacherDao.find(teacher_id);

            CacheUtil.put(XIETONG_TEACHER_ITEM_CACHE, teacher_id, xietong_teacher);
        }

        return xietong_teacher;
    }

    public Boolean save(XietongTeacher xietong_teacher, String system_create_user_id) {
        Boolean success = xietongTeacherDao.save(xietong_teacher, system_create_user_id);
        return success;
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