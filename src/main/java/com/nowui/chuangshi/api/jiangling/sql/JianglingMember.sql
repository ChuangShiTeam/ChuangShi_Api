#namespace("jiangling_member")

  #sql("adminCount")
    SELECT
    COUNT(*)
    FROM table_user
    LEFT JOIN table_jiangling_member on table_jiangling_member.user_id = table_user.user_id
    WHERE table_user.system_status = 1
    AND table_user.app_id = #p(app_id) and table_user.user_type = #p(user_type)
    #if(user_name)
    #set(user_name = "%" + user_name + "%")
    AND table_user.user_name LIKE #p(user_name)
    #end
    #if(member_redeem_code)
    AND table_jiangling_member.member_redeem_code = #p(member_redeem_code)
    #end
  #end

  #sql("adminList")
    SELECT
    table_user.user_id,
    table_user.user_avatar,
    table_user.user_name,
    IFNULL(table_jiangling_member.member_diffent_point, 0) AS member_diffent_point,
    IFNULL(table_jiangling_member.member_like_point, 0) as member_like_point,
    IFNULL(table_jiangling_member.member_redeem_code, '') as member_redeem_code,
    IFNULL(table_jiangling_member.member_redeem_code_is_exchange, false) as member_redeem_code_is_exchange
    FROM table_user
    LEFT JOIN table_jiangling_member on table_jiangling_member.user_id = table_user.user_id
    WHERE table_user.system_status = 1
    AND table_user.app_id = #p(app_id) and table_user.user_type = #p(user_type)
    #if(user_name)
    #set(user_name = "%" + user_name + "%")
    AND table_user.user_name LIKE #p(user_name)
    #end
    #if(member_redeem_code)
    AND table_jiangling_member.member_redeem_code = #p(member_redeem_code)
    #end
    ORDER BY table_user.system_create_time DESC
    LIMIT #p(m), #p(n)
  #end

#end