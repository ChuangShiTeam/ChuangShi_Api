package com.nowui.chuangshi.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.SqlPara;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.model.BillCommission;

public class BillCommissionDao extends Dao {

    public List<BillCommission> listByOrBill_idOrMember_id(String bill_id, String member_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(BillCommission.BILL_ID, bill_id);
        sqlMap.put(BillCommission.MEMBER_ID, member_id);
        SqlPara sqlPara = Db.getSqlPara("bill_commission.listByOrBill_idOrMember_id", sqlMap);

        logSql("bill_commission", "listByOrBill_idOrMember_id", sqlPara);

        return new BillCommission().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public Boolean save(String bill_id, String product_sku_id, String member_id, String member_name, String member_level_id, String member_level_name, Integer product_sku_commission,
            BigDecimal product_sku_commission_amount, String system_create_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(BillCommission.BILL_ID, bill_id);
        sqlMap.put(BillCommission.PRODUCT_SKU_ID, product_sku_id);
        sqlMap.put(BillCommission.MEMBER_ID, member_id);
        sqlMap.put(BillCommission.MEMBER_NAME, member_name);
        sqlMap.put(BillCommission.MEMBER_LEVEL_ID, member_level_id);
        sqlMap.put(BillCommission.MEMBER_LEVEL_NAME, member_level_name);
        sqlMap.put(BillCommission.PRODUCT_SKU_COMMISSION, product_sku_commission);
        sqlMap.put(BillCommission.PRODUCT_SKU_COMMISSION_AMOUNT, product_sku_commission_amount);
        sqlMap.put(BillCommission.SYSTEM_CREATE_USER_ID, system_create_user_id);
        sqlMap.put(BillCommission.SYSTEM_CREATE_TIME, new Date());
        sqlMap.put(BillCommission.SYSTEM_UPDATE_USER_ID, system_create_user_id);
        sqlMap.put(BillCommission.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(BillCommission.SYSTEM_VERSION, 0);
        sqlMap.put(BillCommission.SYSTEM_STATUS, true);
        SqlPara sqlPara = Db.getSqlPara("bill_commission.save", sqlMap);

        logSql("bill_commission", "save", sqlPara);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

    public Boolean deleteByBill_idAndSystem_version(String bill_id, String system_update_user_id, Integer system_version) {
        Kv sqlMap = Kv.create();
        sqlMap.put(BillCommission.BILL_ID, bill_id);
        sqlMap.put(BillCommission.SYSTEM_UPDATE_USER_ID, system_update_user_id);
        sqlMap.put(BillCommission.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(BillCommission.SYSTEM_VERSION, system_version);
        SqlPara sqlPara = Db.getSqlPara("bill_commission.deleteByBill_idAndSystem_version", sqlMap);

        logSql("bill_commission", "deleteByBill_idAndSystem_version", sqlPara);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

    public Boolean batchSave(List<BillCommission> billCommissionList) {
        int[] result = Db.batchSave(billCommissionList, Constant.BATCH_SIZE);

        for (int i : result) {
            if (i == 0) {
                throw new RuntimeException("账单分成保存不成功");
            }
        }
        return true;

    }

}