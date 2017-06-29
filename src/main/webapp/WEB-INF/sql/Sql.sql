#namespace("sql")

  #sql("countByApp_id")
    SELECT COUNT(*) FROM table_sql
    WHERE system_status = 1
    AND app_id = #p(app_id)
  #end

  #sql("countByOrApp_id")
    SELECT COUNT(*) FROM table_sql
    WHERE system_status = 1
    #if(app_id)
    AND app_id = #p(app_id)
    #end
  #end

  #sql("listByApp_idAndSystem_create_timeAndLimit")
    SELECT
    sql_id
    FROM table_sql
    WHERE system_status = 1
    AND app_id = #p(app_id)
    AND system_create_time < UNIX_TIMESTAMP(#p(system_create_time))
    ORDER BY system_create_time DESC
    LIMIT #p(m), #p(n)
  #end

  #sql("listByApp_idAndLimit")
    SELECT
    sql_id
    FROM table_sql
    WHERE system_status = 1
    AND app_id = #p(app_id)
    ORDER BY system_create_time DESC
    LIMIT #p(m), #p(n)
  #end

  #sql("listByOrApp_idAndLimit")
    SELECT
    sql_id
    FROM table_sql
    WHERE system_status = 1
    #if(app_id)
    AND app_id = #p(app_id)
    #end
    ORDER BY system_create_time DESC
    LIMIT #p(m), #p(n)
  #end

  #sql("findBySql_id")
    SELECT
    *
    FROM table_sql
    WHERE system_status = 1
    AND sql_id = #p(sql_id)
  #end

  #sql("save")
    INSERT INTO table_sql (
      sql_id,
      app_id,
      http_id,
      sql_table,
      sql_action,
      sql_content,
      system_create_user_id,
      system_create_time,
      system_update_user_id,
      system_update_time,
      system_version,
      system_status
    ) VALUES (
      #p(sql_id),
      #p(app_id),
      #p(http_id),
      #p(sql_table),
      #p(sql_action),
      #p(sql_content),
      #p(system_create_user_id),
      #p(system_create_time),
      #p(system_update_user_id),
      #p(system_update_time),
      #p(system_version),
      #p(system_status)
    )
  #end

  #sql("update")
    UPDATE table_sql SET
    sql_table = #p(sql_table),
    sql_action = #p(sql_action),
    sql_content = #p(sql_content),
    system_update_user_id = #p(system_update_user_id),
    system_update_time = #p(system_update_time),
    system_version = system_version + 1
    WHERE system_status = 1
    AND sql_id = #p(sql_id)
    AND system_version = #p(system_version)
  #end

  #sql("deleteBySql_idAndSystem_version")
    UPDATE table_sql SET
    system_update_user_id = #p(system_update_user_id),
    system_update_time = #p(system_update_time),
    system_version = system_version + 1,
    system_status = 0
    WHERE system_status = 1
    AND sql_id = #p(sql_id)
    AND system_version = #p(system_version)
  #end

#end