package com.nowui.chuangshi.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.jfinal.weixin.iot.msg.InEquDataMsg;
import com.jfinal.weixin.iot.msg.InEqubindEvent;
import com.jfinal.weixin.sdk.api.ApiConfigKit;
import com.jfinal.weixin.sdk.api.ApiResult;
import com.jfinal.weixin.sdk.api.UserApi;
import com.jfinal.weixin.sdk.jfinal.MsgController;
import com.jfinal.weixin.sdk.msg.in.InImageMsg;
import com.jfinal.weixin.sdk.msg.in.InLinkMsg;
import com.jfinal.weixin.sdk.msg.in.InLocationMsg;
import com.jfinal.weixin.sdk.msg.in.InNotDefinedMsg;
import com.jfinal.weixin.sdk.msg.in.InShortVideoMsg;
import com.jfinal.weixin.sdk.msg.in.InTextMsg;
import com.jfinal.weixin.sdk.msg.in.InVideoMsg;
import com.jfinal.weixin.sdk.msg.in.InVoiceMsg;
import com.jfinal.weixin.sdk.msg.in.card.InCardPassCheckEvent;
import com.jfinal.weixin.sdk.msg.in.card.InCardPayOrderEvent;
import com.jfinal.weixin.sdk.msg.in.card.InCardSkuRemindEvent;
import com.jfinal.weixin.sdk.msg.in.card.InMerChantOrderEvent;
import com.jfinal.weixin.sdk.msg.in.card.InUpdateMemberCardEvent;
import com.jfinal.weixin.sdk.msg.in.card.InUserCardEvent;
import com.jfinal.weixin.sdk.msg.in.card.InUserConsumeCardEvent;
import com.jfinal.weixin.sdk.msg.in.card.InUserGetCardEvent;
import com.jfinal.weixin.sdk.msg.in.card.InUserGiftingCardEvent;
import com.jfinal.weixin.sdk.msg.in.card.InUserPayFromCardEvent;
import com.jfinal.weixin.sdk.msg.in.event.InCustomEvent;
import com.jfinal.weixin.sdk.msg.in.event.InFollowEvent;
import com.jfinal.weixin.sdk.msg.in.event.InLocationEvent;
import com.jfinal.weixin.sdk.msg.in.event.InMassEvent;
import com.jfinal.weixin.sdk.msg.in.event.InMenuEvent;
import com.jfinal.weixin.sdk.msg.in.event.InNotDefinedEvent;
import com.jfinal.weixin.sdk.msg.in.event.InPoiCheckNotifyEvent;
import com.jfinal.weixin.sdk.msg.in.event.InQrCodeEvent;
import com.jfinal.weixin.sdk.msg.in.event.InShakearoundUserShakeEvent;
import com.jfinal.weixin.sdk.msg.in.event.InTemplateMsgEvent;
import com.jfinal.weixin.sdk.msg.in.event.InVerifyFailEvent;
import com.jfinal.weixin.sdk.msg.in.event.InVerifySuccessEvent;
import com.jfinal.weixin.sdk.msg.in.event.InWifiEvent;
import com.jfinal.weixin.sdk.msg.in.speech_recognition.InSpeechRecognitionResults;
import com.jfinal.weixin.sdk.msg.out.OutNewsMsg;
import com.jfinal.weixin.sdk.msg.out.OutTextMsg;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.model.App;
import com.nowui.chuangshi.model.File;
import com.nowui.chuangshi.model.Member;
import com.nowui.chuangshi.model.MemberLevel;
import com.nowui.chuangshi.model.Qrcode;
import com.nowui.chuangshi.model.User;
import com.nowui.chuangshi.service.AppService;
import com.nowui.chuangshi.service.FileService;
import com.nowui.chuangshi.service.MemberLevelService;
import com.nowui.chuangshi.service.MemberService;
import com.nowui.chuangshi.service.QrcodeService;
import com.nowui.chuangshi.service.UserService;
import com.nowui.chuangshi.type.QrcodeType;
import com.nowui.chuangshi.type.UserType;
import com.nowui.chuangshi.util.ValidateUtil;

public class WeChatMessageController extends MsgController {

    private final MemberService memberService = new MemberService();
    private final MemberLevelService memberLevelService = new MemberLevelService();
    private final QrcodeService qrcodeService = new QrcodeService();
    private final AppService appService = new AppService();
    private final UserService userService = new UserService();
    private final FileService fileService = new FileService();

    @Override
    protected void processInTextMsg(InTextMsg inTextMsg) {

    }

    @Override
    protected void processInImageMsg(InImageMsg inImageMsg) {

    }

