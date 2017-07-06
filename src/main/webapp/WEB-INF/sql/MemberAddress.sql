#namespace("member_address")

  #sql("countByApp_idOrLikeMember_address_name")
    SELECT COUNT(*) FROM table_member_address
    WHERE system_status = 1
    AND app_id = #p(app_id)
    #if(member_address_name)
    #set(member_address_name = "%" + member_address_name + "%")
    AND member_address_name LIKE #p(member_address_name)
    #end
  #end

  #sql("countByOrApp_idOrLikeMember_address_name")
    SELECT COUNT(*) FROM table_member_address
    WHERE system_status = 1
    #if(app_id)
    AND app_id = #p(app_id)
    #end
    #if(member_address_name)
    #set(member_address_name = "%" + member_address_name + "%")
    AND member_address_name LIKE #p(member_address_name)
    #end
  #end

  #sql("listByApp_idAndSystem_create_timeAndLimit")
    SELECT
    member_address_id
    FROM table_member_address
    WHERE system_status = 1
    AND app_id = #p(app_id)
    AND system_create_time < UNIX_TIMESTAMP(#p(system_create_time))
    ORDER BY system_create_time DESC
    LIMIT #p(m), #p(n)
  #end

  #sql("listByApp_idOrLikeMember_address_nameAndLimit")
    SELECT
    member_address_id,
    app_id,
    member_address_name,
    member_address_tel,
    member_address_mobile,
    member_address_postcode,
    member_address_province,
    member_address_city,
    member_address_area,
    member_address_address,
    member_delivery_is_default
    FROM table_member_address
    WHERE system_status = 1
    AND app_id = #p(app_id)
    #if(member_address_name)
    #set(member_address_name = "%" + member_address_name + "%")
    AND member_address_name LIKE #p(member_address_name)
    #end
    ORDER BY system_create_time DESC
    LIMIT #p(m), #p(n)
  #end

  #sql("listByOrApp_idOrLikeMember_address_nameAndLimit")
    SELECT
    member_address_id
    FROM table_member_address
    WHERE system_status = 1
    #if(app_id)
    AND app_id = #p(app_id)
    #end
    #if(member_address_name)
    #set(member_address_name = "%" + member_address_name + "%")
    AND member_address_name LIKE #p(member_address_name)
    #end
    ORDER BY system_create_time DESC
    LIMIT #p(m), #p(n)
  #end

  #sql("findByMember_address_id")
    SELECT
    member_address_id,
    app_id,
    member_id,
    user_id,
    member_address_name,
    member_address_tel,
    member_address_mobile,
    member_address_postcode,
    member_address_province,
    member_address_city,
    member_address_area,
    member_address_address,
    member_delivery_is_default
    FROM table_member_address
    WHERE system_status = 1
    AND member_address_id = #p(member_address_id)
  #end

  #sql("save")
    INSERT INTO table_member_address (
      member_address_id,
      app_id,
      member_id,
      user_id,
      member_address_name,
      member_address_tel,
      member_address_mobile,
      member_address_postcode,
      member_address_province,
      member_address_city,
      member_address_area,
      member_address_address,
      member_delivery_is_default,
      system_create_user_id,
      system_create_time,
      system_update_user_id,
      system_update_time,
      system_version,
      system_status
    ) VALUES (
      #p(member_address_id),
      #p(app_id),
      #p(member_id),
      #p(user_id),
      #p(member_address_name),
      #p(member_address_tel),
      #p(member_address_mobile),
      #p(member_address_postcode),
      #p(member_address_province),
      #p(member_address_city),
      #p(member_address_area),
      #p(member_address_address),
      #p(member_delivery_is_default),
      #p(system_create_user_id),
      #p(system_create_time),
      #p(system_update_user_id),
      #p(system_update_time),
      #p(system_version),
      #p(system_status)
    )
  #end

  #sql("update")
    UPDATE table_member_address SET
    member_id = #p(member_id),
    user_id = #p(user_id),
    member_address_name = #p(member_address_name),
    member_address_tel = #p(member_address_tel),
    member_address_mobile = #p(member_address_mobile),
    member_address_postcode = #p(member_address_postcode),
    member_address_province = #p(member_address_province),
    member_address_city = #p(member_address_city),
    member_address_area = #p(member_address_area),
    member_address_address = #p(member_address_address),
    member_delivery_is_default = #p(member_delivery_is_default),
    system_update_user_id = #p(system_update_user_id),
    system_update_time = #p(system_update_time),
    system_version = system_version + 1
    WHERE system_status = 1
    AND member_address_id = #p(member_address_id)
    AND system_version = #p(system_version)
  #end

  #sql("deleteByMember_address_idAndSystem_version")
    UPDATE table_member_address SET
    system_update_user_id = #p(system_update_user_id),
    system_update_time = #p(system_update_time),
    system_version = system_version + 1,
    system_status = 0
    WHERE system_status = 1
    AND member_address_id = #p(member_address_id)
    AND system_version = #p(system_version)
  #end

#end