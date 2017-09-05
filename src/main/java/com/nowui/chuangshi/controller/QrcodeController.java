package com.nowui.chuangshi.controller;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.core.ActionKey;
import com.jfinal.weixin.sdk.api.AccessTokenApi;
import com.jfinal.weixin.sdk.api.ApiConfigKit;
import com.jfinal.weixin.sdk.api.ApiResult;
import com.jfinal.weixin.sdk.api.QrcodeApi;
import com.nowui.chuangshi.api.app.model.App;
import com.nowui.chuangshi.api.app.service.AppService;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.constant.Url;
import com.nowui.chuangshi.model.Member;
import com.nowui.chuangshi.model.Qrcode;
import com.nowui.chuangshi.model.User;
import com.nowui.chuangshi.service.MemberService;
import com.nowui.chuangshi.service.QrcodeService;
import com.nowui.chuangshi.service.UserService;
import com.nowui.chuangshi.type.SceneType;
import com.nowui.chuangshi.util.Util;

public class QrcodeController extends Controller {

    private final QrcodeService qrcodeService = new QrcodeService();
    private final UserService userService = new UserService();
    private final MemberService memberService = new MemberService();

    @ActionKey(Url.QRCODE_LIST)
    public void list() {
        validateRequest_app_id();
        validate(Constant.PAGE_SIZE, Constant.FIRST_CREATE_TIME, Constant.LAST_CREATE_TIME);

        String request_app_id = getRequest_app_id();
        JSONObject jsonObject = getParameterJSONObject();

        authenticateRequest_app_idAndRequest_user_id();

        List<Qrcode> resultList = qrcodeService.listByApp_idAndSystem_create_timeAndLimit(request_app_id,
                jsonObject.getDate(Constant.LAST_CREATE_TIME), 0, getN());

        for (Qrcode result : resultList) {
            result.keep(Qrcode.QRCODE_ID, Qrcode.OBJECT_ID, Qrcode.QRCODE_TYPE, Qrcode.QRCODE_URL, Qrcode.QRCODE_ADD,
                    Qrcode.QRCODE_CANCEL, Qrcode.QRCODE_STATUS, Qrcode.SYSTEM_VERSION);
        }

        renderSuccessJson(resultList);
    }

    @ActionKey(Url.QRCODE_FIND)
    public void find() {
        validateRequest_app_id();
        validate(Qrcode.QRCODE_ID);

        Qrcode model = getModel(Qrcode.class);

        authenticateRequest_app_idAndRequest_user_id();

        Qrcode qrcode = qrcodeService.findByQrcode_id(model.getQrcode_id());

        authenticateApp_id(qrcode.getApp_id());
        authenticateSystem_create_user_id(qrcode.getSystem_create_user_id());

        qrcode.keep(Qrcode.QRCODE_ID, Qrcode.SYSTEM_VERSION);

        renderSuccessJson(qrcode);
    }

