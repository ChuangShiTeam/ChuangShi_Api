package com.nowui.chuangshi.api.member.mobile;

import com.jfinal.core.ActionKey;
import com.jfinal.weixin.sdk.api.AccessTokenApi;
import com.jfinal.weixin.sdk.api.ApiConfigKit;
import com.jfinal.weixin.sdk.api.ApiResult;
import com.jfinal.weixin.sdk.api.QrcodeApi;
import com.nowui.chuangshi.api.app.model.App;
import com.nowui.chuangshi.api.app.service.AppService;
import com.nowui.chuangshi.api.bill.model.Bill;
import com.nowui.chuangshi.api.certificate.model.Certificate;
import com.nowui.chuangshi.api.certificate.model.CertificatePay;
import com.nowui.chuangshi.api.certificate.service.CertificatePayService;
import com.nowui.chuangshi.api.certificate.service.CertificateService;
import com.nowui.chuangshi.api.enchashment.model.Enchashment;
import com.nowui.chuangshi.api.enchashment.service.EnchashmentService;
import com.nowui.chuangshi.api.file.service.FileService;
import com.nowui.chuangshi.api.member.model.Member;
import com.nowui.chuangshi.api.member.model.MemberAddress;
import com.nowui.chuangshi.api.member.model.MemberLevel;
import com.nowui.chuangshi.api.member.service.MemberAddressService;
import com.nowui.chuangshi.api.member.service.MemberLevelService;
import com.nowui.chuangshi.api.member.service.MemberService;
import com.nowui.chuangshi.api.qrcode.model.Qrcode;
import com.nowui.chuangshi.api.qrcode.service.QrcodeService;
import com.nowui.chuangshi.api.trade.model.MemberDeliveryOrder;
import com.nowui.chuangshi.api.trade.model.MemberPurchaseOrder;
import com.nowui.chuangshi.api.trade.service.MemberDeliveryOrderService;
import com.nowui.chuangshi.api.trade.service.MemberPurchaseOrderService;
import com.nowui.chuangshi.api.user.model.User;
import com.nowui.chuangshi.api.user.service.UserService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.type.QrcodeType;
import com.nowui.chuangshi.util.Util;
import com.nowui.chuangshi.util.ValidateUtil;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerKey("/mobile/member")
public class MemberController extends Controller {

    @ActionKey("/mobile/member/team/list")
    public void teamList() {
        String request_user_id = getRequest_user_id();

        User user = UserService.instance.find(request_user_id);
        Member member = MemberService.instance.find(user.getObject_id());

        List<Map<String, Object>> resultList = MemberService.instance.teamList(member.getMember_id());

        renderSuccessMapListJson(resultList);
    }

    @ActionKey("/mobile/member/children/address/list")
    public void childrenAddresslist() {
        validateRequest(MemberAddress.MEMBER_ID);
        MemberAddress model = getModel(MemberAddress.class);

        List<MemberAddress> memberAddressList = MemberAddressService.instance.memberList(model.getMember_id());

        validateResponse(MemberAddress.MEMBER_ADDRESS_ID, MemberAddress.MEMBER_ADDRESS_NAME, MemberAddress.MEMBER_ADDRESS_MOBILE, MemberAddress.MEMBER_ADDRESS_POSTCODE, MemberAddress.MEMBER_ADDRESS_PROVINCE, MemberAddress.MEMBER_ADDRESS_CITY, MemberAddress.MEMBER_ADDRESS_AREA, MemberAddress.MEMBER_ADDRESS_ADDRESS);

        renderSuccessJson(memberAddressList);
    }

