package com.nowui.chuangshi.common.cache;

import com.nowui.chuangshi.common.dao.Dao;
import com.nowui.chuangshi.common.model.Model;
import com.nowui.chuangshi.util.CacheUtil;

import java.util.List;

public class Cache {

    private final Dao dao = new Dao();
    private String item_cache_name = "aaa";
    private String item_cache_key = "article_id";
    private Boolean is_item_cache = true;

    private Boolean isExitKey(Model model, String key) {
        return false;
    }

    public Integer count(Model model) {
        return dao.count(model);
    }

    public <M> List<M> list(Model model) {
        return dao.list(model);
    }

    public <M> M find(Model model) {
        Boolean is_only_condition = false;
        if (is_item_cache) {
            is_only_condition = model.isOnlyCondition(item_cache_key);
        }

        M item;
        if (is_item_cache && is_only_condition) {
            item = CacheUtil.get(item_cache_name, item_cache_key);

            if (item == null) {
                item = dao.find(model);

                CacheUtil.put(item_cache_name, item_cache_key, item);
            }
        } else {
            item = dao.find(model);
        }

        return item;
    }

    public Boolean save(Model model) {
        Boolean result = dao.save(model);

        if (is_item_cache && result) {
            CacheUtil.remove(item_cache_name, item_cache_key);
        }

        return result;
    }

    public Boolean update(Model model) {
        Boolean result = dao.update(model);

        if (is_item_cache && result) {
            CacheUtil.remove(item_cache_name, item_cache_key);
        }

        return result;
    }

    public Boolean delete(Model model) {
        Boolean result = dao.delete(model);

        if (is_item_cache && result) {
            CacheUtil.remove(item_cache_name, item_cache_key);
        }

        return result;
    }

}
