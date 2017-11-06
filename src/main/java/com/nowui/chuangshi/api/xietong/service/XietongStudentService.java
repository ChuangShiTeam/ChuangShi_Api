package com.nowui.chuangshi.api.xietong.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.nowui.chuangshi.api.file.model.File;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.CellType;

import com.jfinal.upload.UploadFile;
import com.nowui.chuangshi.api.user.model.User;
import com.nowui.chuangshi.api.user.service.UserService;
import com.nowui.chuangshi.api.xietong.dao.XietongStudentDao;
import com.nowui.chuangshi.api.xietong.model.XietongClazz;
import com.nowui.chuangshi.api.xietong.model.XietongStudent;
import com.nowui.chuangshi.common.service.Service;
import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.type.UserType;
import com.nowui.chuangshi.util.CacheUtil;
import com.nowui.chuangshi.util.Util;
import com.nowui.chuangshi.util.ValidateUtil;

public class XietongStudentService extends Service {

    public static final XietongStudentService instance = new XietongStudentService();
    private final String XIETONG_STUDENT_ITEM_CACHE = "xietong_student_item_cache";
    private final XietongStudentDao xietongStudentDao = new XietongStudentDao();

    public Integer adminCount(String app_id, String student_name, String clazz_id) {
        Cnd cnd = new Cnd();        
        cnd.where(XietongStudent.SYSTEM_STATUS, true);
        cnd.and(XietongStudent.APP_ID, app_id);
        cnd.andLikeAllowEmpty(XietongStudent.STUDENT_NAME, student_name);
        cnd.andAllowEmpty(XietongStudent.CLAZZ_ID, clazz_id);

        Integer count = xietongStudentDao.count(cnd);
        return count;
    }

    public List<XietongStudent> adminList(String app_id, String student_name, String clazz_id, Integer m, Integer n) {
        Cnd cnd = new Cnd(); 
        cnd.select(XietongStudent.TABLE_XIETONG_STUDENT + "." + XietongStudent.STUDENT_NAME);
        cnd.select(XietongStudent.TABLE_XIETONG_STUDENT + "." + XietongStudent.STUDENT_NUMBER);
        cnd.select(XietongClazz.TABLE_XIETONG_CLAZZ + "." + XietongClazz.CLAZZ_NAME);
        cnd.leftJoin(XietongClazz.TABLE_XIETONG_CLAZZ, XietongClazz.CLAZZ_ID, XietongStudent.TABLE_XIETONG_STUDENT, XietongStudent.CLAZZ_ID);
        cnd.where(XietongStudent.TABLE_XIETONG_STUDENT + "." + XietongStudent.SYSTEM_STATUS, true);
        cnd.andLikeAllowEmpty(XietongStudent.TABLE_XIETONG_STUDENT + "." + XietongStudent.STUDENT_NAME, student_name);
        cnd.andAllowEmpty(XietongStudent.TABLE_XIETONG_STUDENT + "." + XietongStudent.CLAZZ_ID, clazz_id);
        cnd.desc(XietongStudent.TABLE_XIETONG_STUDENT + "." + XietongStudent.SYSTEM_CREATE_TIME);
        cnd.paginate(m, n);
        return xietongStudentDao.primaryKeyList(cnd);
    }
    
    public List<XietongStudent> mobileList(String app_id, String student_name, Integer m, Integer n) {
        Cnd cnd = new Cnd(); 
        cnd.select(XietongStudent.TABLE_XIETONG_STUDENT + "." + XietongStudent.STUDENT_NAME);
        cnd.select(XietongStudent.TABLE_XIETONG_STUDENT + "." + XietongStudent.STUDENT_NUMBER);
        cnd.select(XietongClazz.TABLE_XIETONG_CLAZZ + "." + XietongClazz.CLAZZ_NAME);
        cnd.leftJoin(XietongClazz.TABLE_XIETONG_CLAZZ, XietongClazz.CLAZZ_ID, XietongStudent.TABLE_XIETONG_STUDENT, XietongStudent.CLAZZ_ID);
        cnd.where(XietongStudent.SYSTEM_STATUS, true);
        cnd.andLikeAllowEmpty(XietongStudent.STUDENT_NAME, student_name);
        cnd.desc(XietongStudent.SYSTEM_CREATE_TIME);
        cnd.paginate(m, n);
        return xietongStudentDao.primaryKeyList(cnd);
    }

