package com.nowui.chuangshi.api.feijiu.service;

import com.nowui.chuangshi.api.feijiu.dao.FeijiuFastProductDao;
import com.nowui.chuangshi.api.feijiu.model.FeijiuFastProduct;
import com.nowui.chuangshi.common.service.Service;
import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.util.CacheUtil;

import java.util.List;

public class FeijiuFastProductService extends Service {

    public static final FeijiuFastProductService instance = new FeijiuFastProductService();
    private final String FEIJIU_FAST_PRODUCT_ITEM_CACHE = "feijiu_fast_product_item_cache";
    private final FeijiuFastProductDao feijiuFastProductDao = new FeijiuFastProductDao();

    public Integer adminCount(String app_id, String product_name) {
        Cnd cnd = Cnd.where(FeijiuFastProduct.SYSTEM_STATUS, true);
        cnd.and(FeijiuFastProduct.APP_ID, app_id);
        cnd.andAllowEmpty(FeijiuFastProduct.PRODUCT_NAME, product_name);

        Integer count = feijiuFastProductDao.count(cnd);
        return count;
    }

    public List<FeijiuFastProduct> adminList(String app_id, String product_name, Integer m, Integer n) {
        Cnd cnd = Cnd.where(FeijiuFastProduct.SYSTEM_STATUS, true);
        cnd.and(FeijiuFastProduct.APP_ID, app_id);
        cnd.andAllowEmpty(FeijiuFastProduct.PRODUCT_NAME, product_name);
        cnd.asc(FeijiuFastProduct.PRODUCT_SORT).paginate(m, n);

        List<FeijiuFastProduct> feijiuFastProductList = feijiuFastProductDao.primaryKeyList(cnd);
        for (FeijiuFastProduct feijiuFastProduct : feijiuFastProductList) {
            feijiuFastProduct.put(find(feijiuFastProduct.getProduct_id()));
        }
        return feijiuFastProductList;
    }

    public List<FeijiuFastProduct> appList(String app_id) {
        Cnd cnd = Cnd.where(FeijiuFastProduct.SYSTEM_STATUS, true);
        cnd.and(FeijiuFastProduct.APP_ID, app_id);
        cnd.asc(FeijiuFastProduct.PRODUCT_SORT);

        List<FeijiuFastProduct> feijiuFastProductList = feijiuFastProductDao.primaryKeyList(cnd);
        for (FeijiuFastProduct feijiuFastProduct : feijiuFastProductList) {
            feijiuFastProduct.put(find(feijiuFastProduct.getProduct_id()));
        }
        return feijiuFastProductList;
    }

    public FeijiuFastProduct find(String product_id) {
        FeijiuFastProduct feijiuFastProduct = CacheUtil.get(FEIJIU_FAST_PRODUCT_ITEM_CACHE, product_id);

        if (feijiuFastProduct == null) {
            feijiuFastProduct = feijiuFastProductDao.find(product_id);

            CacheUtil.put(FEIJIU_FAST_PRODUCT_ITEM_CACHE, product_id, feijiuFastProduct);
        }

        return feijiuFastProduct;
    }

    public Boolean save(FeijiuFastProduct feijiuFastProduct) {
        Boolean success = feijiuFastProductDao.save(feijiuFastProduct);
        return success;
    }

    public Boolean update(FeijiuFastProduct feijiuFastProduct, String product_id, Integer system_version) {
        Cnd cnd = Cnd.where(FeijiuFastProduct.SYSTEM_STATUS, true);
        cnd.and(FeijiuFastProduct.PRODUCT_ID, product_id);
        cnd.and(FeijiuFastProduct.SYSTEM_VERSION, system_version);

        Boolean success = feijiuFastProductDao.update(feijiuFastProduct, cnd);

        if (success) {
            CacheUtil.remove(FEIJIU_FAST_PRODUCT_ITEM_CACHE, product_id);
        }

        return success;
    }

    public Boolean delete(String product_id, Integer system_version) {
        Cnd cnd = Cnd.where(FeijiuFastProduct.SYSTEM_STATUS, true);
        cnd.and(FeijiuFastProduct.PRODUCT_ID, product_id);
        cnd.and(FeijiuFastProduct.SYSTEM_VERSION, system_version);

        Boolean success = feijiuFastProductDao.delete(cnd);

        if (success) {
            CacheUtil.remove(FEIJIU_FAST_PRODUCT_ITEM_CACHE, product_id);
        }

        return success;
    }

}