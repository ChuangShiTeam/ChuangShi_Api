package com.nowui.chuangshi.api.feijiu.mobile;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.article.model.Article;
import com.nowui.chuangshi.api.article.model.ArticleCategory;
import com.nowui.chuangshi.api.article.service.ArticleCategoryService;
import com.nowui.chuangshi.api.article.service.ArticleService;
import com.nowui.chuangshi.api.captcha.model.Captcha;
import com.nowui.chuangshi.api.captcha.service.CaptchaService;
import com.nowui.chuangshi.api.feijiu.model.FeijiuFastProduct;
import com.nowui.chuangshi.api.feijiu.model.FeijiuFastProductCategory;
import com.nowui.chuangshi.api.feijiu.service.FeijiuFastProductCategoryService;
import com.nowui.chuangshi.api.feijiu.service.FeijiuFastProductService;
import com.nowui.chuangshi.api.file.service.FileService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.type.CaptchaType;

import java.util.*;

@ControllerKey("/mobile/feijiu/fast")
public class FeijiuFastController extends Controller {

    @ActionKey("/mobile/feijiu/fast/index")
    public void index() {
        String request_app_id = getRequest_app_id();

        Map<String, Object> result = new HashMap<String, Object>();

        List<FeijiuFastProductCategory> productCategoryList = FeijiuFastProductCategoryService.instance.appList(request_app_id);
        for (FeijiuFastProductCategory feijiuFastProductCategory : productCategoryList) {
            feijiuFastProductCategory.keep(FeijiuFastProductCategory.PRODUCT_CATEGORY_ID, FeijiuFastProductCategory.PRODUCT_CATEGORY_NAME);
        }
        result.put("product_category_list", productCategoryList);

        List<FeijiuFastProduct> productList = FeijiuFastProductService.instance.appList(request_app_id);
        for (FeijiuFastProduct feijiuFastProduct : productList) {
            feijiuFastProduct.put(FeijiuFastProduct.PRODUCT_IMAGE, FileService.instance.getFile_path(feijiuFastProduct.getProduct_image()));

            feijiuFastProduct.keep(FeijiuFastProduct.PRODUCT_ID, FeijiuFastProduct.PRODUCT_CATEGORY_ID, FeijiuFastProduct.PRODUCT_NAME, FeijiuFastProduct.PRODUCT_IMAGE, FeijiuFastProduct.PRODUCT_CONTENT, FeijiuFastProduct.PRODUCT_LINK, FeijiuFastProduct.PRODUCT_APPLICANT_QUANTITY);
        }
        result.put("product_list", productList);

        validateResponse("product_category_list", "product_list");

        renderSuccessJson(result);
    }

    @ActionKey("/mobile/feijiu/fast/article/list")
    public void articleList() {
        String request_app_id = getRequest_app_id();

        List<ArticleCategory> articleCategoryList = ArticleCategoryService.instance.appList(request_app_id);
        List<Article> articleList = ArticleService.instance.appList(request_app_id);

        for (Article article : articleList) {
            for (ArticleCategory articleCategory : articleCategoryList) {
                if (article.getArticle_category_id().equals(article.getArticle_category_id())) {
                    article.put(ArticleCategory.ARTICLE_CATEGORY_NAME, article.getArticle_name());

                    break;
                }
            }

            article.setArticle_image(FileService.instance.getFile_path(article.getArticle_image()));
        }

        validateResponse(Article.ARTICLE_ID, Article.ARTICLE_IMAGE, Article.ARTICLE_NAME, Article.ARTICLE_SUMMARY);

        renderSuccessJson(articleList);
    }

    @ActionKey("/mobile/feijiu/fast/captcha/send")
    public void captchaSend() {
        String request_app_id = getRequest_app_id();

        JSONObject jsonObject = getParameterJSONObject();
        String captcha_type = CaptchaType.REGISTER.getKey();
        String captcha_mobile = jsonObject.getString(Captcha.CAPTCHA_MOBILE);
        String captcha_ip_address = getIp_address();
        String access_id = "LTAIhCq5kicoklR1";
        String access_key = "Chr002BuHEQozE7cleDSJf7IofBQcY";
        String endpoint = "https://1202946.mns.cn-hangzhou.aliyuncs.com/";
        String sign_name = "久飞";
        String template_code = "SMS_87765001";

        CaptchaService.instance.send(request_app_id, captcha_type, captcha_mobile, captcha_ip_address, 1, access_id, access_key, endpoint, sign_name, template_code);

        renderSuccessJson();
    }

    @ActionKey("/mobile/feijiu/fast/captcha/check")
    public void captchaCheck() {
        String request_app_id = getRequest_app_id();

        JSONObject jsonObject = getParameterJSONObject();
        String captcha_mobile = jsonObject.getString(Captcha.CAPTCHA_MOBILE);
        String captcha_code = jsonObject.getString(Captcha.CAPTCHA_CODE);

        Integer count = CaptchaService.instance.count(request_app_id, captcha_mobile, captcha_code);

        if (count == 0) {
            throw new RuntimeException("验证码不正确");
        }

        renderSuccessJson();
    }

}
