package com.nowui.chuangshi.api.feijiu.admin;


import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.feijiu.model.FeijiuFastProduct;
import com.nowui.chuangshi.api.feijiu.service.FeijiuFastProductService;
import com.nowui.chuangshi.api.file.service.FileService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;

import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.util.Util;
import com.nowui.chuangshi.util.ValidateUtil;

import java.util.List;


@ControllerKey("/admin/feijiu/fast/product")
public class FeijiuFastProductController extends Controller {
    
    @ActionKey("/admin/feijiu/fast/product/list")
    public void list() {
        validateRequest(FeijiuFastProduct.PRODUCT_NAME, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        FeijiuFastProduct model = getModel(FeijiuFastProduct.class);
        String request_app_id = getRequest_app_id();

        Integer resultCount = FeijiuFastProductService.instance.adminCount(request_app_id, model.getProduct_name());
        List<FeijiuFastProduct> resultList = FeijiuFastProductService.instance.adminList(request_app_id, model.getProduct_name(), getM(), getN());

        validateResponse(FeijiuFastProduct.PRODUCT_ID, FeijiuFastProduct.PRODUCT_NAME, FeijiuFastProduct.PRODUCT_LINK, FeijiuFastProduct.PRODUCT_APPLICANT_QUANTITY, FeijiuFastProduct.PRODUCT_SORT, FeijiuFastProduct.SYSTEM_VERSION);

        renderSuccessJson(resultCount, resultList);
    }

    @ActionKey("/admin/feijiu/fast/product/find")
    public void find() {
        validateRequest(FeijiuFastProduct.PRODUCT_ID);

        FeijiuFastProduct model = getModel(FeijiuFastProduct.class);

        FeijiuFastProduct feijiu_fast_product = FeijiuFastProductService.instance.find(model.getProduct_id());

        if (ValidateUtil.isNullOrEmpty(feijiu_fast_product.getProduct_image())) {
            feijiu_fast_product.put(FeijiuFastProduct.PRODUCT_IMAGE_FILE, "");
        } else {
            feijiu_fast_product.put(FeijiuFastProduct.PRODUCT_IMAGE_FILE, FileService.instance.getFile(feijiu_fast_product.getProduct_image()));
        }
        
        validateResponse(FeijiuFastProduct.PRODUCT_CATEGORY_ID, FeijiuFastProduct.PRODUCT_IMAGE_FILE, FeijiuFastProduct.PRODUCT_NAME, FeijiuFastProduct.PRODUCT_IMAGE, FeijiuFastProduct.PRODUCT_LINK, FeijiuFastProduct.PRODUCT_CONTENT, FeijiuFastProduct.PRODUCT_APPLICANT_QUANTITY, FeijiuFastProduct.PRODUCT_SORT, FeijiuFastProduct.SYSTEM_VERSION);

        renderSuccessJson(feijiu_fast_product);
    }

    @ActionKey("/admin/feijiu/fast/product/save")
    public void save() {
        validateRequest(FeijiuFastProduct.PRODUCT_CATEGORY_ID, FeijiuFastProduct.PRODUCT_NAME, FeijiuFastProduct.PRODUCT_IMAGE, FeijiuFastProduct.PRODUCT_LINK, FeijiuFastProduct.PRODUCT_CONTENT, FeijiuFastProduct.PRODUCT_APPLICANT_QUANTITY);

        FeijiuFastProduct model = getModel(FeijiuFastProduct.class);
        model.setProduct_id(Util.getRandomUUID());
        String request_user_id = getRequest_user_id();

        Boolean result = FeijiuFastProductService.instance.save(model, request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/feijiu/fast/product/update")
    public void update() {
        validateRequest(FeijiuFastProduct.PRODUCT_ID, FeijiuFastProduct.PRODUCT_CATEGORY_ID, FeijiuFastProduct.PRODUCT_NAME, FeijiuFastProduct.PRODUCT_IMAGE, FeijiuFastProduct.PRODUCT_LINK, FeijiuFastProduct.PRODUCT_CONTENT, FeijiuFastProduct.PRODUCT_APPLICANT_QUANTITY, FeijiuFastProduct.SYSTEM_VERSION);

        FeijiuFastProduct model = getModel(FeijiuFastProduct.class);
        String request_user_id = getRequest_user_id();

        Boolean result = FeijiuFastProductService.instance.update(model, model.getProduct_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey("/admin/feijiu/fast/product/delete")
    public void delete() {
        validateRequest(FeijiuFastProduct.PRODUCT_ID, FeijiuFastProduct.SYSTEM_VERSION);

        FeijiuFastProduct model = getModel(FeijiuFastProduct.class);
        String request_user_id = getRequest_user_id();

        Boolean result = FeijiuFastProductService.instance.delete(model.getProduct_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

}