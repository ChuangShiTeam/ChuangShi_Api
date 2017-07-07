#namespace("trade_pay")

  #sql("countByApp_idOrLikeTrade_pay_name")
    SELECT COUNT(*) FROM table_trade_pay
    WHERE system_status = 1
    AND app_id = #p(app_id)
    #if(trade_pay_name)
    #set(trade_pay_name = "%" + trade_pay_name + "%")
    AND trade_pay_name LIKE #p(trade_pay_name)
    #end
  #end

  #sql("countByOrApp_idOrLikeTrade_pay_name")
    SELECT COUNT(*) FROM table_trade_pay
    WHERE system_status = 1
    #if(app_id)
    AND app_id = #p(app_id)
    #end
    #if(trade_pay_name)
    #set(trade_pay_name = "%" + trade_pay_name + "%")
    AND trade_pay_name LIKE #p(trade_pay_name)
    #end
  #end

  #sql("listByApp_idAndSystem_create_timeAndLimit")
    SELECT
    trade_pay_id
    FROM table_trade_pay
    WHERE system_status = 1
    AND app_id = #p(app_id)
    AND system_create_time < UNIX_TIMESTAMP(#p(system_create_time))
    ORDER BY system_create_time DESC
    LIMIT #p(m), #p(n)
  #end

  #sql("listByApp_idOrLikeTrade_pay_nameAndLimit")
    SELECT
    trade_pay_id
    FROM table_trade_pay
    WHERE system_status = 1
    AND app_id = #p(app_id)
    #if(trade_pay_name)
    #set(trade_pay_name = "%" + trade_pay_name + "%")
    AND trade_pay_name LIKE #p(trade_pay_name)
    #end
    ORDER BY system_create_time DESC
    LIMIT #p(m), #p(n)
  #end

  #sql("listByOrApp_idOrLikeTrade_pay_nameAndLimit")
    SELECT
    trade_pay_id
    FROM table_trade_pay
    WHERE system_status = 1
    #if(app_id)
    AND app_id = #p(app_id)
    #end
    #if(trade_pay_name)
    #set(trade_pay_name = "%" + trade_pay_name + "%")
    AND trade_pay_name LIKE #p(trade_pay_name)
    #end
    ORDER BY system_create_time DESC
    LIMIT #p(m), #p(n)
  #end

  #sql("findByTrade_pay_id")
    SELECT
    *
    FROM table_trade_pay
    WHERE system_status = 1
    AND trade_pay_id = #p(trade_pay_id)
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

  #sql("update")
    UPDATE table_trade_pay SET
    trade_id = #p(trade_id),
    trade_pay_type = #p(trade_pay_type),
    trade_pay_number = #p(trade_pay_number),
    trade_pay_account = #p(trade_pay_account),
    trade_pay_time = #p(trade_pay_time),
    trade_pay_result = #p(trade_pay_result),
    system_update_user_id = #p(system_update_user_id),
    system_update_time = #p(system_update_time),
    system_version = system_version + 1
    WHERE system_status = 1
    AND trade_pay_id = #p(trade_pay_id)
    AND system_version = #p(system_version)
  #end

  #sql("deleteByTrade_pay_idAndSystem_version")
    UPDATE table_trade_pay SET
    system_update_user_id = #p(system_update_user_id),
    system_update_time = #p(system_update_time),
    system_version = system_version + 1,
    system_status = 0
    WHERE system_status = 1
    AND trade_pay_id = #p(trade_pay_id)
    AND system_version = #p(system_version)
  #end

#end