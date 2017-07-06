package com.nowui.chuangshi.service;

import com.jfinal.kit.FileKit;
import com.jfinal.kit.PathKit;
import com.jfinal.upload.UploadFile;
import com.nowui.chuangshi.cache.FileCache;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.model.File;
import com.nowui.chuangshi.type.FileType;
import com.nowui.chuangshi.util.FileUtil;
import com.nowui.chuangshi.util.Util;

import java.util.*;

public class FileService extends Service {

    private FileCache fileCache = new FileCache();

    public Integer countByApp_id(String app_id) {
        return fileCache.countByApp_id(app_id);
    }

    public Integer countByOrApp_id(String app_id) {
        return fileCache.countByOrApp_id(app_id);
    }

    public Integer countByApp_idAndFile_typeAndSystem_create_user_id(String app_id, String file_type, String system_create_user_id) {
        return fileCache.countByApp_idAndFile_typeAndSystem_create_user_id(app_id, file_type, system_create_user_id);
    }

    public Integer countByOrApp_idAndFile_type(String app_id, String file_type) {
        return fileCache.countByOrApp_idAndFile_type(app_id, file_type);
    }

    public List<File> listByApp_idAndSystem_create_timeAndLimit(String app_id, Date system_create_time, int m, int n) {
        return fileCache.listByApp_idAndSystem_create_timeAndLimit(app_id, system_create_time, m, n);
    }

    public List<File> listByApp_idAndLimit(String app_id, int m, int n) {
        return fileCache.listByApp_idAndLimit(app_id, m, n);
    }

    public List<File> listByOrApp_idAndLimit(String app_id, int m, int n) {
        return fileCache.listByOrApp_idAndLimit(app_id, m, n);
    }

    public List<File> listByApp_idAndFile_typeAndSystem_create_user_idAndLimit(String app_id, String file_type, String system_create_user_id, int m, int n) {
        return fileCache.listByApp_idAndFile_typeAndSystem_create_user_idAndLimit(app_id, file_type, system_create_user_id, m, n);
    }

    public List<File> listByOrApp_idAndFile_typeAndLimit(String app_id, String file_type, int m, int n) {
        return fileCache.listByOrApp_idAndFile_typeAndLimit(app_id, file_type, m, n);
    }

    public File findByFile_id(String file_id) {
        return fileCache.findByFile_id(file_id);
    }

    public Boolean save(String file_id, String app_id, String file_type, String file_name, String file_suffix, Integer file_size, String file_path, String file_thumbnail_path, String file_original_path, String file_image, String system_create_user_id) {
        return fileCache.save(file_id, app_id, file_type, file_name, file_suffix, file_size, file_path, file_thumbnail_path, file_original_path, file_image, system_create_user_id);
    }

    public Boolean updateValidateSystem_version(String file_id, String file_type, String file_name, String file_suffix, Integer file_size, String file_path, String file_thumbnail_path, String file_original_path, String file_image, String system_update_user_id, Integer system_version) {
        return fileCache.updateValidateSystem_version(file_id, file_type, file_name, file_suffix, file_size, file_path, file_thumbnail_path, file_original_path, file_image, system_update_user_id, system_version);
    }

    public Boolean deleteByFile_idAndSystem_update_user_idValidateSystem_version(String file_id, String system_update_user_id, Integer system_version) {
        return fileCache.deleteByFile_idAndSystem_update_user_idValidateSystem_version(file_id, system_update_user_id, system_version);
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
            String file_suffix = uploadFile.getFileName().substring(uploadFile.getFileName().lastIndexOf(".") + 1);
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

            Boolean result = save(file_id, app_id, file_type, file_name, file_suffix, file_size, file_path, file_thumbnail_path, file_original_path, file_image, system_create_user_id);

            if (!result) {
                throw new RuntimeException("上传不成功");
            }

            Map<String, Object> map = new HashMap<String, Object>();
            map.put(File.FILE_ID, file_id);
            map.put(File.FILE_NAME, file_name);
            map.put(File.FILE_PATH, file_path);
            list.add(map);
        }

        return list;
    }

}