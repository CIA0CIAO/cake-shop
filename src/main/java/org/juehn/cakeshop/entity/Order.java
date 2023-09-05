package org.juehn.cakeshop.entity;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

import java.sql.Timestamp;

public class Order {
    private int id = -1;
    private String goods;
    private int receiverId;
    private Timestamp createTime;
    private String status;
    private double total;

    @JsonGetter
    public int getId() {
        return id;
    }

    @JsonSetter
    public void setId(int id) {
        this.id = id;
    }

    @JsonGetter
    public String getGoods() {
        return goods;
    }

    @JsonSetter
    public void setGoods(String goods) {
        this.goods = goods;
    }

    @JsonGetter
    public int getReceiverId() {
        return receiverId;
    }

    @JsonSetter
    public void setReceiverId(int receiverId) {
        this.receiverId = receiverId;
    }

    @JsonGetter
    public Timestamp getCreateTime() {
        return createTime;
    }

    @JsonSetter
    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @JsonGetter
    public String getStatus() {
        return status;
    }

    @JsonSetter
    public void setStatus(String status) {
        this.status = status;
    }

    @JsonGetter
    public double getTotal() {
        return total;
    }

    @JsonSetter
    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", goods=" + goods +
                ", receiverId=" + receiverId +
                ", createTime=" + createTime +
                ", status='" + status + '\'' +
                '}';
    }
}
