package com.nowui.chuangshi.util;

import io.socket.client.Ack;
import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import org.json.JSONObject;

import java.net.URISyntaxException;

public class SocketUtil {

    private static Socket socket;

    static {
        try {
            socket = IO.socket("http://localhost:2999");
            socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {

                @Override
                public void call(Object... args) {

                }

            }).on("http", new Emitter.Listener() {

                @Override
                public void call(Object... args) {
                    String object = (String) args[0];
                    System.out.println(object.toString());
                }

            }).on(Socket.EVENT_DISCONNECT, new Emitter.Listener() {

                @Override
                public void call(Object... args) {

                }

            });

            socket.connect();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public static void sendHttp(String object) {
        socket.emit("http", object, new Ack() {
            @Override
            public void call(Object... args) {
                JSONObject object = (JSONObject) args[0];
                System.out.println(object.toString());
            }
        });
    }

}
