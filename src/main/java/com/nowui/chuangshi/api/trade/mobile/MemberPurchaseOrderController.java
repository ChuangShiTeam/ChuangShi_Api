package com.nowui.chuangshi.api.trade.mobile;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.file.model.File;
import com.nowui.chuangshi.api.file.service.FileService;
import com.nowui.chuangshi.api.member.model.Member;
import com.nowui.chuangshi.api.member.service.MemberService;
import com.nowui.chuangshi.api.product.model.Product;
import com.nowui.chuangshi.api.product.model.ProductSku;
import com.nowui.chuangshi.api.product.service.ProductService;
import com.nowui.chuangshi.api.product.service.ProductSkuService;
import com.nowui.chuangshi.api.trade.model.MemberDeliveryOrder;
import com.nowui.chuangshi.api.trade.model.MemberPurchaseOrder;
import com.nowui.chuangshi.api.trade.model.MemberPurchaseOrderProductSku;
import com.nowui.chuangshi.api.trade.service.MemberDeliveryOrderService;
import com.nowui.chuangshi.api.trade.service.MemberPurchaseOrderProductSkuService;
import com.nowui.chuangshi.api.trade.service.MemberPurchaseOrderService;
import com.nowui.chuangshi.api.user.model.User;
import com.nowui.chuangshi.api.user.service.UserService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;

import java.util.List;

@ControllerKey("/mobile/member/purchase/order")
public class MemberPurchaseOrderController extends Controller {

    @ActionKey("/mobile/member/purchase/order/list")
    public void list() {
        String request_user_id = getRequest_user_id();

        List<MemberPurchaseOrder> resultList = MemberPurchaseOrderService.instance.userList(request_user_id);

        validateResponse(User.USER_NAME,
                User.USER_AVATAR,
                MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_ID,
                MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_IS_WAREHOUSE_RECEIVE,
                MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_RECEIVER_NAME,
                MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_RECEIVER_MOBILE,
                MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_FLOW,
                MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_TOTAL_QUANTITY,
                MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_PRODUCT_AMOUNT,
                MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_AMOUNT,
                MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_PRODUCT_SKU_LIST,
                MemberPurchaseOrder.SYSTEM_CREATE_TIME);

        renderSuccessJson(resultList);
    }

    @ActionKey("/mobile/member/purchase/order/children/list")
    public void childrenList() {
        validateRequest(Member.MEMBER_ID);

        Member model = getModel(Member.class);
        String request_user_id = getRequest_user_id();

        Member member = MemberService.instance.find(model.getMember_id());

        List<MemberPurchaseOrder> resultList = MemberPurchaseOrderService.instance.userList(member.getUser_id());

        validateResponse(User.USER_NAME,
                User.USER_AVATAR,
                MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_ID,
                MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_IS_WAREHOUSE_RECEIVE,
                MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_RECEIVER_NAME,
                MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_RECEIVER_MOBILE,
                MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_FLOW,
                MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_TOTAL_QUANTITY,
                MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_PRODUCT_AMOUNT,
                MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_AMOUNT,
                MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_PRODUCT_SKU_LIST,
                MemberPurchaseOrder.SYSTEM_CREATE_TIME);

        renderSuccessJson(resultList);
    }

    @ActionKey("/mobile/member/purchase/order/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/member/purchase/order/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/member/purchase/order/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/member/purchase/order/delete")
    public void delete() {

        renderSuccessJson();
    }

}