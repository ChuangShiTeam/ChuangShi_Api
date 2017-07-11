#namespace("qrcode")

  #sql("countByApp_idOrQrcode_type")
    SELECT COUNT(*) FROM table_qrcode
    WHERE system_status = 1
    AND app_id = #p(app_id)
    #if(qrcode_type)
    AND qrcode_type = #p(qrcode_type)
    #end
  #end

  #sql("countByOrApp_idOrQrcode_type")
    SELECT COUNT(*) FROM table_qrcode
    WHERE system_status = 1
    #if(app_id)
    AND app_id = #p(app_id)
    #end
    #if(qrcode_type)
    AND qrcode_type = #p(qrcode_type)
    #end
  #end

  #sql("listByApp_idAndSystem_create_timeAndLimit")
    SELECT
    *
    FROM table_qrcode
    WHERE system_status = 1
    AND app_id = #p(app_id)
    AND system_create_time < UNIX_TIMESTAMP(#p(system_create_time))
    ORDER BY system_create_time DESC
    LIMIT #p(m), #p(n)
  #end

  #sql("listByApp_idOrQrcode_typeAndLimit")
    SELECT
    qrcode_id
    FROM table_qrcode
    WHERE system_status = 1
    AND app_id = #p(app_id)
    #if(qrcode_type)
    AND qrcode_type = #p(qrcode_type)
    #end
    ORDER BY system_create_time DESC
    LIMIT #p(m), #p(n)
  #end

  #sql("listByOrApp_idOrQrcode_typeAndLimit")
    SELECT
    qrcode_id
    FROM table_qrcode
    WHERE system_status = 1
    #if(app_id)
    AND app_id = #p(app_id)
    #end
    #if(qrcode_type)
    AND qrcode_type = #p(qrcode_type)
    #end
    ORDER BY system_create_time DESC
    LIMIT #p(m), #p(n)
  #end

  #sql("findByQrcode_id")
    SELECT
    *
    FROM table_qrcode
    WHERE system_status = 1
    AND qrcode_id = #p(qrcode_id)
  #end

  #sql("save")
    INSERT INTO table_qrcode (
      qrcode_id,
      app_id,
      object_id,
      qrcode_type,
      qrcode_url,
      qrcode_add,
      qrcode_cancel,
      qrcode_status,
      system_create_user_id,
      system_create_time,
      system_update_user_id,
      system_update_time,
      system_version,
      system_status
    ) VALUES (
      #p(qrcode_id),
      #p(app_id),
      #p(object_id),
      #p(qrcode_type),
      #p(qrcode_url),
      #p(qrcode_add),
      #p(qrcode_cancel),
      #p(qrcode_status),
      #p(system_create_user_id),
      #p(system_create_time),
      #p(system_update_user_id),
      #p(system_update_time),
      #p(system_version),
      #p(system_status)
    )
  #end

  #sql("update")
    UPDATE table_qrcode SET
    object_id = #p(object_id),
    qrcode_type = #p(qrcode_type),
    qrcode_url = #p(qrcode_url),
    qrcode_add = #p(qrcode_add),
    qrcode_cancel = #p(qrcode_cancel),
    qrcode_status = #p(qrcode_status),
    system_update_user_id = #p(system_update_user_id),
    system_update_time = #p(system_update_time),
    system_version = system_version + 1
    WHERE system_status = 1
    AND qrcode_id = #p(qrcode_id)
    AND system_version = #p(system_version)
  #end

  #sql("deleteByQrcode_idAndSystem_version")
    UPDATE table_qrcode SET
    system_update_user_id = #p(system_update_user_id),
    system_update_time = #p(system_update_time),
    system_version = system_version + 1,
    system_status = 0
    WHERE system_status = 1
    AND qrcode_id = #p(qrcode_id)
    AND system_version = #p(system_version)
  #end

#end