package com.nowui.chuangshi.api.xietong.mobile;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.xietong.model.XietongAdmissions;
import com.nowui.chuangshi.api.xietong.service.XietongAdmissionsService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;

@ControllerKey("/mobile/xietong/admissions")
public class XietongAdmissionsController extends Controller {

    @ActionKey("/mobile/xietong/admissions/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/xietong/admissions/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/xietong/admissions/save")
    public void save() {
    	validateRequest(XietongAdmissions.ADMISSIONS_NAME, XietongAdmissions.ADMISSIONS_CERTIFICATE_TYPE, XietongAdmissions.ADMISSIONS_CERTIFICATE_NUMBER, XietongAdmissions.ADMISSIONS_SEX, XietongAdmissions.ADMISSIONS_BIRTHDAY, XietongAdmissions.ADMISSIONS_IS_APPLY_LIVE_SCHOOL, XietongAdmissions.ADMISSIONS_OLD_SCHOOL, XietongAdmissions.ADMISSIONS_REGISTRATION_ADDRESS, XietongAdmissions.ADMISSIONS_HOME_ADDRESS, XietongAdmissions.ADMISSIONS_HOME_FIRST_NAME, XietongAdmissions.ADMISSIONS_HOME_FIRST_UNIT, XietongAdmissions.ADMISSIONS_HOME_FIRST_TEL, XietongAdmissions.ADMISSIONS_HOME_SECOND_NAME, XietongAdmissions.ADMISSIONS_HOME_SECOND_UNIT, XietongAdmissions.ADMISSIONS_HOME_SECOND_TEL, XietongAdmissions.ADMISSIONS_NOTES);

        XietongAdmissions model = getModel(XietongAdmissions.class);
        String request_user_id = getRequest_user_id();

        Boolean result = XietongAdmissionsService.instance.save(model, request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey("/mobile/xietong/admissions/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/xietong/admissions/delete")
    public void delete() {

        renderSuccessJson();
    }

}