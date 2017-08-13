package com.nowui.chuangshi.api.course.cache;

import com.nowui.chuangshi.api.course.dao.CourseStudentDao;
import com.nowui.chuangshi.api.course.model.CourseStudent;
import com.nowui.chuangshi.common.cache.Cache;

public class CourseStudentCache extends Cache {

    public static final String COURSE_STUDENT_ITEM_CACHE = "course_student_item_cache";

    public CourseStudentCache() {
        setDao(new CourseStudentDao());

        setItemCache(COURSE_STUDENT_ITEM_CACHE, CourseStudent.COURSE_STUDENT_ID);
    }

}