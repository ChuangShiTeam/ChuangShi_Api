package com.nowui.chuangshi.api.minhang.mobile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.file.service.FileService;
import com.nowui.chuangshi.api.minhang.model.MinhangMemberKey;
import com.nowui.chuangshi.api.minhang.model.MinhangMemberPicture;
import com.nowui.chuangshi.api.minhang.model.MinhangMemberQuestion;
import com.nowui.chuangshi.api.minhang.model.MinhangMemberRecord;
import com.nowui.chuangshi.api.minhang.model.MinhangMemberTask;
import com.nowui.chuangshi.api.minhang.model.MinhangPartyHistory;
import com.nowui.chuangshi.api.minhang.model.MinhangPartySong;
import com.nowui.chuangshi.api.minhang.model.MinhangPoster;
import com.nowui.chuangshi.api.minhang.model.MinhangQuestion;
import com.nowui.chuangshi.api.minhang.model.MinhangTask;
import com.nowui.chuangshi.api.minhang.model.MinhangTimelineEvent;
import com.nowui.chuangshi.api.minhang.model.MinhangVideoTask;
import com.nowui.chuangshi.api.minhang.service.MinhangMemberKeyService;
import com.nowui.chuangshi.api.minhang.service.MinhangMemberPictureService;
import com.nowui.chuangshi.api.minhang.service.MinhangMemberQuestionService;
import com.nowui.chuangshi.api.minhang.service.MinhangMemberRecordService;
import com.nowui.chuangshi.api.minhang.service.MinhangMemberTaskService;
import com.nowui.chuangshi.api.minhang.service.MinhangPartyHistoryService;
import com.nowui.chuangshi.api.minhang.service.MinhangPartySongService;
import com.nowui.chuangshi.api.minhang.service.MinhangPosterService;
import com.nowui.chuangshi.api.minhang.service.MinhangQuestionService;
import com.nowui.chuangshi.api.minhang.service.MinhangTaskService;
import com.nowui.chuangshi.api.minhang.service.MinhangTimelineEventService;
import com.nowui.chuangshi.api.minhang.service.MinhangVideoTaskService;
import com.nowui.chuangshi.api.user.model.User;
import com.nowui.chuangshi.api.user.service.UserService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.type.MinhangTaskType;
import com.nowui.chuangshi.util.Util;
import com.nowui.chuangshi.util.ValidateUtil;

@ControllerKey("/mobile/minhang/task")
public class MinhangTaskController extends Controller {

    @ActionKey("/mobile/minhang/task/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/minhang/task/find")
    public void find() {
        validateRequest(MinhangTask.TASK_ID);

        MinhangTask model = getModel(MinhangTask.class);
        
        String request_user_id = getRequest_user_id();

        MinhangTask minhangTask = MinhangTaskService.instance.find(model.getTask_id());
        
        validateResponse(MinhangTask.KEY_ID, MinhangTask.TASK_NAME, MinhangTask.SCREEN_ID, MinhangTask.TASK_TYPE, MinhangTask.TASK_QRCODE_URL, MinhangTask.TASK_SORT, MinhangTask.TASK_DESCRIPTION, MinhangTask.SYSTEM_VERSION);

        MinhangMemberTask minhangMemberTask = MinhangMemberTaskService.instance.userAndTaskFind(request_user_id, model.getTask_id());
        
        Map<String, Object> result = new HashMap<String, Object>(); 
        
        if (minhangMemberTask == null) { 
            //如果为答题任务查询题目列表
            if (MinhangTaskType.QUESTION.getKey().equals(minhangTask.getTask_type())) {
                List<MinhangQuestion> minhang_question_list = MinhangQuestionService.instance.taskList(minhangTask.getTask_id());
                minhangTask.put(MinhangTask.QUESTION_LIST, minhang_question_list);
                validateResponse(MinhangTask.QUESTION_LIST);
            }
        } else {
            validateResponse(MinhangMemberTask.MEMBER_TASK_ID, MinhangMemberTask.MEMBER_ID, MinhangMemberTask.TASK_ID);
            result.put("member_task", minhangMemberTask);
        }
        result.put("task", minhangTask);
        
        renderSuccessJson(result);
    }
    
