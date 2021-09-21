package Business;

import java.util.ArrayList;
import java.util.List;

import Model.Category;
import Model.Product;

public class ProductBusiness {

    private static ProductBusiness instance;

    public static synchronized ProductBusiness getInstance() {
        if(instance==null) instance = new ProductBusiness();
        return instance;
    }

    private ProductBusiness() {}

    public List<Product> getAllProducts() {
        // TODO usare la classe ProductDAO per inviare la query al db che mi prende tutti i prodotti
        List<Product> list = new ArrayList<>();

        Product p1 = new Product();
        p1.setId(0);
        p1.setName("Lavandino");
        p1.setPrice(100.0F);
        Category cp1 = new Category();
        cp1.setName("Bagno");
        p1.setCategory(cp1);

        Product p2 = new Product();
        p2.setId(1);
        p2.setName("Tavolino");
        p2.setPrice(50.0F);
        Category cp2 = new Category();
        cp2.setName("Giardino");
        p2.setCategory(cp2);

        Product p3 = new Product();
        p3.setId(2);
        p3.setName("Mobile");
        p3.setPrice(80.0F);
        Category cp3 = new Category();
        cp3.setName("Casa");
        p3.setCategory(cp3);

        list.add(p1);
        list.add(p2);
        list.add(p3);

        return list;
    }
}
