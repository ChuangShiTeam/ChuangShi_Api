package com.nowui.chuangshi.api.school.admin;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.school.model.Clazz;
import com.nowui.chuangshi.api.school.service.ClazzService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.common.interceptor.AdminInterceptor;
import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.util.Util;

import java.util.List;

@Before(AdminInterceptor.class)
@ControllerKey("/admin/clazz")
public class ClazzController extends Controller {

    @ActionKey("/admin/clazz/list")
    public void list() {
        validateRequest(Clazz.CLAZZ_NAME, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        Clazz model = getModel(Clazz.class);
        Cnd cnd = Cnd.where(Clazz.APP_ID, model.getApp_id()).andAllowEmpty(Clazz.CLAZZ_NAME, model.getClazz_name());

        Integer resultCount = ClazzService.me.count(cnd);
        List<Clazz> resultList = ClazzService.me.list(cnd.paginate(getM(), getN()));

        validateResponse(Clazz.CLAZZ_ID, Clazz.CLAZZ_NAME, Clazz.CLAZZ_COURSE_APPLY_LIMIT, Clazz.CLAZZ_COURSE_APPLY_START_TIME, Clazz.CLAZZ_COURSE_APPLY_END_TIME, Clazz.CLAZZ_SORT, Clazz.SYSTEM_VERSION);

        renderSuccessJson(resultCount, resultList);
    }

    @ActionKey("/admin/clazz/find")
    public void find() {
        validateRequest(Clazz.CLAZZ_ID);

        Clazz model = getModel(Clazz.class);

        Clazz result = ClazzService.me.findById(model.getClazz_id());

        validateResponse(Clazz.CLAZZ_NAME, Clazz.CLAZZ_COURSE_APPLY_LIMIT, Clazz.CLAZZ_COURSE_APPLY_START_TIME, Clazz.CLAZZ_COURSE_APPLY_END_TIME, Clazz.CLAZZ_SORT, Clazz.SYSTEM_VERSION);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/clazz/save")
    public void save() {
        validateRequest(Clazz.CLAZZ_NAME, Clazz.CLAZZ_COURSE_APPLY_LIMIT, Clazz.CLAZZ_COURSE_APPLY_START_TIME, Clazz.CLAZZ_COURSE_APPLY_END_TIME, Clazz.CLAZZ_SORT);

        Clazz model = getModel(Clazz.class);
        model.setClazz_id(Util.getRandomUUID());

        Boolean result = ClazzService.me.save(model);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/clazz/update")
    public void update() {
        validateRequest(Clazz.CLAZZ_ID, Clazz.CLAZZ_NAME, Clazz.CLAZZ_COURSE_APPLY_LIMIT, Clazz.CLAZZ_COURSE_APPLY_START_TIME, Clazz.CLAZZ_COURSE_APPLY_END_TIME, Clazz.CLAZZ_SORT, Clazz.SYSTEM_VERSION);

        Clazz model = getModel(Clazz.class);

        Boolean result = ClazzService.me.update(model, Cnd.where(model.CLAZZ_ID, model.getClazz_id()).and(Clazz.SYSTEM_VERSION, model.getSystem_version()));

        renderSuccessJson(result);
    }

    @ActionKey("/admin/clazz/delete")
    public void delete() {
        validateRequest(Clazz.CLAZZ_ID, Clazz.SYSTEM_VERSION);

        Clazz model = getModel(Clazz.class);

        Boolean result = ClazzService.me.delete(model, Cnd.where(model.CLAZZ_ID, model.getClazz_id()).and(Clazz.SYSTEM_VERSION, model.getSystem_version()));

        renderSuccessJson(result);
    }

}