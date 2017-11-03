package com.nowui.chuangshi.api.renault.service;

import com.nowui.chuangshi.api.file.service.FileService;
import com.nowui.chuangshi.api.renault.dao.RenaultShareCommentDao;
import com.nowui.chuangshi.api.renault.model.RenaultShareComment;
import com.nowui.chuangshi.api.user.model.User;
import com.nowui.chuangshi.api.user.service.UserService;
import com.nowui.chuangshi.common.service.Service;
import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.util.CacheUtil;
import com.nowui.chuangshi.util.ValidateUtil;

import java.util.List;

public class RenaultShareCommentService extends Service {

    public static final RenaultShareCommentService instance = new RenaultShareCommentService();
    private final String RENAULT_SHARE_COMMENT_ITEM_CACHE = "renault_share_comment_item_cache";
    private final RenaultShareCommentDao renaultShareCommentDao = new RenaultShareCommentDao();

    public Integer adminCount(String share_id) {
        Cnd cnd = new Cnd();
        cnd.where(RenaultShareComment.SYSTEM_STATUS, true);
        cnd.andAllowEmpty(RenaultShareComment.SHARE_ID, share_id);

        Integer count = renaultShareCommentDao.count(cnd);
        return count;
    }

    public List<RenaultShareComment> adminList(String share_id, Integer m, Integer n) {
        Cnd cnd = new Cnd();
        cnd.where(RenaultShareComment.SYSTEM_STATUS, true);
        cnd.and(RenaultShareComment.SHARE_ID, share_id);
        cnd.desc(RenaultShareComment.SYSTEM_CREATE_TIME);
        cnd.paginate(m, n);

        List<RenaultShareComment> renault_share_commentList = renaultShareCommentDao.primaryKeyList(cnd);
        for (RenaultShareComment renaultShareComment : renault_share_commentList) {
            
            renaultShareComment.put(find(renaultShareComment.getComment_id()));
            
            User comment_user = UserService.instance.find(renaultShareComment.getUser_id());
            renaultShareComment.put(User.USER_NAME, comment_user.getUser_name());
            String user_avatar = FileService.instance.getFile_path(comment_user.getUser_avatar());
            if (!ValidateUtil.isNullOrEmpty(user_avatar) && !user_avatar.startsWith("http://")) {  //微信头像无需处理，自己上传的头像加上前置url
                user_avatar = "http://api.chuangshi.nowui.com" + user_avatar;
            }
            renaultShareComment.put(User.USER_AVATAR, user_avatar);
        }
        return renault_share_commentList;
    }

    public RenaultShareComment find(String comment_id) {
        RenaultShareComment renault_share_comment = CacheUtil.get(RENAULT_SHARE_COMMENT_ITEM_CACHE, comment_id);

        if (renault_share_comment == null) {
            renault_share_comment = renaultShareCommentDao.find(comment_id);

            CacheUtil.put(RENAULT_SHARE_COMMENT_ITEM_CACHE, comment_id, renault_share_comment);
        }

        return renault_share_comment;
    }
    
    public Integer shareCount(String share_id) {
        Cnd cnd = new Cnd();
        cnd.where(RenaultShareComment.SYSTEM_STATUS, true);
        cnd.and(RenaultShareComment.SHARE_ID, share_id);
        
        Integer count = renaultShareCommentDao.count(cnd);
        return count;
    }

    public Boolean save(RenaultShareComment renault_share_comment, String system_create_user_id) {
        Boolean success = renaultShareCommentDao.save(renault_share_comment, system_create_user_id);
        return success;
    }

    public Boolean update(RenaultShareComment renault_share_comment, String comment_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(RenaultShareComment.SYSTEM_STATUS, true);
        cnd.and(RenaultShareComment.COMMENT_ID, comment_id);
        cnd.and(RenaultShareComment.SYSTEM_VERSION, system_version);

        Boolean success = renaultShareCommentDao.update(renault_share_comment, system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(RENAULT_SHARE_COMMENT_ITEM_CACHE, comment_id);
        }

        return success;
    }

    public Boolean delete(String comment_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(RenaultShareComment.SYSTEM_STATUS, true);
        cnd.and(RenaultShareComment.COMMENT_ID, comment_id);
        cnd.and(RenaultShareComment.SYSTEM_VERSION, system_version);

        Boolean success = renaultShareCommentDao.delete(system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(RENAULT_SHARE_COMMENT_ITEM_CACHE, comment_id);
        }

        return success;
    }

    public List<RenaultShareComment> mobileList(String share_id, Integer m, Integer n) {
        Cnd cnd = new Cnd();
        cnd.where(RenaultShareComment.SYSTEM_STATUS, true);
        cnd.and(RenaultShareComment.SHARE_ID, share_id);
        cnd.desc(RenaultShareComment.SYSTEM_CREATE_TIME);
        cnd.paginate(m, n);

        List<RenaultShareComment> renaultsharecommentList = renaultShareCommentDao.primaryKeyList(cnd);
        for (RenaultShareComment renaultsharecommentCategory : renaultsharecommentList ){
            renaultsharecommentCategory.put(find(renaultsharecommentCategory.getComment_id()));
        }
        return renaultsharecommentList;
    }

}