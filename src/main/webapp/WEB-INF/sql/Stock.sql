#namespace("stock")

  #sql("countByApp_idOrLikeStock_type")
    SELECT COUNT(*) FROM table_stock
    WHERE system_status = 1
    AND app_id = #p(app_id)
    #if(stock_type)
    #set(stock_type = "%" + stock_type + "%")
    AND stock_type LIKE #p(stock_type)
    #end
  #end

  #sql("countByOrApp_idOrLikeStock_type")
    SELECT COUNT(*) FROM table_stock
    WHERE system_status = 1
    #if(app_id)
    AND app_id = #p(app_id)
    #end
    #if(stock_type)
    #set(stock_type = "%" + stock_type + "%")
    AND stock_type LIKE #p(stock_type)
    #end
  #end

  #sql("listByApp_idAndSystem_create_timeAndLimit")
    SELECT
    stock_id
    FROM table_stock
    WHERE system_status = 1
    AND app_id = #p(app_id)
    AND system_create_time < UNIX_TIMESTAMP(#p(system_create_time))
    ORDER BY system_create_time DESC
    LIMIT #p(m), #p(n)
  #end

  #sql("listByApp_idOrLikeStock_typeAndLimit")
    SELECT
    stock_id
    FROM table_stock
    WHERE system_status = 1
    AND app_id = #p(app_id)
    #if(stock_type)
    #set(stock_type = "%" + stock_type + "%")
    AND stock_type LIKE #p(stock_type)
    #end
    ORDER BY system_create_time DESC
    LIMIT #p(m), #p(n)
  #end

  #sql("listByOrApp_idOrLikeStock_typeAndLimit")
    SELECT
    stock_id
    FROM table_stock
    WHERE system_status = 1
    #if(app_id)
    AND app_id = #p(app_id)
    #end
    #if(stock_type)
    #set(stock_type = "%" + stock_type + "%")
    AND stock_type LIKE #p(stock_type)
    #end
    ORDER BY system_create_time DESC
    LIMIT #p(m), #p(n)
  #end

  #sql("findByStock_id")
    SELECT
    *
    FROM table_stock
    WHERE system_status = 1
    AND stock_id = #p(stock_id)
  #end

  #sql("save")
    INSERT INTO table_stock (
      stock_id,
      app_id,
      product_sku_id,
      object_id,
      stock_type,
      stock_quantity,
      system_create_user_id,
      system_create_time,
      system_update_user_id,
      system_update_time,
      system_version,
      system_status
    ) VALUES (
      #p(stock_id),
      #p(app_id),
      #p(product_sku_id),
      #p(object_id),
      #p(stock_type),
      #p(stock_quantity),
      #p(system_create_user_id),
      #p(system_create_time),
      #p(system_update_user_id),
      #p(system_update_time),
      #p(system_version),
      #p(system_status)
    )
  #end

  #sql("update")
    UPDATE table_stock SET
    product_sku_id = #p(product_sku_id),
    object_id = #p(object_id),
    stock_type = #p(stock_type),
    stock_quantity = #p(stock_quantity),
    system_update_user_id = #p(system_update_user_id),
    system_update_time = #p(system_update_time),
    system_version = system_version + 1
    WHERE system_status = 1
    AND stock_id = #p(stock_id)
    AND system_version = #p(system_version)
  #end

  #sql("deleteByStock_idAndSystem_version")
    UPDATE table_stock SET
    system_update_user_id = #p(system_update_user_id),
    system_update_time = #p(system_update_time),
    system_version = system_version + 1,
    system_status = 0
    WHERE system_status = 1
    AND stock_id = #p(stock_id)
    AND system_version = #p(system_version)
  #end

#end