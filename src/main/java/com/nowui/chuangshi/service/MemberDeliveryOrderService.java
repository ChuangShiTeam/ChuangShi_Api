package com.nowui.chuangshi.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.nowui.chuangshi.cache.MemberDeliveryOrderCache;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.model.Express;
import com.nowui.chuangshi.model.Member;
import com.nowui.chuangshi.model.MemberDeliveryOrder;
import com.nowui.chuangshi.model.MemberDeliveryOrderExpress;
import com.nowui.chuangshi.model.MemberDeliveryOrderProductSku;
import com.nowui.chuangshi.model.MemberPurchaseOrder;
import com.nowui.chuangshi.model.StockOut;
import com.nowui.chuangshi.model.StockOutProductSku;
import com.nowui.chuangshi.model.User;
import com.nowui.chuangshi.type.ExpressFlow;
import com.nowui.chuangshi.type.MemberDeliveryOrderFlow;
import com.nowui.chuangshi.type.MemberPurchaseOrderFlow;
import com.nowui.chuangshi.type.StockType;
import com.nowui.chuangshi.util.Util;

public class MemberDeliveryOrderService extends Service {

    private MemberDeliveryOrderCache memberDeliveryOrderCache = new MemberDeliveryOrderCache();
    
    private MemberDeliveryOrderProductSkuService memberDeliveryOrderProductSkuService = new MemberDeliveryOrderProductSkuService();
    
    private MemberDeliveryOrderExpressService memberDeliveryOrderExpressService = new MemberDeliveryOrderExpressService();
    
    private MemberDeliveryOrderPayService memberDeliveryOrderPayService = new MemberDeliveryOrderPayService();
    
    private ExpressService expressService = new ExpressService();
    
    private MemberPurchaseOrderService memberPurchaseOrderService = new MemberPurchaseOrderService();
    
    private MemberPurchaseOrderExpressService memberPurchaseOrderExpressService = new MemberPurchaseOrderExpressService();
    
    private StockOutService stockOutService = new StockOutService();
    
    private StockService stockService = new StockService();
    
    private UserService userService = new UserService();
    
    private MemberService memberService = new MemberService();
    
    private ProductSkuPriceService productSkuPriceService = new ProductSkuPriceService();

    public Integer countByApp_idOrLikeUser_nameOrLikeMember_delivery_order_receiver_name(String app_id, String user_name, String member_delivery_order_receiver_name) {
        return memberDeliveryOrderCache.countByApp_idOrLikeUser_nameOrLikeMember_delivery_order_receiver_name(app_id, user_name, member_delivery_order_receiver_name);
    }

    public Integer countByOrApp_idOrLikeUser_nameOrLikeMember_delivery_order_receiver_name(String app_id, String user_name, String member_delivery_order_receiver_name) {
        return memberDeliveryOrderCache.countByOrApp_idOrLikeUser_nameOrLikeMember_delivery_order_receiver_name(app_id, user_name, member_delivery_order_receiver_name);
    }
    
    public Integer countWarehouse_deliverByApp_idOrLikeUser_nameOrLikeMember_delivery_order_receiver_name(String app_id, String user_name, String member_delivery_order_receiver_name) {
        return memberDeliveryOrderCache.countWarehouse_deliverByApp_idOrLikeUser_nameOrLikeMember_delivery_order_receiver_name(app_id, user_name, member_delivery_order_receiver_name);
    }
    
    public Integer countWarehouse_deliverByOrApp_idOrLikeUser_nameOrLikeMember_delivery_order_receiver_name(String app_id, String user_name, String member_delivery_order_receiver_name) {
        return memberDeliveryOrderCache.countWarehouse_deliverByOrApp_idOrLikeUser_nameOrLikeMember_delivery_order_receiver_name(app_id, user_name, member_delivery_order_receiver_name);
    }

    public List<MemberDeliveryOrder> listByApp_idAndSystem_create_timeAndLimit(String app_id, Date system_create_time, int m, int n) {
        return memberDeliveryOrderCache.listByApp_idAndSystem_create_timeAndLimit(app_id, system_create_time, m, n);
    }

