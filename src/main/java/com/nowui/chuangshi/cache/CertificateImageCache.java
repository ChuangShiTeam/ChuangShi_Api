package com.nowui.chuangshi.cache;

import java.util.Date;
import java.util.List;

import com.nowui.chuangshi.dao.CertificateImageDao;
import com.nowui.chuangshi.model.CertificateImage;
import com.nowui.chuangshi.util.CacheUtil;

public class CertificateImageCache extends Cache {

    public static final String CERTIFICATE_IMAGE_BY_CERTIFICATE_ID_CACHE = "certificate_image_by_certificate_id_cache";

    private CertificateImageDao certificateImageDao = new CertificateImageDao();

    public List<CertificateImage> listByCertificate_id(String certificate_id) {
        List<CertificateImage> certificate_imageList = CacheUtil.get(CERTIFICATE_IMAGE_BY_CERTIFICATE_ID_CACHE,
                certificate_id);

        if (certificate_imageList == null || certificate_imageList.size() == 0) {
            certificate_imageList = certificateImageDao.listByCertificate_id(certificate_id);

            CacheUtil.put(CERTIFICATE_IMAGE_BY_CERTIFICATE_ID_CACHE, certificate_id, certificate_imageList);
        }

        return certificate_imageList;
    }

    public Boolean save(String certificate_id, String file_id, String certificate_type, String certificate_channel_name,
            String certificate_channel_url, String certificate_people_name, String certificate_people_id_card,
            String certificate_people_mobile, String certificate_shop_name, String certificate_shop_url,
            Date certificate_start_date, Date certificate_end_date, String system_create_user_id) {
        Boolean result = certificateImageDao.save(certificate_id, file_id, certificate_type, certificate_channel_name,
                certificate_channel_url, certificate_people_name, certificate_people_id_card, certificate_people_mobile,
                certificate_shop_name, certificate_shop_url, certificate_start_date, certificate_end_date,
                system_create_user_id);

        if (result) {
            CacheUtil.remove(CERTIFICATE_IMAGE_BY_CERTIFICATE_ID_CACHE, certificate_id);
        }

        return result;
    }

    public Boolean deleteByCertificate_idAndSystem_update_user_id(String certificate_id, String system_update_user_id) {
        boolean result = certificateImageDao.deleteByCertificate_idAndSystem_update_user_id(certificate_id,
                system_update_user_id);

        if (result) {
            CacheUtil.remove(CERTIFICATE_IMAGE_BY_CERTIFICATE_ID_CACHE, certificate_id);
        }

        return result;
    }

}