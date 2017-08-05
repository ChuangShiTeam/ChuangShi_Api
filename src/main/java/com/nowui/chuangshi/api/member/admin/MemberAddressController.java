package com.nowui.chuangshi.api.member.admin;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.member.model.MemberAddress;
import com.nowui.chuangshi.api.member.service.MemberAddressService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.common.interceptor.AdminInterceptor;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.util.Util;

import java.util.List;

@Before(AdminInterceptor.class)
@ControllerKey("/admin/member/address")
public class MemberAddressController extends Controller {

    private final MemberAddressService memberAddressService = MemberAddressService.me;

    @ActionKey("/admin/member/address/list")
    public void list() {
        validateRequest(MemberAddress.MEMBER_ADDRESS_NAME, MemberAddress.MEMBER_ADDRESS_MOBILE, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        MemberAddress model = getModel(MemberAddress.class);
        model.where(MemberAddress.APP_ID).andEmpty(MemberAddress.MEMBER_ADDRESS_NAME).andEmpty(MemberAddress.MEMBER_ADDRESS_MOBILE);

        Integer resultCount = memberAddressService.count(model);
        List<MemberAddress> resultList = memberAddressService.list(model.paginate());

        validateResponse(MemberAddress.MEMBER_ADDRESS_ID, MemberAddress.USER_ID, MemberAddress.MEMBER_ADDRESS_NAME, MemberAddress.MEMBER_ADDRESS_MOBILE, MemberAddress.MEMBER_ADDRESS_POSTCODE, MemberAddress.MEMBER_ADDRESS_PROVINCE, MemberAddress.MEMBER_ADDRESS_CITY, MemberAddress.MEMBER_ADDRESS_AREA, MemberAddress.MEMBER_ADDRESS_ADDRESS, MemberAddress.SYSTEM_VERSION);

        renderSuccessModeListlJson(resultCount, resultList);
    }

    @ActionKey("/admin/member/address/find")
    public void find() {
        validateRequest(MemberAddress.MEMBER_ADDRESS_ID);

        MemberAddress model = getModel(MemberAddress.class);
        model.where(MemberAddress.MEMBER_ADDRESS_ID);

        MemberAddress result = memberAddressService.find(model);

        validateResponse(MemberAddress.MEMBER_ID, MemberAddress.USER_ID, MemberAddress.MEMBER_ADDRESS_NAME, MemberAddress.MEMBER_ADDRESS_TEL, MemberAddress.MEMBER_ADDRESS_MOBILE, MemberAddress.MEMBER_ADDRESS_POSTCODE, MemberAddress.MEMBER_ADDRESS_PROVINCE, MemberAddress.MEMBER_ADDRESS_CITY, MemberAddress.MEMBER_ADDRESS_AREA, MemberAddress.MEMBER_ADDRESS_ADDRESS, MemberAddress.ADDRESS_IS_DEFAULT, MemberAddress.SYSTEM_VERSION);

        renderSuccessModelJson(result);
    }

    @ActionKey("/admin/member/address/save")
    public void save() {
        validateRequest(MemberAddress.MEMBER_ID, MemberAddress.USER_ID, MemberAddress.MEMBER_ADDRESS_NAME, MemberAddress.MEMBER_ADDRESS_TEL, MemberAddress.MEMBER_ADDRESS_MOBILE, MemberAddress.MEMBER_ADDRESS_POSTCODE, MemberAddress.MEMBER_ADDRESS_PROVINCE, MemberAddress.MEMBER_ADDRESS_CITY, MemberAddress.MEMBER_ADDRESS_AREA, MemberAddress.MEMBER_ADDRESS_ADDRESS, MemberAddress.ADDRESS_IS_DEFAULT);

        MemberAddress model = getModel(MemberAddress.class);
        model.setMember_address_id(Util.getRandomUUID());

        Boolean result = memberAddressService.save(model);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/member/address/update")
    public void update() {
        validateRequest(MemberAddress.MEMBER_ADDRESS_ID, MemberAddress.MEMBER_ID, MemberAddress.USER_ID, MemberAddress.MEMBER_ADDRESS_NAME, MemberAddress.MEMBER_ADDRESS_TEL, MemberAddress.MEMBER_ADDRESS_MOBILE, MemberAddress.MEMBER_ADDRESS_POSTCODE, MemberAddress.MEMBER_ADDRESS_PROVINCE, MemberAddress.MEMBER_ADDRESS_CITY, MemberAddress.MEMBER_ADDRESS_AREA, MemberAddress.MEMBER_ADDRESS_ADDRESS, MemberAddress.ADDRESS_IS_DEFAULT, MemberAddress.SYSTEM_VERSION);

        MemberAddress model = getModel(MemberAddress.class);
        model.where(model.MEMBER_ADDRESS_ID).and(MemberAddress.SYSTEM_VERSION);

        Boolean result = memberAddressService.update(model);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/member/address/delete")
    public void delete() {
        validateRequest(MemberAddress.MEMBER_ADDRESS_ID, MemberAddress.SYSTEM_VERSION);

        MemberAddress model = getModel(MemberAddress.class);
        model.where(model.MEMBER_ADDRESS_ID).and(MemberAddress.SYSTEM_VERSION);

        Boolean result = memberAddressService.delete(model);

        renderSuccessJson(result);
    }

}