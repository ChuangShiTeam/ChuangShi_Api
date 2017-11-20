package com.nowui.chuangshi.api.role.admin;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.menu.model.Menu;
import com.nowui.chuangshi.api.role.model.RoleMenu;
import com.nowui.chuangshi.api.role.service.RoleMenuService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.constant.Constant;

@ControllerKey("/admin/role/menu")
public class RoleMenuController extends Controller {

    @ActionKey("/admin/role/menu/list")
    public void list() {
        validateRequest(RoleMenu.ROLE_ID);

        RoleMenu model = getModel(RoleMenu.class);
        String request_app_id = getRequest_app_id();

        List<Map<String, Object>> resultList = RoleMenuService.instance.appIdAndRoleIdList(request_app_id, model.getRole_id());

        validateResponse(Menu.MENU_ID, Menu.MENU_NAME, Constant.IS_SELECT, Constant.CHILDREN);

        renderSuccessJson(resultList);
    }

    @ActionKey("/admin/role/menu/update")
    public void update() {
        validateRequest(RoleMenu.ROLE_ID, "list");

        RoleMenu model = getModel(RoleMenu.class);
        JSONObject jsonObject = getParameterJSONObject();
        String request_user_id = getRequest_user_id();
        String request_app_id = getRequest_app_id();
        
        JSONArray jsonArray = jsonObject.getJSONArray("list");
        List<RoleMenu> roleMenuList = new ArrayList<>();
        
        for (int i = 0; i < jsonArray.size(); i++) {
            RoleMenu roleMenu = jsonArray.getJSONObject(i).toJavaObject(RoleMenu.class);
            roleMenuList.add(roleMenu);
        }

        Boolean result = RoleMenuService.instance.update(roleMenuList, request_app_id, model.getRole_id(), request_user_id);

        renderSuccessJson(result);
    }

}