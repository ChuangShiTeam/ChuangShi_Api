package com.nowui.chuangshi.service;

import com.nowui.chuangshi.cache.StockPayCache;
import com.nowui.chuangshi.model.StockPay;

import java.util.Date;
import java.util.List;

public class StockPayService extends Service {

    private StockPayCache stockPayCache = new StockPayCache();

    public List<StockPay> listByStock_id(String stock_id) {
        return stockPayCache.listByStock_id(stock_id);
    }

    public Boolean save(String stock_id, String stock_pay_type, String stock_pay_number, String stock_pay_account, String stock_pay_time, String stock_pay_result, String system_create_user_id) {
        return stockPayCache.save(stock_id, stock_pay_type, stock_pay_number, stock_pay_account, stock_pay_time, stock_pay_result, system_create_user_id);
    }

    public Boolean deleteByStock_idAndSystem_update_user_idValidateSystem_version(String stock_id, String system_update_user_id, Integer system_version) {
        return stockPayCache.deleteByStock_idAndSystem_update_user_idValidateSystem_version(stock_id, system_update_user_id, system_version);
    }

}