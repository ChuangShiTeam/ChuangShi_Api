package com.nowui.chuangshi.service;

import com.nowui.chuangshi.cache.FeijiuFastCustomerCache;
import com.nowui.chuangshi.model.FeijiuFastCustomer;
import com.nowui.chuangshi.util.Util;
import com.nowui.chuangshi.util.ValidateUtil;

import java.util.Date;
import java.util.List;

public class FeijiuFastCustomerService extends Service {

    private FeijiuFastCustomerCache feijiuFastCustomerCache = new FeijiuFastCustomerCache();

    public Integer countByApp_idAndCustomer_name(String app_id, String customer_name) {
        return feijiuFastCustomerCache.countByApp_idAndCustomer_name(app_id, customer_name);
    }

    public Integer countByOrApp_id(String app_id) {
        return feijiuFastCustomerCache.countByOrApp_id(app_id);
    }

    public List<FeijiuFastCustomer> listByApp_idAndSystem_create_timeAndLimit(String app_id, Date system_create_time, int m, int n) {
        return feijiuFastCustomerCache.listByApp_idAndSystem_create_timeAndLimit(app_id, system_create_time, m, n);
    }

    public List<FeijiuFastCustomer> listByApp_id(String app_id) {
        return feijiuFastCustomerCache.listByApp_id(app_id);
    }

    public List<FeijiuFastCustomer> listByApp_idAndCustomer_nameAndLimit(String app_id, String customer_name, int m, int n) {
        return feijiuFastCustomerCache.listByApp_idAndCustomer_nameAndLimit(app_id, customer_name, m, n);
    }

    public List<FeijiuFastCustomer> listByOrApp_idAndLimit(String app_id, int m, int n) {
        return feijiuFastCustomerCache.listByOrApp_idAndLimit(app_id, m, n);
    }

    public FeijiuFastCustomer findByCustomer_id(String customer_id) {
        return feijiuFastCustomerCache.findByCustomer_id(customer_id);
    }

    public Boolean save(String customer_id, String app_id, String customer_name, String customer_phone, String customer_birthday, String customer_city, String customer_sex, String customer_id_card, String customer_money, String customer_fang, String customer_che, String customer_xin, String customer_shou, String customer_dai, String customer_gong, String system_create_user_id) {
        if (!ValidateUtil.isPhone(customer_phone)) {
            throw new RuntimeException("手机号码不对");
        }

        return feijiuFastCustomerCache.save(customer_id, app_id, customer_name, customer_phone, customer_birthday, customer_city, customer_sex, customer_id_card, customer_money, customer_fang, customer_che, customer_xin, customer_shou, customer_dai, customer_gong, system_create_user_id);
    }

    public Boolean updateValidateSystem_version(String customer_id, String customer_name, String customer_phone, String customer_birthday, String customer_city, String customer_sex, String customer_id_card, String customer_money, String customer_fang, String customer_che, String customer_xin, String customer_shou, String customer_dai, String customer_gong, String system_update_user_id, Integer system_version) {
        return feijiuFastCustomerCache.updateValidateSystem_version(customer_id, customer_name, customer_phone, customer_birthday, customer_city, customer_sex, customer_id_card, customer_money, customer_fang, customer_che, customer_xin, customer_shou, customer_dai, customer_gong, system_update_user_id, system_version);
    }

    public Boolean deleteByCustomer_idAndSystem_update_user_idValidateSystem_version(String customer_id, String system_update_user_id, Integer system_version) {
        return feijiuFastCustomerCache.deleteByCustomer_idAndSystem_update_user_idValidateSystem_version(customer_id, system_update_user_id, system_version);
    }

}