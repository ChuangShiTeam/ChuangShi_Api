#namespace("product_category")

  #sql("listByApp_id")
    SELECT
    product_category_id
    FROM table_product_category
    WHERE system_status = 1
    AND app_id = #p(app_id)
    ORDER BY system_create_time DESC
  #end

  #sql("listByLikeProduct_category_parent_id")
    SELECT
    product_category_id
    FROM table_product_category
    WHERE system_status = 1
    AND product_category_path LIKE #p("%" + product_category_parent_id + "%")
  #end

  #sql("listByApp_idOrLikeProduct_category_name")
    SELECT
    *
    FROM table_product_category
    WHERE system_status = 1
    AND app_id = #p(app_id)
    AND product_category_parent_id != "0"
    UNION ALL
    SELECT
    *
    FROM table_product_category
    WHERE system_status = 1
    AND app_id = #p(app_id)
    AND product_category_parent_id = "0"
    #if(product_category_name)
    #set(product_category_name = "%" + product_category_name + "%")
    AND product_category_name LIKE #p(product_category_name)
    #end
    ORDER BY product_category_sort ASC, system_create_time DESC
  #end

  #sql("listByOrApp_idOrLikeProduct_category_name")
    SELECT
    *
    FROM table_product_category
    WHERE system_status = 1
    #if(app_id)
    AND app_id = #p(app_id)
    #end
    AND product_category_parent_id != "0"
    UNION ALL
    SELECT
    *
    FROM table_product_category
    WHERE system_status = 1
    #if(app_id)
    AND app_id = #p(app_id)
    #end
    AND product_category_parent_id = "0"
    #if(product_category_name)
    #set(product_category_name = "%" + product_category_name + "%")
    AND product_category_name LIKE #p(product_category_name)
    #end
    ORDER BY product_category_sort ASC, system_create_time DESC
  #end

  #sql("findByProduct_category_id")
    SELECT
    *
    FROM table_product_category
    WHERE system_status = 1
    AND product_category_id = #p(product_category_id)
  #end

  #sql("save")
    INSERT INTO table_product_category (
      product_category_id,
      app_id,
      product_category_parent_id,
      product_category_name,
      product_category_sort,
      product_category_path,
      system_create_user_id,
      system_create_time,
      system_update_user_id,
      system_update_time,
      system_version,
      system_status
    ) VALUES (
      #p(product_category_id),
      #p(app_id),
      #p(product_category_parent_id),
      #p(product_category_name),
      #p(product_category_sort),
      #p(product_category_path),
      #p(system_create_user_id),
      #p(system_create_time),
      #p(system_update_user_id),
      #p(system_update_time),
      #p(system_version),
      #p(system_status)
    )
  #end

  #sql("update")
    UPDATE table_product_category SET
    product_category_parent_id = #p(product_category_parent_id),
    product_category_name = #p(product_category_name),
    product_category_sort = #p(product_category_sort),
    product_category_path = #p(product_category_path),
    system_update_user_id = #p(system_update_user_id),
    system_update_time = #p(system_update_time),
    system_version = system_version + 1
    WHERE system_status = 1
    AND product_category_id = #p(product_category_id)
    AND system_version = #p(system_version)
  #end

  #sql("deleteByProduct_category_idAndSystem_version")
    UPDATE table_product_category SET
    system_update_user_id = #p(system_update_user_id),
    system_update_time = #p(system_update_time),
    system_version = system_version + 1,
    system_status = 0
    WHERE system_status = 1
    AND product_category_id = #p(product_category_id)
    AND system_version = #p(system_version)
  #end

  #sql("deleteByProduct_category_parent_id")
    UPDATE table_product_category SET
    system_update_user_id = #p(system_update_user_id),
    system_update_time = #p(system_update_time),
    system_version = system_version + 1,
    system_status = 0
    WHERE system_status = 1
    AND product_category_parent_id = #p(product_category_parent_id)
  #end

#end