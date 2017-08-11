package com.nowui.chuangshi.api.school.dao;

import com.nowui.chuangshi.api.school.model.CourseApplyHistory;
import com.nowui.chuangshi.common.dao.Dao;

public class CourseApplyHistoryDao extends Dao {

    public CourseApplyHistoryDao() {
        setModel(new CourseApplyHistory());
    }

}