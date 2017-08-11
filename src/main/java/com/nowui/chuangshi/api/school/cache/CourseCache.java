package com.nowui.chuangshi.api.school.cache;

import com.nowui.chuangshi.api.school.dao.CourseDao;
import com.nowui.chuangshi.api.school.model.Course;
import com.nowui.chuangshi.common.cache.Cache;

public class CourseCache extends Cache {

    public static final String COURSE_ITEM_CACHE = "course_item_cache";

    public CourseCache() {
        setDao(new CourseDao());

        setItemCache(COURSE_ITEM_CACHE, Course.COURSE_ID);
    }

}