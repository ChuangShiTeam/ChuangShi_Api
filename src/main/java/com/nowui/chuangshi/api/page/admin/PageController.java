package com.nowui.chuangshi.api.page.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.jfinal.kit.Kv;
import com.nowui.chuangshi.api.advertisement.service.AdvertisementService;
import com.nowui.chuangshi.api.article.model.Article;
import com.nowui.chuangshi.api.article.model.ArticleCategory;
import com.nowui.chuangshi.api.article.service.ArticleCategoryService;
import com.nowui.chuangshi.api.article.service.ArticleService;
import com.nowui.chuangshi.api.page.model.Page;
import com.nowui.chuangshi.api.page.service.PageService;
import com.nowui.chuangshi.api.website.model.WebsiteMenu;
import com.nowui.chuangshi.api.website.service.WebsiteMenuService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.common.interceptor.AdminInterceptor;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.util.Util;

@Before(AdminInterceptor.class)
@ControllerKey("/admin/page")
public class PageController extends Controller {

    @ActionKey("/admin/page/list")
    public void list() {
        validateRequest(Page.PAGE_NAME, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        Page model = getModel(Page.class);
        String request_app_id = getRequest_app_id();

        Integer resultCount = PageService.instance.adminCount(request_app_id, model.getPage_name());
        List<Page> resultList = PageService.instance.adminList(request_app_id, model.getPage_name(), getM(), getN());

        validateResponse(Page.PAGE_ID, Page.PAGE_NAME, Page.PAGE_TEMPLATE, Page.PAGE_URL, Page.PAGE_SORT, Page.SYSTEM_VERSION);

        renderSuccessJson(resultCount, resultList);
    }

    @ActionKey("/admin/page/find")
    public void find() {
        validateRequest(Page.PAGE_ID);

        Page model = getModel(Page.class);

        Page result = PageService.instance.find(model.getPage_id());

        validateResponse(Page.WEBSITE_MENU_ID, Page.PAGE_NAME, Page.PAGE_TEMPLATE, Page.PAGE_URL, Page.PAGE_CONTENT, Page.PAGE_SORT, Page.SYSTEM_VERSION);

        renderSuccessJson(result);
    }

//    @ActionKey("/admin/page/all/write")
//    public void allWrite() {
//        String request_app_id = getRequest_app_id();
//
//        List<ArticleCategory> articleCategoryList = ArticleCategoryService.instance.appList(request_app_id);
//
//        List<Article> articleList = ArticleService.instance.topCategoryList(articleCategoryList, 7);
//
//        List<Page> pageList = PageService.instance.appList(request_app_id);
//
//        List<Map<String, Object>> websiteMenuList = WebsiteMenuService.instance.tree(request_app_id);
//
//        List<Map<String, Object>> indexBannerList = AdvertisementService.instance.adminCategoryCodeList(request_app_id, "index_banner");
//
//        List<Map<String, Object>> indexFloatList = AdvertisementService.instance.adminCategoryCodeList(request_app_id, "index_float");
//
//        Kv templateMap = Kv.create();
//        templateMap.put("articleCategoryList", articleCategoryList);
//        templateMap.put("articleList", articleList);
//        templateMap.put("websiteMenuList", websiteMenuList);
//        templateMap.put("indexBannerList", indexBannerList);
//        templateMap.put("indexFloatList", indexFloatList);
//
//        if (articleCategoryList != null && articleCategoryList.size() > 0) {
//            ArticleCategory articleCategory = articleCategoryList.get(0);
//
//            templateMap.put("article_category_id", articleCategory.getArticle_category_id());
//        }
//
//        for (Page page : pageList) {
//            templateMap.put("page_name", page.getPage_name());
//            templateMap.put("page_content", page.getPage_content());
//            templateMap.put("page_url", page.getPage_url());
//            if (page.getPage_template().equals("xydt.template")) {
//                for (ArticleCategory articleCatgory : articleCategoryList) {
//                	PageService.instance.writeXydt(request_app_id, articleCatgory.getArticle_category_id());
//                }
//            } else if (page.getPage_template().equals("wzxq.template")) {
//              //生成所有文章页面
//                List<Article> allArticleList = ArticleService.instance.appList(request_app_id);
//                if (allArticleList != null && allArticleList.size() > 0) {
//                    for (Article article : allArticleList) {
//                        PageService.instance.writeWzxq(request_app_id, article.getArticle_id());
//                    }
//                }
//            } else {
//                PageService.instance.write(request_app_id, page, templateMap);
//            }
//
//        }
//
//
//
//        renderSuccessJson();
//    }

    @ActionKey("/admin/page/write")
    public void write() {
        validateRequest(Page.PAGE_ID);

        Page model = getModel(Page.class);
        String request_app_id = getRequest_app_id();

        Page page = PageService.instance.find(model.getPage_id());

        List<Map<String, Object>> websiteMenuList = WebsiteMenuService.instance.tree(request_app_id);
        
        List<Map<String, Object>> indexBannerList = AdvertisementService.instance.adminCategoryCodeList(request_app_id, "index_banner");

        Kv templateMap = Kv.create();
        templateMap.put("websiteMenuList", websiteMenuList);
        templateMap.put("indexBannerList", indexBannerList);
        templateMap.put("page_name", page.getPage_name());
        templateMap.put("page_content", page.getPage_content());
        templateMap.put("page_url", page.getPage_url());

        if (page.getPage_url().equals("index.html")) {
            List<ArticleCategory> articleCategoryList = ArticleCategoryService.instance.appList(request_app_id);
            templateMap.put("articleCategoryList", articleCategoryList);
            
            if (articleCategoryList != null && articleCategoryList.size() > 0) {
                ArticleCategory articleCategory = articleCategoryList.get(0);
                
                templateMap.put("article_category_id", articleCategory.getArticle_category_id());
            }

            List<Article> articleList = ArticleService.instance.topCategoryList(articleCategoryList, 7);
            templateMap.put("articleList", articleList);
            
            List<Map<String, Object>> indexFloatList = AdvertisementService.instance.adminCategoryCodeList(request_app_id, "index_float");
            templateMap.put("indexFloatList", indexFloatList);
        } 
        if (page.getPage_template().equals("xydt.template")) {
            List<ArticleCategory> articleCategoryList = ArticleCategoryService.instance.appList(request_app_id);
            for (ArticleCategory articleCatgory : articleCategoryList) {
            	PageService.instance.writeXydt(request_app_id, articleCatgory.getArticle_category_id());
            }
            
        } else if (page.getPage_template().equals("wzxq.template")) {
           //根据模板生成所有文章页面
            List<Article> allArticleList = ArticleService.instance.appList(request_app_id);
            if (allArticleList != null && allArticleList.size() > 0) {
                for (Article article : allArticleList) {
                    PageService.instance.writeWzxq(request_app_id, article.getArticle_id());
                }
            }
        } else {
//            PageService.instance.write(request_app_id, page, templateMap);
        }
        
        renderSuccessJson();
    }
    
    @ActionKey("/admin/page/save")
    public void save() {
        validateRequest(Page.WEBSITE_MENU_ID, Page.PAGE_NAME, Page.PAGE_TEMPLATE, Page.PAGE_URL, Page.PAGE_CONTENT, Page.PAGE_SORT);

        Page model = getModel(Page.class);
        model.setPage_id(Util.getRandomUUID());
        String request_user_id = getRequest_user_id();

        Boolean result = PageService.instance.save(model, request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/page/update")
    public void update() {
        validateRequest(Page.PAGE_ID, Page.WEBSITE_MENU_ID, Page.PAGE_NAME, Page.PAGE_TEMPLATE, Page.PAGE_URL, Page.PAGE_CONTENT, Page.PAGE_SORT, Page.SYSTEM_VERSION);

        Page model = getModel(Page.class);
        String request_user_id = getRequest_user_id();

        Boolean result = PageService.instance.update(model, model.getPage_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey("/admin/page/delete")
    public void delete() {
        validateRequest(Page.PAGE_ID, Page.SYSTEM_VERSION);

        Page model = getModel(Page.class);
        String request_user_id = getRequest_user_id();

        Boolean result = PageService.instance.delete(model.getPage_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey("/admin/page/all/write")
    public void allWrite() {
        String request_app_id = getRequest_app_id();

        List<ArticleCategory> articleCategoryList = ArticleCategoryService.instance.appList(request_app_id);

        List<Article> articleList = ArticleService.instance.topCategoryList(articleCategoryList, 7);

        List<Page> pageList = PageService.instance.appList(request_app_id);

        List<Map<String, Object>> websiteMenuList = WebsiteMenuService.instance.tree(request_app_id);

        List<Map<String, Object>> indexBannerList = AdvertisementService.instance.adminCategoryCodeList(request_app_id, "index_banner");

        List<Map<String, Object>> indexFloatList = AdvertisementService.instance.adminCategoryCodeList(request_app_id, "index_float");

        Kv templateMap = Kv.create();
        templateMap.put("articleCategoryList", articleCategoryList);
        templateMap.put("articleList", articleList);
        templateMap.put("websiteMenuList", websiteMenuList);
        templateMap.put("indexBannerList", indexBannerList);

        if (articleCategoryList != null && articleCategoryList.size() > 0) {
            ArticleCategory articleCategory = articleCategoryList.get(0);

            templateMap.put("article_category_id", articleCategory.getArticle_category_id());
        }


        PageService.instance.write(request_app_id, "header.template", "header.js", templateMap);
        PageService.instance.write(request_app_id, "footer.template", "footer.js", templateMap);
        PageService.instance.write(request_app_id, "index.template", "index.html", templateMap);


        for (Page page : pageList) {
            Map<String, Object> websiteMenu = new HashMap<String, Object>();
            for (Map<String, Object> websiteMenuMap : websiteMenuList) {
                List<Map<String, Object>> childrenwebsiteMenuList = (List<Map<String, Object>>) websiteMenuMap.get(Constant.CHILDREN);
                for (Map<String, Object> childrenwebsiteMenuMap : childrenwebsiteMenuList) {
                    if (((String) childrenwebsiteMenuMap.get(WebsiteMenu.WEBSITE_MENU_ID)).equals(page.getWebsite_menu_id())) {
                        websiteMenu = websiteMenuMap;
                    }
                }
            }
            templateMap.put("websiteMenu", websiteMenu);

            templateMap.put("page", page);
            if (page.getPage_template().equals("detail.template")) {
                PageService.instance.write(request_app_id, "detail.template", page.getPage_url(), templateMap);
            }
        }

        renderSuccessJson();
    }

}