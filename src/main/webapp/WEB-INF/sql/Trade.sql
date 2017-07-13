#namespace("trade")

  #sql("countByApp_idOrLikeTrade_number")
    SELECT COUNT(*) FROM table_trade
    WHERE system_status = 1
    AND app_id = #p(app_id)
    #if(trade_number)
    #set(trade_number = "%" + trade_number + "%")
    AND trade_number LIKE #p(trade_number)
    #end
  #end

  #sql("countByOrApp_idOrLikeTrade_number")
    SELECT COUNT(*) FROM table_trade
    WHERE system_status = 1
    #if(app_id)
    AND app_id = #p(app_id)
    #end
    #if(trade_number)
    #set(trade_number = "%" + trade_number + "%")
    AND trade_number LIKE #p(trade_number)
    #end
  #end

  #sql("listByApp_idAndSystem_create_timeAndLimit")
    SELECT
    trade_id
    FROM table_trade
    WHERE system_status = 1
    AND app_id = #p(app_id)
    AND system_create_time < UNIX_TIMESTAMP(#p(system_create_time))
    ORDER BY system_create_time DESC
    LIMIT #p(m), #p(n)
  #end

  #sql("listByApp_idOrLikeTrade_numberAndLimit")
    SELECT
    trade_id,
    app_id,
    user_id,
    trade_number,
    trade_receiver_name,
    trade_receiver_mobile,
    trade_receiver_province,
    trade_receiver_city,
    trade_receiver_area,
    trade_receiver_address,
    trade_message,
    trade_product_quantity,
    trade_product_amount,
    trade_express_amount,
    trade_discount_amount,
    trade_is_commission,
    trade_is_confirm,
    trade_is_pay,
    trade_flow,
    trade_status,
    trade_audit_status,
    system_version
    FROM table_trade
    WHERE system_status = 1
    AND app_id = #p(app_id)
    #if(trade_number)
    #set(trade_number = "%" + trade_number + "%")
    AND trade_number LIKE #p(trade_number)
    #end
    ORDER BY system_create_time DESC
    LIMIT #p(m), #p(n)
  #end

  #sql("listByOrApp_idOrLikeTrade_numberAndLimit")
    SELECT
    trade_id
    FROM table_trade
    WHERE system_status = 1
    #if(app_id)
    AND app_id = #p(app_id)
    #end
    #if(trade_number)
    #set(trade_number = "%" + trade_number + "%")
    AND trade_number LIKE #p(trade_number)
    #end
    ORDER BY system_create_time DESC
    LIMIT #p(m), #p(n)
  #end

  #sql("listByUser_id")
    SELECT
    *
    FROM table_trade
    WHERE system_status = 1
    AND user_id = #p(user_id)
    ORDER BY system_create_time DESC
  #end

  #sql("findByTrade_id")
    SELECT
    trade_id,
    app_id,
    user_id,
    trade_number,
    trade_receiver_name,
    trade_receiver_mobile,
    trade_receiver_province,
    trade_receiver_city,
    trade_receiver_area,
    trade_receiver_address,
    trade_message,
    trade_product_quantity,
    trade_product_amount,
    trade_express_amount,
    trade_discount_amount,
    trade_is_commission,
    trade_is_confirm,
    trade_is_pay,
    trade_flow,
    trade_status,
    trade_audit_status,
    system_version
    FROM table_trade
    WHERE system_status = 1
    AND trade_id = #p(trade_id)
  #end
  
  #sql("findByTrade_number")
    SELECT
    *
    FROM table_trade
    WHERE system_status = 1
    AND trade_number = #p(trade_number)
  #end

  #sql("save")
    INSERT INTO table_trade (
      trade_id,
      app_id,
      user_id,
      trade_number,
      trade_receiver_name,
      trade_receiver_mobile,
      trade_receiver_province,
      trade_receiver_city,
      trade_receiver_area,
      trade_receiver_address,
      trade_message,
      trade_product_quantity,
      trade_product_amount,
      trade_express_amount,
      trade_discount_amount,
      trade_is_commission,
      trade_is_confirm,
      trade_is_pay,
      trade_flow,
      trade_status,
      trade_audit_status,
      system_create_user_id,
      system_create_time,
      system_update_user_id,
      system_update_time,
      system_version,
      system_status
    ) VALUES (
      #p(trade_id),
      #p(app_id),
      #p(user_id),
      #p(trade_number),
      #p(trade_receiver_name),
      #p(trade_receiver_mobile),
      #p(trade_receiver_province),
      #p(trade_receiver_city),
      #p(trade_receiver_area),
      #p(trade_receiver_address),
      #p(trade_message),
      #p(trade_product_quantity),
      #p(trade_product_amount),
      #p(trade_express_amount),
      #p(trade_discount_amount),
      #p(trade_is_commission),
      #p(trade_is_confirm),
      #p(trade_is_pay),
      #p(trade_flow),
      #p(trade_status),
      #p(trade_audit_status),
      #p(system_create_user_id),
      #p(system_create_time),
      #p(system_update_user_id),
      #p(system_update_time),
      #p(system_version),
      #p(system_status)
    )
  #end

  #sql("update")
    UPDATE table_trade SET
    user_id = #p(user_id),
    trade_number = #p(trade_number),
    trade_receiver_name = #p(trade_receiver_name),
    trade_receiver_mobile = #p(trade_receiver_mobile),
    trade_receiver_province = #p(trade_receiver_province),
    trade_receiver_city = #p(trade_receiver_city),
    trade_receiver_area = #p(trade_receiver_area),
    trade_receiver_address = #p(trade_receiver_address),
    trade_message = #p(trade_message),
    trade_product_quantity = #p(trade_product_quantity),
    trade_product_amount = #p(trade_product_amount),
    trade_express_amount = #p(trade_express_amount),
    trade_discount_amount = #p(trade_discount_amount),
    trade_is_commission = #p(trade_is_commission),
    trade_is_confirm = #p(trade_is_confirm),
    trade_is_pay = #p(trade_is_pay),
    trade_flow = #p(trade_flow),
    trade_status = #p(trade_status),
    trade_audit_status = #p(trade_audit_status),
    system_update_user_id = #p(system_update_user_id),
    system_update_time = #p(system_update_time),
    system_version = system_version + 1
    WHERE system_status = 1
    AND trade_id = #p(trade_id)
    AND system_version = #p(system_version)
  #end
  
  #sql("updateTrade_is_payAndTrade_flowAndSystem_update_user_idAndSystem_update_timeAndByTrade_idAndSystem_version")
    UPDATE table_trade SET
    trade_is_pay = #p(trade_is_pay),
    trade_flow = #p(trade_flow),
    system_update_user_id = #p(system_update_user_id),
    system_update_time = #p(system_update_time),
    system_version = system_version + 1
    WHERE system_status = 1
    AND trade_id = #p(trade_id)
    AND system_version = #p(system_version)
  #end

  #sql("deleteByTrade_idAndSystem_version")
    UPDATE table_trade SET
    system_update_user_id = #p(system_update_user_id),
    system_update_time = #p(system_update_time),
    system_version = system_version + 1,
    system_status = 0
    WHERE system_status = 1
    AND trade_id = #p(trade_id)
    AND system_version = #p(system_version)
  #end

#end