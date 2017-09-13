package com.nowui.chuangshi.api.xietong.service;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.upload.UploadFile;
import com.nowui.chuangshi.api.xietong.dao.XietongCourseDao;
import com.nowui.chuangshi.api.xietong.model.XietongClazz;
import com.nowui.chuangshi.api.xietong.model.XietongCourse;
import com.nowui.chuangshi.api.xietong.model.XietongCourseApply;
import com.nowui.chuangshi.api.xietong.model.XietongCourseConfig;
import com.nowui.chuangshi.api.xietong.model.XietongCourseStudent;
import com.nowui.chuangshi.api.xietong.model.XietongStudent;
import com.nowui.chuangshi.common.render.ExcelRender;
import com.nowui.chuangshi.common.service.Service;
import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.type.CourseStudentType;
import com.nowui.chuangshi.util.CacheUtil;
import com.nowui.chuangshi.util.DateUtil;
import com.nowui.chuangshi.util.Util;

public class XietongCourseService extends Service {

    public static final XietongCourseService instance = new XietongCourseService();
    private final String XIETONG_COURSE_ITEM_CACHE = "xietong_course_item_cache";
    private final String XIETONG_COURSE_LIMIT_CACHE = "xietong_course_limit_cache";
    private final XietongCourseDao xietongCourseDao = new XietongCourseDao();

    public Integer adminCount(String app_id, String course_name) {
        Cnd cnd = new Cnd();        
        cnd.where(XietongCourse.SYSTEM_STATUS, true);
        cnd.and(XietongCourse.APP_ID, app_id);
        cnd.andLikeAllowEmpty(XietongCourse.COURSE_NAME, course_name);

        Integer count = xietongCourseDao.count(cnd);
        return count;
    }

    public List<XietongCourse> adminList(String app_id, String course_name, Integer m, Integer n) {
        Cnd cnd = new Cnd();        
        cnd.where(XietongCourse.SYSTEM_STATUS, true);
        cnd.and(XietongCourse.APP_ID, app_id);
        cnd.andLikeAllowEmpty(XietongCourse.COURSE_NAME, course_name);
        cnd.asc(XietongCourse.COURSE_TIME);
        cnd.desc(XietongCourse.SYSTEM_CREATE_TIME);
        cnd.paginate(m, n);

        List<XietongCourse> xietong_courseList = xietongCourseDao.primaryKeyList(cnd);
        for (XietongCourse xietong_course : xietong_courseList) {
            xietong_course.put(find(xietong_course.getCourse_id()));
        }
        return xietong_courseList;
    }
    
    public List<XietongCourse> userList(String user_id) {
        List<XietongCourse> xietongCourseList = xietongCourseDao.userList(user_id);
        
        List<XietongCourseApply> xietongCourseApplyList = XietongCourseApplyService.instance.userList(user_id);
        
        for (XietongCourse course : xietongCourseList) {
            course.put(XietongCourse.IS_APPLY, false);

            for (XietongCourseApply courseApply : xietongCourseApplyList) {
                if (course.getCourse_id().equals(courseApply.getCourse_id())) {
                    course.put(XietongCourse.IS_APPLY, true);

                    break;
                }
            }
        }

        return xietongCourseList;
    }
    
    public List<XietongCourse> allList() {
        Cnd cnd = new Cnd();        
        cnd.where(XietongCourse.SYSTEM_STATUS, true);
        cnd.asc(XietongCourse.COURSE_TIME);
        cnd.desc(XietongCourse.SYSTEM_CREATE_TIME);
        
        List<XietongCourse> xietong_courseList = xietongCourseDao.primaryKeyList(cnd);
        for (XietongCourse xietong_course : xietong_courseList) {
            xietong_course.put(find(xietong_course.getCourse_id()));
        }
        return xietong_courseList;
    }

    public XietongCourse find(String course_id) {
        XietongCourse xietong_course = CacheUtil.get(XIETONG_COURSE_ITEM_CACHE, course_id);

        if (xietong_course == null) {
            xietong_course = xietongCourseDao.find(course_id);

            CacheUtil.put(XIETONG_COURSE_ITEM_CACHE, course_id, xietong_course);
        }

        return xietong_course;
    }
    
