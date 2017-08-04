package com.nowui.chuangshi.api.product.admin;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.product.model.Product;
import com.nowui.chuangshi.api.product.service.ProductService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.common.interceptor.AdminInterceptor;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.util.Util;

import java.util.List;

@Before(AdminInterceptor.class)
@ControllerKey("/admin/product")
public class ProductController extends Controller {

    private final ProductService productService = ProductService.me;

    @ActionKey("/admin/product/list")
    public void list() {
        validateRequest(Product.PRODUCT_CATEGORY_ID, Product.PRODUCT_BRAND_ID, Product.PRODUCT_NAME, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        Product model = getModel(Product.class);
        model.where(Product.APP_ID).andEmpty(Product.PRODUCT_CATEGORY_ID).andEmpty(Product.PRODUCT_BRAND_ID).andEmpty(Product.PRODUCT_NAME);

        Integer resultCount = productService.count(model);
        List<Product> resultList = productService.list(model.paginate());

        validateResponse(Product.PRODUCT_ID, Product.PRODUCT_CATEGORY_ID, Product.PRODUCT_BRAND_ID, Product.PRODUCT_NAME, Product.PRODUCT_IMAGE, Product.PRODUCT_STATUS, Product.SYSTEM_VERSION);

        renderSuccessModeListlJson(resultCount, resultList);
    }

    @ActionKey("/admin/product/find")
    public void find() {
        validateRequest(Product.PRODUCT_ID);

        Product model = getModel(Product.class);
        model.where(Product.PRODUCT_ID);

        Product result = productService.find(model);

        validateResponse(Product.PRODUCT_SNAP_ID, Product.PRODUCT_CATEGORY_ID, Product.PRODUCT_BRAND_ID, Product.PRODUCT_NAME, Product.PRODUCT_IMAGE, Product.PRODUCT_IS_NEW, Product.PRODUCT_IS_RECOMMEND, Product.PRODUCT_IS_BARGAIN, Product.PRODUCT_IS_HOT, Product.PRODUCT_IS_SOLD_OUT, Product.PRODUCT_IS_VIRTUAL, Product.PRODUCT_CONTENT, Product.PRODUCT_STATUS, Product.SYSTEM_VERSION);

        renderSuccessModelJson(result);
    }

    @ActionKey("/admin/product/save")
    public void save() {
        validateRequest(Product.PRODUCT_SNAP_ID, Product.PRODUCT_CATEGORY_ID, Product.PRODUCT_BRAND_ID, Product.PRODUCT_NAME, Product.PRODUCT_IMAGE, Product.PRODUCT_IS_NEW, Product.PRODUCT_IS_RECOMMEND, Product.PRODUCT_IS_BARGAIN, Product.PRODUCT_IS_HOT, Product.PRODUCT_IS_SOLD_OUT, Product.PRODUCT_IS_VIRTUAL, Product.PRODUCT_CONTENT, Product.PRODUCT_STATUS);

        Product model = getModel(Product.class);
        model.setProduct_id(Util.getRandomUUID());

        Boolean result = productService.save(model);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/product/update")
    public void update() {
        validateRequest(Product.PRODUCT_ID, Product.PRODUCT_SNAP_ID, Product.PRODUCT_CATEGORY_ID, Product.PRODUCT_BRAND_ID, Product.PRODUCT_NAME, Product.PRODUCT_IMAGE, Product.PRODUCT_IS_NEW, Product.PRODUCT_IS_RECOMMEND, Product.PRODUCT_IS_BARGAIN, Product.PRODUCT_IS_HOT, Product.PRODUCT_IS_SOLD_OUT, Product.PRODUCT_IS_VIRTUAL, Product.PRODUCT_CONTENT, Product.PRODUCT_STATUS, Product.SYSTEM_VERSION);

        Product model = getModel(Product.class);
        model.where(model.PRODUCT_ID).and(Product.SYSTEM_VERSION);

        Boolean result = productService.update(model);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/product/delete")
    public void delete() {
        validateRequest(Product.PRODUCT_ID, Product.SYSTEM_VERSION);

        Product model = getModel(Product.class);
        model.where(model.PRODUCT_ID).and(Product.SYSTEM_VERSION);

        Boolean result = productService.delete(model);

        renderSuccessJson(result);
    }

}