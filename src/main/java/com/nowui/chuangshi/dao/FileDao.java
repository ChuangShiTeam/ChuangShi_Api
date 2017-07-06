package com.nowui.chuangshi.dao;

import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.SqlPara;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.model.File;

import java.util.Date;
import java.util.List;

public class FileDao extends Dao {

    public Integer countByApp_id(String app_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(File.APP_ID, app_id);
        SqlPara sqlPara = Db.getSqlPara("file.countByApp_id", sqlMap);

        logSql("file", "countByApp_id", sqlPara);

        Number count = Db.queryFirst(sqlPara.getSql(), sqlPara.getPara());
        return count.intValue();
    }

    public Integer countByOrApp_id(String app_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(File.APP_ID, app_id);
        SqlPara sqlPara = Db.getSqlPara("file.countByOrApp_id", sqlMap);

        logSql("file", "countByOrApp_id", sqlPara);

        Number count = Db.queryFirst(sqlPara.getSql(), sqlPara.getPara());
        return count.intValue();
    }

    public Integer countByApp_idAndFile_typeAndSystem_create_user_id(String app_id, String file_type, String system_create_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(File.APP_ID, app_id);
        sqlMap.put(File.FILE_TYPE, file_type);
        sqlMap.put(File.SYSTEM_CREATE_USER_ID, system_create_user_id);
        SqlPara sqlPara = Db.getSqlPara("file.countByApp_idAndFile_typeAndSystem_create_user_id", sqlMap);

        logSql("file", "countByApp_idAndFile_typeAndSystem_create_user_id", sqlPara);

        Number count = Db.queryFirst(sqlPara.getSql(), sqlPara.getPara());
        return count.intValue();
    }

    public Integer countByOrApp_idAndFile_type(String app_id, String file_type) {
        Kv sqlMap = Kv.create();
        sqlMap.put(File.APP_ID, app_id);
        sqlMap.put(File.FILE_TYPE, file_type);
        SqlPara sqlPara = Db.getSqlPara("file.countByOrApp_idAndFile_type", sqlMap);

        logSql("file", "countByOrApp_idAndFile_type", sqlPara);

        Number count = Db.queryFirst(sqlPara.getSql(), sqlPara.getPara());
        return count.intValue();
    }

