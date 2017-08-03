#namespace("code")

  #sql("listByTable_schema")
    SELECT table_name, engine, table_rows
    FROM information_schema.tables
    WHERE table_schema = #p(table_schema)
    #if(table_name)
    #set(table_name = "%" + table_name + "%")
    AND table_name LIKE #p(table_name)
    #end
  #end

  #sql("listByTable_name")
    SELECT
    column_name,
    column_key,
    character_maximum_length,
    column_type,
    data_type,
    column_comment
    FROM information_schema.columns
    WHERE table_schema = #p(table_schema)
    AND table_name = #p(table_name)
  #end

#end