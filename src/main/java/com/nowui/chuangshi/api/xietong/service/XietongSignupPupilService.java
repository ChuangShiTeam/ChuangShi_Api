package com.nowui.chuangshi.api.xietong.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.HorizontalAlignment;

import com.alibaba.fastjson.JSONObject;
import com.nowui.chuangshi.api.user.model.User;
import com.nowui.chuangshi.api.user.service.UserService;
import com.nowui.chuangshi.api.xietong.dao.XietongSignupPupilDao;
import com.nowui.chuangshi.api.xietong.model.XietongSignupJunior;
import com.nowui.chuangshi.api.xietong.model.XietongSignupPupil;
import com.nowui.chuangshi.common.render.ExcelRender;
import com.nowui.chuangshi.common.service.Service;
import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.constant.Config;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.type.UserType;
import com.nowui.chuangshi.util.AesUtil;
import com.nowui.chuangshi.util.CacheUtil;
import com.nowui.chuangshi.util.DateUtil;
import com.nowui.chuangshi.util.Util;

public class XietongSignupPupilService extends Service {

    public static final XietongSignupPupilService instance = new XietongSignupPupilService();
    private final String XIETONG_SIGNUP_PUPIL_ITEM_CACHE = "xietong_signup_pupil_item_cache";
    private final String MOBILE_XIETONG_SIGNUP_PUPIL_ITEM_CACHE = "mobile_xietong_signup_pupil_item_cache";
    private final XietongSignupPupilDao xietongSignupPupilDao = new XietongSignupPupilDao();

    public Integer adminCount(String app_id, String student_name, String id_no) {
        Cnd cnd = new Cnd();
        cnd.where(XietongSignupPupil.SYSTEM_STATUS, true);
        cnd.and(XietongSignupPupil.APP_ID, app_id);
        cnd.andLikeAllowEmpty(XietongSignupPupil.STUDENT_NAME, student_name);
        cnd.andLikeAllowEmpty(XietongSignupPupil.ID_NO, id_no);

        Integer count = xietongSignupPupilDao.count(cnd);
        return count;
    }

    public List<XietongSignupPupil> adminList(String app_id, String student_name, String id_no, Integer m, Integer n) {
        Cnd cnd = new Cnd();
        cnd.where(XietongSignupPupil.SYSTEM_STATUS, true);
        cnd.and(XietongSignupPupil.APP_ID, app_id);
        cnd.andLikeAllowEmpty(XietongSignupPupil.STUDENT_NAME, student_name);
        cnd.andLikeAllowEmpty(XietongSignupPupil.ID_NO, id_no);
        cnd.desc(XietongSignupPupil.SYSTEM_CREATE_TIME);
        cnd.paginate(m, n);

        List<XietongSignupPupil> xietong_signup_pupilList = xietongSignupPupilDao.primaryKeyList(cnd);
        for (XietongSignupPupil xietong_signup_pupil : xietong_signup_pupilList) {
            xietong_signup_pupil.put(find(xietong_signup_pupil.getSignup_id()));
        }
        return xietong_signup_pupilList;
    }
    
    public List<XietongSignupPupil> allList() {
        Cnd cnd = new Cnd();
        cnd.where(XietongSignupPupil.SYSTEM_STATUS, true);
        cnd.asc(XietongSignupPupil.SYSTEM_CREATE_TIME);

        List<XietongSignupPupil> xietong_signup_pupilList = xietongSignupPupilDao.primaryKeyList(cnd);
        for (XietongSignupPupil xietong_signup_pupil : xietong_signup_pupilList) {
            xietong_signup_pupil.put(find(xietong_signup_pupil.getSignup_id()));
        }
        return xietong_signup_pupilList;
    }

    public XietongSignupPupil find(String signup_id) {
        XietongSignupPupil xietong_signup_pupil = CacheUtil.get(XIETONG_SIGNUP_PUPIL_ITEM_CACHE, signup_id);

        if (xietong_signup_pupil == null) {
            xietong_signup_pupil = xietongSignupPupilDao.find(signup_id);

            CacheUtil.put(XIETONG_SIGNUP_PUPIL_ITEM_CACHE, signup_id, xietong_signup_pupil);
        }

        return xietong_signup_pupil;
    }
    
    public String findLatestSignupNumber(String app_id) {
        Cnd cnd = new Cnd();
        cnd.where(XietongSignupPupil.SYSTEM_STATUS, true);
        cnd.and(XietongSignupPupil.APP_ID, app_id);
        cnd.desc(XietongSignupPupil.SYSTEM_CREATE_TIME);
        
        List<XietongSignupPupil> xietong_signup_pupilList = xietongSignupPupilDao.list(cnd);
        
        if (xietong_signup_pupilList == null || xietong_signup_pupilList.size() == 0) {
            return null;
        }
        
        return xietong_signup_pupilList.get(0).getSignupNumber();
        
    }
    
