package com.nowui.chuangshi.api.renault.mobile;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.file.model.File;
import com.nowui.chuangshi.api.renault.model.RenaultShare;
import com.nowui.chuangshi.api.renault.model.RenaultShareImage;
import com.nowui.chuangshi.api.renault.service.RenaultShareImageService;
import com.nowui.chuangshi.api.renault.service.RenaultShareService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.constant.Constant;

import java.util.List;

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

    @ActionKey("/mobile/renault/share/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/renault/share/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/renault/share/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/renault/share/delete")
    public void delete() {

        renderSuccessJson();
    }

}