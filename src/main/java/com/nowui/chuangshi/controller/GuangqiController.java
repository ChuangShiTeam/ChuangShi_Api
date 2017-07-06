package com.nowui.chuangshi.controller;

import com.jfinal.core.ActionKey;
import com.jfinal.weixin.sdk.api.ApiConfigKit;
import com.nowui.chuangshi.constant.Url;
import com.nowui.chuangshi.model.App;
import com.nowui.chuangshi.model.GuangqiCustomer;
import com.nowui.chuangshi.model.GuangqiCustomerPrize;
import com.nowui.chuangshi.model.GuangqiPrize;
import com.nowui.chuangshi.render.ExcelRender;
import com.nowui.chuangshi.service.*;
import com.nowui.chuangshi.util.CacheUtil;
import com.nowui.chuangshi.util.DateUtil;
import com.nowui.chuangshi.util.Util;
import com.nowui.chuangshi.util.WeChatUtil;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.HorizontalAlignment;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class GuangqiController extends Controller {

    public static final String GUANGQI_PRIZE_VISIT_COUNT_BY_APP_ID_CACHE = "guangqi_prize_visit_count_by_app_id_cache";

    private final AppService appService = new AppService();
    private final HttpService httpService = new HttpService();
    private final GuangqiCustomerService guangqiCustomerService = new GuangqiCustomerService();
    private final GuangqiPrizeService guangqiPrizeService = new GuangqiPrizeService();
    private final GuangqiCustomerPrizeService guangqiCustomerPrizeService = new GuangqiCustomerPrizeService();

    @ActionKey(Url.GUANGQI_WECHAT_SHARE)
    public void share() {
        validateRequest_app_id();

        String request_app_id = getRequest_app_id();

        App app = appService.findByApp_id(request_app_id);

        String wechat_app_id = ApiConfigKit.getAppId();
        if (!wechat_app_id.equals(app.getWechat_app_id())) {
            ApiConfigKit.setThreadLocalAppId(app.getWechat_app_id());
        }

        String url = getPara("url");

        Map<String, Object> map = WeChatUtil.sign(url);


        Integer visit_count = CacheUtil.get(GUANGQI_PRIZE_VISIT_COUNT_BY_APP_ID_CACHE, request_app_id);
        if (visit_count == null) {
            visit_count = httpService.countByApp_idAndHttp_url(request_app_id, Url.GUANGQI_WECHAT_SHARE);
        }
        CacheUtil.put(GUANGQI_PRIZE_VISIT_COUNT_BY_APP_ID_CACHE, request_app_id, visit_count + 1);

        map.put("visit_count", visit_count);

        renderSuccessJson(map);
    }

    @ActionKey(Url.GUANGQI_PRIZE_DRAW)
    public void draw() {
        validateRequest_app_id();
        validate(GuangqiCustomer.CUSTOMER_ID);

        GuangqiCustomer model = getModel(GuangqiCustomer.class);
        String request_app_id = getRequest_app_id();

        GuangqiCustomer customer = guangqiCustomerService.findByCustomer_id(model.getCustomer_id());
        if (customer == null) {
            throw new RuntimeException("没有权限抽奖");
        }

        Integer customerCount = guangqiCustomerPrizeService.countByApp_idAndCustomer_id(request_app_id, customer.getCustomer_id());
        if (customerCount > 0) {
            throw new RuntimeException("重复抽奖啦");
        }

        Integer total = 0;
        GuangqiPrize defaultPrize = null;
        List<GuangqiPrize> guangqiPrizeList = guangqiPrizeService.listByApp_id(request_app_id);
        for (GuangqiPrize guangqiPrize : guangqiPrizeList) {
            if (guangqiPrize.getPrize_is_default()) {
                defaultPrize = guangqiPrize;
            } else {
                total += guangqiPrize.getPrize_probability();
            }
        }

        Random random = new Random();
        int number = random.nextInt(total) % (total + 1);
        int start = 0;
        int end = 0;
        GuangqiPrize prize = null;
        for (GuangqiPrize guangqiPrize : guangqiPrizeList) {
            if (!guangqiPrize.getPrize_is_default()) {
                end += guangqiPrize.getPrize_probability();

                if (number > start && number <= end) {
                    prize = guangqiPrize;

                    break;
                }

                start += guangqiPrize.getPrize_probability();
            }
        }

        Boolean isSave = true;
        Integer totalCount = guangqiCustomerPrizeService.countByApp_idAndPrize_id(request_app_id, prize.getPrize_id());
        if (totalCount >= prize.getPrize_quantity()) {
            isSave = false;
        }



        Integer dayCount = 0;
        if (isSave) {
            dayCount = guangqiCustomerPrizeService.countByApp_idAndPrize_idAndCustomer_prize_date(request_app_id, prize.getPrize_id(), DateUtil.getDateString(new Date()));
            if (dayCount >= prize.getPrize_limit()) {
                isSave = false;
            }
        }

        if (!isSave) {
            prize = defaultPrize;
        }

        String customer_prize_id = Util.getRandomUUID();
        String customer_prize_date = DateUtil.getDateString(new Date());
        String system_create_user_id = "";
        Boolean result = guangqiCustomerPrizeService.save(customer_prize_id, request_app_id, customer.getCustomer_id(), prize.getPrize_id(), customer_prize_date, system_create_user_id, prize.getPrize_quantity(), prize.getPrize_limit());

        if (!result) {
            throw new RuntimeException("抽奖不成功");
        }

        renderSuccessJson(prize.keep(GuangqiPrize.PRIZE_ID, GuangqiPrize.PRIZE_NAME));
    }

    @ActionKey(Url.GUANGQI_EXPORT)
    public void export() {
        List<GuangqiCustomerPrize> guangqiCustomerPrizeList = guangqiCustomerPrizeService.listByApp_id("b0f1cf1b4705403ea4e2567c7d860f33");

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
        cell.setCellValue("省份");
        cell.setCellStyle(style);
        cell = row.createCell(3);
        cell.setCellValue("城市");
        cell.setCellStyle(style);
        cell = row.createCell(4);
        cell.setCellValue("经销商");
        cell.setCellStyle(style);
        cell = row.createCell(5);
        cell.setCellValue("奖品名称");
        cell.setCellStyle(style);
        cell = row.createCell(6);
        cell.setCellValue("日期时间");
        cell.setCellStyle(style);

        for (int i = 0; i < guangqiCustomerPrizeList.size(); i++) {
            GuangqiCustomerPrize guangqiCustomerPrize = guangqiCustomerPrizeList.get(i);

            GuangqiCustomer guangqiCustomer = guangqiCustomerService.findByCustomer_id(guangqiCustomerPrize.getCustomer_id());
            GuangqiPrize guangqiPrize = guangqiPrizeService.findByPrize_id(guangqiCustomerPrize.getPrize_id());

            row = sheet.createRow(i + 1);
            cell = row.createCell(0);
            cell.setCellValue(guangqiCustomer.getCustomer_name());
            cell.setCellStyle(style);
            cell = row.createCell(1);
            cell.setCellValue(guangqiCustomer.getCustomer_phone());
            cell.setCellStyle(style);
            cell = row.createCell(2);
            cell.setCellValue(guangqiCustomer.getCustomer_province());
            cell.setCellStyle(style);
            cell = row.createCell(3);
            cell.setCellValue(guangqiCustomer.getCustomer_city());
            cell.setCellStyle(style);
            cell = row.createCell(4);
            cell.setCellValue(guangqiCustomer.getCostomer_dealer());
            cell.setCellStyle(style);
            cell = row.createCell(5);
            cell.setCellValue(guangqiPrize.getPrize_name());
            cell.setCellStyle(style);
            cell = row.createCell(6);
            cell.setCellValue(DateUtil.getDateTimeString(guangqiCustomerPrize.getSystem_create_time()));
            cell.setCellStyle(style);
        }

        render(new ExcelRender(wb, "用户信息"));
    }

}