    @ActionKey("/mobile/minhang/task/member/complete")
    public void memberComplete() {
        validateRequest(MinhangTask.TASK_ID, MinhangMemberTask.KEY_ACTIVATED_STEP);
        
        MinhangTask model = getModel(MinhangTask.class);
        MinhangMemberTask minhang_member_task = getModel(MinhangMemberTask.class);
        String request_user_id = getRequest_user_id();
        
        MinhangMemberTask minhangMemberTask = MinhangMemberTaskService.instance.userAndTaskFind(request_user_id, model.getTask_id());
        
        if (minhangMemberTask != null) {
            throw new RuntimeException("任务已经完成");
        }
        
        MinhangTask task = MinhangTaskService.instance.find(model.getTask_id());
        
        MinhangMemberKey minhangMemberKey = MinhangMemberKeyService.instance.userAndKeyFind(request_user_id, task.getKey_id());
        
        if (minhangMemberKey.getKey_is_activated()) {
            throw new RuntimeException("钥匙已激活，无需再完成任务");
        }
        
        //会员完成任务
        User user = UserService.instance.find(request_user_id);
        
        //保存会员完成记录
        minhang_member_task.setApp_id(task.getApp_id());
        minhang_member_task.setMember_task_id(Util.getRandomUUID());
        minhang_member_task.setTask_id(task.getTask_id());
        minhang_member_task.setKey_id(task.getKey_id());
        minhang_member_task.setUser_id(user.getUser_id());
        minhang_member_task.setMember_id(user.getObject_id());
                
        Boolean result = MinhangMemberTaskService.instance.save(minhang_member_task, request_user_id);
        
        if (result) {
            //会员钥匙状态更新，判断是否获得钥匙
            int task_complete_quantity = minhangMemberKey.getTask_complete_quantity() + 1;
            if (task_complete_quantity == minhangMemberKey.getTask_quantity()) { //判断任务完成数量达到钥匙激活数量
                minhangMemberKey.setKey_is_activated(true);
            }
            minhangMemberKey.setTask_complete_quantity(task_complete_quantity);
            MinhangMemberKeyService.instance.update(minhangMemberKey, minhangMemberKey.getMember_key_id(), request_user_id, minhangMemberKey.getSystem_version());
            JSONObject jsonObject = getParameterJSONObject();
            //保存会员完成任务结果
            if (task.getTask_type().equals(MinhangTaskType.QUESTION.getKey())) {
                validateRequest(MinhangTask.MEMBER_QUESTION_LIST);
                JSONArray jsonArray = jsonObject.getJSONArray(MinhangTask.MEMBER_QUESTION_LIST);
                for (int i = 0; i < jsonArray.size(); i++) {
                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                    MinhangMemberQuestion minhangMemberQuestion = jsonObject1.toJavaObject(MinhangMemberQuestion.class);
                    if (ValidateUtil.isNullOrEmpty(minhangMemberQuestion.getQuestion_id())) {
                        throw new RuntimeException("题目不存在");
                    }
                    if (ValidateUtil.isNullOrEmpty(minhangMemberQuestion.getMember_answer())) {
                        throw new RuntimeException("回答为空"); 
                    }
                    minhangMemberQuestion.setMember_question_id(Util.getRandomUUID());
                    minhangMemberQuestion.setApp_id(task.getApp_id());
                    minhangMemberQuestion.setMember_id(user.getObject_id());
                    minhangMemberQuestion.setTask_id(task.getTask_id());
                    minhangMemberQuestion.setUser_id(user.getUser_id());
                    MinhangMemberQuestionService.instance.save(minhangMemberQuestion, request_user_id);
                }
            } else if (task.getTask_type().equals(MinhangTaskType.PICTURE.getKey())) {
                validateRequest(MinhangTask.MEMBER_PICTURE);
                JSONObject jsonObject1 = jsonObject.getJSONObject(MinhangTask.MEMBER_PICTURE);
                MinhangMemberPicture minhangMemberPicture = jsonObject1.toJavaObject(MinhangMemberPicture.class);
                if (ValidateUtil.isNullOrEmpty(minhangMemberPicture.getPicture_file())) {
                    throw new RuntimeException("上传图片为空");
                }
                minhangMemberPicture.setMember_picture_id(Util.getRandomUUID());
                minhangMemberPicture.setApp_id(task.getApp_id());
                minhangMemberPicture.setMember_id(user.getObject_id());
                minhangMemberPicture.setUser_id(user.getUser_id());
                minhangMemberPicture.setTask_id(task.getTask_id());
                MinhangMemberPictureService.instance.save(minhangMemberPicture, request_user_id);
            } else if (task.getTask_type().equals(MinhangTaskType.RECORD.getKey())) {
                validateRequest(MinhangTask.MEMBER_RECORD);
                JSONObject jsonObject1 = jsonObject.getJSONObject(MinhangTask.MEMBER_RECORD);
                MinhangMemberRecord minhangMemberRecord = jsonObject1.toJavaObject(MinhangMemberRecord.class);
                if (ValidateUtil.isNullOrEmpty(minhangMemberRecord.getRecord_file())) {
                    throw new RuntimeException("上传录音为空");
                }
                minhangMemberRecord.setMember_record_id(Util.getRandomUUID());
                minhangMemberRecord.setApp_id(task.getApp_id());
                minhangMemberRecord.setMember_id(user.getObject_id());
                minhangMemberRecord.setUser_id(user.getUser_id());
                minhangMemberRecord.setTask_id(task.getTask_id());
                MinhangMemberRecordService.instance.save(minhangMemberRecord, request_user_id);
            }
        }

        renderSuccessJson(result);
    }
    
