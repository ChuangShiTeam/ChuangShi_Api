package com.nowui.chuangshi.model;

import com.nowui.chuangshi.constant.Column;
import com.nowui.chuangshi.type.ColumnType;

public class ProductBrand extends Model<ProductBrand> {

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "品牌编号")
    public static final String PRODUCT_BRAND_ID = "product_brand_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "应用编号")
    public static final String APP_ID = "app_id";

    @Column(type = ColumnType.VARCHAR, length = 20, comment = "品牌名称")
    public static final String PRODUCT_BRAND_NAME = "product_brand_name";

    @Column(type = ColumnType.VARCHAR, length = 100, comment = "品牌图片")
    public static final String PRODUCT_BRAND_IMAGE = "product_brand_image";

    @Column(type = ColumnType.VARCHAR, length = 200, comment = "品牌内容")
    public static final String PRODUCT_BRAND_CONTENT = "product_brand_content";

    public String getProduct_brand_id() {
        return getStr(PRODUCT_BRAND_ID);
    }

    public void setProduct_brand_id(String product_brand_id) {
        set(PRODUCT_BRAND_ID, product_brand_id);
    }

    public String getApp_id() {
        return getStr(APP_ID);
    }

    public void setApp_id(String app_id) {
        set(APP_ID, app_id);
    }

    public String getProduct_brand_name() {
        return getStr(PRODUCT_BRAND_NAME);
    }

    public void setProduct_brand_name(String product_brand_name) {
        set(PRODUCT_BRAND_NAME, product_brand_name);
    }

    public String getProduct_brand_image() {
        return getStr(PRODUCT_BRAND_IMAGE);
    }

    public void setProduct_brand_image(String product_brand_image) {
        set(PRODUCT_BRAND_IMAGE, product_brand_image);
    }

    public String getProduct_brand_content() {
        return getStr(PRODUCT_BRAND_CONTENT);
    }

    public void setProduct_brand_content(String product_brand_content) {
        set(PRODUCT_BRAND_CONTENT, product_brand_content);
    }

}