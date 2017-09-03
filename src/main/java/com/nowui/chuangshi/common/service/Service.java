package com.nowui.chuangshi.common.service;
import com.nowui.chuangshi.common.model.Model;
import com.nowui.chuangshi.constant.Constant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Service {

    protected List<Map<String, Object>> getChildren(List<? extends Model> list, String parent_id, String parent_id_column_name, String id_column_name, String name_column_name, String... keys) {
        List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
        for (Model model : list) {
            if (model.getStr(parent_id_column_name).equals(parent_id)) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put(id_column_name, model.getStr(id_column_name));
                map.put(name_column_name, model.getStr(name_column_name));

                for (String key : keys) {
                    map.put(key, model.get(key));
                }

                List<Map<String, Object>> childrenList = getChildren(list, model.getStr(id_column_name), parent_id_column_name, id_column_name, name_column_name, keys);
                map.put(Constant.CHILDREN, childrenList);

                resultList.add(map);
            }
        }
        return resultList;
    }

}
