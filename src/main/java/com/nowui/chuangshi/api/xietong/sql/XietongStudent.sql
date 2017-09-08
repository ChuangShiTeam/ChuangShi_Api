#namespace("xietong_student")

  #sql("updateUser_id")
    UPDATE table_xietong_student SET
    user_id = #p(user_id),
    system_update_user_id = #p(system_update_user_id),
    system_update_time = #p(system_update_time)
    WHERE student_id = #p(student_id)
  #end
  
  #sql("deleteAll")
    UPDATE table_xietong_student SET
    system_update_user_id = #p(system_update_user_id),
    system_update_time = #p(system_update_time),
    system_status = 0
    WHERE system_status = 1
  #end

#end