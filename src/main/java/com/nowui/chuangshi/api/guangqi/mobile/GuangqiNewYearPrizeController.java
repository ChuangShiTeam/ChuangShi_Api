package com.nowui.chuangshi.api.guangqi.mobile;

import java.util.Date;
import java.util.List;
import java.util.Random;

import com.jfinal.core.ActionKey;
import com.nowui.chuangshi.api.guangqi.model.GuangqiNewYearCustomer;
import com.nowui.chuangshi.api.guangqi.model.GuangqiNewYearCustomerPrize;
import com.nowui.chuangshi.api.guangqi.model.GuangqiNewYearPrize;
import com.nowui.chuangshi.api.guangqi.service.GuangqiNewYearCustomerPrizeService;
import com.nowui.chuangshi.api.guangqi.service.GuangqiNewYearCustomerService;
import com.nowui.chuangshi.api.guangqi.service.GuangqiNewYearPrizeService;
import com.nowui.chuangshi.common.annotation.ControllerKey;
import com.nowui.chuangshi.common.controller.Controller;
import com.nowui.chuangshi.util.DateUtil;
import com.nowui.chuangshi.util.Util;

@ControllerKey("/mobile/guangqi/new/year/prize")
public class GuangqiNewYearPrizeController extends Controller {

    @ActionKey("/mobile/guangqi/new/year/prize/list")
    public void list() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/guangqi/new/year/prize/find")
    public void find() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/guangqi/new/year/prize/save")
    public void save() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/guangqi/new/year/prize/update")
    public void update() {

        renderSuccessJson();
    }

    @ActionKey("/mobile/guangqi/new/year/prize/delete")
    public void delete() {

        renderSuccessJson();
    }
    
    /**
     * 抽奖
     */
    @ActionKey("/mobile/guangqi/new/year/prize/draw")
    public void draw() {
    	validateRequest(GuangqiNewYearCustomer.NEW_YEAR_CUSTOMER_ID);
    	
    	GuangqiNewYearCustomer model = getModel(GuangqiNewYearCustomer.class);
    	String request_app_id = getRequest_app_id();
    	
    	
    	GuangqiNewYearCustomer guangqiNewYearCustomer = GuangqiNewYearCustomerService.instance.find(model.getNew_year_customer_id());
    	
    	if (guangqiNewYearCustomer == null) {
    		throw new RuntimeException("没有权限抽奖");
    	}
    	
    	Integer customerCount = GuangqiNewYearCustomerPrizeService.instance.appAndCustomerCount(request_app_id, model.getNew_year_customer_id());
    	
    	if (customerCount > 0) {
            throw new RuntimeException("重复抽奖啦");
        }
    	
    	Integer total = 0;
        GuangqiNewYearPrize defaultPrize = null;
        List<GuangqiNewYearPrize> guangqiNewYearPrizeList = GuangqiNewYearPrizeService.instance.appList(request_app_id);
        for (GuangqiNewYearPrize guangqiNewYearPrize : guangqiNewYearPrizeList) {
            if (guangqiNewYearPrize.getNew_year_prize_is_default()) {
                defaultPrize = guangqiNewYearPrize;
            } else {
                total += guangqiNewYearPrize.getNew_year_prize_probability();
            }
        }

        Random random = new Random();
        int number = random.nextInt(total) + 1;
        int start = 0;
        int end = 0;
        GuangqiNewYearPrize prize = null;
        for (GuangqiNewYearPrize guangqiNewYearPrize : guangqiNewYearPrizeList) {
            if (!guangqiNewYearPrize.getNew_year_prize_is_default()) {
                end += guangqiNewYearPrize.getNew_year_prize_probability();

                if (number > start && number <= end) {
                    prize = guangqiNewYearPrize;

                    break;
                }

                start += guangqiNewYearPrize.getNew_year_prize_probability();
            }
        }

        Boolean isSave = true;
        Integer totalCount = GuangqiNewYearCustomerPrizeService.instance.appAndPrizeCount(request_app_id, prize.getNew_year_prize_id());
        if (totalCount >= prize.getNew_year_prize_quantity()) {
            isSave = false;
        }



        Integer dayCount = 0;
        if (isSave) {
            dayCount = GuangqiNewYearCustomerPrizeService.instance.appAndPrizeAndDateCount(request_app_id, prize.getNew_year_prize_id(), DateUtil.getDateString(new Date()));
            if (dayCount >= prize.getNew_year_prize_limit()) {
                isSave = false;
            }
        }

        if (!isSave) {
            prize = defaultPrize;
        }

        GuangqiNewYearCustomerPrize guangqi_new_year_customer_prize = new GuangqiNewYearCustomerPrize();
        guangqi_new_year_customer_prize.setNew_year_customer_prize_id(Util.getRandomUUID());
        guangqi_new_year_customer_prize.setNew_year_prize_id(prize.getNew_year_prize_id());
        guangqi_new_year_customer_prize.setNew_year_customer_id(guangqiNewYearCustomer.getNew_year_customer_id());
        guangqi_new_year_customer_prize.setNew_year_customer_prize_date(DateUtil.getDateString(new Date()));
        String system_create_user_id = "";
        Boolean result = GuangqiNewYearCustomerPrizeService.instance.save(guangqi_new_year_customer_prize, system_create_user_id);

        if (!result) {
            throw new RuntimeException("抽奖不成功");
        }

        validateResponse(GuangqiNewYearPrize.NEW_YEAR_PRIZE_ID, GuangqiNewYearPrize.NEW_YEAR_PRIZE_NAME, GuangqiNewYearPrize.NEW_YEAR_PRIZE_UNIT_PRICE, GuangqiNewYearPrize.NEW_YEAR_PRIZE_IS_DEFAULT);
        
        renderSuccessJson(prize);
    }

}