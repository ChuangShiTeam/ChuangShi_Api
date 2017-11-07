package com.nowui.chuangshi.api.minhang.admin;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import com.alibaba.fastjson.JSONObject;

import com.jfinal.core.ActionKey;
import com.jfinal.kit.PathKit;
import com.nowui.chuangshi.api.minhang.model.MinhangKey;
import com.nowui.chuangshi.api.minhang.model.MinhangTask;
import com.nowui.chuangshi.api.minhang.service.MinhangTaskService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;

import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.util.FileUtil;
import com.nowui.chuangshi.util.QRCodeUtil;
import com.nowui.chuangshi.util.Util;


@ControllerKey("/admin/minhang/task")
public class MinhangTaskController extends Controller {

    @ActionKey("/admin/minhang/task/list")
    public void list() {
        validateRequest(MinhangTask.KEY_ID, MinhangTask.TASK_NAME, MinhangTask.TASK_TYPE, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        MinhangTask model = getModel(MinhangTask.class);
        String request_app_id = getRequest_app_id();

        Integer resultCount = MinhangTaskService.instance.adminCount(request_app_id, model.getKey_id(), model.getTask_name(), model.getTask_type());
        List<MinhangTask> resultList = MinhangTaskService.instance.adminList(request_app_id, model.getKey_id(), model.getTask_name(), model.getTask_type(), getM(), getN());
        
        validateResponse(MinhangTask.TASK_ID, MinhangTask.KEY_ID, MinhangTask.SCREEN_ID, MinhangKey.KEY_NAME, MinhangTask.TASK_NAME, MinhangTask.TASK_TYPE, MinhangTask.TASK_QRCODE_URL, MinhangTask.TASK_SORT, MinhangTask.SYSTEM_VERSION);

        renderSuccessJson(resultCount, resultList);
    }
    
    @ActionKey("/admin/minhang/task/key/list")
    public void keyList() {
        validateRequest(MinhangTask.KEY_ID);

        MinhangTask model = getModel(MinhangTask.class);

        List<MinhangTask> resultList = MinhangTaskService.instance.keyList(model.getKey_id());
        
        validateResponse(MinhangTask.TASK_ID, MinhangTask.KEY_ID, MinhangTask.SCREEN_ID, MinhangTask.TASK_NAME, MinhangTask.TASK_TYPE, MinhangTask.TASK_QRCODE_URL, MinhangTask.TASK_SORT, MinhangTask.SYSTEM_VERSION);

        renderSuccessJson(resultList);
    }
    
    @ActionKey("/admin/minhang/task/all/list")
    public void allList() {
        
        String request_app_id = getRequest_app_id();
        
        List<MinhangTask> resultList = MinhangTaskService.instance.allList(request_app_id);
        
        validateResponse(MinhangTask.TASK_ID, MinhangTask.KEY_ID, MinhangTask.SCREEN_ID, MinhangTask.TASK_NAME, MinhangTask.TASK_TYPE, MinhangTask.TASK_QRCODE_URL, MinhangTask.TASK_SORT, MinhangTask.SYSTEM_VERSION);

        renderSuccessJson(resultList);
    }

    @ActionKey("/admin/minhang/task/find")
    public void find() {
        validateRequest(MinhangTask.TASK_ID);

        MinhangTask model = getModel(MinhangTask.class);

        MinhangTask result = MinhangTaskService.instance.find(model.getTask_id());
        
        validateResponse(MinhangTask.KEY_ID, MinhangTask.TASK_NAME, MinhangTask.SCREEN_ID, MinhangTask.TASK_TYPE, MinhangTask.TASK_QRCODE_URL, MinhangTask.TASK_SORT, MinhangTask.TASK_DESCRIPTION, MinhangTask.SYSTEM_VERSION);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/minhang/task/save")
    public void save() {
        validateRequest(MinhangTask.KEY_ID, MinhangTask.SCREEN_ID, MinhangTask.TASK_NAME, MinhangTask.TASK_TYPE, MinhangTask.TASK_SORT, MinhangTask.TASK_DESCRIPTION);

        MinhangTask model = getModel(MinhangTask.class);
        model.setTask_id(Util.getRandomUUID());
        String request_user_id = getRequest_user_id();
        String request_app_id = getRequest_app_id();
        
        //生成任务二维码
        OutputStream os = null;
        try {
            String directory_path = "/" + Constant.UPLOAD + "/" + request_app_id;
            String qrcode_url = directory_path+ "/" + model.getTask_id() + ".png";
            String real_file_path = PathKit.getWebRootPath() + qrcode_url;
            FileUtil.createPath(PathKit.getWebRootPath() + directory_path);
            os = new FileOutputStream(real_file_path);
            
            JSONObject jsonObject = new JSONObject();
            jsonObject.put(MinhangTask.TASK_ID, model.getTask_id());
            jsonObject.put(MinhangTask.SCREEN_ID, model.getScreen_id());
            jsonObject.put(MinhangTask.ACTION, ""); //TODO 动作怎样去定义
            String content = jsonObject.toString();
            QRCodeUtil.encode(content, os);
            model.setTask_qrcode_url(qrcode_url);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (os != null) {
                    os.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Boolean result = MinhangTaskService.instance.save(model, request_user_id);
        
        renderSuccessJson(result);
    }

    @ActionKey("/admin/minhang/task/update")
    public void update() {
        validateRequest(MinhangTask.TASK_ID, MinhangTask.KEY_ID, MinhangTask.SCREEN_ID, MinhangTask.TASK_NAME, MinhangTask.TASK_TYPE, MinhangTask.TASK_SORT, MinhangTask.TASK_DESCRIPTION, MinhangTask.SYSTEM_VERSION);

        MinhangTask model = getModel(MinhangTask.class);
        String request_user_id = getRequest_user_id();
        String request_app_id = getRequest_app_id();
        
        //生成任务二维码
        OutputStream os = null;
        try {
            String directory_path = "/" + Constant.UPLOAD + "/" + request_app_id;
            String qrcode_url = directory_path+ "/" + model.getTask_id() + ".png";
            String real_file_path = PathKit.getWebRootPath() + qrcode_url;
            FileUtil.createPath(PathKit.getWebRootPath() + directory_path);
            os = new FileOutputStream(real_file_path);
            
            JSONObject jsonObject = new JSONObject();
            jsonObject.put(MinhangTask.TASK_ID, model.getTask_id());
            jsonObject.put(MinhangTask.SCREEN_ID, model.getScreen_id());
            jsonObject.put(MinhangTask.ACTION, ""); //TODO 动作怎样去定义
            String content = jsonObject.toString();
            QRCodeUtil.encode(content, os);
            model.setTask_qrcode_url(qrcode_url);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (os != null) {
                    os.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Boolean result = MinhangTaskService.instance.update(model, model.getTask_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey("/admin/minhang/task/delete")
    public void delete() {
        validateRequest(MinhangTask.TASK_ID, MinhangTask.SYSTEM_VERSION);

        MinhangTask model = getModel(MinhangTask.class);
        String request_user_id = getRequest_user_id();

        Boolean result = MinhangTaskService.instance.delete(model.getTask_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

}