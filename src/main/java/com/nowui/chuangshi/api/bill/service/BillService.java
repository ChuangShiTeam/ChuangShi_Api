package com.nowui.chuangshi.api.bill.service;

import com.nowui.chuangshi.api.bill.dao.BillDao;
import com.nowui.chuangshi.api.bill.model.Bill;
import com.nowui.chuangshi.common.service.Service;
import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.util.CacheUtil;

import java.util.List;

public class BillService extends Service {

    public static final BillService instance = new BillService();
    private final String BILL_ITEM_CACHE = "bill_item_cache";
    private final BillDao billDao = new BillDao();

    public Integer adminCount(String app_id, String bill_name) {
        Cnd cnd = new Cnd();
        cnd.where(Bill.SYSTEM_STATUS, true);
        cnd.and(Bill.APP_ID, app_id);
        cnd.andAllowEmpty(Bill.BILL_NAME, bill_name);

        Integer count = billDao.count(cnd);
        return count;
    }

    public List<Bill> adminList(String app_id, String bill_name, Integer m, Integer n) {
        Cnd cnd = new Cnd();
        cnd.where(Bill.SYSTEM_STATUS, true);
        cnd.and(Bill.APP_ID, app_id);
        cnd.andAllowEmpty(Bill.BILL_NAME, bill_name);
        cnd.paginate(m, n);

        List<Bill> billList = billDao.primaryKeyList(cnd);
        for (Bill bill : billList) {
            bill.put(find(bill.getBill_id()));
        }
        return billList;
    }

    public Bill find(String bill_id) {
        Bill bill = CacheUtil.get(BILL_ITEM_CACHE, bill_id);

        if (bill == null) {
            bill = billDao.find(bill_id);

            CacheUtil.put(BILL_ITEM_CACHE, bill_id, bill);
        }

        return bill;
    }

    public Boolean save(Bill bill, String system_create_user_id) {
        Boolean success = billDao.save(bill, system_create_user_id);
        return success;
    }

    public Boolean update(Bill bill, String bill_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(Bill.SYSTEM_STATUS, true);
        cnd.and(Bill.BILL_ID, bill_id);
        cnd.and(Bill.SYSTEM_VERSION, system_version);

        Boolean success = billDao.update(bill, system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(BILL_ITEM_CACHE, bill_id);
        }

        return success;
    }

    public Boolean delete(String bill_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(Bill.SYSTEM_STATUS, true);
        cnd.and(Bill.BILL_ID, bill_id);
        cnd.and(Bill.SYSTEM_VERSION, system_version);

        Boolean success = billDao.delete(system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(BILL_ITEM_CACHE, bill_id);
        }

        return success;
    }

}