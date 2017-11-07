package com.nowui.chuangshi.api.member.system;


import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.member.service.MemberLevelService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;



@ControllerKey("/system/member/level")
public class MemberLevelController extends Controller {

    private final MemberLevelService memberLevelService = new MemberLevelService();

    @ActionKey("/system/member/level/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/system/member/level/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/system/member/level/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/system/member/level/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/system/member/level/delete")
    public void delete() {

        renderSuccessJson();
    }

}