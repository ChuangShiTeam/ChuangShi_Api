#namespace("product_brand")

  #sql("countByApp_idOrLikeProduct_brand_name")
    SELECT COUNT(*) FROM table_product_brand
    WHERE system_status = 1
    AND app_id = #p(app_id)
    #if(product_brand_name)
    #set(product_brand_name = "%" + product_brand_name + "%")
    AND product_brand_name LIKE #p(product_brand_name)
    #end
  #end

  #sql("countByOrApp_idOrLikeProduct_brand_name")
    SELECT COUNT(*) FROM table_product_brand
    WHERE system_status = 1
    #if(app_id)
    AND app_id = #p(app_id)
    #end
    #if(product_brand_name)
    #set(product_brand_name = "%" + product_brand_name + "%")
    AND product_brand_name LIKE #p(product_brand_name)
    #end
  #end

  #sql("listByApp_id")
    SELECT
    product_brand_id
    FROM table_product_brand
    WHERE system_status = 1
    AND app_id = #p(app_id)
    ORDER BY system_create_time DESC
  #end

  #sql("listByApp_idAndSystem_create_timeAndLimit")
    SELECT
    product_brand_id
    FROM table_product_brand
    WHERE system_status = 1
    AND app_id = #p(app_id)
    AND system_create_time < UNIX_TIMESTAMP(#p(system_create_time))
    ORDER BY system_create_time DESC
    LIMIT #p(m), #p(n)
  #end

  #sql("listByApp_idOrLikeProduct_brand_nameAndLimit")
    SELECT
    product_brand_id
    FROM table_product_brand
    WHERE system_status = 1
    AND app_id = #p(app_id)
    #if(product_brand_name)
    #set(product_brand_name = "%" + product_brand_name + "%")
    AND product_brand_name LIKE #p(product_brand_name)
    #end
    ORDER BY system_create_time DESC
    LIMIT #p(m), #p(n)
  #end

  #sql("listByOrApp_idOrLikeProduct_brand_nameAndLimit")
    SELECT
    product_brand_id
    FROM table_product_brand
    WHERE system_status = 1
    #if(app_id)
    AND app_id = #p(app_id)
    #end
    #if(product_brand_name)
    #set(product_brand_name = "%" + product_brand_name + "%")
    AND product_brand_name LIKE #p(product_brand_name)
    #end
    ORDER BY system_create_time DESC
    LIMIT #p(m), #p(n)
  #end

  #sql("findByProduct_brand_id")
    SELECT
    *
    FROM table_product_brand
    WHERE system_status = 1
    AND product_brand_id = #p(product_brand_id)
  #end

  #sql("save")
    INSERT INTO table_product_brand (
      product_brand_id,
      app_id,
      product_brand_name,
      product_brand_image,
      product_brand_content,
      system_create_user_id,
      system_create_time,
      system_update_user_id,
      system_update_time,
      system_version,
      system_status
    ) VALUES (
      #p(product_brand_id),
      #p(app_id),
      #p(product_brand_name),
      #p(product_brand_image),
      #p(product_brand_content),
      #p(system_create_user_id),
      #p(system_create_time),
      #p(system_update_user_id),
      #p(system_update_time),
      #p(system_version),
      #p(system_status)
    )
  #end

  #sql("update")
    UPDATE table_product_brand SET
    product_brand_name = #p(product_brand_name),
    product_brand_image = #p(product_brand_image),
    product_brand_content = #p(product_brand_content),
    system_update_user_id = #p(system_update_user_id),
    system_update_time = #p(system_update_time),
    system_version = system_version + 1
    WHERE system_status = 1
    AND product_brand_id = #p(product_brand_id)
    AND system_version = #p(system_version)
  #end

  #sql("deleteByProduct_brand_idAndSystem_version")
    UPDATE table_product_brand SET
    system_update_user_id = #p(system_update_user_id),
    system_update_time = #p(system_update_time),
    system_version = system_version + 1,
    system_status = 0
    WHERE system_status = 1
    AND product_brand_id = #p(product_brand_id)
    AND system_version = #p(system_version)
  #end

#end