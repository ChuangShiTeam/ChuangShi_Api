package com.nowui.chuangshi.common.socket;

import io.socket.client.Ack;
import io.socket.client.IO;
import io.socket.client.Socket;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;

public class MinHangSocket {

    public static final MinHangSocket instance = new MinHangSocket();
    private static Socket socket;

    public MinHangSocket() {
        try {
            socket = IO.socket("http://121.40.44.121:2998");

            socket.connect();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage() {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("targetId", "0");
            jsonObject.put("action", "loadMember");
            jsonObject.put("data", "");

            socket.emit("sendMessage", jsonObject, new Ack() {
                public void call(Object... args) {
                    JSONObject object = (JSONObject) args[0];
                    System.out.println(object.toString());
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        MinHangSocket.instance.sendMessage();
    }

}
