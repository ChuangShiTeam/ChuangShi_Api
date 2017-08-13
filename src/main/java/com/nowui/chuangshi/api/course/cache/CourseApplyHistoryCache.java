package com.nowui.chuangshi.api.course.cache;

import com.nowui.chuangshi.api.course.dao.CourseApplyHistoryDao;
import com.nowui.chuangshi.api.course.model.CourseApplyHistory;
import com.nowui.chuangshi.common.cache.Cache;

public class CourseApplyHistoryCache extends Cache {

    public static final String COURSE_APPLY_HISTORY_ITEM_CACHE = "course_apply_history_item_cache";

    public CourseApplyHistoryCache() {
        setDao(new CourseApplyHistoryDao());

        setItemCache(COURSE_APPLY_HISTORY_ITEM_CACHE, CourseApplyHistory.COURSE_APPLY_HISTORY_ID);
    }

}