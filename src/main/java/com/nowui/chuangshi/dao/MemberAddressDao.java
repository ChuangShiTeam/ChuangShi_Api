package com.nowui.chuangshi.dao;

import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.SqlPara;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.model.MemberAddress;

import java.util.Date;
import java.util.List;

public class MemberAddressDao extends Dao {

    public Integer countByApp_idOrLikeMember_address_name(String app_id, String member_address_name) {
        Kv sqlMap = Kv.create();
        sqlMap.put(MemberAddress.APP_ID, app_id);
        sqlMap.put(MemberAddress.MEMBER_ADDRESS_NAME, member_address_name);
        SqlPara sqlPara = Db.getSqlPara("member_address.countByApp_idOrLikeMember_address_name", sqlMap);

        logSql("member_address", "countByApp_idOrLikeMember_address_name", sqlPara);

        Number count = Db.queryFirst(sqlPara.getSql(), sqlPara.getPara());
        return count.intValue();
    }

    public Integer countByOrApp_idOrLikeMember_address_name(String app_id, String member_address_name) {
        Kv sqlMap = Kv.create();
        sqlMap.put(MemberAddress.APP_ID, app_id);
        sqlMap.put(MemberAddress.MEMBER_ADDRESS_NAME, member_address_name);
        SqlPara sqlPara = Db.getSqlPara("member_address.countByOrApp_idOrLikeMember_address_name", sqlMap);

        logSql("member_address", "countByOrApp_idOrLikeMember_address_name", sqlPara);

        Number count = Db.queryFirst(sqlPara.getSql(), sqlPara.getPara());
        return count.intValue();
    }