    /**
     * 根据用户编号查询
     * @param user_id
     * @return
     */
    public XietongSignupPupil userFind(String user_id) {
    	 Cnd cnd = new Cnd();
         cnd.where(XietongSignupPupil.SYSTEM_STATUS, true);
         cnd.and(XietongSignupPupil.USER_ID, user_id);
         
         return xietongSignupPupilDao.find(cnd);
    }

    //根据证件号码查询
    public XietongSignupPupil idNoFind(String id_no) {
        XietongSignupPupil xietong_signup_pupil = CacheUtil.get(MOBILE_XIETONG_SIGNUP_PUPIL_ITEM_CACHE, id_no);
        Cnd cnd = new Cnd();
        cnd.where(XietongSignupPupil.SYSTEM_STATUS, true);
        cnd.and(XietongSignupPupil.ID_NO, id_no);

        if (xietong_signup_pupil == null) {
            xietong_signup_pupil = xietongSignupPupilDao.find(cnd);

            CacheUtil.put(MOBILE_XIETONG_SIGNUP_PUPIL_ITEM_CACHE, id_no, xietong_signup_pupil);
        }

        return xietong_signup_pupil;
    }

    public Boolean save(XietongSignupPupil xietong_signup_pupil, String system_create_user_id) {
        Boolean success = xietongSignupPupilDao.save(xietong_signup_pupil, system_create_user_id);
        return success;
    }
    
    public String save(XietongSignupPupil xietong_signup_pupil, User user, String system_create_user_id) {
    	String user_id = Util.getRandomUUID();
    	xietong_signup_pupil.setUser_id(user_id);
    	xietong_signup_pupil.setSignup_id(Util.getRandomUUID());
        Boolean result = save(xietong_signup_pupil, system_create_user_id);
        
        String token = null;

        if (result) {
        	user.setUser_password("123456");	//初始密码为123456 
            UserService.instance.userAccountSave(user_id, xietong_signup_pupil.getApp_id(), xietong_signup_pupil.getSignup_id(), UserType.PUPIL_ADMISSIONS.getKey(), xietong_signup_pupil.getStudent_name(), xietong_signup_pupil.getId_no(), user.getUser_password(), system_create_user_id);
            
            Date date = new Date();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.YEAR, 1);

            JSONObject jsonObject = new JSONObject();
            jsonObject.put(User.USER_ID, user_id);
            jsonObject.put(Constant.EXPIRE_TIME, calendar.getTime());
            
            try {
				token = AesUtil.aesEncrypt(jsonObject.toJSONString(), Config.private_key);
			} catch (Exception e) {
				e.printStackTrace();
			}
        }

