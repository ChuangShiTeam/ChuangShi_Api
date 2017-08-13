package com.nowui.chuangshi.common.sql;

public class OrderBy {

    private String key;
    private OrderByType orderByType;

    public OrderBy(String key, OrderByType orderByType) {
        this.key = key;
        this.orderByType = orderByType;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public OrderByType getOrderByType() {
        return orderByType;
    }

    public void setOrderByType(OrderByType orderByType) {
        this.orderByType = orderByType;
    }
}
