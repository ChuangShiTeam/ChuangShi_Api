package com.nowui.chuangshi.api.course.dao;

import com.nowui.chuangshi.api.course.model.CourseStudent;
import com.nowui.chuangshi.common.dao.Dao;

public class CourseStudentDao extends Dao {

    public CourseStudentDao() {
        setModel(new CourseStudent());
    }

}