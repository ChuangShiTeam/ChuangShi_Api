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
        Integer count = billDao.count(Cnd.where(Bill.APP_ID, app_id).andAllowEmpty(Bill.BILL_NAME, bill_name));
        return count;
    }

    public List<Bill> adminList(String app_id, String bill_name, Integer m, Integer n) {
        List<Bill> billList = billDao.list(Cnd.where(Bill.APP_ID, app_id).andAllowEmpty(Bill.BILL_NAME, bill_name).paginate(m, n));
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

    public Boolean save(Bill bill) {
        Boolean result = billDao.save(bill);
        return result;
    }

    public Boolean update(Bill bill, String bill_id, Integer system_version) {
        Boolean result = billDao.update(bill, Cnd.where(Bill.BILL_ID, bill_id).and(Bill.SYSTEM_VERSION, system_version));

        if (result) {
            CacheUtil.remove(BILL_ITEM_CACHE, bill_id);
        }

        return result;
    }

    public Boolean delete(String bill_id, Integer system_version) {
        Boolean result = billDao.delete(Cnd.where(Bill.BILL_ID, bill_id).and(Bill.SYSTEM_VERSION, system_version));

        if (result) {
            CacheUtil.remove(BILL_ITEM_CACHE, bill_id);
        }

        return result;
    }

}