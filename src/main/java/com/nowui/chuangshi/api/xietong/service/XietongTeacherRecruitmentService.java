package com.nowui.chuangshi.api.xietong.service;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.HorizontalAlignment;

import com.nowui.chuangshi.api.xietong.dao.XietongTeacherRecruitmentDao;
import com.nowui.chuangshi.api.xietong.model.XietongTeacherRecruitment;
import com.nowui.chuangshi.common.render.ExcelRender;
import com.nowui.chuangshi.common.service.Service;
import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.util.CacheUtil;
import com.nowui.chuangshi.util.DateUtil;

public class XietongTeacherRecruitmentService extends Service {

    public static final XietongTeacherRecruitmentService instance = new XietongTeacherRecruitmentService();
    private final String XIETONG_TEACHER_RECRUITMENT_ITEM_CACHE = "xietong_teacher_recruitment_item_cache";
    private final XietongTeacherRecruitmentDao xietongTeacherRecruitmentDao = new XietongTeacherRecruitmentDao();

    public Integer adminCount(String app_id, String teacher_recruitment_name) {
        Cnd cnd = new Cnd();
        cnd.where(XietongTeacherRecruitment.SYSTEM_STATUS, true);
        cnd.and(XietongTeacherRecruitment.APP_ID, app_id);
        cnd.andAllowEmpty(XietongTeacherRecruitment.TEACHER_RECRUITMENT_NAME, teacher_recruitment_name);

        Integer count = xietongTeacherRecruitmentDao.count(cnd);
        return count;
    }

    public List<XietongTeacherRecruitment> adminList(String app_id, String teacher_recruitment_name, Integer m, Integer n) {
        Cnd cnd = new Cnd();
        cnd.where(XietongTeacherRecruitment.SYSTEM_STATUS, true);
        cnd.and(XietongTeacherRecruitment.APP_ID, app_id);
        cnd.andAllowEmpty(XietongTeacherRecruitment.TEACHER_RECRUITMENT_NAME, teacher_recruitment_name);
        cnd.desc(XietongTeacherRecruitment.SYSTEM_CREATE_TIME);
        cnd.paginate(m, n);

        List<XietongTeacherRecruitment> xietong_teacher_recruitmentList = xietongTeacherRecruitmentDao.primaryKeyList(cnd);
        for (XietongTeacherRecruitment xietong_teacher_recruitment : xietong_teacher_recruitmentList) {
            xietong_teacher_recruitment.put(find(xietong_teacher_recruitment.getTeacher_recruitment_id()));
        }
        return xietong_teacher_recruitmentList;
    }
    
    public List<XietongTeacherRecruitment> allList() {
        Cnd cnd = new Cnd();
        cnd.where(XietongTeacherRecruitment.SYSTEM_STATUS, true);
        cnd.desc(XietongTeacherRecruitment.SYSTEM_CREATE_TIME);
        
        List<XietongTeacherRecruitment> xietong_teacher_recruitmentList = xietongTeacherRecruitmentDao.primaryKeyList(cnd);
        for (XietongTeacherRecruitment xietong_teacher_recruitment : xietong_teacher_recruitmentList) {
            xietong_teacher_recruitment.put(find(xietong_teacher_recruitment.getTeacher_recruitment_id()));
        }
        return xietong_teacher_recruitmentList;
    }

    public XietongTeacherRecruitment find(String teacher_recruitment_id) {
        XietongTeacherRecruitment xietong_teacher_recruitment = CacheUtil.get(XIETONG_TEACHER_RECRUITMENT_ITEM_CACHE, teacher_recruitment_id);

        if (xietong_teacher_recruitment == null) {
            xietong_teacher_recruitment = xietongTeacherRecruitmentDao.find(teacher_recruitment_id);

            CacheUtil.put(XIETONG_TEACHER_RECRUITMENT_ITEM_CACHE, teacher_recruitment_id, xietong_teacher_recruitment);
        }

        return xietong_teacher_recruitment;
    }

    public Boolean save(XietongTeacherRecruitment xietong_teacher_recruitment, String system_create_user_id) {
        Boolean success = xietongTeacherRecruitmentDao.save(xietong_teacher_recruitment, system_create_user_id);
        return success;
    }