    @ActionKey(Url.QRCODE_SAVE)
    public void save() {
        validateRequest_app_id();
        validate(Qrcode.OBJECT_ID, Qrcode.QRCODE_TYPE, Qrcode.QRCODE_URL, Qrcode.QRCODE_ADD, Qrcode.QRCODE_CANCEL,
                Qrcode.QRCODE_STATUS);

        Qrcode model = getModel(Qrcode.class);
        String qrcode_id = Util.getRandomUUID();
        String request_app_id = getRequest_app_id();
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        Boolean result = qrcodeService.save(qrcode_id, request_app_id, model.getObject_id(), model.getQrcode_type(),
                model.getQrcode_url(), model.getQrcode_add(), model.getQrcode_cancel(), model.getQrcode_status(),
                request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey(Url.QRCODE_UPDATE)
    public void update() {
        validateRequest_app_id();
        validate(Qrcode.QRCODE_ID, Qrcode.OBJECT_ID, Qrcode.QRCODE_TYPE, Qrcode.QRCODE_URL, Qrcode.QRCODE_ADD,
                Qrcode.QRCODE_CANCEL, Qrcode.QRCODE_STATUS, Qrcode.SYSTEM_VERSION);

        Qrcode model = getModel(Qrcode.class);
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        Qrcode qrcode = qrcodeService.findByQrcode_id(model.getQrcode_id());

        authenticateApp_id(qrcode.getApp_id());
        authenticateSystem_create_user_id(qrcode.getSystem_create_user_id());

        Boolean result = qrcodeService.updateValidateSystem_version(model.getQrcode_id(), model.getObject_id(),
                model.getQrcode_type(), model.getQrcode_url(), model.getQrcode_add(), model.getQrcode_cancel(),
                model.getQrcode_status(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey(Url.QRCODE_DELETE)
    public void delete() {
        validateRequest_app_id();
        validate(Qrcode.QRCODE_ID, Qrcode.SYSTEM_VERSION);

        Qrcode model = getModel(Qrcode.class);
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        Qrcode qrcode = qrcodeService.findByQrcode_id(model.getQrcode_id());

        authenticateApp_id(qrcode.getApp_id());
        authenticateSystem_create_user_id(qrcode.getSystem_create_user_id());

        Boolean result = qrcodeService.deleteByQrcode_idAndSystem_update_user_idValidateSystem_version(
                model.getQrcode_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey(Url.QRCODE_ADMIN_LIST)
    public void adminList() {
        validateRequest_app_id();
        validate(Constant.PAGE_INDEX, Qrcode.QRCODE_TYPE, Constant.PAGE_SIZE);

        Qrcode model = getModel(Qrcode.class);
        String request_app_id = getRequest_app_id();

        authenticateRequest_app_idAndRequest_user_id();

        Integer total = qrcodeService.countByApp_idOrQrcode_type(request_app_id, model.getQrcode_type());
        List<Qrcode> resultList = qrcodeService.listByApp_idOrQrcode_typeAndLimit(request_app_id,
                model.getQrcode_type(), getM(), getN());

        for (Qrcode result : resultList) {
            String member_id = result.getObject_id();
            Member member = memberService.findByMember_id(member_id);
            if (member != null) {
                User user = userService.findByUser_id(member.getUser_id());
                result.setObject_id(user.getUser_name());
            }
            result.keep(Qrcode.QRCODE_ID, Qrcode.OBJECT_ID, Qrcode.QRCODE_TYPE, Qrcode.QRCODE_URL, Qrcode.QRCODE_ADD,
                    Qrcode.QRCODE_CANCEL, Qrcode.QRCODE_STATUS, Qrcode.SYSTEM_VERSION);
        }

        renderSuccessJson(total, resultList);
    }

    @ActionKey(Url.QRCODE_ADMIN_FIND)
    public void adminFind() {
        validateRequest_app_id();
        validate(Qrcode.QRCODE_ID);

        Qrcode model = getModel(Qrcode.class);

        authenticateRequest_app_idAndRequest_user_id();

        Qrcode qrcode = qrcodeService.findByQrcode_id(model.getQrcode_id());

        authenticateApp_id(qrcode.getApp_id());

        String member_id = qrcode.getObject_id();
        Member member = memberService.findByMember_id(member_id);
        if (member != null) {
            User user = userService.findByUser_id(member.getUser_id());
            qrcode.setObject_id(user.getUser_name());
        }

        qrcode.keep(Qrcode.QRCODE_ID, Qrcode.OBJECT_ID, Qrcode.QRCODE_TYPE, Qrcode.QRCODE_URL, Qrcode.QRCODE_ADD,
                Qrcode.QRCODE_CANCEL, Qrcode.QRCODE_STATUS, Qrcode.SYSTEM_VERSION);

        renderSuccessJson(qrcode);
    }

    // 新增平台二维码
    @ActionKey(Url.QRCODE_ADMIN_SAVE)
    public void adminSave() {
        String app_id = getRequest_app_id();
        String request_user_id = getRequest_user_id();

        App app = AppService.instance.find(app_id);

        String wechat_app_id = ApiConfigKit.getAppId();
        if (!wechat_app_id.equals(app.getWechat_app_id())) {
            ApiConfigKit.setThreadLocalAppId(app.getWechat_app_id());
            AccessTokenApi.refreshAccessToken();
        }

        String qrcode_id = Util.getRandomUUID();
        String object_id = "";
        String qrcode_type = SceneType.PLATFORM.getKey();
        Boolean qrcode_status = true;

        ApiResult apiResult = QrcodeApi.createPermanent(qrcode_id);
        String qrcode_url = QrcodeApi.getShowQrcodeUrl(apiResult.getStr("ticket"));

        Boolean result = qrcodeService.save(qrcode_id, app_id, object_id, qrcode_type, qrcode_url, 0, 0, qrcode_status,
                request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey(Url.QRCODE_ADMIN_UPDATE)
    public void adminUpdate() {
        validateRequest_app_id();
        validate(Qrcode.QRCODE_ID, Qrcode.OBJECT_ID, Qrcode.QRCODE_TYPE, Qrcode.QRCODE_URL, Qrcode.QRCODE_ADD,
                Qrcode.QRCODE_CANCEL, Qrcode.QRCODE_STATUS, Qrcode.SYSTEM_VERSION);

        Qrcode model = getModel(Qrcode.class);
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        Qrcode qrcode = qrcodeService.findByQrcode_id(model.getQrcode_id());

        authenticateApp_id(qrcode.getApp_id());

        Boolean result = qrcodeService.updateValidateSystem_version(model.getQrcode_id(), model.getObject_id(),
                model.getQrcode_type(), model.getQrcode_url(), model.getQrcode_add(), model.getQrcode_cancel(),
                model.getQrcode_status(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey(Url.QRCODE_ADMIN_DELETE)
    public void adminDelete() {
        validateRequest_app_id();
        validate(Qrcode.QRCODE_ID, Qrcode.SYSTEM_VERSION);

        Qrcode model = getModel(Qrcode.class);
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        Qrcode qrcode = qrcodeService.findByQrcode_id(model.getQrcode_id());

        authenticateApp_id(qrcode.getApp_id());

        Boolean result = qrcodeService.deleteByQrcode_idAndSystem_update_user_idValidateSystem_version(
                model.getQrcode_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey(Url.QRCODE_SYSTEM_LIST)
    public void systemList() {
        validateRequest_app_id();
        validate(Qrcode.APP_ID, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        Qrcode model = getModel(Qrcode.class);

        Integer total = qrcodeService.countByOrApp_idOrQrcode_type(model.getApp_id(), model.getQrcode_type());
        List<Qrcode> resultList = qrcodeService.listByOrApp_idOrQrcode_typeAndLimit(model.getApp_id(),
                model.getQrcode_type(), getM(), getN());

        for (Qrcode result : resultList) {
            result.keep(Qrcode.QRCODE_ID, Qrcode.SYSTEM_VERSION);
        }

        renderSuccessJson(total, resultList);
    }

    @ActionKey(Url.QRCODE_SYSTEM_FIND)
    public void systemFind() {
        validateRequest_app_id();
        validate(Qrcode.QRCODE_ID);

        Qrcode model = getModel(Qrcode.class);

        Qrcode qrcode = qrcodeService.findByQrcode_id(model.getQrcode_id());

        qrcode.keep(Qrcode.QRCODE_ID, Qrcode.SYSTEM_VERSION);

        renderSuccessJson(qrcode);
    }

    @ActionKey(Url.QRCODE_SYSTEM_SAVE)
    public void systemSave() {
        validateRequest_app_id();
        validate(Qrcode.APP_ID, Qrcode.OBJECT_ID, Qrcode.QRCODE_TYPE, Qrcode.QRCODE_URL, Qrcode.QRCODE_ADD,
                Qrcode.QRCODE_CANCEL, Qrcode.QRCODE_STATUS);

        Qrcode model = getModel(Qrcode.class);
        String qrcode_id = Util.getRandomUUID();
        String request_user_id = getRequest_user_id();

        Boolean result = qrcodeService.save(qrcode_id, model.getApp_id(), model.getObject_id(), model.getQrcode_type(),
                model.getQrcode_url(), model.getQrcode_add(), model.getQrcode_cancel(), model.getQrcode_status(),
                request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey(Url.QRCODE_SYSTEM_UPDATE)
    public void systemUpdate() {
        validateRequest_app_id();
        validate(Qrcode.QRCODE_ID, Qrcode.OBJECT_ID, Qrcode.QRCODE_TYPE, Qrcode.QRCODE_URL, Qrcode.QRCODE_ADD,
                Qrcode.QRCODE_CANCEL, Qrcode.QRCODE_STATUS, Qrcode.SYSTEM_VERSION);

        Qrcode model = getModel(Qrcode.class);
        String request_user_id = getRequest_user_id();

        Boolean result = qrcodeService.updateValidateSystem_version(model.getQrcode_id(), model.getObject_id(),
                model.getQrcode_type(), model.getQrcode_url(), model.getQrcode_add(), model.getQrcode_cancel(),
                model.getQrcode_status(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey(Url.QRCODE_SYSTEM_DELETE)
    public void systemDelete() {
        validateRequest_app_id();
        validate(Qrcode.QRCODE_ID, Qrcode.SYSTEM_VERSION);

        Qrcode model = getModel(Qrcode.class);
        String request_user_id = getRequest_user_id();

        Boolean result = qrcodeService.deleteByQrcode_idAndSystem_update_user_idValidateSystem_version(
                model.getQrcode_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

}