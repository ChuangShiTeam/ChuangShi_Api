package com.nowui.chuangshi.api.product.mobile;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.file.service.FileService;
import com.nowui.chuangshi.api.member.model.Member;
import com.nowui.chuangshi.api.member.service.MemberService;
import com.nowui.chuangshi.api.product.model.Product;
import com.nowui.chuangshi.api.product.model.ProductBrand;
import com.nowui.chuangshi.api.product.model.ProductSku;
import com.nowui.chuangshi.api.product.model.ProductSkuPrice;
import com.nowui.chuangshi.api.product.service.ProductBrandService;
import com.nowui.chuangshi.api.product.service.ProductService;
import com.nowui.chuangshi.api.product.service.ProductSkuPriceService;
import com.nowui.chuangshi.api.product.service.ProductSkuService;
import com.nowui.chuangshi.api.user.model.User;
import com.nowui.chuangshi.api.user.service.UserService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.util.ValidateUtil;

import java.util.List;

@ControllerKey("/mobile/product/brand")
public class ProductBrandController extends Controller {

    @ActionKey("/mobile/product/brand/list")
    public void list() {
        ProductBrand model = getModel(ProductBrand.class);
        String request_app_id = getRequest_app_id();

        List<ProductBrand> resultList = ProductBrandService.instance.mobileList(request_app_id);

        for (ProductBrand productBrand : resultList) {
            productBrand.put(ProductBrand.PRODUCT_BRAND_IMAGE, FileService.instance.getFile_path(productBrand.getProduct_brand_image()));
        }

        validateResponse(ProductBrand.PRODUCT_BRAND_ID, ProductBrand.PRODUCT_BRAND_NAME, ProductBrand.PRODUCT_BRAND_IMAGE);

        renderSuccessJson(resultList);
    }

    @ActionKey("/mobile/product/brand/product/list")
    public void productList() {
        Product model = getModel(Product.class);

        String request_user_id = getRequest_user_id();
        User user = UserService.instance.find(request_user_id);
        Member member = MemberService.instance.find(user.getObject_id());

        List<Product> resultList = ProductService.instance.productBrandList(model.getProduct_brand_id());

        for (Product product : resultList) {
            product.put(Product.PRODUCT_IMAGE, FileService.instance.getFile_path(product.getProduct_image()));

            List<ProductSku> productSkuList = ProductSkuService.instance.productList(product.getProduct_id());
            for (ProductSku productSku : productSkuList) {
                if (productSku.getProduct_sku_is_default()) {
                    List<ProductSkuPrice> productSkuPriceList = ProductSkuPriceService.instance.productSkuList(productSku.getProduct_sku_id());
                    for (ProductSkuPrice productSkuPrice : productSkuPriceList) {
                        if (ValidateUtil.isNullOrEmpty(member.getMember_level_id())) {
                            if (productSkuPrice.getMember_level_id().equals("")) {
                                product.put(ProductSkuPrice.PRODUCT_SKU_PRICE, productSkuPrice.getProduct_sku_price());
                                break;
                            }
                        } else {
                            if (productSkuPrice.getMember_level_id().equals(member.getMember_level_id())) {
                                product.put(ProductSkuPrice.PRODUCT_SKU_PRICE, productSkuPrice.getProduct_sku_price());
                                break;
                            }
                        }
                    }
                }
            }
        }

        validateResponse(Product.PRODUCT_ID, Product.PRODUCT_NAME, Product.PRODUCT_IMAGE, ProductSkuPrice.PRODUCT_SKU_PRICE);

        renderSuccessJson(resultList);
    }

    @ActionKey("/mobile/product/brand/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/product/brand/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/product/brand/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/product/brand/delete")
    public void delete() {

        renderSuccessJson();
    }

}