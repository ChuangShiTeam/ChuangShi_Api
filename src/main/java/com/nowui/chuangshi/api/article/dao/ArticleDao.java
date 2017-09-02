package com.nowui.chuangshi.api.article.dao;

import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.SqlPara;
import com.nowui.chuangshi.api.article.model.Article;
import com.nowui.chuangshi.common.dao.Dao;
import com.nowui.chuangshi.constant.Constant;

import java.util.List;

public class ArticleDao extends Dao {

    private final Article article = new Article();

    public ArticleDao() {
        setModel(article);
    }

    public List<Article> topCategoryList(List<String> articleCategoryIdList, Integer n) {
        Kv sqlMap = Kv.create();
        sqlMap.put("articleCategoryIdList", articleCategoryIdList);
        sqlMap.put(Constant.N, n);
        SqlPara sqlPara = Db.getSqlPara("article.topCategoryList", sqlMap);

        logSql("article", "topCategoryList", sqlPara);

        return article.find(sqlPara.getSql(), sqlPara.getPara());
    }

}