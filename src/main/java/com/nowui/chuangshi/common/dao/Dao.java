package com.nowui.chuangshi.common.dao;

import com.jfinal.plugin.activerecord.Db;
import com.nowui.chuangshi.common.model.Model;
import com.nowui.chuangshi.common.sql.*;
import com.nowui.chuangshi.constant.Constant;

import java.util.Date;
import java.util.List;

public class Dao {

    protected Class<? extends Model> clazz;

    public void setClazz(Class<? extends Model> clazz) {
        this.clazz = clazz;
    }

    public Integer count(Cnd cnd) {
        Model model = null;
        try {
            model = clazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        model.setCriteria(cnd.getCriteria());

        String sql = model.buildCountSql();

        System.out.println(sql);

        Number count = Db.queryFirst(sql);
        return count.intValue();
    }

//    public Integer count(Model model) {
//
//        String sql = model.buildCountSql();
//
//        System.out.println(sql);
//
//        Number count = Db.queryFirst(sql);
//        return count.intValue();
//    }

//    public Integer count(String key, Object value) {
//        Model model = null;
//        try {
//            model = clazz.newInstance();
//        } catch (InstantiationException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }
//        model.where(key, value);
//
//        String sql = model.buildCountSql();
//
//        System.out.println(sql);
//
//        Number count = Db.queryFirst(sql);
//        return count.intValue();
//    }

    public <M> List<M> list(Cnd cnd) {
        Model model = null;
        try {
            model = clazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        model.setCriteria(cnd.getCriteria());

        String sql = model.buildListSql();

        System.out.println(sql);

        return model.find(sql);
    }

//    public <M> List<M> list(Model model) {
//        String sql = model.buildListSql();
//
//        System.out.println(sql);
//
//        return model.find(sql);
//    }
//
//    public <M> List<M> list(String key, Object value) {
//        Model model = null;
//        try {
//            model = clazz.newInstance();
//        } catch (InstantiationException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }
//        model.where(key, value);
//
//        String sql = model.buildListSql();
//
//        System.out.println(sql);
//
//        return model.find(sql);
//    }

//    public <M> M find(Model model) {
//        String sql = model.buildFindSql();
//
//        System.out.println(sql);
//
//        List<M> list = model.find(sql);
//
//        if (list.size() == 0) {
//            return null;
//        } else {
//            return list.get(0);
//        }
//    }
//
//    public <M> M find(String key, Object value) {
//        Model model = null;
//        try {
//            model = clazz.newInstance();
//        } catch (InstantiationException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }
//        model.where(key, value);
//
//        String sql = model.buildFindSql();
//        List<M> list = model.find(sql);
//
//        if (list.size() == 0) {
//            return null;
//        } else {
//            return list.get(0);
//        }
//    }

    public <M> M find(Cnd cnd) {
        Model model = null;
        try {
            model = clazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
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
        Model model = null;
        try {
            model = clazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        model.setPrimaryKeyCriteria(id);

        String sql = model.buildFindSql();
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

//    public Boolean update(Model model) {
//        model.put(Constant.SYSTEM_UPDATE_TIME, new Date());
//
//        String sql = model.buildUpdateSql();
//
//        System.out.println(sql);
//
//        return Db.update(sql) != 0;
//    }

    public Boolean update(Model model, Cnd cnd) {
        model.put(Constant.SYSTEM_UPDATE_TIME, new Date());
        model.setCriteria(cnd.getCriteria());

        String sql = model.buildUpdateSql();

        System.out.println(sql);

        return Db.update(sql) != 0;
    }

//    public Boolean delete(Model model) {
////        model.setUpdate(Constant.SYSTEM_UPDATE_TIME, new Date());
////        model.setUpdate(Constant.SYSTEM_STATUS, false);
//
//        String sql = model.buildDeleteSql();
//
//        System.out.println(sql);
//
//        return Db.update(sql) != 0;
//    }

    public Boolean delete(Model model, Cnd cnd) {
//        model.setUpdate(Constant.SYSTEM_UPDATE_TIME, new Date());
//        model.setUpdate(Constant.SYSTEM_STATUS, false);

        model.setCriteria(cnd.getCriteria());

        String sql = model.buildDeleteSql();

        System.out.println(sql);

        return Db.update(sql) != 0;
    }

}
