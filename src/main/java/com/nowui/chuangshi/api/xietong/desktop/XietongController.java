package com.nowui.chuangshi.api.xietong.desktop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.advertisement.model.Advertisement;
import com.nowui.chuangshi.api.advertisement.service.AdvertisementService;
import com.nowui.chuangshi.api.article.model.Article;
import com.nowui.chuangshi.api.article.model.ArticleCategory;
import com.nowui.chuangshi.api.article.service.ArticleCategoryService;
import com.nowui.chuangshi.api.article.service.ArticleService;
import com.nowui.chuangshi.api.file.model.File;
import com.nowui.chuangshi.api.page.model.Page;
import com.nowui.chuangshi.api.website.model.WebsiteMenu;
import com.nowui.chuangshi.api.website.service.WebsiteMenuService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.common.interceptor.AdminInterceptor;

@Before(AdminInterceptor.class)
@ControllerKey("/desktop/xietong")
public class XietongController extends Controller {

    @ActionKey("/desktop/xietong/website/index")
    public void index() {
        List<String> articleCategoryIdList = new ArrayList<String>();
        articleCategoryIdList.add("c9dd8759a7a04aaeb038973c3246d863");
        articleCategoryIdList.add("7e84950e6d96412b860b5be55f46d5e8");
        articleCategoryIdList.add("0cc726f2b92f43d1ba5cc5d0065efb09");
        articleCategoryIdList.add("da9b1750e8ea4f959df23cbdcba53f9a");

        List<Article> articleList = ArticleService.instance.topCategoryList(articleCategoryIdList, 7);

        validateResponse(Article.ARTICLE_ID, File.FILE_PATH, Article.ARTICLE_NAME, Article.SYSTEM_CREATE_TIME);

        renderSuccessJson(articleList);
    }

    @ActionKey("/desktop/xietong/website/init")
    public void init() {
        String request_app_id = getRequest_app_id();

        Map<String, Object> result = new HashMap<String, Object>();

        List<Map<String, Object>> websiteMenuList = WebsiteMenuService.instance.appTree(request_app_id, WebsiteMenu.WEBSITE_MENU_ID, WebsiteMenu.WEBSITE_MENU_NAME, WebsiteMenu.WEBSITE_MENU_URL, Page.PAGE_ID);

        List<Advertisement> advertisementList = AdvertisementService.instance.appList(request_app_id);
        for (Advertisement advertisement : advertisementList) {
            advertisement.keep(Advertisement.ADVERTISEMENT_ID, Advertisement.ADVERTISEMENT_TITLE, Advertisement.ADVERTISEMENT_LINK, File.FILE_ORIGINAL_PATH);
        }

        List<ArticleCategory> articleCategoryList = ArticleCategoryService.instance.appList(request_app_id);
        for (ArticleCategory articleCategory : articleCategoryList) {
            articleCategory.keep(ArticleCategory.ARTICLE_CATEGORY_ID, ArticleCategory.ARTICLE_CATEGORY_NAME);
        }

        result.put("website_menu_list", websiteMenuList);
        result.put("advertisement_list", advertisementList);
        result.put("article_category_list", articleCategoryList);

        validateResponse("website_menu_list", "advertisement_list", "article_category_list");

        renderSuccessJson(result);
    }

}