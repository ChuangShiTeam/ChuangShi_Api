package com.nowui.chuangshi.api.infiniti.service;

import com.nowui.chuangshi.api.infiniti.dao.InfinitiPrizeDao;
import com.nowui.chuangshi.api.infiniti.model.InfinitiPrize;
import com.nowui.chuangshi.common.service.Service;
import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.util.CacheUtil;

import java.util.List;

public class InfinitiPrizeService extends Service {

    public static final InfinitiPrizeService instance = new InfinitiPrizeService();
    private final String INFINITI_PRIZE_ITEM_CACHE = "infiniti_prize_item_cache";
    private final InfinitiPrizeDao infinitiPrizeDao = new InfinitiPrizeDao();

    public Integer adminCount(String app_id, String prize_name) {
        Cnd cnd = new Cnd();
        cnd.where(InfinitiPrize.SYSTEM_STATUS, true);
        cnd.and(InfinitiPrize.APP_ID, app_id);
        cnd.andAllowEmpty(InfinitiPrize.PRIZE_NAME, prize_name);

        Integer count = infinitiPrizeDao.count(cnd);
        return count;
    }

    public List<InfinitiPrize> adminList(String app_id, String prize_name, Integer m, Integer n) {
        Cnd cnd = new Cnd();
        cnd.where(InfinitiPrize.SYSTEM_STATUS, true);
        cnd.and(InfinitiPrize.APP_ID, app_id);
        cnd.andAllowEmpty(InfinitiPrize.PRIZE_NAME, prize_name);
        cnd.paginate(m, n);

        List<InfinitiPrize> infiniti_prizeList = infinitiPrizeDao.primaryKeyList(cnd);
        for (InfinitiPrize infiniti_prize : infiniti_prizeList) {
            infiniti_prize.put(find(infiniti_prize.getPrize_id()));
        }
        return infiniti_prizeList;
    }

    public List<InfinitiPrize> appList(String app_id) {
        Cnd cnd = new Cnd();
        cnd.where(InfinitiPrize.SYSTEM_STATUS, true);
        cnd.and(InfinitiPrize.APP_ID, app_id);

        List<InfinitiPrize> infiniti_prizeList = infinitiPrizeDao.primaryKeyList(cnd);
        for (InfinitiPrize infiniti_prize : infiniti_prizeList) {
            infiniti_prize.put(find(infiniti_prize.getPrize_id()));
        }
        return infiniti_prizeList;
    }

    public InfinitiPrize find(String prize_id) {
        InfinitiPrize infiniti_prize = CacheUtil.get(INFINITI_PRIZE_ITEM_CACHE, prize_id);

        if (infiniti_prize == null) {
            infiniti_prize = infinitiPrizeDao.find(prize_id);

            CacheUtil.put(INFINITI_PRIZE_ITEM_CACHE, prize_id, infiniti_prize);
        }

        return infiniti_prize;
    }

    public Boolean save(InfinitiPrize infiniti_prize, String system_create_user_id) {
        Boolean success = infinitiPrizeDao.save(infiniti_prize, system_create_user_id);
        return success;
    }

    public Boolean update(InfinitiPrize infiniti_prize, String prize_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(InfinitiPrize.SYSTEM_STATUS, true);
        cnd.and(InfinitiPrize.PRIZE_ID, prize_id);
        cnd.and(InfinitiPrize.SYSTEM_VERSION, system_version);

        Boolean success = infinitiPrizeDao.update(infiniti_prize, system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(INFINITI_PRIZE_ITEM_CACHE, prize_id);
        }

        return success;
    }

    public Boolean delete(String prize_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(InfinitiPrize.SYSTEM_STATUS, true);
        cnd.and(InfinitiPrize.PRIZE_ID, prize_id);
        cnd.and(InfinitiPrize.SYSTEM_VERSION, system_version);

        Boolean success = infinitiPrizeDao.delete(system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(INFINITI_PRIZE_ITEM_CACHE, prize_id);
        }

        return success;
    }

}