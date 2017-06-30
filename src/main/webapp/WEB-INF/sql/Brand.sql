#namespace("brand")

  #sql("countByApp_idOrLikeBrand_name")
    SELECT COUNT(*) FROM table_brand
    WHERE system_status = 1
    AND app_id = #p(app_id)
    #if(brand_name)
    #set(brand_name = "%" + brand_name + "%")
    AND brand_name LIKE #p(brand_name)
    #end
  #end

  #sql("countByOrApp_idOrLikeBrand_name")
    SELECT COUNT(*) FROM table_brand
    WHERE system_status = 1
    #if(app_id)
    AND app_id = #p(app_id)
    #end
    #if(brand_name)
    #set(brand_name = "%" + brand_name + "%")
    AND brand_name LIKE #p(brand_name)
    #end
  #end

  #sql("listByApp_idAndSystem_create_timeAndLimit")
    SELECT
    brand_id
    FROM table_brand
    WHERE system_status = 1
    AND app_id = #p(app_id)
    AND system_create_time < UNIX_TIMESTAMP(#p(system_create_time))
    ORDER BY system_create_time DESC
    LIMIT #p(m), #p(n)
  #end

  #sql("listByApp_idOrLikeBrand_nameAndLimit")
    SELECT
    brand_id
    FROM table_brand
    WHERE system_status = 1
    AND app_id = #p(app_id)
    #if(brand_name)
    #set(brand_name = "%" + brand_name + "%")
    AND brand_name LIKE #p(brand_name)
    #end
    ORDER BY system_create_time DESC
    LIMIT #p(m), #p(n)
  #end

  #sql("listByOrApp_idOrLikeBrand_nameAndLimit")
    SELECT
    brand_id
    FROM table_brand
    WHERE system_status = 1
    #if(app_id)
    AND app_id = #p(app_id)
    #end
    #if(brand_name)
    #set(brand_name = "%" + brand_name + "%")
    AND brand_name LIKE #p(brand_name)
    #end
    ORDER BY system_create_time DESC
    LIMIT #p(m), #p(n)
  #end

  #sql("findByBrand_id")
    SELECT
    *
    FROM table_brand
    WHERE system_status = 1
    AND brand_id = #p(brand_id)
  #end

  #sql("save")
    INSERT INTO table_brand (
      brand_id,
      app_id,
      category_id,
      brand_name,
      brand_image,
      brand_content,
      system_create_user_id,
      system_create_time,
      system_update_user_id,
      system_update_time,
      system_version,
      system_status
    ) VALUES (
      #p(brand_id),
      #p(app_id),
      #p(category_id),
      #p(brand_name),
      #p(brand_image),
      #p(brand_content),
      #p(system_create_user_id),
      #p(system_create_time),
      #p(system_update_user_id),
      #p(system_update_time),
      #p(system_version),
      #p(system_status)
    )
  #end

  #sql("update")
    UPDATE table_brand SET
    category_id = #p(category_id),
    brand_name = #p(brand_name),
    brand_image = #p(brand_image),
    brand_content = #p(brand_content),
    system_update_user_id = #p(system_update_user_id),
    system_update_time = #p(system_update_time),
    system_version = system_version + 1
    WHERE system_status = 1
    AND brand_id = #p(brand_id)
    AND system_version = #p(system_version)
  #end

  #sql("deleteByBrand_idAndSystem_version")
    UPDATE table_brand SET
    system_update_user_id = #p(system_update_user_id),
    system_update_time = #p(system_update_time),
    system_version = system_version + 1,
    system_status = 0
    WHERE system_status = 1
    AND brand_id = #p(brand_id)
    AND system_version = #p(system_version)
  #end

#end