    public XietongCourse find(String course_id, String request_user_id) {
        XietongCourse xietong_course = find(course_id);

        List<XietongCourseApply> courseApplyList = XietongCourseApplyService.instance.userList(request_user_id);
        boolean isApply = false;
        for (XietongCourseApply courseApply : courseApplyList) {
            if (xietong_course.getCourse_id().equals(courseApply.getCourse_id())) {
                isApply = true;

                break;
            }
        }

        xietong_course.put(XietongCourse.IS_APPLY, isApply);

        if (isApply) {
            xietong_course.put(XietongCourse.IS_LIMIT, false);
        } else {
            XietongCourse c = CacheUtil.get(XIETONG_COURSE_LIMIT_CACHE, xietong_course.getCourse_id());
            if (c == null) {
                xietong_course.put(XietongCourse.IS_LIMIT, false);

                List<XietongCourseStudent> courseStudentList = XietongCourseStudentService.instance.list(xietong_course.getCourse_id(), CourseStudentType.BLACK.getKey());
                for (XietongCourseStudent courseStudent : courseStudentList) {
                    XietongStudent student = XietongStudentService.instance.userFind(request_user_id);

                    if (courseStudent.getStudent_id().equals(student.getStudent_id())) {
                        xietong_course.put(XietongCourse.IS_LIMIT, true);

                        break;
                    }
                }

            } else {
                xietong_course.put(XietongCourse.IS_LIMIT, true);
            }
        }

        return xietong_course;
    }

    public Boolean save(XietongCourse xietong_course, String system_create_user_id) {
        Boolean success = xietongCourseDao.save(xietong_course, system_create_user_id);
        return success;
    }

    public Boolean update(XietongCourse xietong_course, String course_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();        
        cnd.where(XietongCourse.SYSTEM_STATUS, true);
        cnd.and(XietongCourse.COURSE_ID, course_id);
        cnd.and(XietongCourse.SYSTEM_VERSION, system_version);

        Boolean success = xietongCourseDao.update(xietong_course, system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(XIETONG_COURSE_ITEM_CACHE, course_id);
        }

        return success;
    }

    public Boolean delete(String course_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();        
        cnd.where(XietongCourse.SYSTEM_STATUS, true);
        cnd.and(XietongCourse.COURSE_ID, course_id);
        cnd.and(XietongCourse.SYSTEM_VERSION, system_version);

        Boolean success = xietongCourseDao.delete(system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(XIETONG_COURSE_ITEM_CACHE, course_id);
        }

        return success;
    }
    
    public Boolean allDelete(String system_update_user_id) {
        
        Boolean result = xietongCourseDao.allDelete(system_update_user_id);

        if(result) {
            CacheUtil.removeAll(XIETONG_COURSE_ITEM_CACHE);
        }

        return result;
    }
    
    public ExcelRender export(String request_app_id) {
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);

        List<Record> teacherList = Db.find("select teacher_id, teacher_name from xietong_teacher where system_status = 1");

        List<Record> gradeList = Db.find("select clazz_id, clazz_name from xietong_clazz where system_status = 1");

        HSSFSheet sheet = wb.createSheet("总-按科目");

        HSSFRow row = sheet.createRow(0);
        HSSFCell cell = row.createCell(0);
        cell.setCellValue("班级");
        cell.setCellStyle(style);
        cell = row.createCell(1);
        cell.setCellValue("老师");
        cell.setCellStyle(style);
        cell = row.createCell(2);
        cell.setCellValue("课程名称");
        cell.setCellStyle(style);
        cell = row.createCell(3);
        cell.setCellValue("课程时间");
        cell.setCellStyle(style);
        cell = row.createCell(4);
        cell.setCellValue("申请限制");
        cell.setCellStyle(style);
        cell = row.createCell(5);
        cell.setCellValue("课程地址");
        cell.setCellStyle(style);
        cell = row.createCell(6);
        cell.setCellValue("课程图片");
        cell.setCellStyle(style);
        cell = row.createCell(7);
        cell.setCellValue("课程介绍");
        cell.setCellStyle(style);

