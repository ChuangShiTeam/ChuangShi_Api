package com.nowui.chuangshi.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.constant.Url;
import com.nowui.chuangshi.model.FeijiuRecommendProduct;
import com.nowui.chuangshi.model.File;
import com.nowui.chuangshi.model.Product;
import com.nowui.chuangshi.model.ProductSku;
import com.nowui.chuangshi.model.ProductSkuAttribute;
import com.nowui.chuangshi.model.ProductSkuCommission;
import com.nowui.chuangshi.model.ProductSkuPrice;
import com.nowui.chuangshi.service.FileService;
import com.nowui.chuangshi.service.ProductService;
import com.nowui.chuangshi.service.ProductSkuAttributeService;
import com.nowui.chuangshi.service.ProductSkuCommissionService;
import com.nowui.chuangshi.service.ProductSkuPriceService;
import com.nowui.chuangshi.service.ProductSkuService;
import com.nowui.chuangshi.util.Util;
import com.nowui.chuangshi.util.ValidateUtil;

public class ProductController extends Controller {

    private final ProductService productService = new ProductService();
    private ProductSkuService productSkuService = new ProductSkuService();
    private ProductSkuPriceService productSkuPriceService = new ProductSkuPriceService();
    private ProductSkuAttributeService productSkuAttributeService = new ProductSkuAttributeService();
    private ProductSkuCommissionService productSkuCommissionService = new ProductSkuCommissionService();
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

    @ActionKey(Url.PRODUCT_ADMIN_ALL_LIST)
    public void adminAllList() {
        validateRequest_app_id();

        String request_app_id = getRequest_app_id();

        authenticateRequest_app_idAndRequest_user_id();

        List<Product> productList = productService.listByApp_id(request_app_id);

        List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
        for (Product product : productList) {
            Map<String, Object> resultMap = new HashMap<String, Object>();
            product.keep(Product.PRODUCT_ID, Product.PRODUCT_NAME, Product.SYSTEM_VERSION);
            List<ProductSku> productSkuList = productSkuService.listByProduct_id(product.getProduct_id());
            resultMap.put("product", product);
            resultMap.put("productSkuList", productSkuList);
            resultList.add(resultMap);
        }

        renderSuccessJson(resultList);
    }

    @ActionKey(Url.PRODUCT_ADMIN_FIND)
    public void adminFind() {
        validateRequest_app_id();
        validate(Product.PRODUCT_ID);

        Product model = getModel(Product.class);

        authenticateRequest_app_idAndRequest_user_id();

        Product product = productService.findByProduct_id(model.getProduct_id());

        authenticateApp_id(product.getApp_id());

        product.keep(Product.PRODUCT_ID, Product.PRODUCT_CATEGORY_ID, Product.PRODUCT_BRAND_ID, Product.PRODUCT_NAME, Product.PRODUCT_IMAGE, Product.PRODUCT_IS_NEW, Product.PRODUCT_IS_RECOMMEND,
                Product.PRODUCT_IS_BARGAIN, Product.PRODUCT_IS_HOT, Product.PRODUCT_IS_SOLD_OUT, Product.PRODUCT_IS_VIRTUAL, Product.PRODUCT_CONTENT, Product.PRODUCT_STATUS, Product.SYSTEM_VERSION);

        if (ValidateUtil.isNullOrEmpty(product.getProduct_image())) {
            product.put(Product.PRODUCT_IMAGE_FILE, "");
        } else {
            File file = fileService.findByFile_id(product.getProduct_image());
            product.put(Product.PRODUCT_IMAGE_FILE, file.keep(File.FILE_ID, File.FILE_PATH));
        }

        List<ProductSku> productSkuList = productSkuService.listByProduct_id(model.getProduct_id());
        for (ProductSku productSku : productSkuList) {
            productSku.keep(ProductSku.PRODUCT_SKU_ID, ProductSku.PRODUCT_SKU_IS_DEFAULT);

            List<ProductSkuPrice> productSkuPriceList = productSkuPriceService.listByProduct_sku_id(productSku.getProduct_sku_id());
            for (ProductSkuPrice productSkuPrice : productSkuPriceList) {
                productSkuPrice.keep(ProductSkuPrice.MEMBER_LEVEL_ID, ProductSkuPrice.PRODUCT_SKU_PRICE);
            }
            productSku.put(ProductSku.PRODUCT_SKU_PRICE_LIST, productSkuPriceList);

            productSku.put(ProductSku.PRODUCT_SKU_ATTRIBUTE_LIST, new ArrayList<ProductSkuAttribute>());

            List<ProductSkuCommission> productSkuCommissionList = productSkuCommissionService.listByProduct_sku_id(productSku.getProduct_sku_id());
            for (ProductSkuCommission productSkuCommission : productSkuCommissionList) {
                productSkuCommission.keep(ProductSkuCommission.MEMBER_LEVEL_ID, ProductSkuCommission.PRODUCT_SKU_COMMISSION);
            }
            productSku.put(ProductSku.PRODUCT_SKU_COMMISSION_LIST, productSkuCommissionList);
        }
        product.put(Product.PRODUCT_SKU_LIST, productSkuList);

        renderSuccessJson(product);
    }

