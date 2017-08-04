package com.nowui.chuangshi.api.member.admin;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.member.model.MemberLevel;
import com.nowui.chuangshi.api.member.service.MemberLevelService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.common.interceptor.AdminInterceptor;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.util.Util;

import java.util.List;

@Before(AdminInterceptor.class)
@ControllerKey("/admin/member/level")
public class MemberLevelController extends Controller {

    private final MemberLevelService memberLevelService = MemberLevelService.me;

    @ActionKey("/admin/member/level/list")
    public void list() {
        validateRequest(MemberLevel.MEMBER_LEVEL_NAME, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        MemberLevel model = getModel(MemberLevel.class);
        model.where(MemberLevel.APP_ID).andEmpty(MemberLevel.MEMBER_LEVEL_NAME);

        Integer resultCount = memberLevelService.count(model);
        List<MemberLevel> resultList = memberLevelService.list(model.paginate());

        validateResponse(MemberLevel.MEMBER_LEVEL_ID, MemberLevel.MEMBER_LEVEL_NAME, MemberLevel.MEMBER_LEVEL_VALUE, MemberLevel.MEMBER_LEVEL_SORT, MemberLevel.SYSTEM_VERSION);

        renderSuccessModeListlJson(resultCount, resultList);
    }

    @ActionKey("/admin/member/level/find")
    public void find() {
        validateRequest(MemberLevel.MEMBER_LEVEL_ID);

        MemberLevel model = getModel(MemberLevel.class);
        model.where(MemberLevel.MEMBER_LEVEL_ID);

        MemberLevel result = memberLevelService.find(model);

        validateResponse(MemberLevel.MEMBER_LEVEL_NAME, MemberLevel.MEMBER_LEVEL_VALUE, MemberLevel.MEMBER_LEVEL_SORT, MemberLevel.SYSTEM_VERSION);

        renderSuccessModelJson(result);
    }

    @ActionKey("/admin/member/level/save")
    public void save() {
        validateRequest(MemberLevel.MEMBER_LEVEL_NAME, MemberLevel.MEMBER_LEVEL_VALUE, MemberLevel.MEMBER_LEVEL_SORT);

        MemberLevel model = getModel(MemberLevel.class);
        model.setMember_level_id(Util.getRandomUUID());

        Boolean result = memberLevelService.save(model);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/member/level/update")
    public void update() {
        validateRequest(MemberLevel.MEMBER_LEVEL_ID, MemberLevel.MEMBER_LEVEL_NAME, MemberLevel.MEMBER_LEVEL_VALUE, MemberLevel.MEMBER_LEVEL_SORT, MemberLevel.SYSTEM_VERSION);

        MemberLevel model = getModel(MemberLevel.class);
        model.where(model.MEMBER_LEVEL_ID).and(MemberLevel.SYSTEM_VERSION);

        Boolean result = memberLevelService.update(model);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/member/level/delete")
    public void delete() {
        validateRequest(MemberLevel.MEMBER_LEVEL_ID, MemberLevel.SYSTEM_VERSION);

        MemberLevel model = getModel(MemberLevel.class);
        model.where(model.MEMBER_LEVEL_ID).and(MemberLevel.SYSTEM_VERSION);

        Boolean result = memberLevelService.delete(model);

        renderSuccessJson(result);
    }

}