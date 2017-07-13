package com.nowui.chuangshi.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.constant.Url;
import com.nowui.chuangshi.model.Member;
import com.nowui.chuangshi.model.MemberLevel;
import com.nowui.chuangshi.model.ProductSku;
import com.nowui.chuangshi.model.Qrcode;
import com.nowui.chuangshi.model.Stock;
import com.nowui.chuangshi.model.User;
import com.nowui.chuangshi.service.FileService;
import com.nowui.chuangshi.service.MemberLevelService;
import com.nowui.chuangshi.service.MemberService;
import com.nowui.chuangshi.service.QrcodeService;
import com.nowui.chuangshi.service.StockService;
import com.nowui.chuangshi.service.UserService;
import com.nowui.chuangshi.type.StockAction;
import com.nowui.chuangshi.type.StockFlow;
import com.nowui.chuangshi.type.StockType;
import com.nowui.chuangshi.util.Util;
import com.nowui.chuangshi.util.ValidateUtil;

public class MemberController extends Controller {

    private final MemberService memberService = new MemberService();
    private final UserService userService = new UserService();
    private final StockService stockService = new StockService();
    private final MemberLevelService memberLevelService = new MemberLevelService();
    private final FileService fileService = new FileService();
    private final QrcodeService qrcodeService = new QrcodeService();

