package com.nowui.chuangshi.common.service;
import com.nowui.chuangshi.common.cache.Cache;
import com.nowui.chuangshi.common.model.Model;
import com.nowui.chuangshi.common.sql.Cnd;

import java.util.List;

public class Service {

    protected Cache cache;

    public Cache getCache() {
        return cache;
    }

    public void setCache(Cache cache) {
        this.cache = cache;
    }

    public Integer count(Cnd cnd) {
        return getCache().count(cnd);
    }

    public <M> List<M> list(Cnd cnd) {
        return getCache().list(cnd);
    }

    public <M> M findById(String id) {
        return getCache().findById(id);
    }

    public <M> M find(Cnd cnd) {
        return getCache().find(cnd);
    }

    public Boolean save(Model model) {
        return getCache().save(model);
    }

    public Boolean update(Model model, Cnd cnd) {
        return getCache().update(model, cnd);
    }

    public Boolean delete(Model model, Cnd cnd) {
        return getCache().delete(model, cnd);
    }
}
