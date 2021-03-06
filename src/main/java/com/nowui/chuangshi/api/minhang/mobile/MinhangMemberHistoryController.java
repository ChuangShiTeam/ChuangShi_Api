package com.nowui.chuangshi.api.minhang.mobile;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.file.model.File;
import com.nowui.chuangshi.api.file.service.FileService;
import com.nowui.chuangshi.api.minhang.model.MinhangMemberHistory;
import com.nowui.chuangshi.api.minhang.model.MinhangMemberItinerary;
import com.nowui.chuangshi.api.minhang.model.MinhangMemberKey;
import com.nowui.chuangshi.api.minhang.model.MinhangMemberPicture;
import com.nowui.chuangshi.api.minhang.model.MinhangMemberQuestion;
import com.nowui.chuangshi.api.minhang.model.MinhangMemberRecord;
import com.nowui.chuangshi.api.minhang.model.MinhangMemberTask;
import com.nowui.chuangshi.api.minhang.model.MinhangQuestion;
import com.nowui.chuangshi.api.minhang.service.MinhangMemberHistoryService;
import com.nowui.chuangshi.api.minhang.service.MinhangMemberItineraryService;
import com.nowui.chuangshi.api.minhang.service.MinhangMemberKeyService;
import com.nowui.chuangshi.api.minhang.service.MinhangMemberPictureService;
import com.nowui.chuangshi.api.minhang.service.MinhangMemberQuestionService;
import com.nowui.chuangshi.api.minhang.service.MinhangMemberRecordService;
import com.nowui.chuangshi.api.minhang.service.MinhangMemberTaskService;
import com.nowui.chuangshi.api.minhang.service.MinhangQuestionService;
import com.nowui.chuangshi.api.user.model.User;
import com.nowui.chuangshi.api.user.service.UserService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.type.MinhangMemberTaskType;
import com.nowui.chuangshi.util.DateUtil;
import com.nowui.chuangshi.util.Util;

@ControllerKey("/mobile/minhang/member/history")
public class MinhangMemberHistoryController extends Controller {

    /**
     * 用户纪念册列表
     */
    @ActionKey("/mobile/minhang/member/history/list")
    public void list() {
        
        String request_user_id = getRequest_user_id();
        
        List<MinhangMemberHistory> minhangMemberHistoryList = MinhangMemberHistoryService.instance.userList(request_user_id);
        
        validateResponse(MinhangMemberHistory.MEMBER_HISTORY_ID, MinhangMemberHistory.MEMBER_HISTORY_NAME);
        
        renderSuccessJson(minhangMemberHistoryList);
    }

