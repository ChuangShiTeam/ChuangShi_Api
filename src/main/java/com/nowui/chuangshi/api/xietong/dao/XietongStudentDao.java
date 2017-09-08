package com.nowui.chuangshi.api.xietong.dao;

import java.util.Date;

import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.SqlPara;
import com.nowui.chuangshi.api.xietong.model.XietongStudent;
import com.nowui.chuangshi.common.dao.Dao;
import com.nowui.chuangshi.model.Stock;

public class XietongStudentDao extends Dao {

    public XietongStudentDao() {
        setModel(new XietongStudent());
    }
    
    public Boolean updateUser_id(String student_id, String user_id, String system_update_user_id, Integer system_version) {
        Kv sqlMap = Kv.create();
        sqlMap.put(XietongStudent.STUDENT_ID, student_id);
        sqlMap.put(XietongStudent.USER_ID, user_id);
        sqlMap.put(XietongStudent.SYSTEM_UPDATE_USER_ID, system_update_user_id);
        sqlMap.put(Stock.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(Stock.SYSTEM_VERSION, system_version);
        SqlPara sqlPara = Db.getSqlPara("xietong_student.updateUser_id", sqlMap);

        logSql("xietong_student", "updateUser_id", sqlPara);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }
    
    public Boolean deleteAll(String system_update_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(XietongStudent.SYSTEM_UPDATE_USER_ID, system_update_user_id);
        sqlMap.put(Stock.SYSTEM_UPDATE_TIME, new Date());
        SqlPara sqlPara = Db.getSqlPara("xietong_student.deleteAll", sqlMap);

        logSql("xietong_student", "deleteAll", sqlPara);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

}