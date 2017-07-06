package com.nowui.chuangshi.controller;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.constant.Config;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.constant.Url;
import com.nowui.chuangshi.model.Admin;
import com.nowui.chuangshi.model.User;
import com.nowui.chuangshi.service.AdminService;
import com.nowui.chuangshi.service.MenuService;
import com.nowui.chuangshi.service.UserService;
import com.nowui.chuangshi.type.UserType;
import com.nowui.chuangshi.util.AesUtil;
import com.nowui.chuangshi.util.Util;

import java.util.*;

public class AdminController extends Controller {

    private final AdminService adminService = new AdminService();
    private final UserService userService = new UserService();
    private final MenuService menuService = new MenuService();

    @ActionKey(Url.ADMIN_ADMIN_LIST)
    public void adminList() {
        validateRequest_app_id();
        validate(User.USER_NAME, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        User model = getModel(User.class);
        String request_app_id = getRequest_app_id();

        authenticateRequest_app_idAndRequest_user_id();

        Integer total = adminService.countByApp_idOrLikeUser_name(request_app_id, model.getUser_name());
        List<Admin> resultList = adminService.listByApp_idOrLikeUser_nameAndLimit(request_app_id, model.getUser_name(), getM(), getN());

        for (Admin result : resultList) {
            User user = userService.findByUser_id(result.getUser_id());

            result.keep(Admin.ADMIN_ID, Admin.SYSTEM_VERSION);
            result.put(User.USER_NAME, user.getUser_name());
        }

        renderSuccessJson(total, resultList);
    }

    @ActionKey(Url.ADMIN_ADMIN_FIND)
    public void adminFind() {
        validateRequest_app_id();
        validate(Admin.ADMIN_ID);

        Admin model = getModel(Admin.class);

        authenticateRequest_app_idAndRequest_user_id();

        Admin admin = adminService.findByAdmin_id(model.getAdmin_id());

        authenticateApp_id(admin.getApp_id());

        User user = userService.findByUser_id(admin.getUser_id());

        admin.keep(Admin.ADMIN_ID, Admin.SYSTEM_VERSION);
        admin.put(User.USER_NAME, user.getUser_name());
        admin.put(User.USER_ACCOUNT, user.getUser_account());

        renderSuccessJson(admin);
    }

    @ActionKey(Url.ADMIN_ADMIN_SAVE)
    public void adminSave() {
        validateRequest_app_id();
        validate(User.USER_NAME, User.USER_ACCOUNT, User.USER_PASSWORD);

        User model = getModel(User.class);
        String admin_id = Util.getRandomUUID();
        String user_id = Util.getRandomUUID();
        String request_app_id = getRequest_app_id();
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        Boolean result = adminService.save(admin_id, request_app_id, user_id, request_user_id);

        if (result) {
            result = userService.saveByUser_idAndApp_idAndObject_idAndUser_typeAndUser_nameAndUser_accountAndUser_password(user_id, request_app_id, admin_id, UserType.ADMIN.getKey(), model.getUser_name(), model.getUser_account(), model.getUser_password(), request_user_id);
        }

        if (!result) {
            throw new RuntimeException("新增不成功");
        }

        renderSuccessJson(result);
    }

    @ActionKey(Url.ADMIN_ADMIN_DELETE)
    public void adminDelete() {
        validateRequest_app_id();
        validate(Admin.ADMIN_ID, Admin.SYSTEM_VERSION);

        Admin model = getModel(Admin.class);
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        Admin admin = adminService.findByAdmin_id(model.getAdmin_id());

        authenticateApp_id(admin.getApp_id());

        Boolean result = adminService.deleteByAdmin_idAndSystem_update_user_idValidateSystem_version(model.getAdmin_id(), request_user_id, model.getSystem_version());

        if (result) {
            User user = userService.findByUser_id(admin.getUser_id());

            result = userService.deleteByUser_idAndSystem_update_user_idValidateSystem_version(user.getUser_id(), request_user_id, user.getSystem_version());
        }

        if (!result) {
            throw new RuntimeException("删除不成功");
        }

        renderSuccessJson(result);
    }

    @ActionKey(Url.ADMIN_SYSTEM_LIST)
    public void systemList() {
        validateRequest_app_id();
        validate(Admin.APP_ID, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        User model = getModel(User.class);

        Integer total = adminService.countByOrApp_idOrLikeUser_name(model.getApp_id(), model.getUser_name());
        List<Admin> resultList = adminService.listByOrApp_idOrLikeUser_nameAndLimit(model.getApp_id(), model.getUser_name(), getM(), getN());

        for (Admin result : resultList) {
            User user = userService.findByUser_id(result.getUser_id());

            result.keep(Admin.ADMIN_ID, Admin.SYSTEM_VERSION);
            result.put(User.USER_NAME, user.getUser_name());
        }

        renderSuccessJson(total, resultList);
    }

    @ActionKey(Url.ADMIN_SYSTEM_FIND)
    public void systemFind() {
        validateRequest_app_id();
        validate(Admin.ADMIN_ID);

        Admin model = getModel(Admin.class);

        Admin admin = adminService.findByAdmin_id(model.getAdmin_id());

        User user = userService.findByUser_id(admin.getUser_id());

        admin.keep(Admin.ADMIN_ID, Admin.SYSTEM_VERSION);
        admin.put(User.USER_NAME, user.getUser_name());
        admin.put(User.USER_ACCOUNT, user.getUser_account());

        renderSuccessJson(admin);
    }

    @ActionKey(Url.ADMIN_SYSTEM_UPDATE)
    public void systemUpdate() {
        validateRequest_app_id();
        validate(Admin.ADMIN_ID, User.USER_NAME, User.USER_ACCOUNT, Admin.SYSTEM_VERSION);

        Admin model = getModel(Admin.class);
        String request_user_id = getRequest_user_id();

        Boolean result = adminService.updateValidateSystem_version(model.getAdmin_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey(Url.ADMIN_SYSTEM_DELETE)
    public void systemDelete() {
        validateRequest_app_id();
        validate(Admin.ADMIN_ID, Admin.SYSTEM_VERSION);

        Admin model = getModel(Admin.class);
        String request_user_id = getRequest_user_id();

        Admin admin = adminService.findByAdmin_id(model.getAdmin_id());

        Boolean result = adminService.deleteByAdmin_idAndSystem_update_user_idValidateSystem_version(model.getAdmin_id(), request_user_id, model.getSystem_version());

        if (result) {
            User user = userService.findByUser_id(admin.getUser_id());

            result = userService.deleteByUser_idAndSystem_update_user_idValidateSystem_version(user.getUser_id(), request_user_id, user.getSystem_version());
        }

        if (!result) {
            throw new RuntimeException("删除不成功");
        }

        renderSuccessJson(result);
    }

    @ActionKey(Url.ADMIN_LOGIN)
    public void login() {
        validateRequest_app_id();
        validate(User.USER_ACCOUNT, User.USER_PASSWORD);

        User model = getModel(User.class);
        String request_app_id = getRequest_app_id();

        User user = userService.findByApp_idAndUser_typeAndUser_accountAndUser_password(request_app_id, UserType.ADMIN.getKey(), model.getUser_account(), model.getUser_password());

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

    @ActionKey(Url.ADMIN_MENU_LIST)
    public void menuList() {
        validateRequest_app_id();

        String request_app_id = getRequest_app_id();

        List<Map<String, Object>> resultList = menuService.treeByApp_id(request_app_id);

        renderSuccessJson(resultList);
    }

}