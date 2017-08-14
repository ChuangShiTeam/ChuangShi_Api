package com.nowui.chuangshi.api.student.dao;

import com.nowui.chuangshi.api.student.model.Student;
import com.nowui.chuangshi.common.dao.Dao;

public class StudentDao extends Dao {

    public StudentDao() {
        setModel(new Student());
    }

}