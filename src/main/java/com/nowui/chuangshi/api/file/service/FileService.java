package com.nowui.chuangshi.api.file.service;

import com.nowui.chuangshi.api.file.cache.FileCache;
import com.nowui.chuangshi.api.file.model.File;
import com.nowui.chuangshi.common.service.Service;
import com.nowui.chuangshi.util.ValidateUtil;

public class FileService extends Service {

    public static final FileService me = new FileService();

    public FileService() {
        setCache(new FileCache());
    }

    public File getFile(String file_id) {
        if (ValidateUtil.isNullOrEmpty(file_id)) {
            return null;
        }

        File file = findById(file_id);

        if (file == null) {
            file = new File();
        }

        return file;

//        return file.keep(File.FILE_ID, File.FILE_PATH);
    }

    public String getFile_path(String file_id) {
        if (ValidateUtil.isNullOrEmpty(file_id)) {
            return "";
        }

        File file = findById(file_id);

        if (file == null) {
            return "";
        }

        return file.getFile_original_path();
    }

}