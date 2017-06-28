#namespace("http")

  #sql("countByApp_id")
    SELECT COUNT(*) FROM table_http
    WHERE system_status = 1
    AND app_id = #p(app_id)
  #end

  #sql("countByOrApp_id")
    SELECT COUNT(*) FROM table_http
    WHERE system_status = 1
    #if(app_id)
      AND app_id = #p(app_id)
    #end
  #end

  #sql("countByApp_idAndHttp_url")
    SELECT COUNT(*) FROM table_http
    WHERE system_status = 1
    AND app_id = #p(app_id)
    AND http_url = #p(http_url)
  #end

  #sql("listByApp_idAndSystem_create_timeAndLimit")
    SELECT
    http_id
    FROM table_http
    WHERE system_status = 1
    AND app_id = #p(app_id)
    AND system_create_time < UNIX_TIMESTAMP(#p(system_create_time))
    ORDER BY system_create_time DESC
    LIMIT #p(m), #p(n)
  #end

  #sql("listByApp_idAndLimit")
    SELECT
    http_id
    FROM table_http
    WHERE system_status = 1
    AND app_id = #p(app_id)
    ORDER BY system_create_time DESC
    LIMIT #p(m), #p(n)
  #end

  #sql("listByOrApp_idAndLimit")
    SELECT
    http_id
    FROM table_http
    WHERE system_status = 1
    #if(app_id)
      AND app_id = #p(app_id)
    #end
    ORDER BY system_create_time DESC
    LIMIT #p(m), #p(n)
  #end

  #sql("findByHttp_id")
    SELECT
    *
    FROM table_http
    WHERE system_status = 1
    AND http_id = #p(http_id)
  #end

  #sql("save")
    INSERT INTO table_http (
      http_id,
      app_id,
      http_url,
      http_code,
      http_request,
      http_response,
      http_token,
      http_platform,
      http_version,
      http_ip_address,
      http_run_time,
      system_create_user_id,
      system_create_time,
      system_update_user_id,
      system_update_time,
      system_version,
      system_status
    ) VALUES (
      #p(http_id),
      #p(app_id),
      #p(http_url),
      #p(http_code),
      #p(http_request),
      #p(http_response),
      #p(http_token),
      #p(http_platform),
      #p(http_version),
      #p(http_ip_address),
      #p(http_run_time),
      #p(system_create_user_id),
      #p(system_create_time),
      #p(system_update_user_id),
      #p(system_update_time),
      #p(system_version),
      #p(system_status)
    )
  #end

  #sql("update")
    UPDATE table_http SET
    http_url = #p(http_url),
    http_code = #p(http_code),
    http_request = #p(http_request),
    http_response = #p(http_response),
    http_token = #p(http_token),
    http_platform = #p(http_platform),
    http_version = #p(http_version),
    http_ip_address = #p(http_ip_address),
    http_run_time = #p(http_run_time),
    system_update_user_id = #p(system_update_user_id),
    system_update_time = #p(system_update_time),
    system_version = system_version + 1
    WHERE system_status = 1
    AND http_id = #p(http_id)
    AND system_version = #p(system_version)
  #end

  #sql("deleteByHttp_idAndSystem_version")
    UPDATE table_http SET
    system_update_user_id = #p(system_update_user_id),
    system_update_time = #p(system_update_time),
    system_version = system_version + 1,
    system_status = 0
    WHERE system_status = 1
    AND http_id = #p(http_id)
    AND system_version = #p(system_version)
  #end

#end