package com.nowui.chuangshi.cache;

import java.util.Date;
import java.util.List;

import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.dao.CustomerAttributeDao;
import com.nowui.chuangshi.model.CustomerAttribute;
import com.nowui.chuangshi.util.CacheUtil;

public class CustomerAttributeCache extends Cache {

    public static final String CUSTOMER_ATTRIBUTE_BY_CUSTOMER_ATTRIBUTE_ID_CACHE = "customer_attribute_by_customer_attribute_id_cache";

    public static final String CUSTOMER_ATTRIBUTE_BY_APP_ID_CACHE = "customer_attribute_by_app_id_cache";

    private CustomerAttributeDao customerAttributeDao = new CustomerAttributeDao();

    public Integer countByApp_idOrLikeCustomer_attribute_name(String app_id, String customer_attribute_name) {
        return customerAttributeDao.countByApp_idOrLikeCustomer_attribute_name(app_id, customer_attribute_name);
    }

    public Integer countByOrApp_idOrLikeCustomer_attribute_name(String app_id, String customer_attribute_name) {
        return customerAttributeDao.countByOrApp_idOrLikeCustomer_attribute_name(app_id, customer_attribute_name);
    }

    public List<CustomerAttribute> listByApp_idAndSystem_create_timeAndLimit(String app_id, Date system_create_time,
            int m, int n) {
        List<CustomerAttribute> customer_attributeList = customerAttributeDao
                .listByApp_idAndSystem_create_timeAndLimit(app_id, system_create_time, m, n);

        for (CustomerAttribute customer_attribute : customer_attributeList) {
            customer_attribute.put(findByCustomer_attribute_id(customer_attribute.getCustomer_attribute_id()));
        }

        return customer_attributeList;
    }

    public List<CustomerAttribute> listByApp_id(String app_id) {
        List<CustomerAttribute> customer_attributeList = CacheUtil.get(CUSTOMER_ATTRIBUTE_BY_APP_ID_CACHE, app_id);

        if (customer_attributeList == null || customer_attributeList.size() == 0) {
            customer_attributeList = customerAttributeDao.listByApp_id(app_id);

            CacheUtil.put(CUSTOMER_ATTRIBUTE_BY_APP_ID_CACHE, app_id, customer_attributeList);
        }

        return customer_attributeList;
    }

    public List<CustomerAttribute> listByApp_idOrLikeCustomer_attribute_nameAndLimit(String app_id,
            String customer_attribute_name, int m, int n) {
        List<CustomerAttribute> customer_attributeList = customerAttributeDao
                .listByApp_idOrLikeCustomer_attribute_nameAndLimit(app_id, customer_attribute_name, m, n);

        for (CustomerAttribute customer_attribute : customer_attributeList) {
            customer_attribute.put(findByCustomer_attribute_id(customer_attribute.getCustomer_attribute_id()));
        }

        return customer_attributeList;
    }

    public List<CustomerAttribute> listByOrApp_idOrLikeCustomer_attribute_nameAndLimit(String app_id,
            String customer_attribute_name, int m, int n) {
        List<CustomerAttribute> customer_attributeList = customerAttributeDao
                .listByOrApp_idOrLikeCustomer_attribute_nameAndLimit(app_id, customer_attribute_name, m, n);

        for (CustomerAttribute customer_attribute : customer_attributeList) {
            customer_attribute.put(findByCustomer_attribute_id(customer_attribute.getCustomer_attribute_id()));
        }

        return customer_attributeList;
    }

    public CustomerAttribute findByCustomer_attribute_id(String customer_attribute_id) {
        CustomerAttribute customer_attribute = CacheUtil.get(CUSTOMER_ATTRIBUTE_BY_CUSTOMER_ATTRIBUTE_ID_CACHE,
                customer_attribute_id);

        if (customer_attribute == null) {
            customer_attribute = customerAttributeDao.findByCustomer_attribute_id(customer_attribute_id);

            CacheUtil.put(CUSTOMER_ATTRIBUTE_BY_CUSTOMER_ATTRIBUTE_ID_CACHE, customer_attribute_id, customer_attribute);
        }

        return customer_attribute;
    }

    public Boolean save(String customer_attribute_id, String app_id, String customer_attribute_name,
            String customer_attribute_key, String customer_attribute_input_type, String customer_attribute_data_type,
            String customer_attribute_default_value, Integer customer_attribute_sort, String system_create_user_id) {
        Boolean result = customerAttributeDao.save(customer_attribute_id, app_id, customer_attribute_name,
                customer_attribute_key, customer_attribute_input_type, customer_attribute_data_type,
                customer_attribute_default_value, customer_attribute_sort, system_create_user_id);

        if (result) {
            CacheUtil.remove(CUSTOMER_ATTRIBUTE_BY_CUSTOMER_ATTRIBUTE_ID_CACHE, customer_attribute_id);
            CacheUtil.remove(CUSTOMER_ATTRIBUTE_BY_APP_ID_CACHE, app_id);
        }

        return result;
    }

    public Boolean updateValidateSystem_version(String customer_attribute_id, String customer_attribute_name,
            String customer_attribute_key, String customer_attribute_input_type, String customer_attribute_data_type,
            String customer_attribute_default_value, Integer customer_attribute_sort, String system_update_user_id,
            Integer system_version) {
        CustomerAttribute customer_attribute = findByCustomer_attribute_id(customer_attribute_id);
        if (!customer_attribute.getSystem_version().equals(system_version)) {
            throw new RuntimeException(Constant.ERROR_VERSION);
        }

        boolean result = customerAttributeDao.update(customer_attribute_id, customer_attribute_name,
                customer_attribute_key, customer_attribute_input_type, customer_attribute_data_type,
                customer_attribute_default_value, customer_attribute_sort, system_update_user_id, system_version);

        if (result) {
            CacheUtil.remove(CUSTOMER_ATTRIBUTE_BY_CUSTOMER_ATTRIBUTE_ID_CACHE, customer_attribute_id);
            CacheUtil.remove(CUSTOMER_ATTRIBUTE_BY_APP_ID_CACHE, customer_attribute.getApp_id());
        }

        return result;
    }

    public Boolean deleteByCustomer_attribute_idAndSystem_update_user_idValidateSystem_version(
            String customer_attribute_id, String system_update_user_id, Integer system_version) {
        CustomerAttribute customer_attribute = findByCustomer_attribute_id(customer_attribute_id);
        if (!customer_attribute.getSystem_version().equals(system_version)) {
            throw new RuntimeException(Constant.ERROR_VERSION);
        }

        boolean result = customerAttributeDao.deleteByCustomer_attribute_idAndSystem_version(customer_attribute_id,
                system_update_user_id, system_version);

        if (result) {
            CacheUtil.remove(CUSTOMER_ATTRIBUTE_BY_CUSTOMER_ATTRIBUTE_ID_CACHE, customer_attribute_id);
        }

        return result;
    }

}