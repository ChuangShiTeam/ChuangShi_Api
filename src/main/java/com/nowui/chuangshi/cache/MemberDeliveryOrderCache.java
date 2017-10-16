package com.nowui.chuangshi.cache;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.dao.MemberDeliveryOrderDao;
import com.nowui.chuangshi.model.MemberDeliveryOrder;
import com.nowui.chuangshi.util.CacheUtil;

public class MemberDeliveryOrderCache extends Cache {

    public static final String MEMBER_DELIVERY_ORDER_BY_MEMBER_DELIVERY_ORDER_ID_CACHE = "member_delivery_order_by_member_delivery_order_id_cache";
    
    public static final String MEMBER_DELIVERY_ORDER_NUMBER_LIST_CACHE = "member_delivery_order_number_list_cache";

    private MemberDeliveryOrderDao memberDeliveryOrderDao = new MemberDeliveryOrderDao();

    public Integer countByApp_idOrLikeUser_nameOrLikeMember_delivery_order_receiver_name(String app_id, String user_name, String member_delivery_order_receiver_name) {
        return memberDeliveryOrderDao.countByApp_idOrLikeUser_nameOrLikeMember_delivery_order_receiver_name(app_id, user_name, member_delivery_order_receiver_name);
    }

    public Integer countByOrApp_idOrLikeUser_nameOrLikeMember_delivery_order_receiver_name(String app_id, String user_name, String member_delivery_order_receiver_name) {
        return memberDeliveryOrderDao.countByOrApp_idOrLikeUser_nameOrLikeMember_delivery_order_receiver_name(app_id, user_name, member_delivery_order_receiver_name);
    }
    
    public Integer countWarehouse_deliverByApp_idOrLikeUser_nameOrLikeMember_delivery_order_receiver_name(String app_id, String user_name, String member_delivery_order_receiver_name) {
        return memberDeliveryOrderDao.countWarehouse_deliverByApp_idOrLikeUser_nameOrLikeMember_delivery_order_receiver_name(app_id, user_name, member_delivery_order_receiver_name);
    }
    
    public Integer countWarehouse_deliverByOrApp_idOrLikeUser_nameOrLikeMember_delivery_order_receiver_name(String app_id, String user_name, String member_delivery_order_receiver_name) {
        return memberDeliveryOrderDao.countWarehouse_deliverByOrApp_idOrLikeUser_nameOrLikeMember_delivery_order_receiver_name(app_id, user_name, member_delivery_order_receiver_name);
    }

    public List<MemberDeliveryOrder> listByApp_idAndSystem_create_timeAndLimit(String app_id, Date system_create_time, int m, int n) {
        List<MemberDeliveryOrder> member_delivery_orderList = memberDeliveryOrderDao.listByApp_idAndSystem_create_timeAndLimit(app_id, system_create_time, m, n);

        for (MemberDeliveryOrder member_delivery_order : member_delivery_orderList) {
            member_delivery_order.put(findByMember_delivery_order_id(member_delivery_order.getMember_delivery_order_id()));
        }

        return member_delivery_orderList;
    }

    public List<MemberDeliveryOrder> listByApp_idOrLikeUser_nameOrLikeMember_delivery_order_receiver_nameAndLimit(String app_id, String user_name, String member_delivery_order_receiver_name, int m, int n) {
        List<MemberDeliveryOrder> member_delivery_orderList = memberDeliveryOrderDao.listByApp_idOrLikeUser_nameOrLikeMember_delivery_order_receiver_nameAndLimit(app_id, user_name, member_delivery_order_receiver_name, m, n);

        for (MemberDeliveryOrder member_delivery_order : member_delivery_orderList) {
            member_delivery_order.put(findByMember_delivery_order_id(member_delivery_order.getMember_delivery_order_id()));
        }

        return member_delivery_orderList;
    }

