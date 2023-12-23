package Storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        Warehouse warehouse = new Warehouse();
        PointOfSale pointOfSale = new PointOfSale();
        Customer ultramegasupervojak3000 = new Customer();
        warehouse.setInfo();
        pointOfSale.setInfo();
        warehouse.purchase();
        warehouse.exchangeProduct(pointOfSale);
        pointOfSale.selling(ultramegasupervojak3000);
        warehouse.write("data.json");


    }
}