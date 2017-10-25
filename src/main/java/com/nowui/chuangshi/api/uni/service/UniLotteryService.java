package com.nowui.chuangshi.api.uni.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.nowui.chuangshi.api.uni.dao.UniLotteryDao;
import com.nowui.chuangshi.api.uni.model.UniLottery;
import com.nowui.chuangshi.common.service.Service;
import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.util.CacheUtil;
import com.nowui.chuangshi.util.DateUtil;
import com.nowui.chuangshi.util.ProbabilityUtil;
import com.nowui.chuangshi.util.ValidateUtil;

public class UniLotteryService extends Service {

    public static final UniLotteryService instance = new UniLotteryService();
    private final String UNI_LOTTERY_ITEM_CACHE = "uni_lottery_item_cache";
    private final String UNI_LOTTERY_MAN_UNUSED_NUMBER_LIST_CACHE = "uni_lottery_man_unused_number_list_cache";
    private final String UNI_LOTTERY_WOMEN_UNUSED_NUMBER_LIST_CACHE = "uni_lottery_women_unused_number_list_cache";
    private final UniLotteryDao uniLotteryDao = new UniLotteryDao();

    public Integer adminCount(String app_id, String lottery_user_mobile, String lottery_number) {
        Cnd cnd = new Cnd();
        cnd.where(UniLottery.SYSTEM_STATUS, true);
        cnd.and(UniLottery.APP_ID, app_id);
        cnd.andAllowEmpty(UniLottery.LOTTERY_NUMBER, lottery_number);
        cnd.andAllowEmpty(UniLottery.LOTTERY_USER_MOBILE, lottery_user_mobile);

        Integer count = uniLotteryDao.count(cnd);
        return count;
    }

    public List<UniLottery> adminList(String app_id, String lottery_user_mobile, String lottery_number, Integer m, Integer n) {
        Cnd cnd = new Cnd();
        cnd.where(UniLottery.SYSTEM_STATUS, true);
        cnd.and(UniLottery.APP_ID, app_id);
        cnd.andAllowEmpty(UniLottery.LOTTERY_NUMBER, lottery_number);
        cnd.andAllowEmpty(UniLottery.LOTTERY_USER_MOBILE, lottery_user_mobile);
        cnd.desc(UniLottery.SYSTEM_UPDATE_TIME);
        cnd.paginate(m, n);

        List<UniLottery> uni_lotteryList = uniLotteryDao.primaryKeyList(cnd);
        for (UniLottery uni_lottery : uni_lotteryList) {
            uni_lottery.put(find(uni_lottery.getUser_id()));
        }
        return uni_lotteryList;
    }

    public List<String> manUnusedNumberList(String app_id) {
        List<String> numberList = CacheUtil.get(UNI_LOTTERY_MAN_UNUSED_NUMBER_LIST_CACHE, app_id);

        if (numberList == null) {
            Cnd cnd = new Cnd();
            cnd.select(UniLottery.LOTTERY_NUMBER);
            cnd.where(UniLottery.SYSTEM_STATUS, true);
            cnd.and(UniLottery.APP_ID, app_id);
            cnd.andNot(UniLottery.LOTTERY_NUMBER, "");
            cnd.and(UniLottery.LOTTERY_USER_SEX, true);

            List<UniLottery> uni_lotteryList = uniLotteryDao.primaryKeyList(cnd);

            List<String> userdNumberList = uni_lotteryList.stream().map(uniLottery -> uniLottery.getLottery_number()).collect(Collectors.toList());
            numberList = new ArrayList<String>();
            for (int i = 1; i <= 1000; i++) {
                if (!userdNumberList.contains(String.valueOf(i))) {
                    numberList.add(String.valueOf(i));
                }
            }

            CacheUtil.put(UNI_LOTTERY_MAN_UNUSED_NUMBER_LIST_CACHE, app_id, numberList);
        }
        return numberList;
    }

    public List<String> womanUnusedNumberList(String app_id) {
        List<String> numberList = CacheUtil.get(UNI_LOTTERY_WOMEN_UNUSED_NUMBER_LIST_CACHE, app_id);

        if (numberList == null) {
            Cnd cnd = new Cnd();
            cnd.select(UniLottery.LOTTERY_NUMBER);
            cnd.where(UniLottery.SYSTEM_STATUS, true);
            cnd.and(UniLottery.APP_ID, app_id);
            cnd.andNot(UniLottery.LOTTERY_NUMBER, "");
            cnd.and(UniLottery.LOTTERY_USER_SEX, false);

            List<UniLottery> uni_lotteryList = uniLotteryDao.primaryKeyList(cnd);

            List<String> userdNumberList = uni_lotteryList.stream().map(uniLottery -> uniLottery.getLottery_number()).collect(Collectors.toList());
            numberList = new ArrayList<String>();
            for (int i = 1; i <= 1000; i++) {
                if (!userdNumberList.contains(String.valueOf(i))) {
                    numberList.add(String.valueOf(i));
                }
            }

            CacheUtil.put(UNI_LOTTERY_WOMEN_UNUSED_NUMBER_LIST_CACHE, app_id, numberList);
        }
        return numberList;
    }

