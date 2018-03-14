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
import com.nowui.chuangshi.api.app.model.App;
import com.nowui.chuangshi.api.app.service.AppService;
import com.nowui.chuangshi.api.file.model.File;
import com.nowui.chuangshi.api.file.service.FileService;
import com.nowui.chuangshi.api.member.model.Member;
import com.nowui.chuangshi.api.member.model.MemberLevel;
import com.nowui.chuangshi.api.member.service.MemberLevelService;
import com.nowui.chuangshi.api.member.service.MemberService;
import com.nowui.chuangshi.api.qrcode.model.Qrcode;
import com.nowui.chuangshi.api.qrcode.service.QrcodeService;
import com.nowui.chuangshi.api.user.model.User;
import com.nowui.chuangshi.api.user.service.UserService;
import com.nowui.chuangshi.common.socket.MinHangSocket;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.type.QrcodeType;
import com.nowui.chuangshi.type.UserType;
import com.nowui.chuangshi.util.Util;
import com.nowui.chuangshi.util.ValidateUtil;

public class WeChatMessageController extends MsgController {

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

        App app = AppService.instance.find(app_id);

        String wechat_app_id = ApiConfigKit.getAppId();
        if (!wechat_app_id.equals(app.getWechat_app_id())) {
            ApiConfigKit.setThreadLocalAppId(app.getWechat_app_id());
//            AccessTokenApi.refreshAccessToken();
        }

        ApiResult apiResult = UserApi.getUserInfo(wechat_open_id);
        wechat_union_id = apiResult.getStr("unionid");
        String user_name = Util.getEmoji(apiResult.getStr("nickname"));
        String user_avatar = apiResult.getStr("headimgurl");
        String system_create_user_id = "";

        System.out.println(apiResult.getJson());

        if (ValidateUtil.isNullOrEmpty(wechat_union_id)) {
            wechat_union_id = "";
        }

        if (ValidateUtil.isNullOrEmpty(user_name)) {
            user_name = "";
        }

        if (ValidateUtil.isNullOrEmpty(user_avatar)) {
            user_avatar = "";
        }

