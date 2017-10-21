package com.nowui.chuangshi.api.minhang.service;

import com.nowui.chuangshi.api.minhang.dao.MinhangMemberPictureDao;
import com.nowui.chuangshi.api.minhang.model.MinhangMemberPicture;
import com.nowui.chuangshi.common.service.Service;
import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.util.CacheUtil;

import java.util.List;

public class MinhangMemberPictureService extends Service {

    public static final MinhangMemberPictureService instance = new MinhangMemberPictureService();
    private final String MINHANG_MEMBER_PICTURE_ITEM_CACHE = "minhang_member_picture_item_cache";
    private final MinhangMemberPictureDao minhangMemberPictureDao = new MinhangMemberPictureDao();

    public Integer adminCount(String app_id, String member_id, String task_id) {
        Cnd cnd = new Cnd();
        cnd.where(MinhangMemberPicture.SYSTEM_STATUS, true);
        cnd.and(MinhangMemberPicture.APP_ID, app_id);
        cnd.andAllowEmpty(MinhangMemberPicture.MEMBER_ID, member_id);
        cnd.andAllowEmpty(MinhangMemberPicture.TASK_ID, task_id);

        Integer count = minhangMemberPictureDao.count(cnd);
        return count;
    }

    public List<MinhangMemberPicture> adminList(String app_id, String member_id, String task_id, Integer m, Integer n) {
        Cnd cnd = new Cnd();
        cnd.where(MinhangMemberPicture.SYSTEM_STATUS, true);
        cnd.and(MinhangMemberPicture.APP_ID, app_id);
        cnd.andAllowEmpty(MinhangMemberPicture.MEMBER_ID, member_id);
        cnd.andAllowEmpty(MinhangMemberPicture.TASK_ID, task_id);
        cnd.paginate(m, n);

        List<MinhangMemberPicture> minhang_member_pictureList = minhangMemberPictureDao.primaryKeyList(cnd);
        for (MinhangMemberPicture minhang_member_picture : minhang_member_pictureList) {
            minhang_member_picture.put(find(minhang_member_picture.getMember_picture_id()));
        }
        return minhang_member_pictureList;
    }

    public MinhangMemberPicture find(String member_picture_id) {
        MinhangMemberPicture minhang_member_picture = CacheUtil.get(MINHANG_MEMBER_PICTURE_ITEM_CACHE, member_picture_id);

        if (minhang_member_picture == null) {
            minhang_member_picture = minhangMemberPictureDao.find(member_picture_id);

            CacheUtil.put(MINHANG_MEMBER_PICTURE_ITEM_CACHE, member_picture_id, minhang_member_picture);
        }

        return minhang_member_picture;
    }
    
    public MinhangMemberPicture userAndTaskFind(String user_id, String task_id) {
        Cnd cnd = new Cnd();
        cnd.where(MinhangMemberPicture.SYSTEM_STATUS, true);
        cnd.andAllowEmpty(MinhangMemberPicture.USER_ID, user_id);
        cnd.andAllowEmpty(MinhangMemberPicture.TASK_ID, task_id);

        List<MinhangMemberPicture> minhang_member_pictureList = minhangMemberPictureDao.primaryKeyList(cnd);
        if (minhang_member_pictureList == null || minhang_member_pictureList.size() == 0) {
            return null;
        }
        return find(minhang_member_pictureList.get(0).getMember_picture_id());
    }
    
    public MinhangMemberPicture historyFind(String member_history_id) {
        Cnd cnd = new Cnd();
        cnd.where(MinhangMemberPicture.SYSTEM_STATUS, true);
        cnd.andAllowEmpty(MinhangMemberPicture.MEMBER_HISTORY_ID, member_history_id);

        List<MinhangMemberPicture> minhang_member_pictureList = minhangMemberPictureDao.primaryKeyList(cnd);
        if (minhang_member_pictureList == null || minhang_member_pictureList.size() == 0) {
            return null;
        }
        return find(minhang_member_pictureList.get(0).getMember_picture_id());
    }

    public Boolean save(MinhangMemberPicture minhang_member_picture, String system_create_user_id) {
        Boolean success = minhangMemberPictureDao.save(minhang_member_picture, system_create_user_id);
        return success;
    }

    public Boolean update(MinhangMemberPicture minhang_member_picture, String member_picture_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(MinhangMemberPicture.SYSTEM_STATUS, true);
        cnd.and(MinhangMemberPicture.MEMBER_PICTURE_ID, member_picture_id);
        cnd.and(MinhangMemberPicture.SYSTEM_VERSION, system_version);

        Boolean success = minhangMemberPictureDao.update(minhang_member_picture, system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(MINHANG_MEMBER_PICTURE_ITEM_CACHE, member_picture_id);
        }

        return success;
    }

    public Boolean delete(String member_picture_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(MinhangMemberPicture.SYSTEM_STATUS, true);
        cnd.and(MinhangMemberPicture.MEMBER_PICTURE_ID, member_picture_id);
        cnd.and(MinhangMemberPicture.SYSTEM_VERSION, system_version);

        Boolean success = minhangMemberPictureDao.delete(system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(MINHANG_MEMBER_PICTURE_ITEM_CACHE, member_picture_id);
        }

        return success;
    }

}