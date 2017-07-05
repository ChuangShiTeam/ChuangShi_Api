package com.nowui.chuangshi.cache;

import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.dao.FeijiuRecommendProductDao;
import com.nowui.chuangshi.model.FeijiuRecommendProduct;
import com.nowui.chuangshi.util.CacheUtil;

import java.util.Date;
import java.util.List;

public class FeijiuRecommendProductCache extends Cache {

    public static final String FEIJIU_RECOMMEND_PRODUCT_BY_FEIJIU_RECOMMEND_PRODUCT_ID_CACHE = "feijiu_recommend_product_by_product_id_cache";

    private FeijiuRecommendProductDao feijiuRecommendProductDao = new FeijiuRecommendProductDao();

    public Integer countByApp_id(String app_id) {
        return feijiuRecommendProductDao.countByApp_id(app_id);
    }

    public Integer countByOrApp_id(String app_id) {
        return feijiuRecommendProductDao.countByOrApp_id(app_id);
    }

    public List<FeijiuRecommendProduct> listByApp_idAndSystem_create_timeAndLimit(String app_id, Date system_create_time, int m, int n) {
        List<FeijiuRecommendProduct> feijiu_recommend_productList = feijiuRecommendProductDao.listByApp_idAndSystem_create_timeAndLimit(app_id, system_create_time, m, n);

        for (FeijiuRecommendProduct feijiu_recommend_product : feijiu_recommend_productList) {
            feijiu_recommend_product.put(findByProduct_id(feijiu_recommend_product.getProduct_id()));
        }

        return feijiu_recommend_productList;
    }

    public List<FeijiuRecommendProduct> listByApp_id(String app_id) {
        List<FeijiuRecommendProduct> feijiu_recommend_productList = feijiuRecommendProductDao.listByApp_id(app_id);

        for (FeijiuRecommendProduct feijiu_recommend_product : feijiu_recommend_productList) {
            feijiu_recommend_product.put(findByProduct_id(feijiu_recommend_product.getProduct_id()));
        }

        return feijiu_recommend_productList;
    }

    public List<FeijiuRecommendProduct> listByApp_idAndLimit(String app_id, int m, int n) {
        List<FeijiuRecommendProduct> feijiu_recommend_productList = feijiuRecommendProductDao.listByApp_idAndLimit(app_id, m, n);

        for (FeijiuRecommendProduct feijiu_recommend_product : feijiu_recommend_productList) {
            feijiu_recommend_product.put(findByProduct_id(feijiu_recommend_product.getProduct_id()));
        }

        return feijiu_recommend_productList;
    }

    public List<FeijiuRecommendProduct> listByOrApp_idAndLimit(String app_id, int m, int n) {
        List<FeijiuRecommendProduct> feijiu_recommend_productList = feijiuRecommendProductDao.listByOrApp_idAndLimit(app_id, m, n);

        for (FeijiuRecommendProduct feijiu_recommend_product : feijiu_recommend_productList) {
            feijiu_recommend_product.put(findByProduct_id(feijiu_recommend_product.getProduct_id()));
        }

        return feijiu_recommend_productList;
    }

    public FeijiuRecommendProduct findByProduct_id(String product_id) {
        FeijiuRecommendProduct feijiu_recommend_product = CacheUtil.get(FEIJIU_RECOMMEND_PRODUCT_BY_FEIJIU_RECOMMEND_PRODUCT_ID_CACHE, product_id);

        if (feijiu_recommend_product == null) {
            feijiu_recommend_product = feijiuRecommendProductDao.findByProduct_id(product_id);

            CacheUtil.put(FEIJIU_RECOMMEND_PRODUCT_BY_FEIJIU_RECOMMEND_PRODUCT_ID_CACHE, product_id, feijiu_recommend_product);
        }

        return feijiu_recommend_product;
    }

    public Boolean save(String product_id, String app_id, String product_name, String product_image, String product_link, String product_content, String system_create_user_id) {
        return feijiuRecommendProductDao.save(product_id, app_id, product_name, product_image, product_link, product_content, system_create_user_id);
    }

    public Boolean updateValidateSystem_version(String product_id, String product_name, String product_image, String product_link, String product_content, String system_update_user_id, Integer system_version) {
        FeijiuRecommendProduct feijiu_recommend_product = findByProduct_id(product_id);
        if (!feijiu_recommend_product.getSystem_version().equals(system_version)) {
            throw new RuntimeException(Constant.ERROR_VERSION);
        }

        boolean result = feijiuRecommendProductDao.update(product_id, product_name, product_image, product_link, product_content, system_update_user_id, system_version);

        if (result) {
            CacheUtil.remove(FEIJIU_RECOMMEND_PRODUCT_BY_FEIJIU_RECOMMEND_PRODUCT_ID_CACHE, product_id);
        }

        return result;
    }

    public Boolean deleteByProduct_idAndSystem_update_user_idValidateSystem_version(String product_id, String system_update_user_id, Integer system_version) {
        FeijiuRecommendProduct feijiu_recommend_product = findByProduct_id(product_id);
        if (!feijiu_recommend_product.getSystem_version().equals(system_version)) {
            throw new RuntimeException(Constant.ERROR_VERSION);
        }

        boolean result = feijiuRecommendProductDao.deleteByProduct_idAndSystem_version(product_id, system_update_user_id, system_version);

        if (result) {
            CacheUtil.remove(FEIJIU_RECOMMEND_PRODUCT_BY_FEIJIU_RECOMMEND_PRODUCT_ID_CACHE, product_id);
        }

        return result;
    }

}