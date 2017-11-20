package com.nowui.chuangshi.api.user.admin;

import java.util.List;
import java.util.Map;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.menu.model.Menu;
import com.nowui.chuangshi.api.user.service.UserService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.constant.Constant;

@ControllerKey("/admin/user")
public class UserController extends Controller {
    
    @ActionKey("/admin/user/menu/list")
    public void list() {
        
        String request_user_id = getRequest_user_id();

        List<Map<String, Object>> resultList = UserService.instance.menuList(request_user_id);

        validateResponse(Menu.MENU_ID, Menu.MENU_NAME, Menu.MENU_IMAGE, Menu.MENU_URL, Constant.CHILDREN);

        renderSuccessJson(resultList);
    }

}
