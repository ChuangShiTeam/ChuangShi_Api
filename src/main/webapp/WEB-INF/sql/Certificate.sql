#namespace("certificate")

  #sql("countByApp_idOrLikeCertificate_number")
    SELECT COUNT(*) FROM table_certificate
    WHERE system_status = 1
    AND app_id = #p(app_id)
    #if(certificate_number)
    #set(certificate_number = "%" + certificate_number + "%")
    AND certificate_number LIKE #p(certificate_number)
    #end
  #end

  #sql("countByOrApp_idOrLikeCertificate_number")
    SELECT COUNT(*) FROM table_certificate
    WHERE system_status = 1
    #if(app_id)
    AND app_id = #p(app_id)
    #end为
    #if(ertificate_number)
    #set(certificate_number = "%" + certificate_number + "%")
    AND certificate_number LIKE #p(certificate_number)
    #end
  #end

  #sql("listByApp_idAndSystem_create_timeAndLimit")
    SELECT
    *
    FROM table_certificate
    WHERE system_status = 1
    AND app_id = #p(app_id)
    AND system_create_time < UNIX_TIMESTAMP(#p(system_create_time))
    ORDER BY system_create_time DESC
    LIMIT #p(m), #p(n)
  #end

  #sql("listByApp_idOrLikeCertificate_numberAndLimit")
    SELECT
    *
    FROM table_certificate
    WHERE system_status = 1
    AND app_id = #p(app_id)
    #if(Certificate_number)
    #set(Certificate_number = "%" + Certificate_number + "%")
    AND Certificate_number LIKE #p(Certificate_number)
    #end
    ORDER BY system_create_time DESC
    LIMIT #p(m), #p(n)
  #end

  #sql("listByOrApp_idOrLikeCertificate_numberAndLimit")
    SELECT
    *
    FROM table_certificate
    WHERE system_status = 1
    #if(app_id)
    AND app_id = #p(app_id)
    #end
    #if(certificate_number)
    #set(certificate_number = "%" + certificate_number + "%")
    AND certificate_number LIKE #p(certificate_number)
    #end
    ORDER BY system_create_time DESC
    LIMIT #p(m), #p(n)
  #end

  #sql("findByCertificate_id")
    SELECT
    *
    FROM table_certificate
    WHERE system_status = 1
    AND certificate_id = #p(certificate_id)
  #end

  #sql("findByUser_id")
    SELECT
    *
    FROM table_certificate
    WHERE system_status = 1
    AND user_id = #p(user_id)
  #end

  #sql("save")
    INSERT INTO table_certificate (
      certificate_id,
      app_id,
      user_id,
      certificate_number,
      certificate_start_date,
      certificate_end_date,
      certificate_is_pay,
      system_create_user_id,
      system_create_time,
      system_update_user_id,
      system_update_time,
      system_version,
      system_status
    ) VALUES (
      #p(certificate_id),
      #p(app_id),
      #p(user_id),
      #p(certificate_number),
      #p(certificate_start_date),
      #p(certificate_end_date),
      #p(certificate_is_pay),
      #p(system_create_user_id),
      #p(system_create_time),
      #p(system_update_user_id),
      #p(system_update_time),
      #p(system_version),
      #p(system_status)
    )
  #end

  #sql("update")
    UPDATE table_certificate SET
    user_id = #p(user_id),
    certificate_number = #p(certificate_number),
    certificate_start_date = #p(certificate_start_date),
    certificate_end_date = #p(certificate_end_date),
    certificate_is_pay = #p(certificate_is_pay),
    system_update_user_id = #p(system_update_user_id),
    system_update_time = #p(system_update_time),
    system_version = system_version + 1
    WHERE system_status = 1
    AND certificate_id = #p(certificate_id)
    AND system_version = #p(system_version)
  #end

  #sql("deleteByCertificate_idAndSystem_version")
    UPDATE table_certificate SET
    system_update_user_id = #p(system_update_user_id),
    system_update_time = #p(system_update_time),
    system_version = system_version + 1,
    system_status = 0
    WHERE system_status = 1
    AND certificate_id = #p(certificate_id)
    AND system_version = #p(system_version)
  #end

#end