package com.nowui.chuangshi.api.minhang.admin;


import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.minhang.model.MinhangTimelineEvent;
import com.nowui.chuangshi.api.minhang.service.MinhangTimelineEventService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;

import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.util.Util;

import java.util.List;


@ControllerKey("/admin/minhang/timeline/event")
public class MinhangTimelineEventController extends Controller {

    @ActionKey("/admin/minhang/timeline/event/list")
    public void list() {
        validateRequest(MinhangTimelineEvent.TIMELINE_ID, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        MinhangTimelineEvent model = getModel(MinhangTimelineEvent.class);
        String request_app_id = getRequest_app_id();

        Integer resultCount = MinhangTimelineEventService.instance.adminCount(request_app_id, model.getTimeline_id());
        List<MinhangTimelineEvent> resultList = MinhangTimelineEventService.instance.adminList(request_app_id, model.getTimeline_id(), getM(), getN());

        validateResponse(MinhangTimelineEvent.TIMELINE_EVENT_ID, MinhangTimelineEvent.TIMELINE_ID, MinhangTimelineEvent.TASK_ID, MinhangTimelineEvent.TIMELINE_EVENT_TIME, MinhangTimelineEvent.TIMELINE_EVENT_TITLE, MinhangTimelineEvent.SYSTEM_VERSION);

        renderSuccessJson(resultCount, resultList);
    }

    @ActionKey("/admin/minhang/timeline/event/find")
    public void find() {
        validateRequest(MinhangTimelineEvent.TIMELINE_EVENT_ID);

        MinhangTimelineEvent model = getModel(MinhangTimelineEvent.class);

        MinhangTimelineEvent result = MinhangTimelineEventService.instance.find(model.getTimeline_event_id());

        validateResponse(MinhangTimelineEvent.TIMELINE_ID, MinhangTimelineEvent.TASK_ID, MinhangTimelineEvent.TIMELINE_EVENT_TIME, MinhangTimelineEvent.TIMELINE_EVENT_TITLE, MinhangTimelineEvent.TIMELINE_EVENT_CONTENT, MinhangTimelineEvent.SYSTEM_VERSION);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/minhang/timeline/event/save")
    public void save() {
        validateRequest(MinhangTimelineEvent.TIMELINE_ID, MinhangTimelineEvent.TASK_ID, MinhangTimelineEvent.TIMELINE_EVENT_TIME, MinhangTimelineEvent.TIMELINE_EVENT_TITLE, MinhangTimelineEvent.TIMELINE_EVENT_CONTENT);

        MinhangTimelineEvent model = getModel(MinhangTimelineEvent.class);
        model.setTimeline_event_id(Util.getRandomUUID());
        String request_user_id = getRequest_user_id();

        Boolean result = MinhangTimelineEventService.instance.save(model, request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/minhang/timeline/event/update")
    public void update() {
        validateRequest(MinhangTimelineEvent.TIMELINE_EVENT_ID, MinhangTimelineEvent.TIMELINE_ID, MinhangTimelineEvent.TASK_ID, MinhangTimelineEvent.TIMELINE_EVENT_TIME, MinhangTimelineEvent.TIMELINE_EVENT_TITLE, MinhangTimelineEvent.TIMELINE_EVENT_CONTENT, MinhangTimelineEvent.SYSTEM_VERSION);

        MinhangTimelineEvent model = getModel(MinhangTimelineEvent.class);
        String request_user_id = getRequest_user_id();

        Boolean result = MinhangTimelineEventService.instance.update(model, model.getTimeline_event_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey("/admin/minhang/timeline/event/delete")
    public void delete() {
        validateRequest(MinhangTimelineEvent.TIMELINE_EVENT_ID, MinhangTimelineEvent.SYSTEM_VERSION);

        MinhangTimelineEvent model = getModel(MinhangTimelineEvent.class);
        String request_user_id = getRequest_user_id();

        Boolean result = MinhangTimelineEventService.instance.delete(model.getTimeline_event_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

}