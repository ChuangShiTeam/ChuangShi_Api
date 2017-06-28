package com.nowui.chuangshi.controller;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.constant.Url;
import com.nowui.chuangshi.model.FeijiuFastCustomer;
import com.nowui.chuangshi.model.FeijiuRecommendCustomer;
import com.nowui.chuangshi.render.ExcelRender;
import com.nowui.chuangshi.service.FeijiuFastCustomerService;
import com.nowui.chuangshi.service.FeijiuRecommendCustomerService;
import com.nowui.chuangshi.util.DateUtil;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.HorizontalAlignment;

import java.util.List;

public class FeijiuController extends Controller {

    private final FeijiuFastCustomerService feijiuFastCustomerService = new FeijiuFastCustomerService();
    private final FeijiuRecommendCustomerService feijiuRecommendCustomerService = new FeijiuRecommendCustomerService();

    @ActionKey(Url.FEIJIU_FAST_EXPORT)
    public void fastExport() {
        String request_app_id = getRequest_app_id();
        String request_http_id = getRequest_http_id();
        String request_user_id = getRequest_user_id();

        List<FeijiuFastCustomer> feijiuFastCustomerList = feijiuFastCustomerService.listByApp_id("d49579df8f8342699657335868f90561", request_app_id, request_http_id, request_user_id);

        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);

        HSSFSheet sheet = wb.createSheet("数据汇总");

        HSSFRow row = sheet.createRow(0);
        HSSFCell cell = row.createCell(0);
        cell.setCellValue("客户姓名");
        cell.setCellStyle(style);
        cell = row.createCell(1);
        cell.setCellValue("手机号码");
        cell.setCellStyle(style);
        cell = row.createCell(2);
        cell.setCellValue("出生日期");
        cell.setCellStyle(style);
        cell = row.createCell(3);
        cell.setCellValue("所在城市");
        cell.setCellStyle(style);
        cell = row.createCell(4);
        cell.setCellValue("性别");
        cell.setCellStyle(style);
        cell = row.createCell(5);
        cell.setCellValue("身份证号");
        cell.setCellStyle(style);
        cell = row.createCell(6);
        cell.setCellValue("借款金额");
        cell.setCellStyle(style);
        cell = row.createCell(7);
        cell.setCellValue("名下有房");
        cell.setCellStyle(style);
        cell = row.createCell(8);
        cell.setCellValue("名下有车");
        cell.setCellStyle(style);
        cell = row.createCell(9);
        cell.setCellValue("有信用卡");
        cell.setCellStyle(style);
        cell = row.createCell(10);
        cell.setCellValue("有寿险保单");
        cell.setCellStyle(style);
        cell = row.createCell(11);
        cell.setCellValue("有微粒贷");
        cell.setCellStyle(style);
        cell = row.createCell(12);
        cell.setCellValue("有公积金");
        cell.setCellStyle(style);
        cell = row.createCell(13);
        cell.setCellValue("申请时间");
        cell.setCellStyle(style);

        for (int i = 0; i < feijiuFastCustomerList.size(); i++) {
            FeijiuFastCustomer feijiuFastCustomer = feijiuFastCustomerList.get(i);

            row = sheet.createRow(i + 1);
            cell = row.createCell(0);
            cell.setCellValue(feijiuFastCustomer.getCustomer_name());
            cell.setCellStyle(style);
            cell = row.createCell(1);
            cell.setCellValue(feijiuFastCustomer.getCustomer_phone());
            cell.setCellStyle(style);
            cell = row.createCell(2);
            cell.setCellValue(feijiuFastCustomer.getCustomer_birthday());
            cell.setCellStyle(style);
            cell = row.createCell(3);
            cell.setCellValue(feijiuFastCustomer.getCustomer_city());
            cell.setCellStyle(style);
            cell = row.createCell(4);
            cell.setCellValue(feijiuFastCustomer.getCustomer_sex());
            cell.setCellStyle(style);
            cell = row.createCell(5);
            cell.setCellValue(feijiuFastCustomer.getCustomer_id_card());
            cell.setCellStyle(style);
            cell = row.createCell(6);
            cell.setCellValue(feijiuFastCustomer.getCustomer_money());
            cell.setCellStyle(style);
            cell = row.createCell(7);
            cell.setCellValue(feijiuFastCustomer.getCustomer_fang());
            cell.setCellStyle(style);
            cell = row.createCell(8);
            cell.setCellValue(feijiuFastCustomer.getCustomer_che());
            cell.setCellStyle(style);
            cell = row.createCell(9);
            cell.setCellValue(feijiuFastCustomer.getCustomer_xin());
            cell.setCellStyle(style);
            cell = row.createCell(10);
            cell.setCellValue(feijiuFastCustomer.getCustomer_shou());
            cell.setCellStyle(style);
            cell = row.createCell(11);
            cell.setCellValue(feijiuFastCustomer.getCustomer_dai());
            cell.setCellStyle(style);
            cell = row.createCell(12);
            cell.setCellValue(feijiuFastCustomer.getCustomer_gong());
            cell.setCellStyle(style);
            cell = row.createCell(13);
            cell.setCellValue(DateUtil.getDateTimeString(feijiuFastCustomer.getSystem_create_time()));
            cell.setCellStyle(style);
        }

        render(new ExcelRender(wb, "申请资料"));
    }

    @ActionKey(Url.FEIJIU_RECOMMEND_EXPORT)
    public void recommendExport() {
        String request_app_id = getRequest_app_id();
        String request_http_id = getRequest_http_id();
        String request_user_id = getRequest_user_id();

        List<FeijiuRecommendCustomer> feijiuRecommendCustomerList = feijiuRecommendCustomerService.listByApp_id("d49579df8f8342699657335868f90561", request_app_id, request_http_id, request_user_id);

        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);

        HSSFSheet sheet = wb.createSheet("数据汇总");

        HSSFRow row = sheet.createRow(0);
        HSSFCell cell = row.createCell(0);
        cell.setCellValue("客户姓名");
        cell.setCellStyle(style);
        cell = row.createCell(1);
        cell.setCellValue("手机号码");
        cell.setCellStyle(style);
        cell = row.createCell(2);
        cell.setCellValue("所在城市");
        cell.setCellStyle(style);
        cell = row.createCell(3);
        cell.setCellValue("申请时间");
        cell.setCellStyle(style);

        for (int i = 0; i < feijiuRecommendCustomerList.size(); i++) {
            FeijiuRecommendCustomer feijiuRecommendCustomer = feijiuRecommendCustomerList.get(i);

            row = sheet.createRow(i + 1);
            cell = row.createCell(0);
            cell.setCellValue(feijiuRecommendCustomer.getCustomer_name());
            cell.setCellStyle(style);
            cell = row.createCell(1);
            cell.setCellValue(feijiuRecommendCustomer.getCustomer_phone());
            cell.setCellStyle(style);
            cell = row.createCell(2);
            cell.setCellValue(feijiuRecommendCustomer.getCustomer_city());
            cell.setCellStyle(style);
            cell = row.createCell(3);
            cell.setCellValue(DateUtil.getDateTimeString(feijiuRecommendCustomer.getSystem_create_time()));
            cell.setCellStyle(style);
        }

        render(new ExcelRender(wb, "申请资料"));
    }

}
