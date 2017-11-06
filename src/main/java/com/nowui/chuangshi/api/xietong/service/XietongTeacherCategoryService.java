package com.nowui.chuangshi.api.xietong.service;

import com.nowui.chuangshi.api.xietong.dao.XietongTeacherCategoryDao;
import com.nowui.chuangshi.api.xietong.model.XietongTeacherCategory;
import com.nowui.chuangshi.common.service.Service;
import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.util.CacheUtil;

import java.util.List;

public class XietongTeacherCategoryService extends Service {

    public static final XietongTeacherCategoryService instance = new XietongTeacherCategoryService();
    private final String XIETONG_TEACHER_CATEGORY_ITEM_CACHE = "xietong_teacher_category_item_cache";
    private final XietongTeacherCategoryDao xietongTeacherCategoryDao = new XietongTeacherCategoryDao();

    public Integer adminCount(String app_id, String teacher_category_name) {
        Cnd cnd = new Cnd();
        cnd.where(XietongTeacherCategory.SYSTEM_STATUS, true);
        cnd.and(XietongTeacherCategory.APP_ID, app_id);
        cnd.andAllowEmpty(XietongTeacherCategory.TEACHER_CATEGORY_NAME, teacher_category_name);

        Integer count = xietongTeacherCategoryDao.count(cnd);
        return count;
    }

    public List<XietongTeacherCategory> adminList(String app_id, String teacher_category_name, Integer m, Integer n) {
        Cnd cnd = new Cnd();
        cnd.where(XietongTeacherCategory.SYSTEM_STATUS, true);
        cnd.and(XietongTeacherCategory.APP_ID, app_id);
        cnd.andAllowEmpty(XietongTeacherCategory.TEACHER_CATEGORY_NAME, teacher_category_name);
        cnd.asc(XietongTeacherCategory.TEACHER_CATEGORY_SORT);
        cnd.paginate(m, n);

        List<XietongTeacherCategory> xietong_teacher_categoryList = xietongTeacherCategoryDao.primaryKeyList(cnd);
        for (XietongTeacherCategory xietong_teacher_category : xietong_teacher_categoryList) {
            xietong_teacher_category.put(find(xietong_teacher_category.getTeacher_category_id()));
        }
        return xietong_teacher_categoryList;
    }

    public List<XietongTeacherCategory> appList(String app_id) {
        Cnd cnd = new Cnd();
        cnd.where(XietongTeacherCategory.SYSTEM_STATUS, true);
        cnd.and(XietongTeacherCategory.APP_ID, app_id);
        cnd.asc(XietongTeacherCategory.TEACHER_CATEGORY_SORT);

        List<XietongTeacherCategory> xietong_teacher_categoryList = xietongTeacherCategoryDao.primaryKeyList(cnd);
        for (XietongTeacherCategory xietong_teacher_category : xietong_teacher_categoryList) {
            xietong_teacher_category.put(find(xietong_teacher_category.getTeacher_category_id()));
        }
        return xietong_teacher_categoryList;
    }

    public XietongTeacherCategory find(String teacher_category_id) {
        XietongTeacherCategory xietong_teacher_category = CacheUtil.get(XIETONG_TEACHER_CATEGORY_ITEM_CACHE, teacher_category_id);

        if (xietong_teacher_category == null) {
            xietong_teacher_category = xietongTeacherCategoryDao.find(teacher_category_id);

            CacheUtil.put(XIETONG_TEACHER_CATEGORY_ITEM_CACHE, teacher_category_id, xietong_teacher_category);
        }

        return xietong_teacher_category;
    }

    public Boolean save(XietongTeacherCategory xietong_teacher_category, String system_create_user_id) {
        Boolean success = xietongTeacherCategoryDao.save(xietong_teacher_category, system_create_user_id);
        return success;
    }

    public Boolean update(XietongTeacherCategory xietong_teacher_category, String teacher_category_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(XietongTeacherCategory.SYSTEM_STATUS, true);
        cnd.and(XietongTeacherCategory.TEACHER_CATEGORY_ID, teacher_category_id);
        cnd.and(XietongTeacherCategory.SYSTEM_VERSION, system_version);

        Boolean success = xietongTeacherCategoryDao.update(xietong_teacher_category, system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(XIETONG_TEACHER_CATEGORY_ITEM_CACHE, teacher_category_id);
        }

        return success;
    }

    public Boolean delete(String teacher_category_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(XietongTeacherCategory.SYSTEM_STATUS, true);
        cnd.and(XietongTeacherCategory.TEACHER_CATEGORY_ID, teacher_category_id);
        cnd.and(XietongTeacherCategory.SYSTEM_VERSION, system_version);

        Boolean success = xietongTeacherCategoryDao.delete(system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(XIETONG_TEACHER_CATEGORY_ITEM_CACHE, teacher_category_id);
        }

        return success;
    }

}