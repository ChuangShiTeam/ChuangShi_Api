#namespace("trade_pay")

  #sql("listByTrade_id")
    SELECT
    *
    FROM table_trade_pay
    WHERE system_status = 1
    AND trade_id = #p(trade_id)
    ORDER BY system_create_time DESC
  #end
  
  #sql("save")
    INSERT INTO table_trade_pay (
      trade_id,
      trade_pay_type,
      trade_pay_number,
      trade_pay_account,
      trade_pay_time,
      trade_pay_result,
      system_create_user_id,
      system_create_time,
      system_update_user_id,
      system_update_time,
      system_version,
      system_status
    ) VALUES (
      #p(trade_id),
      #p(trade_pay_type),
      #p(trade_pay_number),
      #p(trade_pay_account),
      #p(trade_pay_time),
      #p(trade_pay_result),
      #p(system_create_user_id),
      #p(system_create_time),
      #p(system_update_user_id),
      #p(system_update_time),
      #p(system_version),
      #p(system_status)
    )
  #end

  #sql("deleteByTrade_idAndSystem_version")
    UPDATE table_trade_pay SET
    system_update_user_id = #p(system_update_user_id),
    system_update_time = #p(system_update_time),
    system_version = system_version + 1,
    system_status = 0
    WHERE system_status = 1
    AND trade_id = #p(trade_id)
    AND system_version = #p(system_version)
  #end

#end