package com.nowui.chuangshi.cache;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.dao.ExpressDao;
import com.nowui.chuangshi.model.Express;
import com.nowui.chuangshi.util.CacheUtil;

public class ExpressCache extends Cache {

    public static final String EXPRESS_BY_EXPRESS_ID_CACHE = "express_by_express_id_cache";

    public static final String EXPRESS_LIST_BY_TRADE_ID = "express_list_by_trade_id";

    private ExpressDao expressDao = new ExpressDao();

    public Integer countByApp_idOrLikeExpress_noOrLikeExpress_receiver_nameOrLikeExpress_sender_name(String app_id, String express_no, String express_receiver_name, String express_sender_name) {
        return expressDao.countByApp_idOrLikeExpress_noOrLikeExpress_receiver_nameOrLikeExpress_sender_name(app_id, express_no, express_receiver_name, express_sender_name);
    }

    public Integer countByOrApp_idOrLikeExpress_noOrLikeExpress_receiver_nameOrLikeExpress_sender_name(String app_id, String express_no, String express_receiver_name, String express_sender_name) {
        return expressDao.countByOrApp_idOrLikeExpress_noOrLikeExpress_receiver_nameOrLikeExpress_sender_name(app_id, express_no, express_receiver_name, express_sender_name);
    }

    public List<Express> listByApp_idAndSystem_create_timeAndLimit(String app_id, Date system_create_time, int m, int n) {
        List<Express> expressList = expressDao.listByApp_idAndSystem_create_timeAndLimit(app_id, system_create_time, m, n);

        for (Express express : expressList) {
            express.put(findByExpress_id(express.getExpress_id()));
        }

        return expressList;
    }

    public List<Express> listByApp_idOrLikeExpress_noOrLikeExpress_receiver_nameOrLikeExpress_sender_nameAndLimit(String app_id, String express_no, String express_receiver_name,
            String express_sender_name, int m, int n) {
        List<Express> expressList = expressDao.listByApp_idOrLikeExpress_noOrLikeExpress_receiver_nameOrLikeExpress_sender_nameAndLimit(app_id, express_no, express_receiver_name, express_sender_name,
                m, n);

        for (Express express : expressList) {
            express.put(findByExpress_id(express.getExpress_id()));
        }

        return expressList;
    }

    public List<Express> listByOrApp_idOrLikeExpress_noOrLikeExpress_receiver_nameOrLikeExpress_sender_nameAndLimit(String app_id, String express_no, String express_receiver_name,
            String express_sender_name, int m, int n) {
        List<Express> expressList = expressDao.listByOrApp_idOrLikeExpress_noOrLikeExpress_receiver_nameOrLikeExpress_sender_nameAndLimit(app_id, express_no, express_receiver_name,
                express_sender_name, m, n);

        for (Express express : expressList) {
            express.put(findByExpress_id(express.getExpress_id()));
        }

        return expressList;
    }

    public Express findByExpress_id(String express_id) {
        Express express = CacheUtil.get(EXPRESS_BY_EXPRESS_ID_CACHE, express_id);

        if (express == null) {
            express = expressDao.findByExpress_id(express_id);

            CacheUtil.put(EXPRESS_BY_EXPRESS_ID_CACHE, express_id, express);
        }

        return express;
    }

    public Boolean save(String express_id, String app_id, String stock_id, String express_shipper_code, String express_no, String express_receiver_company, String express_receiver_name, String express_receiver_tel, String express_receiver_mobile, String express_receiver_postcode, String express_receiver_province, String express_receiver_city, String express_receiver_area, String express_receiver_address, String express_sender_company, String express_sender_name, String express_sender_tel, String express_sender_mobile, String express_sender_postcode, String express_sender_province, String express_sender_city, String express_sender_area, String express_sender_address, BigDecimal express_cost, Boolean express_is_pay, String express_pay_way, String express_traces, String express_flow, Boolean express_status, String express_remark, String system_create_user_id) {
        return expressDao.save(express_id, app_id, stock_id, express_shipper_code, express_no, express_receiver_company, express_receiver_name, express_receiver_tel, express_receiver_mobile, express_receiver_postcode, express_receiver_province, express_receiver_city, express_receiver_area, express_receiver_address, express_sender_company, express_sender_name, express_sender_tel, express_sender_mobile, express_sender_postcode, express_sender_province, express_sender_city, express_sender_area, express_sender_address, express_cost, express_is_pay, express_pay_way, express_traces, express_flow, express_status, express_remark, system_create_user_id);
    }

