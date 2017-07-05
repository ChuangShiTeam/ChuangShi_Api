package com.nowui.chuangshi.service;

import com.nowui.chuangshi.cache.MemberStockActionCache;
import com.nowui.chuangshi.model.MemberStockAction;

import java.util.Date;
import java.util.List;

public class MemberStockActionService extends Service {

    private MemberStockActionCache memberStockActionCache = new MemberStockActionCache();

    public Integer countByApp_idOrLikeMember_stock_action_name(String app_id, String member_stock_action_name, String request_app_id, String request_http_id, String request_user_id) {
        return memberStockActionCache.countByApp_idOrLikeMember_stock_action_name(app_id, member_stock_action_name, request_app_id, request_http_id, request_user_id);
    }

    public Integer countByOrApp_idOrLikeMember_stock_action_name(String app_id, String member_stock_action_name, String request_app_id, String request_http_id, String request_user_id) {
        return memberStockActionCache.countByOrApp_idOrLikeMember_stock_action_name(app_id, member_stock_action_name, request_app_id, request_http_id, request_user_id);
    }

    public List<MemberStockAction> listByApp_idAndSystem_create_timeAndLimit(String app_id, Date system_create_time, int m, int n, String request_app_id, String request_http_id, String request_user_id) {
        return memberStockActionCache.listByApp_idAndSystem_create_timeAndLimit(app_id, system_create_time, m, n, request_app_id, request_http_id, request_user_id);
    }

    public List<MemberStockAction> listByApp_idOrLikeMember_stock_action_nameAndLimit(String app_id, String member_stock_action_name, int m, int n, String request_app_id, String request_http_id, String request_user_id) {
        return memberStockActionCache.listByApp_idOrLikeMember_stock_action_nameAndLimit(app_id, member_stock_action_name, m, n, request_app_id, request_http_id, request_user_id);
    }

    public List<MemberStockAction> listByOrApp_idOrLikeMember_stock_action_nameAndLimit(String app_id, String member_stock_action_name, int m, int n, String request_app_id, String request_http_id, String request_user_id) {
        return memberStockActionCache.listByOrApp_idOrLikeMember_stock_action_nameAndLimit(app_id, member_stock_action_name, m, n, request_app_id, request_http_id, request_user_id);
    }

    public MemberStockAction findByMember_stock_action_id(String member_stock_action_id, String request_app_id, String request_http_id, String request_user_id) {
        return memberStockActionCache.findByMember_stock_action_id(member_stock_action_id, request_app_id, request_http_id, request_user_id);
    }

    public Boolean save(String member_stock_action_id, String app_id, String member_id, String user_id, String product_sku_id, String member_stock_action_name, Integer member_stock_quantity, String system_create_user_id, String request_app_id, String request_http_id, String request_user_id) {
        return memberStockActionCache.save(member_stock_action_id, app_id, member_id, user_id, product_sku_id, member_stock_action_name, member_stock_quantity, system_create_user_id, request_app_id, request_http_id, request_user_id);
    }

    public Boolean updateValidateSystem_version(String member_stock_action_id, String member_id, String user_id, String product_sku_id, String member_stock_action_name, Integer member_stock_quantity, String system_update_user_id, Integer system_version, String request_app_id, String request_http_id, String request_user_id) {
        return memberStockActionCache.updateValidateSystem_version(member_stock_action_id, member_id, user_id, product_sku_id, member_stock_action_name, member_stock_quantity, system_update_user_id, system_version, request_app_id, request_http_id, request_user_id);
    }

    public Boolean deleteByMember_stock_action_idAndSystem_update_user_idValidateSystem_version(String member_stock_action_id, String system_update_user_id, Integer system_version, String request_app_id, String request_http_id, String request_user_id) {
        return memberStockActionCache.deleteByMember_stock_action_idAndSystem_update_user_idValidateSystem_version(member_stock_action_id, system_update_user_id, system_version, request_app_id, request_http_id, request_user_id);
    }

}