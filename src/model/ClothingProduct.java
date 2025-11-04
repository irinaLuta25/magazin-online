package model;

import model.enums.Category;
import model.enums.Size;

import java.util.ArrayList;
import java.util.List;

public class ClothingProduct extends AProduct{
    private Size size;
    private String color;
    private List<String> fabric;
    private Category category;

    public ClothingProduct(String name, double price, String brand, int stock, Size size, String color, List fabric, Category category) {
        super(name, price,brand, stock);
        this.size = size;
        this.color = color;
        this.fabric = new ArrayList<>();
        this.category = category;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public List getFabric() {
        return fabric;
    }

    public void setFabric(List fabric) {
        this.fabric = fabric;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ClothingProduct{");
        sb.append("size=").append(size);
        sb.append(", color='").append(color).append('\'');
        sb.append(", fabric=").append(fabric);
        sb.append(", category=").append(category);
        sb.append('}');
        return sb.toString();
    }
}
