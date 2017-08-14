package com.nowui.chuangshi.api.course.dao;

import com.nowui.chuangshi.api.course.model.CourseApply;
import com.nowui.chuangshi.common.dao.Dao;

public class CourseApplyDao extends Dao {

    public CourseApplyDao() {
        setModel(new CourseApply());
    }

}