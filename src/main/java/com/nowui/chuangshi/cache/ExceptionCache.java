package com.nowui.chuangshi.cache;

import com.alibaba.fastjson.JSON;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.dao.ExceptionDao;
import com.nowui.chuangshi.model.Exception;
import com.nowui.chuangshi.util.CacheUtil;

import java.util.Date;
import java.util.List;

public class ExceptionCache extends Cache {

    public static final String EXCEPTION_BY_EXCEPTION_ID_CACHE = "exception_by_exception_id_cache";

    private ExceptionDao exceptionDao = new ExceptionDao();

    public Integer countByApp_id(String app_id) {
        return exceptionDao.countByApp_id(app_id);
    }

    public Integer countByOrApp_id(String app_id) {
        return exceptionDao.countByOrApp_id(app_id);
    }

    public List<Exception> listByApp_idAndSystem_create_timeAndLimit(String app_id, Date system_create_time, int m, int n) {
        List<Exception> exceptionList = exceptionDao.listByApp_idAndSystem_create_timeAndLimit(app_id, system_create_time, m, n);

        for (Exception exception : exceptionList) {
            exception.put(findByException_id(exception.getException_id()));
        }

        return exceptionList;
    }

    public List<Exception> listByApp_idAndLimit(String app_id, int m, int n) {
        List<Exception> exceptionList = exceptionDao.listByApp_idAndLimit(app_id, m, n);

        for (Exception exception : exceptionList) {
            exception.put(findByException_id(exception.getException_id()));
        }

        return exceptionList;
    }

    public List<Exception> listByOrApp_idAndLimit(String app_id, int m, int n) {
        List<Exception> exceptionList = exceptionDao.listByOrApp_idAndLimit(app_id, m, n);

        for (Exception exception : exceptionList) {
            exception.put(findByException_id(exception.getException_id()));
        }

        return exceptionList;
    }

    public Exception findByException_id(String exception_id) {
        Exception exception = CacheUtil.get(EXCEPTION_BY_EXCEPTION_ID_CACHE, exception_id);

        if (exception == null) {
            exception = exceptionDao.findByException_id(exception_id);

            CacheUtil.put(EXCEPTION_BY_EXCEPTION_ID_CACHE, exception_id, exception);
        }

        return exception;
    }

    public Boolean save(String exception_id, String app_id, String http_id, String exception_content, Boolean exception_is_confirm, String exception_remark, String system_create_user_id) {
        return exceptionDao.save(exception_id, app_id, http_id, exception_content, exception_is_confirm, exception_remark, system_create_user_id);
    }

    public Boolean updateValidateSystem_version(String exception_id, String http_id, String exception_content, Boolean exception_is_confirm, String exception_remark, String system_update_user_id, Integer system_version) {
        Exception exception = findByException_id(exception_id);
        if (!exception.getSystem_version().equals(system_version)) {
            throw new RuntimeException(Constant.ERROR_VERSION);
        }

        boolean result = exceptionDao.update(http_id, exception_content, exception_is_confirm, exception_remark, system_update_user_id, system_version);

        if (result) {
            CacheUtil.remove(EXCEPTION_BY_EXCEPTION_ID_CACHE, exception_id);
        }

        return result;
    }

    public Boolean deleteByException_idAndSystem_update_user_idValidateSystem_version(String exception_id, String system_update_user_id, Integer system_version) {
        Exception exception = findByException_id(exception_id);
        if (!exception.getSystem_version().equals(system_version)) {
            throw new RuntimeException(Constant.ERROR_VERSION);
        }

        boolean result = exceptionDao.deleteByException_idAndSystem_version(exception_id, system_update_user_id, system_version);

        if (result) {
            CacheUtil.remove(EXCEPTION_BY_EXCEPTION_ID_CACHE, exception_id);
        }

        return result;
    }

}