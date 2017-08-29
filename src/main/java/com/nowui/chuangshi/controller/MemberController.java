package com.nowui.chuangshi.controller;

import java.math.BigDecimal;
import java.util.*;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.core.ActionKey;
import com.jfinal.weixin.sdk.api.AccessTokenApi;
import com.jfinal.weixin.sdk.api.ApiConfigKit;
import com.jfinal.weixin.sdk.api.ApiResult;
import com.jfinal.weixin.sdk.api.QrcodeApi;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.constant.Url;
import com.nowui.chuangshi.model.App;
import com.nowui.chuangshi.model.Certificate;
import com.nowui.chuangshi.model.CertificatePay;
import com.nowui.chuangshi.model.Member;
import com.nowui.chuangshi.model.MemberLevel;
import com.nowui.chuangshi.model.Qrcode;
import com.nowui.chuangshi.model.User;
import com.nowui.chuangshi.service.AppService;
import com.nowui.chuangshi.service.CertificatePayService;
import com.nowui.chuangshi.service.CertificateService;
import com.nowui.chuangshi.service.FileService;
import com.nowui.chuangshi.service.MemberLevelService;
import com.nowui.chuangshi.service.MemberService;
import com.nowui.chuangshi.service.QrcodeService;
import com.nowui.chuangshi.service.UserService;
import com.nowui.chuangshi.type.QrcodeType;
import com.nowui.chuangshi.util.Util;
import com.nowui.chuangshi.util.ValidateUtil;

public class MemberController extends Controller {

    private final MemberService memberService = new MemberService();
    private final UserService userService = new UserService();
    private final MemberLevelService memberLevelService = new MemberLevelService();
    private final FileService fileService = new FileService();
    private final QrcodeService qrcodeService = new QrcodeService();
    private final AppService appService = new AppService();
    private final CertificateService certificateService = new CertificateService();
    private final CertificatePayService certificatePayService = new CertificatePayService();

    private List<Map<String, Object>> getChildren(List<Member> memberList, String member_parent_id, String... keys) {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        for (Member member : memberList) {
            if (member.getMember_parent_id().equals(member_parent_id)) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put(Member.MEMBER_ID, member.getMember_id());
                map.put(User.USER_NAME, member.getStr(User.USER_NAME));
                map.put(User.USER_AVATAR, member.getStr(User.USER_AVATAR));
                map.put(MemberLevel.MEMBER_LEVEL_NAME, member.getStr(MemberLevel.MEMBER_LEVEL_NAME));

                for (String key : keys) {
                    map.put(key, member.get(key));
                }

                List<Map<String, Object>> childrenList = getChildren(memberList, member.getMember_id(), keys);
                if (childrenList.size() > 0) {
                    map.put(Constant.CHILDREN, childrenList);
                }
                list.add(map);
            }
        }

        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = 1; j < list.size() - i; j++) {
                Map<String, Object> o1 = list.get(j - 1);
                Map<String, Object> o2 = list.get(j);
                Integer a = 0;
                Integer b = 0;
                if (o2.get(Constant.CHILDREN) != null) {
                    a = ((List<Map<String, Object>>) o2.get(Constant.CHILDREN)).size();
                }
                if (o1.get(Constant.CHILDREN) != null) {
                    b = ((List<Map<String, Object>>) o1.get(Constant.CHILDREN)).size();
                }
                if (a.compareTo(b) > 0) {
                    o1 = list.get(j - 1);
                    list.set((j - 1), list.get(j));
                    list.set(j, o1);
                }
            }
        }

