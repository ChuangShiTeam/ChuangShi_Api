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
        Integer count = jianglingGameDao.count(Cnd.where(JianglingGame.APP_ID, app_id));
        return count;
    }

    public List<JianglingGame> adminList(String app_id, Integer m, Integer n) {
        List<JianglingGame> jianglingGameList = jianglingGameDao.list(Cnd.where(JianglingGame.APP_ID, app_id).paginate(m, n));
        return jianglingGameList;
    }

    public List<JianglingGame> appList(String app_id) {
        List<JianglingGame> jianglingGameList = jianglingGameDao.list(Cnd.where(JianglingGame.APP_ID, app_id).desc(JianglingGame.SYSTEM_CREATE_TIME));
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
        Boolean result = jianglingGameDao.save(jianglingGame);
        return result;
    }

    public Boolean update(JianglingGame jianglingGame, String game_id, Integer system_version) {
        Boolean result = jianglingGameDao.update(jianglingGame, Cnd.where(JianglingGame.GAME_ID, game_id).and(JianglingGame.SYSTEM_VERSION, system_version));

        if (result) {
            CacheUtil.remove(JIANGLING_GAME_ITEM_CACHE, game_id);
        }

        return result;
    }

    public Boolean delete(String game_id, Integer system_version) {
        Boolean result = jianglingGameDao.delete(Cnd.where(JianglingGame.GAME_ID, game_id).and(JianglingGame.SYSTEM_VERSION, system_version));

        if (result) {
            CacheUtil.remove(JIANGLING_GAME_ITEM_CACHE, game_id);
        }

        return result;
    }

}