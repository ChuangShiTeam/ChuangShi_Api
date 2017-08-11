package com.nowui.chuangshi.api.school.cache;

import com.nowui.chuangshi.api.school.dao.StudentDao;
import com.nowui.chuangshi.api.school.model.Student;
import com.nowui.chuangshi.common.cache.Cache;

public class StudentCache extends Cache {

    public static final String STUDENT_ITEM_CACHE = "student_item_cache";

    public StudentCache() {
        setDao(new StudentDao());

        setItemCache(STUDENT_ITEM_CACHE, Student.STUDENT_ID);
    }

}