    public List<MemberAddress> listByApp_idAndSystem_create_timeAndLimit(String app_id, Date system_create_time, int m, int n) {
        Kv sqlMap = Kv.create();
        sqlMap.put(MemberAddress.APP_ID, app_id);
        sqlMap.put(MemberAddress.SYSTEM_CREATE_TIME, system_create_time);
        sqlMap.put(Constant.M, m);
        sqlMap.put(Constant.N, n);
        SqlPara sqlPara = Db.getSqlPara("member_address.listByApp_idAndSystem_create_timeAndLimit", sqlMap);

        logSql("member_address", "listByApp_idAndSystem_create_timeAndLimit", sqlPara);

        return new MemberAddress().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public List<MemberAddress> listByApp_idOrLikeMember_address_nameAndLimit(String app_id, String member_address_name, int m, int n) {
        Kv sqlMap = Kv.create();
        sqlMap.put(MemberAddress.APP_ID, app_id);
        sqlMap.put(MemberAddress.MEMBER_ADDRESS_NAME, member_address_name);
        sqlMap.put(Constant.M, m);
        sqlMap.put(Constant.N, n);
        SqlPara sqlPara = Db.getSqlPara("member_address.listByApp_idOrLikeMember_address_nameAndLimit", sqlMap);

        logSql("member_address", "listByApp_idOrLikeMember_address_nameAndLimit", sqlPara);

        return new MemberAddress().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public List<MemberAddress> listByOrApp_idOrLikeMember_address_nameAndLimit(String app_id, String member_address_name, int m, int n) {
        Kv sqlMap = Kv.create();
        sqlMap.put(MemberAddress.APP_ID, app_id);
        sqlMap.put(MemberAddress.MEMBER_ADDRESS_NAME, member_address_name);
        sqlMap.put(Constant.M, m);
        sqlMap.put(Constant.N, n);
        SqlPara sqlPara = Db.getSqlPara("member_address.listByOrApp_idOrLikeMember_address_nameAndLimit", sqlMap);

        logSql("member_address", "listByOrApp_idOrLikeMember_address_nameAndLimit", sqlPara);

        return new MemberAddress().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public MemberAddress findByMember_address_id(String member_address_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(MemberAddress.MEMBER_ADDRESS_ID, member_address_id);
        SqlPara sqlPara = Db.getSqlPara("member_address.findByMember_address_id", sqlMap);

        logSql("member_address", "findByMember_address_id", sqlPara);

        List<MemberAddress> member_addressList = new MemberAddress().find(sqlPara.getSql(), sqlPara.getPara());
        if (member_addressList.size() == 0) {
            return null;
        } else {
            return member_addressList.get(0);
        }
    }

    public Boolean save(String member_address_id, String app_id, String member_id, String user_id, String member_address_name, String member_address_tel, String member_address_mobile, String member_address_postcode, String member_address_province, String member_address_city, String member_address_area, String member_address_address, Boolean member_delivery_is_default, String system_create_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(MemberAddress.MEMBER_ADDRESS_ID, member_address_id);
        sqlMap.put(MemberAddress.APP_ID, app_id);
        sqlMap.put(MemberAddress.MEMBER_ID, member_id);
        sqlMap.put(MemberAddress.USER_ID, user_id);
        sqlMap.put(MemberAddress.MEMBER_ADDRESS_NAME, member_address_name);
        sqlMap.put(MemberAddress.MEMBER_ADDRESS_TEL, member_address_tel);
        sqlMap.put(MemberAddress.MEMBER_ADDRESS_MOBILE, member_address_mobile);
        sqlMap.put(MemberAddress.MEMBER_ADDRESS_POSTCODE, member_address_postcode);
        sqlMap.put(MemberAddress.MEMBER_ADDRESS_PROVINCE, member_address_province);
        sqlMap.put(MemberAddress.MEMBER_ADDRESS_CITY, member_address_city);
        sqlMap.put(MemberAddress.MEMBER_ADDRESS_AREA, member_address_area);
        sqlMap.put(MemberAddress.MEMBER_ADDRESS_ADDRESS, member_address_address);
        sqlMap.put(MemberAddress.MEMBER_DELIVERY_IS_DEFAULT, member_delivery_is_default);
        sqlMap.put(MemberAddress.SYSTEM_CREATE_USER_ID, system_create_user_id);
        sqlMap.put(MemberAddress.SYSTEM_CREATE_TIME, new Date());
        sqlMap.put(MemberAddress.SYSTEM_UPDATE_USER_ID, system_create_user_id);
        sqlMap.put(MemberAddress.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(MemberAddress.SYSTEM_VERSION, 0);
        sqlMap.put(MemberAddress.SYSTEM_STATUS, true);
        SqlPara sqlPara = Db.getSqlPara("member_address.save", sqlMap);

        logSql("member_address", "save", sqlPara);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

    public Boolean update(String member_address_id, String member_id, String user_id, String member_address_name, String member_address_tel, String member_address_mobile, String member_address_postcode, String member_address_province, String member_address_city, String member_address_area, String member_address_address, Boolean member_delivery_is_default, String system_update_user_id, Integer system_version) {
        Kv sqlMap = Kv.create();
        sqlMap.put(MemberAddress.MEMBER_ADDRESS_ID, member_address_id);
        sqlMap.put(MemberAddress.MEMBER_ID, member_id);
        sqlMap.put(MemberAddress.USER_ID, user_id);
        sqlMap.put(MemberAddress.MEMBER_ADDRESS_NAME, member_address_name);
        sqlMap.put(MemberAddress.MEMBER_ADDRESS_TEL, member_address_tel);
        sqlMap.put(MemberAddress.MEMBER_ADDRESS_MOBILE, member_address_mobile);
        sqlMap.put(MemberAddress.MEMBER_ADDRESS_POSTCODE, member_address_postcode);
        sqlMap.put(MemberAddress.MEMBER_ADDRESS_PROVINCE, member_address_province);
        sqlMap.put(MemberAddress.MEMBER_ADDRESS_CITY, member_address_city);
        sqlMap.put(MemberAddress.MEMBER_ADDRESS_AREA, member_address_area);
        sqlMap.put(MemberAddress.MEMBER_ADDRESS_ADDRESS, member_address_address);
        sqlMap.put(MemberAddress.MEMBER_DELIVERY_IS_DEFAULT, member_delivery_is_default);
        sqlMap.put(MemberAddress.SYSTEM_UPDATE_USER_ID, system_update_user_id);
        sqlMap.put(MemberAddress.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(MemberAddress.SYSTEM_VERSION, system_version);
        SqlPara sqlPara = Db.getSqlPara("member_address.update", sqlMap);

        logSql("member_address", "update", sqlPara);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

    public Boolean deleteByMember_address_idAndSystem_version(String member_address_id, String system_update_user_id, Integer system_version) {
        Kv sqlMap = Kv.create();
        sqlMap.put(MemberAddress.MEMBER_ADDRESS_ID, member_address_id);
        sqlMap.put(MemberAddress.SYSTEM_UPDATE_USER_ID, system_update_user_id);
        sqlMap.put(MemberAddress.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(MemberAddress.SYSTEM_VERSION, system_version);
        SqlPara sqlPara = Db.getSqlPara("member_address.deleteByMember_address_idAndSystem_version", sqlMap);

        logSql("member_address", "deleteByMember_address_idAndSystem_version", sqlPara);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

    
    public MemberAddress findByMember_id(String member_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(MemberAddress.MEMBER_ID, member_id);
        SqlPara sqlPara = Db.getSqlPara("member_address.findByMember_id", sqlMap);

        logSql("member_address", "findByMember_address_id", sqlPara);

        List<MemberAddress> member_addressList = new MemberAddress().find(sqlPara.getSql(), sqlPara.getPara());
        if (member_addressList.size() == 0) {
            return null;
        } else {
            return member_addressList.get(0);
        }
    }

}