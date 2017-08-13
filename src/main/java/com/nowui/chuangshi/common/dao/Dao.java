package com.nowui.chuangshi.common.dao;

import com.jfinal.plugin.activerecord.Db;
import com.nowui.chuangshi.common.model.Model;
import com.nowui.chuangshi.common.sql.*;
import com.nowui.chuangshi.constant.Constant;

import java.util.Date;
import java.util.List;

public class Dao {

    private Model model;

    public void setModel(Model model) {
        this.model = model;
    }

    public Integer count(Cnd cnd) {
        model.setCriteria(cnd.getCriteria());

        String sql = model.buildCountSql();

        System.out.println(sql);

        Number count = Db.queryFirst(sql);
        return count.intValue();
    }

    public <M> List<M> list(Cnd cnd) {
        model.setCriteria(cnd.getCriteria());

        String sql = model.buildListSql();

        System.out.println(sql);

        return model.find(sql);
    }

    public <M> M find(Cnd cnd) {
        model.setCriteria(cnd.getCriteria());
        String sql = model.buildFindSql();

        System.out.println(sql);

        List<M> list = model.find(sql);

        if (list.size() == 0) {
            return null;
        } else {
            return list.get(0);
        }
    }

    public <M> M findById(String id) {
        model.setPrimaryKeyCriteria(id);

        String sql = model.buildFindSql();

        System.out.println(sql);

        List<M> list = model.find(sql);

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

    public Boolean delete(Model model, Cnd cnd) {
        cnd.set(Constant.SYSTEM_UPDATE_TIME, new Date());
        cnd.set(Constant.SYSTEM_STATUS, false);

        model.setCriteria(cnd.getCriteria());

        String sql = model.buildDeleteSql();

        System.out.println(sql);

        return Db.update(sql) != 0;
    }

}