    public List<XietongStudent> categoryList(String app_id) {
        Cnd cnd = new Cnd();
        cnd.where(XietongStudent.SYSTEM_STATUS, true);
        cnd.andNot(XietongStudent.STUDENT_CATEGORY_ID, "");
        cnd.desc(XietongStudent.SYSTEM_CREATE_TIME);

        List<XietongStudent> studentList = xietongStudentDao.primaryKeyList(cnd);
        for (XietongStudent student : studentList) {
            student.put(find(student.getStudent_id()));
        }
        return studentList;
    }
    
    public XietongStudent userFind(String user_id) {
        
        User user = UserService.instance.find(user_id);
        
        return find(user.getObject_id());

    }
    
    public XietongStudent find(String student_id) {
        XietongStudent xietong_student = CacheUtil.get(XIETONG_STUDENT_ITEM_CACHE, student_id);

        if (xietong_student == null) {
            Cnd cnd = new Cnd();
            cnd.select(File.TABLE_FILE + "." + File.FILE_ID);
            cnd.selectIfNull(File.TABLE_FILE + "." + File.FILE_PATH, "", File.FILE_PATH);
            cnd.selectIfNull(File.TABLE_FILE + "." + File.FILE_ORIGINAL_PATH, "", File.FILE_ORIGINAL_PATH);
            cnd.leftJoin(File.TABLE_FILE, File.FILE_ID, XietongStudent.TABLE_XIETONG_STUDENT, XietongStudent.STUDENT_IMAGE);
            cnd.where(XietongStudent.TABLE_XIETONG_STUDENT + "." + XietongStudent.SYSTEM_STATUS, true);
            cnd.and(XietongStudent.TABLE_XIETONG_STUDENT + "." + XietongStudent.STUDENT_ID, student_id);
            
            xietong_student = xietongStudentDao.find(cnd);
            xietong_student.put(XietongClazz.CLAZZ_NAME, XietongClazzService.instance.find(xietong_student.getClazz_id()).getClazz_name());
            CacheUtil.put(XIETONG_STUDENT_ITEM_CACHE, student_id, xietong_student);
        }

        return xietong_student;
    }

    public Boolean save(XietongStudent xietong_student, String system_create_user_id) {
        Boolean success = xietongStudentDao.save(xietong_student, system_create_user_id);
        return success;
    }
    
    public Boolean save(XietongStudent xietong_student, User user, String request_user_id) {
        String user_id = Util.getRandomUUID();
        xietong_student.setStudent_id(Util.getRandomUUID());
        xietong_student.setUser_id(user_id);
        Boolean success = this.save(xietong_student, request_user_id);
        if (success) {
            UserService.instance.userAccountSave(user_id, xietong_student.getApp_id(), xietong_student.getStudent_id(), UserType.STUDENT.getKey(), xietong_student.getStudent_name(), xietong_student.getStudent_number(), user.getUser_password(), request_user_id);
        }
        return success;
    }

    public Boolean update(XietongStudent xietong_student, String student_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();        
        cnd.where(XietongStudent.SYSTEM_STATUS, true);
        cnd.and(XietongStudent.STUDENT_ID, student_id);
        cnd.and(XietongStudent.SYSTEM_VERSION, system_version);

        Boolean success = xietongStudentDao.update(xietong_student, system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(XIETONG_STUDENT_ITEM_CACHE, student_id);
        }

        return success;
    }
    
    public Boolean update(XietongStudent xietong_student, User user, String request_user_id, Integer system_version) {
        boolean success = this.update(xietong_student, xietong_student.getStudent_id(), request_user_id, system_version);
        
        if (success) {
            UserService.instance.userAccountAndNameUpdate(user.getUser_id(), xietong_student.getStudent_number(), xietong_student.getStudent_name(), request_user_id);
            if (!ValidateUtil.isNullOrEmpty(user.getUser_password())) {
            	UserService.instance.userPasswordUpdate(user.getUser_id(), user.getUser_password(), request_user_id);
            }
        }
        
        return success;
    }
    
