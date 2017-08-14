package com.nowui.chuangshi.api.course.dao;

import com.nowui.chuangshi.api.course.model.CourseApplyHistory;
import com.nowui.chuangshi.common.dao.Dao;

public class CourseApplyHistoryDao extends Dao {

    public CourseApplyHistoryDao() {
        setModel(new CourseApplyHistory());
    }

}