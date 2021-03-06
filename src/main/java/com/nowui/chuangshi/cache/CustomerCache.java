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

    public Integer countByApp_idOrLikeCustomer_name(String app_id, String customer_name) {
        return customerDao.countByApp_idOrLikeCustomer_name(app_id, customer_name);
    }

    public Integer countByOrApp_idOrLikeCustomer_name(String app_id, String customer_name) {
        return customerDao.countByOrApp_idOrLikeCustomer_name(app_id, customer_name);
    }

    public List<Customer> listByApp_idAndSystem_create_timeAndLimit(String app_id, Date system_create_time, int m, int n) {
        List<Customer> customerList = customerDao.listByApp_idAndSystem_create_timeAndLimit(app_id, system_create_time, m, n);

        for (Customer customer : customerList) {
            customer.put(findByCustomer_id(customer.getCustomer_id()));
        }

        return customerList;
    }

    public List<Customer> listByApp_idOrLikeCustomer_nameAndLimit(String app_id, String customer_name, int m, int n) {
        List<Customer> customerList = customerDao.listByApp_idOrLikeCustomer_nameAndLimit(app_id, customer_name, m, n);

        for (Customer customer : customerList) {
            customer.put(findByCustomer_id(customer.getCustomer_id()));
        }

        return customerList;
    }

    public List<Customer> listByOrApp_idOrLikeCustomer_nameAndLimit(String app_id, String customer_name, int m, int n) {
        List<Customer> customerList = customerDao.listByOrApp_idOrLikeCustomer_nameAndLimit(app_id, customer_name, m, n);

        for (Customer customer : customerList) {
            customer.put(findByCustomer_id(customer.getCustomer_id()));
        }

        return customerList;
    }

    public Customer findByCustomer_id(String customer_id) {
        Customer customer = CacheUtil.get(CUSTOMER_BY_CUSTOMER_ID_CACHE, customer_id);

        if (customer == null) {
            customer = customerDao.findByCustomer_id(customer_id);

            CacheUtil.put(CUSTOMER_BY_CUSTOMER_ID_CACHE, customer_id, customer);
        }

        return customer;
    }

    public Boolean save(String customer_id, String app_id, String customer_name, String customer_sex, String customer_birthday, String customer_tel, String customer_mobile, String customer_postcode, String customer_id_card, String customer_province, String customer_city, String customer_area, String customer_address, String system_create_user_id) {
        return customerDao.save(customer_id, app_id, customer_name, customer_sex, customer_birthday, customer_tel, customer_mobile, customer_postcode, customer_id_card, customer_province, customer_city, customer_area, customer_address, system_create_user_id);
    }

    public Boolean updateValidateSystem_version(String customer_id, String customer_name, String customer_sex, String customer_birthday, String customer_tel, String customer_mobile, String customer_postcode, String customer_id_card, String customer_province, String customer_city, String customer_area, String customer_address, String system_update_user_id, Integer system_version) {
        Customer customer = findByCustomer_id(customer_id);
        if (!customer.getSystem_version().equals(system_version)) {
            throw new RuntimeException(Constant.ERROR_VERSION);
        }

        boolean result = customerDao.update(customer_id, customer_name, customer_sex, customer_birthday, customer_tel, customer_mobile, customer_postcode, customer_id_card, customer_province, customer_city, customer_area, customer_address, system_update_user_id, system_version);

        if (result) {
            CacheUtil.remove(CUSTOMER_BY_CUSTOMER_ID_CACHE, customer_id);
        }

        return result;
    }

    public Boolean deleteByCustomer_idAndSystem_update_user_idValidateSystem_version(String customer_id, String system_update_user_id, Integer system_version) {
        Customer customer = findByCustomer_id(customer_id);
        if (!customer.getSystem_version().equals(system_version)) {
            throw new RuntimeException(Constant.ERROR_VERSION);
        }

        boolean result = customerDao.deleteByCustomer_idAndSystem_version(customer_id, system_update_user_id, system_version);

        if (result) {
            CacheUtil.remove(CUSTOMER_BY_CUSTOMER_ID_CACHE, customer_id);
        }

        return result;
    }

}