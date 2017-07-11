package com.nowui.chuangshi.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.jfinal.weixin.iot.msg.InEquDataMsg;
import com.jfinal.weixin.iot.msg.InEqubindEvent;
import com.jfinal.weixin.sdk.api.ApiConfigKit;
import com.jfinal.weixin.sdk.api.ApiResult;
import com.jfinal.weixin.sdk.api.UserApi;
import com.jfinal.weixin.sdk.jfinal.MsgController;
import com.jfinal.weixin.sdk.msg.in.*;
import com.jfinal.weixin.sdk.msg.in.card.*;
import com.jfinal.weixin.sdk.msg.in.event.*;
import com.jfinal.weixin.sdk.msg.in.speech_recognition.InSpeechRecognitionResults;
import com.jfinal.weixin.sdk.msg.out.OutTextMsg;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.model.App;
import com.nowui.chuangshi.model.Member;
import com.nowui.chuangshi.model.MemberLevel;
import com.nowui.chuangshi.model.Qrcode;
import com.nowui.chuangshi.service.AppService;
import com.nowui.chuangshi.service.MemberLevelService;
import com.nowui.chuangshi.service.MemberService;
import com.nowui.chuangshi.service.QrcodeService;
import com.nowui.chuangshi.type.QrcodeType;
import com.nowui.chuangshi.util.ValidateUtil;

public class XingXiaoWeChatMessageController extends MsgController {

    private final AppService appService = new AppService();
    private final MemberService memberService = new MemberService();
    private final MemberLevelService memberLevelService = new MemberLevelService();
    private final QrcodeService qrcodeService = new QrcodeService();
    private final String app_id = "c1af3f1ae00e4e0da9b20f5bd41b4279";

    public XingXiaoWeChatMessageController() {
        App app = appService.findByApp_id(app_id);

        String wechat_app_id = ApiConfigKit.getAppId();
        if (!wechat_app_id.equals(app.getWechat_app_id())) {
            ApiConfigKit.setThreadLocalAppId(app.getWechat_app_id());
        }
    }

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
        String wechat_open_id = inFollowEvent.getFromUserName();
        String wechat_union_id = "";
        String member_parent_id = "";
        String from_qrcode_id = "";
        String event = inFollowEvent.getEvent();
        Boolean member_status = false;

        ApiResult apiResult = UserApi.getUserInfo(wechat_open_id);
        wechat_union_id = apiResult.getStr("unionid");
        String user_name = apiResult.getStr("nickname");
        String user_avatar = apiResult.getStr("headimgurl");
        String system_create_user_id = "";

        if (ValidateUtil.isNullOrEmpty(wechat_union_id)) {
            wechat_union_id = "";
        }

        Member member = memberService.saveOrUpdate(app_id, wechat_open_id, wechat_union_id, member_parent_id, from_qrcode_id, user_name, user_avatar, member_status, system_create_user_id);

        if (event.equals("unsubscribe")) {
            qrcodeService.updateQrcode_cancelByQrcode_id(member.getFrom_qrcode_id(), system_create_user_id);
        }

        OutTextMsg outMsg = new OutTextMsg(inFollowEvent);
        outMsg.setContent("恭喜您，成为我们平台的会员!");
        render(outMsg);
    }

    @Override
    protected void processInQrCodeEvent(InQrCodeEvent inQrCodeEvent) {
        String wechat_open_id = inQrCodeEvent.getFromUserName();
        String wechat_union_id = "";
        String member_parent_id = "";
        String from_qrcode_id = inQrCodeEvent.getEventKey().replace("qrscene_", "");
        String parent_path = "";
        String member_level_id = "";
        String content = "";
        String request_user_id = "";
        Boolean member_status = false;
        String system_create_user_id = "";

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

        if (ValidateUtil.isNullOrEmpty(wechat_union_id)) {
            wechat_union_id = "";
        }

        if (qrcode.getQrcode_type().equals(QrcodeType.PLATFORM.getKey())) {
            //合伙人不用审核
            member_status = true;
        }

        Member member = memberService.saveOrUpdate(app_id, wechat_open_id, wechat_union_id, member_parent_id, from_qrcode_id, user_name, user_avatar, member_status, system_create_user_id);

        if (ValidateUtil.isNullOrEmpty(member.getMember_parent_id())) {
            if (qrcode.getQrcode_type().equals(QrcodeType.PLATFORM.getKey())) {
                member_parent_id = "0";

                JSONArray jsonArray = new JSONArray();
                jsonArray.add(member_parent_id);
                parent_path = jsonArray.toJSONString();

                if (ValidateUtil.isNullOrEmpty(member.getMember_level_id())) {
                    MemberLevel memberLevel = memberLevelService.findByMember_level_sort(app_id, 1);
                    member_level_id = memberLevel.getMember_level_id();

                    memberService.updateByMember_idAndMember_parent_idAndMember_parent_pathAndMember_level_id(member.getMember_id(), member_parent_id, parent_path, member_level_id);

                    qrcodeService.updateQrcode_addByQrcode_id(from_qrcode_id, request_user_id);

                    //content = "恭喜您，成为我们的会员！您的等级是" + memberLevel.getMember_level_name() + "。";
                } else {
                    MemberLevel memberLevel = memberLevelService.findByMember_level_id(member.getMember_level_id());

                    //content = "不能绑定，您的等级已经是" + memberLevel.getMember_level_name() + "。";
                }

                qrcodeService.updateQrcode_statusByQrcode_id(from_qrcode_id, request_user_id);
            } else if (qrcode.getQrcode_type().equals(QrcodeType.MEMBER.getKey())) {
                member_parent_id = qrcode.getObject_id();

                Member parentMember = memberService.findByMember_id(member_parent_id);
                JSONArray jsonArray = JSON.parseArray(parentMember.getMember_parent_path());
                jsonArray.add(member_parent_id);
                parent_path = jsonArray.toJSONString();

                //是否需要审核
                if (member_status) {
                    MemberLevel parentMemberLevel = memberLevelService.findByMember_level_id(parentMember.getMember_level_id());
                    MemberLevel memberLevel = memberLevelService.findByMember_level_sort(app_id, parentMemberLevel.getMember_level_sort() + 1);
                    member_level_id = memberLevel.getMember_level_id();
                }

                memberService.updateByMember_idAndMember_parent_idAndMember_parent_pathAndMember_level_id(member.getMember_id(), member_parent_id, parent_path, member_level_id);

                qrcodeService.updateQrcode_addByQrcode_id(from_qrcode_id, request_user_id);

                //content = "恭喜您，成为我们的会员！您的推荐人是" + parentMember.getMember_name() + "。";
            }
        } else if (member.getMember_parent_id().equals(Constant.PARENT_ID)) {
            //您已经是一级会员
        } else {

        }

        OutTextMsg outMsg = new OutTextMsg(inQrCodeEvent);
        outMsg.setContent("恭喜您，成为我们平台的会员!");
        render(outMsg);
    }

    @Override
    protected void processInLocationEvent(InLocationEvent inLocationEvent) {

    }

    @Override
    protected void processInMassEvent(InMassEvent inMassEvent) {

    }

    @Override
    protected void processInMenuEvent(InMenuEvent inMenuEvent) {

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
