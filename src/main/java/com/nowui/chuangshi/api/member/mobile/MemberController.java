package com.nowui.chuangshi.api.member.mobile;

import com.jfinal.core.ActionKey;
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
import com.nowui.chuangshi.api.trade.model.MemberDeliveryOrder;
import com.nowui.chuangshi.api.trade.model.MemberPurchaseOrder;
import com.nowui.chuangshi.api.trade.service.MemberDeliveryOrderService;
import com.nowui.chuangshi.api.trade.service.MemberPurchaseOrderService;
import com.nowui.chuangshi.api.user.model.User;
import com.nowui.chuangshi.api.user.service.UserService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.util.ValidateUtil;

import java.math.BigDecimal;
import java.util.List;

@ControllerKey("/mobile/member")
public class MemberController extends Controller {

    @ActionKey("/mobile/member/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/member/children/address/list")
    public void childrenAddresslist() {
        validateRequest(MemberAddress.MEMBER_ID);
        MemberAddress model = getModel(MemberAddress.class);

        List<MemberAddress> memberAddressList = MemberAddressService.me.list(Cnd.where(MemberAddress.MEMBER_ID, model.getMember_id()));

        validateResponse(MemberAddress.MEMBER_ADDRESS_ID, MemberAddress.MEMBER_ADDRESS_NAME, MemberAddress.MEMBER_ADDRESS_MOBILE, MemberAddress.MEMBER_ADDRESS_POSTCODE, MemberAddress.MEMBER_ADDRESS_PROVINCE, MemberAddress.MEMBER_ADDRESS_CITY, MemberAddress.MEMBER_ADDRESS_AREA, MemberAddress.MEMBER_ADDRESS_ADDRESS);

        renderSuccessJson(memberAddressList);
    }

    @ActionKey("/mobile/member/children/purchase/order/list")
    public void childrenPurchaseOrderlist() {
        validateRequest(MemberAddress.MEMBER_ID);
        Member model = getModel(Member.class);

        Member member = MemberService.me.findById(model.getMember_id());

        List<MemberPurchaseOrder> memberPurchaseOrderList = MemberPurchaseOrderService.me.list(Cnd.where(MemberPurchaseOrder.USER_ID, model.getUser_id()));

        validateResponse(MemberAddress.MEMBER_ADDRESS_ID, MemberAddress.MEMBER_ADDRESS_NAME, MemberAddress.MEMBER_ADDRESS_MOBILE, MemberAddress.MEMBER_ADDRESS_POSTCODE, MemberAddress.MEMBER_ADDRESS_PROVINCE, MemberAddress.MEMBER_ADDRESS_CITY, MemberAddress.MEMBER_ADDRESS_AREA, MemberAddress.MEMBER_ADDRESS_ADDRESS);

        renderSuccessJson(memberPurchaseOrderList);
    }

    @ActionKey("/mobile/member/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/member/purchase/find")
    public void purchaseFind() {
        String request_user_id = getRequest_user_id();
        User user = UserService.me.findById(request_user_id);
        Member result = MemberService.me.findById(user.getObject_id());

        result.put(User.USER_NAME, user.getUser_name());
        result.put(User.USER_AVATAR, FileService.me.getFile_path(user.getUser_avatar()));

        if (ValidateUtil.isNullOrEmpty(result.getMember_level_id())) {
            result.put(MemberLevel.MEMBER_LEVEL_NAME, "");
        } else {
            MemberLevel memberLevel = MemberLevelService.me.findById(result.getMember_level_id());
            result.put(MemberLevel.MEMBER_LEVEL_NAME, memberLevel.getMember_level_name());
        }

        BigDecimal bill_amount = BigDecimal.ZERO;
        List<MemberDeliveryOrder> memberPurchaseOrderList = MemberDeliveryOrderService.me.list(Cnd.where(MemberDeliveryOrder.USER_ID, request_user_id).and(MemberDeliveryOrder.MEMBER_DELIVERY_ORDER_IS_PAY, true));
        for (MemberDeliveryOrder memberDeliveryOrder : memberPurchaseOrderList) {
            bill_amount = bill_amount.add(memberDeliveryOrder.getMember_delivery_order_amount());
        }
        List<Enchashment> enchashmentList = EnchashmentService.me.list(Cnd.where(Enchashment.USER_ID, request_user_id));
        for (Enchashment enchashment : enchashmentList) {
            bill_amount = bill_amount.subtract(enchashment.getEnchashment_amount());
        }
        result.put(Bill.BILL_AMOUNT, bill_amount);

        BigDecimal certificate_amount = BigDecimal.ZERO;
        Certificate certificate = CertificateService.me.find(Cnd.where(Certificate.USER_ID, request_user_id));
        if (certificate != null) {
            CertificatePay certificatePay = CertificatePayService.me.find(Cnd.where(CertificatePay.CERTIFICATE_ID, certificate.getCertificate_id()));
            if (certificatePay != null && certificatePay.getCertificate_amount() != null) {
                certificate_amount = certificatePay.getCertificate_amount();
            }
        }
        result.put(CertificatePay.CERTIFICATE_AMOUNT, certificate_amount);

        validateResponse(Member.MEMBER_ID, User.USER_NAME, User.USER_AVATAR, Member.MEMBER_PARENT_ID, Member.QRCODE_ID, MemberLevel.MEMBER_LEVEL_ID, MemberLevel.MEMBER_LEVEL_NAME, Bill.BILL_AMOUNT, CertificatePay.CERTIFICATE_AMOUNT, Member.MEMBER_STATUS);

        renderSuccessJson(result);
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