        List<XietongCourse> courseList = allList();
        for (int i = 0; i < courseList.size(); i++) {
            XietongCourse course = courseList.get(i);

            String course_clazz = course.getClazz_id();
            for (Record record : gradeList) {
                if (course_clazz.contains(record.getStr("grade_id"))) {
                    course_clazz = course_clazz.replace(record.getStr("grade_id"), record.getStr("grade_name"));
                }
            }
            course_clazz = course_clazz.replace("[\"", "").replace("\"]", "").replace("\",\"", ",");

            String course_teacher = course.getCourse_teacher();
            for (Record record : teacherList) {
                if (course_teacher.contains(record.getStr("teacher_id"))) {
                    course_teacher = course_teacher.replace(record.getStr("teacher_id"), record.getStr("teacher_name"));
                }
            }
            course_teacher = course_teacher.replace("[\"", "").replace("\"]", "").replace("\",\"", ",");

            row = sheet.createRow(i + 1);
            cell = row.createCell(0);
            cell.setCellValue(course_clazz);
            cell.setCellStyle(style);
            cell = row.createCell(1);
            cell.setCellValue(course_teacher);
            cell.setCellStyle(style);
            cell = row.createCell(2);
            cell.setCellValue(course.getCourse_name());
            cell.setCellStyle(style);
            cell = row.createCell(3);
            cell.setCellValue(getCourse_time(course.getCourse_time()));
            cell.setCellStyle(style);
            cell = row.createCell(4);
            cell.setCellValue(course.getCourse_apply_limit());
            cell.setCellStyle(style);
            cell = row.createCell(5);
            cell.setCellValue(course.getCourse_address());
            cell.setCellStyle(style);
            cell = row.createCell(6);
            cell.setCellValue(course.getCourse_image());
            cell.setCellStyle(style);
            cell = row.createCell(7);
            cell.setCellValue(course.getCourse_content());
            cell.setCellStyle(style);
        }