    @Override
    protected void processInVoiceMsg(InVoiceMsg inVoiceMsg) {

    }

    @Override
    protected void processInVideoMsg(InVideoMsg inVideoMsg) {

    }

    @Override
    protected void processInShortVideoMsg(InShortVideoMsg inShortVideoMsg) {

    }

    @Override
    protected void processInLocationMsg(InLocationMsg inLocationMsg) {

    }

    @Override
    protected void processInLinkMsg(InLinkMsg inLinkMsg) {

    }

    @Override
    protected void processInCustomEvent(InCustomEvent inCustomEvent) {

    }

    @Override
    protected void processInFollowEvent(InFollowEvent inFollowEvent) {
        String app_id = getPara(Constant.APP_ID);
        String wechat_open_id = inFollowEvent.getFromUserName();
        String wechat_union_id = "";
        String member_parent_id = "";
        String from_qrcode_id = "";
        String member_level_id = "";
        JSONArray member_parent_path = new JSONArray();
        String event = inFollowEvent.getEvent();
        Boolean member_status = false;

        App app = appService.findByApp_id(app_id);

        String wechat_app_id = ApiConfigKit.getAppId();
        if (!wechat_app_id.equals(app.getWechat_app_id())) {
            ApiConfigKit.setThreadLocalAppId(app.getWechat_app_id());
        }

        ApiResult apiResult = UserApi.getUserInfo(wechat_open_id);
        wechat_union_id = apiResult.getStr("unionid");
        String user_name = apiResult.getStr("nickname");
        String user_avatar = apiResult.getStr("headimgurl");
        String system_create_user_id = "";

        if (ValidateUtil.isNullOrEmpty(wechat_union_id)) {
            wechat_union_id = "";
        }

        if (ValidateUtil.isNullOrEmpty(user_name)) {
            user_name = "";
        }

        if (ValidateUtil.isNullOrEmpty(user_avatar)) {
            user_avatar = "";
        }

        Member member = memberService.saveOrUpdate(app_id, wechat_open_id, wechat_union_id, member_parent_id,
                from_qrcode_id, member_level_id, member_parent_path, user_name, user_avatar, member_status,
                system_create_user_id);

        if (event.equals("unsubscribe")) {
            qrcodeService.updateQrcode_cancelByQrcode_id(member.getFrom_qrcode_id(), system_create_user_id);
        }

        if (app_id.equals("df2078d6c9eb46babb0df957127273ab")) {
            OutNewsMsg outNewsMsg = new OutNewsMsg(inFollowEvent);
            outNewsMsg.addNews("欢迎使用济颐馆健康管理平台！", "广州市济颐馆贸易有限公司",
                    "https://mmbiz.qlogo.cn/mmbiz_jpg/nuPqkdDZjJxu2hqfzf4icmib3UaqAick43icOz1aT4AzI9dXALrZmIqy09mXiaroIXoS3LkNOxibZogl7ZhFSFHBarNQ/0?wx_fmt=jpeg",
                    "https://mmbiz.qlogo.cn/mmbiz_jpg/nuPqkdDZjJxu2hqfzf4icmib3UaqAick43icOz1aT4AzI9dXALrZmIqy09mXiaroIXoS3LkNOxibZogl7ZhFSFHBarNQ/0?wx_fmt=jpeg");
            render(outNewsMsg);
        } else {
            OutTextMsg outMsg = new OutTextMsg(inFollowEvent);
            outMsg.setContent("恭喜您，成为我们平台的会员!");
            render(outMsg);
        }

    }

