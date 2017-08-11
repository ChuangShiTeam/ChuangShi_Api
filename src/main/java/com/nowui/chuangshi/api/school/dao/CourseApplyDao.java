package com.nowui.chuangshi.api.school.dao;

import com.nowui.chuangshi.api.school.model.CourseApply;
import com.nowui.chuangshi.common.dao.Dao;

public class CourseApplyDao extends Dao {

    public CourseApplyDao() {
        setModel(new CourseApply());
    }

}