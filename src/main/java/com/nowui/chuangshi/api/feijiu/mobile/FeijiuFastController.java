package com.nowui.chuangshi.api.feijiu.mobile;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.feijiu.model.FeijiuFastProduct;
import com.nowui.chuangshi.api.feijiu.model.FeijiuFastProductCategory;
import com.nowui.chuangshi.api.feijiu.service.FeijiuFastProductCategoryService;
import com.nowui.chuangshi.api.feijiu.service.FeijiuFastProductService;
import com.nowui.chuangshi.api.file.service.FileService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.common.sql.Cnd;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerKey("/mobile/feijiu/fast")
public class FeijiuFastController extends Controller {

    @ActionKey("/mobile/feijiu/fast/index")
    public void index() {
        String request_app_id = getRequest_app_id();

        Map<String, Object> result = new HashMap<String, Object>();

        List<FeijiuFastProductCategory> productCategoryList = FeijiuFastProductCategoryService.me.list(Cnd.where(FeijiuFastProductCategory.APP_ID, request_app_id));
        for (FeijiuFastProductCategory feijiuFastProductCategory : productCategoryList) {
            feijiuFastProductCategory.keep(FeijiuFastProductCategory.PRODUCT_CATEGORY_ID, FeijiuFastProductCategory.PRODUCT_CATEGORY_NAME);
        }
        result.put("product_category_list", productCategoryList);

        List<FeijiuFastProduct> productList = FeijiuFastProductService.me.list(Cnd.where(FeijiuFastProductCategory.APP_ID, request_app_id));
        for (FeijiuFastProduct feijiuFastProduct : productList) {
            feijiuFastProduct.put(FeijiuFastProduct.PRODUCT_IMAGE, FileService.me.getFile_path(feijiuFastProduct.getProduct_image()));

            feijiuFastProduct.keep(FeijiuFastProduct.PRODUCT_ID, FeijiuFastProduct.PRODUCT_CATEGORY_ID, FeijiuFastProduct.PRODUCT_NAME, FeijiuFastProduct.PRODUCT_IMAGE, FeijiuFastProduct.PRODUCT_CONTENT, FeijiuFastProduct.PRODUCT_LINK, FeijiuFastProduct.PRODUCT_APPLICANT_QUANTITY);
        }
        result.put("product_list", productList);

        validateResponse("product_category_list", "product_list");

        renderSuccessJson(result);
    }

}
