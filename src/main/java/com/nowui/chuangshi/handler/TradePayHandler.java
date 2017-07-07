package com.nowui.chuangshi.handler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jfinal.plugin.zbus.annotation.Handler;
import com.jfinal.plugin.zbus.annotation.Topic;
import com.jfinal.plugin.zbus.handler.TMsgHandler;
import com.nowui.chuangshi.model.Member;
import com.nowui.chuangshi.model.ProductSkuCommission;
import com.nowui.chuangshi.model.Trade;
import com.nowui.chuangshi.model.TradeProductSku;
import com.nowui.chuangshi.model.User;
import com.nowui.chuangshi.service.BillService;
import com.nowui.chuangshi.service.MemberService;
import com.nowui.chuangshi.service.ProductSkuCommissionService;
import com.nowui.chuangshi.service.TradeCommossionService;
import com.nowui.chuangshi.service.TradePayService;
import com.nowui.chuangshi.service.TradeProductSkuService;
import com.nowui.chuangshi.service.TradeService;
import com.nowui.chuangshi.service.UserService;
import com.nowui.chuangshi.util.MQUtil;

@Topic(mq = "MQ", topic = "tradePay")
@Handler
public class TradePayHandler extends TMsgHandler<String> {

    private final TradeService tradeService = new TradeService();
    private final TradeProductSkuService tradeProductSkuService = new TradeProductSkuService();
    private final TradePayService tradePayService = new TradePayService();
    private final TradeCommossionService tadeCommossionService = new TradeCommossionService();
    private final BillService billService = new BillService();
    private final ProductSkuCommissionService productSkuCommissionService = new ProductSkuCommissionService();
    private UserService userService = new UserService();
    private MemberService memberService = new MemberService();

    @Override
    public void handle(String json) {
        try {
            JSONObject jsonObject = JSONObject.parseObject(json);
            String trade_id = jsonObject.getString(Trade.TRADE_ID);

            Trade trade = tradeService.findByTrade_id(trade_id);

            String user_id = trade.getUser_id();
            User user = userService.findByUser_id(user_id);
            Member member = memberService.findByMember_id(user.getObjectId());

            String member_parent_path = member.getMember_parent_path();
            member_parent_path = member_parent_path.trim().replace("'", "");
            String[] member_parent_id_list = member_parent_path.split(".");

            List<TradeProductSku> tradeProductSkuList = tradeProductSkuService.listByTrade_id(trade_id);

            for (String member_parent_id : member_parent_id_list) {
                Member member_parent = memberService.findByMember_id(member_parent_id);
                for (TradeProductSku tradeProductSku : tradeProductSkuList) {
                    List<ProductSkuCommission> productSkuCommissionList = productSkuCommissionService.listByProduct_sku_id(tradeProductSku.getProduct_sku_id());
                    for (ProductSkuCommission productSkuCommission : productSkuCommissionList) {
                        if (productSkuCommission.getMember_level_id().equals(member_parent.getMember_level_id())) {
                            tadeCommossionService.
                        }
                    }
                }
            }

            
            tadeCommossionService.save(trade_id, product_sku_id, member_id, member_name, member_level_id, member_level_name, product_sku_commission, product_sku_commission_amount, system_create_user_id);
            
        } catch (Exception e) {
            JSONObject jsonObject = JSONObject.parseObject(json);

            Map<String, Object> exceptionMap = new HashMap<String, Object>();
            exceptionMap.put(com.nowui.chuangshi.model.Exception.APP_ID, jsonObject.getString(com.nowui.chuangshi.model.Exception.APP_ID));
            exceptionMap.put(com.nowui.chuangshi.model.Exception.HTTP_ID, jsonObject.getString(com.nowui.chuangshi.model.Exception.HTTP_ID));
            exceptionMap.put(com.nowui.chuangshi.model.Exception.EXCEPTION_CONTENT, e.toString());
            exceptionMap.put(com.nowui.chuangshi.model.Exception.SYSTEM_CREATE_USER_ID, jsonObject.getString(com.nowui.chuangshi.model.Exception.SYSTEM_CREATE_USER_ID));
            MQUtil.sendSync("exception", JSON.toJSONString(exceptionMap));

            e.printStackTrace();
        }
    }

}