package com.nowui.chuangshi.common.dao;

import com.jfinal.plugin.activerecord.Db;
import com.nowui.chuangshi.common.model.Model;

import java.util.List;

public class Dao {

    public Integer count(Model model) {
        Number count = Db.queryFirst(model.getCountSql());
        return count.intValue();
    }

    public List<Model> list(Model model) {
        return model.find(model.getListSql());
    }

    public List<Model> listWithoutCache(Model model) {
        return model.find(model.getListWithoutCacheSql());
    }

    public Model find(Model model) {
        List<Model> list = model.find(model.getListSql());

        if (list.size() == 0) {
            return null;
        } else {
            return list.get(0);
        }
    }

    public Boolean save(Model model) {
        System.out.println(model.getSaveSql());
        return Db.update(model.getSaveSql()) != 0;
    }

    public Boolean update(Model model) {
        return Db.update(model.getUpdateSql()) != 0;
    }

    public Boolean delete(Model model) {
        return Db.update(model.getUpdateSql()) != 0;
    }

}
