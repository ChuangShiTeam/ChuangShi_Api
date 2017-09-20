package com.nowui.chuangshi.api.jiangling.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import com.nowui.chuangshi.api.jiangling.dao.JianglingLotteryDao;
import com.nowui.chuangshi.api.jiangling.model.JianglingLottery;
import com.nowui.chuangshi.common.service.Service;
import com.nowui.chuangshi.common.sql.Cnd;
import com.nowui.chuangshi.util.CacheUtil;
import com.nowui.chuangshi.util.ProbabilityUtil;
import com.nowui.chuangshi.util.ValidateUtil;

public class JianglingLotteryService extends Service {

    public static final JianglingLotteryService instance = new JianglingLotteryService();
    private final String JIANGLING_LOTTERY_ITEM_CACHE = "jiangling_lottery_item_cache";
    private final String JIANGLING_LOTTERY_MAN_UNUSED_NUMBER_LIST_CACHE = "jiangling_lottery_man_unused_number_list_cache";
    private final String JIANGLING_LOTTERY_WOMEN_UNUSED_NUMBER_LIST_CACHE = "jiangling_lottery_women_unused_number_list_cache";
    private final JianglingLotteryDao jianglingLotteryDao = new JianglingLotteryDao();

    public Integer adminCount(String app_id, String lottery_number) {
        Cnd cnd = new Cnd();
        cnd.where(JianglingLottery.SYSTEM_STATUS, true);
        cnd.and(JianglingLottery.APP_ID, app_id);
        cnd.andAllowEmpty(JianglingLottery.LOTTERY_NUMBER, lottery_number);

        Integer count = jianglingLotteryDao.count(cnd);
        return count;
    }

    public List<JianglingLottery> adminList(String app_id, String lottery_number, Integer m, Integer n) {
        Cnd cnd = new Cnd();
        cnd.where(JianglingLottery.SYSTEM_STATUS, true);
        cnd.and(JianglingLottery.APP_ID, app_id);
        cnd.andAllowEmpty(JianglingLottery.LOTTERY_NUMBER, lottery_number);
        cnd.paginate(m, n);

        List<JianglingLottery> jiangling_lotteryList = jianglingLotteryDao.primaryKeyList(cnd);
        for (JianglingLottery jiangling_lottery : jiangling_lotteryList) {
            jiangling_lottery.put(find(jiangling_lottery.getUser_id()));
        }
        return jiangling_lotteryList;
    }
    
    public List<String> manUnusedNumberList(String app_id) {
        List<String> numberList = CacheUtil.get(JIANGLING_LOTTERY_MAN_UNUSED_NUMBER_LIST_CACHE, app_id);
        
        if (numberList == null) {
            Cnd cnd = new Cnd();
            cnd.select(JianglingLottery.LOTTERY_NUMBER);
            cnd.where(JianglingLottery.SYSTEM_STATUS, true);
            cnd.and(JianglingLottery.APP_ID, app_id);
            cnd.andNot(JianglingLottery.LOTTERY_NUMBER, "");
            cnd.and(JianglingLottery.LOTTERY_USER_SEX, true);
            
            List<JianglingLottery> jiangling_lotteryList = jianglingLotteryDao.primaryKeyList(cnd);
            
            List<String> userdNumberList = jiangling_lotteryList.stream().map(jianglingLottery -> jianglingLottery.getLottery_number()).collect(Collectors.toList());
            numberList = new ArrayList<String>();
            for (int i = 1; i <= 1000; i++) {
                if (!userdNumberList.contains(String.valueOf(i))) {
                    numberList.add(String.valueOf(i));
                }
            }
            
            CacheUtil.put(JIANGLING_LOTTERY_MAN_UNUSED_NUMBER_LIST_CACHE, app_id, numberList);
        }
        return numberList;
    }
    