    public List<MemberDeliveryOrder> listByApp_idOrLikeUser_nameOrLikeMember_delivery_order_receiver_nameAndLimit(String app_id, String user_name, String member_delivery_order_receiver_name, int m, int n) {
        return memberDeliveryOrderCache.listByApp_idOrLikeUser_nameOrLikeMember_delivery_order_receiver_nameAndLimit(app_id, user_name, member_delivery_order_receiver_name, m, n);
    }

    public List<MemberDeliveryOrder> listByOrApp_idOrLikeUser_nameOrLikeMember_delivery_order_receiver_nameAndLimit(String app_id, String user_name, String member_delivery_order_receiver_name, int m, int n) {
        return memberDeliveryOrderCache.listByOrApp_idOrLikeUser_nameOrLikeMember_delivery_order_receiver_nameAndLimit(app_id, user_name, member_delivery_order_receiver_name, m, n);
    }
    
    public List<MemberDeliveryOrder> listWarehouse_deliverByApp_idOrLikeUser_nameOrLikeMember_delivery_order_receiver_nameAndLimit(String app_id, String user_name, String member_delivery_order_receiver_name, int m, int n) {
        return memberDeliveryOrderCache.listWarehouse_deliverByApp_idOrLikeUser_nameOrLikeMember_delivery_order_receiver_nameAndLimit(app_id, user_name, member_delivery_order_receiver_name, m, n);
    }
    
    public List<MemberDeliveryOrder> listWarehouse_deliverByOrApp_idOrLikeUser_nameOrLikeMember_delivery_order_receiver_nameAndLimit(String app_id, String user_name, String member_delivery_order_receiver_name, int m, int n) {
        return memberDeliveryOrderCache.listWarehouse_deliverByOrApp_idOrLikeUser_nameOrLikeMember_delivery_order_receiver_nameAndLimit(app_id, user_name, member_delivery_order_receiver_name, m, n);
    }
    
    public List<MemberDeliveryOrder> listByUser_id(String user_id) {
    	return memberDeliveryOrderCache.listByUser_id(user_id);
    }

    public MemberDeliveryOrder findByMember_delivery_order_id(String member_delivery_order_id) {
        return memberDeliveryOrderCache.findByMember_delivery_order_id(member_delivery_order_id);
    }

    public Boolean save(String member_delivery_order_id, String app_id, String member_purchase_order_id, String user_id, BigDecimal member_delivery_order_amount, Integer member_delivery_order_total_quantity, String member_delivery_order_receiver_name, String member_delivery_order_receiver_mobile, String member_delivery_order_receiver_province, String member_delivery_order_receiver_city, String member_delivery_order_receiver_area, String member_delivery_order_receiver_address, String member_delivery_order_express_pay_way, String member_delivery_order_express_shipper_code, Boolean member_delivery_order_is_pay, Boolean member_delivery_order_is_warehouse_deliver, String member_delivery_order_flow, Boolean member_delivery_order_is_complete, String system_create_user_id) {
        return memberDeliveryOrderCache.save(member_delivery_order_id, app_id, member_purchase_order_id, user_id, member_delivery_order_amount, member_delivery_order_total_quantity, member_delivery_order_receiver_name, member_delivery_order_receiver_mobile, member_delivery_order_receiver_province, member_delivery_order_receiver_city, member_delivery_order_receiver_area, member_delivery_order_receiver_address, member_delivery_order_express_pay_way, member_delivery_order_express_shipper_code, member_delivery_order_is_pay, member_delivery_order_is_warehouse_deliver, member_delivery_order_flow, member_delivery_order_is_complete, system_create_user_id);
    }

