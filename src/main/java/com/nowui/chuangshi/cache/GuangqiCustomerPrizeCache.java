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

    public Integer countByApp_id(String app_id) {
        return guangqiCustomerPrizeDao.countByApp_id(app_id);
    }

    public Integer countByOrApp_id(String app_id) {
        return guangqiCustomerPrizeDao.countByOrApp_id(app_id);
    }

    public Integer countByApp_idAndCustomer_id(String app_id, String customer_id) {
        return guangqiCustomerPrizeDao.countByApp_idAndCustomer_id(app_id, customer_id);
    }

    public Integer countByApp_idAndPrize_id(String app_id, String prize_id) {
        return guangqiCustomerPrizeDao.countByApp_idAndPrize_id(app_id, prize_id);
    }

    public Integer countByApp_idAndPrize_idAndCustomer_prize_date(String app_id, String prize_id, String customer_prize_date) {
        return guangqiCustomerPrizeDao.countByApp_idAndPrize_idAndCustomer_prize_date(app_id, prize_id, customer_prize_date);
    }

    public List<GuangqiCustomerPrize> listByApp_idAndSystem_create_timeAndLimit(String app_id, Date system_create_time, int m, int n) {
        List<GuangqiCustomerPrize> guangqi_customer_prizeList = guangqiCustomerPrizeDao.listByApp_idAndSystem_create_timeAndLimit(app_id, system_create_time, m, n);

        for (GuangqiCustomerPrize guangqi_customer_prize : guangqi_customer_prizeList) {
            guangqi_customer_prize.put(findByCustomer_prize_id(guangqi_customer_prize.getCustomer_prize_id()));
        }

        return guangqi_customer_prizeList;
    }

    public List<GuangqiCustomerPrize> listByApp_id(String app_id) {
        return guangqiCustomerPrizeDao.listByApp_id(app_id);
    }

    public List<GuangqiCustomerPrize> listByApp_idAndLimit(String app_id, int m, int n) {
        List<GuangqiCustomerPrize> guangqi_customer_prizeList = guangqiCustomerPrizeDao.listByApp_idAndLimit(app_id, m, n);

        for (GuangqiCustomerPrize guangqi_customer_prize : guangqi_customer_prizeList) {
            guangqi_customer_prize.put(findByCustomer_prize_id(guangqi_customer_prize.getCustomer_prize_id()));
        }

        return guangqi_customer_prizeList;
    }

    public List<GuangqiCustomerPrize> listByOrApp_idAndLimit(String app_id, int m, int n) {
        List<GuangqiCustomerPrize> guangqi_customer_prizeList = guangqiCustomerPrizeDao.listByOrApp_idAndLimit(app_id, m, n);

        for (GuangqiCustomerPrize guangqi_customer_prize : guangqi_customer_prizeList) {
            guangqi_customer_prize.put(findByCustomer_prize_id(guangqi_customer_prize.getCustomer_prize_id()));
        }

        return guangqi_customer_prizeList;
    }

    public GuangqiCustomerPrize findByCustomer_prize_id(String customer_prize_id) {
        GuangqiCustomerPrize guangqi_customer_prize = CacheUtil.get(GUANGQI_CUSTOMER_PRIZE_BY_GUANGQI_CUSTOMER_PRIZE_ID_CACHE, customer_prize_id);

        if (guangqi_customer_prize == null) {
            guangqi_customer_prize = guangqiCustomerPrizeDao.findByCustomer_prize_id(customer_prize_id);

            CacheUtil.put(GUANGQI_CUSTOMER_PRIZE_BY_GUANGQI_CUSTOMER_PRIZE_ID_CACHE, customer_prize_id, guangqi_customer_prize);
        }

        return guangqi_customer_prize;
    }

    public Boolean save(String customer_prize_id, String app_id, String customer_id, String prize_id, String customer_prize_date, String system_create_user_id, Integer prize_quantity, Integer prize_limit) {
        return guangqiCustomerPrizeDao.save(customer_prize_id, app_id, customer_id, prize_id, customer_prize_date, system_create_user_id, prize_quantity, prize_limit);
    }

    public Boolean updateValidateSystem_version(String customer_prize_id, String customer_id, String prize_id, String customer_prize_date, String system_update_user_id, Integer system_version) {
        GuangqiCustomerPrize guangqi_customer_prize = findByCustomer_prize_id(customer_prize_id);
        if (!guangqi_customer_prize.getSystem_version().equals(system_version)) {
            throw new RuntimeException(Constant.ERROR_VERSION);
        }

        boolean result = guangqiCustomerPrizeDao.update(customer_prize_id, customer_id, prize_id, customer_prize_date, system_update_user_id, system_version);

        if (result) {
            CacheUtil.remove(GUANGQI_CUSTOMER_PRIZE_BY_GUANGQI_CUSTOMER_PRIZE_ID_CACHE, customer_prize_id);
        }

        return result;
    }

    public Boolean deleteByCustomer_prize_idAndSystem_update_user_idValidateSystem_version(String customer_prize_id, String system_update_user_id, Integer system_version) {
        GuangqiCustomerPrize guangqi_customer_prize = findByCustomer_prize_id(customer_prize_id);
        if (!guangqi_customer_prize.getSystem_version().equals(system_version)) {
            throw new RuntimeException(Constant.ERROR_VERSION);
        }

        boolean result = guangqiCustomerPrizeDao.deleteByCustomer_prize_idAndSystem_version(customer_prize_id, system_update_user_id, system_version);

        if (result) {
            CacheUtil.remove(GUANGQI_CUSTOMER_PRIZE_BY_GUANGQI_CUSTOMER_PRIZE_ID_CACHE, customer_prize_id);
        }

        return result;
    }

}