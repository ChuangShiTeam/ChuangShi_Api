package com.nowui.chuangshi.api.jiangling.service;

import java.util.List;

import com.nowui.chuangshi.api.jiangling.dao.JianglingNewGameDao;
import com.nowui.chuangshi.api.jiangling.model.JianglingNewGame;
import com.nowui.chuangshi.common.service.Service;
import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.util.CacheUtil;

public class JianglingNewGameService extends Service {

    public static final JianglingNewGameService instance = new JianglingNewGameService();
    private final String JIANGLING_NEW_GAME_ITEM_CACHE = "jiangling_new_game_item_cache";
    private final JianglingNewGameDao jianglingNewGameDao = new JianglingNewGameDao();

    public Integer adminCount(String app_id) {
        Cnd cnd = new Cnd();
        cnd.where(JianglingNewGame.SYSTEM_STATUS, true);
        cnd.and(JianglingNewGame.APP_ID, app_id);

        Integer count = jianglingNewGameDao.count(cnd);
        return count;
    }

    public List<JianglingNewGame> adminList(String app_id, Integer m, Integer n) {
        Cnd cnd = new Cnd();
        cnd.where(JianglingNewGame.SYSTEM_STATUS, true);
        cnd.and(JianglingNewGame.APP_ID, app_id);
        cnd.paginate(m, n);

        List<JianglingNewGame> jianglingNewGameList = jianglingNewGameDao.primaryKeyList(cnd);
        for (JianglingNewGame jianglingNewGame : jianglingNewGameList) {
            jianglingNewGame.put(find(jianglingNewGame.getNew_game_id()));
        }
        return jianglingNewGameList;
    }

    public List<JianglingNewGame> appList(String app_id) {
        Cnd cnd = new Cnd();
        cnd.where(JianglingNewGame.SYSTEM_STATUS, true);
        cnd.and(JianglingNewGame.APP_ID, app_id);
        cnd.desc(JianglingNewGame.SYSTEM_CREATE_TIME);

        List<JianglingNewGame> jianglingNewGameList = jianglingNewGameDao.primaryKeyList(cnd);
        for (JianglingNewGame jianglingNewGame : jianglingNewGameList) {
            jianglingNewGame.put(find(jianglingNewGame.getNew_game_id()));
        }
        return jianglingNewGameList;
    }

    public JianglingNewGame find(String new_game_id) {
        JianglingNewGame jianglingNewGame = CacheUtil.get(JIANGLING_NEW_GAME_ITEM_CACHE, new_game_id);

        if (jianglingNewGame == null) {
            jianglingNewGame = jianglingNewGameDao.find(new_game_id);

            CacheUtil.put(JIANGLING_NEW_GAME_ITEM_CACHE, new_game_id, jianglingNewGame);
        }

        return jianglingNewGame;
    }

    public Boolean save(JianglingNewGame jianglingNewGame, String system_create_user_id) {
        Boolean success = jianglingNewGameDao.save(jianglingNewGame, system_create_user_id);
        return success;
    }

    public Boolean update(JianglingNewGame jianglingNewGame, String new_game_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(JianglingNewGame.SYSTEM_STATUS, true);
        cnd.and(JianglingNewGame.NEW_GAME_ID, new_game_id);
        cnd.and(JianglingNewGame.SYSTEM_VERSION, system_version);

        Boolean success = jianglingNewGameDao.update(jianglingNewGame, system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(JIANGLING_NEW_GAME_ITEM_CACHE, new_game_id);
        }

        return success;
    }

    public Boolean delete(String new_game_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(JianglingNewGame.SYSTEM_STATUS, true);
        cnd.and(JianglingNewGame.NEW_GAME_ID, new_game_id);
        cnd.and(JianglingNewGame.SYSTEM_VERSION, system_version);

        Boolean success = jianglingNewGameDao.delete(system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(JIANGLING_NEW_GAME_ITEM_CACHE, new_game_id);
        }

        return success;
    }

}