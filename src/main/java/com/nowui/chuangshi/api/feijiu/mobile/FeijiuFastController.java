package com.nowui.chuangshi.api.feijiu.mobile;

import com.alibaba.fastjson.JSONObject;
import com.aliyun.mns.client.CloudAccount;
import com.aliyun.mns.client.CloudTopic;
import com.aliyun.mns.client.MNSClient;
import com.aliyun.mns.common.ServiceException;
import com.aliyun.mns.model.BatchSmsAttributes;
import com.aliyun.mns.model.MessageAttributes;
import com.aliyun.mns.model.RawTopicMessage;
import com.aliyun.mns.model.TopicMessage;
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
import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.type.CaptchaType;
import com.nowui.chuangshi.util.ValidateUtil;

import java.util.*;

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

    @ActionKey("/mobile/feijiu/fast/article/list")
    public void articleList() {
        String request_app_id = getRequest_app_id();

        Cnd cnd = Cnd.where(Article.APP_ID, request_app_id);

        List<ArticleCategory> articleCategoryList = ArticleCategoryService.me.list(cnd);
        List<Article> articleList = ArticleService.me.list(cnd);

        for (Article article : articleList) {
            for (ArticleCategory articleCategory : articleCategoryList) {
                if (article.getArticle_category_id().equals(article.getArticle_category_id())) {
                    article.put(ArticleCategory.PRODUCT_CATEGORY_NAME, article.getArticle_name());

                    break;
                }
            }

            article.setArticle_image(FileService.me.getFile_path(article.getArticle_image()));
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
        String access_id = "LTAIENw7el5fDsZv";
        String access_key = "XowMkKu4zjxGBIpPJqDXXK7wfWO7i9";
        String endpoint = "https://1202946.mns.cn-hangzhou.aliyuncs.com/";
        String sign_name = "创石";
        String template_code = "SMS_86675110";

        CaptchaService.me.send(request_app_id, captcha_type, captcha_mobile, captcha_ip_address, 1, access_id, access_key, endpoint, sign_name, template_code);

        renderSuccessJson();
    }

    @ActionKey("/mobile/feijiu/fast/captcha/check")
    public void captchaCheck() {
        String request_app_id = getRequest_app_id();

        JSONObject jsonObject = getParameterJSONObject();
        String captcha_mobile = jsonObject.getString(Captcha.CAPTCHA_MOBILE);
        String captcha_code = jsonObject.getString(Captcha.CAPTCHA_CODE);

        Integer count = CaptchaService.me.count(Cnd.where(Captcha.APP_ID, request_app_id).and(Captcha.CAPTCHA_MOBILE, captcha_mobile).and(Captcha.CAPTCHA_CODE, captcha_code));

        if (count == 0) {
            throw new RuntimeException("验证码不正确");
        }

        renderSuccessJson();
    }

}
