package com.nowui.chuangshi.api.school.dao;

import com.nowui.chuangshi.api.school.model.CourseStudent;
import com.nowui.chuangshi.common.dao.Dao;

public class CourseStudentDao extends Dao {

    public CourseStudentDao() {
        setModel(new CourseStudent());
    }

}