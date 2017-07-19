#namespace("stock_replenish")

  #sql("countByApp_idAndStock_replenish_typeOrLikeUser_name")
    SELECT COUNT(*) FROM table_stock_replenish
    #if(stock_replenish_type == 'MEMBER' && user_name)
    LEFT JOIN table_user ON table_user.user_id = table_stock_replenish.object_id
    #end
    WHERE table_stock_replenish.system_status = 1
    AND table_stock_replenish.app_id = #p(app_id)
    AND table_stock_replenish.stock_replenish_type = #p(stock_replenish_type)
    #if(stock_replenish_type == 'MEMBER' && user_name)
    #set(user_name = "%" + user_name + "%")
    AND table_stock_replenish.user_name LIKE #p(user_name)
    #end
  #end

  #sql("countByOrApp_idAndStock_replenish_typeOrLikeUser_name")
    SELECT COUNT(*) FROM table_stock_replenish
    #if(stock_replenish_type == 'MEMBER' && user_name)
    LEFT JOIN table_user ON table_user.user_id = table_stock_replenish.object_id
    #end
    WHERE table_stock_replenish.system_status = 1
    #if(app_id)
    AND app_id = #p(app_id)
    #end
    AND table_stock_replenish.stock_replenish_type = #p(stock_replenish_type)
    #if(stock_replenish_type == 'MEMBER' && user_name)
    #set(user_name = "%" + user_name + "%")
    AND table_stock_replenish.user_name LIKE #p(user_name)
    #end
  #end

  #sql("listByApp_idAndStock_replenish_typeAndSystem_create_timeAndLimit")
    SELECT
    stock_replenish_id
    FROM table_stock_replenish
    WHERE system_status = 1
    AND app_id = #p(app_id)
    AND stock_replenish_type = #p(stock_replenish_type)
    AND system_create_time < UNIX_TIMESTAMP(#p(system_create_time))
    ORDER BY system_create_time DESC
    LIMIT #p(m), #p(n)
  #end

  #sql("listByApp_idAndStock_replenish_typeOrLikeUser_nameAndLimit")
    SELECT
    table_stock_replenish.stock_replenish_id
    FROM table_stock_replenish
    #if(stock_replenish_type == 'MEMBER' && user_name)
    LEFT JOIN table_user ON table_user.user_id = table_stock_replenish.object_id
    #end
    WHERE table_stock_replenish.system_status = 1
    AND table_stock_replenish.app_id = #p(app_id)
    AND table_stock_replenish.stock_replenish_type = #p(stock_replenish_type)
    #if(stock_replenish_type == 'MEMBER' && user_name)
    #set(user_name = "%" + user_name + "%")
    AND table_user.user_name LIKE #p(user_name)
    #end
    ORDER BY table_stock_replenish.system_create_time DESC
    LIMIT #p(m), #p(n)
  #end

  #sql("listByOrApp_idAndStock_replenish_typeOrLikeUser_nameAndLimit")
    SELECT
    table_stock_replenish.stock_replenish_id
    FROM table_stock_replenish
    #if(stock_replenish_type == 'MEMBER' && user_name)
    LEFT JOIN table_user ON table_user.user_id = table_stock_replenish.object_id
    #end
    WHERE table_stock_replenish.system_status = 1
    #if(app_id)
    AND table_stock_replenish.app_id = #p(app_id)
    #end
    AND table_stock_replenish.stock_replenish_type = #p(stock_replenish_type)
    #if(stock_replenish_type == 'MEMBER' && user_name)
    #set(user_name = "%" + user_name + "%")
    AND table_user.user_name LIKE #p(user_name)
    #end
    ORDER BY table_stock_replenish.system_create_time DESC
    LIMIT #p(m), #p(n)
  #end
  
  #sql("findByStock_replenish_id")
    SELECT
    *
    FROM table_stock_replenish
    WHERE system_status = 1
    AND stock_replenish_id = #p(stock_replenish_id)
  #end

  #sql("findByStock_replenish_idAndStock_replenish_type")
    SELECT
    table_stock_replenish.*
    #if(stock_replenish_type == 'MEMBER')
    ,table_user.user_name
    #end
    #if(stock_replenish_type == 'APP')
    ,table_app.app_name
    #end
    FROM table_stock_replenish
    #if(stock_replenish_type == 'MEMBER')
    LEFT JOIN table_user ON table_user.user_id = table_stock_replenish.object_id
    #end
    #if(stock_replenish_type == 'APP')
    LEFT JOIN table_app ON table_app.app_id = table_stock_replenish.object_id
    #end
    WHERE table_stock_replenish.system_status = 1
    AND table_stock_replenish.stock_replenish_id = #p(stock_replenish_id)
  #end

  #sql("save")
    INSERT INTO table_stock_replenish (
      stock_replenish_id,
      app_id,
      object_id,
      stock_replenish_type,
      stock_replenish_quantity,
      stock_replenish_status,
      system_create_user_id,
      system_create_time,
      system_update_user_id,
      system_update_time,
      system_version,
      system_status
    ) VALUES (
      #p(stock_replenish_id),
      #p(app_id),
      #p(object_id),
      #p(stock_replenish_type),
      #p(stock_replenish_quantity),
      #p(stock_replenish_status),
      #p(system_create_user_id),
      #p(system_create_time),
      #p(system_update_user_id),
      #p(system_update_time),
      #p(system_version),
      #p(system_status)
    )
  #end

  #sql("update")
    UPDATE table_stock_replenish SET
    object_id = #p(object_id),
    stock_replenish_type = #p(stock_replenish_type),
    stock_replenish_quantity = #p(stock_replenish_quantity),
    stock_replenish_status = #p(stock_replenish_status),
    system_update_user_id = #p(system_update_user_id),
    system_update_time = #p(system_update_time),
    system_version = system_version + 1
    WHERE system_status = 1
    AND stock_replenish_id = #p(stock_replenish_id)
    AND system_version = #p(system_version)
  #end

  #sql("deleteByStock_replenish_idAndSystem_version")
    UPDATE table_stock_replenish SET
    system_update_user_id = #p(system_update_user_id),
    system_update_time = #p(system_update_time),
    system_version = system_version + 1,
    system_status = 0
    WHERE system_status = 1
    AND stock_replenish_id = #p(stock_replenish_id)
    AND system_version = #p(system_version)
  #end

#end