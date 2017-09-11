package com.nowui.chuangshi.api.member.mobile;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;

@ControllerKey("/mobile/member/address")
public class MemberAddressController extends Controller {

    @ActionKey("/mobile/member/address/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/member/address/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/member/address/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/member/address/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/member/address/delete")
    public void delete() {

        renderSuccessJson();
    }

}