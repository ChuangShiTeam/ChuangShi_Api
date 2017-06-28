package com.nowui.chuangshi.cache;

import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.dao.FeijiuFastCustomerDao;
import com.nowui.chuangshi.model.FeijiuFastCustomer;
import com.nowui.chuangshi.util.CacheUtil;

import java.util.Date;
import java.util.List;

public class FeijiuFastCustomerCache extends Cache {

    public static final String FEIJIU_FAST_CUSTOMER_BY_FEIJIU_FAST_CUSTOMER_ID_CACHE = "feijiu_fast_customer_by_customer_id_cache";

    private FeijiuFastCustomerDao feijiuFastCustomerDao = new FeijiuFastCustomerDao();

    public Integer countByApp_idAndCustomer_name(String app_id, String customer_name, String request_app_id, String request_http_id, String request_user_id) {
        return feijiuFastCustomerDao.countByApp_idAndCustomer_name(app_id, customer_name, request_app_id, request_http_id, request_user_id);
    }

    public Integer countByOrApp_id(String app_id, String request_app_id, String request_http_id, String request_user_id) {
        return feijiuFastCustomerDao.countByOrApp_id(app_id, request_app_id, request_http_id, request_user_id);
    }

    public List<FeijiuFastCustomer> listByApp_idAndSystem_create_timeAndLimit(String app_id, Date system_create_time, int m, int n, String request_app_id, String request_http_id, String request_user_id) {
        List<FeijiuFastCustomer> feijiu_fast_customerList = feijiuFastCustomerDao.listByApp_idAndSystem_create_timeAndLimit(app_id, system_create_time, m, n, request_app_id, request_http_id, request_user_id);

        for (FeijiuFastCustomer feijiu_fast_customer : feijiu_fast_customerList) {
            feijiu_fast_customer.put(findByCustomer_id(feijiu_fast_customer.getCustomer_id(), request_app_id, request_http_id, request_user_id));
        }

        return feijiu_fast_customerList;
    }

    public List<FeijiuFastCustomer> listByApp_id(String app_id, String request_app_id, String request_http_id, String request_user_id) {
        List<FeijiuFastCustomer> feijiu_fast_customerList = feijiuFastCustomerDao.listByApp_id(app_id, request_app_id, request_http_id, request_user_id);

        for (FeijiuFastCustomer feijiu_fast_customer : feijiu_fast_customerList) {
            feijiu_fast_customer.put(findByCustomer_id(feijiu_fast_customer.getCustomer_id(), request_app_id, request_http_id, request_user_id));
        }

        return feijiu_fast_customerList;
    }

    public List<FeijiuFastCustomer> listByApp_idAndCustomer_nameAndLimit(String app_id, String customer_name, int m, int n, String request_app_id, String request_http_id, String request_user_id) {
        List<FeijiuFastCustomer> feijiu_fast_customerList = feijiuFastCustomerDao.listByApp_idAndCustomer_nameAndLimit(app_id, customer_name, m, n, request_app_id, request_http_id, request_user_id);

        for (FeijiuFastCustomer feijiu_fast_customer : feijiu_fast_customerList) {
            feijiu_fast_customer.put(findByCustomer_id(feijiu_fast_customer.getCustomer_id(), request_app_id, request_http_id, request_user_id));
        }

        return feijiu_fast_customerList;
    }

    public List<FeijiuFastCustomer> listByOrApp_idAndLimit(String app_id, int m, int n, String request_app_id, String request_http_id, String request_user_id) {
        List<FeijiuFastCustomer> feijiu_fast_customerList = feijiuFastCustomerDao.listByOrApp_idAndLimit(app_id, m, n, request_app_id, request_http_id, request_user_id);

        for (FeijiuFastCustomer feijiu_fast_customer : feijiu_fast_customerList) {
            feijiu_fast_customer.put(findByCustomer_id(feijiu_fast_customer.getCustomer_id(), request_app_id, request_http_id, request_user_id));
        }

        return feijiu_fast_customerList;
    }

    public FeijiuFastCustomer findByCustomer_id(String customer_id, String request_app_id, String request_http_id, String request_user_id) {
        FeijiuFastCustomer feijiu_fast_customer = CacheUtil.get(FEIJIU_FAST_CUSTOMER_BY_FEIJIU_FAST_CUSTOMER_ID_CACHE, customer_id);

        if (feijiu_fast_customer == null) {
            feijiu_fast_customer = feijiuFastCustomerDao.findByCustomer_id(customer_id, request_app_id, request_http_id, request_user_id);

            CacheUtil.put(FEIJIU_FAST_CUSTOMER_BY_FEIJIU_FAST_CUSTOMER_ID_CACHE, customer_id, feijiu_fast_customer);
        }

        return feijiu_fast_customer;
    }

    public Boolean save(String customer_id, String app_id, String customer_name, String customer_phone, String customer_birthday, String customer_city, String customer_sex, String customer_id_card, String customer_money, String customer_fang, String customer_che, String customer_xin, String customer_shou, String customer_dai, String customer_gong, String system_create_user_id, String request_app_id, String request_http_id, String request_user_id) {
        return feijiuFastCustomerDao.save(customer_id, app_id, customer_name, customer_phone, customer_birthday, customer_city, customer_sex, customer_id_card, customer_money, customer_fang, customer_che, customer_xin, customer_shou, customer_dai, customer_gong, system_create_user_id, request_app_id, request_http_id, request_user_id);
    }

    public Boolean updateValidateSystem_version(String customer_id, String customer_name, String customer_phone, String customer_birthday, String customer_city, String customer_sex, String customer_id_card, String customer_money, String customer_fang, String customer_che, String customer_xin, String customer_shou, String customer_dai, String customer_gong, String system_update_user_id, Integer system_version, String request_app_id, String request_http_id, String request_user_id) {
        FeijiuFastCustomer feijiu_fast_customer = findByCustomer_id(customer_id, request_app_id, request_http_id, request_user_id);
        if (!feijiu_fast_customer.getSystem_version().equals(system_version)) {
            throw new RuntimeException(Constant.ERROR_VERSION);
        }

        boolean result = feijiuFastCustomerDao.update(customer_id, customer_name, customer_phone, customer_birthday, customer_city, customer_sex, customer_id_card, customer_money, customer_fang, customer_che, customer_xin, customer_shou, customer_dai, customer_gong, system_update_user_id, system_version, request_app_id, request_http_id, request_user_id);

        if (result) {
            CacheUtil.remove(FEIJIU_FAST_CUSTOMER_BY_FEIJIU_FAST_CUSTOMER_ID_CACHE, customer_id);
        }

        return result;
    }

    public Boolean deleteByCustomer_idAndSystem_update_user_idValidateSystem_version(String customer_id, String system_update_user_id, Integer system_version, String request_app_id, String request_http_id, String request_user_id) {
        FeijiuFastCustomer feijiu_fast_customer = findByCustomer_id(customer_id, request_app_id, request_http_id, request_user_id);
        if (!feijiu_fast_customer.getSystem_version().equals(system_version)) {
            throw new RuntimeException(Constant.ERROR_VERSION);
        }

        boolean result = feijiuFastCustomerDao.deleteByCustomer_idAndSystem_version(customer_id, system_update_user_id, system_version, request_app_id, request_http_id, request_user_id);

        if (result) {
            CacheUtil.remove(FEIJIU_FAST_CUSTOMER_BY_FEIJIU_FAST_CUSTOMER_ID_CACHE, customer_id);
        }

        return result;
    }

}