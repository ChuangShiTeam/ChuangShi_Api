package com.nowui.chuangshi.cache;

import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.dao.GuangqiPrizeDao;
import com.nowui.chuangshi.model.GuangqiPrize;
import com.nowui.chuangshi.util.CacheUtil;

import java.util.Date;
import java.util.List;

public class GuangqiPrizeCache extends Cache {

    public static final String GUANGQI_PRIZE_BY_GUANGQI_PRIZE_ID_CACHE = "guangqi_prize_by_prize_id_cache";

    private GuangqiPrizeDao guangqiPrizeDao = new GuangqiPrizeDao();

    public Integer countByApp_id(String app_id, String request_app_id, String request_http_id, String request_user_id) {
        return guangqiPrizeDao.countByApp_id(app_id, request_app_id, request_http_id, request_user_id);
    }

    public Integer countByOrApp_id(String app_id, String request_app_id, String request_http_id, String request_user_id) {
        return guangqiPrizeDao.countByOrApp_id(app_id, request_app_id, request_http_id, request_user_id);
    }

    public List<GuangqiPrize> listByApp_idAndSystem_create_timeAndLimit(String app_id, Date system_create_time, int m, int n, String request_app_id, String request_http_id, String request_user_id) {
        List<GuangqiPrize> guangqi_prizeList = guangqiPrizeDao.listByApp_idAndSystem_create_timeAndLimit(app_id, system_create_time, m, n, request_app_id, request_http_id, request_user_id);

        for (GuangqiPrize guangqi_prize : guangqi_prizeList) {
            guangqi_prize.put(findByPrize_id(guangqi_prize.getPrize_id(), request_app_id, request_http_id, request_user_id));
        }

        return guangqi_prizeList;
    }

    public List<GuangqiPrize> listByApp_idAndPrize_nameAndLimit(String app_id, String prize_name, int m, int n, String request_app_id, String request_http_id, String request_user_id) {
        List<GuangqiPrize> guangqi_prizeList = guangqiPrizeDao.listByApp_idAndPrize_nameAndLimit(app_id, prize_name, m, n, request_app_id, request_http_id, request_user_id);

        for (GuangqiPrize guangqi_prize : guangqi_prizeList) {
            guangqi_prize.put(findByPrize_id(guangqi_prize.getPrize_id(), request_app_id, request_http_id, request_user_id));
        }

        return guangqi_prizeList;
    }

    public List<GuangqiPrize> listByApp_id(String app_id, String request_app_id, String request_http_id, String request_user_id) {
        List<GuangqiPrize> guangqi_prizeList = guangqiPrizeDao.listByApp_id(app_id, request_app_id, request_http_id, request_user_id);

        for (GuangqiPrize guangqi_prize : guangqi_prizeList) {
            guangqi_prize.put(findByPrize_id(guangqi_prize.getPrize_id(), request_app_id, request_http_id, request_user_id));
        }

        return guangqi_prizeList;
    }

    public List<GuangqiPrize> listByOrApp_idAndLimit(String app_id, int m, int n, String request_app_id, String request_http_id, String request_user_id) {
        List<GuangqiPrize> guangqi_prizeList = guangqiPrizeDao.listByOrApp_idAndLimit(app_id, m, n, request_app_id, request_http_id, request_user_id);

        for (GuangqiPrize guangqi_prize : guangqi_prizeList) {
            guangqi_prize.put(findByPrize_id(guangqi_prize.getPrize_id(), request_app_id, request_http_id, request_user_id));
        }

        return guangqi_prizeList;
    }

    public GuangqiPrize findByPrize_id(String prize_id, String request_app_id, String request_http_id, String request_user_id) {
        GuangqiPrize guangqi_prize = CacheUtil.get(GUANGQI_PRIZE_BY_GUANGQI_PRIZE_ID_CACHE, prize_id);

        if (guangqi_prize == null) {
            guangqi_prize = guangqiPrizeDao.findByPrize_id(prize_id, request_app_id, request_http_id, request_user_id);

            CacheUtil.put(GUANGQI_PRIZE_BY_GUANGQI_PRIZE_ID_CACHE, prize_id, guangqi_prize);
        }

        return guangqi_prize;
    }

    public Boolean save(String prize_id, String app_id, String prize_name, Integer prize_probability, Integer prize_quantity, Integer prize_limit, Integer prize_sort, Boolean prize_is_default, String system_create_user_id, String request_app_id, String request_http_id, String request_user_id) {
        return guangqiPrizeDao.save(prize_id, app_id, prize_name, prize_probability, prize_quantity, prize_limit, prize_sort, prize_is_default, system_create_user_id, request_app_id, request_http_id, request_user_id);
    }

    public Boolean updateValidateSystem_version(String prize_id, String prize_name, Integer prize_probability, Integer prize_quantity, Integer prize_limit, Integer prize_sort, Boolean prize_is_default, String system_update_user_id, Integer system_version, String request_app_id, String request_http_id, String request_user_id) {
        GuangqiPrize guangqi_prize = findByPrize_id(prize_id, request_app_id, request_http_id, request_user_id);
        if (!guangqi_prize.getSystem_version().equals(system_version)) {
            throw new RuntimeException(Constant.ERROR_VERSION);
        }

        boolean result = guangqiPrizeDao.update(prize_id, prize_name, prize_probability, prize_quantity, prize_limit, prize_sort, prize_is_default, system_update_user_id, system_version, request_app_id, request_http_id, request_user_id);

        if (result) {
            CacheUtil.remove(GUANGQI_PRIZE_BY_GUANGQI_PRIZE_ID_CACHE, prize_id);
        }

        return result;
    }

    public Boolean deleteByPrize_idAndSystem_update_user_idValidateSystem_version(String prize_id, String system_update_user_id, Integer system_version, String request_app_id, String request_http_id, String request_user_id) {
        GuangqiPrize guangqi_prize = findByPrize_id(prize_id, request_app_id, request_http_id, request_user_id);
        if (!guangqi_prize.getSystem_version().equals(system_version)) {
            throw new RuntimeException(Constant.ERROR_VERSION);
        }

        boolean result = guangqiPrizeDao.deleteByPrize_idAndSystem_version(prize_id, system_update_user_id, system_version, request_app_id, request_http_id, request_user_id);

        if (result) {
            CacheUtil.remove(GUANGQI_PRIZE_BY_GUANGQI_PRIZE_ID_CACHE, prize_id);
        }

        return result;
    }

}