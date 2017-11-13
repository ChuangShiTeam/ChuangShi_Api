package com.nowui.chuangshi.api.xietong.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.nowui.chuangshi.api.user.model.User;
import com.nowui.chuangshi.api.user.service.UserService;
import com.nowui.chuangshi.api.xietong.dao.XietongSignupPupilDao;
import com.nowui.chuangshi.api.xietong.model.XietongSignupPupil;
import com.nowui.chuangshi.common.service.Service;
import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.constant.Config;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.type.UserType;
import com.nowui.chuangshi.util.AesUtil;
import com.nowui.chuangshi.util.CacheUtil;
import com.nowui.chuangshi.util.Util;

public class XietongSignupPupilService extends Service {

    public static final XietongSignupPupilService instance = new XietongSignupPupilService();
    private final String XIETONG_SIGNUP_PUPIL_ITEM_CACHE = "xietong_signup_pupil_item_cache";
    private final String MOBILE_XIETONG_SIGNUP_PUPIL_ITEM_CACHE = "mobile_xietong_signup_pupil_item_cache";
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
    
    /**
     * 根据用户编号查询
     * @param user_id
     * @return
     */
    public XietongSignupPupil userFind(String user_id) {
    	 Cnd cnd = new Cnd();
         cnd.where(XietongSignupPupil.SYSTEM_STATUS, true);
         cnd.and(XietongSignupPupil.USER_ID, user_id);
         
         return xietongSignupPupilDao.find(cnd);
    }

    //根据证件号码查询
    public XietongSignupPupil idNoFind(String id_no) {
        XietongSignupPupil xietong_signup_pupil = CacheUtil.get(MOBILE_XIETONG_SIGNUP_PUPIL_ITEM_CACHE, id_no);
        Cnd cnd = new Cnd();
        cnd.where(XietongSignupPupil.SYSTEM_STATUS, true);
        cnd.and(XietongSignupPupil.ID_NO, id_no);

        if (xietong_signup_pupil == null) {
            xietong_signup_pupil = xietongSignupPupilDao.find(cnd);

            CacheUtil.put(MOBILE_XIETONG_SIGNUP_PUPIL_ITEM_CACHE, id_no, xietong_signup_pupil);
        }

        return xietong_signup_pupil;
    }

    public Boolean save(XietongSignupPupil xietong_signup_pupil, String system_create_user_id) {
        Boolean success = xietongSignupPupilDao.save(xietong_signup_pupil, system_create_user_id);
        return success;
    }
    
    public String save(XietongSignupPupil xietong_signup_pupil, User user, String system_create_user_id) {
    	String user_id = Util.getRandomUUID();
    	xietong_signup_pupil.setUser_id(user_id);
    	xietong_signup_pupil.setSignup_id(Util.getRandomUUID());
        Boolean result = save(xietong_signup_pupil, system_create_user_id);
        
        String token = null;

        if (result) {
        	user.setUser_password("123456");	//初始密码为123456 
            UserService.instance.userAccountSave(user_id, xietong_signup_pupil.getApp_id(), xietong_signup_pupil.getSignup_id(), UserType.PUPIL_ADMISSIONS.getKey(), xietong_signup_pupil.getStudent_name(), xietong_signup_pupil.getId_no(), user.getUser_password(), system_create_user_id);
            
            Date date = new Date();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.YEAR, 1);

            JSONObject jsonObject = new JSONObject();
            jsonObject.put(User.USER_ID, user_id);
            jsonObject.put(Constant.EXPIRE_TIME, calendar.getTime());
            
            try {
				token = AesUtil.aesEncrypt(jsonObject.toJSONString(), Config.private_key);
			} catch (Exception e) {
				e.printStackTrace();
			}
        }

        return token;
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
    
    public Boolean update(XietongSignupPupil xietong_signup_pupil, User user, String system_update_user_id) {
        Boolean result = update(xietong_signup_pupil, xietong_signup_pupil.getSignup_id(), system_update_user_id, xietong_signup_pupil.getSystem_version());
        
        if (result) {
            UserService.instance.userAccountAndNameUpdate(user.getUser_id(), xietong_signup_pupil.getId_no(), xietong_signup_pupil.getStudent_name(), system_update_user_id);
        }
        
        return result;
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