package com.nowui.chuangshi.api.product.admin;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.file.model.File;
import com.nowui.chuangshi.api.product.model.*;
import com.nowui.chuangshi.api.product.service.ProductService;
import com.nowui.chuangshi.api.product.service.ProductSkuCommissionService;
import com.nowui.chuangshi.api.product.service.ProductSkuPriceService;
import com.nowui.chuangshi.api.product.service.ProductSkuService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.common.interceptor.AdminInterceptor;
import com.nowui.chuangshi.util.Util;

import java.util.ArrayList;
import java.util.List;

@Before(AdminInterceptor.class)
@ControllerKey("/admin/product")
public class ProductController extends Controller {

    @ActionKey("/admin/product/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/admin/product/find")
    public void find() {
        validateRequest(Product.PRODUCT_ID);

        Product model = getModel(Product.class);

        Product product = ProductService.instance.find(model.getProduct_id());

        List<ProductSku> productSkuList = ProductSkuService.instance.productList(model.getProduct_id());
        for (ProductSku productSku : productSkuList) {
            productSku.keep(ProductSku.PRODUCT_SKU_ID, ProductSku.PRODUCT_SKU_IS_DEFAULT);

            List<ProductSkuPrice> productSkuPriceList = ProductSkuPriceService.instance.productSkuList(productSku.getProduct_sku_id());
            for (ProductSkuPrice productSkuPrice : productSkuPriceList) {
                productSkuPrice.keep(ProductSkuPrice.MEMBER_LEVEL_ID, ProductSkuPrice.PRODUCT_SKU_PRICE_AMOUNT);
            }
            productSku.put(ProductSku.PRODUCT_SKU_PRICE_LIST, productSkuPriceList);

            productSku.put(ProductSku.PRODUCT_SKU_ATTRIBUTE_LIST, new ArrayList<ProductSkuAttribute>());

            List<ProductSkuCommission> productSkuCommissionList = ProductSkuCommissionService.instance.productSkuList(productSku.getProduct_sku_id());
            for (ProductSkuCommission productSkuCommission : productSkuCommissionList) {
                productSkuCommission.keep(ProductSkuCommission.MEMBER_LEVEL_ID,
                        ProductSkuCommission.PRODUCT_SKU_COMMISSION_PERCENTAGE);
            }
            productSku.put(ProductSku.PRODUCT_SKU_COMMISSION_LIST, productSkuCommissionList);
        }
        product.put(Product.PRODUCT_SKU_LIST, productSkuList);

        validateResponse(Product.PRODUCT_ID,
                Product.PRODUCT_CATEGORY_ID,
                Product.PRODUCT_BRAND_ID,
                Product.PRODUCT_NAME,
                File.FILE_ID,
                File.FILE_PATH,
                Product.PRODUCT_IS_NEW,
                Product.PRODUCT_IS_RECOMMEND,
                Product.PRODUCT_IS_BARGAIN,
                Product.PRODUCT_IS_HOT,
                Product.PRODUCT_IS_SOLD_OUT,
                Product.PRODUCT_IS_VIRTUAL,
                Product.PRODUCT_CONTENT,
                Product.PRODUCT_STATUS,
                Product.PRODUCT_SKU_LIST,
                Product.SYSTEM_VERSION);

        renderSuccessJson(product);
    }

    @ActionKey("/admin/product/save")
    public void save() {
        validateRequest(Product.PRODUCT_CATEGORY_ID, Product.PRODUCT_BRAND_ID, Product.PRODUCT_NAME, Product.PRODUCT_IMAGE, Product.PRODUCT_IS_NEW, Product.PRODUCT_IS_RECOMMEND, Product.PRODUCT_IS_BARGAIN, Product.PRODUCT_IS_HOT, Product.PRODUCT_IS_SOLD_OUT, Product.PRODUCT_IS_VIRTUAL, Product.PRODUCT_CONTENT);

        Product model = getModel(Product.class);
        model.setProduct_id(Util.getRandomUUID());
        String request_user_id = getRequest_user_id();
        JSONObject jsonObject = getParameterJSONObject();
        JSONArray productSkuJSONArray = jsonObject.getJSONArray(Product.PRODUCT_SKU_LIST);

        Boolean result = ProductService.instance.save(model, request_user_id);

        if (result) {
            List<ProductSku> productSkuList = new ArrayList<ProductSku>();
            ProductService.instance.skuSaveOrUpdate(model.getProduct_id(), productSkuJSONArray, productSkuList, request_user_id);
        }

        renderSuccessJson(result);
    }

    @ActionKey("/admin/product/update")
    public void update() {
        validateRequest(Product.PRODUCT_ID, Product.PRODUCT_CATEGORY_ID, Product.PRODUCT_BRAND_ID, Product.PRODUCT_NAME, Product.PRODUCT_IMAGE, Product.PRODUCT_IS_NEW, Product.PRODUCT_IS_RECOMMEND, Product.PRODUCT_IS_BARGAIN, Product.PRODUCT_IS_HOT, Product.PRODUCT_IS_SOLD_OUT, Product.PRODUCT_IS_VIRTUAL, Product.PRODUCT_CONTENT, Product.PRODUCT_STATUS, Product.SYSTEM_VERSION);

        Product model = getModel(Product.class);
        String request_user_id = getRequest_user_id();
        JSONObject jsonObject = getParameterJSONObject();
        JSONArray productSkuJSONArray = jsonObject.getJSONArray(Product.PRODUCT_SKU_LIST);

        Boolean result = ProductService.instance.update(model, model.getProduct_id(), request_user_id, model.getSystem_version());

        if (result) {
            List<ProductSku> productSkuList = ProductSkuService.instance.productList(model.getProduct_id());
            ProductService.instance.skuSaveOrUpdate(model.getProduct_id(), productSkuJSONArray, productSkuList, request_user_id);
        }

        renderSuccessJson(result);
    }

    @ActionKey("/admin/product/delete")
    public void delete() {

        renderSuccessJson();
    }

}