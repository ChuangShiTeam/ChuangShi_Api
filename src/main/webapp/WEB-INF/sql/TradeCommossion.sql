#namespace("trade_commossion")

  #sql("listByTrade_id")
    SELECT
    *
    FROM table_trade_commossion
    WHERE system_status = 1
    AND trade_id = #p(trade_id)
    ORDER BY system_create_time DESC
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

  #sql("deleteByTrade_idAndSystem_version")
    UPDATE table_trade_commossion SET
    system_update_user_id = #p(system_update_user_id),
    system_update_time = #p(system_update_time),
    system_version = system_version + 1,
    system_status = 0
    WHERE system_status = 1
    AND trade_id = #p(trade_id)
    AND system_version = #p(system_version)
  #end

#end