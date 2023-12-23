package Storage;

public class PointOfSale extends Storage{
    int revenue;
    public void selling(Human human){
        if(isState()){
            String answer;
            do{
                System.out.println("=======| В ПРОДАЖЕ |========\n" +products.toString());
                System.out.println("Выберите товар который вы хотите купить");
                int tempAnswer = scan.nextInt();
                revenue += products.get(tempAnswer - 1).price;
                products.remove(tempAnswer - 1);
                if(products.size() > 0){
                    System.out.println("Спасибо за покупку! Что-то еще ? yes/no");
                    answer = scan.next();
                }else{
                    answer = "no";
                }
            }while (answer.equalsIgnoreCase("lf") || answer.equalsIgnoreCase("да") || answer.equalsIgnoreCase("yes"));
            System.out.println("Чао персик дозревай");
        }else{
            System.out.println("Пункт закрыт");
        }
    }
    @Override
    public void setInfo(){
        System.out.println("====| Заполните информацию о пункте продаж |====");
        super.setInfo();
    }
    @Override
    public void getInfo(){
        super.getInfo();
        System.out.println("Доход: " + revenue);
    }
}
