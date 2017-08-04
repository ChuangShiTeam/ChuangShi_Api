package com.nowui.chuangshi.api.article.dao;

import com.nowui.chuangshi.api.article.model.Article;
import com.nowui.chuangshi.common.dao.Dao;

public class ArticleDao extends Dao {

    public ArticleDao() {
        setClazz(Article.class);
    }

}