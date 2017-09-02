package com.nowui.chuangshi.common.cache;

import com.nowui.chuangshi.common.dao.Dao;
import com.nowui.chuangshi.common.model.Model;
import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.common.sql.Expression;
import com.nowui.chuangshi.util.CacheUtil;

import java.util.List;

public class Cache {

    protected Dao dao;
    private String item_cache_name = "";
    private String item_cache_key = "";
    private Boolean is_item_cache = false;

    public void setItemCache(String name, String key) {
        this.item_cache_name = item_cache_name;
        this.item_cache_key = item_cache_key;
        this.is_item_cache = false;
    }

    public Dao getDao() {
        return dao;
    }

    public void setDao(Dao dao) {
        this.dao = dao;
    }

    private Boolean isExitKey(Model model, String key) {
        return false;
    }

    public Integer count(Cnd cnd) {
        return dao.count(cnd);
    }

//    public Integer count(Model model) {
//        return dao.count(model);
//    }
//
//    public Integer count(String key, Object value) {
//        return dao.count(key, value);
//    }

    public <M> List<M> list(Cnd cnd) {
        return dao.list(cnd);
    }

//    public <M> List<M> list(Model model) {
//        return dao.list(model);
//    }
//
//    public <M> List<M> list(String key, Object value) {
//        return dao.list(key, value);
//    }

//    public <M> M find(Model model) {
//        Boolean is_only_condition = false;
//
//        if (is_item_cache) {
//            is_only_condition = model.isOnlyCondition(item_cache_key);
//        }
//
//        M item;
//        if (is_item_cache && is_only_condition) {
//            item = CacheUtil.get(item_cache_name, item_cache_key);
//
//            if (item == null) {
//                item = dao.find(model);
//
//                CacheUtil.put(item_cache_name, item_cache_key, item);
//            }
//        } else {
//            item = dao.find(model);
//        }
//
//        return item;
//    }
//
//    public <M> M find(String key, Object value) {
//        Boolean is_only_condition = false;
//
//        if (is_item_cache) {
//            is_only_condition = key.equals(item_cache_key);
//        }
//
//        M item;
//        if (is_item_cache && is_only_condition) {
//            item = CacheUtil.get(item_cache_name, item_cache_key);
//
//            if (item == null) {
//                item = dao.find(key, value);
//
//                CacheUtil.put(item_cache_name, item_cache_key, item);
//            }
//        } else {
//            item = dao.find(key, value);
//        }
//
//        return item;
//    }

    public <M> M find(Cnd cnd) {
        Boolean is_only_condition = false;

        if (is_item_cache) {
            if (cnd.getCriteria().getConditionList().size() == 0) {
                Expression expression = cnd.getCriteria().getConditionList().get(0).getExpression();
                if (expression != null) {
                    if (expression.getKey().equals(item_cache_key)) {
                        is_only_condition = true;
                    }
                }
            }
        }

        M item;
        if (is_item_cache && is_only_condition) {
            item = CacheUtil.get(item_cache_name, item_cache_key);

            if (item == null) {
                item = dao.find(cnd);

                CacheUtil.put(item_cache_name, item_cache_key, item);
            }
        } else {
            item = dao.find(cnd);
        }

        return item;
    }

    public <M> M findById(String id) {
        M item;

        if (is_item_cache) {
            item = CacheUtil.get(item_cache_name, item_cache_key);

            if (item == null) {
                item = dao.find(id);

                CacheUtil.put(item_cache_name, item_cache_key, item);
            }
        } else {
            item = dao.find(id);
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

//    public Boolean update(Model model) {
//        Boolean result = dao.update(model);
//
//        if (is_item_cache && result) {
//            CacheUtil.remove(item_cache_name, item_cache_key);
//        }
//
//        return result;
//    }

    public Boolean update(Model model, Cnd cnd) {
        Boolean result = dao.update(model, cnd);

        if (is_item_cache && result) {
            CacheUtil.remove(item_cache_name, item_cache_key);
        }

        return result;
    }

    public Boolean delete(Cnd cnd) {
        Boolean result = dao.delete(cnd);

        if (is_item_cache && result) {
            CacheUtil.remove(item_cache_name, item_cache_key);
        }

        return result;
    }

}
