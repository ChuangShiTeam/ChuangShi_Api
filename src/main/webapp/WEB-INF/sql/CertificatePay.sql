#namespace("certificate_pay")

  #sql("findByCertificate_id")
    SELECT
    *
    FROM table_certificate_pay
    WHERE system_status = 1
    AND certificate_id = #p(certificate_id)
  #end

  #sql("save")
    INSERT INTO table_certificate_pay (
      certificate_id,
      member_level_id,
      certificate_amount,
      certificate_pay_account,
      certificate_pay_time,
      certificate_pay_result,
      system_create_user_id,
      system_create_time,
      system_update_user_id,
      system_update_time,
      system_version,
      system_status
    ) VALUES (
      #p(certificate_id),
      #p(member_level_id),
      #p(certificate_amount),
      #p(certificate_pay_account),
      #p(certificate_pay_time),
      #p(certificate_pay_result),
      #p(system_create_user_id),
      #p(system_create_time),
      #p(system_update_user_id),
      #p(system_update_time),
      #p(system_version),
      #p(system_status)
    )
  #end

  #sql("update")
    UPDATE table_certificate_pay SET
    member_level_id = #p(member_level_id),
    certificate_amount = #p(certificate_amount),
    certificate_pay_account = #p(certificate_pay_account),
    certificate_pay_time = #p(certificate_pay_time),
    certificate_pay_result = #p(certificate_pay_result),
    system_update_user_id = #p(system_update_user_id),
    system_update_time = #p(system_update_time),
    system_version = system_version + 1
    WHERE system_status = 1
    AND certificate_id = #p(certificate_id)
    AND system_version = #p(system_version)
  #end

  #sql("deleteByCertificate_idAndSystem_version")
    UPDATE table_certificate_pay SET
    system_update_user_id = #p(system_update_user_id),
    system_update_time = #p(system_update_time),
    system_version = system_version + 1,
    system_status = 0
    WHERE system_status = 1
    AND certificate_id = #p(certificate_id)
    AND system_version = #p(system_version)
  #end

#end