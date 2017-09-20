package com.nowui.chuangshi.api.xietong.service;

import com.nowui.chuangshi.api.xietong.dao.XietongTeacherRecruitmentDao;
import com.nowui.chuangshi.api.xietong.model.XietongTeacherRecruitment;
import com.nowui.chuangshi.common.service.Service;
import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.util.CacheUtil;

import java.util.List;

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
        cnd.paginate(m, n);

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

}