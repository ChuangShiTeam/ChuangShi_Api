package com.nowui.chuangshi.api.course.cache;

import com.nowui.chuangshi.api.course.dao.CourseApplyDao;
import com.nowui.chuangshi.api.course.model.CourseApply;
import com.nowui.chuangshi.common.cache.Cache;

public class CourseApplyCache extends Cache {

    public static final String COURSE_APPLY_ITEM_CACHE = "course_apply_item_cache";

    public CourseApplyCache() {
        setDao(new CourseApplyDao());

        setItemCache(COURSE_APPLY_ITEM_CACHE, CourseApply.COURSE_APPLY_ID);
    }

}