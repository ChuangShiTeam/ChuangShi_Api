package com.nowui.chuangshi.api.admin.admin;

import com.alibaba.fastjson.JSONObject;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.admin.model.Admin;
import com.nowui.chuangshi.api.admin.service.AdminService;
import com.nowui.chuangshi.api.menu.model.Menu;
import com.nowui.chuangshi.api.menu.service.MenuService;
import com.nowui.chuangshi.api.user.model.User;
import com.nowui.chuangshi.api.user.service.UserService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;

import com.nowui.chuangshi.constant.Config;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.type.UserType;
import com.nowui.chuangshi.util.AesUtil;
import com.nowui.chuangshi.util.Util;

import java.util.*;


@ControllerKey("/admin/admin")
public class AdminController extends Controller {

    @ActionKey("/admin/admin/list")
    public void list() {
        validateRequest(User.USER_NAME, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        Admin model = getModel(Admin.class);
        String request_app_id = getRequest_app_id();

        Integer resultCount = AdminService.instance.adminCount(request_app_id, model.getStr(User.USER_NAME));
        List<Admin> resultList = AdminService.instance.adminList(request_app_id, model.getStr(User.USER_NAME), getM(), getN());

        validateResponse(Admin.ADMIN_ID, Admin.USER_ID, User.USER_NAME, Admin.SYSTEM_VERSION);

        renderSuccessJson(resultCount, resultList);
    }

    @ActionKey("/admin/admin/find")
    public void find() {
        validateRequest(Admin.ADMIN_ID);

        Admin model = getModel(Admin.class);

        Admin result = AdminService.instance.find(model.getAdmin_id());

        validateResponse(Admin.ADMIN_ID, Admin.USER_ID, User.USER_NAME, User.USER_ACCOUNT, Admin.SYSTEM_VERSION);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/admin/save")
    public void save() {
        validateRequest(User.USER_NAME, User.USER_ACCOUNT, User.USER_PASSWORD);

        String admin_id = Util.getRandomUUID();
        String user_id = Util.getRandomUUID();
        String request_app_id = getRequest_app_id();
        String request_user_id = getRequest_user_id();

        Admin model = getModel(Admin.class);
        model.setAdmin_id(admin_id);
        model.setUser_id(user_id);

        Boolean result = AdminService.instance.save(model, request_user_id);

        if (result) {
            result = UserService.instance.userAccountSave(user_id, request_app_id, admin_id, UserType.ADMIN.getKey(), model.getStr(User.USER_NAME), model.getStr(User.USER_ACCOUNT), model.getStr(User.USER_PASSWORD), request_user_id);
        }

        renderSuccessJson(result);
    }

    @ActionKey("/admin/admin/update")
    public void update() {
        validateRequest(Admin.ADMIN_ID, Admin.USER_ID, Admin.SYSTEM_VERSION);

        Admin model = getModel(Admin.class);
        String request_user_id = getRequest_user_id();

        Boolean result = AdminService.instance.update(model, model.getAdmin_id(), request_user_id, model.getSystem_version());

        if (result) {
            Admin admin = AdminService.instance.find(model.getAdmin_id());
            UserService.instance.userAccountAndNameUpdate(admin.getUser_id(), model.getStr(User.USER_ACCOUNT), model.getStr(User.USER_NAME), request_user_id);
            UserService.instance.userPasswordUpdate(admin.getUser_id(), model.getStr(User.USER_PASSWORD), request_user_id);
        }

        renderSuccessJson(result);
    }

    @ActionKey("/admin/admin/delete")
    public void delete() {
        validateRequest(Admin.ADMIN_ID, Admin.SYSTEM_VERSION);

        Admin model = getModel(Admin.class);
        String request_user_id = getRequest_user_id();

        Boolean result = AdminService.instance.delete(model.getAdmin_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey("/admin/admin/menu")
    public void menu() {
        String request_app_id = getRequest_app_id();

        List<Map<String, Object>> resultList = MenuService.instance.menuList(request_app_id);

        validateResponse(Menu.MENU_ID, Menu.MENU_NAME, Menu.MENU_IMAGE, Menu.MENU_URL, Constant.CHILDREN);

        renderSuccessJson(resultList);
    }

    @ActionKey("/admin/admin/login")
    public void login() {
        validateRequest(User.USER_ACCOUNT, User.USER_PASSWORD);

        User model = getModel(User.class);
        String request_app_id = getRequest_app_id();

        User user = UserService.instance.userAccountFind(request_app_id, UserType.ADMIN.getKey(), model.getUser_account(), model.getUser_password());

        if (user == null) {
            throw new RuntimeException("帐号或者密码不正确");
        }

        Map<String, Object> result = new HashMap<String, Object>();
        try {
            Date date = new Date();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.YEAR, 1);

            JSONObject jsonObject = new JSONObject();
            jsonObject.put(User.USER_ID, user.getUser_id());
            jsonObject.put(Constant.EXPIRE_TIME, calendar.getTime());
            result.put(Constant.TOKEN, AesUtil.aesEncrypt(jsonObject.toJSONString(), Config.private_key));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("登录不成功");
        }

        renderSuccessJson(result);
    }

}