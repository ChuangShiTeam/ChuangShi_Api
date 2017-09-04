package com.nowui.chuangshi.api.jiangling.service;

import com.nowui.chuangshi.api.jiangling.dao.JianglingGameDao;
import com.nowui.chuangshi.api.jiangling.model.JianglingGame;
import com.nowui.chuangshi.common.service.Service;
import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.util.CacheUtil;

import java.util.List;

public class JianglingGameService extends Service {

    public static final JianglingGameService instance = new JianglingGameService();
    private final String JIANGLING_GAME_ITEM_CACHE = "jiangling_game_item_cache";
    private final JianglingGameDao jianglingGameDao = new JianglingGameDao();

    public Integer adminCount(String app_id) {
        Cnd cnd = Cnd.where(JianglingGame.SYSTEM_STATUS, true);
        cnd.and(JianglingGame.APP_ID, app_id);

        Integer count = jianglingGameDao.count(cnd);
        return count;
    }

    public List<JianglingGame> adminList(String app_id, Integer m, Integer n) {
        Cnd cnd = Cnd.where(JianglingGame.SYSTEM_STATUS, true);
        cnd.and(JianglingGame.APP_ID, app_id);
        cnd.paginate(m, n);

        List<JianglingGame> jianglingGameList = jianglingGameDao.primaryKeyList(cnd);
        for (JianglingGame jianglingGame : jianglingGameList) {
            jianglingGame.put(find(jianglingGame.getGame_id()));
        }
        return jianglingGameList;
    }

    public List<JianglingGame> appList(String app_id) {
        Cnd cnd = Cnd.where(JianglingGame.SYSTEM_STATUS, true);
        cnd.and(JianglingGame.APP_ID, app_id);
        cnd.desc(JianglingGame.SYSTEM_CREATE_TIME);

        List<JianglingGame> jianglingGameList = jianglingGameDao.primaryKeyList(cnd);
        for (JianglingGame jianglingGame : jianglingGameList) {
            jianglingGame.put(find(jianglingGame.getGame_id()));
        }
        return jianglingGameList;
    }

    public JianglingGame find(String game_id) {
        JianglingGame jianglingGame = CacheUtil.get(JIANGLING_GAME_ITEM_CACHE, game_id);

        if (jianglingGame == null) {
            jianglingGame = jianglingGameDao.find(game_id);

            CacheUtil.put(JIANGLING_GAME_ITEM_CACHE, game_id, jianglingGame);
        }

        return jianglingGame;
    }

    public Boolean save(JianglingGame jianglingGame) {
        Boolean success = jianglingGameDao.save(jianglingGame);
        return success;
    }

    public Boolean update(JianglingGame jianglingGame, String game_id, Integer system_version) {
        Cnd cnd = Cnd.where(JianglingGame.SYSTEM_STATUS, true);
        cnd.and(JianglingGame.GAME_ID, game_id);
        cnd.and(JianglingGame.SYSTEM_VERSION, system_version);

        Boolean success = jianglingGameDao.update(jianglingGame, cnd);

        if (success) {
            CacheUtil.remove(JIANGLING_GAME_ITEM_CACHE, game_id);
        }

        return success;
    }

    public Boolean delete(String game_id, Integer system_version) {
        Cnd cnd = Cnd.where(JianglingGame.SYSTEM_STATUS, true);
        cnd.and(JianglingGame.GAME_ID, game_id);
        cnd.and(JianglingGame.SYSTEM_VERSION, system_version);

        Boolean success = jianglingGameDao.delete(cnd);

        if (success) {
            CacheUtil.remove(JIANGLING_GAME_ITEM_CACHE, game_id);
        }

        return success;
    }

}