package com.nowui.chuangshi.api.minhang.service;

import com.nowui.chuangshi.api.minhang.dao.MinhangCompanyDao;
import com.nowui.chuangshi.api.minhang.model.MinhangCompany;
import com.nowui.chuangshi.common.service.Service;
import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.util.CacheUtil;

import java.util.List;

public class MinhangCompanyService extends Service {

    public static final MinhangCompanyService instance = new MinhangCompanyService();
    private final String MINHANG_COMPANY_ITEM_CACHE = "minhang_company_item_cache";
    private final MinhangCompanyDao minhangCompanyDao = new MinhangCompanyDao();

    public Integer adminCount(String app_id, String company_name) {
        Cnd cnd = new Cnd();
        cnd.where(MinhangCompany.SYSTEM_STATUS, true);
        cnd.and(MinhangCompany.APP_ID, app_id);
        cnd.andAllowEmpty(MinhangCompany.COMPANY_NAME, company_name);

        Integer count = minhangCompanyDao.count(cnd);
        return count;
    }

    public List<MinhangCompany> adminList(String app_id, String company_name, Integer m, Integer n) {
        Cnd cnd = new Cnd();
        cnd.where(MinhangCompany.SYSTEM_STATUS, true);
        cnd.and(MinhangCompany.APP_ID, app_id);
        cnd.andLikeAllowEmpty(MinhangCompany.COMPANY_NAME, company_name);
        cnd.asc(MinhangCompany.COMPNAY_SORT);
        cnd.paginate(m, n);

        List<MinhangCompany> minhang_companyList = minhangCompanyDao.primaryKeyList(cnd);
        for (MinhangCompany minhang_company : minhang_companyList) {
            minhang_company.put(find(minhang_company.getCompany_id()));
        }
        return minhang_companyList;
    }
    
    public List<MinhangCompany> mobileList(String app_id) {
        Cnd cnd = new Cnd();
        cnd.where(MinhangCompany.SYSTEM_STATUS, true);
        cnd.and(MinhangCompany.APP_ID, app_id);
        cnd.asc(MinhangCompany.COMPNAY_SORT);

        List<MinhangCompany> minhang_companyList = minhangCompanyDao.primaryKeyList(cnd);
        for (MinhangCompany minhang_company : minhang_companyList) {
            minhang_company.put(find(minhang_company.getCompany_id()));
        }
        return minhang_companyList;
    }

    public MinhangCompany find(String company_id) {
        MinhangCompany minhang_company = CacheUtil.get(MINHANG_COMPANY_ITEM_CACHE, company_id);

        if (minhang_company == null) {
            minhang_company = minhangCompanyDao.find(company_id);

            CacheUtil.put(MINHANG_COMPANY_ITEM_CACHE, company_id, minhang_company);
        }

        return minhang_company;
    }

    public Boolean save(MinhangCompany minhang_company, String system_create_user_id) {
        Boolean success = minhangCompanyDao.save(minhang_company, system_create_user_id);
        return success;
    }

    public Boolean update(MinhangCompany minhang_company, String company_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(MinhangCompany.SYSTEM_STATUS, true);
        cnd.and(MinhangCompany.COMPANY_ID, company_id);
        cnd.and(MinhangCompany.SYSTEM_VERSION, system_version);

        Boolean success = minhangCompanyDao.update(minhang_company, system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(MINHANG_COMPANY_ITEM_CACHE, company_id);
        }

        return success;
    }

    public Boolean delete(String company_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(MinhangCompany.SYSTEM_STATUS, true);
        cnd.and(MinhangCompany.COMPANY_ID, company_id);
        cnd.and(MinhangCompany.SYSTEM_VERSION, system_version);

        Boolean success = minhangCompanyDao.delete(system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(MINHANG_COMPANY_ITEM_CACHE, company_id);
        }

        return success;
    }

}