    @Override
    protected void processInQrCodeEvent(InQrCodeEvent inQrCodeEvent) {
        String app_id = getPara(Constant.APP_ID);
        String wechat_open_id = inQrCodeEvent.getFromUserName();
        String wechat_union_id = "";
        String member_parent_id = "";
        String from_qrcode_id = inQrCodeEvent.getEventKey().replace("qrscene_", "");
        String member_level_id = "";
        JSONArray member_parent_path = new JSONArray();
        ;
        String content = "";
        String request_user_id = "";
        Boolean member_status = false;
        String system_create_user_id = "";

        System.out.println(app_id);

        App app = appService.findByApp_id(app_id);

        String wechat_app_id = ApiConfigKit.getAppId();
        if (!wechat_app_id.equals(app.getWechat_app_id())) {
            ApiConfigKit.setThreadLocalAppId(app.getWechat_app_id());
        }

        Qrcode qrcode = qrcodeService.findByQrcode_id(from_qrcode_id);

        if (!qrcode.getQrcode_status()) {
            content = "该二维码已经过期！";

            OutTextMsg outMsg = new OutTextMsg(inQrCodeEvent);
            outMsg.setContent(content);
            render(outMsg);

            return;
        }

        ApiResult apiResult = UserApi.getUserInfo(wechat_open_id);
        wechat_union_id = apiResult.getStr("unionid");
        String user_name = apiResult.getStr("nickname");
        String user_avatar = apiResult.getStr("headimgurl");

        System.out.println(apiResult.getJson());

        if (ValidateUtil.isNullOrEmpty(wechat_union_id)) {
            wechat_union_id = "";
        }

        if (qrcode.getQrcode_type().equals(QrcodeType.PLATFORM.getKey())) {
            // 合伙人不用审核
            member_status = true;
        } else if (app.getApp_is_audit_member()) {
            // 该应用指定需要审核
            member_status = false;
        } else {
            // 该应用指定不需要审核
            member_status = true;
        }

        Member member = memberService.saveOrUpdate(app_id, wechat_open_id, wechat_union_id, member_parent_id,
                from_qrcode_id, member_level_id, member_parent_path, user_name, user_avatar, member_status,
                system_create_user_id);

        if (ValidateUtil.isNullOrEmpty(member.getMember_parent_id())) {
            if (qrcode.getQrcode_type().equals(QrcodeType.PLATFORM.getKey())) {
                member_parent_id = "0";

                member_parent_path.add(member_parent_id);

                if (ValidateUtil.isNullOrEmpty(member.getMember_level_id())) {
                    MemberLevel memberLevel = memberLevelService.findByMember_level_sort(app_id, 1);
                    member_level_id = memberLevel.getMember_level_id();

                    memberService.updateByMember_idAndMember_parent_idAndMember_parent_pathAndMember_level_idAndMember_status(
                            member.getMember_id(), member_parent_id, member_parent_path, member_level_id, member_status,
                            system_create_user_id);

                    qrcodeService.updateQrcode_addByQrcode_id(from_qrcode_id, request_user_id);

                    // content = "恭喜您，成为我们的会员！您的等级是" +
                    // memberLevel.getMember_level_name() + "。";
                } else {
                    MemberLevel memberLevel = memberLevelService.findByMember_level_id(member.getMember_level_id());

                    // content = "不能绑定，您的等级已经是" +
                    // memberLevel.getMember_level_name() + "。";
                }

                qrcodeService.updateQrcode_statusByQrcode_id(from_qrcode_id, request_user_id);
            } else if (qrcode.getQrcode_type().equals(QrcodeType.MEMBER.getKey())) {
                member_parent_id = qrcode.getObject_id();

                Member parentMember = memberService.findByMember_id(member_parent_id);
                JSONArray jsonArray = JSON.parseArray(parentMember.getMember_parent_path());
                jsonArray.add(member_parent_id);
                member_parent_path = jsonArray;

                // 是否需要审核
                if (member_status) {
                    MemberLevel parentMemberLevel = memberLevelService
                            .findByMember_level_id(parentMember.getMember_level_id());
                    MemberLevel memberLevel = memberLevelService.findByMember_level_sort(app_id,
                            parentMemberLevel.getMember_level_sort() + 1);
                    member_level_id = memberLevel.getMember_level_id();
                }

                memberService.updateByMember_idAndMember_parent_idAndMember_parent_pathAndMember_level_idAndMember_status(
                        member.getMember_id(), member_parent_id, member_parent_path, member_level_id, member_status,
                        system_create_user_id);

                qrcodeService.updateQrcode_addByQrcode_id(from_qrcode_id, request_user_id);

                // content = "恭喜您，成为我们的会员！您的推荐人是" +
                // parentMember.getMember_name() + "。";
            }
        } else if (member.getMember_parent_id().equals(Constant.PARENT_ID)) {
            // 您已经是一级会员
        } else {

        }

        if (app_id.equals("df2078d6c9eb46babb0df957127273ab")) {
            OutNewsMsg outNewsMsg = new OutNewsMsg(inQrCodeEvent);
            outNewsMsg.addNews("欢迎使用济颐馆健康管理平台！", "广州市济颐馆贸易有限公司",
                    "https://mmbiz.qlogo.cn/mmbiz_jpg/nuPqkdDZjJxu2hqfzf4icmib3UaqAick43icOz1aT4AzI9dXALrZmIqy09mXiaroIXoS3LkNOxibZogl7ZhFSFHBarNQ/0?wx_fmt=jpeg",
                    "https://mmbiz.qlogo.cn/mmbiz_jpg/nuPqkdDZjJxu2hqfzf4icmib3UaqAick43icOz1aT4AzI9dXALrZmIqy09mXiaroIXoS3LkNOxibZogl7ZhFSFHBarNQ/0?wx_fmt=jpeg");
            render(outNewsMsg);
        } else {
            OutTextMsg outMsg = new OutTextMsg(inQrCodeEvent);
            outMsg.setContent("恭喜您，成为我们平台的会员!");
            render(outMsg);
        }
    }

