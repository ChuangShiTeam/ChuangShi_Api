package com.nowui.chuangshi.api.product.admin;


import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.product.model.ProductCategorySkuAttribute;
import com.nowui.chuangshi.api.product.service.ProductCategorySkuAttributeService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;

import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.util.Util;

import java.util.List;


@ControllerKey("/admin/product/category/sku/attribute")
public class ProductCategorySkuAttributeController extends Controller {

    @ActionKey("/admin/product/category/sku/attribute/list")
    public void list() {
        validateRequest(ProductCategorySkuAttribute.PRODUCT_CATEGORY_ID, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        ProductCategorySkuAttribute model = getModel(ProductCategorySkuAttribute.class);

        Integer resultCount = ProductCategorySkuAttributeService.instance.adminCount(model.getProduct_category_id());
        List<ProductCategorySkuAttribute> resultList = ProductCategorySkuAttributeService.instance.adminList(model.getProduct_category_id(), getM(), getN());

        validateResponse(ProductCategorySkuAttribute.PRODUCT_CATEGORY_SKU_ATTRIBUTE_ID, ProductCategorySkuAttribute.PRODUCT_CATEGORY_ID, ProductCategorySkuAttribute.PRODUCT_CATEGORY_SKU_ATTRIBUTE_NAME, ProductCategorySkuAttribute.PRODUCT_CATEGORY_SKU_ATTRIBUTE_SORT, ProductCategorySkuAttribute.SYSTEM_VERSION);

        renderSuccessJson(resultCount, resultList);
    }

    @ActionKey("/admin/product/category/sku/attribute/find")
    public void find() {
        validateRequest(ProductCategorySkuAttribute.PRODUCT_CATEGORY_SKU_ATTRIBUTE_ID);

        ProductCategorySkuAttribute model = getModel(ProductCategorySkuAttribute.class);

        ProductCategorySkuAttribute result = ProductCategorySkuAttributeService.instance.find(model.getProduct_category_sku_attribute_id());

        validateResponse(ProductCategorySkuAttribute.PRODUCT_CATEGORY_ID, ProductCategorySkuAttribute.PRODUCT_CATEGORY_SKU_ATTRIBUTE_NAME, ProductCategorySkuAttribute.PRODUCT_CATEGORY_SKU_ATTRIBUTE_SORT, ProductCategorySkuAttribute.SYSTEM_VERSION);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/product/category/sku/attribute/save")
    public void save() {
        validateRequest(ProductCategorySkuAttribute.PRODUCT_CATEGORY_ID, ProductCategorySkuAttribute.PRODUCT_CATEGORY_SKU_ATTRIBUTE_NAME, ProductCategorySkuAttribute.PRODUCT_CATEGORY_SKU_ATTRIBUTE_SORT);

        ProductCategorySkuAttribute model = getModel(ProductCategorySkuAttribute.class);
        model.setProduct_category_sku_attribute_id(Util.getRandomUUID());
        String request_user_id = getRequest_user_id();

        Boolean result = ProductCategorySkuAttributeService.instance.save(model, request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/product/category/sku/attribute/update")
    public void update() {
        validateRequest(ProductCategorySkuAttribute.PRODUCT_CATEGORY_SKU_ATTRIBUTE_ID, ProductCategorySkuAttribute.PRODUCT_CATEGORY_ID, ProductCategorySkuAttribute.PRODUCT_CATEGORY_SKU_ATTRIBUTE_NAME, ProductCategorySkuAttribute.PRODUCT_CATEGORY_SKU_ATTRIBUTE_SORT, ProductCategorySkuAttribute.SYSTEM_VERSION);

        ProductCategorySkuAttribute model = getModel(ProductCategorySkuAttribute.class);
        String request_user_id = getRequest_user_id();

        Boolean result = ProductCategorySkuAttributeService.instance.update(model, model.getProduct_category_sku_attribute_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey("/admin/product/category/sku/attribute/delete")
    public void delete() {
        validateRequest(ProductCategorySkuAttribute.PRODUCT_CATEGORY_SKU_ATTRIBUTE_ID, ProductCategorySkuAttribute.SYSTEM_VERSION);

        ProductCategorySkuAttribute model = getModel(ProductCategorySkuAttribute.class);
        String request_user_id = getRequest_user_id();

        Boolean result = ProductCategorySkuAttributeService.instance.delete(model.getProduct_category_sku_attribute_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

}