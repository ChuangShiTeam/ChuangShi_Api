package com.nowui.chuangshi.api.trade.service;

import com.nowui.chuangshi.api.file.model.File;
import com.nowui.chuangshi.api.file.service.FileService;
import com.nowui.chuangshi.api.product.model.Product;
import com.nowui.chuangshi.api.product.model.ProductSku;
import com.nowui.chuangshi.api.product.service.ProductService;
import com.nowui.chuangshi.api.product.service.ProductSkuService;
import com.nowui.chuangshi.api.trade.dao.MemberPurchaseOrderDao;
import com.nowui.chuangshi.api.trade.model.MemberDeliveryOrder;
import com.nowui.chuangshi.api.trade.model.MemberPurchaseOrder;
import com.nowui.chuangshi.api.trade.model.MemberPurchaseOrderProductSku;
import com.nowui.chuangshi.api.user.model.User;
import com.nowui.chuangshi.api.user.service.UserService;
import com.nowui.chuangshi.common.service.Service;
import com.nowui.chuangshi.common.sql.Cnd;

import java.util.List;

public class MemberPurchaseOrderService extends Service {

    public static final MemberPurchaseOrderService instance = new MemberPurchaseOrderService();
    private final String MEMBER_PURCHASE_ORDER_ITEM_CACHE = "member_purchase_order_item_cache";
    private final MemberPurchaseOrderDao memberPurchaseOrderDao = new MemberPurchaseOrderDao();

    public Integer userIsFirstCount(String user_id, String member_level_id) {
        Cnd cnd = new Cnd();
        cnd.where(MemberPurchaseOrder.SYSTEM_STATUS, true);
        cnd.and(MemberPurchaseOrder.USER_ID, user_id);
        cnd.and(MemberPurchaseOrder.MEMBER_LEVEL_ID, member_level_id);
        cnd.and(MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_IS_PAY, true);

        Integer count = memberPurchaseOrderDao.count(cnd);
        return count;
    }

    public List<MemberPurchaseOrder> userList(String user_id) {
        Cnd cnd = new Cnd();
        cnd.where(MemberPurchaseOrder.SYSTEM_STATUS, true);
        cnd.and(MemberPurchaseOrder.USER_ID, user_id);
        cnd.desc(MemberPurchaseOrder.SYSTEM_CREATE_TIME);

        List<MemberPurchaseOrder> memberPurchaseOrderList = memberPurchaseOrderDao.list(cnd);
        for (MemberPurchaseOrder result : memberPurchaseOrderList) {
            MemberDeliveryOrder memberDeliveryOrder = MemberDeliveryOrderService.instance.purchaseOrderFind(result.getMember_purchase_order_id());
            if (memberDeliveryOrder != null) {
                User user = UserService.instance.find(memberDeliveryOrder.getUser_id());
                if (user != null) {
                    result.put(User.USER_NAME, user.getUser_name());
                    File file = FileService.instance.find(user.getUser_avatar());
                    result.put(User.USER_AVATAR, file.getFile_original_path());
                }
            }

            // 根据进货单获取商品列表
            List<MemberPurchaseOrderProductSku> memberPurchaseOrderProductSkuList = MemberPurchaseOrderProductSkuService.instance.MemberPurchaseOrderList(result.getMember_purchase_order_id());
            for (MemberPurchaseOrderProductSku memberPurchaseOrderProductSku : memberPurchaseOrderProductSkuList) {
                ProductSku productSku = ProductSkuService.instance.find(memberPurchaseOrderProductSku.getProduct_sku_id());
                Product product = ProductService.instance.find(productSku.getProduct_id());
                memberPurchaseOrderProductSku.put(Product.PRODUCT_NAME, product.getProduct_name()); // 商品名称
                memberPurchaseOrderProductSku.put(Product.PRODUCT_IMAGE, FileService.instance.getFile_path(product.getProduct_image()));
                memberPurchaseOrderProductSku.keep(MemberPurchaseOrderProductSku.PRODUCT_SKU_ID,
                        MemberPurchaseOrderProductSku.PRODUCT_SKU_AMOUNT,
                        MemberPurchaseOrderProductSku.PRODUCT_SKU_QUANTITY, Product.PRODUCT_NAME,
                        Product.PRODUCT_IMAGE);
            }
            result.put(MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_PRODUCT_SKU_LIST, memberPurchaseOrderProductSkuList);
        }
        return memberPurchaseOrderList;
    }

    public List<MemberPurchaseOrder> userIsPayList(String user_id) {
        Cnd cnd = new Cnd();
        cnd.where(MemberPurchaseOrder.SYSTEM_STATUS, true);
        cnd.and(MemberPurchaseOrder.USER_ID, user_id);
        cnd.and(MemberPurchaseOrder.MEMBER_PURCHASE_ORDER_IS_PAY, true);

        List<MemberPurchaseOrder> memberPurchaseOrderList = memberPurchaseOrderDao.list(cnd);
        return memberPurchaseOrderList;
    }

}