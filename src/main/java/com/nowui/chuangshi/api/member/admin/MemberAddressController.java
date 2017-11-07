package com.nowui.chuangshi.api.member.admin;


import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;



@ControllerKey("/admin/member/address")
public class MemberAddressController extends Controller {

    @ActionKey("/admin/member/address/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/admin/member/address/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/admin/member/address/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/admin/member/address/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/admin/member/address/delete")
    public void delete() {

        renderSuccessJson();
    }

}