    public List<String> womanUnusedNumberList(String app_id) {
        List<String> numberList = CacheUtil.get(JIANGLING_LOTTERY_WOMEN_UNUSED_NUMBER_LIST_CACHE, app_id);
        
        if (numberList == null) {
            Cnd cnd = new Cnd();
            cnd.select(JianglingLottery.LOTTERY_NUMBER);
            cnd.where(JianglingLottery.SYSTEM_STATUS, true);
            cnd.and(JianglingLottery.APP_ID, app_id);
            cnd.andNot(JianglingLottery.LOTTERY_NUMBER, "");
            cnd.and(JianglingLottery.LOTTERY_USER_SEX, false);
            
            List<JianglingLottery> jiangling_lotteryList = jianglingLotteryDao.primaryKeyList(cnd);
            
            List<String> userdNumberList = jiangling_lotteryList.stream().map(jianglingLottery -> jianglingLottery.getLottery_number()).collect(Collectors.toList());
            numberList = new ArrayList<String>();
            for (int i = 1; i <= 1000; i++) {
                if (!userdNumberList.contains(String.valueOf(i))) {
                    numberList.add(String.valueOf(i));
                }
            }
            
            CacheUtil.put(JIANGLING_LOTTERY_WOMEN_UNUSED_NUMBER_LIST_CACHE, app_id, numberList);
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

    public JianglingLottery find(String user_id) {
        JianglingLottery jiangling_lottery = CacheUtil.get(JIANGLING_LOTTERY_ITEM_CACHE, user_id);

        if (jiangling_lottery == null) {
            jiangling_lottery = jianglingLotteryDao.find(user_id);

            CacheUtil.put(JIANGLING_LOTTERY_ITEM_CACHE, user_id, jiangling_lottery);
        }

        return jiangling_lottery;
    }

    public Boolean save(JianglingLottery jiangling_lottery, String system_create_user_id) {
        Boolean success = jianglingLotteryDao.save(jiangling_lottery, system_create_user_id);
        return success;
    }

    public Boolean update(JianglingLottery jiangling_lottery, String user_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(JianglingLottery.SYSTEM_STATUS, true);
        cnd.and(JianglingLottery.USER_ID, user_id);
        cnd.and(JianglingLottery.SYSTEM_VERSION, system_version);

        Boolean success = jianglingLotteryDao.update(jiangling_lottery, system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(JIANGLING_LOTTERY_ITEM_CACHE, user_id);
        }

        return success;
    }

    public Boolean delete(String user_id, String system_update_user_id, Integer system_version) {
        Cnd cnd = new Cnd();
        cnd.where(JianglingLottery.SYSTEM_STATUS, true);
        cnd.and(JianglingLottery.USER_ID, user_id);
        cnd.and(JianglingLottery.SYSTEM_VERSION, system_version);

        Boolean success = jianglingLotteryDao.delete(system_update_user_id, system_version, cnd);

        if (success) {
            CacheUtil.remove(JIANGLING_LOTTERY_ITEM_CACHE, user_id);
        }

        return success;
    }
    
    public Boolean save(String request_app_id, String request_user_id, boolean lottery_user_sex, String lottery_user_mobile) {
        JianglingLottery bean = find(request_user_id);
        
        if (bean != null) {
            return false;
        }
        
        JianglingLottery jiangling_lottery = new JianglingLottery();
        jiangling_lottery.setUser_id(request_user_id);
        jiangling_lottery.setApp_id(request_app_id);
        jiangling_lottery.setLottery_user_sex(lottery_user_sex);
        jiangling_lottery.setLottery_number("");
        jiangling_lottery.setLottery_time(0);
        jiangling_lottery.setLottery_user_mobile(lottery_user_mobile);
        jiangling_lottery.setLottery_status(false);
        
        return save(jiangling_lottery, request_user_id);
    }
    
    /**
     * 抽签
     * @param request_user_id
     * @return
     */
    public String draw(String request_app_id, String request_user_id) {
        JianglingLottery bean = find(request_user_id);
        if (bean == null) {
            throw new RuntimeException("请选择完善用户资料");
        }
        if (!ValidateUtil.isNullOrEmpty(bean.getLottery_number())) {
            return bean.getLottery_number();
        }
        if (bean.getLottery_status() && bean.getLottery_time() > 5) {
            throw new RuntimeException("抽签次数已经用完");
        }
        synchronized (this) {
            System.out.println("request_user_id: " + request_user_id);
            long startTime = System.currentTimeMillis();
            System.out.println("startTime: " + startTime);
            List<String> numberList = numberList(request_app_id, bean.getLottery_user_sex());
            //计算抽签概率
            if (numberList.size() == 0) {
                throw new RuntimeException("号码已被抽完了");
            }
            if (numberList.size() > 200) { //前800名抽签概率为80%
                if (ProbabilityUtil.random(0.8)) {
                    return getLottery_number(numberList, bean, request_user_id, request_app_id);
                }
            } else {
                if (ProbabilityUtil.random(0.5)) { //后200名抽签概率为50%
                    return getLottery_number(numberList, bean, request_user_id, request_app_id);
                } 
            }
            long endTime = System.currentTimeMillis();
            System.out.println("endTime: " + endTime);
            System.out.println("程序运行时间：" + (endTime - startTime) + "ms");
        }
        //未抽中，则更新用户抽签次数，抽签次数加一
        bean.setLottery_time(bean.getLottery_time() + 1);
        this.update(bean, request_user_id, request_user_id, bean.getSystem_version());
        return null;
    }
    
    public String getLottery_number(List<String> numberList, JianglingLottery bean, String request_user_id, String request_app_id) {
        int index = new Random().nextInt(numberList.size());
        String lottery_number = numberList.get(index); //随机获取一个未抽取的号码
        bean.setLottery_number(lottery_number);
        bean.setLottery_time(bean.getLottery_time() + 1);
        boolean result = this.update(bean, request_user_id, request_user_id, bean.getSystem_version());
        if (result) {
            numberList.remove(index);
            if (bean.getLottery_user_sex()) {
                CacheUtil.put(JIANGLING_LOTTERY_MAN_UNUSED_NUMBER_LIST_CACHE, request_app_id, numberList);
            } else {
                CacheUtil.put(JIANGLING_LOTTERY_WOMEN_UNUSED_NUMBER_LIST_CACHE, request_app_id, numberList);
            }
            return lottery_number; 
        }
        return null;
    }

}