package com.nowui.chuangshi.api.course.dao;

import com.nowui.chuangshi.api.course.model.CourseConfig;
import com.nowui.chuangshi.common.dao.Dao;

public class CourseConfigDao extends Dao {

    public CourseConfigDao() {
        setModel(new CourseConfig());
    }

}