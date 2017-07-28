package com.nowui.chuangshi.service;

import java.util.Date;
import java.util.List;

import com.nowui.chuangshi.cache.CertificateImageCache;
import com.nowui.chuangshi.model.CertificateImage;

public class CertificateImageService extends Service {

    private CertificateImageCache certificateImageCache = new CertificateImageCache();

    public List<CertificateImage> listByCertificate_id(String certificate_id) {
        return certificateImageCache.listByCertificate_id(certificate_id);
    }

    public Boolean deleteByCertificate_idAndSystem_update_user_id(String certificate_id, String request_user_id) {
        return certificateImageCache.deleteByCertificate_idAndSystem_update_user_id(certificate_id, request_user_id);
    }

    public Boolean save(String certificate_id, String file_id, String certificate_type, String certificate_channel_name,
            String certificate_channel_url, String certificate_people_name, String certificate_people_id_card,
            String certificate_people_mobile, String certificate_shop_name, String certificate_shop_url,
            Date certificate_start_date, Date certificate_end_date, String system_create_user_id) {
        return certificateImageCache.save(certificate_id, file_id, certificate_type, certificate_channel_name,
                certificate_channel_url, certificate_people_name, certificate_people_id_card, certificate_people_mobile,
                certificate_shop_name, certificate_shop_url, certificate_start_date, certificate_end_date,
                system_create_user_id);
    }

}