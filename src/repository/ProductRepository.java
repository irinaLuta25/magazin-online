package repository;

import model.AProduct;
import model.BookProduct;
import model.ClothingProduct;
import model.ElectronicProduct;
import model.enums.Category;
import model.enums.Genre;
import model.enums.Size;

import java.util.Arrays;

public class ProductRepository  extends AbstractTextRepository<AProduct> {
    public ProductRepository(String filename) {
        super(filename);
    }

    @Override
    protected String convertToLine(AProduct p) {
        return p.getClass().getSimpleName() + ";" +
                p.getName() + ";" +
                p.getBrand() + ";" +
                p.getPrice() + ";" +
                p.getStock();
    }

    @Override
    protected AProduct parseLine(String line) {
        String[] tokens = line.split(";");
        String productType = tokens[0];

        switch (productType) {
            case "ClothingProduct":
                return new ClothingProduct(
                        Integer.parseInt(tokens[4]),
                        tokens[1],
                        Double.parseDouble(tokens[2]),
                        tokens[3],
                        Size.valueOf(tokens[5]),
                        tokens[6],
                        Arrays.asList(tokens[7].split(",")),
                        Category.valueOf(tokens[8])
                );

            case "ElectronicProduct":
                return new ElectronicProduct(
                        tokens[1],
                        Double.parseDouble(tokens[2]),
                        tokens[3],
                        Integer.parseInt(tokens[4]),
                        Integer.parseInt(tokens[5]),
                        Boolean.parseBoolean(tokens[6]),
                        Double.parseDouble(tokens[7])
                );

            case "BookProduct":
                return new BookProduct(
                        tokens[1],
                        Double.parseDouble(tokens[2]),
                        tokens[3],
                        Integer.parseInt(tokens[4]),
                        tokens[5],
                        Integer.parseInt(tokens[6]),
                        Integer.parseInt(tokens[7]),
                        Genre.valueOf(tokens[8])
                );

            default:
                throw new IllegalArgumentException("Unknown product type: " + productType);
        }
    }
}
