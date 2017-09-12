package com.nowui.chuangshi.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;
import com.jfinal.kit.HttpKit;
import com.jfinal.plugin.activerecord.DbKit;
import com.nowui.chuangshi.api.http.model.Http;
import com.nowui.chuangshi.constant.Config;
import com.nowui.chuangshi.constant.Constant;
import com.nowui.chuangshi.constant.Url;
import com.nowui.chuangshi.model.User;
import com.nowui.chuangshi.util.*;
import org.apache.http.HttpStatus;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

public class GlobalActionInterceptor implements Interceptor {

    public GlobalActionInterceptor() {

    }

    public void intercept(Invocation invocation) {
        Date start = new Date();
        Connection connection = null;
        Controller controller = invocation.getController();
        String request_http_id = Util.getRandomUUID();
        String request_app_id = "";
        String request_user_id = "";
        String http_url = invocation.getController().getRequest().getRequestURI();
        Integer http_code = HttpStatus.SC_OK;
        JSONObject http_request = new JSONObject();
        String http_token = "";
        String http_platform = "";
        String http_version = "";
        String http_ip_address = HttpUtil.getIpAddress(controller.getRequest());

        try {
            connection = DbKit.getConfig().getDataSource().getConnection();
            DbKit.getConfig().setThreadLocalConnection(connection);
            connection.setAutoCommit(false);

            request_app_id = controller.getRequest().getHeader(Constant.APP_ID);
            http_platform = controller.getRequest().getHeader(Constant.PLATFORM);
            http_version = controller.getRequest().getHeader(Constant.VERSION);
            http_token = controller.getRequest().getHeader(Constant.TOKEN);

            if (ValidateUtil.isNullOrEmpty(request_app_id)) {
                request_app_id = "";
            }

            if (ValidateUtil.isNullOrEmpty(http_platform)) {
                http_platform = "";
            }

            if (ValidateUtil.isNullOrEmpty(http_version)) {
                http_version = "";
            }

            if (ValidateUtil.isNullOrEmpty(http_token)) {
                http_token = "";
            } else {
                JSONObject jsonObject = JSONObject.parseObject(AesUtil.aesDecrypt(http_token, Config.private_key));
                request_user_id = jsonObject.getString(User.USER_ID);
            }

            if (http_url.equals(Url.FILE_UPLOAD) || http_url.equals(Url.FILE_ADMIN_UPLOAD) || http_url.contains("/wechat/") || http_url.contains(Url.EXPRESS_PUSH)) {

            } else {
                http_request = JSONObject.parseObject(HttpKit.readData(controller.getRequest()));
            }

            if (ValidateUtil.isNull(http_request)) {
                http_request = new JSONObject();
            }

            if (controller instanceof com.nowui.chuangshi.controller.Controller) {
                ((com.nowui.chuangshi.controller.Controller) controller).setPlatform(http_platform);
                ((com.nowui.chuangshi.controller.Controller) controller).setVersion(http_version);
                ((com.nowui.chuangshi.controller.Controller) controller).setIp_address(http_ip_address);
                ((com.nowui.chuangshi.controller.Controller) controller).setRequest_app_id(request_app_id);
                ((com.nowui.chuangshi.controller.Controller) controller).setRequest_http_id(request_http_id);
                ((com.nowui.chuangshi.controller.Controller) controller).setRequest_user_id(request_user_id);
                ((com.nowui.chuangshi.controller.Controller) controller).setParameter(http_request);
            } else if (controller instanceof com.nowui.chuangshi.common.controller.Controller) {
                ((com.nowui.chuangshi.common.controller.Controller) controller).setPlatform(http_platform);
                ((com.nowui.chuangshi.common.controller.Controller) controller).setVersion(http_version);
                ((com.nowui.chuangshi.common.controller.Controller) controller).setIp_address(http_ip_address);
                ((com.nowui.chuangshi.common.controller.Controller) controller).setRequest_app_id(request_app_id);
                ((com.nowui.chuangshi.common.controller.Controller) controller).setRequest_http_id(request_http_id);
                ((com.nowui.chuangshi.common.controller.Controller) controller).setRequest_user_id(request_user_id);
                ((com.nowui.chuangshi.common.controller.Controller) controller).setParameter(http_request);
            }

            invocation.invoke();

            connection.commit();
        } catch (Exception e) {
            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException e1) {

            }

            http_code = HttpStatus.SC_INTERNAL_SERVER_ERROR;

            String message = e.toString();
            String value = "java.lang.RuntimeException: ";
            if (message.contains(value)) {
                http_code = HttpStatus.SC_BAD_REQUEST;

                message = message.replace(value, "");
            }

            Map<String, Object> map = new HashMap<String, Object>();
            map.put(Constant.CODE, http_code);
            map.put(Constant.MESSAGE, message);

            controller.renderJson(map);

            if (!http_url.startsWith("/http/")) {
//                Map<String, Object> exceptionMap = new HashMap<String, Object>();
//                exceptionMap.put(com.nowui.chuangshi.model.Exception.APP_ID, request_app_id);
//                exceptionMap.put(com.nowui.chuangshi.model.Exception.HTTP_ID, request_http_id);
//                exceptionMap.put(com.nowui.chuangshi.model.Exception.EXCEPTION_CONTENT, e.toString());
//                exceptionMap.put(com.nowui.chuangshi.model.Exception.SYSTEM_CREATE_USER_ID, request_user_id);
//                MQUtil.sendSync("exception", JSON.toJSONString(exceptionMap));
            }

            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {

            } finally {
                DbKit.getConfig().removeThreadLocalConnection();
            }

            Date end = new Date();
            String http_run_time = String.valueOf((end.getTime() - start.getTime()));
            String http_response = controller.getAttrForStr(Constant.RESPONSE_PARAMETER);
            if (ValidateUtil.isNullOrEmpty(http_response)) {
                http_response = "";
            }

            Http http = new Http();
            http.setHttp_id(request_http_id);
            http.setApp_id(request_app_id);
            http.setHttp_url(http_url);
            http.setHttp_code(String.valueOf(http_code));
            http.setHttp_request(http_request.toJSONString());
            http.setHttp_response(http_response);
            http.setHttp_token(http_token);
            http.setHttp_platform(http_platform);
            http.setHttp_version(http_version);
            http.setHttp_ip_address(http_ip_address);
            http.setHttp_run_time(http_run_time);
            http.setSystem_create_user_id(request_user_id);
            http.setSystem_create_time(start);
            http.setSystem_update_user_id(request_user_id);
            http.setSystem_update_time(start);

            if (http_url.startsWith("/http/") || http_url.contains("/export")) {

            } else {
                MQUtil.sendMessage("http", JSONObject.toJSONString(http));
            }
        }

    }

}