    public Boolean passwordUpdate(String user_id, String user_password, String request_user_id) {
        return UserService.instance.userPasswordUpdate(user_id, user_password, request_user_id);
    }
    
    public Boolean delete(String student_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(XietongStudent.SYSTEM_STATUS, true);
        cnd.and(XietongStudent.STUDENT_ID, student_id);
        cnd.and(XietongStudent.SYSTEM_VERSION, system_version);

        Boolean success = xietongStudentDao.delete(system_update_user_id, system_version, cnd);

        if (success) {
            UserService.instance.objectIdDelete(student_id, system_update_user_id);
            
            CacheUtil.remove(XIETONG_STUDENT_ITEM_CACHE, student_id);
        }

        return success;
    }
    
    public Boolean allDelete(String system_update_user_id) {
        
        Boolean result = xietongStudentDao.allDelete(system_update_user_id);

        if(result) {
            UserService.instance.userTypeDelete(UserType.STUDENT.getKey(), system_update_user_id);
            
            CacheUtil.removeAll(XIETONG_STUDENT_ITEM_CACHE);
        }

        return result;
    }

    public void upload(UploadFile uploadFile, String request_user_id, String request_app_id) {
        String suffix = uploadFile.getFileName().substring(uploadFile.getFileName().lastIndexOf(".") + 1);

        if (".xls.xlsx".contains(suffix)) {
            try {
                InputStream is = new FileInputStream(uploadFile.getFile());
                POIFSFileSystem fs = new POIFSFileSystem(is);
                @SuppressWarnings("resource")
                HSSFWorkbook wb = new HSSFWorkbook(fs);
                HSSFSheet sheet = wb.getSheetAt(0);
                int trLength = sheet.getLastRowNum();

                List<XietongClazz> clazzList = XietongClazzService.instance.allList(request_app_id);

                for(int i = 1; i <= trLength; i++) {
                    HSSFRow row = sheet.getRow(i);

                    HSSFCell clazzCell = row.getCell(0);
                    clazzCell.setCellType(CellType.STRING);

                    HSSFCell numberCell = row.getCell(1);
                    numberCell.setCellType(CellType.STRING);

                    HSSFCell nameCell = row.getCell(2);
                    nameCell.setCellType(CellType.STRING);

                    HSSFCell sexCell = row.getCell(3);
                    sexCell.setCellType(CellType.STRING);

                    HSSFCell passwordCell = row.getCell(4);
                    passwordCell.setCellType(CellType.STRING);

                    String student_clazz = clazzCell.getStringCellValue();
                    String student_number = clazzCell.getStringCellValue() + (numberCell.getStringCellValue().length() == 1 ? "0" : "") + numberCell.getStringCellValue();
                    String student_name = nameCell.getStringCellValue();
                    String student_category_id_id = "";
                    String student_sex = sexCell.getStringCellValue();
                    String user_password = passwordCell.getStringCellValue();

                    if(ValidateUtil.isNullOrEmpty(student_number) || ValidateUtil.isNullOrEmpty(student_name) || ValidateUtil.isNullOrEmpty(student_sex)) {

                    } else {
                        String clazz_id = "";

                        for(XietongClazz clazz : clazzList) {
                            if(clazz.getClazz_name().equals(student_clazz)) {
                                clazz_id = clazz.getClazz_id();

                                break;
                            }
                        }

                        if(clazz_id != "") {
                            User user = new User();
                            user.setUser_password(user_password);

                            XietongStudent student = new XietongStudent();
                            student.setApp_id(request_app_id);
                            student.setClazz_id(clazz_id);
                            student.setStudent_category_id(student_category_id_id);
                            student.setStudent_name(student_name);
                            student.setStudent_number(student_number);
                            student.setStudent_sex(student_sex);
                            save(student, user, request_user_id);
                        }
                    }
                }
            } catch (FileNotFoundException e) {
                throw new RuntimeException("上传文件格式不正确!");
            } catch (IOException e) {
                throw new RuntimeException("上传文件格式不正确!");
            } finally {
                uploadFile.getFile().delete();
            }
        } else {
            uploadFile.getFile().delete();

            throw new RuntimeException("上传文件格式不正确!");
        }
    }

}