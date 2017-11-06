package com.nowui.chuangshi.api.xietong.service;

import com.nowui.chuangshi.api.xietong.dao.XietongSignupPupilDao;
import com.nowui.chuangshi.api.xietong.model.XietongSignupPupil;
import com.nowui.chuangshi.common.service.Service;
import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.util.CacheUtil;

import java.util.List;

public class XietongSignupPupilService extends Service {

    public static final XietongSignupPupilService instance = new XietongSignupPupilService();
    private final String XIETONG_SIGNUP_PUPIL_ITEM_CACHE = "xietong_signup_pupil_item_cache";
    private final XietongSignupPupilDao xietongSignupPupilDao = new XietongSignupPupilDao();

    public Integer adminCount(String app_id, String student_name, String id_no) {
        Cnd cnd = new Cnd();
        cnd.where(XietongSignupPupil.SYSTEM_STATUS, true);
        cnd.and(XietongSignupPupil.APP_ID, app_id);
        cnd.andAllowEmpty(XietongSignupPupil.STUDENT_NAME, student_name);
        cnd.andAllowEmpty(XietongSignupPupil.ID_NO, id_no);

        Integer count = xietongSignupPupilDao.count(cnd);
        return count;
    }

    public List<XietongSignupPupil> adminList(String app_id, String student_name, String id_no, Integer m, Integer n) {
        Cnd cnd = new Cnd();
        cnd.where(XietongSignupPupil.SYSTEM_STATUS, true);
        cnd.and(XietongSignupPupil.APP_ID, app_id);
        cnd.andAllowEmpty(XietongSignupPupil.STUDENT_NAME, student_name);
        cnd.andAllowEmpty(XietongSignupPupil.ID_NO, id_no);
        cnd.paginate(m, n);

        List<XietongSignupPupil> xietong_signup_pupilList = xietongSignupPupilDao.primaryKeyList(cnd);
        for (XietongSignupPupil xietong_signup_pupil : xietong_signup_pupilList) {
            xietong_signup_pupil.put(find(xietong_signup_pupil.getSignup_id()));
        }
        return xietong_signup_pupilList;
    }

    public XietongSignupPupil find(String signup_id) {
        XietongSignupPupil xietong_signup_pupil = CacheUtil.get(XIETONG_SIGNUP_PUPIL_ITEM_CACHE, signup_id);

        if (xietong_signup_pupil == null) {
            xietong_signup_pupil = xietongSignupPupilDao.find(signup_id);

            CacheUtil.put(XIETONG_SIGNUP_PUPIL_ITEM_CACHE, signup_id, xietong_signup_pupil);
        }

        return xietong_signup_pupil;
    }

    public Boolean save(XietongSignupPupil xietong_signup_pupil, String system_create_user_id) {
        Boolean success = xietongSignupPupilDao.save(xietong_signup_pupil, system_create_user_id);
        return success;
    }

    public Boolean update(XietongSignupPupil xietong_signup_pupil, String signup_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(XietongSignupPupil.SYSTEM_STATUS, true);
        cnd.and(XietongSignupPupil.SIGNUP_ID, signup_id);
        cnd.and(XietongSignupPupil.SYSTEM_VERSION, system_version);

        Boolean success = xietongSignupPupilDao.update(xietong_signup_pupil, system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(XIETONG_SIGNUP_PUPIL_ITEM_CACHE, signup_id);
        }

        return success;
    }

    public Boolean delete(String signup_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(XietongSignupPupil.SYSTEM_STATUS, true);
        cnd.and(XietongSignupPupil.SIGNUP_ID, signup_id);
        cnd.and(XietongSignupPupil.SYSTEM_VERSION, system_version);

        Boolean success = xietongSignupPupilDao.delete(system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(XIETONG_SIGNUP_PUPIL_ITEM_CACHE, signup_id);
        }

        return success;
    }

}