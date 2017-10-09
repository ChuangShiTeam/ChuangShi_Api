package com.nowui.chuangshi.api.minhang.admin;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.minhang.model.MinhangQuestion;
import com.nowui.chuangshi.api.minhang.model.MinhangQuestionAnswer;
import com.nowui.chuangshi.api.minhang.model.MinhangQuestionOption;
import com.nowui.chuangshi.api.minhang.service.MinhangQuestionAnswerService;
import com.nowui.chuangshi.api.minhang.service.MinhangQuestionOptionService;
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
        validateRequest(MinhangQuestion.QUESTION_TITLE, MinhangQuestion.QUESTION_TYPE, MinhangQuestion.TASK_ID, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        MinhangQuestion model = getModel(MinhangQuestion.class);
        String request_app_id = getRequest_app_id();

        Integer resultCount = MinhangQuestionService.instance.adminCount(request_app_id, model.getTask_id(), model.getQuestion_title(), model.getQuestion_type());
        List<MinhangQuestion> resultList = MinhangQuestionService.instance.adminList(request_app_id, model.getTask_id(), model.getQuestion_title(), model.getQuestion_type(), getM(), getN());

        validateResponse(MinhangQuestion.QUESTION_ID, MinhangQuestion.QUESTION_TITLE, MinhangQuestion.QUESTION_TYPE, MinhangQuestion.SYSTEM_VERSION);

        renderSuccessJson(resultCount, resultList);
    }

    @ActionKey("/admin/minhang/question/find")
    public void find() {
        validateRequest(MinhangQuestion.QUESTION_ID);

        MinhangQuestion model = getModel(MinhangQuestion.class);

        MinhangQuestion result = MinhangQuestionService.instance.find(model.getQuestion_id());

        validateResponse(MinhangQuestion.TASK_ID, MinhangQuestion.QUESTION_TITLE, MinhangQuestion.QUESTION_OPTION_LIST, MinhangQuestion.QUESTION_ANSWER_LIST, MinhangQuestion.QUESTION_TYPE, MinhangQuestion.SYSTEM_VERSION);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/minhang/question/save")
    public void save() {
        validateRequest(MinhangQuestion.TASK_ID, MinhangQuestion.QUESTION_TITLE, MinhangQuestion.QUESTION_OPTION_LIST, MinhangQuestion.QUESTION_ANSWER_LIST, MinhangQuestion.QUESTION_TYPE);

        MinhangQuestion model = getModel(MinhangQuestion.class);
        model.setQuestion_id(Util.getRandomUUID());
        String request_user_id = getRequest_user_id();

        Boolean result = MinhangQuestionService.instance.save(model, request_user_id);
        
        if (result) {
           JSONObject jsonObject = getParameterJSONObject();
           //保存题目选项信息
           JSONArray question_option_list = jsonObject.getJSONArray("question_option_list");
           for (int i = 0; i < question_option_list.size(); i++) {
               JSONObject bean = question_option_list.getJSONObject(i);
               MinhangQuestionOption minhangQuestionOption = bean.toJavaObject(MinhangQuestionOption.class);
               minhangQuestionOption.setApp_id(model.getApp_id());
               minhangQuestionOption.setQuestion_option_id(Util.getRandomUUID());
               minhangQuestionOption.setQuestion_id(model.getQuestion_id());
               MinhangQuestionOptionService.instance.save(minhangQuestionOption, request_user_id);
           }
           //保存题目答案信息
           JSONArray question_answer_list = jsonObject.getJSONArray("question_answer_list");
           for (int i = 0; i < question_answer_list.size(); i++) {
               JSONObject bean = question_answer_list.getJSONObject(i);
               MinhangQuestionAnswer minhangQuestionAnswer = bean.toJavaObject(MinhangQuestionAnswer.class);
               minhangQuestionAnswer.setApp_id(model.getApp_id());
               minhangQuestionAnswer.setQuestion_answer_id(Util.getRandomUUID());
               minhangQuestionAnswer.setQuestion_id(model.getQuestion_id());
               MinhangQuestionAnswerService.instance.save(minhangQuestionAnswer, request_user_id);
           }
        }
        renderSuccessJson(result);
    }

    @ActionKey("/admin/minhang/question/update")
    public void update() {
        validateRequest(MinhangQuestion.QUESTION_ID, MinhangQuestion.TASK_ID, MinhangQuestion.QUESTION_OPTION_LIST, MinhangQuestion.QUESTION_ANSWER_LIST, MinhangQuestion.QUESTION_TITLE, MinhangQuestion.QUESTION_TYPE, MinhangQuestion.SYSTEM_VERSION);

        MinhangQuestion model = getModel(MinhangQuestion.class);
        String request_user_id = getRequest_user_id();

        Boolean result = MinhangQuestionService.instance.update(model, model.getQuestion_id(), request_user_id, model.getSystem_version());
        
        if (result) {
            //删除题目选项信息
            MinhangQuestionOptionService.instance.questionDelete(model.getQuestion_id(), request_user_id, 1);
            //删除题目答案信息
            MinhangQuestionAnswerService.instance.questionDelete(model.getQuestion_id(), request_user_id, 1);
            JSONObject jsonObject = getParameterJSONObject();
            //保存题目选项信息
            JSONArray question_option_list = jsonObject.getJSONArray("question_option_list");
            for (int i = 0; i < question_option_list.size(); i++) {
                JSONObject bean = question_option_list.getJSONObject(i);
                MinhangQuestionOption minhangQuestionOption = bean.toJavaObject(MinhangQuestionOption.class);
                minhangQuestionOption.setApp_id(model.getApp_id());
                minhangQuestionOption.setQuestion_option_id(Util.getRandomUUID());
                minhangQuestionOption.setQuestion_id(model.getQuestion_id());
                MinhangQuestionOptionService.instance.save(minhangQuestionOption, request_user_id);
            }
            //保存题目答案信息
            JSONArray question_answer_list = jsonObject.getJSONArray("question_answer_list");
            for (int i = 0; i < question_answer_list.size(); i++) {
                JSONObject bean = question_answer_list.getJSONObject(i);
                MinhangQuestionAnswer minhangQuestionAnswer = bean.toJavaObject(MinhangQuestionAnswer.class);
                minhangQuestionAnswer.setApp_id(model.getApp_id());
                minhangQuestionAnswer.setQuestion_answer_id(Util.getRandomUUID());
                minhangQuestionAnswer.setQuestion_id(model.getQuestion_id());
                MinhangQuestionAnswerService.instance.save(minhangQuestionAnswer, request_user_id);
            }
         }
        renderSuccessJson(result);
    }

    @ActionKey("/admin/minhang/question/delete")
    public void delete() {
        validateRequest(MinhangQuestion.QUESTION_ID, MinhangQuestion.SYSTEM_VERSION);

        MinhangQuestion model = getModel(MinhangQuestion.class);
        String request_user_id = getRequest_user_id();

        Boolean result = MinhangQuestionService.instance.delete(model.getQuestion_id(), request_user_id, model.getSystem_version());

        if (result) {
            //删除题目选项信息
            MinhangQuestionOptionService.instance.questionDelete(model.getQuestion_id(), request_user_id, 1);
            //删除题目答案信息
            MinhangQuestionAnswerService.instance.questionDelete(model.getQuestion_id(), request_user_id, 1);

        }
        renderSuccessJson(result);
    }

}