    @ActionKey(Url.PRODUCT_ADMIN_SAVE)
    public void adminSave() {
        validateRequest_app_id();
        validate(Product.PRODUCT_CATEGORY_ID, Product.PRODUCT_BRAND_ID, Product.PRODUCT_NAME, Product.PRODUCT_IMAGE, Product.PRODUCT_IS_NEW, Product.PRODUCT_IS_RECOMMEND, Product.PRODUCT_IS_BARGAIN,
                Product.PRODUCT_IS_HOT, Product.PRODUCT_IS_SOLD_OUT, Product.PRODUCT_IS_VIRTUAL, Product.PRODUCT_CONTENT);

        Product model = getModel(Product.class);
        String product_id = Util.getRandomUUID();
        String request_app_id = getRequest_app_id();
        String request_user_id = getRequest_user_id();
        JSONObject jsonObject = getParameterJSONObject();

        authenticateRequest_app_idAndRequest_user_id();

        Boolean result = productService.save(product_id, request_app_id, model.getProduct_category_id(), model.getProduct_brand_id(), model.getProduct_name(), model.getProduct_image(),
                model.getProduct_is_new(), model.getProduct_is_recommend(), model.getProduct_is_bargain(), model.getProduct_is_hot(), model.getProduct_is_sold_out(), model.getProduct_is_virtual(),
                model.getProduct_content(), request_user_id);

        if (result) {
            saveProductSkuList(model.getProduct_id(), jsonObject.getJSONArray(Product.PRODUCT_SKU_LIST), new ArrayList<ProductSku>(), request_user_id);
        }

        renderSuccessJson(result);
    }

