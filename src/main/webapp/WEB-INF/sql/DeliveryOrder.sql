#namespace("delivery_order")

  #sql("countByApp_idOrLikeUser_nameOrLikeDelivery_order_receiver_nameOrLikeExpress_no")
    SELECT COUNT(*) FROM table_delivery_order
    LEFT JOIN table_express ON table_delivery_order.delivery_order_id = table_express.delivery_order_id
    LEFT JOIN table_user ON table_delivery_order.delivery_order_user_id = table_user.user_id
    WHERE table_delivery_order.system_status = 1
    AND table_delivery_order.app_id = #p(app_id)
    #if(user_name)
    #set(user_name = "%" + user_name + "%")
    AND table_user.user_name LIKE #p(user_name)
    #end
    #if(delivery_order_receiver_name)
    #set(delivery_order_receiver_name = "%" + delivery_order_receiver_name + "%")
    AND table_delivery_order.delivery_order_receiver_name LIKE #p(delivery_order_receiver_name)
    #end
    #if(express_no)
    #set(express_no = "%" + express_no + "%")
    AND table_express.express_no LIKE #p(express_no)
    #end
  #end

  #sql("countByOrApp_idOrLikeUser_nameOrLikeDelivery_order_receiver_nameOrLikeExpress_no")
    SELECT COUNT(*) FROM table_delivery_order
    LEFT JOIN table_express ON table_delivery_order.delivery_order_id = table_express.delivery_order_id
    LEFT JOIN table_user ON table_delivery_order.delivery_order_user_id = table_user.user_id
    WHERE table_delivery_order.system_status = 1
    #if(app_id)
    AND table_delivery_order.app_id = #p(app_id)
    #end
    #if(user_name)
    #set(user_name = "%" + user_name + "%")
    AND table_user.user_name LIKE #p(user_name)
    #end
    #if(delivery_order_receiver_name)
    #set(delivery_order_receiver_name = "%" + delivery_order_receiver_name + "%")
    AND table_delivery_order.delivery_order_receiver_name LIKE #p(delivery_order_receiver_name)
    #end
    #if(express_no)
    #set(express_no = "%" + express_no + "%")
    AND table_express.express_no LIKE #p(express_no)
    #end
  #end
  
  #sql("countByDelivery_order_sender_user_id")
    SELECT COUNT(*) FROM table_delivery_order
    WHERE table_delivery_order.system_status = 1
    AND table_delivery_order.delivery_order_sender_user_id = #p(delivery_order_sender_user_id)
  #end

  #sql("listByApp_idAndSystem_create_timeAndLimit")
    SELECT
    delivery_order_id
    FROM table_delivery_order
    WHERE system_status = 1
    AND app_id = #p(app_id)
    AND system_create_time < UNIX_TIMESTAMP(#p(system_create_time))
    ORDER BY system_create_time DESC
    LIMIT #p(m), #p(n)
  #end

  #sql("listByApp_idOrLikeUser_nameOrLikeDelivery_order_receiver_nameOrLikeExpress_noAndLimit")
    SELECT
    table_delivery_order.*,
    table_express.express_no,
    table_user.user_name
    FROM table_delivery_order
    LEFT JOIN table_express ON table_delivery_order.delivery_order_id = table_express.delivery_order_id
    LEFT JOIN table_user ON table_delivery_order.delivery_order_user_id = table_user.user_id
    WHERE table_delivery_order.system_status = 1
    AND table_delivery_order.app_id = #p(app_id)
    #if(user_name)
    #set(user_name = "%" + user_name + "%")
    AND table_user.user_name LIKE #p(user_name)
    #end
    #if(delivery_order_receiver_name)
    #set(delivery_order_receiver_name = "%" + delivery_order_receiver_name + "%")
    AND table_delivery_order.delivery_order_receiver_name LIKE #p(delivery_order_receiver_name)
    #end
    #if(express_no)
    #set(express_no = "%" + express_no + "%")
    AND table_express.express_no LIKE #p(express_no)
    #end
    ORDER BY table_delivery_order.system_create_time DESC
    LIMIT #p(m), #p(n)
  #end

  #sql("listByOrApp_idOrLikeUser_nameOrLikeDelivery_order_receiver_nameOrLikeExpress_noAndLimit")
    SELECT
    table_delivery_order.*,
    table_express.express_no,
    table_user.user_name
    FROM table_delivery_order
    LEFT JOIN table_express ON table_delivery_order.delivery_order_id = table_express.delivery_order_id
    LEFT JOIN table_user ON table_delivery_order.delivery_order_user_id = table_user.user_id
    WHERE table_delivery_order.system_status = 1
    #if(app_id)
    AND table_delivery_order.app_id = #p(app_id)
    #end
    #if(user_name)
    #set(user_name = "%" + user_name + "%")
    AND table_user.user_name LIKE #p(user_name)
    #end
    #if(delivery_order_receiver_name)
    #set(delivery_order_receiver_name = "%" + delivery_order_receiver_name + "%")
    AND table_delivery_order.delivery_order_receiver_name LIKE #p(delivery_order_receiver_name)
    #end
    #if(express_no)
    #set(express_no = "%" + express_no + "%")
    AND table_express.express_no LIKE #p(express_no)
    #end
    ORDER BY table_delivery_order.system_create_time DESC
    LIMIT #p(m), #p(n)
  #end
  
  #sql("listWithExpressByDelivery_order_sender_user_idAndLimit")
    SELECT
    table_delivery_order.*,
    table_express.express_no,
    table_express.express_flow,
    table_express.express_shipper_code
    FROM table_delivery_order
    LEFT JOIN table_express ON table_express.delivery_order_id = table_delivery_order.delivery_order_id
    WHERE table_delivery_order.system_status = 1
    AND table_delivery_order.delivery_order_sender_user_id = #p(delivery_order_sender_user_id)
    LIMIT #p(m), #p(n)
  #end

  #sql("findByDelivery_order_id")
    SELECT
    table_delivery_order.*,
    table_user.user_name
    FROM table_delivery_order
    LEFT JOIN table_user ON table_user.user_id = table_delivery_order.delivery_order_user_id
    WHERE table_delivery_order.system_status = 1
    AND table_delivery_order.delivery_order_id = #p(delivery_order_id)
  #end

  #sql("save")
    INSERT INTO table_delivery_order (
      delivery_order_id,
      app_id,
      trade_id,
      delivery_order_user_id,
      delivery_order_sender_user_id,
      delivery_order_reciever_user_id,
      delivery_order_total_quantity,
      delivery_order_receiver_name,
      delivery_order_receiver_mobile,
      delivery_order_receiver_province,
      delivery_order_receiver_city,
      delivery_order_receiver_area,
      delivery_order_receiver_address,
      delivery_order_express_pay_way,
      delivery_order_express_shipper_code,
      delivery_order_is_pay,
      delivery_order_flow,
      delivery_is_complete,
      system_create_user_id,
      system_create_time,
      system_update_user_id,
      system_update_time,
      system_version,
      system_status
    ) VALUES (
      #p(delivery_order_id),
      #p(app_id),
      #p(trade_id),
      #p(delivery_order_user_id),
      #p(delivery_order_sender_user_id),
      #p(delivery_order_reciever_user_id),
      #p(delivery_order_total_quantity),
      #p(delivery_order_receiver_name),
      #p(delivery_order_receiver_mobile),
      #p(delivery_order_receiver_province),
      #p(delivery_order_receiver_city),
      #p(delivery_order_receiver_area),
      #p(delivery_order_receiver_address),
      #p(delivery_order_express_pay_way),
      #p(delivery_order_express_shipper_code),
      #p(delivery_order_is_pay),
      #p(delivery_order_flow),
      #p(delivery_is_complete),
      #p(system_create_user_id),
      #p(system_create_time),
      #p(system_update_user_id),
      #p(system_update_time),
      #p(system_version),
      #p(system_status)
    )
  #end

  #sql("update")
    UPDATE table_delivery_order SET
    trade_id = #p(trade_id),
    delivery_order_user_id = #p(delivery_order_user_id),
    delivery_order_sender_user_id = #p(delivery_order_sender_user_id),
    delivery_order_reciever_user_id = #p(delivery_order_reciever_user_id),
    delivery_order_total_quantity = #p(delivery_order_total_quantity),
    delivery_order_receiver_name = #p(delivery_order_receiver_name),
    delivery_order_receiver_mobile = #p(delivery_order_receiver_mobile),
    delivery_order_receiver_province = #p(delivery_order_receiver_province),
    delivery_order_receiver_city = #p(delivery_order_receiver_city),
    delivery_order_receiver_area = #p(delivery_order_receiver_area),
    delivery_order_receiver_address = #p(delivery_order_receiver_address),
    delivery_order_express_pay_way = #p(delivery_order_express_pay_way),
    delivery_order_express_shipper_code = #p(delivery_order_express_shipper_code),
    delivery_order_is_pay = #p(delivery_order_is_pay),
    delivery_order_flow = #p(delivery_order_flow),
    delivery_is_complete = #p(delivery_is_complete),
    system_update_user_id = #p(system_update_user_id),
    system_update_time = #p(system_update_time),
    system_version = system_version + 1
    WHERE system_status = 1
    AND delivery_order_id = #p(delivery_order_id)
    AND system_version = #p(system_version)
  #end
  
  #sql("updateDelivery_order_flowAndDelivery_is_completeByDelivery_order_idAndSystem_version")
    UPDATE table_delivery_order SET
    delivery_order_flow = #p(delivery_order_flow),
    delivery_is_complete = #p(delivery_is_complete),
    system_update_user_id = #p(system_update_user_id),
    system_update_time = #p(system_update_time),
    system_version = system_version + 1
    WHERE system_status = 1
    AND delivery_order_id = #p(delivery_order_id)
    AND system_version = #p(system_version)
  #end

  #sql("deleteByDelivery_order_idAndSystem_version")
    UPDATE table_delivery_order SET
    system_update_user_id = #p(system_update_user_id),
    system_update_time = #p(system_update_time),
    system_version = system_version + 1,
    system_status = 0
    WHERE system_status = 1
    AND delivery_order_id = #p(delivery_order_id)
    AND system_version = #p(system_version)
  #end

#end