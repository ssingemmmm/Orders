package com.xingzhi.orders.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ClassifiedProduct {
    @Id
    private int id;
    private int productId;
    private int categoryId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public String toString() {
        return "ClassifiedProduct{" +
                "id=" + id +
                ", productId=" + productId +
                ", categoryId=" + categoryId +
                '}';
    }
}
