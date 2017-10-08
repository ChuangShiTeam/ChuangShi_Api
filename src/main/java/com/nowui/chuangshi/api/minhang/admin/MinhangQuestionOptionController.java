package com.nowui.chuangshi.api.minhang.admin;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.minhang.model.MinhangQuestionOption;
import com.nowui.chuangshi.api.minhang.service.MinhangQuestionOptionService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.common.interceptor.AdminInterceptor;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.util.Util;

import java.util.List;

@Before(AdminInterceptor.class)
@ControllerKey("/admin/minhang/question/option")
public class MinhangQuestionOptionController extends Controller {

    @ActionKey("/admin/minhang/question/option/list")
    public void list() {
        validateRequest(MinhangQuestionOption.QUESTION_ID, MinhangQuestionOption.QUESTION_OPTION_KEY, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        MinhangQuestionOption model = getModel(MinhangQuestionOption.class);
        String request_app_id = getRequest_app_id();

        Integer resultCount = MinhangQuestionOptionService.instance.adminCount(request_app_id, model.getQuestion_id(), model.getQuestion_option_key());
        List<MinhangQuestionOption> resultList = MinhangQuestionOptionService.instance.adminList(request_app_id, model.getQuestion_id(), model.getQuestion_option_key(), getM(), getN());

        validateResponse(MinhangQuestionOption.QUESTION_OPTION_ID, MinhangQuestionOption.QUESTION_ID, MinhangQuestionOption.QUESTION_OPTION_KEY, MinhangQuestionOption.QUESTION_OPTION_VALUE, MinhangQuestionOption.QUESTION_OPTION_SORT, MinhangQuestionOption.SYSTEM_VERSION);

        renderSuccessJson(resultCount, resultList);
    }

    @ActionKey("/admin/minhang/question/option/find")
    public void find() {
        validateRequest(MinhangQuestionOption.QUESTION_OPTION_ID);

        MinhangQuestionOption model = getModel(MinhangQuestionOption.class);

        MinhangQuestionOption result = MinhangQuestionOptionService.instance.find(model.getQuestion_option_id());

        validateResponse(MinhangQuestionOption.QUESTION_ID, MinhangQuestionOption.QUESTION_OPTION_KEY, MinhangQuestionOption.QUESTION_OPTION_VALUE, MinhangQuestionOption.QUESTION_OPTION_SORT, MinhangQuestionOption.SYSTEM_VERSION);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/minhang/question/option/save")
    public void save() {
        validateRequest(MinhangQuestionOption.QUESTION_ID, MinhangQuestionOption.QUESTION_OPTION_KEY, MinhangQuestionOption.QUESTION_OPTION_VALUE, MinhangQuestionOption.QUESTION_OPTION_SORT);

        MinhangQuestionOption model = getModel(MinhangQuestionOption.class);
        model.setQuestion_option_id(Util.getRandomUUID());
        String request_user_id = getRequest_user_id();

        Boolean result = MinhangQuestionOptionService.instance.save(model, request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/minhang/question/option/update")
    public void update() {
        validateRequest(MinhangQuestionOption.QUESTION_OPTION_ID, MinhangQuestionOption.QUESTION_ID, MinhangQuestionOption.QUESTION_OPTION_KEY, MinhangQuestionOption.QUESTION_OPTION_VALUE, MinhangQuestionOption.QUESTION_OPTION_SORT, MinhangQuestionOption.SYSTEM_VERSION);

        MinhangQuestionOption model = getModel(MinhangQuestionOption.class);
        String request_user_id = getRequest_user_id();

        Boolean result = MinhangQuestionOptionService.instance.update(model, model.getQuestion_option_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey("/admin/minhang/question/option/delete")
    public void delete() {
        validateRequest(MinhangQuestionOption.QUESTION_OPTION_ID, MinhangQuestionOption.SYSTEM_VERSION);

        MinhangQuestionOption model = getModel(MinhangQuestionOption.class);
        String request_user_id = getRequest_user_id();

        Boolean result = MinhangQuestionOptionService.instance.delete(model.getQuestion_option_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

}