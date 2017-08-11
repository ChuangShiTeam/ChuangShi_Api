package com.nowui.chuangshi.api.school.cache;

import com.nowui.chuangshi.api.school.dao.TeacherDao;
import com.nowui.chuangshi.api.school.model.Teacher;
import com.nowui.chuangshi.common.cache.Cache;

public class TeacherCache extends Cache {

    public static final String TEACHER_ITEM_CACHE = "teacher_item_cache";

    public TeacherCache() {
        setDao(new TeacherDao());

        setItemCache(TEACHER_ITEM_CACHE, Teacher.TEACHER_ID);
    }

}