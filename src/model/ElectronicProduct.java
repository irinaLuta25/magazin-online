package model;

import java.io.Serializable;

public class ElectronicProduct extends AProduct implements Serializable {
    private int warrantyMonths;
    private boolean isSmart;
    private double weight;

    public ElectronicProduct(String name, double price, String brand, int stock, int warrantyMonths, boolean isSmart, double weight) {
        super(stock, name, price, brand);
        this.warrantyMonths = warrantyMonths;
        this.isSmart = isSmart;
        this.weight = weight;
    }

    public int getWarrantyMonths() {
        return warrantyMonths;
    }

    public void setWarrantyMonths(int warrantyMonths) {
        this.warrantyMonths = warrantyMonths;
    }

    public boolean isSmart() {
        return isSmart;
    }

    public void setSmart(boolean smart) {
        isSmart = smart;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public String toFileString() {
        return String.join(";",
                "ElectronicProduct",
                name,
                String.valueOf(price),
                brand,
                String.valueOf(stock),
                String.valueOf(warrantyMonths),
                String.valueOf(isSmart),
                String.valueOf(weight));
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ElectronicProduct{");
        sb.append("warrantyMonths=").append(warrantyMonths);
        sb.append(", isSmart=").append(isSmart);
        sb.append(", weight=").append(weight);
        sb.append(", stock=").append(stock);
        sb.append(", name='").append(name).append('\'');
        sb.append(", price=").append(price);
        sb.append(", brand='").append(brand).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
