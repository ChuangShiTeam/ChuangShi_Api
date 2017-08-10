package com.nowui.chuangshi.api.feijiu.admin;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.feijiu.model.FeijiuFastProduct;
import com.nowui.chuangshi.api.feijiu.service.FeijiuFastProductService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.common.interceptor.AdminInterceptor;
import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.util.Util;

import java.util.List;

@Before(AdminInterceptor.class)
@ControllerKey("/admin/feijiu/fast/product")
public class FeijiuFastProductController extends Controller {

    @ActionKey("/admin/feijiu/fast/product/list")
    public void list() {
        validateRequest(FeijiuFastProduct.PRODUCT_NAME, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        FeijiuFastProduct model = getModel(FeijiuFastProduct.class);
        Cnd cnd = Cnd.where(FeijiuFastProduct.APP_ID, model.getApp_id()).andAllowEmpty(FeijiuFastProduct.PRODUCT_NAME, model.getProduct_name());

        Integer resultCount = FeijiuFastProductService.me.count(cnd);
        List<FeijiuFastProduct> resultList = FeijiuFastProductService.me.list(cnd.paginate(getM(), getN()));

        validateResponse(FeijiuFastProduct.PRODUCT_ID, FeijiuFastProduct.PRODUCT_NAME, FeijiuFastProduct.PRODUCT_LINK, FeijiuFastProduct.PRODUCT_APPLICANT_QUANTITY, FeijiuFastProduct.SYSTEM_VERSION);

        renderSuccessJson(resultCount, resultList);
    }

    @ActionKey("/admin/feijiu/fast/product/find")
    public void find() {
        validateRequest(FeijiuFastProduct.PRODUCT_ID);

        FeijiuFastProduct model = getModel(FeijiuFastProduct.class);

        FeijiuFastProduct result = FeijiuFastProductService.me.findById(model.getProduct_id());

        validateResponse(FeijiuFastProduct.PRODUCT_CATEGORY_ID, FeijiuFastProduct.PRODUCT_NAME, FeijiuFastProduct.PRODUCT_IMAGE, FeijiuFastProduct.PRODUCT_LINK, FeijiuFastProduct.PRODUCT_CONTENT, FeijiuFastProduct.PRODUCT_APPLICANT_QUANTITY, FeijiuFastProduct.SYSTEM_VERSION);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/feijiu/fast/product/save")
    public void save() {
        validateRequest(FeijiuFastProduct.PRODUCT_CATEGORY_ID, FeijiuFastProduct.PRODUCT_NAME, FeijiuFastProduct.PRODUCT_IMAGE, FeijiuFastProduct.PRODUCT_LINK, FeijiuFastProduct.PRODUCT_CONTENT, FeijiuFastProduct.PRODUCT_APPLICANT_QUANTITY);

        FeijiuFastProduct model = getModel(FeijiuFastProduct.class);
        model.setProduct_id(Util.getRandomUUID());

        Boolean result = FeijiuFastProductService.me.save(model);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/feijiu/fast/product/update")
    public void update() {
        validateRequest(FeijiuFastProduct.PRODUCT_ID, FeijiuFastProduct.PRODUCT_CATEGORY_ID, FeijiuFastProduct.PRODUCT_NAME, FeijiuFastProduct.PRODUCT_IMAGE, FeijiuFastProduct.PRODUCT_LINK, FeijiuFastProduct.PRODUCT_CONTENT, FeijiuFastProduct.PRODUCT_APPLICANT_QUANTITY, FeijiuFastProduct.SYSTEM_VERSION);

        FeijiuFastProduct model = getModel(FeijiuFastProduct.class);

        Boolean result = FeijiuFastProductService.me.update(model, Cnd.where(model.PRODUCT_ID, model.getProduct_id()).and(FeijiuFastProduct.SYSTEM_VERSION, model.getSystem_version()));

        renderSuccessJson(result);
    }

    @ActionKey("/admin/feijiu/fast/product/delete")
    public void delete() {
        validateRequest(FeijiuFastProduct.PRODUCT_ID, FeijiuFastProduct.SYSTEM_VERSION);

        FeijiuFastProduct model = getModel(FeijiuFastProduct.class);

        Boolean result = FeijiuFastProductService.me.delete(model, Cnd.where(model.PRODUCT_ID, model.getProduct_id()).and(FeijiuFastProduct.SYSTEM_VERSION, model.getSystem_version()));

        renderSuccessJson(result);
    }

}