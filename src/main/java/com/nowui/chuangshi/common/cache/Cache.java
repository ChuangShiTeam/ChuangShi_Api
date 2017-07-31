package com.nowui.chuangshi.common.cache;

import com.nowui.chuangshi.common.dao.Dao;
import com.nowui.chuangshi.common.model.Model;

import java.util.List;

public class Cache {

    private final Dao dao = new Dao();

    public Integer count(Model model) {
        return dao.count(model);
    }

    public List<Model> list(Model model) {
        return dao.list(model);
    }

    public List<Model> listWithoutCache(Model model) {
        return dao.listWithoutCache(model);
    }

    public Model find(Model model) {
        return dao.find(model);
    }

    public Boolean save(Model model) {
        return dao.save(model);
    }

    public Boolean update(Model model) {
        return dao.update(model);
    }

    public Boolean delete(Model model) {
        return dao.delete(model);
    }

}
