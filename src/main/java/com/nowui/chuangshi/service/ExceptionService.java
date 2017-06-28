package com.nowui.chuangshi.service;

import com.nowui.chuangshi.cache.ExceptionCache;
import com.nowui.chuangshi.model.Exception;

import java.util.Date;
import java.util.List;

public class ExceptionService extends Service {

    private ExceptionCache exceptionCache = new ExceptionCache();

    public Integer countByApp_id(String app_id) {
        return exceptionCache.countByApp_id(app_id);
    }

    public Integer countByOrApp_id(String app_id) {
        return exceptionCache.countByOrApp_id(app_id);
    }

    public List<Exception> listByApp_idAndSystem_create_timeAndLimit(String app_id, Date system_create_time, int m, int n) {
        return exceptionCache.listByApp_idAndSystem_create_timeAndLimit(app_id, system_create_time, m, n);
    }

    public List<Exception> listByApp_idAndLimit(String app_id, int m, int n) {
        return exceptionCache.listByApp_idAndLimit(app_id, m, n);
    }

    public List<Exception> listByOrApp_idAndLimit(String app_id, int m, int n) {
        return exceptionCache.listByOrApp_idAndLimit(app_id, m, n);
    }

    public Exception findByException_id(String exception_id) {
        return exceptionCache.findByException_id(exception_id);
    }

    public Boolean save(String exception_id, String app_id, String http_id, String exception_content, Boolean exception_is_confirm, String exception_remark, String system_create_user_id) {
        return exceptionCache.save(exception_id, app_id, http_id, exception_content, exception_is_confirm, exception_remark, system_create_user_id);
    }

    public Boolean updateValidateSystem_version(String exception_id, String http_id, String exception_content, Boolean exception_is_confirm, String exception_remark, String system_update_user_id, Integer system_version) {
        return exceptionCache.updateValidateSystem_version(exception_id, http_id, exception_content, exception_is_confirm, exception_remark, system_update_user_id, system_version);
    }

    public Boolean deleteByException_idAndSystem_update_user_idValidateSystem_version(String exception_id, String system_update_user_id, Integer system_version) {
        return exceptionCache.deleteByException_idAndSystem_update_user_idValidateSystem_version(exception_id, system_update_user_id, system_version);
    }

}