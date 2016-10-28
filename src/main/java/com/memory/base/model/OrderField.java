package com.memory.base.model;


import com.memory.base.constant.OrderSeq;

/**
 * Author: MemoRyAxis
 * Date: 16-10-28
 * Time: 下午5:30
 */
public class OrderField {

    private String orderField;

    private OrderSeq orderSeq;

    public OrderField(String orderField, OrderSeq orderSeq) {
        this.orderField = orderField;
        this.orderSeq = orderSeq;
    }

    public String getOrderField() {
        return orderField;
    }

    public OrderSeq getOrderSeq() {
        return orderSeq;
    }

    public void setOrderField(String orderField) {
        this.orderField = orderField;
    }

    public void setOrderSeq(OrderSeq orderSeq) {
        this.orderSeq = orderSeq;
    }

}
