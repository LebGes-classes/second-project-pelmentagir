package Storage;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;



class Product {
    public Product(String name, int price, int size) {
        this.name = name;
        this.price = price;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getSize(){
        return size;
    }



    @Override
    public String toString() {
        return "Продукт: " + name + ", Цена: " + price + ", Размер: " + size;}

    String name;
    int price;
    int size;
}
