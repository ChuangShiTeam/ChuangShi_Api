#namespace("delivery_order_pay")

  #sql("listByDelivery_order_id")
    SELECT
    *
    FROM table_delivery_order_pay
    WHERE system_status = 1
    AND delivery_order_id = #p(delivery_order_id)
    ORDER BY system_create_time DESC
  #end
  
  #sql("save")
    INSERT INTO table_delivery_order_pay (
      delivery_order_id,
      delivery_order_pay_type,
      delivery_order_pay_number,
      delivery_order_pay_account,
      delivery_order_pay_time,
      delivery_order_pay_result,
      system_create_user_id,
      system_create_time,
      system_update_user_id,
      system_update_time,
      system_version,
      system_status
    ) VALUES (
      #p(delivery_order_id),
      #p(delivery_order_pay_type),
      #p(delivery_order_pay_number),
      #p(delivery_order_pay_account),
      #p(delivery_order_pay_time),
      #p(delivery_order_pay_result),
      #p(system_create_user_id),
      #p(system_create_time),
      #p(system_update_user_id),
      #p(system_update_time),
      #p(system_version),
      #p(system_status)
    )
  #end

  #sql("deleteByDelivery_order_id")
    UPDATE table_delivery_order_pay SET
    system_update_user_id = #p(system_update_user_id),
    system_update_time = #p(system_update_time),
    system_version = system_version + 1,
    system_status = 0
    WHERE system_status = 1
    AND delivery_order_id = #p(delivery_order_id)
  #end

#end