    /**
     * 用户纪念册信息
     */
    @ActionKey("/mobile/minhang/member/history/find")
    public void find() {
        validateRequest(MinhangMemberHistory.MEMBER_HISTORY_ID);
        
        MinhangMemberHistory model = getModel(MinhangMemberHistory.class);
        String request_user_id = getRequest_user_id();
        
        MinhangMemberHistory bean = MinhangMemberHistoryService.instance.find(model.getMember_history_id());
        if (bean == null) {
            throw new RuntimeException("纪念册不存在");
        }
        //查询纪念册对应的任务记录
        
        List<MinhangMemberTask> minhang_member_taskList = MinhangMemberTaskService.instance.historyList(model.getMember_history_id());
        Map<String, Object> result = new HashMap<String, Object>();
        List<MinhangMemberQuestion> timelineEventQuestion = new ArrayList<>();
        List<MinhangMemberQuestion> videoQuestion = new ArrayList<>();
        for (MinhangMemberTask minhangMemberTask : minhang_member_taskList) {
        	if (MinhangMemberTaskType.POSTER_PICTURE.getKey().equals(minhangMemberTask.getMember_task_type())) {
        		
        		MinhangMemberPicture minhangMemberPicture = MinhangMemberPictureService.instance.userAndTaskAndItineraryFind(request_user_id, minhangMemberTask.getTask_id(), minhangMemberTask.getMember_itinerary_id());
        		minhangMemberPicture.put(File.FILE_PATH, FileService.instance.getFile_path(minhangMemberPicture.getPicture_file()));
        		result.put(MinhangMemberTaskType.POSTER_PICTURE.getKey(), minhangMemberPicture);
        	
        	} else if (MinhangMemberTaskType.PARTY_HISTORY_RECORD.getKey().equals(minhangMemberTask.getMember_task_type())) {
        		
        		MinhangMemberRecord minhangMemberRecord = MinhangMemberRecordService.instance.userAndTaskAndItineraryFind(request_user_id, minhangMemberTask.getTask_id(), minhangMemberTask.getMember_itinerary_id());
        		minhangMemberRecord.put(File.FILE_PATH, FileService.instance.getFile_path(minhangMemberRecord.getRecord_file()));
        		result.put(MinhangMemberTaskType.PARTY_HISTORY_RECORD.getKey(), minhangMemberRecord);
        	
        	} else if (MinhangMemberTaskType.PARTY_SONG_RECORD.getKey().equals(minhangMemberTask.getMember_task_type())) {
        		
        		MinhangMemberRecord minhangMemberRecord = MinhangMemberRecordService.instance.userAndTaskAndItineraryFind(request_user_id, minhangMemberTask.getTask_id(), minhangMemberTask.getMember_itinerary_id());
        		minhangMemberRecord.put(File.FILE_PATH, FileService.instance.getFile_path(minhangMemberRecord.getRecord_file()));
        		result.put(MinhangMemberTaskType.PARTY_SONG_RECORD.getKey(), minhangMemberRecord);
        	
        	} else if (MinhangMemberTaskType.HAND_PRINT_PICTURE.getKey().equals(minhangMemberTask.getMember_task_type())) {
        		
        		MinhangMemberPicture minhangMemberPicture = MinhangMemberPictureService.instance.userAndTaskAndItineraryFind(request_user_id, minhangMemberTask.getTask_id(), minhangMemberTask.getMember_itinerary_id());
        		minhangMemberPicture.put(File.FILE_PATH, FileService.instance.getFile_path(minhangMemberPicture.getPicture_file()));
        		result.put(MinhangMemberTaskType.HAND_PRINT_PICTURE.getKey(), minhangMemberPicture);
        	
        	} else if (MinhangMemberTaskType.LOCATION_QUESTION.getKey().equals(minhangMemberTask.getMember_task_type())) {
        		List<MinhangMemberQuestion> locationQuestion = MinhangMemberQuestionService.instance.userAndTaskAndItineraryList(request_user_id, minhangMemberTask.getTask_id(), minhangMemberTask.getMember_itinerary_id());
        		for (MinhangMemberQuestion minhangMemberQuestion : locationQuestion) {
        			minhangMemberQuestion.put("question", MinhangQuestionService.instance.find(minhangMemberQuestion.getQuestion_id()));
        		}
        		result.put(MinhangMemberTaskType.LOCATION_QUESTION.getKey(), locationQuestion);
        	} else if (MinhangMemberTaskType.INFO_QUESTION.getKey().equals(minhangMemberTask.getMember_task_type())) {
        		MinhangMemberQuestion minhangMemberQuestion = MinhangMemberQuestionService.instance.userAndTaskAndItineraryFind(request_user_id, minhangMemberTask.getTask_id(), minhangMemberTask.getMember_itinerary_id());
        		MinhangQuestion question = MinhangQuestionService.instance.find(minhangMemberQuestion.getQuestion_id());
		        unescapeHtml4Model(question);
        		minhangMemberQuestion.put("question", question);
        		result.put(MinhangMemberTaskType.INFO_QUESTION.getKey(), minhangMemberQuestion);
        	} else if (MinhangMemberTaskType.TIMELINE_EVENT_QUESTION.getKey().equals(minhangMemberTask.getMember_task_type())) {
        		MinhangMemberQuestion minhangMemberQuestion = MinhangMemberQuestionService.instance.userAndTaskAndItineraryFind(request_user_id, minhangMemberTask.getTask_id(), minhangMemberTask.getMember_itinerary_id());
        		MinhangQuestion question = MinhangQuestionService.instance.find(minhangMemberQuestion.getQuestion_id());
                unescapeHtml4Model(question);
        		minhangMemberQuestion.put("question", question);
        		timelineEventQuestion.add(minhangMemberQuestion);
        		result.put(MinhangMemberTaskType.TIMELINE_EVENT_QUESTION.getKey(), timelineEventQuestion);
        	} else if (MinhangMemberTaskType.VIDEO_QUESTION.getKey().equals(minhangMemberTask.getMember_task_type())) {
        		MinhangMemberQuestion minhangMemberQuestion = MinhangMemberQuestionService.instance.userAndTaskAndItineraryFind(request_user_id, minhangMemberTask.getTask_id(), minhangMemberTask.getMember_itinerary_id());
        		MinhangQuestion question = MinhangQuestionService.instance.find(minhangMemberQuestion.getQuestion_id());
                unescapeHtml4Model(question);
                minhangMemberQuestion.put("question", question);        		
                videoQuestion.add(minhangMemberQuestion);
        		result.put(MinhangMemberTaskType.VIDEO_QUESTION.getKey(), videoQuestion);
        	}
        }
        
        validateResponse(MinhangMemberTaskType.POSTER_PICTURE.getKey(), 
        				MinhangMemberTaskType.PARTY_HISTORY_RECORD.getKey(), 
        				MinhangMemberTaskType.PARTY_SONG_RECORD.getKey(),
        				MinhangMemberTaskType.HAND_PRINT_PICTURE.getKey(),
        				MinhangMemberTaskType.LOCATION_QUESTION.getKey(),
        				MinhangMemberTaskType.INFO_QUESTION.getKey(),
        				MinhangMemberTaskType.TIMELINE_EVENT_QUESTION.getKey(),
        				MinhangMemberTaskType.VIDEO_QUESTION.getKey());
        renderSuccessJson(result);
    }

