package com.nowui.chuangshi.api.minhang.admin;


import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.minhang.model.MinhangMemberQuestion;
import com.nowui.chuangshi.api.minhang.service.MinhangMemberQuestionService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;

import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.util.Util;

import java.util.List;


@ControllerKey("/admin/minhang/member/question")
public class MinhangMemberQuestionController extends Controller {

    @ActionKey("/admin/minhang/member/question/list")
    public void list() {
        validateRequest(MinhangMemberQuestion.MEMBER_ID, MinhangMemberQuestion.TASK_ID, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        MinhangMemberQuestion model = getModel(MinhangMemberQuestion.class);
        String request_app_id = getRequest_app_id();

        Integer resultCount = MinhangMemberQuestionService.instance.adminCount(request_app_id, model.getMember_id(), model.getTask_id());
        List<MinhangMemberQuestion> resultList = MinhangMemberQuestionService.instance.adminList(request_app_id, model.getMember_id(), model.getTask_id(), getM(), getN());

        validateResponse(MinhangMemberQuestion.MEMBER_QUESTION_ID, MinhangMemberQuestion.MEMBER_ID, MinhangMemberQuestion.TASK_ID, MinhangMemberQuestion.QUESTION_ID, MinhangMemberQuestion.SYSTEM_VERSION);

        renderSuccessJson(resultCount, resultList);
    }

    @ActionKey("/admin/minhang/member/question/find")
    public void find() {
        validateRequest(MinhangMemberQuestion.MEMBER_QUESTION_ID);

        MinhangMemberQuestion model = getModel(MinhangMemberQuestion.class);

        MinhangMemberQuestion result = MinhangMemberQuestionService.instance.find(model.getMember_question_id());

        validateResponse(MinhangMemberQuestion.MEMBER_ID, MinhangMemberQuestion.USER_ID, MinhangMemberQuestion.TASK_ID, MinhangMemberQuestion.QUESTION_ID, MinhangMemberQuestion.MEMBER_ANSWER, MinhangMemberQuestion.SYSTEM_VERSION);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/minhang/member/question/save")
    public void save() {
        validateRequest(MinhangMemberQuestion.MEMBER_ID, MinhangMemberQuestion.USER_ID, MinhangMemberQuestion.TASK_ID, MinhangMemberQuestion.QUESTION_ID, MinhangMemberQuestion.MEMBER_ANSWER);

        MinhangMemberQuestion model = getModel(MinhangMemberQuestion.class);
        model.setMember_question_id(Util.getRandomUUID());
        String request_user_id = getRequest_user_id();

        Boolean result = MinhangMemberQuestionService.instance.save(model, request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/minhang/member/question/update")
    public void update() {
        validateRequest(MinhangMemberQuestion.MEMBER_QUESTION_ID, MinhangMemberQuestion.MEMBER_ID, MinhangMemberQuestion.USER_ID, MinhangMemberQuestion.TASK_ID, MinhangMemberQuestion.QUESTION_ID, MinhangMemberQuestion.MEMBER_ANSWER, MinhangMemberQuestion.SYSTEM_VERSION);

        MinhangMemberQuestion model = getModel(MinhangMemberQuestion.class);
        String request_user_id = getRequest_user_id();

        Boolean result = MinhangMemberQuestionService.instance.update(model, model.getMember_question_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey("/admin/minhang/member/question/delete")
    public void delete() {
        validateRequest(MinhangMemberQuestion.MEMBER_QUESTION_ID, MinhangMemberQuestion.SYSTEM_VERSION);

        MinhangMemberQuestion model = getModel(MinhangMemberQuestion.class);
        String request_user_id = getRequest_user_id();

        Boolean result = MinhangMemberQuestionService.instance.delete(model.getMember_question_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

}