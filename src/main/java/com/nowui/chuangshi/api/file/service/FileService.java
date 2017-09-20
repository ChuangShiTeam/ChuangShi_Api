package com.nowui.chuangshi.api.file.service;

import com.nowui.chuangshi.api.file.dao.FileDao;
import com.nowui.chuangshi.api.file.model.File;
import com.nowui.chuangshi.common.service.Service;
import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.util.CacheUtil;
import com.nowui.chuangshi.util.ValidateUtil;

import java.util.List;

public class FileService extends Service {

    public static final FileService instance = new FileService();
    private final String FILE_ITEM_CACHE = "file_item_cache";
    private final FileDao fileDao = new FileDao();

    public Integer adminCount(String app_id, String file_name) {
        Cnd cnd = new Cnd();
        cnd.where(File.SYSTEM_STATUS, true);
        cnd.and(File.APP_ID, app_id);
        cnd.andAllowEmpty(File.FILE_NAME, file_name);

        Integer count = fileDao.count(cnd);
        return count;
    }

    public List<File> adminList(String app_id, String file_name, Integer m, Integer n) {
        Cnd cnd = new Cnd();
        cnd.where(File.SYSTEM_STATUS, true);
        cnd.and(File.APP_ID, app_id);
        cnd.andAllowEmpty(File.FILE_NAME, file_name);
        cnd.paginate(m, n);

        List<File> fileList = fileDao.primaryKeyList(cnd);
        for (File file : fileList) {
            file.put(find(file.getFile_id()));
        }
        return fileList;
    }

    public File find(String file_id) {
        File file = CacheUtil.get(FILE_ITEM_CACHE, file_id);

        if (file == null) {
            file = fileDao.find(file_id);

            CacheUtil.put(FILE_ITEM_CACHE, file_id, file);
        }

        return file;
    }

    public Boolean save(String file_id, String app_id, String file_type, String file_name, String file_suffix, Integer file_size, String file_path, String file_thumbnail_path, String file_original_path, String file_image, Boolean file_is_external, String system_create_user_id) {
        File file = new File();
        file.setFile_id(file_id);
        file.setApp_id(app_id);
        file.setFile_type(file_type);
        file.setFile_name(file_name);
        file.setFile_suffix(file_suffix);
        file.setFile_size(file_size);
        file.setFile_path(file_path);
        file.setFile_thumbnail_path(file_thumbnail_path);
        file.setFile_original_path(file_original_path);
        file.setFile_image(file_image);
        file.setFile_is_external(file_is_external);

        Boolean success = fileDao.save(file, system_create_user_id);
        return success;
    }

    public Boolean save(File file, String system_create_user_id) {
        Boolean success = fileDao.save(file, system_create_user_id);
        return success;
    }

    public Boolean update(File file, String file_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(File.SYSTEM_STATUS, true);
        cnd.and(File.FILE_ID, file_id);
        cnd.and(File.SYSTEM_VERSION, system_version);

        Boolean success = fileDao.update(file, system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(FILE_ITEM_CACHE, file_id);
        }

        return success;
    }

    public Boolean filePathUpdate(String file_id, String file_path, String system_update_user_id) {
        File file = new File();
        file.setFile_path(file_path);
        file.setFile_thumbnail_path(file_path);
        file.setFile_original_path(file_path);

        Cnd cnd = new Cnd();
        cnd.where(File.SYSTEM_STATUS, true);
        cnd.and(File.FILE_ID, file_id);

        Boolean success = fileDao.update(file, system_update_user_id, cnd);

        if (success) {
            CacheUtil.remove(FILE_ITEM_CACHE, file_id);
        }

        return success;
    }

    public Boolean delete(String file_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(File.SYSTEM_STATUS, true);
        cnd.and(File.FILE_ID, file_id);
        cnd.and(File.SYSTEM_VERSION, system_version);

        Boolean success = fileDao.delete(system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(FILE_ITEM_CACHE, file_id);
        }

        return success;
    }

    public File getFile(String file_id) {
        if (ValidateUtil.isNullOrEmpty(file_id)) {
            return null;
        }

        File file = find(file_id);

        if (file == null) {
            file = new File();
        }

        return file.keep(File.FILE_ID, File.FILE_NAME, File.FILE_PATH);
    }
    
    public File getOriginalFile(String file_id) {
        if (ValidateUtil.isNullOrEmpty(file_id)) {
            return null;
        }

        File file = find(file_id);

        if (file == null) {
            file = new File();
        } 

        return file.keep(File.FILE_ID, File.FILE_NAME, File.FILE_ORIGINAL_PATH);
    }

    public String getFile_path(String file_id) {
        if (ValidateUtil.isNullOrEmpty(file_id)) {
            return "";
        }

        File file = find(file_id);

        if (file == null) {
            return "";
        }

        return file.getFile_original_path();
    }

}