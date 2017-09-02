package com.nowui.chuangshi.api.feijiu.service;

import com.nowui.chuangshi.api.feijiu.dao.FeijiuFastProductCategoryDao;
import com.nowui.chuangshi.api.feijiu.model.FeijiuFastProductCategory;
import com.nowui.chuangshi.common.service.Service;
import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.util.CacheUtil;

import java.util.List;

public class FeijiuFastProductCategoryService extends Service {

    public static final FeijiuFastProductCategoryService instance = new FeijiuFastProductCategoryService();
    private final String FEIJIU_FAST_PRODUCT_CATEGORY_ITEM_CACHE = "feijiu_fast_product_category_item_cache";
    private final FeijiuFastProductCategoryDao feijiuFastProductCategoryDao = new FeijiuFastProductCategoryDao();

    public Integer adminCount(String app_id, String product_category_name) {
        Integer count = feijiuFastProductCategoryDao.count(Cnd.where(FeijiuFastProductCategory.APP_ID, app_id).andAllowEmpty(FeijiuFastProductCategory.PRODUCT_CATEGORY_NAME, product_category_name));
        return count;
    }

    public List<FeijiuFastProductCategory> adminList(String app_id, String product_category_name, Integer m, Integer n) {
        List<FeijiuFastProductCategory> feijiuFastProductCategoryList = feijiuFastProductCategoryDao.list(Cnd.where(FeijiuFastProductCategory.APP_ID, app_id).andAllowEmpty(FeijiuFastProductCategory.PRODUCT_CATEGORY_NAME, product_category_name).paginate(m, n));
        return feijiuFastProductCategoryList;
    }

    public List<FeijiuFastProductCategory> appList(String app_id) {
        List<FeijiuFastProductCategory> feijiuFastProductCategoryList = feijiuFastProductCategoryDao.list(Cnd.where(FeijiuFastProductCategory.APP_ID, app_id));
        return feijiuFastProductCategoryList;
    }

    public FeijiuFastProductCategory find(String product_category_id) {
        FeijiuFastProductCategory feijiuFastProductCategory = CacheUtil.get(FEIJIU_FAST_PRODUCT_CATEGORY_ITEM_CACHE, product_category_id);

        if (feijiuFastProductCategory == null) {
            feijiuFastProductCategory = feijiuFastProductCategoryDao.find(product_category_id);

            CacheUtil.put(FEIJIU_FAST_PRODUCT_CATEGORY_ITEM_CACHE, product_category_id, feijiuFastProductCategory);
        }

        return feijiuFastProductCategory;
    }

    public Boolean save(FeijiuFastProductCategory feijiuFastProductCategory) {
        Boolean result = feijiuFastProductCategoryDao.save(feijiuFastProductCategory);
        return result;
    }

    public Boolean update(FeijiuFastProductCategory feijiuFastProductCategory, String product_category_id, Integer system_version) {
        Boolean result = feijiuFastProductCategoryDao.update(feijiuFastProductCategory, Cnd.where(FeijiuFastProductCategory.PRODUCT_CATEGORY_ID, product_category_id).and(FeijiuFastProductCategory.SYSTEM_VERSION, system_version));

        if (result) {
            CacheUtil.remove(FEIJIU_FAST_PRODUCT_CATEGORY_ITEM_CACHE, product_category_id);
        }

        return result;
    }

    public Boolean delete(String product_category_id, Integer system_version) {
        Boolean result = feijiuFastProductCategoryDao.delete(Cnd.where(FeijiuFastProductCategory.PRODUCT_CATEGORY_ID, product_category_id).and(FeijiuFastProductCategory.SYSTEM_VERSION, system_version));

        if (result) {
            CacheUtil.remove(FEIJIU_FAST_PRODUCT_CATEGORY_ITEM_CACHE, product_category_id);
        }

        return result;
    }

}