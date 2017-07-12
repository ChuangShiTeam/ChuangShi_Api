package com.nowui.chuangshi.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.nowui.chuangshi.service.*;
import com.nowui.chuangshi.util.ValidateUtil;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.constant.Url;
import com.nowui.chuangshi.model.Express;
import com.nowui.chuangshi.model.Member;
import com.nowui.chuangshi.model.MemberAddress;
import com.nowui.chuangshi.model.MemberLevel;
import com.nowui.chuangshi.model.ProductSku;
import com.nowui.chuangshi.model.Stock;
import com.nowui.chuangshi.model.User;
import com.nowui.chuangshi.type.ExpressStatus;
import com.nowui.chuangshi.type.StockAction;
import com.nowui.chuangshi.type.StockType;
import com.nowui.chuangshi.util.Util;

public class MemberController extends Controller {

    private final MemberService memberService = new MemberService();
    private final UserService userService = new UserService();
    private final ExpressService expressService = new ExpressService();
    private final StockService stockService = new StockService();
    private final MemberAddressService memberAddressService = new MemberAddressService();
    private final MemberLevelService memberLevelService = new MemberLevelService();
    private final FileService fileService = new FileService();

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
        validate(Member.MEMBER_ID, ProductSku.PRODUCT_SKU_ID, Stock.STOCK_QUANTITY, Express.EXPRESS_RECEIVER_COMPANY, Express.EXPRESS_RECEIVER_NAME, Express.EXPRESS_RECEIVER_ADDRESS, Express.EXPRESS_RECEIVER_AREA, Express.EXPRESS_RECEIVER_CITY, Express.EXPRESS_RECEIVER_MOBILE, Express.EXPRESS_RECEIVER_POSTCODE, Express.EXPRESS_RECEIVER_TEL);
        
        String request_app_id = getRequest_app_id();
        String request_user_id = getRequest_user_id();
        Express express = getModel(Express.class);
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
        //查询会员默认地址
        MemberAddress memberAddress = memberAddressService.findByMember_id(member_id);
        /*if (memberAddress == null || StringUtils.isBlank(memberAddress.getMember_address_id())) {
        	throw new RuntimeException("会员地址信息需要完善");
        }*/
        Boolean result = stockService.save(stock_id, member.getApp_id(), product_sku_id, member_id, StockType.MEMBER.getValue(), stock_quantity, StockAction.OUT.getValue(), null, request_user_id);
        if (result) {
            //保存快递单信息
            expressService.save(Util.getRandomUUID(), member.getApp_id(), "", stock_id, "", member.getUser_id(), "", "", "", express.getExpress_receiver_company(), express.getExpress_receiver_name(), express.getExpress_receiver_tel(), express.getExpress_receiver_mobile(), express.getExpress_receiver_postcode(), express.getExpress_receiver_province(), express.getExpress_receiver_city(), express.getExpress_receiver_area(), express.getExpress_receiver_address(), "", memberAddress.getMember_address_name(), memberAddress.getMember_address_tel(), memberAddress.getMember_address_mobile(), memberAddress.getMember_address_postcode(), memberAddress.getMember_address_province(), memberAddress.getMember_address_city(), memberAddress.getMember_address_area(), memberAddress.getMember_address_address(), new BigDecimal(0), false, "", null, null, "", ExpressStatus.NOTRACK.getValue(), "", request_user_id);
        }
        
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