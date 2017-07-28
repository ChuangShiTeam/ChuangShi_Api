package com.nowui.chuangshi.service;

import com.nowui.chuangshi.cache.CustomerAttributeCache;
import com.nowui.chuangshi.model.CustomerAttribute;

import java.util.Date;
import java.util.List;

public class CustomerAttributeService extends Service {

    private CustomerAttributeCache customerAttributeCache = new CustomerAttributeCache();

    public Integer countByApp_idOrLikeCustomer_attribute_name(String app_id, String customer_attribute_name) {
        return customerAttributeCache.countByApp_idOrLikeCustomer_attribute_name(app_id, customer_attribute_name);
    }

    public Integer countByOrApp_idOrLikeCustomer_attribute_name(String app_id, String customer_attribute_name) {
        return customerAttributeCache.countByOrApp_idOrLikeCustomer_attribute_name(app_id, customer_attribute_name);
    }

    public List<CustomerAttribute> listByApp_idAndSystem_create_timeAndLimit(String app_id, Date system_create_time, int m, int n) {
        return customerAttributeCache.listByApp_idAndSystem_create_timeAndLimit(app_id, system_create_time, m, n);
    }

    public List<CustomerAttribute> listByApp_id(String app_id) {
        return customerAttributeCache.listByApp_id(app_id);
    }
    
    public List<CustomerAttribute> listByApp_idOrLikeCustomer_attribute_nameAndLimit(String app_id, String customer_attribute_name, int m, int n) {
        return customerAttributeCache.listByApp_idOrLikeCustomer_attribute_nameAndLimit(app_id, customer_attribute_name, m, n);
    }

    public List<CustomerAttribute> listByOrApp_idOrLikeCustomer_attribute_nameAndLimit(String app_id, String customer_attribute_name, int m, int n) {
        return customerAttributeCache.listByOrApp_idOrLikeCustomer_attribute_nameAndLimit(app_id, customer_attribute_name, m, n);
    }

    public CustomerAttribute findByCustomer_attribute_id(String customer_attribute_id) {
        return customerAttributeCache.findByCustomer_attribute_id(customer_attribute_id);
    }

    public Boolean save(String customer_attribute_id, String app_id, String customer_attribute_name, String customer_attribute_key, String customer_attribute_input_type, String customer_attribute_data_type, String customer_attribute_default_value, Integer customer_attribute_sort, String system_create_user_id) {
        return customerAttributeCache.save(customer_attribute_id, app_id, customer_attribute_name, customer_attribute_key, customer_attribute_input_type, customer_attribute_data_type, customer_attribute_default_value, customer_attribute_sort, system_create_user_id);
    }

    public Boolean updateValidateSystem_version(String customer_attribute_id, String customer_attribute_name, String customer_attribute_key, String customer_attribute_input_type, String customer_attribute_data_type, String customer_attribute_default_value, Integer customer_attribute_sort, String system_update_user_id, Integer system_version) {
        return customerAttributeCache.updateValidateSystem_version(customer_attribute_id, customer_attribute_name, customer_attribute_key, customer_attribute_input_type, customer_attribute_data_type, customer_attribute_default_value, customer_attribute_sort, system_update_user_id, system_version);
    }

    public Boolean deleteByCustomer_attribute_idAndSystem_update_user_idValidateSystem_version(String customer_attribute_id, String system_update_user_id, Integer system_version) {
        return customerAttributeCache.deleteByCustomer_attribute_idAndSystem_update_user_idValidateSystem_version(customer_attribute_id, system_update_user_id, system_version);
    }

}