package com.nowui.chuangshi.api.user.admin;


import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.role.model.Role;
import com.nowui.chuangshi.api.user.model.UserRole;
import com.nowui.chuangshi.api.user.service.UserRoleService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.constant.Constant;

@ControllerKey("/admin/user/role")
public class UserRoleController extends Controller {

    @ActionKey("/admin/user/role/list")
    public void list() {
        validateRequest(UserRole.USER_ID);
        
        UserRole model = getModel(UserRole.class);
        String request_app_id = getRequest_app_id();

        List<Role> resultList = UserRoleService.instance.appIdAndUserIdList(request_app_id, model.getUser_id());

        validateResponse(Role.ROLE_ID, Role.ROLE_NAME, Constant.IS_SELECT);

        renderSuccessJson(resultList);
    }

    @ActionKey("/admin/user/role/update")
    public void update() {
        validateRequest(UserRole.USER_ID, "list");

        UserRole model = getModel(UserRole.class);
        JSONObject jsonObject = getParameterJSONObject();
        String request_user_id = getRequest_user_id();
        String request_app_id = getRequest_app_id();
        
        JSONArray jsonArray = jsonObject.getJSONArray("list");
        List<UserRole> userRoleList = new ArrayList<>();
        
        for (int i = 0; i < jsonArray.size(); i++) {
            UserRole userRole = jsonArray.getJSONObject(i).toJavaObject(UserRole.class);
            userRoleList.add(userRole);
        }

        Boolean result = UserRoleService.instance.update(userRoleList, request_app_id, model.getUser_id(), request_user_id);

        renderSuccessJson(result);
    }

}