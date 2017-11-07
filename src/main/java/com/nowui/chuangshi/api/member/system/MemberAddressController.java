package com.nowui.chuangshi.api.member.system;


import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.member.service.MemberAddressService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;



@ControllerKey("/system/member/address")
public class MemberAddressController extends Controller {

    private final MemberAddressService memberAddressService = new MemberAddressService();

    @ActionKey("/system/member/address/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/system/member/address/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/system/member/address/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/system/member/address/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/system/member/address/delete")
    public void delete() {

        renderSuccessJson();
    }

}