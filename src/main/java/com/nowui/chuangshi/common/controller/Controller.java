package com.nowui.chuangshi.common.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jfinal.plugin.activerecord.Model;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.util.ValidateUtil;
import org.apache.http.HttpStatus;

import java.util.*;

public class Controller extends com.jfinal.core.Controller {

    private String[] validateResponseKey = new String[]{};

    public <T> T getModel(Class<T> modelClass) {
        try {
            Object model = modelClass.newInstance();

            if(!(model instanceof Model)) {
                throw new RuntimeException("getModel only support class of Model, using getBean for other class.");
            } else {
                JSONObject jsonObject = getAttr(Constant.REQUEST_PARAMETER);

                if (jsonObject.containsKey(Constant.PAGE_INDEX) && jsonObject.containsKey(Constant.PAGE_SIZE)) {
                }

                ((Model) model).put(jsonObject);

                ((Model) model).put(Constant.APP_ID, getRequest_app_id());

                return (T) model;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public JSONObject getParameterJSONObject() {
        return getAttr(Constant.REQUEST_PARAMETER);
    }

    public void validateRequest(String... keys) {
        JSONObject jsonObject = getAttr(Constant.REQUEST_PARAMETER);

        for (String key : keys) {
            if (ValidateUtil.isNull(jsonObject.get(key))) {
                throw new RuntimeException(key + " is null");
            }
        }
    }

    public void validateRequest_app_id() {

    }

    public void validateResponse(String... keys) {
        validateResponseKey = keys;
    }

    public void setParameter(JSONObject parameter) {
        setAttr(Constant.REQUEST_PARAMETER, parameter);
    }

    public String getPlatform() {
        return getAttrForStr(Constant.PLATFORM);
    }

    public void setPlatform(String platform) {
        setAttr(Constant.PLATFORM, platform);
    }

    public String getVersion() {
        return getAttrForStr(Constant.VERSION);
    }

    public void setVersion(String version) {
        setAttr(Constant.VERSION, version);
    }

    public String getIp_address() {
        return getAttrForStr(Constant.IP_ADDRESS);
    }

    public void setIp_address(String ip_address) {
        setAttr(Constant.IP_ADDRESS, ip_address);
    }

    public void setPage_index(int page_index) {
        setAttr(Constant.PAGE_INDEX, page_index);
    }

    public void setPage_size(int page_size) {
        setAttr(Constant.PAGE_SIZE, page_size);
    }

    public Integer getM() {
        JSONObject jsonObject = getParameterJSONObject();

        int page_index = jsonObject.getIntValue(Constant.PAGE_INDEX);
        int page_size = jsonObject.getIntValue(Constant.PAGE_SIZE);

        if (page_index > 0) {
            return (page_index - 1) * page_size;
        } else {
            return 0;
        }
    }

    public Integer getN() {
        JSONObject jsonObject = getParameterJSONObject();

        int page_size = jsonObject.getIntValue(Constant.PAGE_SIZE);

        return page_size > 0 ? page_size : 0;
    }

    public String getRequest_app_id() {
        return getAttrForStr(Constant.REQUEST_APP_ID);
    }

    public void setRequest_app_id(String request_app_id) {
        setAttr(Constant.REQUEST_APP_ID, request_app_id);
    }

    public String getRequest_http_id() {
        return getAttrForStr(Constant.REQUEST_HTTP_ID);
    }

    public void setRequest_http_id(String request_http_id) {
        setAttr(Constant.REQUEST_HTTP_ID, request_http_id);
    }

    public void validateAdminController() {
        if (ValidateUtil.isNull(getRequest_app_id())) {
            throw new RuntimeException("app_id is null");
        }
    }

//    public void validateRequest_app_id() {
//        if (ValidateUtil.isNull(getRequest_app_id())) {
//            throw new RuntimeException("app_id is null");
//        }
//    }

    public String getRequest_user_id() {
        return getAttrForStr(Constant.REQUEST_USER_ID);
    }

    public void setRequest_user_id(String request_user_id) {
        setAttr(Constant.REQUEST_USER_ID, request_user_id);
    }

    public void authenticateRequest_app_idAndRequest_user_id() {

    }

//    public void authenticateApp_id(String app_id) {
//        if (!getRequest_app_id().equals(app_id)) {
//            throw new RuntimeException(Constant.ERROR_APP);
//        }
//    }
//
//    public void authenticateSystem_create_user_id(String system_create_user_id) {
//        if (!getRequest_user_id().equals(system_create_user_id)) {
//            throw new RuntimeException(Constant.ERROR_USER);
//        }
//    }

    public void renderSuccessJson() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(Constant.CODE, HttpStatus.SC_OK);

        renderJson(map);
    }

    public void renderSuccessJson(Boolean result) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(Constant.CODE, HttpStatus.SC_OK);
        map.put(Constant.DATA, result);

        renderJson(map);
    }

    public void renderSuccessJson(Integer result) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(Constant.CODE, HttpStatus.SC_OK);
        map.put(Constant.DATA, result);

        renderJson(map);
    }

