#namespace("xietong_student")

  #sql("clazzList")
    SELECT
    table_xietong_student.*,
    table_file.file_path
    FROM table_xietong_student
    LEFT JOIN table_file ON table_xietong_student.student_image = table_file.file_id
    WHERE table_xietong_student.system_status = 1
    AND (
      #for(clazz_id : clazzIdList)
        #if(!for.first)OR#end table_xietong_student.clazz_id = #p(clazz_id)
      #end
    )
    AND table_xietong_student.student_category_id = #p(student_category_id)
    ORDER BY table_xietong_student.system_create_time DESC
    LIMIT #p(m), #p(n)
  #end

#end