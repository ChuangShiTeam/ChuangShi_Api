package com.nowui.chuangshi.service;

import com.nowui.chuangshi.cache.GuangqiCustomerPrizeCache;
import com.nowui.chuangshi.model.GuangqiCustomerPrize;

import java.util.Date;
import java.util.List;

public class GuangqiCustomerPrizeService extends Service {

    private GuangqiCustomerPrizeCache guangqiCustomerPrizeCache = new GuangqiCustomerPrizeCache();

    public Integer countByApp_id(String app_id) {
        return guangqiCustomerPrizeCache.countByApp_id(app_id);
    }

    public Integer countByOrApp_id(String app_id) {
        return guangqiCustomerPrizeCache.countByOrApp_id(app_id);
    }

    public Integer countByApp_idAndCustomer_id(String app_id, String customer_id) {
        return guangqiCustomerPrizeCache.countByApp_idAndCustomer_id(app_id, customer_id);
    }

    public Integer countByApp_idAndPrize_id(String app_id, String prize_id) {
        return guangqiCustomerPrizeCache.countByApp_idAndPrize_id(app_id, prize_id);
    }

    public Integer countByApp_idAndPrize_idAndCustomer_prize_date(String app_id, String prize_id, String customer_prize_date) {
        return guangqiCustomerPrizeCache.countByApp_idAndPrize_idAndCustomer_prize_date(app_id, prize_id, customer_prize_date);
    }

    public List<GuangqiCustomerPrize> listByApp_idAndSystem_create_timeAndLimit(String app_id, Date system_create_time, int m, int n) {
        return guangqiCustomerPrizeCache.listByApp_idAndSystem_create_timeAndLimit(app_id, system_create_time, m, n);
    }

    public List<GuangqiCustomerPrize> listByApp_id(String app_id) {
        return guangqiCustomerPrizeCache.listByApp_id(app_id);
    }

    public List<GuangqiCustomerPrize> listByApp_idAndLimit(String app_id, int m, int n) {
        return guangqiCustomerPrizeCache.listByApp_idAndLimit(app_id, m, n);
    }

    public List<GuangqiCustomerPrize> listByOrApp_idAndLimit(String app_id, int m, int n) {
        return guangqiCustomerPrizeCache.listByOrApp_idAndLimit(app_id, m, n);
    }

    public GuangqiCustomerPrize findByCustomer_prize_id(String customer_prize_id) {
        return guangqiCustomerPrizeCache.findByCustomer_prize_id(customer_prize_id);
    }

    public Boolean save(String customer_prize_id, String app_id, String customer_id, String prize_id, String customer_prize_date, String system_create_user_id, Integer prize_quantity, Integer prize_limit) {
        return guangqiCustomerPrizeCache.save(customer_prize_id, app_id, customer_id, prize_id, customer_prize_date, system_create_user_id, prize_quantity, prize_limit);
    }

    public Boolean updateValidateSystem_version(String customer_prize_id, String customer_id, String prize_id, String customer_prize_date, String system_update_user_id, Integer system_version) {
        return guangqiCustomerPrizeCache.updateValidateSystem_version(customer_prize_id, customer_id, prize_id, customer_prize_date, system_update_user_id, system_version);
    }

    public Boolean deleteByCustomer_prize_idAndSystem_update_user_idValidateSystem_version(String customer_prize_id, String system_update_user_id, Integer system_version) {
        return guangqiCustomerPrizeCache.deleteByCustomer_prize_idAndSystem_update_user_idValidateSystem_version(customer_prize_id, system_update_user_id, system_version);
    }

}