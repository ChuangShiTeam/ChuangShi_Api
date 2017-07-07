#namespace("trade_product_sku")

  #sql("listByTrade_id")
    SELECT
    *
    FROM table_trade_product_sku
    WHERE system_status = 1
    AND trade_id = #p(trade_id)
    ORDER BY system_create_time DESC
  #end
  
  #sql("save")
    INSERT INTO table_trade_product_sku (
      trade_id,
      product_sku_id,
      product_snap_id,
      product_sku_quantity,
      product_sku_amount,
      system_create_user_id,
      system_create_time,
      system_update_user_id,
      system_update_time,
      system_version,
      system_status
    ) VALUES (
      #p(trade_id),
      #p(product_sku_id),
      #p(product_snap_id),
      #p(product_sku_quantity),
      #p(product_sku_amount),
      #p(system_create_user_id),
      #p(system_create_time),
      #p(system_update_user_id),
      #p(system_update_time),
      #p(system_version),
      #p(system_status)
    )
  #end

  #sql("deleteByTrade_idAndSystem_version")
    UPDATE table_trade_product_sku SET
    system_update_user_id = #p(system_update_user_id),
    system_update_time = #p(system_update_time),
    system_version = system_version + 1,
    system_status = 0
    WHERE system_status = 1
    AND trade_id = #p(trade_id)
    AND system_version = #p(system_version)
  #end

#end