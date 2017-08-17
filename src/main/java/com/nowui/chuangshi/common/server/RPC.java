package com.nowui.chuangshi.common.server;

import hprose.client.HproseClient;

import java.io.IOException;
import java.net.URISyntaxException;

public class RPC {

    public HproseClient client;

    public static final RPC me = new RPC();

    public RPC () {
        try {
            client = HproseClient.create("http://localhost:8089");

        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
