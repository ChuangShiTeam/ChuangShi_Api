package com.nowui.chuangshi.cache;

import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.dao.GuangqiCustomerDao;
import com.nowui.chuangshi.model.GuangqiCustomer;
import com.nowui.chuangshi.util.CacheUtil;

import java.util.Date;
import java.util.List;

public class GuangqiCustomerCache extends Cache {

    public static final String GUANGQI_CUSTOMER_BY_GUANGQI_CUSTOMER_ID_CACHE = "guangqi_customer_by_customer_id_cache";

    private GuangqiCustomerDao guangqiCustomerDao = new GuangqiCustomerDao();

    public Integer countByApp_id(String app_id, String request_app_id, String request_http_id, String request_user_id) {
        return guangqiCustomerDao.countByApp_id(app_id, request_app_id, request_http_id, request_user_id);
    }

    public Integer countByOrApp_id(String app_id, String request_app_id, String request_http_id, String request_user_id) {
        return guangqiCustomerDao.countByOrApp_id(app_id, request_app_id, request_http_id, request_user_id);
    }

    public Integer countByCustomer_phone(String customer_phone, String request_app_id, String request_http_id, String request_user_id) {
        return guangqiCustomerDao.countByCustomer_phone(customer_phone, request_app_id, request_http_id, request_user_id);
    }

    public List<GuangqiCustomer> listByApp_idAndSystem_create_timeAndLimit(String app_id, Date system_create_time, int m, int n, String request_app_id, String request_http_id, String request_user_id) {
        List<GuangqiCustomer> guangqi_customerList = guangqiCustomerDao.listByApp_idAndSystem_create_timeAndLimit(app_id, system_create_time, m, n, request_app_id, request_http_id, request_user_id);

        for (GuangqiCustomer guangqi_customer : guangqi_customerList) {
            guangqi_customer.put(findByCustomer_id(guangqi_customer.getCustomer_id(), request_app_id, request_http_id, request_user_id));
        }

        return guangqi_customerList;
    }

    public List<GuangqiCustomer> listByApp_idAndCustomer_nameAndLimit(String app_id, String customer_name, int m, int n, String request_app_id, String request_http_id, String request_user_id) {
        List<GuangqiCustomer> guangqi_customerList = guangqiCustomerDao.listByApp_idAndCustomer_nameAndLimit(app_id, customer_name, m, n, request_app_id, request_http_id, request_user_id);

        for (GuangqiCustomer guangqi_customer : guangqi_customerList) {
            guangqi_customer.put(findByCustomer_id(guangqi_customer.getCustomer_id(), request_app_id, request_http_id, request_user_id));
        }

        return guangqi_customerList;
    }

    public List<GuangqiCustomer> listByOrApp_idAndLimit(String app_id, int m, int n, String request_app_id, String request_http_id, String request_user_id) {
        List<GuangqiCustomer> guangqi_customerList = guangqiCustomerDao.listByOrApp_idAndLimit(app_id, m, n, request_app_id, request_http_id, request_user_id);

        for (GuangqiCustomer guangqi_customer : guangqi_customerList) {
            guangqi_customer.put(findByCustomer_id(guangqi_customer.getCustomer_id(), request_app_id, request_http_id, request_user_id));
        }

        return guangqi_customerList;
    }

    public GuangqiCustomer findByCustomer_id(String customer_id, String request_app_id, String request_http_id, String request_user_id) {
        GuangqiCustomer guangqi_customer = CacheUtil.get(GUANGQI_CUSTOMER_BY_GUANGQI_CUSTOMER_ID_CACHE, customer_id);

        if (guangqi_customer == null) {
            guangqi_customer = guangqiCustomerDao.findByCustomer_id(customer_id, request_app_id, request_http_id, request_user_id);

            CacheUtil.put(GUANGQI_CUSTOMER_BY_GUANGQI_CUSTOMER_ID_CACHE, customer_id, guangqi_customer);
        }

        return guangqi_customer;
    }

    public Boolean save(String customer_id, String app_id, String customer_name, String customer_phone, String customer_province, String customer_city, String costomer_dealer, String system_create_user_id, String request_app_id, String request_http_id, String request_user_id) {
        return guangqiCustomerDao.save(customer_id, app_id, customer_name, customer_phone, customer_province, customer_city, costomer_dealer, system_create_user_id, request_app_id, request_http_id, request_user_id);
    }

    public Boolean updateValidateSystem_version(String customer_id, String customer_name, String customer_phone, String customer_province, String customer_city, String costomer_dealer, String system_update_user_id, Integer system_version, String request_app_id, String request_http_id, String request_user_id) {
        GuangqiCustomer guangqi_customer = findByCustomer_id(customer_id, request_app_id, request_http_id, request_user_id);
        if (!guangqi_customer.getSystem_version().equals(system_version)) {
            throw new RuntimeException(Constant.ERROR_VERSION);
        }

        boolean result = guangqiCustomerDao.update(customer_id, customer_name, customer_phone, customer_province, customer_city, costomer_dealer, system_update_user_id, system_version, request_app_id, request_http_id, request_user_id);

        if (result) {
            CacheUtil.remove(GUANGQI_CUSTOMER_BY_GUANGQI_CUSTOMER_ID_CACHE, customer_id);
        }

        return result;
    }

    public Boolean deleteByCustomer_idAndSystem_update_user_idValidateSystem_version(String customer_id, String system_update_user_id, Integer system_version, String request_app_id, String request_http_id, String request_user_id) {
        GuangqiCustomer guangqi_customer = findByCustomer_id(customer_id, request_app_id, request_http_id, request_user_id);
        if (!guangqi_customer.getSystem_version().equals(system_version)) {
            throw new RuntimeException(Constant.ERROR_VERSION);
        }

        boolean result = guangqiCustomerDao.deleteByCustomer_idAndSystem_version(customer_id, system_update_user_id, system_version, request_app_id, request_http_id, request_user_id);

        if (result) {
            CacheUtil.remove(GUANGQI_CUSTOMER_BY_GUANGQI_CUSTOMER_ID_CACHE, customer_id);
        }

        return result;
    }

}