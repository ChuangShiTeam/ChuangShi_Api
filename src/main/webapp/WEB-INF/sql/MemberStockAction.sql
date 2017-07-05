#namespace("member_stock_action")

  #sql("countByApp_idOrLikeMember_stock_action_name")
    SELECT COUNT(*) FROM table_member_stock_action
    WHERE system_status = 1
    AND app_id = #p(app_id)
    #if(member_stock_action_name)
    #set(member_stock_action_name = "%" + member_stock_action_name + "%")
    AND member_stock_action_name LIKE #p(member_stock_action_name)
    #end
  #end

  #sql("countByOrApp_idOrLikeMember_stock_action_name")
    SELECT COUNT(*) FROM table_member_stock_action
    WHERE system_status = 1
    #if(app_id)
    AND app_id = #p(app_id)
    #end
    #if(member_stock_action_name)
    #set(member_stock_action_name = "%" + member_stock_action_name + "%")
    AND member_stock_action_name LIKE #p(member_stock_action_name)
    #end
  #end

  #sql("listByApp_idAndSystem_create_timeAndLimit")
    SELECT
    member_stock_action_id
    FROM table_member_stock_action
    WHERE system_status = 1
    AND app_id = #p(app_id)
    AND system_create_time < UNIX_TIMESTAMP(#p(system_create_time))
    ORDER BY system_create_time DESC
    LIMIT #p(m), #p(n)
  #end

  #sql("listByApp_idOrLikeMember_stock_action_nameAndLimit")
    SELECT
    member_stock_action_id
    FROM table_member_stock_action
    WHERE system_status = 1
    AND app_id = #p(app_id)
    #if(member_stock_action_name)
    #set(member_stock_action_name = "%" + member_stock_action_name + "%")
    AND member_stock_action_name LIKE #p(member_stock_action_name)
    #end
    ORDER BY system_create_time DESC
    LIMIT #p(m), #p(n)
  #end

  #sql("listByOrApp_idOrLikeMember_stock_action_nameAndLimit")
    SELECT
    member_stock_action_id
    FROM table_member_stock_action
    WHERE system_status = 1
    #if(app_id)
    AND app_id = #p(app_id)
    #end
    #if(member_stock_action_name)
    #set(member_stock_action_name = "%" + member_stock_action_name + "%")
    AND member_stock_action_name LIKE #p(member_stock_action_name)
    #end
    ORDER BY system_create_time DESC
    LIMIT #p(m), #p(n)
  #end

  #sql("findByMember_stock_action_id")
    SELECT
    *
    FROM table_member_stock_action
    WHERE system_status = 1
    AND member_stock_action_id = #p(member_stock_action_id)
  #end

  #sql("save")
    INSERT INTO table_member_stock_action (
      member_stock_action_id,
      app_id,
      member_id,
      user_id,
      product_sku_id,
      member_stock_action_name,
      member_stock_quantity,
      system_create_user_id,
      system_create_time,
      system_update_user_id,
      system_update_time,
      system_version,
      system_status
    ) VALUES (
      #p(member_stock_action_id),
      #p(app_id),
      #p(member_id),
      #p(user_id),
      #p(product_sku_id),
      #p(member_stock_action_name),
      #p(member_stock_quantity),
      #p(system_create_user_id),
      #p(system_create_time),
      #p(system_update_user_id),
      #p(system_update_time),
      #p(system_version),
      #p(system_status)
    )
  #end

  #sql("update")
    UPDATE table_member_stock_action SET
    member_id = #p(member_id),
    user_id = #p(user_id),
    product_sku_id = #p(product_sku_id),
    member_stock_action_name = #p(member_stock_action_name),
    member_stock_quantity = #p(member_stock_quantity),
    system_update_user_id = #p(system_update_user_id),
    system_update_time = #p(system_update_time),
    system_version = system_version + 1
    WHERE system_status = 1
    AND member_stock_action_id = #p(member_stock_action_id)
    AND system_version = #p(system_version)
  #end

  #sql("deleteByMember_stock_action_idAndSystem_version")
    UPDATE table_member_stock_action SET
    system_update_user_id = #p(system_update_user_id),
    system_update_time = #p(system_update_time),
    system_version = system_version + 1,
    system_status = 0
    WHERE system_status = 1
    AND member_stock_action_id = #p(member_stock_action_id)
    AND system_version = #p(system_version)
  #end

#end