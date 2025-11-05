package model;

import java.io.Serializable;

public abstract class AProduct implements Serializable {
    protected int stock;
    protected String name;
    protected double price;
    protected String brand;

    public AProduct(int stock, String name, double price, String brand) {
        this.name = name;
        this.price = price;
        this.brand = brand;
        this.stock = stock;
    }

    public abstract String toFileString();


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("AProduct{");
        sb.append("name='").append(name).append('\'');
        sb.append(", price=").append(price);
        sb.append(", brand='").append(brand).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
