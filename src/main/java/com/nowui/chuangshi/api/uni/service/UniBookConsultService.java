package com.nowui.chuangshi.api.uni.service;

import com.nowui.chuangshi.api.uni.dao.UniBookConsultDao;
import com.nowui.chuangshi.api.uni.model.UniBookConsult;
import com.nowui.chuangshi.common.service.Service;
import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.util.CacheUtil;

import java.util.List;

public class UniBookConsultService extends Service {

    public static final UniBookConsultService instance = new UniBookConsultService();
    private final String UNI_BOOK_CONSULT_ITEM_CACHE = "uni_book_consult_item_cache";
    private final UniBookConsultDao uniBookConsultDao = new UniBookConsultDao();

    public Integer adminCount(String app_id, String book_consult_name, String book_consult_mobile) {
        Cnd cnd = new Cnd();
        cnd.where(UniBookConsult.SYSTEM_STATUS, true);
        cnd.and(UniBookConsult.APP_ID, app_id);
        cnd.andAllowEmpty(UniBookConsult.BOOK_CONSULT_NAME, book_consult_name);
        cnd.andAllowEmpty(UniBookConsult.BOOK_CONSULT_MOBILE, book_consult_mobile);

        Integer count = uniBookConsultDao.count(cnd);
        return count;
    }

    public List<UniBookConsult> adminList(String app_id, String book_consult_name, String book_consult_mobile, Integer m, Integer n) {
        Cnd cnd = new Cnd();
        cnd.where(UniBookConsult.SYSTEM_STATUS, true);
        cnd.and(UniBookConsult.APP_ID, app_id);
        cnd.andAllowEmpty(UniBookConsult.BOOK_CONSULT_NAME, book_consult_name);
        cnd.andAllowEmpty(UniBookConsult.BOOK_CONSULT_MOBILE, book_consult_mobile);
        cnd.paginate(m, n);

        List<UniBookConsult> uni_book_consultList = uniBookConsultDao.primaryKeyList(cnd);
        for (UniBookConsult uni_book_consult : uni_book_consultList) {
            uni_book_consult.put(find(uni_book_consult.getBook_consult_id()));
        }
        return uni_book_consultList;
    }
    
    public UniBookConsult mobileFind(String app_id, String book_consult_mobile) {
        Cnd cnd = new Cnd();
        cnd.where(UniBookConsult.SYSTEM_STATUS, true);
        cnd.and(UniBookConsult.APP_ID, app_id);
        cnd.andAllowEmpty(UniBookConsult.BOOK_CONSULT_MOBILE, book_consult_mobile);

        List<UniBookConsult> uni_book_consultList = uniBookConsultDao.primaryKeyList(cnd);
        
        if (uni_book_consultList == null || uni_book_consultList.size() == 0) {
            return null;
        }
        return find(uni_book_consultList.get(0).getBook_consult_id());
    }

    public UniBookConsult find(String book_consult_id) {
        UniBookConsult uni_book_consult = CacheUtil.get(UNI_BOOK_CONSULT_ITEM_CACHE, book_consult_id);

        if (uni_book_consult == null) {
            uni_book_consult = uniBookConsultDao.find(book_consult_id);

            CacheUtil.put(UNI_BOOK_CONSULT_ITEM_CACHE, book_consult_id, uni_book_consult);
        }

        return uni_book_consult;
    }

    public Boolean save(UniBookConsult uni_book_consult, String system_create_user_id) {
        Boolean success = uniBookConsultDao.save(uni_book_consult, system_create_user_id);
        return success;
    }

    public Boolean update(UniBookConsult uni_book_consult, String book_consult_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(UniBookConsult.SYSTEM_STATUS, true);
        cnd.and(UniBookConsult.BOOK_CONSULT_ID, book_consult_id);
        cnd.and(UniBookConsult.SYSTEM_VERSION, system_version);

        Boolean success = uniBookConsultDao.update(uni_book_consult, system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(UNI_BOOK_CONSULT_ITEM_CACHE, book_consult_id);
        }

        return success;
    }

    public Boolean delete(String book_consult_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(UniBookConsult.SYSTEM_STATUS, true);
        cnd.and(UniBookConsult.BOOK_CONSULT_ID, book_consult_id);
        cnd.and(UniBookConsult.SYSTEM_VERSION, system_version);

        Boolean success = uniBookConsultDao.delete(system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(UNI_BOOK_CONSULT_ITEM_CACHE, book_consult_id);
        }

        return success;
    }

}