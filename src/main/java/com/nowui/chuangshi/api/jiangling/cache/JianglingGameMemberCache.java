package com.nowui.chuangshi.api.jiangling.cache;

import com.nowui.chuangshi.api.jiangling.dao.JianglingGameMemberDao;
import com.nowui.chuangshi.common.cache.Cache;

public class JianglingGameMemberCache extends Cache {

    public static final String JIANGLING_GAME_MEMBER_ITEM_CACHE = "jiangling_game_member_item_cache";

    public JianglingGameMemberCache() {
        setDao(new JianglingGameMemberDao());

    }

}