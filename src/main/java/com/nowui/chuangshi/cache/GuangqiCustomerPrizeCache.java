package com.nowui.chuangshi.cache;

import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.dao.GuangqiCustomerPrizeDao;
import com.nowui.chuangshi.model.GuangqiCustomerPrize;
import com.nowui.chuangshi.util.CacheUtil;

import java.util.Date;
import java.util.List;

public class GuangqiCustomerPrizeCache extends Cache {

    public static final String GUANGQI_CUSTOMER_PRIZE_BY_GUANGQI_CUSTOMER_PRIZE_ID_CACHE = "guangqi_customer_prize_by_customer_prize_id_cache";

    private GuangqiCustomerPrizeDao guangqiCustomerPrizeDao = new GuangqiCustomerPrizeDao();

    public Integer countByApp_id(String app_id, String request_app_id, String request_http_id, String request_user_id) {
        return guangqiCustomerPrizeDao.countByApp_id(app_id, request_app_id, request_http_id, request_user_id);
    }

    public Integer countByOrApp_id(String app_id, String request_app_id, String request_http_id, String request_user_id) {
        return guangqiCustomerPrizeDao.countByOrApp_id(app_id, request_app_id, request_http_id, request_user_id);
    }

    public Integer countByApp_idAndCustomer_id(String app_id, String customer_id, String request_app_id, String request_http_id, String request_user_id) {
        return guangqiCustomerPrizeDao.countByApp_idAndCustomer_id(app_id, customer_id, request_app_id, request_http_id, request_user_id);
    }

    public Integer countByApp_idAndPrize_id(String app_id, String prize_id, String request_app_id, String request_http_id, String request_user_id) {
        return guangqiCustomerPrizeDao.countByApp_idAndPrize_id(app_id, prize_id, request_app_id, request_http_id, request_user_id);
    }

    public Integer countByApp_idAndPrize_idAndCustomer_prize_date(String app_id, String prize_id, String customer_prize_date, String request_app_id, String request_http_id, String request_user_id) {
        return guangqiCustomerPrizeDao.countByApp_idAndPrize_idAndCustomer_prize_date(app_id, prize_id, customer_prize_date, request_app_id, request_http_id, request_user_id);
    }

    public List<GuangqiCustomerPrize> listByApp_idAndSystem_create_timeAndLimit(String app_id, Date system_create_time, int m, int n, String request_app_id, String request_http_id, String request_user_id) {
        List<GuangqiCustomerPrize> guangqi_customer_prizeList = guangqiCustomerPrizeDao.listByApp_idAndSystem_create_timeAndLimit(app_id, system_create_time, m, n, request_app_id, request_http_id, request_user_id);

        for (GuangqiCustomerPrize guangqi_customer_prize : guangqi_customer_prizeList) {
            guangqi_customer_prize.put(findByCustomer_prize_id(guangqi_customer_prize.getCustomer_prize_id(), request_app_id, request_http_id, request_user_id));
        }

        return guangqi_customer_prizeList;
    }

    public List<GuangqiCustomerPrize> listByApp_id(String app_id, String request_app_id, String request_http_id, String request_user_id) {
        List<GuangqiCustomerPrize> guangqi_customer_prizeList = guangqiCustomerPrizeDao.listByApp_id(app_id, request_app_id, request_http_id, request_user_id);

        for (GuangqiCustomerPrize guangqi_customer_prize : guangqi_customer_prizeList) {
            guangqi_customer_prize.put(findByCustomer_prize_id(guangqi_customer_prize.getCustomer_prize_id(), request_app_id, request_http_id, request_user_id));
        }

        return guangqi_customer_prizeList;
    }

    public List<GuangqiCustomerPrize> listByApp_idAndLimit(String app_id, int m, int n, String request_app_id, String request_http_id, String request_user_id) {
        List<GuangqiCustomerPrize> guangqi_customer_prizeList = guangqiCustomerPrizeDao.listByApp_idAndLimit(app_id, m, n, request_app_id, request_http_id, request_user_id);

        for (GuangqiCustomerPrize guangqi_customer_prize : guangqi_customer_prizeList) {
            guangqi_customer_prize.put(findByCustomer_prize_id(guangqi_customer_prize.getCustomer_prize_id(), request_app_id, request_http_id, request_user_id));
        }

        return guangqi_customer_prizeList;
    }