    @ActionKey("/mobile/member/children/purchase/order/list")
    public void childrenPurchaseOrderlist() {
        validateRequest(MemberAddress.MEMBER_ID);
        Member model = getModel(Member.class);

        Member member = MemberService.instance.find(model.getMember_id());

        List<MemberPurchaseOrder> memberPurchaseOrderList = MemberPurchaseOrderService.instance.userList(member.getUser_id());

        validateResponse(MemberAddress.MEMBER_ADDRESS_ID, MemberAddress.MEMBER_ADDRESS_NAME, MemberAddress.MEMBER_ADDRESS_MOBILE, MemberAddress.MEMBER_ADDRESS_POSTCODE, MemberAddress.MEMBER_ADDRESS_PROVINCE, MemberAddress.MEMBER_ADDRESS_CITY, MemberAddress.MEMBER_ADDRESS_AREA, MemberAddress.MEMBER_ADDRESS_ADDRESS);

        renderSuccessJson(memberPurchaseOrderList);
    }

    @ActionKey("/mobile/member/my/find")
    public void myFind() {
        String request_user_id = getRequest_user_id();

        User user = UserService.instance.find(request_user_id);
        Member member = MemberService.instance.find(user.getObject_id());
        String user_avatar = FileService.instance.getFile_path(user.getUser_avatar());

        Map<String, Object> result = new HashMap<String, Object>();
        result.put(User.USER_NAME, user.getUser_name());
        result.put(User.USER_AVATAR, user_avatar);
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
            MemberLevel memberLevel = MemberLevelService.instance.find(member.getMember_level_id());
            result.put(MemberLevel.MEMBER_LEVEL_NAME, memberLevel.getMember_level_name());
            result.put(MemberLevel.MEMBER_LEVEL_SORT, memberLevel.getMember_level_sort());
        }

        // 返回授权保证金
        BigDecimal certificate_amount = BigDecimal.ZERO;
        Certificate certificate = CertificateService.instance.userFind(request_user_id);
        if (certificate != null) {
            CertificatePay certificatePay = CertificatePayService.instance.find(certificate.getCertificate_id());
            if (certificatePay != null && certificatePay.getCertificate_amount() != null) {
                certificate_amount = certificatePay.getCertificate_amount();
            }
        }
        result.put(CertificatePay.CERTIFICATE_AMOUNT, certificate_amount);

