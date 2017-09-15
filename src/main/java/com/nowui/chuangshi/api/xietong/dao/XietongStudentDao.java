package com.nowui.chuangshi.api.xietong.dao;

import java.util.Date;
import java.util.List;

import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.SqlPara;
import com.nowui.chuangshi.api.xietong.model.XietongStudent;
import com.nowui.chuangshi.common.dao.Dao;
import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.constant.Constant;

public class XietongStudentDao extends Dao {

    public XietongStudentDao() {
        setModel(new XietongStudent());
    }
    
    public List<XietongStudent> list(String app_id, String student_name, String clazz_id, int m, int n) {
        Kv sqlMap = Kv.create();
        sqlMap.put(XietongStudent.APP_ID, app_id);
        sqlMap.put(XietongStudent.STUDENT_NAME, student_name);
        sqlMap.put(XietongStudent.CLAZZ_ID, clazz_id);
        sqlMap.put(Constant.M, m);
        sqlMap.put(Constant.N, n);
        SqlPara sqlPara = Db.getSqlPara("xietong_student.list", sqlMap);
        
        logSql("xietong_student", "list", sqlPara);
        
        return new XietongStudent().find(sqlPara.getSql(), sqlPara.getPara());
    }
    
    public Boolean allDelete(String system_update_user_id) {
        Cnd cnd = new Cnd();
        cnd.where(Constant.SYSTEM_STATUS, true);
        
        XietongStudent xietongStudent = new XietongStudent();
        xietongStudent.put(Constant.SYSTEM_UPDATE_USER_ID, system_update_user_id);
        xietongStudent.put(Constant.SYSTEM_UPDATE_TIME, new Date());
        xietongStudent.put(Constant.SYSTEM_STATUS, false);

        xietongStudent.setCriteria(cnd.getCriteria());

        String sql = xietongStudent.buildUpdateSql();

        System.out.println(sql);

        return Db.update(sql) != 0;
    }
    
}