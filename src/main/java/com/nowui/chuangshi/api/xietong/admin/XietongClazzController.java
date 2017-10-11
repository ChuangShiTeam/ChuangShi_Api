package com.nowui.chuangshi.api.xietong.admin;

import java.util.List;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.xietong.model.XietongClazz;
import com.nowui.chuangshi.api.xietong.model.XietongOrganization;
import com.nowui.chuangshi.api.xietong.model.XietongTeacher;
import com.nowui.chuangshi.api.xietong.service.XietongClazzService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.common.interceptor.AdminInterceptor;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.util.Util;

@Before(AdminInterceptor.class)
@ControllerKey("/admin/xietong/clazz")
public class XietongClazzController extends Controller {

    @ActionKey("/admin/xietong/clazz/list")
    public void list() {
        validateRequest(XietongClazz.CLAZZ_NAME, XietongClazz.ORGANIZATION_ID, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        XietongClazz model = getModel(XietongClazz.class);
        String request_app_id = getRequest_app_id();

        Integer resultCount = XietongClazzService.instance.adminCount(request_app_id, model.getClazz_name(), model.getOrganization_id());
        List<XietongClazz> resultList = XietongClazzService.instance.adminList(request_app_id, model.getClazz_name(), model.getOrganization_id(), getM(), getN());

        validateResponse(XietongClazz.CLAZZ_ID, XietongClazz.ORGANIZATION_ID, XietongOrganization.ORGANIZATION_NAME, XietongClazz.CLAZZ_NAME, XietongClazz.CLAZZ_COURSE_APPLY_LIMIT, XietongClazz.CLAZZ_COURSE_APPLY_START_TIME, XietongClazz.CLAZZ_COURSE_APPLY_END_TIME, XietongClazz.CLAZZ_SORT, XietongClazz.SYSTEM_VERSION);

        renderSuccessJson(resultCount, resultList);
    }
    
    @ActionKey("/admin/xietong/clazz/all/list")
    public void alllist() {

        String request_app_id = getRequest_app_id();

        List<XietongClazz> resultList = XietongClazzService.instance.allList(request_app_id);

        validateResponse(XietongClazz.CLAZZ_ID, XietongClazz.CLAZZ_NAME, XietongClazz.CLAZZ_COURSE_APPLY_LIMIT, XietongClazz.CLAZZ_COURSE_APPLY_START_TIME, XietongClazz.CLAZZ_COURSE_APPLY_END_TIME, XietongClazz.CLAZZ_SORT, XietongClazz.SYSTEM_VERSION);

        renderSuccessJson(resultList);
    }

    @ActionKey("/admin/xietong/clazz/find")
    public void find() {
        validateRequest(XietongClazz.CLAZZ_ID);

        XietongClazz model = getModel(XietongClazz.class);

        XietongClazz result = XietongClazzService.instance.find(model.getClazz_id());

        validateResponse(XietongClazz.CLAZZ_NAME, XietongTeacher.ORGANIZATION_ID, XietongClazz.CLAZZ_COURSE_APPLY_LIMIT, XietongClazz.CLAZZ_COURSE_APPLY_START_TIME, XietongClazz.CLAZZ_COURSE_APPLY_END_TIME, XietongClazz.CLAZZ_SORT, XietongClazz.SYSTEM_VERSION);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/xietong/clazz/save")
    public void save() {
        validateRequest(XietongClazz.ORGANIZATION_ID, XietongClazz.CLAZZ_NAME, XietongClazz.CLAZZ_COURSE_APPLY_LIMIT, XietongClazz.CLAZZ_COURSE_APPLY_START_TIME, XietongClazz.CLAZZ_COURSE_APPLY_END_TIME, XietongClazz.CLAZZ_SORT);

        String request_user_id = getRequest_user_id();
        
        XietongClazz model = getModel(XietongClazz.class);
        model.setClazz_id(Util.getRandomUUID());

        Boolean result = XietongClazzService.instance.save(model, request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/xietong/clazz/update")
    public void update() {
        validateRequest(XietongClazz.CLAZZ_ID, XietongClazz.ORGANIZATION_ID, XietongClazz.CLAZZ_NAME, XietongClazz.CLAZZ_COURSE_APPLY_LIMIT, XietongClazz.CLAZZ_COURSE_APPLY_START_TIME, XietongClazz.CLAZZ_COURSE_APPLY_END_TIME, XietongClazz.CLAZZ_SORT, XietongClazz.SYSTEM_VERSION);

        String request_user_id = getRequest_user_id();
        
        XietongClazz model = getModel(XietongClazz.class);

        Boolean result = XietongClazzService.instance.update(model, model.getClazz_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey("/admin/xietong/clazz/delete")
    public void delete() {
        validateRequest(XietongClazz.CLAZZ_ID, XietongClazz.SYSTEM_VERSION);
        
        String request_user_id = getRequest_user_id();

        XietongClazz model = getModel(XietongClazz.class);

        Boolean result = XietongClazzService.instance.delete(model.getClazz_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

}