package com.nowui.chuangshi.api.feijiu.admin;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.feijiu.model.FeijiuFastProductCategory;
import com.nowui.chuangshi.api.feijiu.service.FeijiuFastProductCategoryService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.common.interceptor.AdminInterceptor;
import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.util.Util;

import java.util.List;

@Before(AdminInterceptor.class)
@ControllerKey("/admin/feijiu/fast/product/category")
public class FeijiuFastProductCategoryController extends Controller {

    @ActionKey("/admin/feijiu/fast/product/category/list")
    public void list() {
        validateRequest(FeijiuFastProductCategory.PRODUCT_CATEGORY_NAME, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        FeijiuFastProductCategory model = getModel(FeijiuFastProductCategory.class);
        Cnd cnd = Cnd.where(FeijiuFastProductCategory.APP_ID, model.getApp_id()).andAllowEmpty(FeijiuFastProductCategory.PRODUCT_CATEGORY_NAME, model.getProduct_category_name());

        Integer resultCount = FeijiuFastProductCategoryService.me.count(cnd);
        List<FeijiuFastProductCategory> resultList = FeijiuFastProductCategoryService.me.list(cnd.paginate(getM(), getN()));

        validateResponse(FeijiuFastProductCategory.PRODUCT_CATEGORY_ID, FeijiuFastProductCategory.PRODUCT_CATEGORY_NAME, FeijiuFastProductCategory.PRODUCT_CATEGORY_SORT_NUMBER, FeijiuFastProductCategory.SYSTEM_VERSION);

        renderSuccessJson(resultCount, resultList);
    }

    @ActionKey("/admin/feijiu/fast/product/category/find")
    public void find() {
        validateRequest(FeijiuFastProductCategory.PRODUCT_CATEGORY_ID);

        FeijiuFastProductCategory model = getModel(FeijiuFastProductCategory.class);

        FeijiuFastProductCategory result = FeijiuFastProductCategoryService.me.findById(model.getProduct_category_id());

        validateResponse(FeijiuFastProductCategory.PRODUCT_CATEGORY_NAME, FeijiuFastProductCategory.PRODUCT_CATEGORY_CONTENT, FeijiuFastProductCategory.PRODUCT_CATEGORY_SORT_NUMBER, FeijiuFastProductCategory.SYSTEM_VERSION);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/feijiu/fast/product/category/save")
    public void save() {
        validateRequest(FeijiuFastProductCategory.PRODUCT_CATEGORY_NAME, FeijiuFastProductCategory.PRODUCT_CATEGORY_CONTENT, FeijiuFastProductCategory.PRODUCT_CATEGORY_SORT_NUMBER);

        FeijiuFastProductCategory model = getModel(FeijiuFastProductCategory.class);
        model.setProduct_category_id(Util.getRandomUUID());

        Boolean result = FeijiuFastProductCategoryService.me.save(model);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/feijiu/fast/product/category/update")
    public void update() {
        validateRequest(FeijiuFastProductCategory.PRODUCT_CATEGORY_ID, FeijiuFastProductCategory.PRODUCT_CATEGORY_NAME, FeijiuFastProductCategory.PRODUCT_CATEGORY_CONTENT, FeijiuFastProductCategory.PRODUCT_CATEGORY_SORT_NUMBER, FeijiuFastProductCategory.SYSTEM_VERSION);

        FeijiuFastProductCategory model = getModel(FeijiuFastProductCategory.class);

        Boolean result = FeijiuFastProductCategoryService.me.update(model, Cnd.where(model.PRODUCT_CATEGORY_ID, model.getProduct_category_id()).and(FeijiuFastProductCategory.SYSTEM_VERSION, model.getSystem_version()));

        renderSuccessJson(result);
    }

    @ActionKey("/admin/feijiu/fast/product/category/delete")
    public void delete() {
        validateRequest(FeijiuFastProductCategory.PRODUCT_CATEGORY_ID, FeijiuFastProductCategory.SYSTEM_VERSION);

        FeijiuFastProductCategory model = getModel(FeijiuFastProductCategory.class);

        Boolean result = FeijiuFastProductCategoryService.me.delete(model, Cnd.where(model.PRODUCT_CATEGORY_ID, model.getProduct_category_id()).and(FeijiuFastProductCategory.SYSTEM_VERSION, model.getSystem_version()));

        renderSuccessJson(result);
    }

}