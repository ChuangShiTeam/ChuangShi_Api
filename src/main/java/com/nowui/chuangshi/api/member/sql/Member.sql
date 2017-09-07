#namespace("member")

  #sql("memberChildrenList")
    SELECT
    table_member.member_id,
    table_member.member_parent_id,
    table_user.user_name,
    IFNULL(table_member_level.member_level_name, '') AS member_level_name,
    table_member.member_status, table_file.file_path AS user_avatar
    FROM table_member
    LEFT JOIN table_user ON table_member.user_id = table_user.user_id
    LEFT JOIN table_member_level ON table_member.member_level_id = table_member_level.member_level_id
    LEFT JOIN table_file ON table_user.user_avatar = table_file.file_id
    WHERE table_member.system_status = 1
    AND table_member.member_parent_path LIKE #p("%" + member_parent_id + "%")
    ORDER BY table_member.member_status ASC, table_member.system_create_time ASC
  #end

#end