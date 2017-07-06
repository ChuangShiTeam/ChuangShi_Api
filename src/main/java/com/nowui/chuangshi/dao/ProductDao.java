package com.nowui.chuangshi.dao;

import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.SqlPara;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.model.Product;

import java.util.Date;
import java.util.List;

public class ProductDao extends Dao {

    public Integer countByApp_idOrLikeProduct_name(String app_id, String product_name) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Product.APP_ID, app_id);
        sqlMap.put(Product.PRODUCT_NAME, product_name);
        SqlPara sqlPara = Db.getSqlPara("product.countByApp_idOrLikeProduct_name", sqlMap);

        logSql("product", "countByApp_idOrLikeProduct_name", sqlPara);

        Number count = Db.queryFirst(sqlPara.getSql(), sqlPara.getPara());
        return count.intValue();
    }

    public Integer countByOrApp_idOrLikeProduct_name(String app_id, String product_name) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Product.APP_ID, app_id);
        sqlMap.put(Product.PRODUCT_NAME, product_name);
        SqlPara sqlPara = Db.getSqlPara("product.countByOrApp_idOrLikeProduct_name", sqlMap);

        logSql("product", "countByOrApp_idOrLikeProduct_name", sqlPara);

        Number count = Db.queryFirst(sqlPara.getSql(), sqlPara.getPara());
        return count.intValue();
    }

    public List<Product> listByApp_idAndSystem_create_timeAndLimit(String app_id, Date system_create_time, int m, int n) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Product.APP_ID, app_id);
        sqlMap.put(Product.SYSTEM_CREATE_TIME, system_create_time);
        sqlMap.put(Constant.M, m);
        sqlMap.put(Constant.N, n);
        SqlPara sqlPara = Db.getSqlPara("product.listByApp_idAndSystem_create_timeAndLimit", sqlMap);

        logSql("product", "listByApp_idAndSystem_create_timeAndLimit", sqlPara);

        return new Product().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public List<Product> listByApp_idOrLikeProduct_nameAndLimit(String app_id, String product_name, int m, int n) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Product.APP_ID, app_id);
        sqlMap.put(Product.PRODUCT_NAME, product_name);
        sqlMap.put(Constant.M, m);
        sqlMap.put(Constant.N, n);
        SqlPara sqlPara = Db.getSqlPara("product.listByApp_idOrLikeProduct_nameAndLimit", sqlMap);

        logSql("product", "listByApp_idOrLikeProduct_nameAndLimit", sqlPara);

        return new Product().find(sqlPara.getSql(), sqlPara.getPara());
    }
    
    public List<Product> listByApp_id(String app_id) {
    	Kv sqlMap = Kv.create();
        sqlMap.put(Product.APP_ID, app_id);
        SqlPara sqlPara = Db.getSqlPara("product.listByApp_id", sqlMap);

        logSql("product", "listByApp_id", sqlPara);

        return new Product().find(sqlPara.getSql(), sqlPara.getPara());
    }
    
    public List<Product> listByOrApp_id(String app_id) {
    	Kv sqlMap = Kv.create();
    	sqlMap.put(Product.APP_ID, app_id);
    	SqlPara sqlPara = Db.getSqlPara("product.listByOrApp_id", sqlMap);
    	
    	logSql("product", "listByOrApp_id", sqlPara);
    	
    	return new Product().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public List<Product> listByOrApp_idOrLikeProduct_nameAndLimit(String app_id, String product_name, int m, int n) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Product.APP_ID, app_id);
        sqlMap.put(Product.PRODUCT_NAME, product_name);
        sqlMap.put(Constant.M, m);
        sqlMap.put(Constant.N, n);
        SqlPara sqlPara = Db.getSqlPara("product.listByOrApp_idOrLikeProduct_nameAndLimit", sqlMap);

        logSql("product", "listByOrApp_idOrLikeProduct_nameAndLimit", sqlPara);

        return new Product().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public Product findByProduct_id(String product_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Product.PRODUCT_ID, product_id);
        SqlPara sqlPara = Db.getSqlPara("product.findByProduct_id", sqlMap);

        logSql("product", "findByProduct_id", sqlPara);

        List<Product> productList = new Product().find(sqlPara.getSql(), sqlPara.getPara());
        if (productList.size() == 0) {
            return null;
        } else {
            return productList.get(0);
        }
    }

    public Boolean save(String product_id, String app_id, String product_snap_id, String product_category_id, String product_brand_id, String product_name, String product_image, Boolean product_is_new, Boolean product_is_recommend, Boolean product_is_bargain, Boolean product_is_hot, Boolean product_is_sold_out, Boolean product_is_virtual, String product_content, Boolean product_status, String system_create_user_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Product.PRODUCT_ID, product_id);
        sqlMap.put(Product.APP_ID, app_id);
        sqlMap.put(Product.PRODUCT_SNAP_ID, product_snap_id);
        sqlMap.put(Product.PRODUCT_CATEGORY_ID, product_category_id);
        sqlMap.put(Product.PRODUCT_BRAND_ID, product_brand_id);
        sqlMap.put(Product.PRODUCT_NAME, product_name);
        sqlMap.put(Product.PRODUCT_IMAGE, product_image);
        sqlMap.put(Product.PRODUCT_IS_NEW, product_is_new);
        sqlMap.put(Product.PRODUCT_IS_RECOMMEND, product_is_recommend);
        sqlMap.put(Product.PRODUCT_IS_BARGAIN, product_is_bargain);
        sqlMap.put(Product.PRODUCT_IS_HOT, product_is_hot);
        sqlMap.put(Product.PRODUCT_IS_SOLD_OUT, product_is_sold_out);
        sqlMap.put(Product.PRODUCT_IS_VIRTUAL, product_is_virtual);
        sqlMap.put(Product.PRODUCT_CONTENT, product_content);
        sqlMap.put(Product.PRODUCT_STATUS, product_status);
        sqlMap.put(Product.SYSTEM_CREATE_USER_ID, system_create_user_id);
        sqlMap.put(Product.SYSTEM_CREATE_TIME, new Date());
        sqlMap.put(Product.SYSTEM_UPDATE_USER_ID, system_create_user_id);
        sqlMap.put(Product.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(Product.SYSTEM_VERSION, 0);
        sqlMap.put(Product.SYSTEM_STATUS, true);
        SqlPara sqlPara = Db.getSqlPara("product.save", sqlMap);

        logSql("product", "save", sqlPara);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

    public Boolean update(String product_id, String product_category_id, String product_brand_id, String product_name, String product_image, Boolean product_is_new, Boolean product_is_recommend, Boolean product_is_bargain, Boolean product_is_hot, Boolean product_is_sold_out, Boolean product_is_virtual, String product_content, Boolean product_status, String system_update_user_id, Integer system_version) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Product.PRODUCT_ID, product_id);
        sqlMap.put(Product.PRODUCT_CATEGORY_ID, product_category_id);
        sqlMap.put(Product.PRODUCT_BRAND_ID, product_brand_id);
        sqlMap.put(Product.PRODUCT_NAME, product_name);
        sqlMap.put(Product.PRODUCT_IMAGE, product_image);
        sqlMap.put(Product.PRODUCT_IS_NEW, product_is_new);
        sqlMap.put(Product.PRODUCT_IS_RECOMMEND, product_is_recommend);
        sqlMap.put(Product.PRODUCT_IS_BARGAIN, product_is_bargain);
        sqlMap.put(Product.PRODUCT_IS_HOT, product_is_hot);
        sqlMap.put(Product.PRODUCT_IS_SOLD_OUT, product_is_sold_out);
        sqlMap.put(Product.PRODUCT_IS_VIRTUAL, product_is_virtual);
        sqlMap.put(Product.PRODUCT_CONTENT, product_content);
        sqlMap.put(Product.PRODUCT_STATUS, product_status);
        sqlMap.put(Product.SYSTEM_UPDATE_USER_ID, system_update_user_id);
        sqlMap.put(Product.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(Product.SYSTEM_VERSION, system_version);
        SqlPara sqlPara = Db.getSqlPara("product.update", sqlMap);

        logSql("product", "update", sqlPara);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

    public Boolean deleteByProduct_idAndSystem_version(String product_id, String system_update_user_id, Integer system_version) {
        Kv sqlMap = Kv.create();
        sqlMap.put(Product.PRODUCT_ID, product_id);
        sqlMap.put(Product.SYSTEM_UPDATE_USER_ID, system_update_user_id);
        sqlMap.put(Product.SYSTEM_UPDATE_TIME, new Date());
        sqlMap.put(Product.SYSTEM_VERSION, system_version);
        SqlPara sqlPara = Db.getSqlPara("product.deleteByProduct_idAndSystem_version", sqlMap);

        logSql("product", "deleteByProduct_idAndSystem_version", sqlPara);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

}