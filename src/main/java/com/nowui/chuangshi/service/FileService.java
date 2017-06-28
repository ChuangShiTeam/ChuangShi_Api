package com.nowui.chuangshi.service;

import com.nowui.chuangshi.cache.FileCache;
import com.nowui.chuangshi.model.File;

import java.util.Date;
import java.util.List;

public class FileService extends Service {

    private FileCache fileCache = new FileCache();

    public Integer countByApp_id(String app_id, String request_app_id, String request_http_id, String request_user_id) {
        return fileCache.countByApp_id(app_id, request_app_id, request_http_id, request_user_id);
    }

    public Integer countByOrApp_id(String app_id, String request_app_id, String request_http_id, String request_user_id) {
        return fileCache.countByOrApp_id(app_id, request_app_id, request_http_id, request_user_id);
    }

    public List<File> listByApp_idAndSystem_create_timeAndLimit(String app_id, Date system_create_time, int m, int n, String request_app_id, String request_http_id, String request_user_id) {
        return fileCache.listByApp_idAndSystem_create_timeAndLimit(app_id, system_create_time, m, n, request_app_id, request_http_id, request_user_id);
    }

    public List<File> listByApp_idAndLimit(String app_id, int m, int n, String request_app_id, String request_http_id, String request_user_id) {
        return fileCache.listByApp_idAndLimit(app_id, m, n, request_app_id, request_http_id, request_user_id);
    }

    public List<File> listByOrApp_idAndLimit(String app_id, int m, int n, String request_app_id, String request_http_id, String request_user_id) {
        return fileCache.listByOrApp_idAndLimit(app_id, m, n, request_app_id, request_http_id, request_user_id);
    }

    public File findByFile_id(String file_id, String request_app_id, String request_http_id, String request_user_id) {
        return fileCache.findByFile_id(file_id, request_app_id, request_http_id, request_user_id);
    }

    public Boolean save(String file_id, String app_id, String file_type, String file_name, String file_suffix, Integer file_size, String file_path, String file_thumbnail_path, String file_original_path, String file_image, String system_create_user_id, String request_app_id, String request_http_id, String request_user_id) {
        return fileCache.save(file_id, app_id, file_type, file_name, file_suffix, file_size, file_path, file_thumbnail_path, file_original_path, file_image, system_create_user_id, request_app_id, request_http_id, request_user_id);
    }

    public Boolean updateValidateSystem_version(String file_id, String file_type, String file_name, String file_suffix, Integer file_size, String file_path, String file_thumbnail_path, String file_original_path, String file_image, String system_update_user_id, Integer system_version, String request_app_id, String request_http_id, String request_user_id) {
        return fileCache.updateValidateSystem_version(file_id, file_type, file_name, file_suffix, file_size, file_path, file_thumbnail_path, file_original_path, file_image, system_update_user_id, system_version, request_app_id, request_http_id, request_user_id);
    }

    public Boolean deleteByFile_idAndSystem_update_user_idValidateSystem_version(String file_id, String system_update_user_id, Integer system_version, String request_app_id, String request_http_id, String request_user_id) {
        return fileCache.deleteByFile_idAndSystem_update_user_idValidateSystem_version(file_id, system_update_user_id, system_version, request_app_id, request_http_id, request_user_id);
    }

}