    private List<Map<String, Object>> getChildren(List<Member> memberList, String member_parent_id, String... keys) {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        for (Member member : memberList) {
            if (member.getMember_parent_id().equals(member_parent_id)) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put(Member.MEMBER_ID, member.getMember_id());
                map.put(User.USER_NAME, member.getStr(User.USER_NAME));
                map.put(User.USER_AVATAR, member.getStr(User.USER_AVATAR));
                map.put(MemberLevel.MEMBER_LEVEL_NAME, member.getStr(MemberLevel.MEMBER_LEVEL_NAME));

                for (String key : keys) {
                    map.put(key, member.get(key));
                }

                List<Map<String, Object>> childrenList = getChildren(memberList, member.getMember_id(), keys);
                if (childrenList.size() > 0) {
                    map.put(Constant.CHILDREN, childrenList);
                }
                list.add(map);
            }
        }
        return list;
    }

    @ActionKey(Url.MEMBER_LIST)
    public void list() {
        validateRequest_app_id();
        validate(Constant.PAGE_SIZE, Constant.FIRST_CREATE_TIME, Constant.LAST_CREATE_TIME);

        String request_app_id = getRequest_app_id();
        JSONObject jsonObject = getParameterJSONObject();

        authenticateRequest_app_idAndRequest_user_id();

        List<Member> resultList = memberService.listByApp_idAndSystem_create_timeAndLimit(request_app_id, jsonObject.getDate(Constant.LAST_CREATE_TIME), 0, getN());

        for (Member result : resultList) {
            result.keep(Member.MEMBER_ID, Member.SYSTEM_VERSION);
        }

        renderSuccessJson(resultList);
    }

    @ActionKey(Url.MEMBER_TEAM_LIST)
    public void teamList() {
        validateRequest_app_id();

        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        User user = userService.findByUser_id(request_user_id);

        authenticateApp_id(user.getApp_id());

        Member member = memberService.findByMember_id(user.getObject_Id());

        List<Member> memberList = memberService.listByMember_parent_pathLikeMember_parent_id(member.getMember_id());

        List<Map<String, Object>> resultList = getChildren(memberList, member.getMember_id());

        renderSuccessJson(resultList);
    }

    @ActionKey(Url.MEMBER_FIND)
    public void find() {
        validateRequest_app_id();
        validate(Member.MEMBER_ID);

        Member model = getModel(Member.class);

        authenticateRequest_app_idAndRequest_user_id();

        Member member = memberService.findByMember_id(model.getMember_id());

        authenticateApp_id(member.getApp_id());
        authenticateSystem_create_user_id(member.getSystem_create_user_id());

        member.keep(Member.MEMBER_ID, Member.SYSTEM_VERSION);

        renderSuccessJson(member);
    }

    @ActionKey(Url.MEMBER_MY_FIND)
    public void myFind() {
        validateRequest_app_id();

        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        User user = userService.findByUser_id(request_user_id);
        Member member = memberService.findByMember_id(user.getObject_Id());

        Map<String, Object> result = new HashMap<String, Object>();
        result.put(User.USER_NAME, user.getUser_name());
        result.put(User.USER_AVATAR, fileService.getFile_path(user.getUser_avatar()));

        result.put(Member.MEMBER_COMMISSION_AMOUNT, 0);
        result.put(Member.MEMBER_ORDER_AMOUNT, 0);
        result.put(Member.MEMBER_WAIT_PAY, 0);
        result.put(Member.MEMBER_WAIT_SEND, 0);
        result.put(Member.MEMBER_WAIT_RECEIVE, 0);
        result.put(Member.MEMBER_STATUS, member.getMember_status());

        if (ValidateUtil.isNullOrEmpty(member.getMember_level_id())) {
            result.put(MemberLevel.MEMBER_LEVEL_NAME, "");
        } else {
            MemberLevel memberLevel = memberLevelService.findByMember_level_id(member.getMember_level_id());
            result.put(MemberLevel.MEMBER_LEVEL_NAME, memberLevel.getMember_level_name());
        }

        authenticateApp_id(user.getApp_id());

        renderSuccessJson(result);
    }

    @ActionKey(Url.MEMBER_QRCODE_FIND)
    public void qrcodeFind() {
        validateRequest_app_id();

        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        Member member = memberService.findByUser_id(request_user_id);

        Qrcode qrcode = qrcodeService.findByQrcode_id(member.getQrcode_id());

        authenticateApp_id(member.getApp_id());

        renderSuccessJson(qrcode.getQrcode_url());
    }

    @ActionKey(Url.MEMBER_TEAM_MEMBER_LEVEL_LIST)
    public void teamMemberLevel() {
        validateRequest_app_id();
        validate(Member.MEMBER_ID);

        Member model = getModel(Member.class);

        authenticateRequest_app_idAndRequest_user_id();

        Member member = memberService.findByMember_id(model.getMember_id());

        Member parentMember = memberService.findByMember_id(member.getMember_parent_id());
        MemberLevel parentMemberLevel = memberLevelService.findByMember_level_id(parentMember.getMember_level_id());

        List<MemberLevel> resultList = new ArrayList<MemberLevel>();
        List<MemberLevel> memberLevelList = memberLevelService.listByApp_id(member.getApp_id());
        for(MemberLevel memberLevel : memberLevelList) {
            if (memberLevel.getMember_level_value() > parentMemberLevel.getMember_level_value()) {
                if (memberLevel.getMember_level_id().equals(member.getMember_level_id())) {
                    memberLevel.put(Constant.IS_SELECT, true);
                } else {
                    memberLevel.put(Constant.IS_SELECT, false);
                }

                resultList.add(memberLevel);
            }
        }

        authenticateApp_id(member.getApp_id());

        renderSuccessJson(resultList);
    }

    @ActionKey(Url.MEMBER_TEAM_FIND)
    public void teamFind() {
        validateRequest_app_id();
        validate(Member.MEMBER_ID);

        Member model = getModel(Member.class);

        authenticateRequest_app_idAndRequest_user_id();

        Member member = memberService.findByMember_id(model.getMember_id());

        authenticateApp_id(member.getApp_id());

        User user = userService.findByUser_id(member.getUser_id());
        member.put(User.USER_NAME, user.getUser_name());
        member.put(User.USER_AVATAR, fileService.getFile_path(user.getUser_avatar()));

        String member_level_name = "";
        if (!ValidateUtil.isNullOrEmpty(member.getMember_level_id())) {
            MemberLevel memberLevel = memberLevelService.findByMember_level_id(member.getMember_level_id());

            member_level_name =  memberLevel.getMember_level_name();
        }
        member.put(MemberLevel.MEMBER_LEVEL_NAME, member_level_name);

        member.keep(Member.MEMBER_ID, User.USER_NAME, User.USER_AVATAR, MemberLevel.MEMBER_LEVEL_NAME, Member.MEMBER_STATUS);

        renderSuccessJson(member);
    }

    @ActionKey(Url.MEMBER_TEAM_MEMBER_LEVEL_UPDATE)
    public void teamMemberLevelUpdate() {
        validateRequest_app_id();
        validate(Member.MEMBER_ID);

        Member model = getModel(Member.class);
        String request_user_id = getRequest_user_id();

        authenticateRequest_app_idAndRequest_user_id();

        Member member = memberService.findByMember_id(model.getMember_id());
        User parentUser = userService.findByUser_id(request_user_id);
        Member parentMember = memberService.findByMember_id(parentUser.getObject_Id());

        if (member.getMember_parent_id().equals(parentMember.getMember_id())) {
            memberService.updateByMember_idAndMember_level_id(model.getMember_id(), model.getMember_level_id(), request_user_id);
        } else {
            throw new RuntimeException("您不是上一级");
        }

        renderSuccessJson();
    }
    
    @ActionKey(Url.MEMBER_SEND)
    public void send() {
        validateRequest_app_id();
        validate(Member.MEMBER_ID, ProductSku.PRODUCT_SKU_ID, Stock.STOCK_QUANTITY, Stock.STOCK_RECEIVER_NAME, Stock.STOCK_RECEIVER_ADDRESS, Stock.STOCK_RECEIVER_AREA, Stock.STOCK_RECEIVER_CITY, Stock.STOCK_RECEIVER_MOBILE);
        
        String request_app_id = getRequest_app_id();
        String request_user_id = getRequest_user_id();
        Stock stock = getModel(Stock.class);
        JSONObject jsonObject = getParameterJSONObject();
        String member_id = jsonObject.getString("member_id");
        String product_sku_id = jsonObject.getString("product_sku_id");
        Integer stock_quantity = jsonObject.getInteger("stock_quantity");
        //判断会员库存数量是否足够
        Integer member_product_sku_stock_quantity = stockService.sumStock_quantityByObject_idAndProduct_sku_id(member_id, product_sku_id);
        if (stock_quantity > member_product_sku_stock_quantity) {
        	throw new RuntimeException("会员库存不足");
        }
        authenticateRequest_app_idAndRequest_user_id();
        
        authenticateApp_id(request_app_id);
        
        String stock_id = Util.getRandomUUID();
        Member member = memberService.findByMember_id(member_id);
        Boolean result = stockService.save(stock_id, member.getApp_id(), product_sku_id, member_id, StockType.MEMBER.getKey(), stock_quantity, stock.getStock_receiver_name(), stock.getStock_receiver_mobile(), stock.getStock_receiver_province(), stock.getStock_receiver_city(), stock.getStock_receiver_area(), stock.getStock_receiver_address(), StockAction.OUT.getKey(), StockFlow.WAIT_SEND.getKey(), false, null, request_user_id);
        
        renderSuccessJson(result);
    }
    
    @ActionKey(Url.MEMBER_SEND_DETAIL)
    public void sendDetail() {
        validateRequest_app_id();
        validate(Member.MEMBER_ID);
        
        String request_app_id = getRequest_app_id();
        JSONObject jsonObject = getParameterJSONObject();
        String member_id = jsonObject.getString("member_id");
        
        authenticateRequest_app_idAndRequest_user_id();
        
        authenticateApp_id(request_app_id);
        
        
        
        Map<String, Object> result = new HashMap<String, Object>();
        renderSuccessJson(result);
    }


    @ActionKey(Url.MEMBER_ADMIN_LIST)
    public void adminList() {
        validateRequest_app_id();
        validate(User.USER_NAME, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        User model = getModel(User.class);
        String request_app_id = getRequest_app_id();

        authenticateRequest_app_idAndRequest_user_id();

        Integer total = memberService.countByApp_idOrLikeUser_name(request_app_id, model.getUser_name());
        List<Member> resultList = memberService.listByApp_idOrLikeUser_nameAndLimit(request_app_id, model.getUser_name(), getM(), getN());

        for (Member result : resultList) {
            result.keep(Member.MEMBER_ID, User.USER_NAME, Member.SYSTEM_VERSION);
        }

        renderSuccessJson(total, resultList);
    }
    
    @ActionKey(Url.MEMBER_ADMIN_FIND)
    public void adminFind() {
        validateRequest_app_id();
        validate(Member.MEMBER_ID);

        Member model = getModel(Member.class);

        authenticateRequest_app_idAndRequest_user_id();

        Member member = memberService.findByMember_id(model.getMember_id());

        authenticateApp_id(member.getApp_id());

        member.keep(Member.MEMBER_ID, Member.SYSTEM_VERSION);

        renderSuccessJson(member);
    }

    //会员发货，由公司仓库发货，减去会员库存，快递到会员指定地址
    @ActionKey(Url.MEMBER_ADMIN_SEND)
    public void adminSend() {
        validateRequest_app_id();
        validate(Member.MEMBER_ID, ProductSku.PRODUCT_SKU_ID, Stock.STOCK_QUANTITY, Stock.STOCK_RECEIVER_NAME, Stock.STOCK_RECEIVER_ADDRESS, Stock.STOCK_RECEIVER_AREA, Stock.STOCK_RECEIVER_CITY, Stock.STOCK_RECEIVER_MOBILE);
        
        String request_app_id = getRequest_app_id();
        String request_user_id = getRequest_user_id();
        Stock stock = getModel(Stock.class);
        JSONObject jsonObject = getParameterJSONObject();
        String member_id = jsonObject.getString("member_id");
        String product_sku_id = jsonObject.getString("product_sku_id");
        Integer stock_quantity = jsonObject.getInteger("stock_quantity");
        //判断会员库存数量是否足够
        Integer member_product_sku_stock_quantity = stockService.sumStock_quantityByObject_idAndProduct_sku_id(member_id, product_sku_id);
        if (stock_quantity > member_product_sku_stock_quantity) {
        	throw new RuntimeException("会员库存不足");
        }
        authenticateRequest_app_idAndRequest_user_id();
        
        authenticateApp_id(request_app_id);
        
        String stock_id = Util.getRandomUUID();
        Member member = memberService.findByMember_id(member_id);
        Boolean result = stockService.save(stock_id, member.getApp_id(), product_sku_id, member_id, StockType.MEMBER.getKey(), stock_quantity, stock.getStock_receiver_name(), stock.getStock_receiver_mobile(), stock.getStock_receiver_province(), stock.getStock_receiver_city(), stock.getStock_receiver_area(), stock.getStock_receiver_address(), StockAction.OUT.getKey(), StockFlow.WAIT_SEND.getKey(), false, null, request_user_id);
        
        renderSuccessJson(result);
    }
    
    @ActionKey(Url.MEMBER_SYSTEM_LIST)
    public void systemList() {
        validateRequest_app_id();
        validate(Member.APP_ID, User.USER_NAME, Constant.PAGE_INDEX, Constant.PAGE_SIZE);

        User model = getModel(User.class);

        Integer total = memberService.countByOrApp_idOrLikeUser_name(model.getApp_id(), model.getUser_name());
        List<Member> resultList = memberService.listByOrApp_idOrLikeUser_nameAndLimit(model.getApp_id(), model.getUser_name(), getM(), getN());

        for (Member result : resultList) {
            result.keep(Member.MEMBER_ID, Member.SYSTEM_VERSION);
        }

        renderSuccessJson(total, resultList);
    }
    
    @ActionKey(Url.MEMBER_SYSTEM_ALL_LIST)
    public void systemAllList() {
    	validateRequest_app_id();
    	
    	JSONObject jsonObject = getParameterJSONObject();
    	String app_id = jsonObject.getString("app_id");
    	
    	List<Member> resultList = memberService.listByOrApp_id(app_id);
    	
    	for (Member result : resultList) {
    	    result.keep(Member.MEMBER_ID, User.USER_NAME, Member.SYSTEM_VERSION);
    	}
    	
    	renderSuccessJson(resultList);
    }

    @ActionKey(Url.MEMBER_SYSTEM_FIND)
    public void systemFind() {
        validateRequest_app_id();
        validate(Member.MEMBER_ID);

        Member model = getModel(Member.class);

        Member member = memberService.findByMember_id(model.getMember_id());

        member.keep(Member.MEMBER_ID, Member.SYSTEM_VERSION);

        renderSuccessJson(member);
    }

}