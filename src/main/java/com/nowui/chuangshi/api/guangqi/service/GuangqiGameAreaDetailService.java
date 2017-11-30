package com.nowui.chuangshi.api.guangqi.service;

import com.nowui.chuangshi.api.guangqi.dao.GuangqiGameAreaDetailDao;
import com.nowui.chuangshi.api.guangqi.model.GuangqiGameAreaDetail;
import com.nowui.chuangshi.common.service.Service;
import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.util.CacheUtil;

import java.util.List;

public class GuangqiGameAreaDetailService extends Service {

    public static final GuangqiGameAreaDetailService instance = new GuangqiGameAreaDetailService();
    private final String GUANGQI_GAME_AREA_DETAIL_ITEM_CACHE = "guangqi_game_area_detail_item_cache";
    private final GuangqiGameAreaDetailDao guangqiGameAreaDetailDao = new GuangqiGameAreaDetailDao();

    public Integer adminCount(String game_area_id, String game_area_detail_type) {
        Cnd cnd = new Cnd();
        cnd.where(GuangqiGameAreaDetail.SYSTEM_STATUS, true);
        cnd.and(GuangqiGameAreaDetail.GAME_AREA_ID, game_area_id);
        cnd.andAllowEmpty(GuangqiGameAreaDetail.GAME_AREA_DETAIL_TYPE, game_area_detail_type);

        Integer count = guangqiGameAreaDetailDao.count(cnd);
        return count;
    }

    public List<GuangqiGameAreaDetail> adminList(String game_area_id, String game_area_detail_type, Integer m, Integer n) {
        Cnd cnd = new Cnd();
        cnd.where(GuangqiGameAreaDetail.SYSTEM_STATUS, true);
        cnd.and(GuangqiGameAreaDetail.GAME_AREA_ID, game_area_id);
        cnd.andAllowEmpty(GuangqiGameAreaDetail.GAME_AREA_DETAIL_TYPE, game_area_detail_type);
        cnd.asc(GuangqiGameAreaDetail.GAME_AREA_DETAIL_SORT);
        cnd.paginate(m, n);

        List<GuangqiGameAreaDetail> guangqi_game_area_detailList = guangqiGameAreaDetailDao.primaryKeyList(cnd);
        for (GuangqiGameAreaDetail guangqi_game_area_detail : guangqi_game_area_detailList) {
            guangqi_game_area_detail.put(find(guangqi_game_area_detail.getGame_area_detail_id()));
        }
        return guangqi_game_area_detailList;
    }
    
    public List<GuangqiGameAreaDetail> gameAreaList(String game_area_id) {
        Cnd cnd = new Cnd();
        cnd.where(GuangqiGameAreaDetail.SYSTEM_STATUS, true);
        cnd.and(GuangqiGameAreaDetail.GAME_AREA_ID, game_area_id);
        cnd.asc(GuangqiGameAreaDetail.GAME_AREA_DETAIL_SORT);

        List<GuangqiGameAreaDetail> guangqi_game_area_detailList = guangqiGameAreaDetailDao.primaryKeyList(cnd);
        for (GuangqiGameAreaDetail guangqi_game_area_detail : guangqi_game_area_detailList) {
            guangqi_game_area_detail.put(find(guangqi_game_area_detail.getGame_area_detail_id()));
        }
        return guangqi_game_area_detailList;
    }

    public GuangqiGameAreaDetail find(String game_area_detail_id) {
        GuangqiGameAreaDetail guangqi_game_area_detail = CacheUtil.get(GUANGQI_GAME_AREA_DETAIL_ITEM_CACHE, game_area_detail_id);

        if (guangqi_game_area_detail == null) {
            guangqi_game_area_detail = guangqiGameAreaDetailDao.find(game_area_detail_id);

            CacheUtil.put(GUANGQI_GAME_AREA_DETAIL_ITEM_CACHE, game_area_detail_id, guangqi_game_area_detail);
        }

        return guangqi_game_area_detail;
    }

    public Boolean save(GuangqiGameAreaDetail guangqi_game_area_detail, String system_create_user_id) {
        Boolean success = guangqiGameAreaDetailDao.save(guangqi_game_area_detail, system_create_user_id);
        return success;
    }

    public Boolean update(GuangqiGameAreaDetail guangqi_game_area_detail, String game_area_detail_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(GuangqiGameAreaDetail.SYSTEM_STATUS, true);
        cnd.and(GuangqiGameAreaDetail.GAME_AREA_DETAIL_ID, game_area_detail_id);
        cnd.and(GuangqiGameAreaDetail.SYSTEM_VERSION, system_version);

        Boolean success = guangqiGameAreaDetailDao.update(guangqi_game_area_detail, system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(GUANGQI_GAME_AREA_DETAIL_ITEM_CACHE, game_area_detail_id);
        }

        return success;
    }

    public Boolean delete(String game_area_detail_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(GuangqiGameAreaDetail.SYSTEM_STATUS, true);
        cnd.and(GuangqiGameAreaDetail.GAME_AREA_DETAIL_ID, game_area_detail_id);
        cnd.and(GuangqiGameAreaDetail.SYSTEM_VERSION, system_version);

        Boolean success = guangqiGameAreaDetailDao.delete(system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(GUANGQI_GAME_AREA_DETAIL_ITEM_CACHE, game_area_detail_id);
        }

        return success;
    }

}