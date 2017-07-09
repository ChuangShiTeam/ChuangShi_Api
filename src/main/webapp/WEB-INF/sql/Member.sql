#namespace("member")

  #sql("countByApp_idOrLikeUser_name")
    SELECT COUNT(*) FROM table_member
    #if(user_name)
    LEFT JOIN table_user ON table_user.user_id = table_admin.user_id
    #end
    WHERE table_member.system_status = 1
    AND table_member.app_id = #p(app_id)
    #if(user_name)
    #set(user_name = "%" + user_name + "%")
    AND table_user.user_name LIKE #p(user_name)
    #end
  #end

  #sql("countByOrApp_idOrLikeUser_name")
    SELECT COUNT(*) FROM table_member
    #if(user_name)
    LEFT JOIN table_user ON table_user.user_id = table_admin.user_id
    #end
    WHERE table_member.system_status = 1
    #if(app_id)
    AND table_member.app_id = #p(app_id)
    #end
    #if(user_name)
    #set(user_name = "%" + user_name + "%")
    AND table_user.user_name LIKE #p(user_name)
    #end
  #end

  #sql("listByMember_parent_pathLikeMember_parent_id")
    SELECT
    table_member.member_id, table_member.member_parent_id, table_user.user_name, IFNULL(table_member_level.member_level_name, '') AS member_level_name, table_file.file_path AS user_avatar
    FROM table_member
    LEFT JOIN table_user ON table_member.user_id = table_user.user_id
    LEFT JOIN table_member_level ON table_member.member_level_id = table_member_level.member_level_id
    LEFT JOIN table_file ON table_user.user_avatar = table_file.file_id
    WHERE table_member.system_status = 1
    AND table_member.member_parent_path LIKE #p("%" + member_parent_id + "%")
    ORDER BY table_member.member_status ASC, table_member.system_create_time ASC
  #end

  #sql("listByApp_idAndSystem_create_timeAndLimit")
    SELECT
    member_id
    FROM table_member
    WHERE system_status = 1
    AND app_id = #p(app_id)
    AND system_create_time < UNIX_TIMESTAMP(#p(system_create_time))
    ORDER BY system_create_time DESC
    LIMIT #p(m), #p(n)
  #end

  #sql("listByApp_idOrLikeUser_nameAndLimit")
    SELECT
    member_id
    FROM table_member
    #if(user_name)
    LEFT JOIN table_user ON table_user.user_id = table_admin.user_id
    #end
    WHERE table_member.system_status = 1
    AND table_member.app_id = #p(app_id)
    #if(user_name)
    #set(user_name = "%" + user_name + "%")
    AND table_user.user_name LIKE #p(user_name)
    #end
    ORDER BY table_member.system_create_time DESC
    LIMIT #p(m), #p(n)
  #end
  
  #sql("listByApp_id")
    SELECT
    member_id
    FROM table_member
    WHERE table_member.system_status = 1
    AND table_member.app_id = #p(app_id)
    ORDER BY table_member.system_create_time DESC
  #end
  
  #sql("listByOrApp_id")
    SELECT
    member_id
    FROM table_member
    WHERE table_member.system_status = 1
    #if(app_id)
    AND table_member.app_id = #p(app_id)
    #end
    ORDER BY table_member.system_create_time DESC
  #end

  #sql("listByOrApp_idOrLikeUser_nameAndLimit")
    SELECT
    member_id
    FROM table_member
    #if(user_name)
    LEFT JOIN table_user ON table_user.user_id = table_admin.user_id
    #end
    WHERE table_member.system_status = 1
    #if(app_id)
    AND table_member.app_id = #p(app_id)
    #end
    #if(user_name)
    #set(user_name = "%" + user_name + "%")
    AND table_user.user_name LIKE #p(user_name)
    #end
    ORDER BY table_member.system_create_time DESC
    LIMIT #p(m), #p(n)
  #end

  #sql("findByMember_id")
    SELECT
    *
    FROM table_member
    WHERE system_status = 1
    AND member_id = #p(member_id)
  #end

  #sql("save")
    INSERT INTO table_member (
      member_id,
      app_id,
      user_id,
      member_parent_id,
      from_qrcode_id,
      qrcode_id,
      member_level_id,
      member_parent_path,
      member_status,
      system_create_user_id,
      system_create_time,
      system_update_user_id,
      system_update_time,
      system_version,
      system_status
    ) VALUES (
      #p(member_id),
      #p(app_id),
      #p(user_id),
      #p(member_parent_id),
      #p(from_qrcode_id),
      #p(qrcode_id),
      #p(member_level_id),
      #p(member_parent_path),
      #p(member_status),
      #p(system_create_user_id),
      #p(system_create_time),
      #p(system_update_user_id),
      #p(system_update_time),
      #p(system_version),
      #p(system_status)
    )
  #end

  #sql("update")
    UPDATE table_member SET
    user_id = #p(user_id),
    member_parent_id = #p(member_parent_id),
    from_qrcode_id = #p(from_qrcode_id),
    qrcode_id = #p(qrcode_id),
    member_level_id = #p(member_level_id),
    member_parent_path = #p(member_parent_path),
    member_status = #p(member_status),
    system_update_user_id = #p(system_update_user_id),
    system_update_time = #p(system_update_time),
    system_version = system_version + 1
    WHERE system_status = 1
    AND member_id = #p(member_id)
    AND system_version = #p(system_version)
  #end

  #sql("deleteByMember_idAndSystem_version")
    UPDATE table_member SET
    system_update_user_id = #p(system_update_user_id),
    system_update_time = #p(system_update_time),
    system_version = system_version + 1,
    system_status = 0
    WHERE system_status = 1
    AND member_id = #p(member_id)
    AND system_version = #p(system_version)
  #end

#end