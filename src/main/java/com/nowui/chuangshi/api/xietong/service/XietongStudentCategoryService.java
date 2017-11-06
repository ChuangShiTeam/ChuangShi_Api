package com.nowui.chuangshi.api.xietong.service;

import com.nowui.chuangshi.api.xietong.dao.XietongStudentCategoryDao;
import com.nowui.chuangshi.api.xietong.model.XietongStudentCategory;
import com.nowui.chuangshi.common.service.Service;
import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.util.CacheUtil;

import java.util.List;

public class XietongStudentCategoryService extends Service {

    public static final XietongStudentCategoryService instance = new XietongStudentCategoryService();
    private final String XIETONG_STUDENT_CATEGORY_ITEM_CACHE = "xietong_student_category_item_cache";
    private final XietongStudentCategoryDao xietongStudentCategoryDao = new XietongStudentCategoryDao();

    public Integer adminCount(String app_id, String studnet_category_name) {
        Cnd cnd = new Cnd();
        cnd.where(XietongStudentCategory.SYSTEM_STATUS, true);
        cnd.and(XietongStudentCategory.APP_ID, app_id);
        cnd.andAllowEmpty(XietongStudentCategory.STUDENT_CATEGORY_NAME, studnet_category_name);

        Integer count = xietongStudentCategoryDao.count(cnd);
        return count;
    }

    public List<XietongStudentCategory> adminList(String app_id, String student_category_name, Integer m, Integer n) {
        Cnd cnd = new Cnd();
        cnd.where(XietongStudentCategory.SYSTEM_STATUS, true);
        cnd.and(XietongStudentCategory.APP_ID, app_id);
        cnd.andAllowEmpty(XietongStudentCategory.STUDENT_CATEGORY_NAME, student_category_name);
        cnd.asc(XietongStudentCategory.STUDENT_CATEGORY_SORT);
        cnd.paginate(m, n);

        List<XietongStudentCategory> xietong_student_categoryList = xietongStudentCategoryDao.primaryKeyList(cnd);
        for (XietongStudentCategory xietong_student_category : xietong_student_categoryList) {
            xietong_student_category.put(find(xietong_student_category.getStudent_category_id()));
        }
        return xietong_student_categoryList;
    }
    
    public List<XietongStudentCategory> appList(String app_id) {
        Cnd cnd = new Cnd();
        cnd.where(XietongStudentCategory.SYSTEM_STATUS, true);
        cnd.and(XietongStudentCategory.APP_ID, app_id);
        cnd.asc(XietongStudentCategory.STUDENT_CATEGORY_SORT);

        List<XietongStudentCategory> xietong_student_categoryList = xietongStudentCategoryDao.primaryKeyList(cnd);
        for (XietongStudentCategory xietong_student_category : xietong_student_categoryList) {
            xietong_student_category.put(find(xietong_student_category.getStudent_category_id()));
        }
        return xietong_student_categoryList;
    }

    public XietongStudentCategory find(String student_category_id) {
        XietongStudentCategory xietong_student_category = CacheUtil.get(XIETONG_STUDENT_CATEGORY_ITEM_CACHE, student_category_id);

        if (xietong_student_category == null) {
            xietong_student_category = xietongStudentCategoryDao.find(student_category_id);

            CacheUtil.put(XIETONG_STUDENT_CATEGORY_ITEM_CACHE, student_category_id, xietong_student_category);
        }

        return xietong_student_category;
    }

    public Boolean save(XietongStudentCategory xietong_student_category, String system_create_user_id) {
        Boolean success = xietongStudentCategoryDao.save(xietong_student_category, system_create_user_id);
        return success;
    }

    public Boolean update(XietongStudentCategory xietong_student_category, String student_category_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(XietongStudentCategory.SYSTEM_STATUS, true);
        cnd.and(XietongStudentCategory.STUDENT_CATEGORY_ID, student_category_id);
        cnd.and(XietongStudentCategory.SYSTEM_VERSION, system_version);

        Boolean success = xietongStudentCategoryDao.update(xietong_student_category, system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(XIETONG_STUDENT_CATEGORY_ITEM_CACHE, student_category_id);
        }

        return success;
    }

    public Boolean delete(String student_category_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(XietongStudentCategory.SYSTEM_STATUS, true);
        cnd.and(XietongStudentCategory.STUDENT_CATEGORY_ID, student_category_id);
        cnd.and(XietongStudentCategory.SYSTEM_VERSION, system_version);

        Boolean success = xietongStudentCategoryDao.delete(system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(XIETONG_STUDENT_CATEGORY_ITEM_CACHE, student_category_id);
        }

        return success;
    }

}