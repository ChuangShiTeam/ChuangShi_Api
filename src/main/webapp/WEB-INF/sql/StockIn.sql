#namespace("stock_in")

  #sql("countByApp_idOrLikeStock_in_name")
    SELECT COUNT(*) FROM table_stock_in
    WHERE system_status = 1
    AND app_id = #p(app_id)
    #if(stock_in_name)
    #set(stock_in_name = "%" + stock_in_name + "%")
    AND stock_in_name LIKE #p(stock_in_name)
    #end
  #end

  #sql("countByOrApp_idOrLikeStock_in_name")
    SELECT COUNT(*) FROM table_stock_in
    WHERE system_status = 1
    #if(app_id)
    AND app_id = #p(app_id)
    #end
    #if(stock_in_name)
    #set(stock_in_name = "%" + stock_in_name + "%")
    AND stock_in_name LIKE #p(stock_in_name)
    #end
  #end

  #sql("listByApp_idAndSystem_create_timeAndLimit")
    SELECT
    stock_in_id
    FROM table_stock_in
    WHERE system_status = 1
    AND app_id = #p(app_id)
    AND system_create_time < UNIX_TIMESTAMP(#p(system_create_time))
    ORDER BY system_create_time DESC
    LIMIT #p(m), #p(n)
  #end

  #sql("listByApp_idOrLikeStock_in_nameAndLimit")
    SELECT
    stock_in_id
    FROM table_stock_in
    WHERE system_status = 1
    AND app_id = #p(app_id)
    #if(stock_in_name)
    #set(stock_in_name = "%" + stock_in_name + "%")
    AND stock_in_name LIKE #p(stock_in_name)
    #end
    ORDER BY system_create_time DESC
    LIMIT #p(m), #p(n)
  #end

  #sql("listByOrApp_idOrLikeStock_in_nameAndLimit")
    SELECT
    stock_in_id
    FROM table_stock_in
    WHERE system_status = 1
    #if(app_id)
    AND app_id = #p(app_id)
    #end
    #if(stock_in_name)
    #set(stock_in_name = "%" + stock_in_name + "%")
    AND stock_in_name LIKE #p(stock_in_name)
    #end
    ORDER BY system_create_time DESC
    LIMIT #p(m), #p(n)
  #end

  #sql("findByStock_in_id")
    SELECT
    *
    FROM table_stock_in
    WHERE system_status = 1
    AND stock_in_id = #p(stock_in_id)
  #end

  #sql("save")
    INSERT INTO table_stock_in (
      stock_in_id,
      app_id,
      warehouse_id,
      trade_id,
      object_id,
      stock_in_type,
      stock_in_quantity,
      stock_in_status,
      system_create_user_id,
      system_create_time,
      system_update_user_id,
      system_update_time,
      system_version,
      system_status
    ) VALUES (
      #p(stock_in_id),
      #p(app_id),
      #p(warehouse_id),
      #p(trade_id),
      #p(object_id),
      #p(stock_in_type),
      #p(stock_in_quantity),
      #p(stock_in_status),
      #p(system_create_user_id),
      #p(system_create_time),
      #p(system_update_user_id),
      #p(system_update_time),
      #p(system_version),
      #p(system_status)
    )
  #end

  #sql("update")
    UPDATE table_stock_in SET
    warehouse_id = #p(warehouse_id),
    trade_id = #p(trade_id),
    object_id = #p(object_id),
    stock_in_type = #p(stock_in_type),
    stock_in_quantity = #p(stock_in_quantity),
    stock_in_status = #p(stock_in_status),
    system_update_user_id = #p(system_update_user_id),
    system_update_time = #p(system_update_time),
    system_version = system_version + 1
    WHERE system_status = 1
    AND stock_in_id = #p(stock_in_id)
    AND system_version = #p(system_version)
  #end

  #sql("deleteByStock_in_idAndSystem_version")
    UPDATE table_stock_in SET
    system_update_user_id = #p(system_update_user_id),
    system_update_time = #p(system_update_time),
    system_version = system_version + 1,
    system_status = 0
    WHERE system_status = 1
    AND stock_in_id = #p(stock_in_id)
    AND system_version = #p(system_version)
  #end

#end