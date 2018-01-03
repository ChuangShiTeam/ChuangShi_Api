package com.nowui.chuangshi.api.guangqi.admin;


import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.HorizontalAlignment;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.guangqi.model.GuangqiNewYearCustomer;
import com.nowui.chuangshi.api.guangqi.model.GuangqiNewYearCustomerPrize;
import com.nowui.chuangshi.api.guangqi.model.GuangqiNewYearPrize;
import com.nowui.chuangshi.api.guangqi.service.GuangqiNewYearCustomerPrizeService;
import com.nowui.chuangshi.api.guangqi.service.GuangqiNewYearCustomerService;
import com.nowui.chuangshi.api.guangqi.service.GuangqiNewYearPrizeService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.common.render.ExcelRender;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.util.DateUtil;
import com.nowui.chuangshi.util.Util;

@ControllerKey("/admin/guangqi/new/year/customer")
public class GuangqiNewYearCustomerController extends Controller {

    @ActionKey("/admin/guangqi/new/year/customer/list")
    public void list() {
        validateRequest(GuangqiNewYearCustomer.NEW_YEAR_CUSTOMER_CAR_MODEL, GuangqiNewYearCustomer.NEW_YEAR_CUSTOMER_NAME, GuangqiNewYearCustomer.NEW_YEAR_CUSTOMER_PHONE, GuangqiNewYearCustomer.NEW_YEAR_CUSTOMER_PROVINCE, GuangqiNewYearCustomer.NEW_YEAR_CUSTOMER_CITY, GuangqiNewYearCustomer.NEW_YEAR_CUSTOMER_DEALER, GuangqiNewYearCustomer.NEW_YEAR_CUSTOMER_FROM, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        GuangqiNewYearCustomer model = getModel(GuangqiNewYearCustomer.class);
        String request_app_id = getRequest_app_id();

        Integer resultCount = GuangqiNewYearCustomerService.instance.adminCount(request_app_id, model.getNew_year_customer_car_model(), model.getNew_year_customer_name(), model.getNew_year_customer_phone(), model.getNew_year_customer_province(), model.getNew_year_customer_city(), model.getNew_year_customer_dealer(), model.getNew_year_customer_from());
        List<GuangqiNewYearCustomer> resultList = GuangqiNewYearCustomerService.instance.adminList(request_app_id, model.getNew_year_customer_car_model(), model.getNew_year_customer_name(), model.getNew_year_customer_phone(), model.getNew_year_customer_province(), model.getNew_year_customer_city(), model.getNew_year_customer_dealer(), model.getNew_year_customer_from(), getM(), getN());

        for (GuangqiNewYearCustomer result : resultList) {
        	GuangqiNewYearCustomerPrize guangqiNewYearCustomerPrize = GuangqiNewYearCustomerPrizeService.instance.customerFind(request_app_id, result.getNew_year_customer_id());
        	
        	if (guangqiNewYearCustomerPrize != null) {
        		result.put(GuangqiNewYearPrize.NEW_YEAR_PRIZE_NAME, GuangqiNewYearPrizeService.instance.find(guangqiNewYearCustomerPrize.getNew_year_prize_id()).getNew_year_prize_name());
        	}
        }
        validateResponse(GuangqiNewYearCustomer.NEW_YEAR_CUSTOMER_ID, GuangqiNewYearPrize.NEW_YEAR_PRIZE_NAME, GuangqiNewYearCustomer.NEW_YEAR_CUSTOMER_CAR_MODEL, GuangqiNewYearCustomer.NEW_YEAR_CUSTOMER_NAME, GuangqiNewYearCustomer.NEW_YEAR_CUSTOMER_PHONE, GuangqiNewYearCustomer.NEW_YEAR_CUSTOMER_PROVINCE, GuangqiNewYearCustomer.NEW_YEAR_CUSTOMER_CITY, GuangqiNewYearCustomer.NEW_YEAR_CUSTOMER_DEALER, GuangqiNewYearCustomer.NEW_YEAR_CUSTOMER_FROM, GuangqiNewYearCustomer.SYSTEM_VERSION);

        renderSuccessJson(resultCount, resultList);
    }

    @ActionKey("/admin/guangqi/new/year/customer/find")
    public void find() {
        validateRequest(GuangqiNewYearCustomer.NEW_YEAR_CUSTOMER_ID);

        GuangqiNewYearCustomer model = getModel(GuangqiNewYearCustomer.class);

        GuangqiNewYearCustomer result = GuangqiNewYearCustomerService.instance.find(model.getNew_year_customer_id());

        validateResponse(GuangqiNewYearCustomer.NEW_YEAR_CUSTOMER_CAR_MODEL, GuangqiNewYearCustomer.NEW_YEAR_CUSTOMER_NAME, GuangqiNewYearCustomer.NEW_YEAR_CUSTOMER_PHONE, GuangqiNewYearCustomer.NEW_YEAR_CUSTOMER_PROVINCE, GuangqiNewYearCustomer.NEW_YEAR_CUSTOMER_CITY, GuangqiNewYearCustomer.NEW_YEAR_CUSTOMER_DEALER, GuangqiNewYearCustomer.NEW_YEAR_CUSTOMER_FROM, GuangqiNewYearCustomer.SYSTEM_VERSION);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/guangqi/new/year/customer/save")
    public void save() {
        validateRequest(GuangqiNewYearCustomer.NEW_YEAR_CUSTOMER_CAR_MODEL, GuangqiNewYearCustomer.NEW_YEAR_CUSTOMER_NAME, GuangqiNewYearCustomer.NEW_YEAR_CUSTOMER_PHONE, GuangqiNewYearCustomer.NEW_YEAR_CUSTOMER_PROVINCE, GuangqiNewYearCustomer.NEW_YEAR_CUSTOMER_CITY, GuangqiNewYearCustomer.NEW_YEAR_CUSTOMER_DEALER, GuangqiNewYearCustomer.NEW_YEAR_CUSTOMER_FROM);

        GuangqiNewYearCustomer model = getModel(GuangqiNewYearCustomer.class);
        model.setNew_year_customer_id(Util.getRandomUUID());
        String request_user_id = getRequest_user_id();

        Boolean result = GuangqiNewYearCustomerService.instance.save(model, request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/guangqi/new/year/customer/update")
    public void update() {
        validateRequest(GuangqiNewYearCustomer.NEW_YEAR_CUSTOMER_ID, GuangqiNewYearCustomer.NEW_YEAR_CUSTOMER_CAR_MODEL, GuangqiNewYearCustomer.NEW_YEAR_CUSTOMER_NAME, GuangqiNewYearCustomer.NEW_YEAR_CUSTOMER_PHONE, GuangqiNewYearCustomer.NEW_YEAR_CUSTOMER_PROVINCE, GuangqiNewYearCustomer.NEW_YEAR_CUSTOMER_CITY, GuangqiNewYearCustomer.NEW_YEAR_CUSTOMER_DEALER, GuangqiNewYearCustomer.NEW_YEAR_CUSTOMER_FROM, GuangqiNewYearCustomer.SYSTEM_VERSION);

        GuangqiNewYearCustomer model = getModel(GuangqiNewYearCustomer.class);
        String request_user_id = getRequest_user_id();

        Boolean result = GuangqiNewYearCustomerService.instance.update(model, model.getNew_year_customer_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey("/admin/guangqi/new/year/customer/delete")
    public void delete() {
        validateRequest(GuangqiNewYearCustomer.NEW_YEAR_CUSTOMER_ID, GuangqiNewYearCustomer.SYSTEM_VERSION);

        GuangqiNewYearCustomer model = getModel(GuangqiNewYearCustomer.class);
        String request_user_id = getRequest_user_id();

        Boolean result = GuangqiNewYearCustomerService.instance.delete(model.getNew_year_customer_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }
    
    @ActionKey("/admin/guangqi/new/year/customer/all/export")
    public void allExport() {
        List<GuangqiNewYearCustomerPrize> guangqiNewYearCustomerPrizeList = GuangqiNewYearCustomerPrizeService.instance.allList();

        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);

        HSSFSheet sheet = wb.createSheet("客户抽奖信息汇总");

        HSSFRow row = sheet.createRow(0);
        HSSFCell cell = row.createCell(0);
        cell.setCellValue("客户姓名");
        cell.setCellStyle(style);
        cell = row.createCell(1);
        cell.setCellValue("手机号码");
        cell.setCellStyle(style);
        cell = row.createCell(2);
        cell.setCellValue("车型");
        cell.setCellStyle(style);
        cell = row.createCell(3);
        cell.setCellValue("来源");
        cell.setCellStyle(style);
        cell = row.createCell(4);
        cell.setCellValue("省");
        cell.setCellStyle(style);
        cell = row.createCell(5);
        cell.setCellValue("市");
        cell.setCellStyle(style);
        cell = row.createCell(6);
        cell.setCellValue("经销商");
        cell.setCellStyle(style);
        cell = row.createCell(7);
        cell.setCellValue("奖品");
        cell.setCellStyle(style);
        cell = row.createCell(8);
        cell.setCellValue("抽奖时间");
        cell.setCellStyle(style);

        for (int i = 0; i < guangqiNewYearCustomerPrizeList.size(); i++) {
            GuangqiNewYearCustomerPrize guangqiNewYearCustomerPrize = guangqiNewYearCustomerPrizeList.get(i);

            GuangqiNewYearCustomer guangqiNewYearCustomer = GuangqiNewYearCustomerService.instance.find(guangqiNewYearCustomerPrize.getNew_year_customer_id());
            GuangqiNewYearPrize guangqiNewYearPrize = GuangqiNewYearPrizeService.instance.find(guangqiNewYearCustomerPrize.getNew_year_prize_id());
            
            if (guangqiNewYearCustomer == null || guangqiNewYearPrize == null) {
            	continue;
            }

            row = sheet.createRow(i + 1);
            cell = row.createCell(0);
            cell.setCellValue(guangqiNewYearCustomer.getNew_year_customer_name());
            cell.setCellStyle(style);
            cell = row.createCell(1);
            cell.setCellValue(guangqiNewYearCustomer.getNew_year_customer_phone());
            cell.setCellStyle(style);
            cell = row.createCell(2);
            cell.setCellValue(guangqiNewYearCustomer.getNew_year_customer_car_model());
            cell.setCellStyle(style);
            cell = row.createCell(3);
            cell.setCellValue(guangqiNewYearCustomer.getNew_year_customer_from());
            cell.setCellStyle(style);
            cell = row.createCell(4);
            cell.setCellValue(guangqiNewYearCustomer.getNew_year_customer_province());
            cell.setCellStyle(style);
            cell = row.createCell(5);
            cell.setCellValue(guangqiNewYearCustomer.getNew_year_customer_city());
            cell.setCellStyle(style);
            cell = row.createCell(6);
            cell.setCellValue(guangqiNewYearCustomer.getNew_year_customer_dealer());
            cell.setCellStyle(style);
            cell = row.createCell(7);
            cell.setCellValue(guangqiNewYearPrize.getNew_year_prize_name());
            cell.setCellStyle(style);
            cell = row.createCell(8);
            cell.setCellValue(DateUtil.getDateTimeString(guangqiNewYearCustomerPrize.getSystem_create_time()));
            cell.setCellStyle(style);
        }

        render(new ExcelRender(wb, "客户抽奖信息"));
    }

}