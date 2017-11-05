package com.nowui.chuangshi.api.renault.service;

import com.nowui.chuangshi.api.renault.dao.RenaultShareCommentPraiseDao;
import com.nowui.chuangshi.api.renault.model.RenaultShareCommentPraise;
import com.nowui.chuangshi.common.service.Service;
import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.util.CacheUtil;

import java.util.List;

public class RenaultShareCommentPraiseService extends Service {

    public static final RenaultShareCommentPraiseService instance = new RenaultShareCommentPraiseService();
    private final String RENAULT_SHARE_COMMENT_PRAISE_ITEM_CACHE = "renault_share_comment_praise_item_cache";
    private final RenaultShareCommentPraiseDao renaultShareCommentPraiseDao = new RenaultShareCommentPraiseDao();

    public Integer adminCount(String app_id, String comment_id) {
        Cnd cnd = new Cnd();
        cnd.where(RenaultShareCommentPraise.SYSTEM_STATUS, true);
        cnd.and(RenaultShareCommentPraise.APP_ID, app_id);
        cnd.andAllowEmpty(RenaultShareCommentPraise.COMMENT_ID, comment_id);

        Integer count = renaultShareCommentPraiseDao.count(cnd);
        return count;
    }

    public List<RenaultShareCommentPraise> adminList(String app_id, String comment_id, Integer m, Integer n) {
        Cnd cnd = new Cnd();
        cnd.where(RenaultShareCommentPraise.SYSTEM_STATUS, true);
        cnd.and(RenaultShareCommentPraise.APP_ID, app_id);
        cnd.andAllowEmpty(RenaultShareCommentPraise.COMMENT_ID, comment_id);
        cnd.paginate(m, n);

        List<RenaultShareCommentPraise> renault_share_comment_praiseList = renaultShareCommentPraiseDao.primaryKeyList(cnd);
        for (RenaultShareCommentPraise renault_share_comment_praise : renault_share_comment_praiseList) {
            renault_share_comment_praise.put(find(renault_share_comment_praise.getPraise_id()));
        }
        return renault_share_comment_praiseList;
    }

    public RenaultShareCommentPraise find(String praise_id) {
        RenaultShareCommentPraise renault_share_comment_praise = CacheUtil.get(RENAULT_SHARE_COMMENT_PRAISE_ITEM_CACHE, praise_id);

        if (renault_share_comment_praise == null) {
            renault_share_comment_praise = renaultShareCommentPraiseDao.find(praise_id);

            CacheUtil.put(RENAULT_SHARE_COMMENT_PRAISE_ITEM_CACHE, praise_id, renault_share_comment_praise);
        }

        return renault_share_comment_praise;
    }

    public Boolean save(RenaultShareCommentPraise renault_share_comment_praise, String system_create_user_id) {
        Boolean success = renaultShareCommentPraiseDao.save(renault_share_comment_praise, system_create_user_id);
        return success;
    }

    public Boolean update(RenaultShareCommentPraise renault_share_comment_praise, String praise_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(RenaultShareCommentPraise.SYSTEM_STATUS, true);
        cnd.and(RenaultShareCommentPraise.PRAISE_ID, praise_id);
        cnd.and(RenaultShareCommentPraise.SYSTEM_VERSION, system_version);

        Boolean success = renaultShareCommentPraiseDao.update(renault_share_comment_praise, system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(RENAULT_SHARE_COMMENT_PRAISE_ITEM_CACHE, praise_id);
        }

        return success;
    }

    public Boolean delete(String praise_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(RenaultShareCommentPraise.SYSTEM_STATUS, true);
        cnd.and(RenaultShareCommentPraise.PRAISE_ID, praise_id);
        cnd.and(RenaultShareCommentPraise.SYSTEM_VERSION, system_version);

        Boolean success = renaultShareCommentPraiseDao.delete(system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(RENAULT_SHARE_COMMENT_PRAISE_ITEM_CACHE, praise_id);
        }

        return success;
    }

    public RenaultShareCommentPraise userAndCommentFind(String user_id, String comment_id) {
        Cnd cnd = new Cnd();
        cnd.where(RenaultShareCommentPraise.SYSTEM_STATUS, true);
        cnd.and(RenaultShareCommentPraise.USER_ID, user_id);
        cnd.and(RenaultShareCommentPraise.COMMENT_ID, comment_id);

        List<RenaultShareCommentPraise> renault_comment_praiseList = renaultShareCommentPraiseDao.primaryKeyList(cnd);

        if (renault_comment_praiseList == null || renault_comment_praiseList.size() == 0) {
            return null;
        }
        return find(renault_comment_praiseList.get(0).getPraise_id());
    }

}