    @ActionKey("/mobile/minhang/task/user/complete/list")
    public void userCompleteList() {
        validateRequest(MinhangTask.TASK_ID, Constant.PAGE_INDEX, Constant.PAGE_SIZE);
        
        MinhangTask model = getModel(MinhangTask.class);
        
        List<MinhangMemberTask> resultList = MinhangMemberTaskService.instance.taskList(model.getTask_id(), getM(), getN());
        
        for (MinhangMemberTask result : resultList) {
            User user = UserService.instance.find(result.getUser_id());
            result.put(User.USER_NAME, user.getUser_name());
            result.put(User.USER_AVATAR, FileService.instance.getFile_path(user.getUser_avatar()));
        }
        validateResponse(User.USER_NAME, User.USER_AVATAR);
        renderSuccessJson(resultList);
    }
    
    @ActionKey("/mobile/minhang/task/member/picture/list")
    public void memberPictureList() {
        validateRequest(MinhangTask.TASK_ID, Constant.PAGE_INDEX, Constant.PAGE_SIZE);
        
        MinhangTask model = getModel(MinhangTask.class);
        
        List<MinhangMemberTask> resultList = MinhangMemberTaskService.instance.taskList(model.getTask_id(), getM(), getN());
        
        for (MinhangMemberTask result : resultList) {
            MinhangMemberPicture minhangMemberPicture = MinhangMemberPictureService.instance.userAndTaskfind(result.getUser_id(), model.getTask_id());
            User user = UserService.instance.find(result.getUser_id());
            result.put(User.USER_NAME, user.getUser_name());
            result.put(User.USER_AVATAR, FileService.instance.getFile_path(minhangMemberPicture.getPicture_file()));
        }
        validateResponse(User.USER_NAME, User.USER_AVATAR);
        renderSuccessJson(resultList);
    }
    
    @ActionKey("/mobile/minhang/task/check")
    public void check() {
        validateRequest(MinhangTask.TASK_ID,  MinhangTask.ACTION);

        String request_user_id = getRequest_user_id();
        MinhangTask model = getModel(MinhangTask.class);
        
        MinhangTask minhangTask = MinhangTaskService.instance.find(model.getTask_id());
        
        String result = null;
        if (minhangTask == null) {
            result = "请扫描正确任务二维码";
        } else {
            if ("loadPoster".equals(model.getStr(MinhangTask.ACTION))) {
                List<MinhangPoster> minhangPosterList = MinhangPosterService.instance.taskFind(model.getTask_id());
                if (minhangPosterList ==null || minhangPosterList.size() == 0) {
                    result = "请扫描海报二维码";
                }
            } else if ("loadPartyHistory".equals(model.getStr(MinhangTask.ACTION))) {
                List<MinhangPartyHistory> minhangPartyHistoryList = MinhangPartyHistoryService.instance.taskFind(model.getTask_id());
                if (minhangPartyHistoryList ==null || minhangPartyHistoryList.size() == 0) {
                    result = "请扫描党史二维码";
                }
            } else if ("loadPartySong".equals(model.getStr(MinhangTask.ACTION))) {
                List<MinhangPartySong> minhangPartySongList = MinhangPartySongService.instance.taskFind(model.getTask_id());
                if (minhangPartySongList ==null || minhangPartySongList.size() == 0) {
                    result = "请扫描党歌二维码";
                }
            } else if ("loadHandlePrint".equals(model.getStr(MinhangTask.ACTION))) {
                if (!"001f46fc946647efa4bccaa9735f94e6".equals(model.getTask_id())) {
                    result = "请扫描手印二维码";
                }
            } else if ("loadTimelineEvent".equals(model.getStr(MinhangTask.ACTION))) {
                List<MinhangTimelineEvent> timelineEventList = MinhangTimelineEventService.instance.taskFind(model.getTask_id());
                if (timelineEventList ==null || timelineEventList.size() == 0) {
                    result = "请扫描党史时间轴事件二维码";
                }
            } else if ("loadVideoTask".equals(model.getStr(MinhangTask.ACTION))) {
                List<MinhangVideoTask> minhangVideoTaskList = MinhangVideoTaskService.instance.taskFind(model.getTask_id());
                if (minhangVideoTaskList ==null || minhangVideoTaskList.size() == 0) {
                    result = "请扫描党课学习视频问题二维码";
                }
            } else {
                MinhangMemberTask minhangMemberTask = MinhangMemberTaskService.instance.userAndTaskFind(request_user_id, model.getTask_id());
                if (minhangMemberTask != null) {
                    result = "您已完成该任务，请扫描其他任务二维码";
                }
            }
            
        }
        
        renderSuccessJson(result);
    }

    @ActionKey("/mobile/minhang/task/save")
    public void save() {

        renderSuccessJson();
    }
    
    @ActionKey("/mobile/minhang/task/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/minhang/task/delete")
    public void delete() {

        renderSuccessJson();
    }

}