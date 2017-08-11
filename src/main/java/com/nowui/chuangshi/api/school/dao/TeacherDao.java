package com.nowui.chuangshi.api.school.dao;

import com.nowui.chuangshi.api.school.model.Teacher;
import com.nowui.chuangshi.common.dao.Dao;

public class TeacherDao extends Dao {

    public TeacherDao() {
        setModel(new Teacher());
    }

}