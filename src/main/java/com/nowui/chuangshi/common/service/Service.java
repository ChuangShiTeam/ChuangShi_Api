package com.nowui.chuangshi.common.service;
import com.nowui.chuangshi.common.cache.Cache;
import com.nowui.chuangshi.common.model.Model;

import java.util.List;

public class Service {

    private final Cache cache = new Cache();

    public Integer count(Model model) {
        return cache.count(model);
    }

    public <M> List<M> list(Model model) {
        return cache.list(model);
    }

    public <M> M find(Model model) {
        return cache.find(model);
    }

    public Boolean save(Model model) {
        return cache.save(model);
    }

    public Boolean update(Model model) {
        return cache.update(model);
    }

    public Boolean delete(Model model) {
        return cache.delete(model);
    }
}