        return token;
    }

    public Boolean update(XietongSignupPupil xietong_signup_pupil, String signup_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(XietongSignupPupil.SYSTEM_STATUS, true);
        cnd.and(XietongSignupPupil.SIGNUP_ID, signup_id);
        cnd.and(XietongSignupPupil.SYSTEM_VERSION, system_version);

        Boolean success = xietongSignupPupilDao.update(xietong_signup_pupil, system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(XIETONG_SIGNUP_PUPIL_ITEM_CACHE, signup_id);
        }

        return success;
    }
    
    public Boolean update(XietongSignupPupil xietong_signup_pupil, User user, String system_update_user_id) {
        Boolean result = update(xietong_signup_pupil, xietong_signup_pupil.getSignup_id(), system_update_user_id, xietong_signup_pupil.getSystem_version());
        
        if (result) {
            UserService.instance.userAccountAndNameUpdate(user.getUser_id(), xietong_signup_pupil.getId_no(), xietong_signup_pupil.getStudent_name(), system_update_user_id);
        }
        
        return result;
    }


    public Boolean delete(String signup_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(XietongSignupPupil.SYSTEM_STATUS, true);
        cnd.and(XietongSignupPupil.SIGNUP_ID, signup_id);
        cnd.and(XietongSignupPupil.SYSTEM_VERSION, system_version);

        Boolean success = xietongSignupPupilDao.delete(system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(XIETONG_SIGNUP_PUPIL_ITEM_CACHE, signup_id);
        }

        return success;
    }
    
    /**
     * 导出所有小学报名信息
     * @return
     */
    public ExcelRender allExport() {
               
        List<XietongSignupPupil> xietong_signup_pupilList = allList();
        
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);

        HSSFSheet sheet = wb.createSheet("小学报名信息汇总");

        HSSFRow row = sheet.createRow(0);
        HSSFCell cell = row.createCell(0);
        cell.setCellValue("报名序号");
        cell.setCellStyle(style);
        cell = row.createCell(1);
        cell.setCellValue("姓名");
        cell.setCellStyle(style);
        cell = row.createCell(2);
        cell.setCellValue("性别");
        cell.setCellStyle(style);
        cell = row.createCell(3);
        cell.setCellValue("出生日期");
        cell.setCellStyle(style);
        cell = row.createCell(4);
        cell.setCellValue("原就读幼儿园");
        cell.setCellStyle(style);
        cell = row.createCell(5);
        cell.setCellValue("证件类型");
        cell.setCellStyle(style);
        cell = row.createCell(6);
        cell.setCellValue("证件号码");
        cell.setCellStyle(style);
        cell = row.createCell(7);
        cell.setCellValue("户籍地址");
        cell.setCellStyle(style);
        cell = row.createCell(8);
        cell.setCellValue("家庭住址");
        cell.setCellStyle(style);
        cell = row.createCell(9);
        cell.setCellValue("父亲姓名");
        cell.setCellStyle(style);
        cell = row.createCell(10);
        cell.setCellValue("父亲联系电话");
        cell.setCellStyle(style);
        cell = row.createCell(11);
        cell.setCellValue("父亲工作单位");
        cell.setCellStyle(style);
        cell = row.createCell(12);
        cell.setCellValue("母亲姓名");
        cell.setCellStyle(style);
        cell = row.createCell(13);
        cell.setCellValue("母亲联系电话");
        cell.setCellStyle(style);
        cell = row.createCell(14);
        cell.setCellValue("母亲工作单位");
        cell.setCellStyle(style);
        cell = row.createCell(15);
        cell.setCellValue("需要说明事项");
        cell.setCellStyle(style);
        cell = row.createCell(16);
        cell.setCellValue("报名状态");
        cell.setCellStyle(style);
        
        for (int i = 0; i < xietong_signup_pupilList.size(); i++) {
            XietongSignupPupil xietong_signup_pupil = xietong_signup_pupilList.get(i);
            
            row = sheet.createRow(i + 1);
            cell = row.createCell(0);
            cell.setCellValue(xietong_signup_pupil.getSignupNumber());
            cell.setCellStyle(style);
            cell = row.createCell(1);
            cell.setCellValue(xietong_signup_pupil.getStudent_name());
            cell.setCellStyle(style);
            cell = row.createCell(2);
            cell.setCellValue(xietong_signup_pupil.getStudent_sex());
            cell.setCellStyle(style);
            cell = row.createCell(3);
            cell.setCellValue(DateUtil.getDateString(xietong_signup_pupil.getStudent_birthday()));
            cell.setCellStyle(style);
            cell = row.createCell(4);
            cell.setCellValue(xietong_signup_pupil.getKindergarten());
            cell.setCellStyle(style);
            cell = row.createCell(5);
            cell.setCellValue(xietong_signup_pupil.getId_type());
            cell.setCellStyle(style);
            cell = row.createCell(6);
            cell.setCellValue(xietong_signup_pupil.getId_no());
            cell.setCellStyle(style);
            cell = row.createCell(7);
            cell.setCellValue(xietong_signup_pupil.getPermanent_address());
            cell.setCellStyle(style);
            cell = row.createCell(8);
            cell.setCellValue(xietong_signup_pupil.getLive_addresss());
            cell.setCellStyle(style);
            cell = row.createCell(9);
            cell.setCellValue(xietong_signup_pupil.getFather_name());
            cell.setCellStyle(style);
            cell = row.createCell(10);
            cell.setCellValue(xietong_signup_pupil.getFather_phone());
            cell.setCellStyle(style);
            cell = row.createCell(11);
            cell.setCellValue(xietong_signup_pupil.getFather_work());
            cell.setCellStyle(style);
            cell = row.createCell(12);
            cell.setCellValue(xietong_signup_pupil.getMother_name());
            cell.setCellStyle(style);
            cell = row.createCell(13);
            cell.setCellValue(xietong_signup_pupil.getMother_phone());
            cell.setCellStyle(style);
            cell = row.createCell(14);
            cell.setCellValue(xietong_signup_pupil.getMother_work());
            cell.setCellStyle(style);
            cell = row.createCell(15);
            cell.setCellValue(xietong_signup_pupil.getRemark());
            cell.setCellStyle(style);
            cell = row.createCell(16);
            cell.setCellValue(xietong_signup_pupil.getSignup_status());
            cell.setCellStyle(style);
        }

        return new ExcelRender(wb, "小学报名信息");
    }

}