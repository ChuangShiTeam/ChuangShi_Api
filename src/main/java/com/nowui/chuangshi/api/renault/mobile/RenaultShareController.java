package com.nowui.chuangshi.api.renault.mobile;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.file.model.File;
import com.nowui.chuangshi.api.renault.model.RenaultShare;
import com.nowui.chuangshi.api.renault.service.RenaultShareImageService;
import com.nowui.chuangshi.api.renault.service.RenaultShareService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;

import java.util.List;

@ControllerKey("/mobile/renault/share")
public class RenaultShareController extends Controller {

    //雷诺首页分享列表
    @ActionKey("/mobile/renault/share/list")
    public void list() {
        String request_app_id = getRequest_app_id();
        List<RenaultShare> renaultsharelist = RenaultShareService.instance.appList(request_app_id);

        for (RenaultShare renaultShare : renaultsharelist) {
            renaultShare.put(renaultShare.Renault_Share_Image_LIST,
                    RenaultShareImageService.instance.GetListBySahreId(renaultShare.getShare_id()));
        }

        validateResponse(RenaultShare.SHARE_ID,RenaultShare.Renault_Share_Image_LIST, File.FILE_PATH, RenaultShare.REMARK,RenaultShare.LIKE_NUM,RenaultShare.SHARE_NUM);

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