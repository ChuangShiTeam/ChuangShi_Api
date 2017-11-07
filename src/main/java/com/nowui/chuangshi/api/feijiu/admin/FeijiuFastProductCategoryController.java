package com.nowui.chuangshi.api.feijiu.admin;


import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.feijiu.model.FeijiuFastProductCategory;
import com.nowui.chuangshi.api.feijiu.service.FeijiuFastProductCategoryService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;

import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.util.Util;

import java.util.List;


@ControllerKey("/admin/feijiu/fast/product/category")
public class FeijiuFastProductCategoryController extends Controller {

    @ActionKey("/admin/feijiu/fast/product/category/list")
    public void list() {
        validateRequest(FeijiuFastProductCategory.PRODUCT_CATEGORY_NAME, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        FeijiuFastProductCategory model = getModel(FeijiuFastProductCategory.class);
        String request_app_id = getRequest_app_id();

        Integer resultCount = FeijiuFastProductCategoryService.instance.adminCount(request_app_id, model.getProduct_category_name());
        List<FeijiuFastProductCategory> resultList = FeijiuFastProductCategoryService.instance.adminList(request_app_id, model.getProduct_category_name(), getM(), getN());

        validateResponse(FeijiuFastProductCategory.PRODUCT_CATEGORY_ID, FeijiuFastProductCategory.PRODUCT_CATEGORY_NAME, FeijiuFastProductCategory.PRODUCT_CATEGORY_SORT_NUMBER, FeijiuFastProductCategory.SYSTEM_VERSION);

        renderSuccessJson(resultCount, resultList);
    }
    
    @ActionKey("/admin/feijiu/fast/product/category/list/all")
    public void listAll() {
        FeijiuFastProductCategory model = getModel(FeijiuFastProductCategory.class);
        String request_app_id = getRequest_app_id();

        List<FeijiuFastProductCategory> resultList = FeijiuFastProductCategoryService.instance.appList(request_app_id);

        validateResponse(FeijiuFastProductCategory.PRODUCT_CATEGORY_ID, FeijiuFastProductCategory.PRODUCT_CATEGORY_NAME);

        renderSuccessJson(resultList);
    }


    @ActionKey("/admin/feijiu/fast/product/category/find")
    public void find() {
        validateRequest(FeijiuFastProductCategory.PRODUCT_CATEGORY_ID);

        FeijiuFastProductCategory model = getModel(FeijiuFastProductCategory.class);

        FeijiuFastProductCategory result = FeijiuFastProductCategoryService.instance.find(model.getProduct_category_id());

        validateResponse(FeijiuFastProductCategory.PRODUCT_CATEGORY_NAME, FeijiuFastProductCategory.PRODUCT_CATEGORY_SORT_NUMBER, FeijiuFastProductCategory.SYSTEM_VERSION);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/feijiu/fast/product/category/save")
    public void save() {
        validateRequest(FeijiuFastProductCategory.PRODUCT_CATEGORY_NAME, FeijiuFastProductCategory.PRODUCT_CATEGORY_SORT_NUMBER);

        FeijiuFastProductCategory model = getModel(FeijiuFastProductCategory.class);
        model.setProduct_category_id(Util.getRandomUUID());
        String request_user_id = getRequest_user_id();

        Boolean result = FeijiuFastProductCategoryService.instance.save(model, request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/feijiu/fast/product/category/update")
    public void update() {
        validateRequest(FeijiuFastProductCategory.PRODUCT_CATEGORY_ID, FeijiuFastProductCategory.PRODUCT_CATEGORY_NAME, FeijiuFastProductCategory.PRODUCT_CATEGORY_SORT_NUMBER, FeijiuFastProductCategory.SYSTEM_VERSION);

        FeijiuFastProductCategory model = getModel(FeijiuFastProductCategory.class);
        String request_user_id = getRequest_user_id();

        Boolean result = FeijiuFastProductCategoryService.instance.update(model, model.getProduct_category_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey("/admin/feijiu/fast/product/category/delete")
    public void delete() {
        validateRequest(FeijiuFastProductCategory.PRODUCT_CATEGORY_ID, FeijiuFastProductCategory.SYSTEM_VERSION);

        FeijiuFastProductCategory model = getModel(FeijiuFastProductCategory.class);
        String request_user_id = getRequest_user_id();

        Boolean result = FeijiuFastProductCategoryService.instance.delete(model.getProduct_category_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

}