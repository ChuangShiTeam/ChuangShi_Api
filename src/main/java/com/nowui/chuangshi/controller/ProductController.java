package com.nowui.chuangshi.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.constant.Url;
import com.nowui.chuangshi.model.*;
import com.nowui.chuangshi.service.*;
import com.nowui.chuangshi.util.Util;
import com.nowui.chuangshi.util.ValidateUtil;

import java.util.ArrayList;
import java.util.List;

public class ProductController extends Controller {

    private final ProductService productService = new ProductService();
    private ProductSkuService productSkuService = new ProductSkuService();
    private ProductSkuPriceService productSkuPriceService = new ProductSkuPriceService();
    private ProductSkuAttributeService productSkuAttributeService = new ProductSkuAttributeService();
    private final FileService fileService = new FileService();

    @ActionKey(Url.PRODUCT_ADMIN_LIST)
    public void adminList() {
        validateRequest_app_id();
        validate(Product.PRODUCT_NAME, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        Product model = getModel(Product.class);
        String request_app_id = getRequest_app_id();

        authenticateRequest_app_idAndRequest_user_id();

        Integer total = productService.countByApp_idOrLikeProduct_name(request_app_id, model.getProduct_name());
        List<Product> resultList = productService.listByApp_idOrLikeProduct_nameAndLimit(request_app_id, model.getProduct_name(), getM(), getN());

        for (Product result : resultList) {
            result.keep(Product.PRODUCT_ID, Product.PRODUCT_NAME, Product.SYSTEM_VERSION);
        }

        renderSuccessJson(total, resultList);
    }

    @ActionKey(Url.PRODUCT_ADMIN_FIND)
    public void adminFind() {
        validateRequest_app_id();
        validate(Product.PRODUCT_ID);

        Product model = getModel(Product.class);

        authenticateRequest_app_idAndRequest_user_id();

        Product product = productService.findByProduct_id(model.getProduct_id());

        authenticateApp_id(product.getApp_id());

        product.keep(Product.PRODUCT_ID, Product.CATEGORY_ID, Product.BRAND_ID, Product.PRODUCT_NAME, Product.PRODUCT_IMAGE, Product.PRODUCT_IS_NEW, Product.PRODUCT_IS_RECOMMEND, Product.PRODUCT_IS_BARGAIN, Product.PRODUCT_IS_HOT, Product.PRODUCT_IS_SOLD_OUT, Product.PRODUCT_IS_VIRTUAL, Product.PRODUCT_CONTENT, Product.PRODUCT_STATUS, Product.SYSTEM_VERSION);

        if (ValidateUtil.isNullOrEmpty(product.getProduct_image())) {
            product.put(Product.PRODUCT_IMAGE_FILE, "");
        } else {
            File file = fileService.findByFile_id(product.getProduct_image());
            product.put(Product.PRODUCT_IMAGE_FILE, file.keep(File.FILE_ID, File.FILE_PATH));
        }

        renderSuccessJson(product);
    }

    @ActionKey(Url.PRODUCT_ADMIN_SAVE)
    public void adminSave() {
        validateRequest_app_id();
        validate(Product.CATEGORY_ID, Product.BRAND_ID, Product.PRODUCT_NAME, Product.PRODUCT_IMAGE, Product.PRODUCT_IS_NEW, Product.PRODUCT_IS_RECOMMEND, Product.PRODUCT_IS_BARGAIN, Product.PRODUCT_IS_HOT, Product.PRODUCT_IS_SOLD_OUT, Product.PRODUCT_IS_VIRTUAL, Product.PRODUCT_CONTENT, Product.PRODUCT_STATUS);

        Product model = getModel(Product.class);
        String product_id = Util.getRandomUUID();
        String request_app_id = getRequest_app_id();
        String request_user_id = getRequest_user_id();
        JSONObject jsonObject = getParameterJSONObject();

        authenticateRequest_app_idAndRequest_user_id();

        Boolean result = productService.save(product_id, request_app_id, model.getCategory_id(), model.getBrand_id(), model.getProduct_name(), model.getProduct_image(), model.getProduct_is_new(), model.getProduct_is_recommend(), model.getProduct_is_bargain(), model.getProduct_is_hot(), model.getProduct_is_sold_out(), model.getProduct_is_virtual(), model.getProduct_content(), model.getProduct_status(), request_user_id);

        if (result) {
            saveProductSkuList(model.getProduct_id(), jsonObject.getJSONArray(Product.PRODUCT_SKU_LIST), new ArrayList<ProductSku>(), request_user_id);
        }

        renderSuccessJson(result);
    }

    @ActionKey(Url.PRODUCT_ADMIN_UPDATE)
    public void adminUpdate() {
        validateRequest_app_id();
        validate(Product.PRODUCT_ID, Product.CATEGORY_ID, Product.BRAND_ID, Product.PRODUCT_NAME, Product.PRODUCT_IMAGE, Product.PRODUCT_IS_NEW, Product.PRODUCT_IS_RECOMMEND, Product.PRODUCT_IS_BARGAIN, Product.PRODUCT_IS_HOT, Product.PRODUCT_IS_SOLD_OUT, Product.PRODUCT_IS_VIRTUAL, Product.PRODUCT_CONTENT, Product.PRODUCT_STATUS, Product.SYSTEM_VERSION);

        Product model = getModel(Product.class);
        String request_user_id = getRequest_user_id();
        JSONObject jsonObject = getParameterJSONObject();

        authenticateRequest_app_idAndRequest_user_id();

        Product product = productService.findByProduct_id(model.getProduct_id());

        authenticateApp_id(product.getApp_id());

        Boolean result = productService.updateValidateSystem_version(model.getProduct_id(), model.getCategory_id(), model.getBrand_id(), model.getProduct_name(), model.getProduct_image(), model.getProduct_is_new(), model.getProduct_is_recommend(), model.getProduct_is_bargain(), model.getProduct_is_hot(), model.getProduct_is_sold_out(), model.getProduct_is_virtual(), model.getProduct_content(), model.getProduct_status(), request_user_id, model.getSystem_version());

        List<ProductSku> productSkuList = productSkuService.listByProduct_id(model.getProduct_id());

        if (result) {
            saveProductSkuList(model.getProduct_id(), jsonObject.getJSONArray(Product.PRODUCT_SKU_LIST), productSkuList, request_user_id);
        }

        renderSuccessJson(result);
    }

    @ActionKey(Url.PRODUCT_ADMIN_DELETE)
    public void adminDelete() {
        validateRequest_app_id();
        validate(Product.PRODUCT_ID, Product.SYSTEM_VERSION);

        Product model = getModel(Product.class);
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        Product product = productService.findByProduct_id(model.getProduct_id());

        authenticateApp_id(product.getApp_id());

        Boolean result = productService.deleteByProduct_idAndSystem_update_user_idValidateSystem_version(model.getProduct_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey(Url.PRODUCT_SYSTEM_LIST)
    public void systemList() {
        validateRequest_app_id();
        validate(Product.APP_ID, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        Product model = getModel(Product.class);

        Integer total = productService.countByOrApp_idOrLikeProduct_name(model.getApp_id(), model.getProduct_name());
        List<Product> resultList = productService.listByOrApp_idOrLikeProduct_nameAndLimit(model.getApp_id(), model.getProduct_name(), getM(), getN());

        for (Product result : resultList) {
            result.keep(Product.PRODUCT_ID, Product.PRODUCT_NAME, Product.SYSTEM_VERSION);
        }

        renderSuccessJson(total, resultList);
    }

    @ActionKey(Url.PRODUCT_SYSTEM_FIND)
    public void systemFind() {
        validateRequest_app_id();
        validate(Product.PRODUCT_ID);

        Product model = getModel(Product.class);

        Product product = productService.findByProduct_id(model.getProduct_id());

        product.keep(Product.PRODUCT_ID, Product.CATEGORY_ID, Product.BRAND_ID, Product.PRODUCT_NAME, Product.PRODUCT_IMAGE, Product.PRODUCT_IS_NEW, Product.PRODUCT_IS_RECOMMEND, Product.PRODUCT_IS_BARGAIN, Product.PRODUCT_IS_HOT, Product.PRODUCT_IS_SOLD_OUT, Product.PRODUCT_IS_VIRTUAL, Product.PRODUCT_CONTENT, Product.PRODUCT_STATUS, Product.SYSTEM_VERSION);

        if (ValidateUtil.isNullOrEmpty(product.getProduct_image())) {
            product.put(FeijiuRecommendProduct.PRODUCT_IMAGE_FILE, "");
        } else {
            File file = fileService.findByFile_id(product.getProduct_image());
            product.put(FeijiuRecommendProduct.PRODUCT_IMAGE_FILE, file.keep(File.FILE_ID, File.FILE_PATH));
        }

        renderSuccessJson(product);
    }

    @ActionKey(Url.PRODUCT_SYSTEM_SAVE)
    public void systemSave() {
        validateRequest_app_id();
        validate(Product.APP_ID, Product.CATEGORY_ID, Product.BRAND_ID, Product.PRODUCT_NAME, Product.PRODUCT_IMAGE, Product.PRODUCT_IS_NEW, Product.PRODUCT_IS_RECOMMEND, Product.PRODUCT_IS_BARGAIN, Product.PRODUCT_IS_HOT, Product.PRODUCT_IS_SOLD_OUT, Product.PRODUCT_IS_VIRTUAL, Product.PRODUCT_CONTENT, Product.PRODUCT_STATUS);

        Product model = getModel(Product.class);
        String product_id = Util.getRandomUUID();
        String request_user_id = getRequest_user_id();

        Boolean result = productService.save(product_id, model.getApp_id(), model.getCategory_id(), model.getBrand_id(), model.getProduct_name(), model.getProduct_image(), model.getProduct_is_new(), model.getProduct_is_recommend(), model.getProduct_is_bargain(), model.getProduct_is_hot(), model.getProduct_is_sold_out(), model.getProduct_is_virtual(), model.getProduct_content(), model.getProduct_status(), request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey(Url.PRODUCT_SYSTEM_UPDATE)
    public void systemUpdate() {
        validateRequest_app_id();
        validate(Product.PRODUCT_ID, Product.CATEGORY_ID, Product.BRAND_ID, Product.PRODUCT_NAME, Product.PRODUCT_IMAGE, Product.PRODUCT_IS_NEW, Product.PRODUCT_IS_RECOMMEND, Product.PRODUCT_IS_BARGAIN, Product.PRODUCT_IS_HOT, Product.PRODUCT_IS_SOLD_OUT, Product.PRODUCT_IS_VIRTUAL, Product.PRODUCT_CONTENT, Product.PRODUCT_STATUS, Product.SYSTEM_VERSION);

        Product model = getModel(Product.class);
        String request_user_id = getRequest_user_id();

        Boolean result = productService.updateValidateSystem_version(model.getProduct_id(), model.getCategory_id(), model.getBrand_id(), model.getProduct_name(), model.getProduct_image(), model.getProduct_is_new(), model.getProduct_is_recommend(), model.getProduct_is_bargain(), model.getProduct_is_hot(), model.getProduct_is_sold_out(), model.getProduct_is_virtual(), model.getProduct_content(), model.getProduct_status(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey(Url.PRODUCT_SYSTEM_DELETE)
    public void systemDelete() {
        validateRequest_app_id();
        validate(Product.PRODUCT_ID, Product.SYSTEM_VERSION);

        Product model = getModel(Product.class);
        String request_user_id = getRequest_user_id();

        Boolean result = productService.deleteByProduct_idAndSystem_update_user_idValidateSystem_version(model.getProduct_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    private void saveProductSkuList(String product_id, JSONArray jsonArray, List<ProductSku> productSkuList, String request_user_id) {
        List<ProductSku> productSkuSaveList = new ArrayList<ProductSku>();
        List<String> productSkuIdDeleteList = new ArrayList<String>();

        List<ProductSkuPrice> productSkuPriceSaveList = new ArrayList<ProductSkuPrice>();
        List<ProductSkuPrice> productSkuPriceDeleteList = new ArrayList<ProductSkuPrice>();

        //新增SKU
        for (int i = 0; i < jsonArray.size(); i++) {
            Boolean isPrice = false;
            Boolean isAttribute = false;

            JSONObject jsonObject = jsonArray.getJSONObject(i);
            Boolean product_sku_is_default = jsonObject.getBoolean(ProductSku.PRODUCT_SKU_IS_DEFAULT);

            JSONArray productSkuPriceJSONArray = jsonObject.getJSONArray(Product.PRODUCT_SKU_PRICE_LIST);
            JSONArray productSkuAttributeJSONArray = jsonObject.getJSONArray(Product.PRODUCT_SKU_ATTRIBUTE_LIST);

            for (ProductSku productSku : productSkuList) {
                //对比SKU价格
                int priceCount = 0;
                List<ProductSkuPrice> productSkuPriceList = productSkuPriceService.listByProduct_sku_id(productSku.getProduct_sku_id());
                for (ProductSkuPrice productSkuPrice : productSkuPriceList) {
                    Boolean isExit = false;

                    System.out.println(JSONObject.toJSONString(productSkuPrice));

                    for (int j = 0; j < productSkuPriceJSONArray.size(); j++) {
                        JSONObject productSkuPriceJSONObject = productSkuPriceJSONArray.getJSONObject(j);

                        if (productSkuPrice.getMember_level_id().equals(productSkuPriceJSONObject.getString(ProductSkuPrice.MEMBER_LEVEL_ID)) && productSkuPrice.getMember_level_name().equals(productSkuPriceJSONObject.getString(ProductSkuPrice.MEMBER_LEVEL_NAME)) && productSkuPrice.getProduct_sku_price().compareTo(productSkuPriceJSONObject.getBigDecimal(ProductSkuPrice.PRODUCT_SKU_PRICE)) == 0) {
                            isExit = true;

                            break;

                        }
                    }

                    if (isExit) {
                        priceCount++;
                    }
                }
                if (priceCount == productSkuPriceList.size()) {
                    isPrice = true;
                }

                //对比SKU属性
                int attributeCount = 0;
                List<ProductSkuAttribute> productSkuAttributeList = productSkuAttributeService.listByProduct_sku_id(productSku.getProduct_sku_id());
                for (ProductSkuAttribute productSkuAttribute : productSkuAttributeList) {
                    Boolean isExit = false;

                    for (int j = 0; j < productSkuAttributeJSONArray.size(); j++) {
                        JSONObject productSkuAttributeJSONObject = productSkuAttributeJSONArray.getJSONObject(j);

                        if (productSkuAttribute.getProduct_sku_attribute_name().equals(productSkuAttributeJSONObject.getString(ProductSkuAttribute.PRODUCT_SKU_ATTRIBUTE_NAME)) && productSkuAttribute.getProduct_sku_attribute_value().equals(productSkuAttributeJSONObject.getString(ProductSkuAttribute.PRODUCT_SKU_ATTRIBUTE_VALUE))) {
                            isExit = true;

                            break;
                        }
                    }

                    if (isExit) {
                        attributeCount++;
                    }
                }
                if (attributeCount == productSkuAttributeList.size()) {
                    isAttribute = true;
                }
            }

            if (isPrice && isAttribute) {

            } else {
                String product_sku_id = Util.getRandomUUID();
                ProductSku productSku = new ProductSku();
                productSku.setProduct_sku_id(product_sku_id);
                productSku.setProduct_id(product_id);
                productSku.setProduct_sku_is_default(product_sku_is_default);
                productSkuSaveList.add(productSku);

                for (int j = 0; j < productSkuPriceJSONArray.size(); j++) {
                    JSONObject productSkuPriceJsonObject = productSkuPriceJSONArray.getJSONObject(j);

                    ProductSkuPrice productSkuPrice = new ProductSkuPrice();
                    productSkuPrice.setProduct_sku_id(product_sku_id);
                    productSkuPrice.setMember_level_id(productSkuPriceJsonObject.getString(ProductSkuPrice.MEMBER_LEVEL_ID));
                    productSkuPrice.setMember_level_name(productSkuPriceJsonObject.getString(ProductSkuPrice.MEMBER_LEVEL_NAME));
                    productSkuPrice.setProduct_sku_price(productSkuPriceJsonObject.getBigDecimal(ProductSkuPrice.PRODUCT_SKU_PRICE));
                    productSkuPriceSaveList.add(productSkuPrice);
                }
            }
        }

        //删除SKU
        for (ProductSku productSku : productSkuList) {
            Boolean isPrice = false;
            Boolean isAttribute = false;

            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Boolean product_sku_is_default = jsonObject.getBoolean(ProductSku.PRODUCT_SKU_IS_DEFAULT);

                JSONArray productSkuPriceJSONArray = jsonObject.getJSONArray(Product.PRODUCT_SKU_PRICE_LIST);
                JSONArray productSkuAttributeJSONArray = jsonObject.getJSONArray(Product.PRODUCT_SKU_ATTRIBUTE_LIST);

                //对比SKU价格
                int priceCount = 0;
                List<ProductSkuPrice> productSkuPriceList = productSkuPriceService.listByProduct_sku_id(productSku.getProduct_sku_id());
                for (ProductSkuPrice productSkuPrice : productSkuPriceList) {
                    Boolean isExit = false;

                    for (int j = 0; j < productSkuPriceJSONArray.size(); j++) {
                        JSONObject productSkuPriceJSONObject = productSkuPriceJSONArray.getJSONObject(j);

                        if (productSkuPrice.getMember_level_id().equals(productSkuPriceJSONObject.getString(ProductSkuPrice.MEMBER_LEVEL_ID)) && productSkuPrice.getMember_level_name().equals(productSkuPriceJSONObject.getString(ProductSkuPrice.MEMBER_LEVEL_NAME)) && productSkuPrice.getProduct_sku_price().equals(productSkuPriceJSONObject.getBigDecimal(ProductSkuPrice.PRODUCT_SKU_PRICE))) {
                            isExit = true;
                        }
                    }

                    if (isExit) {
                        priceCount++;

                        break;
                    }
                }
                if (priceCount == productSkuPriceList.size()) {
                    isPrice = true;
                }

                //对比SKU属性
                int attributeCount = 0;
                List<ProductSkuAttribute> productSkuAttributeList = productSkuAttributeService.listByProduct_sku_id(productSku.getProduct_sku_id());
                for (ProductSkuAttribute productSkuAttribute : productSkuAttributeList) {
                    Boolean isExit = false;

                    for (int j = 0; j < productSkuAttributeJSONArray.size(); j++) {
                        JSONObject productSkuAttributeJSONObject = productSkuAttributeJSONArray.getJSONObject(j);

                        if (productSkuAttribute.getProduct_sku_attribute_name().equals(productSkuAttributeJSONObject.getString(ProductSkuAttribute.PRODUCT_SKU_ATTRIBUTE_NAME)) && productSkuAttribute.getProduct_sku_attribute_value().equals(productSkuAttributeJSONObject.getString(ProductSkuAttribute.PRODUCT_SKU_ATTRIBUTE_VALUE))) {
                            isExit = true;
                        }
                    }

                    if (isExit) {
                        attributeCount++;

                        break;
                    }
                }
                if (attributeCount == productSkuAttributeList.size()) {
                    isAttribute = true;
                }
            }

            if (isPrice && isAttribute) {

            } else {
                productSkuIdDeleteList.add(productSku.getProduct_sku_id());
            }
        }

        productSkuService.save(product_id, productSkuSaveList, request_user_id);
        productSkuService.delete(product_id, productSkuIdDeleteList, request_user_id);
        productSkuPriceService.save(productSkuPriceSaveList, request_user_id);
        productSkuPriceService.delete(productSkuIdDeleteList, request_user_id);

        
    }

}