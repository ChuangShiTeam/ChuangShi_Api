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
        Integer count = fileDao.count(Cnd.where(File.APP_ID, app_id).andAllowEmpty(File.FILE_NAME, file_name));
        return count;
    }

    public List<File> adminList(String app_id, String file_name, Integer m, Integer n) {
        List<File> fileList = fileDao.list(Cnd.where(File.APP_ID, app_id).andAllowEmpty(File.FILE_NAME, file_name).paginate(m, n));
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

    public File getFile(String file_id) {
        if (ValidateUtil.isNullOrEmpty(file_id)) {
            return null;
        }

        File file = find(file_id);

        if (file == null) {
            file = new File();
        }

        return file.keep(File.FILE_ID, File.FILE_PATH);
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

    public Boolean save(File file) {
        Boolean result = fileDao.save(file);
        return result;
    }

    public Boolean update(File file, String file_id, Integer system_version) {
        Boolean result = fileDao.update(file, Cnd.where(File.FILE_ID, file_id).and(File.SYSTEM_VERSION, system_version));

        if (result) {
            CacheUtil.remove(FILE_ITEM_CACHE, file_id);
        }

        return result;
    }

    public Boolean delete(String file_id, Integer system_version) {
        Boolean result = fileDao.delete(Cnd.where(File.FILE_ID, file_id).and(File.SYSTEM_VERSION, system_version));

        if (result) {
            CacheUtil.remove(FILE_ITEM_CACHE, file_id);
        }

        return result;
    }

}