        return new ExcelRender(wb, "选课信息");
    }

    @SuppressWarnings({ "unused", "resource" })
    public void upload(UploadFile uploadFile, String request_user_id, String request_app_id) {
        String suffix = uploadFile.getFileName().substring(uploadFile.getFileName().lastIndexOf(".") + 1);
        Workbook wb = null;
        InputStream is = null;
        if (".xls.xlsx".contains(suffix)) {
            try {
                is = new FileInputStream(uploadFile.getFile());
                if (uploadFile.getFileName().endsWith(Constant.EXCEL03_EXTENSION)) {
                    POIFSFileSystem fs = new POIFSFileSystem(new BufferedInputStream(is));
                    wb = new HSSFWorkbook(fs);
                } else if (uploadFile.getFileName().endsWith(Constant.EXCEL07_EXTENSION)) {
                	wb = new XSSFWorkbook(is);
                } else {
                	throw new RuntimeException("上传文件格式不正确!");
                }

                if (wb == null) {
                	throw new RuntimeException("excel文件内容为空！");
                }
                Sheet sheet = wb.getSheetAt(0);
                int trLength = sheet.getLastRowNum();

                List<XietongClazz> clazzList = XietongClazzService.instance.allList(request_app_id);

                for (int i = 1; i <= trLength; i++) {
                    Row row = sheet.getRow(i);

                    Cell clazzCell = row.getCell(0);
                    clazzCell.setCellType(CellType.STRING);

                    Cell teacherCell = row.getCell(1);
                    teacherCell.setCellType(CellType.STRING);

                    Cell nameCell = row.getCell(2);
                    nameCell.setCellType(CellType.STRING);

                    Cell timeCell = row.getCell(3);
                    timeCell.setCellType(CellType.STRING);

                    Cell limitCell = row.getCell(4);
                    limitCell.setCellType(CellType.STRING);

                    Cell addressCell = row.getCell(5);
                    addressCell.setCellType(CellType.STRING);

                    Cell contentCell = row.getCell(6);
                    contentCell.setCellType(CellType.STRING);

                    String clazz_id = clazzCell.getStringCellValue();
                    String course_teacher = teacherCell.getStringCellValue();
                    String course_name = nameCell.getStringCellValue();
                    String time = timeCell.getStringCellValue();
                    String course_apply_limit = limitCell.getStringCellValue();
                    String course_address = addressCell.getStringCellValue();
                    String course_content = contentCell.getStringCellValue();

                    Integer course_time = 0;
                    switch (time) {
                        case "星期一第七节":
                            course_time = 17;
                            break;
                        case "星期二第七节":
                            course_time = 27;
                            break;
                        case "星期二第八节":
                            course_time = 28;
                            break;
                        case "星期四第七节":
                            course_time = 47;
                            break;
                        case "星期四第八节":
                            course_time = 48;
                            break;
                        case "星期五第六节":
                            course_time = 56;
                            break;
                    }

                    for (XietongClazz clazz : clazzList) {
                        if (clazz_id.contains(clazz.getClazz_name())) {
                            clazz_id = clazz_id.replace(clazz.getClazz_name(), clazz.getClazz_id());
                        }
                    }

                    clazz_id = clazz_id.replace(",", "\",\"");
                    clazz_id = "[\"" + clazz_id + "\"]";

                    if (clazz_id != "") {
                        XietongCourse course = new XietongCourse();
                        course.setCourse_id(Util.getRandomUUID());
                        course.setApp_id(request_app_id);
                        course.setClazz_id(clazz_id);
                        course.setCourse_teacher(course_teacher);
                        course.setCourse_name(course_name);
                        course.setCourse_time(course_time);
                        course.setCourse_apply_limit(Integer.valueOf(course_apply_limit));
                        course.setCourse_address(course_address);
                        course.setCourse_image("");
                        course.setCourse_content(course_content);
                        save(course, request_user_id);
                    }
                }
            } catch (FileNotFoundException e) {
                throw new RuntimeException("上传文件格式不正确!");
            } catch (IOException e) {
                throw new RuntimeException("上传文件格式不正确!");
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("excel文件不符合规格或者工作表错误");
            } finally {
                uploadFile.getFile().delete();
                
                if (wb != null) {
                    try {
                    	wb.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (is != null) {
                    try {
                        is.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        } else {
            uploadFile.getFile().delete();

            throw new RuntimeException("上传文件格式不正确!");
        }
    }
    
    public void studentWhiteApplySave(String request_user_id, String request_app_id) {
        List<XietongCourse> courseList = allList();

        for (XietongCourse course : courseList) {
            List<XietongCourseStudent> courseStudentList = XietongCourseStudentService.instance.list(course.getCourse_id(), CourseStudentType.WHITE.getKey());

            for (XietongCourseStudent courseStudent : courseStudentList) {
                XietongStudent student = XietongStudentService.instance.find(courseStudent.getStudent_id());

                XietongCourseApplyService.instance.save(course.getCourse_id(), course, student.getUser_id(), request_app_id);
            }
        }
    }
    
    private String getCourse_time(int time) {
        String course_time = "";
        switch (time) {
            case 17:
                course_time = "星期一第七节";
                break;
            case 27:
                course_time = "星期二第七节";
                break;
            case 28:
                course_time = "星期二第八节";
                break;
            case 47:
                course_time = "星期四第七节";
                break;
            case 48:
                course_time = "星期四第八节";
                break;
            case 56:
                course_time = "星期五第六节";
                break;
        }

        return course_time;
    }
    
    private void check(XietongClazz clazz, String request_user_id, String request_app_id) {
        Date nowDate = new Date();

        Date clazzStartDate = DateUtil.getDateTime(clazz.getClazz_course_apply_start_time());
        Date clazzEndDate = DateUtil.getDateTime(clazz.getClazz_course_apply_end_time());

        if(nowDate.before(clazzStartDate)) {
            throw new RuntimeException(clazz.getClazz_name() + "班于" + clazz.getClazz_course_apply_start_time() + "正式开放申请!");
        }

        if(nowDate.after(clazzEndDate)) {
            throw new RuntimeException(clazz.getClazz_name() + "班已经停止申请!");
        }

        XietongCourseConfig config = XietongCourseConfigService.instance.appFind(request_app_id);

        if (config == null) {
            throw new RuntimeException("该系统还没有开放申请!");
        }

        Date configStartDate = DateUtil.getDateTime(config.getCourse_config_apply_start_time());
        Date configEndDate = DateUtil.getDateTime(config.getCourse_config_apply_end_time());

        if(nowDate.before(configStartDate)) {
            throw new RuntimeException(config.getCourse_config_apply_start_time() + "正式开放申请!");
        }

        if(nowDate.after(configEndDate)) {
            throw new RuntimeException("已经过期，停止申请!");
        }
    }
    
    public boolean applySave(XietongCourseApply course_apply, String request_user_id, String request_app_id) {
        String course_id = course_apply.getCourse_id();

        XietongStudent student = XietongStudentService.instance.userFind(request_user_id);
        XietongClazz clazz = XietongClazzService.instance.find(student.getClazz_id());

        check(clazz, request_user_id, request_app_id);

        XietongCourse c = CacheUtil.get(XIETONG_COURSE_LIMIT_CACHE, course_id);
        if (c != null) {
            throw new RuntimeException("该课程已经没有名额,不能再申请!");
        }

        XietongCourse course = find(course_id);

        int courseApplyCount = XietongCourseApplyService.instance.courseCount(course_id);

        if (courseApplyCount < course.getCourse_apply_limit()) {
            //是否黑名单
            List<XietongCourseStudent> courseStudentList = XietongCourseStudentService.instance.list(course.getCourse_id(), CourseStudentType.BLACK.getKey());
            for (XietongCourseStudent courseStudent : courseStudentList) {
                if (courseStudent.getStudent_id().equals(student.getStudent_id())) {
                    throw new RuntimeException("该课程已经没有名额,不能再申请!");
                }
            }

            int courseTimeCount = XietongCourseApplyService.instance.oneDayCount(request_user_id, course.getCourse_time());

            if (courseTimeCount == 0) {
                int clazzCourseApplyLimit = XietongCourseApplyService.instance.userCount(request_user_id);

                if (clazz.getClazz_course_apply_limit() > clazzCourseApplyLimit) {
                    int count = XietongCourseApplyService.instance.courseAndUserCount(course_id, request_user_id);

                    if (count == 0) {
                        boolean result = XietongCourseApplyService.instance.save(course_id, course, request_user_id, request_app_id);

                        if (result) {

                        } else {
                            throw new RuntimeException("申请不成功，请稍后再试！");
                        }
                    } else {
                        throw new RuntimeException("已经申请过该课程,不能再申请!");
                    }
                } else {
                    throw new RuntimeException("您已经申请了" + clazz.getClazz_course_apply_limit() + "门课程,不能再申请!");
                }
            } else {
                int day = course.getCourse_time() / 10;

                String str = "";
                switch (day) {
                    case 1:
                        str = "星期一";
                        break;
                    case 2:
                        str = "星期二";
                        break;
                    case 3:
                        str = "星期三";
                        break;
                    case 4:
                        str = "星期四";
                        break;
                    case 5:
                        str = "星期五";
                        break;
                    case 6:
                        str = "星期六";
                        break;
                    case 7:
                        str = "星期日";
                        break;
                }

                throw new RuntimeException("您在" + str + "已经申请过课程,不能再申请!");
            }
        } else {
            CacheUtil.put(XIETONG_COURSE_LIMIT_CACHE, course_id, course);

            throw new RuntimeException("该课程已经没有名额,不能再申请!");
        }

        return false;
    }

    public boolean applyDelete(String course_id, String request_user_id, String request_app_id) {
        XietongStudent student = XietongStudentService.instance.userFind(request_user_id);
        XietongClazz clazz = XietongClazzService.instance.find(student.getClazz_id());

        check(clazz, request_user_id, request_app_id);

        boolean result = XietongCourseApplyService.instance.courseAndUserDelete(course_id, request_user_id, request_user_id, request_app_id);

        if (result) {
            //移除课程限制缓存
            CacheUtil.remove(XIETONG_COURSE_LIMIT_CACHE, course_id);
        }
        return result;
    }

    public boolean applyAllDelete(String request_user_id) {
        return XietongCourseApplyService.instance.allDelete(request_user_id);
    }
    
    public ExcelRender applyExport() {
        List<XietongCourseApply> courseApplyListOrderByCourse_id = XietongCourseApplyService.instance.courseIdAndCourseTimeAndStudentNumberOrderByList();
        List<XietongCourseApply> courseApplyListOrderByGrade_idAndStudent_id = XietongCourseApplyService.instance.clazzNameAndStudentIdAndCourseTimeOrderByList();

        List<XietongCourse> courseList = allList();

        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);

        HSSFSheet sheet = wb.createSheet("选修情况汇总");

        HSSFRow row = sheet.createRow(0);
        HSSFCell cell = row.createCell(0);
        cell.setCellValue("课程名称");
        cell.setCellStyle(style);
        cell = row.createCell(1);
        cell.setCellValue("上课时间");
        cell.setCellStyle(style);
        cell = row.createCell(2);
        cell.setCellValue("上课老师");
        cell.setCellStyle(style);
        cell = row.createCell(3);
        cell.setCellValue("上课地点");
        cell.setCellStyle(style);
        cell = row.createCell(4);
        cell.setCellValue("限制人数");
        cell.setCellStyle(style);
        cell = row.createCell(5);
        cell.setCellValue("已报人数");
        cell.setCellStyle(style);

        for (int i = 0; i < courseList.size(); i++) {
            XietongCourse course = courseList.get(i);

            int count = XietongCourseApplyService.instance.courseCount(course.getCourse_id());

            row = sheet.createRow(i + 1);
            cell = row.createCell(0);
            cell.setCellValue(course.getCourse_name());
            cell.setCellStyle(style);
            cell = row.createCell(1);
            cell.setCellValue(getCourse_time(course.getCourse_time()));
            cell.setCellStyle(style);
            cell = row.createCell(2);
            cell.setCellValue(course.getCourse_teacher());
            cell.setCellStyle(style);
            cell = row.createCell(3);
            cell.setCellValue(course.getCourse_address());
            cell.setCellStyle(style);
            cell = row.createCell(4);
            cell.setCellValue(course.getCourse_apply_limit());
            cell.setCellStyle(style);
            cell = row.createCell(5);
            cell.setCellValue(count);
            cell.setCellStyle(style);
        }

        sheet = wb.createSheet("总-按科目");

        row = sheet.createRow(0);
        cell = row.createCell(0);
        cell.setCellValue("姓名");
        cell.setCellStyle(style);
        cell = row.createCell(1);
        cell.setCellValue("班级");
        cell.setCellStyle(style);
        cell = row.createCell(2);
        cell.setCellValue("学号");
        cell.setCellStyle(style);
        cell = row.createCell(3);
        cell.setCellValue("性别");
        cell.setCellStyle(style);
        cell = row.createCell(4);
        cell.setCellValue("课程名称");
        cell.setCellStyle(style);
        cell = row.createCell(5);
        cell.setCellValue("上课时间");
        cell.setCellStyle(style);
        cell = row.createCell(6);
        cell.setCellValue("上课老师");
        cell.setCellStyle(style);
        cell = row.createCell(7);
        cell.setCellValue("上课地点");
        cell.setCellStyle(style);

        for(int i = 0; i < courseApplyListOrderByCourse_id.size(); i++) {
            XietongCourseApply courseApply = courseApplyListOrderByCourse_id.get(i);

            row = sheet.createRow(i + 1);
            cell = row.createCell(0);
            cell.setCellValue(courseApply.getStr(XietongStudent.STUDENT_NAME));
            cell.setCellStyle(style);
            cell = row.createCell(1);
            cell.setCellValue(courseApply.getStr(XietongClazz.CLAZZ_NAME));
            cell.setCellStyle(style);
            cell = row.createCell(2);
            cell.setCellValue(courseApply.getStr(XietongStudent.STUDENT_NUMBER));
            cell.setCellStyle(style);
            cell = row.createCell(3);
            cell.setCellValue(courseApply.getStr(XietongStudent.STUDENT_SEX));
            cell.setCellStyle(style);
            cell = row.createCell(4);
            cell.setCellValue(courseApply.getStr(XietongCourse.COURSE_NAME));
            cell.setCellStyle(style);
            cell = row.createCell(5);
            cell.setCellValue(getCourse_time(courseApply.getInt("course_time")));
            cell.setCellStyle(style);
            cell = row.createCell(6);
            cell.setCellValue(courseApply.getStr(XietongCourse.COURSE_TEACHER));
            cell.setCellStyle(style);
            cell = row.createCell(7);
            cell.setCellValue(courseApply.getStr(XietongCourse.COURSE_ADDRESS));
            cell.setCellStyle(style);
        }

        sheet = wb.createSheet("总-按班级");

        row = sheet.createRow(0);
        cell = row.createCell(0);
        cell.setCellValue("姓名");
        cell.setCellStyle(style);
        cell = row.createCell(1);
        cell.setCellValue("班级");
        cell.setCellStyle(style);
        cell = row.createCell(2);
        cell.setCellValue("学号");
        cell.setCellStyle(style);
        cell = row.createCell(3);
        cell.setCellValue("性别");
        cell.setCellStyle(style);
        cell = row.createCell(4);
        cell.setCellValue("课程名称");
        cell.setCellStyle(style);
        cell = row.createCell(5);
        cell.setCellValue("上课时间");
        cell.setCellStyle(style);
        cell = row.createCell(6);
        cell.setCellValue("上课老师");
        cell.setCellStyle(style);
        cell = row.createCell(7);
        cell.setCellValue("上课地点");
        cell.setCellStyle(style);

        for(int i = 0; i < courseApplyListOrderByGrade_idAndStudent_id.size(); i++) {
            XietongCourseApply courseApply = courseApplyListOrderByGrade_idAndStudent_id.get(i);

            String teacher_name = courseApply.getStr(XietongCourse.COURSE_TEACHER);

            row = sheet.createRow(i + 1);
            cell = row.createCell(0);
            cell.setCellValue(courseApply.getStr(XietongStudent.STUDENT_NAME));
            cell.setCellStyle(style);
            cell = row.createCell(1);
            cell.setCellValue(courseApply.getStr(XietongClazz.CLAZZ_NAME));
            cell.setCellStyle(style);
            cell = row.createCell(2);
            cell.setCellValue(courseApply.getStr(XietongStudent.STUDENT_NUMBER));
            cell.setCellStyle(style);
            cell = row.createCell(3);
            cell.setCellValue(courseApply.getStr(XietongStudent.STUDENT_SEX));
            cell.setCellStyle(style);
            cell = row.createCell(4);
            cell.setCellValue(courseApply.getStr(XietongCourse.COURSE_NAME));
            cell.setCellStyle(style);
            cell = row.createCell(5);
            cell.setCellValue(getCourse_time(courseApply.getInt("course_time")));
            cell.setCellStyle(style);
            cell = row.createCell(6);
            cell.setCellValue(teacher_name);
            cell.setCellStyle(style);
            cell = row.createCell(7);
            cell.setCellValue(courseApply.getStr(XietongCourse.COURSE_ADDRESS));
            cell.setCellStyle(style);
        }

        String course_id = "";
        int index = 0;
        for(int i = 0; i < courseApplyListOrderByCourse_id.size(); i++) {
            XietongCourseApply courseApply = courseApplyListOrderByCourse_id.get(i);

            if(! course_id.equals(courseApply.getCourse_id())) {
                course_id = courseApply.getCourse_id();

                index = 0;

                sheet = wb.createSheet(i + 1 + "、" + courseApply.getStr(XietongCourse.COURSE_NAME));

                row = sheet.createRow(0);
                cell = row.createCell(0);
                cell.setCellValue("姓名");
                cell.setCellStyle(style);
                cell = row.createCell(1);
                cell.setCellValue("班级");
                cell.setCellStyle(style);
                cell = row.createCell(2);
                cell.setCellValue("学号");
                cell.setCellStyle(style);
                cell = row.createCell(3);
                cell.setCellValue("性别");
                cell.setCellStyle(style);
                cell = row.createCell(4);
                cell.setCellValue("课程名称");
                cell.setCellStyle(style);
                cell = row.createCell(5);
                cell.setCellValue("上课时间");
                cell.setCellStyle(style);
                cell = row.createCell(6);
                cell.setCellValue("上课老师");
                cell.setCellStyle(style);
                cell = row.createCell(7);
                cell.setCellValue("上课地点");
                cell.setCellStyle(style);
            }

            String teacher_name = courseApply.getStr(XietongCourse.COURSE_TEACHER);

            row = sheet.createRow(index + 1);
            cell = row.createCell(0);
            cell.setCellValue(courseApply.getStr(XietongStudent.STUDENT_NAME));
            cell.setCellStyle(style);
            cell = row.createCell(1);
            cell.setCellValue(courseApply.getStr(XietongClazz.CLAZZ_NAME));
            cell.setCellStyle(style);
            cell = row.createCell(2);
            cell.setCellValue(courseApply.getStr(XietongStudent.STUDENT_NUMBER));
            cell.setCellStyle(style);
            cell = row.createCell(3);
            cell.setCellValue(courseApply.getStr(XietongStudent.STUDENT_SEX));
            cell.setCellStyle(style);
            cell = row.createCell(4);
            cell.setCellValue(courseApply.getStr(XietongCourse.COURSE_NAME));
            cell.setCellStyle(style);
            cell = row.createCell(5);
            cell.setCellValue(getCourse_time(courseApply.getInt("course_time")));
            cell.setCellStyle(style);
            cell = row.createCell(6);
            cell.setCellValue(teacher_name);
            cell.setCellStyle(style);
            cell = row.createCell(7);
            cell.setCellValue(courseApply.getStr(XietongCourse.COURSE_ADDRESS));
            cell.setCellStyle(style);

            index++;
        }

        return new ExcelRender(wb, "选课信息");
    }

}