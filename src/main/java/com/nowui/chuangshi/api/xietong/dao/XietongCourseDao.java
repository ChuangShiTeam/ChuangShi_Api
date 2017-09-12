package com.nowui.chuangshi.api.xietong.dao;

import java.util.Date;
import java.util.List;

import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.SqlPara;
import com.nowui.chuangshi.api.xietong.model.XietongCourse;
import com.nowui.chuangshi.api.xietong.model.XietongStudent;
import com.nowui.chuangshi.common.dao.Dao;
import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.constant.Constant;

public class XietongCourseDao extends Dao {

    public XietongCourseDao() {
        setModel(new XietongCourse());
    }
    
    public List<XietongCourse> userList(String user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(XietongStudent.USER_ID, user_id);
        SqlPara sqlPara = Db.getSqlPara("xietong_course.userList", sqlMap);

        logSql("xietong_course", "userList", sqlPara);

        return new XietongCourse().find(sqlPara.getSql(), sqlPara.getPara());
    }
    
    public Boolean allDelete(String system_update_user_id) {
        Cnd cnd = new Cnd();
        cnd.where(Constant.SYSTEM_STATUS, true);
        
        XietongCourse xietongCourse = new XietongCourse();
        xietongCourse.put(Constant.SYSTEM_UPDATE_USER_ID, system_update_user_id);
        xietongCourse.put(Constant.SYSTEM_UPDATE_TIME, new Date());
        xietongCourse.put(Constant.SYSTEM_STATUS, false);

        xietongCourse.setCriteria(cnd.getCriteria());

        String sql = xietongCourse.buildUpdateSql();

        System.out.println(sql);

        return Db.update(sql) != 0;
    }

}