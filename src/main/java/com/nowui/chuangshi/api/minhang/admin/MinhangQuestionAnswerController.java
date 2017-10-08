package com.nowui.chuangshi.api.minhang.admin;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.minhang.model.MinhangQuestionAnswer;
import com.nowui.chuangshi.api.minhang.service.MinhangQuestionAnswerService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.common.interceptor.AdminInterceptor;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.util.Util;

import java.util.List;

@Before(AdminInterceptor.class)
@ControllerKey("/admin/minhang/question/answer")
public class MinhangQuestionAnswerController extends Controller {

    @ActionKey("/admin/minhang/question/answer/list")
    public void list() {
        validateRequest(MinhangQuestionAnswer.QUESTION_ID, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        MinhangQuestionAnswer model = getModel(MinhangQuestionAnswer.class);
        String request_app_id = getRequest_app_id();

        Integer resultCount = MinhangQuestionAnswerService.instance.adminCount(request_app_id, model.getQuestion_id());
        List<MinhangQuestionAnswer> resultList = MinhangQuestionAnswerService.instance.adminList(request_app_id, model.getQuestion_id(), getM(), getN());

        validateResponse(MinhangQuestionAnswer.QUESTION_ANSWER_ID, MinhangQuestionAnswer.QUESTION_ID, MinhangQuestionAnswer.SYSTEM_VERSION);

        renderSuccessJson(resultCount, resultList);
    }

    @ActionKey("/admin/minhang/question/answer/find")
    public void find() {
        validateRequest(MinhangQuestionAnswer.QUESTION_ANSWER_ID);

        MinhangQuestionAnswer model = getModel(MinhangQuestionAnswer.class);

        MinhangQuestionAnswer result = MinhangQuestionAnswerService.instance.find(model.getQuestion_answer_id());

        validateResponse(MinhangQuestionAnswer.QUESTION_ID, MinhangQuestionAnswer.QUESTION_ANSWER, MinhangQuestionAnswer.SYSTEM_VERSION);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/minhang/question/answer/save")
    public void save() {
        validateRequest(MinhangQuestionAnswer.QUESTION_ID, MinhangQuestionAnswer.QUESTION_ANSWER);

        MinhangQuestionAnswer model = getModel(MinhangQuestionAnswer.class);
        model.setQuestion_answer_id(Util.getRandomUUID());
        String request_user_id = getRequest_user_id();

        Boolean result = MinhangQuestionAnswerService.instance.save(model, request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/minhang/question/answer/update")
    public void update() {
        validateRequest(MinhangQuestionAnswer.QUESTION_ANSWER_ID, MinhangQuestionAnswer.QUESTION_ID, MinhangQuestionAnswer.QUESTION_ANSWER, MinhangQuestionAnswer.SYSTEM_VERSION);

        MinhangQuestionAnswer model = getModel(MinhangQuestionAnswer.class);
        String request_user_id = getRequest_user_id();

        Boolean result = MinhangQuestionAnswerService.instance.update(model, model.getQuestion_answer_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey("/admin/minhang/question/answer/delete")
    public void delete() {
        validateRequest(MinhangQuestionAnswer.QUESTION_ANSWER_ID, MinhangQuestionAnswer.SYSTEM_VERSION);

        MinhangQuestionAnswer model = getModel(MinhangQuestionAnswer.class);
        String request_user_id = getRequest_user_id();

        Boolean result = MinhangQuestionAnswerService.instance.delete(model.getQuestion_answer_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

}