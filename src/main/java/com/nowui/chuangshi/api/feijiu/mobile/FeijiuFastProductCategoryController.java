package com.nowui.chuangshi.api.feijiu.mobile;

import java.util.List;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.feijiu.model.FeijiuFastProductCategory;
import com.nowui.chuangshi.api.feijiu.service.FeijiuFastProductCategoryService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.common.sql.Cnd;

@ControllerKey("/mobile/feijiu/fast/product/category")
public class FeijiuFastProductCategoryController extends Controller {

    private final FeijiuFastProductCategoryService feijiuFastProductCategoryService = new FeijiuFastProductCategoryService();

    @ActionKey("/mobile/feijiu/fast/product/category/list")
    public void list() {

        renderSuccessJson();
    }
    
    @ActionKey("/mobile/feijiu/fast/product/category/list/all")
    public void listAll() {
        FeijiuFastProductCategory model = getModel(FeijiuFastProductCategory.class);
        Cnd cnd = Cnd.where(FeijiuFastProductCategory.APP_ID, model.getApp_id());

        List<FeijiuFastProductCategory> resultList = feijiuFastProductCategoryService.list(cnd);

        validateResponse(FeijiuFastProductCategory.PRODUCT_CATEGORY_ID, FeijiuFastProductCategory.PRODUCT_CATEGORY_NAME);

        renderSuccessJson(resultList);

    }

    @ActionKey("/mobile/feijiu/fast/product/category/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/feijiu/fast/product/category/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/feijiu/fast/product/category/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/feijiu/fast/product/category/delete")
    public void delete() {

        renderSuccessJson();
    }

}