package com.nowui.chuangshi.handler;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.jfinal.plugin.zbus.annotation.Handler;
import com.jfinal.plugin.zbus.annotation.Topic;
import com.jfinal.plugin.zbus.handler.TMsgHandler;
import com.nowui.chuangshi.model.App;
import com.nowui.chuangshi.model.Bill;
import com.nowui.chuangshi.model.BillCommission;
import com.nowui.chuangshi.model.Member;
import com.nowui.chuangshi.model.ProductSkuCommission;
import com.nowui.chuangshi.model.Trade;
import com.nowui.chuangshi.model.TradeCommossion;
import com.nowui.chuangshi.model.TradeProductSku;
import com.nowui.chuangshi.model.User;
import com.nowui.chuangshi.service.AppService;
import com.nowui.chuangshi.service.BillCommissionService;
import com.nowui.chuangshi.service.BillService;
import com.nowui.chuangshi.service.MemberService;
import com.nowui.chuangshi.service.ProductSkuCommissionService;
import com.nowui.chuangshi.service.TradeCommossionService;
import com.nowui.chuangshi.service.TradeProductSkuService;
import com.nowui.chuangshi.service.TradeService;
import com.nowui.chuangshi.service.UserService;
import com.nowui.chuangshi.util.MQUtil;
import com.nowui.chuangshi.util.Util;

@Topic(mq = "MQ", topic = "tradePay")
@Handler
public class TradePayHandler extends TMsgHandler<String> {

    private final TradeService tradeService = new TradeService();
    private final TradeProductSkuService tradeProductSkuService = new TradeProductSkuService();
    private final TradeCommossionService tradeCommossionService = new TradeCommossionService();
    private final BillService billService = new BillService();
    private final ProductSkuCommissionService productSkuCommissionService = new ProductSkuCommissionService();
    private UserService userService = new UserService();
    private MemberService memberService = new MemberService();
    private AppService appService = new AppService();
    private BillCommissionService billCommissionService = new BillCommissionService();

    @Override
    public void handle(String trade_id) {
        try {
            Trade trade = tradeService.findByTrade_id(trade_id);

            String user_id = trade.getUser_id();
            User user = userService.findByUser_id(user_id);
            Member member = memberService.findByMember_id(user.getObject_Id());

            // 根据应用信息 获取是否分成 和分成级数
            App app = appService.findByApp_id(trade.getTrade_id());
            Boolean app_is_commission = app.getApp_is_commission();
            Integer temp = 0;
            String member_parent_path = member.getMember_parent_path();
            member_parent_path = member_parent_path.trim().replace("'", "");
            String[] member_parent_id_list = member_parent_path.split(".");
            int length = member_parent_id_list.length;
            String[] member_list = null;

            for (int i = 0; i < temp; i++) {
                member_list[i] = member_parent_id_list[length - i];
            }

            List<TradeProductSku> tradeProductSkuList = tradeProductSkuService.listByTrade_id(trade_id);

            List<Bill> billList = new ArrayList<Bill>();
            List<BillCommission> billCommissionList = new ArrayList<BillCommission>();
            List<TradeCommossion> tradeCommossionList = new ArrayList<TradeCommossion>();

            Bill tradeMemberBill = new Bill();
            tradeMemberBill.setBill_id(Util.getRandomUUID());
            tradeMemberBill.setApp_id(trade.getApp_id());
            tradeMemberBill.setUser_id(user_id);
            tradeMemberBill.setBill_is_income(true);
            tradeMemberBill.setBill_amount(trade.getTrade_product_amount().add(trade.getTrade_express_amount()).subtract(trade.getTrade_discount_amount()));
            billList.add(tradeMemberBill);
            
            if (app_is_commission && member_list != null && member_list.length > 0) {
                for (String member_parent_id : member_list) {
                    Member member_parent = memberService.findByMember_id(member_parent_id);

                    Bill bill = new Bill();
                    bill.setBill_id(Util.getRandomUUID());
                    bill.setApp_id(trade.getApp_id());
                    bill.setUser_id(member_parent.getUser_id());
                    bill.setBill_is_income(true);
                    // 完善字段

                    BigDecimal bill_amount = BigDecimal.ZERO;

                    for (TradeProductSku tradeProductSku : tradeProductSkuList) {
                        List<ProductSkuCommission> productSkuCommissionList = productSkuCommissionService.listByProduct_sku_id(tradeProductSku.getProduct_sku_id());

                        TradeCommossion tradeCommossion = new TradeCommossion();
                        BillCommission billCommission = new BillCommission();
                        for (ProductSkuCommission productSkuCommission : productSkuCommissionList) {
                            if (productSkuCommission.getMember_level_id().equals(member_parent.getMember_level_id())) {

                                String member_name = userService.findByUser_id(member_parent.getUser_id()).getUser_name();
                                Integer a = productSkuCommission.getProduct_sku_commission();
                                BigDecimal b = tradeProductSku.getProduct_sku_amount();
                                BigDecimal c = new BigDecimal(a);

                                tradeCommossion.setTrade_id(trade_id);
                                tradeCommossion.setProduct_sku_id(tradeProductSku.getProduct_sku_id());
                                tradeCommossion.setMember_id(member_parent_id);
                                tradeCommossion.setMember_name(member_name);
                                tradeCommossion.setMember_level_id(productSkuCommission.getMember_level_id());
                                tradeCommossion.setMember_level_name(productSkuCommission.getMember_level_name());
                                tradeCommossion.setProduct_sku_commission(productSkuCommission.getProduct_sku_commission());
                                tradeCommossion.setProduct_sku_commission_amount(b.multiply(c));
                                tradeCommossionList.add(tradeCommossion);

                                billCommission.setBill_id(bill.getBill_id());
                                billCommission.setProduct_sku_id(tradeProductSku.getProduct_sku_id());
                                billCommission.setMember_id(member_parent_id);
                                billCommission.setMember_name(member_name);
                                billCommission.setMember_level_id(productSkuCommission.getMember_level_id());
                                billCommission.setMember_level_name(productSkuCommission.getMember_level_name());
                                billCommission.setProduct_sku_commission(productSkuCommission.getProduct_sku_commission());
                                billCommission.setProduct_sku_commission_amount(b.multiply(c));
                                billCommissionList.add(billCommission);

                                bill_amount.add(b.multiply(c));
                                break;
                            }
                        }
                    }

                    bill.setBill_amount(bill_amount);
                    billList.add(bill);
                }

                billCommissionService.save(billCommissionList);
                tradeCommossionService.save(tradeCommossionList);
            }

            billService.save(billList);

        } catch (Exception e) {

            Map<String, Object> exceptionMap = new HashMap<String, Object>();
            exceptionMap.put(com.nowui.chuangshi.model.Exception.APP_ID, "");
            exceptionMap.put(com.nowui.chuangshi.model.Exception.HTTP_ID, "");
            exceptionMap.put(com.nowui.chuangshi.model.Exception.EXCEPTION_CONTENT, e.toString());
            exceptionMap.put(com.nowui.chuangshi.model.Exception.SYSTEM_CREATE_USER_ID, "");
            MQUtil.sendSync("exception", JSON.toJSONString(exceptionMap));

            e.printStackTrace();
        }
    }

}