    public List<String> numberList(String app_id, Boolean lottery_user_sex) {
        if (lottery_user_sex) {
            return manUnusedNumberList(app_id);
        } else {
            return womanUnusedNumberList(app_id);
        }
    }

    public UniLottery find(String user_id) {
        UniLottery uni_lottery = CacheUtil.get(UNI_LOTTERY_ITEM_CACHE, user_id);

        if (uni_lottery == null) {
            uni_lottery = uniLotteryDao.find(user_id);

            CacheUtil.put(UNI_LOTTERY_ITEM_CACHE, user_id, uni_lottery);
        }

        return uni_lottery;
    }

    public UniLottery mobileFind(String lottery_user_mobile) {
        Cnd cnd = new Cnd();
        cnd.where(UniLottery.SYSTEM_STATUS, true);
        cnd.andAllowEmpty(UniLottery.LOTTERY_USER_MOBILE, lottery_user_mobile);

        List<UniLottery> uni_lotteryList = uniLotteryDao.primaryKeyList(cnd);

        if (uni_lotteryList == null || uni_lotteryList.size() == 0) {
            return null;
        }

        return find(uni_lotteryList.get(0).getUser_id());
    }

    public Boolean save(UniLottery uni_lottery, String system_create_user_id) {
        Boolean success = uniLotteryDao.save(uni_lottery, system_create_user_id);
        return success;
    }

    public Boolean update(UniLottery uni_lottery, String user_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(UniLottery.SYSTEM_STATUS, true);
        cnd.and(UniLottery.USER_ID, user_id);
        cnd.and(UniLottery.SYSTEM_VERSION, system_version);

        Boolean success = uniLotteryDao.update(uni_lottery, system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(UNI_LOTTERY_ITEM_CACHE, user_id);
        }

        return success;
    }

    public Boolean delete(String user_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(UniLottery.SYSTEM_STATUS, true);
        cnd.and(UniLottery.USER_ID, user_id);
        cnd.and(UniLottery.SYSTEM_VERSION, system_version);

        Boolean success = uniLotteryDao.delete(system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(UNI_LOTTERY_ITEM_CACHE, user_id);
        }

        return success;
    }

    /**
     * 抽签
     * @param request_user_id
     * @return
     */
    public String draw(String request_app_id, String request_user_id) {
        UniLottery bean = find(request_user_id);
        if (bean == null) {
            throw new RuntimeException("未获得抽签资格");
        }
        if (!ValidateUtil.isNullOrEmpty(bean.getLottery_number())) {
            return bean.getLottery_number();
        }
        if (bean.getLottery_status() && bean.getLottery_time() > 3) {
            throw new RuntimeException("已抽签三次，抽签次数已经用完");
        }
        synchronized (this) {
            List<String> numberList = numberList(request_app_id, bean.getLottery_user_sex());
            //计算抽签概率
            if (numberList.size() == 0) {
                throw new RuntimeException("号码已被抽完了");
            }

            if (("21").equals(DateUtil.getDay()) || "22".equals(DateUtil.getDay()) || ("23").equals(DateUtil.getDay()) || "24".equals(DateUtil.getDay())) {//21-24号抽奖概率80%
                if (ProbabilityUtil.random(0.8)) {
                    return getLottery_number(numberList, bean, request_user_id, request_app_id);
                }
            } else if (("25").equals(DateUtil.getDay()) || "26".equals(DateUtil.getDay()) || "27".equals(DateUtil.getDay()) || "28".equals(DateUtil.getDay())) { //25-28号抽奖概率50%
                if (ProbabilityUtil.random(0.5)) {
                    return getLottery_number(numberList, bean, request_user_id, request_app_id);
                }
            } else if (("29").equals(DateUtil.getDay()) || "30".equals(DateUtil.getDay()) || "31".equals(DateUtil.getDay())) { //29、30、31号抽奖概率100%
                return getLottery_number(numberList, bean, request_user_id, request_app_id);
            }
        }
        System.out.println(bean.getLottery_user_mobile() + "未抽中号码");
        //未抽中，则更新用户抽签次数，抽签次数加一
        bean.setLottery_time(bean.getLottery_time() + 1);
        bean.setLottery_status(true);
        this.update(bean, request_user_id, request_user_id, bean.getSystem_version());
        return null;
    }

    public String getLottery_number(List<String> numberList, UniLottery bean, String request_user_id, String request_app_id) {
        String lottery_number = numberList.get(0); //随机获取一个未抽取的号码
        bean.setLottery_number(lottery_number);
        bean.setLottery_time(bean.getLottery_time() + 1);
        bean.setLottery_status(true);
        boolean result = this.update(bean, request_user_id, request_user_id, bean.getSystem_version());
        if (result) {
            String remove_number = numberList.remove(0);
            System.out.println(bean.getLottery_user_mobile() + "抽中号码：" + remove_number + "剩余号码" + numberList.toString());
            if (bean.getLottery_user_sex()) {
                CacheUtil.put(UNI_LOTTERY_MAN_UNUSED_NUMBER_LIST_CACHE, request_app_id, numberList);
            } else {
                CacheUtil.put(UNI_LOTTERY_WOMEN_UNUSED_NUMBER_LIST_CACHE, request_app_id, numberList);
            }
            return lottery_number;
        }
        return null;
    }

}