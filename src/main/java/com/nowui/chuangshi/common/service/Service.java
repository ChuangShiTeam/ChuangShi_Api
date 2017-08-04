package com.nowui.chuangshi.common.service;
import com.nowui.chuangshi.common.cache.Cache;
import com.nowui.chuangshi.common.model.Model;

import java.util.List;

public class Service {

    protected Cache cache;

    public Cache getCache() {
        return cache;
    }

    public void setCache(Cache cache) {
        this.cache = cache;
    }

    public Integer count(Model model) {
        return getCache().count(model);
    }

    public Integer count(String key, Object value) {
        return getCache().count(key, value);
    }

    public <M> List<M> list(Model model) {
        return getCache().list(model);
    }

    public <M> List<M> list(String key, Object value) {
        return getCache().list(key, value);
    }

    public <M> M findById(String id) {
        return getCache().findById(id);
    }

    public <M> M find(Model model) {
        return getCache().find(model);
    }

    public Boolean save(Model model) {
        return getCache().save(model);
    }

    public Boolean update(Model model) {
        return getCache().update(model);
    }

    public Boolean delete(Model model) {
        return getCache().delete(model);
    }
}
