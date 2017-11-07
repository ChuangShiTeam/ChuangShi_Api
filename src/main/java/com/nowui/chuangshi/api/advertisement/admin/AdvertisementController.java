package com.nowui.chuangshi.api.advertisement.admin;

import java.util.ArrayList;
import java.util.List;


import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.advertisement.model.Advertisement;
import com.nowui.chuangshi.api.advertisement.service.AdvertisementService;
import com.nowui.chuangshi.api.file.service.FileService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;

import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.util.Util;


@ControllerKey("/admin/advertisement")
public class AdvertisementController extends Controller {

    @ActionKey("/admin/advertisement/list")
    public void list() {
        validateRequest(Advertisement.ADVERTISEMENT_CATEGORY_CODE, Advertisement.ADVERTISEMENT_TITLE, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        Advertisement model = getModel(Advertisement.class);
        String request_app_id = getRequest_app_id();

        Integer resultCount = AdvertisementService.instance.adminCount(request_app_id, model.getAdvertisement_category_code(), model.getAdvertisement_title());
        List<Advertisement> advertisementList = AdvertisementService.instance.adminList(request_app_id, model.getAdvertisement_category_code(), model.getAdvertisement_title(), getM(), getN());
        
        List<Advertisement> resultList = new ArrayList<Advertisement>();
        for (Advertisement advertisement : advertisementList) {
            advertisement.put(Advertisement.ADVERTISEMENT_IMAGE_FILE, FileService.instance.getFile(advertisement.getAdvertisement_image()));
            resultList.add(advertisement);
        }

        validateResponse(Advertisement.ADVERTISEMENT_ID, Advertisement.ADVERTISEMENT_IMAGE_FILE, Advertisement.ADVERTISEMENT_CATEGORY_CODE, Advertisement.ADVERTISEMENT_TITLE, Advertisement.ADVERTISEMENT_POSITION, Advertisement.ADVERTISEMENT_IMAGE, Advertisement.ADVERTISEMENT_LINK, Advertisement.ADVERTISEMENT_IS_FLOAT, Advertisement.ADVERTISEMENT_SORT, Advertisement.SYSTEM_VERSION);

        renderSuccessJson(resultCount, resultList);
    }

    @ActionKey("/admin/advertisement/find")
    public void find() {
        validateRequest(Advertisement.ADVERTISEMENT_ID);

        Advertisement model = getModel(Advertisement.class);

        Advertisement result = AdvertisementService.instance.find(model.getAdvertisement_id());
        
        result.put( Advertisement.ADVERTISEMENT_IMAGE_FILE, FileService.instance.getFile(result.getAdvertisement_image()));

        validateResponse(Advertisement.ADVERTISEMENT_CATEGORY_CODE,  Advertisement.ADVERTISEMENT_IMAGE_FILE, Advertisement.ADVERTISEMENT_TITLE, Advertisement.ADVERTISEMENT_POSITION, Advertisement.ADVERTISEMENT_IMAGE, Advertisement.ADVERTISEMENT_LINK, Advertisement.ADVERTISEMENT_IS_FLOAT, Advertisement.ADVERTISEMENT_SORT, Advertisement.SYSTEM_VERSION);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/advertisement/save")
    public void save() {
        validateRequest(Advertisement.ADVERTISEMENT_CATEGORY_CODE, Advertisement.ADVERTISEMENT_TITLE, Advertisement.ADVERTISEMENT_POSITION, Advertisement.ADVERTISEMENT_IMAGE, Advertisement.ADVERTISEMENT_LINK, Advertisement.ADVERTISEMENT_IS_FLOAT, Advertisement.ADVERTISEMENT_SORT);

        Advertisement model = getModel(Advertisement.class);
        model.setAdvertisement_id(Util.getRandomUUID());
        String request_user_id = getRequest_user_id();

        Boolean result = AdvertisementService.instance.save(model, request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/advertisement/update")
    public void update() {
        validateRequest(Advertisement.ADVERTISEMENT_ID, Advertisement.ADVERTISEMENT_CATEGORY_CODE, Advertisement.ADVERTISEMENT_TITLE, Advertisement.ADVERTISEMENT_POSITION, Advertisement.ADVERTISEMENT_IMAGE, Advertisement.ADVERTISEMENT_LINK, Advertisement.ADVERTISEMENT_IS_FLOAT, Advertisement.ADVERTISEMENT_SORT, Advertisement.SYSTEM_VERSION);

        Advertisement model = getModel(Advertisement.class);
        String request_user_id = getRequest_user_id();

        Boolean result = AdvertisementService.instance.update(model, model.getAdvertisement_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey("/admin/advertisement/delete")
    public void delete() {
        validateRequest(Advertisement.ADVERTISEMENT_ID, Advertisement.SYSTEM_VERSION);

        Advertisement model = getModel(Advertisement.class);
        String request_user_id = getRequest_user_id();

        Boolean result = AdvertisementService.instance.delete(model.getAdvertisement_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

}