package com.nowui.chuangshi.cache;

import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.dao.CustomerDao;
import com.nowui.chuangshi.model.Customer;
import com.nowui.chuangshi.util.CacheUtil;

import java.util.Date;
import java.util.List;

public class CustomerCache extends Cache {

    public static final String CUSTOMER_BY_CUSTOMER_ID_CACHE = "customer_by_customer_id_cache";

    private CustomerDao customerDao = new CustomerDao();

    public Integer countByApp_idOrLikeCustomer_name(String app_id, String customer_name, String request_app_id, String request_http_id, String request_user_id) {
        return customerDao.countByApp_idOrLikeCustomer_name(app_id, customer_name, request_app_id, request_http_id, request_user_id);
    }

    public Integer countByOrApp_idOrLikeCustomer_name(String app_id, String customer_name, String request_app_id, String request_http_id, String request_user_id) {
        return customerDao.countByOrApp_idOrLikeCustomer_name(app_id, customer_name, request_app_id, request_http_id, request_user_id);
    }

    public List<Customer> listByApp_idAndSystem_create_timeAndLimit(String app_id, Date system_create_time, int m, int n, String request_app_id, String request_http_id, String request_user_id) {
        List<Customer> customerList = customerDao.listByApp_idAndSystem_create_timeAndLimit(app_id, system_create_time, m, n, request_app_id, request_http_id, request_user_id);

        for (Customer customer : customerList) {
            customer.put(findByCustomer_id(customer.getCustomer_id(), request_app_id, request_http_id, request_user_id));
        }

        return customerList;
    }

    public List<Customer> listByApp_idOrLikeCustomer_nameAndLimit(String app_id, String customer_name, int m, int n, String request_app_id, String request_http_id, String request_user_id) {
        List<Customer> customerList = customerDao.listByApp_idOrLikeCustomer_nameAndLimit(app_id, customer_name, m, n, request_app_id, request_http_id, request_user_id);

        for (Customer customer : customerList) {
            customer.put(findByCustomer_id(customer.getCustomer_id(), request_app_id, request_http_id, request_user_id));
        }

        return customerList;
    }

    public List<Customer> listByOrApp_idOrLikeCustomer_nameAndLimit(String app_id, String customer_name, int m, int n, String request_app_id, String request_http_id, String request_user_id) {
        List<Customer> customerList = customerDao.listByOrApp_idOrLikeCustomer_nameAndLimit(app_id, customer_name, m, n, request_app_id, request_http_id, request_user_id);

        for (Customer customer : customerList) {
            customer.put(findByCustomer_id(customer.getCustomer_id(), request_app_id, request_http_id, request_user_id));
        }

        return customerList;
    }

    public Customer findByCustomer_id(String customer_id, String request_app_id, String request_http_id, String request_user_id) {
        Customer customer = CacheUtil.get(CUSTOMER_BY_CUSTOMER_ID_CACHE, customer_id);

        if (customer == null) {
            customer = customerDao.findByCustomer_id(customer_id, request_app_id, request_http_id, request_user_id);

            CacheUtil.put(CUSTOMER_BY_CUSTOMER_ID_CACHE, customer_id, customer);
        }

        return customer;
    }

    public Boolean save(String customer_id, String app_id, String customer_name, String customer_phone, String customer_birthday, String customer_city, String customer_sex, String customer_id_card, String system_create_user_id, String request_app_id, String request_http_id, String request_user_id) {
        return customerDao.save(customer_id, app_id, customer_name, customer_phone, customer_birthday, customer_city, customer_sex, customer_id_card, system_create_user_id, request_app_id, request_http_id, request_user_id);
    }

    public Boolean updateValidateSystem_version(String customer_id, String customer_name, String customer_phone, String customer_birthday, String customer_city, String customer_sex, String customer_id_card, String system_update_user_id, Integer system_version, String request_app_id, String request_http_id, String request_user_id) {
        Customer customer = findByCustomer_id(customer_id, request_app_id, request_http_id, request_user_id);
        if (!customer.getSystem_version().equals(system_version)) {
            throw new RuntimeException(Constant.ERROR_VERSION);
        }

        boolean result = customerDao.update(customer_id, customer_name, customer_phone, customer_birthday, customer_city, customer_sex, customer_id_card, system_update_user_id, system_version, request_app_id, request_http_id, request_user_id);

        if (result) {
            CacheUtil.remove(CUSTOMER_BY_CUSTOMER_ID_CACHE, customer_id);
        }

        return result;
    }

    public Boolean deleteByCustomer_idAndSystem_update_user_idValidateSystem_version(String customer_id, String system_update_user_id, Integer system_version, String request_app_id, String request_http_id, String request_user_id) {
        Customer customer = findByCustomer_id(customer_id, request_app_id, request_http_id, request_user_id);
        if (!customer.getSystem_version().equals(system_version)) {
            throw new RuntimeException(Constant.ERROR_VERSION);
        }

        boolean result = customerDao.deleteByCustomer_idAndSystem_version(customer_id, system_update_user_id, system_version, request_app_id, request_http_id, request_user_id);

        if (result) {
            CacheUtil.remove(CUSTOMER_BY_CUSTOMER_ID_CACHE, customer_id);
        }

        return result;
    }

}