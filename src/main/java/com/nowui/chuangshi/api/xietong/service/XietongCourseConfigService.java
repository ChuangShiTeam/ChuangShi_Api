package com.nowui.chuangshi.api.xietong.service;

import com.nowui.chuangshi.api.xietong.dao.XietongCourseConfigDao;
import com.nowui.chuangshi.api.xietong.model.XietongCourseConfig;
import com.nowui.chuangshi.common.service.Service;
import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.util.CacheUtil;

import java.util.List;

public class XietongCourseConfigService extends Service {

    public static final XietongCourseConfigService instance = new XietongCourseConfigService();
    private final String XIETONG_COURSE_CONFIG_ITEM_CACHE = "xietong_course_config_item_cache";
    private final XietongCourseConfigDao xietongCourseConfigDao = new XietongCourseConfigDao();

    public Integer adminCount(String app_id, String course_config_apply_start_time, String course_config_apply_end_time) {
        Cnd cnd = new Cnd();        
        cnd.where(XietongCourseConfig.SYSTEM_STATUS, true);
        cnd.and(XietongCourseConfig.APP_ID, app_id);
        cnd.andAllowEmpty(XietongCourseConfig.COURSE_CONFIG_APPLY_START_TIME, course_config_apply_start_time);
        cnd.andAllowEmpty(XietongCourseConfig.COURSE_CONFIG_APPLY_END_TIME, course_config_apply_end_time);

        Integer count = xietongCourseConfigDao.count(cnd);
        return count;
    }

    public List<XietongCourseConfig> adminList(String app_id, String course_config_apply_start_time, String course_config_apply_end_time, Integer m, Integer n) {
        Cnd cnd = new Cnd();        
        cnd.where(XietongCourseConfig.SYSTEM_STATUS, true);
        cnd.and(XietongCourseConfig.APP_ID, app_id);
        cnd.andAllowEmpty(XietongCourseConfig.COURSE_CONFIG_APPLY_START_TIME, course_config_apply_start_time);
        cnd.andAllowEmpty(XietongCourseConfig.COURSE_CONFIG_APPLY_END_TIME, course_config_apply_end_time);
        cnd.paginate(m, n);

        List<XietongCourseConfig> xietong_course_configList = xietongCourseConfigDao.primaryKeyList(cnd);
        for (XietongCourseConfig xietong_course_config : xietong_course_configList) {
            xietong_course_config.put(find(xietong_course_config.getCourse_config_id()));
        }
        return xietong_course_configList;
    }

    public XietongCourseConfig find(String course_config_id) {
        XietongCourseConfig xietong_course_config = CacheUtil.get(XIETONG_COURSE_CONFIG_ITEM_CACHE, course_config_id);

        if (xietong_course_config == null) {
            xietong_course_config = xietongCourseConfigDao.find(course_config_id);

            CacheUtil.put(XIETONG_COURSE_CONFIG_ITEM_CACHE, course_config_id, xietong_course_config);
        }

        return xietong_course_config;
    }

    public Boolean save(XietongCourseConfig xietong_course_config, String system_create_user_id) {
        Boolean success = xietongCourseConfigDao.save(xietong_course_config, system_create_user_id);
        return success;
    }

    public Boolean update(XietongCourseConfig xietong_course_config, String course_config_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();        
        cnd.where(XietongCourseConfig.SYSTEM_STATUS, true);
        cnd.and(XietongCourseConfig.COURSE_CONFIG_ID, course_config_id);
        cnd.and(XietongCourseConfig.SYSTEM_VERSION, system_version);

        Boolean success = xietongCourseConfigDao.update(xietong_course_config, system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(XIETONG_COURSE_CONFIG_ITEM_CACHE, course_config_id);
        }

        return success;
    }

    public Boolean delete(String course_config_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();        
        cnd.where(XietongCourseConfig.SYSTEM_STATUS, true);
        cnd.and(XietongCourseConfig.COURSE_CONFIG_ID, course_config_id);
        cnd.and(XietongCourseConfig.SYSTEM_VERSION, system_version);

        Boolean success = xietongCourseConfigDao.delete(system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(XIETONG_COURSE_CONFIG_ITEM_CACHE, course_config_id);
        }

        return success;
    }

}