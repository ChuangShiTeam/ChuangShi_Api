package com.nowui.chuangshi.api.file.service;

import com.jfinal.kit.FileKit;
import com.jfinal.kit.PathKit;
import com.jfinal.upload.UploadFile;
import com.nowui.chuangshi.api.file.dao.FileDao;
import com.nowui.chuangshi.api.file.model.File;
import com.nowui.chuangshi.common.service.Service;
import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.type.FileType;
import com.nowui.chuangshi.util.CacheUtil;
import com.nowui.chuangshi.util.FileUtil;
import com.nowui.chuangshi.util.Util;
import com.nowui.chuangshi.util.ValidateUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public Integer fileTypeCount(String app_id, String file_type, Boolean file_is_external) {
        Cnd cnd = new Cnd();
        cnd.where(File.SYSTEM_STATUS, true);
        cnd.and(File.APP_ID, app_id);
        cnd.andAllowEmpty(File.FILE_TYPE, file_type);
        cnd.andAllowEmpty(File.FILE_IS_EXTERNAL, file_is_external);

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

    public List<File> fileTypeList(String app_id, String file_type, Boolean file_is_external, Integer m, Integer n) {
        Cnd cnd = new Cnd();
        cnd.where(File.SYSTEM_STATUS, true);
        cnd.and(File.APP_ID, app_id);
        cnd.andAllowEmpty(File.FILE_TYPE, file_type);
        cnd.andAllowEmpty(File.FILE_IS_EXTERNAL, file_is_external);
        cnd.desc(File.SYSTEM_CREATE_TIME);
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

    public List<Map<String, Object>> upload(List<UploadFile> uploadFileList, String app_id, String system_create_user_id) {
        String path = PathKit.getWebRootPath() + "/" + Constant.UPLOAD + "/" + app_id + "/" + system_create_user_id;
        String thumbnailPath = PathKit.getWebRootPath() + "/" + Constant.UPLOAD + "/" + app_id + "/" + system_create_user_id + "/" + Constant.THUMBNAIL;
        String originalPath = PathKit.getWebRootPath() + "/" + Constant.UPLOAD + "/" + app_id + "/" + system_create_user_id + "/" + Constant.ORIGINAL;

        FileUtil.createPath(path);
        FileUtil.createPath(thumbnailPath);
        FileUtil.createPath(originalPath);

        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

        for (UploadFile uploadFile : uploadFileList) {
            String original_file_name = uploadFile.getFileName();
            String file_suffix = original_file_name.substring(original_file_name.lastIndexOf(".") + 1);
            String file_name = Util.getRandomUUID() + "." + file_suffix;

            path = path + "/" + file_name;
            thumbnailPath = thumbnailPath + "/" + file_name;
            originalPath = originalPath + "/" + file_name;

            String file_type = FileType.IMAGE.getKey();

            if (file_suffix.equals("png") || file_suffix.equals("jpg") || file_suffix.equals("jpeg")) {
                FileUtil.resizeImage(uploadFile.getFile(), file_suffix, thumbnailPath, 100);
                FileUtil.resizeImage(uploadFile.getFile(), file_suffix, path, 360);
                FileUtil.resizeImage(uploadFile.getFile(), file_suffix, originalPath, 0);
            } else {
                FileUtil.copy(uploadFile.getFile(), new java.io.File(path));

                thumbnailPath = path;
                originalPath = path;

                file_type = FileType.OTHER.getKey();
            }

            FileKit.delete(uploadFile.getFile());

            String file_id = Util.getRandomUUID();
            Integer file_size = (int) uploadFile.getFile().length();
            String file_path = path.replace(PathKit.getWebRootPath(), "");
            String file_thumbnail_path = thumbnailPath.replace(PathKit.getWebRootPath(), "");
            String file_original_path = originalPath.replace(PathKit.getWebRootPath(), "");
            String file_image = "";
            Boolean file_is_external = false;

            Boolean result = save(file_id, app_id, file_type, original_file_name, file_suffix, file_size, file_path, file_thumbnail_path, file_original_path, file_image, file_is_external, system_create_user_id);

            if (!result) {
                throw new RuntimeException("上传不成功");
            }

            Map<String, Object> map = new HashMap<String, Object>();
            map.put(File.FILE_ID, file_id);
            map.put(File.FILE_NAME, original_file_name);
            map.put(File.FILE_PATH, file_path);
            list.add(map);
        }

        return list;
    }

}