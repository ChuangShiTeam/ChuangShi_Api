#namespace("user")

  #sql("countByApp_idAndNotUser_idAndUser_account")
    SELECT COUNT(*) FROM table_user
    WHERE system_status = 1
    AND app_id = #p(app_id)
    AND user_id != #p(user_id)
    AND user_name = #p(user_name)
  #end

  #sql("countByApp_idOrLikeUser_name")
    SELECT COUNT(*) FROM table_user
    WHERE system_status = 1
    AND app_id = #p(app_id)
    #if(user_name)
    #set(user_name = "%" + user_name + "%")
    AND user_name LIKE #p(user_name)
    #end
  #end

  #sql("countByOrApp_idOrLikeUser_name")
    SELECT COUNT(*) FROM table_user
    WHERE system_status = 1
    #if(app_id)
    AND app_id = #p(app_id)
    #end
    #if(user_name)
    #set(user_name = "%" + user_name + "%")
    AND user_name LIKE #p(user_name)
    #end
  #end

  #sql("listByApp_idAndSystem_create_timeAndLimit")
    SELECT
    user_id
    FROM table_user
    WHERE system_status = 1
    AND app_id = #p(app_id)
    AND system_create_time < UNIX_TIMESTAMP(#p(system_create_time))
    ORDER BY system_create_time DESC
    LIMIT #p(m), #p(n)
  #end

  #sql("listByApp_idOrLikeUser_nameAndLimit")
    SELECT
    user_id
    FROM table_user
    WHERE system_status = 1
    AND app_id = #p(app_id)
    #if(user_name)
    #set(user_name = "%" + user_name + "%")
    AND user_name LIKE #p(user_name)
    #end
    ORDER BY system_create_time DESC
    LIMIT #p(m), #p(n)
  #end

  #sql("listByOrApp_idOrLikeUser_nameAndLimit")
    SELECT
    user_id
    FROM table_user
    WHERE system_status = 1
    #if(app_id)
    AND app_id = #p(app_id)
    #end
    #if(user_name)
    #set(user_name = "%" + user_name + "%")
    AND user_name LIKE #p(user_name)
    #end
    ORDER BY system_create_time DESC
    LIMIT #p(m), #p(n)
  #end

  #sql("findByUser_id")
    SELECT
    *
    FROM table_user
    WHERE system_status = 1
    AND user_id = #p(user_id)
  #end

  #sql("findByApp_idAndUser_typeAndWechat_open_idAndWechat_union_id")
    SELECT
    *
    FROM table_user
    WHERE system_status = 1
    AND app_id = #p(app_id)
    AND user_type = #p(user_type)
    AND wechat_open_id = #p(wechat_open_id)
    AND wechat_union_id = #p(wechat_union_id)
  #end

  #sql("findByApp_idAndUser_typeAndUser_accountAndUser_password")
    SELECT
    *
    FROM table_user
    WHERE system_status = 1
    AND app_id = #p(app_id)
    AND user_type = #p(user_type)
    AND user_account = #p(user_account)
    AND user_password = #p(user_password)
  #end

  #sql("save")
    INSERT INTO table_user (
      user_id,
      app_id,
      object_id,
      user_type,
      user_name,
      user_avatar,
      user_account,
      user_mobile,
      user_email,
      user_password,
      wechat_open_id,
      wechat_union_id,
      system_create_user_id,
      system_create_time,
      system_update_user_id,
      system_update_time,
      system_version,
      system_status
    ) VALUES (
      #p(user_id),
      #p(app_id),
      #p(object_id),
      #p(user_type),
      #p(user_name),
      #p(user_avatar),
      #p(user_account),
      #p(user_mobile),
      #p(user_email),
      #p(user_password),
      #p(wechat_open_id),
      #p(wechat_union_id),
      #p(system_create_user_id),
      #p(system_create_time),
      #p(system_update_user_id),
      #p(system_update_time),
      #p(system_version),
      #p(system_status)
    )
  #end

  #sql("updateByUser_password")
    UPDATE table_user SET
    user_password = #p(user_password),
    system_update_user_id = #p(system_update_user_id),
    system_update_time = #p(system_update_time),
    system_version = system_version + 1
    WHERE system_status = 1
    AND user_id = #p(user_id)
  #end

  #sql("updateByUser_name")
    UPDATE table_user SET
    user_name = #p(user_name),
    system_update_user_id = #p(system_update_user_id),
    system_update_time = #p(system_update_time),
    system_version = system_version + 1
    WHERE system_status = 1
    AND user_id = #p(user_id)
  #end

  #sql("updateByUser_nameAndUser_accountAndUser_password")
    UPDATE table_user SET
    user_name = #p(user_name),
    user_account = #p(user_account),
    user_password = #p(user_password),
    system_update_user_id = #p(system_update_user_id),
    system_update_time = #p(system_update_time),
    system_version = system_version + 1
    WHERE system_status = 1
    AND user_id = #p(user_id)
  #end

  #sql("deleteByUser_idAndSystem_version")
    UPDATE table_user SET
    system_update_user_id = #p(system_update_user_id),
    system_update_time = #p(system_update_time),
    system_version = system_version + 1,
    system_status = 0
    WHERE system_status = 1
    AND user_id = #p(user_id)
    AND system_version = #p(system_version)
  #end

#end