package com.nowui.chuangshi.api.member.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.nowui.chuangshi.api.file.model.File;
import com.nowui.chuangshi.api.file.service.FileService;
import com.nowui.chuangshi.api.member.dao.MemberDao;
import com.nowui.chuangshi.api.member.model.Member;
import com.nowui.chuangshi.api.user.model.User;
import com.nowui.chuangshi.api.user.service.UserService;
import com.nowui.chuangshi.common.service.Service;
import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.constant.Config;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.type.FileType;
import com.nowui.chuangshi.type.UserType;
import com.nowui.chuangshi.util.AesUtil;
import com.nowui.chuangshi.util.CacheUtil;
import com.nowui.chuangshi.util.Util;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MemberService extends Service {

    public static final MemberService instance = new MemberService();
    private final String MEMBER_ITEM_CACHE = "member_item_cache";
    private final String MEMBER_PARENT_LIST_CACHE = "member_parent_list_cache";
    private final MemberDao memberDao = new MemberDao();

    public Integer adminCount(String app_id) {
        Cnd cnd = Cnd.where(Member.SYSTEM_STATUS, true);
        cnd.and(Member.APP_ID, app_id);

        Integer count = memberDao.count(cnd);
        return count;
    }

    public List<Member> adminList(String app_id, Integer m, Integer n) {
        Cnd cnd = Cnd.where(Member.SYSTEM_STATUS, true);
        cnd.and(Member.APP_ID, app_id);
        cnd.paginate(m, n);

        List<Member> memberList = memberDao.list(cnd);
        return memberList;
    }

    public Member find(String member_id) {
        Member member = CacheUtil.get(MEMBER_ITEM_CACHE, member_id);

        if (member == null) {
            member = memberDao.find(member_id);

            CacheUtil.put(MEMBER_ITEM_CACHE, member_id, member);
        }

        return member;
    }

    public Boolean save(String member_id, String app_id, String user_id, String member_parent_id, String from_qrcode_id, String qrcode_id, String member_level_id, JSONArray member_parent_path, Boolean member_status, String system_create_user_id) {
        Member member = new Member();
        member.setMember_id(member_id);
        member.setApp_id(app_id);
        member.setUser_id(user_id);
        member.setMember_parent_id(member_parent_id);
        member.setFrom_qrcode_id(from_qrcode_id);
        member.setQrcode_id(qrcode_id);
        member.setMember_level_id(member_level_id);
        member.setMember_parent_path(member_parent_path.toJSONString());
        member.setMember_status(member_status);

        Boolean success = memberDao.save(member, system_create_user_id);

        if (success) {
            cacheDelete(member_id);
        }

        return success;
    }

    public Boolean update(Member member, String member_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = Cnd.where(Member.SYSTEM_STATUS, true);
        cnd.and(Member.MEMBER_ID, member_id);
        cnd.and(Member.SYSTEM_UPDATE_USER_ID, system_update_user_id);
        cnd.and(Member.SYSTEM_VERSION, system_version);

        Boolean success = memberDao.update(member, system_update_user_id, system_version, cnd);

        if (success) {
            cacheDelete(member_id);
        }

        return success;
    }

    public Member wechatSaveOrUpdate(String app_id, String wechat_open_id, String wechat_union_id, String member_parent_id, String from_qrcode_id, String member_level_id, JSONArray member_parent_path, String user_name, String user_avatar, Boolean member_status, String system_create_user_id) {
        String member_id = "";

        User user = UserService.instance.wechatFind(app_id, UserType.MEMBER.getKey(), wechat_open_id, wechat_union_id);
        if (user == null) {
            member_id = Util.getRandomUUID();
            String user_id = Util.getRandomUUID();
            String qrcode_id = "";

            Boolean success = save(member_id, app_id, user_id, member_parent_id, from_qrcode_id, qrcode_id, member_level_id, member_parent_path, member_status, system_create_user_id);

            if (!success) {
                throw new RuntimeException("保存不成功");
            }

            String file_id = Util.getRandomUUID();
            String file_type = FileType.IMAGE.getKey();
            String file_name = "";
            String file_suffix = "jpeg";
            Integer file_size = 0;
            String file_path = user_avatar;
            String file_thumbnail_path = user_avatar;
            String file_original_path = user_avatar;
            String file_image = "";
            Boolean file_is_external = true;
            FileService.instance.save(file_id, app_id, file_type, file_name, file_suffix, file_size, file_path, file_thumbnail_path, file_original_path, file_image, file_is_external, system_create_user_id);

            success = UserService.instance.wechatSave(user_id, app_id, member_id, UserType.MEMBER.getKey(), user_name, file_id, wechat_open_id, wechat_union_id, system_create_user_id);

            if (!success) {
                throw new RuntimeException("保存不成功");
            }
        } else {
            if (!user.getUser_name().equals(user_name)) {
                UserService.instance.userNameUpdate(user.getUser_id(), user_name, system_create_user_id);

                cacheDelete(user.getObject_id());
            }

            File file = FileService.instance.find(user.getUser_avatar());

            if (!user_avatar.equals(file.getFile_path())) {
                FileService.instance.filePathUpdate(user.getUser_avatar(), user_avatar, system_create_user_id);

                cacheDelete(user.getObject_id());
            }

            member_id = user.getObject_id();
        }

        return find(member_id);
    }

    public Boolean wechatQrCodeUpdate(String member_id, String member_parent_id, JSONArray member_parent_path, String member_level_id, Boolean member_status, String system_update_user_id) {
        Member member = new Member();
        member.setMember_id(member_id);
        member.setMember_parent_id(member_parent_id);
        member.setMember_parent_path(member_parent_path.toJSONString());
        member.setMember_level_id(member_level_id);
        member.setMember_status(member_status);

        Cnd cnd = Cnd.where(Member.SYSTEM_STATUS, true);
        cnd.and(Member.MEMBER_ID, member_id);
        cnd.and(Member.SYSTEM_UPDATE_USER_ID, system_update_user_id);

        Boolean success = memberDao.update(member, system_update_user_id, cnd);

        if (success) {
            cacheDelete(member_id);
        }

        return success;
    }

    public Boolean delete(String member_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = Cnd.where(Member.SYSTEM_STATUS, true);
        cnd.and(Member.MEMBER_ID, member_id);
        cnd.and(Member.SYSTEM_VERSION, system_version);

        Boolean success = memberDao.delete(system_update_user_id, system_version, cnd);

        if (success) {
            cacheDelete(member_id);
        }

        return success;
    }

    public void cacheDelete(String member_id) {
        Member member = find(member_id);

        CacheUtil.remove(MEMBER_ITEM_CACHE, member_id);

        JSONArray jsonArray = JSONArray.parseArray(member.getMember_parent_path());
        for (int i = 0; i < jsonArray.size(); i++) {
            String member_parent_id = jsonArray.getString(i);
            if (!member_parent_id.equals(Constant.PARENT_ID)) {
                CacheUtil.remove(MEMBER_PARENT_LIST_CACHE, member_parent_id);
            }
        }
    }


    public String wechatLogin(String app_id, String wechat_open_id, String wechat_union_id, String member_parent_id, String from_qrcode_id, String member_level_id, JSONArray member_parent_path, String user_name, String user_avatar, Boolean member_status, String system_create_user_id) {
        Member member = wechatSaveOrUpdate(app_id, wechat_open_id, wechat_union_id, member_parent_id, from_qrcode_id, member_level_id, member_parent_path, user_name, user_avatar, member_status, system_create_user_id);

        try {
            Date date = new Date();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.YEAR, 1);

            JSONObject jsonObject = new JSONObject();
            jsonObject.put(com.nowui.chuangshi.model.User.USER_ID, member.getUser_id());
            jsonObject.put(Constant.EXPIRE_TIME, calendar.getTime());

            return AesUtil.aesEncrypt(jsonObject.toJSONString(), Config.private_key);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("登录不成功");
        }
    }

}