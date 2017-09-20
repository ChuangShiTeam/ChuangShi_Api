package com.nowui.chuangshi.api.infiniti.admin;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.infiniti.model.InfinitiMember;
import com.nowui.chuangshi.api.infiniti.model.InfinitiPrize;
import com.nowui.chuangshi.api.infiniti.service.InfinitiMemberService;
import com.nowui.chuangshi.api.infiniti.service.InfinitiPrizeService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.common.interceptor.AdminInterceptor;
import com.nowui.chuangshi.common.render.ExcelRender;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.util.DateUtil;
import com.nowui.chuangshi.util.Util;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.HorizontalAlignment;

import java.util.List;

@Before(AdminInterceptor.class)
@ControllerKey("/admin/infiniti/member")
public class InfinitiMemberController extends Controller {

    @ActionKey("/admin/infiniti/member/list")
    public void list() {
        validateRequest(InfinitiMember.MEMBER_REDEEM_CODE, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        InfinitiMember model = getModel(InfinitiMember.class);
        String request_app_id = getRequest_app_id();

        Integer resultCount = InfinitiMemberService.instance.adminCount(request_app_id, model.getMember_redeem_code());
        List<InfinitiMember> resultList = InfinitiMemberService.instance.adminList(request_app_id, model.getMember_redeem_code(), getM(), getN());

        validateResponse(InfinitiMember.MEMBER_ID, InfinitiMember.MEMBER_NAME, InfinitiMember.MEMBER_MOBILE, InfinitiMember.MEMBER_ADDRESS, InfinitiMember.MEMBER_REDEEM_CODE, InfinitiMember.SYSTEM_CREATE_TIME, InfinitiMember.SYSTEM_VERSION);

        renderSuccessJson(resultCount, resultList);
    }

    @ActionKey("/admin/infiniti/member/find")
    public void find() {
        validateRequest(InfinitiMember.MEMBER_ID);

        InfinitiMember model = getModel(InfinitiMember.class);

        InfinitiMember result = InfinitiMemberService.instance.find(model.getMember_id());

        validateResponse(InfinitiMember.MEMBER_NAME, InfinitiMember.MEMBER_MOBILE, InfinitiMember.MEMBER_ADDRESS, InfinitiMember.MEMBER_REDEEM_CODE, InfinitiMember.MEMBER_REDEEM_CODE_IS_EXCHANGE, InfinitiMember.SYSTEM_VERSION);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/infiniti/member/save")
    public void save() {
        validateRequest(InfinitiMember.MEMBER_NAME, InfinitiMember.MEMBER_MOBILE, InfinitiMember.MEMBER_ADDRESS, InfinitiMember.MEMBER_REDEEM_CODE, InfinitiMember.MEMBER_REDEEM_CODE_IS_EXCHANGE);

        InfinitiMember model = getModel(InfinitiMember.class);
        model.setMember_id(Util.getRandomUUID());
        String request_user_id = getRequest_user_id();

        Boolean result = InfinitiMemberService.instance.save(model, request_user_id);

        renderSuccessJson(result);
    }

    @ActionKey("/admin/infiniti/member/update")
    public void update() {
        validateRequest(InfinitiMember.MEMBER_ID, InfinitiMember.MEMBER_NAME, InfinitiMember.MEMBER_MOBILE, InfinitiMember.MEMBER_ADDRESS, InfinitiMember.MEMBER_REDEEM_CODE, InfinitiMember.MEMBER_REDEEM_CODE_IS_EXCHANGE, InfinitiMember.SYSTEM_VERSION);

        InfinitiMember model = getModel(InfinitiMember.class);
        String request_user_id = getRequest_user_id();

        Boolean result = InfinitiMemberService.instance.update(model, model.getMember_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey("/admin/infiniti/member/delete")
    public void delete() {
        validateRequest(InfinitiMember.MEMBER_ID, InfinitiMember.SYSTEM_VERSION);

        InfinitiMember model = getModel(InfinitiMember.class);
        String request_user_id = getRequest_user_id();

        Boolean result = InfinitiMemberService.instance.delete(model.getMember_id(), request_user_id, model.getSystem_version());

        renderSuccessJson(result);
    }

    @ActionKey("/admin/infiniti/member/export")
    public void export() {
        List<InfinitiMember> infinitiMemberList = InfinitiMemberService.instance.appList("080155de94764857b1790ddff65b8113");

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
        cell.setCellValue("客户地址");
        cell.setCellStyle(style);
        cell = row.createCell(3);
        cell.setCellValue("奖品名称");
        cell.setCellStyle(style);
        cell = row.createCell(4);
        cell.setCellValue("兑换码");
        cell.setCellStyle(style);
        cell = row.createCell(5);
        cell.setCellValue("日期时间");
        cell.setCellStyle(style);

        int i = 0;
        for (InfinitiMember infinitiMember : infinitiMemberList) {
            if (!infinitiMember.getMember_name().equals("")) {
                row = sheet.createRow(i + 1);
                cell = row.createCell(0);
                cell.setCellValue(infinitiMember.getMember_name());
                cell.setCellStyle(style);
                cell = row.createCell(1);
                cell.setCellValue(infinitiMember.getMember_mobile());
                cell.setCellStyle(style);
                cell = row.createCell(2);
                cell.setCellValue(infinitiMember.getMember_address());
                cell.setCellStyle(style);
                cell.setCellStyle(style);
                cell = row.createCell(3);
                cell.setCellValue(infinitiMember.getMember_redeem_code());
                cell.setCellStyle(style);
                cell = row.createCell(4);
                cell.setCellValue(DateUtil.getDateTimeString(infinitiMember.getSystem_create_time()));
                cell.setCellStyle(style);

                i++;
            }
        }

        render(new ExcelRender(wb, "客户信息"));
    }

}