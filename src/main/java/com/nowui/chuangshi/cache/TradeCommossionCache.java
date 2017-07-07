package com.nowui.chuangshi.cache;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.dao.TradeCommossionDao;
import com.nowui.chuangshi.model.TradeCommossion;
import com.nowui.chuangshi.util.CacheUtil;

public class TradeCommossionCache extends Cache {

    public static final String TRADE_COMMOSSION_BY_TRADE_COMMOSSION_ID_CACHE = "trade_commossion_by_trade_commossion_id_cache";

    private TradeCommossionDao tradeCommossionDao = new TradeCommossionDao();

    public Integer countByApp_idOrLikeTrade_commossion_name(String app_id, String trade_commossion_name) {
        return tradeCommossionDao.countByApp_idOrLikeTrade_commossion_name(app_id, trade_commossion_name);
    }

    public Integer countByOrApp_idOrLikeTrade_commossion_name(String app_id, String trade_commossion_name) {
        return tradeCommossionDao.countByOrApp_idOrLikeTrade_commossion_name(app_id, trade_commossion_name);
    }

    public List<TradeCommossion> listByApp_idAndSystem_create_timeAndLimit(String app_id, Date system_create_time, int m, int n) {
        List<TradeCommossion> trade_commossionList = tradeCommossionDao.listByApp_idAndSystem_create_timeAndLimit(app_id, system_create_time, m, n);

        for (TradeCommossion trade_commossion : trade_commossionList) {
            trade_commossion.put(findByTrade_commossion_id(trade_commossion.getTrade_commossion_id()));
        }

        return trade_commossionList;
    }

    public List<TradeCommossion> listByApp_idOrLikeTrade_commossion_nameAndLimit(String app_id, String trade_commossion_name, int m, int n) {
        List<TradeCommossion> trade_commossionList = tradeCommossionDao.listByApp_idOrLikeTrade_commossion_nameAndLimit(app_id, trade_commossion_name, m, n);

        for (TradeCommossion trade_commossion : trade_commossionList) {
            trade_commossion.put(findByTrade_commossion_id(trade_commossion.getTrade_commossion_id()));
        }

        return trade_commossionList;
    }

    public List<TradeCommossion> listByOrApp_idOrLikeTrade_commossion_nameAndLimit(String app_id, String trade_commossion_name, int m, int n) {
        List<TradeCommossion> trade_commossionList = tradeCommossionDao.listByOrApp_idOrLikeTrade_commossion_nameAndLimit(app_id, trade_commossion_name, m, n);

        for (TradeCommossion trade_commossion : trade_commossionList) {
            trade_commossion.put(findByTrade_commossion_id(trade_commossion.getTrade_commossion_id()));
        }

        return trade_commossionList;
    }

    public TradeCommossion findByTrade_commossion_id(String trade_commossion_id) {
        TradeCommossion trade_commossion = CacheUtil.get(TRADE_COMMOSSION_BY_TRADE_COMMOSSION_ID_CACHE, trade_commossion_id);

        if (trade_commossion == null) {
            trade_commossion = tradeCommossionDao.findByTrade_commossion_id(trade_commossion_id);

            CacheUtil.put(TRADE_COMMOSSION_BY_TRADE_COMMOSSION_ID_CACHE, trade_commossion_id, trade_commossion);
        }

        return trade_commossion;
    }

    public Boolean save(String trade_id, String product_sku_id, String member_id, String member_name, String member_level_id, String member_level_name, Integer product_sku_commission,
            BigDecimal product_sku_commission_amount, String system_create_user_id) {
        return tradeCommossionDao.save(trade_id, product_sku_id, member_id, member_name, member_level_id, member_level_name, product_sku_commission, product_sku_commission_amount,
                system_create_user_id);
    }

    public Boolean updateValidateSystem_version(String trade_commossion_id, String trade_id, String product_sku_id, String member_id, String member_name, String member_level_id,
            String member_level_name, Integer product_sku_commission, BigDecimal product_sku_commission_amount, String system_update_user_id, Integer system_version) {
        TradeCommossion trade_commossion = findByTrade_commossion_id(trade_commossion_id);
        if (!trade_commossion.getSystem_version().equals(system_version)) {
            throw new RuntimeException(Constant.ERROR_VERSION);
        }

        boolean result = tradeCommossionDao.update(trade_commossion_id, trade_id, product_sku_id, member_id, member_name, member_level_id, member_level_name, product_sku_commission,
                product_sku_commission_amount, system_update_user_id, system_version);

        if (result) {
            CacheUtil.remove(TRADE_COMMOSSION_BY_TRADE_COMMOSSION_ID_CACHE, trade_commossion_id);
        }

        return result;
    }

    public Boolean deleteByTrade_commossion_idAndSystem_update_user_idValidateSystem_version(String trade_commossion_id, String system_update_user_id, Integer system_version) {
        TradeCommossion trade_commossion = findByTrade_commossion_id(trade_commossion_id);
        if (!trade_commossion.getSystem_version().equals(system_version)) {
            throw new RuntimeException(Constant.ERROR_VERSION);
        }

        boolean result = tradeCommossionDao.deleteByTrade_commossion_idAndSystem_version(trade_commossion_id, system_update_user_id, system_version);

        if (result) {
            CacheUtil.remove(TRADE_COMMOSSION_BY_TRADE_COMMOSSION_ID_CACHE, trade_commossion_id);
        }

        return result;
    }

}