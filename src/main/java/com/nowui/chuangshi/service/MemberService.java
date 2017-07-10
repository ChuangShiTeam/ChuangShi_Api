package com.nowui.chuangshi.service;

import com.alibaba.fastjson.JSONObject;
import com.nowui.chuangshi.cache.MemberCache;
import com.nowui.chuangshi.cache.UserCache;
import com.nowui.chuangshi.constant.Config;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.model.Member;
import com.nowui.chuangshi.model.User;
import com.nowui.chuangshi.type.UserType;
import com.nowui.chuangshi.util.AesUtil;
import com.nowui.chuangshi.util.Util;
import com.nowui.chuangshi.util.ValidateUtil;

import java.util.*;

public class MemberService extends Service {

    private MemberCache memberCache = new MemberCache();
    private UserCache userCache = new UserCache();

    public Integer countByApp_idOrLikeUser_name(String app_id, String user_name) {
        return memberCache.countByApp_idOrLikeUser_name(app_id, user_name);
    }

    public Integer countByOrApp_idOrLikeUser_name(String app_id, String user_name) {
        return memberCache.countByOrApp_idOrLikeUser_name(app_id, user_name);
    }

    public List<Member> listByMember_parent_pathLikeMember_parent_id(String member_parent_id) {
        return memberCache.listByMember_parent_pathLikeMember_parent_id(member_parent_id);
    }

    public List<Member> listByApp_idAndSystem_create_timeAndLimit(String app_id, Date system_create_time, int m, int n) {
        return memberCache.listByApp_idAndSystem_create_timeAndLimit(app_id, system_create_time, m, n);
    }

    public List<Member> listByApp_idOrLikeUser_nameAndLimit(String app_id, String user_name, int m, int n) {
        return memberCache.listByApp_idOrLikeUser_nameAndLimit(app_id, user_name, m, n);
    }
    
    public List<Member> listByApp_id(String app_id) {
    	return memberCache.listByApp_id(app_id);
    }
    
    public List<Member> listByOrApp_id(String app_id) {
    	return memberCache.listByOrApp_id(app_id);
    }

    public List<Member> listByOrApp_idOrLikeUser_nameAndLimit(String app_id, String user_name, int m, int n) {
        return memberCache.listByOrApp_idOrLikeUser_nameAndLimit(app_id, user_name, m, n);
    }

    public Member findByMember_id(String member_id) {
        return memberCache.findByMember_id(member_id);
    }

    public String login(String app_id, String wechat_open_id, String wechat_union_id, String user_name, String user_avatar, String system_create_user_id) {
        if (ValidateUtil.isNullOrEmpty(wechat_open_id)) {
            throw new RuntimeException("wechat_open_id is null");
        }

        User user = userCache.findByApp_idAndUser_typeAndWechat_open_idAndWechat_union_id(app_id, UserType.MEMBER.getKey(), wechat_open_id, wechat_union_id);

        if (user == null) {
            String member_id = Util.getRandomUUID();
            String user_id = Util.getRandomUUID();
            String member_parent_id = "";
            String from_qrcode_id = "";
            String qrcode_id = "";
            String member_level_id = "";
            String member_parent_path = "";
            Boolean member_status = false;

            Boolean result = memberCache.save(member_id, app_id, user_id, member_parent_id, from_qrcode_id, qrcode_id, member_level_id, member_parent_path, member_status, system_create_user_id);

            if (result) {
                throw new RuntimeException("登录不成功");
            }

            String user_account = "";
            String user_mobile = "";
            String user_email = "";
            String user_password = "";

            result = userCache.save(user_id, app_id, member_id, UserType.MEMBER.getKey(), user_name, user_avatar, user_account, user_mobile, user_email, user_password, wechat_open_id, wechat_union_id, system_create_user_id);

            if (result) {
                throw new RuntimeException("登录不成功");
            }
        } else {

        }

        try {
            Date date = new Date();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.YEAR, 1);

            JSONObject jsonObject = new JSONObject();
            jsonObject.put(User.USER_ID, user.getUser_id());
            jsonObject.put(Constant.EXPIRE_TIME, calendar.getTime());

            return AesUtil.aesEncrypt(jsonObject.toJSONString(), Config.private_key);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("登录不成功");
        }
    }

    public Boolean updateValidateSystem_version(String member_id, String user_id, String member_parent_id, String from_qrcode_id, String qrcode_id, String member_level_id, String member_parent_path, Boolean member_status, String system_update_user_id, Integer system_version) {
        return memberCache.updateValidateSystem_version(member_id, user_id, member_parent_id, from_qrcode_id, qrcode_id, member_level_id, member_parent_path, member_status, system_update_user_id, system_version);
    }

    public Boolean deleteByMember_idAndSystem_update_user_idValidateSystem_version(String member_id, String system_update_user_id, Integer system_version) {
        return memberCache.deleteByMember_idAndSystem_update_user_idValidateSystem_version(member_id, system_update_user_id, system_version);
    }

}