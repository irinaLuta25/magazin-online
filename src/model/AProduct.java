package model;

public abstract class AProduct {
    protected String name;
    protected double price;
    protected String brand;
    protected int stock;

    public AProduct(String name, double price, String brand, int stock) {
        this.name = name;
        this.price = price;
        this.brand = brand;
        this.stock = stock;
    }


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
