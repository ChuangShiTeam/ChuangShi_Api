#namespace("app")

  #sql("countByApp_idOrLikeApp_name")
    SELECT COUNT(*) FROM table_app
    WHERE system_status = 1
    AND app_id = #p(app_id)
    #if(app_name)
    #set(app_name = "%" + app_name + "%")
    AND app_name LIKE #p(app_name)
    #end
  #end

  #sql("countByOrApp_idOrLikeApp_name")
    SELECT COUNT(*) FROM table_app
    WHERE system_status = 1
    #if(app_id)
    AND app_id = #p(app_id)
    #end
    #if(app_name)
    #set(app_name = "%" + app_name + "%")
    AND app_name LIKE #p(app_name)
    #end
  #end

  #sql("list")
    SELECT
    app_id
    FROM table_app
    WHERE system_status = 1
  #end

  #sql("listByApp_idAndSystem_create_timeAndLimit")
    SELECT
    app_id
    FROM table_app
    WHERE system_status = 1
    AND app_id = #p(app_id)
    AND system_create_time < UNIX_TIMESTAMP(#p(system_create_time))
    ORDER BY system_create_time DESC
    LIMIT #p(m), #p(n)
  #end

  #sql("listByApp_idOrLikeApp_nameAndLimit")
    SELECT
    app_id
    FROM table_app
    WHERE system_status = 1
    AND app_id = #p(app_id)
    #if(app_name)
    #set(app_name = "%" + app_name + "%")
    AND app_name LIKE #p(app_name)
    #end
    ORDER BY system_create_time DESC
    LIMIT #p(m), #p(n)
  #end

  #sql("listByOrApp_idOrLikeApp_nameAndLimit")
    SELECT
    app_id
    FROM table_app
    WHERE system_status = 1
    #if(app_id)
    AND app_id = #p(app_id)
    #end
    #if(app_name)
    #set(app_name = "%" + app_name + "%")
    AND app_name LIKE #p(app_name)
    #end
    ORDER BY system_create_time DESC
    LIMIT #p(m), #p(n)
  #end

  #sql("findByApp_id")
    SELECT
    *
    FROM table_app
    WHERE system_status = 1
    AND app_id = #p(app_id)
  #end
  
  #sql("save")
    INSERT INTO table_app (
      app_id,
      app_name,
      app_secret,
      wechat_app_id,
      wechat_app_secret,
      wechat_mch_id,
      wechat_mch_key,
      wechat_token,
      wechat_encoding_aes_key,
      app_is_stock,
      app_is_commission,
      app_commission_level,
      system_create_user_id,
      system_create_time,
      system_update_user_id,
      system_update_time,
      system_version,
      system_status
    ) VALUES (
      #p(app_id),
      #p(app_name),
      #p(app_secret),
      #p(wechat_app_id),
      #p(wechat_app_secret),
      #p(wechat_mch_id),
      #p(wechat_mch_key),
      #p(wechat_token),
      #p(wechat_encoding_aes_key),
      #p(app_is_stock),
      #p(app_is_commission),
      #p(app_commission_level),
      #p(system_create_user_id),
      #p(system_create_time),
      #p(system_update_user_id),
      #p(system_update_time),
      #p(system_version),
      #p(system_status)
    )
  #end

  #sql("update")
    UPDATE table_app SET
    app_name = #p(app_name),
    app_secret = #p(app_secret),
    wechat_app_id = #p(wechat_app_id),
    wechat_app_secret = #p(wechat_app_secret),
    wechat_mch_id = #p(wechat_mch_id),
    wechat_mch_key = #p(wechat_mch_key),
    wechat_token = #p(wechat_token),
    wechat_encoding_aes_key = #p(wechat_encoding_aes_key),
    app_is_stock = #p(app_is_stock),
    app_is_commission = #p(app_is_commission),
    app_commission_level = #p(app_commission_level),
    system_update_user_id = #p(system_update_user_id),
    system_update_time = #p(system_update_time),
    system_version = system_version + 1
    WHERE system_status = 1
    AND app_id = #p(app_id)
    AND system_version = #p(system_version)
  #end
  
  #sql("deleteByApp_idAndSystem_version")
    UPDATE table_app SET
    system_update_user_id = #p(system_update_user_id),
    system_update_time = #p(system_update_time),
    system_version = system_version + 1,
    system_status = 0
    WHERE system_status = 1
    AND app_id = #p(app_id)
    AND system_version = #p(system_version)
  #end

#end