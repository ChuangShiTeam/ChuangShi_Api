package com.nowui.chuangshi.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.nowui.chuangshi.cache.TradeCache;
import com.nowui.chuangshi.model.Trade;

public class TradeService extends Service {

    private TradeCache tradeCache = new TradeCache();

    public Integer countByApp_idOrLikeTrade_number(String app_id, String trade_number) {
        return tradeCache.countByApp_idOrLikeTrade_number(app_id, trade_number);
    }

    public Integer countByOrApp_idOrLikeTrade_number(String app_id, String trade_number) {
        return tradeCache.countByOrApp_idOrLikeTrade_number(app_id, trade_number);
    }

    public List<Trade> listByApp_idAndSystem_create_timeAndLimit(String app_id, Date system_create_time, int m, int n) {
        return tradeCache.listByApp_idAndSystem_create_timeAndLimit(app_id, system_create_time, m, n);
    }

    public List<Trade> listByApp_idOrLikeTrade_numberAndLimit(String app_id, String trade_number, int m, int n) {
        return tradeCache.listByApp_idOrLikeTrade_numberAndLimit(app_id, trade_number, m, n);
    }

    public List<Trade> listByOrApp_idOrLikeTrade_numberAndLimit(String app_id, String trade_number, int m, int n) {
        return tradeCache.listByOrApp_idOrLikeTrade_numberAndLimit(app_id, trade_number, m, n);
    }

    public Trade findByTrade_id(String trade_id) {
        return tradeCache.findByTrade_id(trade_id);
    }

    public Boolean save(String trade_id, String app_id, String user_id, String trade_number, String trade_receiver_name, String trade_receiver_mobile, String trade_receiver_province, String trade_receiver_city, String trade_receiver_area, String trade_receiver_address, String trade_message, Integer trade_product_quantity, BigDecimal trade_product_amount, BigDecimal trade_express_amount, BigDecimal trade_discount_amount, Boolean trade_is_commission, Boolean trade_is_confirm, Boolean trade_is_pay, String trade_flow, String trade_status, String trade_audit_status, String system_create_user_id) {
        return tradeCache.save(trade_id, app_id, user_id, trade_number, trade_receiver_name, trade_receiver_mobile, trade_receiver_province, trade_receiver_city, trade_receiver_area, trade_receiver_address, trade_message, trade_product_quantity, trade_product_amount, trade_express_amount, trade_discount_amount, trade_is_commission, trade_is_confirm, trade_is_pay, trade_flow, trade_status, trade_audit_status, system_create_user_id);
    }

    public Boolean updateValidateSystem_version(String trade_id, String user_id, String trade_number, String trade_receiver_name, String trade_receiver_mobile, String trade_receiver_province, String trade_receiver_city, String trade_receiver_area, String trade_receiver_address, String trade_message, Integer trade_product_quantity, BigDecimal trade_product_amount, BigDecimal trade_express_amount, BigDecimal trade_discount_amount, Boolean trade_is_commission, Boolean trade_is_confirm, Boolean trade_is_pay, String trade_flow, String trade_status, String trade_audit_status, String system_update_user_id, Integer system_version) {
        return tradeCache.updateValidateSystem_version(trade_id, user_id, trade_number, trade_receiver_name, trade_receiver_mobile, trade_receiver_province, trade_receiver_city, trade_receiver_area, trade_receiver_address, trade_message, trade_product_quantity, trade_product_amount, trade_express_amount, trade_discount_amount, trade_is_commission, trade_is_confirm, trade_is_pay, trade_flow, trade_status, trade_audit_status, system_update_user_id, system_version);
    }

    public Boolean deleteByTrade_idAndSystem_update_user_idValidateSystem_version(String trade_id, String system_update_user_id, Integer system_version) {
        return tradeCache.deleteByTrade_idAndSystem_update_user_idValidateSystem_version(trade_id, system_update_user_id, system_version);
    }
    
    public String generateTrade_number() {
        return tradeCache.generateTrade_number();
    }

}