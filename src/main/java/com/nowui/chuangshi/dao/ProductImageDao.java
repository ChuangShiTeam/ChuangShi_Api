package com.nowui.chuangshi.dao;

import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.SqlPara;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.model.Product;
import com.nowui.chuangshi.model.ProductImage;
import com.nowui.chuangshi.model.ProductSkuCommission;
import com.nowui.chuangshi.util.Util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProductImageDao extends Dao {

    public List<ProductImage> listByProduct_id(String product_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(ProductImage.PRODUCT_ID, product_id);
        SqlPara sqlPara = Db.getSqlPara("product_image.listByProduct_id", sqlMap);

        logSql("product_image", "listByProduct_id", sqlPara);

        return new ProductImage().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public Boolean save(List<ProductImage> productImageList, String system_create_user_id) {
        if (productImageList.size() == 0) {
            return false;
        }

        Kv map = Kv.create();
        SqlPara sqlPara = Db.getSqlPara("product_image.save", map);

        List<Object[]> parameterList = new ArrayList<Object[]>();
        for(ProductImage productImage : productImageList) {
            List<Object> objectList = new ArrayList<Object>();
            objectList.add(productImage.getProduct_id());
            objectList.add(productImage.getFile_id());
            objectList.add(productImage.getProduct_file_sort());
            objectList.add(system_create_user_id);
            objectList.add(new Date());
            objectList.add(system_create_user_id);
            objectList.add(new Date());
            objectList.add(0);
            objectList.add(true);
            parameterList.add(objectList.toArray());
        }

        int[] result = Db.batch(sqlPara.getSql(), Util.getObjectArray(parameterList), Constant.BATCH_SIZE);

        for (int i : result) {
            if (i == 0) {
                throw new RuntimeException("图片保存不成功");
            }
        }

        logSql("product_image", "save", sqlPara);

        return true;
    }

    public Boolean update(List<ProductImage> productImageList, String system_update_user_id) {
        for(ProductImage productImage : productImageList) {
            productImage.setSystem_update_user_id(system_update_user_id);
            productImage.setSystem_update_time(new Date());
        }

        Kv sqlMap = Kv.create();
        sqlMap.put(Product.PRODUCT_IMAGE_LIST, productImageList);
        SqlPara sqlPara = Db.getSqlPara("product_image.update", sqlMap);

        logSql("product_image", "update", sqlPara);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

    public Boolean delete(List<ProductImage> productImageList, String system_update_user_id) {
        for(ProductImage productImage : productImageList) {
            productImage.setSystem_update_user_id(system_update_user_id);
            productImage.setSystem_update_time(new Date());
        }

        Kv sqlMap = Kv.create();
        sqlMap.put(Product.PRODUCT_IMAGE_LIST, productImageList);
        SqlPara sqlPara = Db.getSqlPara("product_image.delete", sqlMap);

        logSql("product_image", "delete", sqlPara);

        return Db.update(sqlPara.getSql(), sqlPara.getPara()) != 0;
    }

}