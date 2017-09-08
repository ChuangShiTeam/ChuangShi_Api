#namespace("article")

  #sql("topCategoryList")
    #for(article_category_id : articleCategoryIdList)
    (SELECT
    table_article.*, table_file.file_path
    FROM table_article
    LEFT JOIN table_file ON table_article.article_image = table_file.file_id
    WHERE table_article.system_status = 1
    AND table_article.article_category_id = #p(article_category_id)
    LIMIT 0, #p(n))
    #if(!for.last)
    UNION ALL
    #end
    #end
  #end

#end