    public Boolean update(XietongTeacherRecruitment xietong_teacher_recruitment, String teacher_recruitment_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(XietongTeacherRecruitment.SYSTEM_STATUS, true);
        cnd.and(XietongTeacherRecruitment.TEACHER_RECRUITMENT_ID, teacher_recruitment_id);
        cnd.and(XietongTeacherRecruitment.SYSTEM_VERSION, system_version);

        Boolean success = xietongTeacherRecruitmentDao.update(xietong_teacher_recruitment, system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(XIETONG_TEACHER_RECRUITMENT_ITEM_CACHE, teacher_recruitment_id);
        }

        return success;
    }

    public Boolean delete(String teacher_recruitment_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(XietongTeacherRecruitment.SYSTEM_STATUS, true);
        cnd.and(XietongTeacherRecruitment.TEACHER_RECRUITMENT_ID, teacher_recruitment_id);
        cnd.and(XietongTeacherRecruitment.SYSTEM_VERSION, system_version);

        Boolean success = xietongTeacherRecruitmentDao.delete(system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(XIETONG_TEACHER_RECRUITMENT_ITEM_CACHE, teacher_recruitment_id);
        }

        return success;
    }
    
    public ExcelRender export(String teacher_recruitment_id) {
        XietongTeacherRecruitment xietongTeacherRecruitment = find(teacher_recruitment_id);
        
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);

        HSSFSheet sheet = wb.createSheet("教师招聘信息");

        HSSFRow row = sheet.createRow(0);
        HSSFCell cell = row.createCell(0);
        cell.setCellValue("姓名");
        cell.setCellStyle(style);
        cell = row.createCell(1);
        cell.setCellValue("性别");
        cell.setCellStyle(style);
        cell = row.createCell(2);
        cell.setCellValue("出生日期");
        cell.setCellStyle(style);
        cell = row.createCell(3);
        cell.setCellValue("手机号码");
        cell.setCellStyle(style);
        cell = row.createCell(4);
        cell.setCellValue("邮箱地址");
        cell.setCellStyle(style);
        cell = row.createCell(5);
        cell.setCellValue("应聘学部");
        cell.setCellStyle(style);
        cell = row.createCell(6);
        cell.setCellValue("应聘学科");
        cell.setCellStyle(style);
        cell = row.createCell(7);
        cell.setCellValue("是否应届毕业生");
        cell.setCellStyle(style);
        cell = row.createCell(8);
        cell.setCellValue("工作年限");
        cell.setCellStyle(style);
        cell = row.createCell(9);
        cell.setCellValue("原工作单位");
        cell.setCellStyle(style);
        cell = row.createCell(10);
        cell.setCellValue("政治面貌");
        cell.setCellStyle(style);
        cell = row.createCell(11);
        cell.setCellValue("职称");
        cell.setCellStyle(style);
        cell = row.createCell(12);
        cell.setCellValue("学历");
        cell.setCellStyle(style);
        cell = row.createCell(13);
        cell.setCellValue("专业");
        cell.setCellStyle(style);
        cell = row.createCell(14);
        cell.setCellValue("毕业院校");
        cell.setCellStyle(style);
        cell = row.createCell(15);
        cell.setCellValue("教育经历");
        cell.setCellStyle(style);
        cell = row.createCell(16);
        cell.setCellValue("工作经历");
        cell.setCellStyle(style);
        cell = row.createCell(17);
        cell.setCellValue("所获代表性荣誉");
        cell.setCellStyle(style);
        cell = row.createCell(18);
        cell.setCellValue("现在住址");
        cell.setCellStyle(style);
        