    public Boolean updateValidateSystem_version(String member_delivery_order_id, String member_purchase_order_id, BigDecimal member_delivery_order_amount, Integer member_delivery_order_total_quantity, String member_delivery_order_receiver_name, String member_delivery_order_receiver_mobile, String member_delivery_order_receiver_province, String member_delivery_order_receiver_city, String member_delivery_order_receiver_area, String member_delivery_order_receiver_address, String member_delivery_order_express_pay_way, String member_delivery_order_express_shipper_code, Boolean member_delivery_order_is_pay, Boolean member_delivery_order_is_warehouse_deliver, String member_delivery_order_flow, Boolean member_delivery_order_is_complete, String system_update_user_id, Integer system_version) {
        return memberDeliveryOrderCache.updateValidateSystem_version(member_delivery_order_id, member_purchase_order_id, member_delivery_order_amount, member_delivery_order_total_quantity, member_delivery_order_receiver_name, member_delivery_order_receiver_mobile, member_delivery_order_receiver_province, member_delivery_order_receiver_city, member_delivery_order_receiver_area, member_delivery_order_receiver_address, member_delivery_order_express_pay_way, member_delivery_order_express_shipper_code, member_delivery_order_is_pay, member_delivery_order_is_warehouse_deliver, member_delivery_order_flow, member_delivery_order_is_complete, system_update_user_id, system_version);
    }

    public Boolean deleteByMember_delivery_order_idAndSystem_update_user_idValidateSystem_version(String member_delivery_order_id, String system_update_user_id, Integer system_version) {
        return memberDeliveryOrderCache.deleteByMember_delivery_order_idAndSystem_update_user_idValidateSystem_version(member_delivery_order_id, system_update_user_id, system_version);
    }
    
    public Boolean updateMember_delivery_order_flowAndMember_delivery_order_is_payByMember_delivery_order_idValidateSystem_version(String member_delivery_order_id, String member_delivery_order_flow, Boolean member_delivery_order_is_pay, String system_update_user_id, Integer system_version) {
        return memberDeliveryOrderCache.updateMember_delivery_order_flowAndMember_delivery_order_is_payByMember_delivery_order_idValidateSystem_version(member_delivery_order_id, member_delivery_order_flow, member_delivery_order_is_pay, system_update_user_id, system_version);
    }
    
    public Boolean updateMember_delivery_order_flowAndMember_delivery_order_is_warehouse_deliverByMember_delivery_order_idValidateSystem_version(String member_delivery_order_id, String member_delivery_order_flow, Boolean member_delivery_order_is_warehouse_deliver, String system_update_user_id, Integer system_version) {
        return memberDeliveryOrderCache.updateMember_delivery_order_flowAndMember_delivery_order_is_warehouse_deliverByMember_delivery_order_idValidateSystem_version(member_delivery_order_id, member_delivery_order_flow, member_delivery_order_is_warehouse_deliver, system_update_user_id, system_version);
    }
    
    public Boolean updateMember_delivery_order_flowByMember_delivery_order_idValidateSystem_version(String member_delivery_order_id, String member_delivery_order_flow, String system_update_user_id, Integer system_version) {
        return memberDeliveryOrderCache.updateMember_delivery_order_flowByMember_delivery_order_idValidateSystem_version(member_delivery_order_id, member_delivery_order_flow, system_update_user_id, system_version);
    }
    
    public Boolean updateMember_delivery_order_flowAndMember_delivery_order_is_completeByMember_delivery_order_idValidateSystem_version(String member_delivery_order_id, String member_delivery_order_flow, Boolean member_delivery_order_is_complete, String system_update_user_id, Integer system_version) {
        return memberDeliveryOrderCache.updateMember_delivery_order_flowAndMember_delivery_order_is_completeByMember_delivery_order_idValidateSystem_version(member_delivery_order_id, member_delivery_order_flow, member_delivery_order_is_complete, system_update_user_id, system_version);
    }
    
