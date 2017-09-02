package com.nowui.chuangshi.common.dao;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.SqlPara;
import com.nowui.chuangshi.common.model.Model;
import com.nowui.chuangshi.common.sql.*;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.util.DateUtil;

import java.util.Date;
import java.util.List;

public class Dao {

    private Model model;

    public void setModel(Model model) {
        this.model = model;
    }

    public Integer count(Cnd cnd) {
        this.model.setCriteria(cnd.getCriteria());

        String sql = this.model.buildCountSql();

        System.out.println(sql);

        Number count = Db.queryFirst(sql);
        return count.intValue();
    }

    public <M> List<M> list(Cnd cnd) {
        this.model.setCriteria(cnd.getCriteria());

        String sql = this.model.buildListSql();

        System.out.println(sql);

        return this.model.find(sql);
    }

    public <M> M find(Cnd cnd) {
        this.model.setCriteria(cnd.getCriteria());
        String sql = this.model.buildFindSql();

        System.out.println(sql);

        List<M> list = this.model.find(sql);

        if (list.size() == 0) {
            return null;
        } else {
            return list.get(0);
        }
    }

    public <M> M find(String id) {
        this.model.setPrimaryKeyCriteria(id);

        String sql = this.model.buildFindSql();

        System.out.println(sql);

        List<M> list = this.model.find(sql);

        if (list.size() == 0) {
            return null;
        } else {
            return list.get(0);
        }
    }

    public Boolean save(Model model) {
        model.put(Constant.SYSTEM_CREATE_TIME, new Date());
        model.put(Constant.SYSTEM_UPDATE_TIME, new Date());
        model.put(Constant.SYSTEM_VERSION, 0);
        model.put(Constant.SYSTEM_STATUS, true);

        String sql = model.buildSaveSql();

        System.out.println(sql);

        return Db.update(sql) != 0;
    }

    public Boolean update(Model model, Cnd cnd) {
        model.remove(Constant.SYSTEM_CREATE_USER_ID, Constant.SYSTEM_VERSION);
        model.put(Constant.SYSTEM_UPDATE_TIME, new Date());
        model.setCriteria(cnd.getCriteria());

        String sql = model.buildUpdateSql();

        System.out.println(sql);

        return Db.update(sql) != 0;
    }

    public Boolean delete(Cnd cnd) {
        cnd.set(Constant.SYSTEM_UPDATE_TIME, new Date());
        cnd.set(Constant.SYSTEM_STATUS, false);

        model.setCriteria(cnd.getCriteria());

        String sql = model.buildDeleteSql();

        System.out.println(sql);

        return Db.update(sql) != 0;
    }
    protected void logSql(String sql_table, String sql_action, SqlPara sqlPara) {
        String sql_content = getSql(sqlPara.getSql(), sqlPara.getPara());

        System.out.println(sql_content);
    }

    private String getSql(String sql, Object[] params) {
        // 1 如果没有参数，说明是不是动态SQL语句
        int paramNum = 0;
        if (null != params) {
            paramNum = params.length;
        }
        if (1 > paramNum) {
            return sql;
        }
        // 2 如果有参数，则是动态SQL语句
        StringBuffer returnSQL = new StringBuffer();
        String[] subSQL = sql.split("\\?");
        for (int i = 0; i < paramNum; i++) {
            if (params[i] instanceof Date) {
                returnSQL.append(subSQL[i]).append("'").append(DateUtil.getDateTimeString((java.util.Date) params[i])).append("'");
            } else if (params[i] instanceof Integer) {
                returnSQL.append(subSQL[i]).append(params[i]);
            } else {
                returnSQL.append(subSQL[i]).append("'").append(params[i]).append("'");
            }
        }

        if (subSQL.length > params.length) {
            returnSQL.append(subSQL[subSQL.length - 1]);
        }
        return returnSQL.toString();
    }

}