    public List<MemberDeliveryOrder> listByOrApp_idOrLikeUser_nameOrLikeMember_delivery_order_receiver_nameAndLimit(String app_id, String user_name, String member_delivery_order_receiver_name, int m, int n) {
        List<MemberDeliveryOrder> member_delivery_orderList = memberDeliveryOrderDao.listByOrApp_idOrLikeUser_nameOrLikeMember_delivery_order_receiver_nameAndLimit(app_id, user_name, member_delivery_order_receiver_name, m, n);

        for (MemberDeliveryOrder member_delivery_order : member_delivery_orderList) {
            member_delivery_order.put(findByMember_delivery_order_id(member_delivery_order.getMember_delivery_order_id()));
        }

        return member_delivery_orderList;
    }
    
    public List<MemberDeliveryOrder> listWarehouse_deliverByApp_idOrLikeUser_nameOrLikeMember_delivery_order_receiver_nameAndLimit(String app_id, String user_name, String member_delivery_order_receiver_name, int m, int n) {
        List<MemberDeliveryOrder> member_delivery_orderList = memberDeliveryOrderDao.listWarehouse_deliverByApp_idOrLikeUser_nameOrLikeMember_delivery_order_receiver_nameAndLimit(app_id, user_name, member_delivery_order_receiver_name, m, n);
        
        for (MemberDeliveryOrder member_delivery_order : member_delivery_orderList) {
            member_delivery_order.put(findByMember_delivery_order_id(member_delivery_order.getMember_delivery_order_id()));
        }
        
        return member_delivery_orderList;
    }
    
    public List<MemberDeliveryOrder> listWarehouse_deliverByOrApp_idOrLikeUser_nameOrLikeMember_delivery_order_receiver_nameAndLimit(String app_id, String user_name, String member_delivery_order_receiver_name, int m, int n) {
        List<MemberDeliveryOrder> member_delivery_orderList = memberDeliveryOrderDao.listWarehouse_deliverByOrApp_idOrLikeUser_nameOrLikeMember_delivery_order_receiver_nameAndLimit(app_id, user_name, member_delivery_order_receiver_name, m, n);
        
        for (MemberDeliveryOrder member_delivery_order : member_delivery_orderList) {
            member_delivery_order.put(findByMember_delivery_order_id(member_delivery_order.getMember_delivery_order_id()));
        }
        
        return member_delivery_orderList;
    }
    
    public List<MemberDeliveryOrder> listByUser_id(String user_id) {
    	return memberDeliveryOrderDao.listByUser_id(user_id);
    }

    public MemberDeliveryOrder findByMember_delivery_order_id(String member_delivery_order_id) {
        MemberDeliveryOrder member_delivery_order = CacheUtil.get(MEMBER_DELIVERY_ORDER_BY_MEMBER_DELIVERY_ORDER_ID_CACHE, member_delivery_order_id);

        if (member_delivery_order == null) {
            member_delivery_order = memberDeliveryOrderDao.findByMember_delivery_order_id(member_delivery_order_id);

            CacheUtil.put(MEMBER_DELIVERY_ORDER_BY_MEMBER_DELIVERY_ORDER_ID_CACHE, member_delivery_order_id, member_delivery_order);
        }

        return member_delivery_order;
    }

    public Boolean save(String member_delivery_order_id, String app_id, String member_purchase_order_id, String user_id, String member_delivery_order_number, BigDecimal member_delivery_order_amount, Integer member_delivery_order_total_quantity, String member_delivery_order_receiver_name, String member_delivery_order_receiver_mobile, String member_delivery_order_receiver_province, String member_delivery_order_receiver_city, String member_delivery_order_receiver_area, String member_delivery_order_receiver_address, String member_delivery_order_express_pay_way, String member_delivery_order_express_shipper_code, Boolean member_delivery_order_is_pay, Boolean member_delivery_order_is_warehouse_deliver, String member_delivery_order_flow, Boolean member_delivery_order_is_complete, String system_create_user_id) {
        return memberDeliveryOrderDao.save(member_delivery_order_id, app_id, member_purchase_order_id, user_id, member_delivery_order_number, member_delivery_order_amount, member_delivery_order_total_quantity, member_delivery_order_receiver_name, member_delivery_order_receiver_mobile, member_delivery_order_receiver_province, member_delivery_order_receiver_city, member_delivery_order_receiver_area, member_delivery_order_receiver_address, member_delivery_order_express_pay_way, member_delivery_order_express_shipper_code, member_delivery_order_is_pay, member_delivery_order_is_warehouse_deliver, member_delivery_order_flow, member_delivery_order_is_complete, system_create_user_id);
    }

