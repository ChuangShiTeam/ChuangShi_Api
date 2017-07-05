package com.nowui.chuangshi.cache;

import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.dao.MemberStockActionDao;
import com.nowui.chuangshi.model.MemberStockAction;
import com.nowui.chuangshi.util.CacheUtil;

import java.util.Date;
import java.util.List;

public class MemberStockActionCache extends Cache {

    public static final String MEMBER_STOCK_ACTION_BY_MEMBER_STOCK_ACTION_ID_CACHE = "member_stock_action_by_member_stock_action_id_cache";

    private MemberStockActionDao memberStockActionDao = new MemberStockActionDao();

    public Integer countByApp_idOrLikeMember_stock_action_name(String app_id, String member_stock_action_name) {
        return memberStockActionDao.countByApp_idOrLikeMember_stock_action_name(app_id, member_stock_action_name);
    }

    public Integer countByOrApp_idOrLikeMember_stock_action_name(String app_id, String member_stock_action_name) {
        return memberStockActionDao.countByOrApp_idOrLikeMember_stock_action_name(app_id, member_stock_action_name);
    }

    public List<MemberStockAction> listByApp_idAndSystem_create_timeAndLimit(String app_id, Date system_create_time, int m, int n) {
        List<MemberStockAction> member_stock_actionList = memberStockActionDao.listByApp_idAndSystem_create_timeAndLimit(app_id, system_create_time, m, n);

        for (MemberStockAction member_stock_action : member_stock_actionList) {
            member_stock_action.put(findByMember_stock_action_id(member_stock_action.getMember_stock_action_id()));
        }

        return member_stock_actionList;
    }

    public List<MemberStockAction> listByApp_idOrLikeMember_stock_action_nameAndLimit(String app_id, String member_stock_action_name, int m, int n) {
        List<MemberStockAction> member_stock_actionList = memberStockActionDao.listByApp_idOrLikeMember_stock_action_nameAndLimit(app_id, member_stock_action_name, m, n);

        for (MemberStockAction member_stock_action : member_stock_actionList) {
            member_stock_action.put(findByMember_stock_action_id(member_stock_action.getMember_stock_action_id()));
        }

        return member_stock_actionList;
    }

    public List<MemberStockAction> listByOrApp_idOrLikeMember_stock_action_nameAndLimit(String app_id, String member_stock_action_name, int m, int n) {
        List<MemberStockAction> member_stock_actionList = memberStockActionDao.listByOrApp_idOrLikeMember_stock_action_nameAndLimit(app_id, member_stock_action_name, m, n);

        for (MemberStockAction member_stock_action : member_stock_actionList) {
            member_stock_action.put(findByMember_stock_action_id(member_stock_action.getMember_stock_action_id()));
        }

        return member_stock_actionList;
    }

    public MemberStockAction findByMember_stock_action_id(String member_stock_action_id) {
        MemberStockAction member_stock_action = CacheUtil.get(MEMBER_STOCK_ACTION_BY_MEMBER_STOCK_ACTION_ID_CACHE, member_stock_action_id);

        if (member_stock_action == null) {
            member_stock_action = memberStockActionDao.findByMember_stock_action_id(member_stock_action_id);

            CacheUtil.put(MEMBER_STOCK_ACTION_BY_MEMBER_STOCK_ACTION_ID_CACHE, member_stock_action_id, member_stock_action);
        }

        return member_stock_action;
    }

    public Boolean save(String member_stock_action_id, String app_id, String member_id, String user_id, String product_sku_id, String member_stock_action_name, Integer member_stock_quantity, String system_create_user_id) {
        return memberStockActionDao.save(member_stock_action_id, app_id, member_id, user_id, product_sku_id, member_stock_action_name, member_stock_quantity, system_create_user_id);
    }

    public Boolean updateValidateSystem_version(String member_stock_action_id, String member_id, String user_id, String product_sku_id, String member_stock_action_name, Integer member_stock_quantity, String system_update_user_id, Integer system_version) {
        MemberStockAction member_stock_action = findByMember_stock_action_id(member_stock_action_id);
        if (!member_stock_action.getSystem_version().equals(system_version)) {
            throw new RuntimeException(Constant.ERROR_VERSION);
        }

        boolean result = memberStockActionDao.update(member_stock_action_id, member_id, user_id, product_sku_id, member_stock_action_name, member_stock_quantity, system_update_user_id, system_version);

        if (result) {
            CacheUtil.remove(MEMBER_STOCK_ACTION_BY_MEMBER_STOCK_ACTION_ID_CACHE, member_stock_action_id);
        }

        return result;
    }

    public Boolean deleteByMember_stock_action_idAndSystem_update_user_idValidateSystem_version(String member_stock_action_id, String system_update_user_id, Integer system_version) {
        MemberStockAction member_stock_action = findByMember_stock_action_id(member_stock_action_id);
        if (!member_stock_action.getSystem_version().equals(system_version)) {
            throw new RuntimeException(Constant.ERROR_VERSION);
        }

        boolean result = memberStockActionDao.deleteByMember_stock_action_idAndSystem_version(member_stock_action_id, system_update_user_id, system_version);

        if (result) {
            CacheUtil.remove(MEMBER_STOCK_ACTION_BY_MEMBER_STOCK_ACTION_ID_CACHE, member_stock_action_id);
        }

        return result;
    }

}