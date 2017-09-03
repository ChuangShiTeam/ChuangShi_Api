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
        Integer count = feijiuFastProductDao.count(Cnd.where(FeijiuFastProduct.APP_ID, app_id).andAllowEmpty(FeijiuFastProduct.PRODUCT_NAME, product_name));
        return count;
    }

    public List<FeijiuFastProduct> adminList(String app_id, String product_name, Integer m, Integer n) {
        List<FeijiuFastProduct> feijiuFastProductList = feijiuFastProductDao.list(Cnd.where(FeijiuFastProduct.APP_ID, app_id).andAllowEmpty(FeijiuFastProduct.PRODUCT_NAME, product_name).asc(FeijiuFastProduct.PRODUCT_SORT).paginate(m, n));
        return feijiuFastProductList;
    }

    public List<FeijiuFastProduct> appList(String app_id) {
        List<FeijiuFastProduct> feijiuFastProductList = feijiuFastProductDao.list(Cnd.where(FeijiuFastProduct.APP_ID, app_id).asc(FeijiuFastProduct.PRODUCT_SORT));
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
        Boolean result = feijiuFastProductDao.save(feijiuFastProduct);
        return result;
    }

    public Boolean update(FeijiuFastProduct feijiuFastProduct, String product_id, Integer system_version) {
        Boolean result = feijiuFastProductDao.update(feijiuFastProduct, Cnd.where(FeijiuFastProduct.PRODUCT_ID, product_id).and(FeijiuFastProduct.SYSTEM_VERSION, system_version));

        if (result) {
            CacheUtil.remove(FEIJIU_FAST_PRODUCT_ITEM_CACHE, product_id);
        }

        return result;
    }

    public Boolean delete(String product_id, Integer system_version) {
        Boolean result = feijiuFastProductDao.delete(Cnd.where(FeijiuFastProduct.PRODUCT_ID, product_id).and(FeijiuFastProduct.SYSTEM_VERSION, system_version));

        if (result) {
            CacheUtil.remove(FEIJIU_FAST_PRODUCT_ITEM_CACHE, product_id);
        }

        return result;
    }

}