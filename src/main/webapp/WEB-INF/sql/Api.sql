#namespace("api")

  #sql("countByApp_idOrLikeApi_name")
    SELECT COUNT(*) FROM table_api
    WHERE system_status = 1
    AND app_id = #p(app_id)
    #if(api_name)
    #set(api_name = "%" + api_name + "%")
    AND api_name LIKE #p(api_name)
    #end
  #end

  #sql("countByOrApp_idOrLikeApi_name")
    SELECT COUNT(*) FROM table_api
    WHERE system_status = 1
    #if(app_id)
    AND app_id = #p(app_id)
    #end
    #if(api_name)
    #set(api_name = "%" + api_name + "%")
    AND api_name LIKE #p(api_name)
    #end
  #end

  #sql("listByApp_idAndSystem_create_timeAndLimit")
    SELECT
    api_id
    FROM table_api
    WHERE system_status = 1
    AND app_id = #p(app_id)
    AND system_create_time < UNIX_TIMESTAMP(#p(system_create_time))
    ORDER BY system_create_time DESC
    LIMIT #p(m), #p(n)
  #end

  #sql("listNotInMenuByApp_id")
    SELECT
    api_id
    FROM table_api
    WHERE system_status = 1
    AND app_id = #p(app_id)
    AND api_id NOT IN (SELECT api_id FROM table_menu_api WHERE app_id = #p(app_id) AND system_status = 1)
    ORDER BY system_create_time DESC
  #end

  #sql("listByApp_idOrLikeApi_nameAndLimit")
    SELECT
    api_id
    FROM table_api
    WHERE system_status = 1
    AND app_id = #p(app_id)
    #if(api_name)
    #set(api_name = "%" + api_name + "%")
    AND api_name LIKE #p(api_name)
    #end
    ORDER BY system_create_time DESC
    LIMIT #p(m), #p(n)
  #end

  #sql("listByOrApp_idOrLikeApi_nameAndLimit")
    SELECT
    api_id
    FROM table_api
    WHERE system_status = 1
    #if(app_id)
    AND app_id = #p(app_id)
    #end
    #if(api_name)
    #set(api_name = "%" + api_name + "%")
    AND api_name LIKE #p(api_name)
    #end
    ORDER BY system_create_time DESC
    LIMIT #p(m), #p(n)
  #end

  #sql("findByApi_id")
    SELECT
    *
    FROM table_api
    WHERE system_status = 1
    AND api_id = #p(api_id)
  #end

  #sql("save")
    INSERT INTO table_api (
      api_id,
      app_id,
      api_name,
      api_url,
      system_create_user_id,
      system_create_time,
      system_update_user_id,
      system_update_time,
      system_version,
      system_status
    ) VALUES (
      #p(api_id),
      #p(app_id),
      #p(api_name),
      #p(api_url),
      #p(system_create_user_id),
      #p(system_create_time),
      #p(system_update_user_id),
      #p(system_update_time),
      #p(system_version),
      #p(system_status)
    )
  #end

  #sql("update")
    UPDATE table_api SET
    api_name = #p(api_name),
    api_url = #p(api_url),
    system_update_user_id = #p(system_update_user_id),
    system_update_time = #p(system_update_time),
    system_version = system_version + 1
    WHERE system_status = 1
    AND api_id = #p(api_id)
    AND system_version = #p(system_version)
  #end

  #sql("deleteByApi_idAndSystem_version")
    UPDATE table_api SET
    system_update_user_id = #p(system_update_user_id),
    system_update_time = #p(system_update_time),
    system_version = system_version + 1,
    system_status = 0
    WHERE system_status = 1
    AND api_id = #p(api_id)
    AND system_version = #p(system_version)
  #end

#end