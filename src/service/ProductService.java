package service;

import model.AProduct;

import java.util.ArrayList;
import java.util.List;

public class ProductService extends AbstractService<AProduct> {
    public List<AProduct> getByBrand(String brand) {
        List<AProduct> products = new ArrayList<>();
        for(AProduct product : items) {
            if(product.getBrand().equalsIgnoreCase(brand))  {
                products.add(product);
            }
        }
        return products;
    }

    public List<AProduct> getByPrice(double minPrice, double maxPrice) {
        List<AProduct> products = new ArrayList<>();
        for(AProduct product : items) {
            if(product.getPrice() >= minPrice && product.getPrice() <= maxPrice)  {
                products.add(product);
            }
        }
        return products;
    }
}
