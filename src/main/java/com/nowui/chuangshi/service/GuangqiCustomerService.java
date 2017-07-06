package com.nowui.chuangshi.service;

import com.nowui.chuangshi.cache.GuangqiCustomerCache;
import com.nowui.chuangshi.model.GuangqiCustomer;

import java.util.Date;
import java.util.List;

public class GuangqiCustomerService extends Service {

    private GuangqiCustomerCache guangqiCustomerCache = new GuangqiCustomerCache();

    public Integer countByApp_id(String app_id) {
        return guangqiCustomerCache.countByApp_id(app_id);
    }

    public Integer countByOrApp_id(String app_id) {
        return guangqiCustomerCache.countByOrApp_id(app_id);
    }

    public Integer countByCustomer_phone(String customer_phone) {
        return guangqiCustomerCache.countByCustomer_phone(customer_phone);
    }

    public List<GuangqiCustomer> listByApp_idAndSystem_create_timeAndLimit(String app_id, Date system_create_time, int m, int n) {
        return guangqiCustomerCache.listByApp_idAndSystem_create_timeAndLimit(app_id, system_create_time, m, n);
    }

    public List<GuangqiCustomer> listByApp_idAndCustomer_nameAndLimit(String app_id, String customer_name, int m, int n) {
        return guangqiCustomerCache.listByApp_idAndCustomer_nameAndLimit(app_id, customer_name, m, n);
    }

    public List<GuangqiCustomer> listByOrApp_idAndLimit(String app_id, int m, int n) {
        return guangqiCustomerCache.listByOrApp_idAndLimit(app_id, m, n);
    }

    public GuangqiCustomer findByCustomer_id(String customer_id) {
        return guangqiCustomerCache.findByCustomer_id(customer_id);
    }

    public Boolean save(String customer_id, String app_id, String customer_name, String customer_phone, String customer_province, String customer_city, String costomer_dealer, String system_create_user_id) {
        return guangqiCustomerCache.save(customer_id, app_id, customer_name, customer_phone, customer_province, customer_city, costomer_dealer, system_create_user_id);
    }

    public Boolean updateValidateSystem_version(String customer_id, String customer_name, String customer_phone, String customer_province, String customer_city, String costomer_dealer, String system_update_user_id, Integer system_version) {
        return guangqiCustomerCache.updateValidateSystem_version(customer_id, customer_name, customer_phone, customer_province, customer_city, costomer_dealer, system_update_user_id, system_version);
    }

    public Boolean deleteByCustomer_idAndSystem_update_user_idValidateSystem_version(String customer_id, String system_update_user_id, Integer system_version) {
        return guangqiCustomerCache.deleteByCustomer_idAndSystem_update_user_idValidateSystem_version(customer_id, system_update_user_id, system_version);
    }

}