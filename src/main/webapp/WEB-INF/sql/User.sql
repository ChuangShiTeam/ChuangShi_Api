#namespace("user")

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
      organization_id,
      role_id,
      user_level_id,
      user_type,
      user_account,
      user_phone,
      user_email,
      user_password,
      user_name,
      user_avatar,
      wechat_open_id,
      wechat_union_id,
      extend_id,
      system_create_user_id,
      system_create_time,
      system_update_user_id,
      system_update_time,
      system_version,
      system_status
    ) VALUES (
      #p(user_id),
      #p(app_id),
      #p(organization_id),
      #p(role_id),
      #p(user_level_id),
      #p(user_type),
      #p(user_account),
      #p(user_phone),
      #p(user_email),
      #p(user_password),
      #p(user_name),
      #p(user_avatar),
      #p(wechat_open_id),
      #p(wechat_union_id),
      #p(extend_id),
      #p(system_create_user_id),
      #p(system_create_time),
      #p(system_update_user_id),
      #p(system_update_time),
      #p(system_version),
      #p(system_status)
    )
  #end

  #sql("update")
    UPDATE table_user SET
    organization_id = #p(organization_id),
    role_id = #p(role_id),
    user_level_id = #p(user_level_id),
    user_type = #p(user_type),
    user_account = #p(user_account),
    user_phone = #p(user_phone),
    user_email = #p(user_email),
    user_password = #p(user_password),
    user_name = #p(user_name),
    user_avatar = #p(user_avatar),
    wechat_open_id = #p(wechat_open_id),
    wechat_union_id = #p(wechat_union_id),
    system_update_user_id = #p(system_update_user_id),
    system_update_time = #p(system_update_time),
    system_version = system_version + 1
    WHERE system_status = 1
    AND user_id = #p(user_id)
    AND system_version = #p(system_version)
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