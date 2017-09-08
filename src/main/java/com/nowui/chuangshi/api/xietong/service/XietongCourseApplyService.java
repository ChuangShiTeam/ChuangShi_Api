package com.nowui.chuangshi.api.xietong.service;

import com.nowui.chuangshi.api.xietong.dao.XietongCourseApplyDao;
import com.nowui.chuangshi.api.xietong.model.XietongCourseApply;
import com.nowui.chuangshi.common.service.Service;
import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.util.CacheUtil;

import java.util.List;

public class XietongCourseApplyService extends Service {

    public static final XietongCourseApplyService instance = new XietongCourseApplyService();
    private final String XIETONG_COURSE_APPLY_ITEM_CACHE = "xietong_course_apply_item_cache";
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

}