//        list.sort(new Comparator<Map<String, Object>>() {
//
//            @Override
//            public int compare(Map<String, Object> o1, Map<String, Object> o2) {
//                Integer a = 0;
//                Integer b = 0;
//                if (o1.get(Constant.CHILDREN) != null) {
//                    a = ((List<Map<String, Object>>) o1.get(Constant.CHILDREN)).size();
//                }
//                if (o2.get(Constant.CHILDREN) != null) {
//                    b = ((List<Map<String, Object>>) o2.get(Constant.CHILDREN)).size();
//                }
//                if (b == a) {
//                    return 0;
//                }
//                return b.compareTo(a);
//            }
//        });
        return list;
    }

    @ActionKey(Url.MEMBER_LIST)
    public void list() {
        validateRequest_app_id();
        validate(Constant.PAGE_SIZE, Constant.FIRST_CREATE_TIME, Constant.LAST_CREATE_TIME);

        String request_app_id = getRequest_app_id();
        JSONObject jsonObject = getParameterJSONObject();

        authenticateRequest_app_idAndRequest_user_id();

        List<Member> resultList = memberService.listByApp_idAndSystem_create_timeAndLimit(request_app_id,
                jsonObject.getDate(Constant.LAST_CREATE_TIME), 0, getN());

        for (Member result : resultList) {
            result.keep(Member.MEMBER_ID, Member.SYSTEM_VERSION);
        }

        renderSuccessJson(resultList);
    }

    @ActionKey(Url.MEMBER_TEAM_LIST)
    public void teamList() {
        validateRequest_app_id();

        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        User user = userService.findByUser_id(request_user_id);

        authenticateApp_id(user.getApp_id());

        Member member = memberService.findByMember_id(user.getObject_Id());

        List<Member> memberList = memberService.listByMember_parent_pathLikeMember_parent_id(member.getMember_id());

        List<Map<String, Object>> resultList = getChildren(memberList, member.getMember_id(), Member.MEMBER_STATUS);

        renderSuccessJson(resultList);
    }

    @ActionKey(Url.MEMBER_FIND)
    public void find() {
        validateRequest_app_id();
        validate(Member.MEMBER_ID);

        Member model = getModel(Member.class);

        authenticateRequest_app_idAndRequest_user_id();

        Member member = memberService.findByMember_id(model.getMember_id());

        authenticateApp_id(member.getApp_id());
        authenticateSystem_create_user_id(member.getSystem_create_user_id());

        member.keep(Member.MEMBER_ID, Member.SYSTEM_VERSION);

        renderSuccessJson(member);
    }

    @ActionKey(Url.MEMBER_MY_FIND)
    public void myFind() {
        validateRequest_app_id();

        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        User user = userService.findByUser_id(request_user_id);
        Member member = memberService.findByMember_id(user.getObject_Id());

        Map<String, Object> result = new HashMap<String, Object>();
        result.put(User.USER_NAME, user.getUser_name());
        result.put(User.USER_AVATAR, fileService.getFile_path(user.getUser_avatar()));

        result.put(Member.MEMBER_COMMISSION_AMOUNT, 0);
        result.put(Member.MEMBER_ORDER_AMOUNT, 0);
        result.put(Member.MEMBER_WAIT_PAY, 0);
        result.put(Member.MEMBER_WAIT_SEND, 0);
        result.put(Member.MEMBER_WAIT_RECEIVE, 0);
        result.put(Member.MEMBER_STATUS, member.getMember_status());

        if (ValidateUtil.isNullOrEmpty(member.getMember_level_id())) {
            result.put(MemberLevel.MEMBER_LEVEL_NAME, "");
            result.put(MemberLevel.MEMBER_LEVEL_SORT, 999);
        } else {
            MemberLevel memberLevel = memberLevelService.findByMember_level_id(member.getMember_level_id());
            result.put(MemberLevel.MEMBER_LEVEL_NAME, memberLevel.getMember_level_name());
            result.put(MemberLevel.MEMBER_LEVEL_SORT, memberLevel.getMember_level_sort());
        }

        // 返回授权保证金
        BigDecimal certificate_amount = BigDecimal.ZERO;
        Certificate certificate = certificateService.findByUser_id(member.getUser_id());
        if (certificate != null) {
            CertificatePay certificatePay = certificatePayService.findByCertificate_id(certificate.getCertificate_id());
            if (certificatePay != null && certificatePay.getCertificate_amount() != null) {
                certificate_amount = certificatePay.getCertificate_amount();
            }
        }
        result.put(CertificatePay.CERTIFICATE_AMOUNT, certificate_amount);

        authenticateApp_id(user.getApp_id());

        renderSuccessJson(result);
    }

    @ActionKey(Url.MEMBER_QRCODE_FIND)
    public void qrcodeFind() {
        validateRequest_app_id();

        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        Member member = memberService.findByUser_id(request_user_id);

        String qrcode_id = "";

        if (!member.getMember_status()) {
            throw new RuntimeException("您还没有通过审核");
        }

        if (ValidateUtil.isNullOrEmpty(member.getQrcode_id())) {
            qrcode_id = Util.getRandomUUID();
            String member_id = member.getMember_id();

            App app = appService.findByApp_id(member.getApp_id());

            String wechat_app_id = ApiConfigKit.getAppId();
            if (!wechat_app_id.equals(app.getWechat_app_id())) {
                ApiConfigKit.setThreadLocalAppId(app.getWechat_app_id());
                AccessTokenApi.refreshAccessToken();
            }

            ApiResult apiResult = QrcodeApi.createPermanent(qrcode_id);
            Boolean qrcode_status = true;
            String qrcode_url = QrcodeApi.getShowQrcodeUrl(apiResult.getStr("ticket"));

            qrcodeService.save(qrcode_id, member.getApp_id(), member_id, QrcodeType.MEMBER.getKey(), qrcode_url, 0, 0,
                    qrcode_status, request_user_id);

            memberService.updateByMember_idAndQrcode_id(member_id, qrcode_id, request_user_id);
        } else {
            qrcode_id = member.getQrcode_id();
        }

        Qrcode qrcode = qrcodeService.findByQrcode_id(qrcode_id);

        authenticateApp_id(member.getApp_id());

        renderSuccessJson(qrcode.getQrcode_url());
    }

    @ActionKey(Url.MEMBER_TEAM_MEMBER_LEVEL_LIST)
    public void teamMemberLevel() {
        validateRequest_app_id();
        validate(Member.MEMBER_ID);

        Member model = getModel(Member.class);

        authenticateRequest_app_idAndRequest_user_id();

        Member member = memberService.findByMember_id(model.getMember_id());

        Member parentMember = memberService.findByMember_id(member.getMember_parent_id());
        MemberLevel parentMemberLevel = memberLevelService.findByMember_level_id(parentMember.getMember_level_id());

        List<MemberLevel> resultList = new ArrayList<MemberLevel>();
        List<MemberLevel> memberLevelList = memberLevelService.listByApp_id(member.getApp_id());
        for (MemberLevel memberLevel : memberLevelList) {
            if (memberLevel.getMember_level_value() > parentMemberLevel.getMember_level_value()) {
                if (memberLevel.getMember_level_id().equals(member.getMember_level_id())) {
                    memberLevel.put(Constant.IS_SELECT, true);
                } else {
                    memberLevel.put(Constant.IS_SELECT, false);
                }

                resultList.add(memberLevel);
            }
        }

        authenticateApp_id(member.getApp_id());

        renderSuccessJson(resultList);
    }

    @ActionKey(Url.MEMBER_TEAM_FIND)
    public void teamFind() {
        validateRequest_app_id();
        validate(Member.MEMBER_ID);

        Member model = getModel(Member.class);
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        Member member = memberService.findByMember_id(model.getMember_id());
        Member parentMember = memberService.findByUser_id(request_user_id);

        authenticateApp_id(member.getApp_id());

        User user = userService.findByUser_id(member.getUser_id());
        member.put(User.USER_NAME, user.getUser_name());
        member.put(User.USER_AVATAR, fileService.getFile_path(user.getUser_avatar()));

        String member_level_name = "";
        if (!ValidateUtil.isNullOrEmpty(member.getMember_level_id())) {
            MemberLevel memberLevel = memberLevelService.findByMember_level_id(member.getMember_level_id());

            member_level_name = memberLevel.getMember_level_name();
        }
        member.put(MemberLevel.MEMBER_LEVEL_NAME, member_level_name);
        
        String app_pattern = "";
        if ("c1af3f1ae00e4e0da9b20f5bd41b4279".equals(member.getApp_id())) {
            app_pattern = "MEMBER_PURCHASE_ORDER";
        } else if ("df2078d6c9eb46babb0df957127273ab".equals(member.getApp_id())) {
            app_pattern = "TRADE"; 
        }

        member.keep(Member.MEMBER_ID, Member.MEMBER_PARENT_ID, User.USER_NAME, User.USER_AVATAR,
                MemberLevel.MEMBER_LEVEL_NAME, Member.MEMBER_STATUS);
        member.put(Constant.APP_PATTERN, app_pattern);
        member.put(Constant.IS_CHILDREN, member.getMember_parent_id().equals(parentMember.getMember_id()));

        renderSuccessJson(member);
    }

    @ActionKey(Url.MEMBER_TEAM_MEMBER_LEVEL_UPDATE)
    public void teamMemberLevelUpdate() {
        validateRequest_app_id();
        validate(Member.MEMBER_ID);

        Member model = getModel(Member.class);
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        Member member = memberService.findByMember_id(model.getMember_id());
        User parentUser = userService.findByUser_id(request_user_id);
        Member parentMember = memberService.findByMember_id(parentUser.getObject_Id());

        if (member.getMember_parent_id().equals(parentMember.getMember_id())) {
            memberService.updateByMember_idAndMember_level_idAndMember_status(model.getMember_id(),
                    model.getMember_level_id(), true, request_user_id);
        } else {
            throw new RuntimeException("您不是上一级");
        }

        renderSuccessJson();
    }

    @ActionKey(Url.MEMBER_ADMIN_LIST)
    public void adminList() {
        validateRequest_app_id();
        validate(User.USER_NAME, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        User model = getModel(User.class);
        String request_app_id = getRequest_app_id();

        authenticateRequest_app_idAndRequest_user_id();

        Integer total = memberService.countByApp_idOrLikeUser_name(request_app_id, model.getUser_name());
        List<Member> resultList = memberService.listByApp_idOrLikeUser_nameAndLimit(request_app_id,
                model.getUser_name(), getM(), getN());

        for (Member result : resultList) {
            result.keep(Member.MEMBER_ID, Member.USER_ID, User.USER_NAME, Member.SYSTEM_VERSION);
        }

        renderSuccessJson(total, resultList);
    }

    @ActionKey(Url.MEMBER_ADMIN_FIND)
    public void adminFind() {
        validateRequest_app_id();
        validate(Member.MEMBER_ID);

        Member model = getModel(Member.class);

        authenticateRequest_app_idAndRequest_user_id();

        Member member = memberService.findByMember_id(model.getMember_id());

        authenticateApp_id(member.getApp_id());

        member.keep(Member.MEMBER_ID, Member.SYSTEM_VERSION);

        renderSuccessJson(member);
    }

    @ActionKey(Url.MEMBER_SYSTEM_LIST)
    public void systemList() {
        validateRequest_app_id();
        validate(Member.APP_ID, User.USER_NAME, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        User model = getModel(User.class);

        Integer total = memberService.countByOrApp_idOrLikeUser_name(model.getApp_id(), model.getUser_name());
        List<Member> resultList = memberService.listByOrApp_idOrLikeUser_nameAndLimit(model.getApp_id(),
                model.getUser_name(), getM(), getN());

        for (Member result : resultList) {
            result.keep(Member.MEMBER_ID, Member.SYSTEM_VERSION);
        }

        renderSuccessJson(total, resultList);
    }

    @ActionKey(Url.MEMBER_SYSTEM_ALL_LIST)
    public void systemAllList() {
        validateRequest_app_id();

        JSONObject jsonObject = getParameterJSONObject();
        String app_id = jsonObject.getString("app_id");

        List<Member> resultList = memberService.listByOrApp_id(app_id);

        for (Member result : resultList) {
            result.keep(Member.MEMBER_ID, User.USER_NAME, Member.SYSTEM_VERSION);
        }

        renderSuccessJson(resultList);
    }

    @ActionKey(Url.MEMBER_SYSTEM_FIND)
    public void systemFind() {
        validateRequest_app_id();
        validate(Member.MEMBER_ID);

        Member model = getModel(Member.class);

        Member member = memberService.findByMember_id(model.getMember_id());

        member.keep(Member.MEMBER_ID, Member.SYSTEM_VERSION);

        renderSuccessJson(member);
    }

}