    @ActionKey(Url.PRODUCT_ADMIN_UPDATE)
    public void adminUpdate() {
        validateRequest_app_id();
        validate(Product.PRODUCT_ID, Product.PRODUCT_CATEGORY_ID, Product.PRODUCT_BRAND_ID, Product.PRODUCT_NAME, Product.PRODUCT_IMAGE, Product.PRODUCT_IS_NEW, Product.PRODUCT_IS_RECOMMEND,
                Product.PRODUCT_IS_BARGAIN, Product.PRODUCT_IS_HOT, Product.PRODUCT_IS_SOLD_OUT, Product.PRODUCT_IS_VIRTUAL, Product.PRODUCT_CONTENT, Product.PRODUCT_STATUS, Product.SYSTEM_VERSION);

        Product model = getModel(Product.class);
        String request_user_id = getRequest_user_id();
        JSONObject jsonObject = getParameterJSONObject();

        authenticateRequest_app_idAndRequest_user_id();

        Product product = productService.findByProduct_id(model.getProduct_id());

        authenticateApp_id(product.getApp_id());

        Boolean result = productService.updateValidateSystem_version(model.getProduct_id(), model.getProduct_category_id(), model.getProduct_brand_id(), model.getProduct_name(),
                model.getProduct_image(), model.getProduct_is_new(), model.getProduct_is_recommend(), model.getProduct_is_bargain(), model.getProduct_is_hot(), model.getProduct_is_sold_out(),
                model.getProduct_is_virtual(), model.getProduct_content(), model.getProduct_status(), request_user_id, model.getSystem_version());

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

    @ActionKey(Url.PRODUCT_SYSTEM_ALL_LIST)
    public void systemAllList() {
        validateRequest_app_id();

        JSONObject jsonObject = getParameterJSONObject();
        String app_id = jsonObject.getString("app_id");

        List<Product> productList = productService.listByOrApp_id(app_id);

        List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
        for (Product product : productList) {
            Map<String, Object> resultMap = new HashMap<String, Object>();
            product.keep(Product.PRODUCT_ID, Product.PRODUCT_NAME, Product.SYSTEM_VERSION);
            List<ProductSku> productSkuList = productSkuService.listByProduct_id(product.getProduct_id());
            resultMap.put("product", product);
            resultMap.put("productSkuList", productSkuList);
            resultList.add(resultMap);
        }

        renderSuccessJson(resultList);
    }

    @ActionKey(Url.PRODUCT_SYSTEM_FIND)
    public void systemFind() {
        validateRequest_app_id();
        validate(Product.PRODUCT_ID);

        Product model = getModel(Product.class);

        Product product = productService.findByProduct_id(model.getProduct_id());

        product.keep(Product.PRODUCT_ID, Product.PRODUCT_CATEGORY_ID, Product.PRODUCT_BRAND_ID, Product.PRODUCT_NAME, Product.PRODUCT_IMAGE, Product.PRODUCT_IS_NEW, Product.PRODUCT_IS_RECOMMEND,
                Product.PRODUCT_IS_BARGAIN, Product.PRODUCT_IS_HOT, Product.PRODUCT_IS_SOLD_OUT, Product.PRODUCT_IS_VIRTUAL, Product.PRODUCT_CONTENT, Product.PRODUCT_STATUS, Product.SYSTEM_VERSION);

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
        validate(Product.APP_ID, Product.PRODUCT_CATEGORY_ID, Product.PRODUCT_BRAND_ID, Product.PRODUCT_NAME, Product.PRODUCT_IMAGE, Product.PRODUCT_IS_NEW, Product.PRODUCT_IS_RECOMMEND,
                Product.PRODUCT_IS_BARGAIN, Product.PRODUCT_IS_HOT, Product.PRODUCT_IS_SOLD_OUT, Product.PRODUCT_IS_VIRTUAL, Product.PRODUCT_CONTENT);

        Product model = getModel(Product.class);
        String product_id = Util.getRandomUUID();
        String request_user_id = getRequest_user_id();

        Boolean result = productService.save(product_id, model.getApp_id(), model.getProduct_category_id(), model.getProduct_brand_id(), model.getProduct_name(), model.getProduct_image(),
                model.getProduct_is_new(), model.getProduct_is_recommend(), model.getProduct_is_bargain(), model.getProduct_is_hot(), model.getProduct_is_sold_out(), model.getProduct_is_virtual(),
                model.getProduct_content(), request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey(Url.PRODUCT_SYSTEM_UPDATE)
    public void systemUpdate() {
        validateRequest_app_id();
        validate(Product.PRODUCT_ID, Product.PRODUCT_CATEGORY_ID, Product.PRODUCT_BRAND_ID, Product.PRODUCT_NAME, Product.PRODUCT_IMAGE, Product.PRODUCT_IS_NEW, Product.PRODUCT_IS_RECOMMEND,
                Product.PRODUCT_IS_BARGAIN, Product.PRODUCT_IS_HOT, Product.PRODUCT_IS_SOLD_OUT, Product.PRODUCT_IS_VIRTUAL, Product.PRODUCT_CONTENT, Product.PRODUCT_STATUS, Product.SYSTEM_VERSION);

        Product model = getModel(Product.class);
        String request_user_id = getRequest_user_id();

        Boolean result = productService.updateValidateSystem_version(model.getProduct_id(), model.getProduct_category_id(), model.getProduct_brand_id(), model.getProduct_name(),
                model.getProduct_image(), model.getProduct_is_new(), model.getProduct_is_recommend(), model.getProduct_is_bargain(), model.getProduct_is_hot(), model.getProduct_is_sold_out(),
                model.getProduct_is_virtual(), model.getProduct_content(), model.getProduct_status(), request_user_id, model.getSystem_version());

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
        List<ProductSkuCommission> productSkuCommissionSaveList = new ArrayList<ProductSkuCommission>();

        // 新增SKU
        for (int i = 0; i < jsonArray.size(); i++) {
            Boolean isPrice = false;
            Boolean isAttribute = false;
            Boolean isCommission = false;

            JSONObject jsonObject = jsonArray.getJSONObject(i);
            Boolean product_sku_is_default = jsonObject.getBoolean(ProductSku.PRODUCT_SKU_IS_DEFAULT);

            JSONArray productSkuPriceJSONArray = jsonObject.getJSONArray(ProductSku.PRODUCT_SKU_PRICE_LIST);
            JSONArray productSkuAttributeJSONArray = jsonObject.getJSONArray(ProductSku.PRODUCT_SKU_ATTRIBUTE_LIST);
            JSONArray productSkuCommissionJSONArray = jsonObject.getJSONArray(ProductSku.PRODUCT_SKU_COMMISSION_LIST);

            for (ProductSku productSku : productSkuList) {
                // 对比SKU价格
                isPrice = comparePrice(productSku.getProduct_sku_id(), productSkuPriceJSONArray);

                // 对比SKU属性
                isAttribute = compareAttribute(productSku.getProduct_sku_id(), productSkuAttributeJSONArray);

                // 对比SKU佣金
                isCommission = compareCommission(productSku.getProduct_sku_id(), productSkuCommissionJSONArray);
            }

            if (isPrice && isAttribute && isCommission) {

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

                for (int j = 0; j < productSkuCommissionJSONArray.size(); j++) {
                    JSONObject productSkuCommissioJsonObject = productSkuCommissionJSONArray.getJSONObject(j);

                    ProductSkuCommission productSkuCommission = new ProductSkuCommission();
                    productSkuCommission.setProduct_sku_id(product_sku_id);
                    productSkuCommission.setMember_level_id(productSkuCommissioJsonObject.getString(ProductSkuCommission.MEMBER_LEVEL_ID));
                    productSkuCommission.setMember_level_name(productSkuCommissioJsonObject.getString(ProductSkuCommission.MEMBER_LEVEL_NAME));
                    productSkuCommission.setProduct_sku_commission(productSkuCommissioJsonObject.getInteger(ProductSkuCommission.PRODUCT_SKU_COMMISSION));
                    productSkuCommissionSaveList.add(productSkuCommission);
                }
            }
        }

        // 删除SKU
        for (ProductSku productSku : productSkuList) {
            Boolean isPrice = false;
            Boolean isAttribute = false;
            Boolean isCommission = false;

            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                JSONArray productSkuPriceJSONArray = jsonObject.getJSONArray(ProductSku.PRODUCT_SKU_PRICE_LIST);
                JSONArray productSkuAttributeJSONArray = jsonObject.getJSONArray(ProductSku.PRODUCT_SKU_ATTRIBUTE_LIST);
                JSONArray productSkuCommissioJSONArray = jsonObject.getJSONArray(ProductSku.PRODUCT_SKU_COMMISSION_LIST);

                // 对比SKU价格
                isPrice = comparePrice(productSku.getProduct_sku_id(), productSkuPriceJSONArray);

                // 对比SKU属性
                isAttribute = compareAttribute(productSku.getProduct_sku_id(), productSkuAttributeJSONArray);

                // 对比SKU佣金
                isCommission = compareCommission(productSku.getProduct_sku_id(), productSkuCommissioJSONArray);
            }

            if (isPrice && isAttribute && isCommission) {

            } else {
                productSkuIdDeleteList.add(productSku.getProduct_sku_id());
            }
        }

        productSkuService.save(product_id, productSkuSaveList, request_user_id);
        productSkuService.delete(product_id, productSkuIdDeleteList, request_user_id);
        productSkuPriceService.save(productSkuPriceSaveList, request_user_id);
        productSkuPriceService.delete(productSkuIdDeleteList, request_user_id);
        productSkuCommissionService.save(productSkuCommissionSaveList, request_user_id);
        productSkuCommissionService.delete(productSkuIdDeleteList, request_user_id);
    }

    private Boolean comparePrice(String product_sku_id, JSONArray productSkuPriceJSONArray) {
        Boolean result = false;

        int count = 0;
        List<ProductSkuPrice> productSkuPriceList = productSkuPriceService.listByProduct_sku_id(product_sku_id);

        if (productSkuPriceList.size() != productSkuPriceJSONArray.size()) {
            return false;
        }

        for (ProductSkuPrice productSkuPrice : productSkuPriceList) {
            Boolean isExit = false;

            for (int j = 0; j < productSkuPriceJSONArray.size(); j++) {
                JSONObject productSkuPriceJSONObject = productSkuPriceJSONArray.getJSONObject(j);

                if (productSkuPrice.getMember_level_id().equals(productSkuPriceJSONObject.getString(ProductSkuPrice.MEMBER_LEVEL_ID))
                        && productSkuPrice.getMember_level_name().equals(productSkuPriceJSONObject.getString(ProductSkuPrice.MEMBER_LEVEL_NAME))
                        && productSkuPrice.getProduct_sku_price().compareTo(productSkuPriceJSONObject.getBigDecimal(ProductSkuPrice.PRODUCT_SKU_PRICE)) == 0) {
                    isExit = true;

                    break;

                }
            }

            if (isExit) {
                count++;
            }
        }
        if (count == productSkuPriceList.size()) {
            result = true;
        }

        return result;
    }

    private Boolean compareAttribute(String product_sku_id, JSONArray productSkuAttributeJSONArray) {
        Boolean result = false;

        int count = 0;
        List<ProductSkuAttribute> productSkuAttributeList = productSkuAttributeService.listByProduct_sku_id(product_sku_id);

        if (productSkuAttributeList.size() != productSkuAttributeJSONArray.size()) {
            return false;
        }

        for (ProductSkuAttribute productSkuAttribute : productSkuAttributeList) {
            Boolean isExit = false;

            for (int j = 0; j < productSkuAttributeJSONArray.size(); j++) {
                JSONObject productSkuAttributeJSONObject = productSkuAttributeJSONArray.getJSONObject(j);

                if (productSkuAttribute.getProduct_sku_attribute_name().equals(productSkuAttributeJSONObject.getString(ProductSkuAttribute.PRODUCT_SKU_ATTRIBUTE_NAME))
                        && productSkuAttribute.getProduct_sku_attribute_value().equals(productSkuAttributeJSONObject.getString(ProductSkuAttribute.PRODUCT_SKU_ATTRIBUTE_VALUE))) {
                    isExit = true;

                    break;
                }
            }

            if (isExit) {
                count++;
            }
        }
        if (count == productSkuAttributeList.size()) {
            result = true;
        }

        return result;
    }

    private Boolean compareCommission(String product_sku_id, JSONArray productSkuCommissioJSONArray) {
        Boolean result = false;

        int count = 0;
        List<ProductSkuCommission> productSkuCommissionList = productSkuCommissionService.listByProduct_sku_id(product_sku_id);

        if (productSkuCommissionList.size() != productSkuCommissioJSONArray.size()) {
            return false;
        }

        for (ProductSkuCommission productSkuCommission : productSkuCommissionList) {
            Boolean isExit = false;

            for (int j = 0; j < productSkuCommissioJSONArray.size(); j++) {
                JSONObject productSkuCommissioJSONObject = productSkuCommissioJSONArray.getJSONObject(j);

                if (productSkuCommission.getMember_level_id().equals(productSkuCommissioJSONObject.getString(ProductSkuCommission.MEMBER_LEVEL_ID))
                        && productSkuCommission.getMember_level_name().equals(productSkuCommissioJSONObject.getString(ProductSkuCommission.MEMBER_LEVEL_NAME))
                        && productSkuCommission.getProduct_sku_commission().equals(productSkuCommissioJSONObject.getInteger(ProductSkuCommission.PRODUCT_SKU_COMMISSION))) {
                    isExit = true;

                    break;
                }
            }

            if (isExit) {
                count++;
            }
        }
        if (count == productSkuCommissionList.size()) {
            result = true;
        }

        return result;
    }

}