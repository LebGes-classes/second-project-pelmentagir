package Storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Warehouse extends Storage{
    Scanner scan = new Scanner(System.in);
    List<Dealer> dealers;


    @Override
    public String toString() {
        return "Warehouse{" +
                "products=" + products +
                '}';
    }


    public void purchase() throws IOException {
        Path filePath = Path.of("C:/Users/HP/IdeaProjects/storage/root.json");
        String content = Files.readString(filePath);
        dealers = JsonParser.parseJson(content);
        String answer;
        int temper;
        if(isState()){
            do {
                System.out.println("=======| ВЫБЕРИТЕ ПОСТАВЩИКА |=======");
                for (int i = 0; i < 8; i++) {
                    int z = i + 1;
                    System.out.println(z + ") " + dealers.get(i).name);
                }
                System.out.println("Выберите поставщика от 1-8");
                temper = scan.nextInt();
                try {
                    for (int i = 0; i < dealers.get(temper - 1).getProducts().size(); i++) {
                        int z = i + 1;
                        System.out.println(z + ") " + dealers.get(temper - 1).getProducts().get(i).toString());
                    }
                    do {
                        System.out.println("Выберите товар от 1-8");
                        int product = scan.nextInt();
                        try {
                            if (filling(temper, product)) {
                                products.add(dealers.get(temper - 1).getProducts().get(temper-1));
                                setFill(getFill() + dealers.get(temper - 1).getProducts().get(product - 1).size);
                                System.out.println("\uD83D\uDED2: " + products.get(temper-1).name);
                                System.out.println("Оставшиейся вместительность: " + (getCapacity()-getFill()));
                            }else{
                                System.out.println("УУААААА а.. а че не я больной дебил никуда я не пойду УААААААА");
                            }
                        } catch (Exception E) {
                            System.out.println("приплыли");
                        }
                        System.out.println("Вернуться обратно?  yes/no");
                        answer = scan.next();
                    } while (answer.equalsIgnoreCase("no") || answer.equalsIgnoreCase("нет") || answer.equals("нус"));
                } catch (Exception E) {
                    System.out.println("НЕ ТОПИ!! ПРИВЫКНИ К АППАРАТУ");
                }
                System.out.println("Хотите продолжить покупку? yes/no");
                answer = scan.next();


            } while (temper > 0 && temper < 9 && (answer.equalsIgnoreCase("yes") || answer.equalsIgnoreCase("да") || answer.equals("lf")));
        }else{
            System.out.println("Склад закрыт");
        }
    }

    public boolean filling(int temler, int product){
        int temp = dealers.get(temler - 1).getProducts().get(product - 1).size;
        if(getCapacity() > getFill() + temp){
            return true;
        }else{
            return false;
        }
    }
    @Override
    public void setInfo(){
        System.out.println("====| Заполните информацию о cкладе |====");
        super.setInfo();
    }

}
