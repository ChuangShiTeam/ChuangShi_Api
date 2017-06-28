package com.nowui.chuangshi.cache;

import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.dao.FeijiuRecommendCustomerDao;
import com.nowui.chuangshi.model.FeijiuRecommendCustomer;
import com.nowui.chuangshi.util.CacheUtil;

import java.util.Date;
import java.util.List;

public class FeijiuRecommendCustomerCache extends Cache {

    public static final String FEIJIU_RECOMMEND_CUSTOMER_BY_FEIJIU_RECOMMEND_CUSTOMER_ID_CACHE = "feijiu_recommend_customer_by_customer_id_cache";

    private FeijiuRecommendCustomerDao feijiuRecommendCustomerDao = new FeijiuRecommendCustomerDao();

    public Integer countByApp_idAndCustomer_name(String app_id, String customer_name, String request_app_id, String request_http_id, String request_user_id) {
        return feijiuRecommendCustomerDao.countByApp_idAndCustomer_name(app_id, customer_name, request_app_id, request_http_id, request_user_id);
    }

    public Integer countByOrApp_id(String app_id, String request_app_id, String request_http_id, String request_user_id) {
        return feijiuRecommendCustomerDao.countByOrApp_id(app_id, request_app_id, request_http_id, request_user_id);
    }

    public List<FeijiuRecommendCustomer> listByApp_idAndSystem_create_timeAndLimit(String app_id, Date system_create_time, int m, int n, String request_app_id, String request_http_id, String request_user_id) {
        List<FeijiuRecommendCustomer> feijiu_recommend_customerList = feijiuRecommendCustomerDao.listByApp_idAndSystem_create_timeAndLimit(app_id, system_create_time, m, n, request_app_id, request_http_id, request_user_id);

        for (FeijiuRecommendCustomer feijiu_recommend_customer : feijiu_recommend_customerList) {
            feijiu_recommend_customer.put(findByCustomer_id(feijiu_recommend_customer.getCustomer_id(), request_app_id, request_http_id, request_user_id));
        }

        return feijiu_recommend_customerList;
    }

    public List<FeijiuRecommendCustomer> listByApp_id(String app_id, String request_app_id, String request_http_id, String request_user_id) {
        List<FeijiuRecommendCustomer> feijiu_recommend_customerList = feijiuRecommendCustomerDao.listByApp_id(app_id, request_app_id, request_http_id, request_user_id);

        for (FeijiuRecommendCustomer feijiu_recommend_customer : feijiu_recommend_customerList) {
            feijiu_recommend_customer.put(findByCustomer_id(feijiu_recommend_customer.getCustomer_id(), request_app_id, request_http_id, request_user_id));
        }

        return feijiu_recommend_customerList;
    }

    public List<FeijiuRecommendCustomer> listByApp_idAndCustomer_nameAndLimit(String app_id, String customer_name, int m, int n, String request_app_id, String request_http_id, String request_user_id) {
        List<FeijiuRecommendCustomer> feijiu_recommend_customerList = feijiuRecommendCustomerDao.listByApp_idAndCustomer_nameAndLimit(app_id, customer_name, m, n, request_app_id, request_http_id, request_user_id);

        for (FeijiuRecommendCustomer feijiu_recommend_customer : feijiu_recommend_customerList) {
            feijiu_recommend_customer.put(findByCustomer_id(feijiu_recommend_customer.getCustomer_id(), request_app_id, request_http_id, request_user_id));
        }

        return feijiu_recommend_customerList;
    }

    public List<FeijiuRecommendCustomer> listByOrApp_idAndLimit(String app_id, int m, int n, String request_app_id, String request_http_id, String request_user_id) {
        List<FeijiuRecommendCustomer> feijiu_recommend_customerList = feijiuRecommendCustomerDao.listByOrApp_idAndLimit(app_id, m, n, request_app_id, request_http_id, request_user_id);

        for (FeijiuRecommendCustomer feijiu_recommend_customer : feijiu_recommend_customerList) {
            feijiu_recommend_customer.put(findByCustomer_id(feijiu_recommend_customer.getCustomer_id(), request_app_id, request_http_id, request_user_id));
        }

        return feijiu_recommend_customerList;
    }

    public FeijiuRecommendCustomer findByCustomer_id(String customer_id, String request_app_id, String request_http_id, String request_user_id) {
        FeijiuRecommendCustomer feijiu_recommend_customer = CacheUtil.get(FEIJIU_RECOMMEND_CUSTOMER_BY_FEIJIU_RECOMMEND_CUSTOMER_ID_CACHE, customer_id);

        if (feijiu_recommend_customer == null) {
            feijiu_recommend_customer = feijiuRecommendCustomerDao.findByCustomer_id(customer_id, request_app_id, request_http_id, request_user_id);

            CacheUtil.put(FEIJIU_RECOMMEND_CUSTOMER_BY_FEIJIU_RECOMMEND_CUSTOMER_ID_CACHE, customer_id, feijiu_recommend_customer);
        }

        return feijiu_recommend_customer;
    }

    public Boolean save(String customer_id, String app_id, String customer_name, String customer_phone, String customer_city, String system_create_user_id, String request_app_id, String request_http_id, String request_user_id) {
        return feijiuRecommendCustomerDao.save(customer_id, app_id, customer_name, customer_phone, customer_city, system_create_user_id, request_app_id, request_http_id, request_user_id);
    }

    public Boolean updateValidateSystem_version(String customer_id, String customer_name, String customer_phone, String customer_city, String system_update_user_id, Integer system_version, String request_app_id, String request_http_id, String request_user_id) {
        FeijiuRecommendCustomer feijiu_recommend_customer = findByCustomer_id(customer_id, request_app_id, request_http_id, request_user_id);
        if (!feijiu_recommend_customer.getSystem_version().equals(system_version)) {
            throw new RuntimeException(Constant.ERROR_VERSION);
        }

        boolean result = feijiuRecommendCustomerDao.update(customer_id, customer_name, customer_phone, customer_city, system_update_user_id, system_version, request_app_id, request_http_id, request_user_id);

        if (result) {
            CacheUtil.remove(FEIJIU_RECOMMEND_CUSTOMER_BY_FEIJIU_RECOMMEND_CUSTOMER_ID_CACHE, customer_id);
        }

        return result;
    }

    public Boolean deleteByCustomer_idAndSystem_update_user_idValidateSystem_version(String customer_id, String system_update_user_id, Integer system_version, String request_app_id, String request_http_id, String request_user_id) {
        FeijiuRecommendCustomer feijiu_recommend_customer = findByCustomer_id(customer_id, request_app_id, request_http_id, request_user_id);
        if (!feijiu_recommend_customer.getSystem_version().equals(system_version)) {
            throw new RuntimeException(Constant.ERROR_VERSION);
        }

        boolean result = feijiuRecommendCustomerDao.deleteByCustomer_idAndSystem_version(customer_id, system_update_user_id, system_version, request_app_id, request_http_id, request_user_id);

        if (result) {
            CacheUtil.remove(FEIJIU_RECOMMEND_CUSTOMER_BY_FEIJIU_RECOMMEND_CUSTOMER_ID_CACHE, customer_id);
        }

        return result;
    }

}