    public Boolean expressSave(String express_id, String member_delivery_order_id, String express_no, BigDecimal express_cost, String express_shipper_code, String express_remark, String request_user_id) {
        MemberDeliveryOrder memberDeliveryOrder = findByMember_delivery_order_id(member_delivery_order_id);
        if (memberDeliveryOrder == null) {
            throw new RuntimeException("找不到发货单");
        }
//        if (!(MemberDeliveryOrderFlow.WAIT_WAREHOUSE_SEND.getKey().equals(memberDeliveryOrder.getMember_delivery_order_flow()))) {
//            throw new RuntimeException("发货单处于待仓库发货时才可添加快递单");
//        }
        
        //保存快递单信息
        Boolean result = expressService.save(express_id, memberDeliveryOrder.getApp_id(), express_shipper_code,
                express_no, "", memberDeliveryOrder.getMember_delivery_order_receiver_name(), "", memberDeliveryOrder.getMember_delivery_order_receiver_mobile(), "",
                memberDeliveryOrder.getMember_delivery_order_receiver_province(), memberDeliveryOrder.getMember_delivery_order_receiver_city(), memberDeliveryOrder.getMember_delivery_order_receiver_area(),
                memberDeliveryOrder.getMember_delivery_order_receiver_address(), "", "", "", "", "", "", "", "", "", express_cost,
                memberDeliveryOrder.getMember_delivery_order_is_pay(), memberDeliveryOrder.getMember_delivery_order_express_pay_way(), "", ExpressFlow.NOTRACK.getValue(), false,
                express_remark, request_user_id);
        
        if (result) {
            memberDeliveryOrderExpressService.save(member_delivery_order_id, express_id, request_user_id);
            if (StringUtils.isNotBlank(memberDeliveryOrder.getMember_purchase_order_id())) {
                memberPurchaseOrderExpressService.save(memberDeliveryOrder.getMember_purchase_order_id(), express_id, request_user_id);
            }
        }
        return result;
    }

    public Boolean expressDelete(String member_delivery_order_id, String express_id, String request_user_id, Integer system_version) {
        MemberDeliveryOrder memberDeliveryOrder = findByMember_delivery_order_id(member_delivery_order_id);
        if (memberDeliveryOrder == null) {
            throw new RuntimeException("找不到发货单");
        }
        if (!(MemberDeliveryOrderFlow.WAIT_WAREHOUSE_SEND.getKey().equals(memberDeliveryOrder.getMember_delivery_order_flow()))) {
            throw new RuntimeException("发货单处于待仓库发货时才可删除快递单");
        }
        Boolean result = expressService.deleteByExpress_idAndSystem_update_user_idValidateSystem_version(express_id, request_user_id, system_version);
        if (result) {
            memberDeliveryOrderExpressService.deleteByMember_delivery_order_idAndExpress_idAndSystem_update_user_id(member_delivery_order_id, express_id, request_user_id);
            if (StringUtils.isNotBlank(memberDeliveryOrder.getMember_purchase_order_id())) {
                memberPurchaseOrderExpressService.deleteByMember_purchase_order_idAndExpress_idAndSystem_update_user_id(memberDeliveryOrder.getMember_purchase_order_id(), express_id, request_user_id);
            }
        }
        return result;
    }

