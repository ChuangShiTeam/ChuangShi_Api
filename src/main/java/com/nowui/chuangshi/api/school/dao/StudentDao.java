package com.nowui.chuangshi.api.school.dao;

import com.nowui.chuangshi.api.school.model.Student;
import com.nowui.chuangshi.common.dao.Dao;

public class StudentDao extends Dao {

    public StudentDao() {
        setModel(new Student());
    }

}