    public Boolean updateValidateSystem_version(String member_delivery_order_id, String member_purchase_order_id, BigDecimal member_delivery_order_amount, Integer member_delivery_order_total_quantity, String member_delivery_order_receiver_name, String member_delivery_order_receiver_mobile, String member_delivery_order_receiver_province, String member_delivery_order_receiver_city, String member_delivery_order_receiver_area, String member_delivery_order_receiver_address, String member_delivery_order_express_pay_way, String member_delivery_order_express_shipper_code, Boolean member_delivery_order_is_pay, Boolean member_delivery_order_is_warehouse_deliver, String member_delivery_order_flow, Boolean member_delivery_order_is_complete, String system_update_user_id, Integer system_version) {
        MemberDeliveryOrder member_delivery_order = findByMember_delivery_order_id(member_delivery_order_id);
        if (!member_delivery_order.getSystem_version().equals(system_version)) {
            throw new RuntimeException(Constant.ERROR_VERSION);
        }

        boolean result = memberDeliveryOrderDao.update(member_delivery_order_id, member_purchase_order_id, member_delivery_order_amount, member_delivery_order_total_quantity, member_delivery_order_receiver_name, member_delivery_order_receiver_mobile, member_delivery_order_receiver_province, member_delivery_order_receiver_city, member_delivery_order_receiver_area, member_delivery_order_receiver_address, member_delivery_order_express_pay_way, member_delivery_order_express_shipper_code, member_delivery_order_is_pay, member_delivery_order_is_warehouse_deliver, member_delivery_order_flow, member_delivery_order_is_complete, system_update_user_id, system_version);

        if (result) {
            CacheUtil.remove(MEMBER_DELIVERY_ORDER_BY_MEMBER_DELIVERY_ORDER_ID_CACHE, member_delivery_order_id);
        }

        return result;
    }

    public Boolean deleteByMember_delivery_order_idAndSystem_update_user_idValidateSystem_version(String member_delivery_order_id, String system_update_user_id, Integer system_version) {
        MemberDeliveryOrder member_delivery_order = findByMember_delivery_order_id(member_delivery_order_id);
        if (!member_delivery_order.getSystem_version().equals(system_version)) {
            throw new RuntimeException(Constant.ERROR_VERSION);
        }

        boolean result = memberDeliveryOrderDao.deleteByMember_delivery_order_idAndSystem_version(member_delivery_order_id, system_update_user_id, system_version);

        if (result) {
            CacheUtil.remove(MEMBER_DELIVERY_ORDER_BY_MEMBER_DELIVERY_ORDER_ID_CACHE, member_delivery_order_id);
        }

        return result;
    }
    
    public Boolean updateMember_delivery_order_flowAndMember_delivery_order_is_payByMember_delivery_order_idValidateSystem_version(String member_delivery_order_id, String member_delivery_order_flow, Boolean member_delivery_order_is_pay, String system_update_user_id, Integer system_version) {
        MemberDeliveryOrder member_delivery_order = findByMember_delivery_order_id(member_delivery_order_id);
        if (!member_delivery_order.getSystem_version().equals(system_version)) {
            throw new RuntimeException(Constant.ERROR_VERSION);
        }

        boolean result = memberDeliveryOrderDao.updateMember_delivery_order_flowAndMember_delivery_order_is_payByMember_delivery_order_idAndSystem_version(member_delivery_order_id, member_delivery_order_flow, member_delivery_order_is_pay, system_update_user_id, system_version);
       
        if (result) {
            CacheUtil.remove(MEMBER_DELIVERY_ORDER_BY_MEMBER_DELIVERY_ORDER_ID_CACHE, member_delivery_order_id);
        }

        return result;
    }
    
