package com.nowui.chuangshi.api.advertisement.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.nowui.chuangshi.api.advertisement.dao.AdvertisementDao;
import com.nowui.chuangshi.api.advertisement.model.Advertisement;
import com.nowui.chuangshi.api.file.model.File;
import com.nowui.chuangshi.api.file.service.FileService;
import com.nowui.chuangshi.common.service.Service;
import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.util.CacheUtil;

public class AdvertisementService extends Service {

    public static final AdvertisementService instance = new AdvertisementService();
    private final String ADVERTISEMENT_ITEM_CACHE = "advertisement_item_cache";
    private final AdvertisementDao advertisementDao = new AdvertisementDao();

    public Integer adminCount(String app_id, String advertisement_category_code, String advertisement_title) {
        Cnd cnd = new Cnd();       
        cnd.where(Advertisement.SYSTEM_STATUS, true);
        cnd.and(Advertisement.APP_ID, app_id);
        cnd.andAllowEmpty(Advertisement.ADVERTISEMENT_CATEGORY_CODE, advertisement_category_code);
        cnd.andAllowEmpty(Advertisement.ADVERTISEMENT_TITLE, advertisement_title);

        Integer count = advertisementDao.count(cnd);
        return count;
    }

    public List<Advertisement> adminList(String app_id, String advertisement_category_code, String advertisement_title, Integer m, Integer n) {
        Cnd cnd = new Cnd();       
        cnd.where(Advertisement.SYSTEM_STATUS, true);
        cnd.and(Advertisement.APP_ID, app_id);
        cnd.andAllowEmpty(Advertisement.ADVERTISEMENT_CATEGORY_CODE, advertisement_category_code);
        cnd.andAllowEmpty(Advertisement.ADVERTISEMENT_TITLE, advertisement_title);
        cnd.asc(Advertisement.ADVERTISEMENT_SORT);
        cnd.paginate(m, n);

        List<Advertisement> advertisementList = advertisementDao.primaryKeyList(cnd);
        for (Advertisement advertisement : advertisementList) {
            advertisement.put(find(advertisement.getAdvertisement_id()));
        }
        return advertisementList;
    }
    
    public List<Map<String, Object>> adminCategoryCodeList(String app_id, String advertisement_category_code) {
        Cnd cnd = new Cnd();        
        cnd.where(Advertisement.SYSTEM_STATUS, true);
        cnd.and(Advertisement.APP_ID, app_id);
        cnd.andAllowEmpty(Advertisement.ADVERTISEMENT_CATEGORY_CODE, advertisement_category_code);
        cnd.asc(Advertisement.ADVERTISEMENT_SORT);
        
        List<Advertisement> advertisementList = advertisementDao.primaryKeyList(cnd);
        List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
        for (Advertisement advertisement : advertisementList) {
            Advertisement bean = find(advertisement.getAdvertisement_id());
            
            Map<String, Object> map = new HashMap<String, Object>();
            map.put(Advertisement.ADVERTISEMENT_IMAGE_FILE, FileService.instance.getOriginalFile(bean.getAdvertisement_image()));
            map.put(Advertisement.ADVERTISEMENT_ID, bean.getAdvertisement_id());
            map.put(Advertisement.ADVERTISEMENT_TITLE, bean.getAdvertisement_title());
            map.put(Advertisement.ADVERTISEMENT_LINK, bean.getAdvertisement_link());
            map.put(Advertisement.ADVERTISEMENT_IS_FLOAT, bean.getAdvertisement_is_float());
            resultList.add(map);
        }
        return resultList;
    }

    public List<Advertisement> appList(String app_id) {
        Cnd cnd = new Cnd();
        cnd.where(Advertisement.SYSTEM_STATUS, true);
        cnd.and(Advertisement.APP_ID, app_id);
        cnd.asc(Advertisement.ADVERTISEMENT_SORT);

        List<Advertisement> articleCategoryList = advertisementDao.primaryKeyList(cnd);
        for (Advertisement articleCategory : articleCategoryList) {
            articleCategory.put(find(articleCategory.getAdvertisement_id()));
        }
        return articleCategoryList;
    }

    public Advertisement find(String advertisement_id) {
        Advertisement advertisement = CacheUtil.get(ADVERTISEMENT_ITEM_CACHE, advertisement_id);

        if (advertisement == null) {
            Cnd cnd = new Cnd();
            cnd.selectIfNull(File.TABLE_FILE + "." + File.FILE_ID, "", File.FILE_ID);
            cnd.selectIfNull(File.TABLE_FILE + "." + File.FILE_PATH, "", File.FILE_PATH);
            cnd.leftJoin(File.TABLE_FILE, File.FILE_ID, Advertisement.TABLE_ADVERTISEMENT, Advertisement.ADVERTISEMENT_IMAGE);
            cnd.where(Advertisement.TABLE_ADVERTISEMENT + "." + Advertisement.SYSTEM_STATUS, true);
            cnd.and(Advertisement.TABLE_ADVERTISEMENT + "." + Advertisement.ADVERTISEMENT_ID, advertisement_id);

            advertisement = advertisementDao.find(cnd);

            CacheUtil.put(ADVERTISEMENT_ITEM_CACHE, advertisement_id, advertisement);
        }

        return advertisement;
    }

    public Boolean save(Advertisement advertisement, String system_create_user_id) {
        Boolean success = advertisementDao.save(advertisement, system_create_user_id);
        return success;
    }

    public Boolean update(Advertisement advertisement, String advertisement_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();   
        cnd.where(Advertisement.SYSTEM_STATUS, true);
        cnd.and(Advertisement.ADVERTISEMENT_ID, advertisement_id);
        cnd.and(Advertisement.SYSTEM_VERSION, system_version);

        Boolean success = advertisementDao.update(advertisement, system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(ADVERTISEMENT_ITEM_CACHE, advertisement_id);
        }

        return success;
    }

    public Boolean delete(String advertisement_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();        
        cnd.where(Advertisement.SYSTEM_STATUS, true);
        cnd.and(Advertisement.ADVERTISEMENT_ID, advertisement_id);
        cnd.and(Advertisement.SYSTEM_VERSION, system_version);

        Boolean success = advertisementDao.delete(system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(ADVERTISEMENT_ITEM_CACHE, advertisement_id);
        }

        return success;
    }

}