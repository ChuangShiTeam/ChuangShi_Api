package com.nowui.chuangshi.api.product.admin;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.file.model.File;
import com.nowui.chuangshi.api.product.model.ProductBrand;
import com.nowui.chuangshi.api.product.service.ProductBrandService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.common.interceptor.AdminInterceptor;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.util.Util;

import java.util.List;

@Before(AdminInterceptor.class)
@ControllerKey("/admin/product/brand")
public class ProductBrandController extends Controller {

    @ActionKey("/admin/product/brand/list")
    public void list() {
        validateRequest(ProductBrand.PRODUCT_BRAND_NAME, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        ProductBrand model = getModel(ProductBrand.class);
        String request_app_id = getRequest_app_id();

        Integer resultCount = ProductBrandService.instance.adminCount(request_app_id, model.getProduct_brand_name());
        List<ProductBrand> resultList = ProductBrandService.instance.adminList(request_app_id, model.getProduct_brand_name(), getM(), getN());

        validateResponse(ProductBrand.PRODUCT_BRAND_ID, ProductBrand.PRODUCT_BRAND_NAME, ProductBrand.SYSTEM_VERSION);

        renderSuccessJson(resultCount, resultList);
    }

    @ActionKey("/admin/product/brand/find")
    public void find() {
        validateRequest(ProductBrand.PRODUCT_BRAND_ID);

        ProductBrand model = getModel(ProductBrand.class);

        ProductBrand result = ProductBrandService.instance.find(model.getProduct_brand_id());

        validateResponse(ProductBrand.PRODUCT_BRAND_NAME, File.FILE_ID, File.FILE_PATH, ProductBrand.PRODUCT_BRAND_CONTENT, ProductBrand.SYSTEM_VERSION);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/product/brand/save")
    public void save() {
        validateRequest(ProductBrand.PRODUCT_BRAND_NAME, ProductBrand.PRODUCT_BRAND_IMAGE, ProductBrand.PRODUCT_BRAND_CONTENT);

        ProductBrand model = getModel(ProductBrand.class);
        model.setProduct_brand_id(Util.getRandomUUID());
        String request_user_id = getRequest_user_id();

        Boolean result = ProductBrandService.instance.save(model, request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/product/brand/update")
    public void update() {
        validateRequest(ProductBrand.PRODUCT_BRAND_ID, ProductBrand.PRODUCT_BRAND_NAME, ProductBrand.PRODUCT_BRAND_IMAGE, ProductBrand.PRODUCT_BRAND_CONTENT, ProductBrand.SYSTEM_VERSION);

        ProductBrand model = getModel(ProductBrand.class);
        String request_user_id = getRequest_user_id();

        Boolean result = ProductBrandService.instance.update(model, model.getProduct_brand_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey("/admin/product/brand/delete")
    public void delete() {
        validateRequest(ProductBrand.PRODUCT_BRAND_ID, ProductBrand.SYSTEM_VERSION);

        ProductBrand model = getModel(ProductBrand.class);
        String request_user_id = getRequest_user_id();

        Boolean result = ProductBrandService.instance.delete(model.getProduct_brand_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

}