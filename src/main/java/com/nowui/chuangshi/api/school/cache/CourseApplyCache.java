package com.nowui.chuangshi.api.school.cache;

import com.nowui.chuangshi.api.school.dao.CourseApplyDao;
import com.nowui.chuangshi.api.school.model.CourseApply;
import com.nowui.chuangshi.common.cache.Cache;

public class CourseApplyCache extends Cache {

    public static final String COURSE_APPLY_ITEM_CACHE = "course_apply_item_cache";

    public CourseApplyCache() {
        setDao(new CourseApplyDao());

        setItemCache(COURSE_APPLY_ITEM_CACHE, CourseApply.COURSE_APPLY_ID);
    }

}