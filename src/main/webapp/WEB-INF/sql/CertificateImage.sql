#namespace("certificate_image")

  #sql("listByApp_idAndSystem_create_timeAndLimit")
    SELECT
    certificate_image_id
    FROM table_certificate_image
    WHERE system_status = 1
    AND app_id = #p(app_id)
    AND system_create_time < UNIX_TIMESTAMP(#p(system_create_time))
    ORDER BY system_create_time DESC
    LIMIT #p(m), #p(n)
  #end

  #sql("listByApp_idOrLikeCertificate_image_nameAndLimit")
    SELECT
    certificate_image_id
    FROM table_certificate_image
    WHERE system_status = 1
    AND app_id = #p(app_id)
    #if(certificate_image_name)
    #set(certificate_image_name = "%" + certificate_image_name + "%")
    AND certificate_image_name LIKE #p(certificate_image_name)
    #end
    ORDER BY system_create_time DESC
    LIMIT #p(m), #p(n)
  #end

  #sql("listByCertificate_id")
    SELECT
    *
    FROM table_certificate_image
    WHERE system_status = 1
    #if(certificate_id)
    AND certificate_id = #p(certificate_id)
    #end
    ORDER BY system_create_time DESC
  #end

  #sql("save")
    INSERT INTO table_certificate_image (
      certificate_id,
      file_id,
      certificate_type,
      certificate_channel_name,
      certificate_channel_url,
      certificate_people_name,
      certificate_people_id_card,
      certificate_people_mobile,
      certificate_people_wx,
      certificate_shop_name,
      certificate_shop_url,
      certificate_start_date,
      certificate_end_date,
      system_create_user_id,
      system_create_time,
      system_update_user_id,
      system_update_time,
      system_version,
      system_status
    ) VALUES (
      #p(certificate_id),
      #p(file_id),
      #p(certificate_type),
      #p(certificate_channel_name),
      #p(certificate_channel_url),
      #p(certificate_people_name),
      #p(certificate_people_id_card),
      #p(certificate_people_mobile),
      #p(certificate_people_wx),
      #p(certificate_shop_name),
      #p(certificate_shop_url),
      #p(certificate_start_date),
      #p(certificate_end_date),
      #p(system_create_user_id),
      #p(system_create_time),
      #p(system_update_user_id),
      #p(system_update_time),
      #p(system_version),
      #p(system_status)
    )
  #end

  #sql("update")
    UPDATE table_certificate_image SET
    certificate_id = #p(certificate_id),
    file_id = #p(file_id),
    certificate_type = #p(certificate_type),
    certificate_channel_name = #p(certificate_channel_name),
    certificate_channel_url = #p(certificate_channel_url),
    certificate_people_name = #p(certificate_people_name),
    certificate_people_id_card = #p(certificate_people_id_card),
    certificate_people_mobile = #p(certificate_people_mobile),
    certificate_people_wx = #p(certificate_people_wx),
    certificate_shop_name = #p(certificate_shop_name),
    certificate_shop_url = #p(certificate_shop_url),
    certificate_start_date = #p(certificate_start_date),
    certificate_end_date = #p(certificate_end_date),
    system_update_user_id = #p(system_update_user_id),
    system_update_time = #p(system_update_time),
    system_version = system_version + 1
    WHERE system_status = 1
    AND certificate_image_id = #p(certificate_image_id)
    AND system_version = #p(system_version)
  #end

  #sql("deleteByCertificate_idAndSystem_update_user_id")
    UPDATE table_certificate_image SET
    system_update_user_id = #p(system_update_user_id),
    system_update_time = #p(system_update_time),
    system_version = system_version + 1,
    system_status = 0
    WHERE system_status = 1
    AND certificate_id = #p(certificate_id)
  #end

#end