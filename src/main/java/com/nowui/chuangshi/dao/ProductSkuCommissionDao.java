package com.nowui.chuangshi.dao;

import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.SqlPara;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.model.ProductSkuCommission;
import com.nowui.chuangshi.util.Util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProductSkuCommissionDao extends Dao {

    public List<ProductSkuCommission> listByProduct_sku_id(String product_sku_id) {
        Kv sqlMap = Kv.create();
        sqlMap.put(ProductSkuCommission.PRODUCT_SKU_ID, product_sku_id);
        SqlPara sqlPara = Db.getSqlPara("product_sku_commission.listByProduct_sku_id", sqlMap);

        logSql("product_sku_commission", "listByProduct_sku_id", sqlPara);

        return new ProductSkuCommission().find(sqlPara.getSql(), sqlPara.getPara());
    }

    public Boolean save(List<ProductSkuCommission> productSkuCommissionList, String system_create_user_id) {
        if (productSkuCommissionList.size() == 0) {
            return false;
        }

        Kv map = Kv.create();
        SqlPara sqlPara = Db.getSqlPara("product_sku_commission.save", map);

        List<Object[]> parameterList = new ArrayList<Object[]>();
        for(ProductSkuCommission productSkuCommission : productSkuCommissionList) {
            List<Object> objectList = new ArrayList<Object>();
            objectList.add(productSkuCommission.getProduct_sku_id());
            objectList.add(productSkuCommission.getMember_level_id());
            objectList.add(productSkuCommission.getMember_level_name());
            objectList.add(productSkuCommission.getProduct_sku_commission());
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
                throw new RuntimeException("SKU佣金保存不成功");
            }
        }

        logSql("product_sku_commission", "save", sqlPara);

        return true;
    }

    public Boolean delete(List<String> productSkuIdList, String system_update_user_id) {
        if (productSkuIdList.size() == 0) {
            return false;
        }

        Kv map = Kv.create();
        SqlPara sqlPara = Db.getSqlPara("product_sku_commission.delete", map);

        List<Object[]> parameterList = new ArrayList<Object[]>();
        for(String product_sku_id : productSkuIdList) {
            List<Object> objectList = new ArrayList<Object>();
            objectList.add(system_update_user_id);
            objectList.add(new Date());
            objectList.add(product_sku_id);
            parameterList.add(objectList.toArray());
        }

        int[] result = Db.batch(sqlPara.getSql(), Util.getObjectArray(parameterList), Constant.BATCH_SIZE);

        for (int i : result) {
            if (i == 0) {
                throw new RuntimeException("SKU佣金删除不成功");
            }
        }

        logSql("product_sku_commission", "delete", sqlPara);

        return true;
    }

}