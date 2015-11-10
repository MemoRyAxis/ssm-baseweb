package com.memory.base.model;

import java.io.Serializable;
import java.util.Date;

import com.memory.base.constant.OrderSeq;

/**
 * base model
 *
 * @author memoryaxis@gmail.com
 * @date Oct 27, 2015
 */
public class BaseModel implements Serializable {

    private static final long serialVersionUID = 1L;

    protected Long createBy;

    protected Long updateBy;

    protected Date createTime;

    protected Date updateTime;

    protected Date beginCreateTime;

    protected Date endCreateTime;

    protected Date beginUpdateTime;

    protected Date endUpdateTime;

    protected Integer start;

    protected Integer offset;

    protected Integer pageSize;

    protected Integer pageNo;

    protected String orderField;

    protected OrderSeq orderSeq;

    public Long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    public Long getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Long updateBy) {
        this.updateBy = updateBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getBeginCreateTime() {
        return beginCreateTime;
    }

    public void setBeginCreateTime(Date beginCreateTime) {
        this.beginCreateTime = beginCreateTime;
    }

    public Date getEndCreateTime() {
        return endCreateTime;
    }

    public void setEndCreateTime(Date endCreateTime) {
        this.endCreateTime = endCreateTime;
    }

    public Date getBeginUpdateTime() {
        return beginUpdateTime;
    }

    public void setBeginUpdateTime(Date beginUpdateTime) {
        this.beginUpdateTime = beginUpdateTime;
    }

    public Date getEndUpdateTime() {
        return endUpdateTime;
    }

    public void setEndUpdateTime(Date endUpdateTime) {
        this.endUpdateTime = endUpdateTime;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public String getOrderField() {
        return orderField;
    }

    public void setOrderField(String orderField) {
        this.orderField = orderField;
    }

    public OrderSeq getOrderSeq() {
        return orderSeq;
    }

    public void setOrderSeq(OrderSeq orderSeq) {
        this.orderSeq = orderSeq;
    }

    public Integer getOffset() {
        return offset;
    }
}
