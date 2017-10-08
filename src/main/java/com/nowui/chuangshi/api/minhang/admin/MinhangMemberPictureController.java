package com.nowui.chuangshi.api.minhang.admin;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.minhang.model.MinhangMemberPicture;
import com.nowui.chuangshi.api.minhang.service.MinhangMemberPictureService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.common.interceptor.AdminInterceptor;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.util.Util;

import java.util.List;

@Before(AdminInterceptor.class)
@ControllerKey("/admin/minhang/member/picture")
public class MinhangMemberPictureController extends Controller {

    @ActionKey("/admin/minhang/member/picture/list")
    public void list() {
        validateRequest(MinhangMemberPicture.MEMBER_ID, MinhangMemberPicture.TASK_ID, MinhangMemberPicture.PICTURE_FILE, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        MinhangMemberPicture model = getModel(MinhangMemberPicture.class);
        String request_app_id = getRequest_app_id();

        Integer resultCount = MinhangMemberPictureService.instance.adminCount(request_app_id, model.getMember_id(), model.getTask_id(), model.getPicture_file());
        List<MinhangMemberPicture> resultList = MinhangMemberPictureService.instance.adminList(request_app_id, model.getMember_id(), model.getTask_id(), model.getPicture_file(), getM(), getN());

        validateResponse(MinhangMemberPicture.MEMBER_PICTURE_ID, MinhangMemberPicture.MEMBER_ID, MinhangMemberPicture.PICTURE_FILE, MinhangMemberPicture.SYSTEM_VERSION);

        renderSuccessJson(resultCount, resultList);
    }

    @ActionKey("/admin/minhang/member/picture/find")
    public void find() {
        validateRequest(MinhangMemberPicture.MEMBER_PICTURE_ID);

        MinhangMemberPicture model = getModel(MinhangMemberPicture.class);

        MinhangMemberPicture result = MinhangMemberPictureService.instance.find(model.getMember_picture_id());

        validateResponse(MinhangMemberPicture.MEMBER_ID, MinhangMemberPicture.USER_ID, MinhangMemberPicture.TASK_ID, MinhangMemberPicture.PICTURE_FILE, MinhangMemberPicture.SYSTEM_VERSION);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/minhang/member/picture/save")
    public void save() {
        validateRequest(MinhangMemberPicture.MEMBER_ID, MinhangMemberPicture.USER_ID, MinhangMemberPicture.TASK_ID, MinhangMemberPicture.PICTURE_FILE);

        MinhangMemberPicture model = getModel(MinhangMemberPicture.class);
        model.setMember_picture_id(Util.getRandomUUID());
        String request_user_id = getRequest_user_id();

        Boolean result = MinhangMemberPictureService.instance.save(model, request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/minhang/member/picture/update")
    public void update() {
        validateRequest(MinhangMemberPicture.MEMBER_PICTURE_ID, MinhangMemberPicture.MEMBER_ID, MinhangMemberPicture.USER_ID, MinhangMemberPicture.TASK_ID, MinhangMemberPicture.PICTURE_FILE, MinhangMemberPicture.SYSTEM_VERSION);

        MinhangMemberPicture model = getModel(MinhangMemberPicture.class);
        String request_user_id = getRequest_user_id();

        Boolean result = MinhangMemberPictureService.instance.update(model, model.getMember_picture_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey("/admin/minhang/member/picture/delete")
    public void delete() {
        validateRequest(MinhangMemberPicture.MEMBER_PICTURE_ID, MinhangMemberPicture.SYSTEM_VERSION);

        MinhangMemberPicture model = getModel(MinhangMemberPicture.class);
        String request_user_id = getRequest_user_id();

        Boolean result = MinhangMemberPictureService.instance.delete(model.getMember_picture_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

}