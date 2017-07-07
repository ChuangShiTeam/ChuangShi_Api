package com.nowui.chuangshi.cache;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.dao.TradeCommossionDao;
import com.nowui.chuangshi.model.TradeCommossion;
import com.nowui.chuangshi.util.CacheUtil;

public class TradeCommossionCache extends Cache {

    public static final String TRADE_COMMOSSION_LIST_BY_TRADE_ID_CACHE = "trade_commossion_list_by_trade_id_cache";

    private TradeCommossionDao tradeCommossionDao = new TradeCommossionDao();

    public List<TradeCommossion> listByTrade_id(String trade_id) {
        List<TradeCommossion> trade_commossionList = CacheUtil.get(TRADE_COMMOSSION_LIST_BY_TRADE_ID_CACHE, trade_id);

        if (trade_commossionList == null) {
            trade_commossionList = tradeCommossionDao.listByTrade_id(trade_id);
            
            CacheUtil.put(TRADE_COMMOSSION_LIST_BY_TRADE_ID_CACHE, trade_id, trade_commossionList);
        }
        return trade_commossionList;
    }


    public Boolean save(String trade_id, String product_sku_id, String member_id, String member_name, String member_level_id, String member_level_name, Integer product_sku_commission,
            BigDecimal product_sku_commission_amount, String system_create_user_id) {
        return tradeCommossionDao.save(trade_id, product_sku_id, member_id, member_name, member_level_id, member_level_name, product_sku_commission, product_sku_commission_amount,
                system_create_user_id);
    }

    public Boolean deleteByTrade_idAndSystem_update_user_idValidateSystem_version(String trade_id, String system_update_user_id, Integer system_version) {

        boolean result = tradeCommossionDao.deleteByTrade_idAndSystem_version(trade_id, system_update_user_id, system_version);

        if (result) {
            CacheUtil.remove(TRADE_COMMOSSION_LIST_BY_TRADE_ID_CACHE, trade_id);
        }

        return result;
    }

}