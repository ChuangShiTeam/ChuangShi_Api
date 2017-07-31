#namespace("member_delivery_order")

  #sql("countByApp_idOrLikeUser_nameOrLikeMember_delivery_order_receiver_name")
    SELECT COUNT(*) FROM table_member_delivery_order
    LEFT JOIN table_user ON table_member_delivery_order.member_delivery_order_user_id = table_user.user_id
    WHERE table_member_delivery_order.system_status = 1
    AND table_member_delivery_order.app_id = #p(app_id)
    #if(user_name)
    #set(user_name = "%" + user_name + "%")
    AND table_user.user_name LIKE #p(user_name)
    #end
    #if(member_delivery_order_receiver_name)
    #set(member_delivery_order_receiver_name = "%" + member_delivery_order_receiver_name + "%")
    AND table_member_delivery_order.member_delivery_order_receiver_name LIKE #p(member_delivery_order_receiver_name)
    #end
  #end

  #sql("countByOrApp_idOrLikeUser_nameOrLikeMember_delivery_order_receiver_name")
    SELECT COUNT(*) FROM table_member_delivery_order
    LEFT JOIN table_user ON table_member_delivery_order.member_delivery_order_user_id = table_user.user_id
    WHERE table_member_delivery_order.system_status = 1
    #if(app_id)
    AND table_member_delivery_order.app_id = #p(app_id)
    #end
    #if(user_name)
    #set(user_name = "%" + user_name + "%")
    AND table_user.user_name LIKE #p(user_name)
    #end
    #if(member_delivery_order_receiver_name)
    #set(member_delivery_order_receiver_name = "%" + member_delivery_order_receiver_name + "%")
    AND table_member_delivery_order.member_delivery_order_receiver_name LIKE #p(member_delivery_order_receiver_name)
    #end
  #end

  #sql("listByApp_idAndSystem_create_timeAndLimit")
    SELECT
    member_delivery_order_id
    FROM table_member_delivery_order
    WHERE system_status = 1
    AND app_id = #p(app_id)
    AND system_create_time < UNIX_TIMESTAMP(#p(system_create_time))
    ORDER BY system_create_time DESC
    LIMIT #p(m), #p(n)
  #end

  #sql("listByApp_idOrLikeUser_nameOrLikeMember_delivery_order_receiver_nameAndLimit")
    SELECT
    member_delivery_order_id
    FROM table_member_delivery_order
    LEFT JOIN table_user ON table_member_delivery_order.member_delivery_order_user_id = table_user.user_id
    WHERE table_member_delivery_order.system_status = 1
    AND table_member_delivery_order.app_id = #p(app_id)
    #if(user_name)
    #set(user_name = "%" + user_name + "%")
    AND table_user.user_name LIKE #p(user_name)
    #end
    #if(member_delivery_order_receiver_name)
    #set(member_delivery_order_receiver_name = "%" + member_delivery_order_receiver_name + "%")
    AND table_member_delivery_order.member_delivery_order_receiver_name LIKE #p(member_delivery_order_receiver_name)
    #end
    ORDER BY table_member_delivery_order.system_create_time DESC
    LIMIT #p(m), #p(n)
  #end

  #sql("listByOrApp_idOrLikeUser_nameOrLikeMember_delivery_order_receiver_nameAndLimit")
    SELECT
    member_delivery_order_id
    FROM table_member_delivery_order
    WHERE table_member_delivery_order.system_status = 1
    #if(app_id)
    AND table_member_delivery_order.app_id = #p(app_id)
    #end
    #if(user_name)
    #set(user_name = "%" + user_name + "%")
    AND table_user.user_name LIKE #p(user_name)
    #end
    #if(member_delivery_order_receiver_name)
    #set(member_delivery_order_receiver_name = "%" + member_delivery_order_receiver_name + "%")
    AND table_member_delivery_order.member_delivery_order_receiver_name LIKE #p(member_delivery_order_receiver_name)
    #end
    ORDER BY table_member_delivery_order.system_create_time DESC
    LIMIT #p(m), #p(n)
  #end

  #sql("findByMember_delivery_order_id")
    SELECT
    *
    FROM table_member_delivery_order
    WHERE system_status = 1
    AND member_delivery_order_id = #p(member_delivery_order_id)
  #end

  #sql("save")
    INSERT INTO table_member_delivery_order (
      member_delivery_order_id,
      app_id,
      member_purchase_order_id,
      member_delivery_order_user_id,
      member_delivery_order_amount,
      member_delivery_order_total_quantity,
      member_delivery_order_receiver_name,
      member_delivery_order_receiver_mobile,
      member_delivery_order_receiver_province,
      member_delivery_order_receiver_city,
      member_delivery_order_receiver_area,
      member_delivery_order_receiver_address,
      member_delivery_order_express_pay_way,
      member_delivery_order_express_shipper_code,
      member_delivery_order_is_pay,
      member_delivery_order_is_warehouse_deliver,
      member_delivery_order_flow,
      member_delivery_order_is_complete,
      system_create_user_id,
      system_create_time,
      system_update_user_id,
      system_update_time,
      system_version,
      system_status
    ) VALUES (
      #p(member_delivery_order_id),
      #p(app_id),
      #p(member_purchase_order_id),
      #p(member_delivery_order_user_id),
      #p(member_delivery_order_amount),
      #p(member_delivery_order_total_quantity),
      #p(member_delivery_order_receiver_name),
      #p(member_delivery_order_receiver_mobile),
      #p(member_delivery_order_receiver_province),
      #p(member_delivery_order_receiver_city),
      #p(member_delivery_order_receiver_area),
      #p(member_delivery_order_receiver_address),
      #p(member_delivery_order_express_pay_way),
      #p(member_delivery_order_express_shipper_code),
      #p(member_delivery_order_is_pay),
      #p(member_delivery_order_is_warehouse_deliver),
      #p(member_delivery_order_flow),
      #p(member_delivery_order_is_complete),
      #p(system_create_user_id),
      #p(system_create_time),
      #p(system_update_user_id),
      #p(system_update_time),
      #p(system_version),
      #p(system_status)
    )
  #end

  #sql("update")
    UPDATE table_member_delivery_order SET
    member_purchase_order_id = #p(member_purchase_order_id),
    member_delivery_order_user_id = #p(member_delivery_order_user_id),
    member_delivery_order_amount = #p(member_delivery_order_amount),
    member_delivery_order_total_quantity = #p(member_delivery_order_total_quantity),
    member_delivery_order_receiver_name = #p(member_delivery_order_receiver_name),
    member_delivery_order_receiver_mobile = #p(member_delivery_order_receiver_mobile),
    member_delivery_order_receiver_province = #p(member_delivery_order_receiver_province),
    member_delivery_order_receiver_city = #p(member_delivery_order_receiver_city),
    member_delivery_order_receiver_area = #p(member_delivery_order_receiver_area),
    member_delivery_order_receiver_address = #p(member_delivery_order_receiver_address),
    member_delivery_order_express_pay_way = #p(member_delivery_order_express_pay_way),
    member_delivery_order_express_shipper_code = #p(member_delivery_order_express_shipper_code),
    member_delivery_order_is_pay = #p(member_delivery_order_is_pay),
    member_delivery_order_is_warehouse_deliver = #p(member_delivery_order_is_warehouse_deliver),
    member_delivery_order_flow = #p(member_delivery_order_flow),
    member_delivery_order_is_complete = #p(member_delivery_order_is_complete),
    system_update_user_id = #p(system_update_user_id),
    system_update_time = #p(system_update_time),
    system_version = system_version + 1
    WHERE system_status = 1
    AND member_delivery_order_id = #p(member_delivery_order_id)
    AND system_version = #p(system_version)
  #end
  
  #sql("updateMember_delivery_order_flowAndMember_delivery_order_is_payByMember_delivery_order_idAndSystem_version")
    UPDATE table_member_delivery_order SET
    member_delivery_order_is_pay = #p(member_delivery_order_is_pay),
    member_delivery_order_flow = #p(member_delivery_order_flow),
    system_update_user_id = #p(system_update_user_id),
    system_update_time = #p(system_update_time),
    system_version = system_version + 1
    WHERE system_status = 1
    AND member_delivery_order_id = #p(member_delivery_order_id)
    AND system_version = #p(system_version)
  #end
  
  #sql("updateMember_delivery_order_flowByMember_delivery_order_idAndSystem_version")
    UPDATE table_member_delivery_order SET
    member_delivery_order_flow = #p(member_delivery_order_flow),
    system_update_user_id = #p(system_update_user_id),
    system_update_time = #p(system_update_time),
    system_version = system_version + 1
    WHERE system_status = 1
    AND member_delivery_order_id = #p(member_delivery_order_id)
    AND system_version = #p(system_version)
  #end
  
  #sql("updateMember_delivery_order_flowAndMember_delivery_order_is_completeByMember_delivery_order_idAndSystem_version")
    UPDATE table_member_delivery_order SET
    member_delivery_order_is_complete = #p(member_delivery_order_is_complete),
    member_delivery_order_flow = #p(member_delivery_order_flow),
    system_update_user_id = #p(system_update_user_id),
    system_update_time = #p(system_update_time),
    system_version = system_version + 1
    WHERE system_status = 1
    AND member_delivery_order_id = #p(member_delivery_order_id)
    AND system_version = #p(system_version)
  #end

  #sql("deleteByMember_delivery_order_idAndSystem_version")
    UPDATE table_member_delivery_order SET
    system_update_user_id = #p(system_update_user_id),
    system_update_time = #p(system_update_time),
    system_version = system_version + 1,
    system_status = 0
    WHERE system_status = 1
    AND member_delivery_order_id = #p(member_delivery_order_id)
    AND system_version = #p(system_version)
  #end

#end