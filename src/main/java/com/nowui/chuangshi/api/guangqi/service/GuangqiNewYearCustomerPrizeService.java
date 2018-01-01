package com.nowui.chuangshi.api.guangqi.service;

import com.jfinal.render.Render;
import com.nowui.chuangshi.api.guangqi.dao.GuangqiNewYearCustomerPrizeDao;
import com.nowui.chuangshi.api.guangqi.model.GuangqiNewYearCustomerPrize;
import com.nowui.chuangshi.api.xietong.model.XietongTeacherRecruitment;
import com.nowui.chuangshi.common.render.ExcelRender;
import com.nowui.chuangshi.common.service.Service;
import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.util.CacheUtil;
import com.nowui.chuangshi.util.DateUtil;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.HorizontalAlignment;

public class GuangqiNewYearCustomerPrizeService extends Service {

    public static final GuangqiNewYearCustomerPrizeService instance = new GuangqiNewYearCustomerPrizeService();
    private final String GUANGQI_NEW_YEAR_CUSTOMER_PRIZE_ITEM_CACHE = "guangqi_new_year_customer_prize_item_cache";
    private final GuangqiNewYearCustomerPrizeDao guangqiNewYearCustomerPrizeDao = new GuangqiNewYearCustomerPrizeDao();

    public Integer adminCount(String app_id, String new_year_customer_id, String new_year_prize_id) {
        Cnd cnd = new Cnd();
        cnd.where(GuangqiNewYearCustomerPrize.SYSTEM_STATUS, true);
        cnd.and(GuangqiNewYearCustomerPrize.APP_ID, app_id);
        cnd.andAllowEmpty(GuangqiNewYearCustomerPrize.NEW_YEAR_CUSTOMER_ID, new_year_customer_id);
        cnd.andAllowEmpty(GuangqiNewYearCustomerPrize.NEW_YEAR_PRIZE_ID, new_year_prize_id);

        Integer count = guangqiNewYearCustomerPrizeDao.count(cnd);
        return count;
    }

    public List<GuangqiNewYearCustomerPrize> adminList(String app_id, String new_year_customer_id, String new_year_prize_id, Integer m, Integer n) {
        Cnd cnd = new Cnd();
        cnd.where(GuangqiNewYearCustomerPrize.SYSTEM_STATUS, true);
        cnd.and(GuangqiNewYearCustomerPrize.APP_ID, app_id);
        cnd.andAllowEmpty(GuangqiNewYearCustomerPrize.NEW_YEAR_CUSTOMER_ID, new_year_customer_id);
        cnd.andAllowEmpty(GuangqiNewYearCustomerPrize.NEW_YEAR_PRIZE_ID, new_year_prize_id);
        cnd.paginate(m, n);

        List<GuangqiNewYearCustomerPrize> guangqi_new_year_customer_prizeList = guangqiNewYearCustomerPrizeDao.primaryKeyList(cnd);
        for (GuangqiNewYearCustomerPrize guangqi_new_year_customer_prize : guangqi_new_year_customer_prizeList) {
            guangqi_new_year_customer_prize.put(find(guangqi_new_year_customer_prize.getNew_year_customer_prize_id()));
        }
        return guangqi_new_year_customer_prizeList;
    }
    
    public List<GuangqiNewYearCustomerPrize> allList() {
    	Cnd cnd = new Cnd();
    	cnd.where(GuangqiNewYearCustomerPrize.SYSTEM_STATUS, true);
    	
    	List<GuangqiNewYearCustomerPrize> guangqi_new_year_customer_prizeList = guangqiNewYearCustomerPrizeDao.primaryKeyList(cnd);
    	for (GuangqiNewYearCustomerPrize guangqi_new_year_customer_prize : guangqi_new_year_customer_prizeList) {
    		guangqi_new_year_customer_prize.put(find(guangqi_new_year_customer_prize.getNew_year_customer_prize_id()));
    	}
    	return guangqi_new_year_customer_prizeList;
    }
    
    public Integer appAndCustomerCount(String app_id, String new_year_customer_id) {
    	Cnd cnd = new Cnd();
        cnd.where(GuangqiNewYearCustomerPrize.SYSTEM_STATUS, true);
        cnd.and(GuangqiNewYearCustomerPrize.APP_ID, app_id);
        cnd.and(GuangqiNewYearCustomerPrize.NEW_YEAR_CUSTOMER_ID, new_year_customer_id);

        Integer count = guangqiNewYearCustomerPrizeDao.count(cnd);
        return count;
    }
    
