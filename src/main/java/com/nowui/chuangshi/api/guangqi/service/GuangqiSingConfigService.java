package com.nowui.chuangshi.api.guangqi.service;

import com.nowui.chuangshi.api.guangqi.dao.GuangqiSingConfigDao;
import com.nowui.chuangshi.api.guangqi.model.GuangqiSingConfig;
import com.nowui.chuangshi.common.service.Service;
import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.util.CacheUtil;

import java.util.List;

public class GuangqiSingConfigService extends Service {

    public static final GuangqiSingConfigService instance = new GuangqiSingConfigService();
    private final String GUANGQI_SING_CONFIG_ITEM_CACHE = "guangqi_sing_config_item_cache";
    private final GuangqiSingConfigDao guangqiSingConfigDao = new GuangqiSingConfigDao();

    public Integer adminCount(String app_id) {
        Cnd cnd = new Cnd();
        cnd.where(GuangqiSingConfig.SYSTEM_STATUS, true);
        cnd.and(GuangqiSingConfig.APP_ID, app_id);

        Integer count = guangqiSingConfigDao.count(cnd);
        return count;
    }

    public List<GuangqiSingConfig> adminList(String app_id, Integer m, Integer n) {
        Cnd cnd = new Cnd();
        cnd.where(GuangqiSingConfig.SYSTEM_STATUS, true);
        cnd.and(GuangqiSingConfig.APP_ID, app_id);
        cnd.desc(GuangqiSingConfig.SYSTEM_CREATE_TIME);
        cnd.paginate(m, n);

        List<GuangqiSingConfig> guangqi_sing_configList = guangqiSingConfigDao.primaryKeyList(cnd);
        for (GuangqiSingConfig guangqi_sing_config : guangqi_sing_configList) {
            guangqi_sing_config.put(find(guangqi_sing_config.getSing_config_id()));
        }
        return guangqi_sing_configList;
    }
    
    public List<GuangqiSingConfig> appList(String app_id) {
        Cnd cnd = new Cnd();
        cnd.where(GuangqiSingConfig.SYSTEM_STATUS, true);
        cnd.and(GuangqiSingConfig.APP_ID, app_id);
        cnd.desc(GuangqiSingConfig.SYSTEM_CREATE_TIME);

        List<GuangqiSingConfig> guangqi_sing_configList = guangqiSingConfigDao.primaryKeyList(cnd);
        for (GuangqiSingConfig guangqi_sing_config : guangqi_sing_configList) {
            guangqi_sing_config.put(find(guangqi_sing_config.getSing_config_id()));
        }
        return guangqi_sing_configList;
    }
    
    public GuangqiSingConfig appFind(String app_id) {
    	List<GuangqiSingConfig> guangqi_sing_configList = appList(app_id);
    	
    	if (guangqi_sing_configList == null || guangqi_sing_configList.size() == 0) {
    		return null;
    	}
    	
    	return guangqi_sing_configList.get(0);
    }

    public GuangqiSingConfig find(String sing_config_id) {
        GuangqiSingConfig guangqi_sing_config = CacheUtil.get(GUANGQI_SING_CONFIG_ITEM_CACHE, sing_config_id);

        if (guangqi_sing_config == null) {
            guangqi_sing_config = guangqiSingConfigDao.find(sing_config_id);

            CacheUtil.put(GUANGQI_SING_CONFIG_ITEM_CACHE, sing_config_id, guangqi_sing_config);
        }

        return guangqi_sing_config;
    }

    public Boolean save(GuangqiSingConfig guangqi_sing_config, String system_create_user_id) {
        Boolean success = guangqiSingConfigDao.save(guangqi_sing_config, system_create_user_id);
        return success;
    }

    public Boolean update(GuangqiSingConfig guangqi_sing_config, String sing_config_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(GuangqiSingConfig.SYSTEM_STATUS, true);
        cnd.and(GuangqiSingConfig.SING_CONFIG_ID, sing_config_id);
        cnd.and(GuangqiSingConfig.SYSTEM_VERSION, system_version);

        Boolean success = guangqiSingConfigDao.update(guangqi_sing_config, system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(GUANGQI_SING_CONFIG_ITEM_CACHE, sing_config_id);
        }

        return success;
    }

    public Boolean delete(String sing_config_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(GuangqiSingConfig.SYSTEM_STATUS, true);
        cnd.and(GuangqiSingConfig.SING_CONFIG_ID, sing_config_id);
        cnd.and(GuangqiSingConfig.SYSTEM_VERSION, system_version);

        Boolean success = guangqiSingConfigDao.delete(system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(GUANGQI_SING_CONFIG_ITEM_CACHE, sing_config_id);
        }

        return success;
    }

}