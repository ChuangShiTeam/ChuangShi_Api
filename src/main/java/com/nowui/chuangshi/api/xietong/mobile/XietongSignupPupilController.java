package com.nowui.chuangshi.api.xietong.mobile;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.xietong.model.XietongSignupPupil;
import com.nowui.chuangshi.api.xietong.service.XietongSignupPupilService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.util.Util;
import com.nowui.chuangshi.util.ValidateUtil;

@ControllerKey("/mobile/xietong/signup/pupil")
public class XietongSignupPupilController extends Controller {

    @ActionKey("/mobile/xietong/signup/pupil/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/xietong/signup/pupil/find")
    public void find() {

        XietongSignupPupil xietong_signup_pupil = getModel(XietongSignupPupil.class);

        XietongSignupPupil bean = XietongSignupPupilService.instance.idNoFind(xietong_signup_pupil.getId_no());

        if (bean != null) {
            //已报名，已确定，已签到，已评分，已录取，已阅读

            if (bean.getSignup_status() == "已确定") {

                throw new RuntimeException("面谈时间：" + bean.getInterview_time().toString());
            } else if (bean.getSignup_status() == "已评分") {

                throw new RuntimeException("最终评分：" + bean.getMark());

            } else {
                throw new RuntimeException(bean.getSignup_status());
            }
        } else {

            throw new RuntimeException("该学生未报名");
        }
    }

    //add by lyn
    //小学生报名接口
    //2017.11.6
    @ActionKey("/mobile/xietong/signup/pupil/save")
    public void save() {

        String request_app_id = getRequest_app_id();
        String request_user_id = getRequest_user_id();
        XietongSignupPupil xietong_signup_pupil = getModel(XietongSignupPupil.class);

        XietongSignupPupil bean = XietongSignupPupilService.instance.idNoFind(xietong_signup_pupil.getId_no());
        if (bean != null) {
            throw new RuntimeException("该学生已经报名");
        }
        String signup_number = XietongSignupPupilService.instance.findLatestSignupNumber(request_app_id);
        if (ValidateUtil.isNullOrEmpty(signup_number)) {
            xietong_signup_pupil.setSignupNumber("01000");
        } else {
            Integer latestSignupNumber = Integer.valueOf(signup_number);
            latestSignupNumber ++;
            if (latestSignupNumber >= 10000) {
                xietong_signup_pupil.setSignupNumber("" + latestSignupNumber);
            } else {
                xietong_signup_pupil.setSignupNumber("0" + latestSignupNumber);
            }
        }
        xietong_signup_pupil.setSignup_status("已报名");
        xietong_signup_pupil.setSignup_id(Util.getRandomUUID());
        xietong_signup_pupil.setApp_id(request_app_id);
        Boolean result = XietongSignupPupilService.instance.save(xietong_signup_pupil, request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey("/mobile/xietong/signup/pupil/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/xietong/signup/pupil/delete")
    public void delete() {

        renderSuccessJson();
    }

}