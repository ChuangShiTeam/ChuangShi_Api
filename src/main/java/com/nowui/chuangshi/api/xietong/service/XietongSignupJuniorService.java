package com.nowui.chuangshi.api.xietong.service;

import com.nowui.chuangshi.api.xietong.dao.XietongSignupJuniorDao;
import com.nowui.chuangshi.api.xietong.model.XietongSignupJunior;
import com.nowui.chuangshi.common.service.Service;
import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.util.CacheUtil;

import java.util.List;

public class XietongSignupJuniorService extends Service {

    public static final XietongSignupJuniorService instance = new XietongSignupJuniorService();
    private final String XIETONG_SIGNUP_JUNIOR_ITEM_CACHE = "xietong_signup_junior_item_cache";
    private final XietongSignupJuniorDao xietongSignupJuniorDao = new XietongSignupJuniorDao();

    public Integer adminCount(String app_id, String student_name, String id_no) {
        Cnd cnd = new Cnd();
        cnd.where(XietongSignupJunior.SYSTEM_STATUS, true);
        cnd.and(XietongSignupJunior.APP_ID, app_id);
        cnd.andAllowEmpty(XietongSignupJunior.STUDENT_NAME, student_name);
        cnd.andAllowEmpty(XietongSignupJunior.ID_NO, id_no);

        Integer count = xietongSignupJuniorDao.count(cnd);
        return count;
    }

    public List<XietongSignupJunior> adminList(String app_id, String student_name, String id_no, Integer m, Integer n) {
        Cnd cnd = new Cnd();
        cnd.where(XietongSignupJunior.SYSTEM_STATUS, true);
        cnd.and(XietongSignupJunior.APP_ID, app_id);
        cnd.andAllowEmpty(XietongSignupJunior.STUDENT_NAME, student_name);
        cnd.andAllowEmpty(XietongSignupJunior.ID_NO, id_no);
        cnd.paginate(m, n);

        List<XietongSignupJunior> xietong_signup_juniorList = xietongSignupJuniorDao.primaryKeyList(cnd);
        for (XietongSignupJunior xietong_signup_junior : xietong_signup_juniorList) {
            xietong_signup_junior.put(find(xietong_signup_junior.getSignup_id()));
        }
        return xietong_signup_juniorList;
    }

    public XietongSignupJunior find(String signup_id) {
        XietongSignupJunior xietong_signup_junior = CacheUtil.get(XIETONG_SIGNUP_JUNIOR_ITEM_CACHE, signup_id);

        if (xietong_signup_junior == null) {
            xietong_signup_junior = xietongSignupJuniorDao.find(signup_id);

            CacheUtil.put(XIETONG_SIGNUP_JUNIOR_ITEM_CACHE, signup_id, xietong_signup_junior);
        }

        return xietong_signup_junior;
    }

    public Boolean save(XietongSignupJunior xietong_signup_junior, String system_create_user_id) {
        Boolean success = xietongSignupJuniorDao.save(xietong_signup_junior, system_create_user_id);
        return success;
    }

    public Boolean update(XietongSignupJunior xietong_signup_junior, String signup_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(XietongSignupJunior.SYSTEM_STATUS, true);
        cnd.and(XietongSignupJunior.SIGNUP_ID, signup_id);
        cnd.and(XietongSignupJunior.SYSTEM_VERSION, system_version);

        Boolean success = xietongSignupJuniorDao.update(xietong_signup_junior, system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(XIETONG_SIGNUP_JUNIOR_ITEM_CACHE, signup_id);
        }

        return success;
    }

    public Boolean delete(String signup_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(XietongSignupJunior.SYSTEM_STATUS, true);
        cnd.and(XietongSignupJunior.SIGNUP_ID, signup_id);
        cnd.and(XietongSignupJunior.SYSTEM_VERSION, system_version);

        Boolean success = xietongSignupJuniorDao.delete(system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(XIETONG_SIGNUP_JUNIOR_ITEM_CACHE, signup_id);
        }

        return success;
    }

}