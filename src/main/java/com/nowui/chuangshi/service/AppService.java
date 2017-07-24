package com.nowui.chuangshi.service;

import com.nowui.chuangshi.cache.AppCache;
import com.nowui.chuangshi.model.App;

import java.util.Date;
import java.util.List;

public class AppService extends Service {

    private AppCache appCache = new AppCache();

    public Integer countByApp_idOrLikeApp_name(String app_id, String app_name) {
        return appCache.countByApp_idOrLikeApp_name(app_id, app_name);
    }

    public Integer countByOrApp_idOrLikeApp_name(String app_id, String app_name) {
        return appCache.countByOrApp_idOrLikeApp_name(app_id, app_name);
    }

    public List<App> list() {
        return appCache.list();
    }

    public List<App> listByApp_idAndSystem_create_timeAndLimit(String app_id, Date system_create_time, int m, int n) {
        return appCache.listByApp_idAndSystem_create_timeAndLimit(app_id, system_create_time, m, n);
    }

    public List<App> listByApp_idOrLikeApp_nameAndLimit(String app_id, String app_name, int m, int n) {
        return appCache.listByApp_idOrLikeApp_nameAndLimit(app_id, app_name, m, n);
    }

    public List<App> listByOrApp_idOrLikeApp_nameAndLimit(String app_id, String app_name, int m, int n) {
        return appCache.listByOrApp_idOrLikeApp_nameAndLimit(app_id, app_name, m, n);
    }

    public App findByApp_id(String app_id) {
        return appCache.findByApp_id(app_id);
    }

    public Boolean save(String app_id, String app_name, String app_secret, String wechat_app_id, String wechat_app_secret, String wechat_mch_id, String wechat_mch_key, String wechat_token, String wechat_encoding_aes_key, Boolean app_is_create_warehouse, Boolean app_is_delivery, Boolean app_is_audit_member, Boolean app_is_commission, Integer app_commission_level, String system_create_user_id) {
        return appCache.save(app_id, app_name, app_secret, wechat_app_id, wechat_app_secret, wechat_mch_id, wechat_mch_key, wechat_token, wechat_encoding_aes_key, app_is_create_warehouse, app_is_delivery, app_is_audit_member, app_is_commission, app_commission_level, system_create_user_id);
    }

    public Boolean updateValidateSystem_version(String app_id, String app_name, String app_secret, String wechat_app_id, String wechat_app_secret, String wechat_mch_id, String wechat_mch_key, String wechat_token, String wechat_encoding_aes_key, Boolean app_is_create_warehouse, Boolean app_is_delivery, Boolean app_is_audit_member, Boolean app_is_commission, Integer app_commission_level, String system_update_user_id, Integer system_version) {
        return appCache.updateValidateSystem_version(app_id, app_name, app_secret, wechat_app_id, wechat_app_secret, wechat_mch_id, wechat_mch_key, wechat_token, wechat_encoding_aes_key, app_is_create_warehouse, app_is_delivery, app_is_audit_member, app_is_commission, app_commission_level, system_update_user_id, system_version);
    }
    
    public Boolean deleteByApp_idAndSystem_update_user_idValidateSystem_version(String app_id, String system_update_user_id, Integer system_version) {
        return appCache.deleteByApp_idAndSystem_update_user_idValidateSystem_version(app_id, system_update_user_id, system_version);
    }

}