package com.nowui.chuangshi.api.xietong.service;

import com.nowui.chuangshi.api.xietong.dao.XietongClazzDao;
import com.nowui.chuangshi.api.xietong.model.XietongClazz;
import com.nowui.chuangshi.api.xietong.model.XietongOrganization;
import com.nowui.chuangshi.common.service.Service;
import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.util.CacheUtil;

import java.util.List;

public class XietongClazzService extends Service {

    public static final XietongClazzService instance = new XietongClazzService();
    private final String XIETONG_CLAZZ_ITEM_CACHE = "xietong_clazz_item_cache";
    private final XietongClazzDao xietongClazzDao = new XietongClazzDao();

    public Integer adminCount(String app_id, String clazz_name, String organization_id) {
        Cnd cnd = new Cnd();
        cnd.where(XietongClazz.SYSTEM_STATUS, true);
        cnd.and(XietongClazz.APP_ID, app_id);
        cnd.andLikeAllowEmpty(XietongClazz.CLAZZ_NAME, clazz_name);
        cnd.andAllowEmpty(XietongClazz.ORGANIZATION_ID, organization_id);

        Integer count = xietongClazzDao.count(cnd);
        return count;
    }

    public List<XietongClazz> adminList(String app_id, String clazz_name, String organization_id, Integer m, Integer n) {
        Cnd cnd = new Cnd();
        cnd.select(XietongOrganization.TABLE_XIETONG_ORGANIZATION + "." + XietongOrganization.ORGANIZATION_NAME);
        cnd.leftJoin(XietongOrganization.TABLE_XIETONG_ORGANIZATION, XietongOrganization.ORGANIZATION_ID, XietongClazz.TABLE_XIETONG_CLAZZ, XietongClazz.ORGANIZATION_ID);
        cnd.where(XietongClazz.TABLE_XIETONG_CLAZZ + "." + XietongClazz.SYSTEM_STATUS, true);
        cnd.and(XietongClazz.TABLE_XIETONG_CLAZZ + "." + XietongClazz.APP_ID, app_id);
        cnd.andAllowEmpty(XietongClazz.TABLE_XIETONG_CLAZZ + "." + XietongClazz.ORGANIZATION_ID, organization_id);
        cnd.andLikeAllowEmpty(XietongClazz.TABLE_XIETONG_CLAZZ + "." + XietongClazz.CLAZZ_NAME, clazz_name);
        cnd.asc(XietongClazz.TABLE_XIETONG_CLAZZ + "." + XietongClazz.CLAZZ_SORT);
        cnd.desc(XietongClazz.TABLE_XIETONG_CLAZZ + "." + XietongClazz.SYSTEM_CREATE_TIME);
        cnd.paginate(m, n);

        List<XietongClazz> xietong_clazzList = xietongClazzDao.primaryKeyList(cnd);
        for (XietongClazz xietong_clazz : xietong_clazzList) {
            xietong_clazz.put(find(xietong_clazz.getClazz_id()));
        }
        return xietong_clazzList;
    }
    
    public List<XietongClazz> mobileList(String app_id, String clazz_name, Integer m, Integer n) {
        Cnd cnd = new Cnd();
        cnd.where(XietongClazz.SYSTEM_STATUS, true);
        cnd.and(XietongClazz.APP_ID, app_id);
        cnd.andLikeAllowEmpty(XietongClazz.CLAZZ_NAME, clazz_name);
        cnd.asc(XietongClazz.CLAZZ_SORT);
        cnd.desc(XietongClazz.SYSTEM_CREATE_TIME);
        cnd.paginate(m, n);

        List<XietongClazz> xietong_clazzList = xietongClazzDao.primaryKeyList(cnd);
        for (XietongClazz xietong_clazz : xietong_clazzList) {
            xietong_clazz.put(find(xietong_clazz.getClazz_id()));
        }
        return xietong_clazzList;
    }

    public List<XietongClazz> allList(String app_id) {
        Cnd cnd = new Cnd();
        cnd.where(XietongClazz.SYSTEM_STATUS, true);
        cnd.and(XietongClazz.APP_ID, app_id);
        cnd.asc(XietongClazz.CLAZZ_SORT);
        cnd.desc(XietongClazz.SYSTEM_CREATE_TIME);

        List<XietongClazz> xietong_clazzList = xietongClazzDao.primaryKeyList(cnd);
        for (XietongClazz xietong_clazz : xietong_clazzList) {
            xietong_clazz.put(find(xietong_clazz.getClazz_id()));
        }
        return xietong_clazzList;
    }

    public List<XietongClazz> organizationList(String organization_id) {
        Cnd cnd = new Cnd();
        cnd.where(XietongClazz.SYSTEM_STATUS, true);
        cnd.and(XietongClazz.ORGANIZATION_ID, organization_id);
        cnd.asc(XietongClazz.CLAZZ_SORT);
        cnd.desc(XietongClazz.SYSTEM_CREATE_TIME);

        List<XietongClazz> xietong_clazzList = xietongClazzDao.primaryKeyList(cnd);
        for (XietongClazz xietong_clazz : xietong_clazzList) {
            xietong_clazz.put(find(xietong_clazz.getClazz_id()));
        }
        return xietong_clazzList;
    }

    public XietongClazz find(String clazz_id) {
        XietongClazz xietong_clazz = CacheUtil.get(XIETONG_CLAZZ_ITEM_CACHE, clazz_id);

        if (xietong_clazz == null) {
            xietong_clazz = xietongClazzDao.find(clazz_id);

            CacheUtil.put(XIETONG_CLAZZ_ITEM_CACHE, clazz_id, xietong_clazz);
        }

        return xietong_clazz;
    }

    public Boolean save(XietongClazz xietong_clazz, String system_create_user_id) {
        Boolean success = xietongClazzDao.save(xietong_clazz, system_create_user_id);
        return success;
    }

    public Boolean update(XietongClazz xietong_clazz, String clazz_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(XietongClazz.SYSTEM_STATUS, true);
        cnd.and(XietongClazz.CLAZZ_ID, clazz_id);
        cnd.and(XietongClazz.SYSTEM_VERSION, system_version);

        Boolean success = xietongClazzDao.update(xietong_clazz, system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(XIETONG_CLAZZ_ITEM_CACHE, clazz_id);
        }

        return success;
    }

    public Boolean delete(String clazz_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(XietongClazz.SYSTEM_STATUS, true);
        cnd.and(XietongClazz.CLAZZ_ID, clazz_id);
        cnd.and(XietongClazz.SYSTEM_VERSION, system_version);

        Boolean success = xietongClazzDao.delete(system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(XIETONG_CLAZZ_ITEM_CACHE, clazz_id);
        }

        return success;
    }

}