    public List<GuangqiCustomerPrize> listByOrApp_idAndLimit(String app_id, int m, int n, String request_app_id, String request_http_id, String request_user_id) {
        List<GuangqiCustomerPrize> guangqi_customer_prizeList = guangqiCustomerPrizeDao.listByOrApp_idAndLimit(app_id, m, n, request_app_id, request_http_id, request_user_id);

        for (GuangqiCustomerPrize guangqi_customer_prize : guangqi_customer_prizeList) {
            guangqi_customer_prize.put(findByCustomer_prize_id(guangqi_customer_prize.getCustomer_prize_id(), request_app_id, request_http_id, request_user_id));
        }

        return guangqi_customer_prizeList;
    }

    public GuangqiCustomerPrize findByCustomer_prize_id(String customer_prize_id, String request_app_id, String request_http_id, String request_user_id) {
        GuangqiCustomerPrize guangqi_customer_prize = CacheUtil.get(GUANGQI_CUSTOMER_PRIZE_BY_GUANGQI_CUSTOMER_PRIZE_ID_CACHE, customer_prize_id);

        if (guangqi_customer_prize == null) {
            guangqi_customer_prize = guangqiCustomerPrizeDao.findByCustomer_prize_id(customer_prize_id, request_app_id, request_http_id, request_user_id);

            CacheUtil.put(GUANGQI_CUSTOMER_PRIZE_BY_GUANGQI_CUSTOMER_PRIZE_ID_CACHE, customer_prize_id, guangqi_customer_prize);
        }

        return guangqi_customer_prize;
    }

    public Boolean save(String customer_prize_id, String app_id, String customer_id, String prize_id, String customer_prize_date, String system_create_user_id, Integer prize_quantity, Integer prize_limit, String request_app_id, String request_http_id, String request_user_id) {
        return guangqiCustomerPrizeDao.save(customer_prize_id, app_id, customer_id, prize_id, customer_prize_date, system_create_user_id, prize_quantity, prize_limit, request_app_id, request_http_id, request_user_id);
    }

    public Boolean updateValidateSystem_version(String customer_prize_id, String customer_id, String prize_id, String customer_prize_date, String system_update_user_id, Integer system_version, String request_app_id, String request_http_id, String request_user_id) {
        GuangqiCustomerPrize guangqi_customer_prize = findByCustomer_prize_id(customer_prize_id, request_app_id, request_http_id, request_user_id);
        if (!guangqi_customer_prize.getSystem_version().equals(system_version)) {
            throw new RuntimeException(Constant.ERROR_VERSION);
        }

        boolean result = guangqiCustomerPrizeDao.update(customer_prize_id, customer_id, prize_id, customer_prize_date, system_update_user_id, system_version, request_app_id, request_http_id, request_user_id);

        if (result) {
            CacheUtil.remove(GUANGQI_CUSTOMER_PRIZE_BY_GUANGQI_CUSTOMER_PRIZE_ID_CACHE, customer_prize_id);
        }

        return result;
    }

    public Boolean deleteByCustomer_prize_idAndSystem_update_user_idValidateSystem_version(String customer_prize_id, String system_update_user_id, Integer system_version, String request_app_id, String request_http_id, String request_user_id) {
        GuangqiCustomerPrize guangqi_customer_prize = findByCustomer_prize_id(customer_prize_id, request_app_id, request_http_id, request_user_id);
        if (!guangqi_customer_prize.getSystem_version().equals(system_version)) {
            throw new RuntimeException(Constant.ERROR_VERSION);
        }

        boolean result = guangqiCustomerPrizeDao.deleteByCustomer_prize_idAndSystem_version(customer_prize_id, system_update_user_id, system_version, request_app_id, request_http_id, request_user_id);

        if (result) {
            CacheUtil.remove(GUANGQI_CUSTOMER_PRIZE_BY_GUANGQI_CUSTOMER_PRIZE_ID_CACHE, customer_prize_id);
        }

        return result;
    }

}