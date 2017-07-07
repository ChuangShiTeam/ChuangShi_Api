#namespace("trade_commossion")

  #sql("countByApp_idOrLikeTrade_commossion_name")
    SELECT COUNT(*) FROM table_trade_commossion
    WHERE system_status = 1
    AND app_id = #p(app_id)
    #if(trade_commossion_name)
    #set(trade_commossion_name = "%" + trade_commossion_name + "%")
    AND trade_commossion_name LIKE #p(trade_commossion_name)
    #end
  #end

  #sql("countByOrApp_idOrLikeTrade_commossion_name")
    SELECT COUNT(*) FROM table_trade_commossion
    WHERE system_status = 1
    #if(app_id)
    AND app_id = #p(app_id)
    #end
    #if(trade_commossion_name)
    #set(trade_commossion_name = "%" + trade_commossion_name + "%")
    AND trade_commossion_name LIKE #p(trade_commossion_name)
    #end
  #end

  #sql("listByApp_idAndSystem_create_timeAndLimit")
    SELECT
    trade_commossion_id
    FROM table_trade_commossion
    WHERE system_status = 1
    AND app_id = #p(app_id)
    AND system_create_time < UNIX_TIMESTAMP(#p(system_create_time))
    ORDER BY system_create_time DESC
    LIMIT #p(m), #p(n)
  #end

  #sql("listByApp_idOrLikeTrade_commossion_nameAndLimit")
    SELECT
    trade_commossion_id
    FROM table_trade_commossion
    WHERE system_status = 1
    AND app_id = #p(app_id)
    #if(trade_commossion_name)
    #set(trade_commossion_name = "%" + trade_commossion_name + "%")
    AND trade_commossion_name LIKE #p(trade_commossion_name)
    #end
    ORDER BY system_create_time DESC
    LIMIT #p(m), #p(n)
  #end

  #sql("listByOrApp_idOrLikeTrade_commossion_nameAndLimit")
    SELECT
    trade_commossion_id
    FROM table_trade_commossion
    WHERE system_status = 1
    #if(app_id)
    AND app_id = #p(app_id)
    #end
    #if(trade_commossion_name)
    #set(trade_commossion_name = "%" + trade_commossion_name + "%")
    AND trade_commossion_name LIKE #p(trade_commossion_name)
    #end
    ORDER BY system_create_time DESC
    LIMIT #p(m), #p(n)
  #end

  #sql("findByTrade_commossion_id")
    SELECT
    *
    FROM table_trade_commossion
    WHERE system_status = 1
    AND trade_commossion_id = #p(trade_commossion_id)
  #end

  #sql("save")
    INSERT INTO table_trade_commossion (
      trade_id,
      product_sku_id,
      member_id,
      member_name,
      member_level_id,
      member_level_name,
      product_sku_commission,
      product_sku_commission_amount,
      system_create_user_id,
      system_create_time,
      system_update_user_id,
      system_update_time,
      system_version,
      system_status
    ) VALUES (
      #p(trade_id),
      #p(product_sku_id),
      #p(member_id),
      #p(member_name),
      #p(member_level_id),
      #p(member_level_name),
      #p(product_sku_commission),
      #p(product_sku_commission_amount),
      #p(system_create_user_id),
      #p(system_create_time),
      #p(system_update_user_id),
      #p(system_update_time),
      #p(system_version),
      #p(system_status)
    )
  #end

  #sql("update")
    UPDATE table_trade_commossion SET
    trade_id = #p(trade_id),
    product_sku_id = #p(product_sku_id),
    member_id = #p(member_id),
    member_name = #p(member_name),
    member_level_id = #p(member_level_id),
    member_level_name = #p(member_level_name),
    product_sku_commission = #p(product_sku_commission),
    product_sku_commission_amount = #p(product_sku_commission_amount),
    system_update_user_id = #p(system_update_user_id),
    system_update_time = #p(system_update_time),
    system_version = system_version + 1
    WHERE system_status = 1
    AND trade_commossion_id = #p(trade_commossion_id)
    AND system_version = #p(system_version)
  #end

  #sql("deleteByTrade_commossion_idAndSystem_version")
    UPDATE table_trade_commossion SET
    system_update_user_id = #p(system_update_user_id),
    system_update_time = #p(system_update_time),
    system_version = system_version + 1,
    system_status = 0
    WHERE system_status = 1
    AND trade_commossion_id = #p(trade_commossion_id)
    AND system_version = #p(system_version)
  #end

#end