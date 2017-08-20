package com.nowui.chuangshi.api.jiangling.cache;

import com.nowui.chuangshi.api.jiangling.dao.JianglingGameDao;
import com.nowui.chuangshi.api.jiangling.model.JianglingGame;
import com.nowui.chuangshi.common.cache.Cache;

public class JianglingGameCache extends Cache {

    public static final String JIANGLING_GAME_ITEM_CACHE = "jiangling_game_item_cache";

    public JianglingGameCache() {
        setDao(new JianglingGameDao());

        setItemCache(JIANGLING_GAME_ITEM_CACHE, JianglingGame.GAME_ID);
    }

}