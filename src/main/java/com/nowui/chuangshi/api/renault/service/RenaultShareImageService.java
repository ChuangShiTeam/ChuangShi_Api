package com.nowui.chuangshi.api.renault.service;

import com.nowui.chuangshi.api.file.model.File;
import com.nowui.chuangshi.api.file.service.FileService;
import com.nowui.chuangshi.api.renault.dao.RenaultShareImageDao;
import com.nowui.chuangshi.api.renault.model.RenaultShareImage;
import com.nowui.chuangshi.common.service.Service;
import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.util.CacheUtil;

import java.util.List;

public class RenaultShareImageService extends Service {

    public static final RenaultShareImageService instance = new RenaultShareImageService();
    private final String RENAULT_SHARE_IMAGE_ITEM_CACHE = "renault_share_image_item_cache";
    private final RenaultShareImageDao renaultShareImageDao = new RenaultShareImageDao();

    public Integer adminCount(String app_id, String share_id, String share_file_sort) {
        Cnd cnd = new Cnd();
        cnd.where(RenaultShareImage.SYSTEM_STATUS, true);
        //cnd.and(RenaultShareImage.APP_ID, app_id);
        cnd.andAllowEmpty(RenaultShareImage.SHARE_ID, share_id);
        cnd.andAllowEmpty(RenaultShareImage.SHARE_FILE_SORT, share_file_sort);

        Integer count = renaultShareImageDao.count(cnd);
        return count;
    }

    public List<RenaultShareImage> adminList(String app_id, String share_id, String share_file_sort, Integer m, Integer n) {
        Cnd cnd = new Cnd();
        cnd.where(RenaultShareImage.SYSTEM_STATUS, true);
        //cnd.and(RenaultShareImage.APP_ID, app_id);
        cnd.andAllowEmpty(RenaultShareImage.SHARE_ID, share_id);
        cnd.andAllowEmpty(RenaultShareImage.SHARE_FILE_SORT, share_file_sort);
        cnd.paginate(m, n);

        List<RenaultShareImage> renault_share_imageList = renaultShareImageDao.primaryKeyList(cnd);
        for (RenaultShareImage renault_share_image : renault_share_imageList) {
            renault_share_image.put(find(renault_share_image.getImage_id()));
        }
        return renault_share_imageList;
    }

    public RenaultShareImage find(String image_id) {
        RenaultShareImage renault_share_image = CacheUtil.get(RENAULT_SHARE_IMAGE_ITEM_CACHE, image_id);

        if (renault_share_image == null) {
            renault_share_image = renaultShareImageDao.find(image_id);
            
            renault_share_image.put(File.FILE_PATH, FileService.instance.getFile_path(renault_share_image.getFile_id()));

            CacheUtil.put(RENAULT_SHARE_IMAGE_ITEM_CACHE, image_id, renault_share_image);
        }

        return renault_share_image;
    }

    public Boolean save(RenaultShareImage renault_share_image, String system_create_user_id) {
        Boolean success = renaultShareImageDao.save(renault_share_image, system_create_user_id);
        return success;
    }

    public Boolean update(RenaultShareImage renault_share_image, String image_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(RenaultShareImage.SYSTEM_STATUS, true);
        cnd.and(RenaultShareImage.IMAGE_ID, image_id);
        cnd.and(RenaultShareImage.SYSTEM_VERSION, system_version);

        Boolean success = renaultShareImageDao.update(renault_share_image, system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(RENAULT_SHARE_IMAGE_ITEM_CACHE, image_id);
        }

        return success;
    }

    public Boolean delete(String image_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(RenaultShareImage.SYSTEM_STATUS, true);
        cnd.and(RenaultShareImage.IMAGE_ID, image_id);
        cnd.and(RenaultShareImage.SYSTEM_VERSION, system_version);

        Boolean success = renaultShareImageDao.delete(system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(RENAULT_SHARE_IMAGE_ITEM_CACHE, image_id);
        }

        return success;
    }

    //获取图片列表
    public List<RenaultShareImage> shareList(String share_id) {
        Cnd cnd = new Cnd();
        cnd.where(RenaultShareImage.SYSTEM_STATUS, true);
        cnd.andAllowEmpty(RenaultShareImage.SHARE_ID, share_id);

        List<RenaultShareImage> renault_share_imageList = renaultShareImageDao.primaryKeyList(cnd);
        for (RenaultShareImage renault_share_image : renault_share_imageList) {
            renault_share_image.put(find(renault_share_image.getImage_id()));
        }
        return renault_share_imageList;
    }

}