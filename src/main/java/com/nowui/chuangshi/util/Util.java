package com.nowui.chuangshi.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jfinal.kit.PathKit;
import com.nowui.chuangshi.constant.Constant;

public class Util {

    public static String getRandomUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public static String getRandomStringByLength(int length) {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    public static String getEmoji(String source) {
        if (!ValidateUtil.isNull(source)) {
            return source.replaceAll("[\\ud800\\udc00-\\udbff\\udfff\\ud800-\\udfff]", "*");
        } else {
            return source;
        }
    }

    public static Object[][] getObjectArray(List<Object[]> parameterList) {
        Object[][] parameterArray = new Object[parameterList.size()][];
        int i = 0;
        for (Object[] oo : parameterList) {
            parameterArray[i++] = oo;
        }

        return parameterArray;
    }

    public static String getFixLenthString(int strLength) {
        Random rm = new Random();

        double pross = (1 + rm.nextDouble()) * Math.pow(10, strLength);

        String fixLenthString = String.valueOf(pross);

        return fixLenthString.substring(1, strLength + 1);
    }

    public static String get() {
        List<String> list = new ArrayList<String>();
        List<JSONObject> provinceList = new ArrayList<JSONObject>();
        List<JSONObject> cityList = new ArrayList<JSONObject>();
        List<JSONObject> areaList = new ArrayList<JSONObject>();

        try {
            String encoding = "UTF-8";
            File file = new File(PathKit.getWebRootPath() + "/WEB-INF/classes/china.txt");
            if (file.isFile() && file.exists()) {
                InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                while ((lineTxt = bufferedReader.readLine()) != null) {
                    list.add(lineTxt);
                }
                read.close();
            } else {
                System.out.println("找不到指定的文件");
            }
        } catch (Exception e) {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }

        for (String lineTxt : list) {
            if (lineTxt.contains("province")) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("value", lineTxt.split("province")[0]);
                jsonObject.put("label", lineTxt.split("province")[1]);
                provinceList.add(jsonObject);
            }

            if (lineTxt.contains("city")) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("value", lineTxt.split("city")[0]);
                jsonObject.put("label", lineTxt.split("city")[1]);
                cityList.add(jsonObject);
            }

            if (lineTxt.contains("area")) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("value", lineTxt.split("area")[0]);
                jsonObject.put("label", lineTxt.split("area")[1]);
                areaList.add(jsonObject);
            }
        }

        for (JSONObject city : cityList) {
            List<JSONObject> children = new ArrayList<JSONObject>();

            for (JSONObject area : areaList) {
                if (city.getString("value").substring(0, 4).equals(area.getString("value").substring(0, 4))) {
                    if (!area.getString("label").equals("市辖区")) {
                        children.add(area);
                    }
                }
            }

            city.put("children", children);
        }

        for (JSONObject province : provinceList) {
            List<JSONObject> children = new ArrayList<JSONObject>();

            for (JSONObject city : cityList) {
                if (province.getString("value").substring(0, 2).equals(city.getString("value").substring(0, 2))) {
                    if (city.getString("label").equals("市辖区")) {
                        city.put("label", province.getString("label"));
                    }

                    children.add(city);
                }
            }

            province.put("children", children);

            province.put("label", province.getString("label").replace("市", ""));
        }

        return JSONArray.toJSONString(provinceList);
    }

    public static String getThumbnail_path(String path) {
        return path.substring(0, path.lastIndexOf("/")) + "/" + Constant.THUMBNAIL + "/"
                + path.substring(path.lastIndexOf("/") + 1, path.length());
    }

    public static String getOriginal_path(String path) {
        return path.substring(0, path.lastIndexOf("/")) + "/" + Constant.ORIGINAL + "/"
                + path.substring(path.lastIndexOf("/") + 1, path.length());
    }

    public static byte[] aesEncryptToBytes(String content, String encryptKey) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        kgen.init(128);
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(encryptKey.getBytes(), "AES"));

        return cipher.doFinal(content.getBytes("utf-8"));
    }

    public static Map<String, Object> Obj2Map(Object obj) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();

        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            map.put(field.getName(), field.get(obj));
        }

        return map;
    }

    // 获取4位随机数
    public static int getRandomNumber() {
        // 位数 1000
        return (int) ((Math.random() * 9 + 1) * 1000);
    }

    // 获取4位随机数
    public static String getRandomNumber(int length) {
        String result = "";

        Random random = new Random();
        for (int i = 0; i < length; i++) {
            result += random.nextInt(10);
        }

        return result;
    }
}