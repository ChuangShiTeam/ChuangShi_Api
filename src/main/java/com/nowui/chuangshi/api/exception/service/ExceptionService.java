package com.nowui.chuangshi.api.exception.service;

import com.nowui.chuangshi.api.exception.dao.ExceptionDao;
import com.nowui.chuangshi.api.exception.model.Exception;
import com.nowui.chuangshi.common.service.Service;
import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.util.CacheUtil;

import java.util.List;

public class ExceptionService extends Service {

    public static final ExceptionService instance = new ExceptionService();
    private final String EXCEPTION_ITEM_CACHE = "exception_item_cache";
    private final ExceptionDao exceptionDao = new ExceptionDao();

    public Integer adminCount(String app_id, Boolean exception_is_confirm) {
        Cnd cnd = new Cnd();
        cnd.where(Exception.SYSTEM_STATUS, true);
        cnd.and(Exception.APP_ID, app_id);
        cnd.andAllowEmpty(Exception.EXCEPTION_IS_CONFIRM, exception_is_confirm);

        Integer count = exceptionDao.count(cnd);
        return count;
    }

    public List<Exception> adminList(String app_id, Boolean exception_is_confirm, Integer m, Integer n) {
        Cnd cnd = new Cnd();
        cnd.where(Exception.SYSTEM_STATUS, true);
        cnd.and(Exception.APP_ID, app_id);
        cnd.andAllowEmpty(Exception.EXCEPTION_IS_CONFIRM, exception_is_confirm);
        cnd.paginate(m, n);

        List<Exception> exceptionList = exceptionDao.primaryKeyList(cnd);
        for (Exception exception : exceptionList) {
            exception.put(find(exception.getException_id()));
        }
        return exceptionList;
    }

    public Exception find(String exception_id) {
        Exception exception = CacheUtil.get(EXCEPTION_ITEM_CACHE, exception_id);

        if (exception == null) {
            exception = exceptionDao.find(exception_id);

            CacheUtil.put(EXCEPTION_ITEM_CACHE, exception_id, exception);
        }

        return exception;
    }

    public Boolean save(Exception exception, String system_create_user_id) {
        Boolean success = exceptionDao.save(exception, system_create_user_id);
        return success;
    }

    public Boolean update(Exception exception, String exception_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(Exception.SYSTEM_STATUS, true);
        cnd.and(Exception.EXCEPTION_ID, exception_id);
        cnd.and(Exception.SYSTEM_VERSION, system_version);

        Boolean success = exceptionDao.update(exception, system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(EXCEPTION_ITEM_CACHE, exception_id);
        }

        return success;
    }

    public Boolean delete(String exception_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(Exception.SYSTEM_STATUS, true);
        cnd.and(Exception.EXCEPTION_ID, exception_id);
        cnd.and(Exception.SYSTEM_VERSION, system_version);

        Boolean success = exceptionDao.delete(system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(EXCEPTION_ITEM_CACHE, exception_id);
        }

        return success;
    }

}