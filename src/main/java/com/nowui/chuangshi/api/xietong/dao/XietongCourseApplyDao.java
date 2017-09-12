package com.nowui.chuangshi.api.xietong.dao;

import java.util.Date;
import java.util.List;

import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.SqlPara;
import com.nowui.chuangshi.api.xietong.model.XietongCourse;
import com.nowui.chuangshi.api.xietong.model.XietongCourseApply;
import com.nowui.chuangshi.api.xietong.model.XietongStudent;
import com.nowui.chuangshi.common.dao.Dao;
import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.constant.Constant;

public class XietongCourseApplyDao extends Dao {

    public XietongCourseApplyDao() {
        setModel(new XietongCourseApply());
    }
    
    public Integer oneDayCount(String user_id, Integer course_time) {
        Kv sqlMap = Kv.create();
        sqlMap.put(XietongCourseApply.USER_ID, user_id);
        sqlMap.put(XietongCourse.COURSE_TIME, course_time);
        SqlPara sqlPara = Db.getSqlPara("xietong_course_apply.oneDayCount", sqlMap);

        logSql("xietong_course_apply", "oneDayCount", sqlPara);
        
        Number count = Db.queryFirst(sqlPara.getSql(), sqlPara.getPara());
        
        return count.intValue();
    }
    
    public Boolean courseAndUserDelete(String course_id, String user_id, String system_update_user_id) {
        Cnd cnd = new Cnd();
        cnd.where(XietongCourseApply.SYSTEM_STATUS, true);
        cnd.and(XietongCourseApply.COURSE_ID, course_id);
        cnd.and(XietongCourseApply.USER_ID, user_id);
        
        XietongCourseApply xietongCourseApply = new XietongCourseApply();
        xietongCourseApply.put(Constant.SYSTEM_UPDATE_USER_ID, system_update_user_id);
        xietongCourseApply.put(Constant.SYSTEM_UPDATE_TIME, new Date());
        xietongCourseApply.put(Constant.SYSTEM_STATUS, false);

        xietongCourseApply.setCriteria(cnd.getCriteria());

        String sql = xietongCourseApply.buildUpdateSql();

        System.out.println(sql);

        return Db.update(sql) != 0;
    }
    
    public Boolean allDelete(String system_update_user_id) {
        Cnd cnd = new Cnd();
        cnd.where(Constant.SYSTEM_STATUS, true);
        
        XietongCourseApply xietongCourseApply = new XietongCourseApply();
        xietongCourseApply.put(Constant.SYSTEM_UPDATE_USER_ID, system_update_user_id);
        xietongCourseApply.put(Constant.SYSTEM_UPDATE_TIME, new Date());
        xietongCourseApply.put(Constant.SYSTEM_STATUS, false);

        xietongCourseApply.setCriteria(cnd.getCriteria());

        String sql = xietongCourseApply.buildUpdateSql();

        System.out.println(sql);

        return Db.update(sql) != 0;
    }

}