package com.nowui.chuangshi.api.jiangling.service;

import com.nowui.chuangshi.api.jiangling.dao.JianglingPrizeDao;
import com.nowui.chuangshi.api.jiangling.model.JianglingPrize;
import com.nowui.chuangshi.common.service.Service;
import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.util.CacheUtil;

import java.util.List;

public class JianglingPrizeService extends Service {

    public static final JianglingPrizeService instance = new JianglingPrizeService();
    private final String JIANGLING_PRIZE_ITEM_CACHE = "jiangling_prize_item_cache";
    private final JianglingPrizeDao jianglingMemberDao = new JianglingPrizeDao();

    public Integer adminCount(String app_id, String prize_name) {
        Integer count = jianglingMemberDao.count(Cnd.where(JianglingPrize.APP_ID, app_id).andAllowEmpty(JianglingPrize.PRIZE_NAME, prize_name));
        return count;
    }

    public List<JianglingPrize> adminList(String app_id, String prize_name, Integer m, Integer n) {
        List<JianglingPrize> jianglingGameList = jianglingMemberDao.list(Cnd.where(JianglingPrize.APP_ID, app_id).andAllowEmpty(JianglingPrize.PRIZE_NAME, prize_name).paginate(m, n));
        return jianglingGameList;
    }

    public List<JianglingPrize> appList(String app_id) {
        List<JianglingPrize> jianglingGameList = jianglingMemberDao.list(Cnd.where(JianglingPrize.APP_ID, app_id));
        return jianglingGameList;
    }

    public JianglingPrize find(String prize_id) {
        JianglingPrize jianglingMember = CacheUtil.get(JIANGLING_PRIZE_ITEM_CACHE, prize_id);

        if (jianglingMember == null) {
            jianglingMember = jianglingMemberDao.find(prize_id);

            CacheUtil.put(JIANGLING_PRIZE_ITEM_CACHE, prize_id, jianglingMember);
        }

        return jianglingMember;
    }

    public Boolean save(JianglingPrize jianglingMember) {
        Boolean result = jianglingMemberDao.save(jianglingMember);
        return result;
    }

    public Boolean update(JianglingPrize jianglingMember, String prize_id, Integer system_version) {
        Boolean result = jianglingMemberDao.update(jianglingMember, Cnd.where(JianglingPrize.PRIZE_ID, prize_id).and(JianglingPrize.SYSTEM_VERSION, system_version));

        if (result) {
            CacheUtil.remove(JIANGLING_PRIZE_ITEM_CACHE, prize_id);
        }

        return result;
    }

    public Boolean delete(String prize_id, Integer system_version) {
        Boolean result = jianglingMemberDao.delete(Cnd.where(JianglingPrize.PRIZE_ID, prize_id).and(JianglingPrize.SYSTEM_VERSION, system_version));

        if (result) {
            CacheUtil.remove(JIANGLING_PRIZE_ITEM_CACHE, prize_id);
        }

        return result;
    }

}