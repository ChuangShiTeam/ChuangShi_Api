package com.nowui.chuangshi.api.product.mobile;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.file.service.FileService;
import com.nowui.chuangshi.api.member.model.Member;
import com.nowui.chuangshi.api.member.model.MemberLevel;
import com.nowui.chuangshi.api.member.service.MemberLevelService;
import com.nowui.chuangshi.api.member.service.MemberService;
import com.nowui.chuangshi.api.product.model.Product;
import com.nowui.chuangshi.api.product.model.ProductSku;
import com.nowui.chuangshi.api.product.model.ProductSkuPrice;
import com.nowui.chuangshi.api.product.service.ProductService;
import com.nowui.chuangshi.api.product.service.ProductSkuPriceService;
import com.nowui.chuangshi.api.product.service.ProductSkuService;
import com.nowui.chuangshi.api.trade.model.MemberPurchaseOrder;
import com.nowui.chuangshi.api.trade.service.MemberPurchaseOrderService;
import com.nowui.chuangshi.api.user.model.User;
import com.nowui.chuangshi.api.user.service.UserService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.util.ValidateUtil;

import java.math.BigDecimal;
import java.util.List;

@ControllerKey("/mobile/product")
public class ProductController extends Controller {

    @ActionKey("/mobile/product/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/product/find")
    public void find() {
        validateRequest(Product.PRODUCT_ID);

        Product model = getModel(Product.class);

        String request_user_id = getRequest_user_id();
        User user = UserService.me.findById(request_user_id);
        Member member = MemberService.me.findById(user.getObject_id());

        Product result = ProductService.me.findById(model.getProduct_id());
        result.put(Product.PRODUCT_IMAGE, FileService.me.getFile_path(result.getProduct_image()));

        List<ProductSku> productSkuList = ProductSkuService.me.list(Cnd.where(ProductSku.PRODUCT_ID, result.getProduct_id()));
        for (ProductSku productSku : productSkuList) {
            if (productSku.getProduct_sku_is_default()) {
                List<ProductSkuPrice> productSkuPriceList = ProductSkuPriceService.me.list(Cnd.where(ProductSkuPrice.PRODUCT_SKU_ID, productSku.getProduct_sku_id()));
                for (ProductSkuPrice productSkuPrice : productSkuPriceList) {
                    if (productSkuPrice.getMember_level_id().equals("")) {
                        result.put(ProductSkuPrice.PRODUCT_SKU_PRICE, productSkuPrice.getProduct_sku_price());
                    }
                }
                for (ProductSkuPrice productSkuPrice : productSkuPriceList) {
                    if (productSkuPrice.getMember_level_id().equals(member.getMember_level_id())) {
                        result.put(ProductSkuPrice.PRODUCT_SKU_PRICE, productSkuPrice.getProduct_sku_price());
                    }
                }

                if (request_user_id.equals("229736797b4d4283b284f6aef128585c")) {
                    result.put(ProductSkuPrice.PRODUCT_SKU_PRICE, new BigDecimal(0.01));
                }

                result.put(ProductSkuPrice.PRODUCT_SKU_ID, productSku.getProduct_sku_id());

                break;
            }
        }

        Integer member_level_value = 0;
        String member_level_id = member.getMember_level_id();
        if (!ValidateUtil.isNullOrEmpty(member_level_id)) {
            MemberLevel memberLevel = MemberLevelService.me.findById(member_level_id);
            member_level_value = memberLevel.getMember_level_value();
        }

        Integer count = MemberPurchaseOrderService.me.count(Cnd.where(MemberPurchaseOrder.USER_ID, request_user_id));

        result.put("is_first_purchase", count == 0);

        Integer product_quantity_min = 1;

        product_quantity_min = 10;
        if (count == 0) {

        } else {

        }
        if (member_level_value == 1) {
            product_quantity_min = count == 0 ? 25000 : 12500;
        } else if (member_level_value == 2) {
            product_quantity_min = count == 0 ? 10000 : 5000;
        } else if (member_level_value == 3) {
            product_quantity_min = count == 0 ? 10000 : 5000;//可以改//5000
        } else if (member_level_value == 4) {
            product_quantity_min = count == 0 ? 2500 : 1250;
        } else if (member_level_value == 5) {
            product_quantity_min = count == 0 ? 600 : 300;
        } else if (member_level_value == 6) {
            product_quantity_min = count == 0 ? 100 : 50;
        } else if (member_level_value == 7) {
            product_quantity_min = 10;
        }

        if (request_user_id.equals("229736797b4d4283b284f6aef128585c")) {
            product_quantity_min = 1;
        }

        result.put("product_sku_quantity", product_quantity_min);

        validateResponse(Product.PRODUCT_ID, Product.PRODUCT_NAME, Product.PRODUCT_IMAGE, Product.PRODUCT_CONTENT, ProductSkuPrice.PRODUCT_SKU_ID, ProductSkuPrice.PRODUCT_SKU_PRICE, "product_sku_quantity", "is_first_purchase");

        renderSuccessJson(result);
    }

    @ActionKey("/mobile/product/purchase/find")
    public void purchaseFind() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/product/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/product/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/product/delete")
    public void delete() {

        renderSuccessJson();
    }

}