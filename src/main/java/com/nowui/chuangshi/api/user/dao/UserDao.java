package com.nowui.chuangshi.api.user.dao;

import java.util.Date;

import com.jfinal.plugin.activerecord.Db;
import com.nowui.chuangshi.api.user.model.User;
import com.nowui.chuangshi.api.xietong.model.XietongStudent;
import com.nowui.chuangshi.common.dao.Dao;
import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.constant.Constant;

public class UserDao extends Dao {

    public UserDao() {
        setModel(new User());
    }
    
    public Boolean userTypeDelete(String user_type, String system_update_user_id) {
        Cnd cnd = new Cnd();
        cnd.where(Constant.SYSTEM_STATUS, true);
        cnd.and(User.USER_TYPE, user_type);
        
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