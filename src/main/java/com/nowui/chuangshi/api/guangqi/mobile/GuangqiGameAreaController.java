package com.nowui.chuangshi.api.guangqi.mobile;

import java.util.List;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.file.model.File;
import com.nowui.chuangshi.api.file.service.FileService;
import com.nowui.chuangshi.api.guangqi.model.GuangqiGameArea;
import com.nowui.chuangshi.api.guangqi.model.GuangqiGameAreaDetail;
import com.nowui.chuangshi.api.guangqi.service.GuangqiGameAreaDetailService;
import com.nowui.chuangshi.api.guangqi.service.GuangqiGameAreaService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;

@ControllerKey("/mobile/guangqi/game/area")
public class GuangqiGameAreaController extends Controller {

    @ActionKey("/mobile/guangqi/game/area/list")
    public void list() {
    	
    	String request_app_id = getRequest_app_id();
    	
    	List<GuangqiGameArea> resultList = GuangqiGameAreaService.instance.appList(request_app_id);
    	
    	for (GuangqiGameArea result : resultList) {
    		File file = FileService.instance.getFile(result.getGame_area_cover_picture());
    		
    		result.put(File.FILE_PATH, file != null ? file.getFile_path() : null);
    		result.put(File.FILE_ORIGINAL_PATH, file != null ? file.getFile_original_path() : null);
    	}
    	validateResponse(GuangqiGameArea.GAME_AREA_ID, GuangqiGameArea.GAME_AREA_NAME, File.FILE_PATH, File.FILE_ORIGINAL_PATH);
    	
        renderSuccessJson(resultList);
    }

    @ActionKey("/mobile/guangqi/game/area/find")
    public void find() {
    	
		validateRequest(GuangqiGameArea.GAME_AREA_ID);
		
		GuangqiGameArea model = getModel(GuangqiGameArea.class);
		
		List<GuangqiGameAreaDetail> resultList = GuangqiGameAreaDetailService.instance.gameAreaList(model.getGame_area_id());
		
		for (GuangqiGameAreaDetail result : resultList) {
    		File file = FileService.instance.getFile(result.getGame_area_detail_image());
    		
    		result.put(File.FILE_PATH, file != null ? file.getFile_path() : null);
    		result.put(File.FILE_ORIGINAL_PATH, file != null ? file.getFile_original_path() : null);
    	}
		
		validateResponse(GuangqiGameAreaDetail.GAME_AREA_DETAIL_TYPE, GuangqiGameAreaDetail.GAME_AREA_DETAIL_VIDEO, File.FILE_PATH, File.FILE_ORIGINAL_PATH);
		
        renderSuccessJson(resultList);
    }

}