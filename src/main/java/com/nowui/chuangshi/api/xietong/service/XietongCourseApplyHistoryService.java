package com.nowui.chuangshi.api.xietong.service;

import com.nowui.chuangshi.api.xietong.dao.XietongCourseApplyHistoryDao;
import com.nowui.chuangshi.api.xietong.model.XietongCourseApplyHistory;
import com.nowui.chuangshi.common.service.Service;
import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.util.CacheUtil;

import java.util.Date;
import java.util.List;

public class XietongCourseApplyHistoryService extends Service {

    public static final XietongCourseApplyHistoryService instance = new XietongCourseApplyHistoryService();
    private final String XIETONG_COURSE_APPLY_HISTORY_ITEM_CACHE = "xietong_course_apply_history_item_cache";
    private final XietongCourseApplyHistoryDao xietongCourseApplyHistoryDao = new XietongCourseApplyHistoryDao();

    public Integer adminCount(String app_id, String user_id) {
        Cnd cnd = new Cnd();
        cnd.where(XietongCourseApplyHistory.SYSTEM_STATUS, true);
        cnd.and(XietongCourseApplyHistory.APP_ID, app_id);
        cnd.andAllowEmpty(XietongCourseApplyHistory.USER_ID, user_id);

        Integer count = xietongCourseApplyHistoryDao.count(cnd);
        return count;
    }

    public List<XietongCourseApplyHistory> adminList(String app_id, String user_id, Integer m, Integer n) {
        Cnd cnd = new Cnd();
        cnd.where(XietongCourseApplyHistory.SYSTEM_STATUS, true);
        cnd.and(XietongCourseApplyHistory.APP_ID, app_id);
        cnd.andAllowEmpty(XietongCourseApplyHistory.USER_ID, user_id);
        cnd.paginate(m, n);

        List<XietongCourseApplyHistory> xietong_course_apply_historyList = xietongCourseApplyHistoryDao.primaryKeyList(cnd);
        for (XietongCourseApplyHistory xietong_course_apply_history : xietong_course_apply_historyList) {
            xietong_course_apply_history.put(find(xietong_course_apply_history.getCourse_apply_history_id()));
        }
        return xietong_course_apply_historyList;
    }

    public XietongCourseApplyHistory find(String course_apply_history_id) {
        XietongCourseApplyHistory xietong_course_apply_history = CacheUtil.get(XIETONG_COURSE_APPLY_HISTORY_ITEM_CACHE, course_apply_history_id);

        if (xietong_course_apply_history == null) {
            xietong_course_apply_history = xietongCourseApplyHistoryDao.find(course_apply_history_id);

            CacheUtil.put(XIETONG_COURSE_APPLY_HISTORY_ITEM_CACHE, course_apply_history_id, xietong_course_apply_history);
        }

        return xietong_course_apply_history;
    }

    public Boolean save(XietongCourseApplyHistory xietong_course_apply_history, String system_create_user_id) {
        Boolean success = xietongCourseApplyHistoryDao.save(xietong_course_apply_history, system_create_user_id);
        return success;
    }

    public Boolean update(XietongCourseApplyHistory xietong_course_apply_history, String course_apply_history_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(XietongCourseApplyHistory.SYSTEM_STATUS, true);
        cnd.and(XietongCourseApplyHistory.COURSE_APPLY_HISTORY_ID, course_apply_history_id);
        cnd.and(XietongCourseApplyHistory.SYSTEM_VERSION, system_version);

        Boolean success = xietongCourseApplyHistoryDao.update(xietong_course_apply_history, system_update_user_id, system_version, cnd);

        if (success) {
            XietongCourseApplyHistory bean = new XietongCourseApplyHistory();
            bean.setApp_id(xietong_course_apply_history.getApp_id());
            bean.setCourse_apply_history_id(course_apply_history_id);
            bean.setCourse_id(xietong_course_apply_history.getCourse_id());
            bean.setUser_id(xietong_course_apply_history.getUser_id());
            bean.setCourse_apply_history_status(xietong_course_apply_history.getCourse_apply_history_status());
            bean.setCourse_apply_history_result(xietong_course_apply_history.getCourse_apply_history_result());
            bean.setSystem_create_time(xietong_course_apply_history.getSystem_create_time());
            bean.setSystem_create_user_id(xietong_course_apply_history.getSystem_create_user_id());
            bean.setSystem_update_user_id(system_update_user_id);
            bean.setSystem_update_time(new Date());
            bean.setSystem_status(xietong_course_apply_history.getSystem_status());
            bean.setSystem_version(system_version + 1);
            CacheUtil.put(XIETONG_COURSE_APPLY_HISTORY_ITEM_CACHE, course_apply_history_id, bean);
        }

        return success;
    }

    public Boolean delete(String course_apply_history_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(XietongCourseApplyHistory.SYSTEM_STATUS, true);
        cnd.and(XietongCourseApplyHistory.COURSE_APPLY_HISTORY_ID, course_apply_history_id);
        cnd.and(XietongCourseApplyHistory.SYSTEM_VERSION, system_version);

        Boolean success = xietongCourseApplyHistoryDao.delete(system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(XIETONG_COURSE_APPLY_HISTORY_ITEM_CACHE, course_apply_history_id);
        }

        return success;
    }

}