    public Boolean updateValidateSystem_version(String express_id, String stock_id, String express_shipper_code, String express_no, String express_receiver_company, String express_receiver_name, String express_receiver_tel, String express_receiver_mobile, String express_receiver_postcode, String express_receiver_province, String express_receiver_city, String express_receiver_area, String express_receiver_address, String express_sender_company, String express_sender_name, String express_sender_tel, String express_sender_mobile, String express_sender_postcode, String express_sender_province, String express_sender_city, String express_sender_area, String express_sender_address, BigDecimal express_cost, Boolean express_is_pay, String express_pay_way, String express_traces, String express_flow, Boolean express_status, String express_remark, String system_update_user_id, Integer system_version) {
        Express express = findByExpress_id(express_id);
        if (!express.getSystem_version().equals(system_version)) {
            throw new RuntimeException(Constant.ERROR_VERSION);
        }

        boolean result = expressDao.update(express_id, stock_id, express_shipper_code, express_no, express_receiver_company, express_receiver_name, express_receiver_tel, express_receiver_mobile, express_receiver_postcode, express_receiver_province, express_receiver_city, express_receiver_area, express_receiver_address, express_sender_company, express_sender_name, express_sender_tel, express_sender_mobile, express_sender_postcode, express_sender_province, express_sender_city, express_sender_area, express_sender_address, express_cost, express_is_pay, express_pay_way, express_traces, express_flow, express_status, express_remark, system_update_user_id, system_version);

        if (result) {
            CacheUtil.remove(EXPRESS_BY_EXPRESS_ID_CACHE, express_id);
        }

        return result;
    }

    public Boolean updateExpress_flowAndExpress_statusAndExpress_tracesByExpress_idValidateSystem_version(String express_id, String express_flow, Boolean express_status, String express_traces,
            String system_update_user_id, Integer system_version) {
        Express express = findByExpress_id(express_id);
        if (!express.getSystem_version().equals(system_version)) {
            throw new RuntimeException(Constant.ERROR_VERSION);
        }

        boolean result = expressDao.updateExpress_flowAndExpress_statusAndExpress_tracesByExpress_idAndSystem_version(express_id, express_flow, express_status, express_traces, system_update_user_id,
                system_version);

        if (result) {
            CacheUtil.remove(EXPRESS_BY_EXPRESS_ID_CACHE, express_id);
        }

        return result;
    }

    public Boolean deleteByExpress_idAndSystem_update_user_idValidateSystem_version(String express_id, String system_update_user_id, Integer system_version) {
        Express express = findByExpress_id(express_id);
        if (!express.getSystem_version().equals(system_version)) {
            throw new RuntimeException(Constant.ERROR_VERSION);
        }

        boolean result = expressDao.deleteByExpress_idAndSystem_version(express_id, system_update_user_id, system_version);

        if (result) {
            CacheUtil.remove(EXPRESS_BY_EXPRESS_ID_CACHE, express_id);
        }

        return result;
    }

    public List<Express> listByTrade_id(String trade_id) {
        List<Express> expressList = CacheUtil.get(EXPRESS_LIST_BY_TRADE_ID, trade_id);

        if (expressList == null || expressList.size() == 0) {
            expressList = expressDao.listByTrade_id(trade_id);

            CacheUtil.put(EXPRESS_LIST_BY_TRADE_ID, trade_id, expressList);
        }

        return expressList;
    }
    
    public Express findByStock_id(String stock_id) {
    	return expressDao.findByStock_id(stock_id);
    }

}