    public Boolean updateMember_delivery_order_flowAndMember_delivery_order_is_warehouse_deliverByMember_delivery_order_idValidateSystem_version(String member_delivery_order_id, String member_delivery_order_flow, Boolean member_delivery_order_is_warehouse_deliver, String system_update_user_id, Integer system_version) {
        MemberDeliveryOrder member_delivery_order = findByMember_delivery_order_id(member_delivery_order_id);
        if (!member_delivery_order.getSystem_version().equals(system_version)) {
            throw new RuntimeException(Constant.ERROR_VERSION);
        }

        boolean result = memberDeliveryOrderDao.updateMember_delivery_order_flowAndMember_delivery_order_is_warehouse_deliverByMember_delivery_order_idAndSystem_version(member_delivery_order_id, member_delivery_order_flow, member_delivery_order_is_warehouse_deliver, system_update_user_id, system_version);
        if (result) {
            CacheUtil.remove(MEMBER_DELIVERY_ORDER_BY_MEMBER_DELIVERY_ORDER_ID_CACHE, member_delivery_order_id);
        }

        return result;
    }
    
    public Boolean updateMember_delivery_order_flowByMember_delivery_order_idValidateSystem_version(String member_delivery_order_id, String member_delivery_order_flow, String system_update_user_id, Integer system_version) {
        MemberDeliveryOrder member_delivery_order = findByMember_delivery_order_id(member_delivery_order_id);
        if (!member_delivery_order.getSystem_version().equals(system_version)) {
            throw new RuntimeException(Constant.ERROR_VERSION);
        }

        boolean result = memberDeliveryOrderDao.updateMember_delivery_order_flowByMember_delivery_order_idAndSystem_version(member_delivery_order_id, member_delivery_order_flow, system_update_user_id, system_version);
        
        if (result) {
            CacheUtil.remove(MEMBER_DELIVERY_ORDER_BY_MEMBER_DELIVERY_ORDER_ID_CACHE, member_delivery_order_id);
        }

        return result;
    }
    
    public Boolean updateMember_delivery_order_flowAndMember_delivery_order_is_completeByMember_delivery_order_idValidateSystem_version(String member_delivery_order_id, String member_delivery_order_flow, Boolean member_delivery_order_is_complete, String system_update_user_id, Integer system_version) {
        MemberDeliveryOrder member_delivery_order = findByMember_delivery_order_id(member_delivery_order_id);
        if (!member_delivery_order.getSystem_version().equals(system_version)) {
            throw new RuntimeException(Constant.ERROR_VERSION);
        }

        boolean result = memberDeliveryOrderDao.updateMember_delivery_order_flowAndMember_delivery_order_is_completeByMember_delivery_order_idAndSystem_version(member_delivery_order_id, member_delivery_order_flow, member_delivery_order_is_complete, system_update_user_id, system_version);
        if (result) {
            CacheUtil.remove(MEMBER_DELIVERY_ORDER_BY_MEMBER_DELIVERY_ORDER_ID_CACHE, member_delivery_order_id);
        }

        return result;
    }
    
    /**
     * 生成发货单号
     * 
     * @return
     */
    public String generateMember_delivery_order_number() {
        String date = new SimpleDateFormat("yyyyMMdd").format(new Date());
        List<String> member_delivery_order_numberList = CacheUtil.get(MEMBER_DELIVERY_ORDER_NUMBER_LIST_CACHE, date);
        String member_delivery_order_number = new StringBuilder(date).append((new Random().nextInt(899999) + 10)).toString();
        if (member_delivery_order_numberList == null) {
            CacheUtil.removeAll(MEMBER_DELIVERY_ORDER_NUMBER_LIST_CACHE);
            member_delivery_order_numberList = new ArrayList<String>();
        } else {
            while (member_delivery_order_numberList.contains(member_delivery_order_number)) {
                member_delivery_order_number = new StringBuilder(date).append((new Random().nextInt(899999) + 10)).toString();
            }
        }
        member_delivery_order_numberList.add(member_delivery_order_number);
        CacheUtil.put(MEMBER_DELIVERY_ORDER_NUMBER_LIST_CACHE, date, member_delivery_order_numberList);
        return member_delivery_order_number;
    }
    
    public static void main(String[] args) {
        String date = new SimpleDateFormat("yyyyMMdd").format(new Date());
        int j = 20;
        for (int i = 0; i < j; i++) {
            System.out.println(new StringBuilder(date).append((new Random().nextInt(899999) + 10)).toString());
        }
        
    }

}