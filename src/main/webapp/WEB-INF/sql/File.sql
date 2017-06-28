#namespace("file")

  #sql("countByApp_id")
    SELECT COUNT(*) FROM table_file
    WHERE system_status = 1
    AND app_id = #p(app_id)
  #end

  #sql("countByOrApp_id")
    SELECT COUNT(*) FROM table_file
    WHERE system_status = 1
    #if(app_id)
    AND app_id = #p(app_id)
    #end
  #end

  #sql("listByApp_idAndSystem_create_timeAndLimit")
    SELECT
    file_id
    FROM table_file
    WHERE system_status = 1
    AND app_id = #p(app_id)
    AND system_create_time < UNIX_TIMESTAMP(#p(system_create_time))
    ORDER BY system_create_time DESC
    LIMIT #p(m), #p(n)
  #end

  #sql("listByApp_idAndLimit")
    SELECT
    file_id
    FROM table_file
    WHERE system_status = 1
    AND app_id = #p(app_id)
    ORDER BY system_create_time DESC
    LIMIT #p(m), #p(n)
  #end

  #sql("listByOrApp_idAndLimit")
    SELECT
    file_id
    FROM table_file
    WHERE system_status = 1
    #if(app_id)
      AND app_id = #p(app_id)
    #end
    ORDER BY system_create_time DESC
    LIMIT #p(m), #p(n)
  #end

  #sql("findByFile_id")
    SELECT
    *
    FROM table_file
    WHERE system_status = 1
    AND file_id = #p(file_id)
  #end

  #sql("save")
    INSERT INTO table_file (
      file_id,
      app_id,
      file_type,
      file_name,
      file_suffix,
      file_size,
      file_path,
      file_thumbnail_path,
      file_original_path,
      file_image,
      system_create_user_id,
      system_create_time,
      system_update_user_id,
      system_update_time,
      system_version,
      system_status
    ) VALUES (
      #p(file_id),
      #p(app_id),
      #p(file_type),
      #p(file_name),
      #p(file_suffix),
      #p(file_size),
      #p(file_path),
      #p(file_thumbnail_path),
      #p(file_original_path),
      #p(file_image),
      #p(system_create_user_id),
      #p(system_create_time),
      #p(system_update_user_id),
      #p(system_update_time),
      #p(system_version),
      #p(system_status)
    )
  #end

  #sql("update")
    UPDATE table_file SET
    file_type = #p(file_type),
    file_name = #p(file_name),
    file_suffix = #p(file_suffix),
    file_size = #p(file_size),
    file_path = #p(file_path),
    file_thumbnail_path = #p(file_thumbnail_path),
    file_original_path = #p(file_original_path),
    file_image = #p(file_image),
    system_update_user_id = #p(system_update_user_id),
    system_update_time = #p(system_update_time),
    system_version = system_version + 1
    WHERE system_status = 1
    AND file_id = #p(file_id)
    AND system_version = #p(system_version)
  #end

  #sql("deleteByFile_idAndSystem_version")
    UPDATE table_file SET
    system_update_user_id = #p(system_update_user_id),
    system_update_time = #p(system_update_time),
    system_version = system_version + 1,
    system_status = 0
    WHERE system_status = 1
    AND file_id = #p(file_id)
    AND system_version = #p(system_version)
  #end

#end