    @Override
    protected void processInLocationEvent(InLocationEvent inLocationEvent) {

    }

    @Override
    protected void processInMassEvent(InMassEvent inMassEvent) {

    }

    @Override
    protected void processInMenuEvent(InMenuEvent inMenuEvent) {
        String app_id = getPara(Constant.APP_ID);

        App app = appService.findByApp_id(app_id);

        String wechat_app_id = ApiConfigKit.getAppId();
        if (!wechat_app_id.equals(app.getWechat_app_id())) {
            ApiConfigKit.setThreadLocalAppId(app.getWechat_app_id());
        }

        String wechat_open_id = inMenuEvent.getFromUserName();

        ApiResult apiResult = UserApi.getUserInfo(wechat_open_id);
        String wechat_union_id = apiResult.getStr("unionid");
        String user_name = apiResult.getStr("nickname");
        String user_avatar = apiResult.getStr("headimgurl");
        String system_create_user_id = "";

        User user = userService.findByApp_idAndUser_typeAndWechat_open_idAndWechat_union_id(app_id,
                UserType.MEMBER.getKey(), wechat_open_id, wechat_union_id);
        if (user != null) {
            if (!user.getUser_name().equals(user_name)) {
                userService.updateByUser_name(user.getUser_id(), user_name, system_create_user_id);

                memberService.deleteMemberParentCache(user.getObject_Id());
            }

            File file = fileService.findByFile_id(user.getUser_avatar());

            if (!user_avatar.equals(file.getFile_path())) {
                fileService.updateByFile_path(user.getUser_avatar(), user_avatar, system_create_user_id);

                memberService.deleteMemberParentCache(user.getObject_Id());
            }
        }
    }

    @Override
    protected void processInSpeechRecognitionResults(InSpeechRecognitionResults inSpeechRecognitionResults) {

    }

    @Override
    protected void processInTemplateMsgEvent(InTemplateMsgEvent inTemplateMsgEvent) {

    }

    @Override
    protected void processInShakearoundUserShakeEvent(InShakearoundUserShakeEvent inShakearoundUserShakeEvent) {

    }

    @Override
    protected void processInVerifySuccessEvent(InVerifySuccessEvent inVerifySuccessEvent) {

    }

    @Override
    protected void processInVerifyFailEvent(InVerifyFailEvent inVerifyFailEvent) {

    }

    @Override
    protected void processInPoiCheckNotifyEvent(InPoiCheckNotifyEvent inPoiCheckNotifyEvent) {

    }

    @Override
    protected void processInWifiEvent(InWifiEvent inWifiEvent) {

    }

    @Override
    protected void processInUserCardEvent(InUserCardEvent inUserCardEvent) {

    }

    @Override
    protected void processInUpdateMemberCardEvent(InUpdateMemberCardEvent inUpdateMemberCardEvent) {

    }

    @Override
    protected void processInUserPayFromCardEvent(InUserPayFromCardEvent inUserPayFromCardEvent) {

    }

    @Override
    protected void processInMerChantOrderEvent(InMerChantOrderEvent inMerChantOrderEvent) {

    }

    @Override
    protected void processIsNotDefinedEvent(InNotDefinedEvent inNotDefinedEvent) {

    }

    @Override
    protected void processIsNotDefinedMsg(InNotDefinedMsg inNotDefinedMsg) {

    }

    @Override
    protected void processInUserGiftingCardEvent(InUserGiftingCardEvent inUserGiftingCardEvent) {

    }

    @Override
    protected void processInUserGetCardEvent(InUserGetCardEvent inUserGetCardEvent) {

    }

    @Override
    protected void processInUserConsumeCardEvent(InUserConsumeCardEvent inUserConsumeCardEvent) {

    }

    @Override
    protected void processInCardSkuRemindEvent(InCardSkuRemindEvent inCardSkuRemindEvent) {

    }

    @Override
    protected void processInCardPayOrderEvent(InCardPayOrderEvent inCardPayOrderEvent) {

    }

    @Override
    protected void processInCardPassCheckEvent(InCardPassCheckEvent inCardPassCheckEvent) {

    }

    @Override
    protected void processInEqubindEvent(InEqubindEvent inEqubindEvent) {

    }

    @Override
    protected void processInEquDataMsg(InEquDataMsg inEquDataMsg) {

    }
}
