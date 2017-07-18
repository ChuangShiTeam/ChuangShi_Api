package com.nowui.chuangshi.model;

import com.nowui.chuangshi.constant.Column;
import com.nowui.chuangshi.type.ColumnType;

public class ProductImage extends Model<ProductImage> {

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "商品编号")
    public static final String PRODUCT_ID = "product_id";

    @Column(type = ColumnType.VARCHAR, length = 32, comment = "文件编号")
    public static final String FILE_ID = "file_id";

    @Column(type = ColumnType.INT, length = 3, comment = "文件排序")
    public static final String PRODUCT_FILE_SORT = "product_file_sort";

    public String getProduct_id() {
        return getStr(PRODUCT_ID);
    }

    public void setProduct_id(String product_id) {
        set(PRODUCT_ID, product_id);
    }

    public String getFile_id() {
        return getStr(FILE_ID);
    }

    public void setFile_id(String file_id) {
        set(FILE_ID, file_id);
    }

    public Integer getProduct_file_sort() {
        return getInt(PRODUCT_FILE_SORT);
    }

    public void setProduct_file_sort(Integer product_file_sort) {
        set(PRODUCT_FILE_SORT, product_file_sort);
    }

}