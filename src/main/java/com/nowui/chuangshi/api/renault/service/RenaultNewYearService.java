package com.nowui.chuangshi.api.renault.service;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.HorizontalAlignment;

import com.nowui.chuangshi.api.renault.dao.RenaultNewYearDao;
import com.nowui.chuangshi.api.renault.model.RenaultNewYear;
import com.nowui.chuangshi.common.render.ExcelRender;
import com.nowui.chuangshi.common.service.Service;
import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.util.CacheUtil;
import com.nowui.chuangshi.util.DateUtil;

public class RenaultNewYearService extends Service {

    public static final RenaultNewYearService instance = new RenaultNewYearService();
    private final String RENAULT_NEW_YEAR_ITEM_CACHE = "renault_new_year_item_cache";
    private final RenaultNewYearDao renaultNewYearDao = new RenaultNewYearDao();

    public Integer adminCount(String app_id, String new_year_name, String new_year_phone) {
        Cnd cnd = new Cnd();
        cnd.where(RenaultNewYear.SYSTEM_STATUS, true);
        cnd.and(RenaultNewYear.APP_ID, app_id);
        cnd.andLikeAllowEmpty(RenaultNewYear.NEW_YEAR_NAME, new_year_name);
        cnd.andLikeAllowEmpty(RenaultNewYear.NEW_YEAR_PHONE, new_year_phone);

        Integer count = renaultNewYearDao.count(cnd);
        return count;
    }

    public List<RenaultNewYear> adminList(String app_id, String new_year_name, String new_year_phone, Integer m, Integer n) {
        Cnd cnd = new Cnd();
        cnd.where(RenaultNewYear.SYSTEM_STATUS, true);
        cnd.and(RenaultNewYear.APP_ID, app_id);
        cnd.andLikeAllowEmpty(RenaultNewYear.NEW_YEAR_NAME, new_year_name);
        cnd.andLikeAllowEmpty(RenaultNewYear.NEW_YEAR_PHONE, new_year_phone);
        cnd.paginate(m, n);

        List<RenaultNewYear> renault_new_yearList = renaultNewYearDao.primaryKeyList(cnd);
        for (RenaultNewYear renault_new_year : renault_new_yearList) {
            renault_new_year.put(find(renault_new_year.getNew_year_id()));
        }
        return renault_new_yearList;
    }
    
    public List<RenaultNewYear> allList() {
        Cnd cnd = new Cnd();
        cnd.where(RenaultNewYear.SYSTEM_STATUS, true);
        
        List<RenaultNewYear> renault_new_yearList = renaultNewYearDao.primaryKeyList(cnd);
        for (RenaultNewYear renault_new_year : renault_new_yearList) {
            renault_new_year.put(find(renault_new_year.getNew_year_id()));
        }
        return renault_new_yearList;
    }

    public RenaultNewYear find(String new_year_id) {
        RenaultNewYear renault_new_year = CacheUtil.get(RENAULT_NEW_YEAR_ITEM_CACHE, new_year_id);

        if (renault_new_year == null) {
            renault_new_year = renaultNewYearDao.find(new_year_id);

            CacheUtil.put(RENAULT_NEW_YEAR_ITEM_CACHE, new_year_id, renault_new_year);
        }

        return renault_new_year;
    }

    public Boolean save(RenaultNewYear renault_new_year, String system_create_user_id) {
        Boolean success = renaultNewYearDao.save(renault_new_year, system_create_user_id);
        return success;
    }

    public Boolean update(RenaultNewYear renault_new_year, String new_year_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(RenaultNewYear.SYSTEM_STATUS, true);
        cnd.and(RenaultNewYear.NEW_YEAR_ID, new_year_id);
        cnd.and(RenaultNewYear.SYSTEM_VERSION, system_version);

        Boolean success = renaultNewYearDao.update(renault_new_year, system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(RENAULT_NEW_YEAR_ITEM_CACHE, new_year_id);
        }

        return success;
    }

    public Boolean delete(String new_year_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(RenaultNewYear.SYSTEM_STATUS, true);
        cnd.and(RenaultNewYear.NEW_YEAR_ID, new_year_id);
        cnd.and(RenaultNewYear.SYSTEM_VERSION, system_version);

        Boolean success = renaultNewYearDao.delete(system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(RENAULT_NEW_YEAR_ITEM_CACHE, new_year_id);
        }

        return success;
    }
    
    /**
     * 导出所有新年留资信息
     * @return
     */
    public ExcelRender allExport() {
               
        List<RenaultNewYear> renault_new_yearlist = allList();
        
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);

        HSSFSheet sheet = wb.createSheet("新年留资信息汇总");

        HSSFRow row = sheet.createRow(0);
        HSSFCell cell = row.createCell(0);
        cell.setCellValue("姓名");
        cell.setCellStyle(style);
        cell = row.createCell(1);
        cell.setCellValue("电话");
        cell.setCellStyle(style);
        cell = row.createCell(2);
        cell.setCellValue("新年总结");
        cell.setCellStyle(style);
        cell = row.createCell(3);
        cell.setCellValue("新年愿望");
        cell.setCellStyle(style);
        cell = row.createCell(4);
        cell.setCellValue("留资日期");
        cell.setCellStyle(style);
        
        for (int i = 0; i < renault_new_yearlist.size(); i++) {
            RenaultNewYear renault_new_year = renault_new_yearlist.get(i);
            
            row = sheet.createRow(i + 1);
            cell = row.createCell(0);
            cell.setCellValue(renault_new_year.getNew_year_name());
            cell.setCellStyle(style);
            cell = row.createCell(1);
            cell.setCellValue(renault_new_year.getNew_year_phone());
            cell.setCellStyle(style);
            cell = row.createCell(2);
            cell.setCellValue(renault_new_year.getNew_year_summary());
            cell.setCellStyle(style);
            cell = row.createCell(3);
            cell.setCellValue(renault_new_year.getNew_year_wish());
            cell.setCellStyle(style);
            cell = row.createCell(4);
            cell.setCellValue(DateUtil.getDateString(renault_new_year.getSystem_create_time()));
            cell.setCellStyle(style);
        }

        return new ExcelRender(wb, "新年留资信息");
    }

}