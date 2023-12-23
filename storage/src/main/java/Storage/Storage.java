package Storage;


import com.github.cliftonlabs.json_simple.Jsoner;
import com.github.cliftonlabs.json_simple.JsonArray;
import org.json.simple.JSONObject;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public abstract class Storage {
    static Scanner scan = new Scanner(System.in);
    private String id;
    private String address;
    private String idResponsiblePerson;
    private double fill;
    private double capacity;
    ResponsiblePerson responsibleperson = new ResponsiblePerson();
    List<Product> products = new ArrayList();
//    List<Integer> productsSize = new ArrayList<>();
//
//    List<Integer> productsPrice = new ArrayList<>();
    private boolean state;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIdResponsiblePerson() {
        return idResponsiblePerson;
    }

    public ResponsiblePerson getResponsibleperson() {
        return responsibleperson;
    }

    public void setIdResponsiblePerson(String idResponsiblePerson) {
        responsibleperson.setPassport();
        responsibleperson.setId(idResponsiblePerson);
        this.idResponsiblePerson = idResponsiblePerson;
    }

    public double getFill() {
        return fill;
    }

    public void setFill(double fill) {
        this.fill = fill;
    }

    public double getCapacity() {
        return capacity;
    }

    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public void setInfo(){
        String tempInfoStr;
        int tempInfoInt;
        setFill(0);
        setState(true);
        do {
            System.out.println("~~~~ Введите id ~~~~");
            tempInfoStr = scan.next();
            setId(tempInfoStr);
        }while(tempInfoStr.isEmpty());


        do {
            System.out.println("~~~~ Введите адрес ~~~~");
            tempInfoStr = scan.next();
            setAddress(tempInfoStr);
        }while(tempInfoStr.isEmpty());

        do {
            System.out.println("~~~~ Введите вместительность ~~~~");
            tempInfoInt = scan.nextInt();
            setCapacity(tempInfoInt);
        }while(tempInfoInt <= 0);
        do {
            System.out.println("~~~~ Введите id ответственного лица ~~~~");
            tempInfoStr = scan.next();
            setIdResponsiblePerson(tempInfoStr);
        }while(tempInfoStr.isEmpty());
        getInfo();
    }
    public void getInfo(){
        if (state){
            System.out.println("=========== ИНФОРМАЦИЯ ===========");
            System.out.println("\tID: " +getId());
            System.out.println("\tАдрес: " +getAddress());
            System.out.println("\tВместительность: " +getCapacity());
            System.out.println("\tЗаполненость: " +getFill());
            System.out.println("\tID ответсвенного лица: " +getIdResponsiblePerson());
            System.out.println("\tТовары: " + products.toString());
        }else{
            System.out.println("Закрыто");
        }
    }
    public void swapResponsiblePerson(Storage storage){
        String id = getIdResponsiblePerson();
        setIdResponsiblePerson(storage.getIdResponsiblePerson());
        storage.setIdResponsiblePerson(id);
    }
    public void exchangeProduct(Storage storage) throws IOException {
        String answer;
        if(state && storage.state){
            do{
                System.out.println("Какой товар вы бы хотели перенести?\nТовары: ");
                for (int i = 0; i < products.size(); i++) {
                    System.out.println((i+1) + products.get(i).name);
                }
                int tempAnswer = scan.nextInt();
                if(filling(storage,tempAnswer)){
                    storage.products.add(products.get(tempAnswer - 1));
                    setFill(getFill()-products.get(tempAnswer - 1).size);
                    products.remove(tempAnswer - 1);
                    System.out.println("Товар перенесен");
                }else {
                    System.out.println("Без шансов");
                }
                if(products.size() > 0){
                    System.out.println("Продолжаем? yes/no");
                    answer = scan.next();
                }else{
                    answer = "no";
                }
            }while (answer.equalsIgnoreCase("yes") || answer.equalsIgnoreCase("да"));
        }else{
            System.out.println("Закрыто");
        }
    }
    public boolean filling(Storage storage,int tempAnswer) throws IOException {
        if(storage.getCapacity() > storage.getFill() + products.get(tempAnswer - 1).size){
            return true;
        }else{
            return false;
        }
    }
    public void closing(Storage storage) throws InterruptedException {
        setState(false);
        storage.products.addAll(products);
        //storage.productsSize.addAll(productsSize);
        //storage.productsPrice.addAll(productsPrice);
        products.removeAll(products);
        //productsSize.removeAll(productsSize);
        //productsPrice.removeAll(productsPrice);
        int timeInSeconds = 15; // 1 минута = 60 секунд

        for (int i = timeInSeconds; i >= 0; i--) {
            System.out.print("\rЗакрытие произойдет через: " + i + " сек.");
            Thread.sleep(1000); // Пауза на 1 секунду
        }

        System.out.println("\nЗакрыто!");
    }
    public void write (String Data) {
        try {
            BufferedWriter writer = Files.newBufferedWriter(Paths.get(Data));

            JSONObject storageJsonObject = new JSONObject();
            JSONObject responsiblePersonObject = new JSONObject();
            JsonArray productCellsArray = new JsonArray();

            responsiblePersonObject.put("ID responsiblePerson", getIdResponsiblePerson());
            responsiblePersonObject.put("Initials", getResponsibleperson().getInitials());
            responsiblePersonObject.put("dateOfBirth", getResponsibleperson().getDataBirth());

            for (int i = 0; i < products.size(); i++) {
                JSONObject productObject = new JSONObject();

                productObject.put("name", products.get(i).name);
                productObject.put("price", products.get(i).price);
                productObject.put("size", products.get(i).size);

                productCellsArray.add(productObject);
            }

            storageJsonObject.put("id", id);
            storageJsonObject.put("address", address);
            storageJsonObject.put("capacity", capacity);
            storageJsonObject.put("fill", fill);
            storageJsonObject.put("responsiblePerson", responsiblePersonObject);
            storageJsonObject.put("productCells", productCellsArray);

            Jsoner.serialize(storageJsonObject, writer);

            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
