package com.nowui.chuangshi.api.page.service;

import java.util.List;
import java.util.Map;

import com.jfinal.kit.Kv;
import com.jfinal.kit.PathKit;
import com.jfinal.template.Engine;
import com.jfinal.template.Template;
import com.nowui.chuangshi.api.advertisement.service.AdvertisementService;
import com.nowui.chuangshi.api.app.model.App;
import com.nowui.chuangshi.api.app.service.AppService;
import com.nowui.chuangshi.api.article.model.Article;
import com.nowui.chuangshi.api.article.model.ArticleCategory;
import com.nowui.chuangshi.api.article.service.ArticleCategoryService;
import com.nowui.chuangshi.api.article.service.ArticleService;
import com.nowui.chuangshi.api.page.dao.PageDao;
import com.nowui.chuangshi.api.page.model.Page;
import com.nowui.chuangshi.api.website.service.WebsiteMenuService;
import com.nowui.chuangshi.common.service.Service;
import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.constant.Config;
import com.nowui.chuangshi.util.CacheUtil;
import com.nowui.chuangshi.util.FileUtil;
import com.nowui.chuangshi.util.ValidateUtil;

public class PageService extends Service {

    public static final PageService instance = new PageService();
    private final String PAGE_ITEM_CACHE = "page_item_cache";
    private final PageDao pageDao = new PageDao();
    
    private static Engine engine = Engine.create("page_engine");

    public Integer adminCount(String app_id, String page_name) {
        Cnd cnd = new Cnd();
        cnd.where(Page.SYSTEM_STATUS, true);
        cnd.and(Page.APP_ID, app_id);
        cnd.andAllowEmpty(Page.PAGE_NAME, page_name);

        Integer count = pageDao.count(cnd);
        return count;
    }

    public List<Page> adminList(String app_id, String page_name, Integer m, Integer n) {
        Cnd cnd = new Cnd();
        cnd.where(Page.SYSTEM_STATUS, true);
        cnd.and(Page.APP_ID, app_id);
        cnd.andAllowEmpty(Page.PAGE_NAME, page_name);
        cnd.asc(Page.PAGE_SORT);
        cnd.paginate(m, n);

        List<Page> pageList = pageDao.primaryKeyList(cnd);
        for (Page page : pageList) {
            page.put(find(page.getPage_id()));
        }
        return pageList;
    }

    public List<Page> appList(String app_id) {
        Cnd cnd = new Cnd();
        cnd.where(Page.SYSTEM_STATUS, true);
        cnd.and(Page.APP_ID, app_id);

        List<Page> pageList = pageDao.list(cnd);
        for (Page page : pageList) {
            page.put(find(page.getPage_id()));
        }
        return pageList;
    }

    public Page find(String page_id) {
        Page page = CacheUtil.get(PAGE_ITEM_CACHE, page_id);

        if (page == null) {
            page = pageDao.find(page_id);

            CacheUtil.put(PAGE_ITEM_CACHE, page_id, page);
        }

        return page;
    }

    public Boolean save(Page page, String system_create_user_id) {
        Boolean success = pageDao.save(page, system_create_user_id);
        return success;
    }

    public Boolean update(Page page, String page_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(Page.SYSTEM_STATUS, true);
        cnd.and(Page.PAGE_ID, page_id);
        cnd.and(Page.SYSTEM_VERSION, system_version);

        Boolean success = pageDao.update(page, system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(PAGE_ITEM_CACHE, page_id);
        }

        return success;
    }

    public Boolean delete(String page_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(Page.SYSTEM_STATUS, true);
        cnd.and(Page.PAGE_ID, page_id);
        cnd.and(Page.SYSTEM_VERSION, system_version);

        Boolean success = pageDao.delete(system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(PAGE_ITEM_CACHE, page_id);
        }

        return success;
    }
    
    public void write(String app_id, Page page, Kv templateMap) {
        PageService.engine.setBaseTemplatePath(PathKit.getWebRootPath() + "/WEB-INF/template/xietong/");

        Template template = PageService.engine.getTemplate(page.getPage_template());

        String content = template.renderToString(templateMap);

        App app = AppService.instance.find(app_id);

        if (ValidateUtil.isNullOrEmpty(app.getApp_website_path())) {
            throw new RuntimeException("路径不能为空");
        }

        FileUtil.writeFile(content, app.getApp_website_path() + page.getPage_url());
    }
    
    /**
     * 根据文章详情模板生成文章详情页面
     * @param app_id
     * @param article_id
     * @param is_update_prev_and_next 是否更新文章上篇和下篇链接
     */
    public void writeWzxq(String app_id, String article_id, Boolean is_update_prev_and_next) {
        Article article = ArticleService.instance.find(article_id);
        
        //文章非外部链接生成文章详情页面
        if (!article.getArticle_is_outer_link()) {
            
            ArticleCategory articleCategory = ArticleCategoryService.instance.find(article.getArticle_category_id());
            
            List<ArticleCategory> articleCategoryList = ArticleCategoryService.instance.appList(app_id);

            List<Map<String, Object>> websiteMenuList = WebsiteMenuService.instance.tree(app_id);
            
            List<Map<String, Object>> indexBannerList = AdvertisementService.instance.adminCategoryCodeList(app_id, "index_banner");
            
            Article prevArticle = ArticleService.instance.prevArticle(article_id);
            
            Article nextArticle = ArticleService.instance.nextArticle(article_id);

            Kv templateMap = Kv.create();
            templateMap.put("host", Config.host);
            templateMap.put("articleCategoryList", articleCategoryList);
            templateMap.put("article", article);
            templateMap.put("articleCategory", articleCategory);
            templateMap.put("websiteMenuList", websiteMenuList);
            templateMap.put("indexBannerList", indexBannerList);
            templateMap.put("prevArticle", prevArticle);
            templateMap.put("nextArticle", nextArticle);
            
            engine.setBaseTemplatePath(PathKit.getWebRootPath() + "/WEB-INF/template/xietong/");

            Template template = engine.getTemplate("wzxq.template");

            String content = template.renderToString(templateMap);

            App app = AppService.instance.find(app_id);

            if (ValidateUtil.isNullOrEmpty(app.getApp_website_path())) {
                throw new RuntimeException("路径不能为空");
            }

            FileUtil.writeFile(content, app.getApp_website_path() + article.getArticle_id() + ".html");
            if (is_update_prev_and_next) {
                //生成成功后要更新一下上一篇和下一篇对应的链接
                if (prevArticle != null && !ValidateUtil.isNullOrEmpty(prevArticle.getArticle_id())) {
                    writeWzxq(app_id, prevArticle.getArticle_id(), false);
                } 
                
                if (nextArticle != null && !ValidateUtil.isNullOrEmpty(nextArticle.getArticle_id())) {
                    writeWzxq(app_id, nextArticle.getArticle_id(), false);
                }  
            }
        }
    }


}