package com.nowui.chuangshi.api.guangqi.service;

import com.nowui.chuangshi.api.guangqi.dao.GuangqiGameAreaDao;
import com.nowui.chuangshi.api.guangqi.model.GuangqiGameArea;
import com.nowui.chuangshi.common.service.Service;
import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.util.CacheUtil;

import java.util.List;

public class GuangqiGameAreaService extends Service {

    public static final GuangqiGameAreaService instance = new GuangqiGameAreaService();
    private final String GUANGQI_GAME_AREA_ITEM_CACHE = "guangqi_game_area_item_cache";
    private final GuangqiGameAreaDao guangqiGameAreaDao = new GuangqiGameAreaDao();

    public Integer adminCount(String app_id, String game_area_name) {
        Cnd cnd = new Cnd();
        cnd.where(GuangqiGameArea.SYSTEM_STATUS, true);
        cnd.and(GuangqiGameArea.APP_ID, app_id);
        cnd.andLikeAllowEmpty(GuangqiGameArea.GAME_AREA_NAME, game_area_name);

        Integer count = guangqiGameAreaDao.count(cnd);
        return count;
    }

    public List<GuangqiGameArea> adminList(String app_id, String game_area_name, Integer m, Integer n) {
        Cnd cnd = new Cnd();
        cnd.where(GuangqiGameArea.SYSTEM_STATUS, true);
        cnd.and(GuangqiGameArea.APP_ID, app_id);
        cnd.andLikeAllowEmpty(GuangqiGameArea.GAME_AREA_NAME, game_area_name);
        cnd.asc(GuangqiGameArea.GAME_AREA_SORT);
        cnd.paginate(m, n);

        List<GuangqiGameArea> guangqi_game_areaList = guangqiGameAreaDao.primaryKeyList(cnd);
        for (GuangqiGameArea guangqi_game_area : guangqi_game_areaList) {
            guangqi_game_area.put(find(guangqi_game_area.getGame_area_id()));
        }
        return guangqi_game_areaList;
    }
    
    public List<GuangqiGameArea> appList(String app_id) {
        Cnd cnd = new Cnd();
        cnd.where(GuangqiGameArea.SYSTEM_STATUS, true);
        cnd.and(GuangqiGameArea.APP_ID, app_id);
        cnd.asc(GuangqiGameArea.GAME_AREA_SORT);
        
        List<GuangqiGameArea> guangqi_game_areaList = guangqiGameAreaDao.primaryKeyList(cnd);
        for (GuangqiGameArea guangqi_game_area : guangqi_game_areaList) {
            guangqi_game_area.put(find(guangqi_game_area.getGame_area_id()));
        }
        return guangqi_game_areaList;
    }

    public GuangqiGameArea find(String game_area_id) {
        GuangqiGameArea guangqi_game_area = CacheUtil.get(GUANGQI_GAME_AREA_ITEM_CACHE, game_area_id);

        if (guangqi_game_area == null) {
            guangqi_game_area = guangqiGameAreaDao.find(game_area_id);

            CacheUtil.put(GUANGQI_GAME_AREA_ITEM_CACHE, game_area_id, guangqi_game_area);
        }

        return guangqi_game_area;
    }

    public Boolean save(GuangqiGameArea guangqi_game_area, String system_create_user_id) {
        Boolean success = guangqiGameAreaDao.save(guangqi_game_area, system_create_user_id);
        return success;
    }

    public Boolean update(GuangqiGameArea guangqi_game_area, String game_area_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(GuangqiGameArea.SYSTEM_STATUS, true);
        cnd.and(GuangqiGameArea.GAME_AREA_ID, game_area_id);
        cnd.and(GuangqiGameArea.SYSTEM_VERSION, system_version);

        Boolean success = guangqiGameAreaDao.update(guangqi_game_area, system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(GUANGQI_GAME_AREA_ITEM_CACHE, game_area_id);
        }

        return success;
    }

    public Boolean delete(String game_area_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(GuangqiGameArea.SYSTEM_STATUS, true);
        cnd.and(GuangqiGameArea.GAME_AREA_ID, game_area_id);
        cnd.and(GuangqiGameArea.SYSTEM_VERSION, system_version);

        Boolean success = guangqiGameAreaDao.delete(system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(GUANGQI_GAME_AREA_ITEM_CACHE, game_area_id);
        }

        return success;
    }

}