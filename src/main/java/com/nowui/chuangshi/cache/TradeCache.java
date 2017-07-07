package com.nowui.chuangshi.cache;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.dao.TradeDao;
import com.nowui.chuangshi.model.Trade;
import com.nowui.chuangshi.util.CacheUtil;

public class TradeCache extends Cache {

    public static final String TRADE_BY_TRADE_ID_CACHE = "trade_by_trade_id_cache";
    public static final String TRADE_BY_TRADE_NUMBER_CACHE = "trade_by_trade_number_cache";
    public static final String TRADE_NUMBER_LIST_CACHE = "trade_number_list_cache";
    

    private TradeDao tradeDao = new TradeDao();

    public Integer countByApp_idOrLikeTrade_number(String app_id, String trade_number) {
        return tradeDao.countByApp_idOrLikeTrade_number(app_id, trade_number);
    }

    public Integer countByOrApp_idOrLikeTrade_number(String app_id, String trade_number) {
        return tradeDao.countByOrApp_idOrLikeTrade_number(app_id, trade_number);
    }

    public List<Trade> listByApp_idAndSystem_create_timeAndLimit(String app_id, Date system_create_time, int m, int n) {
        List<Trade> tradeList = tradeDao.listByApp_idAndSystem_create_timeAndLimit(app_id, system_create_time, m, n);

        for (Trade trade : tradeList) {
            trade.put(findByTrade_id(trade.getTrade_id()));
        }

        return tradeList;
    }

    public List<Trade> listByApp_idOrLikeTrade_numberAndLimit(String app_id, String trade_number, int m, int n) {
        List<Trade> tradeList = tradeDao.listByApp_idOrLikeTrade_numberAndLimit(app_id, trade_number, m, n);

        for (Trade trade : tradeList) {
            trade.put(findByTrade_id(trade.getTrade_id()));
        }

        return tradeList;
    }

    public List<Trade> listByOrApp_idOrLikeTrade_numberAndLimit(String app_id, String trade_number, int m, int n) {
        List<Trade> tradeList = tradeDao.listByOrApp_idOrLikeTrade_numberAndLimit(app_id, trade_number, m, n);

        for (Trade trade : tradeList) {
            trade.put(findByTrade_id(trade.getTrade_id()));
        }

        return tradeList;
    }

    public Trade findByTrade_id(String trade_id) {
        Trade trade = CacheUtil.get(TRADE_BY_TRADE_ID_CACHE, trade_id);

        if (trade == null) {
            trade = tradeDao.findByTrade_id(trade_id);

            CacheUtil.put(TRADE_BY_TRADE_ID_CACHE, trade_id, trade);
        }

        return trade;
    }
    
    public Trade findByTrade_number(String trade_number) {
        Trade trade = CacheUtil.get(TRADE_BY_TRADE_NUMBER_CACHE, trade_number);

        if (trade == null) {
            trade = tradeDao.findByTrade_number(trade_number);

            CacheUtil.put(TRADE_BY_TRADE_NUMBER_CACHE, trade_number, trade);
        }

        return trade;
    }

    public Boolean save(String trade_id, String app_id, String user_id, String trade_number, String trade_receiver_name, String trade_receiver_mobile, String trade_receiver_province, String trade_receiver_city, String trade_receiver_area, String trade_receiver_address, String trade_message, Integer trade_product_quantity, BigDecimal trade_product_amount, BigDecimal trade_express_amount, BigDecimal trade_discount_amount, Boolean trade_is_commission, Boolean trade_is_confirm, Boolean trade_is_pay, String trade_flow, String trade_status, String trade_audit_status, String system_create_user_id) {
        return tradeDao.save(trade_id, app_id, user_id, trade_number, trade_receiver_name, trade_receiver_mobile, trade_receiver_province, trade_receiver_city, trade_receiver_area, trade_receiver_address, trade_message, trade_product_quantity, trade_product_amount, trade_express_amount, trade_discount_amount, trade_is_commission, trade_is_confirm, trade_is_pay, trade_flow, trade_status, trade_audit_status, system_create_user_id);
    }

    public Boolean updateValidateSystem_version(String trade_id, String user_id, String trade_number, String trade_receiver_name, String trade_receiver_mobile, String trade_receiver_province, String trade_receiver_city, String trade_receiver_area, String trade_receiver_address, String trade_message, Integer trade_product_quantity, BigDecimal trade_product_amount, BigDecimal trade_express_amount, BigDecimal trade_discount_amount, Boolean trade_is_commission, Boolean trade_is_confirm, Boolean trade_is_pay, String trade_flow, String trade_status, String trade_audit_status, String system_update_user_id, Integer system_version) {
        Trade trade = findByTrade_id(trade_id);
        if (!trade.getSystem_version().equals(system_version)) {
            throw new RuntimeException(Constant.ERROR_VERSION);
        }

        boolean result = tradeDao.update(trade_id, user_id, trade_number, trade_receiver_name, trade_receiver_mobile, trade_receiver_province, trade_receiver_city, trade_receiver_area, trade_receiver_address, trade_message, trade_product_quantity, trade_product_amount, trade_express_amount, trade_discount_amount, trade_is_commission, trade_is_confirm, trade_is_pay, trade_flow, trade_status, trade_audit_status, system_update_user_id, system_version);

        if (result) {
            CacheUtil.remove(TRADE_BY_TRADE_ID_CACHE, trade_id);
            CacheUtil.remove(TRADE_BY_TRADE_NUMBER_CACHE, trade_number);
        }

        return result;
    }

    public Boolean deleteByTrade_idAndSystem_update_user_idValidateSystem_version(String trade_id, String system_update_user_id, Integer system_version) {
        Trade trade = findByTrade_id(trade_id);
        if (!trade.getSystem_version().equals(system_version)) {
            throw new RuntimeException(Constant.ERROR_VERSION);
        }

        boolean result = tradeDao.deleteByTrade_idAndSystem_version(trade_id, system_update_user_id, system_version);

        if (result) {
            CacheUtil.remove(TRADE_BY_TRADE_ID_CACHE, trade_id);
            CacheUtil.remove(TRADE_BY_TRADE_NUMBER_CACHE, trade.getTrade_number());
        }

        return result;
    }
    
    /**
     * 生成订单号
     * @return
     */
    public String generateTrade_number() {
        String date = new SimpleDateFormat("yyyyMMdd").format(new Date());  
        List<String> trade_numberList = CacheUtil.get(TRADE_NUMBER_LIST_CACHE, date);
        String trade_number = new StringBuilder(date).append((new Random().nextInt(899999) + 10)).toString();
        if (trade_numberList == null) {
            CacheUtil.removeAll(TRADE_NUMBER_LIST_CACHE); 
            trade_numberList = new ArrayList<String>();
        } else {
            while(trade_numberList.contains(trade_number)) {
                trade_number = new StringBuilder(date).append((new Random().nextInt(899999) + 10)).toString();
            }
        }
        trade_numberList.add(trade_number);
        CacheUtil.put(TRADE_NUMBER_LIST_CACHE, date, trade_numberList);
        return trade_number;
    }

}