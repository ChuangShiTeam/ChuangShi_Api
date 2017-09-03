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
        Cnd cnd = Cnd.where(FeijiuFastProductCategory.SYSTEM_STATUS, true);
        cnd.and(FeijiuFastProductCategory.APP_ID, app_id);
        cnd.andAllowEmpty(FeijiuFastProductCategory.PRODUCT_CATEGORY_NAME, product_category_name);

        Integer count = feijiuFastProductCategoryDao.count(cnd);
        return count;
    }

    public List<FeijiuFastProductCategory> adminList(String app_id, String product_category_name, Integer m, Integer n) {
        Cnd cnd = Cnd.where(FeijiuFastProductCategory.SYSTEM_STATUS, true);
        cnd.and(FeijiuFastProductCategory.APP_ID, app_id);
        cnd.andAllowEmpty(FeijiuFastProductCategory.PRODUCT_CATEGORY_NAME, product_category_name);
        cnd.paginate(m, n);

        List<FeijiuFastProductCategory> feijiuFastProductCategoryList = feijiuFastProductCategoryDao.primaryKeyList(cnd);
        for (FeijiuFastProductCategory feijiuFastProductCategory : feijiuFastProductCategoryList) {
            feijiuFastProductCategory.put(find(feijiuFastProductCategory.getProduct_category_id()));
        }
        return feijiuFastProductCategoryList;
    }

    public List<FeijiuFastProductCategory> appList(String app_id) {
        Cnd cnd = Cnd.where(FeijiuFastProductCategory.SYSTEM_STATUS, true);
        cnd.and(FeijiuFastProductCategory.APP_ID, app_id);

        List<FeijiuFastProductCategory> feijiuFastProductCategoryList = feijiuFastProductCategoryDao.primaryKeyList(cnd);
        for (FeijiuFastProductCategory feijiuFastProductCategory : feijiuFastProductCategoryList) {
            feijiuFastProductCategory.put(find(feijiuFastProductCategory.getProduct_category_id()));
        }
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
        Boolean success = feijiuFastProductCategoryDao.save(feijiuFastProductCategory);
        return success;
    }

    public Boolean update(FeijiuFastProductCategory feijiuFastProductCategory, String product_category_id, Integer system_version) {
        Cnd cnd = Cnd.where(FeijiuFastProductCategory.SYSTEM_STATUS, true);
        cnd.and(FeijiuFastProductCategory.PRODUCT_CATEGORY_ID, product_category_id);
        cnd.and(FeijiuFastProductCategory.SYSTEM_VERSION, system_version);

        Boolean success = feijiuFastProductCategoryDao.update(feijiuFastProductCategory, cnd);

        if (success) {
            CacheUtil.remove(FEIJIU_FAST_PRODUCT_CATEGORY_ITEM_CACHE, product_category_id);
        }

        return success;
    }

    public Boolean delete(String product_category_id, Integer system_version) {
        Cnd cnd = Cnd.where(FeijiuFastProductCategory.SYSTEM_STATUS, true);
        cnd.and(FeijiuFastProductCategory.PRODUCT_CATEGORY_ID, product_category_id);
        cnd.and(FeijiuFastProductCategory.SYSTEM_VERSION, system_version);

        Boolean success = feijiuFastProductCategoryDao.delete(cnd);

        if (success) {
            CacheUtil.remove(FEIJIU_FAST_PRODUCT_CATEGORY_ITEM_CACHE, product_category_id);
        }

        return success;
    }

}