        row = sheet.createRow(1);
        cell = row.createCell(0);
        cell.setCellValue(xietongTeacherRecruitment.getTeacher_recruitment_name());
        cell.setCellStyle(style);
        cell = row.createCell(1);
        cell.setCellValue(xietongTeacherRecruitment.getTeacher_recruitment_sex());
        cell.setCellStyle(style);
        cell = row.createCell(2);
        cell.setCellValue(DateUtil.getDateString(xietongTeacherRecruitment.getTeacher_recruitment_birthday()));
        cell.setCellStyle(style);
        cell = row.createCell(3);
        cell.setCellValue(xietongTeacherRecruitment.getTeacher_recruitment_mobile());
        cell.setCellStyle(style);
        cell = row.createCell(4);
        cell.setCellValue(xietongTeacherRecruitment.getTeacher_recruitment_email());
        cell.setCellStyle(style);
        cell = row.createCell(5);
        cell.setCellValue(xietongTeacherRecruitment.getTeacher_recruitment_faculty());
        cell.setCellStyle(style);
        cell = row.createCell(6);
        cell.setCellValue(xietongTeacherRecruitment.getTeacher_recruitment_subject());
        cell.setCellStyle(style);
        cell = row.createCell(7);
        cell.setCellValue(xietongTeacherRecruitment.getTeacher_recruitment_is_fresh_graduate()?"是":"否");
        cell.setCellStyle(style);
        cell = row.createCell(8);
        cell.setCellValue(xietongTeacherRecruitment.getTeacher_recruitment_work_year());
        cell.setCellStyle(style);
        cell = row.createCell(9);
        cell.setCellValue(xietongTeacherRecruitment.getTeacher_recruitment_old_unit());
        cell.setCellStyle(style);
        cell = row.createCell(10);
        cell.setCellValue(xietongTeacherRecruitment.getTeacher_recruitment_politics_status());
        cell.setCellStyle(style);
        cell = row.createCell(11);
        cell.setCellValue(xietongTeacherRecruitment.getTeacher_recruitment_job_title());
        cell.setCellStyle(style);
        cell = row.createCell(12);
        cell.setCellValue(xietongTeacherRecruitment.getTeacher_recruitment_education());
        cell.setCellStyle(style);
        cell = row.createCell(13);
        cell.setCellValue(xietongTeacherRecruitment.getTeacher_recruitment_major());
        cell.setCellStyle(style);
        cell = row.createCell(14);
        cell.setCellValue(xietongTeacherRecruitment.getTeacher_recruitment_grad_school());
        cell.setCellStyle(style);
        cell = row.createCell(15);
        cell.setCellValue(xietongTeacherRecruitment.getTeacher_recruitment_education());
        cell.setCellStyle(style);
        cell = row.createCell(16);
        cell.setCellValue(xietongTeacherRecruitment.getTeacher_recruitment_work_experience());
        cell.setCellStyle(style);
        cell = row.createCell(17);
        cell.setCellValue(xietongTeacherRecruitment.getTeacher_recruitment_representative_honor());
        cell.setCellStyle(style);
        cell = row.createCell(18);
        cell.setCellValue(xietongTeacherRecruitment.getTeacher_recruitment_now_address());
        cell.setCellStyle(style);
        
