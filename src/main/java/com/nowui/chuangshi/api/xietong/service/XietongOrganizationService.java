package com.nowui.chuangshi.api.xietong.service;

import com.nowui.chuangshi.api.xietong.dao.XietongOrganizationDao;
import com.nowui.chuangshi.api.xietong.model.XietongOrganization;
import com.nowui.chuangshi.common.service.Service;
import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.util.CacheUtil;

import java.util.List;

public class XietongOrganizationService extends Service {

    public static final XietongOrganizationService instance = new XietongOrganizationService();
    private final String XIETONG_ORGANIZATION_ITEM_CACHE = "xietong_organization_item_cache";
    private final XietongOrganizationDao xietongOrganizationDao = new XietongOrganizationDao();

    public Integer adminCount(String app_id, String organization_name, String organization_code) {
        Cnd cnd = new Cnd();
        cnd.where(XietongOrganization.SYSTEM_STATUS, true);
        cnd.and(XietongOrganization.APP_ID, app_id);
        cnd.andLikeAllowEmpty(XietongOrganization.ORGANIZATION_NAME, organization_name);
        cnd.andLikeAllowEmpty(XietongOrganization.ORGANIZATION_CODE, organization_code);

        Integer count = xietongOrganizationDao.count(cnd);
        return count;
    }

    public List<XietongOrganization> adminList(String app_id, String organization_name, String organization_code, Integer m, Integer n) {
        Cnd cnd = new Cnd();
        cnd.where(XietongOrganization.SYSTEM_STATUS, true);
        cnd.and(XietongOrganization.APP_ID, app_id);
        cnd.andLikeAllowEmpty(XietongOrganization.ORGANIZATION_NAME, organization_name);
        cnd.andLikeAllowEmpty(XietongOrganization.ORGANIZATION_CODE, organization_code);
        cnd.asc(XietongOrganization.ORGANIZAITON_SORT);
        cnd.paginate(m, n);

        List<XietongOrganization> xietong_organizationList = xietongOrganizationDao.primaryKeyList(cnd);
        for (XietongOrganization xietong_organization : xietong_organizationList) {
            xietong_organization.put(find(xietong_organization.getOrganization_id()));
        }
        return xietong_organizationList;
    }
    
    public List<XietongOrganization> allList(String app_id) {
        Cnd cnd = new Cnd();
        cnd.where(XietongOrganization.SYSTEM_STATUS, true);
        cnd.and(XietongOrganization.APP_ID, app_id);
        cnd.asc(XietongOrganization.ORGANIZAITON_SORT);
        
        List<XietongOrganization> xietong_organizationList = xietongOrganizationDao.primaryKeyList(cnd);
        for (XietongOrganization xietong_organization : xietong_organizationList) {
            xietong_organization.put(find(xietong_organization.getOrganization_id()));
        }
        return xietong_organizationList;
    }

    public XietongOrganization find(String organization_id) {
        XietongOrganization xietong_organization = CacheUtil.get(XIETONG_ORGANIZATION_ITEM_CACHE, organization_id);

        if (xietong_organization == null) {
            xietong_organization = xietongOrganizationDao.find(organization_id);

            CacheUtil.put(XIETONG_ORGANIZATION_ITEM_CACHE, organization_id, xietong_organization);
        }

        return xietong_organization;
    }

    public Boolean save(XietongOrganization xietong_organization, String system_create_user_id) {
        Boolean success = xietongOrganizationDao.save(xietong_organization, system_create_user_id);
        return success;
    }

    public Boolean update(XietongOrganization xietong_organization, String organization_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(XietongOrganization.SYSTEM_STATUS, true);
        cnd.and(XietongOrganization.ORGANIZATION_ID, organization_id);
        cnd.and(XietongOrganization.SYSTEM_VERSION, system_version);

        Boolean success = xietongOrganizationDao.update(xietong_organization, system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(XIETONG_ORGANIZATION_ITEM_CACHE, organization_id);
        }

        return success;
    }

    public Boolean delete(String organization_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(XietongOrganization.SYSTEM_STATUS, true);
        cnd.and(XietongOrganization.ORGANIZATION_ID, organization_id);
        cnd.and(XietongOrganization.SYSTEM_VERSION, system_version);

        Boolean success = xietongOrganizationDao.delete(system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(XIETONG_ORGANIZATION_ITEM_CACHE, organization_id);
        }

        return success;
    }

}