    public List<File> listByApp_idAndSystem_create_timeAndLimit(String app_id, Date system_create_time, int m, int n) {
        Kv sqlMap = Kv.create();
        sqlMap.put(File.APP_ID, app_id);
        sqlMap.put(File.SYSTEM_CREATE_TIME, system_create_time);
        sqlMap.put(Constant.M, m);
        sqlMap.put(Constant.N, n);
        SqlPara sqlPara = Db.getSqlPara("file.listByApp_idAndSystem_create_timeAndLimit", sqlMap);

        logSql("file", "listByApp_idAndSystem_create_timeAndLimit", sqlPara);

        return new File().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public List<File> listByApp_idAndLimit(String app_id, int m, int n) {
        Kv sqlMap = Kv.create();
        sqlMap.put(File.APP_ID, app_id);
        sqlMap.put(Constant.M, m);
        sqlMap.put(Constant.N, n);
        SqlPara sqlPara = Db.getSqlPara("file.listByApp_idAndLimit", sqlMap);

        logSql("file", "listByApp_idAndLimit", sqlPara);

        return new File().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public List<File> listByOrApp_idAndLimit(String app_id, int m, int n) {
        Kv sqlMap = Kv.create();
        sqlMap.put(File.APP_ID, app_id);
        sqlMap.put(Constant.M, m);
        sqlMap.put(Constant.N, n);
        SqlPara sqlPara = Db.getSqlPara("file.listByOrApp_idAndLimit", sqlMap);

        logSql("file", "listByOrApp_idAndLimit", sqlPara);

        return new File().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public List<File> listByApp_idAndFile_typeAndSystem_create_user_idAndLimit(String app_id, String file_type, String system_create_user_id, int m, int n) {
        Kv sqlMap = Kv.create();
        sqlMap.put(File.APP_ID, app_id);
        sqlMap.put(File.FILE_TYPE, file_type);
        sqlMap.put(File.SYSTEM_CREATE_USER_ID, system_create_user_id);
        sqlMap.put(Constant.M, m);
        sqlMap.put(Constant.N, n);
        SqlPara sqlPara = Db.getSqlPara("file.listByApp_idAndFile_typeAndSystem_create_user_idAndLimit", sqlMap);

        logSql("file", "listByApp_idAndFile_typeAndSystem_create_user_idAndLimit", sqlPara);

        return new File().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public List<File> listByOrApp_idAndFile_typeAndLimit(String app_id, String file_type, int m, int n) {
        Kv sqlMap = Kv.create();
        sqlMap.put(File.APP_ID, app_id);
        sqlMap.put(File.FILE_TYPE, file_type);
        sqlMap.put(Constant.M, m);
        sqlMap.put(Constant.N, n);
        SqlPara sqlPara = Db.getSqlPara("file.listByOrApp_idAndFile_typeAndLimit", sqlMap);

        logSql("file", "listByOrApp_idAndFile_typeAndLimit", sqlPara);

        return new File().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public File findByFile_id(String file_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(File.FILE_ID, file_id);
        SqlPara sqlPara = Db.getSqlPara("file.findByFile_id", sqlMap);

        logSql("file", "findByFile_id", sqlPara);

        List<File> fileList = new File().find(sqlPara.getSql(), sqlPara.getPara());
        if (fileList.size() == 0) {
            return null;
        } else {
            return fileList.get(0);
        }
    }

    public Boolean save(String file_id, String app_id, String file_type, String file_name, String file_suffix, Integer file_size, String file_path, String file_thumbnail_path, String file_original_path, String file_image, String system_create_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(File.FILE_ID, file_id);
        sqlMap.put(File.APP_ID, app_id);
        sqlMap.put(File.FILE_TYPE, file_type);
        sqlMap.put(File.FILE_NAME, file_name);
        sqlMap.put(File.FILE_SUFFIX, file_suffix);
        sqlMap.put(File.FILE_SIZE, file_size);
        sqlMap.put(File.FILE_PATH, file_path);
        sqlMap.put(File.FILE_THUMBNAIL_PATH, file_thumbnail_path);
        sqlMap.put(File.FILE_ORIGINAL_PATH, file_original_path);
        sqlMap.put(File.FILE_IMAGE, file_image);
        sqlMap.put(File.SYSTEM_CREATE_USER_ID, system_create_user_id);
        sqlMap.put(File.SYSTEM_CREATE_TIME, new Date());
        sqlMap.put(File.SYSTEM_UPDATE_USER_ID, system_create_user_id);
        sqlMap.put(File.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(File.SYSTEM_VERSION, 0);
        sqlMap.put(File.SYSTEM_STATUS, true);
        SqlPara sqlPara = Db.getSqlPara("file.save", sqlMap);

        logSql("file", "save", sqlPara);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

    public Boolean update(String file_id, String file_type, String file_name, String file_suffix, Integer file_size, String file_path, String file_thumbnail_path, String file_original_path, String file_image, String system_update_user_id, Integer system_version) {
        Kv sqlMap = Kv.create();
        sqlMap.put(File.FILE_ID, file_id);
        sqlMap.put(File.FILE_TYPE, file_type);
        sqlMap.put(File.FILE_NAME, file_name);
        sqlMap.put(File.FILE_SUFFIX, file_suffix);
        sqlMap.put(File.FILE_SIZE, file_size);
        sqlMap.put(File.FILE_PATH, file_path);
        sqlMap.put(File.FILE_THUMBNAIL_PATH, file_thumbnail_path);
        sqlMap.put(File.FILE_ORIGINAL_PATH, file_original_path);
        sqlMap.put(File.FILE_IMAGE, file_image);
        sqlMap.put(File.SYSTEM_UPDATE_USER_ID, system_update_user_id);
        sqlMap.put(File.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(File.SYSTEM_VERSION, system_version);
        SqlPara sqlPara = Db.getSqlPara("file.update", sqlMap);

        logSql("file", "update", sqlPara);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

    public Boolean deleteByFile_idAndSystem_version(String file_id, String system_update_user_id, Integer system_version) {
        Kv sqlMap = Kv.create();
        sqlMap.put(File.FILE_ID, file_id);
        sqlMap.put(File.SYSTEM_UPDATE_USER_ID, system_update_user_id);
        sqlMap.put(File.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(File.SYSTEM_VERSION, system_version);
        SqlPara sqlPara = Db.getSqlPara("file.deleteByFile_idAndSystem_version", sqlMap);

        logSql("file", "deleteByFile_idAndSystem_version", sqlPara);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

}