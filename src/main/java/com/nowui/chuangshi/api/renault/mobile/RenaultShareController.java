package com.nowui.chuangshi.api.renault.mobile;

import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.file.model.File;
import com.nowui.chuangshi.api.renault.model.RenaultShare;
import com.nowui.chuangshi.api.renault.model.RenaultShareImage;
import com.nowui.chuangshi.api.renault.service.RenaultShareImageService;
import com.nowui.chuangshi.api.renault.service.RenaultShareService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.util.Util;
import com.nowui.chuangshi.util.ValidateUtil;

@ControllerKey("/mobile/renault/share")
public class RenaultShareController extends Controller {

    //雷诺首页分享分页列表
    @ActionKey("/mobile/renault/share/list")
    public void list() {
        validateRequest(Constant.PAGE_INDEX, Constant.PAGE_SIZE);
        String request_app_id = getRequest_app_id();
        
        List<RenaultShare> renaultsharelist = RenaultShareService.instance.mobileList(request_app_id, getM(), getN());

        for (RenaultShare result : renaultsharelist) {
            
            List<RenaultShareImage> renault_share_imageList = RenaultShareImageService.instance.shareList(result.getShare_id());
            
            for (RenaultShareImage renaultShareImage : renault_share_imageList) {
                renaultShareImage.keep(RenaultShareImage.IMAGE_ID, RenaultShareImage.FILE_ID, File.FILE_PATH);
            }
            
            result.put(RenaultShare.SHARE_IMAGE_LIST, renault_share_imageList);
        
        }

        validateResponse(RenaultShare.SHARE_ID, RenaultShare.SHARE_IMAGE_LIST, RenaultShare.REMARK, RenaultShare.LIKE_NUM, RenaultShare.SHARE_NUM);

        renderSuccessJson(renaultsharelist);
    }

    //用户分享信息详情接口。 分享内容 图片 点赞数量
    //Add by lyn
    //2017.11.1
    @ActionKey("/mobile/renault/share/find")
    public void find() {
        validateRequest(RenaultShare.SHARE_ID);
        RenaultShare renault_share = getModel(RenaultShare.class);
        RenaultShare result = RenaultShareService.instance.find(renault_share.getShare_id());

        //String request_user_id = getRequest_user_id();
        List<RenaultShareImage> renault_share_imageList = RenaultShareImageService.instance.shareList(result.getShare_id());

        for (RenaultShareImage renaultShareImage : renault_share_imageList) {
            renaultShareImage.keep(RenaultShareImage.IMAGE_ID, RenaultShareImage.FILE_ID, File.FILE_PATH);
        }

        result.put(RenaultShare.SHARE_IMAGE_LIST, renault_share_imageList);

        validateResponse(RenaultShare.SHARE_ID, RenaultShare.SHARE_IMAGE_LIST, RenaultShare.REMARK, RenaultShare.LIKE_NUM, RenaultShare.SHARE_NUM);

        renderSuccessJson(result);
    }

    @ActionKey("/mobile/renault/share/save")
    public void save() {
        validateRequest(RenaultShare.SHARE_IMAGE_LIST, RenaultShare.REMARK);
        
        String request_user_id = getRequest_user_id();
        RenaultShare renault_share = getModel(RenaultShare.class);
        JSONObject jsonObject = getParameterJSONObject();
        
        String share_id = Util.getRandomUUID();
        renault_share.setShare_id(share_id);
        renault_share.setLike_num(0);
        renault_share.setShare_num(0);
        renault_share.setShare_user_id(request_user_id);
        
        Boolean result = RenaultShareService.instance.save(renault_share, request_user_id);
        
        if (result) {
            JSONArray jsonArray = jsonObject.getJSONArray(RenaultShare.SHARE_IMAGE_LIST);
            
            for (int i = 0; i < jsonArray.size(); i++) {
                RenaultShareImage renaultShareImage = jsonArray.getJSONObject(i).toJavaObject(RenaultShareImage.class);
                if (ValidateUtil.isNullOrEmpty(renaultShareImage.getFile_id())) {
                    throw new RuntimeException("图片不能为空");
                }
                if (ValidateUtil.isNullOrEmpty(renaultShareImage.getShare_file_sort())) {
                    throw new RuntimeException("排序不能为空");
                }
                renaultShareImage.setImage_id(Util.getRandomUUID());
                renaultShareImage.setShare_id(share_id);
                RenaultShareImageService.instance.save(renaultShareImage, request_user_id);
            }
        }
        
        renderSuccessJson(result);
    }

    //增加点赞数量接口、增加分享数量接口
    //Add by lyn 2017.11.1
    @ActionKey("/mobile/renault/share/update")
    public void update() {
        validateRequest(RenaultShare.LIKE_NUM);
        String request_user_id = getRequest_user_id();
        RenaultShare renault_share = getModel(RenaultShare.class);
        renault_share = RenaultShareService.instance.find(renault_share.getShare_id());
        renault_share.setLike_num(renault_share.getShare_num()+1);

        renault_share.setShare_num(renault_share.getShare_num()+1);//每次都加1
        renault_share.setShare_user_id(request_user_id);

        Boolean result = RenaultShareService.instance.update(renault_share,renault_share.getShare_id() ,request_user_id,renault_share.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey("/mobile/renault/share/delete")
    public void delete() {

        renderSuccessJson();
    }

}