package com.nowui.chuangshi.api.xietong.desktop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.advertisement.model.Advertisement;
import com.nowui.chuangshi.api.advertisement.service.AdvertisementService;
import com.nowui.chuangshi.api.article.model.Article;
import com.nowui.chuangshi.api.article.model.ArticleCategory;
import com.nowui.chuangshi.api.article.service.ArticleCategoryService;
import com.nowui.chuangshi.api.article.service.ArticleService;
import com.nowui.chuangshi.api.file.model.File;
import com.nowui.chuangshi.api.file.service.FileService;
import com.nowui.chuangshi.api.page.model.Page;
import com.nowui.chuangshi.api.website.model.WebsiteMenu;
import com.nowui.chuangshi.api.website.service.WebsiteMenuService;
import com.nowui.chuangshi.api.xietong.model.XietongClazz;
import com.nowui.chuangshi.api.xietong.model.XietongStudent;
import com.nowui.chuangshi.api.xietong.model.XietongTeacher;
import com.nowui.chuangshi.api.xietong.service.XietongStudentService;
import com.nowui.chuangshi.api.xietong.service.XietongTeacherService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;



@ControllerKey("/desktop/xietong")
public class XietongController extends Controller {

    @ActionKey("/desktop/xietong/website/index")
    public void index() {
        List<String> articleCategoryIdList = new ArrayList<String>();
        articleCategoryIdList.add("c9dd8759a7a04aaeb038973c3246d863");
        articleCategoryIdList.add("7e84950e6d96412b860b5be55f46d5e8");
        articleCategoryIdList.add("0cc726f2b92f43d1ba5cc5d0065efb09");
        articleCategoryIdList.add("da9b1750e8ea4f959df23cbdcba53f9a");

        List<Article> articleList = ArticleService.instance.topCategoryList(articleCategoryIdList, 4);

        validateResponse(Article.ARTICLE_ID, Article.ARTICLE_CATEGORY_ID, File.FILE_PATH, Article.ARTICLE_NAME, Article.ARTICLE_OUTER_LINK, Article.ARTICLE_IS_OUTER_LINK, Article.ARTICLE_SUMMARY, Article.SYSTEM_CREATE_TIME);

        renderSuccessJson(articleList);
    }
    
    @ActionKey("/desktop/xietong/website/department/init")
    public void primarySchool() {
        validateRequest(XietongClazz.ORGANIZATION_ID);
        
        JSONObject jsonObject = getParameterJSONObject();
        
        Map<String, Object> result = new HashMap<String, Object>();
        List<String> articleCategoryIdList = new ArrayList<String>();
        articleCategoryIdList.add("9d8508d24242499ebcf344e17d8222de");
        articleCategoryIdList.add("9c204d00ccd446298c22cff6350bb6ff");

        List<Article> articleList = ArticleService.instance.topCategoryList(articleCategoryIdList, 4);

        for (Article article : articleList) {
            article.keep(Article.ARTICLE_ID, Article.ARTICLE_CATEGORY_ID, File.FILE_PATH, Article.ARTICLE_NAME, Article.ARTICLE_SUMMARY, Article.SYSTEM_CREATE_TIME);
        }
        
        List<XietongStudent> studentList = XietongStudentService.instance.organizationAndCategoryList(jsonObject.getString(XietongClazz.ORGANIZATION_ID), "858ccb59a12047d5ad0525a6f3f1ce9c", 0, 10);

        for (XietongStudent student : studentList) {
            student.put(XietongStudent.STUDENT_IMAGE, FileService.instance.getFile(student.getStudent_image()).getFile_path());
            student.keep(XietongStudent.STUDENT_ID, XietongStudent.STUDENT_IMAGE, XietongStudent.STUDENT_NAME);
        }
        
        result.put("studentList", studentList);
        
        result.put("articleList", articleList);
        
        validateResponse("articleList", "studentList");
        
        renderSuccessJson(articleList);
    }

    @ActionKey("/desktop/xietong/website/init")
    public void init() {
        String request_app_id = "749388e5dac3465f922c54e61d16a993";

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

    @ActionKey("/desktop/xietong/primary/school/init")
    public void primarySchoolInit() {
        String request_app_id = "749388e5dac3465f922c54e61d16a993";

        Map<String, Object> result = new HashMap<String, Object>();

        List<XietongTeacher> teacherList = XietongTeacherService.instance.organizationList("6fd70c5e490e403b844ca722e0a5d756");
        for (XietongTeacher teacher : teacherList) {
            teacher.keep(XietongTeacher.TEACHER_ID, XietongTeacher.TEACHER_NAME, File.FILE_PATH);
        }

        List<XietongTeacher> teacherList2 = XietongTeacherService.instance.categoryList("5a3e3e701efe486383eaa54dbbe80467");
        for (XietongTeacher teacher : teacherList2) {
            teacher.keep(XietongTeacher.TEACHER_ID, XietongTeacher.TEACHER_NAME, File.FILE_PATH);
        }

        List<String> clazzIdList = new ArrayList<String>();
        clazzIdList.add("71d14fdd1565426f8a8669ee682f7f37");

        List<XietongStudent> studentList = XietongStudentService.instance.clazzList(clazzIdList, "1d49d03576954b3c998608b8f43be324", 0, 5);
        for (XietongStudent student : studentList) {
            student.keep(XietongStudent.STUDENT_ID, XietongStudent.STUDENT_NAME, File.FILE_PATH);
        }

        List<Article> articleList = ArticleService.instance.categoryList("194dfd824f2042d58dd101d374272455");
        for (Article article : articleList) {
            article.keep(Article.ARTICLE_ID, Article.ARTICLE_NAME, File.FILE_PATH);
        }

        result.put("teacher_list", teacherList);
        result.put("teacher_list_2", teacherList2);
        result.put("student_list", studentList);
        result.put("article_list", articleList);

        validateResponse("teacher_list", "student_list", "article_list");

        renderSuccessJson(result);
    }

}