    public Boolean warehouseDeliver(String member_delivery_order_id, String warehouse_id, String request_user_id) {
        MemberDeliveryOrder member_delivery_order = findByMember_delivery_order_id(member_delivery_order_id);
        if (member_delivery_order == null) {
            throw new RuntimeException("找不到发货单");
        }
        //更新发货单状态，及会员出库
        List<MemberDeliveryOrderProductSku> memberDeliveryOrderProductSkuList = memberDeliveryOrderProductSkuService.listByMember_delivery_order_id(member_delivery_order_id);
        List<StockOutProductSku> stockOutProductSkuList = new ArrayList<StockOutProductSku>();
        for (MemberDeliveryOrderProductSku memberDeliveryOrderProductSku : memberDeliveryOrderProductSkuList) {
            StockOutProductSku stockOutProductSku = new StockOutProductSku();
            stockOutProductSku.setProduct_sku_id(memberDeliveryOrderProductSku.getProduct_sku_id());
            stockOutProductSku.setProduct_sku_quantity(memberDeliveryOrderProductSku.getProduct_sku_quantity());
            stockOutProductSkuList.add(stockOutProductSku);
        }
        //出库
        Boolean result = stockOutService.save(member_delivery_order.getApp_id(), warehouse_id, member_delivery_order_id, member_delivery_order.getUser_id(), StockType.MEMBER.getKey(), stockOutProductSkuList, request_user_id);

        if (result) {
            // 更新发货单流程为待收货
            this.updateMember_delivery_order_flowByMember_delivery_order_idValidateSystem_version(member_delivery_order_id, MemberDeliveryOrderFlow.WAIT_RECEIVE.getKey(), request_user_id, member_delivery_order.getSystem_version());
            Boolean is_direct_deliver = false;
            //根据进货单是否仓库代收来判断是否直接则增减库存
            if (StringUtils.isNotBlank(member_delivery_order.getMember_purchase_order_id())) {
                MemberPurchaseOrder memberPurchaseOrder = memberPurchaseOrderService.findByMember_purchase_order_id(member_delivery_order.getMember_purchase_order_id());
                if (memberPurchaseOrder.getMember_purchase_order_is_warehouse_receive()) {
                    is_direct_deliver = true;
                }
                // 更新进货单流程为待收货
                memberPurchaseOrderService.updateMember_purchase_order_flowByMember_purchase_order_idAndSystem_version(memberPurchaseOrder.getMember_purchase_order_id(), MemberPurchaseOrderFlow.WAIT_RECEIVE.getKey(), request_user_id, memberPurchaseOrder.getSystem_version());
            }
            
            if (!is_direct_deliver) {
                //不是直接发货，需填写快递单, 订阅快递
                List<Express> express_list = memberDeliveryOrderExpressService.listByMember_delivery_order_id(member_delivery_order_id);
                if (express_list == null || express_list.size() == 0) {
                    throw new RuntimeException("需填写快递单");
                }
                //快递订阅
                for (Express express : express_list) {
                    expressService.subscription(express.getExpress_id(), Constant.EXPRESS_ORDER_CODE_MEMBER_DELIVERY_ORDER, express.getExpress_shipper_code(), express.getExpress_no());
                }
            } 
        }
        
        return result;
    }
    
    public Boolean warehouseDeliverSave(String app_id, String user_id, String member_delivery_order_receiver_name, String member_delivery_order_receiver_mobile, String member_delivery_order_receiver_province, String member_delivery_order_receiver_city, String member_delivery_order_receiver_area, String member_delivery_order_receiver_address, String member_delivery_order_express_pay_way, String member_delivery_order_express_shipper_code, BigDecimal member_delivery_order_amount, Boolean member_delivery_order_is_pay, List<MemberDeliveryOrderProductSku> memberDeliveryOrderProductSkuList, String system_create_user_id) {
        Integer member_delivery_order_total_quantity = 0;
        String member_delivery_order_id = Util.getRandomUUID();
        List<MemberDeliveryOrderProductSku> list = new ArrayList<MemberDeliveryOrderProductSku>();
        User user = userService.findByUser_id(user_id);
        Member member = memberService.findByMember_id(user.getObject_Id());
        for (MemberDeliveryOrderProductSku memberDeliveryOrderProductSku : memberDeliveryOrderProductSkuList) {
            if (StringUtils.isBlank(memberDeliveryOrderProductSku.getProduct_sku_id())) {
                throw new RuntimeException("商品skuid不能为空");
            }
            if (memberDeliveryOrderProductSku.getProduct_sku_quantity() == null) {
                throw new RuntimeException("商品数量不能为空");
            }
            if (memberDeliveryOrderProductSku.getProduct_sku_quantity() <= 0) {
                throw new RuntimeException("商品数量必须大于0");
            }
            //判断会员库存是否足够
        	Integer stock_quantity = stockService.sumQuantityByApp_idOrWarehouse_idAndObject_idAndProduct_sku_id(app_id, null, user_id, memberDeliveryOrderProductSku.getProduct_sku_id());
        	if (memberDeliveryOrderProductSku.getProduct_sku_quantity() > stock_quantity) {
        		throw new RuntimeException("库存不足");
        	}
        	BigDecimal product_sku_price = productSkuPriceService.findByProduct_sku_idAndMember_level_id(
        			memberDeliveryOrderProductSku.getProduct_sku_id(), member.getMember_level_id());
            BigDecimal product_sku_amount = product_sku_price
                    .multiply(new BigDecimal(memberDeliveryOrderProductSku.getProduct_sku_quantity()));

        	memberDeliveryOrderProductSku.setMember_delivery_order_id(member_delivery_order_id);
        	memberDeliveryOrderProductSku.setProduct_snap_id("");
        	memberDeliveryOrderProductSku.setProduct_sku_amount(product_sku_amount);
        	memberDeliveryOrderProductSku.setSystem_create_user_id(system_create_user_id);
        	memberDeliveryOrderProductSku.setSystem_create_time(new Date());
        	memberDeliveryOrderProductSku.setSystem_update_user_id(system_create_user_id);
        	memberDeliveryOrderProductSku.setSystem_update_time(new Date());
        	memberDeliveryOrderProductSku.setSystem_version(0);
        	memberDeliveryOrderProductSku.setSystem_status(true);
            
        	member_delivery_order_total_quantity += memberDeliveryOrderProductSku.getProduct_sku_quantity();
            list.add(memberDeliveryOrderProductSku);
        }
        Boolean member_delivery_order_is_complete = false;
        String member_delivery_order_flow = MemberDeliveryOrderFlow.WAIT_WAREHOUSE_SEND.getKey();
        Boolean member_delivery_order_is_warehouse_deliver = true;
        Boolean result = memberDeliveryOrderCache.save(member_delivery_order_id, app_id, "", user_id, 
        		member_delivery_order_amount, member_delivery_order_total_quantity, 
        		member_delivery_order_receiver_name, member_delivery_order_receiver_mobile, 
        		member_delivery_order_receiver_province, member_delivery_order_receiver_city, 
        		member_delivery_order_receiver_area, member_delivery_order_receiver_address, 
        		member_delivery_order_express_pay_way, member_delivery_order_express_shipper_code, 
        		member_delivery_order_is_pay, member_delivery_order_is_warehouse_deliver, 
        		member_delivery_order_flow, member_delivery_order_is_complete, system_create_user_id);
        if (result) {
            memberDeliveryOrderProductSkuService.batchSave(list);
        }
        return result;
    }

