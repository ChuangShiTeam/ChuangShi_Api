package com.nowui.chuangshi.api.school.dao;

import com.nowui.chuangshi.api.school.model.Course;
import com.nowui.chuangshi.common.dao.Dao;

public class CourseDao extends Dao {

    public CourseDao() {
        setModel(new Course());
    }

}