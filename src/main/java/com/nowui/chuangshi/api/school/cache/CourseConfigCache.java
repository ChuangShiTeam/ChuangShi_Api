package com.nowui.chuangshi.api.school.cache;

import com.nowui.chuangshi.api.school.dao.CourseConfigDao;
import com.nowui.chuangshi.api.school.model.CourseConfig;
import com.nowui.chuangshi.common.cache.Cache;

public class CourseConfigCache extends Cache {

    public static final String COURSE_CONFIG_ITEM_CACHE = "course_config_item_cache";

    public CourseConfigCache() {
        setDao(new CourseConfigDao());

        setItemCache(COURSE_CONFIG_ITEM_CACHE, CourseConfig.COURSE_CONFIG_ID);
    }

}