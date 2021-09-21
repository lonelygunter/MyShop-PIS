package Model;

import java.util.ArrayList;
import java.util.List;

public class CompositeProduct extends Product {

    private int id;
    private List<IProduct> products;


    public CompositeProduct() {
        this.products = new ArrayList<>();
    }

    public CompositeProduct(int id, List<IProduct> products) {
        this.id = id;
        this.products = products;
    }


    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /**
     * per aggiungere un prodotto alla volta
     */
    public void add(IProduct product) {
        products.add(product);
    }

    /**
     * per aggiungere più prodotti contemporaneamente
     */
    public void add(List<IProduct> products) {
        products.addAll(products);
    }

    public List<IProduct> getList() {
		return this.products;
	}

    public void setList(List<IProduct> products) {
		this.products = products;
	}
}