    public Integer appAndPrizeCount(String app_id, String new_year_prize_id) {
    	Cnd cnd = new Cnd();
        cnd.where(GuangqiNewYearCustomerPrize.SYSTEM_STATUS, true);
        cnd.and(GuangqiNewYearCustomerPrize.APP_ID, app_id);
        cnd.and(GuangqiNewYearCustomerPrize.NEW_YEAR_PRIZE_ID, new_year_prize_id);

        Integer count = guangqiNewYearCustomerPrizeDao.count(cnd);
        return count;
    }
    
    public Integer appAndPrizeAndDateCount(String app_id, String new_year_prize_id, String new_year_customer_prize_date) {
    	Cnd cnd = new Cnd();
        cnd.where(GuangqiNewYearCustomerPrize.SYSTEM_STATUS, true);
        cnd.and(GuangqiNewYearCustomerPrize.APP_ID, app_id);
        cnd.and(GuangqiNewYearCustomerPrize.NEW_YEAR_PRIZE_ID, new_year_prize_id);
        cnd.and(GuangqiNewYearCustomerPrize.NEW_YEAR_CUSTOMER_PRIZE_DATE, new_year_customer_prize_date);

        Integer count = guangqiNewYearCustomerPrizeDao.count(cnd);
        return count;
    }
    
    public GuangqiNewYearCustomerPrize customerFind(String app_id, String new_year_customer_id) {
    	Cnd cnd = new Cnd();
    	cnd.where(GuangqiNewYearCustomerPrize.SYSTEM_STATUS, true);
    	cnd.and(GuangqiNewYearCustomerPrize.APP_ID, app_id);
        cnd.and(GuangqiNewYearCustomerPrize.NEW_YEAR_CUSTOMER_ID, new_year_customer_id);
    	
    	List<GuangqiNewYearCustomerPrize> guangqi_new_year_customer_prizeList = guangqiNewYearCustomerPrizeDao.primaryKeyList(cnd);
    	for (GuangqiNewYearCustomerPrize guangqi_new_year_customer_prize : guangqi_new_year_customer_prizeList) {
    		guangqi_new_year_customer_prize.put(find(guangqi_new_year_customer_prize.getNew_year_customer_prize_id()));
    	}
    	if (guangqi_new_year_customer_prizeList == null || guangqi_new_year_customer_prizeList.size() == 0) {
    		return null;
    	}
    	return guangqi_new_year_customer_prizeList.get(0);
    }

    public GuangqiNewYearCustomerPrize find(String new_year_customer_prize_id) {
        GuangqiNewYearCustomerPrize guangqi_new_year_customer_prize = CacheUtil.get(GUANGQI_NEW_YEAR_CUSTOMER_PRIZE_ITEM_CACHE, new_year_customer_prize_id);

        if (guangqi_new_year_customer_prize == null) {
            guangqi_new_year_customer_prize = guangqiNewYearCustomerPrizeDao.find(new_year_customer_prize_id);

            CacheUtil.put(GUANGQI_NEW_YEAR_CUSTOMER_PRIZE_ITEM_CACHE, new_year_customer_prize_id, guangqi_new_year_customer_prize);
        }

        return guangqi_new_year_customer_prize;
    }

    public Boolean save(GuangqiNewYearCustomerPrize guangqi_new_year_customer_prize, String system_create_user_id) {
        Boolean success = guangqiNewYearCustomerPrizeDao.save(guangqi_new_year_customer_prize, system_create_user_id);
        return success;
    }

    public Boolean update(GuangqiNewYearCustomerPrize guangqi_new_year_customer_prize, String new_year_customer_prize_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(GuangqiNewYearCustomerPrize.SYSTEM_STATUS, true);
        cnd.and(GuangqiNewYearCustomerPrize.NEW_YEAR_CUSTOMER_PRIZE_ID, new_year_customer_prize_id);
        cnd.and(GuangqiNewYearCustomerPrize.SYSTEM_VERSION, system_version);

        Boolean success = guangqiNewYearCustomerPrizeDao.update(guangqi_new_year_customer_prize, system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(GUANGQI_NEW_YEAR_CUSTOMER_PRIZE_ITEM_CACHE, new_year_customer_prize_id);
        }

        return success;
    }

    public Boolean delete(String new_year_customer_prize_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(GuangqiNewYearCustomerPrize.SYSTEM_STATUS, true);
        cnd.and(GuangqiNewYearCustomerPrize.NEW_YEAR_CUSTOMER_PRIZE_ID, new_year_customer_prize_id);
        cnd.and(GuangqiNewYearCustomerPrize.SYSTEM_VERSION, system_version);

        Boolean success = guangqiNewYearCustomerPrizeDao.delete(system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(GUANGQI_NEW_YEAR_CUSTOMER_PRIZE_ITEM_CACHE, new_year_customer_prize_id);
        }

        return success;
    }

}