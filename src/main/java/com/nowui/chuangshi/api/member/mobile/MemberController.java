package com.nowui.chuangshi.api.member.mobile;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.bill.model.Bill;
import com.nowui.chuangshi.api.bill.service.BillService;
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
import com.nowui.chuangshi.api.member.service.MemberLevelService;
import com.nowui.chuangshi.api.member.service.MemberService;
import com.nowui.chuangshi.api.trade.model.MemberPurchaseOrder;
import com.nowui.chuangshi.api.trade.service.MemberPurchaseOrderService;
import com.nowui.chuangshi.api.user.model.User;
import com.nowui.chuangshi.api.user.service.UserService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.type.BillType;
import com.nowui.chuangshi.util.ValidateUtil;

import java.math.BigDecimal;
import java.util.List;

@ControllerKey("/mobile/member")
public class MemberController extends Controller {

    private final MemberService memberService = MemberService.me;

    @ActionKey("/mobile/member/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/member/children/address/list")
    public void childrenAddresslist() {
        validateRequest(MemberAddress.MEMBER_ID);
        MemberAddress model = getModel(MemberAddress.class);

        List<MemberAddress> memberAddressList = MemberService.me.list(new MemberAddress().where(MemberAddress.MEMBER_ID, model.getMember_id()));

        validateResponse(MemberAddress.MEMBER_ADDRESS_ID, MemberAddress.MEMBER_ADDRESS_NAME, MemberAddress.MEMBER_ADDRESS_MOBILE, MemberAddress.MEMBER_ADDRESS_POSTCODE, MemberAddress.MEMBER_ADDRESS_PROVINCE, MemberAddress.MEMBER_ADDRESS_CITY, MemberAddress.MEMBER_ADDRESS_AREA, MemberAddress.MEMBER_ADDRESS_ADDRESS);

        renderSuccessModeListlJson(memberAddressList);
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

//        BigDecimal bill_amount = BigDecimal.ZERO;
//        List<Bill> billList = BillService.me.list(new Bill().where(Bill.USER_ID, request_user_id).and(Bill.BILL_IS_INCOME, true));
//        for (Bill bill : billList) {
//            bill_amount.add(bill.getBill_amount());
//        }
//        result.put(Bill.BILL_AMOUNT, bill_amount);

        BigDecimal bill_amount = BigDecimal.ZERO;
        List<MemberPurchaseOrder> memberPurchaseOrderList = MemberPurchaseOrderService.me.list(new MemberPurchaseOrder().where(MemberPurchaseOrder.USER_ID, request_user_id).and(MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_IS_PAY, true));
        for (MemberPurchaseOrder memberPurchaseOrder : memberPurchaseOrderList) {
            bill_amount = bill_amount.add(memberPurchaseOrder.getMember_purchase_order_amount());
        }
        List<Enchashment> enchashmentList = EnchashmentService.me.list(new Enchashment().where(Enchashment.USER_ID, request_user_id));
        for (Enchashment enchashment : enchashmentList) {
            bill_amount = bill_amount.subtract(enchashment.getEnchashment_amount());
        }
        result.put(Bill.BILL_AMOUNT, bill_amount);

        BigDecimal certificate_amount = BigDecimal.ZERO;
        Certificate certificate = CertificateService.me.find(new Certificate().where(Certificate.USER_ID, request_user_id));
        if (certificate != null) {
            CertificatePay certificatePay = CertificatePayService.me.find(new CertificatePay().where(CertificatePay.CERTIFICATE_ID, certificate.getCertificate_id()));
            if (certificatePay != null && certificatePay.getCertificate_amount() != null) {
                certificate_amount = certificatePay.getCertificate_amount();
            }
        }
        result.put(CertificatePay.CERTIFICATE_AMOUNT, certificate_amount);

        validateResponse(Member.MEMBER_ID, User.USER_NAME, User.USER_AVATAR, Member.MEMBER_PARENT_ID, Member.QRCODE_ID, MemberLevel.MEMBER_LEVEL_ID, MemberLevel.MEMBER_LEVEL_NAME, Bill.BILL_AMOUNT, CertificatePay.CERTIFICATE_AMOUNT, Member.MEMBER_STATUS);

        renderSuccessModelJson(result);
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