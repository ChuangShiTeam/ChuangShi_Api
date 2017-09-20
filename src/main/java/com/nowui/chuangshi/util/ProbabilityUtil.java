package com.nowui.chuangshi.util;

public class ProbabilityUtil {
    
    public static Boolean random(double percent) {
        if (percent <= 0  || percent > 1 ) {
            return false;
        }
        
        if (percent == 1) {
            return true;
        }
        double random = Math.random();
        if (random <= percent) {
            return true;
        }
        
        return false;
    }
    
    public static void main(String args[]) {
        int countTrue = 0;
        int countFalse = 0;
        for (int i = 0; i < 1000; i++) {
            if (random(0.80)) {
                countTrue++; 
            } else {
                countFalse++;
            }
        }
        System.out.println(countTrue);
        System.out.println(countFalse);
    }

}
