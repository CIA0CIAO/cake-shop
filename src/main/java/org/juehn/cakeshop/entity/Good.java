package org.juehn.cakeshop.entity;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

public class Good {
    private int id = -1;
    private String name;
    private double price;
    private String desc;
    private String category;
    private int sales;
    private int stock;
    private String cover;
    private double weight;
    private int num;

    @JsonGetter
    public int getId() {
        return id;
    }

    @JsonSetter
    public void setId(int id) {
        this.id = id;
    }

    @JsonGetter
    public String getName() {
        return name;
    }

    @JsonSetter
    public void setName(String name) {
        this.name = name;
    }

    @JsonGetter
    public double getPrice() {
        return price;
    }

    @JsonSetter
    public void setPrice(double price) {
        this.price = price;
    }

    @JsonGetter
    public String getDesc() {
        return desc;
    }

    @JsonSetter
    public void setDesc(String desc) {
        this.desc = desc;
    }

    @JsonGetter
    public String getCategory() {
        return category;
    }

    @JsonSetter
    public void setCategory(String category) {
        this.category = category;
    }

    @JsonGetter
    public int getSales() {
        return sales;
    }

    @JsonSetter
    public void setSales(int sales) {
        this.sales = sales;
    }

    @JsonGetter
    public int getStock() {
        return stock;
    }

    @JsonSetter
    public void setStock(int stock) {
        this.stock = stock;
    }

    @JsonGetter
    public String getCover() {
        return cover;
    }

    @JsonSetter
    public void setCover(String cover) {
        this.cover = cover;
    }

    @JsonGetter
    public double getWeight() {
        return weight;
    }

    @JsonSetter
    public void setWeight(double weight) {
        this.weight = weight;
    }

    @JsonGetter
    public int getNum() {
        return num;
    }

    @JsonSetter
    public void setNum(int num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "Good{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", desc='" + desc + '\'' +
                ", category='" + category + '\'' +
                ", sales=" + sales +
                ", stock=" + stock +
                ", cover='" + cover + '\'' +
                ", weight=" + weight +
                ", num=" + num +
                '}';
    }
}