        return new ExcelRender(wb, "教师招聘信息");
    }

    /**
     * 导出所有招聘信息
     * @return
     */
    public ExcelRender allExport() {
               
        List<XietongTeacherRecruitment> xietong_teacher_recruitmentlist = allList();
        
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);

        HSSFSheet sheet = wb.createSheet("教师招聘信息汇总");

        HSSFRow row = sheet.createRow(0);
        HSSFCell cell = row.createCell(0);
        cell.setCellValue("姓名");
        cell.setCellStyle(style);
        cell = row.createCell(1);
        cell.setCellValue("性别");
        cell.setCellStyle(style);
        cell = row.createCell(2);
        cell.setCellValue("出生日期");
        cell.setCellStyle(style);
        cell = row.createCell(3);
        cell.setCellValue("手机号码");
        cell.setCellStyle(style);
        cell = row.createCell(4);
        cell.setCellValue("邮箱地址");
        cell.setCellStyle(style);
        cell = row.createCell(5);
        cell.setCellValue("应聘学部");
        cell.setCellStyle(style);
        cell = row.createCell(6);
        cell.setCellValue("应聘学科");
        cell.setCellStyle(style);
        cell = row.createCell(7);
        cell.setCellValue("是否应届毕业生");
        cell.setCellStyle(style);
        cell = row.createCell(8);
        cell.setCellValue("工作年限");
        cell.setCellStyle(style);
        cell = row.createCell(9);
        cell.setCellValue("原工作单位");
        cell.setCellStyle(style);
        cell = row.createCell(10);
        cell.setCellValue("政治面貌");
        cell.setCellStyle(style);
        cell = row.createCell(11);
        cell.setCellValue("职称");
        cell.setCellStyle(style);
        cell = row.createCell(12);
        cell.setCellValue("学历");
        cell.setCellStyle(style);
        cell = row.createCell(13);
        cell.setCellValue("专业");
        cell.setCellStyle(style);
        cell = row.createCell(14);
        cell.setCellValue("毕业院校");
        cell.setCellStyle(style);
        cell = row.createCell(15);
        cell.setCellValue("教育经历");
        cell.setCellStyle(style);
        cell = row.createCell(16);
        cell.setCellValue("工作经历");
        cell.setCellStyle(style);
        cell = row.createCell(17);
        cell.setCellValue("所获代表性荣誉");
        cell.setCellStyle(style);
        cell = row.createCell(18);
        cell.setCellValue("现在住址");
        cell.setCellStyle(style);
        
        for (int i = 0; i < xietong_teacher_recruitmentlist.size(); i++) {
            XietongTeacherRecruitment xietongTeacherRecruitment = xietong_teacher_recruitmentlist.get(i);
            
            row = sheet.createRow(i + 1);
            cell = row.createCell(0);
            cell.setCellValue(xietongTeacherRecruitment.getTeacher_recruitment_name());
            cell.setCellStyle(style);
            cell = row.createCell(1);
            cell.setCellValue(xietongTeacherRecruitment.getTeacher_recruitment_sex());
            cell.setCellStyle(style);
            cell = row.createCell(2);
            cell.setCellValue(DateUtil.getDateString(xietongTeacherRecruitment.getTeacher_recruitment_birthday()));
            cell.setCellStyle(style);
            cell = row.createCell(3);
            cell.setCellValue(xietongTeacherRecruitment.getTeacher_recruitment_mobile());
            cell.setCellStyle(style);
            cell = row.createCell(4);
            cell.setCellValue(xietongTeacherRecruitment.getTeacher_recruitment_email());
            cell.setCellStyle(style);
            cell = row.createCell(5);
            cell.setCellValue(xietongTeacherRecruitment.getTeacher_recruitment_faculty());
            cell.setCellStyle(style);
            cell = row.createCell(6);
            cell.setCellValue(xietongTeacherRecruitment.getTeacher_recruitment_subject());
            cell.setCellStyle(style);
            cell = row.createCell(7);
            cell.setCellValue(xietongTeacherRecruitment.getTeacher_recruitment_is_fresh_graduate()?"是":"否");
            cell.setCellStyle(style);
            cell = row.createCell(8);
            cell.setCellValue(xietongTeacherRecruitment.getTeacher_recruitment_work_year());
            cell.setCellStyle(style);
            cell = row.createCell(9);
            cell.setCellValue(xietongTeacherRecruitment.getTeacher_recruitment_old_unit());
            cell.setCellStyle(style);
            cell = row.createCell(10);
            cell.setCellValue(xietongTeacherRecruitment.getTeacher_recruitment_politics_status());
            cell.setCellStyle(style);
            cell = row.createCell(11);
            cell.setCellValue(xietongTeacherRecruitment.getTeacher_recruitment_job_title());
            cell.setCellStyle(style);
            cell = row.createCell(12);
            cell.setCellValue(xietongTeacherRecruitment.getTeacher_recruitment_education());
            cell.setCellStyle(style);
            cell = row.createCell(13);
            cell.setCellValue(xietongTeacherRecruitment.getTeacher_recruitment_major());
            cell.setCellStyle(style);
            cell = row.createCell(14);
            cell.setCellValue(xietongTeacherRecruitment.getTeacher_recruitment_grad_school());
            cell.setCellStyle(style);
            cell = row.createCell(15);
            cell.setCellValue(xietongTeacherRecruitment.getTeacher_recruitment_education());
            cell.setCellStyle(style);
            cell = row.createCell(16);
            cell.setCellValue(xietongTeacherRecruitment.getTeacher_recruitment_work_experience());
            cell.setCellStyle(style);
            cell = row.createCell(17);
            cell.setCellValue(xietongTeacherRecruitment.getTeacher_recruitment_representative_honor());
            cell.setCellStyle(style);
            cell = row.createCell(18);
            cell.setCellValue(xietongTeacherRecruitment.getTeacher_recruitment_now_address());
            cell.setCellStyle(style);
        }

        return new ExcelRender(wb, "教师招聘信息");
    }


}