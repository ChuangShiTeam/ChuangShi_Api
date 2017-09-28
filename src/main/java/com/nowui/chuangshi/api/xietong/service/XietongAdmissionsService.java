package com.nowui.chuangshi.api.xietong.service;

import java.util.List;

import com.nowui.chuangshi.api.user.service.UserService;
import com.nowui.chuangshi.api.xietong.dao.XietongAdmissionsDao;
import com.nowui.chuangshi.api.xietong.model.XietongAdmissions;
import com.nowui.chuangshi.common.service.Service;
import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.type.UserType;
import com.nowui.chuangshi.util.CacheUtil;
import com.nowui.chuangshi.util.Util;

public class XietongAdmissionsService extends Service {

    public static final XietongAdmissionsService instance = new XietongAdmissionsService();
    private final String XIETONG_ADMISSIONS_ITEM_CACHE = "xietong_admissions_item_cache";
    private final XietongAdmissionsDao xietongAdmissionsDao = new XietongAdmissionsDao();

    public Integer adminCount(String app_id, String admissions_no, String admissions_name) {
        Cnd cnd = new Cnd();
        cnd.where(XietongAdmissions.SYSTEM_STATUS, true);
        cnd.and(XietongAdmissions.APP_ID, app_id);
        cnd.andAllowEmpty(XietongAdmissions.ADMISSIONS_NO, admissions_no);
        cnd.andAllowEmpty(XietongAdmissions.ADMISSIONS_NAME, admissions_name);

        Integer count = xietongAdmissionsDao.count(cnd);
        return count;
    }

    public List<XietongAdmissions> adminList(String app_id, String admissions_no, String admissions_name, Integer m, Integer n) {
        Cnd cnd = new Cnd();
        cnd.where(XietongAdmissions.SYSTEM_STATUS, true);
        cnd.and(XietongAdmissions.APP_ID, app_id);
        cnd.andAllowEmpty(XietongAdmissions.ADMISSIONS_NO, admissions_no);
        cnd.andAllowEmpty(XietongAdmissions.ADMISSIONS_NAME, admissions_name);
        cnd.paginate(m, n);

        List<XietongAdmissions> xietong_admissionsList = xietongAdmissionsDao.primaryKeyList(cnd);
        for (XietongAdmissions xietong_admissions : xietong_admissionsList) {
            xietong_admissions.put(find(xietong_admissions.getAdmissions_id()));
        }
        return xietong_admissionsList;
    }

    public XietongAdmissions find(String admissions_id) {
        XietongAdmissions xietong_admissions = CacheUtil.get(XIETONG_ADMISSIONS_ITEM_CACHE, admissions_id);

        if (xietong_admissions == null) {
            xietong_admissions = xietongAdmissionsDao.find(admissions_id);

            CacheUtil.put(XIETONG_ADMISSIONS_ITEM_CACHE, admissions_id, xietong_admissions);
        }

        return xietong_admissions;
    }

    public Boolean save(XietongAdmissions xietong_admissions, String system_create_user_id) {
        Boolean success = xietongAdmissionsDao.save(xietong_admissions, system_create_user_id);
        return success;
    }
    
    public Integer allCount(String app_id) {
    	Cnd cnd = new Cnd();
        cnd.where(XietongAdmissions.SYSTEM_STATUS, true);
        cnd.and(XietongAdmissions.APP_ID, app_id);

        Integer count = xietongAdmissionsDao.count(cnd);
        return count;
    }
    
    public Boolean save(XietongAdmissions xietong_admissions) {
        String user_id = Util.getRandomUUID();
        xietong_admissions.setAdmissions_id(Util.getRandomUUID());
        xietong_admissions.setAdmissions_no(allCount(xietong_admissions.getApp_id()) + 1 + "");
        xietong_admissions.setUser_id(user_id);
        Boolean success = this.save(xietong_admissions, user_id);
        if (success) {
        	String user_account = xietong_admissions.getAdmissions_certificate_number();
        	String user_password = "123456";  //默认密码123456
        	if (user_account.length() > 6) {  //长度大于6时用证件号码后6位作为密码
        		user_password = user_account.substring(user_account.length() - 6, user_account.length());
        	}
            UserService.instance.userAccountSave(user_id, xietong_admissions.getApp_id(), xietong_admissions.getAdmissions_id(), UserType.ADMISSIONS.getKey(), xietong_admissions.getAdmissions_name(), user_account, user_password, user_id);
        }
        return success;
    }

    public Boolean update(XietongAdmissions xietong_admissions, String admissions_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(XietongAdmissions.SYSTEM_STATUS, true);
        cnd.and(XietongAdmissions.ADMISSIONS_ID, admissions_id);
        cnd.and(XietongAdmissions.SYSTEM_VERSION, system_version);

        Boolean success = xietongAdmissionsDao.update(xietong_admissions, system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(XIETONG_ADMISSIONS_ITEM_CACHE, admissions_id);
        }

        return success;
    }

    public Boolean delete(String admissions_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(XietongAdmissions.SYSTEM_STATUS, true);
        cnd.and(XietongAdmissions.ADMISSIONS_ID, admissions_id);
        cnd.and(XietongAdmissions.SYSTEM_VERSION, system_version);

        Boolean success = xietongAdmissionsDao.delete(system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(XIETONG_ADMISSIONS_ITEM_CACHE, admissions_id);
        }

        return success;
    }

}