    public void renderSuccessJson(String result) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(Constant.CODE, HttpStatus.SC_OK);
        map.put(Constant.DATA, result);

        renderJson(map);
    }

    public void renderSuccessJson(Model result) {
        if (result != null) {
            result.keep(validateResponseKey);
        }

        Map<String, Object> map = new HashMap<String, Object>();
        map.put(Constant.CODE, HttpStatus.SC_OK);
        map.put(Constant.DATA, result);

        renderJson(map);
    }

    public void renderSuccessJson(Map<String, Object> result) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(Constant.CODE, HttpStatus.SC_OK);
        map.put(Constant.DATA, result);

        renderJson(map);
    }

    public void renderSuccessMapListJson(List<Map<String, Object>> resultList) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(Constant.CODE, HttpStatus.SC_OK);
        map.put(Constant.DATA, resultList);

        renderJson(map);
    }

    public void renderSuccessJson(List<? extends Model> resultList) {
        for (Model result : resultList) {
            result.keep(validateResponseKey);
        }

        Map<String, Object> map = new HashMap<String, Object>();
        map.put(Constant.CODE, HttpStatus.SC_OK);
        map.put(Constant.DATA, resultList);

        renderJson(map);
    }

    public void renderSuccessJson(Integer total, List<? extends Model> resultList) {
        for (Model result : resultList) {
            result.keep(validateResponseKey);
        }

        Map<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put(Constant.TOTAL, total);
        dataMap.put(Constant.LIST, resultList);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put(Constant.CODE, HttpStatus.SC_OK);
        map.put(Constant.DATA, dataMap);

        renderJson(map);
    }

    public void renderSuccessMapListJson(Integer total, List<Map<String, Object>> resultList) {
        List<Map<String, Object>> newResultList = new ArrayList<Map<String, Object>>();
        for (Map<String, Object> result : resultList) {
            Map<String, Object> newResult = new HashMap<String, Object>();
            for (String key : validateResponseKey) {
                for (Map.Entry<String, Object> map : result.entrySet()) {
                    if (key.equals(map.getKey())) {
                        newResult.put(map.getKey(), map.getValue());

                        break;
                    }
                }
            }
            newResultList.add(newResult);
        }

        Map<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put(Constant.TOTAL, total);
        dataMap.put(Constant.LIST, resultList);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put(Constant.CODE, HttpStatus.SC_OK);
        map.put(Constant.DATA, newResultList);

        renderJson(map);
    }

    public void renderErrorJson(String message) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(Constant.CODE, HttpStatus.SC_BAD_REQUEST);
        map.put(Constant.MESSAGE, message);

        renderJson(map);
    }

    public void renderJson(Object object) {
        String response = JSON.toJSONString(object);
        setAttr(Constant.RESPONSE_PARAMETER, response);

        super.renderJson(object);
    }

}
