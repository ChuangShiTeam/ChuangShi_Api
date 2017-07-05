package com.nowui.chuangshi.service;

import com.nowui.chuangshi.cache.GuangqiPrizeCache;
import com.nowui.chuangshi.model.GuangqiPrize;

import java.util.Date;
import java.util.List;

public class GuangqiPrizeService extends Service {

    private GuangqiPrizeCache guangqiPrizeCache = new GuangqiPrizeCache();

    public Integer countByApp_id(String app_id) {
        return guangqiPrizeCache.countByApp_id(app_id);
    }

    public Integer countByOrApp_id(String app_id) {
        return guangqiPrizeCache.countByOrApp_id(app_id);
    }

    public List<GuangqiPrize> listByApp_idAndSystem_create_timeAndLimit(String app_id, Date system_create_time, int m, int n) {
        return guangqiPrizeCache.listByApp_idAndSystem_create_timeAndLimit(app_id, system_create_time, m, n);
    }

    public List<GuangqiPrize> listByApp_idAndPrize_nameAndLimit(String app_id, String prize_name, int m, int n) {
        return guangqiPrizeCache.listByApp_idAndPrize_nameAndLimit(app_id, prize_name, m, n);
    }

    public List<GuangqiPrize> listByApp_id(String app_id) {
        return guangqiPrizeCache.listByApp_id(app_id);
    }

    public List<GuangqiPrize> listByOrApp_idAndLimit(String app_id, int m, int n) {
        return guangqiPrizeCache.listByOrApp_idAndLimit(app_id, m, n);
    }

    public GuangqiPrize findByPrize_id(String prize_id) {
        return guangqiPrizeCache.findByPrize_id(prize_id);
    }

    public Boolean save(String prize_id, String app_id, String prize_name, Integer prize_probability, Integer prize_quantity, Integer prize_limit, Integer prize_sort, Boolean prize_is_default, String system_create_user_id) {
        return guangqiPrizeCache.save(prize_id, app_id, prize_name, prize_probability, prize_quantity, prize_limit, prize_sort, prize_is_default, system_create_user_id);
    }

    public Boolean updateValidateSystem_version(String prize_id, String prize_name, Integer prize_probability, Integer prize_quantity, Integer prize_limit, Integer prize_sort, Boolean prize_is_default, String system_update_user_id, Integer system_version) {
        return guangqiPrizeCache.updateValidateSystem_version(prize_id, prize_name, prize_probability, prize_quantity, prize_limit, prize_sort, prize_is_default, system_update_user_id, system_version);
    }

    public Boolean deleteByPrize_idAndSystem_update_user_idValidateSystem_version(String prize_id, String system_update_user_id, Integer system_version) {
        return guangqiPrizeCache.deleteByPrize_idAndSystem_update_user_idValidateSystem_version(prize_id, system_update_user_id, system_version);
    }

}