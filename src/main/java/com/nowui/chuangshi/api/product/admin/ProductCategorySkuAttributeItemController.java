package com.nowui.chuangshi.api.product.admin;


import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.product.model.ProductCategorySkuAttributeItem;
import com.nowui.chuangshi.api.product.service.ProductCategorySkuAttributeItemService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;

import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.util.Util;

import java.util.List;


@ControllerKey("/admin/product/category/sku/attribute/item")
public class ProductCategorySkuAttributeItemController extends Controller {

    @ActionKey("/admin/product/category/sku/attribute/item/list")
    public void list() {
        validateRequest(ProductCategorySkuAttributeItem.PRODUCT_CATEGORY_SKU_ATTRIBUTE_ID, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        ProductCategorySkuAttributeItem model = getModel(ProductCategorySkuAttributeItem.class);

        Integer resultCount = ProductCategorySkuAttributeItemService.instance.adminCount( model.getProduct_category_sku_attribute_id());
        List<ProductCategorySkuAttributeItem> resultList = ProductCategorySkuAttributeItemService.instance.adminList( model.getProduct_category_sku_attribute_id(), getM(), getN());

        validateResponse(ProductCategorySkuAttributeItem.PRODUCT_CATEGORY_SKU_ATTRIBUTE_ITEM_ID, ProductCategorySkuAttributeItem.PRODUCT_CATEGORY_SKU_ATTRIBUTE_ID, ProductCategorySkuAttributeItem.PRODUCT_CATEGORY_SKU_ATTRIBUTE_ITEM_NAME, ProductCategorySkuAttributeItem.PRODUCT_CATEGORY_SKU_ATTRIBUTE_ITEM_SORT, ProductCategorySkuAttributeItem.SYSTEM_VERSION);

        renderSuccessJson(resultCount, resultList);
    }

    @ActionKey("/admin/product/category/sku/attribute/item/find")
    public void find() {
        validateRequest(ProductCategorySkuAttributeItem.PRODUCT_CATEGORY_SKU_ATTRIBUTE_ITEM_ID);

        ProductCategorySkuAttributeItem model = getModel(ProductCategorySkuAttributeItem.class);

        ProductCategorySkuAttributeItem result = ProductCategorySkuAttributeItemService.instance.find(model.getProduct_category_sku_attribute_item_id());

        validateResponse(ProductCategorySkuAttributeItem.PRODUCT_CATEGORY_SKU_ATTRIBUTE_ID, ProductCategorySkuAttributeItem.PRODUCT_CATEGORY_SKU_ATTRIBUTE_ITEM_NAME, ProductCategorySkuAttributeItem.PRODUCT_CATEGORY_SKU_ATTRIBUTE_ITEM_SORT, ProductCategorySkuAttributeItem.SYSTEM_VERSION);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/product/category/sku/attribute/item/save")
    public void save() {
        validateRequest(ProductCategorySkuAttributeItem.PRODUCT_CATEGORY_SKU_ATTRIBUTE_ID, ProductCategorySkuAttributeItem.PRODUCT_CATEGORY_SKU_ATTRIBUTE_ITEM_NAME, ProductCategorySkuAttributeItem.PRODUCT_CATEGORY_SKU_ATTRIBUTE_ITEM_SORT);

        ProductCategorySkuAttributeItem model = getModel(ProductCategorySkuAttributeItem.class);
        model.setProduct_category_sku_attribute_item_id(Util.getRandomUUID());
        String request_user_id = getRequest_user_id();

        Boolean result = ProductCategorySkuAttributeItemService.instance.save(model, request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/product/category/sku/attribute/item/update")
    public void update() {
        validateRequest(ProductCategorySkuAttributeItem.PRODUCT_CATEGORY_SKU_ATTRIBUTE_ITEM_ID, ProductCategorySkuAttributeItem.PRODUCT_CATEGORY_SKU_ATTRIBUTE_ID, ProductCategorySkuAttributeItem.PRODUCT_CATEGORY_SKU_ATTRIBUTE_ITEM_NAME, ProductCategorySkuAttributeItem.PRODUCT_CATEGORY_SKU_ATTRIBUTE_ITEM_SORT, ProductCategorySkuAttributeItem.SYSTEM_VERSION);

        ProductCategorySkuAttributeItem model = getModel(ProductCategorySkuAttributeItem.class);
        String request_user_id = getRequest_user_id();

        Boolean result = ProductCategorySkuAttributeItemService.instance.update(model, model.getProduct_category_sku_attribute_item_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey("/admin/product/category/sku/attribute/item/delete")
    public void delete() {
        validateRequest(ProductCategorySkuAttributeItem.PRODUCT_CATEGORY_SKU_ATTRIBUTE_ITEM_ID, ProductCategorySkuAttributeItem.SYSTEM_VERSION);

        ProductCategorySkuAttributeItem model = getModel(ProductCategorySkuAttributeItem.class);
        String request_user_id = getRequest_user_id();

        Boolean result = ProductCategorySkuAttributeItemService.instance.delete(model.getProduct_category_sku_attribute_item_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

}