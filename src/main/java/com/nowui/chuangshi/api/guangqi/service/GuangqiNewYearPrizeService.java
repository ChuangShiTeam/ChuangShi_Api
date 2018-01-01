package com.nowui.chuangshi.api.guangqi.service;

import com.nowui.chuangshi.api.guangqi.dao.GuangqiNewYearPrizeDao;
import com.nowui.chuangshi.api.guangqi.model.GuangqiNewYearPrize;
import com.nowui.chuangshi.common.service.Service;
import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.util.CacheUtil;

import java.util.List;

public class GuangqiNewYearPrizeService extends Service {

    public static final GuangqiNewYearPrizeService instance = new GuangqiNewYearPrizeService();
    private final String GUANGQI_NEW_YEAR_PRIZE_ITEM_CACHE = "guangqi_new_year_prize_item_cache";
    private final GuangqiNewYearPrizeDao guangqiNewYearPrizeDao = new GuangqiNewYearPrizeDao();

    public Integer adminCount(String app_id, String new_year_prize_name) {
        Cnd cnd = new Cnd();
        cnd.where(GuangqiNewYearPrize.SYSTEM_STATUS, true);
        cnd.and(GuangqiNewYearPrize.APP_ID, app_id);
        cnd.andLikeAllowEmpty(GuangqiNewYearPrize.NEW_YEAR_PRIZE_NAME, new_year_prize_name);
        cnd.asc(GuangqiNewYearPrize.NEW_YEAR_PRIZE_SORT);

        Integer count = guangqiNewYearPrizeDao.count(cnd);
        return count;
    }

    public List<GuangqiNewYearPrize> adminList(String app_id, String new_year_prize_name, Integer m, Integer n) {
        Cnd cnd = new Cnd();
        cnd.where(GuangqiNewYearPrize.SYSTEM_STATUS, true);
        cnd.and(GuangqiNewYearPrize.APP_ID, app_id);
        cnd.andLikeAllowEmpty(GuangqiNewYearPrize.NEW_YEAR_PRIZE_NAME, new_year_prize_name);
        cnd.asc(GuangqiNewYearPrize.NEW_YEAR_PRIZE_SORT);
        cnd.paginate(m, n);

        List<GuangqiNewYearPrize> guangqi_new_year_prizeList = guangqiNewYearPrizeDao.primaryKeyList(cnd);
        for (GuangqiNewYearPrize guangqi_new_year_prize : guangqi_new_year_prizeList) {
            guangqi_new_year_prize.put(find(guangqi_new_year_prize.getNew_year_prize_id()));
        }
        return guangqi_new_year_prizeList;
    }
    
    public List<GuangqiNewYearPrize> appList(String app_id) {
        Cnd cnd = new Cnd();
        cnd.where(GuangqiNewYearPrize.SYSTEM_STATUS, true);
        cnd.and(GuangqiNewYearPrize.APP_ID, app_id);
        cnd.asc(GuangqiNewYearPrize.NEW_YEAR_PRIZE_SORT);

        List<GuangqiNewYearPrize> guangqi_new_year_prizeList = guangqiNewYearPrizeDao.primaryKeyList(cnd);
        for (GuangqiNewYearPrize guangqi_new_year_prize : guangqi_new_year_prizeList) {
            guangqi_new_year_prize.put(find(guangqi_new_year_prize.getNew_year_prize_id()));
        }
        return guangqi_new_year_prizeList;
    }

    public GuangqiNewYearPrize find(String new_year_prize_id) {
        GuangqiNewYearPrize guangqi_new_year_prize = CacheUtil.get(GUANGQI_NEW_YEAR_PRIZE_ITEM_CACHE, new_year_prize_id);

        if (guangqi_new_year_prize == null) {
            guangqi_new_year_prize = guangqiNewYearPrizeDao.find(new_year_prize_id);

            CacheUtil.put(GUANGQI_NEW_YEAR_PRIZE_ITEM_CACHE, new_year_prize_id, guangqi_new_year_prize);
        }

        return guangqi_new_year_prize;
    }

    public Boolean save(GuangqiNewYearPrize guangqi_new_year_prize, String system_create_user_id) {
        Boolean success = guangqiNewYearPrizeDao.save(guangqi_new_year_prize, system_create_user_id);
        return success;
    }

    public Boolean update(GuangqiNewYearPrize guangqi_new_year_prize, String new_year_prize_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(GuangqiNewYearPrize.SYSTEM_STATUS, true);
        cnd.and(GuangqiNewYearPrize.NEW_YEAR_PRIZE_ID, new_year_prize_id);
        cnd.and(GuangqiNewYearPrize.SYSTEM_VERSION, system_version);

        Boolean success = guangqiNewYearPrizeDao.update(guangqi_new_year_prize, system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(GUANGQI_NEW_YEAR_PRIZE_ITEM_CACHE, new_year_prize_id);
        }

        return success;
    }

    public Boolean delete(String new_year_prize_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(GuangqiNewYearPrize.SYSTEM_STATUS, true);
        cnd.and(GuangqiNewYearPrize.NEW_YEAR_PRIZE_ID, new_year_prize_id);
        cnd.and(GuangqiNewYearPrize.SYSTEM_VERSION, system_version);

        Boolean success = guangqiNewYearPrizeDao.delete(system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(GUANGQI_NEW_YEAR_PRIZE_ITEM_CACHE, new_year_prize_id);
        }

        return success;
    }

}