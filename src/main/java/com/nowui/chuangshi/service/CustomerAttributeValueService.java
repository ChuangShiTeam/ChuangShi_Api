package com.nowui.chuangshi.service;

import java.util.List;

import com.nowui.chuangshi.cache.CustomerAttributeValueCache;
import com.nowui.chuangshi.model.CustomerAttributeValue;

public class CustomerAttributeValueService extends Service {

    private CustomerAttributeValueCache customerAttributeValueCache = new CustomerAttributeValueCache();

    public List<CustomerAttributeValue> listByCustomer_id(String customer_id) {
        return customerAttributeValueCache.listByCustomer_id(customer_id);
    }

    public Boolean save(String customer_attribute_id, String customer_id, String customer_attribute_value, String system_create_user_id) {
        return customerAttributeValueCache.save(customer_attribute_id, customer_id, customer_attribute_value, system_create_user_id);
    }

    public Boolean deleteByCustomer_idAndSystem_update_user_id(String customer_id, String system_update_user_id) {
        return customerAttributeValueCache.deleteByCustomer_idAndSystem_update_user_id(customer_id, system_update_user_id);
    }

}