    /**
     * 生成用户纪念册
     */
    @ActionKey("/mobile/minhang/member/history/create")
    public void create() {
        String request_user_id = getRequest_user_id();
        String request_app_id = getRequest_app_id();
        
        //查询用户最近的寻钥之旅记录
        MinhangMemberItinerary member_itinerary = MinhangMemberItineraryService.instance.userLatestFind(request_user_id);
        
        if (member_itinerary == null) {
            throw new RuntimeException("还没有开启寻钥之旅");
        }
        
        MinhangMemberHistory minhangMemberHistory = MinhangMemberHistoryService.instance.itineraryFind(member_itinerary.getMember_itinerary_id());
        Boolean result = true;
        if (minhangMemberHistory == null) {
            //生成纪念册
        	User user = UserService.instance.find(request_user_id);
        	minhangMemberHistory = new MinhangMemberHistory();
        	minhangMemberHistory.setApp_id(request_app_id);
        	minhangMemberHistory.setMember_history_id(Util.getRandomUUID());
        	minhangMemberHistory.setMember_history_name(DateUtil.getDateString(new Date()));
        	minhangMemberHistory.setMember_id(user.getObject_id());
        	minhangMemberHistory.setUser_id(user.getUser_id());
        	minhangMemberHistory.setMember_itinerary_id(member_itinerary.getMember_itinerary_id());
        	result = MinhangMemberHistoryService.instance.save(minhangMemberHistory, request_user_id);
        } 
        if (result) {
        	//纪念册关联会员钥匙
            List<MinhangMemberKey> minhang_member_keyList = MinhangMemberKeyService.instance.itineraryList(member_itinerary.getMember_itinerary_id());
            if (minhang_member_keyList != null && minhang_member_keyList.size() > 0) {
            	for (MinhangMemberKey minhangMemberKey : minhang_member_keyList) {
            		minhangMemberKey.setMember_history_id(minhangMemberHistory.getMember_history_id());
            		MinhangMemberKeyService.instance.update(minhangMemberKey, minhangMemberKey.getMember_key_id(), request_user_id, minhangMemberKey.getSystem_version());
            	}
            }

            //纪念册关联会员任务
            List<MinhangMemberTask> minhang_member_taskList = MinhangMemberTaskService.instance.itineraryList(member_itinerary.getMember_itinerary_id());
            
            if (minhang_member_taskList != null && minhang_member_taskList.size() > 0) {
            	for (MinhangMemberTask minhangMemberTask : minhang_member_taskList) {
            		minhangMemberTask.setMember_history_id(minhangMemberHistory.getMember_history_id());
            		MinhangMemberTaskService.instance.update(minhangMemberTask, minhangMemberTask.getMember_task_id(), request_user_id, minhangMemberTask.getSystem_version());
            	
            	}
            }
            
            //纪念册关联会员图片
            List<MinhangMemberPicture> minhang_member_pictureList = MinhangMemberPictureService.instance.itineraryList(member_itinerary.getMember_itinerary_id());
            if (minhang_member_pictureList != null && minhang_member_pictureList.size() > 0) {
            	for (MinhangMemberPicture minhangMemberPicture : minhang_member_pictureList) {
            		minhangMemberPicture.setMember_history_id(minhangMemberHistory.getMember_history_id());
            		MinhangMemberPictureService.instance.update(minhangMemberPicture, minhangMemberPicture.getMember_picture_id(), request_user_id, minhangMemberPicture.getSystem_version());
            	}
            }
            
            //纪念册关联会员录音
            List<MinhangMemberRecord> minhang_member_recordList = MinhangMemberRecordService.instance.itineraryList(member_itinerary.getMember_itinerary_id());
            if (minhang_member_recordList != null && minhang_member_recordList.size() > 0) {
            	for (MinhangMemberRecord minhangMemberRecord : minhang_member_recordList) {
            		minhangMemberRecord.setMember_history_id(minhangMemberHistory.getMember_history_id());
            		MinhangMemberRecordService.instance.update(minhangMemberRecord, minhangMemberRecord.getMember_record_id(), request_user_id, minhangMemberRecord.getSystem_version());
            	}
            }
            
            //纪念册关联会员答题
            List<MinhangMemberQuestion> minhang_member_questionList = MinhangMemberQuestionService.instance.itineraryList(member_itinerary.getMember_itinerary_id());
            if (minhang_member_questionList != null && minhang_member_questionList.size() > 0) {
            	for (MinhangMemberQuestion minhang_member_question : minhang_member_questionList) {
            		minhang_member_question.setMember_history_id(minhangMemberHistory.getMember_history_id());
            		MinhangMemberQuestionService.instance.update(minhang_member_question, minhang_member_question.getMember_question_id(), request_user_id, minhang_member_question.getSystem_version());
            	}
            }
        }
        
        renderSuccessJson(result);
    }

    /**
     * 用户纪念册名称更新
     */
    @ActionKey("/mobile/minhang/member/history/name/update")
    public void nameUpdate() {
        validateRequest(MinhangMemberHistory.MEMBER_HISTORY_ID, MinhangMemberHistory.MEMBER_HISTORY_NAME);
        
        MinhangMemberHistory model = getModel(MinhangMemberHistory.class);
        String request_user_id = getRequest_user_id();
        
        MinhangMemberHistory bean = MinhangMemberHistoryService.instance.find(model.getMember_history_id());
        
        if (bean == null) {
            throw new RuntimeException("纪念册不存在");
        }
        bean.setMember_history_name(model.getMember_history_name());
        
        Boolean result = MinhangMemberHistoryService.instance.update(bean, bean.getMember_history_id(), request_user_id, bean.getSystem_version());
       
        renderSuccessJson(result);
    }

}