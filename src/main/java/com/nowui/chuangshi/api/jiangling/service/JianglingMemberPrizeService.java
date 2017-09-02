package com.nowui.chuangshi.api.jiangling.service;

import com.nowui.chuangshi.api.jiangling.dao.JianglingMemberPrizeDao;
import com.nowui.chuangshi.api.jiangling.model.JianglingMemberPrize;
import com.nowui.chuangshi.common.service.Service;
import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.util.CacheUtil;

public class JianglingMemberPrizeService extends Service {

    public static final JianglingMemberPrizeService instance = new JianglingMemberPrizeService();
    private final String JIANGLING_MEMBER_PRIZE_ITEM_CACHE = "jiangling_member_prize_item_cache";
    private final JianglingMemberPrizeDao jianglingMemberPrizeDao = new JianglingMemberPrizeDao();

    public Integer userCount(String user_id) {
        Integer count = jianglingMemberPrizeDao.count(Cnd.where(JianglingMemberPrize.USER_ID, user_id));
        return count;
    }

    public Integer prizeCount(String prize_id) {
        Integer count = jianglingMemberPrizeDao.count(Cnd.where(JianglingMemberPrize.PRIZE_ID, prize_id));
        return count;
    }

    public JianglingMemberPrize find(String game_id) {
        JianglingMemberPrize jianglingMemberPrize = CacheUtil.get(JIANGLING_MEMBER_PRIZE_ITEM_CACHE, game_id);

        if (jianglingMemberPrize == null) {
            jianglingMemberPrize = jianglingMemberPrizeDao.find(game_id);

            CacheUtil.put(JIANGLING_MEMBER_PRIZE_ITEM_CACHE, game_id, jianglingMemberPrize);
        }

        return jianglingMemberPrize;
    }

    public Boolean save(JianglingMemberPrize jianglingMemberPrize) {
        Boolean result = jianglingMemberPrizeDao.save(jianglingMemberPrize);
        return result;
    }

}