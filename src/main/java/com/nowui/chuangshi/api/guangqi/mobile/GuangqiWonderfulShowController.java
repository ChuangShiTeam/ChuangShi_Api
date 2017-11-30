package com.nowui.chuangshi.api.guangqi.mobile;

import java.util.List;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.file.model.File;
import com.nowui.chuangshi.api.file.service.FileService;
import com.nowui.chuangshi.api.guangqi.model.GuangqiWonderfulShow;
import com.nowui.chuangshi.api.guangqi.service.GuangqiWonderfulShowService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;

@ControllerKey("/mobile/guangqi/wonderful/show")
public class GuangqiWonderfulShowController extends Controller {

    @ActionKey("/mobile/guangqi/wonderful/show/list")
    public void list() {
    	String request_app_id = getRequest_app_id();
    	
    	List<GuangqiWonderfulShow> resultList = GuangqiWonderfulShowService.instance.appList(request_app_id);
    	
    	for (GuangqiWonderfulShow result : resultList) {
    		File file = FileService.instance.getFile(result.getWonderful_show_cover_picture());
    		
    		result.put(File.FILE_PATH, file != null ? file.getFile_path() : null);
    		result.put(File.FILE_ORIGINAL_PATH, file != null ? file.getFile_original_path() : null);
    	}
    	validateResponse(GuangqiWonderfulShow.WONDERFUL_SHOW_ID, File.FILE_PATH, File.FILE_ORIGINAL_PATH);
    	
        renderSuccessJson(resultList);
    }

    @ActionKey("/mobile/guangqi/wonderful/show/find")
    public void find() {
    	
    	validateRequest(GuangqiWonderfulShow.WONDERFUL_SHOW_ID);
		
    	GuangqiWonderfulShow model = getModel(GuangqiWonderfulShow.class);
		
    	GuangqiWonderfulShow result = GuangqiWonderfulShowService.instance.find(model.getWonderful_show_id());
		
    	File file = FileService.instance.getFile(result.getWonderful_show_cover_picture());
    	
    	result.put(File.FILE_PATH, file != null ? file.getFile_path() : null);
		result.put(File.FILE_ORIGINAL_PATH, file != null ? file.getFile_original_path() : null);
		
		validateResponse(GuangqiWonderfulShow.WONDERFUL_SHOW_VIDEO, File.FILE_PATH, File.FILE_ORIGINAL_PATH);
		
        renderSuccessJson(result);

        renderSuccessJson();
    }

}