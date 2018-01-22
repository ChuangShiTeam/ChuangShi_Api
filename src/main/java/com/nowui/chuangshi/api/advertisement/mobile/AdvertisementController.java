package com.nowui.chuangshi.api.advertisement.mobile;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.advertisement.model.Advertisement;
import com.nowui.chuangshi.api.advertisement.service.AdvertisementService;
import com.nowui.chuangshi.api.file.model.File;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import java.util.List;

@ControllerKey("/mobile/advertisement")
public class AdvertisementController extends Controller {

    @ActionKey("/mobile/advertisement/list")
    public void list() {
        String request_app_id = getRequest_app_id();

        List<Advertisement> advertisementList = AdvertisementService.instance.appList(request_app_id);

        validateResponse(Advertisement.ADVERTISEMENT_ID, Advertisement.ADVERTISEMENT_CATEGORY_CODE, Advertisement.ADVERTISEMENT_IS_FLOAT, File.FILE_PATH, File.FILE_ORIGINAL_PATH, Advertisement.ADVERTISEMENT_LINK);

        renderSuccessJson(advertisementList);
    }

    @ActionKey("/mobile/advertisement/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/advertisement/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/advertisement/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/advertisement/delete")
    public void delete() {

        renderSuccessJson();
    }

}