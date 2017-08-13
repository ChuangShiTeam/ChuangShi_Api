package com.nowui.chuangshi.api.course.cache;

import com.nowui.chuangshi.api.course.dao.CourseDao;
import com.nowui.chuangshi.api.course.model.Course;
import com.nowui.chuangshi.common.cache.Cache;

public class CourseCache extends Cache {

    public static final String COURSE_ITEM_CACHE = "course_item_cache";

    public CourseCache() {
        setDao(new CourseDao());

        setItemCache(COURSE_ITEM_CACHE, Course.COURSE_ID);
    }

}