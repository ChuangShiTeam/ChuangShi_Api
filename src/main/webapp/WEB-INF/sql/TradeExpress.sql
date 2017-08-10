#namespace("trade_express")

  #sql("listByTrade_id")
    SELECT
    express_id
    FROM table_trade_express
    WHERE system_status = 1
    AND trade_id = #p(trade_id)
    ORDER BY system_create_time DESC
  #end

  #sql("save")
    INSERT INTO table_trade_express (
      trade_id,
      express_id,
      system_create_user_id,
      system_create_time,
      system_update_user_id,
      system_update_time,
      system_version,
      system_status
    ) VALUES (
      #p(trade_id),
      #p(express_id),
      #p(system_create_user_id),
      #p(system_create_time),
      #p(system_update_user_id),
      #p(system_update_time),
      #p(system_version),
      #p(system_status)
    )
  #end
  
  #sql("findByExpress_id")
    SELECT
    trade_id
    FROM table_trade_express
    WHERE system_status = 1
    AND express_id = #p(express_id)
  #end

  #sql("deleteByTrade_id")
    UPDATE table_trade_express SET
    system_update_user_id = #p(system_update_user_id),
    system_update_time = #p(system_update_time),
    system_version = system_version + 1,
    system_status = 0
    WHERE system_status = 1
    AND trade_id = #p(trade_id)
  #end
  
  #sql("deleteByTrade_idAndExpress_id")
    UPDATE table_trade_express SET
    system_update_user_id = #p(system_update_user_id),
    system_update_time = #p(system_update_time),
    system_version = system_version + 1,
    system_status = 0
    WHERE system_status = 1
    AND trade_id = #p(trade_id)
    AND express_id = #p(express_id)
  #end

#end

#end