        if (event.equals("unsubscribe")) {
//            QrcodeService.instance.updateQrcode_cancelByQrcode_id(member.getFrom_qrcode_id(), system_create_user_id);
        } else {
            Member member = MemberService.instance.wechatSaveOrUpdate(app_id, wechat_open_id, wechat_union_id, member_parent_id, from_qrcode_id, member_level_id, member_parent_path, user_name, user_avatar, member_status, system_create_user_id);

            if (app_id.equals("df2078d6c9eb46babb0df957127273ab")) {
                OutNewsMsg outNewsMsg = new OutNewsMsg(inFollowEvent);
                outNewsMsg.addNews("欢迎使用济颐馆健康管理平台！", "济颐馆欢迎您",
                        "http://api.chuangshi.nowui.com/upload/df2078d6c9eb46babb0df957127273ab/6a4dbae2ac824d2fb170638d55139666/original/1a4fdcc5e56b49f4bd9962428ace0b10.jpg",
                        "http://h5.jiyiguan.nowui.com/?#/index");
                render(outNewsMsg);
            } else if (app_id.equals("8acc2d49ad014f418878d1a16336c16b")) {
                MinHangSocket.instance.sendMessage();

                OutNewsMsg outNewsMsg = new OutNewsMsg(inFollowEvent);
                outNewsMsg.addNews("欢迎使用闵行区党建服务中心平台！", "来报到的党员朋友们，点这里！",
                        "http://api.chuangshi.nowui.com/upload/8acc2d49ad014f418878d1a16336c16b/f6a470d1597d4bea821d4e393cbbb00e/original/1eb85e1859c24909b221f767455a7d85.jpg",
                        "http://h5.minhang.nowui.com/?#/index");
                render(outNewsMsg);
            } else {
                OutTextMsg outMsg = new OutTextMsg(inFollowEvent);
                outMsg.setContent("恭喜您，成为我们平台的会员!");
                render(outMsg);
            }
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

        App app = AppService.instance.find(app_id);

        String wechat_app_id = ApiConfigKit.getAppId();
        if (!wechat_app_id.equals(app.getWechat_app_id())) {
            ApiConfigKit.setThreadLocalAppId(app.getWechat_app_id());
//            AccessTokenApi.refreshAccessToken();
        }

        Qrcode qrcode = QrcodeService.instance.find(from_qrcode_id);

        if (!qrcode.getQrcode_status()) {
            content = "该二维码已经过期！";

            OutTextMsg outMsg = new OutTextMsg(inQrCodeEvent);
            outMsg.setContent(content);
            render(outMsg);

            return;
        }

        ApiResult apiResult = UserApi.getUserInfo(wechat_open_id);
        wechat_union_id = apiResult.getStr("unionid");
        String user_name = Util.getEmoji(apiResult.getStr("nickname"));
        String user_avatar = apiResult.getStr("headimgurl");

        System.out.println(apiResult.getJson());

        if (ValidateUtil.isNullOrEmpty(wechat_union_id)) {
            wechat_union_id = "";
        }

        if (ValidateUtil.isNullOrEmpty(user_name)) {
            user_name = "";
        }

        if (ValidateUtil.isNullOrEmpty(user_avatar)) {
            user_avatar = "";
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

        Member member = MemberService.instance.wechatSaveOrUpdate(app_id, wechat_open_id, wechat_union_id, member_parent_id, from_qrcode_id, member_level_id, member_parent_path, user_name, user_avatar, member_status, system_create_user_id);

        if (ValidateUtil.isNullOrEmpty(member.getMember_parent_id())) {
            if (qrcode.getQrcode_type().equals(QrcodeType.PLATFORM.getKey())) {
                member_parent_id = "0";

                member_parent_path.add(member_parent_id);

                if (ValidateUtil.isNullOrEmpty(member.getMember_level_id())) {
                    MemberLevel memberLevel = MemberLevelService.instance.memberLevelSortFind(app_id, 1);
                    member_level_id = memberLevel.getMember_level_id();

                    MemberService.instance.wechatQrCodeUpdate(member.getMember_id(), member_parent_id, member_parent_path, member_level_id, member_status, system_create_user_id);

//                    qrcodeService.updateQrcode_addByQrcode_id(from_qrcode_id, request_user_id);

                    // content = "恭喜您，成为我们的会员！您的等级是" +
                    // memberLevel.getMember_level_name() + "。";
                } else {
                    MemberLevel memberLevel = MemberLevelService.instance.find(member.getMember_level_id());

                    // content = "不能绑定，您的等级已经是" +
                    // memberLevel.getMember_level_name() + "。";
                }

                QrcodeService.instance.qrcodeStatusUpdate(from_qrcode_id, request_user_id);
            } else if (qrcode.getQrcode_type().equals(QrcodeType.MEMBER.getKey())) {
                member_parent_id = qrcode.getObject_id();

                Member parentMember = MemberService.instance.find(member_parent_id);
                JSONArray jsonArray = JSON.parseArray(parentMember.getMember_parent_path());
                jsonArray.add(member_parent_id);
                member_parent_path = jsonArray;

                // 是否需要审核
                if (member_status) {
                    MemberLevel parentMemberLevel = MemberLevelService.instance.find(parentMember.getMember_level_id());
                    MemberLevel memberLevel = MemberLevelService.instance.memberLevelSortFind(app_id, parentMemberLevel.getMember_level_sort() + 1);
                    member_level_id = memberLevel.getMember_level_id();
                }

                MemberService.instance.wechatQrCodeUpdate(member.getMember_id(), member_parent_id, member_parent_path, member_level_id, member_status, system_create_user_id);

//                qrcodeService.updateQrcode_addByQrcode_id(from_qrcode_id, request_user_id);

                // content = "恭喜您，成为我们的会员！您的推荐人是" +
                // parentMember.getMember_name() + "。";
            }
        } else if (member.getMember_parent_id().equals(Constant.PARENT_ID)) {
            // 您已经是一级会员
        } else {

        }

        if (app_id.equals("df2078d6c9eb46babb0df957127273ab")) {
            OutNewsMsg outNewsMsg = new OutNewsMsg(inQrCodeEvent);
            outNewsMsg.addNews("欢迎使用济颐馆健康管理平台！", "济颐馆欢迎您",
                    "http://api.chuangshi.nowui.com/upload/df2078d6c9eb46babb0df957127273ab/6a4dbae2ac824d2fb170638d55139666/original/887c0dc8adf142169d50aac84c2d6dab.jpg",
                    "http://h5.jiyiguan.nowui.com/?#/index");
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

        App app = AppService.instance.find(app_id);

        String wechat_app_id = ApiConfigKit.getAppId();
        if (!wechat_app_id.equals(app.getWechat_app_id())) {
            ApiConfigKit.setThreadLocalAppId(app.getWechat_app_id());
//            AccessTokenApi.refreshAccessToken();
        }

        String wechat_open_id = inMenuEvent.getFromUserName();

        ApiResult apiResult = UserApi.getUserInfo(wechat_open_id);
        String wechat_union_id = apiResult.getStr("unionid");
        String user_name = Util.getEmoji(apiResult.getStr("nickname"));
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


        System.out.println(apiResult.getJson());

        User user = UserService.instance.wechatFind(app_id, UserType.MEMBER.getKey(), wechat_open_id, wechat_union_id);
        if (user != null) {
            if (!user.getUser_name().equals(user_name)) {
                UserService.instance.userNameUpdate(user.getUser_id(), user_name, system_create_user_id);

                MemberService.instance.cacheDelete(user.getObject_id());
            }

            File file = FileService.instance.find(user.getUser_avatar());
            System.out.println(user_avatar);
            System.out.println(file.getFile_path());

            if (!user_avatar.equals(file.getFile_path())) {
                FileService.instance.filePathUpdate(user.getUser_avatar(), user_avatar, system_create_user_id);

                MemberService.instance.cacheDelete(user.getObject_id());
            }
        }

        System.out.println(inMenuEvent.getEventKey());
        System.out.println(inMenuEvent.getScanCodeInfo());
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
