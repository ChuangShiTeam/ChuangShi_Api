package com.nowui.chuangshi.cache;

import java.util.List;

import com.nowui.chuangshi.dao.CustomerAttributeValueDao;
import com.nowui.chuangshi.model.CustomerAttributeValue;
import com.nowui.chuangshi.util.CacheUtil;

public class CustomerAttributeValueCache extends Cache {

    public static final String CUSTOMER_ATTRIBUTE_VALUE_BY_CUSTOMER_ID_CACHE = "customer_attribute_value_by_customer_id_cache";

    private CustomerAttributeValueDao customerAttributeValueDao = new CustomerAttributeValueDao();

    public List<CustomerAttributeValue> listByCustomer_id(String customer_id) {
        List<CustomerAttributeValue> customer_attribute_valueList = CacheUtil
                .get(CUSTOMER_ATTRIBUTE_VALUE_BY_CUSTOMER_ID_CACHE, customer_id);

        if (customer_attribute_valueList == null || customer_attribute_valueList.size() == 0) {
            customer_attribute_valueList = customerAttributeValueDao.listByCustomer_id(customer_id);

            CacheUtil.put(CUSTOMER_ATTRIBUTE_VALUE_BY_CUSTOMER_ID_CACHE, customer_id, customer_attribute_valueList);
        }

        return customer_attribute_valueList;
    }

    public Boolean save(String customer_attribute_id, String customer_id, String customer_attribute_value,
            String system_create_user_id) {
        Boolean result = customerAttributeValueDao.save(customer_attribute_id, customer_id, customer_attribute_value,
                system_create_user_id);

        if (result) {
            CacheUtil.remove(CUSTOMER_ATTRIBUTE_VALUE_BY_CUSTOMER_ID_CACHE, customer_id);
        }

        return result;
    }

    public Boolean deleteByCustomer_idAndSystem_update_user_id(String customer_id, String system_update_user_id) {
        boolean result = customerAttributeValueDao.deleteByCustomer_idAndSystem_update_user_id(customer_id, system_update_user_id);

        if (result) {
            CacheUtil.remove(CUSTOMER_ATTRIBUTE_VALUE_BY_CUSTOMER_ID_CACHE, customer_id);
        }

        return result;
    }

}