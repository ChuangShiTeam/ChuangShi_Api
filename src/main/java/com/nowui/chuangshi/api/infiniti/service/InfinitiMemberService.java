package com.nowui.chuangshi.api.infiniti.service;

import com.nowui.chuangshi.api.infiniti.dao.InfinitiMemberDao;
import com.nowui.chuangshi.api.infiniti.model.InfinitiMember;
import com.nowui.chuangshi.common.service.Service;
import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.util.CacheUtil;

import java.util.List;

public class InfinitiMemberService extends Service {

    public static final InfinitiMemberService instance = new InfinitiMemberService();
    private final String INFINITI_MEMBER_ITEM_CACHE = "infiniti_member_item_cache";
    private final InfinitiMemberDao infinitiMemberDao = new InfinitiMemberDao();

    public Integer adminCount(String app_id, String member_redeem_code) {
        Cnd cnd = new Cnd();
        cnd.where(InfinitiMember.SYSTEM_STATUS, true);
        cnd.and(InfinitiMember.APP_ID, app_id);
        cnd.andAllowEmpty(InfinitiMember.MEMBER_REDEEM_CODE, member_redeem_code);

        Integer count = infinitiMemberDao.count(cnd);
        return count;
    }

    public Integer prizeCount(String prize_id) {
        Cnd cnd = new Cnd();
        cnd.where(InfinitiMember.SYSTEM_STATUS, true);
        cnd.and(InfinitiMember.PRIZE_ID, prize_id);

        Integer count = infinitiMemberDao.count(cnd);
        return count;
    }

    public Integer redeemCodeCount(String member_redeem_code) {
        Cnd cnd = new Cnd();
        cnd.where(InfinitiMember.SYSTEM_STATUS, true);
        cnd.and(InfinitiMember.MEMBER_REDEEM_CODE, member_redeem_code);

        Integer count = infinitiMemberDao.count(cnd);
        return count;
    }

    public Integer memberMobileCount(String member_mobile) {
        Cnd cnd = new Cnd();
        cnd.where(InfinitiMember.SYSTEM_STATUS, true);
        cnd.and(InfinitiMember.MEMBER_MOBILE, member_mobile);

        Integer count = infinitiMemberDao.count(cnd);
        return count;
    }

    public List<InfinitiMember> adminList(String app_id, String member_redeem_code, Integer m, Integer n) {
        Cnd cnd = new Cnd();
        cnd.where(InfinitiMember.SYSTEM_STATUS, true);
        cnd.and(InfinitiMember.APP_ID, app_id);
        cnd.andAllowEmpty(InfinitiMember.MEMBER_REDEEM_CODE, member_redeem_code);
        cnd.paginate(m, n);

        List<InfinitiMember> infiniti_memberList = infinitiMemberDao.list(cnd);
        return infiniti_memberList;
    }

    public InfinitiMember find(String member_id) {
        InfinitiMember infiniti_member = CacheUtil.get(INFINITI_MEMBER_ITEM_CACHE, member_id);

        if (infiniti_member == null) {
            infiniti_member = infinitiMemberDao.find(member_id);

            CacheUtil.put(INFINITI_MEMBER_ITEM_CACHE, member_id, infiniti_member);
        }

        return infiniti_member;
    }

    public InfinitiMember memberRedeemCodeFind(String member_redeem_code) {
        Cnd cnd = new Cnd();
        cnd.where(InfinitiMember.SYSTEM_STATUS, true);
        cnd.andAllowEmpty(InfinitiMember.MEMBER_REDEEM_CODE, member_redeem_code);

        InfinitiMember infiniti_member = infinitiMemberDao.find(cnd);
        return infiniti_member;
    }

    public Boolean save(String member_id, String app_id, String member_name, String member_mobile, String member_address, String prize_id, String member_redeem_code, Boolean member_redeem_code_is_exchange) {
        InfinitiMember infiniti_member = new InfinitiMember();
        infiniti_member.setMember_id(member_id);
        infiniti_member.setApp_id(app_id);
        infiniti_member.setMember_name(member_name);
        infiniti_member.setMember_mobile(member_mobile);
        infiniti_member.setMember_address(member_address);
        infiniti_member.setPrize_id(prize_id);
        infiniti_member.setMember_redeem_code(member_redeem_code);
        infiniti_member.setMember_redeem_code_is_exchange(member_redeem_code_is_exchange);
        String system_create_member_id = "";

        Boolean success = save(infiniti_member, system_create_member_id);
        return success;
    }

    public Boolean save(InfinitiMember infiniti_member, String system_create_member_id) {
        Boolean success = infinitiMemberDao.save(infiniti_member, system_create_member_id);
        return success;
    }

    public Boolean update(String member_name, String member_mobile, String member_address, String member_redeem_code, Boolean member_redeem_code_is_exchange) {
        InfinitiMember infiniti_member = new InfinitiMember();
        infiniti_member.setMember_name(member_name);
        infiniti_member.setMember_mobile(member_mobile);
        infiniti_member.setMember_address(member_address);
        infiniti_member.setMember_redeem_code_is_exchange(member_redeem_code_is_exchange);
        String system_update_member_id = "";

        Cnd cnd = new Cnd();
        cnd.where(InfinitiMember.SYSTEM_STATUS, true);
        cnd.and(InfinitiMember.MEMBER_REDEEM_CODE, member_redeem_code);

        Boolean success = infinitiMemberDao.update(infiniti_member, system_update_member_id, cnd);
        return success;
    }

    public Boolean update(InfinitiMember infiniti_member, String member_id, String system_update_member_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(InfinitiMember.SYSTEM_STATUS, true);
        cnd.and(InfinitiMember.MEMBER_ID, member_id);
        cnd.and(InfinitiMember.SYSTEM_VERSION, system_version);

        Boolean success = infinitiMemberDao.update(infiniti_member, system_update_member_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(INFINITI_MEMBER_ITEM_CACHE, member_id);
        }

        return success;
    }

    public Boolean delete(String member_id, String system_update_member_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(InfinitiMember.SYSTEM_STATUS, true);
        cnd.and(InfinitiMember.MEMBER_ID, member_id);
        cnd.and(InfinitiMember.SYSTEM_VERSION, system_version);

        Boolean success = infinitiMemberDao.delete(system_update_member_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(INFINITI_MEMBER_ITEM_CACHE, member_id);
        }

        return success;
    }

}