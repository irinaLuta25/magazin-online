package model;

import model.enums.Category;
import model.enums.Size;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ClothingProduct extends AProduct implements Serializable {
    private Size size;
    private String color;
    private List<String> fabric;
    private Category category;

    public ClothingProduct( int stock,String name, double price, String brand, Size size, String color, List fabric, Category category) {
        super(stock, name, price,brand);
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
    public String toFileString() {
        return String.join(";", "ClothingProduct", name, String.valueOf(price), brand,
                String.valueOf(stock), size.name(), color, String.join(",", fabric),
                category.name());
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ClothingProduct{");
        sb.append("size=").append(size);
        sb.append(", color='").append(color).append('\'');
        sb.append(", fabric=").append(fabric);
        sb.append(", category=").append(category);
        sb.append(", stock=").append(stock);
        sb.append(", name='").append(name).append('\'');
        sb.append(", price=").append(price);
        sb.append(", brand='").append(brand).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
