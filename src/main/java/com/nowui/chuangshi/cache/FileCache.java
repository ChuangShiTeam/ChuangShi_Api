package com.nowui.chuangshi.cache;

import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.dao.FileDao;
import com.nowui.chuangshi.model.File;
import com.nowui.chuangshi.util.CacheUtil;
import com.nowui.chuangshi.util.ValidateUtil;

import java.util.Date;
import java.util.List;

public class FileCache extends Cache {

    public static final String FILE_BY_FILE_ID_CACHE = "file_by_file_id_cache";

    private FileDao fileDao = new FileDao();

    public Integer countByApp_id(String app_id) {
        return fileDao.countByApp_id(app_id);
    }

    public Integer countByOrApp_id(String app_id) {
        return fileDao.countByOrApp_id(app_id);
    }

    public Integer countByApp_idAndFile_typeAndSystem_create_user_id(String app_id, String file_type,
            String system_create_user_id) {
        return fileDao.countByApp_idAndFile_typeAndSystem_create_user_id(app_id, file_type, system_create_user_id);
    }

    public Integer countByOrApp_idAndFile_type(String app_id, String file_type) {
        return fileDao.countByOrApp_idAndFile_type(app_id, file_type);
    }

    public List<File> listByApp_idAndSystem_create_timeAndLimit(String app_id, Date system_create_time, int m, int n) {
        List<File> fileList = fileDao.listByApp_idAndSystem_create_timeAndLimit(app_id, system_create_time, m, n);

        for (File file : fileList) {
            file.put(findByFile_id(file.getFile_id()));
        }

        return fileList;
    }

    public List<File> listByApp_idAndLimit(String app_id, int m, int n) {
        List<File> fileList = fileDao.listByApp_idAndLimit(app_id, m, n);

        for (File file : fileList) {
            file.put(findByFile_id(file.getFile_id()));
        }

        return fileList;
    }

    public List<File> listByOrApp_idAndLimit(String app_id, int m, int n) {
        List<File> fileList = fileDao.listByOrApp_idAndLimit(app_id, m, n);

        for (File file : fileList) {
            file.put(findByFile_id(file.getFile_id()));
        }

        return fileList;
    }

    public List<File> listByApp_idAndFile_typeAndSystem_create_user_idAndLimit(String app_id, String file_type,
            String system_create_user_id, int m, int n) {
        List<File> fileList = fileDao.listByApp_idAndFile_typeAndSystem_create_user_idAndLimit(app_id, file_type,
                system_create_user_id, m, n);

        for (File file : fileList) {
            file.put(findByFile_id(file.getFile_id()));
        }

        return fileList;
    }

    public List<File> listByOrApp_idAndFile_typeAndLimit(String app_id, String file_type, int m, int n) {
        List<File> fileList = fileDao.listByOrApp_idAndFile_typeAndLimit(app_id, file_type, m, n);

        for (File file : fileList) {
            file.put(findByFile_id(file.getFile_id()));
        }

        return fileList;
    }

    public File findByFile_id(String file_id) {
        File file = CacheUtil.get(FILE_BY_FILE_ID_CACHE, file_id);

        if (file == null) {
            file = fileDao.findByFile_id(file_id);

            CacheUtil.put(FILE_BY_FILE_ID_CACHE, file_id, file);
        }

        return file;
    }

    public Boolean save(String file_id, String app_id, String file_type, String file_name, String file_suffix,
            Integer file_size, String file_path, String file_thumbnail_path, String file_original_path,
            String file_image, Boolean file_is_external, String system_create_user_id) {
        Boolean result = fileDao.save(file_id, app_id, file_type, file_name, file_suffix, file_size, file_path,
                file_thumbnail_path, file_original_path, file_image, file_is_external, system_create_user_id);

        if (result) {
            CacheUtil.remove(FILE_BY_FILE_ID_CACHE, file_id);
        }

        return result;
    }

    public Boolean updateValidateSystem_version(String file_id, String file_type, String file_name, String file_suffix,
            Integer file_size, String file_path, String file_thumbnail_path, String file_original_path,
            String file_image, String system_update_user_id, Integer system_version) {
        File file = findByFile_id(file_id);
        if (!file.getSystem_version().equals(system_version)) {
            throw new RuntimeException(Constant.ERROR_VERSION);
        }

        boolean result = fileDao.update(file_id, file_type, file_name, file_suffix, file_size, file_path,
                file_thumbnail_path, file_original_path, file_image, system_update_user_id, system_version);

        if (result) {
            CacheUtil.remove(FILE_BY_FILE_ID_CACHE, file_id);
        }

        return result;
    }

    public Boolean updateByFile_path(String file_id, String file_path, String system_update_user_id) {
        if (ValidateUtil.isNullOrEmpty(file_path)) {
            return false;
        }

        boolean result = fileDao.updateByFile_path(file_id, file_path, system_update_user_id);

        if (result) {
            CacheUtil.remove(FILE_BY_FILE_ID_CACHE, file_id);
        }

        return result;
    }

    public Boolean deleteByFile_idAndSystem_update_user_idValidateSystem_version(String file_id,
            String system_update_user_id, Integer system_version) {
        File file = findByFile_id(file_id);
        if (!file.getSystem_version().equals(system_version)) {
            throw new RuntimeException(Constant.ERROR_VERSION);
        }

        boolean result = fileDao.deleteByFile_idAndSystem_version(file_id, system_update_user_id, system_version);

        if (result) {
            CacheUtil.remove(FILE_BY_FILE_ID_CACHE, file_id);
        }

        return result;
    }

}