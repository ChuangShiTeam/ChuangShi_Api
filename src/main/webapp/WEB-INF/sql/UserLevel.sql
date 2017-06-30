#namespace("user_level")

  #sql("countByApp_idOrLikeUser_level_name")
    SELECT COUNT(*) FROM table_user_level
    WHERE system_status = 1
    AND app_id = #p(app_id)
    #if(user_level_name)
    #set(user_level_name = "%" + user_level_name + "%")
    AND user_level_name LIKE #p(user_level_name)
    #end
  #end

  #sql("countByOrApp_idOrLikeUser_level_name")
    SELECT COUNT(*) FROM table_user_level
    WHERE system_status = 1
    #if(app_id)
    AND app_id = #p(app_id)
    #end
    #if(user_level_name)
    #set(user_level_name = "%" + user_level_name + "%")
    AND user_level_name LIKE #p(user_level_name)
    #end
  #end

  #sql("listByApp_idAndSystem_create_timeAndLimit")
    SELECT
    user_level_id
    FROM table_user_level
    WHERE system_status = 1
    AND app_id = #p(app_id)
    AND system_create_time < UNIX_TIMESTAMP(#p(system_create_time))
    ORDER BY system_create_time DESC
    LIMIT #p(m), #p(n)
  #end

  #sql("listByApp_idOrLikeUser_level_nameAndLimit")
    SELECT
    user_level_id
    FROM table_user_level
    WHERE system_status = 1
    AND app_id = #p(app_id)
    #if(user_level_name)
    #set(user_level_name = "%" + user_level_name + "%")
    AND user_level_name LIKE #p(user_level_name)
    #end
    ORDER BY system_create_time DESC
    LIMIT #p(m), #p(n)
  #end

  #sql("listByOrApp_idOrLikeUser_level_nameAndLimit")
    SELECT
    user_level_id
    FROM table_user_level
    WHERE system_status = 1
    #if(app_id)
    AND app_id = #p(app_id)
    #end
    #if(user_level_name)
    #set(user_level_name = "%" + user_level_name + "%")
    AND user_level_name LIKE #p(user_level_name)
    #end
    ORDER BY system_create_time DESC
    LIMIT #p(m), #p(n)
  #end

  #sql("findByUser_level_id")
    SELECT
    *
    FROM table_user_level
    WHERE system_status = 1
    AND user_level_id = #p(user_level_id)
  #end

  #sql("save")
    INSERT INTO table_user_level (
      user_level_id,
      app_id,
      user_level_name,
      user_level_value,
      user_level_sort,
      system_create_user_id,
      system_create_time,
      system_update_user_id,
      system_update_time,
      system_version,
      system_status
    ) VALUES (
      #p(user_level_id),
      #p(app_id),
      #p(user_level_name),
      #p(user_level_value),
      #p(user_level_sort),
      #p(system_create_user_id),
      #p(system_create_time),
      #p(system_update_user_id),
      #p(system_update_time),
      #p(system_version),
      #p(system_status)
    )
  #end

  #sql("update")
    UPDATE table_user_level SET
    user_level_name = #p(user_level_name),
    user_level_value = #p(user_level_value),
    user_level_sort = #p(user_level_sort),
    system_update_user_id = #p(system_update_user_id),
    system_update_time = #p(system_update_time),
    system_version = system_version + 1
    WHERE system_status = 1
    AND user_level_id = #p(user_level_id)
    AND system_version = #p(system_version)
  #end

  #sql("deleteByUser_level_idAndSystem_version")
    UPDATE table_user_level SET
    system_update_user_id = #p(system_update_user_id),
    system_update_time = #p(system_update_time),
    system_version = system_version + 1,
    system_status = 0
    WHERE system_status = 1
    AND user_level_id = #p(user_level_id)
    AND system_version = #p(system_version)
  #end

#end