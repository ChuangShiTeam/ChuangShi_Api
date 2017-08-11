package com.nowui.chuangshi.api.school.dao;

import com.nowui.chuangshi.api.school.model.CourseConfig;
import com.nowui.chuangshi.common.dao.Dao;

public class CourseConfigDao extends Dao {

    public CourseConfigDao() {
        setModel(new CourseConfig());
    }

}