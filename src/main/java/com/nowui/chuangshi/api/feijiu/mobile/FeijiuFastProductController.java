package com.nowui.chuangshi.api.feijiu.mobile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.feijiu.model.FeijiuFastProduct;
import com.nowui.chuangshi.api.feijiu.model.FeijiuFastProductCategory;
import com.nowui.chuangshi.api.feijiu.service.FeijiuFastProductCategoryService;
import com.nowui.chuangshi.api.feijiu.service.FeijiuFastProductService;
import com.nowui.chuangshi.api.file.model.File;
import com.nowui.chuangshi.api.file.service.FileService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.util.ValidateUtil;

@ControllerKey("/mobile/feijiu/fast/product")
public class FeijiuFastProductController extends Controller {

    @ActionKey("/mobile/feijiu/fast/product/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/feijiu/fast/product/find")
    public void find() {

        renderSuccessJson();
    }
    
    @ActionKey("/mobile/feijiu/fast/product/findByProductCategoryId")
    public void findByProductCategoryId() {
        validateRequest(FeijiuFastProductCategory.PRODUCT_CATEGORY_ID);

        FeijiuFastProductCategory model = getModel(FeijiuFastProductCategory.class);
        
        Cnd cnd = Cnd.where(FeijiuFastProductCategory.APP_ID, model.getApp_id()).and(FeijiuFastProductCategory.PRODUCT_CATEGORY_ID, model.getProduct_category_id());

        List<FeijiuFastProduct> product_list = FeijiuFastProductService.me.list(cnd);
        
        for (FeijiuFastProduct feijiu_fast_product : product_list) {
            if (ValidateUtil.isNullOrEmpty(feijiu_fast_product.getProduct_image())) {
                feijiu_fast_product.put(FeijiuFastProduct.PRODUCT_IMAGE_FILE, "");
            } else {
                File file = FileService.me.getFile(feijiu_fast_product.getProduct_image());
                feijiu_fast_product.put(FeijiuFastProduct.PRODUCT_IMAGE_FILE, file);
            }
        }
        
        validateResponse(FeijiuFastProduct.PRODUCT_ID, FeijiuFastProduct.PRODUCT_NAME, FeijiuFastProduct.PRODUCT_IMAGE_FILE, FeijiuFastProduct.PRODUCT_CONTENT, FeijiuFastProduct.PRODUCT_LINK, FeijiuFastProduct.PRODUCT_APPLICANT_QUANTITY, FeijiuFastProduct.SYSTEM_VERSION);
        
        renderSuccessJson(product_list);
    }

    @ActionKey("/mobile/feijiu/fast/product/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/feijiu/fast/product/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/feijiu/fast/product/delete")
    public void delete() {

        renderSuccessJson();
    }

}