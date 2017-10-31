package com.nowui.chuangshi.api.xietong.service;

import com.nowui.chuangshi.api.xietong.dao.TeacherCategoryDao;
import com.nowui.chuangshi.api.xietong.model.TeacherCategory;
import com.nowui.chuangshi.common.service.Service;
import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.util.CacheUtil;

import java.util.List;

public class TeacherCategoryService extends Service {

    public static final TeacherCategoryService instance = new TeacherCategoryService();
    private final String TEACHER_CATEGORY_ITEM_CACHE = "teacher_category_item_cache";
    private final TeacherCategoryDao teacherCategoryDao = new TeacherCategoryDao();

    public Integer adminCount(String app_id, String teacher_category_name) {
        Cnd cnd = new Cnd();
        cnd.where(TeacherCategory.SYSTEM_STATUS, true);
        cnd.and(TeacherCategory.APP_ID, app_id);
        cnd.andAllowEmpty(TeacherCategory.TEACHER_CATEGORY_NAME, teacher_category_name);

        Integer count = teacherCategoryDao.count(cnd);
        return count;
    }

    public List<TeacherCategory> adminList(String app_id, String teacher_category_name, Integer m, Integer n) {
        Cnd cnd = new Cnd();
        cnd.where(TeacherCategory.SYSTEM_STATUS, true);
        cnd.and(TeacherCategory.APP_ID, app_id);
        cnd.andAllowEmpty(TeacherCategory.TEACHER_CATEGORY_NAME, teacher_category_name);
        cnd.paginate(m, n);

        List<TeacherCategory> teacher_categoryList = teacherCategoryDao.primaryKeyList(cnd);
        for (TeacherCategory teacher_category : teacher_categoryList) {
            teacher_category.put(find(teacher_category.getTeacher_category_id()));
        }
        return teacher_categoryList;
    }

    public TeacherCategory find(String teacher_category_id) {
        TeacherCategory teacher_category = CacheUtil.get(TEACHER_CATEGORY_ITEM_CACHE, teacher_category_id);

        if (teacher_category == null) {
            teacher_category = teacherCategoryDao.find(teacher_category_id);

            CacheUtil.put(TEACHER_CATEGORY_ITEM_CACHE, teacher_category_id, teacher_category);
        }

        return teacher_category;
    }

    public Boolean save(TeacherCategory teacher_category, String system_create_user_id) {
        Boolean success = teacherCategoryDao.save(teacher_category, system_create_user_id);
        return success;
    }

    public Boolean update(TeacherCategory teacher_category, String teacher_category_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(TeacherCategory.SYSTEM_STATUS, true);
        cnd.and(TeacherCategory.TEACHER_CATEGORY_ID, teacher_category_id);
        cnd.and(TeacherCategory.SYSTEM_VERSION, system_version);

        Boolean success = teacherCategoryDao.update(teacher_category, system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(TEACHER_CATEGORY_ITEM_CACHE, teacher_category_id);
        }

        return success;
    }

    public Boolean delete(String teacher_category_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(TeacherCategory.SYSTEM_STATUS, true);
        cnd.and(TeacherCategory.TEACHER_CATEGORY_ID, teacher_category_id);
        cnd.and(TeacherCategory.SYSTEM_VERSION, system_version);

        Boolean success = teacherCategoryDao.delete(system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(TEACHER_CATEGORY_ITEM_CACHE, teacher_category_id);
        }

        return success;
    }

}