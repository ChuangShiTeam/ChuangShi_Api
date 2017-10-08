package com.nowui.chuangshi.api.minhang.admin;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.minhang.model.MinhangQuestion;
import com.nowui.chuangshi.api.minhang.service.MinhangQuestionService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.common.interceptor.AdminInterceptor;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.util.Util;

import java.util.List;

@Before(AdminInterceptor.class)
@ControllerKey("/admin/minhang/question")
public class MinhangQuestionController extends Controller {

    @ActionKey("/admin/minhang/question/list")
    public void list() {
        validateRequest(MinhangQuestion.QUESTION_TITLE, MinhangQuestion.QUESTION_TYPE, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        MinhangQuestion model = getModel(MinhangQuestion.class);
        String request_app_id = getRequest_app_id();

        Integer resultCount = MinhangQuestionService.instance.adminCount(request_app_id, model.getQuestion_title(), model.getQuestion_type());
        List<MinhangQuestion> resultList = MinhangQuestionService.instance.adminList(request_app_id, model.getQuestion_title(), model.getQuestion_type(), getM(), getN());

        validateResponse(MinhangQuestion.QUESTION_ID, MinhangQuestion.QUESTION_TITLE, MinhangQuestion.QUESTION_TYPE, MinhangQuestion.QUESTION_QRCODE_URL, MinhangQuestion.SYSTEM_VERSION);

        renderSuccessJson(resultCount, resultList);
    }

    @ActionKey("/admin/minhang/question/find")
    public void find() {
        validateRequest(MinhangQuestion.QUESTION_ID);

        MinhangQuestion model = getModel(MinhangQuestion.class);

        MinhangQuestion result = MinhangQuestionService.instance.find(model.getQuestion_id());

        validateResponse(MinhangQuestion.TASK_ID, MinhangQuestion.QUESTION_TITLE, MinhangQuestion.QUESTION_TYPE, MinhangQuestion.QUESTION_QRCODE_URL, MinhangQuestion.SYSTEM_VERSION);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/minhang/question/save")
    public void save() {
        validateRequest(MinhangQuestion.TASK_ID, MinhangQuestion.QUESTION_TITLE, MinhangQuestion.QUESTION_TYPE, MinhangQuestion.QUESTION_QRCODE_URL);

        MinhangQuestion model = getModel(MinhangQuestion.class);
        model.setQuestion_id(Util.getRandomUUID());
        String request_user_id = getRequest_user_id();

        Boolean result = MinhangQuestionService.instance.save(model, request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/minhang/question/update")
    public void update() {
        validateRequest(MinhangQuestion.QUESTION_ID, MinhangQuestion.TASK_ID, MinhangQuestion.QUESTION_TITLE, MinhangQuestion.QUESTION_TYPE, MinhangQuestion.QUESTION_QRCODE_URL, MinhangQuestion.SYSTEM_VERSION);

        MinhangQuestion model = getModel(MinhangQuestion.class);
        String request_user_id = getRequest_user_id();

        Boolean result = MinhangQuestionService.instance.update(model, model.getQuestion_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey("/admin/minhang/question/delete")
    public void delete() {
        validateRequest(MinhangQuestion.QUESTION_ID, MinhangQuestion.SYSTEM_VERSION);

        MinhangQuestion model = getModel(MinhangQuestion.class);
        String request_user_id = getRequest_user_id();

        Boolean result = MinhangQuestionService.instance.delete(model.getQuestion_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

}