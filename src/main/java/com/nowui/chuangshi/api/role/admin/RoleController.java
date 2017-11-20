package com.nowui.chuangshi.api.role.admin;


import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.role.model.Role;
import com.nowui.chuangshi.api.role.service.RoleService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;

import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.util.Util;

import java.util.List;

@ControllerKey("/admin/role")
public class RoleController extends Controller {

    @ActionKey("/admin/role/list")
    public void list() {
        validateRequest(Role.ROLE_NAME, Role.ROLE_CODE, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        Role model = getModel(Role.class);
        String request_app_id = getRequest_app_id();

        Integer resultCount = RoleService.instance.adminCount(request_app_id, model.getRole_name(), model.getRole_code());
        List<Role> resultList = RoleService.instance.adminList(request_app_id, model.getRole_name(), model.getRole_code(), getM(), getN());

        validateResponse(Role.ROLE_ID, Role.ROLE_NAME, Role.ROLE_CODE, Role.ROLE_SORT, Role.SYSTEM_VERSION);

        renderSuccessJson(resultCount, resultList);
    }

    @ActionKey("/admin/role/find")
    public void find() {
        validateRequest(Role.ROLE_ID);

        Role model = getModel(Role.class);

        Role result = RoleService.instance.find(model.getRole_id());

        validateResponse(Role.ROLE_NAME, Role.ROLE_CODE, Role.ROLE_DESCRIPTION, Role.ROLE_SORT, Role.SYSTEM_VERSION);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/role/save")
    public void save() {
        validateRequest(Role.ROLE_NAME, Role.ROLE_CODE, Role.ROLE_DESCRIPTION, Role.ROLE_SORT);

        Role model = getModel(Role.class);
        model.setRole_id(Util.getRandomUUID());
        String request_user_id = getRequest_user_id();

        Boolean result = RoleService.instance.save(model, request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/role/update")
    public void update() {
        validateRequest(Role.ROLE_ID, Role.ROLE_NAME, Role.ROLE_CODE, Role.ROLE_DESCRIPTION, Role.ROLE_SORT, Role.SYSTEM_VERSION);

        Role model = getModel(Role.class);
        String request_user_id = getRequest_user_id();

        Boolean result = RoleService.instance.update(model, model.getRole_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey("/admin/role/delete")
    public void delete() {
        validateRequest(Role.ROLE_ID, Role.SYSTEM_VERSION);

        Role model = getModel(Role.class);
        String request_user_id = getRequest_user_id();

        Boolean result = RoleService.instance.delete(model.getRole_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

}