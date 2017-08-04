package com.nowui.chuangshi.api.product.admin;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.file.service.FileService;
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

    private final ProductBrandService productBrandService = ProductBrandService.me;

    @ActionKey("/admin/product/brand/list")
    public void list() {
        validateRequest(ProductBrand.PRODUCT_BRAND_NAME, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        ProductBrand model = getModel(ProductBrand.class);
        model.where(ProductBrand.APP_ID).andEmpty(ProductBrand.PRODUCT_BRAND_NAME);

        Integer resultCount = productBrandService.count(model);
        List<ProductBrand> resultList = productBrandService.list(model.paginate());

        validateResponse(ProductBrand.PRODUCT_BRAND_ID, ProductBrand.PRODUCT_BRAND_NAME, ProductBrand.SYSTEM_VERSION);

        renderSuccessModeListlJson(resultCount, resultList);
    }

    @ActionKey("/admin/product/brand/find")
    public void find() {
        validateRequest(ProductBrand.PRODUCT_BRAND_ID);

        ProductBrand model = getModel(ProductBrand.class);
        model.where(ProductBrand.PRODUCT_BRAND_ID);

        ProductBrand result = productBrandService.find(model);

        result.put(ProductBrand.PRODUCT_BRAND_IMAGE_FILE, FileService.me.getFile(result.getProduct_brand_image()));

        validateResponse(ProductBrand.PRODUCT_BRAND_NAME, ProductBrand.PRODUCT_BRAND_IMAGE_FILE, ProductBrand.PRODUCT_BRAND_CONTENT, ProductBrand.SYSTEM_VERSION);

        renderSuccessModelJson(result);
    }

    @ActionKey("/admin/product/brand/save")
    public void save() {
        validateRequest(ProductBrand.PRODUCT_BRAND_NAME, ProductBrand.PRODUCT_BRAND_IMAGE, ProductBrand.PRODUCT_BRAND_CONTENT);

        ProductBrand model = getModel(ProductBrand.class);
        model.setProduct_brand_id(Util.getRandomUUID());

        Boolean result = productBrandService.save(model);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/product/brand/update")
    public void update() {
        validateRequest(ProductBrand.PRODUCT_BRAND_ID, ProductBrand.PRODUCT_BRAND_NAME, ProductBrand.PRODUCT_BRAND_IMAGE, ProductBrand.PRODUCT_BRAND_CONTENT, ProductBrand.SYSTEM_VERSION);

        ProductBrand model = getModel(ProductBrand.class);
        model.where(model.PRODUCT_BRAND_ID).and(ProductBrand.SYSTEM_VERSION);

        Boolean result = productBrandService.update(model);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/product/brand/delete")
    public void delete() {
        validateRequest(ProductBrand.PRODUCT_BRAND_ID, ProductBrand.SYSTEM_VERSION);

        ProductBrand model = getModel(ProductBrand.class);
        model.where(model.PRODUCT_BRAND_ID).and(ProductBrand.SYSTEM_VERSION);

        Boolean result = productBrandService.delete(model);

        renderSuccessJson(result);
    }

}