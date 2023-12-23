package Storage;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


class Dealer {
    public Dealer() {

    }

    public String getName() {
        return name;
    }

    public List<Product> getProducts() {
        return products;
    }

    public Dealer(String name, List<Product> products) {
        this.name = name;
        this.products = products;
    }

    @Override
    public String toString() {
        return "\n=====| ПОСТАВЩИК |=====\n" +
                "=====| "+ name + " |=====" +
                "\nПродукты: \n" + products.toString();
    }

    String name;
    List<Product> products;
}
