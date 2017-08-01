#namespace("member_delivery_order_express")

  #sql("listByMember_delivery_order_id")
    SELECT
    express_id
    FROM table_member_delivery_order_express
    WHERE system_status = 1
    AND member_delivery_order_id = #p(member_delivery_order_id)
    ORDER BY system_create_time DESC
  #end

  #sql("save")
    INSERT INTO table_member_delivery_order_express (
      member_delivery_order_id,
      express_id,
      system_create_user_id,
      system_create_time,
      system_update_user_id,
      system_update_time,
      system_version,
      system_status
    ) VALUES (
      #p(member_delivery_order_id),
      #p(express_id),
      #p(system_create_user_id),
      #p(system_create_time),
      #p(system_update_user_id),
      #p(system_update_time),
      #p(system_version),
      #p(system_status)
    )
  #end

  #sql("deleteByMember_delivery_order_id")
    UPDATE table_member_delivery_order_express SET
    system_update_user_id = #p(system_update_user_id),
    system_update_time = #p(system_update_time),
    system_version = system_version + 1,
    system_status = 0
    WHERE system_status = 1
    AND member_delivery_order_id = #p(member_delivery_order_id)
  #end
  
  #sql("deleteByMember_delivery_order_idAndExpress_id")
    UPDATE table_member_delivery_order_express SET
    system_update_user_id = #p(system_update_user_id),
    system_update_time = #p(system_update_time),
    system_version = system_version + 1,
    system_status = 0
    WHERE system_status = 1
    AND member_delivery_order_id = #p(member_delivery_order_id)
    AND express_id = #p(express_id)
  #end

#end