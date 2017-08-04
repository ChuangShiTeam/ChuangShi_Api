package com.nowui.chuangshi.api.member.mobile;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.member.service.MemberLevelService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;

@ControllerKey("/mobile/member/level")
public class MemberLevelController extends Controller {

    private final MemberLevelService memberLevelService = new MemberLevelService();

    @ActionKey("/mobile/member/level/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/member/level/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/member/level/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/member/level/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/member/level/delete")
    public void delete() {

        renderSuccessJson();
    }

}