package com.nowui.chuangshi.api.teacher.dao;

import com.nowui.chuangshi.api.teacher.model.Teacher;
import com.nowui.chuangshi.common.dao.Dao;

public class TeacherDao extends Dao {

    public TeacherDao() {
        setModel(new Teacher());
    }

}