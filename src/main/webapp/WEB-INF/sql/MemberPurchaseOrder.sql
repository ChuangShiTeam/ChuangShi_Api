#namespace("member_purchase_order")

  #sql("countByApp_idOrLikeUser_name")
    SELECT COUNT(*) FROM table_member_purchase_order
    LEFT JOIN table_user ON table_member_purchase_order.user_id = table_user.user_id
    WHERE table_member_purchase_order.system_status = 1
    AND table_member_purchase_order.app_id = #p(app_id)
    #if(user_name)
    #set(user_name = "%" + user_name + "%")
    AND table_user.user_name LIKE #p(user_name)
    #end
  #end

  #sql("countByOrApp_idOrLikeUser_name")
    SELECT COUNT(*) FROM table_member_purchase_order
    LEFT JOIN table_user ON table_member_purchase_order.user_id = table_user.user_id
    WHERE table_member_purchase_order.system_status = 1
    #if(app_id)
    AND table_member_purchase_order.app_id = #p(app_id)
    #end
    #if(user_name)
    #set(user_name = "%" + user_name + "%")
    AND table_user.user_name LIKE #p(user_name)
    #end
  #end

  #sql("listByApp_idAndSystem_create_timeAndLimit")
    SELECT
    member_purchase_order_id
    FROM table_member_purchase_order
    WHERE system_status = 1
    AND app_id = #p(app_id)
    AND system_create_time < UNIX_TIMESTAMP(#p(system_create_time))
    ORDER BY system_create_time DESC
    LIMIT #p(m), #p(n)
  #end

  #sql("listByApp_idOrLikeUser_nameAndLimit")
    SELECT
    table_member_purchase_order.member_purchase_order_id
    FROM table_member_purchase_order
    LEFT JOIN table_user ON table_member_purchase_order.user_id = table_user.user_id
    WHERE table_member_purchase_order.system_status = 1
    AND table_member_purchase_order.app_id = #p(app_id)
    #if(user_name)
    #set(user_name = "%" + user_name + "%")
    AND table_user.user_name LIKE #p(user_name)
    #end
    ORDER BY table_member_purchase_order.system_create_time DESC
    LIMIT #p(m), #p(n)
  #end

  #sql("listByOrApp_idOrLikeUser_nameAndLimit")
    SELECT
    table_member_purchase_order.member_purchase_order_id
    FROM table_member_purchase_order
    LEFT JOIN table_user ON table_member_purchase_order.user_id = table_user.user_id
    WHERE table_member_purchase_order.system_status = 1
    #if(app_id)
    AND table_member_purchase_order.app_id = #p(app_id)
    #end
    #if(user_name)
    #set(user_name = "%" + user_name + "%")
    AND table_user.user_name LIKE #p(user_name)
    #end
    ORDER BY table_member_purchase_order.system_create_time DESC
    LIMIT #p(m), #p(n)
  #end
  
  #sql("listByUser_id")
    SELECT
    *
    FROM table_member_purchase_order
    WHERE system_status = 1
    AND user_id = #p(user_id)
    ORDER BY system_create_time DESC
  #end

  #sql("findByMember_purchase_order_id")
    SELECT
    *
    FROM table_member_purchase_order
    WHERE system_status = 1
    AND member_purchase_order_id = #p(member_purchase_order_id)
  #end

  #sql("save")
    INSERT INTO table_member_purchase_order (
      member_purchase_order_id,
      app_id,
      user_id,
      member_level_id,
      member_deliver_user_id,
      member_purchase_order_number,
      member_purchase_order_product_amount,
      member_purchase_order_express_amount,
      member_purchase_order_discount_amount,
      member_purchase_order_amount,
      member_purchase_order_total_quantity,
      member_purchase_order_receiver_name,
      member_purchase_order_receiver_mobile,
      member_purchase_order_receiver_province,
      member_purchase_order_receiver_city,
      member_purchase_order_receiver_area,
      member_purchase_order_receiver_address,
      member_purchase_order_express_pay_way,
      member_purchase_order_express_shipper_code,
      member_purchase_order_is_warehouse_receive,
      member_purchase_order_is_pay,
      member_purchase_order_flow,
      member_purchase_order_is_complete,
      member_purchase_order_message,
      system_create_user_id,
      system_create_time,
      system_update_user_id,
      system_update_time,
      system_version,
      system_status
    ) VALUES (
      #p(member_purchase_order_id),
      #p(app_id),
      #p(user_id),
      #p(member_level_id),
      #p(member_deliver_user_id),
      #p(member_purchase_order_number),
      #p(member_purchase_order_product_amount),
      #p(member_purchase_order_express_amount),
      #p(member_purchase_order_discount_amount),
      #p(member_purchase_order_amount),
      #p(member_purchase_order_total_quantity),
      #p(member_purchase_order_receiver_name),
      #p(member_purchase_order_receiver_mobile),
      #p(member_purchase_order_receiver_province),
      #p(member_purchase_order_receiver_city),
      #p(member_purchase_order_receiver_area),
      #p(member_purchase_order_receiver_address),
      #p(member_purchase_order_express_pay_way),
      #p(member_purchase_order_express_shipper_code),
      #p(member_purchase_order_is_warehouse_receive),
      #p(member_purchase_order_is_pay),
      #p(member_purchase_order_flow),
      #p(member_purchase_order_is_complete),
      #p(member_purchase_order_message),
      #p(system_create_user_id),
      #p(system_create_time),
      #p(system_update_user_id),
      #p(system_update_time),
      #p(system_version),
      #p(system_status)
    )
  #end

  #sql("update")
    UPDATE table_member_purchase_order SET
    member_purchase_order_product_amount = #p(member_purchase_order_product_amount),
    member_purchase_order_express_amount = #p(member_purchase_order_express_amount),
    member_purchase_order_discount_amount = #p(member_purchase_order_discount_amount),
    member_purchase_order_amount = #p(member_purchase_order_amount),
    member_purchase_order_total_quantity = #p(member_purchase_order_total_quantity),
    member_purchase_order_receiver_name = #p(member_purchase_order_receiver_name),
    member_purchase_order_receiver_mobile = #p(member_purchase_order_receiver_mobile),
    member_purchase_order_receiver_province = #p(member_purchase_order_receiver_province),
    member_purchase_order_receiver_city = #p(member_purchase_order_receiver_city),
    member_purchase_order_receiver_area = #p(member_purchase_order_receiver_area),
    member_purchase_order_receiver_address = #p(member_purchase_order_receiver_address),
    member_purchase_order_express_pay_way = #p(member_purchase_order_express_pay_way),
    member_purchase_order_express_shipper_code = #p(member_purchase_order_express_shipper_code),
    member_purchase_order_is_warehouse_receive = #p(member_purchase_order_is_warehouse_receive),
    member_purchase_order_is_pay = #p(member_purchase_order_is_pay),
    member_purchase_order_flow = #p(member_purchase_order_flow),
    member_purchase_order_is_complete = #p(member_purchase_order_is_complete),
    member_purchase_order_message = #p(member_purchase_order_message),
    system_update_user_id = #p(system_update_user_id),
    system_update_time = #p(system_update_time),
    system_version = system_version + 1
    WHERE system_status = 1
    AND member_purchase_order_id = #p(member_purchase_order_id)
    AND system_version = #p(system_version)
  #end
  
  #sql("updateMember_purchase_order_flowAndMember_purchase_order_is_payByMember_purchase_order_idAndSystem_version")
    UPDATE table_member_purchase_order SET
    member_purchase_order_is_pay = #p(member_purchase_order_is_pay),
    member_purchase_order_flow = #p(member_purchase_order_flow),
    system_update_user_id = #p(system_update_user_id),
    system_update_time = #p(system_update_time),
    system_version = system_version + 1
    WHERE system_status = 1
    AND member_purchase_order_id = #p(member_purchase_order_id)
    AND system_version = #p(system_version)
  #end
  
  #sql("updateMember_purchase_order_flowByMember_purchase_order_idAndSystem_version")
    UPDATE table_member_purchase_order SET
    member_purchase_order_flow = #p(member_purchase_order_flow),
    system_update_user_id = #p(system_update_user_id),
    system_update_time = #p(system_update_time),
    system_version = system_version + 1
    WHERE system_status = 1
    AND member_purchase_order_id = #p(member_purchase_order_id)
    AND system_version = #p(system_version)
  #end
  
  #sql("updateMember_purchase_order_flowAndMember_purchase_order_is_completeByMember_purchase_order_idAndSystem_version")
    UPDATE table_member_purchase_order SET
    member_purchase_order_is_complete = #p(member_purchase_order_is_complete),
    member_purchase_order_flow = #p(member_purchase_order_flow),
    system_update_user_id = #p(system_update_user_id),
    system_update_time = #p(system_update_time),
    system_version = system_version + 1
    WHERE system_status = 1
    AND member_purchase_order_id = #p(member_purchase_order_id)
    AND system_version = #p(system_version)
  #end
  
  #sql("deleteByMember_purchase_order_idAndSystem_version")
    UPDATE table_member_purchase_order SET
    system_update_user_id = #p(system_update_user_id),
    system_update_time = #p(system_update_time),
    system_version = system_version + 1,
    system_status = 0
    WHERE system_status = 1
    AND member_purchase_order_id = #p(member_purchase_order_id)
    AND system_version = #p(system_version)
  #end

#end