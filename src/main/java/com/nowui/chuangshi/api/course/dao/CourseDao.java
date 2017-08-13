package com.nowui.chuangshi.api.course.dao;

import com.nowui.chuangshi.api.course.model.Course;
import com.nowui.chuangshi.common.dao.Dao;

public class CourseDao extends Dao {

    public CourseDao() {
        setModel(new Course());
    }

}