	public boolean updatePay(String member_delivery_order_id, String member_delivery_order_pay_type,
			String member_delivery_order_pay_number, String member_delivery_order_pay_account,
			String member_delivery_order_pay_time, String member_delivery_order_pay_result, String user_id,
			Integer system_version) {
		Boolean flag = memberDeliveryOrderPayService.save(member_delivery_order_id, member_delivery_order_pay_type,
				member_delivery_order_pay_number, member_delivery_order_pay_account,
				member_delivery_order_pay_time, member_delivery_order_pay_result, user_id);
        if (flag) {
            flag = updateMember_delivery_order_flowAndMember_delivery_order_is_payByMember_delivery_order_idValidateSystem_version(
            		member_delivery_order_id, MemberDeliveryOrderFlow.WAIT_SEND.getKey(), true, user_id, system_version);
        }
        return flag;
	}

	public void updateFinish(String member_delivery_order_id) {
	    MemberDeliveryOrder memberDeliveryOrder = findByMember_delivery_order_id(member_delivery_order_id);
	    //只有当发货单处于待收货状态时才可以完成
	    if (MemberDeliveryOrderFlow.WAIT_RECEIVE.getKey().equals(memberDeliveryOrder.getMember_delivery_order_flow())) {
	        if (StringUtils.isNotBlank(memberDeliveryOrder.getMember_purchase_order_id())) {
	            List<StockOut> stockOutList = stockOutService.listByDelivery_order_id(member_delivery_order_id);
	            if (stockOutList != null && stockOutList.size() > 0) {
	                String warehouse_id = stockOutList.get(0).getWarehouse_id();
	                memberPurchaseOrderService.updateFinish(memberDeliveryOrder.getMember_purchase_order_id(), warehouse_id);
	            }
	        }
	        this.updateMember_delivery_order_flowAndMember_delivery_order_is_completeByMember_delivery_order_idValidateSystem_version(member_delivery_order_id, MemberDeliveryOrderFlow.COMPLETE.getKey(), true, memberDeliveryOrder.getSystem_create_user_id(), memberDeliveryOrder.getSystem_version());
  
	    }
	}

}