        renderSuccessJson(result);
    }

    @ActionKey("/mobile/member/qrcode/find")
    public void qrcodeFind() {
        String request_user_id = getRequest_user_id();

        User user = UserService.instance.find(request_user_id);
        Member member = MemberService.instance.find(user.getObject_id());

        if (!member.getMember_status()) {
            throw new RuntimeException("您还没有通过审核");
        }

        String qrcode_id = "";

        if (ValidateUtil.isNullOrEmpty(member.getQrcode_id())) {
            qrcode_id = Util.getRandomUUID();
            String member_id = member.getMember_id();

            App app = AppService.instance.find(member.getApp_id());

            String wechat_app_id = ApiConfigKit.getAppId();
            if (!wechat_app_id.equals(app.getWechat_app_id())) {
                ApiConfigKit.setThreadLocalAppId(app.getWechat_app_id());
                AccessTokenApi.refreshAccessToken();
            }

            ApiResult apiResult = QrcodeApi.createPermanent(qrcode_id);
            Boolean qrcode_status = true;
            String qrcode_url = QrcodeApi.getShowQrcodeUrl(apiResult.getStr("ticket"));

            QrcodeService.instance.save(qrcode_id, member.getApp_id(), member_id, QrcodeType.MEMBER.getKey(), qrcode_url, 0, 0,
                    qrcode_status, request_user_id);

            MemberService.instance.qrcodeUpdate(member_id, qrcode_id, request_user_id);
        } else {
            qrcode_id = member.getQrcode_id();
        }

        Qrcode qrcode = QrcodeService.instance.find(qrcode_id);

        renderSuccessJson(qrcode.getQrcode_url());
    }

    @ActionKey("/mobile/member/purchase/find")
    public void purchaseFind() {
        String request_user_id = getRequest_user_id();

        User user = UserService.instance.find(request_user_id);
        Member result = MemberService.instance.find(user.getObject_id());

        result.put(User.USER_NAME, user.getUser_name());
        result.put(User.USER_AVATAR, FileService.instance.getFile_path(user.getUser_avatar()));

        if (ValidateUtil.isNullOrEmpty(result.getMember_level_id())) {
            result.put(MemberLevel.MEMBER_LEVEL_NAME, "");
        } else {
            MemberLevel memberLevel = MemberLevelService.instance.find(result.getMember_level_id());
            result.put(MemberLevel.MEMBER_LEVEL_NAME, memberLevel.getMember_level_name());
        }

        BigDecimal bill_amount = BigDecimal.ZERO;
        List<MemberDeliveryOrder> memberPurchaseOrderList = MemberDeliveryOrderService.instance.userIsPayList(request_user_id);
        for (MemberDeliveryOrder memberDeliveryOrder : memberPurchaseOrderList) {
            bill_amount = bill_amount.add(memberDeliveryOrder.getMember_delivery_order_amount());
        }
        List<Enchashment> enchashmentList = EnchashmentService.instance.userList(request_user_id);
        for (Enchashment enchashment : enchashmentList) {
            bill_amount = bill_amount.subtract(enchashment.getEnchashment_amount());
        }
        result.put(Bill.BILL_AMOUNT, bill_amount);

        BigDecimal certificate_amount = BigDecimal.ZERO;
        Certificate certificate = CertificateService.instance.userFind(request_user_id);
        if (certificate != null) {
            CertificatePay certificatePay = CertificatePayService.instance.find(certificate.getCertificate_id());
            if (certificatePay != null && certificatePay.getCertificate_amount() != null) {
                certificate_amount = certificatePay.getCertificate_amount();
            }
        }
        result.put(CertificatePay.CERTIFICATE_AMOUNT, certificate_amount);

        validateResponse(Member.MEMBER_ID, User.USER_NAME, User.USER_AVATAR, Member.MEMBER_PARENT_ID, Member.QRCODE_ID, MemberLevel.MEMBER_LEVEL_ID, MemberLevel.MEMBER_LEVEL_NAME, Bill.BILL_AMOUNT, CertificatePay.CERTIFICATE_AMOUNT, Member.MEMBER_STATUS);

        renderSuccessJson(result);
    }

    @ActionKey("/mobile/member/team/find")
    public void teamFind() {
        validateRequest(Member.MEMBER_ID);

        Member model = getModel(Member.class);
        String request_user_id = getRequest_user_id();

        Member member = MemberService.instance.find(model.getMember_id());

        User parentUser = UserService.instance.find(request_user_id);
        Member parentMember = MemberService.instance.find(parentUser.getObject_id());

        User user = UserService.instance.find(member.getUser_id());
        member.put(User.USER_NAME, user.getUser_name());
        member.put(User.USER_AVATAR, FileService.instance.getFile_path(user.getUser_avatar()));

        String member_level_name = "";
        if (!ValidateUtil.isNullOrEmpty(member.getMember_level_id())) {
            MemberLevel memberLevel = MemberLevelService.instance.find(member.getMember_level_id());
            member_level_name = memberLevel.getMember_level_name();
        }
        member.put(MemberLevel.MEMBER_LEVEL_NAME, member_level_name);

        member.put(Constant.IS_CHILDREN, member.getMember_parent_id().equals(parentMember.getMember_id()));

        validateResponse(Member.MEMBER_ID, Member.MEMBER_PARENT_ID, User.USER_NAME, User.USER_AVATAR, MemberLevel.MEMBER_LEVEL_NAME, Member.MEMBER_STATUS, Constant.IS_CHILDREN);

        renderSuccessJson(member);
    }

    @ActionKey("/mobile/member/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/member/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/member/delete")
    public void delete() {

        renderSuccessJson();
    }

}