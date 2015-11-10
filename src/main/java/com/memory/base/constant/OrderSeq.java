package com.memory.base.constant;

/**
 * order sequence
 *
 * @author memoryaxis@gmail.com
 * @date 2015/11/9 19:01
 */
public enum OrderSeq {

    ASC("DESC"),
    DESC("DESC");

    private final String seq;

    private OrderSeq(String seq) {
        this.seq = seq;
    }

    public String getSeq() {
        return seq;
    }
}
