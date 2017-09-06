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
        Cnd cnd = Cnd.where(JianglingPrize.SYSTEM_STATUS, true);
        cnd.and(JianglingPrize.APP_ID, app_id);
        cnd.andAllowEmpty(JianglingPrize.PRIZE_NAME, prize_name);

        Integer count = jianglingMemberDao.count(cnd);
        return count;
    }

    public List<JianglingPrize> adminList(String app_id, String prize_name, Integer m, Integer n) {
        Cnd cnd = Cnd.where(JianglingPrize.SYSTEM_STATUS, true);
        cnd.and(JianglingPrize.APP_ID, app_id);
        cnd.andAllowEmpty(JianglingPrize.PRIZE_NAME, prize_name).paginate(m, n);

        List<JianglingPrize> jianglingPrizeList = jianglingMemberDao.primaryKeyList(cnd);
        for (JianglingPrize jianglingPrize : jianglingPrizeList) {
            jianglingPrize.put(find(jianglingPrize.getPrize_id()));
        }
        return jianglingPrizeList;
    }

    public List<JianglingPrize> appList(String app_id) {
        Cnd cnd = Cnd.where(JianglingPrize.SYSTEM_STATUS, true);
        cnd.and(JianglingPrize.APP_ID, app_id);

        List<JianglingPrize> jianglingPrizeList = jianglingMemberDao.primaryKeyList(cnd);
        for (JianglingPrize jianglingPrize : jianglingPrizeList) {
            jianglingPrize.put(find(jianglingPrize.getPrize_id()));
        }
        return jianglingPrizeList;
    }

    public JianglingPrize find(String prize_id) {
        JianglingPrize jianglingMember = CacheUtil.get(JIANGLING_PRIZE_ITEM_CACHE, prize_id);

        if (jianglingMember == null) {
            jianglingMember = jianglingMemberDao.find(prize_id);

            CacheUtil.put(JIANGLING_PRIZE_ITEM_CACHE, prize_id, jianglingMember);
        }

        return jianglingMember;
    }

    public Boolean save(JianglingPrize jianglingMember, String system_create_user_id) {
        Boolean success = jianglingMemberDao.save(jianglingMember, system_create_user_id);
        return success;
    }

    public Boolean update(JianglingPrize jianglingMember, String prize_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = Cnd.where(JianglingPrize.SYSTEM_STATUS, true);
        cnd.and(JianglingPrize.PRIZE_ID, prize_id);
        cnd.and(JianglingPrize.SYSTEM_VERSION, system_version);

        Boolean success = jianglingMemberDao.update(jianglingMember, system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(JIANGLING_PRIZE_ITEM_CACHE, prize_id);
        }

        return success;
    }

    public Boolean delete(String prize_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = Cnd.where(JianglingPrize.SYSTEM_STATUS, true);
        cnd.and(JianglingPrize.PRIZE_ID, prize_id);
        cnd.and(JianglingPrize.SYSTEM_VERSION, system_version);

        Boolean success = jianglingMemberDao.delete(system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(JIANGLING_PRIZE_ITEM_CACHE, prize_id);
        }

        return success;
    }

}